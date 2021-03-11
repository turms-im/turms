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

package im.turms.server.common.mongo.codec;

import im.turms.server.common.mongo.entity.EntityField;
import im.turms.server.common.mongo.entity.MongoEntity;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.ClassUtils;
import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.springframework.data.mapping.PreferredConstructor;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 * @see org.bson.codecs.pojo.PojoCodecImpl
 */
@Log4j2
public class EntityCodec<T> implements Codec<T> {

    private static final String ID_FIELD = "_id";

    private final CodecRegistry registry;
    private final Class<T> entityClass;
    private final MongoEntity<T> entity;

    public EntityCodec(CodecRegistry registry, Class<T> entityClass) {
        this.registry = registry;
        this.entityClass = entityClass;
        entity = new MongoEntity<>(entityClass);
    }

    @Override
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        try {
            PreferredConstructor<T, ?> constructor = entity.getConstructor();
            Constructor<T> ctor = constructor.getConstructor();
            if (constructor.isNoArgConstructor()) {
                T instance = ctor.newInstance();
                initInstance(instance, reader, decoderContext);
                return instance;
            } else {
                Object[] ctorValues = parseCtorValues(reader, decoderContext);
                return ctor.newInstance(ctorValues);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to decode Bson to " + entity.getClazz().getName(), e);
        }
    }

    @Override
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        writer.writeStartDocument();
        try {
            for (EntityField<?> field : entity.getFieldMap().values()) {
                Object fieldValue = field.get(value);
                if (fieldValue != null) {
                    if (field.isIdField()) {
                        writer.writeName(ID_FIELD);
                    } else {
                        writer.writeName(field.getName());
                    }
                    Class<?> fieldValueClass = fieldValue.getClass();
                    if (Map.class.isAssignableFrom(fieldValueClass)) {
                        TurmsMapCodec codec = new TurmsMapCodec(field.getKeyClass(), field.getElementClass());
                        codec.setRegistry(registry);
                        encoderContext.encodeWithChildContext(codec, writer, (Map) fieldValue);
                    } else {
                        Codec codec = registry.get(fieldValueClass);
                        encoderContext.encodeWithChildContext(codec, writer, fieldValue);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to encode " + entity.getClazz().getName(), e);
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
            EntityField field = ID_FIELD.equals(fieldName)
                    ? entity.getField(entity.getIdFieldName())
                    : entity.getField(fieldName);
            if (field != null) {
                Object value = null;
                if (reader.getCurrentBsonType() == BsonType.NULL) {
                    reader.readNull();
                } else {
                    try {
                        value = decode(field, reader, decoderContext);
                    } catch (Exception e) {
                        String message =
                                String.format("Failed to decode the field %s of the class %s", fieldName, entity.getClazz().getName());
                        throw new IllegalStateException(message, e);
                    }
                }
                try {
                    field.set(instance, value);
                } catch (Exception e) {
                    String message = String.format("Failed to set the field %s of the class %s", fieldName, entity.getClazz().getName());
                    throw new IllegalStateException(message, e);
                }
            } else {
                log.warn("Found property not present in the entity: " + fieldName);
                reader.skipValue();
            }
        }
        reader.readEndDocument();
    }

    private Object[] parseCtorValues(BsonReader reader, DecoderContext decoderContext) {
        List<?> parameters = entity.getConstructor().getParameters();
        Object[] values = new Object[parameters.size()];
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String fieldName = reader.readName();
            EntityField<?> field = ID_FIELD.equals(fieldName)
                    ? entity.getField(entity.getIdFieldName())
                    : entity.getField(fieldName);
            if (field != null) {
                Object value = null;
                if (reader.getCurrentBsonType() == BsonType.NULL) {
                    reader.readNull();
                } else {
                    try {
                        value = decode(field, reader, decoderContext);
                    } catch (Exception e) {
                        String message =
                                String.format("Failed to decode the field %s of the class %s", fieldName, entity.getClazz().getName());
                        throw new IllegalStateException(message, e);
                    }
                }
                values[field.getCtorParamIndex()] = value;
            } else {
                log.warn("Found property not present in the entity: " + fieldName);
                reader.skipValue();
            }
        }
        reader.readEndDocument();
        return values;
    }

    private Object decode(EntityField field, BsonReader reader, DecoderContext decoderContext) {
        Class<?> fieldClass = field.getClazz();
        if (Iterable.class.isAssignableFrom(fieldClass)) {
            TurmsIterableCodec codec = new TurmsIterableCodec(fieldClass, field.getElementClass());
            codec.setRegistry(registry);
            return decoderContext.decodeWithChildContext(codec, reader);
        } else if (Map.class.isAssignableFrom(fieldClass)) {
            TurmsMapCodec codec = new TurmsMapCodec(field.getKeyClass(), field.getElementClass());
            codec.setRegistry(registry);
            return decoderContext.decodeWithChildContext(codec, reader);
        } else {
            if (fieldClass.isPrimitive()) {
                fieldClass = ClassUtils.primitiveToWrapper(fieldClass);
            }
            Codec<?> fieldCodec = registry.get(fieldClass);
            return decoderContext.decodeWithChildContext(fieldCodec, reader);
        }
    }
}