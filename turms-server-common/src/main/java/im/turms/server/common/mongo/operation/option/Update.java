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

package im.turms.server.common.mongo.operation.option;

import im.turms.server.common.mongo.BsonPool;
import org.bson.Document;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * @author James Chen
 */
public final class Update {

    private final Document document = new Document();
    private Document set;
    private Document unset;

    private Update() {
    }

    public static Update newBuilder() {
        return new Update();
    }

    public Document asDocument() {
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
            set = new Document();
            document.append("$set", set);
        }
        set.put(key, value);
        return this;
    }

    private Update appendUnset(String field) {
        if (unset == null) {
            unset = new Document();
            document.append("$unset", unset);
        }
        unset.put(field, BsonPool.BSON_STRING_EMPTY);
        return this;
    }

}