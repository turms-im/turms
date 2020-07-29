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

package im.turms.server.common.dao.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.lang.NonNull;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 */
public class IntegerToEnumConverterFactory implements ConverterFactory<Integer, Enum> {

    private static final Map<Class<?>, IntegerToEnumConverter> POOL = new HashMap<>();

    @Override
    @NonNull
    public <T extends Enum> Converter<Integer, T> getConverter(@Nullable Class<T> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        if (enumType == null) {
            throw new IllegalArgumentException("The target type does not refer to an enum");
        }
        return POOL.computeIfAbsent(enumType, clazz -> new IntegerToEnumConverter(clazz));
    }

}
