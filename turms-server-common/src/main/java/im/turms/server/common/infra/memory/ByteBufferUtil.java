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

import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.unsafe.UnsafeUtil;
import lombok.SneakyThrows;
import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;

/**
 * @author James Chen
 */
public final class ByteBufferUtil {

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    private static final MethodHandle INVOKE_CLEANER;

    static {
        try {
            Method method = UNSAFE.getClass()
                    .getDeclaredMethod("invokeCleaner", ByteBuffer.class);
            INVOKE_CLEANER = ReflectionUtil.method2Handle(method);
            ByteBuffer buffer = ByteBuffer.allocateDirect(1);
            freeDirectBuffer(buffer);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    private ByteBufferUtil() {
    }

    public static ByteBuffer wrapAsDirect(byte[] bytes) {
        return ByteBuffer
                .allocateDirect(bytes.length)
                .put(bytes)
                .flip();
    }

    @SneakyThrows
    public static void freeDirectBuffer(ByteBuffer buffer) {
        INVOKE_CLEANER.invokeExact(UNSAFE, buffer);
    }

}
