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
package com.asprise.imaging.core.util.system;

import com.asprise.imaging.core.Imaging;

import java.io.File;
import java.io.IOException;


public class NativeScanLibHelper {

    protected static boolean loaded = false;

    static final String WIN_DLL_NAME_32bit = "asprise_imaging.dll";
    static final String WIN_DLL_NAME_64bit = "asprise_imaging_x64.dll";

    public static void loadScanLib() {
        if(loaded) {
            log("Native library already loaded.");
            return;
        }

        String libFileName = null;
        if(Utils.isWindows()) {
            libFileName = Utils.is64Bit() ? WIN_DLL_NAME_64bit : WIN_DLL_NAME_32bit;
        } else if(Utils.isMac()) {
            System.err.println("TODO: Mac is not supported");
        } else { 
            System.err.println("TODO: Linux/Unix is not supported");
        }
        if(libFileName == null) {
            System.err.println("Failed to detect lib file name. os.name=" + System.getProperty("os.name") + ", os.arch=" + System.getProperty("os.arch"));
            return;
        }

        File fileLib = null;
        try {
            if(StringUtils.isEmpty(System.getProperty("ASPRISE_IMAGING_LIB_FILE"))) { 
                fileLib = Utils.extractResourceToFolder(NativeScanLibHelper.class.getClassLoader(),
                        Utils.firstNotEmpty(System.getProperty("ASPRISE_IMAGING_LIB_FOLDER"),
                                libFileName), Utils.getTempFolderWritable(false), "true".equalsIgnoreCase(System.getProperty("ASPRISE_IMAGING_LIB_FORCE_WRITE")));
                log("Loading native library from file: " + fileLib.getAbsolutePath());
                System.load(fileLib.getAbsolutePath());
            } else { 
                fileLib = new File(System.getProperty("ASPRISE_IMAGING_LIB_FILE"));
                log("Loading native library from file: " + System.getProperty("ASPRISE_IMAGING_LIB_FILE"));
                if(! fileLib.exists()) {
                    System.err.println("ERROR: LIB doesn't exist: " + fileLib.getAbsolutePath());
                }
                System.load(System.getProperty("ASPRISE_IMAGING_LIB_FILE"));
            }
            log("Native library loaded successfully.");
            loaded = true;
        } catch (UnsatisfiedLinkError ule) {
            if(ule.getMessage().contains("Can't load IA 32-bit .dll on a AMD 64-bit platform")) {
                System.err.println("You are running 64bit Java. Please load " + WIN_DLL_NAME_64bit + " instead of " + WIN_DLL_NAME_32bit);
            }
            String osUrlEncoded = System.getProperty("os.name").replace(" ", "%20");
            System.err.println("\n>>> UnsatisfiedLinkError occurs. To fix it, visit http://asprise.com/ocr/fix-link-error?os=" + osUrlEncoded);
            System.err.println(getDebugInfo(fileLib));
            ule.printStackTrace();
            if(ule.getMessage() != null && ule.getMessage().contains("already loaded")) {
                log("Skipped loading native library as: " + ule);
            }
            System.err.println("\n>>> Relax, it's easy to fix: http://asprise.com/ocr/fix-link-error?os=" + osUrlEncoded);
        } catch (Throwable e) {
            System.err.println(">>> Asprise will be glad to help you. Please copy the follow text and email to support@asprise.com");
            System.err.println(getDebugInfo(fileLib));
            e.printStackTrace();
            Utils.displayErrorDialogAndThrowException("Failed to load native library: " + e.getMessage() + "\n" + getDebugInfo(fileLib), e, false);
        }
    }

    static String getDebugInfo(File libFile) {
        StringBuilder sb = new StringBuilder("\n");
        if(libFile != null && !Utils.isWindows()) { 
            String[] cmds = null;
            if(Utils.isMac()) {
                cmds = new String[] {"/usr/bin/otool", "-L", libFile.getAbsolutePath()};
            } else {
                cmds = new String[] {"/usr/bin/ldd", libFile.getAbsolutePath()};
            }
            String deps = Utils.execute(true, false, false, cmds, null, null);
            if(! StringUtils.isEmpty(deps)) {
                sb.append("\nDependency information: " + deps);
            }
        }

        String ldLibPath = System.getenv("LD_LIBRARY_PATH");
        if(! StringUtils.isEmpty(ldLibPath)) {
            sb.append("\nLD_LIBRARY_PATH=" + ldLibPath);
        }

        sb.append("\njava.library.path=" + System.getProperty("java.library.path"));
        sb.append("\n").append(Utils.getEnvInfo(false));

        return sb.toString();
    }

    
    public static String loadDllAndReturnVersion(String dllPath) {
        setLibFile(dllPath);
        loadScanLib();
        return Imaging.getLibraryVersion();
    }

    static void setLibFile(String libFile) {
        System.setProperty("ASPRISE_IMAGING_LIB_FILE", libFile);
    }


    public static void main(String[] args) throws IOException {
        if(args.length < 1) {
            System.out.println("Usage: java com.asprise.ocr.util.OcrLibHelper PATH_TO_LIB_FILE");

        }
        String libFile = "";

        System.out.println("Version: " + loadDllAndReturnVersion("C:\\J\\dev-projects\\asprise\\scan\\projects\\java\\build\\OUTPUT\\c\\Win32\\Debug\\asprise_imaging.dll"));
    }

    public static void log(String s) {
        if(shouldPrintLog()) {
            System.out.println(s);
        }
    }

    private static int shouldPrintLog = -1;
    private static boolean shouldPrintLog() {
        if(shouldPrintLog < 0) {
            shouldPrintLog = (!StringUtils.isEmpty(System.getProperty("DEBUG"))) ? 1 : 0;
        }

        return shouldPrintLog == 1;
    }
}
