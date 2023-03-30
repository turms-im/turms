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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import org.bson.BsonArrayUtil;
import org.bson.BsonDateTime;
import org.bson.BsonDocument;
import org.bson.BsonNull;
import org.bson.BsonValue;

import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.codec.BsonValueEncoder;

/**
 * @author James Chen
 */
public class Filter extends BaseBson {

    Filter(int expectedSize) {
        super(new BsonDocument(CollectionUtil.getMapCapability(expectedSize)));
    }

    public static Filter newBuilder(int expectedSize) {
        return new Filter(expectedSize);
    }

    /**
     * [start, end)
     */
    public Filter addBetweenIfNotNull(@NotNull String key, @Nullable DateRange dateRange) {
        if (dateRange != null) {
            Date start = dateRange.start();
            Date end = dateRange.end();
            if (start != null && end == null) {
                document.append(key, new BsonDocument("$gte", new BsonDateTime(start.getTime())));
            } else if (start == null && end != null) {
                document.append(key, new BsonDocument("$lt", new BsonDateTime(end.getTime())));
            } else if (start != null) {
                document.append(key,
                        new BsonDocument().append("$gte", new BsonDateTime(start.getTime()))
                                .append("$lt", new BsonDateTime(end.getTime())));
            }
        }
        return this;
    }

    public Filter eq(String key, Object value) {
        document.append(key, BsonValueEncoder.encodeSingleValue(value));
        return this;
    }

    public Filter eqIfFalse(@NotNull String key, @Nullable Object value, boolean condition) {
        if (!condition) {
            document.append(key,
                    new BsonDocument("$eq", BsonValueEncoder.encodeSingleValue(value)));
        }
        return this;
    }

    public Filter eqIfNotNull(@NotNull String key, @Nullable Object value) {
        if (value != null) {
            document.append(key,
                    new BsonDocument("$eq", BsonValueEncoder.encodeSingleValue(value)));
        }
        return this;
    }

    public Filter gt(String key, Object value) {
        document.append(key, new BsonDocument("$gt", BsonValueEncoder.encodeSingleValue(value)));
        return this;
    }

    public Filter gtIfNotNull(String key, @Nullable Object value) {
        if (value != null) {
            document.append(key,
                    new BsonDocument("$gt", BsonValueEncoder.encodeSingleValue(value)));
        }
        return this;
    }

    public Filter gtOrNull(String key, Object value) {
        or(Filter.newBuilder(1)
                .eq(key, null),
                Filter.newBuilder(1)
                        .gt(key, value));
        return this;
    }

    public Filter gte(String key, Object value) {
        document.append(key, new BsonDocument("$gte", BsonValueEncoder.encodeSingleValue(value)));
        return this;
    }

    public Filter gteOrNull(String key, Object value) {
        or(Filter.newBuilder(1)
                .eq(key, null),
                Filter.newBuilder(1)
                        .gte(key, value));
        return this;
    }

    public <T> Filter in(String key, T... values) {
        document.append(key, new BsonDocument("$in", BsonValueEncoder.encodeValue(values)));
        return this;
    }

    public <T> Filter in(String key, Collection<T> collection) {
        document.append(key, new BsonDocument("$in", BsonValueEncoder.encodeValue(collection)));
        return this;
    }

    public Filter inIfNotNull(@NotNull String key, @Nullable Collection<?> collection) {
        if (collection != null && !collection.isEmpty()) {
            document.append(key, new BsonDocument("$in", BsonValueEncoder.encodeValue(collection)));
        }
        return this;
    }

    public Filter lt(String key, Object value) {
        document.append(key, new BsonDocument("$lt", BsonValueEncoder.encodeSingleValue(value)));
        return this;
    }

    public Filter ltOrNull(String key, Object value) {
        or(Filter.newBuilder(1)
                .eq(key, null),
                Filter.newBuilder(1)
                        .lt(key, value));
        return this;
    }

    public Filter ne(String key, Object value) {
        document.append(key, new BsonDocument("$ne", BsonValueEncoder.encodeSingleValue(value)));
        return this;
    }

    public Filter neNullIfNotNull(@NotNull String key, @Nullable Object value) {
        if (value != null) {
            document.append(key, new BsonDocument("$ne", BsonNull.VALUE));
        }
        return this;
    }

    public Filter neNullIfTrueOrEqNullIfFalse(String key, @Nullable Boolean value) {
        if (value != null) {
            if (value) {
                ne(key, BsonNull.VALUE);
            } else {
                eq(key, BsonNull.VALUE);
            }
        }
        return this;
    }

    public Filter or(Filter... filters) {
        List<BsonValue> values = new ArrayList<>(filters.length);
        for (Filter filter : filters) {
            values.add(filter.document);
        }
        document.append("$or", BsonArrayUtil.newArray(values));
        return this;
    }

    // Expiration Support

    public Filter isExpired(String creationDateFieldName, @Nullable Date expirationDate) {
        // If never expire
        if (expirationDate == null) {
            return this;
        }
        BsonValue existingDoc = document.get(creationDateFieldName);
        if (existingDoc instanceof BsonDocument doc) {
            BsonDateTime existingDate = doc.getDateTime("$lt");
            long expirationDateTime = expirationDate.getTime();
            if (expirationDateTime < existingDate.getValue()) {
                doc.append("$lt", new BsonDateTime(expirationDateTime));
            }
        } else {
            lt(creationDateFieldName, expirationDate);
        }
        return this;
    }

    public Filter isExpiredOrNot(
            Set<RequestStatus> statuses,
            String creationDateFieldName,
            Date expirationDate) {
        if (statuses == null) {
            return this;
        }
        boolean includesExpired = statuses.contains(RequestStatus.EXPIRED);
        boolean includesNotExpired = statuses.contains(RequestStatus.PENDING);
        if (includesExpired) {
            if (includesNotExpired) {
                return this;
            }
            return isExpired(creationDateFieldName, expirationDate);
        }
        if (includesNotExpired) {
            return isNotExpired(creationDateFieldName, expirationDate);
        }
        return this;
    }

    public Filter isNotExpired(String creationDateFieldName, @Nullable Date expirationDate) {
        // If never expire
        if (expirationDate == null) {
            return this;
        }
        BsonValue existingDoc = document.get(creationDateFieldName);
        if (existingDoc instanceof BsonDocument doc) {
            BsonValue existingDate = doc.get("$gte");
            if (existingDate instanceof BsonDateTime date) {
                if (expirationDate.getTime() > date.getValue()) {
                    doc.append("$gte", new BsonDateTime(expirationDate.getTime()));
                }
            } else if (doc.isEmpty()) {
                gteOrNull(creationDateFieldName, expirationDate);
            } else {
                doc.append("$gte", new BsonDateTime(expirationDate.getTime()));
            }
        } else {
            gteOrNull(creationDateFieldName, expirationDate);
        }
        return this;
    }

}