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

package im.turms.turms.common;

import com.google.protobuf.*;
import im.turms.turms.constant.*;
import im.turms.turms.pojo.bo.UserOnlineInfo;
import im.turms.turms.pojo.bo.user.UserInfo;
import im.turms.turms.pojo.bo.user.UserStatusDetail;
import im.turms.turms.pojo.domain.Message;
import im.turms.turms.pojo.domain.*;
import im.turms.turms.pojo.request.message.CreateMessageRequest;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class ProtoUtil {

    private ProtoUtil() {
    }

    public static im.turms.turms.pojo.bo.message.Message.Builder message2proto(Message message) {
        im.turms.turms.pojo.bo.message.Message.Builder builder = im.turms.turms.pojo.bo.message.Message.newBuilder();
        Long messageId = message.getId();
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
                builder.addRecords(BytesValue.newBuilder()
                        .setValue(ByteString.copyFrom(record))
                        .build());
            }
        }
        return builder;
    }

    public static UserInfo.Builder userProfile2proto(@NotNull User user) {
        UserInfo.Builder builder = UserInfo.newBuilder();
        Long userId = user.getId();
        String name = user.getName();
        String intro = user.getIntro();
        String profilePictureUrl = user.getProfilePictureUrl();
        Date registrationDate = user.getRegistrationDate();
        Boolean active = user.getActive();
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
        if (profilePictureUrl != null) {
            builder.setProfilePictureUrl(StringValue.newBuilder().setValue(profilePictureUrl).build());
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

    public static im.turms.turms.pojo.bo.user.UserFriendRequest.Builder friendRequest2proto(@NotNull UserFriendRequest userFriendRequest) {
        im.turms.turms.pojo.bo.user.UserFriendRequest.Builder builder = im.turms.turms.pojo.bo.user.UserFriendRequest.newBuilder();
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

    public static im.turms.turms.pojo.bo.user.UserRelationship.Builder relationship2proto(@NotNull UserRelationship relationship) {
        im.turms.turms.pojo.bo.user.UserRelationship.Builder builder = im.turms.turms.pojo.bo.user.UserRelationship.newBuilder();
        UserRelationship.Key key = relationship.getKey();
        Boolean isBlocked = relationship.getIsBlocked();
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
        if (isBlocked != null) {
            builder.setIsBlocked(BoolValue.newBuilder().setValue(isBlocked).build());
        }
        if (establishmentDate != null) {
            builder.setEstablishmentDate(Int64Value.newBuilder().setValue(establishmentDate.getTime()).build());
        }
        return builder;
    }

    public static im.turms.turms.pojo.bo.user.UserRelationshipGroup.Builder relationshipGroup2proto(@NotNull UserRelationshipGroup relationshipGroup) {
        im.turms.turms.pojo.bo.user.UserRelationshipGroup.Builder builder = im.turms.turms.pojo.bo.user.UserRelationshipGroup.newBuilder();
        UserRelationshipGroup.Key key = relationshipGroup.getKey();
        if (key != null) {
            Integer index = key.getIndex();
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

    public static im.turms.turms.pojo.bo.group.Group.Builder group2proto(@NotNull Group group) {
        im.turms.turms.pojo.bo.group.Group.Builder builder = im.turms.turms.pojo.bo.group.Group.newBuilder();
        Long groupId = group.getId();
        Long typeId = group.getTypeId();
        Long creatorId = group.getCreatorId();
        Long ownerId = group.getOwnerId();
        String name = group.getName();
        String intro = group.getIntro();
        String announcement = group.getAnnouncement();
        String profilePictureUrl = group.getProfilePictureUrl();
        Date creationDate = group.getCreationDate();
        Date deletionDate = group.getDeletionDate();
        Date muteEndDate = group.getMuteEndDate();
        Boolean active = group.getActive();
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
        if (profilePictureUrl != null) {
            builder.setProfilePictureUrl(StringValue.newBuilder().setValue(profilePictureUrl).build());
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

    public static im.turms.turms.pojo.bo.group.GroupInvitation.Builder groupInvitation2proto(@NotNull GroupInvitation invitation) {
        im.turms.turms.pojo.bo.group.GroupInvitation.Builder builder = im.turms.turms.pojo.bo.group.GroupInvitation.newBuilder();
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

    public static im.turms.turms.pojo.bo.group.GroupJoinRequest.Builder groupJoinRequest2proto(@NotNull GroupJoinRequest groupJoinRequest) {
        im.turms.turms.pojo.bo.group.GroupJoinRequest.Builder builder = im.turms.turms.pojo.bo.group.GroupJoinRequest.newBuilder();
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

    public static im.turms.turms.pojo.bo.group.GroupJoinQuestion.Builder groupJoinQuestion2proto(@NotNull GroupJoinQuestion question) {
        im.turms.turms.pojo.bo.group.GroupJoinQuestion.Builder builder = im.turms.turms.pojo.bo.group.GroupJoinQuestion.newBuilder();
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
            for (String answer : question.getAnswers()) {
                builder.addAnswers(StringValue.newBuilder().setValue(answer).build());
            }
        }
        return builder;
    }

    public static im.turms.turms.pojo.bo.group.GroupMember.Builder groupMember2proto(@NotNull GroupMember groupMember) {
        im.turms.turms.pojo.bo.group.GroupMember.Builder builder = im.turms.turms.pojo.bo.group.GroupMember.newBuilder();
        GroupMember.Key key = groupMember.getKey();
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

    public static im.turms.turms.pojo.bo.message.MessageStatus.Builder messageStatus2proto(MessageStatus messageStatus) {
        im.turms.turms.pojo.bo.message.MessageStatus.Builder builder = im.turms.turms.pojo.bo.message.MessageStatus.newBuilder();
        MessageStatus.Key key = messageStatus.getKey();
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
        ChatType chatType = message.getChatType();
        Date deliveryDate = message.getDeliveryDate();
        String text = message.getText();
//        Long senderId = message.getSenderId(); the field is duplicated with requesterId
        Long targetId = message.getTargetId();
        List<byte[]> records = message.getRecords();
        Integer burnAfter = message.getBurnAfter();
        if (messageId != null) {
            builder.setMessageId(Int64Value.newBuilder().setValue(messageId).build());
        }
        if (chatType != null && chatType != ChatType.UNRECOGNIZED) {
            builder.setChatType(chatType);
        }
        if (deliveryDate != null) {
            builder.setDeliveryDate(deliveryDate.getTime());
        }
        if (text != null) {
            builder.setText(StringValue.newBuilder().setValue(text).build());
        }
        if (targetId != null) {
            builder.setToId(targetId);
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
            @Nullable UserOnlineInfo userOnlineInfo) {
        UserStatusDetail.Builder builder = UserStatusDetail.newBuilder()
                .setUserId(userId);
        if (userOnlineInfo == null) {
            builder.setUserStatus(UserStatus.OFFLINE);
        } else {
            builder.setUserStatus(userOnlineInfo.getUserStatusForClients());
            builder.addAllUsingDeviceTypes(userOnlineInfo.getUsingDeviceTypes());
        }
        return builder;
    }

    public static im.turms.turms.pojo.bo.group.GroupMember.Builder userOnlineInfo2groupMember(
            @NotNull Long userId,
            @Nullable UserOnlineInfo userOnlineInfo) {
        im.turms.turms.pojo.bo.group.GroupMember.Builder builder = im.turms.turms.pojo.bo.group.GroupMember
                .newBuilder()
                .setUserId(Int64Value.newBuilder().setValue(userId).build());
        if (userOnlineInfo == null) {
            builder.setUserStatus(UserStatus.OFFLINE);
        } else {
            builder.setUserStatus(userOnlineInfo.getUserStatusForClients());
            Set<DeviceType> usingDeviceTypes = userOnlineInfo.getUsingDeviceTypes();
            if (usingDeviceTypes != null) {
                builder.addAllUsingDeviceTypes(userOnlineInfo.getUsingDeviceTypes());
            }
        }
        return builder;
    }
}
