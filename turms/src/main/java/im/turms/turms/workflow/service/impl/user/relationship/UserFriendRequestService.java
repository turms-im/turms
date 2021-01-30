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
import im.turms.common.constant.RequestStatus;
import im.turms.common.constant.ResponseAction;
import im.turms.common.model.bo.user.UserFriendRequestsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.TrivialTaskManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidRequestStatus;
import im.turms.turms.constraint.ValidResponseAction;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.user.UserFriendRequest;
import im.turms.turms.workflow.service.documentation.UsesNonIndexedData;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import im.turms.turms.workflow.service.util.RequestStatusUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
public class UserFriendRequestService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final UserVersionService userVersionService;
    private final UserRelationshipService userRelationshipService;

    public UserFriendRequestService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            @Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            UserVersionService userVersionService,
            UserRelationshipService userRelationshipService,
            TrivialTaskManager taskManager) {
        this.node = node;
        this.mongoTemplate = mongoTemplate;
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
        Query query = new Query()
                .addCriteria(Criteria.where(UserFriendRequest.Fields.EXPIRATION_DATE).lt(now));
        return mongoTemplate.remove(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME).then();
    }

    /**
     * Warning: Only use expirationDate to check whether a request is expired.
     * Because of the excessive resource consumption, the request status of requests
     * won't be expired immediately when reaching the expiration date.
     */
    @UsesNonIndexedData
    public Mono<UpdateResult> updateExpiredRequestsStatus() {
        Date now = new Date();
        Query query = new Query()
                .addCriteria(Criteria.where(UserFriendRequest.Fields.EXPIRATION_DATE).lt(now))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.STATUS).is(RequestStatus.PENDING));
        Update update = new Update().set(UserFriendRequest.Fields.STATUS, RequestStatus.EXPIRED);
        return mongoTemplate.updateMulti(query, update, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
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
        Query query = new Query()
                .addCriteria(Criteria.where(UserFriendRequest.Fields.REQUESTER_ID).is(requesterId))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.RECIPIENT_ID).is(recipientId))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.STATUS).is(RequestStatus.PENDING))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.CREATION_DATE).gt(creationDateAfter))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.EXPIRATION_DATE).gt(now));
        return mongoTemplate.exists(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
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
        Query query = new Query()
                .addCriteria(Criteria.where(UserFriendRequest.Fields.REQUESTER_ID).is(requesterId))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.RECIPIENT_ID).is(recipientId))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.CREATION_DATE).gt(creationDateAfter))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.STATUS)
                        .in(RequestStatus.PENDING, RequestStatus.DECLINED, RequestStatus.IGNORED, RequestStatus.EXPIRED));
        return mongoTemplate.exists(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
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
        return mongoTemplate.insert(userFriendRequest, UserFriendRequest.COLLECTION_NAME)
                .flatMap(request -> userVersionService.updateReceivedFriendRequestsVersion(recipientId).onErrorResume(t -> Mono.empty())
                        .then(userVersionService.updateSentFriendRequestsVersion(requesterId).onErrorResume(t -> Mono.empty()))
                        .thenReturn(request));
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
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(requestId, "requestId");
            AssertUtil.notNull(requestStatus, "requestStatus");
            DomainConstraintUtil.validRequestStatus(requestStatus);
            AssertUtil.state(requestStatus != RequestStatus.PENDING, "The request status must not be PENDING");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(requestId))
                .addCriteria(Criteria.where(UserFriendRequest.Fields.STATUS).is(RequestStatus.PENDING));
        Update update = new Update()
                .set(UserFriendRequest.Fields.STATUS, requestStatus)
                .unset(UserFriendRequest.Fields.EXPIRATION_DATE);
        if (reason != null) {
            update.set(UserFriendRequest.Fields.REASON, reason);
        }
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.updateFirst(query, update, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME)
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
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(requestIds));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .setIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .setIfNotNull(UserFriendRequest.Fields.CONTENT, content)
                .setIfNotNull(UserFriendRequest.Fields.REASON, reason)
                .setIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(UserFriendRequest.Fields.EXPIRATION_DATE, expirationDate)
                .build();
        RequestStatusUtil.updateResponseDateBasedOnStatus(update, status, new Date());
        return mongoTemplate.updateMulti(query, update, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
    }

    public Mono<Long> queryRecipientId(@NotNull Long requestId) {
        try {
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(requestId));
        query.fields().include(UserFriendRequest.Fields.RECIPIENT_ID);
        return mongoTemplate.findOne(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME)
                .map(UserFriendRequest::getRecipientId);
    }

    public Mono<UserFriendRequest> queryRequesterAndRecipient(@NotNull Long requestId) {
        try {
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(requestId));
        query.fields()
                .include(UserFriendRequest.Fields.REQUESTER_ID)
                .include(UserFriendRequest.Fields.RECIPIENT_ID);
        return mongoTemplate.findOne(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
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
                            return mongoTemplate.inTransaction()
                                    .execute(operations -> updatePendingFriendRequestStatus(friendRequestId, RequestStatus.ACCEPTED, reason, operations)
                                            .then(userRelationshipService.friendTwoUsers(request.getRequesterId(), requesterId, operations))
                                            .then())
                                    .retryWhen(DaoConstant.TRANSACTION_RETRY)
                                    .singleOrEmpty();
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
        Query query = new Query()
                .addCriteria(Criteria.where(UserFriendRequest.Fields.RECIPIENT_ID).is(recipientId));
        return queryExpirableData(query);
    }

    @UsesNonIndexedData
    public Flux<UserFriendRequest> queryFriendRequestsByRequesterId(@NotNull Long requesterId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserFriendRequest.Fields.REQUESTER_ID).is(requesterId));
        return queryExpirableData(query);
    }

    private Flux<UserFriendRequest> queryExpirableData(Query query) {
        return mongoTemplate.find(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME)
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .buildQuery();
        return mongoTemplate.remove(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .addInIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .addInIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.EXPIRATION_DATE, expirationDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
    }

    public Mono<Long> countFriendRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> recipientIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .addInIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .addInIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(UserFriendRequest.Fields.EXPIRATION_DATE, expirationDateRange)
                .buildQuery();
        return mongoTemplate.count(query, UserFriendRequest.class, UserFriendRequest.COLLECTION_NAME);
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