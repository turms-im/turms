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

package im.turms.service.domain.group.service;

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.access.client.dto.model.group.GroupJoinRequestsWithVersion;
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
import im.turms.server.common.infra.property.env.service.business.group.GroupJoinRequestProperties;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.service.ExpirableEntityService;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.po.GroupJoinRequest;
import im.turms.service.domain.group.repository.GroupJoinRequestRepository;
import im.turms.service.domain.user.service.UserVersionService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidRequestStatus;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

/**
 * @author James Chen
 * @implNote The status of group join requests never become EXPIRED in MongoDB automatically (admins
 *           can specify them to expired manually though) even if there is an expireAfter property
 *           because Turms will not create a cron job to scan and expire requests in MongoDB.
 *           Instead, Turms transforms the status of requests when returning them to users or admins
 *           for less resource consumption and better performance to expire requests.
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupJoinRequestService extends ExpirableEntityService<GroupJoinRequest> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupJoinRequestService.class);

    private final Node node;
    private final GroupBlocklistService groupBlocklistService;
    private final GroupJoinRequestRepository groupJoinRequestRepository;
    private final GroupService groupService;
    private final GroupTypeService groupTypeService;
    private final GroupVersionService groupVersionService;
    private final GroupMemberService groupMemberService;
    private final UserVersionService userVersionService;

    private boolean allowRecallJoinRequestSentByOneself;
    private int maxContentLength;
    private boolean deleteExpiredJoinRequestsWhenCronTriggered;

    public GroupJoinRequestService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            TaskManager taskManager,
            GroupBlocklistService groupBlocklistService,
            GroupJoinRequestRepository groupJoinRequestRepository,
            GroupVersionService groupVersionService,
            GroupMemberService groupMemberService,
            GroupService groupService,
            GroupTypeService groupTypeService,
            UserVersionService userVersionService) {
        super(groupJoinRequestRepository);
        this.node = node;
        this.groupBlocklistService = groupBlocklistService;
        this.groupJoinRequestRepository = groupJoinRequestRepository;
        this.groupVersionService = groupVersionService;
        this.groupMemberService = groupMemberService;
        this.groupService = groupService;
        this.groupTypeService = groupTypeService;
        this.userVersionService = userVersionService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);

        // Set up a cron job to remove requests if deleting expired docs is enabled
        taskManager.reschedule("expiredGroupJoinRequestsCleanup",
                propertiesManager.getLocalProperties()
                        .getService()
                        .getGroup()
                        .getJoinRequest()
                        .getExpiredJoinRequestsCleanupCron(),
                () -> {
                    boolean isLocalNodeLeader = node.isLocalNodeLeader();
                    Date expirationDate = getEntityExpirationDate();
                    if (isLocalNodeLeader
                            && deleteExpiredJoinRequestsWhenCronTriggered
                            && expirationDate != null) {
                        groupJoinRequestRepository
                                .deleteExpiredData(GroupJoinRequest.Fields.CREATION_DATE,
                                        expirationDate)
                                .subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while deleting expired messages",
                                                t));
                    }
                });
    }

    private void updateProperties(TurmsProperties properties) {
        GroupJoinRequestProperties joinRequestProperties = properties.getService()
                .getGroup()
                .getJoinRequest();
        allowRecallJoinRequestSentByOneself =
                joinRequestProperties.isAllowRecallJoinRequestSentByOneself();
        int localMaxContentLength = joinRequestProperties.getMaxContentLength();
        maxContentLength = localMaxContentLength > 0
                ? localMaxContentLength
                : Integer.MAX_VALUE;
        deleteExpiredJoinRequestsWhenCronTriggered =
                joinRequestProperties.isDeleteExpiredJoinRequestsWhenCronTriggered();
    }

    public Mono<GroupJoinRequest> authAndCreateGroupJoinRequest(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @Nullable String content) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupId, "groupId");
            Validator.maxLength(content, "content", maxContentLength);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupBlocklistService.isBlocked(groupId, requesterId)
                .flatMap(isBlocked -> isBlocked
                        ? Mono.error(ResponseException
                                .get(ResponseStatusCode.GROUP_JOIN_REQUEST_SENDER_HAS_BEEN_BLOCKED))
                        : groupService.queryGroupTypeIdIfActiveAndNotDeleted(groupId))
                .switchIfEmpty(Mono.error(ResponseException
                        .get(ResponseStatusCode.SEND_JOIN_REQUEST_TO_INACTIVE_GROUP)))
                .flatMap(groupTypeService::queryGroupType)
                .flatMap(type -> switch (type.getJoinStrategy()) {
                    case MEMBERSHIP_REQUEST -> Mono.error(ResponseException.get(
                            ResponseStatusCode.SEND_JOIN_REQUEST_TO_GROUP_USING_MEMBERSHIP_REQUEST));
                    case INVITATION -> Mono.error(ResponseException
                            .get(ResponseStatusCode.SEND_JOIN_REQUEST_TO_GROUP_USING_INVITATION));
                    case QUESTION -> Mono.error(ResponseException
                            .get(ResponseStatusCode.SEND_JOIN_REQUEST_TO_GROUP_USING_QUESTION));
                    case JOIN_REQUEST -> {
                        long id = node.nextLargeGapId(ServiceType.GROUP_JOIN_REQUEST);
                        String finalContent = content == null
                                ? ""
                                : content;
                        GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                                id,
                                finalContent,
                                RequestStatus.PENDING,
                                new Date(),
                                null,
                                groupId,
                                requesterId,
                                null);
                        yield groupJoinRequestRepository.insert(groupJoinRequest)
                                .then(Mono.whenDelayError(
                                        groupVersionService.updateJoinRequestsVersion(groupId)
                                                .onErrorResume(t -> {
                                                    LOGGER.error(
                                                            "Caught an error while updating the join requests version of the group ({}) after creating a join request",
                                                            groupId,
                                                            t);
                                                    return Mono.empty();
                                                }),
                                        userVersionService
                                                .updateSentGroupJoinRequestsVersion(requesterId)
                                                .onErrorResume(t -> {
                                                    LOGGER.error(
                                                            "Caught an error while updating the sent group join requests version of the requester ({}) after creating a join request",
                                                            requesterId,
                                                            t);
                                                    return Mono.empty();
                                                })))
                                .thenReturn(groupJoinRequest);
                    }
                });
    }

    private Mono<GroupJoinRequest> queryRequesterIdAndStatusAndGroupId(@NotNull Long requestId) {
        try {
            Validator.notNull(requestId, "requestId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupJoinRequestRepository.findRequesterIdAndStatusAndGroupId(requestId);
    }

    public Mono<Void> authAndRecallPendingGroupJoinRequest(
            @NotNull Long requesterId,
            @NotNull Long requestId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(requestId, "requestId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (!allowRecallJoinRequestSentByOneself) {
            return Mono.error(ResponseException
                    .get(ResponseStatusCode.RECALLING_GROUP_JOIN_REQUEST_IS_DISABLED));
        }
        return queryRequesterIdAndStatusAndGroupId(requestId)
                .switchIfEmpty(ResponseExceptionPublisherPool.resourceNotFound())
                .flatMap(request -> {
                    RequestStatus status = request.getStatus();
                    // If the requester is not authorized to the request,
                    // it should not know the status of request from the error code.
                    // So we check whether the requester is authorized first.
                    if (!request.getRequesterId()
                            .equals(requesterId)) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_JOIN_REQUEST_SENDER_TO_RECALL_REQUEST));
                    }
                    if (status != RequestStatus.PENDING) {
                        String reason = "The request is under the status "
                                + status;
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.RECALL_NOT_PENDING_GROUP_JOIN_REQUEST,
                                reason));
                    }
                    return groupJoinRequestRepository
                            .updateStatusIfPending(requestId, RequestStatus.CANCELED, requesterId)
                            .flatMap(result -> result.getModifiedCount() == 0
                                    // Though it may return empty because the request had been
                                    // deleted between the status check,
                                    // but only admins can delete them, and it is really rare.
                                    // So we handle these cases as if the status of the request has
                                    // changed.
                                    ? Mono.error(ResponseException.get(
                                            ResponseStatusCode.RECALL_NOT_PENDING_GROUP_JOIN_REQUEST))
                                    : Mono.whenDelayError(groupVersionService
                                            .updateJoinRequestsVersion(request.getGroupId())
                                            .onErrorResume(t -> {
                                                LOGGER.error(
                                                        "Caught an error while updating the join requests version of the group ({}) after recalling a pending join request",
                                                        request.getGroupId(),
                                                        t);
                                                return Mono.empty();
                                            }),
                                            userVersionService
                                                    .updateSentGroupJoinRequestsVersion(requesterId)
                                                    .onErrorResume(t -> {
                                                        LOGGER.error(
                                                                "Caught an error while updating the sent join requests version of the requester ({}) after recalling a pending join request",
                                                                requesterId,
                                                                t);
                                                        return Mono.empty();
                                                    }))
                                            .then());
                });
    }

    public Mono<GroupJoinRequestsWithVersion> authAndQueryGroupJoinRequestsWithVersion(
            @NotNull Long requesterId,
            @Nullable Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        boolean searchRequestsByGroupId = groupId != null;
        Mono<Date> versionMono = searchRequestsByGroupId
                ? groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                        .flatMap(authenticated -> {
                            if (!authenticated) {
                                return Mono.error(ResponseException.get(
                                        ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_REQUEST));
                            }
                            return groupVersionService.queryGroupJoinRequestsVersion(groupId);
                        })
                : userVersionService.queryGroupJoinRequestsVersion(requesterId);
        return versionMono.flatMap(version -> {
            if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                return ResponseExceptionPublisherPool.alreadyUpToUpdate();
            }
            Flux<GroupJoinRequest> requestFlux = searchRequestsByGroupId
                    ? queryGroupJoinRequestsByGroupId(groupId)
                    : queryGroupJoinRequestsByRequesterId(requesterId);
            return requestFlux.collect(CollectorUtil.toChunkedList())
                    .map(groupJoinRequests -> {
                        if (groupJoinRequests.isEmpty()) {
                            throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                        }
                        GroupJoinRequestsWithVersion.Builder builder =
                                ClientMessagePool.getGroupJoinRequestsWithVersionBuilder();
                        int expireAfterSeconds =
                                groupJoinRequestRepository.getEntityExpireAfterSeconds();
                        for (GroupJoinRequest groupJoinRequest : groupJoinRequests) {
                            builder.addGroupJoinRequests(ProtoModelConvertor
                                    .groupJoinRequest2proto(groupJoinRequest, expireAfterSeconds)
                                    .build());
                        }
                        return builder.setLastUpdatedDate(version.getTime())
                                .build();
                    });
        })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Flux<GroupJoinRequest> queryGroupJoinRequestsByGroupId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupJoinRequestRepository.findRequestsByGroupId(groupId);
    }

    public Flux<GroupJoinRequest> queryGroupJoinRequestsByRequesterId(@NotNull Long requesterId) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupJoinRequestRepository.findRequestsByRequesterId(requesterId);
    }

    public Mono<Long> queryGroupId(@NotNull Long requestId) {
        try {
            Validator.notNull(requestId, "requestId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupJoinRequestRepository.findGroupId(requestId);
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
        return groupJoinRequestRepository.findRequests(ids,
                groupIds,
                requesterIds,
                responderIds,
                statuses,
                creationDateRange,
                responseDateRange,
                expirationDateRange,
                page,
                size);
    }

    public Mono<Long> countJoinRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> responderIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        return groupJoinRequestRepository.countRequests(ids,
                groupIds,
                requesterIds,
                responderIds,
                statuses,
                creationDateRange,
                responseDateRange,
                expirationDateRange);
    }

    public Mono<DeleteResult> deleteJoinRequests(@Nullable Set<Long> ids) {
        return groupJoinRequestRepository.deleteByIds(ids);
    }

    public Mono<UpdateResult> updateJoinRequests(
            @NotEmpty Set<Long> requestIds,
            @Nullable Long requesterId,
            @Nullable Long responderId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate) {
        try {
            Validator.notEmpty(requestIds, "requestIds");
            Validator.maxLength(content, "content", maxContentLength);
            DataValidator.validRequestStatus(status);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(responseDate, "responseDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(requesterId, responderId, content, status, creationDate)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupJoinRequestRepository.updateRequests(requestIds,
                requesterId,
                responderId,
                content,
                status,
                creationDate,
                responseDate);
    }

    public Mono<GroupJoinRequest> createGroupJoinRequest(
            @Nullable Long id,
            @NotNull Long groupId,
            @NotNull Long requesterId,
            @NotNull Long responderId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(responderId, "responderId");
            Validator.maxLength(content, "content", maxContentLength);
            DataValidator.validRequestStatus(status);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(responseDate, "responseDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        id = id == null
                ? node.nextLargeGapId(ServiceType.GROUP_JOIN_REQUEST)
                : id;
        if (content == null) {
            content = "";
        }
        if (creationDate == null) {
            creationDate = now;
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        responseDate = getResponseDateBasedOnStatusForNewRecord(now, status, responseDate);
        GroupJoinRequest groupJoinRequest = new GroupJoinRequest(
                id,
                content,
                status,
                creationDate,
                responseDate,
                groupId,
                requesterId,
                responderId);
        return groupJoinRequestRepository.insert(groupJoinRequest)
                .then(Mono.whenDelayError(groupVersionService.updateJoinRequestsVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error(
                                    "Caught an error while updating the join requests version of the group ({}) after creating a join request",
                                    groupId,
                                    t);
                            return Mono.empty();
                        }),
                        userVersionService.updateSentGroupJoinRequestsVersion(requesterId)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the sent join requests version of the requester ({}) after creating a join request",
                                            requesterId,
                                            t);
                                    return Mono.empty();
                                })))
                .thenReturn(groupJoinRequest);
    }

}