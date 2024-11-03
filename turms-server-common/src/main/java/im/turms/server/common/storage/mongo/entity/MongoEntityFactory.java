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

package im.turms.server.common.storage.mongo.entity;

import java.io.IOException;
import java.io.InputStream;
import java.lang.invoke.MethodHandle;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Nullable;

import com.mongodb.client.model.IndexModel;
import com.mongodb.client.model.IndexOptions;
import org.bson.BsonDocument;
import org.bson.BsonValue;
import org.springframework.util.StringUtils;

import im.turms.server.common.domain.common.po.Customizable;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.reflect.ReflectionUtil;
import im.turms.server.common.infra.reflect.VarAccessor;
import im.turms.server.common.infra.reflect.VarAccessorFactory;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.codec.EntityCodec;
import im.turms.server.common.storage.mongo.entity.annotation.CompoundIndex;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.EnumNumber;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.PersistenceConstructor;
import im.turms.server.common.storage.mongo.entity.annotation.PropertySetter;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;
import im.turms.server.common.storage.mongo.entity.annotation.TieredStorage;

/**
 * @author James Chen
 */
public final class MongoEntityFactory {

    private MongoEntityFactory() {
    }

    public static <T> MongoEntity<T> parse(Class<T> clazz) {
        Constructor<T> constructor = findConstructor(clazz);
        EntityFieldsInfo entityFieldsInfo = parseFields(clazz, constructor);
        ShardKey shardKey = parseShardKey(clazz);
        String collectionName = parseCollectionName(clazz);
        List<EntityField<?>> fields = new ArrayList<>(entityFieldsInfo.nameToField.values());
        // Sort the fields by name to ensure serializing fields in a consistent order.
        // And that's important when querying embedded documents
        fields.sort(Comparator.comparing(EntityField::name));
        return new MongoEntity<>(
                clazz,
                constructor,
                collectionName,
                findCollectionSchema(collectionName),
                shardKey,
                parseZone(clazz, shardKey),
                parseCompoundIndex(clazz),
                entityFieldsInfo.indexes,
                entityFieldsInfo.idFieldName,
                entityFieldsInfo.nameToField,
                fields);
    }

    private static <T> Constructor<T> findConstructor(Class<T> entityClass) {
        Constructor<T>[] declaredConstructors =
                (Constructor<T>[]) entityClass.getDeclaredConstructors();
        if (declaredConstructors.length == 1) {
            Constructor<T> constructor = declaredConstructors[0];
            ReflectionUtil.setAccessible(constructor);
            return constructor;
        }
        for (Constructor<T> candidate : declaredConstructors) {
            if (candidate.isAnnotationPresent(PersistenceConstructor.class)
                    || candidate.getParameterCount() == 0) {
                ReflectionUtil.setAccessible(candidate);
                return candidate;
            }
        }
        String message = "Could not find a constructor for the entity class: "
                + entityClass.getName();
        throw new IllegalArgumentException(message);
    }

    @Nullable
    private static <T> Zone parseZone(Class<T> clazz, ShardKey shardKey) {
        TieredStorage storage = clazz.getAnnotation(TieredStorage.class);
        if (storage == null) {
            return null;
        }
        String creationDateFieldName = storage.creationDateFieldName();
        if (!StringUtils.hasText(creationDateFieldName)) {
            throw new IllegalArgumentException(
                    "The value of the element \"creationDateFieldName\" of @"
                            + TieredStorage.class.getSimpleName()
                            + " must not be blank for the entity class: "
                            + clazz.getName());
        }
        if (!shardKey.document()
                .containsKey(creationDateFieldName)) {
            throw new IllegalArgumentException(
                    "The value of the element \"creationDateFieldName\" of @"
                            + TieredStorage.class.getSimpleName()
                            + " must be a part of the shard key of the entity class: "
                            + clazz.getName());
        }
        return new Zone(creationDateFieldName);
    }

    private static String parseCollectionName(Class<?> clazz) {
        Document document = clazz.getAnnotation(Document.class);
        String name = document != null && StringUtils.hasText(document.value())
                ? document.value()
                : StringUtil.upperCamelToLowerCamelLatin1(clazz.getSimpleName())
                        .intern();
        if (!StringUtils.hasText(name)) {
            throw new IllegalArgumentException(
                    "The collection name must not be blank for the entity class: "
                            + clazz.getName());
        }
        return name;
    }

