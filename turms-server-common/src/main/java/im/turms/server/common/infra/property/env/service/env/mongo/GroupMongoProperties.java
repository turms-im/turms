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

package im.turms.server.common.infra.property.env.service.env.mongo;

import com.mongodb.WriteConcern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.common.mongo.MongoProperties;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
public class GroupMongoProperties extends MongoProperties {

    @NestedConfigurationProperty
    protected OptionalIndexProperties optionalIndex = new OptionalIndexProperties();

    @NestedConfigurationProperty
    protected transient WriteConcernProperties writeConcern = new WriteConcernProperties();

    @Data
    public static class OptionalIndexProperties {
        @NestedConfigurationProperty
        protected GroupOptionalIndexProperties group = new GroupOptionalIndexProperties();

        @NestedConfigurationProperty
        protected GroupBlockedUserOptionalIndexProperties groupBlockedUser =
                new GroupBlockedUserOptionalIndexProperties();

        @NestedConfigurationProperty
        protected GroupInvitationOptionalIndexProperties groupInvitation =
                new GroupInvitationOptionalIndexProperties();

        @NestedConfigurationProperty
        protected GroupJoinRequestOptionalIndexProperties groupJoinRequest =
                new GroupJoinRequestOptionalIndexProperties();

        @NestedConfigurationProperty
        protected GroupMemberOptionalIndexProperties groupMember =
                new GroupMemberOptionalIndexProperties();
    }

    @Data
    public static class GroupOptionalIndexProperties {
        protected boolean typeId;
        protected boolean creatorId;
        protected boolean ownerId = true;
        protected boolean creationDate;
        protected boolean deletionDate = true;
        protected boolean muteEndDate;
    }

    @Data
    public static class GroupBlockedUserOptionalIndexProperties {
        protected boolean blockDate;
        protected boolean requesterId;
    }

    @Data
    public static class GroupInvitationOptionalIndexProperties {
        protected boolean groupId = true;
        protected boolean inviterId;
        protected boolean responseDate;
    }

    @Data
    public static class GroupJoinRequestOptionalIndexProperties {
        protected boolean creationDate;
        protected boolean responseDate;
        protected boolean groupId = true;
        protected boolean responderId;
    }

    @Data
    public static class GroupMemberOptionalIndexProperties {
        protected boolean joinDate;
        protected boolean muteEndDate;
    }

    @Data
    public static class WriteConcernProperties {
        protected WriteConcern group = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern groupBlockedUser = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern groupInvitation = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern groupJoinQuestion = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern groupJoinRequest = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern groupMember = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern groupType = WriteConcern.MAJORITY;
        protected WriteConcern groupVersion = WriteConcern.ACKNOWLEDGED;
    }
}