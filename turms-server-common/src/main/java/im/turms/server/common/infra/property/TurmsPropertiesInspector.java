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

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import im.turms.server.common.infra.property.metadata.annotation.Description;
import im.turms.server.common.infra.property.metadata.annotation.GlobalProperty;
import im.turms.server.common.infra.property.metadata.view.MutablePropertiesView;
import im.turms.server.common.infra.validation.ValidCron;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.util.ConcurrentReferenceHashMap;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public class TurmsPropertiesInspector {

    private static final String TURMS_PROPERTIES_PACKAGE_NAME = TurmsProperties.class.getPackageName();
    private static final String FIELD_NAME_DEPRECATED = "deprecated";
    private static final String FIELD_NAME_DESC = "desc";
    private static final String FIELD_NAME_ELEMENT_TYPE = "elementType";
    private static final String FIELD_NAME_GLOBAL = "global";
    private static final String FIELD_NAME_MUTABLE = "mutable";
    private static final String FIELD_NAME_OPTIONS = "options";
    private static final String FIELD_NAME_TYPE = "type";

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());
    public static final ObjectWriter MUTABLE_PROPERTIES_WRITER = new ObjectMapper()
            .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_EMPTY)
            .writerWithView(MutablePropertiesView.class);

    public static final Map<String, Object> METADATA = ImmutableMap.copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, false));
    public static final Map<String, Object> ONLY_MUTABLE_METADATA =
            ImmutableMap.copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, true));
    public static final TypeReference<HashMap<String, Object>> TYPE_REF_MAP = new TypeReference<>() {
    };

    private static final Map<Field, Map<Class<? extends Annotation>, Annotation>> ANNOTATION_MAP =
            new ConcurrentReferenceHashMap<>(512);

    private TurmsPropertiesInspector() {
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

    @SneakyThrows
    public static Map<String, Object> getPropertyValueMap(TurmsProperties turmsProperties,
                                                          boolean returnOnlyMutableProperties) {
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

    public static Map<Class<? extends Annotation>, Annotation> getConstraints(Field field) {
        Map<Class<? extends Annotation>, Annotation> map = ANNOTATION_MAP.get(field);
        if (map != null) {
            return map;
        }
        map = ANNOTATION_MAP.computeIfAbsent(field, key -> {
            Map<Class<? extends Annotation>, Annotation> newMap = null;
            for (Annotation annotation : key.getDeclaredAnnotations()) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                if (annotationType == Min.class || annotationType == Max.class || annotationType == ValidCron.class) {
                    if (newMap == null) {
                        newMap = new HashMap<>(4);
                    }
                    newMap.put(annotationType, annotation);
                }
            }
            return newMap == null ? Collections.emptyMap() : newMap;
        });
        return map;
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
                    .filter(TurmsPropertiesInspector::isMutableProperty)
                    .toList();
        } else {
            fieldList = FieldUtils.getAllFieldsList(clazz);
        }
        if (fieldList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Field> fields = new ArrayList<>(fieldList.size());
        for (Field field : fieldList) {
            if (!field.isAnnotationPresent(JsonIgnore.class)) {
                fields.add(field);
            }
        }
        return fields;
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

}
