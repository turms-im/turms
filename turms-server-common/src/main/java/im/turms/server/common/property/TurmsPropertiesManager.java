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

package im.turms.server.common.property;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import im.turms.server.common.context.ApplicationContext;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import im.turms.server.common.util.MapUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.stereotype.Component;
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
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * To make the code of {@link TurmsProperties} clean, we separate the operation methods from it.
 *
 * @author James Chen
 */
@Log4j2
@Component
public class TurmsPropertiesManager {

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    public static final TypeReference<HashMap<String, Object>> TYPE_REF_MAP = new TypeReference<>() {
    };
    private static final String PACKAGE_NAME = TurmsProperties.class.getPackageName();

    public static final Map<String, Object> METADATA = getMetadata(new HashMap<>(32), TurmsProperties.class, false);
    public static final Map<String, Object> ONLY_MUTABLE_METADATA = getMetadata(new HashMap<>(32), TurmsProperties.class, true);

    public static final ObjectWriter MUTABLE_PROPERTIES_WRITER = new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            .writerWithView(MutablePropertiesView.class);

    private static final String FIELD_NAME_TYPE = "type";
    private static final String FIELD_NAME_DEPRECATED = "deprecated";
    private static final String FIELD_NAME_MUTABLE = "mutable";
    private static final String FIELD_NAME_DESC = "desc";
    private static final String FIELD_NAME_OPTIONS = "options";

    public final List<Consumer<TurmsProperties>> propertiesChangeListeners = new LinkedList<>();

    private final Path latestConfigFilePath;
    private TurmsProperties turmsProperties;

    public TurmsPropertiesManager(TurmsProperties turmsProperties, ApplicationContext context) {
        this.turmsProperties = turmsProperties;

        // Get latestConfigFilePath according to the active profiles
        String activeProfile = context.getActiveProfile();
        // The property should be passed from turms.cmd or turms.sh
        String configDir = System.getProperty("spring.config.location");
        if (configDir == null || configDir.isBlank()) {
            log.warn("The property \"spring.config.location\" is empty");
            configDir = "./config";
        }
        String latestConfigFileName = activeProfile != null
                ? String.format("application-%s-latest.yaml", activeProfile)
                : "application-latest.yaml";
        latestConfigFilePath = Path.of(String.format("%s/%s", configDir, latestConfigFileName));
    }

    /**
     * Use the instance of TurmsPropertiesManager instead of TurmsProperties instance
     * so that we can update the global TurmsProperties instance easily by replacing its reference
     */
    public TurmsProperties getLocalProperties() {
        return turmsProperties;
    }

    // Metadata

    public String getMutablePropertiesString(TurmsProperties propertiesForUpdating) throws JsonProcessingException {
        return MUTABLE_PROPERTIES_WRITER.writeValueAsString(propertiesForUpdating);
    }

    public Map<String, Object> getPropertyValueMap(TurmsProperties turmsProperties, boolean mutable) throws IOException {
        return mutable
                ? MAPPER.readValue(MUTABLE_PROPERTIES_WRITER.writeValueAsBytes(turmsProperties), TYPE_REF_MAP)
                : MAPPER.readValue(MAPPER.writeValueAsBytes(turmsProperties), TYPE_REF_MAP);
    }

    private static Map<String, Object> getMetadata(Map<String, Object> outputMetadata, Class<?> clazz, boolean onlyMutable) {
        List<Field> fieldList;
        if (onlyMutable) {
            fieldList = FieldUtils.getFieldsListWithAnnotation(clazz, JsonView.class)
                    .stream()
                    .filter(TurmsPropertiesManager::isMutableProperty)
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

    public Map<String, Object> mergePropertiesWithMetadata(
            @NotNull Map<String, Object> properties,
            @NotNull Map<String, Object> metadata) {
        properties = MapUtil.addValueKeyToAllLeaves(properties);
        return MapUtil.deepMerge(properties, metadata);
    }

    private static boolean isMutableProperty(Field field) {
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

    private ObjectNode getNotEmptyPropertiesTree(String propertiesJson) throws JsonProcessingException {
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

    // TurmsProperties instance

    public TurmsProperties merge(
            @NotNull TurmsProperties propertiesToUpdate,
            @NotNull String propertiesForUpdating) throws IOException {
        ObjectReader objectReader = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
                .readerForUpdating(propertiesToUpdate)
                .forType(TurmsProperties.class);
        return objectReader.readValue(propertiesForUpdating);
    }

    public TurmsProperties merge(
            @NotNull TurmsProperties propertiesToUpdate,
            @NotNull TurmsProperties propertiesForUpdating) throws IOException {
        return merge(propertiesToUpdate, getMutablePropertiesString(propertiesForUpdating));
    }

    public void persist(String propertiesJson) throws IOException {
        ObjectNode tree = getNotEmptyPropertiesTree(propertiesJson);
        Yaml yaml = getYaml();
        String configYaml = yaml.dump(yaml.load(MUTABLE_PROPERTIES_WRITER.writeValueAsString(tree)));
        Path dir = latestConfigFilePath.getParent();
        if (dir != null) {
            Files.createDirectories(dir);
        }
        Files.writeString(latestConfigFilePath, configYaml, StandardCharsets.UTF_8,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.CREATE);
    }

    public void updateLocalProperties(TurmsProperties newProperties) {
        turmsProperties = newProperties;
    }

    // Listener

    public void addListeners(Consumer<TurmsProperties> listener) {
        propertiesChangeListeners.add(listener);
    }

    public void notifyListeners(TurmsProperties properties) {
        for (Consumer<TurmsProperties> listener : propertiesChangeListeners) {
            try {
                listener.accept(properties);
            } catch (Exception e) {
                log.error("The properties listener {} failed to handle the new properties", listener.getClass().getName(), e);
            }
        }
    }

    // Helper

    private Yaml getYaml() {
        DumperOptions options = new DumperOptions();
        options.setIndent(2);
        options.setPrettyFlow(true);
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        return new Yaml(options);
    }

}
