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
import lombok.Getter;
import org.bson.BsonDocument;
import org.bson.BsonInt32;
import org.springframework.data.domain.Sort;

import javax.annotation.Nullable;

/**
 * @author James Chen
 * @implNote 1. We don't merge the fields of Query into Filter
 * to avoid unnecessary object memory footprint
 * considering most of query operations don't need these fields
 * 2. We don't use com.mongodb.internal.client.model.FindOptions directly
 * because it's heavy
 * 3. The class will be removed if
 * com.mongodb.reactivestreams.client.internal.FindPublisherImpl#FindPublisherImpl
 * didn't init the FindOptions instance in its constructor
 * so that we can avoid intermediary objects
 */
@Getter
public class QueryOptions {

    private BsonDocument projection;
    private BsonDocument sort;
    private int limit;
    private int skip;

    private QueryOptions() {
        super();
    }

    public static QueryOptions newBuilder() {
        return new QueryOptions();
    }

    public QueryOptions max(String field) {
        limit = 1;
        sort = new BsonDocument(field, BsonPool.BSON_INT32_NEGATIVE_1);
        return this;
    }

    public QueryOptions min(String field) {
        limit = 1;
        sort = new BsonDocument(field, BsonPool.BSON_INT32_1);
        return this;
    }

    public QueryOptions paginateIfNotNull(@Nullable Integer page, @Nullable Integer size) {
        if (size != null) {
            if (page == null) {
                page = 0;
            }
            skip = size * (page - 1);
            limit = size;
        }
        return this;
    }

    public QueryOptions sort(Sort.Direction direction, String field) {
        BsonInt32 value = direction == Sort.Direction.ASC ? BsonPool.BSON_INT32_1 : BsonPool.BSON_INT32_NEGATIVE_1;
        sort = new BsonDocument(field, value);
        return this;
    }

    public QueryOptions include(String... fields) {
        if (projection == null) {
            projection = new BsonDocument();
        }
        for (String field : fields) {
            projection.append(field, BsonPool.BSON_INT32_1);
        }
        return this;
    }

    public QueryOptions exclude(String... fields) {
        if (projection == null) {
            projection = new BsonDocument();
        }
        for (String field : fields) {
            projection.append(field, BsonPool.BSON_INT32_0);
        }
        return this;
    }

}