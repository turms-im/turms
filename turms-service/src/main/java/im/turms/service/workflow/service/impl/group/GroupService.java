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

package im.turms.service.workflow.service.impl.group;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.GroupUpdateStrategy;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.group.GroupsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dao.util.OperationResultUtil;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DateUtil;
import im.turms.server.common.util.ExceptionUtil;
import im.turms.service.bo.ServicePermission;
import im.turms.service.constant.OperationResultConstant;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.dao.domain.group.Group;
import im.turms.service.workflow.dao.domain.group.GroupMember;
import im.turms.service.workflow.dao.domain.group.GroupType;
import im.turms.service.workflow.dao.domain.user.UserPermissionGroup;
import im.turms.service.workflow.service.impl.conversation.ConversationService;
import im.turms.service.workflow.service.impl.message.MessageService;
import im.turms.service.workflow.service.impl.statistics.MetricsService;
import im.turms.service.workflow.service.impl.user.UserPermissionGroupService;
import im.turms.service.workflow.service.impl.user.UserVersionService;
import io.micrometer.core.instrument.Counter;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.service.constant.DaoConstant.DEFAULT_GROUP_TYPE_ID;
import static im.turms.service.constant.DaoConstant.ID_FIELD_NAME;
import static im.turms.service.constant.DaoConstant.TRANSACTION_RETRY;
import static im.turms.service.constant.MetricsConstant.CREATED_GROUPS_COUNTER_NAME;
import static im.turms.service.constant.MetricsConstant.DELETED_GROUPS_COUNTER_NAME;
import static im.turms.service.workflow.dao.domain.group.GroupMember.Fields.ID_GROUP_ID;
import static im.turms.service.workflow.dao.domain.group.GroupMember.Fields.ID_USER_ID;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
@Log4j2
public class GroupService {

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final GroupTypeService groupTypeService;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserVersionService userVersionService;
    private final UserPermissionGroupService userPermissionGroupService;
    private final ConversationService conversationService;
    private final MessageService messageService;

    private final Counter createdGroupsCounter;
    private final Counter deletedGroupsCounter;

    public GroupService(
            Node node,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient,
            GroupMemberService groupMemberService,
            GroupTypeService groupTypeService,
            UserVersionService userVersionService,
            GroupVersionService groupVersionService,
            UserPermissionGroupService userPermissionGroupService,
            ConversationService conversationService,
            MessageService messageService,
            MetricsService metricsService) {
        this.node = node;
        this.mongoClient = mongoClient;
        this.groupTypeService = groupTypeService;
        this.groupMemberService = groupMemberService;
        this.groupVersionService = groupVersionService;
        this.userVersionService = userVersionService;
        this.userPermissionGroupService = userPermissionGroupService;
        this.conversationService = conversationService;
        this.messageService = messageService;

        createdGroupsCounter = metricsService.getRegistry().counter(CREATED_GROUPS_COUNTER_NAME);
        deletedGroupsCounter = metricsService.getRegistry().counter(DELETED_GROUPS_COUNTER_NAME);
    }

