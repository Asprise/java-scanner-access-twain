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
import com.asprise.imaging.core.util.JsonUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.jr.ob.JSON;

import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Represents a scan request.
 */
public class Request implements Cloneable {

    public static final double DEFAULT_BLANK_PAGE_THRESHOLD = 0.02;
    public static final int DEFAULT_BLANK_PAGE_MARGIN_PERCENT = 8;

    /** Request ID */
    String id;

    /**
     * By default images will be processed after each scan; Set to true to process images after all scans.
     * Processing images after all scan could speed up the scanning process.
     */
    boolean processImagesAfterAllScans = false;

    /** Whether to prompt scan more dialog. */
    boolean promptScanMore = false;

    /** Whether blank pages should be discard. */
    boolean discardBlankPages = false;

    /** Ink coverage threshold to determine if a page is blank. */
    double blankPageThreshold = DEFAULT_BLANK_PAGE_THRESHOLD;

    /** Percentage of margin to be excluded when calculating ink coverage. */
    int blankPageMarginPercent = DEFAULT_BLANK_PAGE_MARGIN_PERCENT;

    /** Whether barcodes should be recognized. */
    boolean recognizeBarcodes;

    /** Map of cap name (could be appended with operation and priority, e.g., /RESET, /SET/3456) */
    Map<String, String> twainCapSetting = new HashMap<String, String>();

    /** Names of caps to be retrieved. */
    Set<String> retrieveCaps = new HashSet<String>();

    /** Name of extended image attributes to be retrieved. */
    Set<String> retrieveExtendedImageAttributes = new HashSet<String>();

    /** Output specifications. */
    List<RequestOutputItem> outputItems = new ArrayList<RequestOutputItem>();

    /** List of image files. */
    List<File> imageFiles = new ArrayList<File>();

    Boolean useAspriseDialog = null;
    String dialogTitle = null;
    int dialogWidth = -1;
    int dialogHeight = -1;
    Boolean showScannerUI = null;
    Boolean modalScannerUI = null;
    String sourceName = null;

    /** The root of JSON object that this request is deserialized from. */
    Map<Object, Object> sourceJson;

    @Override
    public Object clone() throws CloneNotSupportedException {
        Request clone = (Request) super.clone();
        clone.twainCapSetting = (Map<String, String>) ((HashMap<String,String>)twainCapSetting).clone();
        clone.retrieveCaps = (Set<String>) ((HashSet<String>)retrieveCaps).clone();
        clone.retrieveExtendedImageAttributes = (Set<String>) ((HashSet<String>)retrieveExtendedImageAttributes).clone();
        clone.outputItems = new ArrayList<RequestOutputItem>();
        for(RequestOutputItem item : outputItems) {
            clone.outputItems.add((RequestOutputItem) item.clone());
        }
        clone.imageFiles = new ArrayList<File>(imageFiles);
        return clone;
    }

    /**
     * Constructor.
     */
    public Request() {
        id = Long.toString(System.currentTimeMillis());
    }

    public String toJson(boolean pretty) {
        return JsonUtils.jsonSerialize(toJsonObject(), pretty);
    }

    public static Request fromJson(String spec) throws IOException {
        JsonParser parser = new JsonFactory().enable(JsonParser.Feature.ALLOW_COMMENTS).createParser(spec);
        Map<Object, Object> json = JSON.std.mapFrom(parser);

        Request request = new Request();
        request.id = JsonUtils.attrValue(json, "request_id", null);
        request.processImagesAfterAllScans = "after-all-scans".equals(JsonUtils.attrValue(json, "processing_strategy", null));
        request.promptScanMore = JsonUtils.attrValueAsBoolean(json, "prompt_scan_more", false);
        request.discardBlankPages = JsonUtils.attrValueAsBoolean(json, "discard_blank_pages", false);
        request.blankPageThreshold = JsonUtils.attrValueAsDouble(json, "blank_page_threshold", DEFAULT_BLANK_PAGE_THRESHOLD);
        request.blankPageMarginPercent = JsonUtils.attrValueAsInt(json, "blank_page_margin_percent", DEFAULT_BLANK_PAGE_MARGIN_PERCENT);
        request.recognizeBarcodes = JsonUtils.attrValueAsBoolean(json, "recognize_barcodes", false);

        request.useAspriseDialog = JsonUtils.attrValueAsBoolean(json, "use_asprise_dialog", null);
        request.showScannerUI = JsonUtils.attrValueAsBoolean(json, "show_scanner_ui", null);
        request.modalScannerUI = JsonUtils.attrValueAsBoolean(json, "modal_scanner_ui", null);
        request.dialogWidth = JsonUtils.attrValueAsInt(json, "dialog_width", -1);
        request.dialogHeight = JsonUtils.attrValueAsInt(json, "dialog_height", -1);
        request.sourceName = JsonUtils.attrValue(json, "source_name", null);

        Map caps = (Map) json.get("twain_cap_setting");
        if(caps != null) {
            request.twainCapSetting.putAll(caps);
        }

        List outputs = (List) json.get("output_settings");
        for(int i = 0; outputs != null && i < outputs.size(); i++) {
            request.outputItems.add(RequestOutputItem.fromJsonMap((Map) outputs.get(i)));
        }

        if(json.get("retrieve_caps") instanceof Collection) {
            request.retrieveCaps.addAll((Collection<? extends String>) json.get("retrieve_caps"));
        }

        if(json.get("retrieve_extended_image_attrs") instanceof Collection) {
            request.retrieveExtendedImageAttributes.addAll((Collection<? extends String>) json.get("retrieve_extended_image_attrs"));
        }

        List imgFiles = (List)json.get("image_files");
        for(int i = 0; imgFiles != null && i < imgFiles.size(); i++) {
            request.imageFiles.add(new File((String)imgFiles.get(i)));
        }

        request.sourceJson = json;
        return request;
    }

