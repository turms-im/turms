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
public class TurmsMapCodec extends MongoCodec<Map> {

    private final Class keyClass;
    private final Class valueClass;

    public TurmsMapCodec(Class keyClass, Class valueClass) {
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
            Codec<String> keyCodec = registry.get(String.class);
            String key = keyCodec.decode(reader, decoderContext);
            Codec valueCodec = registry.get(valueClass);
            Object value = valueCodec.decode(reader, decoderContext);
            map.put(toObject(keyClass, key), value);
        }
        reader.readEndDocument();
        return map;
    }

    private Object toObject(Class clazz, String value) {
        if (String.class == clazz) {
            return value;
        }
        if (Boolean.class == clazz) {
            return Boolean.parseBoolean(value);
        }
        if (Byte.class == clazz) {
            return Byte.parseByte(value);
        }
        if (Short.class == clazz) {
            return Short.parseShort(value);
        }
        if (Integer.class == clazz) {
            return Integer.parseInt(value);
        }
        if (Long.class == clazz) {
            return Long.parseLong(value);
        }
        if (Float.class == clazz) {
            return Float.parseFloat(value);
        }
        if (Double.class == clazz) {
            return Double.parseDouble(value);
        }
        return value;
    }

}
