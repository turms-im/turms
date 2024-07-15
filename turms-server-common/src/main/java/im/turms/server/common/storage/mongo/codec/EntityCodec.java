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

package im.turms.server.common.storage.mongo.codec;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import im.turms.server.common.infra.lang.PrimitiveUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.serialization.DeserializationException;
import im.turms.server.common.infra.serialization.SerializationException;
import im.turms.server.common.storage.mongo.CodecPool;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.entity.EntityField;
import im.turms.server.common.storage.mongo.entity.MongoEntity;
import im.turms.server.common.storage.mongo.entity.MongoEntityFactory;

/**
 * @author James Chen
 * @see org.bson.codecs.pojo.PojoCodecImpl
 */
public class EntityCodec<T> extends MongoCodec<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntityCodec.class);

    private static final Map<IterableCodecKey<?, ?>, TurmsIterableCodec<?, ?>> KEY_TO_ITERABLE_CODEC =
            new ConcurrentHashMap<>(32);
    private static final Map<MapCodecKey<?, ?>, TurmsMapCodec<?, ?>> KEY_TO_MAP_CODEC =
            new ConcurrentHashMap<>(32);
    private static final Map<EnumCodecKey<?>, MongoCodec<?>> KEY_TO_ENUM_CODEC =
            new ConcurrentHashMap<>(32);

    private final CodecRegistry registry;
    private final Class<T> entityClass;
    private final MongoEntity<T> entity;

    public EntityCodec(CodecRegistry registry, Class<T> entityClass) {
        super(entityClass);
        this.registry = registry;
        this.entityClass = entityClass;
        entity = MongoEntityFactory.parse(entityClass);
    }

    @Override
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        T instance = null;
        Object[] ctorValues = null;
        try {
            Constructor<T> constructor = entity.constructor();
            if (constructor.getParameterCount() == 0) {
                instance = constructor.newInstance();
                initInstance(instance, reader, decoderContext);
                return instance;
            } else {
                ctorValues = parseCtorValues(reader, decoderContext);
                return constructor.newInstance(ctorValues);
            }
        } catch (Exception e) {
            throw new DeserializationException(
                    "Failed to decode the current Bson into the entity of the class: "
                            + entity.entityClass()
                                    .getName()
                            + ", instance: "
                            + instance
                            + ", constructor arguments: "
                            + Arrays.toString(ctorValues),
                    e);
        }
    }

    @Override
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        try {
            if (CodecPool.UPSERT_ENCODER_CONTEXT == encoderContext) {
                encodeValueForUpsert(writer, value, encoderContext);
            } else {
                encodeValue(writer, value, encoderContext);
            }
        } catch (Exception e) {
            throw new SerializationException(
                    "Failed to encode the entity of the class: "
                            + entity.entityClass()
                                    .getName(),
                    e);
        }
        writer.writeEndDocument();
    }

    private void encodeValue(BsonWriter writer, T value, EncoderContext encoderContext) {
        for (EntityField<?> field : entity.fields()) {
            Object fieldValue = field.get(value);
            if (fieldValue == null) {
                continue;
            }
            if (field.isIdField()) {
                writer.writeName(DomainFieldName.ID);
            } else {
                writer.writeName(field.getName());
            }
            Codec codec = getCodec(field);
            codec.encode(writer, fieldValue, encoderContext);
        }
    }

    /**
     * Compared to {@link EntityCodec#encodeValue}, this method encodes null fields because we need
     * to overwrite existing fields to null if these fields are not specified in the entity for our
     * use cases.
     */
    private void encodeValueForUpsert(BsonWriter writer, T value, EncoderContext encoderContext) {
        for (EntityField<?> field : entity.fields()) {
            Object fieldValue = field.get(value);
            if (field.isIdField()) {
                writer.writeName(DomainFieldName.ID);
            } else {
                writer.writeName(field.getName());
            }
            if (fieldValue == null) {
                writer.writeNull();
                continue;
            }
            Codec codec = getCodec(field);
            codec.encode(writer, fieldValue, encoderContext);
        }
    }

    @Override
    public Class<T> getEncoderClass() {
        return entityClass;
    }

    private void initInstance(T instance, BsonReader reader, DecoderContext decoderContext) {
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            EntityField field = DomainFieldName.ID.equals(fieldName)
                    ? entity.getField(entity.idFieldName())
                    : entity.getField(fieldName);
            if (field == null) {
                LOGGER.warn("The field \"{}\" does not exist in the entity class: {}",
                        fieldName,
                        entity.entityClass()
                                .getName());
                reader.skipValue();
            } else {
                Object value = null;
                if (reader.getCurrentBsonType() == BsonType.NULL) {
                    reader.readNull();
                } else {
                    try {
                        value = decode(field, reader, decoderContext);
                    } catch (Exception e) {
                        throw new DeserializationException(
                                "Failed to decode the field \""
                                        + fieldName
                                        + "\" of the entity class: "
                                        + entity.entityClass()
                                                .getName(),
                                e);
                    }
                }
                try {
                    field.set(instance, value);
                } catch (Exception e) {
                    throw new DeserializationException(
                            "Failed to set the field \""
                                    + fieldName
                                    + "\" of the entity class: "
                                    + entity.entityClass()
                                            .getName(),
                            e);
                }
            }
        }
        reader.readEndDocument();
    }

    private Object[] parseCtorValues(BsonReader reader, DecoderContext decoderContext) {
        Object[] values = new Object[entity.constructor()
                .getParameters().length];
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            EntityField<T> field = DomainFieldName.ID.equals(fieldName)
                    ? entity.getField(entity.idFieldName())
                    : entity.getField(fieldName);
            if (field == null) {
                LOGGER.warn("The field \"{}\" does not exist in the entity class: {}",
                        fieldName,
                        entity.entityClass()
                                .getName());
                reader.skipValue();
            } else {
                Object value = null;
                if (reader.getCurrentBsonType() == BsonType.NULL) {
                    reader.readNull();
                } else {
                    try {
                        value = decode(field, reader, decoderContext);
                    } catch (Exception e) {
                        throw new DeserializationException(
                                "Failed to decode the field \""
                                        + fieldName
                                        + "\" of the class: "
                                        + entity.entityClass()
                                                .getName(),
                                e);
                    }
                }
                values[field.getCtorParamIndex()] = value;
            }
        }
        reader.readEndDocument();
        return values;
    }

    private <F> F decode(EntityField<F> field, BsonReader reader, DecoderContext decoderContext) {
        Codec<F> codec = getCodec(field);
        return decoderContext.decodeWithChildContext(codec, reader);
    }

    private <F> Codec<F> getCodec(EntityField<F> field) {
        Class<?> fieldClass = field.getFieldClass();
        if (Iterable.class.isAssignableFrom(fieldClass)) {
            return (Codec<F>) KEY_TO_ITERABLE_CODEC.computeIfAbsent(new IterableCodecKey<>(
                    fieldClass,
                    field.getElementClass(),
                    field.isEnumNumber()), key -> {
                        TurmsIterableCodec<?, ?> iterableCodec = new TurmsIterableCodec<>(
                                key.iterableClass,
                                key.elementClass,
                                key.isEnumNumber);
                        iterableCodec.setRegistry(registry);
                        return iterableCodec;
                    });
        } else if (Map.class.isAssignableFrom(fieldClass)) {
            return (Codec<F>) KEY_TO_MAP_CODEC.computeIfAbsent(new MapCodecKey<>(
                    (Class) fieldClass,
                    field.getKeyClass(),
                    field.getElementClass(),
                    field.isEnumNumber()), key -> {
                        TurmsMapCodec<?, ?> mapCodec = new TurmsMapCodec(
                                key.ownerClass,
                                key.keyClass,
                                key.valueClass,
                                key.isEnumNumber);
                        mapCodec.setRegistry(registry);
                        return mapCodec;
                    });
        } else if (fieldClass.isEnum()) {
            return (Codec<F>) KEY_TO_ENUM_CODEC.computeIfAbsent(
                    new EnumCodecKey<>(fieldClass, field.isEnumNumber()),
                    key -> key.isEnumNumber
                            ? new EnumNumberCodec<>((Class) key.fieldClass)
                            : new EnumStringCodec<>((Class) key.fieldClass));
        } else {
            if (fieldClass.isPrimitive()) {
                fieldClass = PrimitiveUtil.primitiveToWrapper(fieldClass);
            }
            return (Codec<F>) registry.get(fieldClass);
        }
    }

    private record IterableCodecKey<I, E>(
            Class<I> iterableClass,
            Class<E> elementClass,
            boolean isEnumNumber
    ) {
    }

    private record MapCodecKey<K, V>(
            Class<Map<K, V>> ownerClass,
            Class<K> keyClass,
            Class<V> valueClass,
            boolean isEnumNumber
    ) {
    }

    private record EnumCodecKey<F>(
            Class<F> fieldClass,
            boolean isEnumNumber
    ) {
    }
}