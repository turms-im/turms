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

package im.turms.server.common.storage.mongo.operation.option;

import im.turms.server.common.infra.collection.MapUtil;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.codec.BsonValueEncoder;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.conversions.Bson;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * @author James Chen
 */
public final class Update implements Bson {

    /**
     * Use {@link BsonDocument} instead of {@link Document}
     * because {@link Document} will be converted to {@link BsonDocument} by mongo-java-driver finally,
     * which is a huge waste of system resources because both documents are heavy
     */
    private final BsonDocument document;
    private BsonDocument set;
    private BsonDocument unset;

    private Update(int expectedSize) {
        document = new BsonDocument(MapUtil.getCapability(expectedSize));
    }

    public static Update newBuilder() {
        return new Update(4);
    }

    public static Update newBuilder(int expectedSize) {
        return new Update(expectedSize);
    }

    @Override
    public <TDocument> BsonDocument toBsonDocument(Class<TDocument> tDocumentClass, CodecRegistry codecRegistry) {
        return document;
    }

    public Update set(String field, Object value) {
        return appendSet(field, value);
    }

    public Update setIfNotNull(@NotNull String field, @Nullable Object value) {
        if (value != null) {
            if (value instanceof Collection<?> collection) {
                if (!collection.isEmpty()) {
                    appendSet(field, value);
                }
            } else {
                appendSet(field, value);
            }
        }
        return this;
    }

    public Update setIfTrue(String field, Object value, boolean condition) {
        if (condition) {
            appendSet(field, value);
        }
        return this;
    }

    public Update setOrUnsetDate(String field, Date date) {
        if (date != null) {
            if (date.getTime() > 0) {
                appendSet(field, date);
            } else {
                appendUnset(field);
            }
        }
        return this;
    }

    public Update unset(String field) {
        return appendUnset(field);
    }

    private Update appendSet(String key, Object value) {
        if (set == null) {
            set = new BsonDocument();
            document.append("$set", set);
        }
        set.put(key, BsonValueEncoder.encodeValue(value));
        return this;
    }

    private Update appendUnset(String field) {
        if (unset == null) {
            unset = new BsonDocument();
            document.append("$unset", unset);
        }
        unset.put(field, BsonPool.BSON_STRING_EMPTY);
        return this;
    }

}