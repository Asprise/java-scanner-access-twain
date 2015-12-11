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

import com.asprise.imaging.core.util.JsonUtils;
import com.asprise.imaging.core.util.system.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Specifies an output target in a scan request.
 */
public class RequestOutputItem implements Cloneable {

    public static final int DEFAULT_THUMBNAIL_HEIGHT = 200;
    public static final int DEFAULT_JPEG_QUALITY = 80;

    /** Output type, any of {@linkplain Imaging#OUTPUT_SAVE} ... */
    String type;

    /** Image format, any of {@linkplain Imaging#FORMAT_JPG} ... */
    String format;

    /** Only for thumbnail output types; default value: 200. */
    int thumbnailHeight = DEFAULT_THUMBNAIL_HEIGHT;

    /** Target output file path, may contain variable for expansion, e.g., C:\\tmp\\${TMS}${EXT}. */
    String savePath;

    /** Only for JPG format; default value: 80 */
    int jpegQuality = DEFAULT_JPEG_QUALITY;

    /** Any of {@linkplain Imaging#TIFF_COMPRESSION_CCITT_G4} ... Only for TIF format; default value: (null) */
    String tiffCompression;

    /** Force to save images into individual single-image tiff files. */
    boolean tiffForceSinglePage;

    /** Force to output black/white PDF to save space. */
    boolean pdfForceBlackWhite;

    /** Set to true to output PDF/A compliant PDF. */
    boolean pdfaCompliant;

    /** PDF owner password */
    String pdfOwnerPassword;

    /** PDF user password */
    String pdfUserPassword;

    /** Optionally prints a line of text at the bottom of the first page. */
    String pdfTextLine;

    /** Exif tags, key should be any of {@linkplain Imaging#EXIF_NAME_DocumentName} ... */
    Map<String, String> exifTags = new HashMap<String, String>();

    /** Perform upload after all scans. Default is true. */
    boolean uploadAfterAllDone = true;

    /** Set to true to upload one image per HTTP request; otherwise upload all images in one HTTP request. Default: false. */
    boolean uploadOneByOne = false;

    /** must be set when type is {@linkplain Imaging#OUTPUT_UPLOAD} or {@linkplain Imaging#OUTPUT_UPLOAD_THUMB}. */
    UploadSetting uploadSetting;

    @Override
    public Object clone() throws CloneNotSupportedException {
        RequestOutputItem clone = (RequestOutputItem) super.clone();
        clone.exifTags = (Map<String, String>) ((HashMap<String, String>)exifTags).clone();
        clone.uploadSetting = uploadSetting == null ? null : (UploadSetting) uploadSetting.clone();
        return clone;
    }

    public RequestOutputItem() {
    }

    /**
     * Constructor.
     * @param type any of {@linkplain Imaging#OUTPUT_SAVE} ...
     * @param format any of {@linkplain Imaging#FORMAT_JPG} ...
     */
    public RequestOutputItem(String type, String format) {
        setType(type);
        setFormat(format);
    }

    public static RequestOutputItem fromJsonMap(Map json) throws IOException {
        RequestOutputItem item = new RequestOutputItem();
        item.type = JsonUtils.attrValue(json, "type", null);
        item.format = JsonUtils.attrValue(json, "format", null);
        item.thumbnailHeight = JsonUtils.attrValueAsInt(json, "thumbnail_height", DEFAULT_THUMBNAIL_HEIGHT);
        item.savePath = JsonUtils.attrValue(json, "save_path", null);
        item.jpegQuality = JsonUtils.attrValueAsInt(json, "jpeg_quality", DEFAULT_JPEG_QUALITY);
        item.tiffCompression = JsonUtils.attrValue(json, "tiff_compression", null);
        item.tiffForceSinglePage = JsonUtils.attrValueAsBoolean(json, "tiff_force_single_page", false);
        item.pdfForceBlackWhite = JsonUtils.attrValueAsBoolean(json, "pdf_force_black_white", false);
        item.pdfaCompliant = JsonUtils.attrValueAsBoolean(json, "pdfa_compliant", false);
        item.pdfOwnerPassword = JsonUtils.attrValue(json, "pdf_owner_password", null);
        item.pdfUserPassword = JsonUtils.attrValue(json, "pdf_user_password", null);
        item.pdfTextLine = JsonUtils.attrValue(json, "pdf_text_line", null);

        Map exifs = (json.get("exif") instanceof Map) ? (Map)json.get("exif") : null;
        if(exifs != null) {
            item.exifTags.putAll(exifs);
        }

        item.uploadAfterAllDone = JsonUtils.attrValueAsBoolean(json, "upload_after_all_done", true);
        item.uploadOneByOne = JsonUtils.attrValueAsBoolean(json, "upload_one_by_one", false);
        item.uploadSetting = json.get("upload_target") == null ? null : UploadSetting.fromJsonMap((Map) json.get("upload_target"));
        return item;
    }

