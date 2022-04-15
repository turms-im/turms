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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import im.turms.server.common.infra.collection.MapUtil;
import im.turms.server.common.infra.reflect.ReflectionUtil;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MAPPER;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MUTABLE_PROPERTIES_WRITER;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.isMutableProperty;

/**
 * @author James Chen
 */
public class TurmsPropertiesConvertor {

    private static final ConcurrentHashMap<ClassAndName, MethodHandle> CLASS_AND_NAME_TO_GETTER =
            new ConcurrentHashMap<>(512);

    private TurmsPropertiesConvertor() {
    }

    // Validate
    @Nullable
    public static InvalidPropertyException validaPropertiesForUpdating(Object properties,
                                                                       Map<String, Object> propertiesForUpdating) {
        return validaPropertiesForUpdating(properties, propertiesForUpdating, null);
    }

    @Nullable
    private static InvalidPropertyException validaPropertiesForUpdating(Object properties,
                                                                        Map<String, Object> propertiesForUpdating,
                                                                        @Nullable String parentFieldPath) {
        for (Map.Entry<String, Object> entry : propertiesForUpdating.entrySet()) {
            String fieldName = entry.getKey();
            String fieldPath = parentFieldPath == null ? fieldName : parentFieldPath + "." + fieldName;
            ClassAndName propertiesClassAndFieldName = new ClassAndName(properties.getClass(), fieldName);
            MethodHandle getter = CLASS_AND_NAME_TO_GETTER.get(propertiesClassAndFieldName);
            if (getter == null) {
                try {
                    // Note that we only cache valid fields because
                    // caching invalid fields will present a security vulnerability
                    getter = CLASS_AND_NAME_TO_GETTER.computeIfAbsent(propertiesClassAndFieldName, classAndName -> {
                        Field field = FieldUtils.getField(classAndName.propertiesClass, classAndName.fieldName, true);
                        if (field == null) {
                            throw new InvalidPropertyException("The property doesn't exist: \"" + fieldPath + "\"");
                        }
                        if (!isMutableProperty(field) && !field.isAnnotationPresent(NestedConfigurationProperty.class)) {
                            throw new InvalidPropertyException("Cannot update an immutable property: \"" + fieldPath + "\"");
                        }
                        return ReflectionUtil.getGetter(field);
                    });
                } catch (InvalidPropertyException e) {
                    return e;
                }
            }
            Object value = entry.getValue();
            if (value instanceof Map nestedPropertiesForUpdating) {
                try {
                    InvalidPropertyException exception = validaPropertiesForUpdating(getter.invoke(properties),
                            nestedPropertiesForUpdating,
                            fieldPath);
                    if (exception != null) {
                        return exception;
                    }
                } catch (Throwable t) {
                    throw new RuntimeException("Failed to validate properties", t);
                }
            }
        }
        return null;
    }

    // Merge

    public static TurmsProperties mergeProperties(
            TurmsProperties propertiesToUpdate,
            Map<String, Object> propertiesForUpdating) {
        return mergeProperties(propertiesToUpdate, toMutablePropertiesString(propertiesForUpdating));
    }

    public static TurmsProperties mergeProperties(
            TurmsProperties propertiesToUpdate,
            String propertiesForUpdating) {
        try {
            // Note that this is a deep clone
            TurmsProperties newProperties = MAPPER
                    .convertValue(propertiesToUpdate, TurmsProperties.class);
            ObjectReader objectReader = new ObjectMapper()
                    .readerForUpdating(newProperties)
                    .forType(TurmsProperties.class);
            return objectReader.readValue(propertiesForUpdating);
        } catch (Exception e) {
            throw new InvalidPropertyException("Failed to merge properties", e);
        }
    }

    public static Map<String, Object> mergePropertiesWithMetadata(
            Map<String, Object> properties,
            Map<String, Object> metadata) {
        properties = MapUtil.addValueKeyToAllLeaves(properties);
        return MapUtil.deepMerge(properties, metadata, false);
    }

    // Convert

    public static String toMutablePropertiesString(TurmsProperties propertiesForUpdating) {
        try {
            return MUTABLE_PROPERTIES_WRITER.writeValueAsString(propertiesForUpdating);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    public static String toMutablePropertiesString(Map<String, Object> propertiesForUpdating) {
        try {
            return MUTABLE_PROPERTIES_WRITER.writeValueAsString(propertiesForUpdating);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    private record ClassAndName(Class<?> propertiesClass, String fieldName) {
    }

}
