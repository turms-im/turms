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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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
import im.turms.server.common.access.client.dto.model.group.GroupInvitationsWithVersion;
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
import im.turms.server.common.infra.property.env.service.business.group.GroupInvitationProperties;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.common.service.ExpirableEntityService;
import im.turms.service.domain.common.suggestion.UsesNonIndexedData;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.po.GroupInvitation;
import im.turms.service.domain.group.repository.GroupInvitationRepository;
import im.turms.service.domain.user.service.UserVersionService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidRequestStatus;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

/**
 * @author James Chen
 * @implNote The status of group invitations never become EXPIRED in MongoDB automatically (admins
 *           can specify them to expired manually though) even if there is an expireAfter property
 *           because Turms will not create a cron job to scan and expire requests in MongoDB.
 *           Instead, Turms transforms the status of requests when returning them to users or admins
 *           for less resource consumption and better performance to expire requests.
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupInvitationService extends ExpirableEntityService<GroupInvitation> {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupInvitationService.class);

    private final Node node;
    private final GroupInvitationRepository groupInvitationRepository;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserVersionService userVersionService;

    private boolean allowRecallPendingInvitationByOwnerAndManager;
    private int maxContentLength;
    private boolean deleteExpiredInvitationsWhenCronTriggered;

    public GroupInvitationService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            GroupInvitationRepository groupInvitationRepository,
            GroupMemberService groupMemberService,
            UserVersionService userVersionService,
            GroupVersionService groupVersionService,
            TaskManager taskManager) {
        super(groupInvitationRepository);
        this.groupInvitationRepository = groupInvitationRepository;
        this.groupMemberService = groupMemberService;
        this.node = node;
        this.userVersionService = userVersionService;
        this.groupVersionService = groupVersionService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
        // Set up a cron job to remove invitations if deleting expired docs is enabled
        taskManager.reschedule("expiredGroupInvitationsCleanup",
                propertiesManager.getLocalProperties()
                        .getService()
                        .getGroup()
                        .getInvitation()
                        .getExpiredInvitationsCleanupCron(),
                () -> {
                    boolean isLocalNodeLeader = node.isLocalNodeLeader();
                    Date expirationDate = getEntityExpirationDate();
                    if (isLocalNodeLeader
                            && deleteExpiredInvitationsWhenCronTriggered
                            && expirationDate != null) {
                        groupInvitationRepository
                                .deleteExpiredData(GroupInvitation.Fields.CREATION_DATE,
                                        expirationDate)
                                .subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while deleting expired group invitations",
                                                t));
                    }
                });
    }

    private void updateProperties(TurmsProperties properties) {
        GroupInvitationProperties invitationProperties = properties.getService()
                .getGroup()
                .getInvitation();
        allowRecallPendingInvitationByOwnerAndManager =
                invitationProperties.isAllowRecallPendingInvitationByOwnerAndManager();
        int localMaxContentLength = invitationProperties.getMaxContentLength();
        maxContentLength = localMaxContentLength > 0
                ? localMaxContentLength
                : Integer.MAX_VALUE;
        deleteExpiredInvitationsWhenCronTriggered =
                invitationProperties.isDeleteExpiredInvitationsWhenCronTriggered();
    }

    public Mono<GroupInvitation> authAndCreateGroupInvitation(
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @NotNull Long inviteeId,
            @Nullable String content) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(inviterId, "inviterId");
            Validator.notNull(inviteeId, "inviteeId");
            Validator.maxLength(content, "content", maxContentLength);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberService.isAllowedToInviteUser(groupId, inviterId, null)
                .flatMap(pair -> {
                    ServicePermission permission = pair.getLeft();
                    ResponseStatusCode statusCode = permission.code();
                    if (statusCode != ResponseStatusCode.OK) {
                        return Mono.error(ResponseException.get(statusCode, permission.reason()));
                    }
                    return groupMemberService.isAllowedToBeInvited(groupId, inviteeId)
                            .flatMap(code -> {
                                if (code != ResponseStatusCode.OK) {
                                    return Mono.error(ResponseException.get(code));
                                }
                                GroupInvitationStrategy strategy = pair.getRight();
                                if (!strategy.requiresApproval()) {
                                    return Mono.error(ResponseException.get(
                                            ResponseStatusCode.SEND_GROUP_INVITATION_TO_GROUP_NOT_REQUIRE_INVITATION));
                                }
                                String finalContent = content == null
                                        ? ""
                                        : content;
                                return createGroupInvitation(null,
                                        groupId,
                                        inviterId,
                                        inviteeId,
                                        finalContent,
                                        RequestStatus.PENDING,
                                        null,
                                        null);
                            });
                });
    }

    public Mono<GroupInvitation> createGroupInvitation(
            @Nullable Long id,
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @NotNull Long inviteeId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(inviterId, "inviterId");
            Validator.notNull(inviteeId, "inviteeId");
            Validator.maxLength(content, "content", maxContentLength);
            DataValidator.validRequestStatus(status);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(responseDate, "responseDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        id = id == null
                ? node.nextLargeGapId(ServiceType.GROUP_INVITATION)
                : id;
        if (content == null) {
            content = "";
        }
        if (creationDate == null) {
            creationDate = new Date();
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        GroupInvitation groupInvitation = new GroupInvitation(
                id,
                groupId,
                inviterId,
                inviteeId,
                content,
                status,
                creationDate,
                responseDate);
        return groupInvitationRepository.insert(groupInvitation)
                .then(Mono.whenDelayError(groupVersionService.updateGroupInvitationsVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error(
                                    "Caught an error while updating the group invitations version of the group ({}) after creating a group invitation",
                                    groupId,
                                    t);
                            return Mono.empty();
                        }),
                        userVersionService.updateSentGroupInvitationsVersion(inviterId)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the sent group invitations version of the inviter ({}) after creating a group invitation",
                                            inviterId,
                                            t);
                                    return Mono.empty();
                                }),
                        userVersionService.updateReceivedGroupInvitationsVersion(inviteeId)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the received group invitations version of the invitee ({}) after creating a group invitation",
                                            inviteeId,
                                            t);
                                    return Mono.empty();
                                })))
                .thenReturn(groupInvitation);
    }

    public Mono<GroupInvitation> queryGroupIdAndStatus(@NotNull Long invitationId) {
        try {
            Validator.notNull(invitationId, "invitationId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupInvitationRepository.findGroupIdAndStatus(invitationId);
    }

    public Mono<Void> authAndRecallPendingGroupInvitation(
            @NotNull Long requesterId,
            @NotNull Long invitationId) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(invitationId, "invitationId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        // TODO: recalled by sender
        if (!allowRecallPendingInvitationByOwnerAndManager) {
            return Mono.error(ResponseException
                    .get(ResponseStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED));
        }
        return queryGroupIdAndStatus(invitationId)
                .switchIfEmpty(ResponseExceptionPublisherPool.resourceNotFound())
                .flatMap(invitation -> groupMemberService
                        .isOwnerOrManager(requesterId, invitation.getGroupId(), false)
                        .flatMap(authenticated -> {
                            if (!authenticated) {
                                return Mono.error(ResponseException.get(
                                        ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_RECALL_INVITATION));
                            }
                            RequestStatus requestStatus = invitation.getStatus();
                            if (requestStatus != RequestStatus.PENDING) {
                                String reason = "The invitation is under the status "
                                        + requestStatus;
                                return Mono.error(ResponseException.get(
                                        ResponseStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION,
                                        reason));
                            }
                            return groupInvitationRepository
                                    .updateStatusIfPending(invitationId, RequestStatus.CANCELED)
                                    .flatMap(result -> result.getModifiedCount() == 0
                                            // Though it may be 0 because the request had been
                                            // deleted between the status check,
                                            // but only admins can delete them, and it is really
                                            // rare.
                                            // So we handle these cases as if the status of the
                                            // request has changed.
                                            ? Mono.error(ResponseException.get(
                                                    ResponseStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION))
                                            : groupVersionService
                                                    .updateGroupInvitationsVersion(
                                                            invitation.getGroupId())
                                                    .onErrorResume(t -> {
                                                        LOGGER.error(
                                                                "Caught an error while updating the group invitations version of the group ({}) after recalling a pending invitation",
                                                                invitation.getGroupId(),
                                                                t);
                                                        return Mono.empty();
                                                    })
                                                    .then());
                        }));
    }

    public Flux<GroupInvitation> queryGroupInvitationsByInviteeId(@NotNull Long inviteeId) {
        try {
            Validator.notNull(inviteeId, "inviteeId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupInvitationRepository.findInvitationsByInviteeId(inviteeId);
    }

    @UsesNonIndexedData
    public Flux<GroupInvitation> queryGroupInvitationsByInviterId(@NotNull Long inviterId) {
        try {
            Validator.notNull(inviterId, "inviterId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupInvitationRepository.findInvitationsByInviterId(inviterId);
    }

    public Flux<GroupInvitation> queryGroupInvitationsByGroupId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupInvitationRepository.findInvitationsByGroupId(groupId);
    }

    public Mono<GroupInvitationsWithVersion> queryUserGroupInvitationsWithVersion(
            @NotNull Long userId,
            boolean areSentByUser,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Date> versionMono = areSentByUser
                ? userVersionService.querySentGroupInvitationsLastUpdatedDate(userId)
                : userVersionService.queryReceivedGroupInvitationsLastUpdatedDate(userId);
        return versionMono.flatMap(version -> {
            if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                return ResponseExceptionPublisherPool.alreadyUpToUpdate();
            }
            Flux<GroupInvitation> invitationFlux = areSentByUser
                    ? queryGroupInvitationsByInviterId(userId)
                    : queryGroupInvitationsByInviteeId(userId);
            return invitationFlux.collect(CollectorUtil.toChunkedList())
                    .map(groupInvitations -> {
                        if (groupInvitations.isEmpty()) {
                            throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                        }
                        GroupInvitationsWithVersion.Builder builder =
                                ClientMessagePool.getGroupInvitationsWithVersionBuilder();
                        int expireAfterSeconds =
                                groupInvitationRepository.getEntityExpireAfterSeconds();
                        for (GroupInvitation groupInvitation : groupInvitations) {
                            builder.addGroupInvitations(ProtoModelConvertor
                                    .groupInvitation2proto(groupInvitation, expireAfterSeconds));
                        }
                        return builder.setLastUpdatedDate(version.getTime())
                                .build();
                    });
        })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<GroupInvitationsWithVersion> authAndQueryGroupInvitationsWithVersion(
            @NotNull Long userId,
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(userId, groupId, false)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_INVITATION));
                    }
                    return groupVersionService.queryGroupInvitationsVersion(groupId)
                            .flatMap(version -> {
                                if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                                    return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                                }
                                Recyclable<List<GroupInvitation>> recyclableList =
                                        ListRecycler.obtain();
                                return queryGroupInvitationsByGroupId(groupId)
                                        .collect(Collectors.toCollection(recyclableList::getValue))
                                        .map(groupInvitations -> {
                                            if (groupInvitations.isEmpty()) {
                                                throw ResponseException
                                                        .get(ResponseStatusCode.NO_CONTENT);
                                            }
                                            GroupInvitationsWithVersion.Builder builder =
                                                    ClientMessagePool
                                                            .getGroupInvitationsWithVersionBuilder()
                                                            .setLastUpdatedDate(version.getTime());
                                            int expireAfterSeconds = groupInvitationRepository
                                                    .getEntityExpireAfterSeconds();
                                            for (GroupInvitation invitation : groupInvitations) {
                                                builder.addGroupInvitations(ProtoModelConvertor
                                                        .groupInvitation2proto(invitation,
                                                                expireAfterSeconds)
                                                        .build());
                                            }
                                            return builder.build();
                                        })
                                        .doFinally(signalType -> recyclableList.recycle());
                            })
                            .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
                });
    }

    public Mono<Long> queryInviteeIdByInvitationId(@NotNull Long invitationId) {
        try {
            Validator.notNull(invitationId, "invitationId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupInvitationRepository.findInviteeId(invitationId);
    }

    public Flux<GroupInvitation> queryInvitations(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> inviterIds,
            @Nullable Set<Long> inviteeIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        return groupInvitationRepository.findInvitations(ids,
                groupIds,
                inviterIds,
                inviteeIds,
                statuses,
                creationDateRange,
                responseDateRange,
                expirationDateRange,
                page,
                size);
    }

    public Mono<Long> countInvitations(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> inviterIds,
            @Nullable Set<Long> inviteeIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        return groupInvitationRepository.count(ids,
                groupIds,
                inviterIds,
                inviteeIds,
                statuses,
                creationDateRange,
                responseDateRange,
                expirationDateRange);
    }

    public Mono<DeleteResult> deleteInvitations(@Nullable Set<Long> ids) {
        return groupInvitationRepository.deleteByIds(ids);
    }

    public Mono<UpdateResult> updateInvitations(
            @NotEmpty Set<Long> invitationIds,
            @Nullable Long inviterId,
            @Nullable Long inviteeId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate) {
        try {
            Validator.notEmpty(invitationIds, "invitationIds");
            Validator.maxLength(content, "content", maxContentLength);
            DataValidator.validRequestStatus(status);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(responseDate, "responseDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(inviterId, inviteeId, content, status, creationDate)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupInvitationRepository.updateInvitations(invitationIds,
                inviterId,
                inviteeId,
                content,
                status,
                creationDate,
                responseDate);
    }

}