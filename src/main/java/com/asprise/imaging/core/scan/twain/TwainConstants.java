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

import java.util.*;

/**
 * TWAIN constants (CAP_, ICAP_, TWEI_, TWEJ_) and util functions.
 */
public class TwainConstants {

    public static int CAP_CUSTOMBASE = 0x8000; /* Base of custom capabilities */

    /* all data sources are REQUIRED to support these caps */
    public static int CAP_XFERCOUNT = 0x0001;

    /* image data sources are REQUIRED to support these caps */
    public static int ICAP_COMPRESSION = 0x0100;
    public static int ICAP_PIXELTYPE = 0x0101;
    public static int ICAP_UNITS = 0x0102;
    public static int ICAP_XFERMECH = 0x0103;

    /* all data sources MAY support these caps */
    public static int CAP_AUTHOR = 0x1000;
    public static int CAP_CAPTION = 0x1001;
    public static int CAP_FEEDERENABLED = 0x1002;
    public static int CAP_FEEDERLOADED = 0x1003;
    public static int CAP_TIMEDATE = 0x1004;
    public static int CAP_SUPPORTEDCAPS = 0x1005;
    public static int CAP_EXTENDEDCAPS = 0x1006;
    public static int CAP_AUTOFEED = 0x1007;
    public static int CAP_CLEARPAGE = 0x1008;
    public static int CAP_FEEDPAGE = 0x1009;
    public static int CAP_REWINDPAGE = 0x100a;
    public static int CAP_INDICATORS = 0x100b;
    public static int CAP_PAPERDETECTABLE = 0x100d;
    public static int CAP_UICONTROLLABLE = 0x100e;
    public static int CAP_DEVICEONLINE = 0x100f;
    public static int CAP_AUTOSCAN = 0x1010;
    public static int CAP_THUMBNAILSENABLED = 0x1011;
    public static int CAP_DUPLEX = 0x1012;
    public static int CAP_DUPLEXENABLED = 0x1013;
    public static int CAP_ENABLEDSUIONLY = 0x1014;
    public static int CAP_CUSTOMDSDATA = 0x1015;
    public static int CAP_ENDORSER = 0x1016;
    public static int CAP_JOBCONTROL = 0x1017;
    public static int CAP_ALARMS = 0x1018;
    public static int CAP_ALARMVOLUME = 0x1019;
    public static int CAP_AUTOMATICCAPTURE = 0x101a;
    public static int CAP_TIMEBEFOREFIRSTCAPTURE = 0x101b;
    public static int CAP_TIMEBETWEENCAPTURES = 0x101c;
    public static int CAP_CLEARBUFFERS = 0x101d;
    public static int CAP_MAXBATCHBUFFERS = 0x101e;
    public static int CAP_DEVICETIMEDATE = 0x101f;
    public static int CAP_POWERSUPPLY = 0x1020;
    public static int CAP_CAMERAPREVIEWUI = 0x1021;
    public static int CAP_DEVICEEVENT = 0x1022;
    public static int CAP_SERIALNUMBER = 0x1024;
    public static int CAP_PRINTER = 0x1026;
    public static int CAP_PRINTERENABLED = 0x1027;
    public static int CAP_PRINTERINDEX = 0x1028;
    public static int CAP_PRINTERMODE = 0x1029;
    public static int CAP_PRINTERSTRING = 0x102a;
    public static int CAP_PRINTERSUFFIX = 0x102b;
    public static int CAP_LANGUAGE = 0x102c;
    public static int CAP_FEEDERALIGNMENT = 0x102d;
    public static int CAP_FEEDERORDER = 0x102e;
    public static int CAP_REACQUIREALLOWED = 0x1030;
    public static int CAP_BATTERYMINUTES = 0x1032;
    public static int CAP_BATTERYPERCENTAGE = 0x1033;
    public static int CAP_CAMERASIDE = 0x1034;
    public static int CAP_SEGMENTED = 0x1035;
    public static int CAP_CAMERAENABLED = 0x1036;
    public static int CAP_CAMERAORDER = 0x1037;
    public static int CAP_MICRENABLED = 0x1038;
    public static int CAP_FEEDERPREP = 0x1039;
    public static int CAP_FEEDERPOCKET = 0x103a;
    public static int CAP_AUTOMATICSENSEMEDIUM = 0x103b;
    public static int CAP_CUSTOMINTERFACEGUID = 0x103c;
    public static int CAP_SUPPORTEDCAPSSEGMENTUNIQUE = 0x103d;
    public static int CAP_SUPPORTEDDATS = 0x103e;
    public static int CAP_DOUBLEFEEDDETECTION = 0x103f;
    public static int CAP_DOUBLEFEEDDETECTIONLENGTH = 0x1040;
    public static int CAP_DOUBLEFEEDDETECTIONSENSITIVITY = 0x1041;
    public static int CAP_DOUBLEFEEDDETECTIONRESPONSE = 0x1042;
    public static int CAP_PAPERHANDLING = 0x1043;
    public static int CAP_INDICATORSMODE = 0x1044;
    public static int CAP_PRINTERVERTICALOFFSET = 0x1045;
    public static int CAP_POWERSAVETIME = 0x1046;
    public static int CAP_PRINTERCHARROTATION = 0x1047;
    public static int CAP_PRINTERFONTSTYLE = 0x1048;
    public static int CAP_PRINTERINDEXLEADCHAR = 0x1049;
    public static int CAP_PRINTERINDEXMAXVALUE = 0x104A;
    public static int CAP_PRINTERINDEXNUMDIGITS = 0x104B;
    public static int CAP_PRINTERINDEXSTEP = 0x104C;
    public static int CAP_PRINTERINDEXTRIGGER = 0x104D;
    public static int CAP_PRINTERSTRINGPREVIEW = 0x104E;

    /* image data sources MAY support these caps */
    public static int ICAP_AUTOBRIGHT = 0x1100;
    public static int ICAP_BRIGHTNESS = 0x1101;
    public static int ICAP_CONTRAST = 0x1103;
    public static int ICAP_CUSTHALFTONE = 0x1104;
    public static int ICAP_EXPOSURETIME = 0x1105;
    public static int ICAP_FILTER = 0x1106;
    public static int ICAP_FLASHUSED = 0x1107;
    public static int ICAP_GAMMA = 0x1108;
    public static int ICAP_HALFTONES = 0x1109;
    public static int ICAP_HIGHLIGHT = 0x110a;
    public static int ICAP_IMAGEFILEFORMAT = 0x110c;
    public static int ICAP_LAMPSTATE = 0x110d;
    public static int ICAP_LIGHTSOURCE = 0x110e;
    public static int ICAP_ORIENTATION = 0x1110;
    public static int ICAP_PHYSICALWIDTH = 0x1111;
    public static int ICAP_PHYSICALHEIGHT = 0x1112;
    public static int ICAP_SHADOW = 0x1113;
    public static int ICAP_FRAMES = 0x1114;
    public static int ICAP_XNATIVERESOLUTION = 0x1116;
    public static int ICAP_YNATIVERESOLUTION = 0x1117;
    public static int ICAP_XRESOLUTION = 0x1118;
    public static int ICAP_YRESOLUTION = 0x1119;
    public static int ICAP_MAXFRAMES = 0x111a;
    public static int ICAP_TILES = 0x111b;
    public static int ICAP_BITORDER = 0x111c;
    public static int ICAP_CCITTKFACTOR = 0x111d;
    public static int ICAP_LIGHTPATH = 0x111e;
    public static int ICAP_PIXELFLAVOR = 0x111f;
    public static int ICAP_PLANARCHUNKY = 0x1120;
    public static int ICAP_ROTATION = 0x1121;
    public static int ICAP_SUPPORTEDSIZES = 0x1122;
    public static int ICAP_THRESHOLD = 0x1123;
    public static int ICAP_XSCALING = 0x1124;
    public static int ICAP_YSCALING = 0x1125;
    public static int ICAP_BITORDERCODES = 0x1126;
    public static int ICAP_PIXELFLAVORCODES = 0x1127;
    public static int ICAP_JPEGPIXELTYPE = 0x1128;
    public static int ICAP_TIMEFILL = 0x112a;
    public static int ICAP_BITDEPTH = 0x112b;
    public static int ICAP_BITDEPTHREDUCTION = 0x112c;
    public static int ICAP_UNDEFINEDIMAGESIZE = 0x112d;
    public static int ICAP_IMAGEDATASET = 0x112e;
    public static int ICAP_EXTIMAGEINFO = 0x112f;
    public static int ICAP_MINIMUMHEIGHT = 0x1130;
    public static int ICAP_MINIMUMWIDTH = 0x1131;
    public static int ICAP_AUTODISCARDBLANKPAGES = 0x1134;
    public static int ICAP_FLIPROTATION = 0x1136;
    public static int ICAP_BARCODEDETECTIONENABLED = 0x1137;
    public static int ICAP_SUPPORTEDBARCODETYPES = 0x1138;
    public static int ICAP_BARCODEMAXSEARCHPRIORITIES = 0x1139;
    public static int ICAP_BARCODESEARCHPRIORITIES = 0x113a;
    public static int ICAP_BARCODESEARCHMODE = 0x113b;
    public static int ICAP_BARCODEMAXRETRIES = 0x113c;
    public static int ICAP_BARCODETIMEOUT = 0x113d;
    public static int ICAP_ZOOMFACTOR = 0x113e;
    public static int ICAP_PATCHCODEDETECTIONENABLED = 0x113f;
    public static int ICAP_SUPPORTEDPATCHCODETYPES = 0x1140;
    public static int ICAP_PATCHCODEMAXSEARCHPRIORITIES = 0x1141;
    public static int ICAP_PATCHCODESEARCHPRIORITIES = 0x1142;
    public static int ICAP_PATCHCODESEARCHMODE = 0x1143;
    public static int ICAP_PATCHCODEMAXRETRIES = 0x1144;
    public static int ICAP_PATCHCODETIMEOUT = 0x1145;
    public static int ICAP_FLASHUSED2 = 0x1146;
    public static int ICAP_IMAGEFILTER = 0x1147;
    public static int ICAP_NOISEFILTER = 0x1148;
    public static int ICAP_OVERSCAN = 0x1149;
    public static int ICAP_AUTOMATICBORDERDETECTION = 0x1150;
    public static int ICAP_AUTOMATICDESKEW = 0x1151;
    public static int ICAP_AUTOMATICROTATE = 0x1152;
    public static int ICAP_JPEGQUALITY = 0x1153;
    public static int ICAP_FEEDERTYPE = 0x1154;
    public static int ICAP_ICCPROFILE = 0x1155;
    public static int ICAP_AUTOSIZE = 0x1156;
    public static int ICAP_AUTOMATICCROPUSESFRAME = 0x1157;
    public static int ICAP_AUTOMATICLENGTHDETECTION = 0x1158;
    public static int ICAP_AUTOMATICCOLORENABLED = 0x1159;
    public static int ICAP_AUTOMATICCOLORNONCOLORPIXELTYPE = 0x115a;
    public static int ICAP_COLORMANAGEMENTENABLED = 0x115b;
    public static int ICAP_IMAGEMERGE = 0x115c;
    public static int ICAP_IMAGEMERGEHEIGHTTHRESHOLD = 0x115d;
    public static int ICAP_SUPPORTEDEXTIMAGEINFO = 0x115e;
    public static int ICAP_FILMTYPE = 0x115f;
    public static int ICAP_MIRROR = 0x1160;
    public static int ICAP_JPEGSUBSAMPLING = 0x1161;

    public static int CAP_SUPPORTEDCAPSEXT = 0x100c;
    public static int CAP_PAGEMULTIPLEACQUIRE = 0x1023;
    public static int CAP_PAPERBINDING = 0x102f;
    public static int CAP_PASSTHRU = 0x1031;
    public static int CAP_POWERDOWNTIME = 0x1034;

    // TWEI
    public static int TWEI_BARCODEX = 0x1200;
    public static int TWEI_BARCODEY = 0x1201;
    public static int TWEI_BARCODETEXT = 0x1202;
    public static int TWEI_BARCODETYPE = 0x1203;
    public static int TWEI_DESHADETOP = 0x1204;
    public static int TWEI_DESHADELEFT = 0x1205;
    public static int TWEI_DESHADEHEIGHT = 0x1206;
    public static int TWEI_DESHADEWIDTH = 0x1207;
    public static int TWEI_DESHADESIZE = 0x1208;
    public static int TWEI_SPECKLESREMOVED = 0x1209;
    public static int TWEI_HORZLINEXCOORD = 0x120A;
    public static int TWEI_HORZLINEYCOORD = 0x120B;
    public static int TWEI_HORZLINELENGTH = 0x120C;
    public static int TWEI_HORZLINETHICKNESS = 0x120D;
    public static int TWEI_VERTLINEXCOORD = 0x120E;
    public static int TWEI_VERTLINEYCOORD = 0x120F;
    public static int TWEI_VERTLINELENGTH = 0x1210;
    public static int TWEI_VERTLINETHICKNESS = 0x1211;
    public static int TWEI_PATCHCODE = 0x1212;
    public static int TWEI_ENDORSEDTEXT = 0x1213;
    public static int TWEI_FORMCONFIDENCE = 0x1214;
    public static int TWEI_FORMTEMPLATEMATCH = 0x1215;
    public static int TWEI_FORMTEMPLATEPAGEMATCH = 0x1216;
    public static int TWEI_FORMHORZDOCOFFSET = 0x1217;
    public static int TWEI_FORMVERTDOCOFFSET = 0x1218;
    public static int TWEI_BARCODECOUNT = 0x1219;
    public static int TWEI_BARCODECONFIDENCE = 0x121A;
    public static int TWEI_BARCODEROTATION = 0x121B;
    public static int TWEI_BARCODETEXTLENGTH = 0x121C;
    public static int TWEI_DESHADECOUNT = 0x121D;
    public static int TWEI_DESHADEBLACKCOUNTOLD = 0x121E;
    public static int TWEI_DESHADEBLACKCOUNTNEW = 0x121F;
    public static int TWEI_DESHADEBLACKRLMIN = 0x1220;
    public static int TWEI_DESHADEBLACKRLMAX = 0x1221;
    public static int TWEI_DESHADEWHITECOUNTOLD = 0x1222;
    public static int TWEI_DESHADEWHITECOUNTNEW = 0x1223;
    public static int TWEI_DESHADEWHITERLMIN = 0x1224;
    public static int TWEI_DESHADEWHITERLAVE = 0x1225;
    public static int TWEI_DESHADEWHITERLMAX = 0x1226;
    public static int TWEI_BLACKSPECKLESREMOVED = 0x1227;
    public static int TWEI_WHITESPECKLESREMOVED = 0x1228;
    public static int TWEI_HORZLINECOUNT = 0x1229;
    public static int TWEI_VERTLINECOUNT = 0x122A;
    public static int TWEI_DESKEWSTATUS = 0x122B;
    public static int TWEI_SKEWORIGINALANGLE = 0x122C;
    public static int TWEI_SKEWFINALANGLE = 0x122D;
    public static int TWEI_SKEWCONFIDENCE = 0x122E;
    public static int TWEI_SKEWWINDOWX1 = 0x122F;
    public static int TWEI_SKEWWINDOWY1 = 0x1230;
    public static int TWEI_SKEWWINDOWX2 = 0x1231;
    public static int TWEI_SKEWWINDOWY2 = 0x1232;
    public static int TWEI_SKEWWINDOWX3 = 0x1233;
    public static int TWEI_SKEWWINDOWY3 = 0x1234;
    public static int TWEI_SKEWWINDOWX4 = 0x1235;
    public static int TWEI_SKEWWINDOWY4 = 0x1236;
    public static int TWEI_BOOKNAME = 0x1238;
    public static int TWEI_CHAPTERNUMBER = 0x1239;
    public static int TWEI_DOCUMENTNUMBER = 0x123A;
    public static int TWEI_PAGENUMBER = 0x123B;
    public static int TWEI_CAMERA = 0x123C;
    public static int TWEI_FRAMENUMBER = 0x123D;
    public static int TWEI_FRAME = 0x123E;
    public static int TWEI_PIXELFLAVOR = 0x123F;
    public static int TWEI_ICCPROFILE = 0x1240;
    public static int TWEI_LASTSEGMENT = 0x1241;
    public static int TWEI_SEGMENTNUMBER = 0x1242;
    public static int TWEI_MAGDATA = 0x1243;
    public static int TWEI_MAGTYPE = 0x1244;
    public static int TWEI_PAGESIDE = 0x1245;
    public static int TWEI_FILESYSTEMSOURCE = 0x1246;
    public static int TWEI_IMAGEMERGED = 0x1247;
    public static int TWEI_MAGDATALENGTH = 0x1248;
    public static int TWEI_PAPERCOUNT = 0x1249;
    public static int TWEI_PRINTERTEXT = 0x124A;

    public static int TWEJ_NONE = 0x0000;
    public static int TWEJ_MIDSEPARATOR = 0x0001;
    public static int TWEJ_PATCH1 = 0x0002;
    public static int TWEJ_PATCH2 = 0x0003;
    public static int TWEJ_PATCH3 = 0x0004;
    public static int TWEJ_PATCH4 = 0x0005;
    public static int TWEJ_PATCH6 = 0x0006;
    public static int TWEJ_PATCHT = 0x0007;


    /***************************************************************************
     *            Return Codes and Condition Codes section                     *
     ***************************************************************************/

    public static int TWRC_CUSTOMBASE = 0x8000;

    public static int TWRC_SUCCESS = 0;
    public static int TWRC_FAILURE = 1;
    public static int TWRC_CHECKSTATUS = 2;
    public static int TWRC_CANCEL = 3;
    public static int TWRC_DSEVENT = 4;
    public static int TWRC_NOTDSEVENT = 5;
    public static int TWRC_XFERDONE = 6;
    public static int TWRC_ENDOFLIST = 7;
    public static int TWRC_INFONOTSUPPORTED = 8;
    public static int TWRC_DATANOTAVAILABLE = 9;
    public static int TWRC_BUSY = 10;
    public static int TWRC_SCANNERLOCKED = 11;

    /* Condition Codes: Application gets these by doing DG_CONTROL DAT_STATUS MSG_GET.  */
    public static int TWCC_CUSTOMBASE = 0x8000;

