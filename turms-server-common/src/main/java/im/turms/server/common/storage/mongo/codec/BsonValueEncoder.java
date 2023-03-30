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

import im.turms.server.common.storage.mongo.CodecPool;

/**
 * @author James Chen
 */
public final class BsonValueEncoder {

    private BsonValueEncoder() {
    }

    public static BsonValue encodeValue(Object value) {
        if (value instanceof Collection<?> values) {
            int size = values.size();
            List<BsonValue> list = new ArrayList<>(size);
            for (Object val : values) {
                list.add(encodeSingleValue(val));
            }
            return BsonArrayUtil.newArray(list);
        }
        if (value instanceof byte[] bytes) {
            return new BsonBinary(bytes);
        }
        Class<?> clazz = value.getClass();
        if (clazz.isArray()) {
            int size = Array.getLength(value);
            List<BsonValue> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(encodeSingleValue(Array.get(value, i)));
            }
            return BsonArrayUtil.newArray(list);
        }
        return encodeSingleValue(value);
    }

    public static BsonValue encodeSingleValue(Object value) {
        if (value == null) {
            return BsonNull.VALUE;
        }
        if (value instanceof BsonValue val) {
            return val;
        }
        if (value instanceof Boolean val) {
            return val
                    ? BsonBoolean.TRUE
                    : BsonBoolean.FALSE;
        }
        if (value instanceof Long val) {
            return new BsonInt64(val);
        }
        if (value instanceof Integer val) {
            return new BsonInt32(val);
        }
        if (value instanceof String val) {
            return new BsonString(val);
        }
        if (value instanceof Date val) {
            return new BsonDateTime(val.getTime());
        }
        if (value instanceof Byte val) {
            return new BsonInt32(val);
        }
        if (value instanceof Short val) {
            return new BsonInt32(val);
        }
        if (value instanceof Float val) {
            return new BsonDouble(val);
        }
        if (value instanceof Double val) {
            return new BsonDouble(val);
        }
        if (value instanceof Character val) {
            return new BsonString(val.toString());
        }
        if (value instanceof byte[] val) {
            return new BsonBinary(val);
        }
        Class<?> clazz = value.getClass();
        if (clazz.isEnum()) {
            Enum<?> element = (Enum<?>) value;
            return new BsonInt32(element.ordinal());
        }
        EncoderContext encoderContext = EncoderContext.builder()
                .build();
        BsonDocument document = new BsonDocument();
        BsonWriter writer = new BsonDocumentWriter(document);
        EntityCodec codec = (EntityCodec) CodecPool.CODEC_REGISTRY.get(clazz);
        codec.encode(writer, value, encoderContext);
        return document;
    }

}
