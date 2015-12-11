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
package com.asprise.imaging.core.scan.twain;

import com.asprise.imaging.core.Imaging;
import com.asprise.imaging.core.util.system.StringUtils;
import com.fasterxml.jackson.jr.ob.JSON;

import java.util.Map;

/**
 * Utils
 */
public class TwainUtil {

    /**
     * Normalizes image format, e.g., TIFF to tif ({@linkplain Imaging#FORMAT_TIF}).
     * @param format
     * @return normalized image format or original format if failed to normalize it to any of the supported formats.
     */
    public static String normalizeImageFormat(String format) {
        if(format == null) {
            return null;
        }
        if(format.toLowerCase().contains("bmp")) {
            return Imaging.FORMAT_BMP;
        } else if(format.toLowerCase().contains("png")) {
            return Imaging.FORMAT_PNG;
        } else if(format.toLowerCase().contains("jp")) {
            return Imaging.FORMAT_JPG;
        } else if(format.toLowerCase().contains("tif")) {
            return Imaging.FORMAT_TIF;
        } else if(format.toLowerCase().contains("pdf")) {
            return Imaging.FORMAT_PDF;
        }
        return format;
    }

    /**
     * Returns the mime type of the given format.
     * @param format cane be bmp, jpng, jp[e]g, tif[f], pdf, etc.
     * @return the mime type or "application/octet-stream" if undetermined.
     */
    public static String getMimeType(String format) {
        if(format == null) {
            return "application/octet-stream";
        }
        if(format.toLowerCase().contains("bmp")) {
            return "image/bmp";
        } else if(format.toLowerCase().contains("png")) {
            return "image/png";
        } else if(format.toLowerCase().contains("jp")) {
            return "image/jpeg";
        } else if(format.toLowerCase().contains("tif")) {
            return "image/tiff";
        } else if(format.toLowerCase().contains("pdf")) {
            return "application/pdf";
        }
        return "application/octet-stream";
    }

    /**
     * Extract the value represented by the given object (Number or String).
     * @param object the object to extract value from
     * @param defaultValue default value to be returned if object is null or {@linkplain NumberFormatException}.
     * @return the value extracted or default value if failed to extract the value.
     */
    public static int toInteger(Object object, int defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Number) {
            return ((Number) object).intValue();
        }

        try {
            Double value = Double.valueOf(object.toString().trim());
            return value.intValue();
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Extract the value represented by the given object (Number or String).
     * @param object the object to extract value from
     * @param defaultValue default value to be returned if object is null or {@linkplain NumberFormatException}.
     * @return the value extracted or default value if failed to extract the value.
     */
    public static long toLong(Object object, long defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Number) {
            return ((Number) object).longValue();
        }

        try {
            Double value = Double.valueOf(object.toString().trim());
            return value.longValue();
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Extract the value represented by the given object (Number or String).
     * @param object the object to extract value from
     * @param defaultValue default value to be returned if object is null or {@linkplain NumberFormatException}.
     * @return the value extracted or default value if failed to extract the value.
     */
    public static double toDouble(Object object, double defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }

        try {
            Double value = Double.valueOf(object.toString().trim());
            return value;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Extract the value represented by the given object (Number or String) - trialing '.0*' will be removed.
     * @param object Integer or Double, fall back to defaultValue.
     * @param defaultValue default value to be returned if object is null or {@linkplain NumberFormatException}.
     * @return the value extracted or default value if failed to extract the value.
     */
    public static Number toNumber(Object object, Number defaultValue) {
        if(object == null) {
            return defaultValue;
        }
        if(object instanceof Number) {
            return (Number) object;
        }

        String s = object.toString();
        s = s.replaceAll("\\.0*$", ""); // remove trialing ".0"
        try {
            if(s.contains(".")) {
                return Double.parseDouble(s);
            } else {
                return Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    /**
     * Parses frame value like "(0.0, 0.0, 4.0, 6.0)"
     * @param frameSpec the value in string representation
     * @return 4-element double array or null if parsing fails.
     */
    public static double[] parseFrame(String frameSpec) {
        if(frameSpec == null || frameSpec.trim().length() == 0) {
            return null;
        }

        // remove enclosing ()
        if(frameSpec.charAt(0) != '(') {
            return null;
        }
        frameSpec = frameSpec.substring(1);

        if(frameSpec.length() == 0 || frameSpec.charAt(frameSpec.length()-1) != ')') {
            return null;
        }
        frameSpec = frameSpec.substring(0, frameSpec.length() - 1);

        String[] parts = StringUtils.split(frameSpec.trim(), ",;");
        if(parts == null || parts.length < 4) {
            return null;
        }

        double[] array = new double[4];
        for(int i = 0; i < array.length; i++) {
            array[i] = toDouble(parts[i], -1);
        }
        return array;
    }

    /**
     * Return null if object is null otherwise object.toString().
     * @param object
     * @return
     */
    public static String toString(Object object) {
        return object == null ? null : object.toString();
    }

    /**
     * Deserialize JSON from String.
     * @param s
     * @return
     * @
     */
    public static Object jsonDeserialize(String s) {
        try {
            return JSON.std.anyFrom(s);
        } catch (Throwable e) {
            throw new TwainException("Failed to parse JSON: " + s, e);
        }
    }

    /**
     * Deserialize JSON from String.
     * @param s
     * @return
     * @
     */
    public static Map<String, Object> jsonDeserializeToArray(String s) {
        Map<String, Object> root = null;
        try {
            root = JSON.std.mapFrom(s);
        } catch (Throwable e) {
            throw new TwainException("Failed to parse JSON: " + s, e);
        }
        return root;
    }

}
