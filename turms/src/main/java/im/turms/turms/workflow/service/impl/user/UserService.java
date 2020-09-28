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

import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.ProfileAccessStrategy;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.manager.PasswordManager;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.MetricsConstant;
import im.turms.turms.constraint.ProfileAccessConstraint;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.service.impl.group.GroupMemberService;
import im.turms.turms.workflow.service.impl.statistics.MetricsService;
import im.turms.turms.workflow.service.impl.user.onlineuser.SessionService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipGroupService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipService;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.*;

/**
 * @author James Chen
 */
@Component
@Validated
public class UserService {

    private final GroupMemberService groupMemberService;
    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final UserVersionService userVersionService;
    private final SessionService sessionService;
    private final Node node;
    private final PasswordManager passwordManager;
    private final ReactiveMongoTemplate mongoTemplate;

    private final Counter registeredUsersCounter;
    private final Counter deletedUsersCounter;

    public UserService(
            Node node,
            @Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            PasswordManager passwordManager,
            UserRelationshipService userRelationshipService,
            @Lazy GroupMemberService groupMemberService,
            UserVersionService userVersionService,
            UserRelationshipGroupService userRelationshipGroupService,
            SessionService sessionService,
            MetricsService metricsService) {
        this.node = node;
        this.mongoTemplate = mongoTemplate;
        this.passwordManager = passwordManager;
        this.userRelationshipService = userRelationshipService;
        this.groupMemberService = groupMemberService;
        this.userVersionService = userVersionService;
        this.userRelationshipGroupService = userRelationshipGroupService;
        this.sessionService = sessionService;

        registeredUsersCounter = metricsService.getRegistry().counter(MetricsConstant.REGISTERED_USERS_COUNTER_NAME);
        deletedUsersCounter = metricsService.getRegistry().counter(MetricsConstant.DELETED_USERS_COUNTER_NAME);
    }

