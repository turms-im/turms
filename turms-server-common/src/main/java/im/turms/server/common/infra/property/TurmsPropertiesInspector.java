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

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.AnnotatedField;
import com.fasterxml.jackson.databind.introspect.AnnotatedMember;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.json.JsonUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;
import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.reflect.VarAccessorFactory;
import im.turms.server.common.infra.security.SensitiveProperty;
import im.turms.server.common.infra.serialization.SerializationException;
import im.turms.server.common.infra.validation.LessThanOrEqualTo;
import im.turms.server.common.infra.validation.ValidCron;

import static im.turms.server.common.infra.json.JsonCodecPool.MAPPER;

/**
 * @author James Chen
 */
public class TurmsPropertiesInspector {

    public static final ObjectWriter MUTABLE_PROPERTIES_WRITER = JsonMapper.builder()
            .enable(MapperFeature.PROPAGATE_TRANSIENT_MARKER)
            // e.g. "SharedConfigProperties" is an empty bean
            .disable(SerializationFeature.FAIL_ON_EMPTY_BEANS)
            .addModule(new JavaTimeModule())
            // Use "NON_NULL" instead of "NON_EMPTY"
            // because we allow admins to apply the properties with empty collection
            .serializationInclusion(JsonInclude.Include.NON_NULL)
            .annotationIntrospector(new JacksonAnnotationIntrospector() {
                @Override
                public boolean hasIgnoreMarker(AnnotatedMember m) {
                    if (super.hasIgnoreMarker(m)) {
                        return true;
                    }
                    if (m instanceof AnnotatedField) {
                        return (!_hasAnnotation(m, NestedConfigurationProperty.class)
                                && !_hasAnnotation(m, MutableProperty.class))
                                || _hasAnnotation(m, SensitiveProperty.class);
                    }
                    return false;
                }
            })
            .build()
            .writer();

    /**
     * Only includes non-static fields
     */
    public static final Map<String, Object> METADATA;
    public static final Map<String, Object> ONLY_MUTABLE_METADATA;

    private static final Map<Class<?>, List<PropertyFieldInfo>> CLASS_TO_FIELD_INFOS;
    private static final Map<Class<?>, Map<String, PropertyFieldInfo>> CLASS_TO_NAME_TO_FIELD_INFO;

    static {
        IdentityHashMap<Class<?>, List<PropertyFieldInfo>> classToFieldInfos =
                new IdentityHashMap<>(128);
        collectFieldInfos(TurmsProperties.class, classToFieldInfos);

        CLASS_TO_FIELD_INFOS = classToFieldInfos;
        CLASS_TO_NAME_TO_FIELD_INFO = new IdentityHashMap<>(CLASS_TO_FIELD_INFOS.size());
        for (Map.Entry<Class<?>, List<PropertyFieldInfo>> classAndFieldInfos : classToFieldInfos
                .entrySet()) {
            List<PropertyFieldInfo> fieldInfos = classAndFieldInfos.getValue();
            Map<String, PropertyFieldInfo> nameToFieldInfo =
                    CollectionUtil.newMapWithExpectedSize(fieldInfos.size());
            for (PropertyFieldInfo fieldInfo : fieldInfos) {
                nameToFieldInfo.put(fieldInfo.field()
                        .getName(), fieldInfo);
            }
            CLASS_TO_NAME_TO_FIELD_INFO.put(classAndFieldInfos.getKey(),
                    Map.copyOf(nameToFieldInfo));
        }
        METADATA = getMetadata(TurmsProperties.class, false);
        ONLY_MUTABLE_METADATA = getMetadata(TurmsProperties.class, true);
    }

    private TurmsPropertiesInspector() {
    }

    public static Map<String, Object> convertPropertiesToValueMap(
            TurmsProperties turmsProperties,
            boolean returnOnlyMutableProperties) {
        try {
            return returnOnlyMutableProperties
                    ? JsonUtil.readStringObjectMapValue(
                            MUTABLE_PROPERTIES_WRITER.writeValueAsBytes(turmsProperties))
                    : JsonUtil.readStringObjectMapValue(MAPPER.writeValueAsBytes(turmsProperties));
        } catch (JsonProcessingException e) {
            throw new SerializationException("Failed to write turms properties as bytes", e);
        }
    }

