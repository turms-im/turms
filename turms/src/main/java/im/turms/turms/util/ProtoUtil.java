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

package im.turms.turms.util;

import com.google.protobuf.*;
import im.turms.common.constant.*;
import im.turms.common.model.bo.group.*;
import im.turms.common.model.bo.message.MessageStatus;
import im.turms.common.model.bo.user.*;
import im.turms.common.model.dto.request.message.CreateMessageRequest;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.dao.domain.User;
import im.turms.turms.workflow.dao.domain.Message;
import lombok.extern.log4j.Log4j2;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author James Chen
 */
@Log4j2
public class ProtoUtil {

    private ProtoUtil() {
    }

    // Transformation

    public static im.turms.common.model.bo.message.Message.Builder message2proto(Message message) {
        im.turms.common.model.bo.message.Message.Builder builder = im.turms.common.model.bo.message.Message.newBuilder();
        Long messageId = message.getId();
        Boolean isSystemMessage = message.getIsSystemMessage();
        Date deliveryDate = message.getDeliveryDate();
        Date deletionDate = message.getDeletionDate();
        String text = message.getText();
        List<byte[]> records = message.getRecords();
        Long senderId = message.getSenderId();
        Long targetId = message.getTargetId();
        Long groupId = message.groupId();
        if (messageId != null) {
            builder.setId(Int64Value.newBuilder().setValue(messageId).build());
        }
        if (isSystemMessage != null) {
            builder.setIsSystemMessage(BoolValue.newBuilder().setValue(isSystemMessage).build());
        }
        if (deliveryDate != null) {
            builder.setDeliveryDate(Int64Value.newBuilder().setValue(deliveryDate.getTime()).build());
        }
        if (deletionDate != null) {
            builder.setDeletionDate(Int64Value.newBuilder().setValue(deletionDate.getTime()).build());
        }
        if (text != null) {
            builder.setText(StringValue.newBuilder().setValue(text).build());
        }
        if (senderId != null) {
            builder.setSenderId(Int64Value.newBuilder().setValue(senderId).build());
        }
        if (targetId != null) {
            builder.setRecipientId(Int64Value.newBuilder().setValue(targetId).build());
        }
        if (groupId != null) {
            builder.setGroupId(Int64Value.newBuilder().setValue(groupId).build());
        }
        if (records != null && !records.isEmpty()) {
            for (byte[] record : records) {
                builder.addRecords(ByteString.copyFrom(record));
            }
        }
        return builder;
    }

    public static UserInfo.Builder userProfile2proto(@NotNull User user) {
        UserInfo.Builder builder = UserInfo.newBuilder();
        Long userId = user.getId();
        String name = user.getName();
        String intro = user.getIntro();
        Date registrationDate = user.getRegistrationDate();
        Boolean active = user.getIsActive();
        ProfileAccessStrategy profileAccess = user.getProfileAccess();
        if (userId != null) {
            builder.setId(Int64Value.newBuilder().setValue(userId).build());
        }
        if (name != null) {
            builder.setName(StringValue.newBuilder().setValue(name).build());
        }
        if (intro != null) {
            builder.setIntro(StringValue.newBuilder().setValue(intro).build());
        }
        if (registrationDate != null) {
            builder.setRegistrationDate(Int64Value.newBuilder().setValue(registrationDate.getTime()).build());
        }
        if (active != null) {
            builder.setActive(BoolValue.newBuilder().setValue(active).build());
        }
        if (profileAccess != null) {
            builder.setProfileAccessStrategy(profileAccess);
        }
        return builder;
    }

