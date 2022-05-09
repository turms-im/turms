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

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.group.GroupMembersWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.permission.ServicePermission;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.group.repository.GroupMemberRepository;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidGroupMemberRole;
import im.turms.service.storage.mongo.OperationResultPublisherPool;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * @param groupService          is lazy because: GroupService -> GroupMemberService -> GroupService
     * @param groupBlocklistService is lazy because: GroupMemberService -> GroupBlocklistService -> GroupMemberService
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

        propertiesManager.triggerAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        checkIfTargetActiveAndNotDeleted = properties.getService().getMessage().isCheckIfTargetActiveAndNotDeleted();
        respondOfflineIfInvisible = properties.getService().getUser().isRespondOfflineIfInvisible();
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
        GroupMember groupMember = new GroupMember(
                groupId,
                userId,
                name,
                groupMemberRole,
                joinDate,
                muteEndDate);
        return groupMemberRepository.insert(groupMember, session)
                .then(groupVersionService.updateMembersVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error("Caught an error while updating the members version of the group {} after adding a group member",
                                    groupId, t);
                            return Mono.empty();
                        }))
                .thenReturn(groupMember);
    }

    public Mono<GroupMember> authAndAddGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull @ValidGroupMemberRole GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return isAllowedToInviteOrAdd(groupId, requesterId, groupMemberRole)
                .flatMap(pair -> {
                    ServicePermission permission = pair.getLeft();
                    ResponseStatusCode code = permission.code();
                    if (code != ResponseStatusCode.OK) {
                        return Mono.error(ResponseException.get(code, permission.reason()));
                    }
                    Mono<Boolean> isBlockedMono = groupBlocklistService.isBlocked(groupId, userId);
                    Mono<Boolean> isGroupActiveMono = groupService.isGroupActiveAndNotDeleted(groupId);
                    return Mono.zip(isBlockedMono, isGroupActiveMono)
                            .flatMap(results -> {
                                boolean isUserBlocked = results.getT1();
                                boolean isGroupActive = results.getT2();
                                if (isUserBlocked) {
                                    return isGroupActive
                                            ? ResponseExceptionPublisherPool.addBlockedUserToGroup()
                                            : ResponseExceptionPublisherPool.addBlockedUserToInactiveGroup();
                                } else if (isGroupActive) {
                                    return addGroupMember(groupId, userId, groupMemberRole, name, new Date(), muteEndDate, session);
                                }
                                return Mono.error(ResponseException.get(ResponseStatusCode.ADD_USER_TO_INACTIVE_GROUP));
                            });
                });
    }

    public Mono<Void> authAndDeleteGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long deleteMemberId,
            @Nullable Long successorId,
            @Nullable Boolean quitAfterTransfer) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupId, "groupId");
            Validator.notNull(deleteMemberId, "deleteMemberId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (successorId != null) {
            quitAfterTransfer = quitAfterTransfer != null && quitAfterTransfer;
            return groupService.authAndTransferGroupOwnership(
                    requesterId, groupId, successorId, quitAfterTransfer, null);
        }
        boolean quitGroup = requesterId.equals(deleteMemberId);
        if (quitGroup) {
            return isOwner(deleteMemberId, groupId)
                    .flatMap(isOwner -> isOwner
                            ? Mono.error(ResponseException.get(ResponseStatusCode.OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR))
                            : deleteGroupMembers(groupId, deleteMemberId, null, true).then());
        }
        return isOwnerOrManager(requesterId, groupId)
                .flatMap(isOwnerOrManager -> isOwnerOrManager
                        ? deleteGroupMembers(Set.of(new GroupMember.Key(groupId, deleteMemberId)), null, true).then()
                        : Mono.error(ResponseException.get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER)));
    }

    public Mono<DeleteResult> deleteGroupMembers(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable ClientSession session,
            boolean updateGroupMembersVersion) {
        return deleteGroupMembers(Set.of(new GroupMember.Key(groupId, memberId)), session, updateGroupMembersVersion);
    }

    public Mono<DeleteResult> deleteGroupMembers(
            @NotEmpty Set<GroupMember.Key> keys,
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
                    if (!updateGroupMembersVersion || result.getDeletedCount() == 0) {
                        return Mono.just(result);
                    }
                    return groupVersionService.updateMembersVersion(groupIds)
                            .onErrorResume(t -> {
                                LOGGER.error("Caught an error while updating the members version of the groups {} after deleting group members",
                                        groupIds, t);
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
        return updateGroupMembers(groupId, Set.of(memberId), name, role, joinDate, muteEndDate, session, updateGroupMembersVersion);
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
        return groupMemberRepository.updateGroupMembers(keys, name, role, joinDate, muteEndDate, session)
                .flatMap(result -> {
                    if (!updateGroupMembersVersion || result.getModifiedCount() == 0) {
                        return Mono.just(result);
                    }
                    int size = keys.size();
                    Set<Long> groupIds = CollectionUtil.newSetWithExpectedSize(size);
                    for (GroupMember.Key key : keys) {
                        groupIds.add(key.getGroupId());
                    }
                    Mono<?> updateMono = size == 1
                            ? groupVersionService.updateMembersVersion(groupIds.iterator().next())
                            : groupVersionService.updateMembersVersion(groupIds);
                    return updateMono
                            .onErrorResume(t -> {
                                LOGGER.error("Caught an error while updating the members version of the groups {} after updating group members",
                                        groupIds, t);
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
        return updateGroupMembers(keys, name, role, joinDate, muteEndDate, session, updateGroupMembersVersion);
    }

    public Flux<Long> findMemberIdsByGroupId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findMemberIdsByGroupId(groupId);
    }

    public Mono<Boolean> isGroupMember(@NotNull Long groupId, @NotNull Long userId) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        GroupMember.Key key = new GroupMember.Key(groupId, userId);
        return groupMemberRepository.existsById(key);
    }

    public Mono<Pair<ServicePermission, GroupInvitationStrategy>> isAllowedToInviteOrAdd(
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @Nullable @ValidGroupMemberRole GroupMemberRole newMemberRole) {
        try {
            Validator.notNull(inviterId, "inviterId");
            DataValidator.validGroupMemberRole(newMemberRole);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryGroupMemberRole(inviterId, groupId)
                .flatMap(inviterRole -> groupService.queryGroupType(groupId)
                        .flatMap(groupType -> {
                            GroupInvitationStrategy strategy = groupType.getInvitationStrategy();
                            ResponseStatusCode code = isAllowedToInviteUserWithSpecifiedRole(inviterRole, newMemberRole, strategy);
                            if (code == ResponseStatusCode.OK) {
                                return Mono.just(Pair.of(ServicePermission.OK, strategy));
                            }
                            String reason = "The inviter with the role %s isn't allowed to send an invitation under the strategy %s"
                                    .formatted(inviterRole, strategy);
                            return Mono.just(Pair.of(ServicePermission.get(code, reason), strategy));
                        })
                        .defaultIfEmpty(ADD_USER_TO_INACTIVE_GROUP))
                .defaultIfEmpty(GROUP_INVITER_NOT_MEMBER);
    }

    /**
     * @return Possible codes: OK, INVITEE_ALREADY_GROUP_MEMBER, INVITEE_HAS_BEEN_BLOCKED
     */
    public Mono<ResponseStatusCode> isAllowedToBeInvited(@NotNull Long groupId, @NotNull Long inviteeId) {
        return isGroupMember(groupId, inviteeId)
                .flatMap(isGroupMember -> {
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
    public Mono<ResponseStatusCode> isAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
        return isGroupMember(groupId, senderId)
                .flatMap(isGroupMember -> isGroupMember != null && isGroupMember
                        ? isGroupMemberAllowedToSendMessage(groupId, senderId)
                        : isGuestAllowedToSendMessage(groupId, senderId));
    }

    /**
     * @return Possible codes: OK, GROUP_HAS_BEEN_MUTED, GROUP_NOT_ACTIVE, MEMBER_HAS_BEEN_MUTED
     */
    private Mono<ResponseStatusCode> isGroupMemberAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
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
                                    ? isMemberMuted(groupId, senderId)
                                    .map(muted -> muted ? ResponseStatusCode.MUTED_MEMBER_SEND_MESSAGE : ResponseStatusCode.OK)
                                    : Mono.just(ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP));
                });
    }

    private Mono<ResponseStatusCode> isGuestAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
        return groupService.queryGroupType(groupId)
                .flatMap(type -> {
                    Boolean speakable = type.getGuestSpeakable();
                    if (speakable == null || !speakable) {
                        return Mono.just(ResponseStatusCode.GUESTS_HAVE_BEEN_MUTED);
                    }
                    return groupService.isGroupMuted(groupId)
                            .flatMap(isGroupMuted -> {
                                if (isGroupMuted) {
                                    return Mono.just(ResponseStatusCode.SEND_MESSAGE_TO_MUTED_GROUP);
                                }
                                return groupService.isGroupActiveAndNotDeleted(groupId)
                                        .flatMap(isGroupActiveAndNotDeleted -> {
                                            if (isGroupActiveAndNotDeleted) {
                                                return groupBlocklistService.isBlocked(groupId, senderId)
                                                        .map(isBlocked -> isBlocked
                                                                ? ResponseStatusCode.GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED
                                                                : ResponseStatusCode.OK);
                                            }
                                            return Mono.just(ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
                                        });
                            });
                })
                .defaultIfEmpty(ResponseStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
    }

    public Mono<Boolean> isMemberMuted(@NotNull Long groupId, @NotNull Long userId) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberRepository.isMemberMuted(groupId, userId);
    }

    public Mono<GroupMemberRole> queryGroupMemberRole(@NotNull Long userId, @NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberRepository.findGroupMemberRole(userId, groupId);
    }

    public Mono<Boolean> isOwner(@NotNull Long userId, @NotNull Long groupId) {
        return queryGroupMemberRole(userId, groupId)
                .map(memberRole -> memberRole == GroupMemberRole.OWNER)
                .switchIfEmpty(PublisherPool.FALSE);
    }

    public Mono<Boolean> isOwnerOrManager(@NotNull Long userId, @NotNull Long groupId) {
        return queryGroupMemberRole(userId, groupId)
                .map(memberRole -> memberRole == GroupMemberRole.OWNER
                        || memberRole == GroupMemberRole.MANAGER)
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isOwnerOrManagerOrMember(@NotNull Long userId, @NotNull Long groupId) {
        return queryGroupMemberRole(userId, groupId)
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

    public Mono<Set<Long>> queryMemberIdsInUsersJoinedGroups(@NotEmpty Set<Long> userIds) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryUsersJoinedGroupIds(userIds, null, null)
                .collect(Collectors.toSet())
                .flatMap(groupIds -> groupIds.isEmpty()
                        ? Mono.just(Collections.emptySet())
                        : queryGroupMemberIds(groupIds).collect(Collectors.toSet()));
    }

    public Flux<Long> queryGroupMemberIds(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findGroupMemberIds(groupId);
    }

    public Flux<Long> queryGroupMemberIds(@NotEmpty Set<Long> groupIds) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findGroupMemberIds(groupIds);
    }

    public Flux<GroupMember> queryGroupsMembers(
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
                return Flux.error(e);
            }
        }
        return groupMemberRepository.findGroupsMembers(groupIds,
                userIds,
                roles,
                joinDateRange,
                muteEndDateRange,
                page,
                size);
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
        return groupMemberRepository.countMembers(groupIds,
                userIds,
                roles,
                joinDateRange,
                muteEndDateRange);
    }

    public Mono<DeleteResult> deleteGroupMembers(boolean updateGroupMembersVersion) {
        return groupMemberRepository.deleteAll()
                .flatMap(result -> updateGroupMembersVersion && result.getDeletedCount() > 0
                        ? groupVersionService.updateMembersVersion().thenReturn(result)
                        : OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT);
    }

    public Flux<GroupMember> queryGroupMembers(@NotNull Long groupId, @NotEmpty Set<Long> memberIds) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notEmpty(memberIds, "memberIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findGroupMembers(groupId, memberIds);
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
        return isGroupMember(groupId, requesterId)
                .flatMap(isGroupMember -> isGroupMember == null || !isGroupMember
                        ? Mono.error(ResponseException.get(ResponseStatusCode.NOT_MEMBER_TO_QUERY_MEMBER_INFO))
                        : queryGroupMembers(groupId, memberIds).collect(CollectorUtil.toChunkedList()))
                .flatMap(members -> {
                    if (members.isEmpty()) {
                        return ResponseExceptionPublisherPool.noContent();
                    }
                    GroupMembersWithVersion.Builder builder = ClientMessagePool.getGroupMembersWithVersionBuilder();
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
        return isGroupMember(groupId, requesterId)
                .flatMap(isGroupMember -> isGroupMember != null && isGroupMember
                        ? groupVersionService.queryMembersVersion(groupId)
                        : Mono.error(ResponseException.get(ResponseStatusCode.NOT_MEMBER_TO_QUERY_MEMBER_INFO)))
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    return queryGroupsMembers(Set.of(groupId), null, null, null, null, null, null)
                            .collect(CollectorUtil.toChunkedList())
                            .flatMap(members -> {
                                if (members.isEmpty()) {
                                    return ResponseExceptionPublisherPool.noContent();
                                }
                                GroupMembersWithVersion.Builder builder = ClientMessagePool.getGroupMembersWithVersionBuilder();
                                if (withStatus) {
                                    return fillMembersBuilderWithStatus(members, builder);
                                }
                                for (GroupMember member : members) {
                                    builder.addGroupMembers(ProtoModelConvertor.groupMember2proto(member));
                                }
                                return Mono.just(builder
                                        .setLastUpdatedDate(version.getTime())
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
        Mono<ResponseStatusCode> isAuthorizedMono;
        if (role != null) {
            isAuthorizedMono = isOwner(requesterId, groupId)
                    .map(isOwner -> isOwner ? ResponseStatusCode.OK : ResponseStatusCode.NOT_OWNER_TO_UPDATE_GROUP_MEMBER_INFO);
        } else if (muteEndDate != null || (name != null && !requesterId.equals(memberId))) {
            isAuthorizedMono = isOwnerOrManager(requesterId, groupId)
                    .map(isOwnerOrManager -> isOwnerOrManager ? ResponseStatusCode.OK :
                            ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO);
        } else {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return isAuthorizedMono
                .flatMap(code -> code == ResponseStatusCode.OK
                        ? updateGroupMember(groupId, memberId, name, role, new Date(), muteEndDate, null, false)
                        : Mono.error(ResponseException.get(code)));
    }

    public Mono<DeleteResult> deleteAllGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable ClientSession session,
            boolean updateMembersVersion) {
        return groupMemberRepository.deleteAllGroupMembers(groupIds, session)
                .flatMap(result -> updateMembersVersion && result.getDeletedCount() > 0
                        ? groupVersionService.updateMembersVersion(groupIds).thenReturn(result)
                        : OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT);
    }

    public Flux<Long> queryGroupManagersAndOwnerId(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupMemberRepository.findGroupManagersAndOwnerId(groupId)
                .map(member -> member.getKey().getUserId());
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
            Long userId = member.getKey().getUserId();
            monoList.add(userStatusService.getUserSessionsStatus(userId));
        }
        return Mono.zip(monoList, objects -> objects)
                .map(results -> {
                    int memberCount = members.size();
                    for (int i = 0; i < memberCount; i++) {
                        GroupMember member = members.get(i);
                        UserSessionsStatus info = (UserSessionsStatus) results[i];
                        var groupMember = ProtoModelConvertor
                                .userOnlineInfo2groupMember(
                                        member.getKey().getUserId(),
                                        info,
                                        respondOfflineIfInvisible);
                        builder.addGroupMembers(groupMember);
                    }
                    return builder.build();
                });
    }

    private ResponseStatusCode isAllowedToInviteUserWithSpecifiedRole(@NotNull GroupMemberRole requesterRole,
                                                                      @Nullable GroupMemberRole newMemberRole,
                                                                      @NotNull GroupInvitationStrategy groupInvitationStrategy) {
        ResponseStatusCode isAllowToAddRole = switch (groupInvitationStrategy) {
            case OWNER, OWNER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    ? ResponseStatusCode.OK
                    : ResponseStatusCode.NOT_OWNER_TO_SEND_INVITATION;
            case OWNER_MANAGER, OWNER_MANAGER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    || requesterRole == GroupMemberRole.MANAGER
                    ? ResponseStatusCode.OK
                    : ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION;
            case OWNER_MANAGER_MEMBER, OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    || requesterRole == GroupMemberRole.MANAGER
                    || requesterRole == GroupMemberRole.MEMBER
                    ? ResponseStatusCode.OK
                    : ResponseStatusCode.NOT_MEMBER_TO_SEND_INVITATION;
            default -> ResponseStatusCode.OK;
        };
        if (isAllowToAddRole == ResponseStatusCode.OK) {
            boolean isRequesterRoleHigherThanNewMemberRole = newMemberRole == null
                    || requesterRole.getNumber() < newMemberRole.getNumber();
            if (isRequesterRoleHigherThanNewMemberRole) {
                return ResponseStatusCode.OK;
            }
            return ResponseStatusCode.ADD_NEW_MEMBER_WITH_ROLE_HIGHER_THAN_REQUESTER;
        }
        return isAllowToAddRole;
    }

}