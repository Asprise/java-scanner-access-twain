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

import java.lang.ref.SoftReference;
import java.util.HashMap;

public class SoftCache
{
    private HashMap map = new HashMap();

    public Object get(Object key)
    {
        SoftReference softRef = (SoftReference)map.get(key);

        if (softRef==null)
            return null;

        return softRef.get();
    }

    public Object put(Object key, Object value)
    {
        SoftReference softRef = (SoftReference)map.put(key, new SoftReference(value));

        if (softRef==null)
            return null;

        Object oldValue = softRef.get();
        softRef.clear();

        return oldValue;
    }

    public Object remove(Object key)
    {
        SoftReference softRef = (SoftReference)map.remove(key);

        if (softRef==null)
            return null;

        Object oldValue = softRef.get();
        softRef.clear();

        return oldValue;
    }
}