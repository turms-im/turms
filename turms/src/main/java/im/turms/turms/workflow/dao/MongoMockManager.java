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

package im.turms.turms.workflow.dao;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupJoinStrategy;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.GroupUpdateStrategy;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.RequestStatus;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.property.env.service.env.MockProperties;
import im.turms.server.common.util.MapUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.dao.domain.admin.Admin;
import im.turms.turms.workflow.dao.domain.admin.AdminRole;
import im.turms.turms.workflow.dao.domain.conversation.GroupConversation;
import im.turms.turms.workflow.dao.domain.conversation.PrivateConversation;
import im.turms.turms.workflow.dao.domain.group.Group;
import im.turms.turms.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.turms.workflow.dao.domain.group.GroupInvitation;
import im.turms.turms.workflow.dao.domain.group.GroupJoinQuestion;
import im.turms.turms.workflow.dao.domain.group.GroupJoinRequest;
import im.turms.turms.workflow.dao.domain.group.GroupMember;
import im.turms.turms.workflow.dao.domain.group.GroupType;
import im.turms.turms.workflow.dao.domain.group.GroupVersion;
import im.turms.turms.workflow.dao.domain.message.Message;
import im.turms.turms.workflow.dao.domain.user.UserFriendRequest;
import im.turms.turms.workflow.dao.domain.user.UserPermissionGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationship;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.turms.workflow.dao.domain.user.UserVersion;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Log4j2
public final class MongoMockManager {

    private final PasswordManager passwordManager;

    private final TurmsMongoClient adminMongoClient;
    private final TurmsMongoClient userMongoClient;
    private final TurmsMongoClient groupMongoClient;
    private final TurmsMongoClient conversationMongoClient;
    private final TurmsMongoClient messageMongoClient;

    @Getter
    private final boolean isMockEnabled;
    private final int userNumber;

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
    private final boolean clearAllCollectionsBeforeMocking;

    private long currentId = 1L;

    public MongoMockManager(MockProperties properties,
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

        isMockEnabled = properties.isEnabled();
        clearAllCollectionsBeforeMocking = properties.isClearAllCollectionsBeforeMocking();
        userNumber = properties.getUserNumber();
        int step = userNumber / 10;

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

    public Mono<Void> mockData() {
        log.info("Start mocking...");

        final int adminCount = 10;

        List<Object> adminRelatedObjs = new LinkedList<>();
        List<Object> userRelatedObjs = new LinkedList<>();
        List<Object> groupRelatedObjs = new LinkedList<>();
        List<Object> conversationRelatedObjs = new LinkedList<>();
        List<Object> messageRelatedObjs = new LinkedList<>();

        // Admin
        final Date now = new Date();
        final long guestRoleId = 2L;
        Admin guest = new Admin(
                "guest",
                passwordManager.encodeAdminPassword("guest"),
                "guest",
                guestRoleId,
                now);
        adminRelatedObjs.add(guest);
        for (int i = 1; i <= adminCount; i++) {
            Admin admin = new Admin(
                    "account" + i,
                    passwordManager.encodeAdminPassword("123"),
                    "my-name",
                    1L,
                    DateUtils.addDays(now, -i));
            adminRelatedObjs.add(admin);
        }
        AdminRole adminRole = new AdminRole(
                1L,
                "ADMIN",
                AdminPermission.ALL,
                0);
        AdminRole guestRole = new AdminRole(
                guestRoleId,
                "GUEST",
                Sets.union(AdminPermission.ALL_QUERY, AdminPermission.ALL_CREATE),
                0);
        adminRelatedObjs.add(adminRole);
        adminRelatedObjs.add(guestRole);

        // Group
        Group group = new Group(
                1L,
                DaoConstant.DEFAULT_GROUP_TYPE_ID,
                1L,
                1L,
                "Turms Developers Group",
                "This is a group for the developers who are interested in Turms",
                "nope",
                0,
                now,
                null,
                null,
                true);
        groupRelatedObjs.add(group);
        GroupVersion groupVersion = new GroupVersion(1L, now, now, now, now, now, now);
        groupRelatedObjs.add(groupVersion);
        for (int i = targetUserToBlockInGroupStart; i <= targetUserToBlockInGroupEnd; i++) {
            GroupBlockedUser groupBlockedUser = new GroupBlockedUser(
                    1L,
                    (long) i,
                    now,
                    1L);
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
                    DateUtils.addDays(now, -i),
                    null);
            groupRelatedObjs.add(groupInvitation);
        }
        GroupJoinQuestion groupJoinQuestion = new GroupJoinQuestion(
                nextId(),
                1L,
                "test-question",
                Set.of("a", "b", "c"),
                20);
        groupRelatedObjs.add(groupJoinQuestion);
        for (int i = targetUserForGroupJoinRequestStart; i <= targetUserForGroupJoinRequestEnd; i++) {
            GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                    nextId(),
                    "test-content",
                    RequestStatus.PENDING,
                    now,
                    null,
                    1L,
                    (long) i,
                    null);
            groupRelatedObjs.add(groupJoinRequest);
        }
        Set<Long> groupMemberIds = new HashSet<>(MapUtil.getCapability(targetUserToBeGroupMemberEnd - targetUserToBeGroupMemberStart));
        for (int i = targetUserToBeGroupMemberStart; i <= targetUserToBeGroupMemberEnd; i++) {
            long groupMemberId = i;
            groupMemberIds.add(groupMemberId);
            GroupMember groupMember = new GroupMember(
                    1L,
                    groupMemberId,
                    "test-name",
                    i == 1 ? GroupMemberRole.OWNER : GroupMemberRole.MEMBER,
                    now,
                    i > userNumber / 10 / 2 ? new Date(9999999999999L) : null);
            groupRelatedObjs.add(groupMember);
        }

