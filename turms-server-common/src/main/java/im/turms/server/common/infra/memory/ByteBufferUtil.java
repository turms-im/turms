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

package im.turms.server.common.infra.memory;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

import sun.misc.Unsafe;

import im.turms.server.common.infra.exception.IncompatibleJvmException;
import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.unsafe.UnsafeUtil;

/**
 * @author James Chen
 */
public final class ByteBufferUtil {

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    private static final MethodHandle INVOKE_CLEANER;

    static {
        Method method;
        try {
            method = UNSAFE.getClass()
                    .getDeclaredMethod("invokeCleaner", ByteBuffer.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleJvmException(
                    "Failed to find the method: sun.misc.Unsafe#invokeCleaner",
                    e);
        }
        INVOKE_CLEANER = ReflectionUtil.method2Handle(method);
        ByteBuffer buffer = ByteBuffer.allocateDirect(1);
        freeDirectBuffer(buffer);
    }

    private ByteBufferUtil() {
    }

    public static ByteBuffer wrapAsDirect(byte[] bytes) {
        return ByteBuffer.allocateDirect(bytes.length)
                .put(bytes)
                .flip();
    }

    public static void freeDirectBuffer(ByteBuffer buffer) {
        try {
            INVOKE_CLEANER.invokeExact(UNSAFE, buffer);
        } catch (Throwable e) {
            throw new RuntimeException(
                    "Failed to free the direct buffer: "
                            + buffer,
                    e);
        }
    }

}