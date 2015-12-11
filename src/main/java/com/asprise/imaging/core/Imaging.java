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

import com.asprise.imaging.core.scan.twain.Source;
import com.asprise.imaging.core.scan.twain.TwainException;
import com.asprise.imaging.core.scan.twain.TwainNative;
import com.asprise.imaging.core.scan.twain.TwainUtil;
import com.asprise.imaging.core.util.JsonUtils;
import com.asprise.imaging.core.util.system.NativeScanLibHelper;
import com.asprise.imaging.core.util.system.Utils;
import com.fasterxml.jackson.jr.ob.JSON;
import com.fasterxml.jackson.jr.ob.JSONComposer;
import com.fasterxml.jackson.jr.ob.comp.ArrayComposer;
import com.fasterxml.jackson.jr.ob.comp.ObjectComposer;
import sun.awt.windows.WComponentPeer;

import javax.swing.*;
import java.awt.Component;
import java.awt.Container;
import java.awt.Window;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Imaging API.
 */
public class Imaging {
    public static final int LOG_LEVEL_ERROR = 1;
    public static final int LOG_LEVEL_WARN = 2;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_DEBUG = 4;

    public static final String LOG_TO_STDOUT = "stdout";
    public static final String LOG_TO_STDERR = "stderr";

    public static final String OUTPUT_RETURN_BASE64 = "return-base64";
    public static final String OUTPUT_RETURN_BASE64_THUMB = "return-base64-thumbnail";
    public static final String OUTPUT_RETURN_HANDLE = "return-handle";
    public static final String OUTPUT_RETURN_HANDLE_THUMB = "return-handle-thumbnail";
    public static final String OUTPUT_SAVE = "save";
    public static final String OUTPUT_SAVE_THUMB = "save-thumbnail";
    public static final String OUTPUT_UPLOAD = "upload";
    public static final String OUTPUT_UPLOAD_THUMB = "upload-thumbnail";

    public static final String FORMAT_JPG = "jpg";
    public static final String FORMAT_PNG = "png";
    public static final String FORMAT_BMP = "bmp";
    public static final String FORMAT_TIF = "tif";
    public static final String FORMAT_PDF = "pdf";

    public static final String TIFF_COMPRESSION_CCITT_G3 = "G3";
    public static final String TIFF_COMPRESSION_CCITT_G4 = "G4";
    public static final String TIFF_COMPRESSION_LZW	= "LZW";
    public static final String TIFF_COMPRESSION_RLE	= "RLE";
    public static final String TIFF_COMPRESSION_NONE = "NONE";

    public static final String TIFF_COMPRESSION_PACKBITS = "PACKBITS";
    public static final String TIFF_COMPRESSION_ZIP	= "ZIP";

    public static final String EXIF_NAME_DocumentName = "DocumentName";
    public static final String EXIF_NAME_ImageDescription = "ImageDescription";
    public static final String EXIF_NAME_EquipMake = "EquipMake";
    public static final String EXIF_NAME_EquipModel	= "EquipModel";
    // public static final String EXIF_NAME_SoftwareUsed = "SoftwareUsed"
    /** Limit length to max 20 */
    // public static final String EXIF_NAME_DateTime			"DateTime"
    public static final String EXIF_NAME_Copyright = "Copyright";
    public static final String EXIF_NAME_UserComment = "UserComment";

    static {
        try {
            NativeScanLibHelper.loadScanLib();
        } catch (Throwable t) {
            System.err.println("Unable to load native library: " + t);
            t.printStackTrace();
        }
    }

    String appId;
    int windowHandle;

    public Imaging(String appId, int windowHandle) {
        this.appId = appId;
        this.windowHandle = windowHandle;
    }

    public Imaging(Component owningUI) {
        this.appId = "Java";
        this.windowHandle = (int)getOwningWindowHandle(owningUI);
    }

    private static ExecutorService executorServiceForScanning;

    /** Use this executor service to make sure that all scanning related code is executed from the same thread. */
    public static ExecutorService getDefaultExecutorServiceForScanning() {
        if(executorServiceForScanning == null) {
            synchronized (Imaging.class) {
                if(executorServiceForScanning == null) {
                    executorServiceForScanning = Executors.newSingleThreadExecutor(new ThreadFactory() { // custom factory for user-friendly thread name
                        final AtomicInteger threadNumber = new AtomicInteger(1);
                        ThreadFactory defaultThreadFactory = Executors.defaultThreadFactory();

                        @Override
                        public Thread newThread(Runnable r) {
                            Thread thread = defaultThreadFactory.newThread(r);
                            thread.setName("scan" + (threadNumber.get() == 1 ? "" : "-" + threadNumber));
                            return thread;
                        }
                    });
                }
            }
        }
        return executorServiceForScanning;
    }

