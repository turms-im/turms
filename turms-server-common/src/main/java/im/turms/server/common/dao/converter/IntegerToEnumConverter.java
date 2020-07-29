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
import org.springframework.data.convert.ReadingConverter;

/**
 * @author James Chen
 */
@ReadingConverter
public class IntegerToEnumConverter<T extends Enum> implements Converter<Integer, Enum> {

    private final Class<T> enumType;

    public IntegerToEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public Enum convert(Integer source) {
        return enumType.getEnumConstants()[source];
    }

}
