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
import com.asprise.imaging.core.scan.twain.TwainUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents an image scanned.
 */
public class ResultImageItem {
    /** List of barcodes recognized on the image. */
    private List<Map<String, Object>> barcodes = new ArrayList<Map<String, Object>>();
    /** Caps returned */
    private Map<String, Object> caps = new HashMap<String, Object>();
    /** Extended image attributes */
    private Map<String, Object> extendedImageAttrs = new HashMap<String, Object>();
    /** Image info if available. */
    private ImageInfo imageInfo;
    /** Image layout if available */
    private ImageLayout imageLayout;

    private Date acquiredOn;

    /** Whether it is blank */
    private Boolean blank;

    /** Ink coverage */
    private double inkCoverage = -1;

    protected ResultImageItem(Map<String, Object> jsonImageObject) {
        barcodes = (List<Map<String,Object>>) jsonImageObject.get("barcodes");
        caps = (Map<String, Object>) jsonImageObject.get("caps");
        extendedImageAttrs = (Map<String, Object>) jsonImageObject.get("extended_image_attrs");
        imageInfo = ImageInfo.createImageInfo((Map<String, Object>) jsonImageObject.get("image_info"));
        imageLayout = ImageLayout.createImageLayout((Map<String, Object>) jsonImageObject.get("image_layout"));
        long acquired = TwainUtil.toLong(jsonImageObject.get("time"), 0);
        acquiredOn = acquired == 0 ? null : new Date(acquired * 1000);
        blank = jsonImageObject.containsKey("is_blank") ? jsonImageObject.get("is_blank").toString().equalsIgnoreCase("true") : null;
        inkCoverage = TwainUtil.toDouble(jsonImageObject.get("ink_coverage"), -1);
    }

    public Map<String, Object> toJsonObject() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("barcodes", barcodes);
        json.put("caps", caps);
        json.put("extended_image_attrs", extendedImageAttrs);
        if(imageInfo != null) {
            json.put("image_info", imageInfo.toJsonObject());
        }
        if(imageLayout != null) {
            json.put("image_layout", imageLayout.toJsonObject());
        }
        json.put("time", acquiredOn == null ? null : acquiredOn.getTime());

        if(blank != null) {
            json.put("is_blank", blank);
        }

        if(inkCoverage >= 0) {
            json.put("ink_coverage", inkCoverage);
        }

