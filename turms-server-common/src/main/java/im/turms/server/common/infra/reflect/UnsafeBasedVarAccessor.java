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

package im.turms.server.common.infra.reflect;

import java.lang.reflect.Field;

import lombok.Data;
import sun.misc.Unsafe;

import im.turms.server.common.infra.unsafe.UnsafeUtil;

/**
 * @author James Chen
 * @implNote Note that the implementation only supports getting and setting the object reference,
 *           and does NOT support primitives
 */
@Data
public class UnsafeBasedVarAccessor<T, V> implements VarAccessor<T, V> {

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;

    private final Class<?> declaringClass;
    private final Class<?> fieldClass;
    private final long fieldOffset;

    public UnsafeBasedVarAccessor(Field field) {
        declaringClass = field.getDeclaringClass();
        fieldClass = field.getType();
        if (fieldClass.isPrimitive()) {
            throw new IllegalArgumentException(
                    "The field type ("
                            + fieldClass.getName()
                            + ") cannot be primitive");
        }
        if (declaringClass.isRecord()) {
            throw new IllegalArgumentException(
                    "The declaring class ("
                            + declaringClass.getName()
                            + ") cannot be record");
        }
        fieldOffset = UNSAFE.objectFieldOffset(field);
    }

    @Override
    public V get(T object) {
        if (!declaringClass.isAssignableFrom(object.getClass())) {
            throw new IllegalArgumentException(
                    "The object class must be the class or the subclass of: "
                            + declaringClass.getName()
                            + ", but got: "
                            + object.getClass()
                                    .getName());
        }
        return (V) UNSAFE.getObject(object, fieldOffset);
    }

    @Override
    public void set(T object, V value) {
        if (!declaringClass.isAssignableFrom(object.getClass())) {
            throw new IllegalArgumentException(
                    "The object class must be the class or the subclass of: "
                            + declaringClass.getName()
                            + ", but got: "
                            + object.getClass()
                                    .getName());
        }
        if (!fieldClass.isAssignableFrom(value.getClass())) {
            throw new IllegalArgumentException(
                    "The value class must be the class or the subclass of: "
                            + fieldClass.getName()
                            + ", but got: "
                            + value.getClass()
                                    .getName());
        }
        UNSAFE.putObject(object, fieldOffset, value);
    }

}
