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

package im.turms.server.common.util;

import sun.misc.Unsafe;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author James Chen
 */
public final class ReflectionUtil {

    private static final Unsafe UNSAFE = UnsafeUtil.UNSAFE;
    /**
     * "UNSAFE.objectFieldOffset(AccessibleObject.class.getDeclaredField("override"))" won't work
     * because Java hides these fields: https://bugs.openjdk.java.net/browse/JDK-8210522
     * So we get the offset via:
     * "jdk.internal.misc.Unsafe#objectFieldOffset(AccessibleObject.class, "override")"
     */
    private static final long OVERRIDE_OFFSET = 12;

    private ReflectionUtil() {
    }

    public static MethodHandle getGetter(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return MethodHandles.lookup().unreflectGetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getGetter(Field field) {
        try {
            field.setAccessible(true);
            return MethodHandles.lookup().unreflectGetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getSetter(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return MethodHandles.lookup().unreflectSetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getSetter(Field field) {
        try {
            field.setAccessible(true);
            return MethodHandles.lookup().unreflectSetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static VarHandle getVarHandle(Class<?> clazz, String fieldName) {
        try {
            return MethodHandles
                    .privateLookupIn(clazz, MethodHandles.lookup())
                    .unreflectVarHandle(clazz.getDeclaredField(fieldName));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle method2Handle(Method method) {
        try {
            method.setAccessible(true);
            return MethodHandles.lookup().unreflect(method);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * The method can ignore the access control of the module system
     */
    public static void setAccessible(AccessibleObject object) {
        UNSAFE.putBoolean(object, OVERRIDE_OFFSET, true);
    }

}