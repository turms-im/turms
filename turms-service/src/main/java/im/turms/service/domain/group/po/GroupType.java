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

import lombok.Data;

import im.turms.server.common.domain.common.po.BaseEntity;
import im.turms.server.common.storage.mongo.entity.annotation.Document;
import im.turms.server.common.storage.mongo.entity.annotation.EnumNumber;
import im.turms.server.common.storage.mongo.entity.annotation.Field;
import im.turms.server.common.storage.mongo.entity.annotation.Id;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.bo.GroupUpdateStrategy;

/**
 * No need to shard because there are only a few (or some) group types.
 * <p>
 * Note that there is a built-in immutable group type:
 * 
 * <pre>
 * { id: 0, name: "DEFAULT" }
 * </pre>
 *
 * @author James Chen
 */
@Data
@Document(GroupType.COLLECTION_NAME)
public final class GroupType extends BaseEntity {

    public static final String COLLECTION_NAME = "groupType";

    @Id
    private final Long id;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.GROUP_SIZE_LIMIT)
    private final Integer groupSizeLimit;

    @Field(Fields.INVITATION_STRATEGY)
    @EnumNumber
    private final GroupInvitationStrategy invitationStrategy;

    @Field(Fields.JOIN_STRATEGY)
    @EnumNumber
    private final GroupJoinStrategy joinStrategy;

    @Field(Fields.GROUP_INFO_UPDATE_STRATEGY)
    @EnumNumber
    private final GroupUpdateStrategy groupInfoUpdateStrategy;

    @Field(Fields.MEMBER_INFO_UPDATE_STRATEGY)
    @EnumNumber
    private final GroupUpdateStrategy memberInfoUpdateStrategy;

    @Field(Fields.GUEST_SPEAKABLE)
    private final Boolean guestSpeakable;

    @Field(Fields.SELF_INFO_UPDATABLE)
    private final Boolean selfInfoUpdatable;

    @Field(Fields.ENABLE_READ_RECEIPT)
    private final Boolean enableReadReceipt;

    @Field(Fields.MESSAGE_EDITABLE)
    private final Boolean messageEditable;

    public static final class Fields {
        public static final String NAME = "n";
        public static final String GROUP_SIZE_LIMIT = "gsl";
        public static final String INVITATION_STRATEGY = "is";
        public static final String JOIN_STRATEGY = "js";
        public static final String GROUP_INFO_UPDATE_STRATEGY = "gius";
        public static final String MEMBER_INFO_UPDATE_STRATEGY = "mius";
        public static final String GUEST_SPEAKABLE = "gs";
        public static final String SELF_INFO_UPDATABLE = "siu";
        public static final String ENABLE_READ_RECEIPT = "err";
        public static final String MESSAGE_EDITABLE = "me";

        private Fields() {
        }
    }
}