    public static int TWCC_SUCCESS = 0;
    public static int TWCC_BUMMER = 1;
    public static int TWCC_LOWMEMORY = 2;
    public static int TWCC_NODS = 3;
    public static int TWCC_MAXCONNECTIONS = 4;
    public static int TWCC_OPERATIONERROR = 5;
    public static int TWCC_BADCAP = 6;
    public static int TWCC_BADPROTOCOL = 9;
    public static int TWCC_BADVALUE = 10;
    public static int TWCC_SEQERROR = 11;
    public static int TWCC_BADDEST = 12;
    public static int TWCC_CAPUNSUPPORTED = 13;
    public static int TWCC_CAPBADOPERATION = 14;
    public static int TWCC_CAPSEQERROR = 15;
    public static int TWCC_DENIED = 16;
    public static int TWCC_FILEEXISTS = 17;
    public static int TWCC_FILENOTFOUND = 18;
    public static int TWCC_NOTEMPTY = 19;
    public static int TWCC_PAPERJAM = 20;
    public static int TWCC_PAPERDOUBLEFEED = 21;
    public static int TWCC_FILEWRITEERROR = 22;
    public static int TWCC_CHECKDEVICEONLINE = 23;
    public static int TWCC_INTERLOCK = 24;
    public static int TWCC_DAMAGEDCORNER = 25;
    public static int TWCC_FOCUSERROR = 26;
    public static int TWCC_DOCTOOLIGHT = 27;
    public static int TWCC_DOCTOODARK = 28;
    public static int TWCC_NOMEDIA = 29;

    /* CAP_CLEARBUFFERS values */
    public static int TWCB_AUTO = 0;
    public static int TWCB_CLEAR = 1;
    public static int TWCB_NOCLEAR = 2;

    /* TW_PASSTHRU.Direction values. */
    public static int TWDR_GET = 1;
    public static int TWDR_SET = 2;

    /* TWEI_DESKEWSTATUS values. */
    public static int TWDSK_SUCCESS = 0;
    public static int TWDSK_REPORTONLY = 1;
    public static int TWDSK_FAIL = 2;
    public static int TWDSK_DISABLED = 3;

    /* CAP_DUPLEX values */
    public static int TWDX_NONE = 0;
    public static int TWDX_1PASSDUPLEX = 1;
    public static int TWDX_2PASSDUPLEX = 2;

    /* CAP_FEEDERALIGNMENT values */
    public static int TWFA_NONE = 0;
    public static int TWFA_LEFT = 1;
    public static int TWFA_CENTER = 2;
    public static int TWFA_RIGHT = 3;

    /* ICAP_FEEDERTYPE values*/
    public static int TWFE_GENERAL = 0;
    public static int TWFE_PHOTO = 1;

    /* ICAP_IMAGEFILEFORMAT values */
    public static int TWFF_TIFF = 0;
    public static int TWFF_PICT = 1;
    public static int TWFF_BMP = 2;
    public static int TWFF_XBM = 3;
    public static int TWFF_JFIF = 4;
    public static int TWFF_FPX = 5;
    public static int TWFF_TIFFMULTI = 6;
    public static int TWFF_PNG = 7;
    public static int TWFF_SPIFF = 8;
    public static int TWFF_EXIF = 9;
    public static int TWFF_PDF = 10;
    public static int TWFF_JP2 = 11;
    public static int TWFF_JPX = 13;
    public static int TWFF_DEJAVU = 14;
    public static int TWFF_PDFA = 15;
    public static int TWFF_PDFA2 = 16;

    /* ICAP_FLASHUSED2 values */
    public static int TWFL_NONE = 0;
    public static int TWFL_OFF = 1;
    public static int TWFL_ON = 2;
    public static int TWFL_AUTO = 3;
    public static int TWFL_REDEYE = 4;

    /* CAP_FEEDERORDER values */
    public static int TWFO_FIRSTPAGEFIRST = 0;
    public static int TWFO_LASTPAGEFIRST = 1;

    /* CAP_FEEDERPOCKET values*/
    public static int TWFP_POCKETERROR = 0;
    public static int TWFP_POCKET1 = 1;
    public static int TWFP_POCKET2 = 2;
    public static int TWFP_POCKET3 = 3;
    public static int TWFP_POCKET4 = 4;
    public static int TWFP_POCKET5 = 5;
    public static int TWFP_POCKET6 = 6;
    public static int TWFP_POCKET7 = 7;
    public static int TWFP_POCKET8 = 8;
    public static int TWFP_POCKET9 = 9;
    public static int TWFP_POCKET10 = 10;
    public static int TWFP_POCKET11 = 11;
    public static int TWFP_POCKET12 = 12;
    public static int TWFP_POCKET13 = 13;
    public static int TWFP_POCKET14 = 14;
    public static int TWFP_POCKET15 = 15;
    public static int TWFP_POCKET16 = 16;

    /* ICAP_FLIPROTATION values */
    public static int TWFR_BOOK = 0;
    public static int TWFR_FANFOLD = 1;

    /* ICAP_FILTER values */
    public static int TWFT_RED = 0;
    public static int TWFT_GREEN = 1;
    public static int TWFT_BLUE = 2;
    public static int TWFT_NONE = 3;
    public static int TWFT_WHITE = 4;
    public static int TWFT_CYAN = 5;
    public static int TWFT_MAGENTA = 6;
    public static int TWFT_YELLOW = 7;
    public static int TWFT_BLACK = 8;

    /* ICAP_LIGHTPATH values */
    public static int TWLP_REFLECTIVE = 0;
    public static int TWLP_TRANSMISSIVE = 1;

    /* ICAP_LIGHTSOURCE values */
    public static int TWLS_RED = 0;
    public static int TWLS_GREEN = 1;
    public static int TWLS_BLUE = 2;
    public static int TWLS_NONE = 3;
    public static int TWLS_WHITE = 4;
    public static int TWLS_UV = 5;
    public static int TWLS_IR = 6;

    /* ICAP_NOISEFILTER values */
    public static int TWNF_NONE = 0;
    public static int TWNF_AUTO = 1;
    public static int TWNF_LONEPIXEL = 2;
    public static int TWNF_MAJORITYRULE = 3;

    /* ICAP_ORIENTATION values */
    public static int TWOR_ROT0 = 0;
    public static int TWOR_ROT90 = 1;
    public static int TWOR_ROT180 = 2;
    public static int TWOR_ROT270 = 3;
    public static int TWOR_PORTRAIT = TWOR_ROT0;
    public static int TWOR_LANDSCAPE = TWOR_ROT270;
    public static int TWOR_AUTO = 4;
    public static int TWOR_AUTOTEXT = 5;
    public static int TWOR_AUTOPICTURE = 6;

    /* ICAP_OVERSCAN values */
    public static int TWOV_NONE = 0;
    public static int TWOV_AUTO = 1;
    public static int TWOV_TOPBOTTOM = 2;
    public static int TWOV_LEFTRIGHT = 3;
    public static int TWOV_ALL = 4;

    /* Palette types for TW_PALETTE8 */
    public static int TWPA_RGB = 0;
    public static int TWPA_GRAY = 1;
    public static int TWPA_CMY = 2;

    /* ICAP_PLANARCHUNKY values */
    public static int TWPC_CHUNKY = 0;
    public static int TWPC_PLANAR = 1;

    /* TWEI_PATCHCODE values*/
    public static int TWPCH_PATCH1 = 0;
    public static int TWPCH_PATCH2 = 1;
    public static int TWPCH_PATCH3 = 2;
    public static int TWPCH_PATCH4 = 3;
    public static int TWPCH_PATCH6 = 4;
    public static int TWPCH_PATCHT = 5;

    /* ICAP_PIXELFLAVOR values */
    public static int TWPF_CHOCOLATE = 0;
    public static int TWPF_VANILLA = 1;

    /* CAP_PRINTERMODE values */
    public static int TWPM_SINGLESTRING = 0;
    public static int TWPM_MULTISTRING = 1;
    public static int TWPM_COMPOUNDSTRING = 2;

    /* CAP_PRINTER values */
    public static int TWPR_IMPRINTERTOPBEFORE = 0;
    public static int TWPR_IMPRINTERTOPAFTER = 1;
    public static int TWPR_IMPRINTERBOTTOMBEFORE = 2;
    public static int TWPR_IMPRINTERBOTTOMAFTER = 3;
    public static int TWPR_ENDORSERTOPBEFORE = 4;
    public static int TWPR_ENDORSERTOPAFTER = 5;
    public static int TWPR_ENDORSERBOTTOMBEFORE = 6;
    public static int TWPR_ENDORSERBOTTOMAFTER = 7;

    /* CAP_PRINTERFONTSTYLE Added 2.3 */
    public static int TWPF_NORMAL = 0;
    public static int TWPF_BOLD = 1;
    public static int TWPF_ITALIC = 2;
    public static int TWPF_LARGESIZE = 3;
    public static int TWPF_SMALLSIZE = 4;

    /* CAP_PRINTERINDEXTRIGGER Added 2.3 */
    public static int TWCT_PAGE = 0;
    public static int TWCT_PATCH1 = 1;
    public static int TWCT_PATCH2 = 2;
    public static int TWCT_PATCH3 = 3;
    public static int TWCT_PATCH4 = 4;
    public static int TWCT_PATCHT = 5;
    public static int TWCT_PATCH6 = 6;

    /* CAP_POWERSUPPLY values */
    public static int TWPS_EXTERNAL = 0;
    public static int TWPS_BATTERY = 1;

    /* ICAP_PIXELTYPE values (PT_ means Pixel Type) */
    public static int TWPT_BW = 0;
    public static int TWPT_GRAY = 1;
    public static int TWPT_RGB = 2;
    public static int TWPT_PALETTE = 3;
    public static int TWPT_CMY = 4;
    public static int TWPT_CMYK = 5;
    public static int TWPT_YUV = 6;
    public static int TWPT_YUVK = 7;
    public static int TWPT_CIEXYZ = 8;
    public static int TWPT_LAB = 9;
    public static int TWPT_SRGB = 10;
    public static int TWPT_SCRGB = 11;
    public static int TWPT_INFRARED = 16;

    /* CAP_SEGMENTED values */
    public static int TWSG_NONE = 0;
    public static int TWSG_AUTO = 1;
    public static int TWSG_MANUAL = 2;

    /* ICAP_FILMTYPE values */
    public static int TWFM_POSITIVE = 0;
    public static int TWFM_NEGATIVE = 1;

    /* CAP_DOUBLEFEEDDETECTION */
    public static int TWDF_ULTRASONIC = 0;
    public static int TWDF_BYLENGTH = 1;
    public static int TWDF_INFRARED = 2;

    /* CAP_DOUBLEFEEDDETECTIONSENSITIVITY */
    public static int TWUS_LOW = 0;
    public static int TWUS_MEDIUM = 1;
    public static int TWUS_HIGH = 2;

    /* CAP_DOUBLEFEEDDETECTIONRESPONSE */
    public static int TWDP_STOP = 0;
    public static int TWDP_STOPANDWAIT = 1;
    public static int TWDP_SOUND = 2;
    public static int TWDP_DONOTIMPRINT = 3;

    /* ICAP_MIRROR values */
    public static int TWMR_NONE = 0;
    public static int TWMR_VERTICAL = 1;
    public static int TWMR_HORIZONTAL = 2;

    /* CAP_PAPERHANDLING values */
    public static int TWPH_NORMAL = 0;
    public static int TWPH_FRAGILE = 1;
    public static int TWPH_THICK = 2;
    public static int TWPH_TRIFOLD = 3;
    public static int TWPH_PHOTOGRAPH = 4;

    /* CAP_INDICATORSMODE values */
    public static int TWCI_INFO = 0;
    public static int TWCI_WARNING = 1;
    public static int TWCI_ERROR = 2;
    public static int TWCI_WARMUP = 3;

    /* ICAP_SUPPORTEDSIZES values (SS_ means Supported Sizes) */
    public static int TWSS_NONE = 0;
    public static int TWSS_A4 = 1;
    public static int TWSS_JISB5 = 2;
    public static int TWSS_USLETTER = 3;
    public static int TWSS_USLEGAL = 4;
    public static int TWSS_A5 = 5;
    public static int TWSS_ISOB4 = 6;
    public static int TWSS_ISOB6 = 7;
    public static int TWSS_USLEDGER = 9;
    public static int TWSS_USEXECUTIVE = 10;
    public static int TWSS_A3 = 11;
    public static int TWSS_ISOB3 = 12;
    public static int TWSS_A6 = 13;
    public static int TWSS_C4 = 14;
    public static int TWSS_C5 = 15;
    public static int TWSS_C6 = 16;
    public static int TWSS_4A0 = 17;
    public static int TWSS_2A0 = 18;
    public static int TWSS_A0 = 19;
    public static int TWSS_A1 = 20;
    public static int TWSS_A2 = 21;
    public static int TWSS_A7 = 22;
    public static int TWSS_A8 = 23;
    public static int TWSS_A9 = 24;
    public static int TWSS_A10 = 25;
    public static int TWSS_ISOB0 = 26;
    public static int TWSS_ISOB1 = 27;
    public static int TWSS_ISOB2 = 28;
    public static int TWSS_ISOB5 = 29;
    public static int TWSS_ISOB7 = 30;
    public static int TWSS_ISOB8 = 31;
    public static int TWSS_ISOB9 = 32;
    public static int TWSS_ISOB10 = 33;
    public static int TWSS_JISB0 = 34;
    public static int TWSS_JISB1 = 35;
    public static int TWSS_JISB2 = 36;
    public static int TWSS_JISB3 = 37;
    public static int TWSS_JISB4 = 38;
    public static int TWSS_JISB6 = 39;
    public static int TWSS_JISB7 = 40;
    public static int TWSS_JISB8 = 41;
    public static int TWSS_JISB9 = 42;
    public static int TWSS_JISB10 = 43;
    public static int TWSS_C0 = 44;
    public static int TWSS_C1 = 45;
    public static int TWSS_C2 = 46;
    public static int TWSS_C3 = 47;
    public static int TWSS_C7 = 48;
    public static int TWSS_C8 = 49;
    public static int TWSS_C9 = 50;
    public static int TWSS_C10 = 51;
    public static int TWSS_USSTATEMENT = 52;
    public static int TWSS_BUSINESSCARD = 53;
    public static int TWSS_MAXSIZE = 54;

    /* ICAP_XFERMECH values (SX_ means Setup XFer) */
    public static int TWSX_NATIVE = 0;
    public static int TWSX_FILE = 1;
    public static int TWSX_MEMORY = 2;
    public static int TWSX_MEMFILE = 4;

    /* ICAP_UNITS values (UN_ means UNits) */
    public static int TWUN_INCHES = 0;
    public static int TWUN_CENTIMETERS = 1;
    public static int TWUN_PICAS = 2;
    public static int TWUN_POINTS = 3;
    public static int TWUN_TWIPS = 4;
    public static int TWUN_PIXELS = 5;
    public static int TWUN_MILLIMETERS = 6;

    /****************************************************************************
     * Country Constants                                                        *
     ****************************************************************************/

