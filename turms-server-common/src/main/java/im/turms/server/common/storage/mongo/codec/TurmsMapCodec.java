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

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

/**
 * @author James Chen
 */
public class TurmsMapCodec<K, V> extends MongoCodec<Map<K, V>> {

    private final boolean isLinkedHashMap;
    private final Class<K> keyClass;
    private final Class<V> valueClass;
    private Codec<V> valueCodec;
    private final boolean isEnumNumber;

    public TurmsMapCodec(
            Class<? extends Map<?, ?>> ownerClass,
            Class<K> keyClass,
            Class<V> valueClass,
            boolean isEnumNumber) {
        super((Class) Map.class);
        this.isLinkedHashMap = LinkedHashMap.class.isAssignableFrom(ownerClass);
        this.keyClass = keyClass;
        this.valueClass = valueClass;
        this.isEnumNumber = isEnumNumber;
    }

    @Override
    public void setRegistry(CodecRegistry registry) {
        super.setRegistry(registry);
        valueCodec = valueClass.isEnum()
                ? MongoCodecProvider.getEnumCodec(isEnumNumber, (Class) valueClass)
                : registry.get(valueClass);
    }

    @Override
    public void encode(BsonWriter writer, Map<K, V> map, EncoderContext encoderContext) {
        writer.writeStartDocument();
        for (Map.Entry<K, V> entry : map.entrySet()) {
            V value = entry.getValue();
            if (value != null) {
                writer.writeName(entry.getKey()
                        .toString());
                encoderContext.encodeWithChildContext(valueCodec, writer, value);
            }
        }
        writer.writeEndDocument();
    }

    @Override
    public Map<K, V> decode(final BsonReader reader, final DecoderContext decoderContext) {
        Map<K, V> map = isLinkedHashMap
                ? new LinkedHashMap<>(32)
                : new HashMap<>(32);
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            V value = valueCodec.decode(reader, decoderContext);
            map.put(parseKeyStr(key, keyClass), value);
        }
        reader.readEndDocument();
        return map;
    }

    private K parseKeyStr(String value, Class<K> keyClass) {
        if (String.class == keyClass) {
            return (K) value;
        }
        if (Boolean.class == keyClass) {
            return (K) Boolean.valueOf(value);
        }
        if (Byte.class == keyClass) {
            return (K) Byte.valueOf(value);
        }
        if (Short.class == keyClass) {
            return (K) Short.valueOf(value);
        }
        if (Integer.class == keyClass) {
            return (K) Integer.valueOf(value);
        }
        if (Long.class == keyClass) {
            return (K) Long.valueOf(value);
        }
        if (Float.class == keyClass) {
            return (K) Float.valueOf(value);
        }
        if (Double.class == keyClass) {
            return (K) Double.valueOf(value);
        }
        return (K) value;
    }

}
