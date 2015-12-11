/**********************************************************************************************
 *
 * Asprise Scanning and Imaging API
 * Copyright (C) 1998-2016. Asprise Inc. <asprise.com>
 *
 * This file is licensed under the GNU Affero General Public License version 3 as published by
 * the Free Software Foundation.
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 *
 * You should have received a copy of the GNU Affero General Public License.  If not, please
 * visit <http://www.gnu.org/licenses/agpl-3.0.html>.
 *
 **********************************************************************************************/
package com.asprise.imaging.core;

import com.asprise.imaging.core.scan.twain.TwainException;
import com.asprise.imaging.core.scan.twain.TwainUtil;
import com.asprise.imaging.core.util.system.SoftCache;
import com.asprise.imaging.core.util.system.Utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an output..
 */
public class ResultOutputItem {

    /** Any of {@linkplain Imaging#OUTPUT_SAVE} ... */
    String type;

    /** Output image format */
    String format;

    SoftCache cache = new SoftCache();

    /**
     * List of output records. Depending on output type, record type can be:
     * <ul><li>path to file for {@linkplain Imaging#OUTPUT_SAVE}, {@linkplain Imaging#OUTPUT_SAVE_THUMB};</li>
     * <li>server response for {@linkplain Imaging#OUTPUT_UPLOAD}, {@linkplain Imaging#OUTPUT_UPLOAD_THUMB};</li>
     * <li>image data in base64 for {@linkplain Imaging#OUTPUT_RETURN_BASE64}, {@linkplain Imaging#OUTPUT_RETURN_BASE64_THUMB}.</li>
     * </ul>
     */
    private List<String> outputRecords = new ArrayList<String>();

    protected ResultOutputItem(Map<String, Object> jsonOutputObject) {
        type = TwainUtil.toString(jsonOutputObject.get("type"));
        format = TwainUtil.normalizeImageFormat(TwainUtil.toString(jsonOutputObject.get("format")));
        Object jsonRecords = jsonOutputObject.get("result");
        if(jsonRecords instanceof List) {
            List jsonList = (List)jsonRecords;
            for(int i = 0; i < jsonList.size(); i++) {
                outputRecords.add(TwainUtil.toString(jsonList.get(i)));
            }
        }
    }

    public Map<String, Object> toJsonObject() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("type", type);
        json.put("format", format);
        json.put("mime_type", TwainUtil.getMimeType(format));
        json.put("result", outputRecords);

        return json;
    }

    /**
     * Creates ScanResultOutputItem if the jsonOutputObject is not null.
     * @param jsonOutputObject
     * @return ScanResultOutputItem created or null if jsonOutputObject is null.
     */
    public static ResultOutputItem createScanResultOutputItem(Map<String, Object> jsonOutputObject) {
        if(jsonOutputObject == null) {
            return null;
        } else {
            return new ResultOutputItem(jsonOutputObject);
        }
    }

    /**
     * Output type - any of {@linkplain Imaging#OUTPUT_SAVE} ...
     * @return
     */
    public String getType() {
        return type;
    }

    /**
     * Image format - any of {@linkplain Imaging#FORMAT_JPG} ...
     * @return
     */
    public String getFormat() {
        return format;
    }


    /**
     * Number of records.
     * @return
     */
    public int getOutputRecordCount() {
        return outputRecords == null ? 0 : outputRecords.size();
    }


    /**
     * List of output records. Depending on output type, record type can be:
     * <ul><li>path to file for {@linkplain Imaging#OUTPUT_SAVE}, {@linkplain Imaging#OUTPUT_SAVE_THUMB};</li>
     * <li>server response for {@linkplain Imaging#OUTPUT_UPLOAD}, {@linkplain Imaging#OUTPUT_UPLOAD_THUMB};</li>
     * <li>image data in base64 for {@linkplain Imaging#OUTPUT_RETURN_BASE64}, {@linkplain Imaging#OUTPUT_RETURN_BASE64_THUMB}.</li>
     * </ul>
     * @return List of output records or empty list if there is no record.
     */
    public List<String> getOutputRecords() {
        return Collections.unmodifiableList(outputRecords);
    }

    /**
     * List of output files for {@linkplain Imaging#OUTPUT_SAVE} and {@linkplain Imaging#OUTPUT_SAVE_THUMB}.
     * @return List of output records or null if the type is not {@linkplain Imaging#OUTPUT_SAVE} or {@linkplain Imaging#OUTPUT_SAVE_THUMB}.
     */
    public List<File> getFiles() {
        if(!Imaging.OUTPUT_SAVE.equals(type) && !Imaging.OUTPUT_SAVE_THUMB.equals(type)) {
            return null;
        }
        List<File> files = new ArrayList<File>();
        for(int i = 0; outputRecords != null && i < outputRecords.size(); i++) {
            files.add(new File(outputRecords.get(i)));
        }
        return files;
    }

    /**
     * Loads the image represented by the given record if output type is one of: {@linkplain Imaging#OUTPUT_SAVE}, {@linkplain Imaging#OUTPUT_SAVE_THUMB}, {@linkplain Imaging#OUTPUT_RETURN_BASE64}, and {@linkplain Imaging#OUTPUT_RETURN_BASE64_THUMB}.
     * @param recordIndex
     * @return
     * @throws IndexOutOfBoundsException if recordIndex is not valid.
     * @throws TwainException in case of other errors.
     */
    public BufferedImage loadImage(int recordIndex) {
        Object cached = cache.get(getCacheKey(recordIndex));
        if(cached != null) {
            return (BufferedImage) cached;
        }
        String record = outputRecords.get(recordIndex);
        if(record == null) {
            return null;
        }

        BufferedImage image = null;
        if(Imaging.OUTPUT_SAVE.equals(type) || Imaging.OUTPUT_SAVE_THUMB.equals(type)) {
            String file = null;
            if(Imaging.FORMAT_TIF.equals(type)) { // multi-page
                if(outputRecords.size() == 1) {
                    file = outputRecords.get(0);
                }
            } else if(Imaging.FORMAT_PDF.equals(type)) { // PDF
                throw new RuntimeException("PDF reading is not supported.");
            } else { // bmp, jpg, etc.
                file = outputRecords.get(recordIndex);
            }
            try {
                image = ImageIO.read(new File(file));
            } catch (Throwable e) {
                throw new TwainException("Unable to load image from: " + file, e);
            }
        } else if(Imaging.OUTPUT_RETURN_BASE64.equals(type) || Imaging.OUTPUT_RETURN_BASE64_THUMB.equals(type)) {
            String base64 = null;
            if(Imaging.FORMAT_TIF.equals(type)) { // multi-page
                if(outputRecords.size() == 1) {
                    base64 = outputRecords.get(0);
                }
            } else if(Imaging.FORMAT_PDF.equals(type)) { // PDF
                throw new RuntimeException("PDF reading is not supported.");
            } else { // bmp, jpg, etc.
                base64 = outputRecords.get(recordIndex);
            }

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(Utils.md5Decode(base64));
            try {
                image = ImageIO.read(byteArrayInputStream);
            } catch (Throwable e) {
                throw new TwainException("Unable to load image from base64", e);
            }
        } else {
            throw new TwainException("You can load image when output type is " + type);
        }

        cache.put(getCacheKey(recordIndex), image);
        return image;
    }

    private String getCacheKey(int recordIndex) {
        return hashCode() + "-" + recordIndex;
    }

    @Override
    public String toString() {
        return "ScanResultOutputItem{" +
                "type='" + type + '\'' +
                ", format='" + format + '\'' +
                ", outputRecords=" + outputRecords +
                '}';
    }
}
