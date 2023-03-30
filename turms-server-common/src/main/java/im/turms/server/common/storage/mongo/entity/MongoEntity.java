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

package im.turms.server.common.storage.mongo.entity;

import java.lang.reflect.Constructor;
import java.util.List;
import java.util.Map;
import jakarta.annotation.Nullable;

import org.bson.BsonDocument;

/**
 * @author James Chen
 */
public record MongoEntity<T>(
        // Meta
        Class<T> entityClass,
        Constructor<T> constructor,
        // Collection
        String collectionName,
        BsonDocument jsonSchema,
        // Shard key, zone and index
        ShardKey shardKey,
        Zone zone,
        List<CompoundIndex> compoundIndexes,
        List<Index> indexes,
        // Field
        String idFieldName,
        Map<String, EntityField<?>> nameToField,
        List<EntityField<?>> fields
) {
    public <F> EntityField<F> getField(String fieldName) {
        return (EntityField<F>) nameToField.get(fieldName);
    }

    @Nullable
    public BsonDocument getShardKeyBson() {
        if (shardKey == null) {
            return null;
        }
        return shardKey.document();
    }

}