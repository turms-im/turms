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

package im.turms.turms.workflow.service.impl.user;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.dao.util.OperationResultUtil;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.mongo.IMongoDataGenerator;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.ServicePermission;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.MetricsConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidProfileAccess;
import im.turms.turms.workflow.service.impl.conversation.ConversationService;
import im.turms.turms.workflow.service.impl.group.GroupMemberService;
import im.turms.turms.workflow.service.impl.statistics.MetricsService;
import im.turms.turms.workflow.service.impl.user.onlineuser.SessionService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipGroupService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@Component
@DependsOn(IMongoDataGenerator.BEAN_NAME)
public class UserService {

    private final GroupMemberService groupMemberService;
    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final UserVersionService userVersionService;
    private final SessionService sessionService;
    private final ConversationService conversationService;

    private final Node node;
    private final PasswordManager passwordManager;
    private final TurmsMongoClient mongoClient;

    private final Counter registeredUsersCounter;
    private final Counter deletedUsersCounter;

    public UserService(
            Node node,
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient,
            PasswordManager passwordManager,
            UserRelationshipService userRelationshipService,
            GroupMemberService groupMemberService,
            UserVersionService userVersionService,
            UserRelationshipGroupService userRelationshipGroupService,
            SessionService sessionService,
            ConversationService conversationService,
            MetricsService metricsService) {
        this.node = node;
        this.mongoClient = mongoClient;
        this.passwordManager = passwordManager;

        this.userRelationshipService = userRelationshipService;
        this.groupMemberService = groupMemberService;
        this.userVersionService = userVersionService;
        this.userRelationshipGroupService = userRelationshipGroupService;
        this.sessionService = sessionService;
        this.conversationService = conversationService;

        registeredUsersCounter = metricsService.getRegistry().counter(MetricsConstant.REGISTERED_USERS_COUNTER_NAME);
        deletedUsersCounter = metricsService.getRegistry().counter(MetricsConstant.DELETED_USERS_COUNTER_NAME);
    }

