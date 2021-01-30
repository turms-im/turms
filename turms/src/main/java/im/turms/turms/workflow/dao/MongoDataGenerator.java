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
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.RequestStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.context.ApplicationContext;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.MockProperties;
import im.turms.server.common.util.MapUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.dao.domain.admin.Admin;
import im.turms.turms.workflow.dao.domain.admin.AdminRole;
import im.turms.turms.workflow.dao.domain.conversation.GroupConversation;
import im.turms.turms.workflow.dao.domain.conversation.PrivateConversation;
import im.turms.turms.workflow.dao.domain.group.*;
import im.turms.turms.workflow.dao.domain.message.Message;
import im.turms.turms.workflow.dao.domain.user.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import java.time.Duration;
import java.util.*;

/**
 * TODO: Extract the mocking feature as an independent project
 *
 * @author James Chen
 */
@Log4j2
@Component
public class MongoDataGenerator {

    private static final List<String> ADMIN_COLLECTIONS = List.of(
            Admin.COLLECTION_NAME,
            AdminRole.COLLECTION_NAME);

    private static final List<String> GROUP_COLLECTIONS = List.of(
            Group.COLLECTION_NAME,
            GroupBlockedUser.COLLECTION_NAME,
            GroupInvitation.COLLECTION_NAME,
            GroupJoinQuestion.COLLECTION_NAME,
            GroupJoinRequest.COLLECTION_NAME,
            GroupMember.COLLECTION_NAME,
            GroupType.COLLECTION_NAME,
            GroupVersion.COLLECTION_NAME);

    private static final List<String> CONVERSATION_COLLECTIONS = List.of(
            PrivateConversation.COLLECTION_NAME,
            GroupConversation.COLLECTION_NAME);

    private static final List<String> MESSAGE_COLLECTIONS = List.of(
            Message.COLLECTION_NAME);

    private static final List<String> USER_COLLECTIONS = List.of(
            User.COLLECTION_NAME,
            UserFriendRequest.COLLECTION_NAME,
            UserPermissionGroup.COLLECTION_NAME,
            UserRelationship.COLLECTION_NAME,
            UserRelationshipGroup.COLLECTION_NAME,
            UserRelationshipGroupMember.COLLECTION_NAME,
            UserVersion.COLLECTION_NAME);

    public final boolean isMockEnabled;
    public final int userNumber;
    public final int step;

    public final int targetUserToBeGroupMemberStart;
    public final int targetUserToBeGroupMemberEnd;
    public final int targetUserForGroupJoinRequestStart;
    public final int targetUserForGroupJoinRequestEnd;
    public final int targetUserForGroupInvitationStart;
    public final int targetUserForGroupInvitationEnd;
    public final int targetUserToBlockInGroupStart;
    public final int targetUserToBlockInGroupEnd;

    public final int targetUserToRequestFriendRequestStart;
    public final int targetUserToRequestFriendRequestEnd;
    public final int targetUserToBeFriendRelationshipStart;
    public final int targetUserToBeFriendRelationshipEnd;

    private final Node node;
    private final ReactiveMongoTemplate adminMongoTemplate;
    private final ReactiveMongoTemplate userMongoTemplate;
    private final ReactiveMongoTemplate groupMongoTemplate;
    private final ReactiveMongoTemplate conversationMongoTemplate;
    private final ReactiveMongoTemplate messageMongoTemplate;
    private final PasswordManager passwordUtil;
    private final ApplicationContext context;
    private final boolean clearAllCollectionsBeforeMocking;

