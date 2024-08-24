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
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationGroupConversationSettingDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationGroupConversationSettingUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationReadDateUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationSettingDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.conversation.NotificationPrivateConversationSettingUpdatedProperties;
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
import im.turms.server.common.infra.property.env.service.business.notification.meeting.NotificationMeetingCanceledProperties;
import im.turms.server.common.infra.property.env.service.business.notification.meeting.NotificationMeetingInvitationUpdatedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.meeting.NotificationMeetingUpdatedProperties;
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
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserSettingDeletedProperties;
import im.turms.server.common.infra.property.env.service.business.notification.user.NotificationUserSettingUpdatedProperties;

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
    protected NotificationUserInfoUpdatedProperties userInfoUpdated =
            new NotificationUserInfoUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationUserOnlineStatusUpdatedProperties userOnlineStatusUpdated =
            new NotificationUserOnlineStatusUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationUserSettingDeletedProperties userSettingDeleted =
            new NotificationUserSettingDeletedProperties();

    @NestedConfigurationProperty
    protected NotificationUserSettingUpdatedProperties userSettingUpdated =
            new NotificationUserSettingUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationFriendRequestCreatedProperties friendRequestCreated =
            new NotificationFriendRequestCreatedProperties();

    @NestedConfigurationProperty
    protected NotificationFriendRequestRecalledProperties friendRequestRecalled =
            new NotificationFriendRequestRecalledProperties();

    @NestedConfigurationProperty
    protected NotificationFriendRequestRepliedProperties friendRequestReplied =
            new NotificationFriendRequestRepliedProperties();

    @NestedConfigurationProperty
    protected NotificationOneSidedRelationshipUpdatedProperties oneSidedRelationshipUpdated =
            new NotificationOneSidedRelationshipUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationOneSidedRelationshipGroupDeletedProperties oneSidedRelationshipGroupDeleted =
            new NotificationOneSidedRelationshipGroupDeletedProperties();

    @NestedConfigurationProperty
    protected NotificationOneSidedRelationshipGroupUpdatedProperties oneSidedRelationshipGroupUpdated =
            new NotificationOneSidedRelationshipGroupUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationOneSidedRelationshipGroupMemberAddedProperties oneSidedRelationshipGroupMemberAdded =
            new NotificationOneSidedRelationshipGroupMemberAddedProperties();

    @NestedConfigurationProperty
    protected NotificationOneSidedRelationshipGroupMemberRemovedProperties oneSidedRelationshipGroupMemberRemoved =
            new NotificationOneSidedRelationshipGroupMemberRemovedProperties();
    // endregion

    // region Group

    @NestedConfigurationProperty
    protected NotificationGroupCreatedProperties groupCreated =
            new NotificationGroupCreatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupDeletedProperties groupDeleted =
            new NotificationGroupDeletedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupUpdatedProperties groupUpdated =
            new NotificationGroupUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupMemberAddedProperties groupMemberAdded =
            new NotificationGroupMemberAddedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupMemberRemovedProperties groupMemberRemoved =
            new NotificationGroupMemberRemovedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupMemberInfoUpdatedProperties groupMemberInfoUpdated =
            new NotificationGroupMemberInfoUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupMemberOnlineStatusUpdatedProperties groupMemberOnlineStatusUpdated =
            new NotificationGroupMemberOnlineStatusUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupBlockedUserAddedProperties groupBlockedUserAdded =
            new NotificationGroupBlockedUserAddedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupBlockedUserRemovedProperties groupBlockedUserRemoved =
            new NotificationGroupBlockedUserRemovedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupInvitationAddedProperties groupInvitationAdded =
            new NotificationGroupInvitationAddedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupInvitationRecalledProperties groupInvitationRecalled =
            new NotificationGroupInvitationRecalledProperties();

    @NestedConfigurationProperty
    protected NotificationGroupInvitationRepliedProperties groupInvitationReplied =
            new NotificationGroupInvitationRepliedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupJoinRequestCreatedProperties groupJoinRequestCreated =
            new NotificationGroupJoinRequestCreatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupJoinRequestRecalledProperties groupJoinRequestRecalled =
            new NotificationGroupJoinRequestRecalledProperties();

    @NestedConfigurationProperty
    protected NotificationGroupJoinRequestRepliedProperties groupJoinRequestReplied =
            new NotificationGroupJoinRequestRepliedProperties();

    // endregion

    // region Conversation

    @NestedConfigurationProperty
    protected NotificationPrivateConversationReadDateUpdatedProperties privateConversationReadDateUpdated =
            new NotificationPrivateConversationReadDateUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationPrivateConversationSettingDeletedProperties privateConversationSettingDeleted =
            new NotificationPrivateConversationSettingDeletedProperties();

    @NestedConfigurationProperty
    protected NotificationPrivateConversationSettingUpdatedProperties privateConversationSettingUpdated =
            new NotificationPrivateConversationSettingUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupConversationReadDateUpdatedProperties groupConversationReadDateUpdated =
            new NotificationGroupConversationReadDateUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupConversationSettingDeletedProperties groupConversationSettingDeleted =
            new NotificationGroupConversationSettingDeletedProperties();

    @NestedConfigurationProperty
    protected NotificationGroupConversationSettingUpdatedProperties groupConversationSettingUpdated =
            new NotificationGroupConversationSettingUpdatedProperties();

    // endregion

    // region Message

    @NestedConfigurationProperty
    protected NotificationMessageCreatedProperties messageCreated =
            new NotificationMessageCreatedProperties();

    @NestedConfigurationProperty
    protected NotificationMessageUpdatedProperties messageUpdated =
            new NotificationMessageUpdatedProperties();

    // endregion

    // region Meeting

    @NestedConfigurationProperty
    protected NotificationMeetingCanceledProperties meetingCanceled =
            new NotificationMeetingCanceledProperties();

    @NestedConfigurationProperty
    protected NotificationMeetingUpdatedProperties meetingUpdated =
            new NotificationMeetingUpdatedProperties();

    @NestedConfigurationProperty
    protected NotificationMeetingInvitationUpdatedProperties meetingInvitationUpdated =
            new NotificationMeetingInvitationUpdatedProperties();

    // endregion
}