    public Map<String, Object> toJsonObject() {
        // validate first
        if(Imaging.OUTPUT_SAVE.equalsIgnoreCase(type) || Imaging.OUTPUT_SAVE_THUMB.equalsIgnoreCase(type)) {
            if(StringUtils.isEmpty(savePath)) {
                throw new IllegalArgumentException("savePath must be set for " + type);
            }
        } else if(Imaging.OUTPUT_UPLOAD.equalsIgnoreCase(type) || Imaging.OUTPUT_UPLOAD_THUMB.equalsIgnoreCase(type)) {
            if(uploadSetting == null) {
                throw new IllegalArgumentException("uploadSetting must be set for " + type);
            }
        }

        Map<String, Object> json = new HashMap<String, Object>();
        json.put("type", type);
        json.put("format", format);

        if(type.contains("thumb")) {
            json.put("thumbnail_height", thumbnailHeight);
        }

        if(!StringUtils.isEmpty(savePath)) {
            json.put("save_path", savePath);
        }

        if(format.contains("jp")) {
            json.put("jpeg_quality", jpegQuality);
        }

        if(!StringUtils.isEmpty(tiffCompression)) {
            json.put("tiff_compression", tiffCompression);
        }

        if(tiffForceSinglePage) {
            json.put("tiff_force_single_page", tiffForceSinglePage);
        }

        if(format.equals(Imaging.FORMAT_PDF)) {
            json.put("pdf_force_black_white", pdfForceBlackWhite);
            json.put("pdfa_compliant", pdfaCompliant);
            if(!StringUtils.isEmpty(pdfOwnerPassword)) {
                json.put("pdf_owner_password", pdfOwnerPassword);
            }
            if(!StringUtils.isEmpty(pdfUserPassword)) {
                json.put("pdf_user_password", pdfUserPassword);
            }
            if(!StringUtils.isEmpty(pdfTextLine)) {
                json.put("pdf_text_line", pdfTextLine);
            }
        }

        if(exifTags.size() > 0) {
            json.put("exif", exifTags);
        }

        if(type.contains("upload")) {
            json.put("upload_after_all_done", uploadAfterAllDone);
            json.put("upload_one_by_one", uploadOneByOne);
            json.put("upload_target", uploadSetting == null ? null : uploadSetting.toJsonObject());
        }

        return json;
    }

    /** Output type, any of {@linkplain Imaging#OUTPUT_SAVE} ... */
    public String getType() {
        return type;
    }

    /** Output type, any of {@linkplain Imaging#OUTPUT_SAVE} ... */
    public RequestOutputItem setType(String type) {
        if(Imaging.OUTPUT_SAVE.equals(type) || Imaging.OUTPUT_SAVE_THUMB.equals(type)
                || Imaging.OUTPUT_RETURN_BASE64.equals(type) || Imaging.OUTPUT_RETURN_BASE64_THUMB.equals(type)
                || Imaging.OUTPUT_UPLOAD.equals(type) || Imaging.OUTPUT_UPLOAD_THUMB.equals(type)) {
            // ok
        } else {
            throw new UnsupportedOperationException("Output type: " + type);
        }
        this.type = type;
        return this;
    }

    /** Image format, any of {@linkplain Imaging#FORMAT_JPG} ... */
    public String getFormat() {
        return format;
    }

    /** Image format, any of {@linkplain Imaging#FORMAT_JPG} ... */
    public RequestOutputItem setFormat(String format) {
        if(Imaging.FORMAT_JPG.equals(format) || Imaging.FORMAT_PDF.equals(format) || Imaging.FORMAT_TIF.equals(format)
                || Imaging.FORMAT_PNG.equals(format) || Imaging.FORMAT_BMP.equals(format)) {
            // ok
        } else {
            throw new UnsupportedOperationException("Image format: " + format);
        }
        this.format = format;
        return this;
    }

