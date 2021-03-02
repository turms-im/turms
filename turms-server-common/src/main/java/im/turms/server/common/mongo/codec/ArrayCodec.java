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

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * @author James Chen
 */
public class ArrayCodec<T> extends MongoCodec<T[]> {

    private final Class<T[]> arrayClazz;
    private final Class<T> componentClazz;

    public ArrayCodec(Class arrayClazz) {
        super(arrayClazz);
        this.arrayClazz = arrayClazz;
        componentClazz = arrayClazz.getComponentType();
    }

    @Override
    public T[] decode(BsonReader reader, DecoderContext decoderContext) {
        List<T> list = new ArrayList<>(8);
        reader.readStartArray();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            Codec<T> codec = registry.get(componentClazz);
            list.add(codec.decode(reader, decoderContext));
        }
        reader.readEndArray();
        int length = list.size();
        return list.toArray((T[]) Array.newInstance(arrayClazz.getComponentType(), length));
    }

    @Override
    public void encode(BsonWriter writer, T[] values, EncoderContext encoderContext) {
        writer.writeStartArray();
        Codec<T> codec = registry.get(componentClazz);
        for (T value : values) {
            codec.encode(writer, value, encoderContext);
        }
        writer.writeEndArray();
    }

}