    public static int TWCY_AFGHANISTAN = 1001;
    public static int TWCY_ALGERIA = 213;
    public static int TWCY_AMERICANSAMOA = 684;
    public static int TWCY_ANDORRA = 033;
    public static int TWCY_ANGOLA = 1002;
    public static int TWCY_ANGUILLA = 8090;
    public static int TWCY_ANTIGUA = 8091;
    public static int TWCY_ARGENTINA = 54;
    public static int TWCY_ARUBA = 297;
    public static int TWCY_ASCENSIONI = 247;
    public static int TWCY_AUSTRALIA = 61;
    public static int TWCY_AUSTRIA = 43;
    public static int TWCY_BAHAMAS = 8092;
    public static int TWCY_BAHRAIN = 973;
    public static int TWCY_BANGLADESH = 880;
    public static int TWCY_BARBADOS = 8093;
    public static int TWCY_BELGIUM = 32;
    public static int TWCY_BELIZE = 501;
    public static int TWCY_BENIN = 229;
    public static int TWCY_BERMUDA = 8094;
    public static int TWCY_BHUTAN = 1003;
    public static int TWCY_BOLIVIA = 591;
    public static int TWCY_BOTSWANA = 267;
    public static int TWCY_BRITAIN = 6;
    public static int TWCY_BRITVIRGINIS = 8095;
    public static int TWCY_BRAZIL = 55;
    public static int TWCY_BRUNEI = 673;
    public static int TWCY_BULGARIA = 359;
    public static int TWCY_BURKINAFASO = 1004;
    public static int TWCY_BURMA = 1005;
    public static int TWCY_BURUNDI = 1006;
    public static int TWCY_CAMAROON = 237;
    public static int TWCY_CANADA = 2;
    public static int TWCY_CAPEVERDEIS = 238;
    public static int TWCY_CAYMANIS = 8096;
    public static int TWCY_CENTRALAFREP = 1007;
    public static int TWCY_CHAD = 1008;
    public static int TWCY_CHILE = 56;
    public static int TWCY_CHINA = 86;
    public static int TWCY_CHRISTMASIS = 1009;
    public static int TWCY_COCOSIS = 1009;
    public static int TWCY_COLOMBIA = 57;
    public static int TWCY_COMOROS = 1010;
    public static int TWCY_CONGO = 1011;
    public static int TWCY_COOKIS = 1012;
    public static int TWCY_COSTARICA = 506;
    public static int TWCY_CUBA = 005;
    public static int TWCY_CYPRUS = 357;
    public static int TWCY_CZECHOSLOVAKIA = 42;
    public static int TWCY_DENMARK = 45;
    public static int TWCY_DJIBOUTI = 1013;
    public static int TWCY_DOMINICA = 8097;
    public static int TWCY_DOMINCANREP = 8098;
    public static int TWCY_EASTERIS = 1014;
    public static int TWCY_ECUADOR = 593;
    public static int TWCY_EGYPT = 20;
    public static int TWCY_ELSALVADOR = 503;
    public static int TWCY_EQGUINEA = 1015;
    public static int TWCY_ETHIOPIA = 251;
    public static int TWCY_FALKLANDIS = 1016;
    public static int TWCY_FAEROEIS = 298;
    public static int TWCY_FIJIISLANDS = 679;
    public static int TWCY_FINLAND = 358;
    public static int TWCY_FRANCE = 33;
    public static int TWCY_FRANTILLES = 596;
    public static int TWCY_FRGUIANA = 594;
    public static int TWCY_FRPOLYNEISA = 689;
    public static int TWCY_FUTANAIS = 1043;
    public static int TWCY_GABON = 241;
    public static int TWCY_GAMBIA = 220;
    public static int TWCY_GERMANY = 49;
    public static int TWCY_GHANA = 233;
    public static int TWCY_GIBRALTER = 350;
    public static int TWCY_GREECE = 30;
    public static int TWCY_GREENLAND = 299;
    public static int TWCY_GRENADA = 8099;
    public static int TWCY_GRENEDINES = 8015;
    public static int TWCY_GUADELOUPE = 590;
    public static int TWCY_GUAM = 671;
    public static int TWCY_GUANTANAMOBAY = 5399;
    public static int TWCY_GUATEMALA = 502;
    public static int TWCY_GUINEA = 224;
    public static int TWCY_GUINEABISSAU = 1017;
    public static int TWCY_GUYANA = 592;
    public static int TWCY_HAITI = 509;
    public static int TWCY_HONDURAS = 504;
    public static int TWCY_HONGKONG = 852;
    public static int TWCY_HUNGARY = 36;
    public static int TWCY_ICELAND = 354;
    public static int TWCY_INDIA = 91;
    public static int TWCY_INDONESIA = 62;
    public static int TWCY_IRAN = 98;
    public static int TWCY_IRAQ = 964;
    public static int TWCY_IRELAND = 353;
    public static int TWCY_ISRAEL = 972;
    public static int TWCY_ITALY = 39;
    public static int TWCY_IVORYCOAST = 225;
    public static int TWCY_JAMAICA = 8010;
    public static int TWCY_JAPAN = 81;
    public static int TWCY_JORDAN = 962;
    public static int TWCY_KENYA = 254;
    public static int TWCY_KIRIBATI = 1018;
    public static int TWCY_KOREA = 82;
    public static int TWCY_KUWAIT = 965;
    public static int TWCY_LAOS = 1019;
    public static int TWCY_LEBANON = 1020;
    public static int TWCY_LIBERIA = 231;
    public static int TWCY_LIBYA = 218;
    public static int TWCY_LIECHTENSTEIN = 41;
    public static int TWCY_LUXENBOURG = 352;
    public static int TWCY_MACAO = 853;
    public static int TWCY_MADAGASCAR = 1021;
    public static int TWCY_MALAWI = 265;
    public static int TWCY_MALAYSIA = 60;
    public static int TWCY_MALDIVES = 960;
    public static int TWCY_MALI = 1022;
    public static int TWCY_MALTA = 356;
    public static int TWCY_MARSHALLIS = 692;
    public static int TWCY_MAURITANIA = 1023;
    public static int TWCY_MAURITIUS = 230;
    public static int TWCY_MEXICO = 3;
    public static int TWCY_MICRONESIA = 691;
    public static int TWCY_MIQUELON = 508;
    public static int TWCY_MONACO = 33;
    public static int TWCY_MONGOLIA = 1024;
    public static int TWCY_MONTSERRAT = 8011;
    public static int TWCY_MOROCCO = 212;
    public static int TWCY_MOZAMBIQUE = 1025;
    public static int TWCY_NAMIBIA = 264;
    public static int TWCY_NAURU = 1026;
    public static int TWCY_NEPAL = 977;
    public static int TWCY_NETHERLANDS = 31;
    public static int TWCY_NETHANTILLES = 599;
    public static int TWCY_NEVIS = 8012;
    public static int TWCY_NEWCALEDONIA = 687;
    public static int TWCY_NEWZEALAND = 64;
    public static int TWCY_NICARAGUA = 505;
    public static int TWCY_NIGER = 227;
    public static int TWCY_NIGERIA = 234;
    public static int TWCY_NIUE = 1027;
    public static int TWCY_NORFOLKI = 1028;
    public static int TWCY_NORWAY = 47;
    public static int TWCY_OMAN = 968;
    public static int TWCY_PAKISTAN = 92;
    public static int TWCY_PALAU = 1029;
    public static int TWCY_PANAMA = 507;
    public static int TWCY_PARAGUAY = 595;
    public static int TWCY_PERU = 51;
    public static int TWCY_PHILLIPPINES = 63;
    public static int TWCY_PITCAIRNIS = 1030;
    public static int TWCY_PNEWGUINEA = 675;
    public static int TWCY_POLAND = 48;
    public static int TWCY_PORTUGAL = 351;
    public static int TWCY_QATAR = 974;
    public static int TWCY_REUNIONI = 1031;
    public static int TWCY_ROMANIA = 40;
    public static int TWCY_RWANDA = 250;
    public static int TWCY_SAIPAN = 670;
    public static int TWCY_SANMARINO = 39;
    public static int TWCY_SAOTOME = 1033;
    public static int TWCY_SAUDIARABIA = 966;
    public static int TWCY_SENEGAL = 221;
    public static int TWCY_SEYCHELLESIS = 1034;
    public static int TWCY_SIERRALEONE = 1035;
    public static int TWCY_SINGAPORE = 65;
    public static int TWCY_SOLOMONIS = 1036;
    public static int TWCY_SOMALI = 1037;
    public static int TWCY_SOUTHAFRICA = 27;
    public static int TWCY_SPAIN = 34;
    public static int TWCY_SRILANKA = 94;
    public static int TWCY_STHELENA = 1032;
    public static int TWCY_STKITTS = 8013;
    public static int TWCY_STLUCIA = 8014;
    public static int TWCY_STPIERRE = 508;
    public static int TWCY_STVINCENT = 8015;
    public static int TWCY_SUDAN = 1038;
    public static int TWCY_SURINAME = 597;
    public static int TWCY_SWAZILAND = 268;
    public static int TWCY_SWEDEN = 46;
    public static int TWCY_SWITZERLAND = 41;
    public static int TWCY_SYRIA = 1039;
    public static int TWCY_TAIWAN = 886;
    public static int TWCY_TANZANIA = 255;
    public static int TWCY_THAILAND = 66;
    public static int TWCY_TOBAGO = 8016;
    public static int TWCY_TOGO = 228;
    public static int TWCY_TONGAIS = 676;
    public static int TWCY_TRINIDAD = 8016;
    public static int TWCY_TUNISIA = 216;
    public static int TWCY_TURKEY = 90;
    public static int TWCY_TURKSCAICOS = 8017;
    public static int TWCY_TUVALU = 1040;
    public static int TWCY_UGANDA = 256;
    public static int TWCY_USSR = 7;
    public static int TWCY_UAEMIRATES = 971;
    public static int TWCY_UNITEDKINGDOM = 44;
    public static int TWCY_USA = 1;
    public static int TWCY_URUGUAY = 598;
    public static int TWCY_VANUATU = 1041;
    public static int TWCY_VATICANCITY = 39;
    public static int TWCY_VENEZUELA = 58;
    public static int TWCY_WAKE = 1042;
    public static int TWCY_WALLISIS = 1043;
    public static int TWCY_WESTERNSAHARA = 1044;
    public static int TWCY_WESTERNSAMOA = 1045;
    public static int TWCY_YEMEN = 1046;
    public static int TWCY_YUGOSLAVIA = 38;
    public static int TWCY_ZAIRE = 243;
    public static int TWCY_ZAMBIA = 260;
    public static int TWCY_ZIMBABWE = 263;
    public static int TWCY_ALBANIA = 355;
    public static int TWCY_ARMENIA = 374;
    public static int TWCY_AZERBAIJAN = 994;
    public static int TWCY_BELARUS = 375;
    public static int TWCY_BOSNIAHERZGO = 387;
    public static int TWCY_CAMBODIA = 855;
    public static int TWCY_CROATIA = 385;
    public static int TWCY_CZECHREPUBLIC = 420;
    public static int TWCY_DIEGOGARCIA = 246;
    public static int TWCY_ERITREA = 291;
    public static int TWCY_ESTONIA = 372;
    public static int TWCY_GEORGIA = 995;
    public static int TWCY_LATVIA = 371;
    public static int TWCY_LESOTHO = 266;
    public static int TWCY_LITHUANIA = 370;
    public static int TWCY_MACEDONIA = 389;
    public static int TWCY_MAYOTTEIS = 269;
    public static int TWCY_MOLDOVA = 373;
    public static int TWCY_MYANMAR = 95;
    public static int TWCY_NORTHKOREA = 850;
    public static int TWCY_PUERTORICO = 787;
    public static int TWCY_RUSSIA = 7;
    public static int TWCY_SERBIA = 381;
    public static int TWCY_SLOVAKIA = 421;
    public static int TWCY_SLOVENIA = 386;
    public static int TWCY_SOUTHKOREA = 82;
    public static int TWCY_UKRAINE = 380;
    public static int TWCY_USVIRGINIS = 340;
    public static int TWCY_VIETNAM = 84;

    /****************************************************************************
     * Language Constants                                                       *
     ****************************************************************************/
    public static int TWLG_USERLOCALE = -1;
    public static int TWLG_DAN = 0;
    public static int TWLG_DUT = 1;
    public static int TWLG_ENG = 2;
    public static int TWLG_FCF = 3;
    public static int TWLG_FIN = 4;
    public static int TWLG_FRN = 5;
    public static int TWLG_GER = 6;
    public static int TWLG_ICE = 7;
    public static int TWLG_ITN = 8;
    public static int TWLG_NOR = 9;
    public static int TWLG_POR = 10;
    public static int TWLG_SPA = 11;
    public static int TWLG_SWE = 12;
    public static int TWLG_USA = 13;
    public static int TWLG_AFRIKAANS = 14;
    public static int TWLG_ALBANIA = 15;
    public static int TWLG_ARABIC = 16;
    public static int TWLG_ARABIC_ALGERIA = 17;
    public static int TWLG_ARABIC_BAHRAIN = 18;
    public static int TWLG_ARABIC_EGYPT = 19;
    public static int TWLG_ARABIC_IRAQ = 20;
    public static int TWLG_ARABIC_JORDAN = 21;
    public static int TWLG_ARABIC_KUWAIT = 22;
    public static int TWLG_ARABIC_LEBANON = 23;
    public static int TWLG_ARABIC_LIBYA = 24;
    public static int TWLG_ARABIC_MOROCCO = 25;
    public static int TWLG_ARABIC_OMAN = 26;
    public static int TWLG_ARABIC_QATAR = 27;
    public static int TWLG_ARABIC_SAUDIARABIA = 28;
    public static int TWLG_ARABIC_SYRIA = 29;
    public static int TWLG_ARABIC_TUNISIA = 30;
    public static int TWLG_ARABIC_UAE = 31;
    public static int TWLG_ARABIC_YEMEN = 32;
    public static int TWLG_BASQUE = 33;
    public static int TWLG_BYELORUSSIAN = 34;
    public static int TWLG_BULGARIAN = 35;
    public static int TWLG_CATALAN = 36;
    public static int TWLG_CHINESE = 37;
    public static int TWLG_CHINESE_HONGKONG = 38;
    public static int TWLG_CHINESE_PRC = 39;
    public static int TWLG_CHINESE_SINGAPORE = 40;
    public static int TWLG_CHINESE_SIMPLIFIED = 41;
    public static int TWLG_CHINESE_TAIWAN = 42;
    public static int TWLG_CHINESE_TRADITIONAL = 43;
    public static int TWLG_CROATIA = 44;
    public static int TWLG_CZECH = 45;
    public static int TWLG_DANISH = TWLG_DAN;
    public static int TWLG_DUTCH = TWLG_DUT;
    public static int TWLG_DUTCH_BELGIAN = 46;
    public static int TWLG_ENGLISH = TWLG_ENG;
    public static int TWLG_ENGLISH_AUSTRALIAN = 47;
    public static int TWLG_ENGLISH_CANADIAN = 48;
    public static int TWLG_ENGLISH_IRELAND = 49;
    public static int TWLG_ENGLISH_NEWZEALAND = 50;
    public static int TWLG_ENGLISH_SOUTHAFRICA = 51;
    public static int TWLG_ENGLISH_UK = 52;
    public static int TWLG_ENGLISH_USA = TWLG_USA;
    public static int TWLG_ESTONIAN = 53;
    public static int TWLG_FAEROESE = 54;
    public static int TWLG_FARSI = 55;
    public static int TWLG_FINNISH = TWLG_FIN;
    public static int TWLG_FRENCH = TWLG_FRN;
    public static int TWLG_FRENCH_BELGIAN = 56;
    public static int TWLG_FRENCH_CANADIAN = TWLG_FCF;
    public static int TWLG_FRENCH_LUXEMBOURG = 57;
    public static int TWLG_FRENCH_SWISS = 58;
    public static int TWLG_GERMAN = TWLG_GER;
    public static int TWLG_GERMAN_AUSTRIAN = 59;
    public static int TWLG_GERMAN_LUXEMBOURG = 60;
    public static int TWLG_GERMAN_LIECHTENSTEIN = 61;
    public static int TWLG_GERMAN_SWISS = 62;
    public static int TWLG_GREEK = 63;
    public static int TWLG_HEBREW = 64;
    public static int TWLG_HUNGARIAN = 65;
    public static int TWLG_ICELANDIC = TWLG_ICE;
    public static int TWLG_INDONESIAN = 66;
    public static int TWLG_ITALIAN = TWLG_ITN;
    public static int TWLG_ITALIAN_SWISS = 67;
    public static int TWLG_JAPANESE = 68;
    public static int TWLG_KOREAN = 69;
    public static int TWLG_KOREAN_JOHAB = 70;
    public static int TWLG_LATVIAN = 71;
    public static int TWLG_LITHUANIAN = 72;
    public static int TWLG_NORWEGIAN = TWLG_NOR;
    public static int TWLG_NORWEGIAN_BOKMAL = 73;
    public static int TWLG_NORWEGIAN_NYNORSK = 74;
    public static int TWLG_POLISH = 75;
    public static int TWLG_PORTUGUESE = TWLG_POR;
    public static int TWLG_PORTUGUESE_BRAZIL = 76;
    public static int TWLG_ROMANIAN = 77;
    public static int TWLG_RUSSIAN = 78;
    public static int TWLG_SERBIAN_LATIN = 79;
    public static int TWLG_SLOVAK = 80;
    public static int TWLG_SLOVENIAN = 81;
    public static int TWLG_SPANISH = TWLG_SPA;
    public static int TWLG_SPANISH_MEXICAN = 82;
    public static int TWLG_SPANISH_MODERN = 83;
    public static int TWLG_SWEDISH = TWLG_SWE;
    public static int TWLG_THAI = 84;
    public static int TWLG_TURKISH = 85;
    public static int TWLG_UKRANIAN = 86;
    public static int TWLG_ASSAMESE = 87;
    public static int TWLG_BENGALI = 88;
    public static int TWLG_BIHARI = 89;
    public static int TWLG_BODO = 90;
    public static int TWLG_DOGRI = 91;
    public static int TWLG_GUJARATI = 92;
    public static int TWLG_HARYANVI = 93;
    public static int TWLG_HINDI = 94;
    public static int TWLG_KANNADA = 95;
    public static int TWLG_KASHMIRI = 96;
    public static int TWLG_MALAYALAM = 97;
    public static int TWLG_MARATHI = 98;
    public static int TWLG_MARWARI = 99;
    public static int TWLG_MEGHALAYAN = 100;
    public static int TWLG_MIZO = 101;
    public static int TWLG_NAGA = 102;
    public static int TWLG_ORISSI = 103;
    public static int TWLG_PUNJABI = 104;
    public static int TWLG_PUSHTU = 105;
    public static int TWLG_SERBIAN_CYRILLIC = 106;
    public static int TWLG_SIKKIMI = 107;
    public static int TWLG_SWEDISH_FINLAND = 108;
    public static int TWLG_TAMIL = 109;
    public static int TWLG_TELUGU = 110;
    public static int TWLG_TRIPURI = 111;
    public static int TWLG_URDU = 112;
    public static int TWLG_VIETNAMESE = 113;


    /**
     * Gets the name of the cap corresponding to the given code.
     *
     * @param capCode e.g., 0x0101
     * @return cap name (e.g., ICAP_PIXELTYPE) or null if not found.
     */
    public static String getCapName(int capCode) {
        return getMapCapCodeToName().get(capCode);
    }

    /**
     * Gets the code of the cap corresponding to the given name.
     *
     * @param capName e.g., ICAP_PIXELTYPE
     * @return cap code (e.g., 0x0101) or -1 if not found.
     */
    public static int getCapCode(String capName) {
        Integer code = getMapCapNameToCode().get(capName);
        return code == null ? -1 : code;
    }

    /**
     * Gets the name of the result code.
     *
     * @param twrcCode e.g., 0
     * @return Result code name (e.g., TWRC_SUCCESS) or null if not found.
     */
    public static String getTwrcName(int twrcCode) {
        return getMapTwrcCodeToName().get(twrcCode);
    }

    /**
     * Gets the result code corresponding to the given name.
     *
     * @param twrcName e.g., TWRC_SUCCESS
     * @return TWEI code (e.g., 0) or -1 if not found.
     */
    public static int getTwrcCode(String twrcName) {
        Integer code = getMapTwrcNameToCode().get(twrcName);
        return code == null ? -1 : code;
    }

    /**
     * Gets the name of the TWEI attribute corresponding to the given code.
     *
     * @param tweiCode e.g., 0x1200
     * @return TWEI name (e.g., TWEI_BARCODEX) or null if not found.
     */
    public static String getTweiName(int tweiCode) {
        return getMapTweiCodeToName().get(tweiCode);
    }

    /**
     * Gets the code of the TWEI attribute corresponding to the given name.
     *
     * @param tweiName e.g., TWEI_BARCODEX
     * @return TWEI code (e.g., 0x1200) or -1 if not found.
     */
    public static int getTweiCode(String tweiName) {
        Integer code = getMapTweiNameToCode().get(tweiName);
        return code == null ? -1 : code;
    }

    /**
     * Returns the integer value of any TW* constant.
     * @param contantName
     * @return
     */
    public static Integer getConstantValue(String contantName) {
        return getMapConstantNameToIntValue().get(contantName);
    }


    private static Map<String, Integer> mapCapNameToCode;

