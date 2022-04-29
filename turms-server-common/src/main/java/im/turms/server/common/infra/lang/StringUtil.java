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
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Constructor;
import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public final class StringUtil {

    public static final byte LATIN1 = 0;
    public static final byte UTF16 = 1;

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    private static final long STRING_VALUE_OFFSET;
    private static final long STRING_CODER_OFFSET;
    private static final MethodHandle NEW_STRING;

    static {
        try {
            Constructor<String> constructor = String.class.getDeclaredConstructor(byte[].class, byte.class);
            ReflectionUtil.setAccessible(constructor);
            NEW_STRING = MethodHandles.lookup().unreflectConstructor(constructor);
            STRING_VALUE_OFFSET = UNSAFE.objectFieldOffset(String.class.getDeclaredField("value"));
            STRING_CODER_OFFSET = UNSAFE.objectFieldOffset(String.class.getDeclaredField("coder"));

            String test = StringUtil.newString(StringUtil.getBytes(""), StringUtil.getCoder(""));
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

    @SneakyThrows
    public static String newLatin1String(byte[] bytes) {
        return (String) NEW_STRING.invokeExact(bytes, LATIN1);
    }

}