    public Map<String, Object> toJsonObject() {
        Map<String, Object> json = new HashMap<String, Object>();
        json.put("request_id", id);
        if(processImagesAfterAllScans) {
            json.put("processing_strategy", "after-all-scans");
        }

        if(twainCapSetting.size() > 0) {
            json.put("twain_cap_setting", twainCapSetting);
        }

        json.put("prompt_scan_more", promptScanMore);
        json.put("discard_blank_pages", discardBlankPages);
        json.put("blank_page_threshold", blankPageThreshold);
        json.put("blank_page_margin_percent", blankPageMarginPercent);

        json.put("recognize_barcodes", recognizeBarcodes);

        List outputJsons = new ArrayList();
        for(int o = 0; outputItems != null && o < outputItems.size(); o++) {
            outputJsons.add(outputItems.get(o).toJsonObject());
        }
        json.put("output_settings", outputJsons);

        json.put("retrieve_caps", retrieveCaps);

        json.put("retrieve_extended_image_attrs", retrieveExtendedImageAttributes);

        List imgFilesJson = new ArrayList();
        for(int i = 0; imageFiles != null && i < imageFiles.size(); i++) {
            imgFilesJson.add(imageFiles.get(i).getAbsolutePath());
        }
        json.put("image_files", imgFilesJson);

        if(useAspriseDialog != null) {
            json.put("use_asprise_dialog", useAspriseDialog);
        }
        if(dialogTitle != null) {
            json.put("dialog_title", dialogTitle);
        }
        if(dialogWidth > 0) {
            json.put("dialog_width", dialogWidth);
        }
        if(dialogHeight > 0) {
            json.put("dialog_height", dialogWidth);
        }
        if(showScannerUI != null) {
            json.put("show_scanner_ui", showScannerUI);
        }
        if(modalScannerUI != null) {
            json.put("modal_scanner_ui", modalScannerUI);
        }
        if(sourceName != null) {
            json.put("source_name", sourceName);
        }

        return json;
    }

    static String[] keywordsWithExtras;

    public static String[] getKeywordsWithExtras() {
        if(keywordsWithExtras == null) {
            String[] array1 = getKeywords();
            String[] array2 = TwainConstants.getConstants();
            keywordsWithExtras = new String[array1.length + array2.length];
            System.arraycopy(array1, 0, keywordsWithExtras, 0, array1.length);
            System.arraycopy(array2, 0, keywordsWithExtras, array1.length, array2.length);
        }
        return keywordsWithExtras;
    }

    static String[] keywords;

    public static String[] getKeywords() {
        if(keywords == null) {
            keywords = new String[]{
                    "request_id",
                    "processing_strategy", "after-all-scans",
                    "twain_cap_setting",
                    "prompt_scan_more",
                    "discard_blank_pages",
                    "blank_page_threshold",
                    "blank_page_margin_percent",
                    "recognize_barcodes",
                    "output_settings",
                    "retrieve_caps",
                    "retrieve_extended_image_attrs",
                    "image_files",

                    "type", "return-base64", "return-base64-thumbnail", "return-handle", "return-handle-thumbnail", "save", "save-thumbnail", "upload", "upload-thumbnail",
                    "format", "jpg", "png", "bmp", "tif", "pdf",
                    "thumbnail_height",
                    "save_path",
                    "jpeg_quality",
                    "tiff_compression", "G3", "G4", "LZW", "RLE", "NONE", "PACKBITS", "ZIP",
                    "tiff_force_single_page",
                    "pdf_force_black_white",
                    "pdfa_compliant",
                    "pdf_owner_password",
                    "pdf_user_password",
                    "pdf_text_line",
                    "exif", "DocumentName", "ImageDescription", "EquipMake", "EquipModel", "Copyright", "UserComment",
                    "upload_after_all_done",
                    "upload_one_by_one",
                    "upload_target",

                    "url",
                    "max_retries",
                    "post_fields",
                    "post_file_field_name",
                    "post_files",
                    "cookies",
                    "auth",
                    "headers",
                    "log_file", "stdout", "stderr",
                    "to_file",
                    "max_operation_time",

                    "use_asprise_dialog",
                    "dialog_title",
                    "dialog_width",
                    "dialog_height",
                    "show_scanner_ui",
                    "modal_scanner_ui",
                    "source_name"
            };
            Arrays.sort(keywords);
        }
        return keywords;
    }

