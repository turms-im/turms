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

/**
 * @author James Chen
 */
public final class MathUtil {

    private MathUtil() {
    }

    /**
     * @see Math#addExact(int, int)
     */
    public static int add(int x, int y, int fallbackValue) {
        int r = x + y;
        if (((x ^ r) & (y ^ r)) < 0) {
            return fallbackValue;
        }
        return r;
    }

    /**
     * @see Math#addExact(long, long)
     */
    public static long add(long x, long y, long fallbackValue) {
        long r = x + y;
        if (((x ^ r) & (y ^ r)) < 0) {
            return fallbackValue;
        }
        return r;
    }

    /**
     * @see Math#multiplyExact(int, int)
     */
    public static int multiply(int x, int y, int fallbackValue) {
        long r = (long) x * (long) y;
        if ((int) r != r) {
            return fallbackValue;
        }
        return (int) r;
    }

    /**
     * @see Math#multiplyExact(long, long)
     */
    public static long multiply(long x, long y, long fallbackValue) {
        long r = x * y;
        long ax = Math.abs(x);
        long ay = Math.abs(y);
        if (((ax | ay) >>> 31 != 0)) {
            if (((y != 0) && (r / y != x)) || (x == Long.MIN_VALUE && y == -1)) {
                return fallbackValue;
            }
        }
        return r;
    }

}