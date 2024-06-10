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

import java.math.BigDecimal;
import java.math.BigInteger;

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

    public static int toInt(long value) {
        if (value > Integer.MAX_VALUE) {
            return Integer.MAX_VALUE;
        }
        if (value < Integer.MIN_VALUE) {
            return Integer.MIN_VALUE;
        }
        return (int) value;
    }

    public static BigDecimal toBigDecimalExact(Object value) {
        return switch (value) {
            case BigDecimal val -> val;
            case String val -> new BigDecimal(val);
            case Byte val -> new BigDecimal(val);
            case Short val -> new BigDecimal(val);
            case Integer val -> new BigDecimal(val);
            case Long val -> new BigDecimal(val);
            case Float val -> BigDecimal.valueOf(val);
            case Double val -> BigDecimal.valueOf(val);
            default -> throw new IllegalArgumentException(
                    "The value is not a number: "
                            + value);
        };
    }

    public static BigInteger toBigIntegerExact(Object value) {
        return switch (value) {
            case BigInteger val -> val;
            case String val -> new BigInteger(val);
            case Byte val -> BigInteger.valueOf(val);
            case Short val -> BigInteger.valueOf(val);
            case Integer val -> BigInteger.valueOf(val);
            case Long val -> BigInteger.valueOf(val);
            case Float val -> {
                if (val % 1 == 0) {
                    yield BigInteger.valueOf(val.longValue());
                }
                throw new IllegalArgumentException(
                        "The value is not an integer: "
                                + value);
            }
            case Double val -> {
                if (val % 1 == 0) {
                    yield BigInteger.valueOf(val.longValue());
                }
                throw new IllegalArgumentException(
                        "The value is not an integer: "
                                + value);
            }
            default -> throw new IllegalArgumentException(
                    "The value is not an integer: "
                            + value);
        };
    }

}