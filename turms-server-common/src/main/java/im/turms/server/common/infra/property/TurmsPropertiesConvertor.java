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

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.MapUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import lombok.SneakyThrows;

import javax.annotation.Nullable;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MAPPER;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MUTABLE_PROPERTIES_WRITER;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getField;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.isMutableProperty;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.isNestedProperty;

/**
 * @author James Chen
 */
public class TurmsPropertiesConvertor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsPropertiesConvertor.class);

    private TurmsPropertiesConvertor() {
    }

    // Validate

    @Nullable
    public static InvalidPropertyException validatePropertiesForUpdating(Object properties,
                                                                         Map<String, Object> propertiesForUpdating) {
        return validatePropertiesForUpdating(properties, propertiesForUpdating, null);
    }

    @Nullable
    private static InvalidPropertyException validatePropertiesForUpdating(Object properties,
                                                                          Map<String, Object> propertiesForUpdating,
                                                                          @Nullable String parentFieldPath) {
        for (Map.Entry<String, Object> entry : propertiesForUpdating.entrySet()) {
            String fieldName = entry.getKey();
            String fieldPath = parentFieldPath == null ? fieldName : parentFieldPath + "." + fieldName;
            Field field = getField(properties.getClass(), fieldName);
            if (field == null) {
                return new InvalidPropertyException("The property doesn't exist: \"" + fieldPath + "\"");
            }
            if (!isMutableProperty(field) && !isNestedProperty(field)) {
                return new InvalidPropertyException("Cannot update an immutable property: \"" + fieldPath + "\"");
            }
            Object value = entry.getValue();
            if (value instanceof Map nestedPropertiesForUpdating) {
                try {
                    InvalidPropertyException exception = validatePropertiesForUpdating(field.get(properties),
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
        return mergeProperties(propertiesToUpdate, toJsonNode(propertiesForUpdating));
    }

    public static TurmsProperties mergeProperties(
            TurmsProperties propertiesToUpdate,
            JsonNode propertiesForUpdating) {
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

    public static Map<String, Object> mergeMetadataWithPropertyValue(
            Map<String, Object> metadata,
            TurmsProperties properties) {
        return mergeMetadataWithPropertyValue0(metadata, properties);
    }

    @SneakyThrows
    private static Map<String, Object> mergeMetadataWithPropertyValue0(Map<String, Object> metadata,
                                                                       Object properties) {
        Class<?> propertiesClass = properties.getClass();
        Map<String, Object> metadataWithValue = new HashMap<>(MapUtil.getCapability(metadata.size() + 1));
        for (Map.Entry<String, Object> entry : metadata.entrySet()) {
            String key = entry.getKey();
            Map<String, Object> originalValueMetadata = (Map<String, Object>) entry.getValue();
            Field field = getField(propertiesClass, key);
            Object value;
            if (field == null || (value = field.get(properties)) == null) {
                LOGGER.warn("Skip the unknown property \"" + key + "\" in the properties class \"" + propertiesClass.getName() + "\". "
                        + "This may happen if the property schema have changed");
                continue;
            }
            if (isNestedProperty(field)) {
                metadataWithValue.put(key, mergeMetadataWithPropertyValue0(originalValueMetadata, value));
            } else {
                // Clone the metadata because we need to add "value" to it
                // while we should not modify the original metadata
                Map<String, Object> newValueMetadata = CollectionUtil.newMapWithExpectedSize(originalValueMetadata.size() + 1);
                newValueMetadata.putAll(originalValueMetadata);
                newValueMetadata.put("value", value);
                metadataWithValue.put(key, newValueMetadata);
            }
        }
        return metadataWithValue;
    }

    // Convert

    public static JsonNode toMutablePropertiesJsonNode(TurmsProperties propertiesForUpdating) {
        try {
            return MAPPER.readTree(MUTABLE_PROPERTIES_WRITER.writeValueAsBytes(propertiesForUpdating));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static JsonNode toJsonNode(Object propertiesForUpdating) {
        return MAPPER.valueToTree(propertiesForUpdating);
    }

}