    public static UserFriendRequest.Builder friendRequest2proto(@NotNull im.turms.turms.workflow.dao.domain.UserFriendRequest userFriendRequest) {
        UserFriendRequest.Builder builder = UserFriendRequest.newBuilder();
        Long requestId = userFriendRequest.getId();
        Date creationDate = userFriendRequest.getCreationDate();
        String content = userFriendRequest.getContent();
        RequestStatus status = userFriendRequest.getStatus();
        String reason = userFriendRequest.getReason();
        Date expirationDate = userFriendRequest.getExpirationDate();
        Long requesterId = userFriendRequest.getRequesterId();
        Long recipientId = userFriendRequest.getRecipientId();
        if (requestId != null) {
            builder.setId(Int64Value.newBuilder().setValue(requestId).build());
        }
        if (creationDate != null) {
            builder.setCreationDate(Int64Value.newBuilder().setValue(creationDate.getTime()).build());
        }
        if (content != null) {
            builder.setContent(StringValue.newBuilder().setValue(content).build());
        }
        if (status != null) {
            builder.setRequestStatus(status);
        }
        if (reason != null) {
            builder.setReason(StringValue.newBuilder().setValue(reason).build());
        }
        if (expirationDate != null) {
            builder.setExpirationDate(Int64Value.newBuilder().setValue(expirationDate.getTime()).build());
        }
        if (requesterId != null) {
            builder.setRequesterId(Int64Value.newBuilder().setValue(requesterId).build());
        }
        if (recipientId != null) {
            builder.setRecipientId(Int64Value.newBuilder().setValue(recipientId).build());
        }
        return builder;
    }

    public static UserRelationship.Builder relationship2proto(@NotNull im.turms.turms.workflow.dao.domain.UserRelationship relationship) {
        UserRelationship.Builder builder = UserRelationship.newBuilder();
        im.turms.turms.workflow.dao.domain.UserRelationship.Key key = relationship.getKey();
        Date blockDate = relationship.getBlockDate();
        Date establishmentDate = relationship.getEstablishmentDate();
        if (key != null) {
            Long ownerId = key.getOwnerId();
            Long relatedUserId = key.getRelatedUserId();
            if (ownerId != null) {
                builder.setOwnerId(Int64Value.newBuilder().setValue(ownerId).build());
            }
            if (relatedUserId != null) {
                builder.setRelatedUserId(Int64Value.newBuilder().setValue(relatedUserId).build());
            }
        }
        // TODO: change Boolean to Date
        builder.setBlocked(BoolValue.newBuilder().setValue(blockDate != null).build());
        if (establishmentDate != null) {
            builder.setEstablishmentDate(Int64Value.newBuilder().setValue(establishmentDate.getTime()).build());
        }
        return builder;
    }

    public static UserRelationshipGroup.Builder relationshipGroup2proto(@NotNull im.turms.turms.workflow.dao.domain.UserRelationshipGroup relationshipGroup) {
        UserRelationshipGroup.Builder builder = UserRelationshipGroup.newBuilder();
        im.turms.turms.workflow.dao.domain.UserRelationshipGroup.Key key = relationshipGroup.getKey();
        if (key != null) {
            Integer index = key.getGroupIndex();
            if (index != null) {
                builder.setIndex(index);
            }
        }
        String name = relationshipGroup.getName();
        if (name != null) {
            builder.setName(name);
        }
        return builder;
    }

    public static Group.Builder group2proto(@NotNull im.turms.turms.workflow.dao.domain.Group group) {
        Group.Builder builder = Group.newBuilder();
        Long groupId = group.getId();
        Long typeId = group.getTypeId();
        Long creatorId = group.getCreatorId();
        Long ownerId = group.getOwnerId();
        String name = group.getName();
        String intro = group.getIntro();
        String announcement = group.getAnnouncement();
        Date creationDate = group.getCreationDate();
        Date deletionDate = group.getDeletionDate();
        Date muteEndDate = group.getMuteEndDate();
        Boolean active = group.getIsActive();
        if (groupId != null) {
            builder.setId(Int64Value.newBuilder().setValue(groupId).build());
        }
        if (typeId != null) {
            builder.setTypeId(Int64Value.newBuilder().setValue(typeId).build());
        }
        if (creatorId != null) {
            builder.setCreatorId(Int64Value.newBuilder().setValue(creatorId).build());
        }
        if (ownerId != null) {
            builder.setOwnerId(Int64Value.newBuilder().setValue(ownerId).build());
        }
        if (name != null) {
            builder.setName(StringValue.newBuilder().setValue(name).build());
        }
        if (intro != null) {
            builder.setIntro(StringValue.newBuilder().setValue(intro).build());
        }
        if (announcement != null) {
            builder.setAnnouncement(StringValue.newBuilder().setValue(announcement).build());
        }
        if (creationDate != null) {
            builder.setCreationDate(Int64Value.newBuilder().setValue(creationDate.getTime()).build());
        }
        if (deletionDate != null) {
            builder.setDeletionDate(Int64Value.newBuilder().setValue(deletionDate.getTime()).build());
        }
        if (muteEndDate != null) {
            builder.setMuteEndDate(Int64Value.newBuilder().setValue(muteEndDate.getTime()).build());
        }
        if (active != null) {
            builder.setActive(BoolValue.newBuilder().setValue(active));
        }
        return builder;
    }

