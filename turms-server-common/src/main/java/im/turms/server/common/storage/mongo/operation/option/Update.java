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

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.codec.CodecUtil;

/**
 * @author James Chen
 */
public final class Update extends BaseBson {

    private BsonDocument set;
    private BsonDocument unset;

    private Update(int expectedSize) {
        super(new BsonDocument(CollectionUtil.getMapCapability(expectedSize)));
    }

    public static Update newBuilder() {
        return new Update(4);
    }

    public static Update newBuilder(int expectedSize) {
        return new Update(expectedSize);
    }

    public Update set(String field, Object value) {
        return appendSet(field, value);
    }

    public Update setEnumString(@NotNull String field, @NotNull Enum<?> value) {
        return appendSetForEnumString(field, value);
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

    public Update setIfNotNullForEnumStrings(
            @NotNull String field,
            @Nullable Collection<? extends Enum<?>> value) {
        if (value != null && !value.isEmpty()) {
            appendSetForEnumStrings(field, value);
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
        set.put(key, CodecUtil.encode(value));
        return this;
    }

    private Update appendSet(String key, BsonValue value) {
        if (set == null) {
            set = new BsonDocument();
            document.append("$set", set);
        }
        set.put(key, value);
        return this;
    }

    private Update appendSetForEnumString(String key, Enum<?> value) {
        if (set == null) {
            set = new BsonDocument();
            document.append("$set", set);
        }
        set.put(key, new BsonString(value.name()));
        return this;
    }

    private Update appendSetForEnumStrings(String key, Collection<? extends Enum<?>> collection) {
        if (set == null) {
            set = new BsonDocument();
            document.append("$set", set);
        }
        set.put(key, CodecUtil.encodeAsStrings(collection));
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

    // region array
    public Update addToSet(String field, Object value) {
        document.append("$addToSet", new BsonDocument(field, CodecUtil.encodeSingleValue(value)));
        return this;
    }

    public Update pullAll(String field, List<Object> values) {
        document.append("$pullAll", new BsonDocument(field, CodecUtil.encode(values)));
        return this;
    }

    // endregion

    // region special values

    public Update setUserDefinedAttributesIfNotNull(
            @Nullable Map<String, Object> userDefinedAttributes) {
        if (userDefinedAttributes != null) {
            for (Map.Entry<String, Object> entry : userDefinedAttributes.entrySet()) {
                appendSet(entry.getKey(), CodecUtil.encode(entry.getValue()));
            }
        }
        return this;
    }

    // endregion

}