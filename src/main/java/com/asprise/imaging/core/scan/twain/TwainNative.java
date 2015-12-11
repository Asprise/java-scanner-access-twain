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

/**
 * TWAIN JNI interface.
 *
 */
public class TwainNative {

    public static final int LOG_LEVEL_ERROR = 1;
    public static final int LOG_LEVEL_WARN = 2;
    public static final int LOG_LEVEL_INFO = 3;
    public static final int LOG_LEVEL_DEBUG = 4;

    public static final String LOG_TO_STDOUT = "stdout";
    public static final String LOG_TO_STDERR = "stderr";

    public static final String FUNC_twain_list_sources = "asprise_scan_twain_list_sources";
    public static final String FUNC_twain_get_default_source_name = "asprise_scan_twain_get_default_source_name";
    public static final String FUNC_twain_select_default_source = "asprise_scan_twain_select_default_source";
    public static final String FUNC_twain_scan = "asprise_scan_twain_scan";
    public static final String FUNC_image_output = "asprise_scan_image_output";
    public static final String FUNC_image_info = "asprise_scan_image_info";
    public static final String FUNC_image_process = "asprise_scan_image_process";
    public static final String FUNC_twain_source_manager_open = "asprise_scan_twain_source_manager_open";
    public static final String FUNC_twain_source_manager_close = "asprise_scan_twain_source_manager_close";
    public static final String FUNC_twain_open_data_source = "asprise_scan_twain_open_data_source";
    public static final String FUNC_twain_close_data_source = "asprise_scan_twain_close_data_source";
    public static final String FUNC_twain_get_source = "asprise_scan_twain_get_source";
    public static final String FUNC_twain_caps_set = "asprise_scan_twain_caps_set";
    public static final String FUNC_twain_down_to_state_gracefully = "asprise_scan_twain_down_to_state_gracefully";
    public static final String FUNC_twain_cap_set = "asprise_scan_twain_cap_set";
    public static final String FUNC_twain_cap_get = "asprise_scan_twain_cap_get";
    public static final String FUNC_twain_cap_reset = "asprise_scan_twain_cap_reset";
    public static final String FUNC_twain_caps_print = "asprise_scan_twain_caps_print";
    public static final String FUNC_twain_create_dummy_window = "asprise_scan_twain_create_dummy_window";
    public static final String FUNC_twain_destroy_window = "asprise_scan_twain_destroy_window";
    public static final String FUNC_twain_register_app = "asprise_scan_twain_register_app";
    public static final String FUNC_twain_get_state = "asprise_scan_twain_get_state";
    public static final String FUNC_twain_is_loaded = "asprise_scan_twain_is_loaded";
    public static final String FUNC_version = "asprise_scan_version";

    /**
     * Invokes functions from C++.
     * @param requestInJson request in JSON format.
     * @return response.
     */
    public static native String invokeFunc(String requestInJson);

    /**
     * Configure logging settings.
     * @param level Any of {@linkplain #LOG_LEVEL_INFO} (default), {@linkplain #LOG_LEVEL_WARN}, etc.
     * @param logFilePath path to the target output file or special values: "stdout", "stderr" for console logging, null or empty string to disable logging.
     */
    public static native void configureLogging(int level, String logFilePath);
}
