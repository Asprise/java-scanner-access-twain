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
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Represents a TWAIN device.
 */
public class Source {

    // struct: TW_IDENTITY

    int Id;
    int ProtocolMajor;
    int ProtocolMinor;
    int SupportedGroups;
    String Manufacturer;
    String ProductFamily;
    String ProductName;

    boolean defaultSource;
    boolean feeder;
    boolean flatbed;

    List<Capability> caps;

    public Source (String prodName) {
        this.ProductName = prodName;
    }

    public Source(Map<String, Object> jsonObject) {
        this.Id = TwainUtil.toInteger(jsonObject.get("Id"), -1);
        this.Manufacturer = TwainUtil.toString(jsonObject.get("Manufacturer"));
        this.ProductFamily = TwainUtil.toString(jsonObject.get("ProductFamily"));
        this.ProductName = TwainUtil.toString(jsonObject.get("ProductName"));
        this.ProtocolMajor = TwainUtil.toInteger(jsonObject.get("ProtocolMajor"), -1);
        this.ProtocolMinor = TwainUtil.toInteger(jsonObject.get("ProtocolMinor"), -1);
        this.SupportedGroups = TwainUtil.toInteger(jsonObject.get("SupportedGroups"), -1);
        this.defaultSource = jsonObject.containsKey("default") ? (Boolean)jsonObject.get("default") : false;
        this.feeder = jsonObject.containsKey("feeder") ? (Boolean)jsonObject.get("feeder") : false;
        this.flatbed = jsonObject.containsKey("flatbed") ? (Boolean)jsonObject.get("flatbed") : false;
        this.caps = Capability.createCapList((Map<String, Object>) jsonObject.get("caps"));
    }

    public static List<Source> createSourceList(List jsonArray) {
        List<Source> sources = new ArrayList<Source>();
        for (Object sourceObject : jsonArray) {
            sources.add(new Source((Map<String, Object>) sourceObject));
        }
        return sources;
    }

    /** Returns the source object with given name or null if not found */
    public static Source getSourceByName(List<Source> sources, String name) {
        if(sources == null || name == null) {
            return null;
        }
        for(Source source : sources) {
            if(source != null && name.equals(source.getProductName())) {
                return source;
            }
        }
        return null;
    }

    /** Returns the first source marked as default in the first or null if not found */
    public static Source getDefaultSource(List<Source> sources) {
        if(sources == null) {
            return null;
        }
        for(Source source : sources) {
            if(source != null && source.isDefaultSource()) {
                return source;
            }
        }
        return null;
    }

    public int getId() {
        return Id;
    }

    public int getProtocolMajor() {
        return ProtocolMajor;
    }

    public int getProtocolMinor() {
        return ProtocolMinor;
    }

    public int getSupportedGroups() {
        return SupportedGroups;
    }

    public String getManufacturer() {
        return Manufacturer;
    }

    public String getProductFamily() {
        return ProductFamily;
    }

    public String getProductName() {
        return ProductName;
    }

    public boolean isDefaultSource() {
        return defaultSource;
    }

    public boolean hasFeeder() {
        return feeder;
    }

    public boolean hasFlatbed() {
        return flatbed;
    }

    public List<Capability> getCaps() {
        return caps;
    }

    /**
     * Gets the capability of the given name.
     * @param capName the cap name
     * @return the capability of the given name or null if not found.
     */
    public Capability getCap(String capName) {
        if(capName == null) {
            return null;
        }
        for(int i = 0; caps != null && i < caps.size(); i++) {
            Capability cap = caps.get(i);
            if(capName.equalsIgnoreCase(cap.getName())) {
                return cap;
            }
        }
        return null;
    }

