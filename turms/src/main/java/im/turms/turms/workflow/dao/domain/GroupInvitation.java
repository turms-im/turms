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
import im.turms.turms.workflow.dao.index.documentation.OptionalIndexedForAdvancedFeature;
import im.turms.turms.workflow.dao.index.documentation.OptionalIndexedForCustomFeature;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Sharded;
import org.springframework.data.mongodb.core.mapping.ShardingStrategy;

import java.util.Date;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor
@Builder(toBuilder = true)
@Document
@CompoundIndex(
        name = GroupInvitation.Fields.INVITEE_ID + "_" + GroupInvitation.Fields.CREATION_DATE + "_idx",
        def = "{'" + GroupInvitation.Fields.INVITEE_ID + "': 1, '" + GroupInvitation.Fields.CREATION_DATE + "': 1}")
@Sharded(shardKey = {GroupInvitation.Fields.INVITEE_ID, GroupInvitation.Fields.CREATION_DATE}, shardingStrategy = ShardingStrategy.HASH, immutableKey = true)
public final class GroupInvitation {

    public static final String COLLECTION_NAME = "groupInvitation";

    @Id
    private final Long id;

    @Field(Fields.GROUP_ID)
    @OptionalIndexedForCustomFeature
    private final Long groupId;

    @Field(Fields.INVITER_ID)
    @OptionalIndexedForCustomFeature
    private final Long inviterId;

    @Field(Fields.INVITEE_ID)
    private final Long inviteeId;

    @Field(Fields.CONTENT)
    private final String content;

    /**
     * Not indexed because it has a low index selectivity.
     */
    @Field(Fields.STATUS)
    private final RequestStatus status;

    @Field(Fields.CREATION_DATE)
    private final Date creationDate;

    @Field(Fields.RESPONSE_DATE)
    @OptionalIndexedForCustomFeature
    private final Date responseDate;

    @Field(Fields.EXPIRATION_DATE)
    @OptionalIndexedForAdvancedFeature
    private final Date expirationDate;

    public static final class Fields {
        public static final String GROUP_ID = "gid";
        public static final String INVITER_ID = "irId";
        public static final String INVITEE_ID = "ieId";
        public static final String CONTENT = "cnt";
        public static final String STATUS = "stat";
        public static final String CREATION_DATE = "cd";
        public static final String RESPONSE_DATE = "rd";
        public static final String EXPIRATION_DATE = "ed";

        private Fields() {
        }
    }
}