    @Nullable
    private static BsonDocument findCollectionSchema(String name) {
        String resourceName = "schema/"
                + StringUtil.lowerCamelToLowerHyphenLatin1(name)
                + ".json";
        InputStream stream = MongoEntityFactory.class.getClassLoader()
                .getResourceAsStream(resourceName);
        if (stream == null) {
            return null;
        }
        byte[] bytes;
        try {
            bytes = stream.readAllBytes();
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to read bytes from the resource: "
                            + resourceName,
                    e);
        }
        return BsonDocument.parse(StringUtil.newLatin1String(bytes));
    }

    /**
     * @implNote The method doesn't the support shard keys that contains a hashed key supported in
     *           4.4
     */
    private static <T> List<im.turms.server.common.storage.mongo.entity.CompoundIndex> parseCompoundIndex(
            Class<T> clazz) {
        CompoundIndex[] indexes = clazz.getAnnotationsByType(CompoundIndex.class);
        if (indexes.length == 0) {
            return Collections.emptyList();
        }
        List<im.turms.server.common.storage.mongo.entity.CompoundIndex> list =
                new ArrayList<>(indexes.length);
        for (CompoundIndex index : indexes) {
            String[] fields = index.value();
            if (fields.length == 0) {
                throw new IllegalArgumentException(
                        "The compound index of the entity class ("
                                + clazz.getName()
                                + ") must specify which fields to index");
            }
            BsonDocument document = new BsonDocument();
            for (String key : fields) {
                document.append(key, BsonPool.BSON_INT32_1);
            }
            list.add(new im.turms.server.common.storage.mongo.entity.CompoundIndex(
                    index,
                    new IndexModel(document)));
        }
        return list;
    }

    /**
     * @implNote The method doesn't the support shard keys that contains a hashed key supported in
     *           4.4
     */
    @Nullable
    private static ShardKey parseShardKey(Class<?> clazz) {
        Sharded sharded = clazz.getAnnotation(Sharded.class);
        if (sharded == null) {
            return null;
        }
        String[] keys = sharded.shardKey();
        if (keys.length == 0) {
            // default shard key
            keys = new String[]{DomainFieldName.ID};
        }
        BsonDocument document;
        if (sharded.shardingStrategy()
                .equals(ShardingStrategy.HASH)) {
            if (keys.length > 1) {
                throw new IllegalArgumentException(
                        "The hash sharding strategy can have only one shard key, "
                                + "but the entity class ("
                                + clazz.getName()
                                + ") has multiple shard keys: "
                                + Arrays.toString(keys));
            } else if (keys[0].equals(DomainFieldName.ID)) {
                throw new IllegalArgumentException(
                        "The entity class ("
                                + clazz.getName()
                                + ") must not create a hashed index on the collection key: \""
                                + DomainFieldName.ID
                                + "\". If so, MongoDB will create a default range index on the key");
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
            // because we don't have other cases currently.
            Pair<String, String> pair = StringUtil.splitLatin1(shardKey, AsciiCode.PERIOD);
            String[] path = pair == null
                    ? new String[]{shardKey}
                    : new String[]{pair.first(), pair.second()};
            paths.add(new ShardKey.Path(shardKey.startsWith(DomainFieldName.ID), shardKey, path));
        }
        return new ShardKey(document, paths);
    }

    private static <T> EntityFieldsInfo parseFields(Class<T> clazz, Constructor<T> constructor) {
        List<Class<?>> classes = new ArrayList<>(8);
        Class<?> currentClass = clazz;
        do {
            classes.add(currentClass);
            currentClass = currentClass.getSuperclass();
        } while (currentClass != null && currentClass != Object.class);
        // Reverse the classes so that the child can override
        // the parent's fields and methods when parsing.
        classes = classes.reversed();

        String idField = null;
        Map<String, EntityField<?>> nameToField = null;
        List<Index> entityIndexes = null;
        List<Field> fields = parseFields(classes);
        Map<String, MethodHandle> propertyNameToSetter = parseSetterMethods(classes);
        boolean hasParameters = constructor.getParameterCount() > 0;
        Parameter[] parameters = hasParameters
                ? constructor.getParameters()
                : null;
        boolean isUserDefinedAttributesFieldRequired = Customizable.class.isAssignableFrom(clazz);
        boolean isUserDefinedAttributesFieldFound = false;
        for (Field field : fields) {
            int modifiers = field.getModifiers();
            if (Modifier.isStatic(modifiers) || Modifier.isTransient(modifiers)) {
                continue;
            }
            // Classes
            Class<?> fieldClass = field.getType();
            Class keyClass = null;
            Class elementClass = null;
            if (Iterable.class.isAssignableFrom(fieldClass)) {
                elementClass = ClassUtil.getIterableElementClass(field);
            } else if (Map.class.isAssignableFrom(fieldClass)) {
                Pair<Class<?>, Class<?>> keyAndElement =
                        ClassUtil.getMapKeyClassAndElementClass(field);
                keyClass = keyAndElement.first();
                elementClass = keyAndElement.second();
            }

            // ID, field name and type
            String fieldName = parseFieldName(field);
            EntityFieldType fieldType = EntityFieldType.NORMAL;
            boolean isIdField = field.isAnnotationPresent(Id.class);
            if (isIdField) {
                if (idField == null) {
                    idField = fieldName;
                    fieldType = EntityFieldType.ID;
                } else {
                    throw new IllegalArgumentException(
                            "The entity class ("
                                    + clazz.getName()
                                    + ") must not have multiple fields marked with @"
                                    + Id.class.getSimpleName());
                }
            }
            if (isUserDefinedAttributesFieldRequired
                    && Customizable.USER_DEFINED_ATTRIBUTES_FIELD_NAME.equals(field.getName())) {
                if (isIdField) {
                    throw new IllegalArgumentException(
                            "The entity class ("
                                    + clazz.getName()
                                    + ") must not have a field named \""
                                    + Customizable.USER_DEFINED_ATTRIBUTES_FIELD_NAME
                                    + "\" that is also marked with @"
                                    + Id.class.getSimpleName());
                }
                if (keyClass == null) {
                    throw new IllegalArgumentException(
                            "The entity class ("
                                    + clazz.getName()
                                    + ") must have a field named \""
                                    + Customizable.USER_DEFINED_ATTRIBUTES_FIELD_NAME
                                    + "\" that is a Map");
                }
                fieldType = EntityFieldType.USER_DEFINED_ATTRIBUTES;
                isUserDefinedAttributesFieldFound = true;
            }
            // Indexes
            List<Index> indexes = parseIndexes(field);
            if (!indexes.isEmpty()) {
                if (entityIndexes == null) {
                    entityIndexes = new ArrayList<>(8);
                }
                entityIndexes.addAll(indexes);
            }
            // Getter and Setter
            MethodHandle setter = propertyNameToSetter.get(fieldName);
            VarAccessor<T, ?> varAccessor = setter == null
                    ? VarAccessorFactory.get(field)
                    : VarAccessorFactory.get(field, setter);
            // Constructor
            int constructorParamIndex = hasParameters
                    ? parseConstructorParamIndex(constructor, parameters, field)
                    : EntityField.UNSET_CTOR_PARAM_INDEX;
            if (nameToField == null) {
                nameToField = CollectionUtil.newMapWithExpectedSize(fields.size());
            }
            nameToField.put(fieldName,
                    new EntityField<>(
                            fieldClass,
                            keyClass,
                            elementClass,
                            fieldName,
                            fieldType,
                            constructorParamIndex,
                            varAccessor,
                            field.isAnnotationPresent(EnumNumber.class)));
        }
        if (isUserDefinedAttributesFieldRequired && !isUserDefinedAttributesFieldFound) {
            throw new IllegalArgumentException(
                    "The entity class ("
                            + clazz.getName()
                            + ") must have a field named \""
                            + Customizable.USER_DEFINED_ATTRIBUTES_FIELD_NAME
                            + "\" that is a Map");
        }
        return new EntityFieldsInfo(
                idField,
                nameToField == null
                        ? Collections.emptyMap()
                        : Map.copyOf(nameToField),
                entityIndexes == null
                        ? Collections.emptyList()
                        : entityIndexes);
    }

    private static List<Field> parseFields(List<Class<?>> classes) {
        List<Field> fields = null;
        for (Class<?> clazz : classes) {
            Field[] declaredFields = clazz.getDeclaredFields();
            for (Field field : declaredFields) {
                if (fields == null) {
                    fields = new ArrayList<>(declaredFields.length);
                }
                fields.add(field);
            }
        }
        return fields == null
                ? Collections.emptyList()
                : fields;
    }

    private static Map<String, MethodHandle> parseSetterMethods(List<Class<?>> classes) {
        Map<String, MethodHandle> setterMethods = null;
        for (Class<?> clazz : classes) {
            for (Method method : clazz.getDeclaredMethods()) {
                if (!method.isAnnotationPresent(PropertySetter.class)) {
                    continue;
                }
                if (setterMethods == null) {
                    setterMethods = new HashMap<>(8);
                }
                String methodName = method.getName();
                if (!methodName.startsWith("set")) {
                    throw new IllegalArgumentException(
                            "The setter method name must start with \"set\"");
                }
                String propertyName =
                        StringUtil.upperCamelToLowerCamelLatin1(methodName.substring(3));
                setterMethods.put(propertyName, ReflectionUtil.method2Handle(method));
            }
        }
        return setterMethods == null
                ? Collections.emptyMap()
                : setterMethods;
    }

    private static <T> int parseConstructorParamIndex(
            Constructor<T> constructor,
            Parameter[] params,
            Field field) {
        String fieldName = parseFieldName(field);
        String propertyName = field.getName();
        for (int i = 0, length = params.length; i < length; i++) {
            String paramName = params[i].getName();
            if (paramName.equals(fieldName) || paramName.equals(propertyName)) {
                return i;
            }
        }
        throw new IllegalArgumentException(
                "Could not find the index of the parameter \""
                        + fieldName
                        + "\" in the constructor: "
                        + constructor.getName());
    }

    /**
     * @implNote Note that we just use the original specified field name without modification.
     */
    @Nullable
    private static String parseFieldName(Field field) {
        var property = field
                .getAnnotation(im.turms.server.common.storage.mongo.entity.annotation.Field.class);
        return property == null
                ? field.getName()
                : property.value();
    }

    /**
     * @return The indexes of the current field with its nested fields
     */
    private static List<Index> parseIndexes(Field field) {
        List<Index> indexes = null;
        Indexed indexed = field.getAnnotation(Indexed.class);
        IndexModel index = parseIndex(null, field, indexed);
        if (index != null) {
            indexes = new ArrayList<>(1);
            indexes.add(new Index(field, indexed, index));
        }
        String fieldName = field.isAnnotationPresent(Id.class)
                ? DomainFieldName.ID
                : parseFieldName(field);
        for (Field subField : field.getType()
                .getDeclaredFields()) {
            Indexed subFieldIndexed = subField.getAnnotation(Indexed.class);
            IndexModel subIndex = parseIndex(fieldName, subField, subFieldIndexed);
            if (subIndex != null) {
                if (indexes == null) {
                    indexes = new ArrayList<>(1);
                }
                indexes.add(new Index(subField, subFieldIndexed, subIndex));
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
    private static IndexModel parseIndex(
            @Nullable String parentFieldName,
            Field field,
            @Nullable Indexed indexed) {
        if (indexed == null) {
            return null;
        }
        IndexType indexType = indexed.value();
        BsonValue index = indexType.equals(IndexType.RANGE)
                ? BsonPool.BSON_INT32_1
                : BsonPool.BSON_STRING_HASHED;
        IndexOptions options = new IndexOptions();
        options.unique(indexed.unique());
        long expireAfterSeconds = indexed.expireAfterSeconds();
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
                : parentFieldName
                        + "."
                        + fieldName;
        return new IndexModel(new BsonDocument(name, index), options);
    }

    private record EntityFieldsInfo(
            @Nullable String idFieldName,
            Map<String, EntityField<?>> nameToField,
            List<Index> indexes
    ) {
    }

}