    /** Only for thumbnail output types; default value: 200. */
    public int getThumbnailHeight() {
        return thumbnailHeight;
    }

    /** Only for thumbnail output types; default value: 200. */
    public RequestOutputItem setThumbnailHeight(int thumbnailHeight) {
        this.thumbnailHeight = thumbnailHeight;
        return this;
    }

    /** Target output file path, may contain variable for expansion, e.g., C:\\tmp\\${TMS}${EXT}. */
    public String getSavePath() {
        return savePath;
    }

    /** Target output file path, may contain variable for expansion, e.g., C:\\tmp\\${TMS}${EXT}. */
    public RequestOutputItem setSavePath(String savePath) {
        this.savePath = savePath;
        return this;
    }

    /** Only for JPG format; default value: 80 */
    public int getJpegQuality() {
        return jpegQuality;
    }

    /** Only for JPG format; default value: 80 */
    public RequestOutputItem setJpegQuality(int jpegQuality) {
        this.jpegQuality = jpegQuality;
        return this;
    }

    /** Any of {@linkplain Imaging#TIFF_COMPRESSION_CCITT_G4} ... Only for TIF format; default value: (null) */
    public String getTiffCompression() {
        return tiffCompression;
    }

    /** Any of {@linkplain Imaging#TIFF_COMPRESSION_CCITT_G4} ... Only for TIF format; default value: (null) */
    public RequestOutputItem setTiffCompression(String tiffCompression) {
        this.tiffCompression = tiffCompression;
        return this;
    }

    /** Force to save images into individual single-image tiff files. */
    public boolean isTiffForceSinglePage() {
        return tiffForceSinglePage;
    }

    /** Force to save images into individual single-image tiff files. */
    public RequestOutputItem setTiffForceSinglePage(boolean tiffForceSinglePage) {
        this.tiffForceSinglePage = tiffForceSinglePage;
        return this;
    }

    /** Force to output black/white PDF to save space. */
    public boolean isPdfForceBlackWhite() {
        return pdfForceBlackWhite;
    }

    /** Force to output black/white PDF to save space. */
    public RequestOutputItem setPdfForceBlackWhite(boolean pdfForceBlackWhite) {
        this.pdfForceBlackWhite = pdfForceBlackWhite;
        return this;
    }

    /** Set to true to output PDF/A compliant PDF. */
    public boolean isPdfaCompliant() {
        return pdfaCompliant;
    }

    /** Set to true to output PDF/A compliant PDF. */
    public RequestOutputItem setPdfaCompliant(boolean pdfaCompliant) {
        this.pdfaCompliant = pdfaCompliant;
        return this;
    }

    /** PDF owner password */
    public String getPdfOwnerPassword() {
        return pdfOwnerPassword;
    }

    /** PDF owner password */
    public RequestOutputItem setPdfOwnerPassword(String pdfOwnerPassword) {
        this.pdfOwnerPassword = pdfOwnerPassword;
        return this;
    }

    /** PDF user password */
    public String getPdfUserPassword() {
        return pdfUserPassword;
    }

    /** PDF user password */
    public RequestOutputItem setPdfUserPassword(String pdfUserPassword) {
        this.pdfUserPassword = pdfUserPassword;
        return this;
    }

    /** Optionally prints a line of text at the bottom of the first page. */
    public String getPdfTextLine() {
        return pdfTextLine;
    }

    /** Optionally prints a line of text at the bottom of the first page. */
    public RequestOutputItem setPdfTextLine(String pdfTextLine) {
        this.pdfTextLine = pdfTextLine;
        return this;
    }

    /** Exif tags, key should be any of {@linkplain Imaging#EXIF_NAME_DocumentName} ... */
    public Map<String, String> getExifTags() {
        return exifTags;
    }

    /** Exif tags, key should be any of {@linkplain Imaging#EXIF_NAME_DocumentName} ... */
    public RequestOutputItem addExifTag(String tagName, String value) {
        this.exifTags.put(tagName, value);
        return this;
    }

    /** Perform upload after all scans. Default is true. */
    public boolean isUploadAfterAllDone() {
        return uploadAfterAllDone;
    }

