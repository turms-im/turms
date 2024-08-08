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

package generator;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.SequencedMap;
import java.util.SortedMap;
import java.util.TreeMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.FieldMetadata;
import im.turms.server.common.infra.property.TurmsProperties;

/**
 * @author James Chen
 */
class TurmsPropertiesPropertiesFileGenerator {

    public static void main(String[] args) {
        // 1. Prepare properties metadata
        Path basePath;
        try {
            basePath = Path.of(TurmsPropertiesPropertiesFileGenerator.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to get the output path", e);
        }
        basePath = basePath.resolve("../../src/test/resources");

        Path propertiesMetadataPath =
                basePath.resolve("turms-properties-metadata-with-property-value.json");
        InputStream propertiesMetadataInputStream;
        try {
            propertiesMetadataInputStream = Files.newInputStream(propertiesMetadataPath);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read the properties metadata: "
                            + propertiesMetadataPath.normalize(),
                    e);
        }
        Map<String, Object> propertiesMap;
        try {
            propertiesMap = new ObjectMapper().readValue(propertiesMetadataInputStream, Map.class);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to parse the properties metadata: "
                            + propertiesMetadataPath.normalize(),
                    e);
        }
        propertiesMap = Map.of(TurmsProperties.PROPERTIES_PREFIX, propertiesMap);

        // 2. Prepare output properties
        TreeMap<String, Object> outputMap = new TreeMap<>();
        collectionOutputFields(propertiesMap, outputMap);

        // 3. Format output properties to string
        StringBuilder builder = new StringBuilder(1024 * 128).append(
                "# These property values are the default values of Turms servers for the production environment.\n");
        writeToString(builder, outputMap, 0);

        // 4. Write to file
        String yaml = builder.toString();
        Path path = basePath.resolve("application-full-example.yaml")
                .toAbsolutePath()
                .normalize();
        try {
            Files.write(path,
                    yaml.getBytes(StandardCharsets.UTF_8),
                    StandardOpenOption.WRITE,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to write to path: "
                            + path,
                    e);
        }
        System.out.println("Generated file: "
                + path);
    }

    private static void writeToString(
            StringBuilder builder,
            Map<String, Object> inputMap,
            int indent) {
        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
            Object value = entry.getValue();
            String propertyName = formatPropertyName(entry.getKey());
            int spaceCount = indent << 1;
            builder.append('\n')
                    .repeat(' ', spaceCount);
            if (value instanceof OutputField field) {
                String description = field.description;
                if (StringUtil.isNotBlank(description)) {
                    builder.append('#')
                            .append(' ')
                            .append(description.replace('\n', ' '));
                    if (!description.endsWith(".")) {
                        builder.append('.');
                    }
                    builder.append('\n')
                            .repeat(' ', spaceCount);
                }
                builder.append("# global property: ")
                        .append(field.global);
                builder.append('\n')
                        .repeat(' ', spaceCount)
                        .append("# mutable property: ")
                        .append(field.mutable);
                if (CollectionUtil.isNotEmpty(field.enumValues)) {
                    builder.append('\n')
                            .repeat(' ', spaceCount)
                            .append("# enum values: ")
                            .append(field.enumValues);
                }
                String valueAsString = formatPropertyValue(field, indent + 1, false);
                builder.append('\n')
                        .repeat(' ', spaceCount)
                        .append(propertyName)
                        .append(':')
                        .append(valueAsString);
            } else {
                builder.append(propertyName)
                        .append(':');
                Map<String, Object> map = (Map<String, Object>) value;
                if (map.isEmpty()) {
                    builder.append(" {}");
                } else {
                    writeToString(builder, map, indent + 1);
                }
            }
        }
    }

    private static void collectionOutputFields(
            Map<String, Object> inputMap,
            Map<String, Object> outputMap) {
        for (Map.Entry<String, Object> entry : inputMap.entrySet()) {
            Map<String, Object> valueMap = (Map) entry.getValue();
            Boolean mutable = (Boolean) valueMap.get(FieldMetadata.Fields.mutable);
            Boolean global = (Boolean) valueMap.get(FieldMetadata.Fields.global);
            String description = (String) valueMap.get(FieldMetadata.Fields.description);
            List<String> enumValues = (List<String>) valueMap.get(FieldMetadata.Fields.options);
            Object value = valueMap.get("value");
            if (mutable != null && global != null && value != null) {
                String type = (String) valueMap.get(FieldMetadata.Fields.type);
                if (type.equals("enum")) {
                    value = ((String) value).toLowerCase();
                    enumValues = enumValues.stream()
                            .map(String::toLowerCase)
                            .toList();
                } else if ("enum".equals(valueMap.get(FieldMetadata.Fields.elementType))) {
                    value = ((List<String>) value).stream()
                            .map(String::toLowerCase)
                            .toList();
                    enumValues = enumValues.stream()
                            .map(String::toLowerCase)
                            .toList();
                }
                OutputField field =
                        new OutputField(type, global, mutable, description, value, enumValues);
                outputMap.put(entry.getKey(), field);
            } else {
                Map<String, Object> map = new TreeMap<>();
                collectionOutputFields(valueMap, map);
                outputMap.put(entry.getKey(), map);
            }
        }
    }

    private static String formatPropertyName(String key) {
        int length = key.length();
        StringBuilder builder = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            char c = key.charAt(i);
            if (Character.isUpperCase(c)) {
                if (i > 0) {
                    builder.append('-');
                }
                builder.append(Character.toLowerCase(c));
            } else {
                builder.append(c);
            }
        }
        return builder.toString();
    }

    private static String formatPropertyValue(OutputField field, int indent, boolean isParentList) {
        return switch (field.value) {
            case null -> throw new IllegalArgumentException("The property value cannot be null");
            case Boolean booleanValue -> booleanValue
                    ? " true"
                    : " false";
            case String string -> field.enumValues() == null
                    ? " \""
                            + string
                            + "\""
                    : " "
                            + string;
            case Number number -> " "
                    + number;
            case Collection<?> collection -> {
                if (collection.isEmpty()) {
                    yield " []";
                }
                StringBuilder builder = new StringBuilder(256);
                for (Object item : collection) {
                    builder.append('\n')
                            .repeat(' ', indent << 1)
                            .append('-')
                            .append(formatPropertyValue(
                                    new OutputField(null, null, null, null, item, null),
                                    indent + 1,
                                    true));
                }
                yield builder.toString();
            }
            case Map<?, ?> map -> {
                if (map.isEmpty()) {
                    yield " {}";
                }
                if (!(map instanceof SequencedMap<?, ?>)) {
                    map = new TreeMap<>(map);
                }
                StringBuilder builder = new StringBuilder(256);
                boolean isFirst = true;
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    if (isParentList) {
                        if (isFirst) {
                            builder.append(' ');
                            isFirst = false;
                        } else {
                            builder.append('\n')
                                    .repeat(' ', indent << 1);
                        }
                    } else {
                        builder.append('\n')
                                .repeat(' ', indent << 1)
                                .append('-')
                                .append(' ');
                    }
                    builder.append(formatPropertyName((String) entry.getKey()))
                            .append(':')
                            .append(formatPropertyValue(
                                    new OutputField(null, null, null, null, entry.getValue(), null),
                                    indent + 1,
                                    false));
                }
                yield builder.toString();
            }
            default -> throw new IllegalArgumentException(
                    "Unsupported property value type: "
                            + field.value.getClass());
        };
    }

    private record OutputField(
            String type,
            Boolean global,
            Boolean mutable,
            String description,
            Object value,
            List<String> enumValues
    ) {
    }
}