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

import java.io.IOException;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectReader;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

import static im.turms.server.common.infra.json.JsonCodecPool.MAPPER;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.MUTABLE_PROPERTIES_WRITER;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getFieldInfo;

/**
 * @author James Chen
 */
public class TurmsPropertiesConvertor {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsPropertiesConvertor.class);

    private TurmsPropertiesConvertor() {
    }

    // Validate

    @Nullable
    public static InvalidPropertyException validatePropertiesForUpdating(
            Object properties,
            Map<String, Object> propertiesForUpdating) {
        return validatePropertiesForUpdating(properties, propertiesForUpdating, null);
    }

    @Nullable
    private static InvalidPropertyException validatePropertiesForUpdating(
            Object properties,
            Map<String, Object> propertiesForUpdating,
            @Nullable String parentFieldPath) {
        for (Map.Entry<String, Object> entry : propertiesForUpdating.entrySet()) {
            String fieldName = entry.getKey();
            String fieldPath = parentFieldPath == null
                    ? fieldName
                    : parentFieldPath
                            + "."
                            + fieldName;
            PropertyFieldInfo fieldInfo = getFieldInfo(properties.getClass(), fieldName);
            if (fieldInfo == null) {
                return new InvalidPropertyException(
                        "Nonexistent property: "
                                + fieldPath);
            }
            if (!fieldInfo.isMutableProperty() && !fieldInfo.isNestedProperty()) {
                return new InvalidPropertyException(
                        "Immutable property: "
                                + fieldPath);
            }
            Object value = entry.getValue();
            if (value instanceof Map nestedPropertiesForUpdating) {
                try {
                    InvalidPropertyException exception =
                            validatePropertiesForUpdating(fieldInfo.get(properties),
                                    nestedPropertiesForUpdating,
                                    fieldPath);
                    if (exception != null) {
                        return exception;
                    }
                } catch (Exception e) {
                    throw new RuntimeException("Failed to validate properties", e);
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
            TurmsProperties newProperties =
                    MAPPER.convertValue(propertiesToUpdate, TurmsProperties.class);
            ObjectReader objectReader = MAPPER.readerForUpdating(newProperties)
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

    private static Map<String, Object> mergeMetadataWithPropertyValue0(
            Map<String, Object> metadata,
            Object properties) {
        Class<?> propertiesClass = properties.getClass();
        Map<String, Object> metadataWithValue =
                CollectionUtil.newMapWithExpectedSize(metadata.size() + 1);
        for (Map.Entry<String, Object> entry : metadata.entrySet()) {
            String key = entry.getKey();
            PropertyFieldInfo propertyFieldInfo = getFieldInfo(propertiesClass, key);
            Object value;
            if (propertyFieldInfo == null || (value = propertyFieldInfo.get(properties)) == null) {
                LOGGER.warn("Skip the unknown property \""
                        + key
                        + "\" in the properties class: "
                        + propertiesClass.getName()
                        + ". This may happen if the property schema have changed");
                continue;
            }
            if (propertyFieldInfo.isNestedProperty()) {
                Map<String, Object> originalValueMetadata = (Map<String, Object>) entry.getValue();
                metadataWithValue.put(key,
                        mergeMetadataWithPropertyValue0(originalValueMetadata, value));
            } else {
                FieldMetadata fieldMetadata = (FieldMetadata) entry.getValue();
                int entryCount = 6;
                String elementType = fieldMetadata.elementType();
                Object[] options = fieldMetadata.options();
                String description = fieldMetadata.description();
                if (elementType != null) {
                    entryCount++;
                }
                if (options != null) {
                    entryCount++;
                }
                if (description != null) {
                    entryCount++;
                }
                Map.Entry<String, Object>[] entries = new Map.Entry[entryCount];
                int i = 0;
                entries[i++] = Map.entry("value",
                        fieldMetadata.sensitive()
                                ? ""
                                : value);
                entries[i++] =
                        Map.entry(FieldMetadata.Fields.deprecated, fieldMetadata.deprecated());
                entries[i++] = Map.entry(FieldMetadata.Fields.global, fieldMetadata.global());
                entries[i++] = Map.entry(FieldMetadata.Fields.mutable, fieldMetadata.mutable());
                entries[i++] = Map.entry(FieldMetadata.Fields.sensitive, fieldMetadata.sensitive());
                entries[i++] = Map.entry(FieldMetadata.Fields.type, fieldMetadata.type());
                if (elementType != null) {
                    entries[i++] = Map.entry(FieldMetadata.Fields.elementType, elementType);
                }
                if (options != null) {
                    entries[i++] = Map.entry(FieldMetadata.Fields.options, options);
                }
                if (description != null) {
                    entries[i] = Map.entry(FieldMetadata.Fields.description, description);
                }
                metadataWithValue.put(key, Map.ofEntries(entries));
            }
        }
        return Map.copyOf(metadataWithValue);
    }

    // Convert

    public static JsonNode toMutablePropertiesJsonNode(TurmsProperties propertiesForUpdating) {
        try {
            return MAPPER
                    .readTree(MUTABLE_PROPERTIES_WRITER.writeValueAsBytes(propertiesForUpdating));
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    public static JsonNode toJsonNode(Object propertiesForUpdating) {
        return MAPPER.valueToTree(propertiesForUpdating);
    }

}
