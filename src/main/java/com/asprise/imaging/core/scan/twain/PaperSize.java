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

import java.util.ArrayList;
import java.util.List;

/**
 * Paper size.
 */
public class PaperSize {

    String name;
    int widthMm;
    int heightMm;
    double widthInches;
    double heightInches;

    int twainPaperValue;

    public PaperSize(int twainPaperValue, String name, int widthMm, int heightMm, double widthInches, double heightInches) {
        this.name = name;
        this.widthMm = Math.min(widthMm, heightMm);
        this.heightMm = Math.max(widthMm, heightMm);
        this.widthInches = Math.min(widthInches, heightInches);
        this.heightInches = Math.max(widthInches, heightInches);
        this.twainPaperValue = twainPaperValue;
    }

    /**
     * Returns the first paper size with the given twain value or null if there is no close size.
     */
    public static PaperSize getPaperSize(int twainPaperValue) {
        for(PaperSize paperSize : getPredefinedPaperSizes()) {
            if(paperSize.twainPaperValue == twainPaperValue) {
                return paperSize;
            }
        }
        return null;
    }

    /**
     * Returns the paper size of the corresponding width/height with error range 2% or null if there is no close size.
     */
    public static PaperSize getPaperSize(double widthInches, double heightInches) {
        for(PaperSize paperSize : getPredefinedPaperSizes()) {
            if(Math.abs((widthInches - paperSize.widthInches) / paperSize.widthInches) < 0.02
                    && Math.abs((heightInches - paperSize.heightInches) / paperSize.heightInches) < 0.02) {
                return paperSize;
            }
        }
        return null;
    }

    /**
     * Returns the paper size of the corresponding width/height with error range 2% or null if there is no close size.
     */
    public static PaperSize getPaperSize(int widthMm, double heightMm) {
        for(PaperSize paperSize : getPredefinedPaperSizes()) {
            if(Math.abs((widthMm - paperSize.widthMm) * 1.0 / paperSize.widthMm) < 0.02
                    && Math.abs((heightMm - paperSize.heightMm) * 1.0 / paperSize.heightMm) < 0.02) {
                return paperSize;
            }
        }
        return null;
    }

    private static List<PaperSize> paperSizes;
    /** Get list of pre-defined paper sizes. */
    public static List<PaperSize> getPredefinedPaperSizes() {
        if(paperSizes != null) {
            return paperSizes;
        }

        List<PaperSize> list = new ArrayList<PaperSize>();

        if(sizeDefinition.length % 6  != 0) {
            throw new RuntimeException("Invalid definitions. array size is not dividable by 6: " + sizeDefinition.length);
        }

        for(int i = 0; i < sizeDefinition.length; i++) {
            PaperSize ps = new PaperSize((Integer)sizeDefinition[i], (String)sizeDefinition[i+1],
                    (Integer)sizeDefinition[i+2], (Integer)sizeDefinition[i+3], (Double)sizeDefinition[i+4], (Double)sizeDefinition[i+5]);
            list.add(ps);
        }

        paperSizes = list;
        return paperSizes;
    }

