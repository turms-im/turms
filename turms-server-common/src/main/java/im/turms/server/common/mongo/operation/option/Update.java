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

    private Update() {
    }

    public static Update newBuilder() {
        return new Update();
    }

    public Document asDocument() {
        return document;
    }

    public Update set(String key, Object value) {
        document.append("$set", new Document(key, value));
        return this;
    }

    public Update setIfNotNull(@NotNull String key, @Nullable Object value) {
        if (value != null) {
            if (value instanceof Collection) {
                if (!((Collection<?>) value).isEmpty()) {
                    document.append("$set", new Document(key, value));
                }
            } else {
                document.append("$set", new Document(key, value));
            }
        }
        return this;
    }

    public Update setIfTrue(String key, Object value, boolean condition) {
        if (condition) {
            document.append("$set", new Document(key, value));
        }
        return this;
    }

    public Update setOrUnsetDate(String key, Date date) {
        if (date != null) {
            if (date.getTime() > 0) {
                document.append("$set", new Document(key, date));
            } else {
                document.append("$unset", key);
            }
        }
        return this;
    }

    public Update unset(String key) {
        document.append("$unset", key);
        return this;
    }

}