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

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.RequestStatus;
import im.turms.common.model.bo.group.GroupInvitationsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.TrivialTaskManager;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.ServicePermission;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidRequestStatus;
import im.turms.turms.util.ProtoModelUtil;
import im.turms.turms.workflow.dao.domain.group.GroupInvitation;
import im.turms.turms.workflow.service.documentation.UsesNonIndexedData;
import im.turms.turms.workflow.service.impl.ExpirableModelService;
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
import java.util.stream.Collectors;

import static im.turms.turms.constant.DaoConstant.ID_FIELD_NAME;

/**
 * @author James Chen
 * @implNote The status of group invitations never become EXPIRED in MongoDB automatically
 * (admins can specify them to expired manually though) even if there is an expireAfter
 * property because Turms will not create a cron job to scan and expire requests in
 * MongoDB. Instead, Turms transforms the status of requests when returning them to users
 * or admins for less resource consumption and better performance to expire requests.
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupInvitationService extends ExpirableModelService<GroupInvitation> {

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserVersionService userVersionService;

    public GroupInvitationService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient,
            GroupMemberService groupMemberService,
            UserVersionService userVersionService,
            GroupVersionService groupVersionService,
            TrivialTaskManager taskManager) {
        super(mongoClient, GroupInvitation.class);
        this.groupMemberService = groupMemberService;
        this.node = node;
        this.mongoClient = mongoClient;
        this.userVersionService = userVersionService;
        this.groupVersionService = groupVersionService;

        // Set up a cron job to remove invitations if deleting expired docs is enabled
        taskManager.reschedule(
                "expiredGroupInvitationsCleanup",
                turmsPropertiesManager.getLocalProperties().getService().getGroup().getExpiredGroupInvitationsCleanupCron(),
                () -> {
                    boolean isLocalNodeLeader = node.isLocalNodeLeader();
                    boolean deleteExpiredRequestsWhenCronTriggered = node.getSharedProperties()
                            .getService()
                            .getGroup()
                            .isDeleteExpiredGroupInvitationsWhenCronTriggered();
                    Date expirationDate = getModelExpirationDate();
                    if (isLocalNodeLeader && deleteExpiredRequestsWhenCronTriggered && expirationDate != null) {
                        removeAllExpiredGroupInvitations(expirationDate).subscribe();
                    }
                });
    }

    public Mono<Void> removeAllExpiredGroupInvitations(Date expirationDate) {
        Filter filter = Filter.newBuilder(1)
                .isExpired(GroupInvitation.Fields.CREATION_DATE, expirationDate);
        return mongoClient.deleteMany(GroupInvitation.class, filter).then();
    }

    public Mono<GroupInvitation> authAndCreateGroupInvitation(
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @NotNull Long inviteeId,
            @Nullable String content) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(inviterId, "inviterId");
            AssertUtil.notNull(inviteeId, "inviteeId");
            validInvitationContentLength(content);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService
                .isAllowedToInviteOrAdd(groupId, inviterId, null)
                .flatMap(pair -> {
                    ServicePermission permission = pair.getLeft();
                    TurmsStatusCode statusCode = permission.code();
                    if (statusCode != TurmsStatusCode.OK) {
                        return Mono.error(TurmsBusinessException.get(statusCode, permission.reason()));
                    }
                    return groupMemberService
                            .isAllowedToBeInvited(groupId, inviteeId)
                            .flatMap(code -> {
                                if (code != TurmsStatusCode.OK) {
                                    return Mono.error(TurmsBusinessException.get(code));
                                }
                                GroupInvitationStrategy strategy = pair.getRight();
                                if (strategy.requiresApproval()) {
                                    String finalContent = content != null ? content : "";
                                    return createGroupInvitation(null, groupId, inviterId, inviteeId, finalContent,
                                            RequestStatus.PENDING, null, null);
                                } else {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.REDUNDANT_GROUP_INVITATION,
                                            "The invitation is redundant under the strategy " + strategy));
                                }
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
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(inviterId, "inviterId");
            AssertUtil.notNull(inviteeId, "inviteeId");
            validInvitationContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        id = id != null ? id : node.nextRandomId(ServiceType.GROUP_INVITATION);
        if (content == null) {
            content = "";
        }
        if (creationDate == null) {
            creationDate = new Date();
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        GroupInvitation groupInvitation =
                new GroupInvitation(id, groupId, inviterId, inviteeId, content, status, creationDate, responseDate);
        return mongoClient.insert(groupInvitation)
                .then(Mono.defer(() -> groupVersionService.updateGroupInvitationsVersion(groupId).onErrorResume(t -> Mono.empty())
                        .then(userVersionService.updateSentGroupInvitationsVersion(inviterId).onErrorResume(t -> Mono.empty()))
                        .then(userVersionService.updateReceivedGroupInvitationsVersion(inviteeId).onErrorResume(t -> Mono.empty()))))
                .thenReturn(groupInvitation);
    }

    public Mono<GroupInvitation> queryGroupIdAndStatus(@NotNull Long invitationId) {
        try {
            AssertUtil.notNull(invitationId, "invitationId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_FIELD_NAME, invitationId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupInvitation.Fields.GROUP_ID, GroupInvitation.Fields.STATUS);
        return queryExpirableData(filter, options)
                .singleOrEmpty();
    }

    /**
     * @return return a empty publisher even if the invitation doesn't exist
     */
    public Mono<Void> recallPendingGroupInvitation(
            @NotNull Long requesterId,
            @NotNull Long invitationId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(invitationId, "invitationId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!node.getSharedProperties()
                .getService().getGroup().isAllowRecallingPendingGroupInvitationByOwnerAndManager()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RECALLING_GROUP_INVITATION_IS_DISABLED));
        }
        return queryGroupIdAndStatus(invitationId)
                .flatMap(invitation -> {
                    RequestStatus requestStatus = invitation.getStatus();
                    if (requestStatus != RequestStatus.PENDING) {
                        String reason = "The invitation is under the status " + requestStatus;
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RECALL_NOT_PENDING_GROUP_INVITATION, reason));
                    }
                    return groupMemberService.isOwnerOrManager(requesterId, invitation.getGroupId())
                            .flatMap(authenticated -> {
                                if (!authenticated) {
                                    return Mono
                                            .error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_RECALL_INVITATION));
                                }
                                Filter filter = Filter.newBuilder(1)
                                        .eq(ID_FIELD_NAME, invitationId);
                                Update update = Update.newBuilder(1)
                                        .set(GroupInvitation.Fields.STATUS, RequestStatus.CANCELED);
                                return mongoClient.updateOne(GroupInvitation.class, filter, update)
                                        .flatMap(result -> {
                                            if (result.getModifiedCount() > 0) {
                                                return groupVersionService.updateGroupInvitationsVersion(invitation.getGroupId())
                                                        .onErrorResume(t -> Mono.empty())
                                                        .then();
                                            } else {
                                                return Mono.empty();
                                            }
                                        });
                            });
                });
    }

    public Flux<GroupInvitation> queryGroupInvitationsByInviteeId(@NotNull Long inviteeId) {
        try {
            AssertUtil.notNull(inviteeId, "inviteeId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupInvitation.Fields.INVITEE_ID, inviteeId);
        return queryExpirableData(filter);
    }

    @UsesNonIndexedData
    public Flux<GroupInvitation> queryGroupInvitationsByInviterId(@NotNull Long inviterId) {
        try {
            AssertUtil.notNull(inviterId, "inviterId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupInvitation.Fields.INVITER_ID, inviterId);
        return queryExpirableData(filter);
    }

    public Flux<GroupInvitation> queryGroupInvitationsByGroupId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupInvitation.Fields.GROUP_ID, groupId);
        return queryExpirableData(filter);
    }

    public Mono<GroupInvitationsWithVersion> queryUserGroupInvitationsWithVersion(
            @NotNull Long userId,
            boolean areSentByUser,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<Date> versionMono = areSentByUser
                ? userVersionService.querySentGroupInvitationsLastUpdatedDate(userId)
                : userVersionService.queryReceivedGroupInvitationsLastUpdatedDate(userId);
        return versionMono
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        Flux<GroupInvitation> invitationFlux = areSentByUser
                                ? queryGroupInvitationsByInviterId(userId)
                                : queryGroupInvitationsByInviteeId(userId);
                        return invitationFlux
                                .collectList()
                                .map(groupInvitations -> {
                                    if (groupInvitations.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    GroupInvitationsWithVersion.Builder builder = GroupInvitationsWithVersion.newBuilder();
                                    int expireAfterSeconds = getModelExpireAfterSeconds();
                                    for (GroupInvitation groupInvitation : groupInvitations) {
                                        var invitation = ProtoModelUtil.groupInvitation2proto(groupInvitation, expireAfterSeconds);
                                        builder.addGroupInvitations(invitation);
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

    public Mono<GroupInvitationsWithVersion> queryGroupInvitationsWithVersion(
            @NotNull Long userId,
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(userId, groupId)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_INVITATION));
                    }
                    return groupVersionService.queryGroupInvitationsVersion(groupId)
                            .flatMap(version -> {
                                if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                                    return queryGroupInvitationsByGroupId(groupId)
                                            .collect(Collectors.toSet())
                                            .map(groupInvitations -> {
                                                if (groupInvitations.isEmpty()) {
                                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                                }
                                                GroupInvitationsWithVersion.Builder builder = GroupInvitationsWithVersion.newBuilder()
                                                        .setLastUpdatedDate(version.getTime());
                                                int expireAfterSeconds = getModelExpireAfterSeconds();
                                                for (GroupInvitation invitation : groupInvitations) {
                                                    builder.addGroupInvitations(
                                                            ProtoModelUtil.groupInvitation2proto(invitation, expireAfterSeconds).build());
                                                }
                                                return builder.build();
                                            });
                                } else {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                                }
                            })
                            .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
                });
    }

    public Mono<Long> queryInviteeIdByInvitationId(@NotNull Long invitationId) {
        try {
            AssertUtil.notNull(invitationId, "invitationId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_FIELD_NAME, invitationId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupInvitation.Fields.INVITEE_ID);
        return mongoClient.findOne(GroupInvitation.class, filter, options)
                .map(GroupInvitation::getInviteeId);
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
        Filter filter = Filter.newBuilder(10)
                .inIfNotNull(ID_FIELD_NAME, ids)
                .inIfNotNull(GroupInvitation.Fields.GROUP_ID, groupIds)
                .inIfNotNull(GroupInvitation.Fields.INVITER_ID, inviterIds)
                .inIfNotNull(GroupInvitation.Fields.INVITEE_ID, inviteeIds)
                .inIfNotNull(GroupInvitation.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupInvitation.Fields.CREATION_DATE, getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(GroupInvitation.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, GroupInvitation.Fields.CREATION_DATE, getModelExpirationDate());
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return queryExpirableData(filter, options);
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
        Filter filter = Filter.newBuilder(10)
                .inIfNotNull(ID_FIELD_NAME, ids)
                .inIfNotNull(GroupInvitation.Fields.GROUP_ID, groupIds)
                .inIfNotNull(GroupInvitation.Fields.INVITER_ID, inviterIds)
                .inIfNotNull(GroupInvitation.Fields.INVITEE_ID, inviteeIds)
                .inIfNotNull(GroupInvitation.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupInvitation.Fields.CREATION_DATE, getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(GroupInvitation.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, GroupInvitation.Fields.CREATION_DATE, getModelExpirationDate());
        return mongoClient.count(GroupInvitation.class, filter);
    }

    public Mono<DeleteResult> deleteInvitations(@Nullable Set<Long> ids) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(ID_FIELD_NAME, ids);
        return mongoClient.deleteMany(GroupInvitation.class, filter);
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
            AssertUtil.notEmpty(invitationIds, "invitationIds");
            validInvitationContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(inviterId, inviteeId, content, status, creationDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(ID_FIELD_NAME, invitationIds);
        Update update = Update
                .newBuilder(5)
                .setIfNotNull(GroupInvitation.Fields.INVITER_ID, inviterId)
                .setIfNotNull(GroupInvitation.Fields.INVITEE_ID, inviteeId)
                .setIfNotNull(GroupInvitation.Fields.CONTENT, content)
                .setIfNotNull(GroupInvitation.Fields.STATUS, status)
                .setIfNotNull(GroupInvitation.Fields.CREATION_DATE, creationDate);
        RequestStatusUtil.updateResponseDateBasedOnStatus(update, status, responseDate);
        return mongoClient.updateMany(GroupInvitation.class, filter, update);
    }

    // Internal methods

    @Override
    protected int getModelExpireAfterSeconds() {
        return node.getSharedProperties()
                .getService()
                .getGroup()
                .getGroupInvitationExpireAfterSeconds();
    }

    private void validInvitationContentLength(@Nullable String content) {
        if (content != null) {
            int contentLimit = node.getSharedProperties().getService().getGroup().getGroupInvitationContentLimit();
            if (contentLimit > 0) {
                AssertUtil.max(content.length(), "content", contentLimit);
            }
        }
    }

}