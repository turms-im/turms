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

import java.lang.reflect.Array;
import java.util.Collection;

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

}