        groupRelatedObjs.add(new GroupType(1L,
                "test",
                1000,
                GroupInvitationStrategy.ALL,
                GroupJoinStrategy.ACCEPT_ANY_REQUEST,
                GroupUpdateStrategy.OWNER_MANAGER,
                GroupUpdateStrategy.OWNER_MANAGER_MEMBER,
                true,
                true,
                true,
                true));

        // Message
        long senderId = 1L;
        Set<Long> targetIds = new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            long id = nextId();
            long targetId = (long) 2 + (i % 9);
            targetIds.add(targetId);
            Message privateMessage = new Message(
                    id,
                    false,
                    false,
                    DateUtils.addHours(now, -i),
                    null,
                    null,
                    null,
                    "private-message-text" + RandomStringUtils.randomAlphanumeric(16),
                    senderId,
                    targetId,
                    null,
                    30,
                    null);
            id = nextId();
            Message groupMessage = new Message(
                    id,
                    true,
                    false,
                    now,
                    null,
                    null,
                    null,
                    "group-message-text" + RandomStringUtils.randomAlphanumeric(16),
                    1L,
                    1L,
                    null,
                    30,
                    null);
            messageRelatedObjs.add(privateMessage);
            messageRelatedObjs.add(groupMessage);
        }

        // Conversation
        for (Long targetId : targetIds) {
            PrivateConversation privateConversation = new PrivateConversation(
                    new PrivateConversation.Key(senderId, targetId),
                    now);
            conversationRelatedObjs.add(privateConversation);
        }
        GroupConversation groupConversation = new GroupConversation(1L, Maps.asMap(groupMemberIds, id -> now));
        conversationRelatedObjs.add(groupConversation);

        // User
        for (int i = 1; i <= userNumber; i++) {
            Date userDate = DateUtils.addDays(now, -i);
            User user = new User(
                    (long) i,
                    passwordManager.encodeUserPassword("123"),
                    "user-name",
                    "user-intro",
                    ProfileAccessStrategy.ALL,
                    DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID,
                    userDate,
                    null,
                    true,
                    userDate);
            UserVersion userVersion = new UserVersion(
                    (long) i, userDate, userDate, userDate, userDate,
                    userDate, userDate, userDate, userDate, userDate);
            UserRelationshipGroup relationshipGroup = new UserRelationshipGroup((long) i, 0, "", userDate);
            userRelatedObjs.add(user);
            userRelatedObjs.add(userVersion);
            userRelatedObjs.add(relationshipGroup);
        }

        for (int i = targetUserToRequestFriendRequestStart; i <= targetUserToRequestFriendRequestEnd; i++) {
            UserFriendRequest userFriendRequest = new UserFriendRequest(
                    nextId(),
                    "test-request",
                    RequestStatus.PENDING,
                    null,
                    now,
                    null,
                    1L,
                    (long) i);
            userRelatedObjs.add(userFriendRequest);
        }

        userRelatedObjs.add(new UserPermissionGroup(1L, Set.of(1L), 10, 10, Map.of(1L, 1, 2L, 1)));

        for (int i = targetUserToBeFriendRelationshipStart; i <= targetUserToBeFriendRelationshipEnd; i++) {
            UserRelationship userRelationship1 = new UserRelationship(
                    new UserRelationship.Key(1L, (long) i),
                    null,
                    now);
            UserRelationship userRelationship2 = new UserRelationship(
                    new UserRelationship.Key((long) i, 1L),
                    null,
                    now);
            UserRelationshipGroupMember relationshipGroupMember1 = new UserRelationshipGroupMember(
                    1L, 0, (long) i, now);
            UserRelationshipGroupMember relationshipGroupMember2 = new UserRelationshipGroupMember(
                    (long) i, 0, 1L, now);
            userRelatedObjs.add(userRelationship1);
            userRelatedObjs.add(userRelationship2);
            userRelatedObjs.add(relationshipGroupMember1);
            userRelatedObjs.add(relationshipGroupMember2);
        }

        // FIXME: Use "subscribeOn(Schedulers.boundedElastic())" for now because of a weired behaviour
        // that TurmsMongoClient#insertAll seems blocking when running in debug mode
        // but it won't block when running in non-debug mode
        // and there is no blocking method after reviewing the workflow of TurmsMongoClient#insertAll
        Mono<Void> adminMono = adminMongoClient.insertAll(adminRelatedObjs)
                .doOnSubscribe(s -> log.info("Start mocking admin-related data"))
                .doOnError(error -> log.error("Failed to mock admin-related data", error))
                .doOnSuccess(unused -> log.info("Admin-related data has been mocked"))
                .subscribeOn(Schedulers.boundedElastic());
        Mono<Void> userMono = userMongoClient.insertAll(userRelatedObjs)
                .doOnSubscribe(s -> log.info("Start mocking user-related data"))
                .doOnError(error -> log.error("Failed to mock user-related data", error))
                .doOnSuccess(unused -> log.info("User-related data has been mocked"))
                .subscribeOn(Schedulers.boundedElastic());
        Mono<Void> groupMono = groupMongoClient.insertAll(groupRelatedObjs)
                .doOnSubscribe(s -> log.info("Start mocking group-related data"))
                .doOnError(error -> log.error("Failed to mock group-related data", error))
                .doOnSuccess(unused -> log.info("Group-related data has been mocked"))
                .subscribeOn(Schedulers.boundedElastic());
        Mono<Void> conversationMono = conversationMongoClient.insertAll(conversationRelatedObjs)
                .doOnSubscribe(s -> log.info("Start mocking conversation-related data"))
                .doOnError(error -> log.error("Failed to mock conversation-related data", error))
                .doOnSuccess(unused -> log.info("Conversation-related data has been mocked"))
                .subscribeOn(Schedulers.boundedElastic());
        Mono<Void> messageMono = messageMongoClient.insertAll(messageRelatedObjs)
                .doOnSubscribe(s -> log.info("Start mocking message-related data"))
                .doOnError(error -> log.error("Failed to mock message-related data", error))
                .doOnSuccess(unused -> log.info("Message-related data has been mocked"))
                .subscribeOn(Schedulers.boundedElastic());
        return Mono.when(adminMono, userMono, groupMono, conversationMono, messageMono)
                .then()
                .doOnSuccess(ignored -> log.info("All data has been mocked"))
                .doOnError(t -> log.error("Failed to mock data", t));
    }

    private long nextId() {
        return currentId++;
    }

}