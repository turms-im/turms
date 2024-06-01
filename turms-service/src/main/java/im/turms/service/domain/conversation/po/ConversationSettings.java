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

package im.turms.service.domain.conversation.po;

import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.IndexType;
import im.turms.server.common.storage.mongo.entity.ShardingStrategy;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * {@link ConversationSettings} store conversation settings for specific users.
 * <p>
 * 1. Though conversation settings can be considered as the special part of the user settings, we
 * don't put {@link ConversationSettings} under the "user" domain because:
 * <p>
 * 1. Now that we have the more specific domain, "conversation", we use it to not mix the "user"
 * domain and the "conversation" domain as it is more confusing for developers and users.
 * <p>
 * 2. The effective difference for a collection to be in different domains is that they MAY use
 * different MongoDB clients to store data in different databases. But we can also share the same
 * MongoDB client, and the same database if users have such a need, so they can have no effective
 * difference.
 * <p>
 * 3. When we support different turms-service can be responsible for different domains, we DO want
 * to the turms-service servers that are responsible for the "conversation" domain to manage
 * {@link ConversationSettings}, which is why we will support assigning different domains to
 * different turms-service.
 * <p>
 * In conclusion, we use the more specific domain "conversation" for {@link ConversationSettings}.
 * <p>
 * 2. Use one collection for conversation settings instead of two collections for private and group
 * conversation settings correspondingly because it is very common use case that users want to find
 * all conversation settings in one request, so using one collection is more efficient, while their
 * settings are the same.
 *
 * @author James Chen
 */
@Data
@Document(ConversationSettings.COLLECTION_NAME)
@Sharded(
        shardKey = ConversationSettings.Fields.ID_OWNER_ID,
        shardingStrategy = ShardingStrategy.HASH)
public final class ConversationSettings extends BaseEntity {

    public static final String COLLECTION_NAME = "conversationSettings";

    @Id
    private final Key key;

    @Field(Fields.SETTINGS)
    private final Map<String, Object> settings;

    @Field(Fields.LAST_UPDATED_DATE)
    private final Date lastUpdatedDate;

    @Data
    @AllArgsConstructor
    public static final class Key {

        @Field(Key.Fields.OWNER_ID)
        @Indexed(IndexType.HASH)
        private Long ownerId;

        /**
         * Note that we use the positive ID as the user ID, and the negative ID as the group ID. We
         * don't introduce a field like "isGroupId" because using a single ID is both storage and
         * performance efficient.
         */
        @Field(Key.Fields.TARGET_ID)
        private Long targetId;

        public static final class Fields {
            public static final String OWNER_ID = "oid";
            public static final String TARGET_ID = "tid";

            private Fields() {
            }
        }
    }

    public static final class Fields {

        public static final String ID_OWNER_ID = "_id."
                + Key.Fields.OWNER_ID;
        public static final String SETTINGS = "s";
        public static final String LAST_UPDATED_DATE = "lud";

        private Fields() {
        }

    }

}