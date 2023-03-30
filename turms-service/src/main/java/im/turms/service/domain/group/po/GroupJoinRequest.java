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
 * @implNote Don't use the "groupId" and "requesterId" as the key because a requester can send
 *           multiple requests (no matter it is pending, handled or etc) to the same group.
 *           <p>
 *           Sharded by requesterId and createDate because it is common for users to query the
 *           status of member requests sent by them at application startup.
 *           <p>
 *           Note that the query for the member requests of a group sent by a group admin isn't
 *           targeted query but scatter query with indexes supported.
 */
@Data
@AllArgsConstructor
@Document(GroupJoinRequest.COLLECTION_NAME)
@CompoundIndex({GroupJoinRequest.Fields.REQUESTER_ID, GroupJoinRequest.Fields.CREATION_DATE})
@Sharded(shardKey = GroupJoinRequest.Fields.REQUESTER_ID)
public class GroupJoinRequest extends BaseEntity implements Expirable {

    public static final String COLLECTION_NAME = "groupJoinRequest";

    @Id
    private final Long id;

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
    @Indexed(optional = true, reason = EXTENDED_FEATURE)
    private final Date creationDate;

    @Field(Fields.RESPONSE_DATE)
    @Indexed(
            optional = true,
            reason = EXTENDED_FEATURE,
            partialFilter = "{"
                    + Fields.RESPONSE_DATE
                    + ":{$exists:true}}")
    private final Date responseDate;

    /**
     * Used by queryGroupJoinRequestsByGroupId
     */
    @Field(Fields.GROUP_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long groupId;

    @Field(Fields.REQUESTER_ID)
    private final Long requesterId;

    @Field(Fields.RESPONDER_ID)
    @Indexed(
            optional = true,
            value = HASH,
            reason = EXTENDED_FEATURE,
            partialFilter = "{"
                    + Fields.RESPONDER_ID
                    + ":{$exists:true}}")
    private final Long responderId;

    public static final class Fields {
        public static final String CONTENT = "cnt";
        public static final String STATUS = "stat";
        public static final String CREATION_DATE = "cd";
        public static final String RESPONSE_DATE = "rd";
        public static final String GROUP_ID = "gid";
        public static final String REQUESTER_ID = "rqid";
        public static final String RESPONDER_ID = "rpid";

        private Fields() {
        }
    }
}