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
public class UserMongoProperties extends MongoProperties {

    @NestedConfigurationProperty
    protected OptionalIndexProperties optionalIndex = new OptionalIndexProperties();

    @NestedConfigurationProperty
    protected transient WriteConcernProperties writeConcern = new WriteConcernProperties();

    @Data
    public static class WriteConcernProperties {
        protected WriteConcern user = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userFriendRequest = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userMaxDailyOnlineUser = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userRelationship = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userRelationshipGroup = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userRelationshipGroupMember = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userRole = WriteConcern.ACKNOWLEDGED;
        protected WriteConcern userVersion = WriteConcern.ACKNOWLEDGED;
    }

    @Data
    public static class OptionalIndexProperties {
        @NestedConfigurationProperty
        protected UserFriendRequestOptionalIndexProperties userFriendRequest =
                new UserFriendRequestOptionalIndexProperties();

        @NestedConfigurationProperty
        protected UserRelationshipOptionalIndexProperties userRelationship =
                new UserRelationshipOptionalIndexProperties();

        @NestedConfigurationProperty
        protected UserRelationshipGroupMemberOptionalIndexProperties userRelationshipGroupMember =
                new UserRelationshipGroupMemberOptionalIndexProperties();
    }

    @Data
    public static class UserFriendRequestOptionalIndexProperties {
        protected boolean responseDate;
        protected boolean requesterId;
        protected boolean recipientId;
    }

    @Data
    public static class UserRelationshipOptionalIndexProperties {
        protected boolean establishmentDate;
    }

    @Data
    public static class UserRelationshipGroupMemberOptionalIndexProperties {
        protected boolean joinDate;
        protected boolean groupIndex;
        protected boolean relatedUserId;
    }
}