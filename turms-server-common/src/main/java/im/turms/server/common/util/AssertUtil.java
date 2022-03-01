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

import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Date;

import static im.turms.common.util.Validator.areAllFalsy;

/**
 * @author James Chen
 */
public final class AssertUtil {

    private AssertUtil() {
    }

    public static void notNull(Object object, String name) {
        if (object == null) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must not be null");
        }
    }

    public static void notEmpty(Collection<?> collection, String name) {
        if (collection == null || collection.isEmpty()) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must not be null or empty");
        }
    }

    public static void notEmpty(Object[] array, String name) {
        if (array == null || array.length == 0) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must not be null or empty");
        }
    }

    public static void throwIfAllFalsy(String message, @NotEmpty Object... array) {
        if (areAllFalsy(array)) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

    public static void min(Integer num, String name, int min) {
        if (num != null && num < min) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must be greater than or equal to " + min);
        }
    }

    public static void min(int num, String name, int min) {
        if (num < min) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must be greater than or equal to " + min);
        }
    }

    public static void max(Integer num, String name, int max) {
        if (num != null && num > max) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must be less than or equal to " + max);
        }
    }

    public static void max(int num, String name, int max) {
        if (num > max) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must be less than or equal to " + max);
        }
    }

    public static void inRange(double num, String name, double min, double max) {
        if (num > max || num < min) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT,
                    name + " must be less than or equal to " + max + ", and greater than or equal to " + min);
        }
    }

    public static void length(String s, String name, int min, int max) {
        if (s != null) {
            int length = s.length();
            if (length < min || length > max) {
                throw TurmsBusinessException
                        .get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must have length between " + min + " and " + max);
            }
        }
    }

    public static void maxLength(String s, String name, int max) {
        if (s != null) {
            int length = s.length();
            if (length > max) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must have length less than " + max);
            }
        }
    }

    public static void noWhitespace(String s, String name) {
        if (StringUtils.containsWhitespace(s)) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must not contain whitespace");
        }
    }

    public static void maxBytes(byte[] bytes, String name, int max) {
        if (bytes != null && bytes.length > max) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must have length less than " + max);
        }
    }

    public static void pastOrPresent(Date date, String name) {
        if (date != null && date.getTime() > System.currentTimeMillis()) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, name + " must be a date in the past or in the present");
        }
    }

    public static void before(Date startDate, Date endDate, String startDateName, String endDateName) {
        if (startDate != null && endDate != null && endDate.before(startDate)) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, endDateName + " must not be before " + startDateName);
        }
    }

    public static void state(boolean expression, String message) {
        if (!expression) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, message);
        }
    }

}