    /** Request ID */
    public String getId() {
        return id;
    }

    /** Request ID */
    public Request setId(String id) {
        this.id = id;
        return this;
    }

    /**
     * By default images will be processed after each scan; Set to true to process images after all scans.
     * Processing images after all scan could speed up the scanning process.
     */
    public boolean isProcessImagesAfterAllScans() {
        return processImagesAfterAllScans;
    }

    /**
     * By default images will be processed after each scan; Set to true to process images after all scans.
     * Processing images after all scan could speed up the scanning process.
     */
    public Request setProcessImagesAfterAllScans(boolean processImagesAfterAllScan) {
        this.processImagesAfterAllScans = processImagesAfterAllScan;
        return this;
    }

    /** Whether to prompt scan more dialog. */
    public boolean isPromptScanMore() {
        return promptScanMore;
    }

    /** Whether to prompt scan more dialog. */
    public Request setPromptScanMore(boolean promptScanMore) {
        this.promptScanMore = promptScanMore;
        return this;
    }

    /** Whether blank pages should be discard. */
    public boolean isDiscardBlankPages() {
        return discardBlankPages;
    }

    /** Whether blank pages should be discard. */
    public Request setDiscardBlankPages(boolean discardBlankPages) {
        this.discardBlankPages = discardBlankPages;
        return this;
    }

    /** Ink coverage threshold to determine if a page is blank. */
    public double getBlankPageThreshold() {
        return blankPageThreshold;
    }

    /** Ink coverage threshold to determine if a page is blank. */
    public Request setBlankPageThreshold(double blankPageThreshold) {
        this.blankPageThreshold = blankPageThreshold;
        return this;
    }

    /** Percentage of margin to be excluded when calculating ink coverage. */
    public int getBlankPageMarginPercent() {
        return blankPageMarginPercent;
    }

    /** Percentage of margin to be excluded when calculating ink coverage. */
    public Request setBlankPageMarginPercent(int blankPageMarginPercent) {
        this.blankPageMarginPercent = blankPageMarginPercent;
        return this;
    }

    /** Whether barcodes should be recognized. */
    public boolean isRecognizeBarcodes() {
        return recognizeBarcodes;
    }

    /** Whether barcodes should be recognized. */
    public Request setRecognizeBarcodes(boolean recognizeBarcodes) {
        this.recognizeBarcodes = recognizeBarcodes;
        return this;
    }

    /** Map of cap name (could be appended with operation and priority, e.g., /RESET, /SET/3456) */
    public Map<String, String> getTwainCapSetting() {
        return twainCapSetting;
    }

    /**
     * Set or reset twain capability.
     * @param capCode e.g., {@linkplain TwainConstants#ICAP_PIXELTYPE}
     * @param value support raw value and twain constant expansion; support fall back values e.g., "TWPT_GRAY,TWPT_BW"
     * @param reset true to set operation RESET instead of SET.
     */
    public Request setTwainCap(int capCode, Object value, boolean reset) {
        this.twainCapSetting.put(TwainConstants.getCapName(capCode) + (reset ? "/RESET" : ""), value == null ? null : value.toString());
        return this;
    }

    /**
     * Set twain capability.
     * @param capCode e.g., {@linkplain TwainConstants#ICAP_PIXELTYPE}
     * @param value support raw value and twain constant expansion; support fall back values e.g., "TWPT_GRAY,TWPT_BW"
     */
    public Request setTwainCap(int capCode, Object value) {
        return setTwainCap(capCode, value, false);
    }

    /**
     * Returns the cap value setting specified in the request as int or -1 if failed to convert to int.
     * @param capCode
     * @return
     */
    public int getTwainCapValueAsInt(int capCode) {
        Object value = twainCapSetting.get(TwainConstants.getCapName(capCode));
        if(value instanceof String) {
            String sVal = (String)value;
            if(sVal.contains(",")) {
                sVal = sVal.substring(0, sVal.indexOf(","));
            }
            if(sVal.contains("TW")) {
                Integer intVal = TwainConstants.getConstantValue(sVal.trim());
                return intVal == null ? -1 : intVal;
            }
        }
        return TwainUtil.toInteger(value, -1);
    }

