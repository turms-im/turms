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

package im.turms.turms.workflow.dao.domain;

import im.turms.common.constant.RequestStatus;
import im.turms.turms.workflow.dao.index.documentation.OptionalIndexedForCustomFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.util.Date;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Document
@CompoundIndex(
        name = UserFriendRequest.Fields.CREATION_DATE + "_" + UserFriendRequest.Fields.RECIPIENT_ID + "_idx",
        def = "{'" + UserFriendRequest.Fields.CREATION_DATE + "': 1, '" + UserFriendRequest.Fields.RECIPIENT_ID + "': 1}")
@Sharded(shardKey = {UserFriendRequest.Fields.CREATION_DATE, UserFriendRequest.Fields.RECIPIENT_ID}, immutableKey = true)
public final class UserFriendRequest {

    public static final String COLLECTION_NAME = "userFriendRequest";

    @Id
    private final Long id;

    @Field(Fields.CONTENT)
    private final String content;

    /**
     * Not indexed because of its low index selectivity.
     * Not recommend to change it.
     */
    @Field(Fields.STATUS)
    private final RequestStatus status;

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
    @OptionalIndexedForCustomFeature
    private final Date responseDate;

    @Field(Fields.REQUESTER_ID)
    @OptionalIndexedForCustomFeature
    private final Long requesterId;

    @Field(Fields.RECIPIENT_ID)
    @OptionalIndexedForCustomFeature
    private final Long recipientId;

    public static class Fields {
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