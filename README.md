# java-scanner-access-twain


Scan documents directly from your Java applications (Java applets, web applications, Swing/JavaFX components, JEE enterprise applications). High speed ADF scanning allows the end user to scan multiple pages into a ultra small size multi-page TIFF or PDF (including PDF/A-1) file. **Support Java 26, 25, 21, 17, 11 and 8** (both 64bits and 32bits).

### Add Dependency
```xml
<dependency> <groupId>com.asprise.imaging</groupId> <artifactId>java-scanner-access-twain</artifactId> <version>[12,)</version> </dependency>
```

### Complete Example Code
```java
package org.example;
import com.asprise.imaging.core.*;

public class Main {
 static void main() throws Throwable {
  // Imaging.setTwainBitness(Imaging.TWAIN_CONFIG_BITNESS_FORCE_64); // Use 32bit or 64bit scanner drivers
   Result result = new com.asprise.imaging.scan.ui.workbench.AspriseScanUI().setRequest(Request.fromJson(
    "{\"output_settings\" : [ { \"type\" : \"save\", \"format\" : \"pdf\", \"save_path\" : \"${TMP}\\\\${TMS}${EXT}\" } ] }"))
    .setInstruction("Scan <b>test</b>").showDialog(null, "Dialog Title", true, null);
   System.out.println("\nScanned: " + result.getImageFiles());
 }
}
```

![Screenshot of the Java scanning dialog when running the code above](https://asprise.com/scan/sdk/img/java-scan-dialog.png)

Visit http://asprise.com/document-scanner-image-pdf/java-scanning-api-overview.html for developer's guide.

Core Features:

- Compatible With Almost All Scanners: Asprise Scanning and Imaging SDK supports almost any kind of scanner on Windows 32bit and 64bit. With the SDK’s widest compatibility, you can rest assure that your customers will able to scan from their scanners.

- Extreme Speed ADF Scanning: make full use of scanners equipped with automatic document feeders.

- Output to TIFF, PDF (including PDF/A-1) with Ease: supporting wide range of output formats like JPG, PNG, GIF, TIFF, PDF.

- Powerful Image Editing Functionality: allows the user to re-order pages, edit images (crop, rotate, etc) before final output.

- 20+ Languages: e.g, English, Spanish, French, German, Italian, Hungarian, Finnish, Swedish, Romanian, Polish, Malay, Arabic, Indonesian, and Russian.

- Scan and Recognize Barcodes: recognize almost every kind of bar code while scanning. Formats supported:
   - CODE 128 (128b, 128C, 128raw)
   - EAN 8 EAN 13
   - UPC
   - code 3 of 9
   - code interleaved 2 of 5
   - QR code

- Blank Page Detection: flexible parameters allow you to perform powerful blank page detection.

- Return, Save, Upload or Any Combinations: Using powerful JSON based domain-specific language to perform complex tasks like saving to local disk, upload to remote servers or any combinations.

Visit http://asprise.com/document-scanner-image-pdf/java-scanning-api-overview.html for developer's guide.
