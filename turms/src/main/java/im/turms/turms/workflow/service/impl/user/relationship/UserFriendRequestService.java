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

package im.turms.turms.workflow.service.impl.user.relationship;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.common.constant.RequestStatus;
import im.turms.common.constant.ResponseAction;
import im.turms.common.model.bo.user.UserFriendRequestsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.DateConstant;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.TrivialTaskManager;
import im.turms.server.common.mongo.IMongoDataGenerator;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidRequestStatus;
import im.turms.turms.constraint.ValidResponseAction;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.domain.user.UserFriendRequest;
import im.turms.turms.workflow.service.documentation.UsesNonIndexedData;
import im.turms.turms.workflow.service.impl.ExpirableService;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import im.turms.turms.workflow.service.util.RequestStatusUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 * @implNote The status of friend requests never become EXPIRED in MongoDB automatically
 * (admins can specify them to expired manually though) even if there is an expireAfter
 * property because Turms will not create a cron job to scan and expire requests in
 * MongoDB. Instead, Turms transforms the status of requests when returning them to users
 * or admins for less resource consumption and better performance to expire requests.
 */
@Service
@DependsOn(IMongoDataGenerator.BEAN_NAME)
public class UserFriendRequestService extends ExpirableService<UserFriendRequest> {

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final UserVersionService userVersionService;
    private final UserRelationshipService userRelationshipService;

