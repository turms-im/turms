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

package im.turms.server.common.infra.unsafe;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

import im.turms.server.common.infra.exception.IncompatibleJvmException;

/**
 * @author James Chen
 */
public class UnsafeUtil {

    /**
     * Though {@link jdk.internal.misc.Unsafe} is more powerful, we don't want to "add-exports"
     * everywhere, which causes a bad development experience
     */
    public static final Unsafe UNSAFE;

    static {
        Field field;
        try {
            field = Unsafe.class.getDeclaredField("theUnsafe");
        } catch (NoSuchFieldException e) {
            throw new IncompatibleJvmException("Missing field: sun.misc.Unsafe#theUnsafe", e);
        }
        field.setAccessible(true);
        try {
            UNSAFE = (Unsafe) field.get(null);
        } catch (IllegalAccessException e) {
            throw new IncompatibleJvmException(
                    "The field (sun.misc.Unsafe#theUnsafe) should be accessible",
                    e);
        }
    }

    private UnsafeUtil() {
    }

}
