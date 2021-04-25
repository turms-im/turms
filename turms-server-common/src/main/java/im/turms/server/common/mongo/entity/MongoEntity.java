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

package im.turms.server.common.mongo.entity;

import com.mongodb.client.model.IndexModel;
import lombok.Data;
import org.bson.BsonDocument;
import org.springframework.data.mapping.PreferredConstructor;

import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
@Data
public class MongoEntity<T> {

    // Meta

    private final Class<T> entityClass;
    private final PreferredConstructor<T, ?> constructor;

    // Collection

    private final String collectionName;

    // Shard key, zone and index

    private final BsonDocument shardKey;
    private final Zone zone;
    private final IndexModel compoundIndex;
    private final List<IndexModel> indexes;

    // Field

    private final String idFieldName;
    private final Map<String, EntityField<?>> fieldMap;

    public <F> EntityField<F> getField(String fieldName) {
        return (EntityField<F>) fieldMap.get(fieldName);
    }

}