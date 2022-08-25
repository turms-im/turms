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

import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.unsafe.UnsafeUtil;
import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public final class StringUtil {

    public static final byte LATIN1 = 0;
    public static final byte UTF16 = 1;

    private static final byte SINGLE_TOKEN = '?';
    private static final byte MULTIPLE_TOKEN = '*';

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    private static final long STRING_VALUE_OFFSET;
    private static final long STRING_CODER_OFFSET;
    private static final MethodHandle NEW_STRING;

    static {
        try {
            Constructor<String> constructor = String.class.getDeclaredConstructor(byte[].class, byte.class);
            ReflectionUtil.setAccessible(constructor);
            NEW_STRING = ReflectionUtil.LOOKUP.unreflectConstructor(constructor);
            STRING_VALUE_OFFSET = UNSAFE.objectFieldOffset(String.class.getDeclaredField("value"));
            STRING_CODER_OFFSET = UNSAFE.objectFieldOffset(String.class.getDeclaredField("coder"));

            // Validate
            newString(getBytes(""), getCoder(""));
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
    }

    private StringUtil() {
    }

    /**
     * Get the internal bytes of String without copying,
     * and the caller needs to ensure it won't modify the byte array
     */
    public static byte[] getBytes(String s) {
        try {
            return (byte[]) UNSAFE.getObject(s, STRING_VALUE_OFFSET);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * @implNote The method assumes the string does NOT contain any extended control character
     * if it's encoded in LATIN1
     * @see <a href="https://cs.stanford.edu/people/miles/iso8859.html">iso8859</a>
     */
    public static byte[] getUTF8Bytes(String s) {
        try {
            return getCoder(s) == LATIN1 ? getBytes(s) : s.getBytes(StandardCharsets.UTF_8);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static byte getCoder(String s) {
        try {
            return UNSAFE.getByte(s, STRING_CODER_OFFSET);
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static int getLength(String s) {
        return getBytes(s).length;
    }

    public static boolean match(String text, String pattern) {
        byte[] textBytes = getBytes(text);
        byte[] patternBytes = getBytes(pattern);
        int textReaderIndex = 0;
        int patternReaderIndex = 0;
        int textReaderIndexForMultipleToken = -1;
        int patternReaderIndexForMultipleToken = -1;
        int textLength = text.length();
        int patternLength = pattern.length();

        while (textReaderIndex < textLength) {
            if (patternReaderIndex < patternLength
                    && (textBytes[textReaderIndex] == patternBytes[patternReaderIndex] || patternBytes[patternReaderIndex] == SINGLE_TOKEN)) {
                textReaderIndex++;
                patternReaderIndex++;
            } else if (patternReaderIndex < patternLength && patternBytes[patternReaderIndex] == MULTIPLE_TOKEN) {
                textReaderIndexForMultipleToken = textReaderIndex;
                patternReaderIndexForMultipleToken = patternReaderIndex++;
            } else if (textReaderIndexForMultipleToken >= 0) {
                textReaderIndex = ++textReaderIndexForMultipleToken;
                patternReaderIndex = patternReaderIndexForMultipleToken + 1;
            } else {
                return false;
            }
        }
        while (patternReaderIndex < patternLength && patternBytes[patternReaderIndex] == '*') {
            patternReaderIndex++;
        }
        return patternReaderIndex == patternLength;
    }

    /**
     * @implNote 1. The strings must be ASCII
     * and the method won't validate their coder for better performance.
     * 2. The number of placeholder "{}" must the same as the number of arguments,
     * or its will throw. We don't support mismatch to avoiding growing or shrinking the result string,
     * or preprocess the message, which cause a bad performance while it can be avoided.
     */
    public static String substitute(String message, String... args) {
        byte[] bytes = getBytes(message);
        int length = bytes.length;
        int argIndex = 0;
        int argCount = args.length;
        int argBytesLength = 0;
        for (String str : args) {
            argBytesLength += str == null ? 2 : str.length() - 2;
        }
        byte[] newBytes = new byte[bytes.length + argBytesLength];
        int writeIndex = 0;
        for (int i = 0; i < length; i++) {
            byte b = bytes[i];
            if (b == '{' && i < length - 1 && bytes[i + 1] == '}') {
                if (argIndex >= argCount) {
                    throw new IllegalArgumentException("The number of placeholder \"{}\" must be the same as the number of arguments");
                }
                for (byte argByte : getBytes(String.valueOf(args[argIndex++]))) {
                    newBytes[writeIndex++] = argByte;
                }
                i++;
            } else {
                newBytes[writeIndex++] = b;
            }
        }
        if (writeIndex != newBytes.length) {
            throw new IllegalArgumentException("The number of placeholder \"{}\" must be the same as the number of arguments");
        }
        return newString(newBytes, LATIN1);
    }

    public static String toString(Object val) {
        return val == null ? "" : val.toString();
    }

    public static boolean isLatin1(String s) {
        try {
            return UNSAFE.getByte(s, STRING_CODER_OFFSET) == LATIN1;
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean isLatin1(byte coder) {
        return coder == LATIN1;
    }

    @SneakyThrows
    public static String newString(byte[] bytes, byte coder) {
        return (String) NEW_STRING.invokeExact(bytes, coder);
    }

    public static String newLatin1String(byte[] srcBytes, int srcPos, int length) {
        byte[] bytes = new byte[length];
        System.arraycopy(srcBytes, srcPos, bytes, 0, length);
        return newLatin1String(bytes);
    }

    @SneakyThrows
    public static String newLatin1String(byte[] bytes) {
        return (String) NEW_STRING.invokeExact(bytes, LATIN1);
    }

}
