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

package im.turms.turms.workflow.dao.domain.group;

import im.turms.common.constant.RequestStatus;
import im.turms.server.common.mongo.entity.IndexType;
import im.turms.server.common.mongo.entity.annotation.CompoundIndex;
import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Field;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import im.turms.server.common.mongo.entity.annotation.Sharded;
import im.turms.turms.workflow.dao.index.OptionalIndexedForColdData;
import im.turms.turms.workflow.dao.index.OptionalIndexedForExtendedFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author James Chen
 * @implNote Don't use the "groupId" and "requesterId" as the key because
 * a requester can send multiple requests (not matter it's pending, handled or etc) to the same group.
 *
 * Sharded by requesterId and createDate because it's common for users to
 * query the status of member requests sent by them at application startup.
 * <p>
 * Note that the query for the member requests of a group sent by a group admin
 * isn't targeted query but scatter query with indexes supported.
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Document(GroupJoinRequest.COLLECTION_NAME)
@CompoundIndex({GroupJoinRequest.Fields.REQUESTER_ID,
        GroupJoinRequest.Fields.CREATION_DATE})
@Sharded(shardKey = GroupJoinRequest.Fields.REQUESTER_ID)
public final class GroupJoinRequest {

    public static final String COLLECTION_NAME = "groupJoinRequest";

    @Id
    private final Long id;

    @Field(Fields.CONTENT)
    private final String content;

    @Field(Fields.STATUS)
    private final RequestStatus status;

    @Field(Fields.CREATION_DATE)
    @OptionalIndexedForExtendedFeature
    private final Date creationDate;

    @Field(Fields.RESPONSE_DATE)
    @OptionalIndexedForExtendedFeature
    private final Date responseDate;

    @Field(Fields.EXPIRATION_DATE)
    @OptionalIndexedForColdData
    @Indexed
    private final Date expirationDate;

    /**
     * Used by queryGroupJoinRequestsByGroupId
     */
    @Field(Fields.GROUP_ID)
    @OptionalIndexedForExtendedFeature
    @Indexed(IndexType.HASH)
    private final Long groupId;

    @Field(Fields.REQUESTER_ID)
    private final Long requesterId;

    @Field(Fields.RESPONDER_ID)
    @OptionalIndexedForExtendedFeature
    private final Long responderId;

    public static final class Fields {
        public static final String CONTENT = "cnt";
        public static final String STATUS = "stat";
        public static final String CREATION_DATE = "cd";
        public static final String RESPONSE_DATE = "rd";
        public static final String EXPIRATION_DATE = "ed";
        public static final String GROUP_ID = "gid";
        public static final String REQUESTER_ID = "rqid";
        public static final String RESPONDER_ID = "rpid";

        private Fields() {
        }
    }
}