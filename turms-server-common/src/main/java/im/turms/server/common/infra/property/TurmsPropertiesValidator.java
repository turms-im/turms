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

package im.turms.server.common.infra.property;

import im.turms.server.common.infra.validation.ValidCron;
import lombok.SneakyThrows;
import org.springframework.scheduling.support.CronExpression;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getFieldInfos;

/**
 * @author James Chen
 */
public class TurmsPropertiesValidator {

    private TurmsPropertiesValidator() {
    }

    /**
     * @implNote We don't use "hibernate-validator" because it has a terrible performance
     */
    @Nullable
    public static InvalidPropertyException validate(TurmsProperties properties) {
        List<String> errorMessages = validateProperties(properties);
        if (errorMessages.isEmpty()) {
            return null;
        }
        InvalidPropertyException root = new InvalidPropertyException("The properties contain invalid properties");
        for (String message : errorMessages) {
            root.addSuppressed(new InvalidPropertyException(message));
        }
        return root;
    }

    private static List<String> validateProperties(TurmsProperties properties) {
        List<String> errorMessages = new LinkedList<>();
        validateProperties(properties, errorMessages);
        return errorMessages;
    }

    @SneakyThrows
    private static void validateProperties(Object properties, List<String> errorMessages) {
        List<PropertyFieldInfo> propertyFieldInfos = getFieldInfos(properties.getClass());
        for (PropertyFieldInfo propertyFieldInfo : propertyFieldInfos) {
            if (propertyFieldInfo.isNestedProperty()) {
                Object nestedProperties = propertyFieldInfo.get(properties);
                validateProperties(nestedProperties, errorMessages);
            } else {
                validateProperty(properties, propertyFieldInfo, errorMessages);
            }
        }
    }

    @SneakyThrows
    private static void validateProperty(Object properties, PropertyFieldInfo fieldInfo, List<String> errorMessages) {
        PropertyConstraints constraints = fieldInfo.constraints();
        Min min = constraints.min();
        Max max = constraints.max();
        Size size = constraints.size();
        ValidCron cron = constraints.validCron();
        if (min == null && max == null && size == null && cron == null) {
            return;
        }
        Object value = fieldInfo.get(properties);
        Field field = fieldInfo.field();
        if (min != null || max != null) {
            validateMinMaxProperty(min, max, value, field, errorMessages);
        }
        if (size != null) {
            validateSizeProperty(size, value, field, errorMessages);
        }
        if (cron != null) {
            validateCronProperty(value, field, errorMessages);
        }
    }

    private static void validateMinMaxProperty(Min min, Max max, Object value, Field field, List<String> errorMessages) {
        if (!(value instanceof Number number)) {
            throw new IllegalArgumentException("The field \"" + field + "\" must be a number for min and max validation");
        }
        if (min != null && min.value() > number.longValue()) {
            String message = "The property \"" + field.getName() + "\" must be greater than or equal to " + min.value();
            errorMessages.add(message);
        }
        if (max != null && max.value() < number.longValue()) {
            String message = "The property \"" + field.getName() + "\" must be less than or equal to " + max.value();
            errorMessages.add(message);
        }
    }

    private static void validateSizeProperty(Size size, Object value, Field field, List<String> errorMessages) {
        int min = size.min();
        int max = size.max();
        int count = -1;
        String propertyLengthName = null;
        if (value instanceof String str) {
            count = str.length();
            propertyLengthName = "length";
        } else if (value instanceof Collection<?> collection) {
            count = collection.size();
            propertyLengthName = "size";
        } else if (value instanceof Map<?, ?> map) {
            count = map.size();
            propertyLengthName = "size";
        }
        if (count == -1) {
            throw new IllegalArgumentException("The field \"" + field + "\" must be a string, collection, or map for size validation");
        }
        if (min > count) {
            String message = "The " + propertyLengthName + " of property \"" + field.getName() + "\" must be greater than or equal to " + min;
            errorMessages.add(message);
        }
        if (max < count) {
            String message = "The" + propertyLengthName + " of property \"" + field.getName() + "\" must be less than or equal to " + max;
            errorMessages.add(message);
        }
    }

    private static void validateCronProperty(Object value, Field field, List<String> errorMessages) {
        if (!(value instanceof String str)) {
            throw new IllegalArgumentException("The field \"" + field + "\" must be a string for cron validation");
        }
        if (!CronExpression.isValidExpression(str)) {
            String message = "The property \"" + field.getName() + "\" has an invalid cron \"" + str + "\"";
            errorMessages.add(message);
        }
    }

}