    public Mono<Boolean> isActiveAndNotDeleted(@NotNull Long userId) {
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(userId))
                .addCriteria(Criteria.where(User.Fields.IS_ACTIVE).is(true))
                .addCriteria(Criteria.where(User.Fields.DELETION_DATE).is(null));
        return mongoTemplate.exists(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Boolean> isAllowedToSendMessageToTarget(
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long requesterId,
            @NotNull Long targetId) {
        if (isSystemMessage) {
            return Mono.just(true);
        }
        if (isGroupMessage) {
            return groupMemberService.isAllowedToSendMessage(targetId, requesterId);
        } else {
            if (requesterId.equals(targetId)) {
                return node.getSharedProperties().getService().getMessage().isAllowSendingMessagesToOneself()
                        ? Mono.just(true)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
            } else {
                if (node.getSharedProperties().getService().getMessage().isAllowSendingMessagesToStranger()) {
                    return node.getSharedProperties().getService().getMessage().isCheckIfTargetActiveAndNotDeleted()
                            ? isActiveAndNotDeleted(targetId)
                            .zipWith(userRelationshipService.isNotBlocked(targetId, requesterId))
                            .map(results -> results.getT1() && results.getT2())
                            : userRelationshipService.isNotBlocked(targetId, requesterId);
                } else {
                    return userRelationshipService.isRelatedAndAllowed(targetId, requesterId);
                }
            }
        }
    }

    public Mono<User> addUser(
            @Nullable Long id,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable @ProfileAccessConstraint ProfileAccessStrategy profileAccess,
            @Nullable Long permissionGroupId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive) {
        Date now = new Date();
        id = id != null ? id : node.nextId(ServiceType.USER);
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
        return mongoTemplate.inTransaction()
                .execute(operations -> operations.save(user, User.COLLECTION_NAME)
                        .then(userRelationshipGroupService.createRelationshipGroup(finalId, 0, "", now, operations))
                        .then(userVersionService.upsertEmptyUserVersion(user.getId(), date, operations))
                        .thenReturn(user))
                .retryWhen(DaoConstant.TRANSACTION_RETRY)
                .singleOrEmpty()
                .doOnSuccess(ignored -> registeredUsersCounter.increment());
    }

    public Mono<Boolean> isAllowToQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long targetUserId) {
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(targetUserId));
        query.fields().include(User.Fields.PROFILE_ACCESS);
        return mongoTemplate.findOne(query, User.class, User.COLLECTION_NAME)
                .flatMap(user -> {
                    switch (user.getProfileAccess()) {
                        case ALL:
                            return Mono.just(true);
                        case FRIENDS:
                            return userRelationshipService.isRelatedAndAllowed(targetUserId, requesterId);
                        case ALL_EXCEPT_BLACKLISTED_USERS:
                            return userRelationshipService.isNotBlocked(targetUserId, requesterId);
                        case UNRECOGNIZED:
                        default:
                            return Mono.just(false);
                    }
                });
    }

    public Mono<User> authAndQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long userId,
            boolean queryDeletedRecords) {
        return authAndQueryUsersProfiles(requesterId, Set.of(userId), queryDeletedRecords).singleOrEmpty();
    }

    public Flux<User> authAndQueryUsersProfiles(
            @NotNull Long requesterId,
            @NotEmpty Set<Long> userIds,
            boolean queryDeletedRecords) {
        List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            monos.add(isAllowToQueryUserProfile(requesterId, userId)
                    .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.TARGET_USERS_NOT_EXIST))));
        }
        return Mono.zip(monos, objects -> objects)
                .flatMapMany(results -> {
                    for (Object result : results) {
                        if (!(boolean) result) {
                            throw TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED);
                        }
                    }
                    return queryUsersProfiles(userIds, queryDeletedRecords);
                });
    }

    public Flux<User> queryUsersProfiles(@NotEmpty Set<Long> userIds, boolean queryDeletedRecords) {
        Query query = QueryBuilder
                .newBuilder()
                .add(Criteria.where(DaoConstant.ID_FIELD_NAME).in(userIds))
                .addIsNullIfFalse(User.Fields.DELETION_DATE, queryDeletedRecords)
                .buildQuery();
        query.fields()
                .include(DaoConstant.ID_FIELD_NAME)
                .include(User.Fields.NAME)
                .include(User.Fields.INTRO)
                .include(User.Fields.REGISTRATION_DATE)
                .include(User.Fields.PROFILE_ACCESS)
                .include(User.Fields.PERMISSION_GROUP_ID)
                .include(User.Fields.IS_ACTIVE);
        return mongoTemplate.find(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Long> queryUserPermissionGroupId(@NotNull Long userId) {
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(userId));
        query.fields().include(User.Fields.PERMISSION_GROUP_ID);
        return mongoTemplate.findOne(query, User.class, User.COLLECTION_NAME)
                .map(User::getPermissionGroupId);
    }

    public Mono<Boolean> deleteUsers(
            @NotEmpty Set<Long> userIds,
            @Nullable Boolean deleteLogically) {
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(userIds));
        Mono<Boolean> deleteOrUpdateMono;
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties().getService().getUser().isDeleteUserLogically();
        }
        if (deleteLogically) {
            Date now = new Date();
            Update update = new Update()
                    .set(User.Fields.DELETION_DATE, now)
                    .set(User.Fields.LAST_UPDATED_DATE, now);
            deleteOrUpdateMono = mongoTemplate.updateMulti(query, update, User.class, User.COLLECTION_NAME)
                    .map(UpdateResult::wasAcknowledged);
        } else {
            deleteOrUpdateMono = mongoTemplate.inTransaction()
                    .execute(operations -> operations.remove(query, User.class, User.COLLECTION_NAME)
                            .map(result -> {
                                long count = result.getDeletedCount();
                                if (count > 0) {
                                    deletedUsersCounter.increment(count);
                                }
                                return result.wasAcknowledged();
                            })
                            .flatMap(acknowledged -> acknowledged != null && acknowledged
                                    ? userRelationshipService.deleteAllRelationships(userIds, operations, false)
                                    .then(userRelationshipGroupService.deleteAllRelationshipGroups(userIds, operations, false))
                                    .then(userVersionService.delete(userIds, operations))
                                    .thenReturn(true)
                                    : Mono.just(false)))
                    .retryWhen(DaoConstant.TRANSACTION_RETRY)
                    .singleOrEmpty();
        }
        return deleteOrUpdateMono.flatMap(success -> success
                ? sessionService.disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED)
                .then(Mono.just(true))
                : Mono.just(false));
    }

    public Mono<Boolean> userExists(@NotNull Long userId, boolean queryDeletedRecords) {
        Query query = QueryBuilder
                .newBuilder()
                .add(Criteria.where(DaoConstant.ID_FIELD_NAME).is(userId))
                .addIsNullIfFalse(User.Fields.DELETION_DATE, queryDeletedRecords)
                .buildQuery();
        return mongoTemplate.exists(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Boolean> updateUser(
            @NotNull Long userId,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable @ProfileAccessConstraint ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long permissionGroupId,
            @Nullable Boolean isActive,
            @Nullable @PastOrPresent Date registrationDate) {
        return updateUsers(Collections.singleton(userId),
                rawPassword,
                name,
                intro,
                profileAccessStrategy,
                permissionGroupId,
                registrationDate,
                isActive);
    }

    public Flux<User> queryUsers(
            @Nullable Collection<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean queryDeletedRecords) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, userIds)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, registrationDateRange)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, deletionDateRange)
                .addIsIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .addIsNullIfFalse(User.Fields.DELETION_DATE, queryDeletedRecords)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Long> countRegisteredUsers(@Nullable DateRange dateRange, boolean queryDeletedRecords) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, dateRange)
                .addIsNullIfFalse(User.Fields.DELETION_DATE, queryDeletedRecords)
                .buildQuery();
        return mongoTemplate.count(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Long> countDeletedUsers(@Nullable DateRange dateRange) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, dateRange)
                .buildQuery();
        return mongoTemplate.count(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Long> countUsers(boolean queryDeletedRecords) {
        Query query = QueryBuilder
                .newBuilder()
                .addIsNullIfFalse(User.Fields.DELETION_DATE, queryDeletedRecords)
                .buildQuery();
        return mongoTemplate.count(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Long> countUsers(
            @Nullable Set<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, userIds)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, registrationDateRange)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, deletionDateRange)
                .addIsIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .buildQuery();
        return mongoTemplate.count(query, User.class, User.COLLECTION_NAME);
    }

    public Mono<Boolean> updateUsers(
            @NotEmpty Set<Long> userIds,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable @ProfileAccessConstraint ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long permissionGroupId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive) {
        if (Validator.areAllFalsy(rawPassword,
                name,
                intro,
                profileAccessStrategy,
                registrationDate,
                isActive)) {
            return Mono.just(true);
        }
        String password = null;
        if (rawPassword != null && !rawPassword.isEmpty()) {
            password = passwordManager.encodeUserPassword(rawPassword);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(userIds));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(User.Fields.PASSWORD, password)
                .setIfNotNull(User.Fields.NAME, name)
                .setIfNotNull(User.Fields.INTRO, intro)
                .setIfNotNull(User.Fields.PROFILE_ACCESS, profileAccessStrategy)
                .setIfNotNull(User.Fields.PERMISSION_GROUP_ID, permissionGroupId)
                .setIfNotNull(User.Fields.REGISTRATION_DATE, registrationDate)
                .setIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .setIfNotNull(User.Fields.LAST_UPDATED_DATE, new Date())
                .build();
        return mongoTemplate.updateMulti(query, update, User.class, User.COLLECTION_NAME)
                .flatMap(result -> {
                    if (result.wasAcknowledged()) {
                        return isActive != null && !isActive
                                ? Mono.just(sessionService.disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED))
                                .thenReturn(true)
                                : Mono.just(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

}
