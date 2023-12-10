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

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import org.bson.BsonArray;
import org.bson.BsonArrayUtil;
import org.bson.BsonBinary;
import org.bson.BsonBoolean;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonNull;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.EncoderContext;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.storage.mongo.CodecPool;

/**
 * @author James Chen
 */
public final class BsonValueEncoder {

    private BsonValueEncoder() {
    }

    public static BsonValue encodeValue(Object value) {
        return switch (value) {
            case Collection<?> values -> {
                int size = values.size();
                List<BsonValue> list = new ArrayList<>(size);
                for (Object val : values) {
                    list.add(encodeSingleValue(val));
                }
                yield BsonArrayUtil.newArray(list);
            }
            case Map<?, ?> map -> {
                int size = map.size();
                Object key;
                BsonDocument document = new BsonDocument(CollectionUtil.getMapCapability(size));
                for (Map.Entry<?, ?> entry : map.entrySet()) {
                    key = entry.getKey();
                    if (key instanceof String str) {
                        document.put(str, encodeValue(entry.getValue()));
                    } else if (key instanceof Number number) {
                        document.put(number.toString(), encodeValue(entry.getValue()));
                    } else {
                        throw new IllegalArgumentException(
                                "Expecting the map key to be a string or number, but got: "
                                        + key);
                    }
                }
                yield document;
            }
            case byte[] bytes -> new BsonBinary(bytes);
            default -> {
                Class<?> clazz = value.getClass();
                if (clazz.isArray()) {
                    int size = Array.getLength(value);
                    List<BsonValue> list = new ArrayList<>(size);
                    for (int i = 0; i < size; i++) {
                        list.add(encodeSingleValue(Array.get(value, i)));
                    }
                    yield BsonArrayUtil.newArray(list);
                }
                yield encodeSingleValue(value);
            }
        };
    }

    public static BsonArray encodeValuesAsStrings(Collection<? extends Enum<?>> collection) {
        List<BsonValue> list = new ArrayList<>(collection.size());
        for (Enum<?> value : collection) {
            list.add(new BsonString(value.name()));
        }
        return BsonArrayUtil.newArray(list);
    }

    public static BsonValue encodeSingleValue(@Nullable Object value) {
        return switch (value) {
            case null -> BsonNull.VALUE;
            case Byte val -> new BsonInt32(val);
            case Short val -> new BsonInt32(val);
            case Integer val -> new BsonInt32(val);
            case Long val -> new BsonInt64(val);
            case Float val -> new BsonDouble(val);
            case Double val -> new BsonDouble(val);
            case Character val -> new BsonString(val.toString());
            case Boolean val -> val
                    ? BsonBoolean.TRUE
                    : BsonBoolean.FALSE;
            case String val -> new BsonString(val);
            case Date val -> new BsonDateTime(val.getTime());
            case byte[] val -> new BsonBinary(val);
            case BsonValue val -> val;
            default -> {
                Class<?> clazz = value.getClass();
                if (clazz.isEnum()) {
                    Enum<?> element = (Enum<?>) value;
                    yield new BsonInt32(element.ordinal());
                }
                EncoderContext encoderContext = EncoderContext.builder()
                        .build();
                BsonDocument document = new BsonDocument();
                BsonWriter writer = new BsonDocumentWriter(document);
                EntityCodec codec = (EntityCodec) CodecPool.CODEC_REGISTRY.get(clazz);
                codec.encode(writer, value, encoderContext);
                yield document;
            }
        };
    }

}