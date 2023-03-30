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

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.access.client.dto.constant.ResponseAction;
import im.turms.server.common.access.client.dto.model.user.UserFriendRequestsWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.service.ExpirableEntityService;
import im.turms.service.domain.common.suggestion.UsesNonIndexedData;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.user.po.UserFriendRequest;
import im.turms.service.domain.user.repository.UserFriendRequestRepository;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidRequestStatus;
import im.turms.service.infra.validation.ValidResponseAction;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 * @implNote The status of friend requests never become EXPIRED in MongoDB automatically (admins can
 *           specify them to expired manually though) even if there is an expireAfter property
 *           because Turms will not create a cron job to scan and expire requests in MongoDB.
 *           Instead, Turms transforms the status of requests when returning them to users or admins
 *           for less resource consumption and better performance to expire requests.
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserFriendRequestService extends ExpirableEntityService<UserFriendRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserFriendRequestService.class);

    private final Node node;
    private final UserFriendRequestRepository userFriendRequestRepository;
    private final UserVersionService userVersionService;
    private final UserRelationshipService userRelationshipService;

    private boolean allowSendRequestAfterDeclinedOrIgnoredOrExpired;
    private int maxContentLength;
    private boolean deleteExpiredRequestsWhenCronTriggered;

    public UserFriendRequestService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            UserFriendRequestRepository userFriendRequestRepository,
            UserVersionService userVersionService,
            UserRelationshipService userRelationshipService,
            TaskManager taskManager) {
        super(userFriendRequestRepository);
        this.node = node;
        this.userFriendRequestRepository = userFriendRequestRepository;
        this.userVersionService = userVersionService;
        this.userRelationshipService = userRelationshipService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
        // Set up a cron job to remove requests if deleting expired docs is enabled
        taskManager.reschedule("expiredUserFriendRequestsCleanup",
                propertiesManager.getLocalProperties()
                        .getService()
                        .getUser()
                        .getFriendRequest()
                        .getExpiredUserFriendRequestsCleanupCron(),
                () -> {
                    boolean isLocalNodeLeader = node.isLocalNodeLeader();
                    Date expirationDate = getEntityExpirationDate();
                    if (isLocalNodeLeader
                            && deleteExpiredRequestsWhenCronTriggered
                            && expirationDate != null) {
                        removeAllExpiredFriendRequests(expirationDate).subscribe(null,
                                t -> LOGGER.error(
                                        "Caught an error while removing expired friend requests",
                                        t));
                    }
                });
    }

    private void updateProperties(TurmsProperties properties) {
        allowSendRequestAfterDeclinedOrIgnoredOrExpired = properties.getService()
                .getUser()
                .getFriendRequest()
                .isAllowSendRequestAfterDeclinedOrIgnoredOrExpired();
        int localMaxContentLength = properties.getService()
                .getUser()
                .getFriendRequest()
                .getMaxContentLength();
        maxContentLength = localMaxContentLength > 0
                ? localMaxContentLength
                : Integer.MAX_VALUE;
        deleteExpiredRequestsWhenCronTriggered = properties.getService()
                .getUser()
                .getFriendRequest()
                .isDeleteExpiredRequestsWhenCronTriggered();
    }

    public Mono<Void> removeAllExpiredFriendRequests(Date expirationDate) {
        return userFriendRequestRepository.deleteExpiredData(UserFriendRequest.Fields.CREATION_DATE,
                expirationDate);
    }

    public Mono<Boolean> hasPendingFriendRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(recipientId, "recipientId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userFriendRequestRepository.hasPendingFriendRequest(requesterId, recipientId);
    }

    private Mono<Boolean> hasPendingOrDeclinedOrIgnoredOrExpiredRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(recipientId, "recipientId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userFriendRequestRepository
                .hasPendingOrDeclinedOrIgnoredOrExpiredRequest(requesterId, recipientId);
    }

    public Mono<UserFriendRequest> createFriendRequest(
            @Nullable Long id,
            @NotNull Long requesterId,
            @NotNull Long recipientId,
            @NotNull String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate,
            @Nullable String reason) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(recipientId, "recipientId");
            Validator.notNull(content, "content");
            Validator.maxLength(content, "content", maxContentLength);
            DataValidator.validRequestStatus(status);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(responseDate, "responseDate");
            Validator.notEquals(requesterId,
                    recipientId,
                    "The requester ID must not equal to the recipient ID");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        id = id == null
                ? node.nextLargeGapId(ServiceType.USER_FRIEND_REQUEST)
                : id;
        Date now = new Date();
        if (creationDate == null) {
            creationDate = now;
        } else {
            creationDate = creationDate.before(now)
                    ? creationDate
                    : now;
        }
        responseDate = getResponseDateBasedOnStatusForNewRecord(now, status, responseDate);
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        UserFriendRequest userFriendRequest = new UserFriendRequest(
                id,
                content,
                status,
                reason,
                creationDate,
                responseDate,
                requesterId,
                recipientId);
        return userFriendRequestRepository.insert(userFriendRequest)
                .then(Mono.whenDelayError(
                        userVersionService.updateReceivedFriendRequestsVersion(recipientId)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the received friend requests version of the recipient ({}) after creating a friend request",
                                            recipientId,
                                            t);
                                    return Mono.empty();
                                }),
                        userVersionService.updateSentFriendRequestsVersion(requesterId)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the sent friend requests version of the requester ({}) after creating a friend request",
                                            requesterId,
                                            t);
                                    return Mono.empty();
                                })))
                .thenReturn(userFriendRequest);
    }

    public Mono<UserFriendRequest> authAndCreateFriendRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId,
            @Nullable String content,
            @NotNull @PastOrPresent Date creationDate) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(recipientId, "recipientId");
            Validator.maxLength(content, "content", maxContentLength);
            Validator.notNull(creationDate, "creationDate");
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.notEquals(requesterId,
                    recipientId,
                    "The requester ID must not equal to the recipient ID");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRelationshipService.hasNoRelationshipOrNotBlocked(recipientId, requesterId)
                .flatMap(isNotBlocked -> {
                    if (!isNotBlocked) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.FRIEND_REQUEST_SENDER_HAS_BEEN_BLOCKED));
                    }
                    // Allow to create a friend request even there is already an accepted request
                    // because the relationships can be deleted and rebuilt
                    Mono<Boolean> requestExistsMono =
                            allowSendRequestAfterDeclinedOrIgnoredOrExpired
                                    ? hasPendingFriendRequest(requesterId, recipientId)
                                    : hasPendingOrDeclinedOrIgnoredOrExpiredRequest(requesterId,
                                            recipientId);
                    return requestExistsMono.flatMap(requestExists -> {
                        String finalContent = content == null
                                ? ""
                                : content;
                        return requestExists
                                ? Mono.error(ResponseException
                                        .get(ResponseStatusCode.CREATE_EXISTING_FRIEND_REQUEST))
                                : createFriendRequest(null,
                                        requesterId,
                                        recipientId,
                                        finalContent,
                                        RequestStatus.PENDING,
                                        creationDate,
                                        null,
                                        null);
                    });
                });
    }

    public Mono<UpdateResult> updatePendingFriendRequestStatus(
            @NotNull Long requestId,
            @NotNull @ValidRequestStatus RequestStatus requestStatus,
            @Nullable String reason,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(requestId, "requestId");
            Validator.notNull(requestStatus, "requestStatus");
            DataValidator.validRequestStatus(requestStatus);
            Validator.notEquals(requestStatus,
                    RequestStatus.PENDING,
                    "The request status must not be PENDING");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userFriendRequestRepository
                .updatePendingFriendRequestStatus(requestId, requestStatus, reason, session)
                .flatMap(result -> result.getModifiedCount() > 0
                        ? queryRecipientId(requestId).flatMap(recipientId -> userVersionService
                                .updateReceivedFriendRequestsVersion(recipientId)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the received friend requests version of the recipient ({}) after updating a pending friend request",
                                            recipientId,
                                            t);
                                    return Mono.empty();
                                })
                                .thenReturn(result))
                        : Mono.just(result));
    }

    public Mono<UpdateResult> updateFriendRequests(
            @NotEmpty Set<Long> requestIds,
            @Nullable Long requesterId,
            @Nullable Long recipientId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable String reason,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate) {
        try {
            Validator.notEmpty(requestIds, "requestIds");
            Validator.maxLength(content, "content", maxContentLength);
            DataValidator.validRequestStatus(status);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(responseDate, "responseDate");
            Validator.shouldTrue(requesterId == null || !requesterId.equals(recipientId),
                    "The requester ID must not equal the recipient ID");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(requesterId,
                recipientId,
                content,
                status,
                reason,
                creationDate,
                responseDate)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return userFriendRequestRepository.updateFriendRequests(requestIds,
                requesterId,
                recipientId,
                content,
                status,
                reason,
                creationDate);
    }

    public Mono<Long> queryRecipientId(@NotNull Long requestId) {
        try {
            Validator.notNull(requestId, "requestId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userFriendRequestRepository.findRecipientId(requestId);
    }

    public Mono<UserFriendRequest> queryRequesterAndRecipient(@NotNull Long requestId) {
        try {
            Validator.notNull(requestId, "requestId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userFriendRequestRepository.findRequesterAndRecipient(requestId);
    }

    public Mono<Void> authAndHandleFriendRequest(
            @NotNull Long friendRequestId,
            @NotNull Long requesterId,
            @NotNull @ValidResponseAction ResponseAction action,
            @Nullable String reason) {
        try {
            Validator.notNull(friendRequestId, "friendRequestId");
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(action, "action");
            DataValidator.validResponseAction(action);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryRequesterAndRecipient(friendRequestId).flatMap(request -> {
            if (!request.getRecipientId()
                    .equals(requesterId)) {
                return Mono.error(ResponseException
                        .get(ResponseStatusCode.REQUESTER_NOT_FRIEND_REQUEST_RECIPIENT));
            }
            return switch (action) {
                case ACCEPT -> userFriendRequestRepository
                        .inTransaction(session -> updatePendingFriendRequestStatus(friendRequestId,
                                RequestStatus.ACCEPTED,
                                reason,
                                session)
                                .then(userRelationshipService.friendTwoUsers(request
                                        .getRequesterId(), requesterId, session)))
                        .retryWhen(TRANSACTION_RETRY);
                case IGNORE -> updatePendingFriendRequestStatus(friendRequestId,
                        RequestStatus.IGNORED,
                        reason,
                        null);
                case DECLINE -> updatePendingFriendRequestStatus(friendRequestId,
                        RequestStatus.DECLINED,
                        reason,
                        null);
                default -> Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The response action must not be UNRECOGNIZED"));
            };
        })
                .then();
    }

    public Mono<UserFriendRequestsWithVersion> queryFriendRequestsWithVersion(
            @NotNull Long userId,
            boolean areSentByUser,
            @Nullable Date lastUpdatedDate) {
        Mono<Date> versionMono = areSentByUser
                ? userVersionService.querySentFriendRequestsVersion(userId)
                : userVersionService.queryReceivedFriendRequestsVersion(userId);
        return versionMono.flatMap(version -> {
            if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                return ResponseExceptionPublisherPool.alreadyUpToUpdate();
            }
            Flux<UserFriendRequest> requestFlux = areSentByUser
                    ? queryFriendRequestsByRequesterId(userId)
                    : queryFriendRequestsByRecipientId(userId);
            return requestFlux.collect(CollectorUtil.toChunkedList())
                    .map(requests -> {
                        if (requests.isEmpty()) {
                            throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                        }
                        UserFriendRequestsWithVersion.Builder builder =
                                ClientMessagePool.getUserFriendRequestsWithVersionBuilder();
                        int expireAfterSeconds =
                                userFriendRequestRepository.getEntityExpireAfterSeconds();
                        for (UserFriendRequest request : requests) {
                            builder.addUserFriendRequests(ProtoModelConvertor
                                    .friendRequest2proto(request, expireAfterSeconds));
                        }
                        return builder.setLastUpdatedDate(version.getTime())
                                .build();
                    });
        })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Flux<UserFriendRequest> queryFriendRequestsByRecipientId(@NotNull Long recipientId) {
        try {
            Validator.notNull(recipientId, "recipientId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return userFriendRequestRepository.findFriendRequestsByRecipientId(recipientId);
    }

    @UsesNonIndexedData
    public Flux<UserFriendRequest> queryFriendRequestsByRequesterId(@NotNull Long requesterId) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return userFriendRequestRepository.findFriendRequestsByRequesterId(requesterId);
    }

    public Mono<DeleteResult> deleteFriendRequests(@Nullable Set<Long> ids) {
        return userFriendRequestRepository.deleteByIds(ids);
    }

    public Flux<UserFriendRequest> queryFriendRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> recipientIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        return userFriendRequestRepository.findFriendRequests(ids,
                requesterIds,
                recipientIds,
                statuses,
                creationDateRange,
                responseDateRange,
                expirationDateRange,
                page,
                size);
    }

    public Mono<Long> countFriendRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> recipientIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        return userFriendRequestRepository.countFriendRequests(ids,
                requesterIds,
                recipientIds,
                statuses,
                creationDateRange,
                responseDateRange,
                expirationDateRange);
    }

}