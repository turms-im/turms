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

package unit.im.turms.server.common.infra.property;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.HardcodedPropertyNameConst;
import im.turms.server.common.infra.property.TurmsProperties;

/**
 * @author James Chen
 */
class HardcodedPropertyNameConstTests {

    @Test
    void allPropertiesShouldBePresent() {
        Field[] fields = HardcodedPropertyNameConst.class.getDeclaredFields();
        for (Field field : fields) {
            ensureFieldExists(field);
        }
    }

    private static void ensureFieldExists(Field field) {
        int modifiers = field.getModifiers();
        if (!Modifier.isStatic(modifiers)) {
            return;
        }
        Object value;
        try {
            value = field.get(null);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(
                    "Failed to get the value of: "
                            + field.getName(),
                    e);
        }
        if (!(value instanceof String valueStr)) {
            throw new RuntimeException(
                    "The field value is not a string: "
                            + field.getName());
        }
        String[] parts = valueStr.split("\\.");
        String firstPart = parts[0];
        if (!TurmsProperties.PROPERTIES_PREFIX.equals(firstPart)) {
            throw new RuntimeException(
                    "The first part of the value of the field ("
                            + field.getName()
                            + ") is not \""
                            + TurmsProperties.PROPERTIES_PREFIX
                            + "\". Actual: \""
                            + firstPart
                            + "\"");
        }
        int length = parts.length;
        Class<?> currentPropertyClass = TurmsProperties.class;
        for (int i = 1; i < length; i++) {
            String part = parts[i];
            Optional<Field> matchedField = ClassUtil.getNonStaticFields(currentPropertyClass)
                    .stream()
                    .filter(f -> StringUtil.lowerCamelToLowerHyphenLatin1(f.getName())
                            .equals(part))
                    .findFirst();
            if (matchedField.isEmpty()) {
                throw new RuntimeException(
                        "The value of the field ("
                                + field.getName()
                                + ") is not found");
            }
            currentPropertyClass = matchedField.get()
                    .getType();
        }
    }
}