    public static GroupInvitation.Builder groupInvitation2proto(@NotNull im.turms.turms.workflow.dao.domain.GroupInvitation invitation) {
        GroupInvitation.Builder builder = GroupInvitation.newBuilder();
        Long invitationId = invitation.getId();
        Date creationDate = invitation.getCreationDate();
        String content = invitation.getContent();
        RequestStatus status = invitation.getStatus();
        Date expirationDate = invitation.getExpirationDate();
        Long groupId = invitation.getGroupId();
        Long inviterId = invitation.getInviterId();
        Long inviteeId = invitation.getInviteeId();
        if (invitationId != null) {
            builder.setId(Int64Value.newBuilder().setValue(invitationId).build());
        }
        if (creationDate != null) {
            builder.setCreationDate(Int64Value.newBuilder().setValue(creationDate.getTime()).build());
        }
        if (content != null) {
            builder.setContent(StringValue.newBuilder().setValue(content).build());
        }
        if (status != null) {
            builder.setStatusValue(status.getNumber());
        }
        if (expirationDate != null) {
            builder.setExpirationDate(Int64Value.newBuilder().setValue(expirationDate.getTime()).build());
        }
        if (groupId != null) {
            builder.setGroupId(Int64Value.newBuilder().setValue(groupId).build());
        }
        if (inviterId != null) {
            builder.setInviterId(Int64Value.newBuilder().setValue(inviterId).build());
        }
        if (inviteeId != null) {
            builder.setInviteeId(Int64Value.newBuilder().setValue(inviteeId).build());
        }
        return builder;
    }

    public static GroupJoinRequest.Builder groupJoinRequest2proto(@NotNull im.turms.turms.workflow.dao.domain.GroupJoinRequest groupJoinRequest) {
        GroupJoinRequest.Builder builder = GroupJoinRequest.newBuilder();
        Long requestId = groupJoinRequest.getId();
        Date creationDate = groupJoinRequest.getCreationDate();
        String content = groupJoinRequest.getContent();
        RequestStatus status = groupJoinRequest.getStatus();
        Date expirationDate = groupJoinRequest.getExpirationDate();
        Long groupId = groupJoinRequest.getGroupId();
        Long requesterId = groupJoinRequest.getRequesterId();
        Long responderId = groupJoinRequest.getResponderId();
        if (requestId != null) {
            builder.setId(Int64Value.newBuilder().setValue(requestId).build());
        }
        if (creationDate != null) {
            builder.setCreationDate(Int64Value.newBuilder().setValue(creationDate.getTime()).build());
        }
        if (content != null) {
            builder.setContent(StringValue.newBuilder().setValue(content).build());
        }
        if (status != null) {
            builder.setStatus(status);
        }
        if (expirationDate != null) {
            builder.setExpirationDate(Int64Value.newBuilder().setValue(expirationDate.getTime()).build());
        }
        if (groupId != null) {
            builder.setGroupId(Int64Value.newBuilder().setValue(groupId).build());
        }
        if (requesterId != null) {
            builder.setRequesterId(Int64Value.newBuilder().setValue(requesterId).build());
        }
        if (responderId != null) {
            builder.setResponderId(Int64Value.newBuilder().setValue(responderId).build());
        }
        return builder;
    }

