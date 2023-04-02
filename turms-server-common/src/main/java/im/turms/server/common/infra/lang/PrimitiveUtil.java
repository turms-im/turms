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

import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

/**
 * @author James Chen
 */
public final class PrimitiveUtil {

    private static final Character CHAR_DEFAULT = '\0';
    private static final Byte BYTE_DEFAULT = 0;
    private static final Short SHORT_DEFAULT = 0;
    private static final Integer INT_DEFAULT = 0;
    private static final Long LONG_DEFAULT = 0L;
    private static final Float FLOAT_DEFAULT = 0F;
    private static final Double DOUBLE_DEFAULT = 0D;

    private static final Set<Object> WRAPPERS = Set.of(Byte.class,
            Short.class,
            Integer.class,
            Long.class,
            Float.class,
            Double.class,
            Boolean.class,
            Character.class);
    private static final Map<Class<?>, Class<?>> PRIMITIVE_TO_WRAPPER = Map.of(boolean.class,
            Boolean.class,
            byte.class,
            Byte.class,
            char.class,
            Character.class,
            short.class,
            Short.class,
            int.class,
            Integer.class,
            long.class,
            Long.class,
            double.class,
            Double.class,
            float.class,
            Float.class);

    private PrimitiveUtil() {
    }

    public static boolean isPrimitiveOrWrapperClass(Class<?> type) {
        return type.isPrimitive() || isWrapperClass(type);
    }

    public static boolean isWrapperClass(Class<?> type) {
        return WRAPPERS.contains(type);
    }

    @Nullable
    public static <T> T getDefaultValue(Class<T> type) {
        if (!type.isPrimitive()) {
            return null;
        }
        if (type == boolean.class) {
            return (T) Boolean.FALSE;
        } else if (type == char.class) {
            return (T) CHAR_DEFAULT;
        } else if (type == byte.class) {
            return (T) BYTE_DEFAULT;
        } else if (type == short.class) {
            return (T) SHORT_DEFAULT;
        } else if (type == int.class) {
            return (T) INT_DEFAULT;
        } else if (type == long.class) {
            return (T) LONG_DEFAULT;
        } else if (type == float.class) {
            return (T) FLOAT_DEFAULT;
        } else if (type == double.class) {
            return (T) DOUBLE_DEFAULT;
        }
        return null;
    }

    public static Class<?> primitiveToWrapper(Class<?> clazz) {
        return PRIMITIVE_TO_WRAPPER.get(clazz);
    }
}
