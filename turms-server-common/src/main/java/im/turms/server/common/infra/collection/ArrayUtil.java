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

package im.turms.server.common.infra.collection;

import java.lang.reflect.Array;

/**
 * @author James Chen
 */
public final class ArrayUtil {

    private ArrayUtil() {
    }

    public static byte[] concat(byte[] a, byte[] b) {
        int aLength = a.length;
        int bLength = b.length;
        byte[] result = new byte[aLength + bLength];
        System.arraycopy(a, 0, result, 0, aLength);
        System.arraycopy(b, 0, result, aLength, bLength);
        return result;
    }

    public static byte[] concat(byte[] a, byte b) {
        int length = a.length;
        byte[] result = new byte[length + 1];
        System.arraycopy(a, 0, result, 0, length);
        result[length] = b;
        return result;
    }

    public static Object[] getArray(Object value) {
        if (value instanceof Object[] values) {
            return values;
        }
        int length = Array.getLength(value);
        Object[] outputArray = new Object[length];
        for (int i = 0; i < length; ++i) {
            outputArray[i] = Array.get(value, i);
        }
        return outputArray;
    }

}