    /** Perform upload after all scans. Default is true. */
    public RequestOutputItem setUploadAfterAllDone(boolean uploadAfterAllDone) {
        this.uploadAfterAllDone = uploadAfterAllDone;
        return this;
    }

    /** Set to true to upload one image per HTTP request; otherwise upload all images in one HTTP request. Default: false. */
    public boolean isUploadOneByOne() {
        return uploadOneByOne;
    }

    /** Set to true to upload one image per HTTP request; otherwise upload all images in one HTTP request. Default: false. */
    public RequestOutputItem setUploadOneByOne(boolean uploadOneByOne) {
        this.uploadOneByOne = uploadOneByOne;
        return this;
    }

    /** must be set when type is {@linkplain Imaging#OUTPUT_UPLOAD} or {@linkplain Imaging#OUTPUT_UPLOAD_THUMB}. */
    public UploadSetting getUploadSetting() {
        return uploadSetting;
    }

    /** must be set when type is {@linkplain Imaging#OUTPUT_UPLOAD} or {@linkplain Imaging#OUTPUT_UPLOAD_THUMB}. */
    public RequestOutputItem setUploadSetting(UploadSetting uploadSetting) {
        this.uploadSetting = uploadSetting;
        return this;
    }

    /**
     * Upload settings.
     * url is the only mandatory parameter.
     */
    public static class UploadSetting implements Cloneable {
        public static final int DEFAULT_MAX_RETRIES = 2;
        public static final int DEFAULT_MAX_OPERATION_TIME = 1200;
        public static final String DEFAULT_POST_FILE_FIELD_NAME = "asprise_scans";

        /** Target URL */
        String url;

        /** Max number of retries. Default is 2. */
        int maxRetries = DEFAULT_MAX_RETRIES;

        /** Max HTTP operation time in seconds; default: 1200 (20 mins). */
        int maxOperationTime = DEFAULT_MAX_OPERATION_TIME;

        /** Field name for files. */
        String postFileFieldName = DEFAULT_POST_FILE_FIELD_NAME;

        /** Additional files to be posted along */
        List<String> postFiles = new ArrayList<String>();

        /** Any additional post fields */
        Map<String, String> postFields = new HashMap<String, String>();

        /** e.g., "name=Asprise; domain=asprise.com" */
        String cookies;

        /** Authentication, e.g., "user:pass" */
        String auth;

        /** e.g., "Referer: http://asprise.com" */
        List<String> headers = new ArrayList<String>();

        /** Saves response to the given file instead of returning response. */
        File saveResponseToFile;

        /** Log file to record HTTP operations. */
        String logToFile;

        @Override
        public Object clone() throws CloneNotSupportedException {
            UploadSetting clone = (UploadSetting) super.clone();
            clone.postFiles = new ArrayList<String>(postFiles);
            clone.postFields = (Map<String, String>) ((HashMap<String, String>)postFields).clone();
            clone.headers = new ArrayList<String>(headers);
            return clone;
        }

        /**
         * Constructor
         * @param url target URL to post files.
         */
        public UploadSetting(String url) {
            this.url = url;
        }

        public UploadSetting() {
        }

        /** Target URL */
        public String getUrl() {
            return url;
        }

        /** Target URL */
        public UploadSetting setUrl(String url) {
            this.url = url;
            return this;
        }

        /** Max number of retries. Default is 2. */
        public int getMaxRetries() {
            return maxRetries;
        }

        /** Max number of retries. Default is 2. */
        public UploadSetting setMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
            return this;
        }

        /** Max HTTP operation time in seconds; default: 1200 (20 mins). */
        public int getMaxOperationTime() {
            return maxOperationTime;
        }

        /** Max HTTP operation time in seconds; default: 1200 (20 mins). */
        public UploadSetting setMaxOperationTime(int maxOperationTime) {
            this.maxOperationTime = maxOperationTime;
            return this;
        }

        /** Field name for files. */
        public String getPostFileFieldName() {
            return postFileFieldName;
        }

        /** Field name for files. */
        public UploadSetting setPostFileFieldName(String postFileFieldName) {
            this.postFileFieldName = postFileFieldName;
            return this;
        }

        /** Additional files to be posted along */
        public List<String> getPostFiles() {
            return postFiles;
        }

        /** Additional files to be posted along */
        public UploadSetting addPostFiles(String postFile) {
            this.postFiles.add(postFile);
            return this;
        }

