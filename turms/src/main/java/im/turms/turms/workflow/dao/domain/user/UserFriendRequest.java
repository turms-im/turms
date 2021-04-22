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

package im.turms.turms.workflow.dao.domain.user;

import im.turms.common.constant.RequestStatus;
import im.turms.server.common.mongo.entity.annotation.CompoundIndex;
import im.turms.server.common.mongo.entity.annotation.Document;
import im.turms.server.common.mongo.entity.annotation.Field;
import im.turms.server.common.mongo.entity.annotation.Id;
import im.turms.server.common.mongo.entity.annotation.Indexed;
import im.turms.server.common.mongo.entity.annotation.Sharded;
import im.turms.turms.workflow.dao.domain.Expirable;
import im.turms.turms.workflow.dao.index.OptionalIndexedForExtendedFeature;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

/**
 * @author James Chen
 * @implNote In the compound index,
 * The first field is used to query requests according to the recipient ID;
 * The second field is used to limit the date range of data to avoid the linear growth of the amount of target data,
 * especially for the scenario that a user queries his/her requests by the creation date;
 * The third field is used to check if there already a request when creating a request.
 */
@Data
@AllArgsConstructor
@Document(UserFriendRequest.COLLECTION_NAME)
@CompoundIndex({UserFriendRequest.Fields.RECIPIENT_ID,
        UserFriendRequest.Fields.CREATION_DATE,
        UserFriendRequest.Fields.REQUESTER_ID})
@Sharded(shardKey = UserFriendRequest.Fields.RECIPIENT_ID)
public final class UserFriendRequest implements Expirable {

    public static final String COLLECTION_NAME = "userFriendRequest";

    @Id
    private final Long id;

    @Field(Fields.CONTENT)
    private final String content;

    /**
     * @implNote 1. Not indexed because of its low index selectivity.
     * 2. Not final so that we can change it without using a builder (bad performance)
     */
    @Field(Fields.STATUS)
    private RequestStatus status;

    @Field(Fields.REASON)
    private final String reason;

    @Field(Fields.CREATION_DATE)
    private final Date creationDate;

    /**
     * Indexed so that turms can queries and remove expiry requests regularly.
     * No need to change it.
     */
    @Field(Fields.EXPIRATION_DATE)
    @Indexed
    private final Date expirationDate;

    @Field(Fields.RESPONSE_DATE)
    @OptionalIndexedForExtendedFeature
    private final Date responseDate;

    /**
     * Used by queryFriendRequestsByRequesterId
     */
    @Field(Fields.REQUESTER_ID)
    @OptionalIndexedForExtendedFeature
    private final Long requesterId;

    @Field(Fields.RECIPIENT_ID)
    @OptionalIndexedForExtendedFeature
    private final Long recipientId;

    public static final class Fields {
        public static final String CONTENT = "cnt";
        public static final String STATUS = "stat";
        public static final String REASON = "rsn";
        public static final String CREATION_DATE = "cd";
        public static final String EXPIRATION_DATE = "ed";
        public static final String RESPONSE_DATE = "adr";
        public static final String REQUESTER_ID = "rrid";
        public static final String RECIPIENT_ID = "rtid";

        private Fields() {
        }
    }
}