    private static Map<String, Integer> getMapCapNameToCode() {
        if (mapCapNameToCode != null) {
            return mapCapNameToCode;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("CAP_CUSTOMBASE", 0x8000); /* Base of custom capabilities */

	/* all data sources are REQUIRED to support these caps */
        map.put("CAP_XFERCOUNT", 0x0001);

	/* image data sources are REQUIRED to support these caps */
        map.put("ICAP_COMPRESSION", 0x0100);
        map.put("ICAP_PIXELTYPE", 0x0101);
        map.put("ICAP_UNITS", 0x0102);
        map.put("ICAP_XFERMECH", 0x0103);

	/* all data sources MAY support these caps */
        map.put("CAP_AUTHOR", 0x1000);
        map.put("CAP_CAPTION", 0x1001);
        map.put("CAP_FEEDERENABLED", 0x1002);
        map.put("CAP_FEEDERLOADED", 0x1003);
        map.put("CAP_TIMEDATE", 0x1004);
        map.put("CAP_SUPPORTEDCAPS", 0x1005);
        map.put("CAP_EXTENDEDCAPS", 0x1006);
        map.put("CAP_AUTOFEED", 0x1007);
        map.put("CAP_CLEARPAGE", 0x1008);
        map.put("CAP_FEEDPAGE", 0x1009);
        map.put("CAP_REWINDPAGE", 0x100a);
        map.put("CAP_INDICATORS", 0x100b);
        map.put("CAP_PAPERDETECTABLE", 0x100d);
        map.put("CAP_UICONTROLLABLE", 0x100e);
        map.put("CAP_DEVICEONLINE", 0x100f);
        map.put("CAP_AUTOSCAN", 0x1010);
        map.put("CAP_THUMBNAILSENABLED", 0x1011);
        map.put("CAP_DUPLEX", 0x1012);
        map.put("CAP_DUPLEXENABLED", 0x1013);
        map.put("CAP_ENABLEDSUIONLY", 0x1014);
        map.put("CAP_CUSTOMDSDATA", 0x1015);
        map.put("CAP_ENDORSER", 0x1016);
        map.put("CAP_JOBCONTROL", 0x1017);
        map.put("CAP_ALARMS", 0x1018);
        map.put("CAP_ALARMVOLUME", 0x1019);
        map.put("CAP_AUTOMATICCAPTURE", 0x101a);
        map.put("CAP_TIMEBEFOREFIRSTCAPTURE", 0x101b);
        map.put("CAP_TIMEBETWEENCAPTURES", 0x101c);
        map.put("CAP_CLEARBUFFERS", 0x101d);
        map.put("CAP_MAXBATCHBUFFERS", 0x101e);
        map.put("CAP_DEVICETIMEDATE", 0x101f);
        map.put("CAP_POWERSUPPLY", 0x1020);
        map.put("CAP_CAMERAPREVIEWUI", 0x1021);
        map.put("CAP_DEVICEEVENT", 0x1022);
        map.put("CAP_SERIALNUMBER", 0x1024);
        map.put("CAP_PRINTER", 0x1026);
        map.put("CAP_PRINTERENABLED", 0x1027);
        map.put("CAP_PRINTERINDEX", 0x1028);
        map.put("CAP_PRINTERMODE", 0x1029);
        map.put("CAP_PRINTERSTRING", 0x102a);
        map.put("CAP_PRINTERSUFFIX", 0x102b);
        map.put("CAP_LANGUAGE", 0x102c);
        map.put("CAP_FEEDERALIGNMENT", 0x102d);
        map.put("CAP_FEEDERORDER", 0x102e);
        map.put("CAP_REACQUIREALLOWED", 0x1030);
        map.put("CAP_BATTERYMINUTES", 0x1032);
        map.put("CAP_BATTERYPERCENTAGE", 0x1033);
        map.put("CAP_CAMERASIDE", 0x1034);
        map.put("CAP_SEGMENTED", 0x1035);
        map.put("CAP_CAMERAENABLED", 0x1036);
        map.put("CAP_CAMERAORDER", 0x1037);
        map.put("CAP_MICRENABLED", 0x1038);
        map.put("CAP_FEEDERPREP", 0x1039);
        map.put("CAP_FEEDERPOCKET", 0x103a);
        map.put("CAP_AUTOMATICSENSEMEDIUM", 0x103b);
        map.put("CAP_CUSTOMINTERFACEGUID", 0x103c);
        map.put("CAP_SUPPORTEDCAPSSEGMENTUNIQUE", 0x103d);
        map.put("CAP_SUPPORTEDDATS", 0x103e);
        map.put("CAP_DOUBLEFEEDDETECTION", 0x103f);
        map.put("CAP_DOUBLEFEEDDETECTIONLENGTH", 0x1040);
        map.put("CAP_DOUBLEFEEDDETECTIONSENSITIVITY", 0x1041);
        map.put("CAP_DOUBLEFEEDDETECTIONRESPONSE", 0x1042);
        map.put("CAP_PAPERHANDLING", 0x1043);
        map.put("CAP_INDICATORSMODE", 0x1044);
        map.put("CAP_PRINTERVERTICALOFFSET", 0x1045);
        map.put("CAP_POWERSAVETIME", 0x1046);
        map.put("CAP_PRINTERCHARROTATION", 0x1047);
        map.put("CAP_PRINTERFONTSTYLE", 0x1048);
        map.put("CAP_PRINTERINDEXLEADCHAR", 0x1049);
        map.put("CAP_PRINTERINDEXMAXVALUE", 0x104A);
        map.put("CAP_PRINTERINDEXNUMDIGITS", 0x104B);
        map.put("CAP_PRINTERINDEXSTEP", 0x104C);
        map.put("CAP_PRINTERINDEXTRIGGER", 0x104D);
        map.put("CAP_PRINTERSTRINGPREVIEW", 0x104E);

	/* image data sources MAY support these caps */
        map.put("ICAP_AUTOBRIGHT", 0x1100);
        map.put("ICAP_BRIGHTNESS", 0x1101);
        map.put("ICAP_CONTRAST", 0x1103);
        map.put("ICAP_CUSTHALFTONE", 0x1104);
        map.put("ICAP_EXPOSURETIME", 0x1105);
        map.put("ICAP_FILTER", 0x1106);
        map.put("ICAP_FLASHUSED", 0x1107);
        map.put("ICAP_GAMMA", 0x1108);
        map.put("ICAP_HALFTONES", 0x1109);
        map.put("ICAP_HIGHLIGHT", 0x110a);
        map.put("ICAP_IMAGEFILEFORMAT", 0x110c);
        map.put("ICAP_LAMPSTATE", 0x110d);
        map.put("ICAP_LIGHTSOURCE", 0x110e);
        map.put("ICAP_ORIENTATION", 0x1110);
        map.put("ICAP_PHYSICALWIDTH", 0x1111);
        map.put("ICAP_PHYSICALHEIGHT", 0x1112);
        map.put("ICAP_SHADOW", 0x1113);
        map.put("ICAP_FRAMES", 0x1114);
        map.put("ICAP_XNATIVERESOLUTION", 0x1116);
        map.put("ICAP_YNATIVERESOLUTION", 0x1117);
        map.put("ICAP_XRESOLUTION", 0x1118);
        map.put("ICAP_YRESOLUTION", 0x1119);
        map.put("ICAP_MAXFRAMES", 0x111a);
        map.put("ICAP_TILES", 0x111b);
        map.put("ICAP_BITORDER", 0x111c);
        map.put("ICAP_CCITTKFACTOR", 0x111d);
        map.put("ICAP_LIGHTPATH", 0x111e);
        map.put("ICAP_PIXELFLAVOR", 0x111f);
        map.put("ICAP_PLANARCHUNKY", 0x1120);
        map.put("ICAP_ROTATION", 0x1121);
        map.put("ICAP_SUPPORTEDSIZES", 0x1122);
        map.put("ICAP_THRESHOLD", 0x1123);
        map.put("ICAP_XSCALING", 0x1124);
        map.put("ICAP_YSCALING", 0x1125);
        map.put("ICAP_BITORDERCODES", 0x1126);
        map.put("ICAP_PIXELFLAVORCODES", 0x1127);
        map.put("ICAP_JPEGPIXELTYPE", 0x1128);
        map.put("ICAP_TIMEFILL", 0x112a);
        map.put("ICAP_BITDEPTH", 0x112b);
        map.put("ICAP_BITDEPTHREDUCTION", 0x112c);
        map.put("ICAP_UNDEFINEDIMAGESIZE", 0x112d);
        map.put("ICAP_IMAGEDATASET", 0x112e);
        map.put("ICAP_EXTIMAGEINFO", 0x112f);
        map.put("ICAP_MINIMUMHEIGHT", 0x1130);
        map.put("ICAP_MINIMUMWIDTH", 0x1131);
        map.put("ICAP_AUTODISCARDBLANKPAGES", 0x1134);
        map.put("ICAP_FLIPROTATION", 0x1136);
        map.put("ICAP_BARCODEDETECTIONENABLED", 0x1137);
        map.put("ICAP_SUPPORTEDBARCODETYPES", 0x1138);
        map.put("ICAP_BARCODEMAXSEARCHPRIORITIES", 0x1139);
        map.put("ICAP_BARCODESEARCHPRIORITIES", 0x113a);
        map.put("ICAP_BARCODESEARCHMODE", 0x113b);
        map.put("ICAP_BARCODEMAXRETRIES", 0x113c);
        map.put("ICAP_BARCODETIMEOUT", 0x113d);
        map.put("ICAP_ZOOMFACTOR", 0x113e);
        map.put("ICAP_PATCHCODEDETECTIONENABLED", 0x113f);
        map.put("ICAP_SUPPORTEDPATCHCODETYPES", 0x1140);
        map.put("ICAP_PATCHCODEMAXSEARCHPRIORITIES", 0x1141);
        map.put("ICAP_PATCHCODESEARCHPRIORITIES", 0x1142);
        map.put("ICAP_PATCHCODESEARCHMODE", 0x1143);
        map.put("ICAP_PATCHCODEMAXRETRIES", 0x1144);
        map.put("ICAP_PATCHCODETIMEOUT", 0x1145);
        map.put("ICAP_FLASHUSED2", 0x1146);
        map.put("ICAP_IMAGEFILTER", 0x1147);
        map.put("ICAP_NOISEFILTER", 0x1148);
        map.put("ICAP_OVERSCAN", 0x1149);
        map.put("ICAP_AUTOMATICBORDERDETECTION", 0x1150);
        map.put("ICAP_AUTOMATICDESKEW", 0x1151);
        map.put("ICAP_AUTOMATICROTATE", 0x1152);
        map.put("ICAP_JPEGQUALITY", 0x1153);
        map.put("ICAP_FEEDERTYPE", 0x1154);
        map.put("ICAP_ICCPROFILE", 0x1155);
        map.put("ICAP_AUTOSIZE", 0x1156);
        map.put("ICAP_AUTOMATICCROPUSESFRAME", 0x1157);
        map.put("ICAP_AUTOMATICLENGTHDETECTION", 0x1158);
        map.put("ICAP_AUTOMATICCOLORENABLED", 0x1159);
        map.put("ICAP_AUTOMATICCOLORNONCOLORPIXELTYPE", 0x115a);
        map.put("ICAP_COLORMANAGEMENTENABLED", 0x115b);
        map.put("ICAP_IMAGEMERGE", 0x115c);
        map.put("ICAP_IMAGEMERGEHEIGHTTHRESHOLD", 0x115d);
        map.put("ICAP_SUPPORTEDEXTIMAGEINFO", 0x115e);
        map.put("ICAP_FILMTYPE", 0x115f);
        map.put("ICAP_MIRROR", 0x1160);
        map.put("ICAP_JPEGSUBSAMPLING", 0x1161);

        map.put("CAP_SUPPORTEDCAPSEXT", 0x100c);
        map.put("CAP_PAGEMULTIPLEACQUIRE", 0x1023);
        map.put("CAP_PAPERBINDING", 0x102f);
        map.put("CAP_PASSTHRU", 0x1031);
        map.put("CAP_POWERDOWNTIME", 0x1034);

        mapCapNameToCode = map;
        return mapCapNameToCode;
    }

    private static Map<Integer, String> mapCapCodeToName;

    private static Map<Integer, String> getMapCapCodeToName() {
        if (mapCapCodeToName != null) {
            return mapCapCodeToName;
        }

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0x8000, "CAP_CUSTOMBASE"); /* Base of custom capabilities */

	/* all data sources are REQUIRED to support these caps */
        map.put(0x0001, "CAP_XFERCOUNT");

	/* image data sources are REQUIRED to support these caps */
        map.put(0x0100, "ICAP_COMPRESSION");
        map.put(0x0101, "ICAP_PIXELTYPE");
        map.put(0x0102, "ICAP_UNITS");
        map.put(0x0103, "ICAP_XFERMECH");

	/* all data sources MAY support these caps */
        map.put(0x1000, "CAP_AUTHOR");
        map.put(0x1001, "CAP_CAPTION");
        map.put(0x1002, "CAP_FEEDERENABLED");
        map.put(0x1003, "CAP_FEEDERLOADED");
        map.put(0x1004, "CAP_TIMEDATE");
        map.put(0x1005, "CAP_SUPPORTEDCAPS");
        map.put(0x1006, "CAP_EXTENDEDCAPS");
        map.put(0x1007, "CAP_AUTOFEED");
        map.put(0x1008, "CAP_CLEARPAGE");
        map.put(0x1009, "CAP_FEEDPAGE");
        map.put(0x100a, "CAP_REWINDPAGE");
        map.put(0x100b, "CAP_INDICATORS");
        map.put(0x100d, "CAP_PAPERDETECTABLE");
        map.put(0x100e, "CAP_UICONTROLLABLE");
        map.put(0x100f, "CAP_DEVICEONLINE");
        map.put(0x1010, "CAP_AUTOSCAN");
        map.put(0x1011, "CAP_THUMBNAILSENABLED");
        map.put(0x1012, "CAP_DUPLEX");
        map.put(0x1013, "CAP_DUPLEXENABLED");
        map.put(0x1014, "CAP_ENABLEDSUIONLY");
        map.put(0x1015, "CAP_CUSTOMDSDATA");
        map.put(0x1016, "CAP_ENDORSER");
        map.put(0x1017, "CAP_JOBCONTROL");
        map.put(0x1018, "CAP_ALARMS");
        map.put(0x1019, "CAP_ALARMVOLUME");
        map.put(0x101a, "CAP_AUTOMATICCAPTURE");
        map.put(0x101b, "CAP_TIMEBEFOREFIRSTCAPTURE");
        map.put(0x101c, "CAP_TIMEBETWEENCAPTURES");
        map.put(0x101d, "CAP_CLEARBUFFERS");
        map.put(0x101e, "CAP_MAXBATCHBUFFERS");
        map.put(0x101f, "CAP_DEVICETIMEDATE");
        map.put(0x1020, "CAP_POWERSUPPLY");
        map.put(0x1021, "CAP_CAMERAPREVIEWUI");
        map.put(0x1022, "CAP_DEVICEEVENT");
        map.put(0x1024, "CAP_SERIALNUMBER");
        map.put(0x1026, "CAP_PRINTER");
        map.put(0x1027, "CAP_PRINTERENABLED");
        map.put(0x1028, "CAP_PRINTERINDEX");
        map.put(0x1029, "CAP_PRINTERMODE");
        map.put(0x102a, "CAP_PRINTERSTRING");
        map.put(0x102b, "CAP_PRINTERSUFFIX");
        map.put(0x102c, "CAP_LANGUAGE");
        map.put(0x102d, "CAP_FEEDERALIGNMENT");
        map.put(0x102e, "CAP_FEEDERORDER");
        map.put(0x1030, "CAP_REACQUIREALLOWED");
        map.put(0x1032, "CAP_BATTERYMINUTES");
        map.put(0x1033, "CAP_BATTERYPERCENTAGE");
        map.put(0x1034, "CAP_CAMERASIDE");
        map.put(0x1035, "CAP_SEGMENTED");
        map.put(0x1036, "CAP_CAMERAENABLED");
        map.put(0x1037, "CAP_CAMERAORDER");
        map.put(0x1038, "CAP_MICRENABLED");
        map.put(0x1039, "CAP_FEEDERPREP");
        map.put(0x103a, "CAP_FEEDERPOCKET");
        map.put(0x103b, "CAP_AUTOMATICSENSEMEDIUM");
        map.put(0x103c, "CAP_CUSTOMINTERFACEGUID");
        map.put(0x103d, "CAP_SUPPORTEDCAPSSEGMENTUNIQUE");
        map.put(0x103e, "CAP_SUPPORTEDDATS");
        map.put(0x103f, "CAP_DOUBLEFEEDDETECTION");
        map.put(0x1040, "CAP_DOUBLEFEEDDETECTIONLENGTH");
        map.put(0x1041, "CAP_DOUBLEFEEDDETECTIONSENSITIVITY");
        map.put(0x1042, "CAP_DOUBLEFEEDDETECTIONRESPONSE");
        map.put(0x1043, "CAP_PAPERHANDLING");
        map.put(0x1044, "CAP_INDICATORSMODE");
        map.put(0x1045, "CAP_PRINTERVERTICALOFFSET");
        map.put(0x1046, "CAP_POWERSAVETIME");
        map.put(0x1047, "CAP_PRINTERCHARROTATION");
        map.put(0x1048, "CAP_PRINTERFONTSTYLE");
        map.put(0x1049, "CAP_PRINTERINDEXLEADCHAR");
        map.put(0x104A, "CAP_PRINTERINDEXMAXVALUE");
        map.put(0x104B, "CAP_PRINTERINDEXNUMDIGITS");
        map.put(0x104C, "CAP_PRINTERINDEXSTEP");
        map.put(0x104D, "CAP_PRINTERINDEXTRIGGER");
        map.put(0x104E, "CAP_PRINTERSTRINGPREVIEW");

	/* image data sources MAY support these caps */
        map.put(0x1100, "ICAP_AUTOBRIGHT");
        map.put(0x1101, "ICAP_BRIGHTNESS");
        map.put(0x1103, "ICAP_CONTRAST");
        map.put(0x1104, "ICAP_CUSTHALFTONE");
        map.put(0x1105, "ICAP_EXPOSURETIME");
        map.put(0x1106, "ICAP_FILTER");
        map.put(0x1107, "ICAP_FLASHUSED");
        map.put(0x1108, "ICAP_GAMMA");
        map.put(0x1109, "ICAP_HALFTONES");
        map.put(0x110a, "ICAP_HIGHLIGHT");
        map.put(0x110c, "ICAP_IMAGEFILEFORMAT");
        map.put(0x110d, "ICAP_LAMPSTATE");
        map.put(0x110e, "ICAP_LIGHTSOURCE");
        map.put(0x1110, "ICAP_ORIENTATION");
        map.put(0x1111, "ICAP_PHYSICALWIDTH");
        map.put(0x1112, "ICAP_PHYSICALHEIGHT");
        map.put(0x1113, "ICAP_SHADOW");
        map.put(0x1114, "ICAP_FRAMES");
        map.put(0x1116, "ICAP_XNATIVERESOLUTION");
        map.put(0x1117, "ICAP_YNATIVERESOLUTION");
        map.put(0x1118, "ICAP_XRESOLUTION");
        map.put(0x1119, "ICAP_YRESOLUTION");
        map.put(0x111a, "ICAP_MAXFRAMES");
        map.put(0x111b, "ICAP_TILES");
        map.put(0x111c, "ICAP_BITORDER");
        map.put(0x111d, "ICAP_CCITTKFACTOR");
        map.put(0x111e, "ICAP_LIGHTPATH");
        map.put(0x111f, "ICAP_PIXELFLAVOR");
        map.put(0x1120, "ICAP_PLANARCHUNKY");
        map.put(0x1121, "ICAP_ROTATION");
        map.put(0x1122, "ICAP_SUPPORTEDSIZES");
        map.put(0x1123, "ICAP_THRESHOLD");
        map.put(0x1124, "ICAP_XSCALING");
        map.put(0x1125, "ICAP_YSCALING");
        map.put(0x1126, "ICAP_BITORDERCODES");
        map.put(0x1127, "ICAP_PIXELFLAVORCODES");
        map.put(0x1128, "ICAP_JPEGPIXELTYPE");
        map.put(0x112a, "ICAP_TIMEFILL");
        map.put(0x112b, "ICAP_BITDEPTH");
        map.put(0x112c, "ICAP_BITDEPTHREDUCTION");
        map.put(0x112d, "ICAP_UNDEFINEDIMAGESIZE");
        map.put(0x112e, "ICAP_IMAGEDATASET");
        map.put(0x112f, "ICAP_EXTIMAGEINFO");
        map.put(0x1130, "ICAP_MINIMUMHEIGHT");
        map.put(0x1131, "ICAP_MINIMUMWIDTH");
        map.put(0x1134, "ICAP_AUTODISCARDBLANKPAGES");
        map.put(0x1136, "ICAP_FLIPROTATION");
        map.put(0x1137, "ICAP_BARCODEDETECTIONENABLED");
        map.put(0x1138, "ICAP_SUPPORTEDBARCODETYPES");
        map.put(0x1139, "ICAP_BARCODEMAXSEARCHPRIORITIES");
        map.put(0x113a, "ICAP_BARCODESEARCHPRIORITIES");
        map.put(0x113b, "ICAP_BARCODESEARCHMODE");
        map.put(0x113c, "ICAP_BARCODEMAXRETRIES");
        map.put(0x113d, "ICAP_BARCODETIMEOUT");
        map.put(0x113e, "ICAP_ZOOMFACTOR");
        map.put(0x113f, "ICAP_PATCHCODEDETECTIONENABLED");
        map.put(0x1140, "ICAP_SUPPORTEDPATCHCODETYPES");
        map.put(0x1141, "ICAP_PATCHCODEMAXSEARCHPRIORITIES");
        map.put(0x1142, "ICAP_PATCHCODESEARCHPRIORITIES");
        map.put(0x1143, "ICAP_PATCHCODESEARCHMODE");
        map.put(0x1144, "ICAP_PATCHCODEMAXRETRIES");
        map.put(0x1145, "ICAP_PATCHCODETIMEOUT");
        map.put(0x1146, "ICAP_FLASHUSED2");
        map.put(0x1147, "ICAP_IMAGEFILTER");
        map.put(0x1148, "ICAP_NOISEFILTER");
        map.put(0x1149, "ICAP_OVERSCAN");
        map.put(0x1150, "ICAP_AUTOMATICBORDERDETECTION");
        map.put(0x1151, "ICAP_AUTOMATICDESKEW");
        map.put(0x1152, "ICAP_AUTOMATICROTATE");
        map.put(0x1153, "ICAP_JPEGQUALITY");
        map.put(0x1154, "ICAP_FEEDERTYPE");
        map.put(0x1155, "ICAP_ICCPROFILE");
        map.put(0x1156, "ICAP_AUTOSIZE");
        map.put(0x1157, "ICAP_AUTOMATICCROPUSESFRAME");
        map.put(0x1158, "ICAP_AUTOMATICLENGTHDETECTION");
        map.put(0x1159, "ICAP_AUTOMATICCOLORENABLED");
        map.put(0x115a, "ICAP_AUTOMATICCOLORNONCOLORPIXELTYPE");
        map.put(0x115b, "ICAP_COLORMANAGEMENTENABLED");
        map.put(0x115c, "ICAP_IMAGEMERGE");
        map.put(0x115d, "ICAP_IMAGEMERGEHEIGHTTHRESHOLD");
        map.put(0x115e, "ICAP_SUPPORTEDEXTIMAGEINFO");
        map.put(0x115f, "ICAP_FILMTYPE");
        map.put(0x1160, "ICAP_MIRROR");
        map.put(0x1161, "ICAP_JPEGSUBSAMPLING");

        map.put(0x100c, "CAP_SUPPORTEDCAPSEXT");
        map.put(0x1023, "CAP_PAGEMULTIPLEACQUIRE");
        map.put(0x102f, "CAP_PAPERBINDING");
        map.put(0x1031, "CAP_PASSTHRU");
        map.put(0x1034, "CAP_POWERDOWNTIME");

        mapCapCodeToName = map;
        return mapCapCodeToName;
    }

