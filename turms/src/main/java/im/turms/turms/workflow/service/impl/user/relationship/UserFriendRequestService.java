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

import com.google.protobuf.Int64Value;
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
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.TrivialTaskManager;
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
import im.turms.turms.workflow.dao.MongoDataGenerator;
import im.turms.turms.workflow.dao.domain.user.UserFriendRequest;
import im.turms.turms.workflow.service.documentation.UsesNonIndexedData;
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
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
@DependsOn(MongoDataGenerator.BEAN_NAME)
public class UserFriendRequestService {

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
        this.node = node;
        this.mongoClient = mongoClient;
        this.userVersionService = userVersionService;
        this.userRelationshipService = userRelationshipService;

        // Set up the checker for expired user friend requests
        taskManager.reschedule(
                "userFriendRequestsChecker",
                turmsPropertiesManager.getLocalProperties().getService().getUser().getFriendRequest().getExpiredUserFriendRequestsCheckerCron(),
                () -> {
                    if (node.isLocalNodeMaster()) {
                        if (node.getSharedProperties().getService().getUser()
                                .getFriendRequest().isDeleteExpiredRequestsWhenCronTriggered()) {
                            removeAllExpiredFriendRequests().subscribe();
                        } else {
                            updateExpiredRequestsStatus().subscribe();
                        }
                    }
                });
    }

    public Mono<Void> removeAllExpiredFriendRequests() {
        Date now = new Date();
        Filter filter = Filter.newBuilder()
                .lt(UserFriendRequest.Fields.EXPIRATION_DATE, now);
        return mongoClient.deleteMany(UserFriendRequest.class, filter).then();
    }

    /**
     * Warning: Only use expirationDate to check whether a request is expired.
     * Because of the excessive resource consumption, the request status of requests
     * won't be expired immediately when reaching the expiration date.
     */
    @UsesNonIndexedData
    public Mono<UpdateResult> updateExpiredRequestsStatus() {
        Date now = new Date();
        Filter filter = Filter.newBuilder()
                .lt(UserFriendRequest.Fields.EXPIRATION_DATE, now)
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING);
        Update update = Update.newBuilder()
                .set(UserFriendRequest.Fields.STATUS, RequestStatus.EXPIRED);
        return mongoClient.updateMany(UserFriendRequest.class, filter, update);
    }

    /**
     * @param creationDateAfter used to limit the amount of data for querying to improve performance
     */
    public Mono<Boolean> hasPendingFriendRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId,
            @NotNull @PastOrPresent Date creationDateAfter) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(recipientId, "recipientId");
            AssertUtil.notNull(creationDateAfter, "creationDateAfter");
            AssertUtil.pastOrPresent(creationDateAfter, "creationDateAfter");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        Filter filter = Filter.newBuilder()
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING)
                .gt(UserFriendRequest.Fields.CREATION_DATE, creationDateAfter)
                .gt(UserFriendRequest.Fields.EXPIRATION_DATE, now);
        return mongoClient.exists(UserFriendRequest.class, filter);
    }

    /**
     * @param creationDateAfter used to limit the amount of data for querying to improve performance
     */
    private Mono<Boolean> hasPendingOrDeclinedOrIgnoredOrExpiredRequest(
            @NotNull Long requesterId,
            @NotNull Long recipientId,
            @NotNull Date creationDateAfter) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(recipientId, "recipientId");
            AssertUtil.notNull(creationDateAfter, "creationDateAfter");
            AssertUtil.pastOrPresent(creationDateAfter, "creationDateAfter");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        // Do not need to check expirationDate because both PENDING status or EXPIRED status has been used
        Filter filter = Filter.newBuilder()
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .gt(UserFriendRequest.Fields.CREATION_DATE, creationDateAfter)
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
            @Nullable Date expirationDate,
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
        if (expirationDate == null) {
            int timeToLiveHours = node.getSharedProperties()
                    .getService()
                    .getUser()
                    .getFriendRequest()
                    .getFriendRequestTimeToLiveHours();
            expirationDate = Date.from(Instant.now().plus(timeToLiveHours, ChronoUnit.HOURS));
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        UserFriendRequest userFriendRequest = new UserFriendRequest(id, content, status, reason, creationDate,
                expirationDate, responseDate, requesterId, recipientId);
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
                    int timeToLiveHours = node.getSharedProperties()
                            .getService()
                            .getUser()
                            .getFriendRequest()
                            .getFriendRequestTimeToLiveHours();
                    Date creationDateAfter = Date.from(Instant.now().minus(timeToLiveHours, ChronoUnit.HOURS));
                    Mono<Boolean> requestExistsMono = node.getSharedProperties().getService().getUser().getFriendRequest()
                            .isAllowResendingRequestAfterDeclinedOrIgnoredOrExpired()
                            ? hasPendingFriendRequest(requesterId, recipientId, creationDateAfter)
                            : hasPendingOrDeclinedOrIgnoredOrExpiredRequest(requesterId, recipientId, creationDateAfter);
                    return requestExistsMono.flatMap(requestExists -> {
                        String finalContent = content != null ? content : "";
                        return requestExists
                                ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.CREATE_EXISTING_FRIEND_REQUEST))
                                : createFriendRequest(null, requesterId, recipientId, finalContent, RequestStatus.PENDING, creationDate, null, null, null);
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
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING);
        Update update = Update.newBuilder()
                .set(UserFriendRequest.Fields.STATUS, requestStatus)
                .unset(UserFriendRequest.Fields.EXPIRATION_DATE)
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
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
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
        if (Validator.areAllNull(requesterId, recipientId, content, status, reason, creationDate, responseDate, expirationDate)) {
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
                .setIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(UserFriendRequest.Fields.EXPIRATION_DATE, expirationDate);
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

    public Mono<Void> handleFriendRequest(
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
                            return mongoClient.inTransaction(session -> updatePendingFriendRequestStatus(friendRequestId, RequestStatus.ACCEPTED, reason, session)
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
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The response action must not be UNRECOGNIZED"));
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
                                    for (UserFriendRequest request : requests) {
                                        builder.addUserFriendRequests(ProtoUtil.friendRequest2proto(request));
                                    }
                                    return builder
                                            .setLastUpdatedDate(Int64Value.of(version.getTime()))
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

    private Flux<UserFriendRequest> queryExpirableData(Filter filter) {
        return mongoClient.findMany(UserFriendRequest.class, filter)
                .map(friendRequest -> {
                    Date expirationDate = friendRequest.getExpirationDate();
                    boolean isExpired = expirationDate != null
                            && friendRequest.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis();
                    return isExpired
                            ? friendRequest.toBuilder().status(RequestStatus.EXPIRED).build()
                            : friendRequest;
                });
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
        Filter filter = Filter.newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .inIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.EXPIRATION_DATE, expirationDateRange);
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(UserFriendRequest.class, filter, options);
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
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.EXPIRATION_DATE, expirationDateRange);
        return mongoClient.count(UserFriendRequest.class, filter);
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