    public Mono<ServicePermission> isAllowedToSendMessageToTarget(
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long requesterId,
            @NotNull Long targetId) {
        try {
            AssertUtil.notNull(isGroupMessage, "isGroupMessage");
            AssertUtil.notNull(isSystemMessage, "isSystemMessage");
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(targetId, "targetId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (isSystemMessage) {
            return Mono.just(ServicePermission.OK);
        }
        if (isGroupMessage) {
            return groupMemberService.isAllowedToSendMessage(targetId, requesterId)
                    .map(ServicePermission::get);
        } else {
            if (requesterId.equals(targetId)) {
                return node.getSharedProperties().getService().getMessage().isAllowSendingMessagesToOneself()
                        ? Mono.just(ServicePermission.OK)
                        : Mono.just(ServicePermission.get(TurmsStatusCode.SENDING_MESSAGES_TO_ONESELF_IS_DISABLED));
            } else {
                if (node.getSharedProperties().getService().getMessage().isAllowSendingMessagesToStranger()) {
                    if (node.getSharedProperties().getService().getMessage().isCheckIfTargetActiveAndNotDeleted()) {
                        return isActiveAndNotDeleted(targetId)
                                .flatMap(isActiveAndNotDeleted -> {
                                    if (!isActiveAndNotDeleted) {
                                        return Mono.just(ServicePermission.get(TurmsStatusCode.MESSAGE_RECIPIENT_NOT_ACTIVE));
                                    }
                                    return userRelationshipService.hasNoRelationshipOrNotBlocked(targetId, requesterId)
                                            .map(isNotBlocked -> isNotBlocked
                                                    ? ServicePermission.OK
                                                    : ServicePermission.get(TurmsStatusCode.PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED));
                                });
                    }
                    return userRelationshipService.hasNoRelationshipOrNotBlocked(targetId, requesterId)
                            .map(isNotBlocked -> isNotBlocked
                                    ? ServicePermission.OK
                                    : ServicePermission.get(TurmsStatusCode.PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED));
                } else {
                    return userRelationshipService.hasRelationshipAndNotBlocked(targetId, requesterId)
                            .map(isRelatedAndNotBlocked -> isRelatedAndNotBlocked
                                    ? ServicePermission.OK
                                    : ServicePermission.get(TurmsStatusCode.MESSAGE_SENDER_NOT_IN_CONTACTS_OR_BLOCKED));
                }
            }
        }
    }

    public Mono<User> addUser(
            @Nullable Long id,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccess,
            @Nullable Long permissionGroupId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive) {
        try {
            DomainConstraintUtil.validProfileAccess(profileAccess);
            AssertUtil.pastOrPresent(registrationDate, "registrationDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        id = id != null ? id : node.nextRandomId(ServiceType.USER);
        String password = rawPassword != null
                ? passwordManager.encodeUserPassword(rawPassword)
                : null;
        name = name != null ? name : "";
        intro = intro != null ? intro : "";
        profileAccess = profileAccess != null
                ? profileAccess
                : ProfileAccessStrategy.ALL;
        permissionGroupId = permissionGroupId != null
                ? permissionGroupId
                : DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID;
        isActive = isActive != null
                ? isActive
                : node.getSharedProperties().getService().getUser().isActivateUserWhenAdded();
        Date date = registrationDate != null
                ? registrationDate
                : now;
        User user = new User(
                id,
                password,
                name,
                intro,
                profileAccess,
                permissionGroupId,
                date,
                null,
                isActive,
                now);
        Long finalId = id;
        return mongoClient.inTransaction(session -> mongoClient.upsert(session, user)
                .then(userRelationshipGroupService.createRelationshipGroup(finalId, 0, "", now, session))
                .then(userVersionService.upsertEmptyUserVersion(user.getId(), date, session).onErrorResume(t -> Mono.empty()))
                .thenReturn(user))
                .retryWhen(DaoConstant.TRANSACTION_RETRY)
                .doOnSuccess(ignored -> registeredUsersCounter.increment());
    }

    public Mono<ServicePermission> isAllowToQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long targetUserId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(targetUserId, "targetUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, targetUserId)
                .eq(User.Fields.DELETION_DATE, null);
        QueryOptions options = QueryOptions.newBuilder()
                .include(User.Fields.PROFILE_ACCESS);
        return mongoClient.findOne(User.class, filter, options)
                .flatMap(user -> {
                    switch (user.getProfileAccess()) {
                        case ALL:
                            return Mono.just(ServicePermission.OK);
                        case FRIENDS:
                            return userRelationshipService.hasRelationshipAndNotBlocked(targetUserId, requesterId)
                                    .map(isRelatedAndAllowed -> isRelatedAndAllowed
                                            ? ServicePermission.OK
                                            : ServicePermission.get(TurmsStatusCode.PROFILE_REQUESTER_NOT_IN_CONTACTS_OR_BLOCKED));
                        case ALL_EXCEPT_BLOCKED_USERS:
                            return userRelationshipService.hasNoRelationshipOrNotBlocked(targetUserId, requesterId)
                                    .map(isNotBlocked -> isNotBlocked
                                            ? ServicePermission.OK
                                            : ServicePermission.get(TurmsStatusCode.PROFILE_REQUESTER_HAS_BEEN_BLOCKED));
                        case UNRECOGNIZED:
                        default:
                            return Mono.error(TurmsBusinessException
                                    .get(TurmsStatusCode.SERVER_INTERNAL_ERROR, "Unexpected value " + user.getProfileAccess()));
                    }
                })
                .defaultIfEmpty(ServicePermission.get(TurmsStatusCode.USER_PROFILE_NOT_FOUND));
    }

    public Mono<User> authAndQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long userId,
            boolean queryDeletedRecords) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return isAllowToQueryUserProfile(requesterId, userId)
                .flatMap(permission -> {
                    TurmsStatusCode code = permission.getCode();
                    if (code != TurmsStatusCode.OK) {
                        return Mono.error(TurmsBusinessException.get(code, permission.getReason()));
                    }
                    return queryUsersProfiles(Set.of(userId), queryDeletedRecords).singleOrEmpty();
                });
    }

    public Flux<User> queryUsersProfiles(@NotEmpty Set<Long> userIds, boolean queryDeletedRecords) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, userIds)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        QueryOptions options = QueryOptions.newBuilder()
                .include(DaoConstant.ID_FIELD_NAME,
                        User.Fields.NAME,
                        User.Fields.INTRO,
                        User.Fields.REGISTRATION_DATE,
                        User.Fields.PROFILE_ACCESS,
                        User.Fields.PERMISSION_GROUP_ID,
                        User.Fields.IS_ACTIVE);
        return mongoClient.findMany(User.class, filter, options);
    }

    public Mono<Long> queryUserPermissionGroupId(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, userId);
        QueryOptions options = QueryOptions.newBuilder()
                .include(User.Fields.PERMISSION_GROUP_ID);
        return mongoClient.findOne(User.class, filter, options)
                .map(User::getPermissionGroupId);
    }

    public Mono<DeleteResult> deleteUsers(
            @NotEmpty Set<Long> userIds,
            @Nullable Boolean deleteLogically) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, userIds);
        Mono<DeleteResult> deleteOrUpdateMono;
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties().getService().getUser().isDeleteUserLogically();
        }
        if (deleteLogically) {
            Date now = new Date();
            Update update = Update.newBuilder()
                    .set(User.Fields.DELETION_DATE, now)
                    .set(User.Fields.LAST_UPDATED_DATE, now);
            deleteOrUpdateMono = mongoClient.updateMany(User.class, filter, update)
                    .map(OperationResultUtil::update2delete);
        } else {
            deleteOrUpdateMono = mongoClient.inTransaction(session -> mongoClient.deleteMany(session, User.class, filter)
                    .flatMap(result -> {
                        long count = result.getDeletedCount();
                        if (count > 0) {
                            deletedUsersCounter.increment(count);
                        }
                        return userRelationshipService.deleteAllRelationships(userIds, session, false)
                                .then(userRelationshipGroupService.deleteAllRelationshipGroups(userIds, session, false))
                                .then(conversationService.deletePrivateConversations(userIds, session))
                                .then(userVersionService.delete(userIds, session).onErrorResume(t -> Mono.empty()))
                                .thenReturn(result);
                    }))
                    .retryWhen(DaoConstant.TRANSACTION_RETRY);
        }
        return deleteOrUpdateMono
                .doOnNext(ignored -> sessionService.disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED).subscribe());
    }

    public Mono<Boolean> userExists(@NotNull Long userId, boolean queryDeletedRecords) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter
                .newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, userId)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        return mongoClient.exists(User.class, filter);
    }

    public Mono<Void> updateUser(
            @NotNull Long userId,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long permissionGroupId,
            @Nullable Boolean isActive,
            @Nullable @PastOrPresent Date registrationDate) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return updateUsers(Collections.singleton(userId),
                rawPassword,
                name,
                intro,
                profileAccessStrategy,
                permissionGroupId,
                registrationDate,
                isActive)
                .flatMap(result -> result.getMatchedCount() > 0
                        ? Mono.empty()
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UPDATE_INFO_OF_NON_EXISTING_USER)));
    }

    public Flux<User> queryUsers(
            @Nullable Collection<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean queryDeletedRecords) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, userIds)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, registrationDateRange)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, deletionDateRange)
                .eqIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(User.class, filter, options);
    }

    public Mono<Long> countRegisteredUsers(@Nullable DateRange dateRange, boolean queryDeletedRecords) {
        Filter filter = Filter.newBuilder()
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, dateRange)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        return mongoClient.count(User.class, filter);
    }

    public Mono<Long> countDeletedUsers(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder()
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, dateRange);
        return mongoClient.count(User.class, filter);
    }

    public Mono<Long> countUsers(boolean queryDeletedRecords) {
        Filter filter = Filter
                .newBuilder()
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        return mongoClient.count(User.class, filter);
    }

    public Mono<Long> countUsers(
            @Nullable Set<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, userIds)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, registrationDateRange)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, deletionDateRange)
                .eqIfNotNull(User.Fields.IS_ACTIVE, isActive);
        return mongoClient.count(User.class, filter);
    }

    public Mono<UpdateResult> updateUsers(
            @NotEmpty Set<Long> userIds,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long permissionGroupId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
            DomainConstraintUtil.validProfileAccess(profileAccessStrategy);
            AssertUtil.pastOrPresent(registrationDate, "registrationDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(rawPassword,
                name,
                intro,
                profileAccessStrategy,
                registrationDate,
                isActive)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        String password = null;
        if (rawPassword != null && !rawPassword.isEmpty()) {
            password = passwordManager.encodeUserPassword(rawPassword);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, userIds);
        Update update = Update.newBuilder()
                .setIfNotNull(User.Fields.PASSWORD, password)
                .setIfNotNull(User.Fields.NAME, name)
                .setIfNotNull(User.Fields.INTRO, intro)
                .setIfNotNull(User.Fields.PROFILE_ACCESS, profileAccessStrategy)
                .setIfNotNull(User.Fields.PERMISSION_GROUP_ID, permissionGroupId)
                .setIfNotNull(User.Fields.REGISTRATION_DATE, registrationDate)
                .setIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .setIfNotNull(User.Fields.LAST_UPDATED_DATE, new Date());
        return mongoClient.updateMany(User.class, filter, update)
                .flatMap(result -> result.getModifiedCount() > 0
                        ? Mono.just(sessionService.disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED))
                        .onErrorResume(t -> Mono.empty()).thenReturn(result)
                        : Mono.just(result));
    }

    private Mono<Boolean> isActiveAndNotDeleted(@NotNull Long userId) {
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, userId)
                .eq(User.Fields.IS_ACTIVE, true)
                .eq(User.Fields.DELETION_DATE, null);
        return mongoClient.exists(User.class, filter);
    }

}