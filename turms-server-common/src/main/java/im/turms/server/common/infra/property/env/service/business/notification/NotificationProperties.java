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

package im.turms.server.common.infra.property.env.service.business.notification;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationGroupConversationReadDateUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationReadDateUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupBlockedUserAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupBlockedUserRemovedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupInvitationAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupInvitationRecalledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupInvitationRepliedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupJoinRequestCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupJoinRequestRecalledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupJoinRequestRepliedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberInfoUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberOnlineStatusUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupMemberRemovedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.group.NotificationGroupUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.message.NotificationMessageCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.message.NotificationMessageUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationFriendRequestCreatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationFriendRequestRecalledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationFriendRequestRepliedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupMemberAddedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupMemberRemovedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipGroupUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationOneSidedRelationshipUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserInfoUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserOnlineStatusUpdatedProperties;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class NotificationProperties {

    // region User

    @NestedConfigurationProperty
    private NotificationUserInfoUpdatedProperties userInfoUpdated =
            new NotificationUserInfoUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationUserOnlineStatusUpdatedProperties userOnlineStatusUpdated =
            new NotificationUserOnlineStatusUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationFriendRequestCreatedProperties friendRequestCreated =
            new NotificationFriendRequestCreatedProperties();

    @NestedConfigurationProperty
    private NotificationFriendRequestRecalledProperties friendRequestRecalled =
            new NotificationFriendRequestRecalledProperties();

    @NestedConfigurationProperty
    private NotificationFriendRequestRepliedProperties friendRequestReplied =
            new NotificationFriendRequestRepliedProperties();

    @NestedConfigurationProperty
    private NotificationOneSidedRelationshipUpdatedProperties oneSidedRelationshipUpdated =
            new NotificationOneSidedRelationshipUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationOneSidedRelationshipGroupDeletedProperties oneSidedRelationshipGroupDeleted =
            new NotificationOneSidedRelationshipGroupDeletedProperties();

    @NestedConfigurationProperty
    private NotificationOneSidedRelationshipGroupUpdatedProperties oneSidedRelationshipGroupUpdated =
            new NotificationOneSidedRelationshipGroupUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationOneSidedRelationshipGroupMemberAddedProperties oneSidedRelationshipGroupMemberAdded =
            new NotificationOneSidedRelationshipGroupMemberAddedProperties();

    @NestedConfigurationProperty
    private NotificationOneSidedRelationshipGroupMemberRemovedProperties oneSidedRelationshipGroupMemberRemoved =
            new NotificationOneSidedRelationshipGroupMemberRemovedProperties();
    // endregion

    // region Group

    @NestedConfigurationProperty
    private NotificationGroupCreatedProperties groupCreated =
            new NotificationGroupCreatedProperties();

    @NestedConfigurationProperty
    private NotificationGroupDeletedProperties groupDeleted =
            new NotificationGroupDeletedProperties();

    @NestedConfigurationProperty
    private NotificationGroupUpdatedProperties groupUpdated =
            new NotificationGroupUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationGroupMemberAddedProperties groupMemberAdded =
            new NotificationGroupMemberAddedProperties();

    @NestedConfigurationProperty
    private NotificationGroupMemberRemovedProperties groupMemberRemoved =
            new NotificationGroupMemberRemovedProperties();

    @NestedConfigurationProperty
    private NotificationGroupMemberInfoUpdatedProperties groupMemberInfoUpdated =
            new NotificationGroupMemberInfoUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationGroupMemberOnlineStatusUpdatedProperties groupMemberOnlineStatusUpdated =
            new NotificationGroupMemberOnlineStatusUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationGroupBlockedUserAddedProperties groupBlockedUserAdded =
            new NotificationGroupBlockedUserAddedProperties();

    @NestedConfigurationProperty
    private NotificationGroupBlockedUserRemovedProperties groupBlockedUserRemoved =
            new NotificationGroupBlockedUserRemovedProperties();

    @NestedConfigurationProperty
    private NotificationGroupInvitationAddedProperties groupInvitationAdded =
            new NotificationGroupInvitationAddedProperties();

    @NestedConfigurationProperty
    private NotificationGroupInvitationRecalledProperties groupInvitationRecalled =
            new NotificationGroupInvitationRecalledProperties();

    @NestedConfigurationProperty
    private NotificationGroupInvitationRepliedProperties groupInvitationReplied =
            new NotificationGroupInvitationRepliedProperties();

    @NestedConfigurationProperty
    private NotificationGroupJoinRequestCreatedProperties groupJoinRequestCreated =
            new NotificationGroupJoinRequestCreatedProperties();

    @NestedConfigurationProperty
    private NotificationGroupJoinRequestRecalledProperties groupJoinRequestRecalled =
            new NotificationGroupJoinRequestRecalledProperties();

    @NestedConfigurationProperty
    private NotificationGroupJoinRequestRepliedProperties groupJoinRequestReplied =
            new NotificationGroupJoinRequestRepliedProperties();

    // endregion

    // region Conversation

    @NestedConfigurationProperty
    private NotificationPrivateConversationReadDateUpdatedProperties privateConversationReadDateUpdated =
            new NotificationPrivateConversationReadDateUpdatedProperties();

    @NestedConfigurationProperty
    private NotificationGroupConversationReadDateUpdatedProperties groupConversationReadDateUpdated =
            new NotificationGroupConversationReadDateUpdatedProperties();

    // endregion

    // region Message

    @NestedConfigurationProperty
    private NotificationMessageCreatedProperties messageCreated =
            new NotificationMessageCreatedProperties();

    @NestedConfigurationProperty
    private NotificationMessageUpdatedProperties messageUpdated =
            new NotificationMessageUpdatedProperties();

    // endregion
}