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

package im.turms.turms.workflow.service.impl.group;

import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.RequestStatus;
import im.turms.common.model.bo.group.GroupJoinRequestsWithVersion;
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
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.MongoDataGenerator;
import im.turms.turms.workflow.dao.domain.group.GroupJoinRequest;
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
public class GroupJoinRequestService {

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;
    private final GroupMemberService groupMemberService;
    private final UserVersionService userVersionService;

    public GroupJoinRequestService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            TrivialTaskManager taskManager,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient,
            GroupVersionService groupVersionService,
            GroupMemberService groupMemberService,
            GroupService groupService,
            UserVersionService userVersionService) {
        this.node = node;
        this.mongoClient = mongoClient;
        this.groupVersionService = groupVersionService;
        this.groupMemberService = groupMemberService;
        this.groupService = groupService;
        this.userVersionService = userVersionService;

        // Set up the checker for expired group join requests
        taskManager.reschedule(
                "groupJoinRequestsChecker",
                turmsPropertiesManager.getLocalProperties().getService().getGroup().getExpiredGroupJoinRequestsCheckerCron(),
                () -> {
                    if (node.isLocalNodeMaster()) {
                        if (node.getSharedProperties().getService().getGroup()
                                .isDeleteExpiredGroupJoinRequestsWhenCronTriggered()) {
                            removeAllExpiredGroupJoinRequests().subscribe();
                        } else {
                            updateExpiredRequestsStatus().subscribe();
                        }
                    }
                });
    }

    public Mono<Void> removeAllExpiredGroupJoinRequests() {
        Date now = new Date();
        Filter filter = Filter.newBuilder()
                .lt(GroupJoinRequest.Fields.EXPIRATION_DATE, now);
        return mongoClient.deleteMany(GroupJoinRequest.class, filter).then();
    }

    /**
     * @implNote Only use expirationDate to check whether a request is expired.
     * Because of the excessive resource consumption, the request status of requests
     * won't be expiry immediately when reaching the expiration date.
     */
    public Mono<Void> updateExpiredRequestsStatus() {
        Date now = new Date();
        Filter filter = Filter.newBuilder()
                .lt(GroupJoinRequest.Fields.EXPIRATION_DATE, now)
                .eq(GroupJoinRequest.Fields.STATUS, RequestStatus.PENDING);
        Update update = Update.newBuilder()
                .set(GroupJoinRequest.Fields.STATUS, RequestStatus.EXPIRED);
        return mongoClient.updateMany(GroupJoinRequest.class, filter, update).then();
    }

    public Mono<GroupJoinRequest> authAndCreateGroupJoinRequest(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @Nullable String content) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(groupId, "groupId");
            validJoinRequestContentLength(content);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService.isBlocked(groupId, requesterId)
                .flatMap(isBlocked -> isBlocked
                        ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.GROUP_JOIN_REQUEST_SENDER_HAS_BEEN_BLOCKED))
                        : groupService.isGroupActiveAndNotDeleted(groupId))
                .flatMap(isActive -> {
                    if (!isActive) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SEND_JOIN_REQUEST_TO_INACTIVE_GROUP));
                    }
                    int hours = node.getSharedProperties().getService().getGroup()
                            .getGroupJoinRequestTimeToLiveHours();
                    Date expirationDate = Date.from(Instant.now().plus(hours, ChronoUnit.HOURS));
                    long id = node.nextRandomId(ServiceType.GROUP_JOIN_REQUEST);
                    String finalContent = content != null ? content : "";
                    GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                            id,
                            finalContent,
                            RequestStatus.PENDING,
                            new Date(),
                            null,
                            expirationDate,
                            groupId,
                            requesterId,
                            null);
                    return mongoClient.insert(groupJoinRequest)
                            .flatMap(unused -> groupVersionService.updateJoinRequestsVersion(groupId).onErrorResume(t -> Mono.empty())
                                    .then(userVersionService.updateSentGroupJoinRequestsVersion(requesterId).onErrorResume(t -> Mono.empty())))
                            .thenReturn(groupJoinRequest);
                });
    }

    private Mono<GroupJoinRequest> queryRequesterIdAndStatusAndGroupId(@NotNull Long requestId) {
        try {
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, requestId);
        QueryOptions options = QueryOptions.newBuilder()
                .include(GroupJoinRequest.Fields.REQUESTER_ID,
                        GroupJoinRequest.Fields.STATUS,
                        GroupJoinRequest.Fields.GROUP_ID);
        return mongoClient.findOne(GroupJoinRequest.class, filter, options)
                .map(groupJoinRequest -> {
                    Date expirationDate = groupJoinRequest.getExpirationDate();
                    boolean isExpired = expirationDate != null
                            && groupJoinRequest.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis();
                    return isExpired
                            ? groupJoinRequest.toBuilder().status(RequestStatus.EXPIRED).build()
                            : groupJoinRequest;
                });
    }

    /**
     * @return return a empty publisher even if the request doesn't exist
     */
    public Mono<Void> recallPendingGroupJoinRequest(@NotNull Long requesterId, @NotNull Long requestId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!node.getSharedProperties().getService().getGroup().isAllowRecallingJoinRequestSentByOneself()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED));
        }
        return queryRequesterIdAndStatusAndGroupId(requestId)
                .flatMap(request -> {
                    RequestStatus status = request.getStatus();
                    if (status != RequestStatus.PENDING) {
                        String reason = "The request is under the status " + status;
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RECALL_NOT_PENDING_GROUP_JOIN_REQUEST, reason));
                    }
                    if (!request.getRequesterId().equals(requesterId)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST));
                    }
                    Filter filter = Filter.newBuilder()
                            .eq(DaoConstant.ID_FIELD_NAME, requestId);
                    Update update = Update.newBuilder()
                            .set(GroupJoinRequest.Fields.STATUS, RequestStatus.CANCELED)
                            .set(GroupJoinRequest.Fields.RESPONDER_ID, requesterId);
                    return mongoClient.updateOne(GroupJoinRequest.class, filter, update)
                            .flatMap(result -> result.getModifiedCount() > 0
                                    ? Mono.when(groupVersionService.updateJoinRequestsVersion(request.getGroupId()).onErrorResume(t -> Mono.empty()),
                                    userVersionService.updateSentGroupJoinRequestsVersion(requesterId).onErrorResume(t -> Mono.empty()))
                                    : Mono.empty());
                });
    }

    public Mono<GroupJoinRequestsWithVersion> queryGroupJoinRequestsWithVersion(
            @NotNull Long requesterId,
            @Nullable Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        boolean searchRequestsByGroupId = groupId != null;
        Mono<Date> versionMono = searchRequestsByGroupId ?
                groupMemberService.isOwnerOrManager(requesterId, groupId)
                        .flatMap(authenticated -> {
                            if (authenticated != null && authenticated) {
                                return groupVersionService.queryGroupJoinRequestsVersion(groupId);
                            }
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_REQUEST));
                        })
                : userVersionService.queryGroupJoinRequestsVersion(requesterId);
        return versionMono
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        Flux<GroupJoinRequest> requestFlux = searchRequestsByGroupId
                                ? queryGroupJoinRequestsByGroupId(groupId)
                                : queryGroupJoinRequestsByRequesterId(requesterId);
                        return requestFlux
                                .collectList()
                                .map(groupJoinRequests -> {
                                    if (groupJoinRequests.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    GroupJoinRequestsWithVersion.Builder builder = GroupJoinRequestsWithVersion.newBuilder();
                                    for (GroupJoinRequest groupJoinRequest : groupJoinRequests) {
                                        builder.addGroupJoinRequests(ProtoUtil.groupJoinRequest2proto(groupJoinRequest).build());
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

    public Flux<GroupJoinRequest> queryGroupJoinRequestsByGroupId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(GroupJoinRequest.Fields.GROUP_ID, groupId);
        return queryExpirableData(filter);
    }

    public Flux<GroupJoinRequest> queryGroupJoinRequestsByRequesterId(@NotNull Long requesterId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(GroupJoinRequest.Fields.REQUESTER_ID, requesterId);
        return queryExpirableData(filter);
    }

    public Mono<Long> queryGroupId(@NotNull Long requestId) {
        try {
            AssertUtil.notNull(requestId, "requestId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .eq(DaoConstant.ID_FIELD_NAME, requestId);
        QueryOptions options = QueryOptions.newBuilder()
                .include(GroupJoinRequest.Fields.GROUP_ID);
        return mongoClient.findOne(GroupJoinRequest.class, filter, options)
                .map(GroupJoinRequest::getGroupId);
    }

    public Flux<GroupJoinRequest> queryJoinRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> responderIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(GroupJoinRequest.Fields.GROUP_ID, groupIds)
                .inIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderIds)
                .inIfNotNull(GroupJoinRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.EXPIRATION_DATE, expirationDateRange);
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(GroupJoinRequest.class, filter, options);
    }

    public Mono<Long> countJoinRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupId,
            @Nullable Set<Long> requesterId,
            @Nullable Set<Long> responderId,
            @Nullable Set<RequestStatus> status,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .eqIfNotNull(GroupJoinRequest.Fields.GROUP_ID, groupId)
                .eqIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterId)
                .eqIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderId)
                .eqIfNotNull(GroupJoinRequest.Fields.STATUS, status)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.EXPIRATION_DATE, expirationDateRange);
        return mongoClient.count(GroupJoinRequest.class, filter);
    }

    public Mono<DeleteResult> deleteJoinRequests(@Nullable Set<Long> ids) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids);
        return mongoClient.deleteMany(GroupJoinRequest.class, filter);
    }

    public Mono<UpdateResult> updateJoinRequests(
            @NotEmpty Set<Long> requestIds,
            @Nullable Long requesterId,
            @Nullable Long responderId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
        try {
            AssertUtil.notEmpty(requestIds, "requestIds");
            validJoinRequestContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(requesterId, responderId, content, status, creationDate, expirationDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, requestIds);
        Update update = Update
                .newBuilder()
                .setIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterId)
                .setIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderId)
                .setIfNotNull(GroupJoinRequest.Fields.CONTENT, content)
                .setIfNotNull(GroupJoinRequest.Fields.STATUS, status)
                .setIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(GroupJoinRequest.Fields.EXPIRATION_DATE, expirationDate);
        RequestStatusUtil.updateResponseDateBasedOnStatus(update, status, responseDate);
        return mongoClient.updateMany(GroupJoinRequest.class, filter, update);
    }

    public Mono<GroupJoinRequest> createGroupJoinRequest(
            @Nullable Long id,
            @NotNull Long groupId,
            @NotNull Long requesterId,
            @NotNull Long responderId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(responderId, "responderId");
            validJoinRequestContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        id = id != null ? id : node.nextRandomId(ServiceType.GROUP_JOIN_REQUEST);
        if (content == null) {
            content = "";
        }
        if (creationDate == null) {
            creationDate = now;
        }
        if (expirationDate == null) {
            int timeToLiveHours = node.getSharedProperties().getService().getGroup()
                    .getGroupJoinRequestTimeToLiveHours();
            expirationDate = Date.from(Instant.now().plus(timeToLiveHours, ChronoUnit.HOURS));
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        responseDate = RequestStatusUtil.getResponseDateBasedOnStatus(status, responseDate, now);
        GroupJoinRequest groupJoinRequest = new GroupJoinRequest(id, content, status, creationDate, responseDate,
                expirationDate, groupId, requesterId, responderId);
        return mongoClient.insert(groupJoinRequest)
                .flatMap(request -> groupVersionService.updateJoinRequestsVersion(groupId).onErrorResume(t -> Mono.empty())
                        .then(userVersionService.updateSentGroupJoinRequestsVersion(responderId).onErrorResume(t -> Mono.empty()))
                ).thenReturn(groupJoinRequest);
    }

    private Flux<GroupJoinRequest> queryExpirableData(Filter filter) {
        return mongoClient.findMany(GroupJoinRequest.class, filter)
                .map(groupJoinRequest -> {
                    Date expirationDate = groupJoinRequest.getExpirationDate();
                    boolean isExpired = expirationDate != null
                            && groupJoinRequest.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis();
                    return isExpired
                            ? groupJoinRequest.toBuilder().status(RequestStatus.EXPIRED).build()
                            : groupJoinRequest;
                });
    }

    private void validJoinRequestContentLength(@Nullable String content) {
        if (content != null) {
            int contentLimit = node.getSharedProperties().getService().getGroup().getGroupJoinRequestContentLimit();
            if (contentLimit > 0) {
                AssertUtil.max(content.length(), "content", contentLimit);
            }
        }
    }

}