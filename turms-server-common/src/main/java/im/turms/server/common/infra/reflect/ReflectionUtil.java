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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import sun.misc.Unsafe;

import im.turms.server.common.infra.exception.IncompatibleJvmException;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.unsafe.UnsafeUtil;

/**
 * @author James Chen
 */
public final class ReflectionUtil {

    public static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    /**
     * "UNSAFE.objectFieldOffset(AccessibleObject.class.getDeclaredField("override"))" won't work
     * because Java hides {@link java.lang.reflect.AccessibleObject#override} in
     * https://bugs.openjdk.java.net/browse/JDK-8210522
     * https://github.com/openjdk/jdk/commit/9c70e26c146ae4c5a2e2311948efec9bf662bb8c Though we get
     * the offset via: "jdk.internal.misc.Unsafe#objectFieldOffset(AccessibleObject.class,
     * "override")", we don't want to "add-exports" everywhere, which causes a bad development
     * experience
     */
    private static final long OVERRIDE_OFFSET;

    static {
        Field field;
        try {
            class OverrideTest {
                private boolean firstField;
            }
            field = OverrideTest.class.getDeclaredField("firstField");
        } catch (NoSuchFieldException e) {
            throw new IncompatibleJvmException("Failed to find the first field of a class", e);
        }
        // "AccessibleObject#override" should be the first field,
        // so their object offset should be the same
        OVERRIDE_OFFSET = UNSAFE.objectFieldOffset(field);
        if (field.isAccessible()) {
            throw new IncompatibleJvmException(
                    "The private field should be inaccessible by default");
        }
        setAccessible(field);
        if (!field.isAccessible()) {
            throw new IncompatibleJvmException(
                    "The private field should be accessible after updating \"override\" to true");
        }
    }

    private ReflectionUtil() {
    }

    public static <T> MethodHandle getConstructor(Constructor<T> constructor) {
        setAccessible(constructor);
        try {
            return LOOKUP.unreflectConstructor(constructor);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Failed to unreflect the constructor: "
                            + constructor.getName(),
                    e);
        }
    }

    public static MethodHandle getGetter(Class<?> clazz, String fieldName) {
        Field field = null;
        try {
            field = clazz.getDeclaredField(fieldName);
            setAccessible(field);
            return LOOKUP.unreflectGetter(field);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException(
                    "The class ("
                            + clazz.getName()
                            + ") does not have the field: \""
                            + fieldName
                            + "\"",
                    e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Failed to reflect the getter for the field: "
                            + ClassUtil.getReference(field),
                    e);
        }
    }

    public static MethodHandle method2Handle(Method method) {
        try {
            setAccessible(method);
            return LOOKUP.unreflect(method);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Failed to reflect the method: "
                            + ClassUtil.getReference(method),
                    e);
        }
    }

    /**
     * 1. Ignore the access control of the module system 2. Better performance than
     * {@link AccessibleObject#setAccessible(boolean)}:
     * {@link benchmark.im.turms.server.common.infra.reflect.SetAccessible}
     */
    public static void setAccessible(AccessibleObject object) {
        if (object == null) {
            throw new IllegalArgumentException("The target object must not be null");
        }
        UNSAFE.putBoolean(object, OVERRIDE_OFFSET, true);
    }

}