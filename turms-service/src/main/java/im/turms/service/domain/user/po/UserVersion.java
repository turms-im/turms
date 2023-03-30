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

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;

/**
 * @author James Chen
 * @implNote Use the range index instead of the hashed index to avoid creating two indexes on the
 *           key because MongoDB will create a default range index on the key if we use a hashed
 *           index
 */
@Data
@Document(UserVersion.COLLECTION_NAME)
@Sharded
public final class UserVersion extends BaseEntity {

    public static final String COLLECTION_NAME = "userVersion";

    @Id
    private final Long userId;

    @Field(Fields.SENT_FRIEND_REQUESTS)
    private final Date sentFriendRequests;

    @Field(Fields.RECEIVED_FRIEND_REQUESTS)
    private final Date receivedFriendRequests;

    @Field(Fields.RELATIONSHIPS)
    private final Date relationships;

    @Field(Fields.RELATIONSHIP_GROUPS)
    private final Date relationshipGroups;

    @Field(Fields.RELATIONSHIP_GROUP_MEMBERS)
    private final Date relationshipGroupMembers;

    @Field(Fields.GROUP_JOIN_REQUESTS)
    private final Date groupJoinRequests; // sent group join requests

    @Field(Fields.SENT_GROUP_INVITATIONS)
    private final Date sentGroupInvitations;

    @Field(Fields.RECEIVED_GROUP_INVITATIONS)
    private final Date receivedGroupInvitations;

    @Field(Fields.JOINED_GROUPS)
    private final Date joinedGroups;

    public static final class Fields {

        public static final String SENT_FRIEND_REQUESTS = "sfr";
        public static final String RECEIVED_FRIEND_REQUESTS = "rfr";

        public static final String RELATIONSHIPS = "r";
        public static final String RELATIONSHIP_GROUPS = "rg";
        public static final String RELATIONSHIP_GROUP_MEMBERS = "rgm";

        public static final String GROUP_JOIN_REQUESTS = "gjr";

        public static final String SENT_GROUP_INVITATIONS = "sgi";
        public static final String RECEIVED_GROUP_INVITATIONS = "rgi";

        public static final String JOINED_GROUPS = "jg";

        private Fields() {
        }

    }

}
