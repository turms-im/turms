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
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.scheduling.support.CronExpression;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @SneakyThrows
    private static List<String> validateProperties(Object properties) {
        List<String> errorMessages = new LinkedList<>();
        List<Field> fields = FieldUtils.getAllFieldsList(properties.getClass());
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            if (field.isAnnotationPresent(NestedConfigurationProperty.class)) {
                field.setAccessible(true);
                Object nestedProperties = field.get(properties);
                errorMessages.addAll(validateProperties(nestedProperties));
            } else {
                validateProperty(properties, field, errorMessages);
            }
        }
        return errorMessages;
    }

    @SneakyThrows
    private static void validateProperty(Object properties, Field field, List<String> errorMessages) {
        Map<Class<? extends Annotation>, Annotation> constraints = TurmsPropertiesInspector.getConstraints(field);
        Min min = (Min) constraints.get(Min.class);
        Max max = (Max) constraints.get(Max.class);
        ValidCron cron = (ValidCron) constraints.get(ValidCron.class);
        if (min == null && max == null && cron == null) {
            return;
        }
        field.setAccessible(true);
        Object value = field.get(properties);
        if (cron != null) {
            String str = (String) value;
            if (!CronExpression.isValidExpression(str)) {
                String message = "The property \"%s\" has an invalid cron \"%s\""
                        .formatted(field.getName(), str);
                errorMessages.add(message);
            }
        } else {
            Number number = (Number) value;
            if (min != null && min.value() > number.longValue()) {
                String message = "The property \"%s\" must be greater than or equal to %d"
                        .formatted(field.getName(), min.value());
                errorMessages.add(message);
            }
            if (max != null && max.value() < number.longValue()) {
                String message = "The property \"%s\" must be less than or equal to %d"
                        .formatted(field.getName(), max.value());
                errorMessages.add(message);
            }
        }
    }

}
