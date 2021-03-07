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

import im.turms.server.common.bo.common.DateRange;
import org.bson.Document;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.Date;

/**
 * @author James Chen
 */
public class Filter {

    private final Document document = new Document();

    Filter() {
    }

    public static Filter newBuilder() {
        return new Filter();
    }

    public Document asDocument() {
        return document;
    }

    /**
     * [start, end)
     */
    public Filter addBetweenIfNotNull(
            @NotNull String key,
            @Nullable DateRange dateRange) {
        if (dateRange != null) {
            Date start = dateRange.getStart();
            Date end = dateRange.getEnd();
            if (start != null && end == null) {
                document.append(key, new Document("$gte", start));
            } else if (start == null && end != null) {
                document.append(key, new Document("$lt", end));
            } else if (start != null) {
                document.append(key, new Document()
                        .append("$gte", start)
                        .append("$lt", end));
            }
        }
        return this;
    }

    public Filter eq(String key, Object value) {
        document.append(key, value);
        return this;
    }

    public Filter eqIfFalse(@NotNull String key, @Nullable Object obj, boolean condition) {
        if (!condition) {
            document.append(key, new Document("$eq", obj));
        }
        return this;
    }

    public Filter eqIfNotNull(@NotNull String key, @Nullable Object obj) {
        if (obj != null) {
            document.append(key, new Document("$eq", obj));
        }
        return this;
    }

    public Filter gt(String key, Date date) {
        document.append(key, new Document("$gt", date));
        return this;
    }

    public <T> Filter in(String key, T... values) {
        document.append(key, new Document("$in", values));
        return this;
    }

    public <T> Filter in(String key, Collection<T> collection) {
        document.append(key, new Document("$in", collection));
        return this;
    }

    public Filter inIfNotNull(@NotNull String key, @Nullable Collection<?> collection) {
        if (collection != null && !collection.isEmpty()) {
            document.append(key, new Document("$in", collection));
        }
        return this;
    }

    public Filter lt(String key, Date date) {
        document.append(key, new Document("$lt", date));
        return this;
    }

    public Filter ne(String key, Object value) {
        document.append(key, new Document("$ne", value));
        return this;
    }

    public Filter neNullIfNotNull(@NotNull String key, @Nullable Object obj) {
        if (obj != null) {
            document.append(key, new Document("$ne", null));
        }
        return this;
    }

    public Filter or(Filter... filters) {
        Document[] documents = new Document[filters.length];
        for (int i = 0; i < filters.length; i++) {
            documents[i] = filters[i].asDocument();
        }
        document.append("$or", documents);
        return this;
    }
}