    public static GroupJoinQuestion.Builder groupJoinQuestion2proto(@NotNull im.turms.turms.workflow.dao.domain.GroupJoinQuestion question) {
        GroupJoinQuestion.Builder builder = GroupJoinQuestion.newBuilder();
        Long questionId = question.getId();
        Long groupId = question.getGroupId();
        String content = question.getQuestion();
        if (questionId != null) {
            builder.setId(Int64Value.newBuilder().setValue(questionId).build());
        }
        if (groupId != null) {
            builder.setGroupId(Int64Value.newBuilder().setValue(groupId).build());
        }
        if (content != null) {
            builder.setQuestion(StringValue.newBuilder().setValue(content).build());
        }
        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            builder.addAllAnswers(question.getAnswers());
        }
        return builder;
    }

    public static GroupMember.Builder groupMember2proto(@NotNull im.turms.turms.workflow.dao.domain.GroupMember groupMember) {
        GroupMember.Builder builder = GroupMember.newBuilder();
        im.turms.turms.workflow.dao.domain.GroupMember.Key key = groupMember.getKey();
        if (key != null) {
            Long groupId = key.getGroupId();
            Long userId = key.getUserId();
            if (groupId != null) {
                builder.setGroupId(Int64Value.newBuilder().setValue(groupId).build());
            }
            if (userId != null) {
                builder.setUserId(Int64Value.newBuilder().setValue(userId).build());
            }
        }
        String name = groupMember.getName();
        GroupMemberRole role = groupMember.getRole();
        Date joinDate = groupMember.getJoinDate();
        Date muteEndDate = groupMember.getMuteEndDate();
        if (name != null) {
            builder.setName(StringValue.newBuilder().setValue(name).build());
        }
        if (role != null) {
            builder.setRole(role);
        }
        if (joinDate != null) {
            builder.setJoinDate(Int64Value.newBuilder().setValue(joinDate.getTime()).build());
        }
        if (muteEndDate != null) {
            builder.setMuteEndDate(Int64Value.newBuilder().setValue(muteEndDate.getTime()).build());
        }
        return builder;
    }

    public static MessageStatus.Builder messageStatus2proto(im.turms.turms.workflow.dao.domain.MessageStatus messageStatus) {
        MessageStatus.Builder builder = MessageStatus.newBuilder();
        im.turms.turms.workflow.dao.domain.MessageStatus.Key key = messageStatus.getKey();
        if (key != null) {
            Long messageId = key.getMessageId();
            Long recipientId = key.getRecipientId();
            if (messageId != null) {
                builder.setMessageId(Int64Value.newBuilder().setValue(messageId).build());
            }
            if (recipientId != null) {
                builder.setToUserId(Int64Value.newBuilder().setValue(recipientId).build());
            }
        }
        MessageDeliveryStatus deliveryStatus = messageStatus.getDeliveryStatus();
        Date receptionDate = messageStatus.getReceptionDate();
        Date readDate = messageStatus.getReadDate();
        Date recallDate = messageStatus.getRecallDate();
        if (deliveryStatus != null) {
            builder.setDeliveryStatus(deliveryStatus);
        }
        if (receptionDate != null) {
            builder.setReceptionDate(Int64Value.newBuilder().setValue(receptionDate.getTime()).build());
        }
        if (readDate != null) {
            builder.setReadDate(Int64Value.newBuilder().setValue(readDate.getTime()).build());
        }
        if (recallDate != null) {
            builder.setRecallDate(Int64Value.newBuilder().setValue(recallDate.getTime()).build());
        }
        return builder;
    }

    public static CreateMessageRequest.Builder message2createMessageRequest(Message message) {
        CreateMessageRequest.Builder builder = CreateMessageRequest.newBuilder();
        Long messageId = message.getId();
        Boolean isGroupMessage = message.getIsGroupMessage();
        Boolean isSystemMessage = message.getIsSystemMessage();
        Date deliveryDate = message.getDeliveryDate();
        String text = message.getText();
//        Long senderId = message.getSenderId(); the field is duplicate with requesterId
        Long targetId = message.getTargetId();
        List<byte[]> records = message.getRecords();
        Integer burnAfter = message.getBurnAfter();
        if (messageId != null) {
            builder.setMessageId(Int64Value.newBuilder().setValue(messageId).build());
        }
        if (isGroupMessage) {
            builder.setGroupId(Int64Value.newBuilder().setValue(targetId));
        } else {
            builder.setRecipientId(Int64Value.newBuilder().setValue(targetId));
        }
        if (isSystemMessage != null) {
            builder.setIsSystemMessage(BoolValue.newBuilder().setValue(isSystemMessage).build());
        }
        if (deliveryDate != null) {
            builder.setDeliveryDate(deliveryDate.getTime());
        }
        if (text != null) {
            builder.setText(StringValue.newBuilder().setValue(text).build());
        }
        if (records != null && !records.isEmpty()) {
            for (byte[] record : records) {
                ByteString byteString = ByteString.copyFrom(record);
                builder.addRecords(byteString);
            }
        }
        if (burnAfter != null) {
            builder.setBurnAfter(Int32Value.newBuilder().setValue(burnAfter).build());
        }
        return builder;
    }

    public static UserStatusDetail.Builder userOnlineInfo2userStatus(
            @NotNull Long userId,
            @Nullable UserSessionsStatus userSessionsStatus,
            boolean convertInvisibleToOffline) {
        UserStatusDetail.Builder builder = UserStatusDetail.newBuilder()
                .setUserId(userId);
        if (userSessionsStatus == null) {
            builder.setUserStatus(UserStatus.OFFLINE);
        } else {
            builder.setUserStatus(userSessionsStatus.getUserStatus(convertInvisibleToOffline));
            builder.addAllUsingDeviceTypes(userSessionsStatus.getLoggedInDeviceTypes());
        }
        return builder;
    }

    public static GroupMember.Builder userOnlineInfo2groupMember(
            @NotNull Long userId,
            @Nullable UserSessionsStatus userSessionsStatus,
            boolean convertInvisibleToOffline) {
        GroupMember.Builder builder = GroupMember
                .newBuilder()
                .setUserId(Int64Value.newBuilder().setValue(userId).build());
        if (userSessionsStatus == null) {
            builder.setUserStatus(UserStatus.OFFLINE);
        } else {
            builder.setUserStatus(userSessionsStatus.getUserStatus(convertInvisibleToOffline));
            builder.addAllUsingDeviceTypes(userSessionsStatus.getLoggedInDeviceTypes());
        }
        return builder;
    }

    public static CreateMessageRequest.Builder cloneAndFillMessageRequest(
            @NotNull CreateMessageRequest request,
            @NotNull Message message) {
        CreateMessageRequest.Builder builder = request.toBuilder();
        Long messageId = message.getId();
        Boolean isSystemMessage = message.getIsSystemMessage();
        Date deliveryDate = message.getDeliveryDate();
        String text = message.getText();
        List<byte[]> records = message.getRecords();
        Integer burnAfter = message.getBurnAfter();

        if (messageId != null) {
            builder.setMessageId(Int64Value.newBuilder().setValue(messageId).build());
        } else {
            builder.clearMessageId();
        }
        builder.setIsSystemMessage(BoolValue.newBuilder().setValue(isSystemMessage).build());
        builder.setDeliveryDate(deliveryDate.getTime());
        if (text != null) {
            builder.setText(StringValue.newBuilder().setValue(text).build());
        }
        if (records != null) {
            for (byte[] record : records) {
                builder.addRecords(ByteString.copyFrom(record));
            }
        }
        if (burnAfter != null) {
            builder.setBurnAfter(Int32Value.newBuilder().setValue(burnAfter).build());
        }
        return builder;
    }

}
