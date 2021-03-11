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

package im.turms.turms.workflow.dao.domain.conversation;

import im.turms.server.common.mongo.entity.IndexType;
import im.turms.server.common.mongo.entity.ShardingStrategy;
import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Field;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import im.turms.server.common.mongo.entity.annotation.Sharded;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * @author James Chen
 */
@Data
@Document(PrivateConversation.COLLECTION_NAME)
@Sharded(shardKey = PrivateConversation.Fields.ID_OWNER_ID, shardingStrategy = ShardingStrategy.HASH)
public final class PrivateConversation {

    public static final String COLLECTION_NAME = "privateConversation";

    @Id
    private final Key key;

    @Field(Fields.READ_DATE)
    private final Date readDate;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor // Make sure spring can initiate the key and use setters
    public static final class Key {

        /**
         * The index is used by deleteAllPrivateConversations
         */
        @Field(Fields.OWNER_ID)
        @Indexed(IndexType.HASH)
        private Long ownerId;

        @Field(Fields.TARGET_ID)
        private Long targetId;

        public static final class Fields {
            public static final String OWNER_ID = "oid";
            public static final String TARGET_ID = "tid";

            private Fields() {
            }
        }
    }

    @Data
    @AllArgsConstructor
    public static final class KeyList {
        private List<Key> privateConversationKeys;
    }

    public static final class Fields {
        public static final String ID_OWNER_ID = "_id." + Key.Fields.OWNER_ID;
        public static final String ID_TARGET_ID = "_id." + Key.Fields.TARGET_ID;
        public static final String READ_DATE = "rd";

        private Fields() {
        }
    }

}