    /**
     * Executes and wait indefinitely until the result is returned or exception occurs
     * @param callable
     * @param <R>
     * @return
     * @throws Throwable in case of exeception occurred during execution
     */
    public static <R> R executeInDefaultExecutorServiceAndWaitTillReturn(Callable<R> callable) throws Throwable {
        List<Callable<R>> list = new ArrayList<Callable<R>>();
        list.add(callable);
        try {
            List<Future<R>> futures = getDefaultExecutorServiceForScanning().invokeAll(list);
            Future<R> returned = futures.get(0);
            return returned.get();
        } catch (Throwable e) {
            if(e instanceof ExecutionException) {
                throw ((ExecutionException)e).getCause();
            } else {
                throw e;
            }
        }
    }

    /**
     * Performs scanning from a device and output (return, save, and/or upload).
     * @param request scan request object.
     * @param sourceName the exact source name or "select" to prompt dialog selection; "default" to use default source; "current" refers to current opened source if any.
     * @param showUI set to true to use scanner UI or false to hide the UI. Set to true for maximum compatibility.
     * @param modalUI whether the scanner UI should be modal. Set to to true if you are not sure.
     * @return Scan result or null if user cancels.
     * @throws TwainException if failed.
     */
    public Result scan(Request request, String sourceName, boolean showUI, boolean modalUI) {
        return scan(JsonUtils.jsonSerialize(request.toJsonObject(), true), sourceName, showUI, modalUI);
    }

    /**
     * Performs scanning from a device and output (return, save, and/or upload).
     * @param scanRequestInJson scan request in JSON format.
     * @param sourceName the exact source name or "select" to prompt dialog selection; "default" to use default source; "current" refers to current opened source if any.
     * @param showUI set to true to use scanner UI or false to hide the UI. Set to true for maximum compatibility.
     * @param modalUI whether the scanner UI should be modal. Set to to true if you are not sure.
     * @return Scan result or null if user cancels.
     * @throws TwainException if failed.
     */
    public Result scan(String scanRequestInJson, String sourceName, boolean showUI, boolean modalUI) {
        ensureScanFuncsCallInTheSameThread();
        String rawResult = scanAndReturnRaw(scanRequestInJson, sourceName, showUI, modalUI);
        if(rawResult != null && rawResult.startsWith("<error:")) {
            throw new TwainException(rawResult);
        }
        try {
            Map<String, Object> root = JSON.std.mapFrom(rawResult);
            Result r = Result.createScanResult(root);
            return r;
        } catch (Throwable t) {
            throw new TwainException(rawResult, t);
        }
    }

    /**
     * Performs scanning from a device and output result in JSON.
     * @param scanRequestInJson scan request in JSON format.
     * @param sourceName the exact source name or "select" to prompt dialog selection; "default" to use default source; "current" refers to current opened source if any.
     * @param showUI set to true to use scanner UI or false to hide the UI. Set to true for maximum compatibility.
     * @param modalUI whether the scanner UI should be modal. Set to to true if you are not sure.
     * @return Scan result in JSON or null if user cancels.
     * @throws TwainException if failed.
     */
    public String scanAndReturnRaw(String scanRequestInJson, String sourceName, boolean showUI, boolean modalUI) {
        ensureScanFuncsCallInTheSameThread();
        String rawResult = callNativeFunc(TwainNative.FUNC_twain_scan, appId,
                // FUJITSU fi-5120Cdj waits forever if we use windowHandle (from JFrame) here
                0, // windowHandle,
                scanRequestInJson, sourceName, showUI, modalUI);
        if(rawResult != null && rawResult.contains("failed to open data source: TWRC_CANCEL")) {
            return null;
        }
        return rawResult;
    }

    /**
     * Performs image conversion and output (return, save, and/or upload).
     * @param request scan request object.
     * @return Scan result or null if user cancels.
     * @throws TwainException if failed.
     */
    public Result convert(Request request) {
        String requestInJson = JsonUtils.jsonSerialize(request.toJsonObject(), true);
        String result = callNativeFunc(TwainNative.FUNC_image_output, requestInJson);
        if(result != null && result.startsWith("<error:")) {
            throw new TwainException(result);
        }
        try {
            Map<String, Object> root = JSON.std.mapFrom(result);
            return Result.createScanResult(root);
        } catch (Throwable t) {
            throw new TwainException(result, t);
        }
    }

