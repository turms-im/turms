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

package im.turms.service.infra.proto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import com.google.protobuf.ByteStringUtil;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.model.conversation.GroupConversation;
import im.turms.server.common.access.client.dto.model.conversation.PrivateConversation;
import im.turms.server.common.access.client.dto.model.group.Group;
import im.turms.server.common.access.client.dto.model.group.GroupInvitation;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestion;
import im.turms.server.common.access.client.dto.model.group.GroupJoinRequest;
import im.turms.server.common.access.client.dto.model.group.GroupMember;
import im.turms.server.common.access.client.dto.model.user.NearbyUser;
import im.turms.server.common.access.client.dto.model.user.UserFriendRequest;
import im.turms.server.common.access.client.dto.model.user.UserInfo;
import im.turms.server.common.access.client.dto.model.user.UserOnlineStatus;
import im.turms.server.common.access.client.dto.model.user.UserRelationship;
import im.turms.server.common.access.client.dto.model.user.UserRelationshipGroup;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.storage.bo.StorageResourceInfo;

/**
 * @author James Chen
 */
public final class ProtoModelConvertor {

    private ProtoModelConvertor() {
    }

    // Transformation

    public static List<String> toList(Map<String, String> map) {
        if (map.isEmpty()) {
            return Collections.emptyList();
        }
        Set<Map.Entry<String, String>> entries = map.entrySet();
        List<String> list = new ArrayList<>(entries.size() << 1);
        for (Map.Entry<String, String> entry : entries) {
            list.add(entry.getKey());
            list.add(entry.getValue());
        }
        return list;
    }

    public static im.turms.server.common.access.client.dto.model.message.Message.Builder message2proto(
            Message message) {
        var builder = ClientMessagePool.getMessageBuilder();
        Long messageId = message.getId();
        Boolean isSystemMessage = message.getIsSystemMessage();
        Date deliveryDate = message.getDeliveryDate();
        Date modificationDate = message.getModificationDate();
        String text = message.getText();
        List<byte[]> records = message.getRecords();
        Long senderId = message.getSenderId();
        Long targetId = message.getTargetId();
        Long groupId = message.groupId();
        Integer sequenceId = message.getSequenceId();
        Long preMessageId = message.getPreMessageId();
        if (messageId != null) {
            builder.setId(messageId);
        }
        if (isSystemMessage != null) {
            builder.setIsSystemMessage(isSystemMessage);
        }
        if (deliveryDate != null) {
            builder.setDeliveryDate(deliveryDate.getTime());
        }
        if (modificationDate != null) {
            builder.setModificationDate(modificationDate.getTime());
        }
        if (text != null) {
            builder.setText(text);
        }
        if (senderId != null) {
            builder.setSenderId(senderId);
        }
        if (targetId != null) {
            builder.setRecipientId(targetId);
        }
        if (groupId != null) {
            builder.setGroupId(groupId);
        }
        if (records != null && !records.isEmpty()) {
            for (byte[] record : records) {
                builder.addRecords(ByteStringUtil.wrap(record));
            }
        }
        if (sequenceId != null) {
            builder.setSequenceId(sequenceId);
        }
        if (preMessageId != null) {
            builder.setPreMessageId(preMessageId);
        }
        return builder;
    }

    public static UserInfo.Builder userProfile2proto(@NotNull User user) {
        UserInfo.Builder builder = ClientMessagePool.getUserInfoBuilder();
        Long userId = user.getId();
        String name = user.getName();
        String intro = user.getIntro();
        String profilePicture = user.getProfilePicture();
        Date registrationDate = user.getRegistrationDate();
        Boolean active = user.getIsActive();
        ProfileAccessStrategy profileAccessStrategy = user.getProfileAccessStrategy();
        Date lastUpdatedDate = user.getLastUpdatedDate();
        if (userId != null) {
            builder.setId(userId);
        }
        if (name != null) {
            builder.setName(name);
        }
        if (intro != null) {
            builder.setIntro(intro);
        }
        if (profilePicture != null) {
            builder.setProfilePicture(profilePicture);
        }
        if (registrationDate != null) {
            builder.setRegistrationDate(registrationDate.getTime());
        }
        if (active != null) {
            builder.setActive(active);
        }
        if (profileAccessStrategy != null) {
            builder.setProfileAccessStrategy(profileAccessStrategy);
        }
        if (lastUpdatedDate != null) {
            builder.setLastUpdatedDate(lastUpdatedDate.getTime());
        }
        return builder;
    }

