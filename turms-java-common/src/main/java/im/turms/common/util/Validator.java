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

package im.turms.common.util;

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;

/**
 * @author James Chen
 */
public class Validator {
    private Validator() {
    }

    public static void throwIfAnyFalsy(@NotEmpty Object... array) {
        for (Object o : array) {
            if (o == null) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The required values must not be null");
            } else {
                if (o instanceof String) {
                    if (((String) o).isEmpty()) {
                        throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The string value must not be blank");
                    }
                } else if (o instanceof Collection && ((Collection<?>) o).isEmpty()) {
                    throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The collection value must not be empty");
                }
            }
        }
    }

    public static void throwIfAllFalsy(String message, @NotEmpty Object... array) {
        if (areAllFalsy(array)) {
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, message);
        }
    }

    public static boolean areAllFalsy(Object... array) {
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

}
