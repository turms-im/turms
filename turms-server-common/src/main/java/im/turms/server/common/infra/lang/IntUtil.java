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

package im.turms.server.common.infra.lang;

import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
public final class IntUtil {

    private IntUtil() {
    }

    public static int fromBytes(byte b1, byte b2, byte b3, byte b4) {
        return b1 << 24 | (b2 & 0xFF) << 16 | (b3 & 0xFF) << 8 | (b4 & 0xFF);
    }

    /**
     * {@link Integer#parseInt}
     */
    @Nullable
    public static Integer tryParse(String s) {
        int length = s.length();
        if (length <= 0) {
            return null;
        }
        boolean negative = false;
        int limit = -Integer.MAX_VALUE;
        int i = 0;
        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                negative = true;
                limit = Integer.MIN_VALUE;
            } else if (firstChar != '+') {
                return null;
            }
            if (length == 1) { // Cannot have lone "+" or "-"
                return null;
            }
            i++;
        }
        int multmin = limit / 10;
        int result = 0;
        while (i < length) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int digit = Character.digit(s.charAt(i++), 10);
            if (digit < 0 || result < multmin) {
                return null;
            }
            result *= 10;
            if (result < limit + digit) {
                return null;
            }
            result -= digit;
        }
        return negative
                ? result
                : -result;
    }

}