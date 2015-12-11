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

import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Common used caption options
 */
public class CapOptions {

    public static int PAPER_SOURCE_FLATBED = 1;
    public static int PAPER_SOURCE_FEEDER_FRONT_SIDE = 2;
    public static int PAPER_SOURCE_FEEDER_BOTH_SIDES = 6;

    public static List<CapOptionItem> EMPTY_LIST = new ArrayList<CapOptionItem>();

    public static class CapOptionItem {
        Object value;
        String label;
        boolean isDefault;

        public CapOptionItem(Object value, String label, boolean isDefault) {
            this.value = value;
            this.label = label;
            this.isDefault = isDefault;
        }

        public Object getValue() {
            return value;
        }

        public int getValueAsInt() {
            return ((Number)value).intValue();
        }

        public String getLabel() {
            return label;
        }

        public boolean isDefault() {
            return isDefault;
        }

        public void setIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
        }

        @Override
        public String toString() {
            return label == null ? String.valueOf(value) : label;
        }

        /**
         * Short-hand method to create list.
         * @param args in format value, label [, default].
         */
        public static List<CapOptionItem> createCapOptionItemList(Object ... args) {
            List<CapOptionItem> items = new ArrayList<CapOptionItem>();
            for(int i = 0; i < args.length; ) {
                Object value = args[i++];
                if(!(value instanceof Integer) && !(value instanceof String)) {
                    throw new IllegalArgumentException("Invalid value type: " + value.getClass());
                }
                if(i >= args.length) {
                    throw new IllegalArgumentException("No label for value: " + value);
                }

                String label = (String) args[i++];

                boolean isDefault = false;
                if(i < args.length && (args[i] instanceof Boolean)) {
                    isDefault = (Boolean)args[i];
                    i++;
                }
                items.add(new CapOptionItem(value, label, isDefault));
            }
            return items;
        }
    }

    public static List<CapOptionItem> getPaperSource() {
        return CapOptionItem.createCapOptionItemList(
                PAPER_SOURCE_FLATBED, "Flatbed",
                PAPER_SOURCE_FEEDER_FRONT_SIDE, "Feeder (Front Sides Only)",
                PAPER_SOURCE_FEEDER_BOTH_SIDES, "Feeder (Both Sides)"
        );
    }

    public static Source.Capability createSyntheticCapForPaperSource(boolean hasFlatbed, boolean hasFeeder) {
        List list = new ArrayList();
        if(hasFlatbed) {
            list.add(PAPER_SOURCE_FLATBED);
        }
        if(hasFeeder) {
            list.add(PAPER_SOURCE_FEEDER_FRONT_SIDE);
            list.add(PAPER_SOURCE_FEEDER_BOTH_SIDES);
        }
        Source.Capability cap = new Source.Capability("SYN-PAPER-SOURCE", list);
        return cap;
    }

    public static List<CapOptionItem> getPixelTypes() {
        return CapOptionItem.createCapOptionItemList(
                TwainConstants.TWPT_BW, "Black and White",
                TwainConstants.TWPT_GRAY, "Grayscale",
                TwainConstants.TWPT_RGB, "Color", true
        );
    }

    public static List<CapOptionItem> getResolutions() {
        int[] values = new int[] {50, 100, 150, 200, 300, 400, 500, 600, 900, 1200, 2400, 3000};
        List list = new ArrayList();
        for(int value : values) {
            list.add(value);
            list.add(String.valueOf(value) + " DPI");
            list.add(value == 300 ? true : false);
        }

        return CapOptionItem.createCapOptionItemList(list.toArray());
    }

    public static List<CapOptionItem> getPaperSizes() {
        return CapOptionItem.createCapOptionItemList(
                TwainConstants.TWSS_NONE, "Default", true,
                TwainConstants.TWSS_A4, "A4 (210 x 297mm)",
                TwainConstants.TWSS_USLETTER, "US Letter (8.5 x 11 in.)",

                TwainConstants.TWSS_USLEGAL, "US Legal (8.5 x 14 in.)",
                TwainConstants.TWSS_USEXECUTIVE, "US Executive (7 x 10 in.)",
                TwainConstants.TWSS_USLEDGER, "US Ledger (11 x 17 in.)",

                TwainConstants.TWSS_A0, "A0 (841 × 1189mm)",
                TwainConstants.TWSS_A1, "A1 (594 × 841mm)",
                TwainConstants.TWSS_A2, "A2 (420 × 594mm)",
                TwainConstants.TWSS_A3, "A3 (297 × 420mm)",
                TwainConstants.TWSS_A5, "A5 (148 x 210mm)",
                TwainConstants.TWSS_A6, "A6 (104 x 148mm)",
                TwainConstants.TWSS_A6, "A6 (104 x 148mm)",
                TwainConstants.TWSS_A7, "A7 (74 x 105mm)",
                TwainConstants.TWSS_A8, "A8 (52 x 74mm)",

                TwainConstants.TWSS_ISOB0, "B0 (1000 × 1414mm)",
                TwainConstants.TWSS_ISOB1, "B1 (707 × 1000mm)",
                TwainConstants.TWSS_ISOB2, "B2 (500 × 707mm)",
                TwainConstants.TWSS_ISOB3, "B3 (353 × 500mm)",
                TwainConstants.TWSS_ISOB4, "B4 (250 × 353mm)",
                TwainConstants.TWSS_ISOB5, "B5 (176 × 250mm)",
                TwainConstants.TWSS_ISOB6, "B6 (125 × 176mm)",
                TwainConstants.TWSS_ISOB7, "B7 (88 × 125mm)",
                TwainConstants.TWSS_ISOB8, "B8 (62 × 88mm)",
                TwainConstants.TWSS_JISB0, "JISB0 (1030 × 1456mm)",
                TwainConstants.TWSS_JISB0, "JISB1 (728 × 1030mm)",
                TwainConstants.TWSS_JISB0, "JISB2 (515 × 728mm)",
                TwainConstants.TWSS_JISB0, "JISB3 (364 × 515mm)",
                TwainConstants.TWSS_JISB0, "JISB4 (257 × 364mm)",
                TwainConstants.TWSS_JISB0, "JISB5 (182 × 257mm)",
                TwainConstants.TWSS_JISB0, "JISB6 (128 × 182mm)",
                TwainConstants.TWSS_JISB0, "JISB7 (91 × 128mm)",
                TwainConstants.TWSS_JISB0, "JISB8 (64 × 91mm)",
                TwainConstants.TWSS_C0, "C0 (917 × 1297mm)",
                TwainConstants.TWSS_C1, "C1 (648 × 917mm)",
                TwainConstants.TWSS_C2, "C2 (458 × 648mm)",
                TwainConstants.TWSS_C3, "C3 (324 × 458mm)",
                TwainConstants.TWSS_C4, "C4 (229 × 324mm)",
                TwainConstants.TWSS_C5, "C5 (162 × 229mm)",
                TwainConstants.TWSS_C6, "C6 (114 × 162mm)",
                TwainConstants.TWSS_C7, "C7 (81 × 114mm)",
                TwainConstants.TWSS_C8, "C8 (57 × 81mm)"
        );
    }

    public static class CapOptionsComboBoxModel implements ComboBoxModel {
        List<ListDataListener> listeners = new ArrayList<ListDataListener>();

        List<CapOptionItem> list;
        int selecteIndex = -1;
        CapOptionItem selectedItem;

        // stored for future use
        Source.Capability capability;
        Object defaultSelectedValue;


        public CapOptionsComboBoxModel() {
        }

        /**
         * Will set the selectedItem to cap value, or list default (if cap value doesn't exist) or fall back to the first item.
         * @param fullList full list of items
         * @param capability
         */
        public CapOptionsComboBoxModel(List<CapOptionItem> fullList, Source.Capability capability, boolean filterInvalidItems, Object selectedValue) {
            setList(fullList, capability, filterInvalidItems, selectedValue);
        }

        /**
         * Will set the selectedItem to cap value, or list default (if cap value doesn't exist) or fall back to the first item.
         * @param fullList full list of items
         * @param capability
         */
        public void setList(List<CapOptionItem> fullList, Source.Capability capability, boolean filterInvalidItems, Object defaultSelectedValue) {
            this.selecteIndex = -1;
            this.selectedItem = null;
            this.capability = capability;
            this.defaultSelectedValue = defaultSelectedValue;

            List<CapOptionItem> filtered = new ArrayList<CapOptionItem>();
            for(CapOptionItem item : fullList) {
                if(!filterInvalidItems || capability == null || capability.validate(item.getValue()) != 0) {
                    filtered.add(item);
                }
            }

            this.list = filtered;

            int capValue = capability == null ? -999 : TwainUtil.toNumber(capability.getCurrentValue(), -999).intValue();

            int indexCapValue = -1;
            int indexDefaultSelected = -1;
            int indexListDefault = -1;
            for(int i = 0; i < list.size(); i++) {
                CapOptionItem item = list.get(i);
                if(capValue != -1) {
                    int itemValue = TwainUtil.toNumber(item.getValue(), -2).intValue();
                    if (itemValue == capValue) {
                        indexCapValue = i;
                    }
                }
                if(defaultSelectedValue != null) {
                    int selectedValueInt = TwainUtil.toNumber(defaultSelectedValue, -3).intValue();
                    if(selectedValueInt == TwainUtil.toNumber(item.getValue(), -4).intValue()) {
                        indexDefaultSelected = i;
                    }
                }
                if(item.isDefault()) {
                    indexListDefault = i;
                }
            }

            if(indexDefaultSelected >= 0) {
                selecteIndex = indexDefaultSelected;
            } else if(indexCapValue >= 0) {
                selecteIndex = indexCapValue;
            } else {
                if(indexListDefault >= 0) {
                    selecteIndex = indexListDefault;
                } else { // fall back to use the first one.
                    selecteIndex = list.size() > 0 ? 0 : -1;
                }
            }

            selectedItem = selecteIndex >= 0 ? list.get(selecteIndex) : null;

            for(ListDataListener listener : listeners) {
                listener.contentsChanged(new ListDataEvent(this, ListDataEvent.CONTENTS_CHANGED, 0, 0));
            }
        }

        public void setSelectedIndex(int index) {
            if(list == null) {
                throw new RuntimeException("List is null");
            }
            if(index < 0 || index >= list.size()) {
                throw new IndexOutOfBoundsException();
            }
            setSelectedItem(list.get(index));
        }

        public int getSelecteIndex() {
            return selecteIndex;
        }

        @Override
        public void setSelectedItem(Object anItem) {
            this.selectedItem = (CapOptionItem) anItem;
            this.selecteIndex = list.indexOf(selectedItem);
        }

        @Override
        public Object getSelectedItem() {
            return selectedItem;
        }

        @Override
        public int getSize() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getElementAt(int index) {
            return list == null ? null : list.get(index);
        }

        @Override
        public void addListDataListener(ListDataListener l) {
            if(! listeners.contains(l)) {
                listeners.add(l);
            }
        }

        @Override
        public void removeListDataListener(ListDataListener l) {
            listeners.remove(l);
        }
    }
}
