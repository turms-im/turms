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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.group.GroupJoinRequestsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.manager.TrivialTaskManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constraint.RequestStatusConstraint;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.GroupJoinRequest;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.RequestStatusUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Set;

import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author James Chen
 */
@Service
@Validated
public class GroupJoinRequestService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;
    private final GroupMemberService groupMemberService;
    private final UserVersionService userVersionService;

    public GroupJoinRequestService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            TrivialTaskManager taskManager,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupVersionService groupVersionService,
            GroupMemberService groupMemberService,
            GroupService groupService,
            UserVersionService userVersionService) {
        this.node = node;
        this.mongoTemplate = mongoTemplate;
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

    public Mono<Boolean> removeAllExpiredGroupJoinRequests() {
        Date now = new Date();
        Query query = new Query()
                .addCriteria(Criteria.where(GroupJoinRequest.Fields.EXPIRATION_DATE).lt(now));
        return mongoTemplate.remove(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
    }

    /**
     * Warning: Only use expirationDate to check whether a request is expired.
     * Because of the excessive resource consumption, the request status of requests
     * won't be expiry immediately when reaching the expiration date.
     */
    public Mono<Boolean> updateExpiredRequestsStatus() {
        Date now = new Date();
        Query query = new Query()
                .addCriteria(Criteria.where(GroupJoinRequest.Fields.EXPIRATION_DATE).lt(now))
                .addCriteria(Criteria.where(GroupJoinRequest.Fields.STATUS).is(RequestStatus.PENDING));
        Update update = new Update().set(GroupJoinRequest.Fields.STATUS, RequestStatus.EXPIRED);
        return mongoTemplate.updateMulti(query, update, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<GroupJoinRequest> authAndCreateGroupJoinRequest(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull String content) {
        int contentLimit = node.getSharedProperties()
                .getService().getGroup().getGroupJoinRequestContentLimit();
        if (content.length() > contentLimit) {
            String reason = "The content has exceeded the character limit: " + contentLimit;
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, reason);
        }
        return groupMemberService.isBlacklisted(groupId, requesterId)
                .flatMap(isBlacklisted -> {
                    if (isBlacklisted != null && isBlacklisted) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    } else {
                        return groupService.isGroupActiveAndNotDeleted(groupId)
                                .flatMap(isActive -> {
                                    if (isActive == null || !isActive) {
                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_ACTIVE));
                                    }
                                    Date expirationDate = null;
                                    int hours = node.getSharedProperties().getService().getGroup()
                                            .getGroupJoinRequestTimeToLiveHours();
                                    if (hours != 0) {
                                        expirationDate = Date.from(Instant.now().plus(hours, ChronoUnit.HOURS));
                                    }
                                    long id = node.nextId(ServiceType.GROUP_JOIN_REQUEST);
                                    GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                                            id,
                                            content,
                                            RequestStatus.PENDING,
                                            new Date(),
                                            null,
                                            expirationDate,
                                            groupId,
                                            requesterId,
                                            null);
                                    return mongoTemplate.insert(groupJoinRequest, GroupJoinRequest.COLLECTION_NAME)
                                            .flatMap(request -> groupVersionService.updateJoinRequestsVersion(groupId)
                                                    .then(userVersionService.updateSentGroupJoinRequestsVersion(requesterId))
                                                    .thenReturn(request));
                                });
                    }
                });
    }

    private Mono<GroupJoinRequest> queryRequesterIdAndStatusAndGroupId(@NotNull Long requestId) {
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(requestId));
        query.fields()
                .include(GroupJoinRequest.Fields.REQUESTER_ID)
                .include(GroupJoinRequest.Fields.STATUS)
                .include(GroupJoinRequest.Fields.GROUP_ID);
        return mongoTemplate.findOne(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(groupJoinRequest -> {
                    Date expirationDate = groupJoinRequest.getExpirationDate();
                    return expirationDate != null
                            && groupJoinRequest.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis()
                            ? groupJoinRequest.toBuilder().status(RequestStatus.EXPIRED).build()
                            : groupJoinRequest;
                });
    }

    public Mono<Boolean> recallPendingGroupJoinRequest(@NotNull Long requesterId, @NotNull Long requestId) {
        if (!node.getSharedProperties().getService().getGroup().isAllowRecallingJoinRequestSentByOneself()) {
            throw TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION);
        }
        return queryRequesterIdAndStatusAndGroupId(requestId)
                .flatMap(request -> {
                    if (request.getStatus() != RequestStatus.PENDING) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RESOURCES_HAVE_BEEN_HANDLED));
                    }
                    if (!request.getRequesterId().equals(requesterId)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                    Query query = new Query().addCriteria(where(DaoConstant.ID_FIELD_NAME).is(requestId));
                    Update update = new Update()
                            .set(GroupJoinRequest.Fields.STATUS, RequestStatus.CANCELED)
                            .set(GroupJoinRequest.Fields.RESPONDER_ID, requesterId);
                    return mongoTemplate.updateFirst(query, update, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                            .flatMap(result -> {
                                if (result.wasAcknowledged()) {
                                    return groupVersionService.updateJoinRequestsVersion(request.getGroupId())
                                            .zipWith(userVersionService.updateSentGroupJoinRequestsVersion(requesterId))
                                            .thenReturn(true);
                                } else {
                                    return Mono.just(false);
                                }
                            });
                });
    }

    public Mono<GroupJoinRequestsWithVersion> queryGroupJoinRequestsWithVersion(
            @NotNull Long userId,
            @Nullable Long groupId,
            @Nullable Date lastUpdatedDate) {
        boolean searchRequestsByGroupId = groupId != null;
        Mono<Date> versionMono = searchRequestsByGroupId ?
                groupMemberService.isOwnerOrManager(userId, groupId)
                        .flatMap(authenticated -> authenticated != null && authenticated
                                ? groupVersionService.queryGroupJoinRequestsVersion(groupId)
                                : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)))
                : userVersionService.queryGroupJoinRequestsVersion(userId);
        return versionMono
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        Flux<GroupJoinRequest> requestFlux = searchRequestsByGroupId
                                ? queryGroupJoinRequestsByGroupId(groupId)
                                : queryGroupJoinRequestsByRequesterId(userId);
                        return requestFlux
                                .collectList()
                                .map(groupJoinRequests -> {
                                    if (!groupJoinRequests.isEmpty()) {
                                        GroupJoinRequestsWithVersion.Builder builder = GroupJoinRequestsWithVersion.newBuilder();
                                        for (GroupJoinRequest groupJoinRequest : groupJoinRequests) {
                                            builder.addGroupJoinRequests(ProtoUtil.groupJoinRequest2proto(groupJoinRequest).build());
                                        }
                                        return builder
                                                .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                                .build();
                                    } else {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Flux<GroupJoinRequest> queryGroupJoinRequestsByGroupId(@NotNull Long groupId) {
        Query query = new Query().addCriteria(where(GroupJoinRequest.Fields.GROUP_ID).is(groupId));
        return queryExpirableData(query);
    }

    public Flux<GroupJoinRequest> queryGroupJoinRequestsByRequesterId(@NotNull Long requesterId) {
        Query query = new Query().addCriteria(where(GroupJoinRequest.Fields.REQUESTER_ID).is(requesterId));
        return queryExpirableData(query);
    }

    public Mono<Long> queryGroupId(@NotNull Long requestId) {
        Query query = new Query().addCriteria(where(DaoConstant.ID_FIELD_NAME).is(requestId));
        query.fields().include(GroupJoinRequest.Fields.GROUP_ID);
        return mongoTemplate.findOne(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(GroupJoinRequest::getGroupId);
    }


    public Flux<GroupJoinRequest> queryJoinRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> responderIds,
            @Nullable Set<@RequestStatusConstraint RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(GroupJoinRequest.Fields.GROUP_ID, groupIds)
                .addInIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterIds)
                .addInIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderIds)
                .addInIfNotNull(GroupJoinRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.EXPIRATION_DATE, expirationDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME);
    }

    public Mono<Long> countJoinRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupId,
            @Nullable Set<Long> requesterId,
            @Nullable Set<Long> responderId,
            @Nullable Set<@RequestStatusConstraint RequestStatus> status,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addIsIfNotNull(GroupJoinRequest.Fields.GROUP_ID, groupId)
                .addIsIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterId)
                .addIsIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderId)
                .addIsIfNotNull(GroupJoinRequest.Fields.STATUS, status)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.EXPIRATION_DATE, expirationDateRange)
                .buildQuery();
        return mongoTemplate.count(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME);
    }

    public Mono<Boolean> deleteJoinRequests(@Nullable Set<Long> ids) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .buildQuery();
        return mongoTemplate.remove(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
    }

    public Mono<Boolean> updateJoinRequests(
            @NotEmpty Set<Long> ids,
            @Nullable Long requesterId,
            @Nullable Long responderId,
            @Nullable String content,
            @Nullable @RequestStatusConstraint RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
        if (Validator.areAllNull(requesterId, responderId, content, status, creationDate, expirationDate)) {
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(where(DaoConstant.ID_FIELD_NAME).in(ids));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterId)
                .setIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderId)
                .setIfNotNull(GroupJoinRequest.Fields.CONTENT, content)
                .setIfNotNull(GroupJoinRequest.Fields.STATUS, status)
                .setIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(GroupJoinRequest.Fields.EXPIRATION_DATE, expirationDate)
                .build();
        RequestStatusUtil.updateResponseDateBasedOnStatus(update, status, responseDate);
        return mongoTemplate.updateMulti(query, update, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<GroupJoinRequest> createGroupJoinRequest(
            @Nullable Long id,
            @NotNull Long groupId,
            @NotNull Long requesterId,
            @NotNull Long responderId,
            @NotNull String content,
            @Nullable @RequestStatusConstraint RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
        Date now = new Date();
        id = id != null ? id : node.nextId(ServiceType.GROUP_JOIN_REQUEST);
        if (creationDate == null) {
            creationDate = now;
        }
        if (expirationDate == null) {
            int timeToLiveHours = node.getSharedProperties().getService().getGroup()
                    .getGroupJoinRequestTimeToLiveHours();
            if (timeToLiveHours == 0) {
                expirationDate = Date.from(Instant.now().plus(timeToLiveHours, ChronoUnit.HOURS));
            }
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        responseDate = RequestStatusUtil.getResponseDateBasedOnStatus(status, responseDate, now);
        GroupJoinRequest groupJoinRequest = new GroupJoinRequest(id, content, status, creationDate, responseDate,
                expirationDate, groupId, requesterId, responderId);
        return Mono.zip(mongoTemplate.insert(groupJoinRequest, GroupJoinRequest.COLLECTION_NAME),
                groupVersionService.updateJoinRequestsVersion(groupId),
                userVersionService.updateSentGroupJoinRequestsVersion(responderId))
                .map(Tuple2::getT1);
    }

    private Flux<GroupJoinRequest> queryExpirableData(Query query) {
        return mongoTemplate.find(query, GroupJoinRequest.class, GroupJoinRequest.COLLECTION_NAME)
                .map(groupJoinRequest -> {
                    Date expirationDate = groupJoinRequest.getExpirationDate();
                    return expirationDate != null
                            && groupJoinRequest.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis()
                            ? groupJoinRequest.toBuilder().status(RequestStatus.EXPIRED).build()
                            : groupJoinRequest;
                });
    }
}