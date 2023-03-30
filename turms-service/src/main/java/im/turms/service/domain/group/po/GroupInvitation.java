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

package im.turms.service.domain.group.po;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.CompoundIndex;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.EnumNumber;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.server.common.storage.mongo.entity.annotation.Indexed;
import im.turms.server.common.storage.mongo.entity.annotation.Sharded;
import im.turms.service.domain.common.po.Expirable;

import static im.turms.server.common.storage.mongo.entity.IndexType.HASH;
import static im.turms.server.common.storage.mongo.entity.annotation.IndexedReason.EXTENDED_FEATURE;

/**
 * @author James Chen
 * @implNote Sharded by inviteeId and createDate because it is common for users to query whether
 *           they have received any group invitation at application startup.
 *           <p>
 *           Note that the query for the invitations of a group sent by a group admin isn't a
 *           targeted query, but a scatter query with indexes supported.
 */
@Data
@AllArgsConstructor
@Document(GroupInvitation.COLLECTION_NAME)
@CompoundIndex({GroupInvitation.Fields.INVITEE_ID, GroupInvitation.Fields.CREATION_DATE})
@Sharded(shardKey = GroupInvitation.Fields.INVITEE_ID)
public class GroupInvitation extends BaseEntity implements Expirable {

    public static final String COLLECTION_NAME = "groupInvitation";

    @Id
    private final Long id;

    /**
     * Used by queryGroupInvitationsByGroupId
     */
    @Field(Fields.GROUP_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long groupId;

    /**
     * Used by queryGroupInvitationsByInviterId
     */
    @Field(Fields.INVITER_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long inviterId;

    @Field(Fields.INVITEE_ID)
    private final Long inviteeId;

    @Field(Fields.CONTENT)
    private final String content;

    /**
     * @implNote 1. Not indexed because of its low index selectivity. 2. Not final so that we can
     *           change it without using a builder (bad performance)
     */
    @Field(Fields.STATUS)
    @EnumNumber
    private RequestStatus status;

    @Field(Fields.CREATION_DATE)
    private final Date creationDate;

    @Field(Fields.RESPONSE_DATE)
    @Indexed(
            optional = true,
            reason = EXTENDED_FEATURE,
            partialFilter = "{"
                    + Fields.RESPONSE_DATE
                    + ":{$exists:true}}")
    private final Date responseDate;

    public static final class Fields {
        public static final String GROUP_ID = "gid";
        public static final String INVITER_ID = "irid";
        public static final String INVITEE_ID = "ieid";
        public static final String CONTENT = "cnt";
        public static final String STATUS = "stat";
        public static final String CREATION_DATE = "cd";
        public static final String RESPONSE_DATE = "rd";

        private Fields() {
        }
    }
}