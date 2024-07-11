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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.bson.BsonReader;
import org.bson.BsonType;
import org.bson.BsonWriter;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.MaxKey;
import org.bson.types.MinKey;

/**
 * @author James Chen
 * @implNote Limitations: Since the codec is used for {@link Object}, we cannot know the exact type
 *           it should be when decoding, so we use limited types when decoding.
 */
public class ObjectCodec extends MongoCodec<Object> {

    @Override
    public void setRegistry(CodecRegistry registry) {
        super.setRegistry(registry);
    }

    @Override
    public Object decode(BsonReader reader, DecoderContext decoderContext) {
        return switch (reader.getCurrentBsonType()) {
            case DOUBLE -> reader.readDouble();
            case STRING -> reader.readString();
            case DOCUMENT -> {
                // We use LinkedHashMap because we don't know
                // if the user cares about the order or not,
                // so we always use LinkedHashMap to keep the order.
                Map<String, Object> map = new LinkedHashMap<>();
                reader.readStartDocument();
                while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                    String key = reader.readName();
                    Object value = decode(reader, decoderContext);
                    map.put(key, value);
                }
                reader.readEndDocument();
                yield map;
            }
            case ARRAY -> {
                // For our use cases, we just use small lists in most cases,
                // so 32 is a reasonable size.
                List<Object> list = new ArrayList<>(32);
                reader.readStartArray();
                while (reader.readBsonType() != BsonType.END_OF_DOCUMENT) {
                    Object value = decode(reader, decoderContext);
                    list.add(value);
                }
                reader.readEndArray();
                yield list;
            }
            case BINARY -> reader.readBinaryData()
                    .getData();
            case UNDEFINED -> {
                reader.readUndefined();
                yield null;
            }
            case OBJECT_ID -> reader.readObjectId();
            case BOOLEAN -> reader.readBoolean();
            case DATE_TIME -> new Date(reader.readDateTime());
            case NULL -> {
                reader.readNull();
                yield null;
            }
            case REGULAR_EXPRESSION -> Pattern.compile(reader.readRegularExpression()
                    .getPattern());
            case DB_POINTER ->
                throw new UnsupportedOperationException("DBPointer is not supported");
            case JAVASCRIPT -> reader.readJavaScript();
            case SYMBOL -> throw new UnsupportedOperationException("Symbol is not supported");
            case JAVASCRIPT_WITH_SCOPE ->
                throw new UnsupportedOperationException("JavaScriptWithScope is not supported");
            case INT32 -> reader.readInt32();
            case TIMESTAMP -> reader.readTimestamp();
            case INT64 -> reader.readInt64();
            case DECIMAL128 -> reader.readDecimal128();
            case MIN_KEY -> {
                reader.readMinKey();
                yield new MinKey();
            }
            case MAX_KEY -> {
                reader.readMaxKey();
                yield new MaxKey();
            }
            case END_OF_DOCUMENT -> throw new UnsupportedOperationException(
                    "The end of document should be handled by the caller");
        };
    }

    @Override
    public void encode(BsonWriter writer, Object value, EncoderContext encoderContext) {
        Class<?> valueClass = value.getClass();
        if (Object.class == valueClass) {
            throw new IllegalArgumentException("The value class must not be Object");
        }
        Codec valueCodec = registry.get(valueClass);
        valueCodec.encode(writer, value, encoderContext);
    }

}