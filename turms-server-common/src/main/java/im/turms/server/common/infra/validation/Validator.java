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

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.exception.ResponseException;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Date;

/**
 * @author James Chen
 */
public final class Validator {

    private Validator() {
    }

    public static boolean areAllFalsy(Object... array) {
        if (array == null) {
            return true;
        }
        for (Object o : array) {
            if (o != null) {
                if (o instanceof String) {
                    if (!((String) o).isEmpty()) {
                        return false;
                    }
                } else if (o instanceof Collection) {
                    if (!((Collection<?>) o).isEmpty()) {
                        return false;
                    }
                } else if (o.getClass().isArray()) {
                    if (Array.getLength(o) > 0) {
                        return false;
                    }
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean areAllNull(Object... array) {
        if (array == null) {
            return true;
        } else {
            for (Object o : array) {
                if (o != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void notNull(Object object, String name) {
        if (object == null) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must not be null");
        }
    }

    public static void notEmpty(Collection<?> collection, String name) {
        if (collection == null || collection.isEmpty()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must not be null or empty");
        }
    }

    public static void notEmpty(Object[] array, String name) {
        if (array == null || array.length == 0) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must not be null or empty");
        }
    }

    public static void throwIfAllFalsy(String message, @NotEmpty Object... array) {
        if (areAllFalsy(array)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

    public static void min(Integer num, String name, int min) {
        if (num != null && num < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must be greater than or equal to " + min);
        }
    }

    public static void min(int num, String name, int min) {
        if (num < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must be greater than or equal to " + min);
        }
    }

    public static void max(Integer num, String name, int max) {
        if (num != null && num > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must be less than or equal to " + max);
        }
    }

    public static void max(int num, String name, int max) {
        if (num > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must be less than or equal to " + max);
        }
    }

    public static void inRange(double num, String name, double min, double max) {
        if (num > max || num < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    name + " must be less than or equal to " + max + ", and greater than or equal to " + min);
        }
    }

    public static void length(String s, String name, int min, int max) {
        if (s != null) {
            int length = s.length();
            if (length < min || length > max) {
                throw ResponseException
                        .get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must have length between " + min + " and " + max);
            }
        }
    }

    public static void maxLength(String s, String name, int max) {
        if (s != null) {
            int length = s.length();
            if (length > max) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must have length less than " + max);
            }
        }
    }

    public static void noWhitespace(String s, String name) {
        if (StringUtils.containsWhitespace(s)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must not contain whitespace");
        }
    }

    public static void maxBytes(byte[] bytes, String name, int max) {
        if (bytes != null && bytes.length > max) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must have length less than " + max);
        }
    }

    public static void pastOrPresent(Date date, String name) {
        if (date != null && date.getTime() > System.currentTimeMillis()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, name + " must be a date in the past or in the present");
        }
    }

    public static void before(Date startDate, Date endDate, String startDateName, String endDateName) {
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, endDateName + " must not be before " + startDateName);
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

}
