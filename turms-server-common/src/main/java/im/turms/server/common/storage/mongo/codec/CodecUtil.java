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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import jakarta.annotation.Nullable;

import org.bson.BsonArray;
import org.bson.BsonArrayUtil;
import org.bson.BsonBinary;
import org.bson.BsonBoolean;
import org.bson.BsonDateTime;
import org.bson.BsonDecimal128;
import org.bson.BsonDocument;
import org.bson.BsonDocumentWriter;
import org.bson.BsonDouble;
import org.bson.BsonInt32;
import org.bson.BsonInt64;
import org.bson.BsonJavaScript;
import org.bson.BsonMaxKey;
import org.bson.BsonMinKey;
import org.bson.BsonNull;
import org.bson.BsonReader;
import org.bson.BsonRegularExpression;
import org.bson.BsonString;
import org.bson.BsonTimestamp;
import org.bson.BsonType;
import org.bson.BsonUndefined;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.codecs.EncoderContext;
import org.bson.types.Decimal128;
import org.bson.types.ObjectId;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.CodecPool;

/**
 * @author James Chen
 */
public final class CodecUtil {

    private CodecUtil() {
    }

    /**
     * TODO: unify the logic of {@link #encode} and {@link #write}
     */
    public static BsonValue encode(Object value) {
        return switch (value) {
            case BsonValue val -> val;
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
                    switch (key) {
                        case String str -> document.put(str, encode(entry.getValue()));
                        case Number number ->
                            document.put(number.toString(), encode(entry.getValue()));
                        default -> throw new IllegalArgumentException(
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

    public static BsonArray encodeAsStrings(Collection<? extends Enum<?>> collection) {
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

    public static void write(BsonWriter writer, Object value) {
        switch (value) {
            // double
            case BsonDouble val -> writer.writeDouble(val.doubleValue());
            case Float val -> writer.writeDouble(val);
            case Double val -> writer.writeDouble(val);
            // string
            case BsonString val -> writer.writeString(val.getValue());
            case String val -> writer.writeString(val);
            // document
            case BsonDocument val -> {
                writer.writeStartDocument();
                for (Map.Entry<String, BsonValue> entry : val.entrySet()) {
                    writer.writeName(entry.getKey());
                    write(writer, entry.getValue());
                }
                writer.writeEndDocument();
            }
            case Map<?, ?> val -> {
                writer.writeStartDocument();
                for (Map.Entry<?, ?> entry : val.entrySet()) {
                    writer.writeName(entry.getKey()
                            .toString());
                    write(writer, entry.getValue());
                }
                writer.writeEndDocument();
            }
            // array
            case BsonArray val -> {
                writer.writeStartArray();
                for (BsonValue v : val) {
                    write(writer, v);
                }
                writer.writeEndArray();
            }
            case Collection<?> val -> {
                writer.writeStartArray();
                for (Object v : val) {
                    write(writer, v);
                }
                writer.writeEndArray();
            }
            // binary
            case BsonBinary val -> writer.writeBinaryData(val);
            case byte[] val -> writer.writeBinaryData(new BsonBinary(val));
            // undefined
            case BsonUndefined ignored -> writer.writeUndefined();
            // object id
            case ObjectId val -> writer.writeObjectId(val);
            // bool
            case BsonBoolean val -> writer.writeBoolean(val.getValue());
            case Boolean val -> writer.writeBoolean(val);
            // date time
            case BsonDateTime val -> writer.writeDateTime(val.getValue());
            case Date val -> writer.writeDateTime(val.getTime());
            // null
            case BsonNull ignored -> writer.writeNull();
            case null -> writer.writeNull();
            // regular expression
            case BsonRegularExpression val -> writer.writeRegularExpression(val);
            case Pattern val ->
                writer.writeRegularExpression(new BsonRegularExpression(val.pattern()));
            // javascript
            case BsonJavaScript val -> writer.writeJavaScript(val.getCode());
            // int32
            case BsonInt32 val -> writer.writeInt32(val.getValue());
            case Integer val -> writer.writeInt32(val);
            // timestamp
            case BsonTimestamp val -> writer.writeTimestamp(val);
            // int64
            case BsonInt64 val -> writer.writeInt64(val.getValue());
            case Long val -> writer.writeInt64(val);
            // decimal128
            case BsonDecimal128 val -> writer.writeDecimal128(val.getValue());
            case Decimal128 val -> writer.writeDecimal128(val);
            // min key
            case BsonMinKey ignored -> writer.writeMinKey();
            // max key
            case BsonMaxKey ignored -> writer.writeMaxKey();
            default -> {
                if (value.getClass()
                        .isArray()) {
                    int length = Array.getLength(value);
                    writer.writeStartArray();
                    for (int i = 0; i < length; i++) {
                        write(writer, Array.get(value, i));
                    }
                    writer.writeEndArray();
                    return;
                }
                throw new IllegalArgumentException(
                        "Unsupported value: "
                                + value);
            }
        }
    }

    public static Object read(BsonReader reader) {
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
                    Object value = read(reader);
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
                    Object value = read(reader);
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
                yield BsonPool.MIN_KEY;
            }
            case MAX_KEY -> {
                reader.readMaxKey();
                yield BsonPool.MAX_KEY;
            }
            case END_OF_DOCUMENT -> throw new UnsupportedOperationException(
                    "The caller must handle the end of a document");
        };
    }

}