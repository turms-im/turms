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

package im.turms.server.common.property.env.service.business;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
public class NotificationProperties {

    // User
    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify related users after other related user's information has been updated")
    private boolean notifyRelatedUsersAfterOtherRelatedUserInfoUpdated = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify related users after other related user's online status has been updated")
    private boolean notifyRelatedUsersAfterOtherRelatedUserOnlineStatusUpdated = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the recipient when receiving a friend request")
    private boolean notifyRecipientWhenReceivingFriendRequest = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the requester after a friend request has been updated")
    private boolean notifyRequesterAfterFriendRequestUpdated = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the related user after a one-sided relationship has benn updated by others")
    private boolean notifyRelatedUserAfterOneSidedRelationshipUpdatedByOthers = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the related user after added to a one-sided relationship group by others")
    private boolean notifyRelatedUserAfterAddedToOneSidedRelationshipGroupByOthers = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify members after a one-sided relationship group has been updated by others")
    private boolean notifyMembersAfterOneSidedRelationshipGroupUpdatedByOthers = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the member after removed from a one-sided relationship group by others")
    private boolean notifyMemberAfterRemovedFromRelationshipGroupByOthers = false;

    // Group
    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify members after a group has been removed")
    private boolean notifyMembersAfterGroupDeleted = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify members after a group has been updated")
    private boolean notifyMembersAfterGroupUpdated = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify members after other group member's online status has been updated")
    private boolean notifyMembersAfterOtherMemberOnlineStatusUpdated = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify members after other group member's information has been updated")
    private boolean notifyMembersAfterOtherMemberInfoUpdated = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the user after blacklisted by a group")
    private boolean notifyUserAfterBlacklistedByGroup = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the user after unblacklisted by a group")
    private boolean notifyUserAfterUnblacklistedByGroup = false;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the user after invited by a group")
    private boolean notifyUserAfterInvitedByGroup = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the owner and managers after a join request has been received")
    private boolean notifyOwnerAndManagersAfterReceivingJoinRequest = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the invitee after a group invitation has been recalled")
    private boolean notifyInviteeAfterGroupInvitationRecalled = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the owner and managers after a group invitation has been recalled")
    private boolean notifyOwnerAndManagersAfterGroupJoinRequestRecalled = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the user after added to a group by others")
    private boolean notifyUserAfterAddedToGroupByOthers = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the user after removed from a group by others")
    private boolean notifyUserAfterRemovedFromGroupByOthers = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify members after its member information has been updated by others")
    private boolean notifyMemberAfterInfoUpdatedByOthers = true;

    // Message
    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the sender after the read receipt of a message has been updated by recipients")
    private boolean notifySenderAfterReadStatusUpdatedByRecipients = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to notify the recipients after a message has been updated by the sender")
    private boolean notifyRecipientsAfterMessageUpdatedBySender = true;

}