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
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.List;

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
     * Though we get the offset via:
     * "jdk.internal.misc.Unsafe#objectFieldOffset(AccessibleObject.class, "override")",
     * we don't want to "add-exports" everywhere, which causes a bad development experience
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
            throw new RuntimeException(e);
        }
        // "AccessibleObject#override" should be the first field,
        // so their object offset should be the same
        OVERRIDE_OFFSET = UNSAFE.objectFieldOffset(field);
        if (field.isAccessible()) {
            throw new IllegalStateException("The private field should be inaccessible");
        }
        setAccessible(field);
        if (!field.isAccessible()) {
            throw new IllegalStateException("The private field should be accessible after updating \"override\" to true");
        }
    }

    private ReflectionUtil() {
    }

    public static List<Field> getNonStaticFields(Class<?> clazz) {
        List<Field> fields = new LinkedList<>();
        Class<?> currentClass = clazz;
        while (currentClass != null) {
            for (Field field : currentClass.getDeclaredFields()) {
                if (!Modifier.isStatic(field.getModifiers())) {
                    fields.add(field);
                }
            }
            currentClass = currentClass.getSuperclass();
        }
        return fields;
    }

    public static <T> MethodHandle getConstructor(Constructor<T> constructor) {
        setAccessible(constructor);
        try {
            return LOOKUP.unreflectConstructor(constructor);
        } catch (IllegalAccessException e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle getGetter(Class<?> clazz, String fieldName) {
        try {
            Field field = clazz.getDeclaredField(fieldName);
            setAccessible(field);
            return LOOKUP.unreflectGetter(field);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static MethodHandle method2Handle(Method method) {
        try {
            setAccessible(method);
            return LOOKUP.unreflect(method);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    /**
     * 1. Ignore the access control of the module system
     * 2. Better performance than {@link AccessibleObject#setAccessible(boolean)}:
     * {@link benchmark.im.turms.server.common.infra.reflect.SetAccessible}
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

    public static Class<?> getIterableElementClass(Field field) {
        return getElementClass(field.getGenericType());
    }

    public static Pair<Class<?>, Class<?>> getMapKeyClassAndElementClass(Field field) {
        ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
        Type[] actualTypes = parameterizedType.getActualTypeArguments();
        return Pair.of((Class<?>) actualTypes[0], (Class<?>) actualTypes[1]);
    }

    public static boolean isListOf(Type type, Class<?> elementClass) {
        if (!(type instanceof ParameterizedType parameterizedType &&
                parameterizedType.getRawType() instanceof Class<?> rawType &&
                List.class.isAssignableFrom(rawType))) {
            return false;
        }
        Type[] arguments = parameterizedType.getActualTypeArguments();
        return arguments.length == 1 && arguments[0] instanceof Class<?> argClass && elementClass.isAssignableFrom(argClass);
    }

}