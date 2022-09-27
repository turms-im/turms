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

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * @author James Chen
 */
public final class StringUtil {

    private static final byte LATIN1 = 0;
    private static final byte UTF16 = 1;

    private static final byte SINGLE_TOKEN = '?';
    private static final byte MULTIPLE_TOKEN = '*';

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    private static final long STRING_VALUE_OFFSET;
    private static final long STRING_CODER_OFFSET;
    private static final MethodHandle NEW_STRING;

    static {
        try {
            Constructor<String> constructor = String.class.getDeclaredConstructor(byte[].class, byte.class);
            NEW_STRING = ReflectionUtil.getConstructor(constructor);
            STRING_VALUE_OFFSET = UNSAFE.objectFieldOffset(String.class.getDeclaredField("value"));
            STRING_CODER_OFFSET = UNSAFE.objectFieldOffset(String.class.getDeclaredField("coder"));
        } catch (Throwable e) {
            throw new IllegalStateException(e);
        }
        // Validate
        String text = "abc";
        byte[] actual = getBytes(newString(getBytes(text), getCoder(text)));
        if (!Arrays.equals(actual, text.getBytes())) {
            throw new IllegalStateException("Validation failed");
        }
    }

    private StringUtil() {
    }

    public static int countOccurrencesLatin1(String s, byte character) {
        int count = 0;
        byte[] bytes = getBytes(s);
        for (byte b : bytes) {
            if (b == character) {
                count++;
            }
        }
        return count;
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

    public static String joinLatin1(String separator, Collection<?> items) {
        int separatorLength = separator.length();
        int itemCount = items.size();
        if (itemCount == 0) {
            return "";
        }
        if (itemCount == 1) {
            return String.valueOf(items instanceof List<?> list
                    ? list.get(0)
                    : items.iterator().next());
        }
        List<String> strings = new ArrayList<>(itemCount);
        int size = 0;
        String str;
        for (Object item : items) {
            str = String.valueOf(item);
            size += str.length();
            strings.add(str);
        }
        size += separatorLength * (itemCount - 1);
        byte[] newStringBytes = new byte[size];
        int writerIndex = 0;
        byte[] bytes;
        byte[] separatorBytes = getBytes(separator);
        for (int i = 0; i < itemCount; i++) {
            String s = strings.get(i);
            bytes = getBytes(s);
            System.arraycopy(bytes, 0, newStringBytes, writerIndex, bytes.length);
            writerIndex += bytes.length;
            if (i < itemCount - 1) {
                System.arraycopy(separatorBytes, 0, newStringBytes, writerIndex, separatorBytes.length);
                writerIndex += separatorBytes.length;
            }
        }
        return newLatin1String(newStringBytes);
    }

    public static boolean matchLatin1(String text, String pattern) {
        byte[] textBytes = getBytes(text);
        byte[] patternBytes = getBytes(pattern);
        int textReaderIndex = 0;
        int patternReaderIndex = 0;
        int textReaderIndexForMultipleToken = -1;
        int patternReaderIndexForMultipleToken = -1;
        int textLength = textBytes.length;
        int patternLength = patternBytes.length;

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

    public static boolean matchLatin1(String text, Collection<String> patterns) {
        for (String pattern : patterns) {
            if (!matchLatin1(text, pattern)) {
                return false;
            }
        }
        return true;
    }

    public static <T> List<T> findMatchesLatin1(T[] values, String pattern) {
        List<T> newValues = null;
        for (T value : values) {
            if (matchLatin1(value.toString(), pattern)) {
                if (newValues == null) {
                    newValues = new LinkedList<>();
                }
                newValues.add(value);
            }
        }
        return newValues == null
                ? Collections.emptyList()
                : newValues;
    }

    public static String padStartLatin1(String string, int minLength, byte padChar) {
        int length = string.length();
        if (length >= minLength) {
            return string;
        }
        byte[] bytes = getBytes(string);
        byte[] dest = new byte[minLength];
        int padLength = minLength - length;
        for (int i = 0; i < padLength; i++) {
            dest[i] = padChar;
        }
        System.arraycopy(bytes, 0, dest, padLength, length);
        return newLatin1String(dest);
    }

    public static String replaceLatin1(String message, byte oldByte, byte newByte) {
        byte[] bytes = getBytes(message);
        byte[] newBytes = null;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == oldByte) {
                if (newBytes == null) {
                    newBytes = Arrays.copyOf(bytes, bytes.length);
                }
                newBytes[i] = newByte;
            }
        }
        if (newBytes == null) {
            return message;
        }
        return newLatin1String(newBytes);
    }

    @Nullable
    public static Pair<String, String> splitLatin1(String toSplit, byte delimiter) {
        return splitLatin1(getBytes(toSplit), delimiter);
    }

