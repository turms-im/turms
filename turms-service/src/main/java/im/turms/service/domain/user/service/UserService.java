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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import io.micrometer.core.instrument.Counter;
import lombok.Getter;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.domain.user.rpc.service.RpcUserService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.service.business.message.MessageProperties;
import im.turms.server.common.infra.property.env.service.business.user.UserInfoProperties;
import im.turms.server.common.infra.property.env.service.business.user.UserProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.validation.ValidProfileAccess;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.operation.OperationResultConvertor;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.conversation.service.ConversationSettingsService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.domain.observation.service.MetricsService;
import im.turms.service.domain.user.repository.UserRepository;
import im.turms.service.domain.user.service.onlineuser.SessionService;
import im.turms.service.infra.metrics.MetricNameConst;
import im.turms.service.storage.elasticsearch.ElasticsearchManager;
import im.turms.service.storage.elasticsearch.model.Hit;
import im.turms.service.storage.elasticsearch.model.doc.UserDoc;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_USER_ROLE_ID;
import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Component
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserService implements RpcUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private final GroupMemberService groupMemberService;
    private final UserInfoUserCustomAttributesService userInfoUserDefinedAttributesService;
    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final UserSettingsService userSettingsService;
    private final UserVersionService userVersionService;
    private final SessionService sessionService;
    private final ConversationService conversationService;
    private final ConversationSettingsService conversationSettingsService;
    private final MessageService messageService;

    private final Node node;
    private final ElasticsearchManager elasticsearchManager;
    private final PasswordManager passwordManager;
    private final UserRepository userRepository;

    private final Counter registeredUsersCounter;
    private final Counter deletedUsersCounter;

    private boolean activateUserWhenAdded;
    private boolean deleteUserLogically;

    private int minPasswordLengthForCreate;
    private int minPasswordLengthForUpdate;
    private int maxPasswordLength;
    private int maxNameLength;
    private int maxIntroLength;
    private int maxProfilePictureLength;

    private boolean allowSendMessagesToOneself;
    private boolean allowSendMessagesToStranger;
    @Getter
    private boolean isUserCollectionBasedAuthEnabled;
    private boolean checkIfTargetActiveAndNotDeleted;

    public UserService(
            Node node,
            ElasticsearchManager elasticsearchManager,
            TurmsPropertiesManager propertiesManager,
            PasswordManager passwordManager,
            UserRepository userRepository,
            GroupMemberService groupMemberService,
            UserInfoUserCustomAttributesService userInfoUserDefinedAttributesService,
            UserRelationshipService userRelationshipService,
            UserRelationshipGroupService userRelationshipGroupService,
            UserSettingsService userSettingsService,
            UserVersionService userVersionService,
            SessionService sessionService,
            ConversationService conversationService,
            ConversationSettingsService conversationSettingsService,
            @Lazy MessageService messageService,
            MetricsService metricsService) {
        this.node = node;
        this.elasticsearchManager = elasticsearchManager;
        this.passwordManager = passwordManager;
        this.userRepository = userRepository;

        this.groupMemberService = groupMemberService;
        this.userInfoUserDefinedAttributesService = userInfoUserDefinedAttributesService;
        this.userRelationshipService = userRelationshipService;
        this.userRelationshipGroupService = userRelationshipGroupService;
        this.userSettingsService = userSettingsService;
        this.userVersionService = userVersionService;
        this.sessionService = sessionService;
        this.conversationService = conversationService;
        this.conversationSettingsService = conversationSettingsService;
        this.messageService = messageService;

        registeredUsersCounter = metricsService.getRegistry()
                .counter(MetricNameConst.TURMS_BUSINESS_USER_REGISTERED);
        deletedUsersCounter = metricsService.getRegistry()
                .counter(MetricNameConst.TURMS_BUSINESS_USER_DELETED);

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        UserProperties userProperties = properties.getService()
                .getUser();

        activateUserWhenAdded = userProperties.isActivateUserWhenAdded();
        deleteUserLogically = userProperties.isDeleteUserLogically();

        UserInfoProperties infoProperties = userProperties.getInfo();

        userInfoUserDefinedAttributesService
                .updateGlobalProperties(infoProperties.getUserDefinedAttributes());

        int localMinPasswordLength = infoProperties.getMinPasswordLength();
        int localMaxPasswordLength = infoProperties.getMaxPasswordLength();
        int localMaxIntroLength = infoProperties.getMaxIntroLength();
        int localMaxNameLength = infoProperties.getMaxNameLength();
        int localMaxProfilePictureLength = infoProperties.getMaxProfilePictureLength();
        minPasswordLengthForCreate = localMinPasswordLength;
        minPasswordLengthForUpdate = Math.max(0, localMinPasswordLength);
        maxPasswordLength = localMaxPasswordLength > 0
                ? localMaxPasswordLength
                : Integer.MAX_VALUE;
        maxIntroLength = localMaxIntroLength > 0
                ? localMaxIntroLength
                : Integer.MAX_VALUE;
        maxNameLength = localMaxNameLength > 0
                ? localMaxNameLength
                : Integer.MAX_VALUE;
        maxProfilePictureLength = localMaxProfilePictureLength > 0
                ? localMaxProfilePictureLength
                : Integer.MAX_VALUE;

        MessageProperties messageProperties = properties.getService()
                .getMessage();
        allowSendMessagesToOneself = messageProperties.isAllowSendMessagesToOneself();
        allowSendMessagesToStranger = messageProperties.isAllowSendMessagesToStranger();

        GatewayProperties gatewayProperties = properties.getGateway();
        // We need to check if it's null because "properties" comes from the global properties,
        // this will be null if no a turms-gateway server ever started in the cluster
        // (because if a turms-gateway server ever started in the cluster,
        // it will store the global properties in MongoDB).
        isUserCollectionBasedAuthEnabled = gatewayProperties != null
                && gatewayProperties.getSession()
                        .getIdentityAccessManagement()
                        .getType()
                        .isUserCollectionBasedAuthEnabled();

        checkIfTargetActiveAndNotDeleted = isUserCollectionBasedAuthEnabled
                && messageProperties.isCheckIfTargetActiveAndNotDeleted();
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
            return allowSendMessagesToOneself
                    ? Mono.just(ServicePermission.OK)
                    : Mono.just(ServicePermission
                            .get(ResponseStatusCode.SENDING_MESSAGES_TO_ONESELF_IS_DISABLED));
        }
        if (allowSendMessagesToStranger) {
            if (checkIfTargetActiveAndNotDeleted) {
                return userRepository.isActiveAndNotDeleted(targetId)
                        .flatMap(isActiveAndNotDeleted -> {
                            if (!isActiveAndNotDeleted) {
                                return Mono.just(ServicePermission
                                        .get(ResponseStatusCode.MESSAGE_RECIPIENT_NOT_ACTIVE));
                            }
                            return userRelationshipService.isBlocked(targetId, requesterId, true)
                                    .map(isBlocked -> isBlocked
                                            ? ServicePermission.get(
                                                    ResponseStatusCode.BLOCKED_USER_SEND_PRIVATE_MESSAGE)
                                            : ServicePermission.OK);
                        });
            }
            return userRelationshipService.isBlocked(targetId, requesterId, true)
                    .map(isBlocked -> isBlocked
                            ? ServicePermission
                                    .get(ResponseStatusCode.BLOCKED_USER_SEND_PRIVATE_MESSAGE)
                            : ServicePermission.OK);
        }
        return userRelationshipService.hasRelationshipAndNotBlocked(targetId, requesterId, true)
                .map(isRelatedAndNotBlocked -> isRelatedAndNotBlocked
                        ? ServicePermission.OK
                        : ServicePermission
                                .get(ResponseStatusCode.NOT_FRIEND_TO_SEND_PRIVATE_MESSAGE));
    }

    @Override
    public Mono<Long> createUser(
            @Nullable Long id,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String profilePicture,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long roleId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive) {
        return addUser(id,
                rawPassword,
                name,
                intro,
                profilePicture,
                profileAccessStrategy,
                roleId,
                registrationDate,
                isActive).map(User::getId);
    }

    public Mono<User> addUser(
            @Nullable Long id,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String profilePicture,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long roleId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive) {
        try {
            Validator.length(rawPassword,
                    "rawPassword",
                    minPasswordLengthForCreate,
                    maxPasswordLength);
            Validator.maxLength(name, "name", maxNameLength);
            Validator.maxLength(intro, "intro", maxIntroLength);
            Validator.maxLength(profilePicture, "profilePicture", maxProfilePictureLength);
            DataValidator.validProfileAccess(profileAccessStrategy);
            Validator.pastOrPresent(registrationDate, "registrationDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        id = id == null
                ? node.nextLargeGapId(ServiceType.USER)
                : id;
        byte[] password = StringUtil.isEmpty(rawPassword)
                ? null
                : passwordManager.encodeUserPassword(rawPassword);
        name = name == null
                ? ""
                : name;
        intro = intro == null
                ? ""
                : intro;
        profileAccessStrategy = profileAccessStrategy == null
                ? ProfileAccessStrategy.ALL
                : profileAccessStrategy;
        roleId = roleId == null
                ? DEFAULT_USER_ROLE_ID
                : roleId;
        isActive = isActive == null
                ? activateUserWhenAdded
                : isActive;
        Date date = registrationDate == null
                ? now
                : registrationDate;
        User user = new User(
                id,
                password,
                name,
                intro,
                profilePicture,
                profileAccessStrategy,
                roleId,
                date,
                null,
                now,
                isActive,
                null);
        Long finalId = id;
        String finalName = name;
        Boolean putEsDocInTransaction =
                elasticsearchManager.isUserUseCaseEnabled() && !finalName.isBlank()
                        ? elasticsearchManager.isTransactionWithMongoEnabledForUser()
                        : null;
        Mono<User> addUser = userRepository.inTransaction(session -> {
            Mono<?> mono = userRepository.insert(user, session)
                    .then(userRelationshipGroupService
                            .createRelationshipGroup(finalId, 0, "", now, session));
            if (Boolean.TRUE.equals(putEsDocInTransaction)) {
                mono = mono.then(elasticsearchManager.putUserDoc(finalId, finalName));
            }
            return mono.then(userVersionService.upsertEmptyUserVersion(user.getId(), date, session)
                    .onErrorResume(t -> {
                        LOGGER.error(
                                "Caught an error while upserting a version for the user ({}) after creating the user",
                                user.getId(),
                                t);
                        return Mono.empty();
                    }))
                    .thenReturn(user)
                    .retryWhen(TRANSACTION_RETRY);
        });
        return addUser.doOnSuccess(ignored -> {
            registeredUsersCounter.increment();
            if (Boolean.FALSE.equals(putEsDocInTransaction)) {
                elasticsearchManager.putUserDoc(finalId, finalName)
                        .subscribe(null,
                                t -> LOGGER
                                        .error("Caught an error while creating a doc for the user: "
                                                + finalId, t));
            }
        });
    }

    /**
     * @return Possible codes: {@link ResponseStatusCode#OK}
     *         {@link ResponseStatusCode#NOT_FRIEND_TO_QUERY_USER_PROFILE}
     *         {@link ResponseStatusCode#BLOCKED_USER_TO_QUERY_USER_PROFILE}
     *         {@link ResponseStatusCode#SERVER_INTERNAL_ERROR}
     *         {@link ResponseStatusCode#RESOURCE_NOT_FOUND}
     */
    public Mono<ServicePermission> isAllowToQueryUserProfile(
            @NotNull Long requesterId,
            @NotNull Long targetUserId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(targetUserId, "targetUserId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }

        Mono<ProfileAccessStrategy> findProfileAccess =
                userRepository.findProfileAccessIfNotDeleted(targetUserId);
        if (!isUserCollectionBasedAuthEnabled) {
            findProfileAccess = findProfileAccess.defaultIfEmpty(ProfileAccessStrategy.ALL);
        }
        return findProfileAccess.flatMap(strategy -> switch (strategy) {
            case ALL -> Mono.just(ServicePermission.OK);
            case FRIENDS -> userRelationshipService
                    .hasRelationshipAndNotBlocked(targetUserId, requesterId, false)
                    .map(isRelatedAndAllowed -> isRelatedAndAllowed
                            ? ServicePermission.OK
                            : ServicePermission
                                    .get(ResponseStatusCode.NOT_FRIEND_TO_QUERY_USER_PROFILE));
            case ALL_EXCEPT_BLOCKED_USERS -> userRelationshipService
                    .isNotBlocked(targetUserId, requesterId, false)
                    .map(isNotBlocked -> isNotBlocked
                            ? ServicePermission.OK
                            : ServicePermission
                                    .get(ResponseStatusCode.BLOCKED_USER_TO_QUERY_USER_PROFILE));
            default -> Mono.error(ResponseException.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                    "Unexpected profile access strategy: "
                            + strategy));
        })
                .switchIfEmpty(ResponseExceptionPublisherPool.resourceNotFound());
    }

    public Mono<List<User>> authAndQueryUsersProfile(
            @NotNull Long requesterId,
            @Nullable Set<Long> userIds,
            @Nullable String name,
            @Nullable Date lastUpdatedDate,
            @Nullable Integer skip,
            @Nullable Integer limit,
            @Nullable List<Integer> fieldsToHighlight) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (StringUtil.isNotBlank(name)) {
            return search(skip, limit, userIds, name, fieldsToHighlight);
        }
        try {
            Validator.notNull(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (userIds.isEmpty()) {
            return PublisherPool.emptyList();
        }
        // we don't need to call "isAllowToQueryUserProfile" to check the permissions
        // because we only provide basic user info that doesn't need access control currently.
        return userRepository
                .findNotDeletedUserProfiles(userIds,
                        userInfoUserDefinedAttributesService.knownAttributes,
                        lastUpdatedDate)
                .collect(CollectorUtil.toList(userIds.size()));
    }

    public Mono<String> queryUserName(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.findName(userId);
    }

    public Flux<User> queryUsersProfile(
            @NotEmpty Collection<Long> userIds,
            boolean queryDeletedRecords) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return userRepository.findUsersProfile(userIds,
                userInfoUserDefinedAttributesService.knownAttributes,
                queryDeletedRecords);
    }

    public Mono<Long> queryUserRoleIdByUserId(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.findUserRoleId(userId);
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
            deleteLogically = deleteUserLogically;
        }
        if (deleteLogically) {
            deleteOrUpdateMono = userRepository.updateUsersDeletionDate(userIds)
                    .map(OperationResultConvertor::update2delete);
            // For logical deletion, we don't need to update the user doc in Elasticsearch.
        } else {
            Boolean deleteEsDocInTransaction = elasticsearchManager.isUserUseCaseEnabled()
                    ? elasticsearchManager.isTransactionWithMongoEnabledForUser()
                    : null;
            deleteOrUpdateMono = userRepository
                    .inTransaction(session -> userRepository.deleteByIds(userIds, session)
                            .flatMap(result -> {
                                // TODO: Remove data on Redis
                                return (Boolean.TRUE.equals(deleteEsDocInTransaction)
                                        ? elasticsearchManager.deleteUserDocs(userIds)
                                        : Mono.empty())
                                        .then(userRelationshipService
                                                .deleteAllRelationships(userIds, session, false))
                                        .then(userRelationshipGroupService
                                                .deleteAllRelationshipGroups(userIds,
                                                        session,
                                                        false))
                                        .then(userSettingsService.deleteSettings(userIds, session))
                                        .then(conversationService
                                                .deletePrivateConversations(userIds, session))
                                        .then(conversationService
                                                .deleteGroupMemberConversations(userIds, session))
                                        .then(conversationSettingsService.deleteSettings(userIds,
                                                session))
                                        .then(userVersionService.delete(userIds, session)
                                                .onErrorResume(t -> {
                                                    LOGGER.error(
                                                            "Caught an error while deleting the version for the users {} after deleting the users",
                                                            userIds,
                                                            t);
                                                    return Mono.empty();
                                                }))
                                        .then(messageService
                                                .deletePrivateMessageSequenceIds(userIds)
                                                .doOnError(t -> LOGGER.error(
                                                        "Failed to remove the message sequence IDs of the users: "
                                                                + userIds,
                                                        t)))
                                        .thenReturn(result);
                            }))
                    .retryWhen(TRANSACTION_RETRY)
                    .doOnSuccess(result -> {
                        long count = result.getDeletedCount();
                        if (count > 0) {
                            deletedUsersCounter.increment(count);
                        }
                    });
            if (Boolean.FALSE.equals(deleteEsDocInTransaction)) {
                deleteOrUpdateMono = deleteOrUpdateMono.doOnSuccess(result -> elasticsearchManager
                        .deleteUserDocs(userIds)
                        .subscribe(null,
                                t -> LOGGER.error(
                                        "Caught an error while deleting the docs of the users: "
                                                + userIds,
                                        t)));
            }
        }
        return deleteOrUpdateMono.doOnNext(ignored -> sessionService
                .disconnect(userIds, SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED)
                .subscribe(null,
                        t -> LOGGER.error(
                                "Caught an error while closing the user session of the users: "
                                        + userIds,
                                t)));
    }

    public Mono<Boolean> checkIfUserExists(@NotNull Long userId, boolean queryDeletedRecords) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRepository.checkIfUserExists(userId, queryDeletedRecords);
    }

    public Mono<Boolean> updateUser(
            @NotNull Long userId,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String profilePicture,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long roleId,
            @Nullable Boolean isActive,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Map<String, Value> userDefinedAttributes) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (userDefinedAttributes == null || userDefinedAttributes.isEmpty()) {
            return updateUsers(Collections.singleton(userId),
                    rawPassword,
                    name,
                    intro,
                    profilePicture,
                    profileAccessStrategy,
                    roleId,
                    registrationDate,
                    isActive,
                    null).map(result -> result.getModifiedCount() > 0);
        }
        return userInfoUserDefinedAttributesService.parseAttributesForUpsert(userDefinedAttributes)
                .flatMap(attributes -> updateUsers(Collections.singleton(userId),
                        rawPassword,
                        name,
                        intro,
                        profilePicture,
                        profileAccessStrategy,
                        roleId,
                        registrationDate,
                        isActive,
                        attributes).map(result -> result.getModifiedCount() > 0));
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

    public Mono<Long> countRegisteredUsers(
            @Nullable DateRange dateRange,
            boolean queryDeletedRecords) {
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
        return userRepository
                .countUsers(userIds, registrationDateRange, deletionDateRange, isActive);
    }

    public Mono<UpdateResult> updateUsers(
            @NotEmpty Set<Long> userIds,
            @Nullable String rawPassword,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String profilePicture,
            @Nullable @ValidProfileAccess ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long roleId,
            @Nullable @PastOrPresent Date registrationDate,
            @Nullable Boolean isActive,
            @Nullable Map<String, Object> userDefinedAttributes) {
        try {
            Validator.notEmpty(userIds, "userIds");
            Validator.length(rawPassword,
                    "rawPassword",
                    minPasswordLengthForUpdate,
                    maxPasswordLength);
            Validator.maxLength(name, "name", maxNameLength);
            Validator.maxLength(intro, "intro", maxIntroLength);
            Validator.maxLength(profilePicture, "profilePicture", maxProfilePictureLength);
            DataValidator.validProfileAccess(profileAccessStrategy);
            Validator.pastOrPresent(registrationDate, "registrationDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(rawPassword,
                name,
                intro,
                profilePicture,
                profileAccessStrategy,
                roleId,
                registrationDate,
                isActive,
                userDefinedAttributes)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        // Save an empty string "" is supported if it passes validation
        byte[] password = rawPassword == null
                ? null
                : passwordManager.encodeUserPassword(rawPassword);

        if (name == null || !elasticsearchManager.isUserUseCaseEnabled()) {
            return updateUsers(userIds,
                    name,
                    intro,
                    profilePicture,
                    profileAccessStrategy,
                    roleId,
                    registrationDate,
                    isActive,
                    password,
                    userDefinedAttributes,
                    null);
        }
        if (elasticsearchManager.isTransactionWithMongoEnabledForUser()) {
            return userRepository.inTransaction(session -> updateUsers(userIds,
                    name,
                    intro,
                    profilePicture,
                    profileAccessStrategy,
                    roleId,
                    registrationDate,
                    isActive,
                    password,
                    userDefinedAttributes,
                    session).flatMap(updateResult -> {
                        if (updateResult.getModifiedCount() == 0) {
                            return Mono.just(updateResult);
                        }
                        Mono<?> syncUserDocs = name.isBlank()
                                ? elasticsearchManager.deleteUserDocs(userIds)
                                : elasticsearchManager.putUserDocs(userIds, name);
                        return syncUserDocs.thenReturn(updateResult);
                    }));
        }
        return updateUsers(userIds,
                name,
                intro,
                profilePicture,
                profileAccessStrategy,
                roleId,
                registrationDate,
                isActive,
                password,
                userDefinedAttributes,
                null).doOnSuccess(ignored -> {
                    if (name.isBlank()) {
                        elasticsearchManager.deleteUserDocs(userIds)
                                .subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while deleting the docs of the users {}",
                                                userIds,
                                                t));
                    } else {
                        elasticsearchManager.putUserDocs(userIds, name)
                                .subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while updating the docs of the users {}",
                                                userIds,
                                                t));
                    }
                });
    }

    private Mono<UpdateResult> updateUsers(
            Set<Long> userIds,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String profilePicture,
            @Nullable ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long roleId,
            @Nullable Date registrationDate,
            @Nullable Boolean isActive,
            @Nullable byte[] password,
            @Nullable Map<String, Object> userDefinedAttributes,
            @Nullable ClientSession session) {
        return userRepository
                .updateUsers(userIds,
                        password,
                        name,
                        intro,
                        profilePicture,
                        profileAccessStrategy,
                        roleId,
                        registrationDate,
                        isActive,
                        userDefinedAttributes,
                        session)
                .flatMap(result -> Boolean.FALSE.equals(isActive) && result.getModifiedCount() > 0
                        ? sessionService
                                .disconnect(userIds,
                                        SessionCloseStatus.USER_IS_DELETED_OR_INACTIVATED)
                                .onErrorComplete(t -> {
                                    LOGGER.error(
                                            "Caught an error while closing the sessions of the users {} after inactivating the users",
                                            userIds,
                                            t);
                                    return true;
                                })
                                .thenReturn(result)
                        : Mono.just(result));
    }

    // TODO: support PIT to ensure the result view is consistent within a specified time.
    private Mono<List<User>> search(
            @Nullable Integer skip,
            @Nullable Integer limit,
            @Nullable Set<Long> userIds,
            @NotNull String name,
            @Nullable List<Integer> fieldsToHighlight) {
        if (!elasticsearchManager.isUserUseCaseEnabled()) {
            return Mono.error(ResponseException.get(ResponseStatusCode.SEARCHING_USER_IS_DISABLED));
        }
        boolean highlight = CollectionUtil.isNotEmpty(fieldsToHighlight);
        return elasticsearchManager
                .searchUserDocs(skip, limit, name, userIds, highlight, null, null)
                .flatMap(response -> {
                    List<Hit<UserDoc>> hits = response.hits()
                            .hits();
                    int count = hits.size();
                    if (count == 0) {
                        return PublisherPool.emptyList();
                    }
                    if (highlight) {
                        Map<Long, String> userIdToHighlightedName =
                                CollectionUtil.newMapWithExpectedSize(count);
                        List<Long> ids = new ArrayList<>(count);
                        for (Hit<UserDoc> hit : hits) {
                            Long id = Long.parseLong(hit.id());
                            Collection<List<String>> highlightValues = hit.highlight()
                                    .values();
                            if (!highlightValues.isEmpty()) {
                                List<String> values = highlightValues.iterator()
                                        .next();
                                if (!values.isEmpty()) {
                                    userIdToHighlightedName.put(id, values.getFirst());
                                }
                            }
                            ids.add(id);
                        }
                        Mono<List<User>> mono = userRepository
                                .findNotDeletedUserProfiles(ids,
                                        userInfoUserDefinedAttributesService.knownAttributes,
                                        null)
                                .collect(CollectorUtil.toList(count));
                        return mono.map(users -> {
                            if (users.isEmpty()) {
                                return Collections.emptyList();
                            }
                            Map<Long, User> userIdToUser =
                                    CollectionUtil.newMapWithExpectedSize(users.size());
                            for (User user : users) {
                                userIdToUser.put(user.getId(), user);
                            }
                            for (Map.Entry<Long, String> entry : userIdToHighlightedName
                                    .entrySet()) {
                                User user = userIdToUser.get(entry.getKey());
                                if (user != null) {
                                    user.setName(entry.getValue());
                                }
                            }
                            return users;
                        });
                    } else {
                        List<Long> ids = new ArrayList<>(count);
                        for (Hit<UserDoc> hit : hits) {
                            ids.add(Long.parseLong(hit.id()));
                        }
                        return userRepository
                                .findNotDeletedUserProfiles(ids,
                                        userInfoUserDefinedAttributesService.knownAttributes,
                                        null)
                                .collect(CollectorUtil.toList(count));
                    }
                });
    }

}