        /** Any additional post fields */
        public Map<String, String> getPostFields() {
            return postFields;
        }

        /** Any additional post fields */
        public UploadSetting addPostField(String name, String value) {
            this.postFields.put(name, value);
            return this;
        }

        /** e.g., "name=Asprise; domain=asprise.com" */
        public String getCookies() {
            return cookies;
        }

        /** e.g., "name=Asprise; domain=asprise.com" */
        public UploadSetting setCookies(String cookies) {
            this.cookies = cookies;
            return this;
        }

        /** Authentication, e.g., "user:pass" */
        public String getAuth() {
            return auth;
        }

        /** Authentication, e.g., "user:pass" */
        public UploadSetting setAuth(String auth) {
            this.auth = auth;
            return this;
        }

        /** e.g., "Referer: http://asprise.com" */
        public List<String> getHeaders() {
            return headers;
        }

        /** e.g., "Referer: http://asprise.com" */
        public UploadSetting addHeaders(String header) {
            this.headers.add(header);
            return this;
        }

        /** Saves response to the given file instead of returning response. */
        public File getSaveResponseToFile() {
            return saveResponseToFile;
        }

        /** Saves response to the given file instead of returning response. */
        public UploadSetting setSaveResponseToFile(File saveResponseToFile) {
            this.saveResponseToFile = saveResponseToFile;
            return this;
        }

        /** Log file to record HTTP operations. */
        public String getLogToFile() {
            return logToFile;
        }

        /** Log file to record HTTP operations. */
        public UploadSetting setLogToFile(String httpLogFile) {
            this.logToFile = httpLogFile;
            return this;
        }

        public static UploadSetting fromJsonMap(Map json) throws IOException {
            UploadSetting setting = new UploadSetting();
            setting.url = JsonUtils.attrValue(json, "url", null);
            setting.maxRetries = JsonUtils.attrValueAsInt(json, "max_retries", DEFAULT_MAX_RETRIES);
            Map postFieldsInJson = (json.get("post_fields") instanceof Map) ? (Map)json.get("post_fields") : null;
            if(postFieldsInJson != null) {
                setting.postFields.putAll(postFieldsInJson);
            }
            setting.postFileFieldName = JsonUtils.attrValue(json, "post_file_field_name", DEFAULT_POST_FILE_FIELD_NAME);

            List postFilesInJson = (List) json.get("post_files");
            if(postFilesInJson != null) {
                setting.postFiles.addAll(postFilesInJson);
            }

            setting.cookies = JsonUtils.attrValue(json, "cookies", null);
            setting.auth = JsonUtils.attrValue(json, "auth", null);

            List headersInJson = (List) json.get("headers");
            if(headersInJson != null) {
                setting.headers.addAll(headersInJson);
            }

            setting.logToFile = JsonUtils.attrValue(json, "log_file", null);
            if(!StringUtils.isEmpty(JsonUtils.attrValue(json, "to_file", null))) {
                setting.saveResponseToFile = new File(JsonUtils.attrValue(json, "to_file", null));
            }

            setting.maxOperationTime = JsonUtils.attrValueAsInt(json, "max_operation_time", DEFAULT_MAX_OPERATION_TIME);
            return setting;
        }

        public Map<String, Object> toJsonObject() {
            Map<String, Object> json = new HashMap<String, Object>();
            json.put("url", url);
            // json.put("method", "post"); // default is post
            json.put("max_retries", maxRetries);

            if(postFields != null && postFields.size() > 0) {
                json.put("post_fields", postFields);
            }

            json.put("post_file_field_name", postFileFieldName);

            if(postFiles != null && postFiles.size() > 0) {
                json.put("post_files", postFiles);
            }

            if(! StringUtils.isEmpty(cookies)) {
                json.put("cookies", cookies);
            }

            if(! StringUtils.isEmpty(auth)) {
                json.put("auth", auth);
            }

            if(headers != null && headers.size() > 0) {
                json.put("headers", headers);
            }

            if(logToFile != null) {
                json.put("log_file", logToFile);
            }

            if(saveResponseToFile != null) {
                json.put("to_file", saveResponseToFile.getAbsolutePath());
            }

            json.put("max_operation_time", maxOperationTime);
            return json;
        }
    }
}
