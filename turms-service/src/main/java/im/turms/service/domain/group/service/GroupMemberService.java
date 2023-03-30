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
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.apache.commons.lang3.tuple.Pair;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.group.GroupProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.group.repository.GroupMemberRepository;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidGroupMemberRole;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupMemberService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupMemberService.class);

    private static final Pair<ServicePermission, GroupInvitationStrategy> ADD_USER_TO_INACTIVE_GROUP =
            Pair.of(ServicePermission.get(ResponseStatusCode.ADD_USER_TO_INACTIVE_GROUP), null);

    private static final Pair<ServicePermission, GroupInvitationStrategy> GROUP_INVITER_NOT_MEMBER =
            Pair.of(ServicePermission.get(ResponseStatusCode.GROUP_INVITER_NOT_MEMBER), null);

    private final GroupMemberRepository groupMemberRepository;
    private final GroupService groupService;
    private final GroupBlocklistService groupBlocklistService;
    private final GroupVersionService groupVersionService;
    private final UserStatusService userStatusService;

    private boolean checkIfTargetActiveAndNotDeleted;
    private boolean respondOfflineIfInvisible;

    private final boolean isMemberCacheEnabled;
    private final Cache<Long, Map<GroupMember.Key, GroupMember>> groupIdToMembersCache;

    /**
     * @param groupService          is lazy because: GroupService -> GroupMemberService ->
     *                              GroupService
     * @param groupBlocklistService is lazy because: GroupMemberService -> GroupBlocklistService ->
     *                              GroupMemberService
     */
    public GroupMemberService(
            TurmsPropertiesManager propertiesManager,
            GroupMemberRepository groupMemberRepository,
            @Lazy GroupService groupService,
            GroupVersionService groupVersionService,
            @Lazy GroupBlocklistService groupBlocklistService,
            UserStatusService userStatusService) {
        this.groupService = groupService;
        this.groupBlocklistService = groupBlocklistService;
        this.groupMemberRepository = groupMemberRepository;
        this.groupVersionService = groupVersionService;
        this.userStatusService = userStatusService;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);

        GroupProperties groupProperties = propertiesManager.getGlobalProperties()
                .getService()
                .getGroup();
        int memberCacheExpireAfterSeconds = groupProperties.getMemberCacheExpireAfterSeconds();
        isMemberCacheEnabled = memberCacheExpireAfterSeconds > 0;
        groupIdToMembersCache = isMemberCacheEnabled
                ? Caffeine.newBuilder()
                        // TODO: support dynamic expiration time
                        .expireAfterWrite(Duration.ofSeconds(memberCacheExpireAfterSeconds))
                        .maximumSize(100_000)
                        .build()
                : null;
    }

    private void updateProperties(TurmsProperties properties) {
        checkIfTargetActiveAndNotDeleted = properties.getService()
                .getMessage()
                .isCheckIfTargetActiveAndNotDeleted();
        respondOfflineIfInvisible = properties.getService()
                .getUser()
                .isRespondOfflineIfInvisible();
    }

    public Mono<GroupMember> addGroupMember(
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull @ValidGroupMemberRole GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
            Validator.notNull(groupMemberRole, "groupMemberRole");
            DataValidator.validGroupMemberRole(groupMemberRole);
            Validator.pastOrPresent(joinDate, "joinDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (joinDate == null) {
            joinDate = new Date();
        }
        GroupMember groupMember =
                new GroupMember(groupId, userId, name, groupMemberRole, joinDate, muteEndDate);
        return groupMemberRepository.insert(groupMember, session)
                .then(groupVersionService.updateMembersVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error(
                                    "Caught an error while updating the members version of the group ({}) after adding a group member",
                                    groupId,
                                    t);
                            return Mono.empty();
                        }))
                .then(Mono.fromCallable(() -> {
                    cacheMember(groupMember.getKey()
                            .getGroupId(), groupMember);
                    return groupMember;
                }));
    }

    public Mono<List<GroupMember>> addGroupMembers(
            @NotNull Long groupId,
            @NotNull Set<Long> userIds,
            @NotNull @ValidGroupMemberRole GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userIds, "userIds");
            // TODO: configurable
            Validator.maxSize(userIds, "userIds", 100);
            Validator.notNull(groupMemberRole, "groupMemberRole");
            DataValidator.validGroupMemberRole(groupMemberRole);
            Validator.pastOrPresent(joinDate, "joinDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (userIds.isEmpty()) {
            return PublisherPool.emptyList();
        }
        if (joinDate == null) {
            joinDate = new Date();
        }
        List<GroupMember> groupMembers = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            groupMembers.add(
                    new GroupMember(groupId, userId, name, groupMemberRole, joinDate, muteEndDate));
        }
        return groupMemberRepository.insertAllOfSameType(groupMembers, session)
                .then(groupVersionService.updateMembersVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error(
                                    "Caught an error while updating the members version of the group ({}) after adding a group member",
                                    groupId,
                                    t);
                            return Mono.empty();
                        }))
                .thenReturn(groupMembers);
    }

    /**
     * If the users for group members are already group members, the method will return an error
     */
    public Mono<List<GroupMember>> authAndAddGroupMembers(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Set<Long> userIds,
            @Nullable @ValidGroupMemberRole GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        boolean onlyRequester = CollectionUtil.containsExactly(userIds, requesterId);
        try {
            Validator.notNull(userIds, "userIds");
            // TODO: configurable
            Validator.maxSize(userIds, "userIds", 100);
            if (onlyRequester) {
                if (groupMemberRole == null) {
                    groupMemberRole = GroupMemberRole.MEMBER;
                } else if (groupMemberRole != GroupMemberRole.MEMBER) {
                    return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The role of the new group member for the requester must be MEMBER"));
                }
            }
            Validator.notNull(groupMemberRole, "groupMemberRole");
            DataValidator.validGroupMemberRole(groupMemberRole);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (groupMemberRole == GroupMemberRole.OWNER) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The role of the new member must not be OWNER"));
        }
        if (userIds.isEmpty()) {
            return Mono.empty();
        }
        GroupMemberRole finalGroupMemberRole = groupMemberRole;
        return groupService.queryGroupType(groupId)
                .switchIfEmpty(Mono.error(
                        ResponseException.get(ResponseStatusCode.ADD_USER_TO_INACTIVE_GROUP)))
                .flatMap(groupType -> {
                    if (onlyRequester
                            && groupType
                                    .getJoinStrategy() == GroupJoinStrategy.MEMBERSHIP_REQUEST) {
                        return Mono.just(ResponseStatusCode.OK);
                    }
                    if (groupType.getInvitationStrategy()
                            .requiresApproval()) {
                        return Mono.just(ResponseStatusCode.ADD_USER_TO_GROUP_REQUIRING_INVITATION);
                    }
                    return queryGroupMemberRole(requesterId, groupId, false).map(requesterRole -> {
                        boolean isRequesterRoleHigherThanNewMemberRole =
                                requesterRole.getNumber() < finalGroupMemberRole.getNumber();
                        return isRequesterRoleHigherThanNewMemberRole
                                ? ResponseStatusCode.OK
                                : ResponseStatusCode.ADD_USER_WITH_ROLE_HIGHER_THAN_REQUESTER;
                    })
                            .defaultIfEmpty(ResponseStatusCode.GROUP_INVITER_NOT_MEMBER);
                })
                .flatMap(code -> code == ResponseStatusCode.OK
                        ? groupService.isGroupActiveAndNotDeleted(groupId)
                        : Mono.error(ResponseException.get(code)))
                .flatMap(isGroupActive -> {
                    if (!isGroupActive) {
                        return ResponseExceptionPublisherPool.addBlockedUserToInactiveGroup();
                    }
                    return groupBlocklistService.findBlockedUserIds(groupId, userIds)
                            .collect(CollectorUtil.toList(userIds.size()));
                })
                .flatMap(blockedUserIds -> {
                    if (blockedUserIds.isEmpty()) {
                        return addGroupMembers(groupId,
                                userIds,
                                finalGroupMemberRole,
                                name,
                                new Date(),
                                muteEndDate,
                                session);
                    }
                    String ids = StringUtil.joinLatin1(", ", blockedUserIds);
                    return Mono.error(
                            ResponseException.get(ResponseStatusCode.ADD_BLOCKED_USER_TO_GROUP,
                                    "The blocked user IDs: "
                                            + ids));
                });
    }

    /**
     * @return the deleted member IDs
     */
    public Mono<Set<Long>> authAndDeleteGroupMembers(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Set<Long> memberIdsToDelete,
            @Nullable Long successorId,
            @Nullable Boolean quitAfterTransfer) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupId, "groupId");
            Validator.notNull(memberIdsToDelete, "memberIdsToDelete");
            // TODO: configurable
            Validator.maxSize(memberIdsToDelete, "memberIdsToDelete", 100);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (successorId != null) {
            if (!memberIdsToDelete.isEmpty()) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Cannot transfer the group ownership while removing other members"));
            }
            return groupService
                    .authAndTransferGroupOwnership(requesterId,
                            groupId,
                            successorId,
                            quitAfterTransfer != null && quitAfterTransfer,
                            null)
                    .thenReturn(Set.of(requesterId));
        }
        if (memberIdsToDelete.isEmpty()) {
            return PublisherPool.emptySet();
        }
        boolean quitGroup = memberIdsToDelete.contains(requesterId);
        if (quitGroup) {
            if (memberIdsToDelete.size() > 1) {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Cannot quit a group while removing other members"));
            }
            return isOwner(requesterId, groupId, false).flatMap(isOwner -> isOwner
                    ? Mono.error(ResponseException
                            .get(ResponseStatusCode.OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR))
                    : deleteGroupMember(groupId, requesterId, null, true)
                            .thenReturn(Set.of(requesterId)));
        }
        return queryGroupMemberRole(requesterId, groupId, false).flatMap(requesterRole -> {
            int requesterRoleNumber = requesterRole.getNumber();
            if (requesterRoleNumber > GroupMemberRole.MANAGER_VALUE) {
                return Mono.error(ResponseException
                        .get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER));
            }
            return queryGroupMemberKeyAndRolePairs(memberIdsToDelete, groupId)
                    .flatMap(keyAndRolePairs -> {
                        if (keyAndRolePairs.isEmpty()) {
                            return PublisherPool.emptySet();
                        }
                        int pairCount = keyAndRolePairs.size();
                        Set<Long> finalMemberIdsToDelete;
                        boolean needComputeMemberId;
                        if (pairCount == memberIdsToDelete.size()) {
                            finalMemberIdsToDelete = memberIdsToDelete;
                            needComputeMemberId = false;
                        } else {
                            finalMemberIdsToDelete =
                                    CollectionUtil.newSetWithExpectedSize(pairCount);
                            needComputeMemberId = true;
                        }
                        List<GroupMember.Key> keys = new ArrayList<>(pairCount);
                        for (GroupMember keyAndRole : keyAndRolePairs) {
                            Long userId = keyAndRole.getKey()
                                    .getUserId();
                            if (keyAndRole.getRole()
                                    .getNumber() <= requesterRoleNumber) {
                                return Mono.error(ResponseException.get(
                                        ResponseStatusCode.NOT_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER,
                                        ResponseStatusCode.NOT_OWNER_TO_REMOVE_GROUP_OWNER_OR_MANAGER
                                                .getReason()
                                                + ". No permission to remove the member: "
                                                + userId));
                            }
                            keys.add(new GroupMember.Key(groupId, userId));
                            if (needComputeMemberId) {
                                finalMemberIdsToDelete.add(userId);
                            }
                        }
                        return deleteGroupMembers(keys, null, true)
                                .thenReturn(finalMemberIdsToDelete);
                    });
        });
    }

    public Mono<DeleteResult> deleteGroupMember(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable ClientSession session,
            boolean updateGroupMembersVersion) {
        return deleteGroupMembers(Set.of(new GroupMember.Key(groupId, memberId)),
                session,
                updateGroupMembersVersion);
    }

    public Mono<DeleteResult> deleteGroupMembers(
            @NotEmpty Collection<GroupMember.Key> keys,
            @Nullable ClientSession session,
            boolean updateGroupMembersVersion) {
        Set<Long> groupIds;
        try {
            Validator.notEmpty(keys, "keys");
            groupIds = CollectionUtil.newSetWithExpectedSize(keys.size());
            for (GroupMember.Key key : keys) {
                DataValidator.validGroupMemberKey(key);
                groupIds.add(key.getGroupId());
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberRepository.deleteByIds(keys, session)
                .flatMap(result -> {
                    long deletedCount = result.getDeletedCount();
                    if (deletedCount == keys.size()) {
                        invalidMemberCache(keys);
                    }
                    if (!updateGroupMembersVersion || deletedCount == 0) {
                        return Mono.just(result);
                    }
                    return groupVersionService.updateMembersVersion(groupIds)
                            .onErrorResume(t -> {
                                LOGGER.error(
                                        "Caught an error while updating the members version of the groups {} after deleting group members",
                                        groupIds,
                                        t);
                                return Mono.empty();
                            })
                            .thenReturn(result);
                });
    }

    public Mono<UpdateResult> updateGroupMember(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session,
            boolean updateGroupMembersVersion) {
        try {
            Validator.notNull(memberId, "memberId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return updateGroupMembers(groupId,
                Set.of(memberId),
                name,
                role,
                joinDate,
                muteEndDate,
                session,
                updateGroupMembersVersion);
    }

    public Mono<UpdateResult> updateGroupMembers(
            @NotEmpty Set<GroupMember.Key> keys,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session,
            boolean updateGroupMembersVersion) {
        try {
            Validator.notEmpty(keys, "keys");
            DataValidator.validGroupMemberRole(role);
            Validator.pastOrPresent(joinDate, "joinDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(name, role, joinDate, muteEndDate)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupMemberRepository
                .updateGroupMembers(keys, name, role, joinDate, muteEndDate, session)
                .flatMap(result -> {
                    long modifiedCount = result.getModifiedCount();
                    if (modifiedCount == keys.size()) {
                        updateMembersCache(keys, name, role, joinDate, muteEndDate);
                    }
                    if (!updateGroupMembersVersion && modifiedCount == 0) {
                        return Mono.just(result);
                    }
                    int keyCount = keys.size();
                    Set<Long> groupIds = CollectionUtil.newSetWithExpectedSize(keyCount);
                    for (GroupMember.Key key : keys) {
                        groupIds.add(key.getGroupId());
                    }
                    Mono<?> updateMono = keyCount == 1
                            ? groupVersionService.updateMembersVersion(groupIds.iterator()
                                    .next())
                            : groupVersionService.updateMembersVersion(groupIds);
                    return updateMono.onErrorResume(t -> {
                        LOGGER.error(
                                "Caught an error while updating the members version of the groups {} after updating group members",
                                groupIds,
                                t);
                        return Mono.empty();
                    })
                            .thenReturn(result);
                });
    }

    public Mono<UpdateResult> updateGroupMembers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> memberIds,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session,
            boolean updateGroupMembersVersion) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notEmpty(memberIds, "memberIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Set<GroupMember.Key> keys = CollectionUtil.newSetWithExpectedSize(memberIds.size());
        for (Long memberId : memberIds) {
            keys.add(new GroupMember.Key(groupId, memberId));
        }
        return updateGroupMembers(keys,
                name,
                role,
                joinDate,
                muteEndDate,
                session,
                updateGroupMembersVersion);
    }

    public Mono<Boolean> isGroupMember(
            @NotNull Long groupId,
            @NotNull Long userId,
            boolean preferCache) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        GroupMember.Key key = new GroupMember.Key(groupId, userId);
        if (preferCache && isMemberCacheEnabled) {
            Map<GroupMember.Key, GroupMember> keyToMember =
                    groupIdToMembersCache.getIfPresent(groupId);
            if (keyToMember != null) {
                GroupMember member = keyToMember.get(key);
                return Mono.just(member != null);
            }
        }
        return groupMemberRepository.existsById(key);
    }

    public Mono<Boolean> isGroupMember(@NotEmpty Set<Long> groupIds, @NotNull Long userId) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        List<GroupMember.Key> keys = new ArrayList<>(groupIds.size());
        for (Long groupId : groupIds) {
            keys.add(new GroupMember.Key(groupId, userId));
        }
        return groupMemberRepository.existsByIds(keys);
    }

    public Flux<Long> findExistentMemberGroupIds(
            @NotEmpty Set<Long> groupIds,
            @NotNull Long userId) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        Set<GroupMember.Key> keys = CollectionUtil.newSetWithExpectedSize(groupIds.size());
        for (Long groupId : groupIds) {
            keys.add(new GroupMember.Key(groupId, userId));
        }
        return groupMemberRepository.findIdsByIds(keys)
                .map(groupMember -> groupMember.getKey()
                        .getGroupId());
    }

    public Mono<Pair<ServicePermission, GroupInvitationStrategy>> isAllowedToInviteUser(
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @Nullable @ValidGroupMemberRole GroupMemberRole newMemberRole) {
        try {
            Validator.notNull(inviterId, "inviterId");
            DataValidator.validGroupMemberRole(newMemberRole);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryGroupMemberRole(inviterId, groupId, false)
                .flatMap(inviterRole -> groupService.queryGroupType(groupId)
                        .flatMap(groupType -> {
                            GroupInvitationStrategy strategy = groupType.getInvitationStrategy();
                            ResponseStatusCode code =
                                    isAllowedToInviteUserWithSpecifiedRole(inviterRole,
                                            newMemberRole,
                                            strategy);
                            if (code == ResponseStatusCode.OK) {
                                return Mono.just(Pair.of(ServicePermission.OK, strategy));
                            }
                            String reason = "The inviter with the role ("
                                    + inviterRole
                                    + ") cannot send an invitation under the strategy: "
                                    + strategy;
                            return Mono
                                    .just(Pair.of(ServicePermission.get(code, reason), strategy));
                        })
                        .defaultIfEmpty(ADD_USER_TO_INACTIVE_GROUP))
                .defaultIfEmpty(GROUP_INVITER_NOT_MEMBER);
    }

    /**
     * @return Possible codes: {@link ResponseStatusCode#OK},
     *         {@link ResponseStatusCode#GROUP_INVITEE_ALREADY_GROUP_MEMBER},
     *         {@link ResponseStatusCode#INVITEE_HAS_BEEN_BLOCKED}
     */
    public Mono<ResponseStatusCode> isAllowedToBeInvited(
            @NotNull Long groupId,
            @NotNull Long inviteeId) {
        return isGroupMember(groupId, inviteeId, false).flatMap(isGroupMember -> {
            if (isGroupMember) {
                return Mono.just(ResponseStatusCode.GROUP_INVITEE_ALREADY_GROUP_MEMBER);
            }
            return groupBlocklistService.isBlocked(groupId, inviteeId)
                    .map(isBlocked -> isBlocked
                            ? ResponseStatusCode.INVITEE_HAS_BEEN_BLOCKED
                            : ResponseStatusCode.OK);
        });
    }

    /**
     * Note that a blocked user is never a group member
     */
    public Mono<ResponseStatusCode> isAllowedToSendMessage(
            @NotNull Long groupId,
            @NotNull Long senderId) {
        return isGroupMember(groupId, senderId, true)
                .flatMap(isGroupMember -> isGroupMember != null && isGroupMember
                        ? isGroupMemberAllowedToSendMessage(groupId, senderId)
                        : isGuestAllowedToSendMessage(groupId, senderId));
    }

    /**
     * @return Possible codes: {@link ResponseStatusCode#OK}
     *         {@link ResponseStatusCode#SEND_MESSAGE_TO_MUTED_GROUP}
     *         {@link ResponseStatusCode#SEND_MESSAGE_TO_INACTIVE_GROUP}
     *         {@link ResponseStatusCode#MUTED_MEMBER_SEND_MESSAGE}
     */
    private Mono<ResponseStatusCode> isGroupMemberAllowedToSendMessage(
            @NotNull Long groupId,
            @NotNull Long senderId) {
        return groupService.isGroupMuted(groupId)
                .flatMap(isGroupMuted -> {
                    if (isGroupMuted) {
                        return Mono.just(ResponseStatusCode.SEND_MESSAGE_TO_MUTED_GROUP);
                    }
                    if (!checkIfTargetActiveAndNotDeleted) {
                        return Mono.just(ResponseStatusCode.OK);
                    }
                    return groupService.isGroupActiveAndNotDeleted(groupId)
                            .flatMap(isActive -> isActive
                                    ? isMemberMuted(groupId, senderId, true).map(muted -> muted
                                            ? ResponseStatusCode.MUTED_MEMBER_SEND_MESSAGE
                                            : ResponseStatusCode.OK)
                                    : Mono.just(ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP));
                });
    }

    private Mono<ResponseStatusCode> isGuestAllowedToSendMessage(
            @NotNull Long groupId,
            @NotNull Long senderId) {
        return groupService.queryGroupType(groupId)
                .flatMap(type -> {
                    Boolean speakable = type.getGuestSpeakable();
                    if (speakable == null || !speakable) {
                        return Mono.just(ResponseStatusCode.GUESTS_HAVE_BEEN_MUTED);
                    }
                    return groupService.isGroupMuted(groupId)
                            .flatMap(isGroupMuted -> {
                                if (isGroupMuted) {
                                    return Mono
                                            .just(ResponseStatusCode.SEND_MESSAGE_TO_MUTED_GROUP);
                                }
                                return groupService.isGroupActiveAndNotDeleted(groupId)
                                        .flatMap(isGroupActiveAndNotDeleted -> {
                                            if (isGroupActiveAndNotDeleted) {
                                                return groupBlocklistService
                                                        .isBlocked(groupId, senderId)
                                                        .map(isBlocked -> isBlocked
                                                                ? ResponseStatusCode.GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED
                                                                : ResponseStatusCode.OK);
                                            }
                                            return Mono.just(
                                                    ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
                                        });
                            });
                })
                .defaultIfEmpty(ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
    }

    public Mono<Boolean> isMemberMuted(
            @NotNull Long groupId,
            @NotNull Long userId,
            boolean preferCache) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (preferCache && isMemberCacheEnabled) {
            Map<GroupMember.Key, GroupMember> keyToMember =
                    groupIdToMembersCache.getIfPresent(groupId);
            if (keyToMember != null) {
                GroupMember member = keyToMember.get(new GroupMember.Key(groupId, userId));
                return member != null && member.getMuteEndDate() != null
                        ? PublisherPool.TRUE
                        : PublisherPool.FALSE;
            }
        }
        return groupMemberRepository.isMemberMuted(groupId, userId);
    }

    public Mono<List<GroupMember>> queryGroupMemberKeyAndRolePairs(
            @NotNull Set<Long> userIds,
            @NotNull Long groupId) {
        try {
            Validator.notNull(userIds, "userIds");
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberRepository.findGroupMemberKeyAndRoleParis(userIds, groupId)
                .collect(CollectorUtil.toList(userIds.size()));
    }

    public Mono<GroupMemberRole> queryGroupMemberRole(
            @NotNull Long userId,
            @NotNull Long groupId,
            boolean preferCache) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (preferCache && isMemberCacheEnabled) {
            Map<GroupMember.Key, GroupMember> keyToMember =
                    groupIdToMembersCache.getIfPresent(groupId);
            if (keyToMember != null) {
                GroupMember member = keyToMember.get(new GroupMember.Key(groupId, userId));
                return member == null
                        ? Mono.empty()
                        : Mono.just(member.getRole());
            }
        }
        return groupMemberRepository.findGroupMemberRole(userId, groupId);
    }

    public Mono<Boolean> isOwner(@NotNull Long userId, @NotNull Long groupId, boolean preferCache) {
        return queryGroupMemberRole(userId, groupId, preferCache)
                .map(memberRole -> memberRole == GroupMemberRole.OWNER)
                .switchIfEmpty(PublisherPool.FALSE);
    }

    public Mono<Boolean> isOwnerOrManager(
            @NotNull Long userId,
            @NotNull Long groupId,
            boolean preferCache) {
        return queryGroupMemberRole(userId, groupId, preferCache)
                .map(memberRole -> memberRole == GroupMemberRole.OWNER
                        || memberRole == GroupMemberRole.MANAGER)
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isOwnerOrManagerOrMember(
            @NotNull Long userId,
            @NotNull Long groupId,
            boolean preferCache) {
        return queryGroupMemberRole(userId, groupId, preferCache)
                .map(memberRole -> memberRole == GroupMemberRole.OWNER
                        || memberRole == GroupMemberRole.MANAGER
                        || memberRole == GroupMemberRole.MEMBER)
                .defaultIfEmpty(false);
    }

    public Flux<Long> queryUserJoinedGroupIds(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findUserJoinedGroupIds(userId);
    }

    public Flux<Long> queryUsersJoinedGroupIds(
            @NotEmpty Set<Long> userIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findUsersJoinedGroupIds(userIds, page, size);
    }

    public Mono<Set<Long>> queryMemberIdsInUsersJoinedGroups(
            @NotEmpty Set<Long> userIds,
            boolean preferCache) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
        return queryUsersJoinedGroupIds(userIds, null, null)
                .collect(Collectors.toCollection(recyclableSet::getValue))
                .flatMap(groupIds -> groupIds.isEmpty()
                        ? PublisherPool.<Long>emptySet()
                        : queryGroupMemberIds(groupIds, preferCache))
                .doFinally(signalType -> recyclableSet.recycle());
    }

    public Mono<Set<Long>> queryGroupMemberIds(@NotNull Long groupId, boolean preferCache) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (preferCache && isMemberCacheEnabled) {
            Map<GroupMember.Key, GroupMember> keyToMember =
                    groupIdToMembersCache.getIfPresent(groupId);
            if (keyToMember != null) {
                Collection<GroupMember> members = keyToMember.values();
                Set<Long> memberIds = CollectionUtil.newSetWithExpectedSize(members.size());
                for (GroupMember member : members) {
                    memberIds.add(member.getKey()
                            .getUserId());
                }
                return Mono.just(memberIds);
            }
        }
        Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
        return groupMemberRepository.findMemberIdsByGroupId(groupId)
                .collect(Collectors.toCollection(recyclableSet::getValue))
                .map(CollectionUtil::newSet)
                .doFinally(signalType -> recyclableSet.recycle());
    }

    public Mono<Set<Long>> queryGroupMemberIds(@NotEmpty Set<Long> groupIds, boolean preferCache) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (preferCache && isMemberCacheEnabled) {
            Map<Long, Map<GroupMember.Key, GroupMember>> cachedGroupIdToMembers =
                    groupIdToMembersCache.getAllPresent(groupIds);
            int cachedGroupCount = cachedGroupIdToMembers.size();
            if (cachedGroupCount == groupIds.size()) {
                int memberCount = 0;
                Collection<Map<GroupMember.Key, GroupMember>> idToMemberCache =
                        cachedGroupIdToMembers.values();
                for (Map<GroupMember.Key, GroupMember> idToMember : idToMemberCache) {
                    memberCount += idToMember.size();
                }
                Set<Long> memberIds = CollectionUtil.newSetWithExpectedSize(memberCount);
                for (Map<GroupMember.Key, GroupMember> keyToMember : idToMemberCache) {
                    for (GroupMember member : keyToMember.values()) {
                        memberIds.add(member.getKey()
                                .getUserId());
                    }
                }
                return Mono.just(memberIds);
            } else if (cachedGroupCount > 0) {
                Set<Long> memberIds = UnifiedSet.newSet(groupIds.size() * 50);
                for (Map<GroupMember.Key, GroupMember> keyToMember : cachedGroupIdToMembers
                        .values()) {
                    for (GroupMember.Key key : keyToMember.keySet()) {
                        memberIds.add(key.getUserId());
                    }
                }
                groupIds = UnifiedSet.newSet(groupIds);
                groupIds.removeAll(cachedGroupIdToMembers.keySet());
                return groupMemberRepository.findGroupMemberIds(groupIds)
                        .collect(Collectors.toCollection(() -> memberIds));
            }
        }
        Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
        return groupMemberRepository.findGroupMemberIds(groupIds)
                .collect(Collectors.toCollection(recyclableSet::getValue))
                .map(CollectionUtil::newSet)
                .doFinally(signalType -> recyclableSet.recycle());
    }

    public Mono<List<GroupMember>> queryGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable Set<@ValidGroupMemberRole GroupMemberRole> roles,
            @Nullable DateRange joinDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        if (roles != null) {
            try {
                for (GroupMemberRole role : roles) {
                    DataValidator.validGroupMemberRole(role);
                }
            } catch (ResponseException e) {
                return Mono.error(e);
            }
        }
        Flux<GroupMember> groupsMemberFlux = groupMemberRepository.findGroupsMembers(groupIds,
                userIds,
                roles,
                joinDateRange,
                muteEndDateRange,
                page,
                size);
        if (isMemberCacheEnabled
                && CollectionUtil.isNotEmpty(groupIds)
                && userIds == null
                && roles == null
                && joinDateRange == null
                && muteEndDateRange == null
                && page == null
                && size == null) {
            return groupsMemberFlux.collect(CollectorUtil.toChunkedList())
                    .doOnNext(members -> {
                        int groupCount = groupIds.size();
                        if (groupCount == 1) {
                            cacheMembers(groupIds.iterator()
                                    .next(), members);
                        } else {
                            Map<Long, List<GroupMember>> groupIdToMembers =
                                    CollectionUtil.newMapWithExpectedSize(groupCount);
                            for (GroupMember member : members) {
                                Long groupId = member.getKey()
                                        .getGroupId();
                                List<GroupMember> groupMembers = groupIdToMembers.get(groupId);
                                if (groupMembers == null) {
                                    groupMembers = new ChunkedArrayList<>();
                                    groupIdToMembers.put(groupId, groupMembers);
                                }
                                groupMembers.add(member);
                            }
                            for (Map.Entry<Long, List<GroupMember>> entry : groupIdToMembers
                                    .entrySet()) {
                                cacheMembers(entry.getKey(), entry.getValue());
                            }
                        }
                    });
        }
        return groupsMemberFlux.collect(CollectorUtil.toChunkedList());
    }

    public Mono<Long> countMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable Set<@ValidGroupMemberRole GroupMemberRole> roles,
            @Nullable DateRange joinDateRange,
            @Nullable DateRange muteEndDateRange) {
        if (roles != null) {
            try {
                for (GroupMemberRole role : roles) {
                    DataValidator.validGroupMemberRole(role);
                }
            } catch (ResponseException e) {
                return Mono.error(e);
            }
        }
        return groupMemberRepository
                .countMembers(groupIds, userIds, roles, joinDateRange, muteEndDateRange);
    }

    public Mono<DeleteResult> deleteGroupMembers(boolean updateGroupMembersVersion) {
        return groupMemberRepository.deleteAll()
                .flatMap(result -> updateGroupMembersVersion && result.getDeletedCount() > 0
                        ? groupVersionService.updateMembersVersion()
                                .thenReturn(result)
                        : OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT);
    }

    public Mono<List<GroupMember>> queryGroupMembers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> memberIds,
            boolean preferCache) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notEmpty(memberIds, "memberIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (preferCache && isMemberCacheEnabled) {
            Map<GroupMember.Key, GroupMember> keyToMember =
                    groupIdToMembersCache.getIfPresent(groupId);
            if (keyToMember != null) {
                List<GroupMember> members = new ArrayList<>(memberIds.size());
                for (Long memberId : memberIds) {
                    GroupMember member = keyToMember.get(new GroupMember.Key(groupId, memberId));
                    if (member != null) {
                        members.add(member);
                    }
                }
                return Mono.just(members);
            }
        }
        return groupMemberRepository.findGroupMembers(groupId, memberIds)
                .collect(CollectorUtil.toList(memberIds.size()));
    }

    public Mono<GroupMembersWithVersion> authAndQueryGroupMembers(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotEmpty Set<Long> memberIds,
            boolean withStatus) {
        try {
            Validator.notEmpty(memberIds, "memberIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return isGroupMember(groupId, requesterId, false)
                .flatMap(isGroupMember -> isGroupMember == null || !isGroupMember
                        ? Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_MEMBER_TO_QUERY_MEMBER_INFO))
                        : queryGroupMembers(groupId, memberIds, false))
                .flatMap(members -> {
                    if (members.isEmpty()) {
                        return ResponseExceptionPublisherPool.noContent();
                    }
                    GroupMembersWithVersion.Builder builder =
                            ClientMessagePool.getGroupMembersWithVersionBuilder();
                    if (withStatus) {
                        return fillMembersBuilderWithStatus(members, builder);
                    }
                    for (GroupMember member : members) {
                        builder.addGroupMembers(ProtoModelConvertor.groupMember2proto(member));
                    }
                    return Mono.just(builder.build());
                });
    }

    public Mono<GroupMembersWithVersion> authAndQueryGroupMembersWithVersion(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate,
            boolean withStatus) {
        return isGroupMember(groupId, requesterId, false)
                .flatMap(isGroupMember -> isGroupMember != null && isGroupMember
                        ? groupVersionService.queryMembersVersion(groupId)
                        : Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_MEMBER_TO_QUERY_MEMBER_INFO)))
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    return queryGroupMembers(Set.of(groupId), null, null, null, null, null, null)
                            .flatMap(members -> {
                                if (members.isEmpty()) {
                                    return ResponseExceptionPublisherPool.noContent();
                                }
                                GroupMembersWithVersion.Builder builder =
                                        ClientMessagePool.getGroupMembersWithVersionBuilder();
                                if (withStatus) {
                                    return fillMembersBuilderWithStatus(members, builder);
                                }
                                for (GroupMember member : members) {
                                    builder.addGroupMembers(
                                            ProtoModelConvertor.groupMember2proto(member));
                                }
                                return Mono.just(builder.setLastUpdatedDate(version.getTime())
                                        .build());
                            });
                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<UpdateResult> authAndUpdateGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable Date muteEndDate) {
        try {
            Validator.notNull(memberId, "memberId");
            DataValidator.validGroupMemberRole(role);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (role == GroupMemberRole.OWNER) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Cannot update a group member's role to OWNER"));
        }
        Mono<ResponseStatusCode> isAuthorizedMono;
        if (role != null) {
            isAuthorizedMono = isOwner(requesterId, groupId, false).map(isOwner -> isOwner
                    ? ResponseStatusCode.OK
                    : ResponseStatusCode.NOT_OWNER_TO_UPDATE_GROUP_MEMBER_INFO);
        } else if (muteEndDate != null || (name != null && !requesterId.equals(memberId))) {
            isAuthorizedMono = isOwnerOrManager(requesterId, groupId, false)
                    .map(isOwnerOrManager -> isOwnerOrManager
                            ? ResponseStatusCode.OK
                            : ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO);
        } else {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return isAuthorizedMono.flatMap(code -> code == ResponseStatusCode.OK
                ? updateGroupMember(groupId,
                        memberId,
                        name,
                        role,
                        new Date(),
                        muteEndDate,
                        null,
                        false)
                : Mono.error(ResponseException.get(code)));
    }

    public Mono<DeleteResult> deleteAllGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable ClientSession session,
            boolean updateMembersVersion) {
        return groupMemberRepository.deleteAllGroupMembers(groupIds, session)
                .flatMap(result -> {
                    long deletedCount = result.getDeletedCount();
                    if (deletedCount == 0) {
                        return OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT;
                    }
                    if (groupIds == null) {
                        groupIdToMembersCache.invalidateAll();
                    } else {
                        groupIdToMembersCache.invalidateAll(groupIds);
                    }
                    if (updateMembersVersion) {
                        return groupVersionService.updateMembersVersion(groupIds)
                                .thenReturn(result);
                    }
                    return OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT;
                });
    }

    public Flux<Long> queryGroupManagersAndOwnerId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findGroupManagersAndOwnerId(groupId)
                .map(member -> {
                    GroupMember.Key key = member.getKey();
                    cacheMember(key.getGroupId(), member);
                    return key.getUserId();
                });
    }

    private Mono<GroupMembersWithVersion> fillMembersBuilderWithStatus(
            @NotNull List<GroupMember> members,
            @NotNull GroupMembersWithVersion.Builder builder) {
        try {
            Validator.notNull(members, "members");
            Validator.notNull(builder, "builder");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        List<Mono<UserSessionsStatus>> monoList = new ArrayList<>(members.size());
        for (GroupMember member : members) {
            Long userId = member.getKey()
                    .getUserId();
            monoList.add(userStatusService.getUserSessionsStatus(userId));
        }
        return Mono.zip(monoList, objects -> objects)
                .map(results -> {
                    int memberCount = members.size();
                    for (int i = 0; i < memberCount; i++) {
                        GroupMember member = members.get(i);
                        UserSessionsStatus info = (UserSessionsStatus) results[i];
                        var groupMember =
                                ProtoModelConvertor.userOnlineInfo2groupMember(member.getKey()
                                        .getUserId(), info, respondOfflineIfInvisible);
                        builder.addGroupMembers(groupMember);
                    }
                    return builder.build();
                });
    }

    private ResponseStatusCode isAllowedToInviteUserWithSpecifiedRole(
            @NotNull GroupMemberRole requesterRole,
            @Nullable GroupMemberRole newMemberRole,
            @NotNull GroupInvitationStrategy groupInvitationStrategy) {
        ResponseStatusCode isAllowToAddRole = switch (groupInvitationStrategy) {
            case ALL, ALL_REQUIRING_APPROVAL -> ResponseStatusCode.OK;
            case OWNER, OWNER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    ? ResponseStatusCode.OK
                    : ResponseStatusCode.NOT_OWNER_TO_SEND_INVITATION;
            case OWNER_MANAGER, OWNER_MANAGER_REQUIRING_APPROVAL ->
                requesterRole == GroupMemberRole.OWNER || requesterRole == GroupMemberRole.MANAGER
                        ? ResponseStatusCode.OK
                        : ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION;
            case OWNER_MANAGER_MEMBER, OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL ->
                requesterRole == GroupMemberRole.OWNER
                        || requesterRole == GroupMemberRole.MANAGER
                        || requesterRole == GroupMemberRole.MEMBER
                                ? ResponseStatusCode.OK
                                : ResponseStatusCode.NOT_MEMBER_TO_SEND_INVITATION;
        };
        if (isAllowToAddRole == ResponseStatusCode.OK) {
            boolean isRequesterRoleHigherThanNewMemberRole =
                    newMemberRole == null || requesterRole.getNumber() < newMemberRole.getNumber();
            if (isRequesterRoleHigherThanNewMemberRole) {
                return ResponseStatusCode.OK;
            }
            return ResponseStatusCode.ADD_USER_WITH_ROLE_HIGHER_THAN_REQUESTER;
        }
        return isAllowToAddRole;
    }

    // Cache

    private void cacheMember(Long groupId, GroupMember member) {
        if (!isMemberCacheEnabled) {
            return;
        }
        Map<GroupMember.Key, GroupMember> keyAndMember =
                groupIdToMembersCache.getIfPresent(groupId);
        if (keyAndMember != null) {
            keyAndMember.put(member.getKey(), member);
        }
    }

    private void cacheMembers(Long groupId, List<GroupMember> members) {
        if (!isMemberCacheEnabled) {
            return;
        }
        Map<GroupMember.Key, GroupMember> keyAndMember =
                groupIdToMembersCache.get(groupId, id -> new ConcurrentHashMap<>(16));
        for (GroupMember member : members) {
            keyAndMember.put(member.getKey(), member);
        }
    }

    private void updateMembersCache(
            Set<GroupMember.Key> keys,
            @Nullable String name,
            @Nullable GroupMemberRole role,
            @Nullable Date joinDate,
            @Nullable Date muteEndDate) {
        if (!isMemberCacheEnabled) {
            return;
        }
        for (GroupMember.Key key : keys) {
            Map<GroupMember.Key, GroupMember> keyAndMember =
                    groupIdToMembersCache.getIfPresent(key.getGroupId());
            if (keyAndMember == null) {
                continue;
            }
            GroupMember member = keyAndMember.get(key);
            if (member == null) {
                continue;
            }
            if (name != null) {
                member.setName(name);
            }
            if (role != null) {
                member.setRole(role);
            }
            if (joinDate != null) {
                member.setJoinDate(joinDate);
            }
            if (muteEndDate != null) {
                member.setMuteEndDate(muteEndDate);
            }
        }
    }

    private void invalidMemberCache(Collection<GroupMember.Key> keys) {
        if (!isMemberCacheEnabled) {
            return;
        }
        for (GroupMember.Key key : keys) {
            Map<GroupMember.Key, GroupMember> keyAndMember =
                    groupIdToMembersCache.getIfPresent(key.getGroupId());
            if (keyAndMember != null) {
                keyAndMember.remove(key);
            }
        }
    }

}