    private static Map<Integer, String> mapTweiCodeToName;

    private static Map<Integer, String> getMapTweiCodeToName() {
        if (mapTweiCodeToName != null) {
            return mapTweiCodeToName;
        }

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0x1200, "TWEI_BARCODEX");
        map.put(0x1201, "TWEI_BARCODEY");
        map.put(0x1202, "TWEI_BARCODETEXT");
        map.put(0x1203, "TWEI_BARCODETYPE");
        map.put(0x1204, "TWEI_DESHADETOP");
        map.put(0x1205, "TWEI_DESHADELEFT");
        map.put(0x1206, "TWEI_DESHADEHEIGHT");
        map.put(0x1207, "TWEI_DESHADEWIDTH");
        map.put(0x1208, "TWEI_DESHADESIZE");
        map.put(0x1209, "TWEI_SPECKLESREMOVED");
        map.put(0x120A, "TWEI_HORZLINEXCOORD");
        map.put(0x120B, "TWEI_HORZLINEYCOORD");
        map.put(0x120C, "TWEI_HORZLINELENGTH");
        map.put(0x120D, "TWEI_HORZLINETHICKNESS");
        map.put(0x120E, "TWEI_VERTLINEXCOORD");
        map.put(0x120F, "TWEI_VERTLINEYCOORD");
        map.put(0x1210, "TWEI_VERTLINELENGTH");
        map.put(0x1211, "TWEI_VERTLINETHICKNESS");
        map.put(0x1212, "TWEI_PATCHCODE");
        map.put(0x1213, "TWEI_ENDORSEDTEXT");
        map.put(0x1214, "TWEI_FORMCONFIDENCE");
        map.put(0x1215, "TWEI_FORMTEMPLATEMATCH");
        map.put(0x1216, "TWEI_FORMTEMPLATEPAGEMATCH");
        map.put(0x1217, "TWEI_FORMHORZDOCOFFSET");
        map.put(0x1218, "TWEI_FORMVERTDOCOFFSET");
        map.put(0x1219, "TWEI_BARCODECOUNT");
        map.put(0x121A, "TWEI_BARCODECONFIDENCE");
        map.put(0x121B, "TWEI_BARCODEROTATION");
        map.put(0x121C, "TWEI_BARCODETEXTLENGTH");
        map.put(0x121D, "TWEI_DESHADECOUNT");
        map.put(0x121E, "TWEI_DESHADEBLACKCOUNTOLD");
        map.put(0x121F, "TWEI_DESHADEBLACKCOUNTNEW");
        map.put(0x1220, "TWEI_DESHADEBLACKRLMIN");
        map.put(0x1221, "TWEI_DESHADEBLACKRLMAX");
        map.put(0x1222, "TWEI_DESHADEWHITECOUNTOLD");
        map.put(0x1223, "TWEI_DESHADEWHITECOUNTNEW");
        map.put(0x1224, "TWEI_DESHADEWHITERLMIN");
        map.put(0x1225, "TWEI_DESHADEWHITERLAVE");
        map.put(0x1226, "TWEI_DESHADEWHITERLMAX");
        map.put(0x1227, "TWEI_BLACKSPECKLESREMOVED");
        map.put(0x1228, "TWEI_WHITESPECKLESREMOVED");
        map.put(0x1229, "TWEI_HORZLINECOUNT");
        map.put(0x122A, "TWEI_VERTLINECOUNT");
        map.put(0x122B, "TWEI_DESKEWSTATUS");
        map.put(0x122C, "TWEI_SKEWORIGINALANGLE");
        map.put(0x122D, "TWEI_SKEWFINALANGLE");
        map.put(0x122E, "TWEI_SKEWCONFIDENCE");
        map.put(0x122F, "TWEI_SKEWWINDOWX1");
        map.put(0x1230, "TWEI_SKEWWINDOWY1");
        map.put(0x1231, "TWEI_SKEWWINDOWX2");
        map.put(0x1232, "TWEI_SKEWWINDOWY2");
        map.put(0x1233, "TWEI_SKEWWINDOWX3");
        map.put(0x1234, "TWEI_SKEWWINDOWY3");
        map.put(0x1235, "TWEI_SKEWWINDOWX4");
        map.put(0x1236, "TWEI_SKEWWINDOWY4");
        map.put(0x1238, "TWEI_BOOKNAME");
        map.put(0x1239, "TWEI_CHAPTERNUMBER");
        map.put(0x123A, "TWEI_DOCUMENTNUMBER");
        map.put(0x123B, "TWEI_PAGENUMBER");
        map.put(0x123C, "TWEI_CAMERA");
        map.put(0x123D, "TWEI_FRAMENUMBER");
        map.put(0x123E, "TWEI_FRAME");
        map.put(0x123F, "TWEI_PIXELFLAVOR");
        map.put(0x1240, "TWEI_ICCPROFILE");
        map.put(0x1241, "TWEI_LASTSEGMENT");
        map.put(0x1242, "TWEI_SEGMENTNUMBER");
        map.put(0x1243, "TWEI_MAGDATA");
        map.put(0x1244, "TWEI_MAGTYPE");
        map.put(0x1245, "TWEI_PAGESIDE");
        map.put(0x1246, "TWEI_FILESYSTEMSOURCE");
        map.put(0x1247, "TWEI_IMAGEMERGED");
        map.put(0x1248, "TWEI_MAGDATALENGTH");
        map.put(0x1249, "TWEI_PAPERCOUNT");
        map.put(0x124A, "TWEI_PRINTERTEXT");

        map.put(0x0000, "TWEJ_NONE");
        map.put(0x0001, "TWEJ_MIDSEPARATOR");
        map.put(0x0002, "TWEJ_PATCH1");
        map.put(0x0003, "TWEJ_PATCH2");
        map.put(0x0004, "TWEJ_PATCH3");
        map.put(0x0005, "TWEJ_PATCH4");
        map.put(0x0006, "TWEJ_PATCH6");
        map.put(0x0007, "TWEJ_PATCHT");