    public MongoDataGenerator(
            Node node,
            ReactiveMongoTemplate adminMongoTemplate,
            ReactiveMongoTemplate userMongoTemplate,
            ReactiveMongoTemplate groupMongoTemplate,
            ReactiveMongoTemplate conversationMongoTemplate,
            ReactiveMongoTemplate messageMongoTemplate,
            PasswordManager passwordUtil,
            TurmsPropertiesManager turmsPropertiesManager,
            ApplicationContext context) {
        this.node = node;
        this.adminMongoTemplate = adminMongoTemplate;
        this.userMongoTemplate = userMongoTemplate;
        this.groupMongoTemplate = groupMongoTemplate;
        this.conversationMongoTemplate = conversationMongoTemplate;
        this.messageMongoTemplate = messageMongoTemplate;
        this.passwordUtil = passwordUtil;
        this.context = context;

        MockProperties mockProperties = turmsPropertiesManager.getLocalProperties().getService().getMock();
        isMockEnabled = mockProperties.isEnabled();
        clearAllCollectionsBeforeMocking = mockProperties.isClearAllCollectionsBeforeMocking();
        userNumber = mockProperties.getUserNumber();
        step = userNumber / 10;

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

    @EventListener(classes = ContextRefreshedEvent.class)
    public void createCollectionsIfNotExist() {
        if (!context.isProduction() && clearAllCollectionsBeforeMocking) {
            log.info("Start clearing collections...");
            clearAllCollections().block(Duration.ofSeconds(120));
            log.info("All collections are cleared");
        }
        log.info("Start creating collections...");
        Mono.when(
                createCollectionIfNotExist(Admin.class, null),
                createCollectionIfNotExist(AdminRole.class, null),

                createCollectionIfNotExist(Group.class, null),
                createCollectionIfNotExist(GroupBlockedUser.class, null),
                createCollectionIfNotExist(GroupInvitation.class, null),
                createCollectionIfNotExist(GroupJoinQuestion.class, null),
                createCollectionIfNotExist(GroupMember.class, null),
                createCollectionIfNotExist(GroupType.class, null),
                createCollectionIfNotExist(GroupVersion.class, null),

                createCollectionIfNotExist(PrivateConversation.class, null),
                createCollectionIfNotExist(GroupConversation.class, null),

                createCollectionIfNotExist(Message.class, null),

                createCollectionIfNotExist(User.class, null),
                createCollectionIfNotExist(UserFriendRequest.class, null),
                createCollectionIfNotExist(UserPermissionGroup.class, null),
                createCollectionIfNotExist(UserRelationship.class, null),
                createCollectionIfNotExist(UserRelationshipGroup.class, null),
                createCollectionIfNotExist(UserRelationshipGroupMember.class, null),
                createCollectionIfNotExist(UserVersion.class, null))
                .doOnError(t -> log.error("Failed to create collections", t))
                .doOnSuccess(ignored -> {
                    log.info("All collections are created");
                    if (!context.isProduction() && isMockEnabled) {
                        try {
                            mockData().block(Duration.ofSeconds(120));
                        } catch (Exception e) {
                            log.error("Failed to mock data", e);
                        }
                    }
                })
                .block(Duration.ofSeconds(120));
    }

    private Mono<Void> clearAllCollections() {
        Query queryAll = new Query();
        List<Flux<?>> fluxes = new ArrayList<>(5);
        fluxes.add(adminMongoTemplate.getCollectionNames()
                .flatMap(name -> {
                    if (ADMIN_COLLECTIONS.contains(name)) {
                        Query query = queryAll;
                        if (Admin.COLLECTION_NAME.equals(name)) {
                            query = new Query()
                                    .addCriteria(Criteria.where(Admin.Fields.ROLE_ID).ne(DaoConstant.ADMIN_ROLE_ROOT_ID));
                        }
                        return adminMongoTemplate.remove(query, name);
                    } else {
                        return Mono.empty();
                    }
                }));
        fluxes.add(userMongoTemplate.getCollectionNames()
                .flatMap(name -> USER_COLLECTIONS.contains(name)
                        ? userMongoTemplate.remove(queryAll, name)
                        : Mono.empty()));
        fluxes.add(groupMongoTemplate.getCollectionNames()
                .flatMap(name -> GROUP_COLLECTIONS.contains(name)
                        ? groupMongoTemplate.remove(queryAll, name)
                        : Mono.empty()));
        fluxes.add(conversationMongoTemplate.getCollectionNames()
                .flatMap(name -> CONVERSATION_COLLECTIONS.contains(name)
                        ? conversationMongoTemplate.remove(queryAll, name)
                        : Mono.empty()));
        fluxes.add(messageMongoTemplate.getCollectionNames()
                .flatMap(name -> MESSAGE_COLLECTIONS.contains(name)
                        ? messageMongoTemplate.remove(queryAll, name)
                        : Mono.empty()));
        return Mono.when(fluxes);
    }

    /**
     * Note: Better not to remove all mock data after turms closed
     */
    private Mono<Void> mockData() {
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
                passwordUtil.encodeAdminPassword("guest"),
                "guest",
                guestRoleId,
                now);
        adminRelatedObjs.add(guest);
        for (int i = 1; i <= adminCount; i++) {
            Admin admin = new Admin(
                    "account" + i,
                    passwordUtil.encodeAdminPassword("123"),
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
                    node.nextRandomId(ServiceType.GROUP_INVITATION),
                    1L,
                    1L,
                    (long) i,
                    "test-content",
                    RequestStatus.PENDING,
                    DateUtils.addDays(now, -i),
                    null,
                    null);
            groupRelatedObjs.add(groupInvitation);
        }
        GroupJoinQuestion groupJoinQuestion = new GroupJoinQuestion(
                node.nextRandomId(ServiceType.GROUP_JOIN_QUESTION),
                1L,
                "test-question",
                Set.of("a", "b", "c"),
                20);
        groupRelatedObjs.add(groupJoinQuestion);
        for (int i = targetUserForGroupJoinRequestStart; i <= targetUserForGroupJoinRequestEnd; i++) {
            GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                    node.nextRandomId(ServiceType.GROUP_JOIN_REQUEST),
                    "test-content",
                    RequestStatus.PENDING,
                    now,
                    null,
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

        // Message
        long senderId = 1L;
        Set<Long> targetIds = new HashSet<>();
        for (int i = 1; i <= 100; i++) {
            long id = node.nextRandomId(ServiceType.MESSAGE);
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
            id = node.nextRandomId(ServiceType.MESSAGE);
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
                    passwordUtil.encodeUserPassword("123"),
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
                    node.nextRandomId(ServiceType.USER_FRIEND_REQUEST),
                    "test-request",
                    RequestStatus.PENDING,
                    null,
                    now,
                    null,
                    null,
                    1L,
                    (long) i);
            userRelatedObjs.add(userFriendRequest);
        }
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

        return Mono.when(
                adminMongoTemplate.insertAll(adminRelatedObjs)
                        .doOnError(error -> log.error("Failed to mock admin-related data", error))
                        .doOnComplete(() -> log.info("Admin-related data has been mocked")),
                userMongoTemplate.insertAll(userRelatedObjs)
                        .doOnError(error -> log.error("Failed to mock user-related data", error))
                        .doOnComplete(() -> log.info("User-related data has been mocked")),
                groupMongoTemplate.insertAll(groupRelatedObjs)
                        .doOnError(error -> log.error("Failed to mock group-related data", error))
                        .doOnComplete(() -> log.info("Group-related data has been mocked")),
                conversationMongoTemplate.insertAll(conversationRelatedObjs)
                        .doOnError(error -> log.error("Failed to mock conversation-related data", error))
                        .doOnComplete(() -> log.info("Conversation-related data has been mocked")),
                messageMongoTemplate.insertAll(messageRelatedObjs)
                        .doOnError(error -> log.error("Failed to mock message-related data", error))
                        .doOnComplete(() -> log.info("Message-related data has been mocked")))
                .doOnSuccess(ignored -> log.info("All data has been mocked"));
    }