    @Nullable
    public static PropertyFieldInfo getFieldInfo(Class<?> propertiesClass, String fieldName) {
        Map<String, PropertyFieldInfo> map = CLASS_TO_NAME_TO_FIELD_INFO.get(propertiesClass);
        if (map == null) {
            return null;
        }
        return map.get(fieldName);
    }

    @Nullable
    public static List<PropertyFieldInfo> getFieldInfos(Class<?> propertiesClass) {
        return CLASS_TO_FIELD_INFOS.get(propertiesClass);
    }

    private static void collectFieldInfos(
            Class<?> propertiesClass,
            Map<Class<?>, List<PropertyFieldInfo>> classToFieldInfosOutput) {
        List<Field> fields = ClassUtil.getNonStaticFields(propertiesClass);
        List<PropertyFieldInfo> propertyFieldInfos = new ArrayList<>(fields.size());
        for (Field field : fields) {
            ReflectionUtil.setAccessible(field);
            boolean isNestedProperty = field.isAnnotationPresent(NestedConfigurationProperty.class);
            if (isNestedProperty) {
                collectFieldInfos(field.getType(), classToFieldInfosOutput);
            }
            PropertyConstraints constraints =
                    PropertyConstraints.of(field.getDeclaredAnnotation(Min.class),
                            field.getDeclaredAnnotation(Max.class),
                            field.getDeclaredAnnotation(LessThanOrEqualTo.class),
                            field.getDeclaredAnnotation(Size.class),
                            field.getDeclaredAnnotation(ValidCron.class));
            propertyFieldInfos.add(new PropertyFieldInfo(
                    field,
                    VarAccessorFactory.get(field),
                    constraints,
                    field.isAnnotationPresent(MutableProperty.class),
                    isNestedProperty));
        }
        classToFieldInfosOutput.put(propertiesClass, propertyFieldInfos);
    }

    private static Map<String, Object> getMetadata(Class<?> clazz, boolean onlyMutable) {
        List<PropertyFieldInfo> fieldInfos = CLASS_TO_FIELD_INFOS.get(clazz);
        List<Map.Entry<String, Object>> entries = new ArrayList<>(fieldInfos.size());
        for (PropertyFieldInfo fieldInfo : fieldInfos) {
            Field field = fieldInfo.field();
            if (Modifier.isTransient(field.getModifiers())
                    || (onlyMutable
                            && (!fieldInfo.isMutableProperty() && !fieldInfo.isNestedProperty()))) {
                continue;
            }
            Object fieldMetadata = fieldInfo.isNestedProperty()
                    ? getMetadata(field.getType(), onlyMutable)
                    : getFlatFieldMetadata(fieldInfo);
            entries.add(Map.entry(field.getName(), fieldMetadata));
        }
        return CollectionUtil.newImmutableMap(entries);
    }

    private static FieldMetadata getFlatFieldMetadata(PropertyFieldInfo fieldInfo) {
        Field field = fieldInfo.field();
        Class<?> type = field.getType();
        String elementType = null;
        Object[] options = null;
        if (Iterable.class.isAssignableFrom(type)) {
            Class<?> elementClass = ClassUtil.getElementClass(field.getGenericType());
            elementType = getTypeName(elementClass);
            if (elementClass.isEnum()) {
                options = elementClass.getEnumConstants();
            }
        } else if (type.isArray()) {
            elementType = getTypeName(type.getComponentType());
        } else if (type.isEnum()) {
            options = type.getEnumConstants();
        }
        Description descriptionAnnotation = field.getDeclaredAnnotation(Description.class);
        return new FieldMetadata(
                field.isAnnotationPresent(Deprecated.class),
                field.isAnnotationPresent(GlobalProperty.class),
                fieldInfo.isMutableProperty(),
                field.isAnnotationPresent(SensitiveProperty.class),
                getTypeName(type),
                elementType,
                options,
                descriptionAnnotation == null
                        ? null
                        : descriptionAnnotation.value());
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