    private static final Object[] sizeDefinition = new Object[] {
            // Ref: http://www.paper-sizes.com/north-american-paper-sizes/north-american-loose-paper-sizes
            TwainConstants.TWSS_USLEDGER, "US Ledger", 432, 279, 17.0, 11.0,
//            TwainConstants.TWSS_, "US Tabloid", 279, 432, 11.0, 17.0,
            TwainConstants.TWSS_USLETTER, "US Letter", 216, 279, 8.5, 11.0,
            TwainConstants.TWSS_USLEGAL, "US Legal", 216, 356, 8.5, 14.0,
//            TwainConstants.TWSS_, "Government Letter", 203, 267, 8.0, 10.5,
//            TwainConstants.TWSS_, "Junior Legal", 203, 127, 8.0, 5.0,

            TwainConstants.TWSS_USEXECUTIVE, "", 184, 267, 7.25, 10.50,

            TwainConstants.TWSS_A0, "A0", 841, 1189, 33.1, 46.8,
            TwainConstants.TWSS_A1, "A1", 594, 841, 23.4, 33.1,
            TwainConstants.TWSS_A2, "A2", 420, 594, 16.5, 23.4,
            TwainConstants.TWSS_A3, "A3", 297, 420, 11.7, 16.5,
            TwainConstants.TWSS_A4, "A4", 210, 297, 8.3, 11.7,
            TwainConstants.TWSS_A5, "A5", 148, 210, 5.8, 8.3,
            TwainConstants.TWSS_A6, "A6", 105, 148, 4.1, 5.8,
            TwainConstants.TWSS_A7, "A7", 74, 105, 2.9, 4.1,
            TwainConstants.TWSS_A8, "A8", 52, 74, 2.0, 2.9,
            TwainConstants.TWSS_A9, "A9", 37, 52, 1.5, 2.0,
            TwainConstants.TWSS_A10, "A10", 26, 37, 1.0, 1.5,

            TwainConstants.TWSS_ISOB0, "B0", 1000, 1414, 39.4, 55.7,
            TwainConstants.TWSS_ISOB1, "B1", 707, 1000, 27.8, 39.4,
            TwainConstants.TWSS_ISOB2, "B2", 500, 707, 19.7, 27.8,
            TwainConstants.TWSS_ISOB3, "B3", 353, 500, 13.9, 19.7,
            TwainConstants.TWSS_ISOB4, "B4", 250, 353, 9.8, 13.9,
            TwainConstants.TWSS_ISOB5, "B5", 176, 250, 6.9, 9.8,
            TwainConstants.TWSS_ISOB6, "B6", 125, 176, 4.9, 6.9,
            TwainConstants.TWSS_ISOB7, "B7", 88, 125, 3.5, 4.9,
            TwainConstants.TWSS_ISOB8, "B8", 62, 88, 2.4, 3.5,
            TwainConstants.TWSS_ISOB9, "B9", 44, 62, 1.7, 2.4,
            TwainConstants.TWSS_ISOB10, "B10", 31, 44, 1.2, 1.7,

            TwainConstants.TWSS_C0, "C0", 917, 1297, 36.1, 51.1,
            TwainConstants.TWSS_C1, "C1", 648, 917, 25.5, 36.1,
            TwainConstants.TWSS_C2, "C2", 458, 648, 18.0, 25.5,
            TwainConstants.TWSS_C3, "C3", 324, 458, 12.8, 18.0,
            TwainConstants.TWSS_C4, "C4", 228, 324, 9.0, 12.8,
            TwainConstants.TWSS_C5, "C5", 162, 229, 6.4, 9.0,
            TwainConstants.TWSS_C6, "C6", 114, 162, 4.5, 6.4,
//            TwainConstants.TWSS_, "C7/6", 81, 162, 3.2, 6.4,
            TwainConstants.TWSS_C7, "C7", 81, 114, 3.2, 4.5,
            TwainConstants.TWSS_C8, "C8", 57, 81, 2.2, 3.2,
            TwainConstants.TWSS_C9, "C9", 40, 57, 1.6, 2.2,
            TwainConstants.TWSS_C10, "C10", 28, 40, 1.1, 1.6,
//            TwainConstants.TWSS_, "DL", 110, 220, 4.3, 8.7,

            TwainConstants.TWSS_JISB0, "JIS B0", 1030, 1456, 40.6, 57.3,
            TwainConstants.TWSS_JISB1, "JIS B1", 728, 1030, 28.7, 40.6,
            TwainConstants.TWSS_JISB2, "JIS B2", 515, 728, 20.3, 28.7,
            TwainConstants.TWSS_JISB3, "JIS B3", 364, 515, 14.3, 20.3,
            TwainConstants.TWSS_JISB4, "JIS B4", 257, 364, 10.1, 14.3,
            TwainConstants.TWSS_JISB5, "JIS B5", 182, 257, 7.2, 10.1,
            TwainConstants.TWSS_JISB6, "JIS B6", 128, 182, 5.0, 7.2,
            TwainConstants.TWSS_JISB7, "JIS B7", 91, 128, 3.6, 5.0,
            TwainConstants.TWSS_JISB8, "JIS B8", 64, 91, 2.5, 3.6,
            TwainConstants.TWSS_JISB9, "JIS B9", 45, 64, 1.8, 2.5,
            TwainConstants.TWSS_JISB10, "JIS B10", 32, 45, 1.3, 1.8,
//            TwainConstants.TWSS_, "JIS B11", 22, 32, 0.9, 1.3,
//            TwainConstants.TWSS_, "JIS B12", 16, 22, 0.6, 0.9,
    };

    public String getName() {
        return name;
    }

    public int getWidthMm() {
        return widthMm;
    }

    public int getHeightMm() {
        return heightMm;
    }

    public double getWidthInches() {
        return widthInches;
    }

    public double getHeightInches() {
        return heightInches;
    }

    public int getTwainPaperValue() {
        return twainPaperValue;
    }
}