    public static UserOnlineStatus.Builder userSessionsStatus2proto(
            @NotNull Long userId,
            @Nullable UserSessionsStatus userSessionsStatus,
            boolean convertInvisibleToOffline) {
        UserOnlineStatus.Builder builder = ClientMessagePool.getUserOnlineStatusBuilder()
                .setUserId(userId);
        if (userSessionsStatus == null) {
            builder.setUserStatus(UserStatus.OFFLINE);
        } else {
            builder.setUserStatus(userSessionsStatus.getUserStatus(convertInvisibleToOffline))
                    .addAllUsingDeviceTypes(userSessionsStatus.getLoggedInDeviceTypes());
        }
        return builder;
    }

    public static GroupMember.Builder userOnlineInfo2groupMember(
            @NotNull Long userId,
            @Nullable UserSessionsStatus userSessionsStatus,
            boolean convertInvisibleToOffline) {
        GroupMember.Builder builder = ClientMessagePool.getGroupMemberBuilder()
                .setUserId(userId);
        if (userSessionsStatus == null) {
            builder.setUserStatus(UserStatus.OFFLINE);
        } else {
            builder.setUserStatus(userSessionsStatus.getUserStatus(convertInvisibleToOffline))
                    .addAllUsingDeviceTypes(userSessionsStatus.getLoggedInDeviceTypes());
        }
        return builder;
    }

    public static NearbyUser.Builder nearbyUser2proto(
            @NotNull im.turms.server.common.domain.location.bo.NearbyUser nearbyUser) {
        NearbyUser.Builder builder = ClientMessagePool.getNearbyUserBuilder();
        Long userId = nearbyUser.userId();
        DeviceType deviceType = nearbyUser.deviceType();
        Float longitude = nearbyUser.longitude();
        Float latitude = nearbyUser.latitude();
        Integer distance = nearbyUser.distance();
        User info = nearbyUser.info();
        if (userId != null) {
            builder.setUserId(userId);
        }
        if (deviceType != null) {
            builder.setDeviceType(deviceType);
        }
        if (longitude != null && latitude != null) {
            builder.setLocation(ClientMessagePool.getUserLocationBuilder()
                    .setLongitude(longitude)
                    .setLatitude(latitude)
                    .build());
        }
        if (distance != null) {
            builder.setDistance(distance);
        }
        if (info != null) {
            builder.setInfo(userProfile2proto(info));
        }
        return builder;
    }

    public static UserFriendRequest.Builder friendRequest2proto(
            @NotNull im.turms.service.domain.user.po.UserFriendRequest userFriendRequest,
            int expireAfterSeconds) {
        UserFriendRequest.Builder builder = ClientMessagePool.getUserFriendRequestBuilder();
        Long requestId = userFriendRequest.getId();
        Date creationDate = userFriendRequest.getCreationDate();
        String content = userFriendRequest.getContent();
        RequestStatus status = userFriendRequest.getStatus();
        String reason = userFriendRequest.getReason();
        Long requesterId = userFriendRequest.getRequesterId();
        Long recipientId = userFriendRequest.getRecipientId();
        if (requestId != null) {
            builder.setId(requestId);
        }
        if (creationDate != null) {
            builder.setCreationDate(creationDate.getTime());
            if (expireAfterSeconds > 0) {
                builder.setExpirationDate(creationDate.getTime() + expireAfterSeconds * 1000L);
            }
        }
        if (content != null) {
            builder.setContent(content);
        }
        if (status != null) {
            builder.setRequestStatus(status);
        }
        if (reason != null) {
            builder.setReason(reason);
        }
        if (requesterId != null) {
            builder.setRequesterId(requesterId);
        }
        if (recipientId != null) {
            builder.setRecipientId(recipientId);
        }
        return builder;
    }

