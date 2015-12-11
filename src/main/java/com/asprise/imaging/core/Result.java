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

import com.asprise.imaging.core.scan.twain.TwainConstants;
import com.asprise.imaging.core.scan.twain.TwainException;
import com.asprise.imaging.core.scan.twain.TwainUtil;
import com.asprise.imaging.core.util.JsonUtils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents a scan result
 */
public class Result {

    /** Source device */
    String device;
    /** Number of images acquired */
    int imageCount = -1;
    /** Last transfer code, e.g., {@linkplain TwainConstants#TWRC_SUCCESS}. */
    int lastTransferResultCode = -1;

    String requestId;
    String responseId;

    // String rawResult; // could boost memory footprint, so we disable it here.

    List<ResultImageItem> images = new ArrayList<ResultImageItem>();

    List<ResultOutputItem> outputItems = new ArrayList<ResultOutputItem>();

    protected Result(Map<String, Object> jsonScanResult) {
        device = TwainUtil.toString(jsonScanResult.get("device"));
        imageCount = TwainUtil.toInteger(jsonScanResult.get("image_count"), -1);
        lastTransferResultCode = TwainConstants.getTwrcCode(TwainUtil.toString(jsonScanResult.get("last_transfer_rc")));
        requestId = TwainUtil.toString(jsonScanResult.get("request_id"));
        responseId = TwainUtil.toString(jsonScanResult.get("response_id"));

        Object jsonImages = jsonScanResult.get("images");
        if(jsonImages instanceof List) {
            List jsonList = (List)jsonImages;
            for(int i = 0; i < jsonList.size(); i++) {
                images.add(ResultImageItem.createScanResultImageItem((Map<String, Object>) jsonList.get(i)));
            }
        }

        Object jsonOutputs = jsonScanResult.get("output");
        if(jsonOutputs instanceof List) {
            List jsonList = (List)jsonOutputs;
            for(int i = 0; i < jsonList.size(); i++) {
                outputItems.add(ResultOutputItem.createScanResultOutputItem((Map<String, Object>) jsonList.get(i)));
            }
        }
    }

    public Map<String, Object> toJsonObject() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("device", device);
        json.put("image_count", imageCount);
        json.put("last_transfer_rc", lastTransferResultCode);
        json.put("request_id", requestId);
        json.put("response_id", responseId);

        List jsonImages = new ArrayList();
        for(int i = 0; images != null && i < images.size(); i++) {
            jsonImages.add(images.get(i).toJsonObject());
        }
        json.put("images", jsonImages);

        List jsonOutputs = new ArrayList();
        for(int i = 0; outputItems != null && i < outputItems.size(); i++) {
            jsonOutputs.add(outputItems.get(i).toJsonObject());
        }
        json.put("output", jsonOutputs);