    /**
     * Get information about the image, e.g. bytes, width, height, etc.
     * @param imageFile Path to the image file.
     * @return Information as map
     * @throws TwainException if failed.
     */
    public Map<String, Object> getImageInfo(String imageFile) {
        String result = callNativeFunc(TwainNative.FUNC_image_info, imageFile);
        try {
            Map<String, Object> root = JSON.std.mapFrom(result);
            return root;
        } catch (Throwable t) {
            throw new TwainException(result, t);
        }
    }

    /**
     * Performs operations on image, e.g., rotate, crop, scale, gray, etc.
     * @param inputImageFile Path to the input file.
     * @param commands Processing commands
     * @param outputImageFile Path to the output file.
     * @return Information as map
     * @throws TwainException if failed.
     */
    public Map<String, Object> processImage(String inputImageFile, String commands, String outputImageFile) {
        String result = callNativeFunc(TwainNative.FUNC_image_process, inputImageFile, commands, outputImageFile);
        try {
            Map<String, Object> root = JSON.std.mapFrom(result);
            return root;
        } catch (Throwable t) {
            throw new TwainException(result, t);
        }
    }

    /**
     * Retrieve list of sources (i.e., devices) optionally with caps; the default source has "default": true in JSON format.
     * @param nameOnly if true, return list of device names separated by ',' otherwise return device info in JSON format.
     * @param capsToRetrieve only effective if nameOnly is false - If set, return JSON string; can be cap name or code separated by comma or 'all' to list all caps supported.
     * @param detectDeviceType detect whether the device has ADF and/or flatbed.
     * @param excludeTwainDsOnWia exclude WIA synthesized sources
     * @return JSON or comma separated string depending on nameOnly.
     */
    public List<Source> scanListSources(boolean nameOnly, String capsToRetrieve, boolean detectDeviceType, boolean excludeTwainDsOnWia) {
        ensureScanFuncsCallInTheSameThread();
        String raw = callNativeFunc(TwainNative.FUNC_twain_list_sources, appId, windowHandle, nameOnly, capsToRetrieve, false, detectDeviceType, excludeTwainDsOnWia);
        if(raw == null || raw.startsWith("<error")) {
            throw new TwainException("Failed to list sources: " + raw);
        }

        List<Source> sources = new ArrayList<Source>();
        if(nameOnly) {
            if(raw.trim().length() == 0) {
                return sources;
            }

            StringTokenizer st = new StringTokenizer(raw, ",");
            while(st.hasMoreTokens()) {
                String name = st.nextToken();
                if(name != null && name.trim().length() > 0) {
                    sources.add(new Source(name));
                }
            }
        } else {
            sources = Source.createSourceList((List) TwainUtil.jsonDeserialize(raw));
        }

        return sources;
    }

    /**
     * Retrieve list of sources (i.e., devices) with all caps; the default source has "default": true in JSON format.
     * @return JSON representation of all sources with details.
     */
    public List<Source> scanListSourcesWithFullDetails() {
        return scanListSources(false, "all", true, true);
    }

    /**
     * Lists all sources with names only.
     * @return sources with names only.
     */
    public List<Source> scanListSources() {
        return scanListSources(true, null, false, true);
    }

    /**
     * Gets the name of the default source or null if none presents.
     * @return the name of the default source or null if none presents.
     */
    public String scanGetDefaultSourceName() {
        ensureScanFuncsCallInTheSameThread();
        String result = callNativeFunc(TwainNative.FUNC_twain_get_default_source_name, appId, windowHandle);
        return result;
    }

    /**
     * Prompts system device selection dialog for the user to select the default device.
     * @param defaultSelectedSourceName the name of the source to be pre-selected or null to use the system default.
     * return: the name of the source selected or empty string if user cancels or error occurs.
     */
    public String scanSelectDefaultSource(String defaultSelectedSourceName) {
        ensureScanFuncsCallInTheSameThread();
        String result = callNativeFunc(TwainNative.FUNC_twain_select_default_source, appId, windowHandle, defaultSelectedSourceName);
        return result;
    }

    /**
     * Prompts system device selection dialog for the user to select the default device.
     * return: the name of the source selected or empty string if user cancels or error occurs.
     */
    public String scanSelectDefaultSource() {
        return scanSelectDefaultSource(null);
    }

