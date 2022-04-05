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

package im.turms.service.domain.user.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.security.PasswordManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.operation.OperationResultConvertor;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.domain.observation.service.MetricsService;
import im.turms.service.domain.user.repository.UserRepository;
import im.turms.service.domain.user.service.onlineuser.SessionService;
import im.turms.service.infra.metrics.MetricNameConst;
import im.turms.service.infra.validation.ValidProfileAccess;
import im.turms.service.storage.mongo.OperationResultPublisherPool;
import io.micrometer.core.instrument.Counter;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
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

import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_USER_PERMISSION_GROUP_ID;
import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Component
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final GroupMemberService groupMemberService;
    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final UserVersionService userVersionService;
    private final SessionService sessionService;
    private final ConversationService conversationService;
    private final MessageService messageService;

    private final Node node;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;

    private final Counter registeredUsersCounter;
    private final Counter deletedUsersCounter;

    public UserService(
            Node node,
            PasswordManager passwordManager,
            UserRepository userRepository,
            UserRelationshipService userRelationshipService,
            GroupMemberService groupMemberService,
            UserVersionService userVersionService,
            UserRelationshipGroupService userRelationshipGroupService,
            SessionService sessionService,
            ConversationService conversationService,
            @Lazy MessageService messageService,
            MetricsService metricsService) {
        this.node = node;
        this.passwordManager = passwordManager;
        this.userRepository = userRepository;

        this.userRelationshipService = userRelationshipService;
        this.groupMemberService = groupMemberService;
        this.userVersionService = userVersionService;
        this.userRelationshipGroupService = userRelationshipGroupService;
        this.sessionService = sessionService;
        this.conversationService = conversationService;
        this.messageService = messageService;

        registeredUsersCounter = metricsService.getRegistry().counter(MetricNameConst.REGISTERED_USERS_COUNTER);
        deletedUsersCounter = metricsService.getRegistry().counter(MetricNameConst.DELETED_USERS_COUNTER);
    }

    public Mono<ServicePermission> isAllowedToSendMessageToTarget(
            @NotNull Boolean isGroupMessage,
            @NotNull Boolean isSystemMessage,
            @NotNull Long requesterId,
            @NotNull Long targetId) {
        try {
            Validator.notNull(isGroupMessage, "isGroupMessage");
            Validator.notNull(isSystemMessage, "isSystemMessage");
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(targetId, "targetId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (isSystemMessage) {
            return Mono.just(ServicePermission.OK);
        }
        if (isGroupMessage) {
            return groupMemberService.isAllowedToSendMessage(targetId, requesterId)
                    .map(ServicePermission::get);
        } else if (requesterId.equals(targetId)) {
            return node.getSharedProperties().getService().getMessage().isAllowSendMessagesToOneself()
                    ? Mono.just(ServicePermission.OK)
                    : Mono.just(ServicePermission.get(ResponseStatusCode.SENDING_MESSAGES_TO_ONESELF_IS_DISABLED));
        }
        if (node.getSharedProperties().getService().getMessage().isAllowSendMessagesToStranger()) {
            if (node.getSharedProperties().getService().getMessage().isCheckIfTargetActiveAndNotDeleted()) {
                return userRepository.isActiveAndNotDeleted(targetId)
                        .flatMap(isActiveAndNotDeleted -> {
                            if (!isActiveAndNotDeleted) {
                                return Mono.just(ServicePermission.get(ResponseStatusCode.MESSAGE_RECIPIENT_NOT_ACTIVE));
                            }
                            return userRelationshipService.hasNoRelationshipOrNotBlocked(targetId, requesterId)
                                    .map(isNotBlocked -> isNotBlocked
                                            ? ServicePermission.OK
                                            : ServicePermission.get(ResponseStatusCode.PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED));
                        });
            }
            return userRelationshipService.hasNoRelationshipOrNotBlocked(targetId, requesterId)
                    .map(isNotBlocked -> isNotBlocked
                            ? ServicePermission.OK
                            : ServicePermission.get(ResponseStatusCode.PRIVATE_MESSAGE_SENDER_HAS_BEEN_BLOCKED));
        }
        return userRelationshipService.hasRelationshipAndNotBlocked(targetId, requesterId)
                .map(isRelatedAndNotBlocked -> isRelatedAndNotBlocked
                        ? ServicePermission.OK
                        : ServicePermission.get(ResponseStatusCode.MESSAGE_SENDER_NOT_IN_CONTACTS_OR_BLOCKED));
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
            DataValidator.validProfileAccess(profileAccess);
            Validator.pastOrPresent(registrationDate, "registrationDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        id = id == null ? node.nextLargeGapId(ServiceType.USER) : id;
        byte[] password = rawPassword == null ? null : passwordManager.encodeUserPassword(rawPassword);
        name = name == null ? "" : name;
        intro = intro == null ? "" : intro;
        profileAccess = profileAccess == null ? ProfileAccessStrategy.ALL : profileAccess;
        permissionGroupId = permissionGroupId == null ? DEFAULT_USER_PERMISSION_GROUP_ID : permissionGroupId;
        isActive = isActive == null ? node.getSharedProperties().getService().getUser().isActivateUserWhenAdded() : isActive;
        Date date = registrationDate == null ? now : registrationDate;
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
        return userRepository.inTransaction(session -> userRepository.insert(user, session)
                        .then(userRelationshipGroupService.createRelationshipGroup(finalId, 0, "", now, session))
                        .then(userVersionService.upsertEmptyUserVersion(user.getId(), date, session)
                                .onErrorResume(t -> {
                                    LOGGER.error("Caught an error while upserting a version for the user {} after creating the user",
                                            user.getId(), t);
                                    return Mono.empty();
                                }))
                        .thenReturn(user))
                .retryWhen(TRANSACTION_RETRY)
                .doOnSuccess(ignored -> registeredUsersCounter.increment());
    }

    public Mono<ServicePermission> isAllowToQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long targetUserId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(targetUserId, "targetUserId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.findProfileAccessIfNotDeleted(targetUserId)
                .flatMap(strategy -> switch (strategy) {
                    case ALL -> Mono.just(ServicePermission.OK);
                    case FRIENDS -> userRelationshipService.hasRelationshipAndNotBlocked(targetUserId, requesterId)
                            .map(isRelatedAndAllowed -> isRelatedAndAllowed
                                    ? ServicePermission.OK
                                    : ServicePermission.get(ResponseStatusCode.PROFILE_REQUESTER_NOT_IN_CONTACTS_OR_BLOCKED));
                    case ALL_EXCEPT_BLOCKED_USERS -> userRelationshipService.hasNoRelationshipOrNotBlocked(targetUserId, requesterId)
                            .map(isNotBlocked -> isNotBlocked
                                    ? ServicePermission.OK
                                    : ServicePermission.get(ResponseStatusCode.PROFILE_REQUESTER_HAS_BEEN_BLOCKED));
                    default -> Mono.error(ResponseException
                            .get(ResponseStatusCode.SERVER_INTERNAL_ERROR, "Unexpected value " + strategy));
                })
                .defaultIfEmpty(ServicePermission.get(ResponseStatusCode.USER_PROFILE_NOT_FOUND));
    }

    public Mono<User> authAndQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long userId,
            boolean queryDeletedRecords) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return isAllowToQueryUserProfile(requesterId, userId)
                .flatMap(permission -> {
                    ResponseStatusCode code = permission.code();
                    if (code != ResponseStatusCode.OK) {
                        return Mono.error(ResponseException.get(code, permission.reason()));
                    }
                    return queryUsersProfile(Set.of(userId), queryDeletedRecords).singleOrEmpty();
                });
    }

    public Flux<User> queryUsersProfile(@NotEmpty Collection<Long> userIds, boolean queryDeletedRecords) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return userRepository.findUsersProfile(userIds, queryDeletedRecords);
    }

    public Mono<Long> queryUserPermissionGroupId(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.findUserPermissionGroupId(userId);
    }

    public Mono<DeleteResult> deleteUsers(
            @NotEmpty Set<Long> userIds,
            @Nullable Boolean deleteLogically) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<DeleteResult> deleteOrUpdateMono;
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties().getService().getUser().isDeleteUserLogically();
        }
        if (deleteLogically) {
            deleteOrUpdateMono = userRepository.updateUsersDeleted(userIds)
                    .map(OperationResultConvertor::update2delete);
        } else {
            deleteOrUpdateMono = userRepository
                    .inTransaction(session -> userRepository.deleteByIds(userIds, session)
                            .flatMap(result -> {
                                long count = result.getDeletedCount();
                                if (count > 0) {
                                    deletedUsersCounter.increment(count);
                                }
                                return userRelationshipService.deleteAllRelationships(userIds, session, false)
                                        .then(userRelationshipGroupService.deleteAllRelationshipGroups(userIds, session, false))
                                        .then(conversationService.deletePrivateConversations(userIds, session))
//                                        .then(conversationService.deleteGroupConversations(userIds, session)) TODO
                                        .then(userVersionService.delete(userIds, session)
                                                .onErrorResume(t -> {
                                                    LOGGER.error("Caught an error while deleting the version for the users {} after deleting the users", userIds, t);
                                                    return Mono.empty();
                                                }))
                                        .then(messageService.deleteSequenceIds(false, userIds)
                                                .doOnError(t -> LOGGER.error("Failed to remove the message sequence IDs for the user IDs: {}", userIds, t)))
                                        .thenReturn(result);
                            }))
                    .retryWhen(TRANSACTION_RETRY);
        }
        return deleteOrUpdateMono
                .doOnNext(ignored -> sessionService.disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED)
                        .subscribe(null, t -> LOGGER.error("Caught an error while closing the session of the user IDs: " + userIds, t)));
    }

    public Mono<Boolean> checkIfUserExists(@NotNull Long userId, boolean queryDeletedRecords) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.checkIfUserExists(userId, queryDeletedRecords);
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
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
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
                        : Mono.error(ResponseException.get(ResponseStatusCode.UPDATE_INFO_OF_NON_EXISTING_USER)));
    }

    public Flux<User> queryUsers(
            @Nullable Collection<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean queryDeletedRecords) {
        return userRepository.findUsers(userIds,
                registrationDateRange,
                deletionDateRange,
                isActive,
                page,
                size,
                queryDeletedRecords);
    }

    public Mono<Long> countRegisteredUsers(@Nullable DateRange dateRange, boolean queryDeletedRecords) {
        return userRepository.countRegisteredUsers(dateRange, queryDeletedRecords);
    }

    public Mono<Long> countDeletedUsers(@Nullable DateRange dateRange) {
        return userRepository.countDeletedUsers(dateRange);
    }

    public Mono<Long> countUsers(boolean queryDeletedRecords) {
        return userRepository.countUsers(queryDeletedRecords);
    }

    public Mono<Long> countUsers(
            @Nullable Set<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive) {
        return userRepository.countUsers(userIds, registrationDateRange, deletionDateRange, isActive);
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
            Validator.notEmpty(userIds, "userIds");
            DataValidator.validProfileAccess(profileAccessStrategy);
            Validator.pastOrPresent(registrationDate, "registrationDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(rawPassword,
                name,
                intro,
                profileAccessStrategy,
                registrationDate,
                isActive)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        byte[] password = rawPassword == null || rawPassword.isEmpty()
                ? null
                : passwordManager.encodeUserPassword(rawPassword);
        return userRepository.updateUsers(userIds,
                        password,
                        name,
                        intro,
                        profileAccessStrategy,
                        permissionGroupId,
                        registrationDate,
                        isActive)
                .flatMap(result -> Boolean.FALSE.equals(isActive) && result.getModifiedCount() > 0
                        ? sessionService.disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED)
                        .onErrorResume(t -> {
                            LOGGER.error("Caught an error while disconnecting the session of the users {} after inactivating the users", userIds, t);
                            return Mono.empty();
                        }).thenReturn(result)
                        : Mono.just(result));
    }

}