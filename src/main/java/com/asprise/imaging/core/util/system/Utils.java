/**********************************************************************************************
 *
 * Asprise Scanning and Imaging API - http://asprise.com/document-scanner-image-pdf/java-scanning-api-overview.html
 * Copyright (C) 1998-2026. Asprise Inc. <asprise.com>
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
import com.asprise.imaging.core.scan.twain.TwainNative;
import com.asprise.imaging.core.util.Base64;
import com.asprise.imaging.core.util.prefs.FileSystemPreferencesFactory;

import javax.swing.JOptionPane;

import java.awt.*;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigInteger;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.security.CodeSource;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.util.*;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;


public class Utils {
    static String osName = System.getProperty("os.name");
    static String osArch = System.getProperty("os.arch");

    private static volatile Class _entryClass;

    
    public static boolean setEntryClassIfNotYet(Class cls) {
        if(_entryClass == null) {
            synchronized (Utils.class) {
                if(_entryClass != null) {
                    _entryClass = cls;
                    return true;
                }
            }
        }
        return false;
    }

    
    public static Class getEntryClass() {
        return _entryClass;
    }

    
    public static Preferences getPrefsNode(String nodeName) {
        Preferences prefs = null;
        try {
            prefs = new FileSystemPreferencesFactory().userRoot().node(nodeName);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        if(prefs != null) {
            return prefs;
        }

        try {
            prefs = Preferences.userRoot().node(nodeName);
        } catch (Throwable t) {
            t.printStackTrace();
        }
        if(prefs != null) {
            return prefs;
        }

        return Preferences.userRoot();
    }

    
    public static String getSystemPropertyOrEnv(String key, String defaultValueToReturn) {
        String value = System.getProperty(key);
        if(value == null) {
            value = System.getenv(key);
        }
        return value == null || value.trim().length() == 0 ? defaultValueToReturn : value;
    }

    
    public static boolean getSystemPropertyOrEnvAsBoolean(String key, boolean defaultValueToReturn) {
        String value = getSystemPropertyOrEnv(key, null);
        if(value != null) {
            value = value.trim();
        }
        if("true".equalsIgnoreCase(value) || "t".equalsIgnoreCase(value) || "1".equalsIgnoreCase(value)) {
            return true;
        } else if("false".equalsIgnoreCase(value) || "f".equalsIgnoreCase(value) || "0".equalsIgnoreCase(value)) {
            return false;
        } else {
            return defaultValueToReturn;
        }
    }

    
    public static boolean openFileUsingDesktop(File file) {

        try {
            Class classDesktop = Class.forName("java.awt.Desktop");
            if(classDesktop == null) {
                System.out.println("Class not found: java.awt.Desktop");
                return false;
            }
            Method methodGetDesktop = classDesktop.getMethod("getDesktop");
            Object desktop = methodGetDesktop.invoke(null);
            if(desktop == null) {
                System.out.println("java.awt.Desktop.getDesktop() returns null");
                return false;
            }

            Method methodOpen = desktop.getClass().getMethod("open", File.class);
            methodOpen.invoke(desktop, file);
            return true;
        } catch (Throwable e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public static long getHwnd(Component component) {
        return TwainNative.getHwnd(component);
    }

    
    public static boolean isWindows() {
        return osName != null && osName.toLowerCase().startsWith("windows");
    }

    private static String _windowsVer = null;
    
    public static String getWindowsVer() {
        if(_windowsVer == null) {
            try {
                _windowsVer = execute(true, false, false, new String[]{"cmd", "/c", "ver"}, null, null);
                if(_windowsVer != null) {
                    _windowsVer = _windowsVer.trim();
                }
            } catch (Throwable t) {

            }
        }
        return _windowsVer;
    }

    public static boolean isWindowsOs64Bit() {
        String procArch = System.getenv("PROCESSOR_ARCHITECTURE");
        if(procArch != null && procArch.contains("64")) {
            return true;
        }
        procArch = System.getenv("PROCESSOR_ARCHITEW6432");
        if(procArch != null && procArch.contains("64")) {
            return true;
        }
        return false;
    }

    
    public static boolean isMac() {
        return osName != null && osName.toLowerCase().startsWith("mac");
    }

    
    public static boolean is64Bit() {
        return osArch.contains("64");
    }

    
    public static boolean is32Bit() {
        return !is64Bit();
    }

    public static boolean isJava5() {
        String javaVersion = System.getProperty("java.version");
        boolean isJava5 = javaVersion != null && javaVersion.startsWith("1.5");
        return isJava5;
    }

    static Boolean inIde;

    
    private static boolean isInIde() {
        if(inIde != null) {
            return inIde;
        }
        try {

            String classPath = System.getProperty("java.class.path");
            inIde = classPath != null && classPath.contains("IntelliJ");
            if(inIde) {
                System.out.println("Running from inside IDE.");
            }
        } catch (Throwable e) {
            inIde = false;
        }
        return inIde;
    }

    
    public static boolean isAspriseDevInIde() {
        return isInIde() && !StringUtils.isEmpty(System.getenv("ASPRISE_DEV"));
    }

    
    public static String flagToString(long value, Object... flagValueAndNames) {
        if(flagValueAndNames.length % 2 != 0) {
            throw new IllegalArgumentException("Flag values and names must come in pair");
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < flagValueAndNames.length; ) {
            if((value & (Long)flagValueAndNames[i]) != 0) {
                if(sb.length() > 0) {
                    sb.append(" | ");
                }
                sb.append(flagValueAndNames[i+1]);
            }
            i += 2;
        }

        for(int i = 0; i < flagValueAndNames.length; ) {
            if((value & (Long)flagValueAndNames[i]) != 0) {
                value -= (Long)flagValueAndNames[i];
            }
            i += 2;
        }
        boolean exhausted = value == 0;

        if(! exhausted) {
            sb.append(" (0x").append(Long.toHexString(value)).append(")");
        }

        return sb.toString();
    }

    
    public static long clearFlags(long value, long... flags) {
        for(int i = 0; i < flags.length; i++) {
            if((value & flags[i]) != 0) {
                value -= flags[i];
            }
        }
        return value;
    }

    public static String getEnvInfo(boolean withClassPath) {
        StringBuilder sb = new StringBuilder();
        sb.append("OS: ").append(System.getProperty("os.name")).append(" ").append(System.getProperty("os.version")).append(" ").append(System.getProperty("os.arch")).append("\n");
        if(! isWindows()) {
            String uname = null;
            try {
                uname = execute(true, false, false, new String[]{"/usr/bin/uname", "-a"}, null, null);
            } catch (Throwable t) {

            }
            if(StringUtils.isEmpty(uname)) {
                try {
                    uname = execute(true, false, false, new String[]{"/bin/uname", "-a"}, null, null);
                } catch (Throwable t) {

                }
            }
            if(! StringUtils.isEmpty(uname)) {
                sb.append(uname).append("\n");
            }
        }
        sb.append("JVM: ").append(Utils.firstNotNull(System.getProperty("java.runtime.version"), System.getProperty("java.version"))).append(" ").append(Utils.is64Bit() ? "64bit" : "32bit")
            .append(" by ").append(System.getProperty("java.vendor")).append("\n");
        if(withClassPath) {
            sb.append("Classpath: ").append(System.getProperty("java.class.path"));
        }
        return sb.toString();
    }

    public static String getRunInfo() {
        StringBuilder sb = new StringBuilder();
        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
        Thread[] threads = threadSet.toArray(new Thread[threadSet.size()]);
        sb.append("Threads: [").append(threads.length).append("] ").append(Arrays.toString(threads));

        return sb.toString();
    }

    public static Object firstNotNull(Object... args) {
        for(Object o : args) {
            if(o != null) {
                return o;
            }
        }
        return null;
    }

    public static String firstNotEmpty(String... args) {
        for(String s : args) {
            if(s != null && s.trim().length() > 0) {
                return s;
            }
        }
        return null;
    }

    public static boolean isFileInUse(File file) {

        if(file == null || !file.isFile()) {
            return false;
        }

        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            return false;
        } catch (Throwable e) {

            return true;
        } finally {
            if(outputStream != null) {
                try {
                    outputStream.close();
                } catch (Throwable t) {

                }
            }
        }

    }

    public static void displayErrorDialogAndThrowException(String errorMesg, Throwable t, boolean rethrowException) {
        try {
            if(errorMesg != null && errorMesg.length() > 80) {
                errorMesg = errorMesg.replaceAll("(.{80})", "$1\n");
            }
            JOptionPane.showMessageDialog(null, errorMesg, "Fatal error", JOptionPane.ERROR_MESSAGE);
        } catch (HeadlessException e) {
            e.printStackTrace();
        }
        if(rethrowException) {
            throw new RuntimeException(errorMesg, t);
        }
    }

    
    public static boolean testCreateFileInside(File folder) {
        if(! folder.exists()) { 
            try {
                folder.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(! folder.isDirectory()) {
            return false;
        }

        File f = new File(folder, "t-" + System.currentTimeMillis() + "-" + StringUtils.randomAlphanumeric(3));
        try {
            boolean fileCreated = f.createNewFile();
            boolean fileDeleted = f.delete();
            return fileCreated;
        } catch (Throwable t) {
            return false;
        }
    }

    static File dirTempWritableAnyChars = null;
    static File dirTempWritableAsciiOnly = null;

    public static File getTempFolderWritable(boolean asciiCharsOnly) {
        if(asciiCharsOnly) {
            if (dirTempWritableAsciiOnly != null) {
                return dirTempWritableAsciiOnly;
            }
        } else {
            if(dirTempWritableAnyChars != null) {
                return dirTempWritableAnyChars;
            }
        }

        File[] dirs = new File[] {
                System.getenv("ASPRISE_TEMP_FOLDER") == null ? null : new File(System.getenv("ASPRISE_TEMP_FOLDER"))
                , System.getenv("TMP") == null ? null : new File(System.getenv("TMP"))
                , System.getProperty("java.io.tmpdir") == null ? null : new File(System.getProperty("java.io.tmpdir")) 
                , System.getProperty("user.dir") == null ? null : new File(System.getProperty("user.dir")) 
                , new File(Utils.isWindows() ? ((System.getProperty("java.io.tmpdir") != null && System.getProperty("java.io.tmpdir").length() > 0 ? System.getProperty("java.io.tmpdir").substring(0, 1) : "C") + ":\\") : "/") 
                , Utils.isWindows() ? new File(System.getenv("HOMEDRIVE") + "\\tmp") : null
                , Utils.isWindows() ? new File(System.getenv("HOMEDRIVE") + "\\temp") : null
                , new File(".")
        };

        for(File dir : dirs) {
            if(asciiCharsOnly) {
                if (dir != null && (!asciiCharsOnly || isAllASCII(dir.getAbsolutePath())) && testCreateFileInside(dir)) {
                    dirTempWritableAsciiOnly = dir;
                    return dirTempWritableAsciiOnly;
                }
            } else {
                if (dir != null && testCreateFileInside(dir)) {
                    dirTempWritableAnyChars = dir;
                    return dirTempWritableAnyChars;
                }
            }
        }

        StringBuilder errorMesg = new StringBuilder("Failed to find a writable temp directory. Tried: ");
        for(int i = 0; i < dirs.length; i++) {
            errorMesg.append(i == 0 ? "" : ", ");
            errorMesg.append(dirs[i].getAbsolutePath());
        }

        displayErrorDialogAndThrowException(errorMesg.toString(), null, true);
        return null;
    }

    public static void unzipToFolder(File zipFile, File outputFolder) throws IOException {
        outputFolder.mkdirs();

        ZipInputStream zis = new ZipInputStream(new FileInputStream(zipFile));

        ZipEntry ze = zis.getNextEntry();

        byte[] buffer = new byte[1024 * 4];

        while(ze!=null){

            String fileName = ze.getName();
            File newFile = new File(outputFolder + File.separator + fileName);

            if(ze.isDirectory()) {
                newFile.mkdirs();
            } else {
                new File(newFile.getParent()).mkdirs();

                FileOutputStream fos = new FileOutputStream(newFile);

                int len;
                while ((len = zis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }

                fos.close();
            }
            ze = zis.getNextEntry();
        }

        zis.closeEntry();
        zis.close();
    }

    
    public static File extractResourceToFolder(ClassLoader classLoader, String resourcePath, File targetFolder, boolean forceWrite) throws IOException {
        int lastSlashPos = Math.max(resourcePath.lastIndexOf('/'), resourcePath.lastIndexOf('\\'));
        String simpleName = lastSlashPos > 0 ? resourcePath.substring(lastSlashPos + 1) : resourcePath;

        targetFolder.mkdirs();
        if(! targetFolder.exists()) {
            throw new IOException("Folder does not exist despite effort of attempting to create it: " + targetFolder.getAbsolutePath());
        }

        File targetFile = new File(targetFolder, simpleName);
        boolean skipWriting = false;
        try {
            if((!forceWrite) && targetFile.exists()) {
                String md5ExistingFile = getMd5(new FileInputStream(targetFile));
                String md5Stream = getMd5(classLoader.getResourceAsStream(resourcePath));
                if(md5ExistingFile != null && md5Stream != null && md5ExistingFile.equalsIgnoreCase(md5Stream)) { 
                    skipWriting = true;
                }
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

        if(targetFile.exists() && !skipWriting && isFileInUse(targetFile)) {
            while(targetFile.exists()) {
                targetFile = new File(targetFolder, Integer.toString((int) (Math.random() * 1000)) + "-" + simpleName);
                if(System.getenv("ASPRISE_DEV") != null) {
                    System.out.println("New file to avoid name collision: " + targetFile);
                }
            }
        }

        if(! skipWriting) {
            InputStream inputStream = classLoader.getResourceAsStream(resourcePath);
            if(inputStream == null) {

                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(resourcePath);
            }
            if(inputStream == null) {
                throw new IOException("Can not find resource: " + resourcePath);
            }

            try {
                FileOutputStream outputStream = new FileOutputStream(targetFile);
                byte[] buffer = new byte[1024 * 4];
                int bytesRead = 0;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }

                outputStream.close();

            } finally {
                inputStream.close();
            }
        }

        if(resourcePath.endsWith(".zip")) {
            String simpleNameWithoutExt = targetFile.getName().substring(0, targetFile.getName().lastIndexOf("."));
            File zipOutputFolder = new File(targetFile.getAbsoluteFile().getParentFile(), simpleNameWithoutExt);
            if(zipOutputFolder.exists() && zipOutputFolder.isFile()) { 
                zipOutputFolder.delete();
                zipOutputFolder = new File(zipOutputFolder.getAbsolutePath());
                zipOutputFolder.mkdirs();
            }
            unzipToFolder(targetFile, zipOutputFolder);
            return zipOutputFolder;
        }

        return targetFile;
    }

    
    public static String getMd5(File file) {
        try {
            return getMd5(new FileInputStream(file));
        } catch (Throwable e) {
            return null;
        }
    }

    
    public static boolean verifyMd5(File file, String expectedMd5) {
        if(file == null || expectedMd5 == null) {
            return false;
        }
        return expectedMd5.trim().equalsIgnoreCase(getMd5(file));
    }

    
    public static String getMd5(String s) {
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(s.getBytes("UTF-8"));
            return getMd5(inputStream);
        } catch (Throwable t) {
            return null;
        }
    }

    
    public static String getMd5(InputStream input) {
        if(input == null) {
            return null;
        }
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            DigestInputStream dis = new DigestInputStream(input, md);
            byte[] buffer = new byte[1024 * 8]; 
            while(dis.read(buffer) != -1) {
                ;
            }
            dis.close();
            byte[] raw = md.digest();

            BigInteger bigInt = new BigInteger(1, raw);
            StringBuilder hash = new StringBuilder(bigInt.toString(16));

            while(hash.length() < 32 ){
                hash.insert(0, '0');
            }
            return hash.toString();
        } catch (Throwable t) {
            return null;
        }
    }




    public static byte[] base64Decode(String base64) {
        base64 = base64.replaceAll("\\s", "");
        try {
            return Base64.base64ToByteArray(base64);
        } catch (Throwable e) {

            throw new RuntimeException("Base64 decode failed", e);
        }
    }

    public static String base64Encode(byte[] content) {
        try {
            return Base64.byteArrayToBase64(content);
        } catch (Throwable e) {

            throw new RuntimeException("Base64 encode failed", e);
        }
    }

    public static String getFileExtensionWithoutDot(String fileName) {
        String extension = "";

        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            extension = fileName.substring(i+1);
        }
        return extension;
    }

    public static boolean openInFileExplorer(String path) {
        if(isWindows()) {
            try {
                Process p = new ProcessBuilder("explorer.exe", path).start();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean showFilesInWindowsExplorer(File file) {
        if(isWindows()) {
            try {
                Process p = new ProcessBuilder("explorer.exe", "/select,",
                        "\"" + file.getAbsolutePath().replace("/", "\\") +"\"").start();
                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    
    public static boolean deleteDirectory(File directory, boolean cleanOnly) {
        if(directory.exists()){
            File[] files = directory.listFiles();
            if(null!=files){
                for(int i=0; i<files.length; i++) {
                    if(files[i].isDirectory()) {
                        deleteDirectory(files[i], true);
                    }
                    else {
                        files[i].delete();
                    }
                }
            }
        }
        return cleanOnly ? true : (directory.delete());
    }

    
    public static void saveToFile(URL url, File target) throws IOException {
        ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream output = new FileOutputStream(target);
        output.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
    }

    static int isLaptop = Integer.MIN_VALUE;

    
    public static int isLaptop() {
        if(isLaptop != Integer.MIN_VALUE) {
            return isLaptop;
        }

        if(! isWindows()) {
            return -1;
        }
        String result = "";
        File file = null;
        try {
            file = File.createTempFile("info", ".vbs", null);

            FileWriter fw = new FileWriter(file);

            String vbs = "strComputer = \".\"\n" +
                    "Set objWMIService = GetObject(\"winmgmts:\" _\n" +
                    "    & \"{impersonationLevel=impersonate}!\\\\\" & strComputer & \"\\root\\cimv2\")\n" +
                    "Set colChassis = objWMIService.ExecQuery _\n" +
                    "    (\"Select * from Win32_SystemEnclosure\")\n" +
                    "For Each objChassis in colChassis\n" +
                    "    For  Each strChassisType in objChassis.ChassisTypes\n" +
                    "        Wscript.Echo strChassisType\n" +
                    "    Next\n" +
                    "Next\n";

            fw.write(vbs);
            fw.close();

            Process p = Runtime.getRuntime().exec("cscript //NoLogo \"" + file.getPath() + "\"");
            BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = input.readLine()) != null) {
                result += line;
            }
            input.close();
        } catch (IOException e) {
            return -1;
        }

        try {
            file.delete();
        } catch (Exception e) {

        }

        int chassis = Integer.parseInt(result.trim());
        return chassis >= 8 && chassis <= 12 ? 1 : 0;
    }

    public static void main(String[] args) {

        System.out.println(isAllASCII("C:\\Users\\甲\\AppData\\Roaming\\ScanApp2"));
    }

    
    public static String execute(boolean returnOutput, boolean returnError, boolean throwException, String[] cmd, File dir, String[] env) {
        StringBuilder output = new StringBuilder();
        StringBuilder error = new StringBuilder();
        try {
            Process proc = Runtime.getRuntime().exec(cmd, env, dir);

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(proc.getErrorStream()));

            String line = null;
            while ((line = stdInput.readLine()) != null) {
                if(output.length() > 0) {
                    output.append("\n");
                }
                output.append(line);
            }

            while ((line = stdError.readLine()) != null) {
                if(error.length() > 0) {
                    error.append("\n");
                }
                error.append(line);
            }

            if(returnOutput) {
                return output.toString();
            }
            if(returnError) {
                return error.toString();
            }
            return null;
        } catch (Throwable t) {
            if(throwException) {
                throw new RuntimeException(t);
            } else {
                if(System.getenv("DEBUG") != null) {
                    t.printStackTrace();
                }
                if(returnOutput) {
                    return output.length() > 0 ? output.toString() : null;
                }
                if(returnError) {
                    return error.length() > 0 ? error.toString() : t.getMessage();
                }
                return null;
            }
        }
    }

    
    public static Properties loadProperties(String resourcePath, boolean ignoreException) {
        Properties props = new Properties();
        try {
            InputStream inputStream = Utils.class.getResourceAsStream(resourcePath);
            props.load(inputStream);
            inputStream.close();
        } catch (Throwable e) {
            String title = "Failed to load properties from " + resourcePath;
            if(ignoreException) {
                System.err.println(title + ": " + e.getMessage());
            } else {
                throw new RuntimeException("Unable to load properties from " + resourcePath, e);
            }
        }
        return props;
    }

    public static boolean isAllASCII(String input) {
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

    public static int LIC_TYPE_LIT = 1;
    public static int LIC_TYPE_STD = 2;
    public static int LIC_TYPE_PRO = 3;
    public static int LIC_TYPE_ENT = 4;

    public static boolean isEnterpriseOrEvaluation(Imaging imaging) {
        int licType = getLicenseType(imaging);
        return licType == 0 || licType == LIC_TYPE_ENT;
    }

    public static int getLicenseType(Imaging imaging) {
        return getLicenseType(imaging != null ? imaging.getLicense() : null);
    }

    public static int getLicenseType(String otherLic) {
        String licTypeShort = null;

        String lic = System.getProperty("GLOBAL_FLEX_LICENSE"); 
        if(lic == null || lic.trim().length() == 0) {
            lic = otherLic != null && otherLic.length() > 0 ? otherLic : lic; 
        }

        licTypeShort = lic != null && lic.length() >= 3 ? lic.substring(0, 3) : null; 
        if(licTypeShort == null) {
            String licName = System.getProperty("ASCAN_LICENSE_NAME"); 
            String licCode = System.getProperty("ASCAN_LICENSE_CODE");
            if (licName == null || licCode == null || licName.trim().length() == 0 || licCode.trim().length() == 0) {

            } else {
                licName = licName.trim();
                licTypeShort = licName.length() > 3 ? licName.substring(licName.length() - 3, licName.length()) : null; 
            }
        }

        if(licTypeShort == null) {
            return 0;
        }

        if ("LIT".equalsIgnoreCase(licTypeShort)) {
            return LIC_TYPE_LIT;
        } else if ("STD".equalsIgnoreCase(licTypeShort)) {
            return LIC_TYPE_STD;
        } else if ("PRO".equalsIgnoreCase(licTypeShort)) {
            return LIC_TYPE_PRO;
        } else if ("ENT".equalsIgnoreCase(licTypeShort)) {
            return LIC_TYPE_ENT;
        } else {
            return 0;
        }
    }

    public static boolean isEval(Imaging imaging) {
        String value = System.getProperty("GLOBAL_FLEX_LICENSE"); 
        if(value != null && value.length() > 0) {
            return false;
        }

        value = imaging != null ? imaging.getLicense() : null; 
        if(value != null && value.length() > 0) {
            return false;
        }

        value = System.getProperty("ASCAN_LICENSE_NAME"); 
        if(value != null && value.length() > 0) {
            return false;
        }

        return true;
    }

    static Boolean usingFlexDll = null;

    
    public static boolean isFlex() {
        if(usingFlexDll == null) {
            String versionAndBuildInfo = Imaging.getLibraryVersion() + " " + Imaging.getLibraryBuildInfo();
            usingFlexDll = versionAndBuildInfo.toUpperCase().contains("FLEX");
        }
        return usingFlexDll;
    }

    
    public static void readPropertiesFromUtf8(InputStream sourceInput, Properties saveTo) {
        BufferedReader reader = null;
        String line = null;
        try {
            reader = new BufferedReader(new InputStreamReader(sourceInput, "UTF-8"));
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.length() == 0 || line.startsWith("!") || line.startsWith("#")) { 
                    continue;
                }

                int indexOfEqual = line.indexOf('=');
                int indexOfColon = line.indexOf(':');
                int indexOfSeparator = -1;
                if (indexOfEqual < 0) {
                    indexOfSeparator = indexOfColon;
                } else {
                    if (indexOfColon < 0) {
                        indexOfSeparator = indexOfEqual;
                    } else {
                        indexOfSeparator = Math.min(indexOfEqual, indexOfColon);
                    }
                }

                if (indexOfSeparator < 0) {
                    System.err.println("Invalid line in properties: " + line);
                    continue;
                }

                String key = line.substring(0, indexOfSeparator).trim();
                String value = line.substring(indexOfSeparator + 1).trim();
                value = value.replace("\\=", "=");
                value = value.replace("\\:", ":");
                value = value.replace("\\n", "\n");

                if (value.startsWith("base64,")) {
                    byte[] bytes = base64Decode(value.substring("base64,".length()));
                    value = new String(bytes, 0, bytes.length, "UTF-8");
                }

                saveTo.setProperty(key, value);
            }
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    
    public static java.util.List<String> searchInCodeSourceRoot(final String fileNamePattern) {
        List<String> list = new ArrayList<String>();
        CodeSource codeSource = Utils.class.getProtectionDomain().getCodeSource();
        URL location = codeSource == null ? null : codeSource.getLocation();

        if(location == null) {
            return null;
        }

        if(location.toString().endsWith(".jar")) {
            ZipInputStream zip = null;
            try {
                zip = new ZipInputStream(new BufferedInputStream(location.openStream()));

                for(ZipEntry entry = zip.getNextEntry(); entry != null ; entry = zip.getNextEntry()){

                    if(StringUtils.patternMatchString(fileNamePattern, entry.getName(), true)) {
                        list.add(entry.getName());
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            } finally {
                if(zip != null) {
                    try {
                        zip.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        } else if("file".equalsIgnoreCase(location.getProtocol())) {
            try {
                File dir = new File(location.toURI());

                String[] files = dir.list();

                if(files != null) {
                    for(String file : files) {
                        if(StringUtils.patternMatchString(fileNamePattern, file, true)) {
                            list.add(file);
                        }
                    }
                }

            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        return list;
    }
}
