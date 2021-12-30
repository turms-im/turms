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

package im.turms.server.common.mongo.entity;

import com.google.common.base.CaseFormat;
import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import im.turms.server.common.mongo.BsonPool;
import im.turms.server.common.mongo.entity.annotation.CompoundIndex;
import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import im.turms.server.common.mongo.entity.annotation.PropertySetter;
import im.turms.server.common.mongo.entity.annotation.Sharded;
import im.turms.server.common.mongo.entity.annotation.WithTemperature;
import im.turms.server.common.util.ReflectionUtil;
import lombok.Data;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mapping.PreferredConstructor;
import org.springframework.data.mapping.model.PreferredConstructorDiscoverer;
import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
public final class MongoEntityFactory {

    public <T> MongoEntity<T> parse(Class<T> clazz) {
        PreferredConstructor<T, ?> constructor = PreferredConstructorDiscoverer.discover(clazz);
        EntityFieldsInfo entityFieldsInfo = parseFields(clazz, constructor, clazz.getDeclaredMethods());
        ShardKey shardKey = parseShardKey(clazz);
        return new MongoEntity<>(
                clazz,
                constructor,
                parseCollectionName(clazz),
                shardKey,
                parseZone(clazz, shardKey),
                parseCompoundIndex(clazz),
                entityFieldsInfo.getIndexes(),
                entityFieldsInfo.getIdFieldName(),
                entityFieldsInfo.getFields()
        );
    }

    private <T> Zone parseZone(Class<T> clazz, ShardKey shardKey) {
        WithTemperature temperature = clazz.getAnnotation(WithTemperature.class);
        if (temperature == null) {
            return null;
        }
        String creationDateFieldName = temperature.creationDateFieldName();
        if (!StringUtils.hasText(creationDateFieldName)) {
            throw new IllegalStateException(
                    "The creationDateFieldName of @WithTemperature must not be blank for the class " + clazz.getName());
        }
        if (!shardKey.document().containsKey(creationDateFieldName)) {
            throw new IllegalStateException(
                    "The creationDateFieldName of @WithTemperature must be a part of the shard key of the class " + clazz.getName());
        }
        return new Zone(creationDateFieldName);
    }

