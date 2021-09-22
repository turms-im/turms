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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

/**
 * @author James Chen
 */
public final class DateUtil {

    private static final ThreadLocal<Calendar> CALENDAR_THREAD_LOCAL = ThreadLocal
            .withInitial(() -> new GregorianCalendar(TimeZone.getTimeZone("UTC")));
    // "1970-01-01T00:00:00.000Z"
    public static final int DATE_TIME_LENGTH = 24;

    private DateUtil() {
    }

    public static String toISO(long timeInMillis) {
        Calendar calendar = CALENDAR_THREAD_LOCAL.get();
        calendar.setTimeInMillis(timeInMillis);
        StringBuilder sb = new StringBuilder(DATE_TIME_LENGTH);
        sb.append(calendar.get(Calendar.YEAR))
                .append("-")
                .append(twoDigit(calendar.get(Calendar.MONTH) + 1))
                .append("-")
                .append(twoDigit(calendar.get(Calendar.DAY_OF_MONTH)))
                .append("T")
                .append(twoDigit(calendar.get(Calendar.HOUR_OF_DAY)))
                .append(":")
                .append(twoDigit(calendar.get(Calendar.MINUTE)))
                .append(":")
                .append(twoDigit(calendar.get(Calendar.SECOND)))
                .append(".")
                .append(threeDigit(calendar.get(Calendar.MILLISECOND)))
                .append("Z");
        return sb.toString();
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

    private static String twoDigit(int i) {
        if (i >= 0 && i < 10) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

    private static String threeDigit(int i) {
        if (i >= 0 && i < 10) {
            return "00" + i;
        } else if (i < 100) {
            return "0" + i;
        }
        return String.valueOf(i);
    }

}
