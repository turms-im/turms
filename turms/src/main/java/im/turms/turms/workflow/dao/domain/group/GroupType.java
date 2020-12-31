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

import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupJoinStrategy;
import im.turms.common.constant.GroupUpdateStrategy;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * No need to shard because there are only a few (or some) group types.
 * <p>
 * Note that there is a built-in immutable group type:
 * <pre>
 * {
 *     id: 0,
 *     name: "DEFAULT"
 * }
 * </pre>
 *
 * @author James Chen
 */
@Data
@Document(GroupType.COLLECTION_NAME)
public final class GroupType {

    public static final String COLLECTION_NAME = "groupType";

    @Id
    private final Long id;

    @Field(Fields.NAME)
    private final String name;

    @Field(Fields.GROUP_SIZE_LIMIT)
    private final Integer groupSizeLimit;

    @Field(Fields.INVITATION_STRATEGY)
    private final GroupInvitationStrategy invitationStrategy;

    @Field(Fields.JOIN_STRATEGY)
    private final GroupJoinStrategy joinStrategy;

    @Field(Fields.GROUP_INFO_UPDATE_STRATEGY)
    private final GroupUpdateStrategy groupInfoUpdateStrategy;

    @Field(Fields.MEMBER_INFO_UPDATE_STRATEGY)
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