    @Nullable
    public static Pair<String, String> splitLatin1(byte[] bytes, byte delimiter) {
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == delimiter) {
                byte[] first = new byte[i];
                byte[] second = new byte[bytes.length - i - 1];
                System.arraycopy(bytes, 0, first, 0, first.length);
                System.arraycopy(bytes, first.length + 1, second, 0, second.length);
                return Pair.of(newLatin1String(first), newLatin1String(second));
            }
        }
        return null;
    }

    @Nullable
    public static List<String> splitMultipleLatin1(String toSplit, byte delimiter) {
        return splitMultipleLatin1(getBytes(toSplit), delimiter);
    }

    @Nullable
    public static List<String> splitMultipleLatin1(byte[] bytes, byte delimiter) {
        List<String> results = null;
        int begin = 0;
        byte[] stringBytes;
        for (int i = 0; i < bytes.length; i++) {
            if (bytes[i] == delimiter) {
                stringBytes = new byte[i - begin];
                System.arraycopy(bytes, begin, stringBytes, 0, stringBytes.length);
                begin = i + 1;
                if (results == null) {
                    results = new ArrayList<>(8);
                }
                results.add(newLatin1String(stringBytes));
            }
        }
        if (begin != 0) {
            stringBytes = new byte[bytes.length - begin];
            System.arraycopy(bytes, begin, stringBytes, 0, stringBytes.length);
            results.add(newLatin1String(stringBytes));
        }
        return results;
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
        byte[] newBytes = new byte[length + argBytesLength];
        int writeIndex = 0;
        byte b;
        byte[] argBytes;
        for (int i = 0; i < length; i++) {
            b = bytes[i];
            if (b == '{' && i < length - 1 && bytes[i + 1] == '}') {
                if (argIndex >= argCount) {
                    throw new IllegalArgumentException("The number of placeholder \"{}\" must be the same as the number of arguments");
                }
                argBytes = getBytes(String.valueOf(args[argIndex++]));
                System.arraycopy(argBytes, 0, newBytes, writeIndex, argBytes.length);
                writeIndex += argBytes.length;
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

    public static String substitute(String message, Object... args) {
        int length = args.length;
        String[] strings = new String[length];
        for (int i = 0; i < length; i++) {
            strings[i] = String.valueOf(args[i]);
        }
        return substitute(message, strings);
    }

    public static String substringToFirstDelimiter(String message, char toFirstDelimiter) {
        int delimiterIndex = message.indexOf(toFirstDelimiter);
        if (delimiterIndex != -1) {
            return message.substring(0, delimiterIndex);
        }
        return message;
    }

    public static String substringToLastDelimiter(String message, char toLastDelimiter) {
        int delimiterIndex = message.lastIndexOf(toLastDelimiter);
        if (delimiterIndex != -1) {
            return message.substring(0, delimiterIndex);
        }
        return message;
    }

    public static String toString(Object val) {
        return val == null ? "" : val.toString();
    }

    public static boolean isBlank(String string) {
        return string == null || string.isBlank();
    }

    public static boolean isNotBlank(String string) {
        return string != null && !string.isBlank();
    }

    public static boolean isEmpty(String string) {
        return string == null || string.isEmpty();
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

    // Case format conversion

    /**
     * e.g. "helloWorld" -> "hello-world"
     */
    public static String lowerCamelToLowerHyphenLatin1(String string) {
        return upperCamelToLowerHyphenLatin1(string);
    }

    /**
     * e.g. "HelloWorld" -> "helloWorld"
     */
    public static String upperCamelToLowerCamelLatin1(String string) {
        byte[] bytes = getBytes(string);
        int length = bytes.length;
        byte[] dest = new byte[length];
        if (length > 1) {
            System.arraycopy(bytes, 1, dest, 1, length - 1);
            dest[0] = (byte) Character.toLowerCase(bytes[0]);
        } else if (length == 1) {
            dest[0] = (byte) Character.toLowerCase(bytes[0]);
        }
        return newLatin1String(dest);
    }

    /**
     * e.g. "HelloWorld" -> "HELLO_WORLD"
     */
    public static String upperCamelToUpperUnderscoreLatin1(String string) {
        byte[] bytes = getBytes(string);
        int length = bytes.length;
        int destLength = length;
        byte b;
        for (int i = 0; i < length; i++) {
            b = bytes[i];
            if (Character.isUpperCase(b) && shouldAppendHyphenOrUnderscore(bytes, i)) {
                destLength++;
            }
        }
        byte[] dest = new byte[destLength];
        for (int i = 0, j = 0; i < length; i++) {
            b = bytes[i];
            if (Character.isUpperCase(b)) {
                if (shouldAppendHyphenOrUnderscore(bytes, i)) {
                    dest[j++] = '_';
                }
            } else {
                b = (byte) Character.toUpperCase(b);
            }
            dest[j++] = b;
        }
        return newLatin1String(dest);
    }

    /**
     * e.g. "HelloWorld" -> "hello-world"
     */
    public static String upperCamelToLowerHyphenLatin1(String string) {
        byte[] bytes = getBytes(string);
        int length = bytes.length;
        int destLength = length;
        byte b;
        for (int i = 0; i < length; i++) {
            b = bytes[i];
            if (Character.isUpperCase(b) && shouldAppendHyphenOrUnderscore(bytes, i)) {
                destLength++;
            }
        }
        byte[] dest = new byte[destLength];
        for (int i = 0, j = 0; i < length; i++) {
            b = bytes[i];
            if (Character.isUpperCase(b)) {
                b = (byte) Character.toLowerCase(b);
                if (shouldAppendHyphenOrUnderscore(bytes, i)) {
                    dest[j++] = '-';
                }
            }
            dest[j++] = b;
        }
        return newLatin1String(dest);
    }

    private static boolean shouldAppendHyphenOrUnderscore(byte[] bytes, int index) {
        if (index == 0) {
            // Never put a hyphen or underscore at the beginning
            return false;
        } else if (!Character.isUpperCase(bytes[index - 1])) {
            // Append if previous char wasn't upper case
            return true;
        } else if (index + 1 < bytes.length && !Character.isUpperCase(bytes[index + 1])) {
            // Append if next char isn't upper case
            return true;
        } else {
            return false;
        }
    }
}