    public Mono<Group> createGroup(
            @NotNull Long creatorId,
            @NotNull Long ownerId,
            @Nullable String groupName,
            @Nullable String intro,
            @Nullable String announcement,
            @Nullable @Min(value = 0) Integer minimumScore,
            @Nullable Long groupTypeId,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date deletionDate,
            @Nullable Date muteEndDate,
            @Nullable Boolean isActive) {
        try {
            AssertUtil.notNull(creatorId, "creatorId");
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.min(minimumScore, "minimumScore", 0);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(deletionDate, "deletionDate");
            AssertUtil.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        isActive = isActive != null
                ? isActive
                : node.getSharedProperties().getService().getGroup().isActivateGroupWhenCreated();
        Long groupId = node.nextLargeGapId(ServiceType.GROUP);
        Group group = new Group(groupId, groupTypeId, creatorId, ownerId, groupName, intro,
                announcement, minimumScore, creationDate, deletionDate, muteEndDate, isActive);
        return mongoClient
                .inTransaction(session -> {
                    Date now = new Date();
                    return mongoClient.insert(session, group)
                            .then(Mono.defer(() -> groupMemberService.addGroupMember(
                                            group.getId(),
                                            creatorId,
                                            GroupMemberRole.OWNER,
                                            null,
                                            now,
                                            null,
                                            session)
                                    .then(Mono.defer(() -> {
                                        createdGroupsCounter.increment();
                                        return groupVersionService.upsert(groupId, now)
                                                .onErrorResume(t -> Mono.empty())
                                                .thenReturn(group);
                                    }))));
                })
                .retryWhen(TRANSACTION_RETRY);
    }

    public Mono<Void> authAndDeleteGroup(@NotNull Long requesterId, @NotNull Long groupId) {
        return groupMemberService
                .isOwner(requesterId, groupId)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_TO_DELETE_GROUP));
                    }
                    if (node.getSharedProperties().getService().getNotification().isNotifyMembersAfterGroupDeleted()) {
                        return queryGroupMemberIds(groupId)
                                .collect(Collectors.toSet())
                                .flatMap(memberIds -> deleteGroupsAndGroupMembers(Set.of(groupId), null))
                                .then();
                    } else {
                        return deleteGroupsAndGroupMembers(
                                Set.of(groupId),
                                null)
                                .then();
                    }
                });
    }

    public Mono<Group> authAndCreateGroup(
            @NotNull Long creatorId,
            @NotNull Long ownerId,
            @Nullable String groupName,
            @Nullable String intro,
            @Nullable String announcement,
            @Nullable @Min(value = 0) Integer minimumScore,
            @Nullable Long groupTypeId,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date deletionDate,
            @Nullable Date muteEndDate,
            @Nullable Boolean isActive) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.min(minimumScore, "minimumScore", 0);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(deletionDate, "deletionDate");
            AssertUtil.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (groupTypeId == null) {
            groupTypeId = DEFAULT_GROUP_TYPE_ID;
        }
        Long finalGroupTypeId = groupTypeId;
        return isAllowedToCreateGroupAndHaveGroupType(creatorId, groupTypeId)
                .flatMap(result -> {
                    TurmsStatusCode code = result.code();
                    if (code == TurmsStatusCode.OK) {
                        return createGroup(creatorId,
                                ownerId,
                                groupName,
                                intro,
                                announcement,
                                minimumScore,
                                finalGroupTypeId,
                                creationDate,
                                deletionDate,
                                muteEndDate,
                                isActive);
                    } else {
                        return Mono.error(TurmsBusinessException.get(code, result.reason()));
                    }
                });
    }

    public Mono<DeleteResult> deleteGroupsAndGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Boolean deleteLogically) {
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties()
                    .getService()
                    .getGroup()
                    .isDeleteGroupLogicallyByDefault();
        }
        boolean finalShouldDeleteLogically = deleteLogically;
        return mongoClient
                .inTransaction(session -> {
                    Filter filter = Filter.newBuilder(1)
                            .inIfNotNull(ID_FIELD_NAME, groupIds);
                    Mono<DeleteResult> updateOrRemoveMono;
                    if (finalShouldDeleteLogically) {
                        Update update = Update.newBuilder(1)
                                .set(Group.Fields.DELETION_DATE, new Date());
                        updateOrRemoveMono = mongoClient.updateMany(session, Group.class, filter, update)
                                .map(OperationResultUtil::update2delete);
                    } else {
                        updateOrRemoveMono = mongoClient.deleteMany(session, Group.class, filter);
                    }
                    return updateOrRemoveMono.flatMap(result -> {
                        long count = result.getDeletedCount();
                        if (count > 0) {
                            deletedGroupsCounter.increment(count);
                        }
                        Mono<Void> deleteSequenceIds = groupIds == null
                                ? Mono.empty()
                                : messageService.deleteSequenceIds(true, groupIds);
                        return groupMemberService.deleteAllGroupMembers(groupIds, session, false)
                                .then(conversationService.deleteGroupConversations(groupIds, session))
                                .then(groupVersionService.delete(groupIds, session))
                                .then(deleteSequenceIds
                                        .doOnError(t -> log.error("Failed to remove the message sequence IDs for the group IDs: {}", groupIds, t)))
                                .thenReturn(result);
                    });
                })
                .retryWhen(TRANSACTION_RETRY);
    }

    public Flux<Group> queryGroups(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> typeIds,
            @Nullable Set<Long> creatorIds,
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isActive,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Set<Long> memberIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        return getGroupIdsFromGroupIdsAndMemberIds(ids, memberIds)
                .defaultIfEmpty(Collections.emptySet())
                .flatMapMany(groupIds -> {
                    Filter filter = Filter.newBuilder(11)
                            .inIfNotNull(ID_FIELD_NAME, groupIds)
                            .inIfNotNull(Group.Fields.TYPE_ID, typeIds)
                            .inIfNotNull(Group.Fields.CREATOR_ID, creatorIds)
                            .inIfNotNull(Group.Fields.OWNER_ID, ownerIds)
                            .eqIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                            .addBetweenIfNotNull(Group.Fields.CREATION_DATE, creationDateRange)
                            .addBetweenIfNotNull(Group.Fields.DELETION_DATE, deletionDateRange)
                            .addBetweenIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDateRange);
                    QueryOptions options = QueryOptions.newBuilder(2)
                            .paginateIfNotNull(page, size);
                    return mongoClient.findMany(Group.class, filter, options);
                });
    }

    public Mono<Long> queryGroupTypeId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(Group.Fields.TYPE_ID);
        return mongoClient.findOne(Group.class, filter, options)
                .map(Group::getTypeId);
    }

    public Mono<Integer> queryGroupMinimumScore(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(Group.Fields.MINIMUM_SCORE);
        return mongoClient.findOne(Group.class, filter, options)
                .map(Group::getMinimumScore);
    }

    public Mono<Void> authAndTransferGroupOwnership(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long successorId,
            boolean quitAfterTransfer,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(successorId, "successorId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService
                .isOwner(requesterId, groupId)
                .flatMap(isOwner -> isOwner
                        ? checkAndTransferGroupOwnership(requesterId, groupId, successorId, quitAfterTransfer, session)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_TO_TRANSFER_GROUP)));
    }

    public Mono<Long> queryGroupOwnerId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(Group.Fields.OWNER_ID);
        return mongoClient.findOne(Group.class, filter, options)
                .map(Group::getOwnerId);
    }

    public Mono<UpdateResult> checkAndTransferGroupOwnership(
            @NotEmpty Set<Long> groupIds,
            @NotNull Long successorId,
            boolean quitAfterTransfer,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
            AssertUtil.notNull(successorId, "successorId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        List<Mono<Signal<Void>>> monos = new ArrayList<>(groupIds.size());
        for (Long groupId : groupIds) {
            Mono<Signal<Void>> mono = checkAndTransferGroupOwnership(
                    null,
                    groupId,
                    successorId,
                    quitAfterTransfer,
                    null)
                    .materialize();
            monos.add(mono);
        }
        return Flux.merge(monos)
                .collectList()
                .map(signals -> {
                    int matched = 0;
                    long modified = 0;
                    for (Signal<Void> signal : signals) {
                        if (signal.isOnError()) {
                            if (!ExceptionUtil.isStatusCode(signal.getThrowable(), TurmsStatusCode.TRANSFER_NON_EXISTING_GROUP)) {
                                matched++;
                            }
                        } else if (signal.isOnComplete()) {
                            matched++;
                            modified++;
                        }
                    }
                    return UpdateResult.acknowledged(matched, modified, null);
                });
    }

    public Mono<Void> checkAndTransferGroupOwnership(
            @Nullable Long auxiliaryCurrentOwnerId,
            @NotNull Long groupId,
            @NotNull Long successorId,
            boolean quitAfterTransfer,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(successorId, "successorId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<Long> queryOwnerIdMono = auxiliaryCurrentOwnerId != null
                ? Mono.just(auxiliaryCurrentOwnerId)
                : queryGroupOwnerId(groupId);
        return queryOwnerIdMono
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.TRANSFER_NON_EXISTING_GROUP)))
                .flatMap(ownerId -> groupMemberService
                        .isGroupMember(groupId, successorId)
                        .flatMap(isGroupMember -> !isGroupMember
                                ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.SUCCESSOR_NOT_GROUP_MEMBER))
                                : queryGroupTypeId(groupId))
                        .flatMap(groupTypeId ->
                                isAllowedToCreateGroupAndHaveGroupType(successorId, groupTypeId)
                                        .flatMap(result -> {
                                            TurmsStatusCode code = result.code();
                                            if (code != TurmsStatusCode.OK) {
                                                return Mono.error(TurmsBusinessException.get(code, result.reason()));
                                            }
                                            if (quitAfterTransfer) {
                                                return groupMemberService.deleteGroupMembers(groupId, ownerId, session, false);
                                            } else {
                                                return groupMemberService.updateGroupMember(
                                                        groupId,
                                                        ownerId,
                                                        null,
                                                        GroupMemberRole.MEMBER,
                                                        null,
                                                        null,
                                                        session,
                                                        false);
                                            }
                                        })
                                        .then(groupMemberService.updateGroupMember(
                                                groupId,
                                                successorId,
                                                null,
                                                GroupMemberRole.OWNER,
                                                null,
                                                null,
                                                session,
                                                true))
                                        .then()));
    }

    public Mono<GroupType> queryGroupType(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(Group.Fields.TYPE_ID);
        return mongoClient.findOne(Group.class, filter, options)
                .flatMap(group -> groupTypeService.queryGroupType(group.getTypeId()));
    }

    public Mono<Void> updateGroupInformation(
            @NotNull Long groupId,
            @Nullable Long typeId,
            @Nullable Long creatorId,
            @Nullable Long ownerId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String announcement,
            @Nullable @Min(0) Integer minimumScore,
            @Nullable Boolean isActive,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date deletionDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return updateGroupsInformation(Set.of(groupId),
                typeId,
                creatorId,
                ownerId,
                name,
                intro,
                announcement,
                minimumScore,
                isActive,
                creationDate,
                deletionDate,
                muteEndDate,
                session)
                .then();
    }

    public Mono<UpdateResult> updateGroupsInformation(
            @NotNull Set<Long> groupIds,
            @Nullable Long typeId,
            @Nullable Long creatorId,
            @Nullable Long ownerId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String announcement,
            @Nullable @Min(0) Integer minimumScore,
            @Nullable Boolean isActive,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date deletionDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
            AssertUtil.min(minimumScore, "minimumScore", 0);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(deletionDate, "deletionDate");
            AssertUtil.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(typeId, creatorId, ownerId, name, intro, announcement,
                minimumScore, isActive, creationDate, deletionDate, muteEndDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(ID_FIELD_NAME, groupIds);
        Update update = Update.newBuilder(11)
                .setIfNotNull(Group.Fields.TYPE_ID, typeId)
                .setIfNotNull(Group.Fields.CREATOR_ID, creatorId)
                .setIfNotNull(Group.Fields.OWNER_ID, ownerId)
                .setIfNotNull(Group.Fields.NAME, name)
                .setIfNotNull(Group.Fields.INTRO, intro)
                .setIfNotNull(Group.Fields.ANNOUNCEMENT, announcement)
                .setIfNotNull(Group.Fields.MINIMUM_SCORE, minimumScore)
                .setIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                .setIfNotNull(Group.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(Group.Fields.DELETION_DATE, deletionDate)
                .setIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDate);
        return mongoClient.updateMany(session, Group.class, filter, update)
                .flatMap(result -> {
                    int size = groupIds.size();
                    if (size == 1) {
                        return groupVersionService.updateInformation(groupIds.iterator().next()).thenReturn(result);
                    } else {
                        List<Mono<Boolean>> monos = new ArrayList<>(size);
                        for (Long groupId : groupIds) {
                            Mono<Boolean> mono = groupVersionService.updateInformation(groupId);
                            monos.add(mono);
                        }
                        return Flux.merge(monos).then().thenReturn(result);
                    }
                });
    }

    public Mono<Void> authAndUpdateGroupInformation(
            @Nullable Long requesterId,
            @NotNull Long groupId,
            @Nullable Long typeId,
            @Nullable Long creatorId,
            @Nullable Long ownerId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String announcement,
            @Nullable @Min(0) Integer minimumScore,
            @Nullable Boolean isActive,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable @PastOrPresent Date deletionDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        try {
            AssertUtil.min(minimumScore, "minimumScore", 0);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(deletionDate, "deletionDate");
            AssertUtil.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(typeId, creatorId, ownerId, name, intro, announcement,
                minimumScore, isActive, creationDate, deletionDate, muteEndDate)) {
            return Mono.empty();
        }
        return queryGroupType(groupId)
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.UPDATE_INFO_OF_NON_EXISTING_GROUP)))
                .flatMap(groupType -> {
                    GroupUpdateStrategy groupUpdateStrategy = groupType.getGroupInfoUpdateStrategy();
                    return switch (groupUpdateStrategy) {
                        case OWNER -> groupMemberService.isOwner(requesterId, groupId)
                                .map(isOwner -> isOwner ? TurmsStatusCode.OK : TurmsStatusCode.NOT_OWNER_TO_UPDATE_GROUP_INFO);
                        case OWNER_MANAGER -> groupMemberService.isOwnerOrManager(requesterId, groupId)
                                .map(isOwnerOrManager -> isOwnerOrManager
                                        ? TurmsStatusCode.OK
                                        : TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO);
                        case OWNER_MANAGER_MEMBER -> groupMemberService.isOwnerOrManagerOrMember(requesterId, groupId)
                                .map(isMember -> isMember ? TurmsStatusCode.OK : TurmsStatusCode.NOT_MEMBER_TO_UPDATE_GROUP_INFO);
                        case ALL -> Mono.just(TurmsStatusCode.OK);
                        default -> Mono.error(new IllegalStateException("Unexpected value: " + groupUpdateStrategy));
                    };
                })
                .flatMap(code -> code == TurmsStatusCode.OK
                        ? updateGroupInformation(groupId, typeId, creatorId, ownerId, name, intro,
                        announcement, minimumScore, isActive, creationDate, deletionDate, muteEndDate, session)
                        : Mono.error(TurmsBusinessException.get(code)));
    }

    public Mono<GroupsWithVersion> queryGroupWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        return groupVersionService.queryInfoVersion(groupId)
                .flatMap(version -> DateUtil.isAfterOrSame(lastUpdatedDate, version)
                        ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE))
                        : mongoClient.findById(Group.class, groupId)
                        .map(group -> GroupsWithVersion.newBuilder()
                                .addGroups(ProtoModelUtil.group2proto(group))
                                .setLastUpdatedDate(version.getTime())
                                .build()))
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    private Flux<Group> queryGroups(@NotEmpty List<Long> groupIds) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .in(ID_FIELD_NAME, groupIds);
        return mongoClient.findMany(Group.class, filter);
    }

    public Flux<Long> queryJoinedGroupIds(@NotNull Long memberId) {
        try {
            AssertUtil.notNull(memberId, "memberId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_USER_ID, memberId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(ID_GROUP_ID);
        return mongoClient
                .findMany(GroupMember.class, filter, options)
                .map(groupMember -> groupMember.getKey().getGroupId());
    }

    public Flux<Group> queryJoinedGroups(@NotNull Long memberId) {
        return queryJoinedGroupIds(memberId)
                .collectList()
                .flatMapMany(groupIds -> groupIds.isEmpty()
                        ? Flux.empty()
                        : this.queryGroups(groupIds));
    }

    public Mono<Int64ValuesWithVersion> queryJoinedGroupIdsWithVersion(
            @NotNull Long memberId,
            @Nullable Date lastUpdatedDate) {
        return userVersionService
                .queryJoinedGroupVersion(memberId)
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryJoinedGroupIds(memberId)
                            .collectList()
                            .map(ids -> {
                                if (ids.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                return Int64ValuesWithVersion
                                        .newBuilder()
                                        .addAllValues(ids)
                                        .setLastUpdatedDate(version.getTime())
                                        .build();
                            });
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<GroupsWithVersion> queryJoinedGroupsWithVersion(
            @NotNull Long memberId,
            @Nullable Date lastUpdatedDate) {
        return userVersionService
                .queryJoinedGroupVersion(memberId)
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryJoinedGroups(memberId)
                            .collectList()
                            .map(groups -> {
                                if (groups.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                GroupsWithVersion.Builder builder = GroupsWithVersion.newBuilder();
                                for (Group group : groups) {
                                    builder.addGroups(ProtoModelUtil.group2proto(group));
                                }
                                return builder
                                        .setLastUpdatedDate(version.getTime())
                                        .build();
                            });
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<ServicePermission> isAllowedToCreateGroupAndHaveGroupType(
            @NotNull Long requesterId,
            @NotNull Long groupTypeId) {
        try {
            AssertUtil.notNull(groupTypeId, "groupTypeId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<UserPermissionGroup> userPermissionGroupMono = userPermissionGroupService.queryUserPermissionGroupByUserId(requesterId);
        return userPermissionGroupMono
                .flatMap(userPermissionGroup -> isAllowedToCreateGroup(requesterId, userPermissionGroup)
                        .flatMap(permission -> permission.code() == TurmsStatusCode.OK
                                ? isAllowedHaveGroupType(requesterId, groupTypeId, userPermissionGroup)
                                : Mono.just(ServicePermission.get(permission.code(), permission.reason()))));
    }

    /**
     * @return OK, USER_NOT_ACTIVE, OWNED_RESOURCE_LIMIT_REACHED
     */
    public Mono<ServicePermission> isAllowedToCreateGroup(
            @NotNull Long requesterId,
            @Nullable UserPermissionGroup auxiliaryUserPermissionGroup) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<UserPermissionGroup> userPermissionGroupMono = auxiliaryUserPermissionGroup != null
                ? Mono.just(auxiliaryUserPermissionGroup)
                : userPermissionGroupService.queryUserPermissionGroupByUserId(requesterId);
        return userPermissionGroupMono
                .flatMap(userPermissionGroup -> {
                    Integer ownedGroupLimit = userPermissionGroup.getOwnedGroupLimit();
                    if (ownedGroupLimit == Integer.MAX_VALUE) {
                        return Mono.just(ServicePermission.get(TurmsStatusCode.OK));
                    } else {
                        return countOwnedGroups(requesterId)
                                .map(ownedGroupsNumber -> {
                                    TurmsStatusCode code;
                                    String reason = null;
                                    if (ownedGroupsNumber < ownedGroupLimit) {
                                        code = TurmsStatusCode.OK;
                                    } else {
                                        code = TurmsStatusCode.MAX_OWNED_GROUPS_REACHED;
                                        reason = "The number of groups owned by the requester has reached the limit " + ownedGroupLimit;
                                    }
                                    return ServicePermission.get(code, reason);
                                });
                    }
                })
                .defaultIfEmpty(ServicePermission.get(TurmsStatusCode.NOT_ACTIVE_USER_TO_CREATE_GROUP));
    }

    public Mono<ServicePermission> isAllowedHaveGroupType(
            @NotNull Long requesterId,
            @NotNull Long groupTypeId,
            @Nullable UserPermissionGroup auxiliaryUserPermissionGroup) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupTypeService.groupTypeExists(groupTypeId)
                .flatMap(existed -> {
                    if (!existed) {
                        return Mono.just(ServicePermission.get(TurmsStatusCode.CREATE_GROUP_WITH_NON_EXISTING_GROUP_TYPE));
                    }
                    Mono<UserPermissionGroup> groupMono = auxiliaryUserPermissionGroup != null
                            ? Mono.just(auxiliaryUserPermissionGroup)
                            : userPermissionGroupService.queryUserPermissionGroupByUserId(requesterId);
                    return groupMono.flatMap(userPermissionGroup -> {
                        Set<Long> creatableGroupTypeIds = userPermissionGroup.getCreatableGroupTypeIds();
                        if (!creatableGroupTypeIds.contains(groupTypeId)) {
                            String ids = StringUtils.join(creatableGroupTypeIds, ", ");
                            String reason = "The requester are only allowed to create groups with the types " + ids;
                            return Mono.just(ServicePermission.get(TurmsStatusCode.NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE, reason));
                        }
                        boolean hasUnlimitedGroups = userPermissionGroup.getOwnedGroupLimitForEachGroupType() == Integer.MAX_VALUE
                                && (userPermissionGroup.getGroupTypeLimits() == null
                                || userPermissionGroup.getGroupTypeLimits()
                                .getOrDefault(groupTypeId, Integer.MAX_VALUE) == Integer.MAX_VALUE);
                        if (hasUnlimitedGroups) {
                            return Mono.just(ServicePermission.OK);
                        }
                        return countOwnedGroups(requesterId, groupTypeId)
                                .map(ownedGroupsNumber -> {
                                    boolean canCreate = ownedGroupsNumber < userPermissionGroup.getOwnedGroupLimitForEachGroupType()
                                            && userPermissionGroup.getGroupTypeLimits().getOrDefault(groupTypeId, Integer.MAX_VALUE) <
                                            Integer.MAX_VALUE;
                                    TurmsStatusCode code = canCreate ? TurmsStatusCode.OK : TurmsStatusCode.MAX_OWNED_GROUPS_REACHED;
                                    return ServicePermission.get(code);
                                });
                    });
                });
    }

    public Mono<Long> countOwnedGroups(@NotNull Long ownerId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(Group.Fields.OWNER_ID, ownerId);
        return mongoClient.count(Group.class, filter);
    }

    public Mono<Long> countOwnedGroups(
            @NotNull Long ownerId,
            @NotNull Long groupTypeId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(groupTypeId, "groupTypeId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .eq(Group.Fields.OWNER_ID, ownerId)
                .eq(Group.Fields.TYPE_ID, groupTypeId);
        return mongoClient.count(Group.class, filter);
    }

    public Mono<Long> countCreatedGroups(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder(3)
                .addBetweenIfNotNull(Group.Fields.CREATION_DATE, dateRange)
                .eq(Group.Fields.DELETION_DATE, null);
        return mongoClient.count(Group.class, filter);
    }

    public Mono<Long> countGroups(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> typeIds,
            @Nullable Set<Long> creatorIds,
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isActive,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Set<Long> memberIds) {
        return getGroupIdsFromGroupIdsAndMemberIds(ids, memberIds)
                .defaultIfEmpty(Collections.emptySet())
                .flatMap(groupIds -> {
                    Filter filter = Filter.newBuilder(11)
                            .inIfNotNull(ID_FIELD_NAME, groupIds)
                            .inIfNotNull(Group.Fields.TYPE_ID, typeIds)
                            .inIfNotNull(Group.Fields.CREATOR_ID, creatorIds)
                            .inIfNotNull(Group.Fields.OWNER_ID, ownerIds)
                            .eqIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                            .addBetweenIfNotNull(Group.Fields.CREATION_DATE, creationDateRange)
                            .addBetweenIfNotNull(Group.Fields.DELETION_DATE, deletionDateRange)
                            .addBetweenIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDateRange);
                    return mongoClient.count(Group.class, filter);
                });
    }

    public Mono<Long> countDeletedGroups(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder(2)
                .addBetweenIfNotNull(Group.Fields.DELETION_DATE, dateRange);
        return mongoClient.count(Group.class, filter);
    }

    public Mono<Long> count() {
        return mongoClient.countAll(Group.class);
    }

    public Flux<Long> queryGroupMemberIds(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(ID_USER_ID);
        return mongoClient.findMany(GroupMember.class, filter, options)
                .map(member -> member.getKey().getUserId());
    }

    public Mono<Boolean> isGroupMuted(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .eq(ID_FIELD_NAME, groupId)
                .gt(Group.Fields.MUTE_END_DATE, new Date());
        return mongoClient.exists(Group.class, filter);
    }

    public Mono<Boolean> isGroupActiveAndNotDeleted(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(3)
                .eq(ID_FIELD_NAME, groupId)
                .eq(Group.Fields.IS_ACTIVE, true)
                .eq(Group.Fields.DELETION_DATE, null);
        return mongoClient.exists(Group.class, filter);
    }

    private Mono<Set<Long>> getGroupIdsFromGroupIdsAndMemberIds(@Nullable Set<Long> groupIds, @Nullable Set<Long> memberIds) {
        if (memberIds != null) {
            Mono<Set<Long>> joinedGroupIdsMono = groupMemberService
                    .queryUsersJoinedGroupIds(memberIds, null, null)
                    .collect(Collectors.toSet());
            return groupIds != null ?
                    joinedGroupIdsMono.map(ids -> {
                        ids.addAll(groupIds);
                        return ids;
                    })
                    : joinedGroupIdsMono;
        } else {
            return groupIds != null ? Mono.just(groupIds) : Mono.just(Collections.emptySet());
        }
    }

}