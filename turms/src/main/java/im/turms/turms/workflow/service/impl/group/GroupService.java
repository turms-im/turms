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
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.GroupUpdateStrategy;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.group.GroupsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.Group;
import im.turms.turms.workflow.dao.domain.GroupMember;
import im.turms.turms.workflow.dao.domain.GroupType;
import im.turms.turms.workflow.dao.domain.UserPermissionGroup;
import im.turms.turms.workflow.service.impl.statistics.MetricsService;
import im.turms.turms.workflow.service.impl.user.UserPermissionGroupService;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import io.micrometer.core.instrument.Counter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.*;
import java.util.stream.Collectors;

import static im.turms.turms.constant.DaoConstant.*;
import static im.turms.turms.constant.MetricsConstant.CREATED_GROUPS_COUNTER_NAME;
import static im.turms.turms.constant.MetricsConstant.DELETED_GROUPS_COUNTER_NAME;
import static im.turms.turms.workflow.dao.domain.GroupMember.Fields.ID_GROUP_ID;
import static im.turms.turms.workflow.dao.domain.GroupMember.Fields.ID_USER_ID;

/**
 * @author James Chen
 */
@Service
public class GroupService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupTypeService groupTypeService;
    private final GroupMemberService groupMemberService;
    private final UserVersionService userVersionService;
    private final GroupVersionService groupVersionService;
    private final UserPermissionGroupService userPermissionGroupService;

    private final Counter createdGroupsCounter;
    private final Counter deletedGroupsCounter;

    public GroupService(
            Node node,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            @Lazy GroupMemberService groupMemberService,
            GroupTypeService groupTypeService,
            UserVersionService userVersionService,
            GroupVersionService groupVersionService,
            UserPermissionGroupService userPermissionGroupService,
            MetricsService metricsService) {
        this.node = node;
        this.groupMemberService = groupMemberService;
        this.groupTypeService = groupTypeService;
        this.mongoTemplate = mongoTemplate;
        this.userVersionService = userVersionService;
        this.groupVersionService = groupVersionService;
        this.userPermissionGroupService = userPermissionGroupService;

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
        isActive = isActive != null ? isActive : node.getSharedProperties().getService().getGroup().isActivateGroupWhenCreated();
        Long groupId = node.nextId(ServiceType.GROUP);
        Group group = new Group(groupId, groupTypeId, creatorId, ownerId, groupName, intro,
                announcement, minimumScore, creationDate, deletionDate, muteEndDate, isActive);
        return mongoTemplate
                .inTransaction()
                .execute(operations -> {
                    Date now = new Date();
                    return operations.insert(group, Group.COLLECTION_NAME)
                            .zipWith(groupMemberService.addGroupMember(
                                    group.getId(),
                                    creatorId,
                                    GroupMemberRole.OWNER,
                                    null,
                                    now,
                                    null,
                                    operations))
                            .flatMap(results -> {
                                createdGroupsCounter.increment();
                                return groupVersionService.upsert(groupId, now)
                                        .thenReturn(results.getT1());
                            });
                })
                .retryWhen(TRANSACTION_RETRY)
                .singleOrEmpty();
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
                .flatMap(allowed -> allowed != null && allowed
                        ? createGroup(creatorId,
                        ownerId,
                        groupName,
                        intro,
                        announcement,
                        minimumScore,
                        finalGroupTypeId,
                        creationDate,
                        deletionDate,
                        muteEndDate,
                        isActive)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.OWNED_RESOURCE_LIMIT_REACHED)));
    }

    public Mono<Boolean> deleteGroupsAndGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Boolean deleteLogically) {
        if (deleteLogically == null) {
            deleteLogically = node.getSharedProperties()
                    .getService()
                    .getGroup()
                    .isDeleteGroupLogicallyByDefault();
        }
        boolean finalShouldDeleteLogically = deleteLogically;
        return mongoTemplate.inTransaction()
                .execute(operations -> {
                    Query query = QueryBuilder
                            .newBuilder()
                            .addInIfNotNull(ID_FIELD_NAME, groupIds)
                            .buildQuery();
                    Mono<Boolean> updateOrRemoveMono;
                    if (finalShouldDeleteLogically) {
                        Update update = new Update().set(Group.Fields.DELETION_DATE, new Date());
                        updateOrRemoveMono = operations.updateMulti(query, update, Group.class, Group.COLLECTION_NAME)
                                .map(result -> {
                                    long count = result.getModifiedCount();
                                    if (count > 0) {
                                        deletedGroupsCounter.increment(count);
                                    }
                                    return result.wasAcknowledged();
                                });
                    } else {
                        updateOrRemoveMono = operations.remove(query, Group.class, Group.COLLECTION_NAME)
                                .map(result -> {
                                    long count = result.getDeletedCount();
                                    if (count > 0) {
                                        deletedGroupsCounter.increment(count);
                                    }
                                    return result.wasAcknowledged();
                                });
                    }
                    return updateOrRemoveMono.flatMap(acknowledged -> acknowledged != null && acknowledged
                            ? groupMemberService.deleteAllGroupMembers(groupIds, operations, false)
                            .then(groupVersionService.delete(groupIds, operations))
                            : Mono.just(false));
                })
                .retryWhen(TRANSACTION_RETRY)
                .singleOrEmpty();
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
                    Query query = QueryBuilder
                            .newBuilder()
                            .addInIfNotNull(ID_FIELD_NAME, groupIds)
                            .addInIfNotNull(Group.Fields.TYPE_ID, typeIds)
                            .addInIfNotNull(Group.Fields.CREATOR_ID, creatorIds)
                            .addInIfNotNull(Group.Fields.OWNER_ID, ownerIds)
                            .addIsIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                            .addBetweenIfNotNull(Group.Fields.CREATION_DATE, creationDateRange)
                            .addBetweenIfNotNull(Group.Fields.DELETION_DATE, deletionDateRange)
                            .addBetweenIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDateRange)
                            .paginateIfNotNull(page, size);
                    return mongoTemplate.find(query, Group.class, Group.COLLECTION_NAME);
                });
    }

    public Mono<Long> queryGroupTypeId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(groupId));
        return mongoTemplate.findOne(query, Group.class, Group.COLLECTION_NAME)
                .map(Group::getTypeId);
    }

    public Mono<Integer> queryGroupMinimumScore(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(groupId));
        return mongoTemplate.findOne(query, Group.class, Group.COLLECTION_NAME)
                .map(Group::getMinimumScore);
    }

    public Mono<Boolean> authAndTransferGroupOwnership(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long successorId,
            boolean quitAfterTransfer,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(successorId, "successorId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService
                .isOwner(requesterId, groupId)
                .flatMap(isOwner -> isOwner != null && isOwner
                        ? checkAndTransferGroupOwnership(requesterId, groupId, successorId, quitAfterTransfer, operations)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
    }

    public Mono<Long> queryGroupOwnerId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(groupId));
        query.fields().include(Group.Fields.OWNER_ID);
        return mongoTemplate.findOne(query, Group.class, Group.COLLECTION_NAME)
                .map(Group::getOwnerId);
    }

    private Mono<Boolean> checkAndTransferGroupOwnership(
            @Nullable Long helperCurrentOwnerId,
            @NotNull Long groupId,
            @NotNull Long successorId,
            boolean quitAfterTransfer,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(successorId, "successorId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<Long> queryOwnerIdMono = helperCurrentOwnerId != null
                ? Mono.just(helperCurrentOwnerId)
                : queryGroupOwnerId(groupId);
        return queryOwnerIdMono.flatMap(ownerId -> groupMemberService
                .isGroupMember(groupId, successorId)
                .flatMap(isGroupMember -> isGroupMember == null || !isGroupMember
                        ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.SUCCESSOR_NOT_GROUP_MEMBER))
                        : queryGroupTypeId(groupId))
                .flatMap(groupTypeId ->
                        isAllowedToCreateGroupAndHaveGroupType(successorId, groupTypeId)
                                .flatMap(allowed -> {
                                    if (allowed == null || !allowed) {
                                        return Mono.error(TurmsBusinessException
                                                .get(TurmsStatusCode.OWNED_RESOURCE_LIMIT_REACHED));
                                    }
                                    if (quitAfterTransfer) {
                                        return groupMemberService.deleteGroupMembers(
                                                groupId, Set.of(ownerId), operations, false);
                                    } else {
                                        return groupMemberService.updateGroupMember(
                                                groupId,
                                                ownerId,
                                                null,
                                                GroupMemberRole.MEMBER,
                                                null,
                                                null,
                                                operations,
                                                false);
                                    }
                                })
                                .flatMap(isDeletedOrUpdated -> {
                                    if (isDeletedOrUpdated) {
                                        return groupMemberService.updateGroupMember(
                                                groupId,
                                                successorId,
                                                null,
                                                GroupMemberRole.OWNER,
                                                null,
                                                null,
                                                operations,
                                                true);
                                    } else {
                                        return Mono.just(false);
                                    }
                                })));
    }

    public Mono<GroupType> queryGroupType(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(groupId));
        query.fields().include(Group.Fields.TYPE_ID);
        return mongoTemplate.findOne(query, Group.class, Group.COLLECTION_NAME)
                .flatMap(group -> groupTypeService.queryGroupType(group.getTypeId()));
    }

    public Mono<Boolean> updateGroupsInformation(
            @NotEmpty Set<Long> groupIds,
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
            @Nullable ReactiveMongoOperations operations) {
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
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).in(groupIds));
        Update update = UpdateBuilder.newBuilder()
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
                .setIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDate)
                .build();
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.updateMulti(query, update, Group.class, Group.COLLECTION_NAME)
                .flatMap(result -> {
                    if (result.wasAcknowledged()) {
                        ArrayList<Mono<Boolean>> list = new ArrayList<>(groupIds.size());
                        for (Long groupId : groupIds) {
                            list.add(groupVersionService.updateInformation(groupId));
                        }
                        return Flux.merge(list).then(Mono.just(true));
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    public Mono<Boolean> authAndUpdateGroupInformation(
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
            @Nullable ReactiveMongoOperations operations) {
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
            return Mono.just(true);
        }
        return queryGroupType(groupId)
                .flatMap(groupType -> {
                    GroupUpdateStrategy groupUpdateStrategy = groupType.getGroupInfoUpdateStrategy();
                    switch (groupUpdateStrategy) {
                        case OWNER:
                            return groupMemberService.isOwner(requesterId, groupId);
                        case OWNER_MANAGER:
                            return groupMemberService.isOwnerOrManager(requesterId, groupId);
                        case OWNER_MANAGER_MEMBER:
                            return groupMemberService.isOwnerOrManagerOrMember(requesterId, groupId);
                        case ALL:
                            return Mono.just(true);
                        default:
                            return Mono.just(false);
                    }
                })
                .flatMap(authenticated -> authenticated != null && authenticated
                        ? updateGroupsInformation(Set.of(groupId), typeId, creatorId, ownerId, name, intro,
                        announcement, minimumScore, isActive, creationDate, deletionDate, muteEndDate, operations)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
    }

    public Mono<GroupsWithVersion> queryGroupWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        return groupVersionService.queryInfoVersion(groupId)
                .flatMap(version -> lastUpdatedDate == null || lastUpdatedDate.before(version)
                        ? mongoTemplate.findById(groupId, Group.class, Group.COLLECTION_NAME)
                        .map(group -> GroupsWithVersion.newBuilder()
                                .addGroups(ProtoUtil.group2proto(group))
                                .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                .build())
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)))
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    private Flux<Group> queryGroups(@NotEmpty List<Long> groupIds) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).in(groupIds));
        return mongoTemplate.find(query, Group.class, Group.COLLECTION_NAME);
    }

    public Flux<Long> queryJoinedGroupIds(@NotNull Long memberId) {
        try {
            AssertUtil.notNull(memberId, "memberId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_USER_ID).is(memberId));
        query.fields().include(ID_GROUP_ID);
        return mongoTemplate
                .find(query, GroupMember.class)
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
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryJoinedGroupIds(memberId)
                                .collectList()
                                .map(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    return Int64ValuesWithVersion
                                            .newBuilder()
                                            .addAllValues(ids)
                                            .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<GroupsWithVersion> queryJoinedGroupsWithVersion(
            @NotNull Long memberId,
            @Nullable Date lastUpdatedDate) {
        return userVersionService
                .queryJoinedGroupVersion(memberId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryJoinedGroups(memberId)
                                .collectList()
                                .map(groups -> {
                                    if (groups.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    GroupsWithVersion.Builder builder = GroupsWithVersion.newBuilder();
                                    for (Group group : groups) {
                                        builder.addGroups(ProtoUtil.group2proto(group));
                                    }
                                    return builder
                                            .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<Boolean> updateGroups(
            @NotEmpty Set<Long> groupIds,
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
            @Nullable Long successorId,
            @NotNull Boolean quitAfterTransfer) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
            AssertUtil.min(minimumScore, "minimumScore", 0);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(deletionDate, "deletionDate");
            AssertUtil.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(
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
                successorId)) {
            return Mono.just(true);
        }
        return mongoTemplate
                .inTransaction()
                .execute(operations -> {
                    List<Mono<Boolean>> monos = new LinkedList<>();
                    if (successorId != null) {
                        for (Long groupId : groupIds) {
                            Mono<Boolean> transferMono = checkAndTransferGroupOwnership(
                                    null, groupId, successorId, quitAfterTransfer, operations);
                            monos.add(transferMono);
                        }
                    }
                    if (!Validator.areAllNull(typeId, creatorId, ownerId, name, intro, announcement,
                            minimumScore, isActive, creationDate, deletionDate, muteEndDate)) {
                        monos.add(updateGroupsInformation(
                                groupIds, typeId, creatorId, ownerId, name, intro, announcement,
                                minimumScore, isActive, creationDate, deletionDate, muteEndDate,
                                operations));
                    }
                    return monos.isEmpty()
                            ? Mono.just(true)
                            : Mono.when(monos).thenReturn(true);
                })
                .retryWhen(TRANSACTION_RETRY)
                .singleOrEmpty();
    }

    public Mono<Boolean> authAndUpdateGroup(
            @NotNull Long requesterId,
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
            @Nullable Long successorId,
            boolean quitAfterTransfer) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.min(minimumScore, "minimumScore", 0);
            AssertUtil.pastOrPresent(creationDate, "creationDate");
            AssertUtil.pastOrPresent(deletionDate, "deletionDate");
            AssertUtil.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<Boolean> authorizeMono = Mono.just(true);
        if (successorId != null || typeId != null) {
            authorizeMono = groupMemberService.isOwner(requesterId, groupId);
            if (typeId != null) {
                authorizeMono = authorizeMono.flatMap(
                        authorized -> authorized != null && authorized
                                ? isAllowedToCreateGroupAndHaveGroupType(requesterId, typeId)
                                : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
            }
        }
        return authorizeMono.flatMap(authorized -> {
            if (authorized != null && authorized) {
                return mongoTemplate.inTransaction()
                        .execute(operations -> {
                            List<Mono<Boolean>> monos = new LinkedList<>();
                            if (successorId != null) {
                                Mono<Boolean> transferMono = authAndTransferGroupOwnership(
                                        requesterId, groupId, successorId, quitAfterTransfer, operations);
                                monos.add(transferMono);
                            }
                            if (!Validator.areAllNull(typeId, creatorId, ownerId, name, intro, announcement,
                                    minimumScore, isActive, creationDate, deletionDate, muteEndDate)) {
                                Mono<Boolean> updateMono = authAndUpdateGroupInformation(
                                        requesterId, groupId, typeId, creatorId, ownerId, name, intro, announcement,
                                        minimumScore, isActive, creationDate, deletionDate, muteEndDate, operations);
                                monos.add(updateMono);
                            }
                            return monos.isEmpty()
                                    ? Mono.just(true)
                                    : Mono.when(monos).thenReturn(true);
                        })
                        .retryWhen(TRANSACTION_RETRY)
                        .singleOrEmpty();
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
            }
        });
    }

    public Mono<Boolean> isAllowedToCreateGroupAndHaveGroupType(
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
                        .flatMap(isAllowedToCreateGroup -> isAllowedToCreateGroup
                                ? isAllowedHaveGroupType(requesterId, groupTypeId, userPermissionGroup)
                                : Mono.just(false)));
    }

    public Mono<Boolean> isAllowedToCreateGroup(
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
                    if (userPermissionGroup.getOwnedGroupLimit() == Integer.MAX_VALUE) {
                        return Mono.just(true);
                    } else {
                        return countOwnedGroups(requesterId)
                                .map(ownedGroupsNumber -> {
                                    if (ownedGroupsNumber < userPermissionGroup.getOwnedGroupLimit()) {
                                        return true;
                                    } else {
                                        throw TurmsBusinessException.get(TurmsStatusCode.OWNED_RESOURCE_LIMIT_REACHED);
                                    }
                                });
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.OWNED_RESOURCE_LIMIT_REACHED)));
    }

    public Mono<Boolean> isAllowedHaveGroupType(
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
                    if (existed != null && existed) {
                        return auxiliaryUserPermissionGroup != null
                                ? Mono.just(auxiliaryUserPermissionGroup)
                                : userPermissionGroupService.queryUserPermissionGroupByUserId(requesterId);
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.TYPE_NOT_EXISTS));
                    }
                })
                .flatMap(userPermissionGroup -> {
                    if (userPermissionGroup.getCreatableGroupTypeIds().contains(groupTypeId)) {
                        if (userPermissionGroup.getOwnedGroupLimitForEachGroupType() == Integer.MAX_VALUE
                                && (userPermissionGroup.getGroupTypeLimits() == null
                                || userPermissionGroup.getGroupTypeLimits().getOrDefault(groupTypeId, Integer.MAX_VALUE) == Integer.MAX_VALUE)) {
                            return Mono.just(true);
                        } else {
                            return countOwnedGroups(requesterId, groupTypeId)
                                    .map(ownedGroupsNumber -> {
                                        if (ownedGroupsNumber < userPermissionGroup.getOwnedGroupLimitForEachGroupType()
                                                && userPermissionGroup.getGroupTypeLimits().getOrDefault(groupTypeId, Integer.MAX_VALUE) < Integer.MAX_VALUE) {
                                            return true;
                                        } else {
                                            throw TurmsBusinessException.get(TurmsStatusCode.OWNED_RESOURCE_LIMIT_REACHED);
                                        }
                                    });
                        }
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Mono<Long> countOwnedGroups(@NotNull Long ownerId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(Group.Fields.OWNER_ID).is(ownerId));
        return mongoTemplate.count(query, Group.class, Group.COLLECTION_NAME);
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
        Query query = new Query()
                .addCriteria(Criteria.where(Group.Fields.OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(Group.Fields.TYPE_ID).is(groupTypeId));
        return mongoTemplate.count(query, Group.class, Group.COLLECTION_NAME);
    }

    public Mono<Long> countCreatedGroups(@Nullable DateRange dateRange) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Group.Fields.CREATION_DATE, dateRange)
                .add(Criteria.where(Group.Fields.DELETION_DATE).is(null))
                .buildQuery();
        return mongoTemplate.count(query, Group.class, Group.COLLECTION_NAME);
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
                    Query query = QueryBuilder
                            .newBuilder()
                            .addInIfNotNull(ID_FIELD_NAME, groupIds)
                            .addInIfNotNull(Group.Fields.TYPE_ID, typeIds)
                            .addInIfNotNull(Group.Fields.CREATOR_ID, creatorIds)
                            .addInIfNotNull(Group.Fields.OWNER_ID, ownerIds)
                            .addIsIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                            .addBetweenIfNotNull(Group.Fields.CREATION_DATE, creationDateRange)
                            .addBetweenIfNotNull(Group.Fields.DELETION_DATE, deletionDateRange)
                            .addBetweenIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDateRange)
                            .buildQuery();
                    return mongoTemplate.count(query, Group.class, Group.COLLECTION_NAME);
                });
    }

    public Mono<Long> countDeletedGroups(@Nullable DateRange dateRange) {
        Query query = QueryBuilder.newBuilder()
                .addBetweenIfNotNull(Group.Fields.DELETION_DATE, dateRange)
                .buildQuery();
        return mongoTemplate.count(query, Group.class, Group.COLLECTION_NAME);
    }

    public Mono<Long> count() {
        return mongoTemplate.count(new Query(), Group.class, Group.COLLECTION_NAME);
    }

    public Flux<Long> queryGroupMemberIds(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_GROUP_ID).is(groupId));
        query.fields().include(ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getUserId());
    }

    public Mono<Boolean> isGroupMuted(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(ID_FIELD_NAME).is(groupId))
                .addCriteria(Criteria.where(Group.Fields.MUTE_END_DATE).gt(new Date()));
        return mongoTemplate.exists(query, Group.class, Group.COLLECTION_NAME);
    }

    public Mono<Boolean> isGroupActiveAndNotDeleted(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(ID_FIELD_NAME).is(groupId))
                .addCriteria(Criteria.where(Group.Fields.IS_ACTIVE).is(true))
                .addCriteria(Criteria.where(Group.Fields.DELETION_DATE).is(null));
        return mongoTemplate.exists(query, Group.class, Group.COLLECTION_NAME);
    }

    private Mono<Set<Long>> getGroupIdsFromGroupIdsAndMemberIds(@Nullable Set<Long> groupIds, @Nullable Set<Long> memberIds) {
        if (memberIds != null) {
            Mono<Set<Long>> joinedGroupIdsMono = groupMemberService
                    .queryUsersJoinedGroupsIds(memberIds, null, null)
                    .collect(Collectors.toSet());
            return groupIds != null ?
                    joinedGroupIdsMono.map(ids -> {
                        ids.addAll(groupIds);
                        return ids;
                    })
                    : joinedGroupIdsMono;
        } else {
            return groupIds != null ? Mono.just(groupIds) : Mono.empty();
        }
    }

}
