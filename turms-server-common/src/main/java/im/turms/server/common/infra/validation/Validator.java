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

package im.turms.server.common.infra.validation;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;

import org.springframework.util.StringUtils;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.net.InetAddressUtil;

/**
 * @author James Chen
 */
public final class Validator {

    private Validator() {
    }

    public static boolean isFalsy(@Nullable Object item) {
        if (item == null) {
            return true;
        }
        if (item instanceof String str) {
            return str.isEmpty();
        } else if (item instanceof Collection<?> collection) {
            return collection.isEmpty();
        } else if (item.getClass()
                .isArray()) {
            return Array.getLength(item) <= 0;
        } else if (item instanceof Map<?, ?> map) {
            return map.isEmpty();
        }
        return false;
    }

    public static boolean isTruthy(@Nullable Object item) {
        return !isFalsy(item);
    }

    public static boolean areAllFalsy(@Nullable Object... array) {
        if (array == null) {
            return true;
        }
        for (Object o : array) {
            if (isTruthy(o)) {
                return false;
            }
        }
        return true;
    }

    public static boolean areAllNull(Object... array) {
        if (array == null) {
            return true;
        }
        for (Object o : array) {
            if (o != null) {
                return false;
            }
        }
        return true;
    }

    public static boolean areBothNullOrNotNull(@Nullable Object value1, @Nullable Object value2) {
        return (value1 == null) == (value2 == null);
    }

    public static void notContains(
            @Nullable Collection<?> collection,
            Object value,
            String message) {
        if (collection != null && collection.contains(value)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

    public static void notEmpty(@Nullable Collection<?> collection, String name) {
        if (collection == null || collection.isEmpty()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must not be null or empty").intern());
        }
    }

    public static void notEmpty(@Nullable Object[] array, String name) {
        if (array == null || array.length == 0) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must not be null or empty").intern());
        }
    }

    public static void notEquals(@Nullable Object val1, @Nullable Object val2, String message) {
        if (val1 == null) {
            if (val2 == null) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
            }
        } else if (val1.equals(val2)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

    public static void notNull(@Nullable Object object, String name) {
        if (object == null) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must not be null").intern());
        }
    }

    public static void throwIfAllFalsy(String message, @NotEmpty Object... array) {
        if (areAllFalsy(array)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

    public static void min(@Nullable Integer num, String name, int min) {
        if (num != null && num < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be greater than or equal to "
                            + min).intern());
        }
    }

    public static void min(int num, String name, int min) {
        if (num < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be greater than or equal to "
                            + min).intern());
        }
    }

    public static void max(@Nullable Integer num, String name, int max) {
        if (num != null && num > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be less than or equal to "
                            + max).intern());
        }
    }

    public static void max(int num, String name, int max) {
        if (num > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be less than or equal to "
                            + max).intern());
        }
    }

    public static void inRange(double num, String name, double min, double max) {
        if (num > max || num < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be greater than or equal to "
                            + min
                            + ", and less than or equal to "
                            + max).intern());
        }
    }

    public static void inRange(@Nullable Short num, String name, int min, int max) {
        if (num != null && (num > max || num < min)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be greater than or equal to "
                            + min
                            + ", and less than or equal to "
                            + max).intern());
        }
    }

    public static void inRange(@Nullable Integer num, String name, int min, int max) {
        if (num != null && (num < min || num > max)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be greater than or equal to "
                            + min
                            + ", and less than or equal to "
                            + max).intern());
        }
    }

    public static void inSizeRange(@Nullable Collection<?> items, String name, int min, int max) {
        if (items == null) {
            return;
        }
        int size = items.size();
        if (size < min || size > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("The size of \""
                            + name
                            + "\" must be greater than or equal to "
                            + min
                            + ", and less than or equal to "
                            + max).intern());
        }
    }

    public static void maxSize(@Nullable Collection<?> items, String name, int max) {
        if (items == null) {
            return;
        }
        int size = items.size();
        if (size > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("The size of \""
                            + name
                            + "\" must be less than or equal to "
                            + max).intern());
        }
    }

    public static void inRange(float num, String name, double min, double max) {
        if (num < min || num > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be greater than or equal to "
                            + min
                            + ", and less than or equal to "
                            + max).intern());
        }
    }

    /**
     * @param min if -1, it means the string cannot be null
     */
    public static void length(@Nullable String s, String name, int min, int max) {
        if (s == null) {
            if (min == -1) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        ("\""
                                + name
                                + "\" must not be null, and have a length greater than or equal to 0, and less than or equal to "
                                + max).intern());
            }
            return;
        }
        int length = s.length();
        if (length < min || length > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must have a length greater than or equal to "
                            + min
                            + ", and less than or equal to "
                            + max).intern());
        }
    }

    public static void maxLength(@Nullable String s, String name, int max) {
        if (s == null) {
            return;
        }
        int length = s.length();
        if (length > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must have a length less than or equal to "
                            + max).intern());
        }
    }

    public static void maxLength(@Nullable Collection<String> items, String name, int max) {
        if (items == null) {
            return;
        }
        for (String item : items) {
            int length = item.length();
            if (length > max) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        ("The item of \""
                                + name
                                + "\" must have a length less than or equal to "
                                + max).intern());
            }
        }
    }

    public static void noWhitespace(String s, String name) {
        if (StringUtils.containsWhitespace(s)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must not contain whitespace").intern());
        }
    }

    public static void maxBytes(@Nullable byte[] bytes, String name, int max) {
        if (bytes != null && bytes.length > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must have length less than "
                            + max).intern());
        }
    }

    public static void pastOrPresent(@Nullable Date date, String name) {
        if (date != null && date.getTime() > System.currentTimeMillis()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be a date in the past or in the present").intern());
        }
    }

    public static void before(
            @Nullable Date startDate,
            @Nullable Date endDate,
            String startDateName,
            String endDateName) {
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + endDateName
                            + "\" must not be before \""
                            + startDateName
                            + "\"").intern());
        }
    }

    public static void shouldTrue(boolean expression, String message) {
        if (!expression) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

    public static void ip(@Nullable byte[] ip, String name) {
        if (ip != null && !InetAddressUtil.isIpV4OrV6(ip)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    ("\""
                            + name
                            + "\" must be a valid IP").intern());
        }
    }

    public static Exception url(@Nullable String url) {
        if (url != null) {
            try {
                new URL(url).toURI();
            } catch (Exception e) {
                return e;
            }
        }
        return null;
    }

}