    /**
     * Gets the current Asprise Scan version.
     * @return the current Asprise Scan version.
     */
    public static String getLibraryVersion() {
        return callNativeFunc(TwainNative.FUNC_version);
    }


    private volatile Thread calledInThread;

    protected void ensureScanFuncsCallInTheSameThread() {
        if(calledInThread == null) {
            calledInThread = Thread.currentThread();
        } else {
            if(calledInThread != Thread.currentThread()) {
                throw new TwainException("Scan operations should be performed in the same thread. Previous used thread: " + calledInThread.getName() + ", current thread: " + Thread.currentThread().getName());
            }
        }
    }

    /**
     *
     * @param functionName
     * @param args Must be of type: String, Integer or Boolean;
     * @return
     * @throws RuntimeException if failed to form request in JSON format.
     */
    public static String callNativeFunc(String functionName, Object... args) {
        String jsonRequest = null;
        try {
            ArrayComposer<ObjectComposer<JSONComposer<String>>> argArray = JSON.std.composeString().startObject().put("function", functionName).startArrayField("args");
            for(int i = 0; args != null && i < args.length; i++) {
                Object arg = args[i];
                if(arg instanceof String) {
                    argArray = argArray.add((String)arg);
                } else if(arg instanceof Integer) {
                    argArray = argArray.add(((Number)arg).intValue());
                } else if(arg instanceof Long) {
                    argArray = argArray.add(((Number)arg).longValue());
                } else if(arg instanceof Boolean) {
                    argArray = argArray.add(((Boolean) arg).booleanValue());
                } else if(arg == null) {
                    argArray = argArray.addNull();
                } else {
                    throw new IllegalArgumentException("Unsupported arg type: " + arg.getClass().getName() + "; object: " + arg);
                }
            }
            jsonRequest = argArray.end().end().finish();
        } catch (Throwable e) {
            if(e instanceof RuntimeException) {
                throw (RuntimeException)e;
            } else {
                throw new RuntimeException(e);
            }
        }

        if(jsonRequest == null || jsonRequest.length() == 0) {
            throw new IllegalArgumentException("Invalid requst: " + jsonRequest);
        }

        // System.out.println(jsonRequest);
        String result = TwainNative.invokeFunc(jsonRequest);
        return result;
    }

    /** Return the owning window handle of the given component or 0 if failed to determine. */
    private static long getOwningWindowHandle(Component component) {
        if(component == null)
            return 0;

        Window window = null;
        Container container = component.getParent();
        while(true) {
            if(container == null) {
                return 0;
            }

            if(container instanceof Window) {
                window = (Window) container;
                break;
            }

            container = container.getParent();
        }

        return window.getPeer() != null ? ((WComponentPeer) window.getPeer()).getHWnd() : 0;
    }

    /**
     * Configure logging settings.
     * @param level Any of {@linkplain #LOG_LEVEL_INFO} (default), {@linkplain #LOG_LEVEL_WARN}, etc.
     * @param logFilePath path to the target output file or special values: "stdout", "stderr" for console logging, null or empty string to disable logging.
     */
    public static void configureNativeLogging(int level, String logFilePath) {
        TwainNative.configureLogging(level, logFilePath);
    }

    public static void main(String[] args) {
        if(args == null) {
            args = new String[0];
        }

        // Try UI mode if possible
        try {
            if(!(args.length > 0 && "console".equalsIgnoreCase(args[args.length - 1])) && !java.awt.GraphicsEnvironment.isHeadless()) {
                Class<?> cls = Class.forName("com.asprise.imaging.scan.ui.workbench.demo.FrameScanDemo");
                Method meth = cls.getMethod("main", String[].class);
                String[] params = null; // init params accordingly
                meth.invoke(null, (Object) args); // static method doesn't have an instance
                return;
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        String copyright = "Copyright Asprise, " + Calendar.getInstance().get(Calendar.YEAR) + ". All Rights Reserved. Visit www.asprise.com";
        String version = "Library version: " + getLibraryVersion();

        try {
            JOptionPane.showMessageDialog(null, version, copyright, JOptionPane.OK_OPTION | JOptionPane.INFORMATION_MESSAGE);
        } catch (Throwable t) {
            // ignore exception
        }

        System.out.println(copyright);
        System.out.println(version);
        System.out.println(Utils.getEnvInfo(false));
    }
}
