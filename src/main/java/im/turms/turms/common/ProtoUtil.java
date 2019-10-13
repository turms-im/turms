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
import im.turms.turms.constant.DeviceType;
import im.turms.turms.constant.UserStatus;
import im.turms.turms.pojo.bo.UserOnlineInfo;
import im.turms.turms.pojo.domain.Message;
import im.turms.turms.pojo.domain.*;
import im.turms.turms.pojo.dto.UserInfo;
import im.turms.turms.pojo.dto.UserStatusDetail;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ProtoUtil {
    public static im.turms.turms.pojo.dto.Message.Builder message2proto(Message message) {
        im.turms.turms.pojo.dto.Message.Builder messageBuilder = im.turms.turms.pojo.dto.Message
                .newBuilder()
                .setId(Int64Value.newBuilder().setValue(message.getId()).build())
                .setChatType(message.getChatType())
                .setDeliveryDate(Int64Value.newBuilder().setValue(message.getDeliveryDate().getTime()).build())
                .setDeletionDate(Int64Value.newBuilder().setValue(message.getDeletionDate().getTime()).build())
                .setText(StringValue.newBuilder().setValue(message.getText()).build())
                .setFromId(Int64Value.newBuilder().setValue(message.getSenderId()).build())
                .setToId(Int64Value.newBuilder().setValue(message.getTargetId()).build());
        for (byte[] record : message.getRecords()) {
            messageBuilder.addRecords(
                    BytesValue.newBuilder()
                            .setValue(ByteString.copyFrom(record))
                            .build());
        }
        return messageBuilder;
    }

    public static UserInfo.Builder userProfile2proto(@NotNull User user) {
        return UserInfo.newBuilder()
                .setId(Int64Value.newBuilder().setValue(user.getId()).build())
                .setName(StringValue.newBuilder().setValue(user.getName()).build())
                .setIntro(StringValue.newBuilder().setValue(user.getIntro()).build())
                .setProfilePictureUrl(StringValue.newBuilder().setValue(user.getProfilePictureUrl()).build())
                .setRegistrationDate(Int64Value.newBuilder().setValue(user.getRegistrationDate().getTime()).build())
                .setActive(BoolValue.newBuilder().setValue(user.getActive()).build())
                .setProfileAccessStrategy(user.getProfileAccess());
    }

    public static im.turms.turms.pojo.dto.UserFriendRequest.Builder friendRequest2proto(@NotNull UserFriendRequest userFriendRequest) {
        return im.turms.turms.pojo.dto.UserFriendRequest.newBuilder()
                .setId(Int64Value.newBuilder().setValue(userFriendRequest.getId()).build())
                .setCreationDate(Int64Value.newBuilder().setValue(userFriendRequest.getCreationDate().getTime()).build())
                .setContent(StringValue.newBuilder().setValue(userFriendRequest.getContent()).build())
                .setRequestStatus(userFriendRequest.getStatus())
                .setReason(StringValue.newBuilder().setValue(userFriendRequest.getReason()).build())
                .setExpirationDate(Int64Value.newBuilder().setValue(userFriendRequest.getExpirationDate().getTime()).build())
                .setRequesterId(Int64Value.newBuilder().setValue(userFriendRequest.getRequesterId()).build())
                .setRecipientId(Int64Value.newBuilder().setValue(userFriendRequest.getRecipientId()).build());
    }

    public static im.turms.turms.pojo.dto.UserRelationship.Builder relationship2proto(@NotNull UserRelationship relationship) {
        return im.turms.turms.pojo.dto.UserRelationship.newBuilder()
                .setOwnerId(Int64Value.newBuilder().setValue(relationship.getKey().getOwnerId()).build())
                .setRelatedUserId(Int64Value.newBuilder().setValue(relationship.getKey().getRelatedUserId()).build())
                .setIsBlocked(BoolValue.newBuilder().setValue(relationship.getIsBlocked()).build())
                .setEstablishmentDate(Int64Value.newBuilder().setValue(relationship.getEstablishmentDate().getTime()).build());
    }

    public static im.turms.turms.pojo.dto.UserRelationshipGroup.Builder relationshipGroup2proto(@NotNull UserRelationshipGroup relationshipGroup) {
        return im.turms.turms.pojo.dto.UserRelationshipGroup.newBuilder()
                .setIndex(relationshipGroup.getKey().getIndex())
                .setName(relationshipGroup.getName());
    }

    public static im.turms.turms.pojo.dto.Group.Builder group2proto(@NotNull Group group) {
        return im.turms.turms.pojo.dto.Group.newBuilder()
                .setId(Int64Value.newBuilder().setValue(group.getId()).build())
                .setTypeId(Int64Value.newBuilder().setValue(group.getTypeId()).build())
                .setCreatorId(Int64Value.newBuilder().setValue(group.getCreatorId()).build())
                .setOwnerId(Int64Value.newBuilder().setValue(group.getOwnerId()).build())
                .setName(StringValue.newBuilder().setValue(group.getName()).build())
                .setIntro(StringValue.newBuilder().setValue(group.getIntro()).build())
                .setAnnouncement(StringValue.newBuilder().setValue(group.getAnnouncement()).build())
                .setProfilePictureUrl(StringValue.newBuilder().setValue(group.getProfilePictureUrl()).build())
                .setCreationDate(Int64Value.newBuilder().setValue(group.getCreationDate().getTime()).build())
                .setDeletionDate(Int64Value.newBuilder().setValue(group.getDeletionDate().getTime()).build())
                .setMuteEndDate(Int64Value.newBuilder().setValue(group.getMuteEndDate().getTime()).build())
                .setActive(BoolValue.newBuilder().setValue(group.getActive()));
    }

    public static im.turms.turms.pojo.dto.GroupInvitation.Builder groupInvitation2proto(@NotNull GroupInvitation invitation) {
        return im.turms.turms.pojo.dto.GroupInvitation.newBuilder()
                .setId(Int64Value.newBuilder().setValue(invitation.getId()).build())
                .setCreationDate(Int64Value.newBuilder().setValue(invitation.getCreationDate().getTime()).build())
                .setContent(StringValue.newBuilder().setValue(invitation.getContent()).build())
                .setStatusValue(invitation.getStatus().getNumber())
                .setExpirationDate(Int64Value.newBuilder().setValue(invitation.getExpirationDate().getTime()).build())
                .setGroupId(Int64Value.newBuilder().setValue(invitation.getGroupId()).build())
                .setInviterId(Int64Value.newBuilder().setValue(invitation.getInviterId()).build())
                .setInviteeId(Int64Value.newBuilder().setValue(invitation.getInviteeId()).build());
    }

    public static im.turms.turms.pojo.dto.GroupJoinRequest.Builder groupJoinRequest2proto(@NotNull GroupJoinRequest groupJoinRequest) {
        return im.turms.turms.pojo.dto.GroupJoinRequest.newBuilder()
                .setId(Int64Value.newBuilder().setValue(groupJoinRequest.getId()).build())
                .setCreationDate(Int64Value.newBuilder().setValue(groupJoinRequest.getCreationDate().getTime()).build())
                .setContent(StringValue.newBuilder().setValue(groupJoinRequest.getContent()).build())
                .setStatus(groupJoinRequest.getStatus())
                .setExpirationDate(Int64Value.newBuilder().setValue(groupJoinRequest.getExpirationDate().getTime()).build())
                .setGroupId(Int64Value.newBuilder().setValue(groupJoinRequest.getGroupId()).build())
                .setRequesterId(Int64Value.newBuilder().setValue(groupJoinRequest.getRequesterId()).build())
                .setResponderId(Int64Value.newBuilder().setValue(groupJoinRequest.getResponderId()).build());
    }

    public static im.turms.turms.pojo.dto.GroupJoinQuestion.Builder groupJoinQuestion2proto(@NotNull GroupJoinQuestion question) {
        im.turms.turms.pojo.dto.GroupJoinQuestion.Builder builder = im.turms.turms.pojo.dto.GroupJoinQuestion.newBuilder()
                .setId(Int64Value.newBuilder().setValue(question.getId()).build())
                .setGroupId(Int64Value.newBuilder().setValue(question.getGroupId()).build())
                .setQuestion(StringValue.newBuilder().setValue(question.getQuestion()).build());
        if (question.getAnswers() != null && !question.getAnswers().isEmpty()) {
            for (String answer : question.getAnswers()) {
                builder.addAnswers(StringValue.newBuilder().setValue(answer).build());
            }
        }
        return builder;
    }

    public static im.turms.turms.pojo.dto.GroupMember.Builder groupMember2proto(@NotNull GroupMember groupMember) {
        return im.turms.turms.pojo.dto.GroupMember.newBuilder()
                .setGroupId(Int64Value.newBuilder().setValue(groupMember.getKey().getGroupId()).build())
                .setUserId(Int64Value.newBuilder().setValue(groupMember.getKey().getUserId()).build())
                .setName(StringValue.newBuilder().setValue(groupMember.getName()).build())
                .setRole(groupMember.getRole())
                .setJoinDate(Int64Value.newBuilder().setValue(groupMember.getJoinDate().getTime()).build())
                .setMuteEndDate(Int64Value.newBuilder().setValue(groupMember.getMuteEndDate().getTime()).build());
    }

    public static im.turms.turms.pojo.dto.MessageStatus.Builder messageStatus2proto(MessageStatus messageStatus) {
        return im.turms.turms.pojo.dto.MessageStatus
                .newBuilder()
                .setMessageId(Int64Value.newBuilder().setValue(messageStatus.getKey().getMessageId()).build())
                .setToUserId(Int64Value.newBuilder().setValue(messageStatus.getKey().getMessageId()).build())
                .setDeliveryStatus(messageStatus.getDeliveryStatus())
                .setReceptionDate(Int64Value.newBuilder().setValue(messageStatus.getReceptionDate().getTime()).build())
                .setReadDate(Int64Value.newBuilder().setValue(messageStatus.getReadDate().getTime()).build())
                .setRecallDate(Int64Value.newBuilder().setValue(messageStatus.getRecallDate().getTime()).build());
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

    public static im.turms.turms.pojo.dto.GroupMember.Builder userOnlineInfo2groupMember(
            @NotNull Long userId,
            @Nullable UserOnlineInfo userOnlineInfo) {
        im.turms.turms.pojo.dto.GroupMember.Builder builder = im.turms.turms.pojo.dto.GroupMember
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

    public static im.turms.turms.pojo.dto.GroupMember.Builder mergeGroupMember(
            @NotNull GroupMember member,
            @NotNull im.turms.turms.pojo.dto.GroupMember.Builder memberBuilder) {
        return memberBuilder
                .setGroupId(Int64Value.newBuilder().setValue(member.getKey().getGroupId()).build())
                .setUserId(Int64Value.newBuilder().setValue(member.getKey().getUserId()).build())
                .setName(StringValue.newBuilder().setValue(member.getName()).build())
                .setRole(member.getRole())
                .setJoinDate(Int64Value.newBuilder().setValue(member.getJoinDate().getTime()).build())
                .setMuteEndDate(Int64Value.newBuilder().setValue(member.getMuteEndDate().getTime()).build());
    }
}