    public static UserRelationship.Builder relationship2proto(
            @NotNull im.turms.service.domain.user.po.UserRelationship relationship) {
        UserRelationship.Builder builder = ClientMessagePool.getUserRelationshipBuilder();
        im.turms.service.domain.user.po.UserRelationship.Key key = relationship.getKey();
        Date establishmentDate = relationship.getEstablishmentDate();
        Date blockDate = relationship.getBlockDate();
        if (key != null) {
            Long ownerId = key.getOwnerId();
            Long relatedUserId = key.getRelatedUserId();
            if (ownerId != null) {
                builder.setOwnerId(ownerId);
            }
            if (relatedUserId != null) {
                builder.setRelatedUserId(relatedUserId);
            }
        }
        if (blockDate != null) {
            builder.setBlockDate(blockDate.getTime());
        }
        if (establishmentDate != null) {
            builder.setEstablishmentDate(establishmentDate.getTime());
        }
        return builder;
    }

    public static UserRelationshipGroup.Builder relationshipGroup2proto(
            @NotNull im.turms.service.domain.user.po.UserRelationshipGroup relationshipGroup) {
        UserRelationshipGroup.Builder builder = ClientMessagePool.getUserRelationshipGroupBuilder();
        im.turms.service.domain.user.po.UserRelationshipGroup.Key key = relationshipGroup.getKey();
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

    public static Group.Builder group2proto(@NotNull im.turms.service.domain.group.po.Group group) {
        Group.Builder builder = ClientMessagePool.getGroupBuilder();
        Long groupId = group.getId();
        Long typeId = group.getTypeId();
        Long creatorId = group.getCreatorId();
        Long ownerId = group.getOwnerId();
        String name = group.getName();
        String intro = group.getIntro();
        String announcement = group.getAnnouncement();
        Date creationDate = group.getCreationDate();
        Date lastUpdatedDate = group.getLastUpdatedDate();
        Date muteEndDate = group.getMuteEndDate();
        Boolean active = group.getIsActive();
        if (groupId != null) {
            builder.setId(groupId);
        }
        if (typeId != null) {
            builder.setTypeId(typeId);
        }
        if (creatorId != null) {
            builder.setCreatorId(creatorId);
        }
        if (ownerId != null) {
            builder.setOwnerId(ownerId);
        }
        if (name != null) {
            builder.setName(name);
        }
        if (intro != null) {
            builder.setIntro(intro);
        }
        if (announcement != null) {
            builder.setAnnouncement(announcement);
        }
        if (creationDate != null) {
            builder.setCreationDate(creationDate.getTime());
        }
        if (lastUpdatedDate != null) {
            builder.setLastUpdatedDate(lastUpdatedDate.getTime());
        }
        if (muteEndDate != null) {
            builder.setMuteEndDate(muteEndDate.getTime());
        }
        if (active != null) {
            builder.setActive(active);
        }
        return builder;
    }

    public static GroupInvitation.Builder groupInvitation2proto(
            @NotNull im.turms.service.domain.group.po.GroupInvitation invitation,
            int expireAfterSeconds) {
        GroupInvitation.Builder builder = ClientMessagePool.getGroupInvitationBuilder();
        Long invitationId = invitation.getId();
        Date creationDate = invitation.getCreationDate();
        String content = invitation.getContent();
        RequestStatus status = invitation.getStatus();
        Long groupId = invitation.getGroupId();
        Long inviterId = invitation.getInviterId();
        Long inviteeId = invitation.getInviteeId();
        if (invitationId != null) {
            builder.setId(invitationId);
        }
        if (creationDate != null) {
            builder.setCreationDate(creationDate.getTime());
            if (expireAfterSeconds > 0) {
                builder.setExpirationDate(creationDate.getTime() + expireAfterSeconds * 1000L);
            }
        }
        if (content != null) {
            builder.setContent(content);
        }
        if (status != null) {
            builder.setStatusValue(status.getNumber());
        }
        if (groupId != null) {
            builder.setGroupId(groupId);
        }
        if (inviterId != null) {
            builder.setInviterId(inviterId);
        }
        if (inviteeId != null) {
            builder.setInviteeId(inviteeId);
        }
        return builder;
    }

    public static GroupJoinRequest.Builder groupJoinRequest2proto(
            @NotNull im.turms.service.domain.group.po.GroupJoinRequest groupJoinRequest,
            int expireAfterSeconds) {
        GroupJoinRequest.Builder builder = ClientMessagePool.getGroupJoinRequestBuilder();
        Long requestId = groupJoinRequest.getId();
        Date creationDate = groupJoinRequest.getCreationDate();
        String content = groupJoinRequest.getContent();
        RequestStatus status = groupJoinRequest.getStatus();
        Long groupId = groupJoinRequest.getGroupId();
        Long requesterId = groupJoinRequest.getRequesterId();
        Long responderId = groupJoinRequest.getResponderId();
        if (requestId != null) {
            builder.setId(requestId);
        }
        if (creationDate != null) {
            builder.setCreationDate(creationDate.getTime());
            if (expireAfterSeconds > 0) {
                builder.setExpirationDate(creationDate.getTime() + expireAfterSeconds * 1000L);
            }
        }
        if (content != null) {
            builder.setContent(content);
        }
        if (status != null) {
            builder.setStatus(status);
        }
        if (groupId != null) {
            builder.setGroupId(groupId);
        }
        if (requesterId != null) {
            builder.setRequesterId(requesterId);
        }
        if (responderId != null) {
            builder.setResponderId(responderId);
        }
        return builder;
    }

    public static GroupJoinQuestion.Builder groupJoinQuestion2proto(
            @NotNull im.turms.service.domain.group.po.GroupJoinQuestion question) {
        GroupJoinQuestion.Builder builder = ClientMessagePool.getGroupJoinQuestionBuilder();
        Long questionId = question.getId();
        Long groupId = question.getGroupId();
        String content = question.getQuestion();
        if (questionId != null) {
            builder.setId(questionId);
        }
        if (groupId != null) {
            builder.setGroupId(groupId);
        }
        if (content != null) {
            builder.setQuestion(content);
        }
        if (question.getAnswers() != null
                && !question.getAnswers()
                        .isEmpty()) {
            builder.addAllAnswers(question.getAnswers());
        }
        return builder;
    }

    public static GroupMember.Builder groupMember2proto(
            @NotNull im.turms.service.domain.group.po.GroupMember groupMember) {
        GroupMember.Builder builder = ClientMessagePool.getGroupMemberBuilder();
        im.turms.service.domain.group.po.GroupMember.Key key = groupMember.getKey();
        if (key != null) {
            Long groupId = key.getGroupId();
            Long userId = key.getUserId();
            if (groupId != null) {
                builder.setGroupId(groupId);
            }
            if (userId != null) {
                builder.setUserId(userId);
            }
        }
        String name = groupMember.getName();
        GroupMemberRole role = groupMember.getRole();
        Date joinDate = groupMember.getJoinDate();
        Date muteEndDate = groupMember.getMuteEndDate();
        if (name != null) {
            builder.setName(name);
        }
        if (role != null) {
            builder.setRole(role);
        }
        if (joinDate != null) {
            builder.setJoinDate(joinDate.getTime());
        }
        if (muteEndDate != null) {
            builder.setMuteEndDate(muteEndDate.getTime());
        }
        return builder;
    }

    // Conversation

    public static PrivateConversation.Builder privateConversation2proto(
            im.turms.service.domain.conversation.po.PrivateConversation privateConversation) {
        PrivateConversation.Builder builder = ClientMessagePool.getPrivateConversationBuilder();
        im.turms.service.domain.conversation.po.PrivateConversation.Key key =
                privateConversation.getKey();
        if (key != null) {
            Long ownerId = key.getOwnerId();
            Long targetId = key.getTargetId();
            if (ownerId != null) {
                builder.setOwnerId(ownerId);
            }
            if (targetId != null) {
                builder.setTargetId(targetId);
            }
        }
        Date readDate = privateConversation.getReadDate();
        if (readDate != null) {
            builder.setReadDate(readDate.getTime());
        }
        return builder;
    }

    public static GroupConversation.Builder groupConversations2proto(
            im.turms.service.domain.conversation.po.GroupConversation groupConversation) {
        GroupConversation.Builder builder = ClientMessagePool.getGroupConversationBuilder();
        Long groupId = groupConversation.getGroupId();
        if (groupId != null) {
            builder.setGroupId(groupId);
        }
        Map<Long, Date> memberIdToReadDate = groupConversation.getMemberIdToReadDate();
        if (memberIdToReadDate != null) {
            Map<Long, Long> map = CollectionUtil.transformValues(memberIdToReadDate, Date::getTime);
            builder.putAllMemberIdToReadDate(map);
        }
        return builder;
    }

    public static CreateMessageRequest.Builder message2createMessageRequest(Message message) {
        CreateMessageRequest.Builder builder = ClientMessagePool.getCreateMessageRequestBuilder();
        Long messageId = message.getId();
        Boolean isGroupMessage = message.getIsGroupMessage();
        Boolean isSystemMessage = message.getIsSystemMessage();
        Date deliveryDate = message.getDeliveryDate();
        String text = message.getText();
        // message.getSenderId() is just requesterId
        Long targetId = message.getTargetId();
        List<byte[]> records = message.getRecords();
        Integer burnAfter = message.getBurnAfter();
        Long preMessageId = message.getPreMessageId();
        if (messageId != null) {
            builder.setMessageId(messageId);
        }
        if (isGroupMessage) {
            builder.setGroupId(targetId);
        } else {
            builder.setRecipientId(targetId);
        }
        if (isSystemMessage != null) {
            builder.setIsSystemMessage(isSystemMessage);
        }
        if (deliveryDate != null) {
            builder.setDeliveryDate(deliveryDate.getTime());
        }
        if (text != null) {
            builder.setText(text);
        }
        if (records != null && !records.isEmpty()) {
            for (byte[] record : records) {
                builder.addRecords(ByteStringUtil.wrap(record));
            }
        }
        if (burnAfter != null) {
            builder.setBurnAfter(burnAfter);
        }
        if (preMessageId != null) {
            builder.setPreMessageId(preMessageId);
        }
        return builder;
    }

    public static CreateMessageRequest.Builder cloneAndFillMessageRequest(
            @NotNull CreateMessageRequest request,
            @NotNull Message message) {
        CreateMessageRequest.Builder builder = ClientMessagePool.getCreateMessageRequestBuilder()
                .mergeFrom(request);
        Long messageId = message.getId();
        Boolean isSystemMessage = message.getIsSystemMessage();
        Date deliveryDate = message.getDeliveryDate();
        String text = message.getText();
        List<byte[]> records = message.getRecords();
        Integer burnAfter = message.getBurnAfter();

        if (messageId != null) {
            builder.setMessageId(messageId);
        } else {
            builder.clearMessageId();
        }
        builder.setIsSystemMessage(isSystemMessage);
        builder.setDeliveryDate(deliveryDate.getTime());
        if (text != null) {
            builder.setText(text);
        }
        if (records != null) {
            for (byte[] record : records) {
                builder.addRecords(ByteStringUtil.wrap(record));
            }
        }
        if (burnAfter != null) {
            builder.setBurnAfter(burnAfter);
        }
        return builder;
    }

    public static im.turms.server.common.access.client.dto.model.storage.StorageResourceInfo storageResourceInfo2proto(
            StorageResourceInfo info) {
        var builder = ClientMessagePool.getStorageResourceInfoBuilder();
        Long idNum = info.idNum();
        String idStr = info.idStr();
        if (idNum != null) {
            builder.setIdNum(idNum);
        }
        if (idStr != null) {
            builder.setIdStr(idStr);
        }
        return builder.setMediaType(info.mediaType())
                .setUploaderId(info.uploaderId())
                .setCreationDate(info.creationDate()
                        .getTime())
                .build();
    }

}