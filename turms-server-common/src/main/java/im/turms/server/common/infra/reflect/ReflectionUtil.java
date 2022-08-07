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

import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.unsafe.UnsafeUtil;
import sun.misc.Unsafe;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

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
     * https://github.com/openjdk/jdk/commit/9c70e26c146ae4c5a2e2311948efec9bf662bb8c
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
            return LOOKUP.unreflectGetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getGetter(Field field) {
        try {
            field.setAccessible(true);
            return LOOKUP.unreflectGetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getSetter(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            return LOOKUP.unreflectSetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getSetter(Field field) {
        try {
            field.setAccessible(true);
            return LOOKUP.unreflectSetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static VarHandle getVarHandle(Class<?> clazz, String fieldName) {
        try {
            return MethodHandles
                    .privateLookupIn(clazz, LOOKUP)
                    .unreflectVarHandle(clazz.getDeclaredField(fieldName));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle method2Handle(Method method) {
        try {
            method.setAccessible(true);
            return LOOKUP.unreflect(method);
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

    @Nullable
    public static Class<?> getElementClass(Type type) {
        return type instanceof ParameterizedType parameterizedType
                ? (Class<?>) parameterizedType.getActualTypeArguments()[0]
                : null;
    }

    @Nullable
    public static Class<?> getIterableElementClass(Field field) {
        Class<?> fieldType = field.getType();
        if (Iterable.class.isAssignableFrom(fieldType)) {
            return getElementClass(field.getGenericType());
        }
        return null;
    }

    @Nullable
    public static Pair<Class<?>, Class<?>> getMapKeyClassAndElementClass(Field field) {
        Class<?> fieldType = field.getType();
        if (Map.class.isAssignableFrom(fieldType)) {
            ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
            Type[] actualTypes = parameterizedType.getActualTypeArguments();
            return Pair.of((Class<?>) actualTypes[0], (Class<?>) actualTypes[1]);
        }
        return null;
    }

}