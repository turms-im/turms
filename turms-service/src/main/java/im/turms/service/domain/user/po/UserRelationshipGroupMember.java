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
import jakarta.validation.constraints.NotNull;

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

import static im.turms.server.common.storage.mongo.entity.IndexType.HASH;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.EXTENDED_FEATURE;

/**
 * @author James Chen
 * @implNote Shard by "_id.ownerId" instead of ID so that we can query all related users according
 *           to the owner ID in the same shard efficiently. And the chunk size is acceptable.
 */
@Data
@AllArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document(UserRelationshipGroupMember.COLLECTION_NAME)
@Sharded(
        shardKey = UserRelationshipGroupMember.Fields.ID_OWNER_ID,
        shardingStrategy = ShardingStrategy.HASH)
public final class UserRelationshipGroupMember extends BaseEntity {

    public static final String COLLECTION_NAME = "userRelationshipGroupMember";

    @Id
    private final Key key;

    @Field(Fields.JOIN_DATE)
    @Indexed(optional = true, reason = EXTENDED_FEATURE)
    private final Date joinDate;

    public UserRelationshipGroupMember(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex,
            @NotNull Long relatedUserId,
            @NotNull Date joinDate) {
        this.key = new Key(ownerId, groupIndex, relatedUserId);
        this.joinDate = joinDate;
    }

    @Data
    public static final class Key {

        @Field(Fields.OWNER_ID)
        @Indexed(IndexType.HASH)
        private final Long ownerId;

        /**
         * Not need to index because its low selectivity
         */
        @Field(Fields.GROUP_INDEX)
        @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
        private final Integer groupIndex;

        @Field(Fields.RELATED_USER_ID)
        @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
        private final Long relatedUserId;

        public static final class Fields {
            public static final String OWNER_ID = "oid";
            public static final String GROUP_INDEX = "gidx";
            public static final String RELATED_USER_ID = "ruid";

            private Fields() {
            }
        }
    }

    public static final class Fields {
        public static final String ID_OWNER_ID = "_id."
                + Key.Fields.OWNER_ID;
        public static final String ID_GROUP_INDEX = "_id."
                + Key.Fields.GROUP_INDEX;
        public static final String ID_RELATED_USER_ID = "_id."
                + Key.Fields.RELATED_USER_ID;
        public static final String JOIN_DATE = "jd";

        private Fields() {
        }
    }
}