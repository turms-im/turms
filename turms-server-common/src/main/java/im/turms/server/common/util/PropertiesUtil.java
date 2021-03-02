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

package im.turms.server.common.util;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
public class PropertiesUtil {

    private static final String PACKAGE_NAME = TurmsProperties.class.getPackageName();
    private static final String FIELD_NAME_TYPE = "type";
    private static final String FIELD_NAME_DEPRECATED = "deprecated";
    private static final String FIELD_NAME_MUTABLE = "mutable";
    private static final String FIELD_NAME_DESC = "desc";
    private static final String FIELD_NAME_OPTIONS = "options";

    public static final ObjectWriter MUTABLE_PROPERTIES_WRITER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            .writerWithView(MutablePropertiesView.class);
    public static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    public static final TypeReference<HashMap<String, Object>> TYPE_REF_MAP = new TypeReference<>() {
    };
    public static final Map<String, Object> METADATA = ImmutableMap.copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, false));
    public static final Map<String, Object> ONLY_MUTABLE_METADATA = ImmutableMap.copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, true));

    PropertiesUtil() {
    }

    public static boolean isMutableProperty(Field field) {
        JsonView jsonView = field.getDeclaredAnnotation(JsonView.class);
        if (jsonView != null) {
            for (Class<?> clazz : jsonView.value()) {
                if (clazz == MutablePropertiesView.class) {
                    return true;
                }
            }
        }
        return false;
    }

    // Merge

    public static TurmsProperties mergeAsProperties(
            @NotNull TurmsProperties propertiesToUpdate,
            @NotNull String propertiesForUpdating) throws IOException {
        ObjectReader objectReader = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .readerForUpdating(propertiesToUpdate)
                .forType(TurmsProperties.class);
        return objectReader.readValue(propertiesForUpdating);
    }

    public static TurmsProperties mergeAsProperties(
            @NotNull TurmsProperties propertiesToUpdate,
            Map<String, Object> propertiesForUpdating) throws IOException {
        return mergeAsProperties(propertiesToUpdate, PropertiesUtil.toMutablePropertiesString(propertiesForUpdating));
    }

    public static Map<String, Object> mergePropertiesWithMetadata(
            @NotNull Map<String, Object> properties,
            @NotNull Map<String, Object> metadata) {
        properties = MapUtil.addValueKeyToAllLeaves(properties);
        return MapUtil.deepMerge(properties, metadata);
    }

    // Structure analysis

    public static Map<String, Object> getPropertyValueMap(TurmsProperties turmsProperties, boolean returnOnlyMutableProperties) throws IOException {
        return returnOnlyMutableProperties
                ? MAPPER.readValue(MUTABLE_PROPERTIES_WRITER.writeValueAsBytes(turmsProperties), TYPE_REF_MAP)
                : MAPPER.readValue(MAPPER.writeValueAsBytes(turmsProperties), TYPE_REF_MAP);
    }

    public static ObjectNode getNotEmptyPropertiesTree(String propertiesJson) throws JsonProcessingException {
        ObjectNode jsonNodeTree = (ObjectNode) new ObjectMapper().readTree(propertiesJson);
        List<String> emptyFieldNames = new LinkedList<>();
        Iterator<Map.Entry<String, JsonNode>> fields = jsonNodeTree.fields();
        while (fields.hasNext()) {
            Map.Entry<String, JsonNode> entry = fields.next();
            if (entry.getValue().isEmpty()) {
                emptyFieldNames.add(entry.getKey());
            }
        }
        for (String name : emptyFieldNames) {
            jsonNodeTree.remove(name);
        }
        return JsonNodeFactory.instance.objectNode()
                .set("turms", jsonNodeTree);
    }

    private static Map<String, Object> getMetadata(Map<String, Object> outputMetadata, Class<?> clazz, boolean onlyMutable) {
        List<Field> fieldList;
        if (onlyMutable) {
            fieldList = FieldUtils.getFieldsListWithAnnotation(clazz, JsonView.class)
                    .stream()
                    .filter(PropertiesUtil::isMutableProperty)
                    .collect(Collectors.toList());
        } else {
            fieldList = FieldUtils.getAllFieldsList(clazz);
        }
        fieldList = fieldList
                .stream()
                .filter(field -> !field.isAnnotationPresent(JsonIgnore.class))
                .collect(Collectors.toList());
        for (Field field : fieldList) {
            if (field.getType().getTypeName().startsWith(PACKAGE_NAME)) {
                if (field.getType().isEnum()) {
                    HashMap<Object, Object> fieldMap = Maps.newHashMapWithExpectedSize(5);
                    fieldMap.put(FIELD_NAME_TYPE, "enum");
                    fieldMap.put(FIELD_NAME_DEPRECATED, field.isAnnotationPresent(Deprecated.class));
                    fieldMap.put(FIELD_NAME_MUTABLE, isMutableProperty(field));
                    fieldMap.put(FIELD_NAME_OPTIONS, field.getType().getEnumConstants());
                    if (field.isAnnotationPresent(Description.class)) {
                        fieldMap.put(FIELD_NAME_DESC, field.getDeclaredAnnotation(Description.class).value());
                    }
                    outputMetadata.put(field.getName(), fieldMap);
                } else {
                    Object any = getMetadata(new HashMap<>(), field.getType(), onlyMutable);
                    outputMetadata.put(field.getName(), any);
                }
            } else if (!Modifier.isStatic(field.getModifiers())) {
                String typeName = field.getType().getTypeName();
                if (typeName.equals(String.class.getTypeName())) {
                    typeName = "string";
                }
                HashMap<Object, Object> fieldMap = Maps.newHashMapWithExpectedSize(4);
                fieldMap.put(FIELD_NAME_TYPE, typeName);
                fieldMap.put(FIELD_NAME_DEPRECATED, field.isAnnotationPresent(Deprecated.class));
                fieldMap.put(FIELD_NAME_MUTABLE, isMutableProperty(field));
                if (field.isAnnotationPresent(Description.class)) {
                    fieldMap.put(FIELD_NAME_DESC, field.getDeclaredAnnotation(Description.class).value());
                }
                outputMetadata.put(field.getName(), fieldMap);
            }
        }
        return outputMetadata;
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

    // Persist

    public static void persist(Path filePath, String propertiesJson) throws IOException {
        ObjectNode tree = getNotEmptyPropertiesTree(propertiesJson);
        Yaml yaml = getYaml();
        String configYaml = yaml.dump(yaml.load(MUTABLE_PROPERTIES_WRITER.writeValueAsString(tree)));
        Path dir = filePath.getParent();
        if (dir != null) {
            Files.createDirectories(dir);
        }
        Files.writeString(filePath, configYaml, StandardCharsets.UTF_8,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE);
    }

    private static Yaml getYaml() {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return new Yaml(options);
    }

}