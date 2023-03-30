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

import java.lang.reflect.ParameterizedType;

import lombok.Setter;
import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import im.turms.server.common.infra.exception.NotImplementedException;

/**
 * @author James Chen
 */
public abstract class MongoCodec<T> implements Codec<T> {

    final Class<T> clazz;
    @Setter
    protected CodecRegistry registry;

    protected MongoCodec() {
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class<T>) parameterizedType.getActualTypeArguments()[0];
    }

    protected MongoCodec(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T decode(BsonReader reader, DecoderContext decoderContext) {
        return decode(reader);
    }

    @Override
    public void encode(BsonWriter writer, T value, EncoderContext encoderContext) {
        encode(writer, value);
    }

    @Override
    public Class<T> getEncoderClass() {
        return clazz;
    }

    public T decode(BsonReader reader) {
        throw new NotImplementedException();
    }

    public void encode(BsonWriter writer, T value) {
        throw new NotImplementedException();
    }

}