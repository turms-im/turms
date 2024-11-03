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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
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

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import io.micrometer.core.instrument.Counter;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Signal;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.common.LongsWithVersion;
import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.client.dto.model.group.GroupsWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
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
import im.turms.service.domain.user.po.UserRole;
import im.turms.service.domain.user.service.UserRoleService;
import im.turms.service.domain.user.service.UserVersionService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.storage.elasticsearch.ElasticsearchManager;
import im.turms.service.storage.elasticsearch.model.Hit;
import im.turms.service.storage.elasticsearch.model.doc.GroupDoc;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_ID;
import static im.turms.service.infra.metrics.MetricNameConst.TURMS_BUSINESS_GROUP_CREATED;
import static im.turms.service.infra.metrics.MetricNameConst.TURMS_BUSINESS_GROUP_DELETED;
import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupService.class);

    private final Node node;
    private final ElasticsearchManager elasticsearchManager;
    private final GroupRepository groupRepository;

    private final GroupInfoUserCustomAttributesService groupInfoUserCustomAttributesService;
    private final GroupTypeService groupTypeService;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserVersionService userVersionService;
    private final UserRoleService userRoleService;
    private final ConversationService conversationService;
    private final MessageService messageService;

    private final Counter createdGroupsCounter;
    private final Counter deletedGroupsCounter;

    private boolean activateGroupWhenCreated;
    private boolean allowGroupOwnerChangeGroupType;
    private boolean deleteGroupLogicallyByDefault;

    private final Cache<Long, GroupType> groupIdToGroupTypeCache;

    /**
     * @param messageService is lazy because messageService -> groupService -> messageService
     */
    public GroupService(
            Node node,
            ElasticsearchManager elasticsearchManager,
            TurmsPropertiesManager propertiesManager,
            GroupRepository groupRepository,
            GroupInfoUserCustomAttributesService groupInfoUserCustomAttributesService,
            GroupTypeService groupTypeService,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            UserVersionService userVersionService,
            UserRoleService userRoleService,
            ConversationService conversationService,
            @Lazy MessageService messageService,
            MetricsService metricsService) {
        this.node = node;
        this.elasticsearchManager = elasticsearchManager;
        this.groupRepository = groupRepository;
        this.groupInfoUserCustomAttributesService = groupInfoUserCustomAttributesService;
        this.groupTypeService = groupTypeService;
        this.groupMemberService = groupMemberService;
        this.groupVersionService = groupVersionService;
        this.userVersionService = userVersionService;
        this.userRoleService = userRoleService;
        this.conversationService = conversationService;
        this.messageService = messageService;

        createdGroupsCounter = metricsService.getRegistry()
                .counter(TURMS_BUSINESS_GROUP_CREATED);
        deletedGroupsCounter = metricsService.getRegistry()
                .counter(TURMS_BUSINESS_GROUP_DELETED);

        // TODO: make configurable
        groupIdToGroupTypeCache = Caffeine.newBuilder()
                .maximumSize(10_000)
                .expireAfterWrite(Duration.ofHours(1))
                .build();

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        GroupProperties groupProperties = properties.getService()
                .getGroup();
        activateGroupWhenCreated = groupProperties.isActivateGroupWhenCreated();
        allowGroupOwnerChangeGroupType = groupProperties.isAllowGroupOwnerChangeGroupType();
        deleteGroupLogicallyByDefault = groupProperties.isDeleteGroupLogicallyByDefault();
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
                isActive,
                null);

        Boolean putEsDocInTransaction =
                elasticsearchManager.isGroupUseCaseEnabled() && StringUtil.isNotBlank(groupName)
                        ? elasticsearchManager.isTransactionWithMongoEnabledForGroup()
                        : null;

        Mono<Group> addGroup = groupRepository.inTransaction(session -> {
            Mono<Void> mono = groupRepository.insert(group, session);
            if (Boolean.TRUE.equals(putEsDocInTransaction)) {
                mono = mono.then(elasticsearchManager.putGroupDoc(groupId, groupName));
            }
            return mono
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
                    .thenReturn(group);
        })
                .retryWhen(TRANSACTION_RETRY);
        if (Boolean.FALSE.equals(putEsDocInTransaction)) {
            addGroup = addGroup.doOnSuccess(ignored -> elasticsearchManager
                    .putGroupDoc(groupId, groupName)
                    .subscribe(null,
                            t -> LOGGER.error("Caught an error while putting the doc of the group: "
                                    + groupId, t)));
        }
        return addGroup;
    }

    /**
     * @return group member IDs
     */
    public Mono<Set<Long>> authAndDeleteGroup(
            boolean queryGroupMemberIds,
            @NotNull Long requesterId,
            @NotNull Long groupId) {
        return groupMemberService.isOwner(requesterId, groupId, false)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_GROUP_OWNER_TO_DELETE_GROUP));
                    }
                    if (queryGroupMemberIds) {
                        return groupMemberService.queryGroupMemberIds(groupId, false)
                                // TODO: handle the case when we are deleting group members while
                                // another application is adding new group members
                                .flatMap(memberIds -> deleteGroupsAndGroupMembers(Set.of(groupId),
                                        null).thenReturn(memberIds));
                    }
                    return deleteGroupsAndGroupMembers(Set.of(groupId), null)
                            .then((Mono<Set<Long>>) PublisherPool.EMPTY_SET);
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
        Mono<DeleteResult> delete = groupRepository.inTransaction(session -> {
            Mono<DeleteResult> updateOrDeleteMono;
            if (finalShouldDeleteLogically) {
                updateOrDeleteMono = groupRepository.updateGroupsDeletionDate(groupIds, session)
                        .map(OperationResultConvertor::update2delete);
                // For logical deletion, we don't need to update the group doc in Elasticsearch.
            } else {
                updateOrDeleteMono = groupRepository.deleteByIds(groupIds, session);
                if (elasticsearchManager.isGroupUseCaseEnabled()
                        && elasticsearchManager.isTransactionWithMongoEnabledForGroup()) {
                    updateOrDeleteMono = updateOrDeleteMono.flatMap(result -> (groupIds == null
                            ? elasticsearchManager.deleteAllGroupDocs()
                            : elasticsearchManager.deleteGroupDocs(groupIds)).thenReturn(result));
                }
            }
            return updateOrDeleteMono.flatMap(result -> {
                long count = result.getDeletedCount();
                if (count > 0) {
                    deletedGroupsCounter.increment(count);
                }
                Mono<Long> deleteSequenceIds = groupIds == null
                        ? PublisherPool.LONG_ZERO
                        : messageService.deleteGroupMessageSequenceIds(groupIds);
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
        if (elasticsearchManager.isGroupUseCaseEnabled()
                && !elasticsearchManager.isTransactionWithMongoEnabledForGroup()) {
            delete = delete.doOnSuccess(result -> (groupIds == null
                    ? elasticsearchManager.deleteAllGroupDocs()
                    : elasticsearchManager.deleteGroupDocs(groupIds)).subscribe(null,
                            t -> LOGGER.error("Failed to delete the docs of the groups: "
                                    + groupIds, t)));
        }
        return delete;
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
        if (CollectionUtil.isEmpty(memberIds)) {
            return groupRepository.findGroups(ids,
                    typeIds,
                    creatorIds,
                    ownerIds,
                    isActive,
                    creationDateRange,
                    deletionDateRange,
                    lastUpdatedDateRange,
                    muteEndDateRange,
                    page,
                    size);
        }
        return queryGroupIdsFromGroupIdsAndMemberIds(ids, memberIds).flatMapMany(groupIds -> {
            if (groupIds.isEmpty()) {
                return Flux.empty();
            }
            return groupRepository.findGroups(groupIds,
                    typeIds,
                    creatorIds,
                    ownerIds,
                    isActive,
                    creationDateRange,
                    deletionDateRange,
                    lastUpdatedDateRange,
                    muteEndDateRange,
                    page,
                    size);
        });
    }

    /**
     * TODO: remove and keep {@link GroupService#queryGroupTypeIfActiveAndNotDeleted(Long, boolean)}
     * only.
     */
    public Mono<GroupType> queryGroupTypeIfActiveAndNotDeleted(@NotNull Long groupId) {
        return queryGroupTypeIfActiveAndNotDeleted(groupId, false);
    }

    public Mono<GroupType> queryGroupTypeIfActiveAndNotDeleted(
            @NotNull Long groupId,
            boolean preferCache) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (preferCache) {
            GroupType groupType = groupIdToGroupTypeCache.getIfPresent(groupId);
            if (groupType != null) {
                return Mono.just(groupType);
            }
        }
        return groupRepository.findTypeIdIfActiveAndNotDeleted(groupId)
                .flatMap(groupTypeService::queryGroupType)
                .doOnNext(groupType -> groupIdToGroupTypeCache.put(groupId, groupType));
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
                                .get(ResponseStatusCode.NOT_GROUP_OWNER_TO_TRANSFER_GROUP)));
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
                                    ResponseStatusCode.TRANSFER_NONEXISTENT_GROUP)) {
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
                .switchIfEmpty(ResponseExceptionPublisherPool.transferNonexistentGroup())
                .flatMap(ownerId -> {
                    if (successorId.equals(ownerId)) {
                        return Mono.empty();
                    }
                    return groupMemberService.isGroupMember(groupId, successorId, false)
                            .flatMap(isGroupMember -> isGroupMember
                                    ? queryGroupTypeId(groupId)
                                    : Mono.error(ResponseException.get(
                                            ResponseStatusCode.GROUP_SUCCESSOR_NOT_GROUP_MEMBER)))
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
            @Nullable Map<String, Object> userDefinedAttributes,
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
                userDefinedAttributes,
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
            @Nullable Map<String, Object> userDefinedAttributes,
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
                muteEndDate)
                && (userDefinedAttributes == null || userDefinedAttributes.isEmpty())) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        if (!elasticsearchManager.isGroupUseCaseEnabled() || name == null) {
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
                    userDefinedAttributes,
                    session);
        }
        if (elasticsearchManager.isTransactionWithMongoEnabledForGroup()) {
            if (session == null) {
                return groupRepository.inTransaction(clientSession -> groupRepository
                        .updateGroups(groupIds,
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
                                userDefinedAttributes,
                                clientSession)
                        .flatMap(updateResult -> (StringUtil.isBlank(name)
                                ? elasticsearchManager.deleteGroupDocs(groupIds)
                                : elasticsearchManager.putGroupDocs(groupIds, name))
                                .thenReturn(updateResult)));
            } else {
                return groupRepository
                        .updateGroups(groupIds,
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
                                userDefinedAttributes,
                                session)
                        .flatMap(updateResult -> (StringUtil.isBlank(name)
                                ? elasticsearchManager.deleteGroupDocs(groupIds)
                                : elasticsearchManager.putGroupDocs(groupIds, name))
                                .thenReturn(updateResult));
            }
        }
        return groupRepository
                .updateGroups(groupIds,
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
                        userDefinedAttributes,
                        session)
                .doOnSuccess(updateResult -> (StringUtil.isBlank(name)
                        ? elasticsearchManager.deleteGroupDocs(groupIds)
                        : elasticsearchManager.putGroupDocs(groupIds, name)).subscribe(null,
                                t -> LOGGER.error("Failed to update the docs of the groups: "
                                        + groupIds, t)));
    }

    public Mono<Void> authAndUpdateGroupInformation(
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
            @Nullable Map<String, Value> userDefinedAttributes,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.min(minimumScore, "minimumScore", 0);
            Validator.pastOrPresent(creationDate, "creationDate");
            Validator.pastOrPresent(deletionDate, "deletionDate");
            Validator.before(creationDate, deletionDate, "creationDate", "deletionDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Void> checkIfAllowedToUpdateTypeId;
        if (typeId == null) {
            checkIfAllowedToUpdateTypeId = Mono.empty();
        } else {
            if (allowGroupOwnerChangeGroupType) {
                checkIfAllowedToUpdateTypeId =
                        isAllowedUpdateGroupToGroupType(requesterId, typeId, null)
                                .flatMap(permission -> {
                                    ResponseStatusCode code = permission.code();
                                    return code == ResponseStatusCode.OK
                                            ? Mono.empty()
                                            : Mono.error(ResponseException.get(code,
                                                    permission.reason()));
                                });
            } else {
                checkIfAllowedToUpdateTypeId = Mono.error(
                        ResponseException.get(ResponseStatusCode.UPDATING_GROUP_TYPE_IS_DISABLED));
            }
        }
        boolean noUserDefinedAttribute =
                userDefinedAttributes == null || userDefinedAttributes.isEmpty();
        if (Validator.areAllNull(creatorId,
                ownerId,
                name,
                intro,
                announcement,
                minimumScore,
                isActive,
                creationDate,
                deletionDate,
                muteEndDate) && noUserDefinedAttribute) {
            return checkIfAllowedToUpdateTypeId;
        }
        return checkIfAllowedToUpdateTypeId
                .then(Mono.defer(() -> queryGroupTypeIfActiveAndNotDeleted(groupId)
                        .switchIfEmpty(Mono.error(ResponseException
                                .get(ResponseStatusCode.UPDATE_INFO_OF_NONEXISTENT_GROUP)))
                        .flatMap(groupType -> {
                            GroupUpdateStrategy groupUpdateStrategy =
                                    groupType.getGroupInfoUpdateStrategy();
                            return switch (groupUpdateStrategy) {
                                case OWNER -> groupMemberService
                                        .isOwner(requesterId, groupId, false)
                                        .map(isOwner -> isOwner
                                                ? ResponseStatusCode.OK
                                                : ResponseStatusCode.NOT_GROUP_OWNER_TO_UPDATE_GROUP_INFO);
                                case OWNER_MANAGER -> groupMemberService
                                        .isOwnerOrManager(requesterId, groupId, false)
                                        .map(isOwnerOrManager -> isOwnerOrManager
                                                ? ResponseStatusCode.OK
                                                : ResponseStatusCode.NOT_GROUP_OWNER_OR_MANAGER_TO_UPDATE_GROUP_INFO);
                                case OWNER_MANAGER_MEMBER -> groupMemberService
                                        .isOwnerOrManagerOrMember(requesterId, groupId, false)
                                        .map(isMember -> isMember
                                                ? ResponseStatusCode.OK
                                                : ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_INFO);
                                case ALL -> Mono.just(ResponseStatusCode.OK);
                            };
                        })
                        .flatMap(code -> {
                            if (code != ResponseStatusCode.OK) {
                                return Mono.error(ResponseException.get(code));
                            }
                            if (noUserDefinedAttribute) {
                                return updateGroupInformation(groupId,
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
                                        null,
                                        session);
                            }
                            return groupInfoUserCustomAttributesService
                                    .parseAttributesForUpsert(userDefinedAttributes)
                                    .flatMap(attributes -> updateGroupInformation(groupId,
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
                                            attributes,
                                            session));
                        })));
    }

    public Mono<List<Group>> authAndQueryGroups(
            @Nullable Set<Long> groupIds,
            @Nullable String name,
            @Nullable Date lastUpdatedDate,
            @Nullable Integer skip,
            @Nullable Integer limit,
            @Nullable List<Integer> fieldsToHighlight) {
        if (StringUtil.isNotBlank(name)) {
            return search(skip, limit, groupIds, name, fieldsToHighlight);
        }
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
        Mono<UserRole> userRoleMono =
                userRoleService.queryStoredOrDefaultUserRoleByUserId(requesterId);
        return userRoleMono.flatMap(userRole -> isAllowedToCreateGroup(requesterId, userRole)
                .flatMap(permission -> permission.code() == ResponseStatusCode.OK
                        ? isAllowedCreateGroupWithGroupType(requesterId, groupTypeId, userRole)
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
            @Nullable UserRole auxiliaryUserRole) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<UserRole> userRoleMono = auxiliaryUserRole == null
                ? userRoleService.queryStoredOrDefaultUserRoleByUserId(requesterId)
                : Mono.just(auxiliaryUserRole);
        return userRoleMono.flatMap(userRole -> {
            Integer ownedGroupLimit = userRole.getOwnedGroupLimit();
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

    /**
     * @implNote We don't need to check if the group count with the specified type owned by the
     *           requester exceeds here because it is implemented in
     *           {@link GroupService#isAllowedToCreateGroup}
     */
    public Mono<ServicePermission> isAllowedCreateGroupWithGroupType(
            @NotNull Long requesterId,
            @NotNull Long groupTypeId,
            @Nullable UserRole auxiliaryUserRole) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupTypeService.groupTypeExists(groupTypeId)
                .flatMap(existed -> {
                    if (!existed) {
                        return Mono.just(ServicePermission
                                .get(ResponseStatusCode.CREATE_GROUP_WITH_NONEXISTENT_GROUP_TYPE));
                    }
                    Mono<UserRole> userRoleMono = auxiliaryUserRole != null
                            ? Mono.just(auxiliaryUserRole)
                            : userRoleService.queryStoredOrDefaultUserRoleByUserId(requesterId);
                    return userRoleMono.flatMap(userRole -> {
                        Set<Long> creatableGroupTypeIds = userRole.getCreatableGroupTypeIds();
                        if (!creatableGroupTypeIds.contains(groupTypeId)) {
                            List<Long> sortedIds = new ArrayList<>(creatableGroupTypeIds);
                            sortedIds.sort(null);
                            String ids = StringUtil.joinLatin1(", ", sortedIds);
                            String reason =
                                    "The requester is only allowed to create groups with the types: "
                                            + ids;
                            return Mono.just(ServicePermission.get(
                                    ResponseStatusCode.NO_PERMISSION_TO_CREATE_GROUP_WITH_GROUP_TYPE,
                                    reason));
                        }
                        Integer ownedGroupLimit = userRole.getGroupTypeIdToLimit()
                                .getOrDefault(groupTypeId,
                                        userRole.getOwnedGroupLimitForEachGroupType());
                        if (ownedGroupLimit == Integer.MAX_VALUE) {
                            return Mono.just(ServicePermission.OK);
                        }
                        return countOwnedGroups(requesterId, groupTypeId)
                                .map(ownedGroupCount -> ServicePermission
                                        .get(ownedGroupCount < ownedGroupLimit
                                                ? ResponseStatusCode.OK
                                                : ResponseStatusCode.MAX_OWNED_GROUPS_REACHED));
                    });
                });
    }

    public Mono<ServicePermission> isAllowedUpdateGroupToGroupType(
            @NotNull Long requesterId,
            @NotNull Long groupTypeId,
            @Nullable UserRole auxiliaryUserRole) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupTypeService.groupTypeExists(groupTypeId)
                .flatMap(existed -> {
                    if (!existed) {
                        return Mono.just(ServicePermission
                                .get(ResponseStatusCode.UPDATE_GROUP_TO_NONEXISTENT_GROUP_TYPE));
                    }
                    Mono<UserRole> userRoleMono = auxiliaryUserRole != null
                            ? Mono.just(auxiliaryUserRole)
                            : userRoleService.queryStoredOrDefaultUserRoleByUserId(requesterId);
                    return userRoleMono.flatMap(userRole -> {
                        Set<Long> creatableGroupTypeIds = userRole.getCreatableGroupTypeIds();
                        if (!creatableGroupTypeIds.contains(groupTypeId)) {
                            List<Long> sortedIds = new ArrayList<>(creatableGroupTypeIds);
                            sortedIds.sort(null);
                            String ids = StringUtil.joinLatin1(", ", sortedIds);
                            String reason =
                                    "The requester is only allowed to update groups to the types: "
                                            + ids;
                            return Mono.just(ServicePermission.get(
                                    ResponseStatusCode.NO_PERMISSION_TO_UPDATE_GROUP_TO_GROUP_TYPE,
                                    reason));
                        }
                        Integer ownedGroupLimit = userRole.getGroupTypeIdToLimit()
                                .getOrDefault(groupTypeId,
                                        userRole.getOwnedGroupLimitForEachGroupType());
                        if (ownedGroupLimit == Integer.MAX_VALUE) {
                            return Mono.just(ServicePermission.OK);
                        }
                        return countOwnedGroups(requesterId, groupTypeId)
                                .map(ownedGroupCount -> ServicePermission
                                        .get(ownedGroupCount < ownedGroupLimit
                                                ? ResponseStatusCode.OK
                                                : ResponseStatusCode.MAX_OWNED_GROUPS_REACHED));
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
        if (CollectionUtil.isEmpty(memberIds)) {
            return groupRepository.countGroups(ids,
                    typeIds,
                    creatorIds,
                    ownerIds,
                    isActive,
                    creationDateRange,
                    deletionDateRange,
                    lastUpdatedDateRange,
                    muteEndDateRange);
        }
        return queryGroupIdsFromGroupIdsAndMemberIds(ids, memberIds).flatMap(groupIds -> {
            if (groupIds.isEmpty()) {
                return PublisherPool.LONG_ZERO;
            }
            return groupRepository.countGroups(groupIds,
                    typeIds,
                    creatorIds,
                    ownerIds,
                    isActive,
                    creationDateRange,
                    deletionDateRange,
                    lastUpdatedDateRange,
                    muteEndDateRange);
        });
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
            @NotEmpty Set<Long> memberIds) {
        Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
        Mono<List<Long>> joinedGroupIdListMono =
                groupMemberService.queryUsersJoinedGroupIds(groupIds, memberIds, null, null)
                        .collect(Collectors.toCollection(recyclableList::getValue));
        return joinedGroupIdListMono.map(CollectionUtil::newSet)
                .doFinally(signalType -> recyclableList.recycle());
    }

    private Mono<List<Group>> search(
            @Nullable Integer skip,
            @Nullable Integer limit,
            @Nullable Set<Long> groupIds,
            @NotNull String name,
            @Nullable List<Integer> fieldsToHighlight) {
        if (!elasticsearchManager.isGroupUseCaseEnabled()) {
            return Mono
                    .error(ResponseException.get(ResponseStatusCode.SEARCHING_GROUP_IS_DISABLED));
        }
        boolean highlight = CollectionUtil.isNotEmpty(fieldsToHighlight);
        return elasticsearchManager
                .searchGroupDocs(skip, limit, name, groupIds, highlight, null, null)
                .flatMap(response -> {
                    List<Hit<GroupDoc>> hits = response.hits()
                            .hits();
                    int count = hits.size();
                    if (count == 0) {
                        return PublisherPool.emptyList();
                    }
                    if (highlight) {
                        Map<Long, String> groupIdToHighlightedName =
                                CollectionUtil.newMapWithExpectedSize(count);
                        List<Long> ids = new ArrayList<>(count);
                        for (Hit<GroupDoc> hit : hits) {
                            Long id = Long.parseLong(hit.id());
                            Collection<List<String>> highlightValues = hit.highlight()
                                    .values();
                            if (!highlightValues.isEmpty()) {
                                List<String> values = highlightValues.iterator()
                                        .next();
                                if (!values.isEmpty()) {
                                    groupIdToHighlightedName.put(id, values.getFirst());
                                }
                            }
                            ids.add(id);
                        }
                        Mono<List<Group>> mono = groupRepository.findNotDeletedGroups(ids, null)
                                .collect(CollectorUtil.toList(count));
                        return mono.map(groups -> {
                            if (groups.isEmpty()) {
                                return Collections.emptyList();
                            }
                            Map<Long, Group> groupIdToGroup =
                                    CollectionUtil.newMapWithExpectedSize(groups.size());
                            for (Group group : groups) {
                                groupIdToGroup.put(group.getId(), group);
                            }
                            for (Map.Entry<Long, String> entry : groupIdToHighlightedName
                                    .entrySet()) {
                                Group group = groupIdToGroup.get(entry.getKey());
                                if (group != null) {
                                    group.setName(entry.getValue());
                                }
                            }
                            return groups;
                        });
                    } else {
                        List<Long> ids = new ArrayList<>(count);
                        for (Hit<GroupDoc> hit : hits) {
                            ids.add(Long.parseLong(hit.id()));
                        }
                        return groupRepository.findNotDeletedGroups(ids, null)
                                .collect(CollectorUtil.toList(count));
                    }
                });
    }

}