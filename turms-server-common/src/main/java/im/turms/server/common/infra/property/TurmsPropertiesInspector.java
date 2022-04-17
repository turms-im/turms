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
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import im.turms.server.common.infra.collection.MapUtil;
import im.turms.server.common.infra.property.metadata.annotation.Description;
import im.turms.server.common.infra.property.metadata.annotation.GlobalProperty;
import im.turms.server.common.infra.property.metadata.view.MutablePropertiesView;
import im.turms.server.common.infra.validation.ValidCron;
import lombok.SneakyThrows;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.annotation.Nullable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public class TurmsPropertiesInspector {

    private static final String FIELD_NAME_DEPRECATED = "deprecated";
    private static final String FIELD_NAME_DESC = "desc";
    private static final String FIELD_NAME_ELEMENT_TYPE = "elementType";
    private static final String FIELD_NAME_GLOBAL = "global";
    private static final String FIELD_NAME_MUTABLE = "mutable";
    private static final String FIELD_NAME_OPTIONS = "options";
    private static final String FIELD_NAME_TYPE = "type";

    public static final ObjectMapper MAPPER = new ObjectMapper()
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .registerModule(new JavaTimeModule())
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);
    public static final ObjectWriter MUTABLE_PROPERTIES_WRITER = JsonMapper.builder()
            // Only serialize the properties with "MutablePropertiesView"
            .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
            // e.g. "SharedConfigProperties" is an empty bean
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .build()
            .registerModule(new JavaTimeModule())
            // Use "NON_NULL" instead of "NON_EMPTY"
            // because we allow admins to apply the properties with empty collection
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .writerWithView(MutablePropertiesView.class);

    /**
     * Only includes non-static fields
     */
    public static final Map<String, Object> METADATA;
    public static final Map<String, Object> ONLY_MUTABLE_METADATA;
    public static final TypeReference<HashMap<String, Object>> TYPE_REF_MAP = new TypeReference<>() {
    };

    private static final Map<Class<?>, List<Field>> CLASS_TO_FIELDS;
    private static final Map<Class<?>, Map<String, Field>> CLASS_TO_NAME_TO_FIELD;
    private static final Map<Field, PropertyConstraints> FIELD_TO_CONSTRAINTS;

    static {
        IdentityHashMap<Class<?>, List<Field>> classToFields = new IdentityHashMap<>(128);
        IdentityHashMap<Field, PropertyConstraints> fieldToConstraints = new IdentityHashMap<>(512);
        collectFieldsInfo(TurmsProperties.class, classToFields, fieldToConstraints);

        CLASS_TO_FIELDS = classToFields;
        CLASS_TO_NAME_TO_FIELD = new IdentityHashMap<>(CLASS_TO_FIELDS.size());
        for (Map.Entry<Class<?>, List<Field>> classAndFields : classToFields.entrySet()) {
            List<Field> fields = classAndFields.getValue();
            Map<String, Field> nameToField = new HashMap<>(MapUtil.getCapability(fields.size()));
            for (Field field : fields) {
                nameToField.put(field.getName(), field);
            }
            CLASS_TO_NAME_TO_FIELD.put(classAndFields.getKey(), nameToField);
        }
        FIELD_TO_CONSTRAINTS = fieldToConstraints;
        METADATA = ImmutableMap
                .copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, false));
        ONLY_MUTABLE_METADATA = ImmutableMap
                .copyOf(getMetadata(new HashMap<>(32), TurmsProperties.class, true));
    }

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

    public static boolean isNestedProperty(Field field) {
        return field.isAnnotationPresent(NestedConfigurationProperty.class);
    }

    @SneakyThrows
    public static Map<String, Object> getPropertyToValueMap(TurmsProperties turmsProperties,
                                                            boolean returnOnlyMutableProperties) {
        return returnOnlyMutableProperties
                ? MAPPER.readValue(MUTABLE_PROPERTIES_WRITER.writeValueAsBytes(turmsProperties), TYPE_REF_MAP)
                : MAPPER.readValue(MAPPER.writeValueAsBytes(turmsProperties), TYPE_REF_MAP);
    }

    public static PropertyConstraints getConstraints(Field field) {
        return FIELD_TO_CONSTRAINTS.get(field);
    }

    @Nullable
    public static Field getField(Class<?> propertiesClass, String fieldName) {
        Map<String, Field> map = CLASS_TO_NAME_TO_FIELD.get(propertiesClass);
        if (map == null) {
            return null;
        }
        return map.get(fieldName);
    }

    @Nullable
    public static List<Field> getFields(Class<?> propertiesClass) {
        return CLASS_TO_FIELDS.get(propertiesClass);
    }

    private static void collectFieldsInfo(Class<?> propertiesClass,
                                          Map<Class<?>, List<Field>> classToFieldsOutput,
                                          Map<Field, PropertyConstraints> fieldToConstraintsOutput) {
        List<Field> fields = FieldUtils.getAllFieldsList(propertiesClass);
        List<Field> nonStaticFields = new ArrayList<>(fields.size());
        for (Field field : fields) {
            if (Modifier.isStatic(field.getModifiers())) {
                continue;
            }
            field.setAccessible(true);
            nonStaticFields.add(field);
            if (isNestedProperty(field)) {
                collectFieldsInfo(field.getType(), classToFieldsOutput, fieldToConstraintsOutput);
            }
            fieldToConstraintsOutput.put(field, PropertyConstraints.of(
                    field.getDeclaredAnnotation(Min.class),
                    field.getDeclaredAnnotation(Max.class),
                    field.getDeclaredAnnotation(ValidCron.class)
            ));
        }
        classToFieldsOutput.put(propertiesClass, nonStaticFields);
    }

    private static Map<String, Object> getMetadata(Map<String, Object> metadataOutput,
                                                   Class<?> clazz,
                                                   boolean onlyMutable) {
        for (Field field : CLASS_TO_FIELDS.get(clazz)) {
            if (field.isAnnotationPresent(JsonIgnore.class)
                    || (onlyMutable && !isMutableProperty(field))) {
                continue;
            }
            Class<?> type = field.getType();
            Object fieldMetadata = isNestedProperty(field)
                    ? getMetadata(new HashMap<>(), type, onlyMutable)
                    : getFlatFieldMetadata(field);
            metadataOutput.put(field.getName(), fieldMetadata);
        }
        return metadataOutput;
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
