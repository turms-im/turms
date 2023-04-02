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
public final class LongUtil {

    private LongUtil() {
    }

    public static byte[] toBytes(long v) {
        return new byte[]{(byte) v,
                (byte) (v >>> 8),
                (byte) (v >>> 16),
                (byte) (v >>> 24),
                (byte) (v >>> 32),
                (byte) (v >>> 40),
                (byte) (v >>> 48),
                (byte) (v >>> 56)};
    }

    /**
     * {@link Long#parseLong}
     */
    @Nullable
    public static Long tryParse(String s) {
        int length = s.length();
        if (length == 0) {
            return null;
        }
        boolean isNegative = false;
        int i = 0;
        long limit = -Long.MAX_VALUE;

        char firstChar = s.charAt(0);
        if (firstChar < '0') { // Possible leading "+" or "-"
            if (firstChar == '-') {
                isNegative = true;
                limit = Long.MIN_VALUE;
            } else if (firstChar != '+') {
                return null;
            }
            if (length == 1) { // Cannot have lone "+" or "-"
                return null;
            }
            i++;
        }
        long multmin = limit / 10;
        long result = 0;
        while (i < length) {
            // Accumulating negatively avoids surprises near MAX_VALUE
            int digit = CharUtil.digit(s.charAt(i++));
            if (digit < 0 || result < multmin) {
                return null;
            }
            result *= 10;
            if (result < limit + digit) {
                return null;
            }
            result -= digit;
        }
        return isNegative
                ? result
                : -result;
    }

}