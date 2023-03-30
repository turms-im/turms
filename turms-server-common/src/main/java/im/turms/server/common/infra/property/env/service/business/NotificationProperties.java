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

package im.turms.server.common.infra.property.env.service.business;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class NotificationProperties {

    // User

    @Description("Whether to notify related users after other related user's information has been updated")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRelatedUsersAfterOtherRelatedUserInfoUpdated;

    @Description("Whether to notify related users after other related user's online status has been updated")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated;

    @Description("Whether to notify the recipient when receiving a friend request")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRecipientWhenReceivingFriendRequest = true;

    @Description("Whether to notify the requester after a friend request has been updated")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRequesterAfterFriendRequestUpdated = true;

    @Description("Whether to notify the related user after a one-sided relationship has benn updated by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers;

    @Description("Whether to notify the related user after added to a one-sided relationship group by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers;

    @Description("Whether to notify members after a one-sided relationship group has been updated by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers;

    @Description("Whether to notify the member after removed from a one-sided relationship group by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMemberAfterRemovedFromRelationshipGroupByOthers;

    // Group

    @Description("Whether to notify members after a group has been removed")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMembersAfterGroupDeleted = true;

    @Description("Whether to notify members after a group has been updated")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMembersAfterGroupUpdated = true;

    @Description("Whether to notify members after other group member's online status has been updated")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMembersAfterOtherMemberOnlineStatusUpdated;

    @Description("Whether to notify members after other group member's information has been updated")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMembersAfterOtherMemberInfoUpdated;

    @Description("Whether to notify the user after blocked by a group")
    @GlobalProperty
    @MutableProperty
    private boolean notifyUserAfterBlockedByGroup;

    @Description("Whether to notify the user after unblocked by a group")
    @GlobalProperty
    @MutableProperty
    private boolean notifyUserAfterUnblockedByGroup;

    @Description("Whether to notify the user after invited by a group")
    @GlobalProperty
    @MutableProperty
    private boolean notifyUserAfterInvitedByGroup = true;

    @Description("Whether to notify the owner and managers after a join request has been received")
    @GlobalProperty
    @MutableProperty
    private boolean notifyOwnerAndManagersAfterReceivingJoinRequest = true;

    @Description("Whether to notify the invitee after a group invitation has been recalled")
    @GlobalProperty
    @MutableProperty
    private boolean notifyInviteeAfterGroupInvitationRecalled = true;

    @Description("Whether to notify the owner and managers after a group invitation has been recalled")
    @GlobalProperty
    @MutableProperty
    private boolean notifyOwnerAndManagersAfterGroupJoinRequestRecalled = true;

    @Description("Whether to notify the user after added to a group by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyUserAfterAddedToGroupByOthers = true;

    @Description("Whether to notify the user after removed from a group by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyUserAfterRemovedFromGroupByOthers = true;

    @Description("Whether to notify members after its member information has been updated by others")
    @GlobalProperty
    @MutableProperty
    private boolean notifyMemberAfterInfoUpdatedByOthers = true;

    // Conversation

    @Description("Whether to notify the private conversation participant "
            + "after the read receipt of a conversation has been updated by the recipient")
    @GlobalProperty
    @MutableProperty
    private boolean notifyPrivateConversationParticipantAfterReadDateUpdated;

    @Description("Whether to notify the group conversation participants "
            + "after the read receipt of a conversation has been updated by recipients")
    @GlobalProperty
    @MutableProperty
    private boolean notifyGroupConversationParticipantsAfterReadDateUpdated;

    // Message

    @Description("Whether to notify the recipients after a message has been updated by the sender")
    @GlobalProperty
    @MutableProperty
    private boolean notifyRecipientsAfterMessageUpdatedBySender = true;

}