        return json;
    }

    public String toJson(boolean pretty) {
        return JsonUtils.jsonSerialize(toJsonObject(), pretty);
    }

    /**
     * Creates ScanResultImageItem if the jsonImageObject is not null.
     * @param jsonImageObject
     * @return ScanResultImageItem created or null if jsonImageObject is null.
     */
    public static Result createScanResult(Map<String, Object> jsonImageObject) {
        if(jsonImageObject == null) {
            return null;
        } else {
            return new Result(jsonImageObject);
        }
    }

    /**
     * The source device from which images are acquired.
     * @return name of the source device
     */
    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    /**
     * Total number of images acquired.
     * @return
     */
    public int getImageCount() {
        return imageCount;
    }

    public void setImageCount(int imageCount) {
        this.imageCount = imageCount;
    }

    /**
     * Last transfer result code, e.g., {@linkplain TwainConstants#TWRC_SUCCESS}.
     * @return
     */
    public int getLastTransferResultCode() {
        return lastTransferResultCode;
    }

    public void setLastTransferResultCode(int lastTransferResultCode) {
        this.lastTransferResultCode = lastTransferResultCode;
    }

    /**
     * Whether user pressed 'Cancel'.
     */
    public boolean isUserCancelled() {
        return getLastTransferResultCode() == TwainConstants.TWRC_CANCEL;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getResponseId() {
        return responseId;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    /**
     * Lists of images acquired.
     * @return
     */
    public List<ResultImageItem> getImageItems() {
        return images;
    }

    /**
     * List of all output items.
     * @return
     */
    public List<ResultOutputItem> getOutputItems() {
        return outputItems;
    }

    /**
     * Finds the first output item of the given type.
     * @param outputType any of {@linkplain Imaging#OUTPUT_SAVE} ...
     * @return the first output item of the list sorted by record count descending then format (JPG, PNG, BMP, TIF, PDF).
     */
    public ResultOutputItem getOutputItem(String outputType) {
        for(int i = 0; outputItems != null && i < outputItems.size(); i++) {
            ResultOutputItem outputItem = outputItems.get(i);
            if(outputItem == null) {
                continue;
            }
            if(outputType.equals(outputItem.getType())) {
                return outputItem;
            }
        }
        return null;
    }

    /**
     * Finds the all output items of the given type.
     * @param outputType any of {@linkplain Imaging#OUTPUT_SAVE} ...
     * @return All output items of the given type sorted by record count descending then format (JPG, PNG, BMP, TIF, PDF).
     */
    public List<ResultOutputItem> getOutputItems(String outputType) {
        List<ResultOutputItem> filtered = new ArrayList<ResultOutputItem>();
        for(int i = 0; outputItems != null && i < outputItems.size(); i++) {
            ResultOutputItem outputItem = outputItems.get(i);
            if(outputItem == null) {
                continue;
            }
            if(outputType.equals(outputItem.getType())) {
                filtered.add(outputItem);
            }
        }

        // order by number of record counts
        Collections.sort(filtered, new Comparator<ResultOutputItem>() {
            public int compare(ResultOutputItem o1, ResultOutputItem o2) {
                int recordDiff = o2.getOutputRecordCount() - o1.getOutputRecordCount();
                if(recordDiff != 0) {
                    return recordDiff;
                }
                return getFileFormatRanking(o1.getFormat()) - getFileFormatRanking(o2.getFormat());
            }
        });
        return filtered;
    }

    private static int getFileFormatRanking(String format) {
        if(Imaging.FORMAT_JPG.equalsIgnoreCase(format)) {
            return 1;
        } else if(Imaging.FORMAT_PNG.equalsIgnoreCase(format)) {
            return 2;
        } else if(Imaging.FORMAT_BMP.equalsIgnoreCase(format)) {
            return 3;
        } else if(Imaging.FORMAT_TIF.equalsIgnoreCase(format)) {
            return 4;
        } else if(Imaging.FORMAT_PDF.equalsIgnoreCase(format)) {
            return 5;
        } else {
            return 6;
        }
    }

    /**
     * Gets the list of image files acquired through {@linkplain Imaging#OUTPUT_SAVE}
     * The
     * @return the list of image files or null if no {@linkplain Imaging#OUTPUT_SAVE} record found.
     */
    public List<File> getImageFiles() {
        ResultOutputItem outputSave = getOutputItem(Imaging.OUTPUT_SAVE);
        return outputSave == null ? null : outputSave.getFiles();
    }

    /**
     * Gets the list of image files acquired through {@linkplain Imaging#OUTPUT_SAVE_THUMB}
     * @return the list of image files or null if no {@linkplain Imaging#OUTPUT_SAVE_THUMB} record found.
     */
    public List<File> getThumbnailFiles() {
        ResultOutputItem outputSave = getOutputItem(Imaging.OUTPUT_SAVE_THUMB);
        return outputSave == null ? null : outputSave.getFiles();
    }

    /**
     * Gets the PDF file acquired through {@linkplain Imaging#OUTPUT_SAVE}.
     * @return the PDF file acquired through {@linkplain Imaging#OUTPUT_SAVE} or null if none is found.
     */
    public File getPdfFile() {
        List<ResultOutputItem> outputItems = getOutputItems(Imaging.OUTPUT_SAVE);
        for(int i = 0; outputItems != null && i < outputItems.size(); i++) {
            ResultOutputItem item = outputItems.get(i);
            if(Imaging.FORMAT_PDF.equalsIgnoreCase(item.getFormat())) {
                List<File> files = item.getFiles();
                return files != null && files.size() > 0 ? files.get(0) : null;
            }
        }
        return null;
    }

    /**
     * Gets the TIFF file acquired through {@linkplain Imaging#OUTPUT_SAVE}.
     * @return the TIFF file acquired through {@linkplain Imaging#OUTPUT_SAVE} or null if none is found.
     */
    public File getTiffFile() {
        List<ResultOutputItem> outputItems = getOutputItems(Imaging.OUTPUT_SAVE);
        for(int i = 0; outputItems != null && i < outputItems.size(); i++) {
            ResultOutputItem item = outputItems.get(i);
            if(item.getFormat() != null && item.getFormat().toLowerCase().contains(Imaging.FORMAT_TIF)) {
                List<File> files = item.getFiles();
                return files != null && files.size() > 0 ? files.get(0) : null;
            }
        }
        return null;
    }

    /**
     * Gets the original image of the given index by searching the output in order: {@linkplain Imaging#OUTPUT_RETURN_BASE64}, {@linkplain Imaging#OUTPUT_SAVE}
     * This is a shortcut method, use {@linkplain #getOutputItems()} if you need more control.
     * @param index
     * @return the image of the given index or null if not available.
     * @throws IndexOutOfBoundsException if index is invalid
     * @throws TwainException if failed to load.
     */
    public BufferedImage getImage(int index) {
        List<ResultOutputItem> outputReturnBase64s = getOutputItems(Imaging.OUTPUT_RETURN_BASE64);
        if(outputReturnBase64s != null && outputReturnBase64s.size() > 0) {
            return outputReturnBase64s.get(0).loadImage(index);
        }
        List<ResultOutputItem> outputSaves = getOutputItems(Imaging.OUTPUT_SAVE);
        if(outputSaves != null && outputSaves.size() > 0) {
            return outputSaves.get(0).loadImage(index);
        }
        return null;
    }

    /**
     * Returns all scanned images.
     * @return
     */
    public List<BufferedImage> getImages() {
        List<BufferedImage> images = new ArrayList<BufferedImage>();
        for(int i = 0; i < getImageCount(); i++) {
            images.add(getImage(i));
        }
        return images;
    }

    /**
     * Gets the thumbnail image of the given index by searching the output in order: {@linkplain Imaging#OUTPUT_RETURN_BASE64_THUMB}, {@linkplain Imaging#OUTPUT_SAVE_THUMB}
     * This is a shortcut method, use {@linkplain #getOutputItems()} if you need more control.
     * @param index
     * @return the image of the given index or null if not available.
     * @throws IndexOutOfBoundsException if index is invalid
     * @throws TwainException if failed to load.
     */
    public BufferedImage getThumbnail(int index) {
        List<ResultOutputItem> outputReturnBase64Thumbs = getOutputItems(Imaging.OUTPUT_RETURN_BASE64_THUMB);
        if(outputReturnBase64Thumbs != null && outputReturnBase64Thumbs.size() > 0) {
            return outputReturnBase64Thumbs.get(0).loadImage(index);
        }
        List<ResultOutputItem> outputSaveThumbs = getOutputItems(Imaging.OUTPUT_SAVE_THUMB);
        if(outputSaveThumbs != null && outputSaveThumbs.size() > 0) {
            return outputSaveThumbs.get(0).loadImage(index);
        }
        return null;
    }

    /**
     * Returns the upload response if available.
     * This is a shortcut method, use {@linkplain #getOutputItems()} if you need more control.
     * @return the upload response or null if not available.
     */
    public String getUploadResponse() {
        ResultOutputItem outputItemUpload = getOutputItem(Imaging.OUTPUT_UPLOAD);
        return outputItemUpload == null || outputItemUpload.getOutputRecordCount() == 0 ?
                null : outputItemUpload.getOutputRecords().get(0);
    }

    /**
     * Returns the thumbnail upload response if available.
     * This is a shortcut method, use {@linkplain #getOutputItems()} if you need more control.
     * @return the thumbnail upload response or null if not available.
     */
    public String getThumbnailUploadResponse() {
        ResultOutputItem outputItemUpload = getOutputItem(Imaging.OUTPUT_UPLOAD_THUMB);
        return outputItemUpload == null || outputItemUpload.getOutputRecordCount() == 0 ?
                null : outputItemUpload.getOutputRecords().get(0);
    }

    public List<String> getBarcodes() {
        List<String> list = new ArrayList<String>();
        for(int i = 0; i < getImageItems().size(); i++) {
            ResultImageItem item = getImageItems().get(i);
            List barcodes = item.getBarcodes();
            if(barcodes == null || barcodes.size() == 0) {
                continue;
            }
            for(int b = 0; b < barcodes.size(); b++) {
                Map barcode = (Map) barcodes.get(b);
                StringBuilder sb = new StringBuilder().append("barcodeIndex=").append(list.size()).append(", pageIndex=").append(i).append(", ").append(mapToString(barcode));
                list.add(sb.toString());
            }
        }
        return list;
    }

    static String mapToString(Map map) {
        StringBuilder sb = new StringBuilder();
        for (Object key : map.keySet()) {
            if(sb.length() > 0) {
                sb.append(", ");
            }
            sb.append(String.valueOf(key)).append("=").append(String.valueOf(map.get(key)));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return "ScanResult{" +
                "device='" + device + '\'' +
                ", imageCount=" + imageCount +
                ", lastTransferResultCode=" + lastTransferResultCode +
                ", requestId='" + requestId + '\'' +
                ", responseId='" + responseId + '\'' +
                ", images=" + images +
                ", outputItems=" + outputItems +
                '}';
    }
}
