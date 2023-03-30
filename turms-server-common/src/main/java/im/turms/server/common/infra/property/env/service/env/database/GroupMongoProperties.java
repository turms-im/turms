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

package im.turms.server.common.infra.property.env.service.env.database;

import com.mongodb.WriteConcern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class GroupMongoProperties extends TurmsMongoProperties {

    @NestedConfigurationProperty
    private OptionalIndexProperties optionalIndex = new OptionalIndexProperties();

    @NestedConfigurationProperty
    private transient WriteConcernProperties writeConcern = new WriteConcernProperties();

    @Data
    public static class OptionalIndexProperties {
        @NestedConfigurationProperty
        private GroupOptionalIndexProperties group = new GroupOptionalIndexProperties();

        @NestedConfigurationProperty
        private GroupBlockedUserOptionalIndexProperties groupBlockedUser =
                new GroupBlockedUserOptionalIndexProperties();

        @NestedConfigurationProperty
        private GroupInvitationOptionalIndexProperties groupInvitation =
                new GroupInvitationOptionalIndexProperties();

        @NestedConfigurationProperty
        private GroupJoinRequestOptionalIndexProperties groupJoinRequest =
                new GroupJoinRequestOptionalIndexProperties();

        @NestedConfigurationProperty
        private GroupMemberOptionalIndexProperties groupMember =
                new GroupMemberOptionalIndexProperties();
    }

    @Data
    public static class GroupOptionalIndexProperties {
        private boolean typeId;
        private boolean creatorId;
        private boolean ownerId = true;
        private boolean creationDate;
        private boolean deletionDate = true;
        private boolean muteEndDate;
    }

    @Data
    public static class GroupBlockedUserOptionalIndexProperties {
        private boolean blockDate;
        private boolean requesterId;
    }

    @Data
    public static class GroupInvitationOptionalIndexProperties {
        private boolean groupId = true;
        private boolean inviterId;
        private boolean responseDate;
    }

    @Data
    public static class GroupJoinRequestOptionalIndexProperties {
        private boolean creationDate;
        private boolean responseDate;
        private boolean groupId = true;
        private boolean responderId;
    }

    @Data
    public static class GroupMemberOptionalIndexProperties {
        private boolean joinDate;
        private boolean muteEndDate;
    }

    @Data
    public static class WriteConcernProperties {
        private WriteConcern group = WriteConcern.ACKNOWLEDGED;
        private WriteConcern groupBlockedUser = WriteConcern.ACKNOWLEDGED;
        private WriteConcern groupInvitation = WriteConcern.ACKNOWLEDGED;
        private WriteConcern groupJoinQuestion = WriteConcern.ACKNOWLEDGED;
        private WriteConcern groupJoinRequest = WriteConcern.ACKNOWLEDGED;
        private WriteConcern groupMember = WriteConcern.ACKNOWLEDGED;
        private WriteConcern groupType = WriteConcern.MAJORITY;
        private WriteConcern groupVersion = WriteConcern.ACKNOWLEDGED;
    }
}
