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
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.PrimitiveUtil;
import im.turms.server.common.infra.lang.Quadruple;
import im.turms.server.common.infra.lang.Triple;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.serialization.DeserializationException;
import im.turms.server.common.infra.serialization.SerializationException;
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

    private static final Map<Triple<Class<?>, Class<?>, Boolean>, TurmsIterableCodec> CLASS_TO_ITERABLE_CODEC =
            new ConcurrentHashMap<>(32);
    private static final Map<Quadruple<Class<?>, Class<?>, Class<?>, Boolean>, TurmsMapCodec> CLASS_TO_MAP_CODEC =
            new ConcurrentHashMap<>(32);
    private static final Map<Pair<Class<?>, Boolean>, MongoCodec<?>> CLASS_TO_ENUM_CODEC =
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
        try {
            Constructor<T> constructor = entity.constructor();
            if (constructor.getParameterCount() == 0) {
                T instance = constructor.newInstance();
                initInstance(instance, reader, decoderContext);
                return instance;
            } else {
                Object[] ctorValues = parseCtorValues(reader, decoderContext);
                return constructor.newInstance(ctorValues);
            }
        } catch (Exception e) {
            throw new DeserializationException(
                    "Failed to decode the current Bson into the entity of the class: "
                            + entity.entityClass()
                                    .getName(),
                    e);
        }
    }

    @Override
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        try {
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
                encoderContext.encodeWithChildContext(codec, writer, fieldValue);
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
            return CLASS_TO_ITERABLE_CODEC.computeIfAbsent(
                    Triple.of(fieldClass, field.getElementClass(), field.isEnumNumber()),
                    triple -> {
                        TurmsIterableCodec iterableCodec = new TurmsIterableCodec(
                                triple.first(),
                                triple.second(),
                                triple.third());
                        iterableCodec.setRegistry(registry);
                        return iterableCodec;
                    });
        } else if (Map.class.isAssignableFrom(fieldClass)) {
            Quadruple<Class<?>, Class<?>, Class<?>, Boolean> key = Quadruple.of(fieldClass,
                    field.getKeyClass(),
                    field.getElementClass(),
                    field.isEnumNumber());
            return CLASS_TO_MAP_CODEC.computeIfAbsent(key, quadruple -> {
                TurmsMapCodec<?, ?> mapCodec = new TurmsMapCodec(
                        quadruple.first(),
                        quadruple.second(),
                        quadruple.third(),
                        quadruple.fourth());
                mapCodec.setRegistry(registry);
                return mapCodec;
            });
        } else if (fieldClass.isEnum()) {
            Pair<Class<?>, Boolean> pair = Pair.of(fieldClass, field.isEnumNumber());
            return (Codec<F>) CLASS_TO_ENUM_CODEC.computeIfAbsent(pair,
                    key -> key.second()
                            ? new EnumNumberCodec<>((Class) key.first())
                            : new EnumStringCodec<>((Class) key.first()));
        } else {
            if (fieldClass.isPrimitive()) {
                fieldClass = PrimitiveUtil.primitiveToWrapper(fieldClass);
            }
            return (Codec<F>) registry.get(fieldClass);
        }
    }
}