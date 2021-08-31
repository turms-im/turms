/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.server.common.util;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @author James Chen
 */
public final class DateUtil {

    private DateUtil() {
    }

    public static Date max(@Nullable Date date1, @Nullable Date date2) {
        if (date1 == null) {
            if (date2 != null) {
                return date2;
            }
        } else if (date2 != null && date1.before(date2)) {
            return date2;
        }
        return date1;
    }

    public static Date min(@Nullable Date date1, @Nullable Date date2) {
        if (date1 == null) {
            if (date2 != null) {
                return date2;
            }
        } else if (date2 != null && date1.after(date2)) {
            return date2;
        }
        return date1;
    }

    public static boolean isAfterOrSame(@Nullable Date d1, Date d2) {
        return d1 != null && !d1.before(d2);
    }

}