    private String parseCollectionName(Class<?> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        String name = document != null && StringUtils.hasText(document.value())
                ? document.value()
                : CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, clazz.getSimpleName()).intern();
        if (!StringUtils.hasText(name)) {
            throw new IllegalStateException("The collection name must not be blank for the class " + clazz.getName());
        }
        return name;
    }

    /**
     * @implNote The method doesn't the support shard keys that contains a hashed key supported in 4.4
     */
    private <T> IndexModel parseCompoundIndex(Class<T> clazz) {
        CompoundIndex index = clazz.getAnnotation(CompoundIndex.class);
        if (index == null) {
            return null;
        }
        String[] fields = index.value();
        if (fields.length == 0) {
            throw new IllegalStateException("CompoundIndex doesn't specify which fields to index for the class " + clazz.getName());
        }
        BsonDocument document = new BsonDocument();
        for (String key : fields) {
            document.append(key, BsonPool.BSON_INT32_1);
        }
        return new IndexModel(document);
    }

    /**
     * @implNote The method doesn't the support shard keys that contains a hashed key supported in 4.4
     */
    @Nullable
    private ShardKey parseShardKey(Class<?> clazz) {
        Sharded sharded = clazz.getAnnotation(Sharded.class);
        if (sharded == null) {
            return null;
        }
        String[] keys = sharded.shardKey();
        if (keys.length == 0) {
            // default shard key
            keys = new String[]{"_id"};
        }
        BsonDocument document;
        if (sharded.shardingStrategy().equals(ShardingStrategy.HASH)) {
            if (keys.length > 1) {
                throw new IllegalStateException("The hash sharding strategy can have only one shard key: " + clazz.getName());
            } else if (keys[0].equals("_id")) {
                throw new IllegalStateException("Should not create an hashed index on the key. " +
                        "If so, MongoDB will create a default range index on the key: " + clazz.getName());
            } else {
                document = new BsonDocument(keys[0], BsonPool.BSON_STRING_HASHED);
            }
        } else {
            document = new BsonDocument();
            for (String key : keys) {
                document.append(key, BsonPool.BSON_INT32_1);
            }
        }
        List<ShardKey.Path> paths = new ArrayList<>(2);
        for (String shardKey : document.keySet()) {
            // Note that we split the shard key one level at most (e.g. "_id.whatever")
            // because we don't have other cases currently
            String[] path = StringUtils.split(shardKey, ".");
            if (path == null) {
                paths.add(new ShardKey.Path(shardKey, new String[]{shardKey}));
            } else {
                paths.add(new ShardKey.Path(shardKey, path));
            }
        }
        return new ShardKey(document, paths);
    }

    private <T> EntityFieldsInfo parseFields(Class<?> clazz, PreferredConstructor<T, ?> constructor, Method[] allClassMethods) {
        Field[] fields = clazz.getDeclaredFields();
        String idField = null;
        Map<String, EntityField<?>> entityFields = null;
        List<IndexModel> entityIndexes = null;
        Map<String, MethodHandle> setterMethods = parseSetterMethods(allClassMethods);
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || field.isAnnotationPresent(Transient.class)) {
                continue;
            }
            // Classes
            Class<?> fieldClass = field.getType();
            Class keyClass = null;
            Class elementClass = null;
            if (Iterable.class.isAssignableFrom(fieldClass)) {
                elementClass = (Class) ((ParameterizedType) field.getGenericType()).getActualTypeArguments()[0];
            } else if (Map.class.isAssignableFrom(fieldClass)) {
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                Type[] actualTypes = parameterizedType.getActualTypeArguments();
                keyClass = (Class) actualTypes[0];
                elementClass = (Class) actualTypes[1];
            }
            // Id and field name
            String fieldName = parseFieldName(field);
            boolean isIdField = field.isAnnotationPresent(Id.class);
            if (isIdField) {
                if (idField == null) {
                    idField = fieldName;
                } else {
                    throw new IllegalStateException("The class " + clazz.getName() + " should not have multiple fields marked with @Id");
                }
            }
            // Indexes
            List<IndexModel> indexModels = parseIndexes(field);
            if (!indexModels.isEmpty()) {
                if (entityIndexes == null) {
                    entityIndexes = new ArrayList<>(8);
                }
                entityIndexes.addAll(indexModels);
            }
            // Getter and Setter
            MethodHandle getter = ReflectionUtil.getGetter(field);
            MethodHandle setter = setterMethods.get(fieldName);
            if (setter == null) {
                setter = ReflectionUtil.getSetter(field);
            }
            // Constructor
            Integer ctorParamIndex = constructor.isNoArgConstructor()
                    ? null
                    : parseCtorParamIndex(constructor, field);
            if (entityFields == null) {
                entityFields = new HashMap<>(16);
            }
            entityFields.put(fieldName,
                    new EntityField(fieldClass, keyClass, elementClass, fieldName, isIdField, ctorParamIndex, getter, setter));
        }
        return new EntityFieldsInfo(
                idField,
                entityFields == null ? Collections.emptyMap() : entityFields,
                entityIndexes == null ? Collections.emptyList() : entityIndexes);
    }

    private Map<String, MethodHandle> parseSetterMethods(Method[] methods) {
        Map<String, MethodHandle> setterMethods = null;
        for (Method method : methods) {
            if (method.isAnnotationPresent(PropertySetter.class)) {
                if (setterMethods == null) {
                    setterMethods = new HashMap<>(8);
                }
                String methodName = CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_CAMEL, method.getName().substring(3));
                setterMethods.put(methodName, ReflectionUtil.method2Handle(method));
            }
        }
        if (setterMethods == null) {
            return Collections.emptyMap();
        }
        return setterMethods;
    }

    private <T> int parseCtorParamIndex(PreferredConstructor<T, ?> constructor, Field field) {
        String fieldName = parseFieldName(field);
        String propertyName = field.getName();
        var params = constructor.getParameters();
        for (int i = 0; i < params.size(); i++) {
            var param = params.get(i);
            String paramName = param.getName();
            if (paramName.equals(fieldName) || paramName.equals(propertyName)) {
                return i;
            }
        }
        throw new IllegalStateException("Cannot find the index of the parameter %s in constructor".formatted(fieldName));
    }

    /**
     * @implNote Note that we just follow the original name without any naming convention
     */
    private String parseFieldName(Field field) {
        im.turms.server.common.mongo.entity.annotation.Field property =
                field.getAnnotation(im.turms.server.common.mongo.entity.annotation.Field.class);
        return property == null ? field.getName() : property.value();
    }

    /**
     * @return The indexes of the current field with its nested fields
     */
    private List<IndexModel> parseIndexes(Field field) {
        List<IndexModel> indexes = null;
        IndexModel index = parseIndex(null, field);
        if (index != null) {
            indexes = new ArrayList<>(1);
            indexes.add(index);
        }
        String fieldName = field.isAnnotationPresent(Id.class)
                ? "_id"
                : parseFieldName(field);
        for (Field subField : field.getType().getDeclaredFields()) {
            IndexModel subIndex = parseIndex(fieldName, subField);
            if (subIndex != null) {
                if (indexes == null) {
                    indexes = new ArrayList<>(1);
                }
                indexes.add(subIndex);
            }
        }
        return indexes == null
                ? Collections.emptyList()
                : indexes;
    }

    /**
     * @return The index of the current field without its nested fields
     */
    @Nullable
    private IndexModel parseIndex(String parentFieldName, Field field) {
        Indexed indexed = field.getAnnotation(Indexed.class);
        if (indexed == null) {
            return null;
        }
        IndexType indexType = indexed.value();
        BsonValue index = indexType.equals(IndexType.RANGE)
                ? BsonPool.BSON_INT32_1
                : BsonPool.BSON_STRING_HASHED;
        long expireAfterSeconds = indexed.expireAfterSeconds();
        IndexOptions options = new IndexOptions();
        if (expireAfterSeconds > 0) {
            options.expireAfter(expireAfterSeconds, TimeUnit.SECONDS);
        }
        String partialFilter = indexed.partialFilter();
        if (StringUtils.hasText(partialFilter)) {
            options.partialFilterExpression(BsonDocument.parse(partialFilter));
        }
        String fieldName = parseFieldName(field);
        String name = parentFieldName == null
                ? fieldName
                : parentFieldName + "." + fieldName;
        return new IndexModel(new BsonDocument(name, index), options);
    }

    @Data
    private static class EntityFieldsInfo {
        @Nullable
        private final String idFieldName;
        private final Map<String, EntityField<?>> fields;
        private final List<IndexModel> indexes;
    }

}
