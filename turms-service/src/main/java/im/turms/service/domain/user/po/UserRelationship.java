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

package im.turms.service.domain.user.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.IndexType;
import im.turms.server.common.storage.mongo.entity.ShardingStrategy;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.PersistenceConstructor;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.EXTENDED_FEATURE;

/**
 * Don't consolidate the two-sided relationships as one model to eliminate transactions (unless
 * MongoDB supports multiple shard keys though it seems impossible
 * https://jira.mongodb.org/browse/SERVER-2949). Otherwise, it is impossible to use targeted queries
 * to query the data according to either the owner ID or the related user ID because MongoDB can
 * have only one shard key. https://github.com/turms-im/turms/issues/343
 *
 * @author James Chen
 */
@Data
@AllArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document(UserRelationship.COLLECTION_NAME)
@Sharded(shardKey = UserRelationship.Fields.ID_OWNER_ID, shardingStrategy = ShardingStrategy.HASH)
public final class UserRelationship extends BaseEntity {

    public static final String COLLECTION_NAME = "userRelationship";

    @Id
    private final Key key;

    @Field(Fields.BLOCK_DATE)
    private final Date blockDate;

    @Field(Fields.ESTABLISHMENT_DATE)
    @Indexed(optional = true, reason = EXTENDED_FEATURE)
    private final Date establishmentDate;

    public UserRelationship(
            Long ownerId,
            Long relatedUserId,
            Date blockDate,
            Date establishmentDate) {
        this.key = new Key(ownerId, relatedUserId);
        this.blockDate = blockDate;
        this.establishmentDate = establishmentDate;
    }

    @Data
    @AllArgsConstructor
    public static final class Key {

        @Field(Fields.OWNER_ID)
        @Indexed(IndexType.HASH)
        private Long ownerId;

        // The index is used by deleteAllRelationships
        @Field(Fields.RELATED_USER_ID)
        @Indexed(IndexType.HASH)
        private Long relatedUserId;

        public static final class Fields {
            public static final String OWNER_ID = "oid";
            public static final String RELATED_USER_ID = "rid";

            private Fields() {
            }
        }
    }

    public static final class Fields {
        public static final String ID_OWNER_ID = "_id."
                + Key.Fields.OWNER_ID;
        public static final String ID_RELATED_USER_ID = "_id."
                + Key.Fields.RELATED_USER_ID;
        public static final String BLOCK_DATE = "bd";
        public static final String ESTABLISHMENT_DATE = "ed";

        private Fields() {
        }
    }

}