    /**
     * Gets the capability of the given code.
     * @param capCode the cap code
     * @return the capability of the given code or null if not found.
     */
    public Capability getCap(int capCode) {
        for(int i = 0; caps != null && i < caps.size(); i++) {
            Capability cap = caps.get(i);
            if(capCode == cap.getCode()) {
                return cap;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return getProductName();
    }

    /**
     * Represents a cap.
     */
    public static class Capability {
        String name;
        int code;

        /** Can be: null, Integer, String, List of Int/String, CapValueEnumeration, or CapValueRange. */
        Object value;

        String errorString;
        boolean supported = true;

        public static List<Capability> createCapList(Map<String, Object> jsonCaps) {
            List<Capability> caps = new ArrayList<Capability>();
            if(jsonCaps != null) {
                for (Map.Entry<String, Object> entry : jsonCaps.entrySet()) {
                    caps.add(new Capability(entry.getKey(), entry.getValue()));
                }
            }
            return caps;
        }

        public Capability(String capName, Object capValueInJson) {
            this.name = capName;
            this.code = TwainConstants.getCapCode(capName);
            if(capValueInJson == null) {
                this.value = null;
            } else if(capValueInJson instanceof List) {
                List list = new ArrayList((Collection) capValueInJson);
                this.value = list;
            } else if(capValueInJson instanceof Map) {
                Map<String, Object> map = (Map<String, Object>) capValueInJson;
                if(map.containsKey("ItemList")) {
                    this.value = new CapValueEnumeration(map);
                } else if(map.containsKey("StepSize")) {
                    this.value = new CapValueRange(map);
                } else {
                    throw new UnsupportedOperationException("Not recognzied: " + map.toString());
                }
            } else {
                if((capValueInJson instanceof String) && ((String)capValueInJson).startsWith("<error:")) {
                    errorString = (String) capValueInJson;
                    this.value = null;
                    this.supported = false;
                } else {
                    this.value = capValueInJson; // simple value.
                }
            }
        }

        /**
         * Return 1 if valid, 0 if invalid; -1 if unable to valid.
         * @param attempt
         * @return
         */
        public int validate(Object attempt) {
            if(attempt == value) {
                return 1;
            }
            if(value == null) {
                return -1;
            }

            if(attempt != null && attempt.equals(value)) {
                return 1;
            }

            if(! ((value instanceof List) || (value instanceof CapValueContainer)) ) {
                return -1;
            }

            if(value instanceof List) {
                for(Object element : (List)value) {
                    if(attempt instanceof Number) {
                        Number elementNum = TwainUtil.toNumber(element, null);
                        if(elementNum != null && ((Number)attempt).intValue() == elementNum.intValue() ) {
                            return 1;
                        }
                    } else if(attempt instanceof String) {
                        if(((String)attempt).equals(String.valueOf(element))) {
                            return 1;
                        }
                    }
                }
                return 0;
            } else if(value instanceof CapValueContainer) {
                return ((CapValueContainer)value).validate(attempt) ? 1 : 0;
            } else {
                throw new UnsupportedOperationException();
            }
        }

        public String getName() {
            return name;
        }

        public int getCode() {
            return code;
        }

        /** Can be of type: null, Integer, String, List of Int/String, CapValueEnumeration, or CapValueRange. */
        public Object getValue() {
            return value;
        }

        public Object getCurrentValue() {
            if(! ((value instanceof List) || (value instanceof CapValueContainer)) ) {
                return value;
            }

            if(value instanceof List) {
                return null; // unknown
            } else if(value instanceof CapValueContainer) {
                return ((CapValueContainer)value).getCurrentValue();
            } else {
                throw new UnsupportedOperationException();
            }
        }

        public String getErrorString() {
            return errorString;
        }

        /**
         * Return false if this cap is not supported by the device.
         * @return
         */
        public boolean isSupported() {
            return supported;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(name).append(": ");

            if(supported) {
                sb.append(value);
            } else {
                sb.append(errorString);
            }
            return sb.toString();
        }
    }

    // The container structure will be one of four types: TWON_ARRAY, TWON_ENUMERATION,
    // TWON_ONEVALUE, or TWON_RANGE.

    // TWON_ONEVALUE: Direct value, either Integer or String
    // TWON_ARRAY: List of Integer or String

    public static abstract class CapValueContainer {

        abstract public Object getCurrentValue();

        abstract public Object getDefaultValue();

        /** Check whether the attempt is valid against the enum or range. */
        abstract public boolean validate(Object attempt);

        public Object getCurrentValueFallBackToDefault() {
            Object value = getCurrentValue();
            if(value != null) {
                return value;
            }
            return getDefaultValue();
        }
    }

    /** TWON_ENUMERATION */
    public static class CapValueEnumeration extends CapValueContainer {
        // ItemType
        // NumItems
        int CurrentIndex = -1;
        int DefaultIndex = -1;
        List ItemList;

        public CapValueEnumeration(Map<String, Object> jsonObject) {
            this.CurrentIndex = TwainUtil.toInteger(jsonObject.get("CurrentIndex"), -1);
            this.DefaultIndex = TwainUtil.toInteger(jsonObject.get("DefaultIndex"), -1);
            List list = new ArrayList((Collection) jsonObject.get("ItemList"));
            this.ItemList = list;
        }

        @Override
        public Object getCurrentValue() {
            return ItemList == null || CurrentIndex < 0 ? null : ItemList.get(CurrentIndex);
        }

        @Override
        public Object getDefaultValue() {
            return ItemList == null || DefaultIndex < 0 ? null : ItemList.get(DefaultIndex);
        }

        @Override
        public boolean validate(Object attempt) {
            if(attempt == null) {
                return false;
            }
            if(attempt instanceof Number) {
                int attemptInt = ((Number)attempt).intValue();
                for(int i = 0; ItemList != null && i < ItemList.size(); i++) {
                    Object item = ItemList.get(i);
                    Number number = TwainUtil.toNumber(item, -1);
                    if((number instanceof Integer) && number.intValue() == attemptInt) {
                        return true;
                    }
                }
            } else if(attempt instanceof String) {
                for(int i = 0; ItemList != null && i < ItemList.size(); i++) {
                    Object item = ItemList.get(i);
                    if(((String)attempt).equals(String.valueOf(item))) {
                        return true;
                    }
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return "{" +
                    "CurrentIndex=" + CurrentIndex +
                    ", DefaultIndex=" + DefaultIndex +
                    ", ItemList=" + ItemList +
                    '}';
        }
    }

    /** TWON_RANGE */
    public static class CapValueRange extends CapValueContainer {
        // ItemType;
        Number MinValue;
        Number MaxValue;
        Number StepSize;
        Number DefaultValue;
        Number CurrentValue;

        public CapValueRange(Map<String, Object> jsonObject) {
            this.MinValue = TwainUtil.toNumber(jsonObject.get("MinValue"), -1);
            this.MaxValue = TwainUtil.toNumber(jsonObject.get("MaxValue"), -1);
            this.StepSize = TwainUtil.toNumber(jsonObject.get("StepSize"), -1);
            this.DefaultValue = TwainUtil.toNumber(jsonObject.get("DefaultValue"), -1);
            this.CurrentValue = TwainUtil.toNumber(jsonObject.get("CurrentValue"), -1);
        }

        @Override
        public boolean validate(Object attempt) {
            if(! (attempt instanceof Number)) {
                return false;
            }
            Number attemptNum = (Number)attempt;

            if(attemptNum.doubleValue() > MaxValue.doubleValue() || attemptNum.doubleValue() < MinValue.doubleValue()) {
                return false;
            }

            double steps = (attemptNum.doubleValue() - MinValue.doubleValue()) / StepSize.doubleValue();
            double delta = steps - Math.round(steps);
            if(Math.abs(delta) < 0.02) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object getCurrentValue() {
            return CurrentValue;
        }

        @Override
        public Object getDefaultValue() {
            return DefaultValue;
        }

        @Override
        public String toString() {
            return "{" +
                    "MinValue=" + MinValue +
                    ", MaxValue=" + MaxValue +
                    ", StepSize=" + StepSize +
                    ", DefaultValue=" + DefaultValue +
                    ", CurrentValue=" + CurrentValue +
                    '}';
        }

        public Number getMin() {
            return MinValue;
        }

        public Number getMax() {
            return MaxValue;
        }
    }
}