        mapTweiCodeToName = map;
        return mapTweiCodeToName;
    }

    private static Map<String, Integer> mapTweiNameToCode;

    private static Map<String, Integer> getMapTweiNameToCode() {
        if (mapTweiNameToCode != null) {
            return mapTweiNameToCode;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("TWEI_BARCODEX", 0x1200);
        map.put("TWEI_BARCODEY", 0x1201);
        map.put("TWEI_BARCODETEXT", 0x1202);
        map.put("TWEI_BARCODETYPE", 0x1203);
        map.put("TWEI_DESHADETOP", 0x1204);
        map.put("TWEI_DESHADELEFT", 0x1205);
        map.put("TWEI_DESHADEHEIGHT", 0x1206);
        map.put("TWEI_DESHADEWIDTH", 0x1207);
        map.put("TWEI_DESHADESIZE", 0x1208);
        map.put("TWEI_SPECKLESREMOVED", 0x1209);
        map.put("TWEI_HORZLINEXCOORD", 0x120A);
        map.put("TWEI_HORZLINEYCOORD", 0x120B);
        map.put("TWEI_HORZLINELENGTH", 0x120C);
        map.put("TWEI_HORZLINETHICKNESS", 0x120D);
        map.put("TWEI_VERTLINEXCOORD", 0x120E);
        map.put("TWEI_VERTLINEYCOORD", 0x120F);
        map.put("TWEI_VERTLINELENGTH", 0x1210);
        map.put("TWEI_VERTLINETHICKNESS", 0x1211);
        map.put("TWEI_PATCHCODE", 0x1212);
        map.put("TWEI_ENDORSEDTEXT", 0x1213);
        map.put("TWEI_FORMCONFIDENCE", 0x1214);
        map.put("TWEI_FORMTEMPLATEMATCH", 0x1215);
        map.put("TWEI_FORMTEMPLATEPAGEMATCH", 0x1216);
        map.put("TWEI_FORMHORZDOCOFFSET", 0x1217);
        map.put("TWEI_FORMVERTDOCOFFSET", 0x1218);
        map.put("TWEI_BARCODECOUNT", 0x1219);
        map.put("TWEI_BARCODECONFIDENCE", 0x121A);
        map.put("TWEI_BARCODEROTATION", 0x121B);
        map.put("TWEI_BARCODETEXTLENGTH", 0x121C);
        map.put("TWEI_DESHADECOUNT", 0x121D);
        map.put("TWEI_DESHADEBLACKCOUNTOLD", 0x121E);
        map.put("TWEI_DESHADEBLACKCOUNTNEW", 0x121F);
        map.put("TWEI_DESHADEBLACKRLMIN", 0x1220);
        map.put("TWEI_DESHADEBLACKRLMAX", 0x1221);
        map.put("TWEI_DESHADEWHITECOUNTOLD", 0x1222);
        map.put("TWEI_DESHADEWHITECOUNTNEW", 0x1223);
        map.put("TWEI_DESHADEWHITERLMIN", 0x1224);
        map.put("TWEI_DESHADEWHITERLAVE", 0x1225);
        map.put("TWEI_DESHADEWHITERLMAX", 0x1226);
        map.put("TWEI_BLACKSPECKLESREMOVED", 0x1227);
        map.put("TWEI_WHITESPECKLESREMOVED", 0x1228);
        map.put("TWEI_HORZLINECOUNT", 0x1229);
        map.put("TWEI_VERTLINECOUNT", 0x122A);
        map.put("TWEI_DESKEWSTATUS", 0x122B);
        map.put("TWEI_SKEWORIGINALANGLE", 0x122C);
        map.put("TWEI_SKEWFINALANGLE", 0x122D);
        map.put("TWEI_SKEWCONFIDENCE", 0x122E);
        map.put("TWEI_SKEWWINDOWX1", 0x122F);
        map.put("TWEI_SKEWWINDOWY1", 0x1230);
        map.put("TWEI_SKEWWINDOWX2", 0x1231);
        map.put("TWEI_SKEWWINDOWY2", 0x1232);
        map.put("TWEI_SKEWWINDOWX3", 0x1233);
        map.put("TWEI_SKEWWINDOWY3", 0x1234);
        map.put("TWEI_SKEWWINDOWX4", 0x1235);
        map.put("TWEI_SKEWWINDOWY4", 0x1236);
        map.put("TWEI_BOOKNAME", 0x1238);
        map.put("TWEI_CHAPTERNUMBER", 0x1239);
        map.put("TWEI_DOCUMENTNUMBER", 0x123A);
        map.put("TWEI_PAGENUMBER", 0x123B);
        map.put("TWEI_CAMERA", 0x123C);
        map.put("TWEI_FRAMENUMBER", 0x123D);
        map.put("TWEI_FRAME", 0x123E);
        map.put("TWEI_PIXELFLAVOR", 0x123F);
        map.put("TWEI_ICCPROFILE", 0x1240);
        map.put("TWEI_LASTSEGMENT", 0x1241);
        map.put("TWEI_SEGMENTNUMBER", 0x1242);
        map.put("TWEI_MAGDATA", 0x1243);
        map.put("TWEI_MAGTYPE", 0x1244);
        map.put("TWEI_PAGESIDE", 0x1245);
        map.put("TWEI_FILESYSTEMSOURCE", 0x1246);
        map.put("TWEI_IMAGEMERGED", 0x1247);
        map.put("TWEI_MAGDATALENGTH", 0x1248);
        map.put("TWEI_PAPERCOUNT", 0x1249);
        map.put("TWEI_PRINTERTEXT", 0x124A);

        map.put("TWEJ_NONE", 0x0000);
        map.put("TWEJ_MIDSEPARATOR", 0x0001);
        map.put("TWEJ_PATCH1", 0x0002);
        map.put("TWEJ_PATCH2", 0x0003);
        map.put("TWEJ_PATCH3", 0x0004);
        map.put("TWEJ_PATCH4", 0x0005);
        map.put("TWEJ_PATCH6", 0x0006);
        map.put("TWEJ_PATCHT", 0x0007);
        mapTweiNameToCode = map;
        return mapTweiNameToCode;
    }

    private static Map<String, Integer> mapTwrcNameToCode;

    private static Map<String, Integer> getMapTwrcNameToCode() {
        if (mapTwrcNameToCode != null) {
            return mapTwrcNameToCode;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("TWRC_CUSTOMBASE", 0x8000);

        map.put("TWRC_SUCCESS", 0);
        map.put("TWRC_FAILURE", 1);
        map.put("TWRC_CHECKSTATUS", 2);
        map.put("TWRC_CANCEL", 3);
        map.put("TWRC_DSEVENT", 4);
        map.put("TWRC_NOTDSEVENT", 5);
        map.put("TWRC_XFERDONE", 6);
        map.put("TWRC_ENDOFLIST", 7);
        map.put("TWRC_INFONOTSUPPORTED", 8);
        map.put("TWRC_DATANOTAVAILABLE", 9);
        map.put("TWRC_BUSY", 10);
        map.put("TWRC_SCANNERLOCKED", 11);
        mapTwrcNameToCode = map;
        return mapTwrcNameToCode;
    }

    private static Map<Integer, String> mapTwrcCodeToName;

    private static Map<Integer, String> getMapTwrcCodeToName() {
        if (mapTwrcCodeToName != null) {
            return mapTwrcCodeToName;
        }

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0x8000, "TWRC_CUSTOMBASE");

        map.put(0, "TWRC_SUCCESS");
        map.put(1, "TWRC_FAILURE");
        map.put(2, "TWRC_CHECKSTATUS");
        map.put(3, "TWRC_CANCEL");
        map.put(4, "TWRC_DSEVENT");
        map.put(5, "TWRC_NOTDSEVENT");
        map.put(6, "TWRC_XFERDONE");
        map.put(7, "TWRC_ENDOFLIST");
        map.put(8, "TWRC_INFONOTSUPPORTED");
        map.put(9, "TWRC_DATANOTAVAILABLE");
        map.put(10, "TWRC_BUSY");
        map.put(11, "TWRC_SCANNERLOCKED");
        mapTwrcCodeToName = map;
        return mapTwrcCodeToName;
    }


    private static Map<String, Integer> mapTwccNameToCode;

    private static Map<String, Integer> getMapTwccNameToCode() {
        if (mapTwccNameToCode != null) {
            return mapTwccNameToCode;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("TWCC_CUSTOMBASE", 0x8000);

        map.put("TWCC_SUCCESS", 0);
        map.put("TWCC_BUMMER", 1);
        map.put("TWCC_LOWMEMORY", 2);
        map.put("TWCC_NODS", 3);
        map.put("TWCC_MAXCONNECTIONS", 4);
        map.put("TWCC_OPERATIONERROR", 5);
        map.put("TWCC_BADCAP", 6);
        map.put("TWCC_BADPROTOCOL", 9);
        map.put("TWCC_BADVALUE", 10);
        map.put("TWCC_SEQERROR", 11);
        map.put("TWCC_BADDEST", 12);
        map.put("TWCC_CAPUNSUPPORTED", 13);
        map.put("TWCC_CAPBADOPERATION", 14);
        map.put("TWCC_CAPSEQERROR", 15);
        map.put("TWCC_DENIED", 16);
        map.put("TWCC_FILEEXISTS", 17);
        map.put("TWCC_FILENOTFOUND", 18);
        map.put("TWCC_NOTEMPTY", 19);
        map.put("TWCC_PAPERJAM", 20);
        map.put("TWCC_PAPERDOUBLEFEED", 21);
        map.put("TWCC_FILEWRITEERROR", 22);
        map.put("TWCC_CHECKDEVICEONLINE", 23);
        map.put("TWCC_INTERLOCK", 24);
        map.put("TWCC_DAMAGEDCORNER", 25);
        map.put("TWCC_FOCUSERROR", 26);
        map.put("TWCC_DOCTOOLIGHT", 27);
        map.put("TWCC_DOCTOODARK", 28);
        map.put("TWCC_NOMEDIA", 29);
        mapTwccNameToCode = map;
        return mapTwccNameToCode;
    }

    private static Map<Integer, String> mapTwccCodeToName;

    private static Map<Integer, String> getMapTwccCodeToName() {
        if (mapTwccCodeToName != null) {
            return mapTwccCodeToName;
        }

        Map<Integer, String> map = new HashMap<Integer, String>();
        map.put(0x8000, "TWCC_CUSTOMBASE");

        map.put(0, "TWCC_SUCCESS");
        map.put(1, "TWCC_BUMMER");
        map.put(2, "TWCC_LOWMEMORY");
        map.put(3, "TWCC_NODS");
        map.put(4, "TWCC_MAXCONNECTIONS");
        map.put(5, "TWCC_OPERATIONERROR");
        map.put(6, "TWCC_BADCAP");
        map.put(9, "TWCC_BADPROTOCOL");
        map.put(10, "TWCC_BADVALUE");
        map.put(11, "TWCC_SEQERROR");
        map.put(12, "TWCC_BADDEST");
        map.put(13, "TWCC_CAPUNSUPPORTED");
        map.put(14, "TWCC_CAPBADOPERATION");
        map.put(15, "TWCC_CAPSEQERROR");
        map.put(16, "TWCC_DENIED");
        map.put(17, "TWCC_FILEEXISTS");
        map.put(18, "TWCC_FILENOTFOUND");
        map.put(19, "TWCC_NOTEMPTY");
        map.put(20, "TWCC_PAPERJAM");
        map.put(21, "TWCC_PAPERDOUBLEFEED");
        map.put(22, "TWCC_FILEWRITEERROR");
        map.put(23, "TWCC_CHECKDEVICEONLINE");
        map.put(24, "TWCC_INTERLOCK");
        map.put(25, "TWCC_DAMAGEDCORNER");
        map.put(26, "TWCC_FOCUSERROR");
        map.put(27, "TWCC_DOCTOOLIGHT");
        map.put(28, "TWCC_DOCTOODARK");
        map.put(29, "TWCC_NOMEDIA");
        mapTwccCodeToName = map;
        return mapTwccCodeToName;
    }

    private static Map<String, Integer> mapConstantNameToIntValue;

    private static Map<String, Integer> getMapConstantNameToIntValue() {
        if (mapConstantNameToIntValue != null) {
            return mapConstantNameToIntValue;
        }

        Map<String, Integer> map = new HashMap<String, Integer>();
        map.put("CAP_CUSTOMBASE", 0x8000); /* Base of custom capabilities */

    /* all data sources are REQUIRED to support these caps */
        map.put("CAP_XFERCOUNT", 0x0001);

    /* image data sources are REQUIRED to support these caps */
        map.put("ICAP_COMPRESSION", 0x0100);
        map.put("ICAP_PIXELTYPE", 0x0101);
        map.put("ICAP_UNITS", 0x0102);
        map.put("ICAP_XFERMECH", 0x0103);

    /* all data sources MAY support these caps */
        map.put("CAP_AUTHOR", 0x1000);
        map.put("CAP_CAPTION", 0x1001);
        map.put("CAP_FEEDERENABLED", 0x1002);
        map.put("CAP_FEEDERLOADED", 0x1003);
        map.put("CAP_TIMEDATE", 0x1004);
        map.put("CAP_SUPPORTEDCAPS", 0x1005);
        map.put("CAP_EXTENDEDCAPS", 0x1006);
        map.put("CAP_AUTOFEED", 0x1007);
        map.put("CAP_CLEARPAGE", 0x1008);
        map.put("CAP_FEEDPAGE", 0x1009);
        map.put("CAP_REWINDPAGE", 0x100a);
        map.put("CAP_INDICATORS", 0x100b);
        map.put("CAP_PAPERDETECTABLE", 0x100d);
        map.put("CAP_UICONTROLLABLE", 0x100e);
        map.put("CAP_DEVICEONLINE", 0x100f);
        map.put("CAP_AUTOSCAN", 0x1010);
        map.put("CAP_THUMBNAILSENABLED", 0x1011);
        map.put("CAP_DUPLEX", 0x1012);
        map.put("CAP_DUPLEXENABLED", 0x1013);
        map.put("CAP_ENABLEDSUIONLY", 0x1014);
        map.put("CAP_CUSTOMDSDATA", 0x1015);
        map.put("CAP_ENDORSER", 0x1016);
        map.put("CAP_JOBCONTROL", 0x1017);
        map.put("CAP_ALARMS", 0x1018);
        map.put("CAP_ALARMVOLUME", 0x1019);
        map.put("CAP_AUTOMATICCAPTURE", 0x101a);
        map.put("CAP_TIMEBEFOREFIRSTCAPTURE", 0x101b);
        map.put("CAP_TIMEBETWEENCAPTURES", 0x101c);
        map.put("CAP_CLEARBUFFERS", 0x101d);
        map.put("CAP_MAXBATCHBUFFERS", 0x101e);
        map.put("CAP_DEVICETIMEDATE", 0x101f);
        map.put("CAP_POWERSUPPLY", 0x1020);
        map.put("CAP_CAMERAPREVIEWUI", 0x1021);
        map.put("CAP_DEVICEEVENT", 0x1022);
        map.put("CAP_SERIALNUMBER", 0x1024);
        map.put("CAP_PRINTER", 0x1026);
        map.put("CAP_PRINTERENABLED", 0x1027);
        map.put("CAP_PRINTERINDEX", 0x1028);
        map.put("CAP_PRINTERMODE", 0x1029);
        map.put("CAP_PRINTERSTRING", 0x102a);
        map.put("CAP_PRINTERSUFFIX", 0x102b);
        map.put("CAP_LANGUAGE", 0x102c);
        map.put("CAP_FEEDERALIGNMENT", 0x102d);
        map.put("CAP_FEEDERORDER", 0x102e);
        map.put("CAP_REACQUIREALLOWED", 0x1030);
        map.put("CAP_BATTERYMINUTES", 0x1032);
        map.put("CAP_BATTERYPERCENTAGE", 0x1033);
        map.put("CAP_CAMERASIDE", 0x1034);
        map.put("CAP_SEGMENTED", 0x1035);
        map.put("CAP_CAMERAENABLED", 0x1036);
        map.put("CAP_CAMERAORDER", 0x1037);
        map.put("CAP_MICRENABLED", 0x1038);
        map.put("CAP_FEEDERPREP", 0x1039);
        map.put("CAP_FEEDERPOCKET", 0x103a);
        map.put("CAP_AUTOMATICSENSEMEDIUM", 0x103b);
        map.put("CAP_CUSTOMINTERFACEGUID", 0x103c);
        map.put("CAP_SUPPORTEDCAPSSEGMENTUNIQUE", 0x103d);
        map.put("CAP_SUPPORTEDDATS", 0x103e);
        map.put("CAP_DOUBLEFEEDDETECTION", 0x103f);
        map.put("CAP_DOUBLEFEEDDETECTIONLENGTH", 0x1040);
        map.put("CAP_DOUBLEFEEDDETECTIONSENSITIVITY", 0x1041);
        map.put("CAP_DOUBLEFEEDDETECTIONRESPONSE", 0x1042);
        map.put("CAP_PAPERHANDLING", 0x1043);
        map.put("CAP_INDICATORSMODE", 0x1044);
        map.put("CAP_PRINTERVERTICALOFFSET", 0x1045);
        map.put("CAP_POWERSAVETIME", 0x1046);
        map.put("CAP_PRINTERCHARROTATION", 0x1047);
        map.put("CAP_PRINTERFONTSTYLE", 0x1048);
        map.put("CAP_PRINTERINDEXLEADCHAR", 0x1049);
        map.put("CAP_PRINTERINDEXMAXVALUE", 0x104A);
        map.put("CAP_PRINTERINDEXNUMDIGITS", 0x104B);
        map.put("CAP_PRINTERINDEXSTEP", 0x104C);
        map.put("CAP_PRINTERINDEXTRIGGER", 0x104D);
        map.put("CAP_PRINTERSTRINGPREVIEW", 0x104E);

    /* image data sources MAY support these caps */
        map.put("ICAP_AUTOBRIGHT", 0x1100);
        map.put("ICAP_BRIGHTNESS", 0x1101);
        map.put("ICAP_CONTRAST", 0x1103);
        map.put("ICAP_CUSTHALFTONE", 0x1104);
        map.put("ICAP_EXPOSURETIME", 0x1105);
        map.put("ICAP_FILTER", 0x1106);
        map.put("ICAP_FLASHUSED", 0x1107);
        map.put("ICAP_GAMMA", 0x1108);
        map.put("ICAP_HALFTONES", 0x1109);
        map.put("ICAP_HIGHLIGHT", 0x110a);
        map.put("ICAP_IMAGEFILEFORMAT", 0x110c);
        map.put("ICAP_LAMPSTATE", 0x110d);
        map.put("ICAP_LIGHTSOURCE", 0x110e);
        map.put("ICAP_ORIENTATION", 0x1110);
        map.put("ICAP_PHYSICALWIDTH", 0x1111);
        map.put("ICAP_PHYSICALHEIGHT", 0x1112);
        map.put("ICAP_SHADOW", 0x1113);
        map.put("ICAP_FRAMES", 0x1114);
        map.put("ICAP_XNATIVERESOLUTION", 0x1116);
        map.put("ICAP_YNATIVERESOLUTION", 0x1117);
        map.put("ICAP_XRESOLUTION", 0x1118);
        map.put("ICAP_YRESOLUTION", 0x1119);
        map.put("ICAP_MAXFRAMES", 0x111a);
        map.put("ICAP_TILES", 0x111b);
        map.put("ICAP_BITORDER", 0x111c);
        map.put("ICAP_CCITTKFACTOR", 0x111d);
        map.put("ICAP_LIGHTPATH", 0x111e);
        map.put("ICAP_PIXELFLAVOR", 0x111f);
        map.put("ICAP_PLANARCHUNKY", 0x1120);
        map.put("ICAP_ROTATION", 0x1121);
        map.put("ICAP_SUPPORTEDSIZES", 0x1122);
        map.put("ICAP_THRESHOLD", 0x1123);
        map.put("ICAP_XSCALING", 0x1124);
        map.put("ICAP_YSCALING", 0x1125);
        map.put("ICAP_BITORDERCODES", 0x1126);
        map.put("ICAP_PIXELFLAVORCODES", 0x1127);
        map.put("ICAP_JPEGPIXELTYPE", 0x1128);
        map.put("ICAP_TIMEFILL", 0x112a);
        map.put("ICAP_BITDEPTH", 0x112b);
        map.put("ICAP_BITDEPTHREDUCTION", 0x112c);
        map.put("ICAP_UNDEFINEDIMAGESIZE", 0x112d);
        map.put("ICAP_IMAGEDATASET", 0x112e);
        map.put("ICAP_EXTIMAGEINFO", 0x112f);
        map.put("ICAP_MINIMUMHEIGHT", 0x1130);
        map.put("ICAP_MINIMUMWIDTH", 0x1131);
        map.put("ICAP_AUTODISCARDBLANKPAGES", 0x1134);
        map.put("ICAP_FLIPROTATION", 0x1136);
        map.put("ICAP_BARCODEDETECTIONENABLED", 0x1137);
        map.put("ICAP_SUPPORTEDBARCODETYPES", 0x1138);
        map.put("ICAP_BARCODEMAXSEARCHPRIORITIES", 0x1139);
        map.put("ICAP_BARCODESEARCHPRIORITIES", 0x113a);
        map.put("ICAP_BARCODESEARCHMODE", 0x113b);
        map.put("ICAP_BARCODEMAXRETRIES", 0x113c);
        map.put("ICAP_BARCODETIMEOUT", 0x113d);
        map.put("ICAP_ZOOMFACTOR", 0x113e);
        map.put("ICAP_PATCHCODEDETECTIONENABLED", 0x113f);
        map.put("ICAP_SUPPORTEDPATCHCODETYPES", 0x1140);
        map.put("ICAP_PATCHCODEMAXSEARCHPRIORITIES", 0x1141);
        map.put("ICAP_PATCHCODESEARCHPRIORITIES", 0x1142);
        map.put("ICAP_PATCHCODESEARCHMODE", 0x1143);
        map.put("ICAP_PATCHCODEMAXRETRIES", 0x1144);
        map.put("ICAP_PATCHCODETIMEOUT", 0x1145);
        map.put("ICAP_FLASHUSED2", 0x1146);
        map.put("ICAP_IMAGEFILTER", 0x1147);
        map.put("ICAP_NOISEFILTER", 0x1148);
        map.put("ICAP_OVERSCAN", 0x1149);
        map.put("ICAP_AUTOMATICBORDERDETECTION", 0x1150);
        map.put("ICAP_AUTOMATICDESKEW", 0x1151);
        map.put("ICAP_AUTOMATICROTATE", 0x1152);
        map.put("ICAP_JPEGQUALITY", 0x1153);
        map.put("ICAP_FEEDERTYPE", 0x1154);
        map.put("ICAP_ICCPROFILE", 0x1155);
        map.put("ICAP_AUTOSIZE", 0x1156);
        map.put("ICAP_AUTOMATICCROPUSESFRAME", 0x1157);
        map.put("ICAP_AUTOMATICLENGTHDETECTION", 0x1158);
        map.put("ICAP_AUTOMATICCOLORENABLED", 0x1159);
        map.put("ICAP_AUTOMATICCOLORNONCOLORPIXELTYPE", 0x115a);
        map.put("ICAP_COLORMANAGEMENTENABLED", 0x115b);
        map.put("ICAP_IMAGEMERGE", 0x115c);
        map.put("ICAP_IMAGEMERGEHEIGHTTHRESHOLD", 0x115d);
        map.put("ICAP_SUPPORTEDEXTIMAGEINFO", 0x115e);
        map.put("ICAP_FILMTYPE", 0x115f);
        map.put("ICAP_MIRROR", 0x1160);
        map.put("ICAP_JPEGSUBSAMPLING", 0x1161);

        map.put("CAP_SUPPORTEDCAPSEXT", 0x100c);
        map.put("CAP_PAGEMULTIPLEACQUIRE", 0x1023);
        map.put("CAP_PAPERBINDING", 0x102f);
        map.put("CAP_PASSTHRU", 0x1031);
        map.put("CAP_POWERDOWNTIME", 0x1034);

        // TWEI
        map.put("TWEI_BARCODEX", 0x1200);
        map.put("TWEI_BARCODEY", 0x1201);
        map.put("TWEI_BARCODETEXT", 0x1202);
        map.put("TWEI_BARCODETYPE", 0x1203);
        map.put("TWEI_DESHADETOP", 0x1204);
        map.put("TWEI_DESHADELEFT", 0x1205);
        map.put("TWEI_DESHADEHEIGHT", 0x1206);
        map.put("TWEI_DESHADEWIDTH", 0x1207);
        map.put("TWEI_DESHADESIZE", 0x1208);
        map.put("TWEI_SPECKLESREMOVED", 0x1209);
        map.put("TWEI_HORZLINEXCOORD", 0x120A);
        map.put("TWEI_HORZLINEYCOORD", 0x120B);
        map.put("TWEI_HORZLINELENGTH", 0x120C);
        map.put("TWEI_HORZLINETHICKNESS", 0x120D);
        map.put("TWEI_VERTLINEXCOORD", 0x120E);
        map.put("TWEI_VERTLINEYCOORD", 0x120F);
        map.put("TWEI_VERTLINELENGTH", 0x1210);
        map.put("TWEI_VERTLINETHICKNESS", 0x1211);
        map.put("TWEI_PATCHCODE", 0x1212);
        map.put("TWEI_ENDORSEDTEXT", 0x1213);
        map.put("TWEI_FORMCONFIDENCE", 0x1214);
        map.put("TWEI_FORMTEMPLATEMATCH", 0x1215);
        map.put("TWEI_FORMTEMPLATEPAGEMATCH", 0x1216);
        map.put("TWEI_FORMHORZDOCOFFSET", 0x1217);
        map.put("TWEI_FORMVERTDOCOFFSET", 0x1218);
        map.put("TWEI_BARCODECOUNT", 0x1219);
        map.put("TWEI_BARCODECONFIDENCE", 0x121A);
        map.put("TWEI_BARCODEROTATION", 0x121B);
        map.put("TWEI_BARCODETEXTLENGTH", 0x121C);
        map.put("TWEI_DESHADECOUNT", 0x121D);
        map.put("TWEI_DESHADEBLACKCOUNTOLD", 0x121E);
        map.put("TWEI_DESHADEBLACKCOUNTNEW", 0x121F);
        map.put("TWEI_DESHADEBLACKRLMIN", 0x1220);
        map.put("TWEI_DESHADEBLACKRLMAX", 0x1221);
        map.put("TWEI_DESHADEWHITECOUNTOLD", 0x1222);
        map.put("TWEI_DESHADEWHITECOUNTNEW", 0x1223);
        map.put("TWEI_DESHADEWHITERLMIN", 0x1224);
        map.put("TWEI_DESHADEWHITERLAVE", 0x1225);
        map.put("TWEI_DESHADEWHITERLMAX", 0x1226);
        map.put("TWEI_BLACKSPECKLESREMOVED", 0x1227);
        map.put("TWEI_WHITESPECKLESREMOVED", 0x1228);
        map.put("TWEI_HORZLINECOUNT", 0x1229);
        map.put("TWEI_VERTLINECOUNT", 0x122A);
        map.put("TWEI_DESKEWSTATUS", 0x122B);
        map.put("TWEI_SKEWORIGINALANGLE", 0x122C);
        map.put("TWEI_SKEWFINALANGLE", 0x122D);
        map.put("TWEI_SKEWCONFIDENCE", 0x122E);
        map.put("TWEI_SKEWWINDOWX1", 0x122F);
        map.put("TWEI_SKEWWINDOWY1", 0x1230);
        map.put("TWEI_SKEWWINDOWX2", 0x1231);
        map.put("TWEI_SKEWWINDOWY2", 0x1232);
        map.put("TWEI_SKEWWINDOWX3", 0x1233);
        map.put("TWEI_SKEWWINDOWY3", 0x1234);
        map.put("TWEI_SKEWWINDOWX4", 0x1235);
        map.put("TWEI_SKEWWINDOWY4", 0x1236);
        map.put("TWEI_BOOKNAME", 0x1238);
        map.put("TWEI_CHAPTERNUMBER", 0x1239);
        map.put("TWEI_DOCUMENTNUMBER", 0x123A);
        map.put("TWEI_PAGENUMBER", 0x123B);
        map.put("TWEI_CAMERA", 0x123C);
        map.put("TWEI_FRAMENUMBER", 0x123D);
        map.put("TWEI_FRAME", 0x123E);
        map.put("TWEI_PIXELFLAVOR", 0x123F);
        map.put("TWEI_ICCPROFILE", 0x1240);
        map.put("TWEI_LASTSEGMENT", 0x1241);
        map.put("TWEI_SEGMENTNUMBER", 0x1242);
        map.put("TWEI_MAGDATA", 0x1243);
        map.put("TWEI_MAGTYPE", 0x1244);
        map.put("TWEI_PAGESIDE", 0x1245);
        map.put("TWEI_FILESYSTEMSOURCE", 0x1246);
        map.put("TWEI_IMAGEMERGED", 0x1247);
        map.put("TWEI_MAGDATALENGTH", 0x1248);
        map.put("TWEI_PAPERCOUNT", 0x1249);
        map.put("TWEI_PRINTERTEXT", 0x124A);

        map.put("TWEJ_NONE", 0x0000);
        map.put("TWEJ_MIDSEPARATOR", 0x0001);
        map.put("TWEJ_PATCH1", 0x0002);
        map.put("TWEJ_PATCH2", 0x0003);
        map.put("TWEJ_PATCH3", 0x0004);
        map.put("TWEJ_PATCH4", 0x0005);
        map.put("TWEJ_PATCH6", 0x0006);
        map.put("TWEJ_PATCHT", 0x0007);


        /***************************************************************************
         *            Return Codes and Condition Codes section                     *
         ***************************************************************************/

        map.put("TWRC_CUSTOMBASE", 0x8000);

        map.put("TWRC_SUCCESS", 0);
        map.put("TWRC_FAILURE", 1);
        map.put("TWRC_CHECKSTATUS", 2);
        map.put("TWRC_CANCEL", 3);
        map.put("TWRC_DSEVENT", 4);
        map.put("TWRC_NOTDSEVENT", 5);
        map.put("TWRC_XFERDONE", 6);
        map.put("TWRC_ENDOFLIST", 7);
        map.put("TWRC_INFONOTSUPPORTED", 8);
        map.put("TWRC_DATANOTAVAILABLE", 9);
        map.put("TWRC_BUSY", 10);
        map.put("TWRC_SCANNERLOCKED", 11);

    /* Condition Codes: Application gets these by doing DG_CONTROL DAT_STATUS MSG_GET.  */
        map.put("TWCC_CUSTOMBASE", 0x8000);

        map.put("TWCC_SUCCESS", 0);
        map.put("TWCC_BUMMER", 1);
        map.put("TWCC_LOWMEMORY", 2);
        map.put("TWCC_NODS", 3);
        map.put("TWCC_MAXCONNECTIONS", 4);
        map.put("TWCC_OPERATIONERROR", 5);
        map.put("TWCC_BADCAP", 6);
        map.put("TWCC_BADPROTOCOL", 9);
        map.put("TWCC_BADVALUE", 10);
        map.put("TWCC_SEQERROR", 11);
        map.put("TWCC_BADDEST", 12);
        map.put("TWCC_CAPUNSUPPORTED", 13);
        map.put("TWCC_CAPBADOPERATION", 14);
        map.put("TWCC_CAPSEQERROR", 15);
        map.put("TWCC_DENIED", 16);
        map.put("TWCC_FILEEXISTS", 17);
        map.put("TWCC_FILENOTFOUND", 18);
        map.put("TWCC_NOTEMPTY", 19);
        map.put("TWCC_PAPERJAM", 20);
        map.put("TWCC_PAPERDOUBLEFEED", 21);
        map.put("TWCC_FILEWRITEERROR", 22);
        map.put("TWCC_CHECKDEVICEONLINE", 23);
        map.put("TWCC_INTERLOCK", 24);
        map.put("TWCC_DAMAGEDCORNER", 25);
        map.put("TWCC_FOCUSERROR", 26);
        map.put("TWCC_DOCTOOLIGHT", 27);
        map.put("TWCC_DOCTOODARK", 28);
        map.put("TWCC_NOMEDIA", 29);

    /* CAP_CLEARBUFFERS values */
        map.put("TWCB_AUTO", 0);
        map.put("TWCB_CLEAR", 1);
        map.put("TWCB_NOCLEAR", 2);

    /* TW_PASSTHRU.Direction values. */
        map.put("TWDR_GET", 1);
        map.put("TWDR_SET", 2);

    /* TWEI_DESKEWSTATUS values. */
        map.put("TWDSK_SUCCESS", 0);
        map.put("TWDSK_REPORTONLY", 1);
        map.put("TWDSK_FAIL", 2);
        map.put("TWDSK_DISABLED", 3);

    /* CAP_DUPLEX values */
        map.put("TWDX_NONE", 0);
        map.put("TWDX_1PASSDUPLEX", 1);
        map.put("TWDX_2PASSDUPLEX", 2);

    /* CAP_FEEDERALIGNMENT values */
        map.put("TWFA_NONE", 0);
        map.put("TWFA_LEFT", 1);
        map.put("TWFA_CENTER", 2);
        map.put("TWFA_RIGHT", 3);

    /* ICAP_FEEDERTYPE values*/
        map.put("TWFE_GENERAL", 0);
        map.put("TWFE_PHOTO", 1);

    /* ICAP_IMAGEFILEFORMAT values */
        map.put("TWFF_TIFF", 0);
        map.put("TWFF_PICT", 1);
        map.put("TWFF_BMP", 2);
        map.put("TWFF_XBM", 3);
        map.put("TWFF_JFIF", 4);
        map.put("TWFF_FPX", 5);
        map.put("TWFF_TIFFMULTI", 6);
        map.put("TWFF_PNG", 7);
        map.put("TWFF_SPIFF", 8);
        map.put("TWFF_EXIF", 9);
        map.put("TWFF_PDF", 10);
        map.put("TWFF_JP2", 11);
        map.put("TWFF_JPX", 13);
        map.put("TWFF_DEJAVU", 14);
        map.put("TWFF_PDFA", 15);
        map.put("TWFF_PDFA2", 16);

    /* ICAP_FLASHUSED2 values */
        map.put("TWFL_NONE", 0);
        map.put("TWFL_OFF", 1);
        map.put("TWFL_ON", 2);
        map.put("TWFL_AUTO", 3);
        map.put("TWFL_REDEYE", 4);

    /* CAP_FEEDERORDER values */
        map.put("TWFO_FIRSTPAGEFIRST", 0);
        map.put("TWFO_LASTPAGEFIRST", 1);

    /* CAP_FEEDERPOCKET values*/
        map.put("TWFP_POCKETERROR", 0);
        map.put("TWFP_POCKET1", 1);
        map.put("TWFP_POCKET2", 2);
        map.put("TWFP_POCKET3", 3);
        map.put("TWFP_POCKET4", 4);
        map.put("TWFP_POCKET5", 5);
        map.put("TWFP_POCKET6", 6);
        map.put("TWFP_POCKET7", 7);
        map.put("TWFP_POCKET8", 8);
        map.put("TWFP_POCKET9", 9);
        map.put("TWFP_POCKET10", 10);
        map.put("TWFP_POCKET11", 11);
        map.put("TWFP_POCKET12", 12);
        map.put("TWFP_POCKET13", 13);
        map.put("TWFP_POCKET14", 14);
        map.put("TWFP_POCKET15", 15);
        map.put("TWFP_POCKET16", 16);

    /* ICAP_FLIPROTATION values */
        map.put("TWFR_BOOK", 0);
        map.put("TWFR_FANFOLD", 1);

    /* ICAP_FILTER values */
        map.put("TWFT_RED", 0);
        map.put("TWFT_GREEN", 1);
        map.put("TWFT_BLUE", 2);
        map.put("TWFT_NONE", 3);
        map.put("TWFT_WHITE", 4);
        map.put("TWFT_CYAN", 5);
        map.put("TWFT_MAGENTA", 6);
        map.put("TWFT_YELLOW", 7);
        map.put("TWFT_BLACK", 8);

    /* ICAP_LIGHTPATH values */
        map.put("TWLP_REFLECTIVE", 0);
        map.put("TWLP_TRANSMISSIVE", 1);

    /* ICAP_LIGHTSOURCE values */
        map.put("TWLS_RED", 0);
        map.put("TWLS_GREEN", 1);
        map.put("TWLS_BLUE", 2);
        map.put("TWLS_NONE", 3);
        map.put("TWLS_WHITE", 4);
        map.put("TWLS_UV", 5);
        map.put("TWLS_IR", 6);

    /* ICAP_NOISEFILTER values */
        map.put("TWNF_NONE", 0);
        map.put("TWNF_AUTO", 1);
        map.put("TWNF_LONEPIXEL", 2);
        map.put("TWNF_MAJORITYRULE", 3);

    /* ICAP_ORIENTATION values */
        map.put("TWOR_ROT0", 0);
        map.put("TWOR_ROT90", 1);
        map.put("TWOR_ROT180", 2);
        map.put("TWOR_ROT270", 3);
        map.put("TWOR_PORTRAIT", TWOR_ROT0);
        map.put("TWOR_LANDSCAPE", TWOR_ROT270);
        map.put("TWOR_AUTO", 4);
        map.put("TWOR_AUTOTEXT", 5);
        map.put("TWOR_AUTOPICTURE", 6);

    /* ICAP_OVERSCAN values */
        map.put("TWOV_NONE", 0);
        map.put("TWOV_AUTO", 1);
        map.put("TWOV_TOPBOTTOM", 2);
        map.put("TWOV_LEFTRIGHT", 3);
        map.put("TWOV_ALL", 4);

    /* Palette types for TW_PALETTE8 */
        map.put("TWPA_RGB", 0);
        map.put("TWPA_GRAY", 1);
        map.put("TWPA_CMY", 2);

    /* ICAP_PLANARCHUNKY values */
        map.put("TWPC_CHUNKY", 0);
        map.put("TWPC_PLANAR", 1);

    /* TWEI_PATCHCODE values*/
        map.put("TWPCH_PATCH1", 0);
        map.put("TWPCH_PATCH2", 1);
        map.put("TWPCH_PATCH3", 2);
        map.put("TWPCH_PATCH4", 3);
        map.put("TWPCH_PATCH6", 4);
        map.put("TWPCH_PATCHT", 5);

    /* ICAP_PIXELFLAVOR values */
        map.put("TWPF_CHOCOLATE", 0);
        map.put("TWPF_VANILLA", 1);

    /* CAP_PRINTERMODE values */
        map.put("TWPM_SINGLESTRING", 0);
        map.put("TWPM_MULTISTRING", 1);
        map.put("TWPM_COMPOUNDSTRING", 2);

    /* CAP_PRINTER values */
        map.put("TWPR_IMPRINTERTOPBEFORE", 0);
        map.put("TWPR_IMPRINTERTOPAFTER", 1);
        map.put("TWPR_IMPRINTERBOTTOMBEFORE", 2);
        map.put("TWPR_IMPRINTERBOTTOMAFTER", 3);
        map.put("TWPR_ENDORSERTOPBEFORE", 4);
        map.put("TWPR_ENDORSERTOPAFTER", 5);
        map.put("TWPR_ENDORSERBOTTOMBEFORE", 6);
        map.put("TWPR_ENDORSERBOTTOMAFTER", 7);

    /* CAP_PRINTERFONTSTYLE Added 2.3 */
        map.put("TWPF_NORMAL", 0);
        map.put("TWPF_BOLD", 1);
        map.put("TWPF_ITALIC", 2);
        map.put("TWPF_LARGESIZE", 3);
        map.put("TWPF_SMALLSIZE", 4);

    /* CAP_PRINTERINDEXTRIGGER Added 2.3 */
        map.put("TWCT_PAGE", 0);
        map.put("TWCT_PATCH1", 1);
        map.put("TWCT_PATCH2", 2);
        map.put("TWCT_PATCH3", 3);
        map.put("TWCT_PATCH4", 4);
        map.put("TWCT_PATCHT", 5);
        map.put("TWCT_PATCH6", 6);

    /* CAP_POWERSUPPLY values */
        map.put("TWPS_EXTERNAL", 0);
        map.put("TWPS_BATTERY", 1);

    /* ICAP_PIXELTYPE values (PT_ means Pixel Type) */
        map.put("TWPT_BW", 0);
        map.put("TWPT_GRAY", 1);
        map.put("TWPT_RGB", 2);
        map.put("TWPT_PALETTE", 3);
        map.put("TWPT_CMY", 4);
        map.put("TWPT_CMYK", 5);
        map.put("TWPT_YUV", 6);
        map.put("TWPT_YUVK", 7);
        map.put("TWPT_CIEXYZ", 8);
        map.put("TWPT_LAB", 9);
        map.put("TWPT_SRGB", 10);
        map.put("TWPT_SCRGB", 11);
        map.put("TWPT_INFRARED", 16);

    /* CAP_SEGMENTED values */
        map.put("TWSG_NONE", 0);
        map.put("TWSG_AUTO", 1);
        map.put("TWSG_MANUAL", 2);

    /* ICAP_FILMTYPE values */
        map.put("TWFM_POSITIVE", 0);
        map.put("TWFM_NEGATIVE", 1);

    /* CAP_DOUBLEFEEDDETECTION */
        map.put("TWDF_ULTRASONIC", 0);
        map.put("TWDF_BYLENGTH", 1);
        map.put("TWDF_INFRARED", 2);

    /* CAP_DOUBLEFEEDDETECTIONSENSITIVITY */
        map.put("TWUS_LOW", 0);
        map.put("TWUS_MEDIUM", 1);
        map.put("TWUS_HIGH", 2);

    /* CAP_DOUBLEFEEDDETECTIONRESPONSE */
        map.put("TWDP_STOP", 0);
        map.put("TWDP_STOPANDWAIT", 1);
        map.put("TWDP_SOUND", 2);
        map.put("TWDP_DONOTIMPRINT", 3);

    /* ICAP_MIRROR values */
        map.put("TWMR_NONE", 0);
        map.put("TWMR_VERTICAL", 1);
        map.put("TWMR_HORIZONTAL", 2);

    /* CAP_PAPERHANDLING values */
        map.put("TWPH_NORMAL", 0);
        map.put("TWPH_FRAGILE", 1);
        map.put("TWPH_THICK", 2);
        map.put("TWPH_TRIFOLD", 3);
        map.put("TWPH_PHOTOGRAPH", 4);

    /* CAP_INDICATORSMODE values */
        map.put("TWCI_INFO", 0);
        map.put("TWCI_WARNING", 1);
        map.put("TWCI_ERROR", 2);
        map.put("TWCI_WARMUP", 3);

    /* ICAP_SUPPORTEDSIZES values (SS_ means Supported Sizes) */
        map.put("TWSS_NONE", 0);
        map.put("TWSS_A4", 1);
        map.put("TWSS_JISB5", 2);
        map.put("TWSS_USLETTER", 3);
        map.put("TWSS_USLEGAL", 4);
        map.put("TWSS_A5", 5);
        map.put("TWSS_ISOB4", 6);
        map.put("TWSS_ISOB6", 7);
        map.put("TWSS_USLEDGER", 9);
        map.put("TWSS_USEXECUTIVE", 10);
        map.put("TWSS_A3", 11);
        map.put("TWSS_ISOB3", 12);
        map.put("TWSS_A6", 13);
        map.put("TWSS_C4", 14);
        map.put("TWSS_C5", 15);
        map.put("TWSS_C6", 16);
        map.put("TWSS_4A0", 17);
        map.put("TWSS_2A0", 18);
        map.put("TWSS_A0", 19);
        map.put("TWSS_A1", 20);
        map.put("TWSS_A2", 21);
        map.put("TWSS_A7", 22);
        map.put("TWSS_A8", 23);
        map.put("TWSS_A9", 24);
        map.put("TWSS_A10", 25);
        map.put("TWSS_ISOB0", 26);
        map.put("TWSS_ISOB1", 27);
        map.put("TWSS_ISOB2", 28);
        map.put("TWSS_ISOB5", 29);
        map.put("TWSS_ISOB7", 30);
        map.put("TWSS_ISOB8", 31);
        map.put("TWSS_ISOB9", 32);
        map.put("TWSS_ISOB10", 33);
        map.put("TWSS_JISB0", 34);
        map.put("TWSS_JISB1", 35);
        map.put("TWSS_JISB2", 36);
        map.put("TWSS_JISB3", 37);
        map.put("TWSS_JISB4", 38);
        map.put("TWSS_JISB6", 39);
        map.put("TWSS_JISB7", 40);
        map.put("TWSS_JISB8", 41);
        map.put("TWSS_JISB9", 42);
        map.put("TWSS_JISB10", 43);
        map.put("TWSS_C0", 44);
        map.put("TWSS_C1", 45);
        map.put("TWSS_C2", 46);
        map.put("TWSS_C3", 47);
        map.put("TWSS_C7", 48);
        map.put("TWSS_C8", 49);
        map.put("TWSS_C9", 50);
        map.put("TWSS_C10", 51);
        map.put("TWSS_USSTATEMENT", 52);
        map.put("TWSS_BUSINESSCARD", 53);
        map.put("TWSS_MAXSIZE", 54);

    /* ICAP_XFERMECH values (SX_ means Setup XFer) */
        map.put("TWSX_NATIVE", 0);
        map.put("TWSX_FILE", 1);
        map.put("TWSX_MEMORY", 2);
        map.put("TWSX_MEMFILE", 4);

    /* ICAP_UNITS values (UN_ means UNits) */
        map.put("TWUN_INCHES", 0);
        map.put("TWUN_CENTIMETERS", 1);
        map.put("TWUN_PICAS", 2);
        map.put("TWUN_POINTS", 3);
        map.put("TWUN_TWIPS", 4);
        map.put("TWUN_PIXELS", 5);
        map.put("TWUN_MILLIMETERS", 6);

        /****************************************************************************
         * Country Constants                                                        *
         ****************************************************************************/

        map.put("TWCY_AFGHANISTAN", 1001);
        map.put("TWCY_ALGERIA", 213);
        map.put("TWCY_AMERICANSAMOA", 684);
        map.put("TWCY_ANDORRA", 033);
        map.put("TWCY_ANGOLA", 1002);
        map.put("TWCY_ANGUILLA", 8090);
        map.put("TWCY_ANTIGUA", 8091);
        map.put("TWCY_ARGENTINA", 54);
        map.put("TWCY_ARUBA", 297);
        map.put("TWCY_ASCENSIONI", 247);
        map.put("TWCY_AUSTRALIA", 61);
        map.put("TWCY_AUSTRIA", 43);
        map.put("TWCY_BAHAMAS", 8092);
        map.put("TWCY_BAHRAIN", 973);
        map.put("TWCY_BANGLADESH", 880);
        map.put("TWCY_BARBADOS", 8093);
        map.put("TWCY_BELGIUM", 32);
        map.put("TWCY_BELIZE", 501);
        map.put("TWCY_BENIN", 229);
        map.put("TWCY_BERMUDA", 8094);
        map.put("TWCY_BHUTAN", 1003);
        map.put("TWCY_BOLIVIA", 591);
        map.put("TWCY_BOTSWANA", 267);
        map.put("TWCY_BRITAIN", 6);
        map.put("TWCY_BRITVIRGINIS", 8095);
        map.put("TWCY_BRAZIL", 55);
        map.put("TWCY_BRUNEI", 673);
        map.put("TWCY_BULGARIA", 359);
        map.put("TWCY_BURKINAFASO", 1004);
        map.put("TWCY_BURMA", 1005);
        map.put("TWCY_BURUNDI", 1006);
        map.put("TWCY_CAMAROON", 237);
        map.put("TWCY_CANADA", 2);
        map.put("TWCY_CAPEVERDEIS", 238);
        map.put("TWCY_CAYMANIS", 8096);
        map.put("TWCY_CENTRALAFREP", 1007);
        map.put("TWCY_CHAD", 1008);
        map.put("TWCY_CHILE", 56);
        map.put("TWCY_CHINA", 86);
        map.put("TWCY_CHRISTMASIS", 1009);
        map.put("TWCY_COCOSIS", 1009);
        map.put("TWCY_COLOMBIA", 57);
        map.put("TWCY_COMOROS", 1010);
        map.put("TWCY_CONGO", 1011);
        map.put("TWCY_COOKIS", 1012);
        map.put("TWCY_COSTARICA", 506);
        map.put("TWCY_CUBA", 005);
        map.put("TWCY_CYPRUS", 357);
        map.put("TWCY_CZECHOSLOVAKIA", 42);
        map.put("TWCY_DENMARK", 45);
        map.put("TWCY_DJIBOUTI", 1013);
        map.put("TWCY_DOMINICA", 8097);
        map.put("TWCY_DOMINCANREP", 8098);
        map.put("TWCY_EASTERIS", 1014);
        map.put("TWCY_ECUADOR", 593);
        map.put("TWCY_EGYPT", 20);
        map.put("TWCY_ELSALVADOR", 503);
        map.put("TWCY_EQGUINEA", 1015);
        map.put("TWCY_ETHIOPIA", 251);
        map.put("TWCY_FALKLANDIS", 1016);
        map.put("TWCY_FAEROEIS", 298);
        map.put("TWCY_FIJIISLANDS", 679);
        map.put("TWCY_FINLAND", 358);
        map.put("TWCY_FRANCE", 33);
        map.put("TWCY_FRANTILLES", 596);
        map.put("TWCY_FRGUIANA", 594);
        map.put("TWCY_FRPOLYNEISA", 689);
        map.put("TWCY_FUTANAIS", 1043);
        map.put("TWCY_GABON", 241);
        map.put("TWCY_GAMBIA", 220);
        map.put("TWCY_GERMANY", 49);
        map.put("TWCY_GHANA", 233);
        map.put("TWCY_GIBRALTER", 350);
        map.put("TWCY_GREECE", 30);
        map.put("TWCY_GREENLAND", 299);
        map.put("TWCY_GRENADA", 8099);
        map.put("TWCY_GRENEDINES", 8015);
        map.put("TWCY_GUADELOUPE", 590);
        map.put("TWCY_GUAM", 671);
        map.put("TWCY_GUANTANAMOBAY", 5399);
        map.put("TWCY_GUATEMALA", 502);
        map.put("TWCY_GUINEA", 224);
        map.put("TWCY_GUINEABISSAU", 1017);
        map.put("TWCY_GUYANA", 592);
        map.put("TWCY_HAITI", 509);
        map.put("TWCY_HONDURAS", 504);
        map.put("TWCY_HONGKONG", 852);
        map.put("TWCY_HUNGARY", 36);
        map.put("TWCY_ICELAND", 354);
        map.put("TWCY_INDIA", 91);
        map.put("TWCY_INDONESIA", 62);
        map.put("TWCY_IRAN", 98);
        map.put("TWCY_IRAQ", 964);
        map.put("TWCY_IRELAND", 353);
        map.put("TWCY_ISRAEL", 972);
        map.put("TWCY_ITALY", 39);
        map.put("TWCY_IVORYCOAST", 225);
        map.put("TWCY_JAMAICA", 8010);
        map.put("TWCY_JAPAN", 81);
        map.put("TWCY_JORDAN", 962);
        map.put("TWCY_KENYA", 254);
        map.put("TWCY_KIRIBATI", 1018);
        map.put("TWCY_KOREA", 82);
        map.put("TWCY_KUWAIT", 965);
        map.put("TWCY_LAOS", 1019);
        map.put("TWCY_LEBANON", 1020);
        map.put("TWCY_LIBERIA", 231);
        map.put("TWCY_LIBYA", 218);
        map.put("TWCY_LIECHTENSTEIN", 41);
        map.put("TWCY_LUXENBOURG", 352);
        map.put("TWCY_MACAO", 853);
        map.put("TWCY_MADAGASCAR", 1021);
        map.put("TWCY_MALAWI", 265);
        map.put("TWCY_MALAYSIA", 60);
        map.put("TWCY_MALDIVES", 960);
        map.put("TWCY_MALI", 1022);
        map.put("TWCY_MALTA", 356);
        map.put("TWCY_MARSHALLIS", 692);
        map.put("TWCY_MAURITANIA", 1023);
        map.put("TWCY_MAURITIUS", 230);
        map.put("TWCY_MEXICO", 3);
        map.put("TWCY_MICRONESIA", 691);
        map.put("TWCY_MIQUELON", 508);
        map.put("TWCY_MONACO", 33);
        map.put("TWCY_MONGOLIA", 1024);
        map.put("TWCY_MONTSERRAT", 8011);
        map.put("TWCY_MOROCCO", 212);
        map.put("TWCY_MOZAMBIQUE", 1025);
        map.put("TWCY_NAMIBIA", 264);
        map.put("TWCY_NAURU", 1026);
        map.put("TWCY_NEPAL", 977);
        map.put("TWCY_NETHERLANDS", 31);
        map.put("TWCY_NETHANTILLES", 599);
        map.put("TWCY_NEVIS", 8012);
        map.put("TWCY_NEWCALEDONIA", 687);
        map.put("TWCY_NEWZEALAND", 64);
        map.put("TWCY_NICARAGUA", 505);
        map.put("TWCY_NIGER", 227);
        map.put("TWCY_NIGERIA", 234);
        map.put("TWCY_NIUE", 1027);
        map.put("TWCY_NORFOLKI", 1028);
        map.put("TWCY_NORWAY", 47);
        map.put("TWCY_OMAN", 968);
        map.put("TWCY_PAKISTAN", 92);
        map.put("TWCY_PALAU", 1029);
        map.put("TWCY_PANAMA", 507);
        map.put("TWCY_PARAGUAY", 595);
        map.put("TWCY_PERU", 51);
        map.put("TWCY_PHILLIPPINES", 63);
        map.put("TWCY_PITCAIRNIS", 1030);
        map.put("TWCY_PNEWGUINEA", 675);
        map.put("TWCY_POLAND", 48);
        map.put("TWCY_PORTUGAL", 351);
        map.put("TWCY_QATAR", 974);
        map.put("TWCY_REUNIONI", 1031);
        map.put("TWCY_ROMANIA", 40);
        map.put("TWCY_RWANDA", 250);
        map.put("TWCY_SAIPAN", 670);
        map.put("TWCY_SANMARINO", 39);
        map.put("TWCY_SAOTOME", 1033);
        map.put("TWCY_SAUDIARABIA", 966);
        map.put("TWCY_SENEGAL", 221);
        map.put("TWCY_SEYCHELLESIS", 1034);
        map.put("TWCY_SIERRALEONE", 1035);
        map.put("TWCY_SINGAPORE", 65);
        map.put("TWCY_SOLOMONIS", 1036);
        map.put("TWCY_SOMALI", 1037);
        map.put("TWCY_SOUTHAFRICA", 27);
        map.put("TWCY_SPAIN", 34);
        map.put("TWCY_SRILANKA", 94);
        map.put("TWCY_STHELENA", 1032);
        map.put("TWCY_STKITTS", 8013);
        map.put("TWCY_STLUCIA", 8014);
        map.put("TWCY_STPIERRE", 508);
        map.put("TWCY_STVINCENT", 8015);
        map.put("TWCY_SUDAN", 1038);
        map.put("TWCY_SURINAME", 597);
        map.put("TWCY_SWAZILAND", 268);
        map.put("TWCY_SWEDEN", 46);
        map.put("TWCY_SWITZERLAND", 41);
        map.put("TWCY_SYRIA", 1039);
        map.put("TWCY_TAIWAN", 886);
        map.put("TWCY_TANZANIA", 255);
        map.put("TWCY_THAILAND", 66);
        map.put("TWCY_TOBAGO", 8016);
        map.put("TWCY_TOGO", 228);
        map.put("TWCY_TONGAIS", 676);
        map.put("TWCY_TRINIDAD", 8016);
        map.put("TWCY_TUNISIA", 216);
        map.put("TWCY_TURKEY", 90);
        map.put("TWCY_TURKSCAICOS", 8017);
        map.put("TWCY_TUVALU", 1040);
        map.put("TWCY_UGANDA", 256);
        map.put("TWCY_USSR", 7);
        map.put("TWCY_UAEMIRATES", 971);
        map.put("TWCY_UNITEDKINGDOM", 44);
        map.put("TWCY_USA", 1);
        map.put("TWCY_URUGUAY", 598);
        map.put("TWCY_VANUATU", 1041);
        map.put("TWCY_VATICANCITY", 39);
        map.put("TWCY_VENEZUELA", 58);
        map.put("TWCY_WAKE", 1042);
        map.put("TWCY_WALLISIS", 1043);
        map.put("TWCY_WESTERNSAHARA", 1044);
        map.put("TWCY_WESTERNSAMOA", 1045);
        map.put("TWCY_YEMEN", 1046);
        map.put("TWCY_YUGOSLAVIA", 38);
        map.put("TWCY_ZAIRE", 243);
        map.put("TWCY_ZAMBIA", 260);
        map.put("TWCY_ZIMBABWE", 263);
        map.put("TWCY_ALBANIA", 355);
        map.put("TWCY_ARMENIA", 374);
        map.put("TWCY_AZERBAIJAN", 994);
        map.put("TWCY_BELARUS", 375);
        map.put("TWCY_BOSNIAHERZGO", 387);
        map.put("TWCY_CAMBODIA", 855);
        map.put("TWCY_CROATIA", 385);
        map.put("TWCY_CZECHREPUBLIC", 420);
        map.put("TWCY_DIEGOGARCIA", 246);
        map.put("TWCY_ERITREA", 291);
        map.put("TWCY_ESTONIA", 372);
        map.put("TWCY_GEORGIA", 995);
        map.put("TWCY_LATVIA", 371);
        map.put("TWCY_LESOTHO", 266);
        map.put("TWCY_LITHUANIA", 370);
        map.put("TWCY_MACEDONIA", 389);
        map.put("TWCY_MAYOTTEIS", 269);
        map.put("TWCY_MOLDOVA", 373);
        map.put("TWCY_MYANMAR", 95);
        map.put("TWCY_NORTHKOREA", 850);
        map.put("TWCY_PUERTORICO", 787);
        map.put("TWCY_RUSSIA", 7);
        map.put("TWCY_SERBIA", 381);
        map.put("TWCY_SLOVAKIA", 421);
        map.put("TWCY_SLOVENIA", 386);
        map.put("TWCY_SOUTHKOREA", 82);
        map.put("TWCY_UKRAINE", 380);
        map.put("TWCY_USVIRGINIS", 340);
        map.put("TWCY_VIETNAM", 84);

        /****************************************************************************
         * Language Constants                                                       *
         ****************************************************************************/
        map.put("TWLG_USERLOCALE", -1);
        map.put("TWLG_DAN", 0);
        map.put("TWLG_DUT", 1);
        map.put("TWLG_ENG", 2);
        map.put("TWLG_FCF", 3);
        map.put("TWLG_FIN", 4);
        map.put("TWLG_FRN", 5);
        map.put("TWLG_GER", 6);
        map.put("TWLG_ICE", 7);
        map.put("TWLG_ITN", 8);
        map.put("TWLG_NOR", 9);
        map.put("TWLG_POR", 10);
        map.put("TWLG_SPA", 11);
        map.put("TWLG_SWE", 12);
        map.put("TWLG_USA", 13);
        map.put("TWLG_AFRIKAANS", 14);
        map.put("TWLG_ALBANIA", 15);
        map.put("TWLG_ARABIC", 16);
        map.put("TWLG_ARABIC_ALGERIA", 17);
        map.put("TWLG_ARABIC_BAHRAIN", 18);
        map.put("TWLG_ARABIC_EGYPT", 19);
        map.put("TWLG_ARABIC_IRAQ", 20);
        map.put("TWLG_ARABIC_JORDAN", 21);
        map.put("TWLG_ARABIC_KUWAIT", 22);
        map.put("TWLG_ARABIC_LEBANON", 23);
        map.put("TWLG_ARABIC_LIBYA", 24);
        map.put("TWLG_ARABIC_MOROCCO", 25);
        map.put("TWLG_ARABIC_OMAN", 26);
        map.put("TWLG_ARABIC_QATAR", 27);
        map.put("TWLG_ARABIC_SAUDIARABIA", 28);
        map.put("TWLG_ARABIC_SYRIA", 29);
        map.put("TWLG_ARABIC_TUNISIA", 30);
        map.put("TWLG_ARABIC_UAE", 31);
        map.put("TWLG_ARABIC_YEMEN", 32);
        map.put("TWLG_BASQUE", 33);
        map.put("TWLG_BYELORUSSIAN", 34);
        map.put("TWLG_BULGARIAN", 35);
        map.put("TWLG_CATALAN", 36);
        map.put("TWLG_CHINESE", 37);
        map.put("TWLG_CHINESE_HONGKONG", 38);
        map.put("TWLG_CHINESE_PRC", 39);
        map.put("TWLG_CHINESE_SINGAPORE", 40);
        map.put("TWLG_CHINESE_SIMPLIFIED", 41);
        map.put("TWLG_CHINESE_TAIWAN", 42);
        map.put("TWLG_CHINESE_TRADITIONAL", 43);
        map.put("TWLG_CROATIA", 44);
        map.put("TWLG_CZECH", 45);
        map.put("TWLG_DANISH", TWLG_DAN);
        map.put("TWLG_DUTCH", TWLG_DUT);
        map.put("TWLG_DUTCH_BELGIAN", 46);
        map.put("TWLG_ENGLISH", TWLG_ENG);
        map.put("TWLG_ENGLISH_AUSTRALIAN", 47);
        map.put("TWLG_ENGLISH_CANADIAN", 48);
        map.put("TWLG_ENGLISH_IRELAND", 49);
        map.put("TWLG_ENGLISH_NEWZEALAND", 50);
        map.put("TWLG_ENGLISH_SOUTHAFRICA", 51);
        map.put("TWLG_ENGLISH_UK", 52);
        map.put("TWLG_ENGLISH_USA", TWLG_USA);
        map.put("TWLG_ESTONIAN", 53);
        map.put("TWLG_FAEROESE", 54);
        map.put("TWLG_FARSI", 55);
        map.put("TWLG_FINNISH", TWLG_FIN);
        map.put("TWLG_FRENCH", TWLG_FRN);
        map.put("TWLG_FRENCH_BELGIAN", 56);
        map.put("TWLG_FRENCH_CANADIAN", TWLG_FCF);
        map.put("TWLG_FRENCH_LUXEMBOURG", 57);
        map.put("TWLG_FRENCH_SWISS", 58);
        map.put("TWLG_GERMAN", TWLG_GER);
        map.put("TWLG_GERMAN_AUSTRIAN", 59);
        map.put("TWLG_GERMAN_LUXEMBOURG", 60);
        map.put("TWLG_GERMAN_LIECHTENSTEIN", 61);
        map.put("TWLG_GERMAN_SWISS", 62);
        map.put("TWLG_GREEK", 63);
        map.put("TWLG_HEBREW", 64);
        map.put("TWLG_HUNGARIAN", 65);
        map.put("TWLG_ICELANDIC", TWLG_ICE);
        map.put("TWLG_INDONESIAN", 66);
        map.put("TWLG_ITALIAN", TWLG_ITN);
        map.put("TWLG_ITALIAN_SWISS", 67);
        map.put("TWLG_JAPANESE", 68);
        map.put("TWLG_KOREAN", 69);
        map.put("TWLG_KOREAN_JOHAB", 70);
        map.put("TWLG_LATVIAN", 71);
        map.put("TWLG_LITHUANIAN", 72);
        map.put("TWLG_NORWEGIAN", TWLG_NOR);
        map.put("TWLG_NORWEGIAN_BOKMAL", 73);
        map.put("TWLG_NORWEGIAN_NYNORSK", 74);
        map.put("TWLG_POLISH", 75);
        map.put("TWLG_PORTUGUESE", TWLG_POR);
        map.put("TWLG_PORTUGUESE_BRAZIL", 76);
        map.put("TWLG_ROMANIAN", 77);
        map.put("TWLG_RUSSIAN", 78);
        map.put("TWLG_SERBIAN_LATIN", 79);
        map.put("TWLG_SLOVAK", 80);
        map.put("TWLG_SLOVENIAN", 81);
        map.put("TWLG_SPANISH", TWLG_SPA);
        map.put("TWLG_SPANISH_MEXICAN", 82);
        map.put("TWLG_SPANISH_MODERN", 83);
        map.put("TWLG_SWEDISH", TWLG_SWE);
        map.put("TWLG_THAI", 84);
        map.put("TWLG_TURKISH", 85);
        map.put("TWLG_UKRANIAN", 86);
        map.put("TWLG_ASSAMESE", 87);
        map.put("TWLG_BENGALI", 88);
        map.put("TWLG_BIHARI", 89);
        map.put("TWLG_BODO", 90);
        map.put("TWLG_DOGRI", 91);
        map.put("TWLG_GUJARATI", 92);
        map.put("TWLG_HARYANVI", 93);
        map.put("TWLG_HINDI", 94);
        map.put("TWLG_KANNADA", 95);
        map.put("TWLG_KASHMIRI", 96);
        map.put("TWLG_MALAYALAM", 97);
        map.put("TWLG_MARATHI", 98);
        map.put("TWLG_MARWARI", 99);
        map.put("TWLG_MEGHALAYAN", 100);
        map.put("TWLG_MIZO", 101);
        map.put("TWLG_NAGA", 102);
        map.put("TWLG_ORISSI", 103);
        map.put("TWLG_PUNJABI", 104);
        map.put("TWLG_PUSHTU", 105);
        map.put("TWLG_SERBIAN_CYRILLIC", 106);
        map.put("TWLG_SIKKIMI", 107);
        map.put("TWLG_SWEDISH_FINLAND", 108);
        map.put("TWLG_TAMIL", 109);
        map.put("TWLG_TELUGU", 110);
        map.put("TWLG_TRIPURI", 111);
        map.put("TWLG_URDU", 112);
        map.put("TWLG_VIETNAMESE", 113);

        mapConstantNameToIntValue = map;
        return mapConstantNameToIntValue;
    }

    /** List of all twain constant names */
    public static String[] getConstants() {
        Map<String, Integer> map = getMapConstantNameToIntValue();
        List<String> list = new ArrayList<String>(map.keySet());
        Collections.sort(list);
        return list.toArray(new String[0]);
    }
}