    private <T> Mono<Boolean> createCollectionIfNotExist(
            Class<T> clazz,
            @Nullable CollectionOptions options) {
        ReactiveMongoTemplate mongoTemplate;
        if (clazz == Admin.class || clazz == AdminRole.class) {
            mongoTemplate = adminMongoTemplate;
        } else if (clazz == User.class || clazz == UserFriendRequest.class
                || clazz == UserPermissionGroup.class || clazz == UserRelationship.class
                || clazz == UserRelationshipGroup.class || clazz == UserRelationshipGroupMember.class || clazz == UserVersion.class) {
            mongoTemplate = userMongoTemplate;
        } else if (clazz == Group.class || clazz == GroupBlockedUser.class || clazz == GroupInvitation.class
                || clazz == GroupJoinQuestion.class || clazz == GroupJoinRequest.class || clazz == GroupMember.class
                || clazz == GroupType.class || clazz == GroupVersion.class) {
            mongoTemplate = groupMongoTemplate;
        } else if (clazz == PrivateConversation.class || clazz == GroupConversation.class) {
            mongoTemplate = conversationMongoTemplate;
        } else if (clazz == Message.class) {
            mongoTemplate = messageMongoTemplate;
        } else {
            return Mono.error(new IllegalArgumentException("Unknown collection=" + clazz.getName()));
        }
        return mongoTemplate.collectionExists(clazz)
                .flatMap(exists -> exists != null && !exists
                        ? mongoTemplate.createCollection(clazz, options).thenReturn(true)
                        : Mono.just(false));
    }

}
