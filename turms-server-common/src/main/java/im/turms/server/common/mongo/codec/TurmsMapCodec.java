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

import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 */
public class TurmsMapCodec<K, V> extends MongoCodec<Map> {

    private final Class<K> keyClass;
    private final Class<V> valueClass;

    public TurmsMapCodec(Class<K> keyClass, Class<V> valueClass) {
        super(Map.class);
        this.keyClass = keyClass;
        this.valueClass = valueClass;
    }

    @Override
    public void encode(BsonWriter writer, Map map, EncoderContext encoderContext) {
        writer.writeStartDocument();
        for (Object o : map.entrySet()) {
            Map.Entry entry = (Map.Entry) o;
            Object value = entry.getValue();
            if (value != null) {
                writer.writeName(entry.getKey().toString());
                Codec valueCodec = registry.get(valueClass);
                encoderContext.encodeWithChildContext(valueCodec, writer, value);
            }
        }
        writer.writeEndDocument();
    }

    @Override
    public Map decode(final BsonReader reader, final DecoderContext decoderContext) {
        Map map = new HashMap<>(32);
        reader.readStartDocument();
        while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
            String key = reader.readName();
            Codec<V> valueCodec = registry.get(valueClass);
            V value = valueCodec.decode(reader, decoderContext);
            map.put(parseKeyStr(key, keyClass), value);
        }
        reader.readEndDocument();
        return map;
    }

    private Object parseKeyStr(String value, Class<?> keyClass) {
        if (String.class == keyClass) {
            return value;
        }
        if (Boolean.class == keyClass) {
            return Boolean.parseBoolean(value);
        }
        if (Byte.class == keyClass) {
            return Byte.parseByte(value);
        }
        if (Short.class == keyClass) {
            return Short.parseShort(value);
        }
        if (Integer.class == keyClass) {
            return Integer.parseInt(value);
        }
        if (Long.class == keyClass) {
            return Long.parseLong(value);
        }
        if (Float.class == keyClass) {
            return Float.parseFloat(value);
        }
        if (Double.class == keyClass) {
            return Double.parseDouble(value);
        }
        return value;
    }

}