    public UserFriendRequestService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient,
            UserVersionService userVersionService,
            UserRelationshipService userRelationshipService,
            TrivialTaskManager taskManager) {
        super(mongoClient, UserFriendRequest.class);
        this.node = node;
        this.mongoClient = mongoClient;
        this.userVersionService = userVersionService;
        this.userRelationshipService = userRelationshipService;

        // Set up a cron job to remove requests if deleting expired docs is enabled
        taskManager.reschedule(
                "userFriendRequestsCleanup",
                turmsPropertiesManager.getLocalProperties().getService().getUser().getFriendRequest()
                        .getExpiredUserFriendRequestsCleanupCron(),
                () -> {
                    boolean isLocalNodeLeader = node.isLocalNodeLeader();
                    boolean deleteExpiredRequestsWhenCronTriggered = node.getSharedProperties()
                            .getService()
                            .getUser()
                            .getFriendRequest()
                            .isDeleteExpiredRequestsWhenCronTriggered();
                    Date expirationDate = getModelExpirationDate();
                    if (isLocalNodeLeader && deleteExpiredRequestsWhenCronTriggered && expirationDate != null) {
                        removeAllExpiredFriendRequests(expirationDate).subscribe();
                    }
                });
    }

    public Mono<Void> removeAllExpiredFriendRequests(Date expirationDate) {
        Filter filter = Filter.newBuilder()
                .isExpired(UserFriendRequest.Fields.CREATION_DATE, expirationDate);
        return mongoClient.deleteMany(UserFriendRequest.class, filter).then();
    }

    public Mono<Boolean> hasPendingFriendRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(recipientId, "recipientId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Date expirationDate = getModelExpirationDate();
        Filter filter = Filter.newBuilder()
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING)
                // creationDate is used to:
                // 1. Make sure the pending requests are not expired actually
                // 2. Make full use of the compound index for better performance
                .isNotExpired(UserFriendRequest.Fields.CREATION_DATE,
                        expirationDate == null ? DateConstant.EPOCH : expirationDate);
        return mongoClient.exists(UserFriendRequest.class, filter);
    }

    private Mono<Boolean> hasPendingOrDeclinedOrIgnoredOrExpiredRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(recipientId, "recipientId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        // Do not need to check expirationDate because both PENDING status or EXPIRED status has been used
        Filter filter = Filter.newBuilder()
                .gt(UserFriendRequest.Fields.CREATION_DATE, DateConstant.EPOCH)
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .in(UserFriendRequest.Fields.STATUS,
                        RequestStatus.PENDING, RequestStatus.DECLINED, RequestStatus.IGNORED, RequestStatus.EXPIRED);
        return mongoClient.exists(UserFriendRequest.class, filter);
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
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(recipientId, "recipientId");
            AssertUtil.notNull(content, "content");
            validFriendRequestContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
            AssertUtil.state(!requesterId.equals(recipientId), "The requester ID must not equal to the recipient ID");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        id = id != null ? id : node.nextRandomId(ServiceType.USER_FRIEND_REQUEST);
        Date now = new Date();
        if (creationDate == null) {
            creationDate = now;
        } else {
            creationDate = creationDate.before(now) ? creationDate : now;
        }
        responseDate = RequestStatusUtil.getResponseDateBasedOnStatus(status, responseDate, now);
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        UserFriendRequest userFriendRequest = new UserFriendRequest(id, content, status, reason,
                creationDate, responseDate, requesterId, recipientId);
        return mongoClient.insert(userFriendRequest)
                .flatMap(unused -> userVersionService.updateReceivedFriendRequestsVersion(recipientId).onErrorResume(t -> Mono.empty())
                        .then(userVersionService.updateSentFriendRequestsVersion(requesterId).onErrorResume(t -> Mono.empty()))
                ).thenReturn(userFriendRequest);
    }

    public Mono<UserFriendRequest> authAndCreateFriendRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId,
            @Nullable String content,
            @NotNull @PastOrPresent Date creationDate) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(recipientId, "recipientId");
            validFriendRequestContentLength(content);
            AssertUtil.notNull(creationDate, "creationDate");
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.state(!requesterId.equals(recipientId), "The requester ID must not equal to the recipient ID");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userRelationshipService.hasNoRelationshipOrNotBlocked(recipientId, requesterId)
                .flatMap(isNotBlocked -> {
                    if (!isNotBlocked) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.FRIEND_REQUEST_SENDER_HAS_BEEN_BLOCKED));
                    }
                    // Allow to create a friend request even there is already an accepted request
                    // because the relationships can be deleted and rebuilt
                    Mono<Boolean> requestExistsMono = node.getSharedProperties().getService().getUser().getFriendRequest()
                            .isAllowResendingRequestAfterDeclinedOrIgnoredOrExpired()
                            ? hasPendingFriendRequest(requesterId, recipientId)
                            : hasPendingOrDeclinedOrIgnoredOrExpiredRequest(requesterId, recipientId);
                    return requestExistsMono.flatMap(requestExists -> {
                        String finalContent = content != null ? content : "";
                        return requestExists
                                ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.CREATE_EXISTING_FRIEND_REQUEST))
                                : createFriendRequest(null, requesterId, recipientId, finalContent,
                                RequestStatus.PENDING, creationDate, null, null);
                    });
                });
    }

    public Mono<UpdateResult> updatePendingFriendRequestStatus(
            @NotNull Long requestId,
            @NotNull @ValidRequestStatus RequestStatus requestStatus,
            @Nullable String reason,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(requestId, "requestId");
            AssertUtil.notNull(requestStatus, "requestStatus");
            DomainConstraintUtil.validRequestStatus(requestStatus);
            AssertUtil.state(requestStatus != RequestStatus.PENDING, "The request status must not be PENDING");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, requestId)
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING)
                .isNotExpired(UserFriendRequest.Fields.CREATION_DATE, getModelExpirationDate());
        Update update = Update.newBuilder()
                .set(UserFriendRequest.Fields.STATUS, requestStatus)
                .setIfNotNull(UserFriendRequest.Fields.REASON, reason);
        return mongoClient.updateOne(session, UserFriendRequest.class, filter, update)
                .flatMap(result -> result.getModifiedCount() > 0
                        ? queryRecipientId(requestId)
                        .flatMap(recipientId -> userVersionService.updateSentFriendRequestsVersion(recipientId)
                                .onErrorResume(t -> Mono.empty())
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
            AssertUtil.notEmpty(requestIds, "requestIds");
            validFriendRequestContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
            AssertUtil.state(requesterId == null || !requesterId.equals(recipientId), "The requester ID must not equal the recipient ID");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(requesterId, recipientId, content, status, reason, creationDate, responseDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, requestIds);
        Update update = Update
                .newBuilder()
                .setIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .setIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .setIfNotNull(UserFriendRequest.Fields.CONTENT, content)
                .setIfNotNull(UserFriendRequest.Fields.REASON, reason)
                .setIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDate);
        RequestStatusUtil.updateResponseDateBasedOnStatus(update, status, new Date());
        return mongoClient.updateMany(UserFriendRequest.class, filter, update);
    }

    public Mono<Long> queryRecipientId(@NotNull Long requestId) {
        try {
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, requestId);
        QueryOptions options = QueryOptions.newBuilder()
                .include(UserFriendRequest.Fields.RECIPIENT_ID);
        return mongoClient.findOne(UserFriendRequest.class, filter, options)
                .map(UserFriendRequest::getRecipientId);
    }

    public Mono<UserFriendRequest> queryRequesterAndRecipient(@NotNull Long requestId) {
        try {
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, requestId);
        QueryOptions options = QueryOptions.newBuilder()
                .include(UserFriendRequest.Fields.REQUESTER_ID,
                        UserFriendRequest.Fields.RECIPIENT_ID);
        return mongoClient.findOne(UserFriendRequest.class, filter, options);
    }

    public Mono<Void> authAndHandleFriendRequest(
            @NotNull Long friendRequestId,
            @NotNull Long requesterId,
            @NotNull @ValidResponseAction ResponseAction action,
            @Nullable String reason) {
        try {
            AssertUtil.notNull(friendRequestId, "friendRequestId");
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(action, "action");
            DomainConstraintUtil.validResponseAction(action);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryRequesterAndRecipient(friendRequestId)
                .flatMap(request -> {
                    if (!request.getRecipientId().equals(requesterId)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.REQUESTER_NOT_FRIEND_REQUEST_RECIPIENT));
                    }
                    switch (action) {
                        case ACCEPT:
                            return mongoClient.inTransaction(
                                    session -> updatePendingFriendRequestStatus(friendRequestId, RequestStatus.ACCEPTED, reason, session)
                                            .then(userRelationshipService.friendTwoUsers(request.getRequesterId(), requesterId, session))
                                            .then())
                                    .retryWhen(DaoConstant.TRANSACTION_RETRY);
                        case IGNORE:
                            return updatePendingFriendRequestStatus(friendRequestId, RequestStatus.IGNORED, reason, null)
                                    .then();
                        case DECLINE:
                            return updatePendingFriendRequestStatus(friendRequestId, RequestStatus.DECLINED, reason, null)
                                    .then();
                        default:
                            return Mono.error(TurmsBusinessException
                                    .get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The response action must not be UNRECOGNIZED"));
                    }
                });
    }

    public Mono<UserFriendRequestsWithVersion> queryFriendRequestsWithVersion(
            @NotNull Long userId,
            boolean areSentByUser,
            @Nullable Date lastUpdatedDate) {
        Mono<Date> versionMono = areSentByUser
                ? userVersionService.querySentFriendRequestsVersion(userId)
                : userVersionService.queryReceivedFriendRequestsVersion(userId);
        return versionMono
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        Flux<UserFriendRequest> requestFlux = areSentByUser
                                ? queryFriendRequestsByRequesterId(userId)
                                : queryFriendRequestsByRecipientId(userId);
                        return requestFlux
                                .collectList()
                                .map(requests -> {
                                    if (requests.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    UserFriendRequestsWithVersion.Builder builder = UserFriendRequestsWithVersion.newBuilder();
                                    int expireAfterSeconds = getModelExpireAfterSeconds();
                                    for (UserFriendRequest request : requests) {
                                        builder.addUserFriendRequests(ProtoUtil.friendRequest2proto(request, expireAfterSeconds));
                                    }
                                    return builder
                                            .setLastUpdatedDate(version.getTime())
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Flux<UserFriendRequest> queryFriendRequestsByRecipientId(@NotNull Long recipientId) {
        try {
            AssertUtil.notNull(recipientId, "recipientId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId);
        return queryExpirableData(filter);
    }

    @UsesNonIndexedData
    public Flux<UserFriendRequest> queryFriendRequestsByRequesterId(@NotNull Long requesterId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId);
        return queryExpirableData(filter);
    }

    public Mono<DeleteResult> deleteFriendRequests(@Nullable Set<Long> ids) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids);
        return mongoClient.deleteMany(UserFriendRequest.class, filter);
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
        Date expirationDate = getModelExpirationDate();
        Filter filter = Filter.newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .inIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE, getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, UserFriendRequest.Fields.CREATION_DATE, expirationDate);
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        return queryExpirableData(filter, options);
    }

    public Mono<Long> countFriendRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> recipientIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .inIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE, getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, UserFriendRequest.Fields.CREATION_DATE, getModelExpirationDate());
        return mongoClient.count(UserFriendRequest.class, filter);
    }

    // Internal methods

    @Override
    protected int getModelExpireAfterSeconds() {
        return node.getSharedProperties()
                .getService()
                .getUser()
                .getFriendRequest()
                .getFriendRequestExpireAfterSeconds();
    }

    private void validFriendRequestContentLength(@Nullable String content) {
        if (content != null) {
            int contentLimit = node.getSharedProperties()
                    .getService()
                    .getUser()
                    .getFriendRequest()
                    .getContentLimit();
            AssertUtil.max(content.length(), "content", contentLimit);
        }
    }

}