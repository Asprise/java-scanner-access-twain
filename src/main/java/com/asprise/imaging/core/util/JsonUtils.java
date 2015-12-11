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
package com.asprise.imaging.core.util;

import com.asprise.imaging.core.scan.twain.TwainException;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.jr.ob.JSON;

import java.io.IOException;
import java.util.Map;


public class JsonUtils {

    
    public static Map<Object, Object> parseJson(String s) throws IOException {
        JsonParser parser = new JsonFactory().enable(JsonParser.Feature.ALLOW_COMMENTS).createParser(s);
        Map<Object, Object> json = JSON.std.mapFrom(parser);
        return json;
    }

    
    public static String jsonSerialize(Object jsonObject, boolean pretty) {
        try {
            return JSON.std.with(JSON.Feature.PRETTY_PRINT_OUTPUT, pretty).with(JSON.Feature.WRITE_NULL_PROPERTIES).asString(jsonObject);
        } catch (Throwable e) {
            throw new TwainException("Failed to serialize JSON", e);
        }
    }

    public static String attrValue(Map json, String key, String defaultValue) {
        if(json == null || key == null) {
            return defaultValue;
        }
        Object object = json.get(key);
        return object == null ? defaultValue : String.valueOf(object);
    }

    public static Boolean attrValueAsBoolean(Map json, String key, Boolean defaultValue) {
        if(json == null || key == null) {
            return defaultValue;
        }
        return toBoolean(json.get(key), defaultValue);
    }

    public static Integer attrValueAsInt(Map json, String key, Integer defaultValue) {
        if(json == null || key == null) {
            return defaultValue;
        }
        return toInteger(json.get(key), defaultValue);
    }

    public static Long attrValueAsLong(Map json, String key, Long defaultValue) {
        if(json == null || key == null) {
            return defaultValue;
        }
        return toLong(json.get(key), defaultValue);
    }

    public static Double attrValueAsDouble(Map json, String key, Double defaultValue) {
        if(json == null || key == null) {
            return defaultValue;
        }
        return toDouble(json.get(key), defaultValue);
    }

    
    public static Integer toInteger(Object object, Integer defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Number) {
            return ((Number) object).intValue();
        }

        try {
            Double value = Double.valueOf(object.toString().trim());
            return value.intValue();
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    
    public static Long toLong(Object object, Long defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Number) {
            return ((Number) object).longValue();
        }

        try {
            Double value = Double.valueOf(object.toString().trim());
            return value.longValue();
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    
    public static Double toDouble(Object object, Double defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Number) {
            return ((Number) object).doubleValue();
        }

        try {
            Double value = Double.valueOf(object.toString().trim());
            return value;
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    
    public static Number toNumber(Object object, Number defaultValue) {
        if(object == null) {
            return defaultValue;
        }
        if(object instanceof Number) {
            return (Number) object;
        }

        String s = object.toString();
        s = s.replaceAll("\\.0*$", ""); 
        try {
            if(s.contains(".")) {
                return Double.parseDouble(s);
            } else {
                return Integer.parseInt(s);
            }
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    
    public static Boolean toBoolean(Object object, Boolean defaultValue) {
        if (object == null) {
            return defaultValue;
        }
        if (object instanceof Boolean) {
            return ((Boolean) object).booleanValue();
        }

        try {
            Boolean value = Boolean.valueOf(object.toString().trim());
            return value;
        } catch (Throwable e) {
            return defaultValue;
        }
    }
}
