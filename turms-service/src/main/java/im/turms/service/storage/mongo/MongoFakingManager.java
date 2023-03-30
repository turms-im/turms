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

package im.turms.service.storage.mongo;

import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import lombok.Getter;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Scheduler;
import reactor.core.scheduler.Schedulers;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.property.env.service.env.FakeProperties;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.service.domain.conversation.po.GroupConversation;
import im.turms.service.domain.conversation.po.PrivateConversation;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.bo.GroupUpdateStrategy;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.po.GroupBlockedUser;
import im.turms.service.domain.group.po.GroupInvitation;
import im.turms.service.domain.group.po.GroupJoinQuestion;
import im.turms.service.domain.group.po.GroupJoinRequest;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.po.GroupVersion;
import im.turms.service.domain.message.po.Message;
import im.turms.service.domain.message.repository.MessageRepository;
import im.turms.service.domain.user.po.UserFriendRequest;
import im.turms.service.domain.user.po.UserPermissionGroup;
import im.turms.service.domain.user.po.UserRelationship;
import im.turms.service.domain.user.po.UserRelationshipGroup;
import im.turms.service.domain.user.po.UserRelationshipGroupMember;
import im.turms.service.domain.user.po.UserVersion;

import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_ID;
import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_USER_PERMISSION_GROUP_ID;

/**
 * @author James Chen
 */
