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
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.annotation.GlobalProperty;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public final class PropertiesUtil {

    private static final String TURMS_PROPERTIES_PACKAGE_NAME = TurmsProperties.class.getPackageName();
    private static final String FIELD_NAME_DEPRECATED = "deprecated";
    private static final String FIELD_NAME_DESC = "desc";
    private static final String FIELD_NAME_ELEMENT_TYPE = "elementType";
    private static final String FIELD_NAME_GLOBAL = "global";
    private static final String FIELD_NAME_MUTABLE = "mutable";
    private static final String FIELD_NAME_OPTIONS = "options";
    private static final String FIELD_NAME_TYPE = "type";

    public static final ObjectWriter MUTABLE_PROPERTIES_WRITER = new ObjectMapper()
            .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .writerWithView(MutablePropertiesView.class);
    public static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    public static final TypeReference<HashMap<String, Object>> TYPE_REF_MAP = new TypeReference<>() {
    };
    public static final Map<String, Object> METADATA = ImmutableMap.copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, false));
    public static final Map<String, Object> ONLY_MUTABLE_METADATA =
            ImmutableMap.copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, true));

    private PropertiesUtil() {
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
        return mergeAsProperties(propertiesToUpdate, toMutablePropertiesString(propertiesForUpdating));
    }

    public static Map<String, Object> mergePropertiesWithMetadata(
            @NotNull Map<String, Object> properties,
            @NotNull Map<String, Object> metadata) {
        properties = MapUtil.addValueKeyToAllLeaves(properties);
        return MapUtil.deepMerge(properties, metadata);
    }

    // Structure analysis

    public static Map<String, Object> getPropertyValueMap(TurmsProperties turmsProperties, boolean returnOnlyMutableProperties)
            throws IOException {
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
        List<Field> fieldList = getFields(clazz, onlyMutable);
        for (Field field : fieldList) {
            Object fieldMetadata = getFieldMetadata(field, onlyMutable);
            if (fieldMetadata != null) {
                outputMetadata.put(field.getName(), fieldMetadata);
            }
        }
        return outputMetadata;
    }

    private static List<Field> getFields(Class<?> clazz, boolean onlyMutable) {
        List<Field> fieldList;
        if (onlyMutable) {
            fieldList = FieldUtils.getFieldsListWithAnnotation(clazz, JsonView.class)
                    .stream()
                    .filter(PropertiesUtil::isMutableProperty)
                    .toList();
        } else {
            fieldList = FieldUtils.getAllFieldsList(clazz);
        }
        return fieldList
                .stream()
                .filter(field -> !field.isAnnotationPresent(JsonIgnore.class))
                .toList();
    }

    private static Object getFieldMetadata(Field field, boolean onlyMutable) {
        Class<?> type = field.getType();
        String typeName = type.getTypeName();
        if (Modifier.isStatic(field.getModifiers())) {
            return null;
        }
        if (typeName.startsWith(TURMS_PROPERTIES_PACKAGE_NAME) && !type.isEnum()) {
            return getMetadata(new HashMap<>(), type, onlyMutable);
        }
        return getFlatFieldMetadata(field);
    }

    private static Map<String, Object> getFlatFieldMetadata(Field field) {
        Class<?> type = field.getType();
        String elementType = null;
        String typeName = getTypeName(type);
        Object[] options = type.isEnum() ? type.getEnumConstants() : null;
        if (Iterable.class.isAssignableFrom(type)) {
            Type[] types = ((ParameterizedType) field.getGenericType()).getActualTypeArguments();
            if (types.length == 1) {
                elementType = getTypeName((Class<?>) types[0]);
                if (type.isEnum()) {
                    options = type.getEnumConstants();
                }
            }
        } else if (type.isArray()) {
            elementType = getTypeName(type.getComponentType());
        }
        // Fill in
        HashMap<String, Object> metadata = Maps.newHashMapWithExpectedSize(7);
        if (field.isAnnotationPresent(Description.class)) {
            metadata.put(FIELD_NAME_DESC, field.getDeclaredAnnotation(Description.class).value());
        }
        if (elementType != null) {
            metadata.put(FIELD_NAME_ELEMENT_TYPE, elementType);
        }
        metadata.put(FIELD_NAME_TYPE, typeName);
        metadata.put(FIELD_NAME_DEPRECATED, field.isAnnotationPresent(Deprecated.class));
        metadata.put(FIELD_NAME_GLOBAL, field.isAnnotationPresent(GlobalProperty.class));
        metadata.put(FIELD_NAME_MUTABLE, isMutableProperty(field));
        if (options != null) {
            metadata.put(FIELD_NAME_OPTIONS, options);
        }
        return metadata;
    }

    private static String getTypeName(Class<?> type) {
        if (type.isEnum()) {
            return "enum";
        }
        if (String.class.isAssignableFrom(type)) {
            return "string";
        }
        return type.getTypeName();
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