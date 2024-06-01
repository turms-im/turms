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

package im.turms.server.common.infra.random;

import java.util.concurrent.ThreadLocalRandom;

import im.turms.server.common.infra.lang.StringUtil;

import static im.turms.server.common.infra.lang.StringUtil.ALPHABETIC_TABLE;
import static im.turms.server.common.infra.lang.StringUtil.ALPHABETIC_TABLE_LENGTH;
import static im.turms.server.common.infra.lang.StringUtil.ALPHANUMERIC_TABLE;
import static im.turms.server.common.infra.lang.StringUtil.ALPHANUMERIC_TABLE_LENGTH;
import static im.turms.server.common.infra.lang.StringUtil.NUMERIC_TABLE;
import static im.turms.server.common.infra.lang.StringUtil.NUMERIC_TABLE_LENGTH;

/**
 * @author James Chen
 */
public final class RandomUtil {

    private RandomUtil() {
    }

    public static int nextPositiveInt() {
        int i = ThreadLocalRandom.current()
                .nextInt() >>> 1;
        return i == 0
                ? 1
                : i;
    }

    public static long nextPositiveLong() {
        long l = ThreadLocalRandom.current()
                .nextLong() >>> 1;
        return l == 0L
                ? 1L
                : l;
    }

    public static String nextAlphabeticString(int minLengthInclusive, int maxLengthExclusive) {
        return nextString(minLengthInclusive,
                maxLengthExclusive,
                ALPHABETIC_TABLE,
                ALPHABETIC_TABLE_LENGTH);
    }

    public static String nextAlphanumericString(int minLengthInclusive, int maxLengthExclusive) {
        return nextString(minLengthInclusive,
                maxLengthExclusive,
                ALPHANUMERIC_TABLE,
                ALPHANUMERIC_TABLE_LENGTH);
    }

    public static String nextNumericString(int minLengthInclusive, int maxLengthExclusive) {
        return nextString(minLengthInclusive,
                maxLengthExclusive,
                NUMERIC_TABLE,
                NUMERIC_TABLE_LENGTH);
    }

    private static String nextString(
            int minLengthInclusive,
            int maxLengthExclusive,
            byte[] table,
            int tableLength) {
        ThreadLocalRandom random = ThreadLocalRandom.current();
        int length = random.nextInt(minLengthInclusive, maxLengthExclusive);
        if (length == 0) {
            return "";
        }
        byte[] bytes = new byte[length];
        for (int i = 0; i < length; i++) {
            bytes[i] = table[random.nextInt(tableLength)];
        }
        return StringUtil.newLatin1String(bytes);
    }

}