    /** Names of caps to be retrieved. */
    public Set<String> getRetrieveCaps() {
        return retrieveCaps;
    }

    /** Add a cap to be retrieved. */
    public Request retrieveCap(int capCode) {
        String capName = TwainConstants.getCapName(capCode);
        if(capName == null) {
            System.err.println("Invalid cap code: " + capCode);
            return this;
        }
        this.retrieveCaps.add(capName);
        return this;
    }

    /** Name of extended image attributes to be retrieved. */
    public Set<String> getRetrieveExtendedImageAttributes() {
        return retrieveExtendedImageAttributes;
    }

    /** Adds an extended image attribute to be retrieved. */
    public Request retrieveExtendedImageAttributes(int tweiCode) {
        String tweiName = TwainConstants.getTweiName(tweiCode);
        if(tweiName == null) {
            System.err.println("Invalid twei code: " + tweiCode);
            return this;
        }
        this.retrieveExtendedImageAttributes.add(tweiName);
        return this;
    }

    /** Output specifications. */
    public List<RequestOutputItem> getOutputItems() {
        return outputItems;
    }

    /** Adds an output specification. */
    public Request addOutputItem(RequestOutputItem outputItem) {
        this.outputItems.add(outputItem);
        return this;
    }

    /** Adds output specifications. */
    public Request addOutputItems(List<RequestOutputItem> items) {
        this.outputItems.addAll(items);
        return this;
    }

    public Request removeAllOutputItems() {
        this.outputItems.clear();
        return this;
    }

    public List<File> getImageFiles() {
        return imageFiles;
    }

    /**
     *  Adds an image file
     *  @throws IllegalArgumentException if the file is not readable
     */
    public Request addImageFile(File imageFile) {
        if(imageFile == null || !imageFile.exists()) {
            throw new IllegalArgumentException("File doesn't exist: " + imageFile.getAbsolutePath());
        }
        if(!imageFile.canRead()) {
            throw new IllegalArgumentException("File isn't readable: " + imageFile);
        }
        if(!isAllASCII(imageFile.getAbsolutePath())) {
            throw new IllegalArgumentException("File path contains non-ASCII char(s): " + imageFile.getAbsolutePath());
        }

        this.imageFiles.add(imageFile);
        return this;
    }

    /**
     * Adds list of image files.
     * @param imageFiles list of image files or null.
     * @throws IllegalArgumentException if any of the file in the list is not readable
     */
    public Request addImageFiles(List<File> imageFiles) {
        if(imageFiles == null) {
            return this;
        }
        for(File file : imageFiles) {
            addImageFile(file);
        }
        return this;
    }

    private static boolean isAllASCII(String input) {
        boolean isASCII = true;
        for (int i = 0; i < input.length(); i++) {
            int c = input.charAt(i);
            if (c > 0x7F) {
                isASCII = false;
                break;
            }
        }
        return isASCII;
    }

    public void setUseAspriseDialog(Boolean useAspriseDialog) {
        this.useAspriseDialog = useAspriseDialog;
    }

    public void setDialogTitle(String dialogTitle) {
        this.dialogTitle = dialogTitle;
    }

    public void setDialogWidth(int dialogWidth) {
        this.dialogWidth = dialogWidth;
    }

    public void setDialogHeight(int dialogHeight) {
        this.dialogHeight = dialogHeight;
    }

    public void setShowScannerUI(Boolean showScannerUI) {
        this.showScannerUI = showScannerUI;
    }

    public void setModalScannerUI(Boolean modalScannerUI) {
        this.modalScannerUI = modalScannerUI;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName;
    }

    public Boolean getUseAspriseDialog() {
        return useAspriseDialog;
    }

    public String getDialogTitle() {
        return dialogTitle;
    }

    public int getDialogWidth() {
        return dialogWidth;
    }

    public int getDialogHeight() {
        return dialogHeight;
    }

    public Boolean getShowScannerUI() {
        return showScannerUI;
    }

    public Boolean getModalScannerUI() {
        return modalScannerUI;
    }

    public String getSourceName() {
        return sourceName;
    }

    /**
     * Dimension from dialog width and dialog height or null if either is not greater than 0.
     * @return
     */
    public Dimension getSpecifiedDialogSize() {
        return dialogWidth > 0 && dialogHeight > 0 ? new Dimension(dialogWidth, dialogHeight) : null;
    }
}
