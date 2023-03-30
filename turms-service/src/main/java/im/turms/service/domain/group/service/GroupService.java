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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import io.micrometer.core.instrument.Counter;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.common.LongsWithVersion;
import im.turms.server.common.access.client.dto.model.group.GroupsWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.group.GroupProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.operation.OperationResultConvertor;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.group.bo.GroupUpdateStrategy;
import im.turms.service.domain.group.po.Group;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.repository.GroupRepository;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.domain.observation.service.MetricsService;
import im.turms.service.domain.user.po.UserPermissionGroup;
import im.turms.service.domain.user.service.UserPermissionGroupService;
import im.turms.service.domain.user.service.UserVersionService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_ID;
import static im.turms.service.infra.metrics.MetricNameConst.CREATED_GROUPS_COUNTER;
import static im.turms.service.infra.metrics.MetricNameConst.DELETED_GROUPS_COUNTER;
import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

    private final Node node;
    private final GroupRepository groupRepository;
    private final GroupTypeService groupTypeService;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserVersionService userVersionService;
    private final UserPermissionGroupService userPermissionGroupService;
    private final ConversationService conversationService;
    private final MessageService messageService;

    private final Counter createdGroupsCounter;
    private final Counter deletedGroupsCounter;

    private boolean activateGroupWhenCreated;
    private boolean deleteGroupLogicallyByDefault;
    private boolean notifyMembersAfterGroupDeleted;

    public GroupService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            GroupRepository groupRepository,
            GroupMemberService groupMemberService,
            GroupTypeService groupTypeService,
            UserVersionService userVersionService,
            GroupVersionService groupVersionService,
            UserPermissionGroupService userPermissionGroupService,
            ConversationService conversationService,
            MessageService messageService,
            MetricsService metricsService) {
        this.node = node;
        this.groupRepository = groupRepository;
        this.groupTypeService = groupTypeService;
        this.groupMemberService = groupMemberService;
        this.groupVersionService = groupVersionService;
        this.userVersionService = userVersionService;
        this.userPermissionGroupService = userPermissionGroupService;
        this.conversationService = conversationService;
        this.messageService = messageService;

        createdGroupsCounter = metricsService.getRegistry()
                .counter(CREATED_GROUPS_COUNTER);
        deletedGroupsCounter = metricsService.getRegistry()
                .counter(DELETED_GROUPS_COUNTER);

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        GroupProperties groupProperties = properties.getService()
                .getGroup();
        activateGroupWhenCreated = groupProperties.isActivateGroupWhenCreated();
        deleteGroupLogicallyByDefault = groupProperties.isDeleteGroupLogicallyByDefault();
        notifyMembersAfterGroupDeleted = properties.getService()
                .getNotification()
                .isNotifyMembersAfterGroupDeleted();
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
            Validator.notNull(creatorId, "creatorId");
            Validator.notNull(ownerId, "ownerId");
            Validator.min(minimumScore, "minimumScore", 0);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(deletionDate, "deletionDate");
            Validator.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        isActive = isActive == null
                ? activateGroupWhenCreated
                : isActive;
        Long groupId = node.nextLargeGapId(ServiceType.GROUP);
        Date now = new Date();
        Group group = new Group(
                groupId,
                groupTypeId,
                creatorId,
                ownerId,
                groupName,
                intro,
                announcement,
                minimumScore == null
                        ? 0
                        : minimumScore,
                creationDate == null
                        ? now
                        : creationDate,
                deletionDate,
                now,
                muteEndDate,
                isActive);
        return groupRepository.inTransaction(session -> groupRepository.insert(group, session)
                .then(groupMemberService.addGroupMember(group
                        .getId(), creatorId, GroupMemberRole.OWNER, null, now, null, session))
                .then(Mono.defer(() -> {
                    createdGroupsCounter.increment();
                    return groupVersionService.upsert(groupId, now)
                            .onErrorResume(t -> {
                                LOGGER.error(
                                        "Caught an error while upserting a version for the group ({}) after creating the group",
                                        groupId,
                                        t);
                                return Mono.empty();
                            });
                }))
                .thenReturn(group))
                .retryWhen(TRANSACTION_RETRY);
    }

    public Mono<Void> authAndDeleteGroup(@NotNull Long requesterId, @NotNull Long groupId) {
        return groupMemberService.isOwner(requesterId, groupId, false)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_OWNER_TO_DELETE_GROUP));
                    }
                    if (notifyMembersAfterGroupDeleted) {
                        return groupMemberService.queryGroupMemberIds(groupId, false)
                                .flatMap(memberIds -> deleteGroupsAndGroupMembers(Set.of(groupId),
                                        null))
                                .then();
                    }
                    return deleteGroupsAndGroupMembers(Set.of(groupId), null).then();
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
            Validator.notNull(ownerId, "ownerId");
            Validator.min(minimumScore, "minimumScore", 0);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(deletionDate, "deletionDate");
            Validator.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (groupTypeId == null) {
            groupTypeId = DEFAULT_GROUP_TYPE_ID;
        }
        Long finalGroupTypeId = groupTypeId;
        return isAllowedToCreateGroupAndHaveGroupType(creatorId, groupTypeId).flatMap(result -> {
            ResponseStatusCode code = result.code();
            if (code == ResponseStatusCode.OK) {
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
            }
            return Mono.error(ResponseException.get(code, result.reason()));
        });
    }

    public Mono<DeleteResult> deleteGroupsAndGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Boolean deleteLogically) {
        if (deleteLogically == null) {
            deleteLogically = deleteGroupLogicallyByDefault;
        }
        boolean finalShouldDeleteLogically = deleteLogically;
        return groupRepository.inTransaction(session -> {
            Mono<DeleteResult> updateOrDeleteMono = finalShouldDeleteLogically
                    ? groupRepository.updateGroupsDeletionDate(groupIds, session)
                            .map(OperationResultConvertor::update2delete)
                    : groupRepository.deleteByIds(groupIds, session);
            return updateOrDeleteMono.flatMap(result -> {
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
                        .then(deleteSequenceIds.doOnError(t -> LOGGER.error(
                                "Failed to remove the message sequence IDs for the group IDs: {}",
                                groupIds,
                                t)))
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
            @Nullable DateRange lastUpdatedDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Set<Long> memberIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        return queryGroupIdsFromGroupIdsAndMemberIds(ids, memberIds)
                .defaultIfEmpty(Collections.emptySet())
                .flatMapMany(groupIds -> groupRepository.findGroups(groupIds,
                        typeIds,
                        creatorIds,
                        ownerIds,
                        isActive,
                        creationDateRange,
                        deletionDateRange,
                        lastUpdatedDateRange,
                        muteEndDateRange,
                        page,
                        size));
    }

    public Mono<GroupType> queryGroupTypeIfActiveAndNotDeleted(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.findTypeIdIfActiveAndNotDeleted(groupId)
                .flatMap(groupTypeService::queryGroupType);
    }

    public Mono<Long> queryGroupTypeId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.findTypeId(groupId);
    }

    public Mono<Long> queryGroupTypeIdIfActiveAndNotDeleted(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.findTypeIdIfActiveAndNotDeleted(groupId);
    }

    public Mono<Integer> queryGroupMinimumScore(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.findMinimumScore(groupId);
    }

    public Mono<Void> authAndTransferGroupOwnership(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long successorId,
            boolean quitAfterTransfer,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(successorId, "successorId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (successorId.equals(requesterId)) {
            return Mono.empty();
        }
        return groupMemberService.isOwner(requesterId, groupId, false)
                .flatMap(isOwner -> isOwner
                        ? checkAndTransferGroupOwnership(requesterId,
                                groupId,
                                successorId,
                                quitAfterTransfer,
                                session)
                        : Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_OWNER_TO_TRANSFER_GROUP)));
    }

    public Mono<Long> queryGroupOwnerId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.findOwnerId(groupId);
    }

    public Mono<UpdateResult> checkAndTransferGroupOwnership(
            @NotEmpty Set<Long> groupIds,
            @NotNull Long successorId,
            boolean quitAfterTransfer) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
            Validator.notNull(successorId, "successorId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        int size = groupIds.size();
        List<Mono<Signal<Void>>> monos = new ArrayList<>(size);
        for (Long groupId : groupIds) {
            Mono<Signal<Void>> mono = checkAndTransferGroupOwnership(null,
                    groupId,
                    successorId,
                    quitAfterTransfer,
                    null).materialize();
            monos.add(mono);
        }
        return Flux.merge(monos)
                .collect(CollectorUtil.toList(size))
                .map(signals -> {
                    int matched = 0;
                    long modified = 0;
                    for (Signal<Void> signal : signals) {
                        if (signal.isOnError()) {
                            if (!ThrowableUtil.isStatusCode(signal.getThrowable(),
                                    ResponseStatusCode.TRANSFER_NON_EXISTING_GROUP)) {
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
            Validator.notNull(groupId, "groupId");
            Validator.notNull(successorId, "successorId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Long> queryOwnerIdMono;
        if (auxiliaryCurrentOwnerId == null) {
            queryOwnerIdMono = queryGroupOwnerId(groupId);
        } else {
            if (successorId.equals(auxiliaryCurrentOwnerId)) {
                return Mono.empty();
            }
            queryOwnerIdMono = Mono.just(auxiliaryCurrentOwnerId);
        }
        return queryOwnerIdMono
                .switchIfEmpty(ResponseExceptionPublisherPool.transferNonExistingGroup())
                .flatMap(ownerId -> {
                    if (successorId.equals(ownerId)) {
                        return Mono.empty();
                    }
                    return groupMemberService.isGroupMember(groupId, successorId, false)
                            .flatMap(isGroupMember -> isGroupMember
                                    ? queryGroupTypeId(groupId)
                                    : Mono.error(ResponseException
                                            .get(ResponseStatusCode.SUCCESSOR_NOT_GROUP_MEMBER)))
                            .flatMap(groupTypeId -> isAllowedToCreateGroupAndHaveGroupType(
                                    successorId,
                                    groupTypeId).flatMap(result -> {
                                        ResponseStatusCode code = result.code();
                                        if (code != ResponseStatusCode.OK) {
                                            return Mono.error(
                                                    ResponseException.get(code, result.reason()));
                                        }
                                        if (quitAfterTransfer) {
                                            return groupMemberService.deleteGroupMember(groupId,
                                                    ownerId,
                                                    session,
                                                    false);
                                        }
                                        return groupMemberService.updateGroupMember(groupId,
                                                ownerId,
                                                null,
                                                GroupMemberRole.MEMBER,
                                                null,
                                                null,
                                                session,
                                                false);
                                    })
                                    .then(groupMemberService.updateGroupMember(groupId,
                                            successorId,
                                            null,
                                            GroupMemberRole.OWNER,
                                            null,
                                            null,
                                            session,
                                            true))
                                    .then());
                });
    }

    public Mono<GroupType> queryGroupType(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.findTypeId(groupId)
                .flatMap(groupTypeService::queryGroupType);
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
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
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
                session).then();
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
            Validator.notEmpty(groupIds, "groupIds");
            Validator.min(minimumScore, "minimumScore", 0);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(deletionDate, "deletionDate");
            Validator.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(typeId,
                creatorId,
                ownerId,
                name,
                intro,
                announcement,
                minimumScore,
                isActive,
                creationDate,
                deletionDate,
                muteEndDate)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupRepository.updateGroups(groupIds,
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
                new Date(),
                session);
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
            Validator.min(minimumScore, "minimumScore", 0);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(deletionDate, "deletionDate");
            Validator.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(typeId,
                creatorId,
                ownerId,
                name,
                intro,
                announcement,
                minimumScore,
                isActive,
                creationDate,
                deletionDate,
                muteEndDate)) {
            return Mono.empty();
        }
        return queryGroupType(groupId)
                .switchIfEmpty(Mono.error(ResponseException
                        .get(ResponseStatusCode.UPDATE_INFO_OF_NON_EXISTING_GROUP)))
                .flatMap(groupType -> {
                    GroupUpdateStrategy groupUpdateStrategy =
                            groupType.getGroupInfoUpdateStrategy();
                    return switch (groupUpdateStrategy) {
                        case OWNER -> groupMemberService.isOwner(requesterId, groupId, false)
                                .map(isOwner -> isOwner
                                        ? ResponseStatusCode.OK
                                        : ResponseStatusCode.NOT_OWNER_TO_UPDATE_GROUP_INFO);
                        case OWNER_MANAGER -> groupMemberService
                                .isOwnerOrManager(requesterId, groupId, false)
                                .map(isOwnerOrManager -> isOwnerOrManager
                                        ? ResponseStatusCode.OK
                                        : ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO);
                        case OWNER_MANAGER_MEMBER ->
                            groupMemberService.isOwnerOrManagerOrMember(requesterId, groupId, false)
                                    .map(isMember -> isMember
                                            ? ResponseStatusCode.OK
                                            : ResponseStatusCode.NOT_MEMBER_TO_UPDATE_GROUP_INFO);
                        case ALL -> Mono.just(ResponseStatusCode.OK);
                        default -> Mono.error(new RuntimeException(
                                "Unexpected group update strategy: "
                                        + groupUpdateStrategy));
                    };
                })
                .flatMap(code -> code == ResponseStatusCode.OK
                        ? updateGroupInformation(groupId,
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
                        : Mono.error(ResponseException.get(code)));
    }

    public Mono<List<Group>> authAndQueryGroups(
            @NotNull Set<Long> groupIds,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (groupIds.isEmpty()) {
            return PublisherPool.emptyList();
        }
        return groupRepository.findNotDeletedGroups(groupIds, lastUpdatedDate)
                .collect(CollectorUtil.toList(groupIds.size()));
    }

    private Flux<Group> queryGroups(@NotEmpty List<Long> groupIds) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupRepository.findByIds(groupIds);
    }

    public Flux<Group> queryJoinedGroups(@NotNull Long memberId) {
        return groupMemberService.queryUserJoinedGroupIds(memberId)
                .collect(CollectorUtil.toChunkedList())
                .flatMapMany(groupIds -> groupIds.isEmpty()
                        ? Flux.empty()
                        : this.queryGroups(groupIds));
    }

    public Mono<LongsWithVersion> queryJoinedGroupIdsWithVersion(
            @NotNull Long memberId,
            @Nullable Date lastUpdatedDate) {
        return userVersionService.queryJoinedGroupVersion(memberId)
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    return groupMemberService.queryUserJoinedGroupIds(memberId)
                            .collect(CollectorUtil.toChunkedList())
                            .map(ids -> {
                                if (ids.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                return ClientMessagePool.getLongsWithVersionBuilder()
                                        .addAllLongs(ids)
                                        .setLastUpdatedDate(version.getTime())
                                        .build();
                            });
                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<GroupsWithVersion> queryJoinedGroupsWithVersion(
            @NotNull Long memberId,
            @Nullable Date lastUpdatedDate) {
        return userVersionService.queryJoinedGroupVersion(memberId)
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    return queryJoinedGroups(memberId).collect(CollectorUtil.toChunkedList())
                            .map(groups -> {
                                if (groups.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                GroupsWithVersion.Builder builder =
                                        ClientMessagePool.getGroupsWithVersionBuilder();
                                for (Group group : groups) {
                                    builder.addGroups(ProtoModelConvertor.group2proto(group));
                                }
                                return builder.setLastUpdatedDate(version.getTime())
                                        .build();
                            });
                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<ServicePermission> isAllowedToCreateGroupAndHaveGroupType(
            @NotNull Long requesterId,
            @NotNull Long groupTypeId) {
        try {
            Validator.notNull(groupTypeId, "groupTypeId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<UserPermissionGroup> userPermissionGroupMono =
                userPermissionGroupService.queryUserPermissionGroupByUserId(requesterId);
        return userPermissionGroupMono.flatMap(userPermissionGroup -> isAllowedToCreateGroup(
                requesterId,
                userPermissionGroup)
                .flatMap(permission -> permission.code() == ResponseStatusCode.OK
                        ? isAllowedHaveGroupType(requesterId, groupTypeId, userPermissionGroup)
                        : Mono.just(
                                ServicePermission.get(permission.code(), permission.reason()))));
    }

    /**
     * @return Possible codes: {@link ResponseStatusCode#OK},
     *         {@link ResponseStatusCode#LOGGING_IN_USER_NOT_ACTIVE},
     *         {@link ResponseStatusCode#MAX_OWNED_GROUPS_REACHED}
     */
    public Mono<ServicePermission> isAllowedToCreateGroup(
            @NotNull Long requesterId,
            @Nullable UserPermissionGroup auxiliaryUserPermissionGroup) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<UserPermissionGroup> userPermissionGroupMono = auxiliaryUserPermissionGroup == null
                ? userPermissionGroupService.queryUserPermissionGroupByUserId(requesterId)
                : Mono.just(auxiliaryUserPermissionGroup);
        return userPermissionGroupMono.flatMap(userPermissionGroup -> {
            Integer ownedGroupLimit = userPermissionGroup.getOwnedGroupLimit();
            if (ownedGroupLimit == Integer.MAX_VALUE) {
                return Mono.just(ServicePermission.get(ResponseStatusCode.OK));
            }
            return countOwnedGroups(requesterId).map(ownedGroupsNumber -> {
                ResponseStatusCode code;
                String reason = null;
                if (ownedGroupsNumber < ownedGroupLimit) {
                    code = ResponseStatusCode.OK;
                } else {
                    code = ResponseStatusCode.MAX_OWNED_GROUPS_REACHED;
                    reason = "The number of groups owned by the requester has reached the limit "
                            + ownedGroupLimit;
                }
                return ServicePermission.get(code, reason);
            });
        })
                .defaultIfEmpty(
                        ServicePermission.get(ResponseStatusCode.NOT_ACTIVE_USER_TO_CREATE_GROUP));
    }

    public Mono<ServicePermission> isAllowedHaveGroupType(
            @NotNull Long requesterId,
            @NotNull Long groupTypeId,
            @Nullable UserPermissionGroup auxiliaryUserPermissionGroup) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupTypeService.groupTypeExists(groupTypeId)
                .flatMap(existed -> {
                    if (!existed) {
                        return Mono.just(ServicePermission
                                .get(ResponseStatusCode.CREATE_GROUP_WITH_NON_EXISTING_GROUP_TYPE));
                    }
                    Mono<UserPermissionGroup> groupMono = auxiliaryUserPermissionGroup != null
                            ? Mono.just(auxiliaryUserPermissionGroup)
                            : userPermissionGroupService
                                    .queryUserPermissionGroupByUserId(requesterId);
                    return groupMono.flatMap(userPermissionGroup -> {
                        Set<Long> creatableGroupTypeIds =
                                userPermissionGroup.getCreatableGroupTypeIds();
                        if (!creatableGroupTypeIds.contains(groupTypeId)) {
                            String ids = StringUtil.joinLatin1(", ", creatableGroupTypeIds);
                            String reason =
                                    "The requester is only allowed to create groups with the types: "
                                            + ids;
                            return Mono.just(ServicePermission.get(
                                    ResponseStatusCode.NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE,
                                    reason));
                        }
                        Map<Long, Integer> groupTypeIdToLimit =
                                userPermissionGroup.getGroupTypeIdToLimit();
                        boolean hasUnlimitedGroups = userPermissionGroup
                                .getOwnedGroupLimitForEachGroupType() == Integer.MAX_VALUE
                                && (groupTypeIdToLimit == null
                                        || groupTypeIdToLimit.getOrDefault(groupTypeId,
                                                Integer.MAX_VALUE) == Integer.MAX_VALUE);
                        if (hasUnlimitedGroups) {
                            return Mono.just(ServicePermission.OK);
                        }
                        return countOwnedGroups(requesterId, groupTypeId).map(ownedGroupsNumber -> {
                            boolean canCreate = ownedGroupsNumber < userPermissionGroup
                                    .getOwnedGroupLimitForEachGroupType()
                                    && groupTypeIdToLimit.getOrDefault(groupTypeId,
                                            Integer.MAX_VALUE) < Integer.MAX_VALUE;
                            ResponseStatusCode code = canCreate
                                    ? ResponseStatusCode.OK
                                    : ResponseStatusCode.MAX_OWNED_GROUPS_REACHED;
                            return ServicePermission.get(code);
                        });
                    });
                });
    }

    public Mono<Long> countOwnedGroups(@NotNull Long ownerId) {
        try {
            Validator.notNull(ownerId, "ownerId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.countOwnedGroups(ownerId);
    }

    public Mono<Long> countOwnedGroups(@NotNull Long ownerId, @NotNull Long groupTypeId) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(groupTypeId, "groupTypeId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.countOwnedGroups(ownerId, groupTypeId);
    }

    public Mono<Long> countCreatedGroups(@Nullable DateRange dateRange) {
        return groupRepository.countCreatedGroups(dateRange);
    }

    public Mono<Long> countGroups(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> typeIds,
            @Nullable Set<Long> creatorIds,
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isActive,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable DateRange lastUpdatedDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Set<Long> memberIds) {
        return queryGroupIdsFromGroupIdsAndMemberIds(ids, memberIds)
                .defaultIfEmpty(Collections.emptySet())
                .flatMap(groupIds -> groupRepository.countGroups(groupIds,
                        typeIds,
                        creatorIds,
                        ownerIds,
                        isActive,
                        creationDateRange,
                        deletionDateRange,
                        lastUpdatedDateRange,
                        muteEndDateRange));
    }

    public Mono<Long> countDeletedGroups(@Nullable DateRange dateRange) {
        return groupRepository.countDeletedGroups(dateRange);
    }

    public Mono<Long> count() {
        return groupRepository.countAll();
    }

    public Mono<Boolean> isGroupMuted(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.isGroupMuted(groupId, new Date());
    }

    public Mono<Boolean> isGroupActiveAndNotDeleted(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupRepository.isGroupActiveAndNotDeleted(groupId);
    }

    private Mono<Set<Long>> queryGroupIdsFromGroupIdsAndMemberIds(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> memberIds) {
        if (CollectionUtil.isEmpty(memberIds)) {
            return CollectionUtil.isEmpty(groupIds)
                    ? PublisherPool.emptySet()
                    : Mono.just(groupIds);
        }
        Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
        Mono<List<Long>> joinedGroupIdListMono =
                groupMemberService.queryUsersJoinedGroupIds(memberIds, null, null)
                        .collect(Collectors.toCollection(recyclableList::getValue));
        return (groupIds == null
                ? joinedGroupIdListMono.map(CollectionUtil::newSet)
                : joinedGroupIdListMono
                        .map(groupIdList -> CollectionUtil.newSet(groupIdList, groupIds)))
                .doFinally(signalType -> recyclableList.recycle());
    }

}