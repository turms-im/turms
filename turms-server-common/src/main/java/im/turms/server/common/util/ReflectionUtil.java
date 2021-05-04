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

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author James Chen
 */
public final class ReflectionUtil {

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

    public static MethodHandle method2Handle(Method method) {
        try {
            method.setAccessible(true);
            return MethodHandles.lookup().unreflect(method);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

}