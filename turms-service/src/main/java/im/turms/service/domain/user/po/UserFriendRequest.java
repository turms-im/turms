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
 * @implNote In the compound index, The first field is used to query requests according to the
 *           recipient ID; The second field is used to limit the date range of data because of the
 *           ever-growing collection, especially for the scenario that a user queries his/her
 *           requests by the creation date; The third field is used to check if there is already a
 *           request when creating a request.
 */
@Data
@AllArgsConstructor
@Document(UserFriendRequest.COLLECTION_NAME)
@CompoundIndex({UserFriendRequest.Fields.RECIPIENT_ID,
        UserFriendRequest.Fields.CREATION_DATE,
        UserFriendRequest.Fields.REQUESTER_ID})
@Sharded(shardKey = UserFriendRequest.Fields.RECIPIENT_ID)
public class UserFriendRequest extends BaseEntity implements Expirable {

    public static final String COLLECTION_NAME = "userFriendRequest";

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

    @Field(Fields.REASON)
    private final String reason;

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

    /**
     * Used by queryFriendRequestsByRequesterId
     */
    @Field(Fields.REQUESTER_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long requesterId;

    @Field(Fields.RECIPIENT_ID)
    @Indexed(optional = true, value = HASH, reason = EXTENDED_FEATURE)
    private final Long recipientId;

    public static final class Fields {
        public static final String CONTENT = "cnt";
        public static final String STATUS = "stat";
        public static final String REASON = "rsn";
        public static final String CREATION_DATE = "cd";
        public static final String RESPONSE_DATE = "adr";
        public static final String REQUESTER_ID = "rrid";
        public static final String RECIPIENT_ID = "rtid";

        private Fields() {
        }
    }
}