public final class MongoFakingManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoFakingManager.class);

    private final PasswordManager passwordManager;

    private final TurmsMongoClient adminMongoClient;
    private final TurmsMongoClient userMongoClient;
    private final TurmsMongoClient groupMongoClient;
    private final TurmsMongoClient conversationMongoClient;
    private final TurmsMongoClient messageMongoClient;

    @Getter
    private final boolean isFakingEnabled;
    private final int userCount;

    private final int targetUserToBeGroupMemberStart;
    private final int targetUserToBeGroupMemberEnd;
    private final int targetUserForGroupJoinRequestStart;
    private final int targetUserForGroupJoinRequestEnd;
    private final int targetUserForGroupInvitationStart;
    private final int targetUserForGroupInvitationEnd;
    private final int targetUserToBlockInGroupStart;
    private final int targetUserToBlockInGroupEnd;

    private final int targetUserToRequestFriendRequestStart;
    private final int targetUserToRequestFriendRequestEnd;
    private final int targetUserToBeFriendRelationshipStart;
    private final int targetUserToBeFriendRelationshipEnd;

    @Getter
    private final boolean clearAllCollectionsBeforeFaking;
    @Getter
    private final boolean fakeIfCollectionExists;

    private long currentId = 1L;

    public MongoFakingManager(
            FakeProperties properties,
            PasswordManager passwordManager,
            TurmsMongoClient adminMongoClient,
            TurmsMongoClient userMongoClient,
            TurmsMongoClient groupMongoClient,
            TurmsMongoClient conversationMongoClient,
            TurmsMongoClient messageMongoClient) {
        this.passwordManager = passwordManager;
        this.adminMongoClient = adminMongoClient;
        this.userMongoClient = userMongoClient;
        this.groupMongoClient = groupMongoClient;
        this.conversationMongoClient = conversationMongoClient;
        this.messageMongoClient = messageMongoClient;

        isFakingEnabled = properties.isEnabled();
        clearAllCollectionsBeforeFaking = properties.isClearAllCollectionsBeforeFaking();
        fakeIfCollectionExists = properties.isFakeIfCollectionExists();
        userCount = properties.getUserCount();
        int step = userCount / 10;

        targetUserToBeGroupMemberStart = 1;
        targetUserToBeGroupMemberEnd = step;
        targetUserForGroupJoinRequestStart = 1 + step * 7;
        targetUserForGroupJoinRequestEnd = step * 8;
        targetUserForGroupInvitationStart = 1 + step * 8;
        targetUserForGroupInvitationEnd = step * 9;
        targetUserToBlockInGroupStart = 1 + step * 9;
        targetUserToBlockInGroupEnd = step * 10;

        targetUserToBeFriendRelationshipStart = 2;
        targetUserToBeFriendRelationshipEnd = step;
        targetUserToRequestFriendRequestStart = 1 + step;
        targetUserToRequestFriendRequestEnd = 1 + step * 2;
    }

    public Mono<Void> fakeData() {
        LOGGER.info("Start faking");

        final int adminCount = 10;

        List<Object> adminRelatedObjs = new LinkedList<>();
        List<Object> userRelatedObjs = new LinkedList<>();
        List<Object> groupRelatedObjs = new LinkedList<>();
        List<Object> conversationRelatedObjs = new LinkedList<>();
        List<Object> messageRelatedObjs = new LinkedList<>();

        // Admin
        // "2000-01-01:00:00:00Z"
        final Date epoch = new Date(946684800000L);
        final long guestRoleId = 2L;
        Admin guest = new Admin(
                "guest",
                passwordManager.encodeAdminPassword("guest"),
                "guest",
                guestRoleId,
                epoch);
        adminRelatedObjs.add(guest);
        for (int i = 1; i <= adminCount; i++) {
            Admin admin = new Admin(
                    "account"
                            + i,
                    passwordManager.encodeAdminPassword("123"),
                    "my-name",
                    1L,
                    DateUtils.addDays(epoch, i));
            adminRelatedObjs.add(admin);
        }
        AdminRole adminRole = new AdminRole(1L, "ADMIN", AdminPermission.ALL, 0, epoch);
        AdminRole guestRole = new AdminRole(
                guestRoleId,
                "GUEST",
                CollectionUtil.newSet(AdminPermission.matchPermission("*"
                        + AdminPermission.SUFFIX_QUERY),
                        AdminPermission.matchPermission("*"
                                + AdminPermission.SUFFIX_CREATE)),
                0,
                epoch);
        adminRelatedObjs.add(adminRole);
        adminRelatedObjs.add(guestRole);

        // Group
        Group group = new Group(
                1L,
                DEFAULT_GROUP_TYPE_ID,
                1L,
                1L,
                "Turms Developers Group",
                "This is a group for the developers who are interested in Turms",
                "nope",
                0,
                epoch,
                null,
                epoch,
                null,
                true);
        groupRelatedObjs.add(group);
        GroupVersion groupVersion = new GroupVersion(1L, epoch, epoch, epoch, epoch, epoch);
        groupRelatedObjs.add(groupVersion);
        for (int i = targetUserToBlockInGroupStart; i <= targetUserToBlockInGroupEnd; i++) {
            GroupBlockedUser groupBlockedUser = new GroupBlockedUser(1L, (long) i, epoch, 1L);
            groupRelatedObjs.add(groupBlockedUser);
        }
        for (int i = targetUserForGroupInvitationStart; i <= targetUserForGroupInvitationEnd; i++) {
            GroupInvitation groupInvitation = new GroupInvitation(
                    nextId(),
                    1L,
                    1L,
                    (long) i,
                    "test-content",
                    RequestStatus.PENDING,
                    DateUtils.addDays(epoch, i),
                    null);
            groupRelatedObjs.add(groupInvitation);
        }
        GroupJoinQuestion groupJoinQuestion = new GroupJoinQuestion(
                nextId(),
                1L,
                "test-question",
                new LinkedHashSet<>(List.of("a", "b", "c")),
                20);
        groupRelatedObjs.add(groupJoinQuestion);
        for (int i =
                targetUserForGroupJoinRequestStart; i <= targetUserForGroupJoinRequestEnd; i++) {
            GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                    nextId(),
                    "test-content",
                    RequestStatus.PENDING,
                    epoch,
                    null,
                    1L,
                    (long) i,
                    null);
            groupRelatedObjs.add(groupJoinRequest);
        }
        Set<Long> groupMemberIds = CollectionUtil.newSetWithExpectedSize(
                targetUserToBeGroupMemberEnd - targetUserToBeGroupMemberStart);
        for (int i = targetUserToBeGroupMemberStart; i <= targetUserToBeGroupMemberEnd; i++) {
            long groupMemberId = i;
            groupMemberIds.add(groupMemberId);
            GroupMember groupMember = new GroupMember(
                    1L,
                    groupMemberId,
                    "test-name",
                    i == 1
                            ? GroupMemberRole.OWNER
                            : GroupMemberRole.MEMBER,
                    epoch,
                    i > userCount / 10 / 2
                            ? new Date(9999999999999L)
                            : null);
            groupRelatedObjs.add(groupMember);
        }

        groupRelatedObjs.add(new GroupType(
                1L,
                "test",
                1000,
                GroupInvitationStrategy.ALL,
                GroupJoinStrategy.MEMBERSHIP_REQUEST,
                GroupUpdateStrategy.OWNER_MANAGER,
                GroupUpdateStrategy.OWNER_MANAGER_MEMBER,
                true,
                true,
                true,
                true));

        // Message
        long senderId = 1L;
        Set<Long> targetIds = CollectionUtil.newSetWithExpectedSize(100);
        byte[] ip = new byte[]{127, 0, 0, 1};
        Integer ipBytes = InetAddressUtil.ipBytesToInt(ip);
        for (int i = 1; i <= 100; i++) {
            long targetId = (long) 2 + (i % 9);
            targetIds.add(targetId);
            byte[] privateConversationId =
                    MessageRepository.getPrivateConversationId(senderId, targetId);
            Date deliveryDate = DateUtils.addHours(epoch, i);
            Message privateMessage1 = new Message(
                    nextId(),
                    privateConversationId,
                    false,
                    false,
                    deliveryDate,
                    null,
                    null,
                    null,
                    "private-message-text"
                            + RandomStringUtils.randomAlphanumeric(16),
                    senderId,
                    ipBytes,
                    targetId,
                    null,
                    30,
                    null,
                    null,
                    null);
            Message privateMessage2 = new Message(
                    nextId(),
                    privateConversationId,
                    false,
                    false,
                    deliveryDate,
                    null,
                    null,
                    null,
                    "private-message-text"
                            + RandomStringUtils.randomAlphanumeric(16),
                    targetId,
                    ipBytes,
                    senderId,
                    null,
                    30,
                    null,
                    null,
                    null);
            long groupId = 1L;
            Message groupMessage = new Message(
                    nextId(),
                    MessageRepository.getGroupConversationId(groupId),
                    true,
                    false,
                    epoch,
                    null,
                    null,
                    null,
                    "group-message-text"
                            + RandomStringUtils.randomAlphanumeric(16),
                    1L,
                    ipBytes,
                    groupId,
                    null,
                    30,
                    null,
                    null,
                    null);
            messageRelatedObjs.add(privateMessage1);
            messageRelatedObjs.add(privateMessage2);
            messageRelatedObjs.add(groupMessage);
        }

        // Conversation
        for (Long targetId : targetIds) {
            PrivateConversation privateConversation =
                    new PrivateConversation(new PrivateConversation.Key(senderId, targetId), epoch);
            conversationRelatedObjs.add(privateConversation);
        }
        GroupConversation groupConversation =
                new GroupConversation(1L, CollectionUtil.newMap(groupMemberIds, id -> epoch));
        conversationRelatedObjs.add(groupConversation);

        // User
        for (int i = 1; i <= userCount; i++) {
            Date userDate = DateUtils.addDays(epoch, i);
            User user = new User(
                    (long) i,
                    passwordManager.encodeUserPassword("123"),
                    "my-name",
                    "my-intro",
                    "my-avatar-id",
                    ProfileAccessStrategy.ALL,
                    DEFAULT_USER_PERMISSION_GROUP_ID,
                    userDate,
                    null,
                    userDate,
                    true);
            UserVersion userVersion = new UserVersion(
                    (long) i,
                    userDate,
                    userDate,
                    userDate,
                    userDate,
                    userDate,
                    userDate,
                    userDate,
                    userDate,
                    userDate);
            UserRelationshipGroup relationshipGroup =
                    new UserRelationshipGroup((long) i, 0, "", userDate);
            userRelatedObjs.add(user);
            userRelatedObjs.add(userVersion);
            userRelatedObjs.add(relationshipGroup);
        }

        for (int i =
                targetUserToRequestFriendRequestStart; i <= targetUserToRequestFriendRequestEnd; i++) {
            UserFriendRequest userFriendRequest = new UserFriendRequest(
                    nextId(),
                    "test-request",
                    RequestStatus.PENDING,
                    null,
                    epoch,
                    null,
                    1L,
                    (long) i);
            userRelatedObjs.add(userFriendRequest);
        }

        userRelatedObjs.add(new UserPermissionGroup(1L, Set.of(1L), 10, 10, Map.of(1L, 1, 2L, 1)));

        for (int i =
                targetUserToBeFriendRelationshipStart; i <= targetUserToBeFriendRelationshipEnd; i++) {
            UserRelationship userRelationship1 =
                    new UserRelationship(new UserRelationship.Key(1L, (long) i), null, epoch);
            UserRelationship userRelationship2 =
                    new UserRelationship(new UserRelationship.Key((long) i, 1L), null, epoch);
            UserRelationshipGroupMember relationshipGroupMember1 =
                    new UserRelationshipGroupMember(1L, 0, (long) i, epoch);
            UserRelationshipGroupMember relationshipGroupMember2 =
                    new UserRelationshipGroupMember((long) i, 0, 1L, epoch);
            userRelatedObjs.add(userRelationship1);
            userRelatedObjs.add(userRelationship2);
            userRelatedObjs.add(relationshipGroupMember1);
            userRelatedObjs.add(relationshipGroupMember2);
        }

        // FIXME: Use "subscribeOn(Schedulers.boundedElastic())" for now because of a weired
        // behaviour
        // that TurmsMongoClient#insertAll seems blocking when running in debug mode
        // but it won't block when running in non-debug mode
        // and there is no blocking method after reviewing the workflow of
        // TurmsMongoClient#insertAll
        Scheduler scheduler = Schedulers.boundedElastic();
        Mono<Void> adminMono = adminMongoClient.insertAll(adminRelatedObjs)
                .doOnSubscribe(s -> LOGGER.info("Start faking admin-related data"))
                .doOnError(error -> LOGGER.error("Failed to fake admin-related data", error))
                .doOnSuccess(unused -> LOGGER.info("Admin-related data has been faked"))
                .subscribeOn(scheduler);
        Mono<Void> userMono = userMongoClient.insertAll(userRelatedObjs)
                .doOnSubscribe(s -> LOGGER.info("Start faking user-related data"))
                .doOnError(error -> LOGGER.error("Failed to fake user-related data", error))
                .doOnSuccess(unused -> LOGGER.info("User-related data has been faked"))
                .subscribeOn(scheduler);
        Mono<Void> groupMono = groupMongoClient.insertAll(groupRelatedObjs)
                .doOnSubscribe(s -> LOGGER.info("Start faking group-related data"))
                .doOnError(error -> LOGGER.error("Failed to fake group-related data", error))
                .doOnSuccess(unused -> LOGGER.info("Group-related data has been faked"))
                .subscribeOn(scheduler);
        Mono<Void> conversationMono = conversationMongoClient.insertAll(conversationRelatedObjs)
                .doOnSubscribe(s -> LOGGER.info("Start faking conversation-related data"))
                .doOnError(error -> LOGGER.error("Failed to fake conversation-related data", error))
                .doOnSuccess(unused -> LOGGER.info("Conversation-related data has been faked"))
                .subscribeOn(scheduler);
        Mono<Void> messageMono = messageMongoClient.insertAll(messageRelatedObjs)
                .doOnSubscribe(s -> LOGGER.info("Start faking message-related data"))
                .doOnError(error -> LOGGER.error("Failed to fake message-related data", error))
                .doOnSuccess(unused -> LOGGER.info("Message-related data has been faked"))
                .subscribeOn(scheduler);
        return Mono.whenDelayError(adminMono, userMono, groupMono, conversationMono, messageMono)
                .then()
                .doOnSuccess(ignored -> LOGGER.info("All data has been faked"))
                .doOnError(t -> LOGGER.error("Failed to fake data", t));
    }

    private long nextId() {
        return currentId++;
    }

}