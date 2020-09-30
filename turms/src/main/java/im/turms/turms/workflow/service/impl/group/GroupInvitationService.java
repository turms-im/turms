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
import im.turms.common.model.bo.group.GroupInvitationsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.manager.TrivialTaskManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constraint.ValidRequestStatus;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.GroupInvitation;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import im.turms.turms.workflow.service.util.RequestStatusUtil;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.stream.Collectors;

import static im.turms.turms.constant.DaoConstant.ID_FIELD_NAME;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * @author James Chen
 */
@Service
public class GroupInvitationService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserVersionService userVersionService;

    public GroupInvitationService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupMemberService groupMemberService,
            UserVersionService userVersionService,
            GroupVersionService groupVersionService,
            TrivialTaskManager taskManager) {
        this.groupMemberService = groupMemberService;
        this.node = node;
        this.mongoTemplate = mongoTemplate;
        this.userVersionService = userVersionService;
        this.groupVersionService = groupVersionService;

        // Set up the checker for expired group invitations
        taskManager.reschedule(
                "groupInvitationsChecker",
                turmsPropertiesManager.getLocalProperties().getService().getGroup().getExpiredGroupInvitationsCheckerCron(),
                () -> {
                    if (node.isLocalNodeMaster()) {
                        if (node.getSharedProperties().getService().getGroup()
                                .isDeleteExpiredGroupInvitationsWhenCronTriggered()) {
                            removeAllExpiredGroupInvitations().subscribe();
                        } else {
                            updateExpiredRequestsStatus().subscribe();
                        }
                    }
                });
    }

    public Mono<Boolean> removeAllExpiredGroupInvitations() {
        Date now = new Date();
        Query query = new Query()
                .addCriteria(Criteria.where(GroupInvitation.Fields.EXPIRATION_DATE).lt(now));
        return mongoTemplate.remove(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
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
                .addCriteria(Criteria.where(GroupInvitation.Fields.EXPIRATION_DATE).lt(now))
                .addCriteria(Criteria.where(GroupInvitation.Fields.STATUS).is(RequestStatus.PENDING));
        Update update = new Update().set(GroupInvitation.Fields.STATUS, RequestStatus.EXPIRED);
        return mongoTemplate.updateMulti(query, update, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
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
                .flatMap(strategy -> {
                    if (!strategy.isInvitable()) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                    return groupMemberService
                            .isAllowedToBeInvited(groupId, inviteeId)
                            .flatMap(allowedToBeInvited -> {
                                if (allowedToBeInvited == null || !allowedToBeInvited) {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.TARGET_USERS_UNAUTHORIZED));
                                }
                                if (strategy.getGroupInvitationStrategy().requireAcceptance()) {
                                    String finalContent = content != null ? content : "";
                                    return createGroupInvitation(null, groupId, inviterId, inviteeId, finalContent,
                                            RequestStatus.PENDING, null, null, null);
                                } else {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.REDUNDANT_REQUEST));
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
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
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
        id = id != null ? id : node.nextId(ServiceType.GROUP_INVITATION);
        if (content == null) {
            content = "";
        }
        if (creationDate == null) {
            creationDate = new Date();
        }
        if (expirationDate == null) {
            int groupInvitationTimeToLiveHours = node.getSharedProperties().getService().getGroup()
                    .getGroupInvitationTimeToLiveHours();
            if (groupInvitationTimeToLiveHours > 0) {
                Instant expirationInstant = Instant.now().plus(groupInvitationTimeToLiveHours, ChronoUnit.HOURS);
                expirationDate = Date.from(expirationInstant);
            }
        }
        if (status == null) {
            status = RequestStatus.PENDING;
        }
        GroupInvitation groupInvitation = new GroupInvitation(id, groupId, inviterId, inviteeId, content, status, creationDate, responseDate, expirationDate);
        return mongoTemplate.insert(groupInvitation, GroupInvitation.COLLECTION_NAME)
                .flatMap(invitation -> groupVersionService.updateGroupInvitationsVersion(groupId)
                        .then(userVersionService.updateSentGroupInvitationsVersion(inviterId))
                        .then(userVersionService.updateReceivedGroupInvitationsVersion(inviteeId))
                        .thenReturn(invitation));
    }

    public Mono<GroupInvitation> queryGroupIdAndStatus(@NotNull Long invitationId) {
        try {
            AssertUtil.notNull(invitationId, "invitationId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(where(ID_FIELD_NAME).is(invitationId));
        query.fields()
                .include(GroupInvitation.Fields.GROUP_ID)
                .include(GroupInvitation.Fields.STATUS);
        return mongoTemplate.findOne(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
                .map(groupInvitation -> {
                    Date expirationDate = groupInvitation.getExpirationDate();
                    return expirationDate != null
                            && groupInvitation.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis()
                            ? groupInvitation.toBuilder().status(RequestStatus.EXPIRED).build()
                            : groupInvitation;
                });
    }

    public Mono<Boolean> recallPendingGroupInvitation(
            @NotNull Long requesterId,
            @NotNull Long invitationId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (!node.getSharedProperties()
                .getService().getGroup().isAllowRecallingPendingGroupInvitationByOwnerAndManager()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.DISABLED_FUNCTION));
        }
        return queryGroupIdAndStatus(invitationId)
                .flatMap(invitation -> {
                    if (invitation.getStatus() != RequestStatus.PENDING) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.RESOURCES_HAVE_BEEN_HANDLED));
                    }
                    return groupMemberService.isOwnerOrManager(requesterId, invitation.getGroupId())
                            .flatMap(authenticated -> {
                                if (authenticated == null || !authenticated) {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                                }
                                Query query = new Query().addCriteria(where(ID_FIELD_NAME).is(invitationId));
                                Update update = new Update()
                                        .set(GroupInvitation.Fields.STATUS, RequestStatus.CANCELED);
                                return mongoTemplate.updateFirst(query, update, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
                                        .flatMap(result -> result.wasAcknowledged()
                                                ? groupVersionService.updateGroupInvitationsVersion(invitation.getGroupId()).thenReturn(true)
                                                : Mono.just(false));
                            });
                });
    }

    public Flux<GroupInvitation> queryGroupInvitationsByInviteeId(@NotNull Long inviteeId) {
        try {
            AssertUtil.notNull(inviteeId, "inviteeId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(where(GroupInvitation.Fields.INVITEE_ID).is(inviteeId));
        return queryExpirableData(query);
    }

    public Flux<GroupInvitation> queryGroupInvitationsByInviterId(@NotNull Long inviterId) {
        try {
            AssertUtil.notNull(inviterId, "inviterId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(where(GroupInvitation.Fields.INVITER_ID).is(inviterId));
        return queryExpirableData(query);
    }

    public Flux<GroupInvitation> queryGroupInvitationsByGroupId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(where(GroupInvitation.Fields.GROUP_ID).is(groupId));
        return queryExpirableData(query);
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
                                    if (!groupInvitations.isEmpty()) {
                                        GroupInvitationsWithVersion.Builder builder = GroupInvitationsWithVersion.newBuilder();
                                        for (GroupInvitation groupInvitation : groupInvitations) {
                                            builder.addGroupInvitations(ProtoUtil.groupInvitation2proto(groupInvitation));
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

    public Mono<GroupInvitationsWithVersion> queryGroupInvitationsWithVersion(
            @NotNull Long userId,
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        return groupMemberService.isOwnerOrManager(userId, groupId)
                .flatMap(authenticated -> {
                    if (authenticated == null || !authenticated) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
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
                                                GroupInvitationsWithVersion.Builder builder = GroupInvitationsWithVersion.newBuilder();
                                                builder.setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build());
                                                for (GroupInvitation invitation : groupInvitations) {
                                                    builder.addGroupInvitations(ProtoUtil.groupInvitation2proto(invitation).build());
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
        Query query = new Query().addCriteria(where(ID_FIELD_NAME).is(invitationId));
        query.fields().include(GroupInvitation.Fields.INVITEE_ID);
        return mongoTemplate.findOne(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID_FIELD_NAME, ids)
                .addInIfNotNull(GroupInvitation.Fields.GROUP_ID, groupIds)
                .addInIfNotNull(GroupInvitation.Fields.INVITER_ID, inviterIds)
                .addInIfNotNull(GroupInvitation.Fields.INVITEE_ID, inviteeIds)
                .addInIfNotNull(GroupInvitation.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupInvitation.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(GroupInvitation.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(GroupInvitation.Fields.EXPIRATION_DATE, expirationDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME);
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID_FIELD_NAME, ids)
                .addInIfNotNull(GroupInvitation.Fields.GROUP_ID, groupIds)
                .addInIfNotNull(GroupInvitation.Fields.INVITER_ID, inviterIds)
                .addInIfNotNull(GroupInvitation.Fields.INVITEE_ID, inviteeIds)
                .addInIfNotNull(GroupInvitation.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupInvitation.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(GroupInvitation.Fields.RESPONSE_DATE, responseDateRange)
                .addBetweenIfNotNull(GroupInvitation.Fields.EXPIRATION_DATE, expirationDateRange)
                .buildQuery();
        return mongoTemplate.count(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME);
    }

    public Mono<Boolean> deleteInvitations(@Nullable Set<Long> ids) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(ID_FIELD_NAME, ids)
                .buildQuery();
        return mongoTemplate.remove(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
    }

    public Mono<Boolean> updateInvitations(
            @NotEmpty Set<Long> invitationIds,
            @Nullable Long inviterId,
            @Nullable Long inviteeId,
            @Nullable String content,
            @Nullable @ValidRequestStatus RequestStatus status,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date responseDate,
            @Nullable Date expirationDate) {
        try {
            AssertUtil.notEmpty(invitationIds, "invitationIds");
            validInvitationContentLength(content);
            DomainConstraintUtil.validRequestStatus(status);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(responseDate, "responseDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(inviterId, inviteeId, content, status, creationDate, expirationDate)) {
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(where(ID_FIELD_NAME).in(invitationIds));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(GroupInvitation.Fields.INVITER_ID, inviterId)
                .setIfNotNull(GroupInvitation.Fields.INVITEE_ID, inviteeId)
                .setIfNotNull(GroupInvitation.Fields.CONTENT, content)
                .setIfNotNull(GroupInvitation.Fields.STATUS, status)
                .setIfNotNull(GroupInvitation.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(GroupInvitation.Fields.EXPIRATION_DATE, expirationDate)
                .build();
        RequestStatusUtil.updateResponseDateBasedOnStatus(update, status, responseDate);
        return mongoTemplate.updateMulti(query, update, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    private Flux<GroupInvitation> queryExpirableData(Query query) {
        return mongoTemplate.find(query, GroupInvitation.class, GroupInvitation.COLLECTION_NAME)
                .map(groupInvitation -> {
                    Date expirationDate = groupInvitation.getExpirationDate();
                    return expirationDate != null
                            && groupInvitation.getStatus() == RequestStatus.PENDING
                            && expirationDate.getTime() < System.currentTimeMillis()
                            ? groupInvitation.toBuilder().status(RequestStatus.EXPIRED).build()
                            : groupInvitation;
                });
    }

    private void validInvitationContentLength(@Nullable String content) {
        if (content != null) {
            int contentLimit = node.getSharedProperties().getService().getGroup().getGroupJoinRequestContentLimit();
            if (contentLimit > 0) {
                AssertUtil.max(content.length(), "content", contentLimit);
            }
        }
    }

}