        return json;
    }

    /**
     * Creates ScanResultImageItem if the jsonImageObject is not null.
     * @param jsonImageObject
     * @return ScanResultImageItem created or null if jsonImageObject is null.
     */
    public static ResultImageItem createScanResultImageItem(Map<String, Object> jsonImageObject) {
        if(jsonImageObject == null) {
            return null;
        } else {
            return new ResultImageItem(jsonImageObject);
        }
    }

    /**
     * Caps in key(cap name)-value map.
     * @return
     */
    public Map<String, Object> getCaps() {
        return caps;
    }

    /**
     * Retrieves the value of the given cap.
     * @param capCode
     * @return the cap value or null if cap hasn't been retrieved.
     */
    public String getCap(int capCode) {
        if(caps == null) {
            return null;
        }
        String capName = TwainConstants.getCapName(capCode);
        Object value = caps.get(capName);
        return value == null ? null : value.toString();
    }

    /**
     * Extended image attributes in key(extended image attribute name)-value map.
     * @return
     */
    public Map<String, Object> getExtendedImageAttrs() {
        return extendedImageAttrs;
    }

    /**
     * Retrieves the value of the given attribute.
     * @param attrCode
     * @return the attribute value or null if it hasn't been retrieved.
     */
    public String getExtendedImageAttr(int attrCode) {
        if(extendedImageAttrs == null) {
            return null;
        }
        String tweiName = TwainConstants.getTweiName(attrCode);
        Object value = extendedImageAttrs.get(tweiName);
        return value == null ? null : value.toString();
    }

    /**
     * Image info if available.
     * @return
     */
    public ImageInfo getImageInfo() {
        return imageInfo;
    }

    /**
     * Gets the image resolution in x direction.
     * @return the image resolution or -1 if not available.
     */
    public int getImageInfoResolution() {
        return imageInfo == null ? -1 : imageInfo.XResolution;
    }

    /**
     * Gets the image width.
     * @return the image width or -1 if not available.
     */
    public int getImageInfoWidth() {
        return imageInfo == null ? -1 : imageInfo.ImageWidth;
    }

    /**
     * Gets the image height.
     * @return the image height or -1 not available.
     */
    public int getImageInfoHeight() {
        return imageInfo == null ? -1 : imageInfo.ImageLength;
    }

    /**
     * Gets pixel type.
     * @return -1 if not available or any of {@linkplain TwainConstants#TWPT_BW}, {@linkplain TwainConstants#TWPT_GRAY}, {@linkplain TwainConstants#TWPT_RGB}.
     */
    public int getImageInfoPixelType() {
        return imageInfo == null ? -1 : imageInfo.PixelType;
    }

    /**
     * Gets the bit depth (bits per pixel).
     * @return -1 if not available or the actual bit depth.
     */
    public int getImageInfoBitDepth() {
        return imageInfo == null ? -1 : imageInfo.BitsPerPixel;
    }

    /**
     * Image layout if available.
     * @return
     */
    public ImageLayout getImageLayout() {
        return imageLayout;
    }

    /**
     * Gets the document number.
     * @return the document number or -1 if not available.
     */
    public int getImageLayoutDocumentNumber() {
        return imageLayout == null ? -1 : imageLayout.DocumentNumber;
    }

    /**
     * Gets the frame number.
     * @return the frame number or -1 if not available.
     */
    public int getImageLayoutFrameNumber() {
        return imageLayout == null ? -1 : imageLayout.FrameNumber;
    }

    /**
     * Gets the page number.
     * @return the page number or -1 if not available.
     */
    public int getImageLayoutPageNumber() {
        return imageLayout == null ? -1 : imageLayout.PageNumber;
    }

    /**
     * Gets the frame location (left, top, right, bottom) in unit {@linkplain TwainConstants#ICAP_UNITS}.
     * @return the frame location (4-element double array) or null if not available.
     */
    public double[] getImageLayoutFrame() {
        return imageLayout == null ? null : imageLayout.Frame;
    }

    /**
     * The acquisition time or null if not available.
     * @return
     */
    public Date getAcquiredOn() {
        return acquiredOn;
    }

    /**
     * List of barcodes recognized on the image.
     * @return barcodes recognized or empty list if recognition is not performed or none is recognized.
     */
    public List<Map<String, Object>> getBarcodes() {
        return barcodes;
    }

    /** Whether it is blank - available only if blank page detection is enabled. */
    public boolean isBlank() {
        return blank;
    }

    /**
     * Ink coverage - available only if blank page detection is enabled.
     * @return ink coverage or negative value if it is not available.
     */
    public double getInkCoverage() {
        return inkCoverage;
    }

    /**
     * Corresponding to TWAIN's TW_IMAGEINFO struct
     */
    public static class ImageInfo {
        /** Impacted by cap: ICAP_XRESOLUTION */
        public int XResolution = -1;
        /** Impacted by cap: ICAP_YRESOLUTION */
        public int YResolution = -1;
        /** Impacted by cap: TW_IMAGELAYOUT.TW_FRAME.Right - TW_FRAME.Left */
        public int ImageWidth = -1;
        /** Impacted by cap: TW_IMAGELAYOUT.TW_FRAME.Bottom - TW_FRAME.Top */
        public int ImageLength = -1;
        /** Impacted by cap: ICAP_PIXELTYPE (i.e. TWPT_BW has 1, TWPT_RGB has 3) */
        public int SamplesPerPixel = -1;
        /** Calculated by BitsPerPixel divided by SamplesPerPixel. */
        public int[] BitsPerSample;
        /** Impacted by cap: ICAP_BITDEPTH */
        public int BitsPerPixel = -1;
        /** Impacted by cap: ICAP_PLANARCHUNKY */
        public Boolean Planar = false;
        /** Impacted by cap: ICAP_PIXELTYPE */
        public int PixelType = -1;
        /** Impacted by cap: ICAP_COMPRESSION */
        public int Compression = -1;

        public ImageInfo(Map<String, Object> valueMap) {
            this.XResolution = TwainUtil.toInteger(valueMap.get("XResolution"), -1);
            this.YResolution = TwainUtil.toInteger(valueMap.get("YResolution"), -1);
            this.ImageWidth = TwainUtil.toInteger(valueMap.get("ImageWidth"), -1);
            this.ImageLength = TwainUtil.toInteger(valueMap.get("ImageLength"), -1);
            this.SamplesPerPixel = TwainUtil.toInteger(valueMap.get("SamplesPerPixel"), -1);
            this.BitsPerPixel = TwainUtil.toInteger(valueMap.get("BitsPerPixel"), -1);
            this.Planar = valueMap.get("Planar") == null ? null : TwainUtil.toInteger(valueMap.get("Planar"), 0) != 0;
            this.PixelType = TwainUtil.toInteger(valueMap.get("PixelType"), -1);
            this.Compression = TwainUtil.toInteger(valueMap.get("Compression"), -1);
        }

        public Map<String, Object> toJsonObject() {
            Map<String, Object> json = new HashMap<String, Object>();
            if(XResolution >= 0) {
                json.put("XResolution", XResolution);
            }
            if(YResolution >= 0) {
                json.put("YResolution", YResolution);
            }
            if(ImageWidth >= 0) {
                json.put("ImageWidth", ImageWidth);
            }
            if(ImageLength >= 0) {
                json.put("ImageLength", ImageLength);
            }
            if(SamplesPerPixel >= 0) {
                json.put("SamplesPerPixel", SamplesPerPixel);
            }
            if(BitsPerPixel >= 0) {
                json.put("BitsPerPixel", BitsPerPixel);
            }
            if(Planar != null) {
                json.put("Planar", Planar);
            }
            if(PixelType >= 0) {
                json.put("PixelType", PixelType);
            }
            if(Compression >= 0) {
                json.put("Compression", Compression);
            }

            return json;
        }


        public ImageInfo() {
        }

        /**
         * Creates ImageInfo if the valueMap is not null.
         * @param valueMap
         * @return ImageInfo created or null if valueMap is null.
         */
        public static ImageInfo createImageInfo(Map<String, Object> valueMap) {
            if(valueMap == null) {
                return null;
            } else {
                return new ImageInfo(valueMap);
            }
        }

         @Override
        public String toString() {
            return "ImageInfo{" +
                    "XResolution=" + XResolution +
                    ", YResolution=" + YResolution +
                    ", ImageWidth=" + ImageWidth +
                    ", ImageLength=" + ImageLength +
                    ", SamplesPerPixel=" + SamplesPerPixel +
                    ", BitsPerSample=" + Arrays.toString(BitsPerSample) +
                    ", BitsPerPixel=" + BitsPerPixel +
                    ", Planar=" + Planar +
                    ", PixelType=" + PixelType +
                    ", Compression=" + Compression +
                    '}';
        }
    }

    /**
     * Corresponding to TWAIN's TW_IMAGELAYOUT struct
     */
    public static class ImageLayout {
        public double[] Frame;
        public int DocumentNumber = -1;
        public int PageNumber = -1;
        public int FrameNumber = -1;

        public ImageLayout(Map<String, Object> valueMap) {
            this.DocumentNumber = TwainUtil.toInteger(valueMap.get("DocumentNumber"), -1);
            this.PageNumber = TwainUtil.toInteger(valueMap.get("PageNumber"), -1);
            this.FrameNumber = TwainUtil.toInteger(valueMap.get("FrameNumber"), -1);
            this.Frame = TwainUtil.parseFrame(String.valueOf(valueMap.get("Frame")));
        }

        public Map<String, Object> toJsonObject() {
            Map<String, Object> json = new HashMap<String, Object>();
            if(DocumentNumber >= 0) {
                json.put("DocumentNumber", DocumentNumber);
            }
            if(PageNumber >= 0) {
                json.put("PageNumber", PageNumber);
            }
            if(FrameNumber >= 0) {
                json.put("FrameNumber", FrameNumber);
            }
            if(Frame != null && Frame.length == 4) {
                json.put("Frame", "(" + Frame[0] + ", " + Frame[1] + ", " + Frame[2] + ", " + Frame[3] + ")");
            }

            return json;
        }

        public ImageLayout() {
        }

        /**
         * Creates ImageLayout if the valueMap is not null.
         * @param valueMap
         * @return ImageLayout created or null if valueMap is null.
         */
        public static ImageLayout createImageLayout(Map<String, Object> valueMap) {
            if(valueMap == null) {
                return null;
            } else {
                return new ImageLayout(valueMap);
            }
        }

        @Override
        public String toString() {
            return "ImageLayout{" +
                    "Frame=" + Arrays.toString(Frame) +
                    ", DocumentNumber=" + DocumentNumber +
                    ", PageNumber=" + PageNumber +
                    ", FrameNumber=" + FrameNumber +
                    '}';
        }
    }
}
