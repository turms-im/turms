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
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.model.bo.group.GroupMembersWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectionUtil;
import im.turms.server.common.util.DateUtil;
import im.turms.service.bo.ServicePermission;
import im.turms.service.constant.DaoConstant;
import im.turms.service.constant.OperationResultConstant;
import im.turms.service.constraint.ValidGroupMemberRole;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.service.workflow.dao.domain.group.GroupMember;
import im.turms.service.workflow.service.util.DomainConstraintUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;
    private final UserStatusService userStatusService;

    /**
     * @param groupService is lazy because: GroupService -> GroupMemberService -> GroupService
     */
    public GroupMemberService(
            Node node,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient,
            @Lazy GroupService groupService,
            GroupVersionService groupVersionService,
            UserStatusService userStatusService) {
        this.mongoClient = mongoClient;
        this.groupService = groupService;
        this.groupVersionService = groupVersionService;
        this.userStatusService = userStatusService;
        this.node = node;
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
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(groupMemberRole, "groupMemberRole");
            DomainConstraintUtil.validGroupMemberRole(groupMemberRole);
            AssertUtil.pastOrPresent(joinDate, "joinDate");
        } catch (TurmsBusinessException e) {
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
        return mongoClient.insert(session, groupMember)
                .then(Mono.defer(() -> groupVersionService.updateMembersVersion(groupId)
                        .onErrorResume(t -> Mono.empty())))
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
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return isAllowedToInviteOrAdd(groupId, requesterId, groupMemberRole)
                .flatMap(pair -> {
                    ServicePermission permission = pair.getLeft();
                    TurmsStatusCode code = permission.code();
                    if (code != TurmsStatusCode.OK) {
                        return Mono.error(TurmsBusinessException.get(code, permission.reason()));
                    }
                    Mono<Boolean> isBlockedMono = isBlocked(groupId, userId);
                    Mono<Boolean> isGroupActiveMono = groupService.isGroupActiveAndNotDeleted(groupId);
                    return Mono.zip(isBlockedMono, isGroupActiveMono)
                            .flatMap(results -> {
                                boolean isUserBlocked = results.getT1();
                                boolean isGroupActive = results.getT2();
                                if (isUserBlocked) {
                                    return isGroupActive
                                            ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_BLOCKED_USER_TO_GROUP))
                                            : Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_BLOCKED_USER_TO_INACTIVE_GROUP));
                                } else if (isGroupActive) {
                                    return addGroupMember(groupId, userId, groupMemberRole, name, new Date(), muteEndDate, session);
                                }
                                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_USER_TO_INACTIVE_GROUP));
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
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(deleteMemberId, "deleteMemberId");
        } catch (TurmsBusinessException e) {
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
                            ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.OWNER_QUITS_WITHOUT_SPECIFYING_SUCCESSOR))
                            : deleteGroupMembers(groupId, deleteMemberId, null, true).then());
        }
        return isOwnerOrManager(requesterId, groupId)
                .flatMap(isOwnerOrManager -> isOwnerOrManager
                        ? deleteGroupMembers(Set.of(new GroupMember.Key(groupId, deleteMemberId)), null, true).then()
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER)));
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
            AssertUtil.notEmpty(keys, "keys");
            groupIds = CollectionUtil.newSetWithExpectedSize(keys.size());
            for (GroupMember.Key key : keys) {
                DomainConstraintUtil.validGroupMemberKey(key);
                groupIds.add(key.getGroupId());
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, keys);
        return mongoClient.deleteMany(session, GroupMember.class, filter)
                .flatMap(result -> updateGroupMembersVersion && result.getDeletedCount() > 0
                        ? groupVersionService.updateMembersVersion(groupIds).onErrorResume(t -> Mono.empty()).thenReturn(result)
                        : Mono.just(result));
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
            AssertUtil.notNull(memberId, "memberId");
        } catch (TurmsBusinessException e) {
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
            AssertUtil.notEmpty(keys, "keys");
            DomainConstraintUtil.validGroupMemberRole(role);
            AssertUtil.pastOrPresent(joinDate, "joinDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(name, role, joinDate, muteEndDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, keys);
        Update update = Update.newBuilder(muteEndDate == null ? 3 : 4)
                .setIfNotNull(GroupMember.Fields.NAME, name)
                .setIfNotNull(GroupMember.Fields.ROLE, role)
                .setIfNotNull(GroupMember.Fields.JOIN_DATE, joinDate);
        if (muteEndDate != null) {
            if (muteEndDate.getTime() < System.currentTimeMillis()) {
                update.unset(GroupMember.Fields.MUTE_END_DATE);
            } else {
                update.set(GroupMember.Fields.MUTE_END_DATE, muteEndDate);
            }
        }
        return mongoClient.updateMany(session, GroupMember.class, filter, update)
                .flatMap(result -> {
                    if (updateGroupMembersVersion && result.getModifiedCount() > 0) {
                        int size = keys.size();
                        Mono<?> updateMono;
                        if (size == 1) {
                            Long groupId = keys.iterator().next().getGroupId();
                            updateMono = groupVersionService.updateMembersVersion(groupId);
                        } else {
                            Set<Long> groupIds = CollectionUtil.newSetWithExpectedSize(size);
                            updateMono = groupVersionService.updateMembersVersion(groupIds);
                        }
                        return updateMono.onErrorResume(t -> Mono.empty()).thenReturn(result);
                    }
                    return Mono.just(result);
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
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notEmpty(memberIds, "memberIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Set<GroupMember.Key> keys = CollectionUtil.newSetWithExpectedSize(memberIds.size());
        for (Long memberId : memberIds) {
            keys.add(new GroupMember.Key(groupId, memberId));
        }
        return updateGroupMembers(keys, name, role, joinDate, muteEndDate, session, updateGroupMembersVersion);
    }

    public Flux<Long> getMemberIdsByGroupId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_USER_ID);
        return mongoClient.findMany(GroupMember.class, filter, options)
                .map(groupMember -> groupMember.getKey().getUserId());
    }

    public Mono<Boolean> isGroupMember(@NotNull Long groupId, @NotNull Long userId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupMember.Key key = new GroupMember.Key(groupId, userId);
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, key);
        return mongoClient.exists(GroupMember.class, filter);
    }

    public Mono<Boolean> isBlocked(@NotNull Long groupId, @NotNull Long userId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupBlockedUser.Key key = new GroupBlockedUser.Key(groupId, userId);
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, key);
        return mongoClient.exists(GroupBlockedUser.class, filter);
    }

    public Mono<Pair<ServicePermission, GroupInvitationStrategy>> isAllowedToInviteOrAdd(
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @Nullable @ValidGroupMemberRole GroupMemberRole newMemberRole) {
        try {
            AssertUtil.notNull(inviterId, "inviterId");
            DomainConstraintUtil.validGroupMemberRole(newMemberRole);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryGroupMemberRole(inviterId, groupId)
                .flatMap(inviterRole -> groupService.queryGroupType(groupId)
                        .flatMap(groupType -> {
                            GroupInvitationStrategy strategy = groupType.getInvitationStrategy();
                            TurmsStatusCode code = isAllowedToInviteUserWithSpecifiedRole(inviterRole, newMemberRole, strategy);
                            if (code == TurmsStatusCode.OK) {
                                return Mono.just(Pair.of(ServicePermission.OK, strategy));
                            }
                            String reason = "The inviter with the role %s isn't allowed to send an invitation under the strategy %s"
                                    .formatted(inviterRole, strategy);
                            return Mono.just(Pair.of(ServicePermission.get(code, reason), strategy));
                        })
                        .defaultIfEmpty(Pair.of(ServicePermission.get(TurmsStatusCode.ADD_USER_TO_INACTIVE_GROUP), null)))
                .defaultIfEmpty(Pair.of(ServicePermission.get(TurmsStatusCode.GROUP_INVITER_NOT_MEMBER), null));
    }

    /**
     * @return Possible codes: OK, INVITEE_ALREADY_GROUP_MEMBER, INVITEE_HAS_BEEN_BLOCKED
     */
    public Mono<TurmsStatusCode> isAllowedToBeInvited(@NotNull Long groupId, @NotNull Long inviteeId) {
        return isGroupMember(groupId, inviteeId)
                .flatMap(isGroupMember -> {
                    if (isGroupMember) {
                        return Mono.just(TurmsStatusCode.GROUP_INVITEE_ALREADY_GROUP_MEMBER);
                    }
                    return isBlocked(groupId, inviteeId)
                            .map(isBlocked -> isBlocked
                                    ? TurmsStatusCode.INVITEE_HAS_BEEN_BLOCKED
                                    : TurmsStatusCode.OK);
                });
    }

    /**
     * Note that a blocked user is never a group member
     */
    public Mono<TurmsStatusCode> isAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
        return isGroupMember(groupId, senderId)
                .flatMap(isGroupMember -> isGroupMember != null && isGroupMember
                        ? isGroupMemberAllowedToSendMessage(groupId, senderId)
                        : isGuestAllowedToSendMessage(groupId, senderId));
    }

    /**
     * @return Possible codes: OK, GROUP_HAS_BEEN_MUTED, GROUP_NOT_ACTIVE, MEMBER_HAS_BEEN_MUTED
     */
    private Mono<TurmsStatusCode> isGroupMemberAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
        return groupService.isGroupMuted(groupId)
                .flatMap(isGroupMuted -> {
                    if (isGroupMuted) {
                        return Mono.just(TurmsStatusCode.SEND_MESSAGE_TO_MUTED_GROUP);
                    }
                    if (!node.getSharedProperties().getService().getMessage().isCheckIfTargetActiveAndNotDeleted()) {
                        return Mono.just(TurmsStatusCode.OK);
                    }
                    return groupService.isGroupActiveAndNotDeleted(groupId)
                            .flatMap(isActive -> isActive
                                    ? isMemberMuted(groupId, senderId)
                                    .map(muted -> muted ? TurmsStatusCode.MUTED_MEMBER_SEND_MESSAGE : TurmsStatusCode.OK)
                                    : Mono.just(TurmsStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP));
                });
    }

    private Mono<TurmsStatusCode> isGuestAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
        return groupService.queryGroupType(groupId)
                .flatMap(type -> {
                    Boolean speakable = type.getGuestSpeakable();
                    if (speakable == null || !speakable) {
                        return Mono.just(TurmsStatusCode.GUESTS_HAVE_BEEN_MUTED);
                    }
                    return groupService.isGroupMuted(groupId)
                            .flatMap(isGroupMuted -> {
                                if (isGroupMuted) {
                                    return Mono.just(TurmsStatusCode.SEND_MESSAGE_TO_MUTED_GROUP);
                                }
                                return groupService.isGroupActiveAndNotDeleted(groupId)
                                        .flatMap(isGroupActiveAndNotDeleted -> {
                                            if (isGroupActiveAndNotDeleted) {
                                                return isBlocked(groupId, senderId)
                                                        .map(isBlocked -> isBlocked
                                                                ? TurmsStatusCode.GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED
                                                                : TurmsStatusCode.OK);
                                            }
                                            return Mono.just(TurmsStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
                                        });
                            });
                })
                .defaultIfEmpty(TurmsStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
    }

    public Mono<Boolean> isMemberMuted(@NotNull Long groupId, @NotNull Long userId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(3)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId)
                .eq(GroupMember.Fields.ID_USER_ID, userId)
                .gt(GroupMember.Fields.MUTE_END_DATE, new Date());
        return mongoClient.exists(GroupMember.class, filter);
    }

    public Mono<GroupMemberRole> queryGroupMemberRole(@NotNull Long userId, @NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .eq(GroupMember.Fields.ID_USER_ID, userId)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupMember.Fields.ROLE);
        return mongoClient.findOne(GroupMember.class, filter, options)
                .map(GroupMember::getRole);
    }

    public Mono<Boolean> isOwner(@NotNull Long userId, @NotNull Long groupId) {
        return queryGroupMemberRole(userId, groupId)
                .map(memberRole -> memberRole == GroupMemberRole.OWNER)
                .defaultIfEmpty(false);
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

    public Flux<Long> queryUsersJoinedGroupIds(
            @NotEmpty Set<Long> userIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(GroupMember.Fields.ID_USER_ID, userIds);
        QueryOptions options = QueryOptions.newBuilder(3)
                .paginateIfNotNull(page, size)
                .include(GroupMember.Fields.ID_GROUP_ID);
        return mongoClient.findMany(GroupMember.class, filter, options)
                .map(groupMember -> groupMember.getKey().getGroupId());
    }

    public Mono<Set<Long>> queryMemberIdsInUsersJoinedGroups(@NotEmpty Set<Long> userIds) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
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
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_USER_ID);
        return mongoClient.findMany(GroupMember.class, filter, options)
                .map(member -> member.getKey().getUserId());
    }

    public Flux<Long> queryGroupMemberIds(@NotEmpty Set<Long> groupIds) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .in(GroupMember.Fields.ID_GROUP_ID, groupIds);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_USER_ID);
        return mongoClient.findMany(GroupMember.class, filter, options)
                .map(member -> member.getKey().getUserId());
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
                    DomainConstraintUtil.validGroupMemberRole(role);
                }
            } catch (TurmsBusinessException e) {
                return Flux.error(e);
            }
        }
        Filter filter = Filter.newBuilder(7)
                .inIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupMember.Fields.ROLE, roles)
                .addBetweenIfNotNull(GroupMember.Fields.JOIN_DATE, joinDateRange)
                .addBetweenIfNotNull(GroupMember.Fields.MUTE_END_DATE, muteEndDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(GroupMember.class, filter, options);
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
                    DomainConstraintUtil.validGroupMemberRole(role);
                }
            } catch (TurmsBusinessException e) {
                return Mono.error(e);
            }
        }
        Filter filter = Filter.newBuilder(7)
                .inIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupMember.Fields.ROLE, roles)
                .addBetweenIfNotNull(GroupMember.Fields.JOIN_DATE, joinDateRange)
                .addBetweenIfNotNull(GroupMember.Fields.MUTE_END_DATE, muteEndDateRange);
        return mongoClient.count(GroupMember.class, filter);
    }

    public Mono<DeleteResult> deleteGroupMembers(boolean updateGroupMembersVersion) {
        return mongoClient.deleteAll(GroupMember.class)
                .flatMap(result -> updateGroupMembersVersion && result.getDeletedCount() > 0
                        ? groupVersionService.updateMembersVersion().thenReturn(result)
                        : Mono.just(OperationResultConstant.ACKNOWLEDGED_DELETE_RESULT));
    }

    public Flux<GroupMember> queryGroupMembers(@NotNull Long groupId, @NotEmpty Set<Long> memberIds) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notEmpty(memberIds, "memberIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId)
                .in(GroupMember.Fields.ID_USER_ID, memberIds);
        return mongoClient.findMany(GroupMember.class, filter);
    }

    public Mono<GroupMembersWithVersion> authAndQueryGroupMembers(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotEmpty Set<Long> memberIds,
            boolean withStatus) {
        try {
            AssertUtil.notEmpty(memberIds, "memberIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return isGroupMember(groupId, requesterId)
                .flatMap(isGroupMember -> isGroupMember == null || !isGroupMember
                        ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_MEMBER_TO_QUERY_MEMBER_INFO))
                        : queryGroupMembers(groupId, memberIds).collectList())
                .flatMap(members -> {
                    if (members.isEmpty()) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT));
                    }
                    GroupMembersWithVersion.Builder builder = GroupMembersWithVersion.newBuilder();
                    if (withStatus) {
                        return fillMembersBuilderWithStatus(members, builder);
                    }
                    for (GroupMember member : members) {
                        var groupMember = ProtoModelUtil.groupMember2proto(member).build();
                        builder.addGroupMembers(groupMember);
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
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_MEMBER_TO_QUERY_MEMBER_INFO)))
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryGroupsMembers(Set.of(groupId), null, null, null, null, null, null)
                            .collectList()
                            .flatMap(members -> {
                                if (members.isEmpty()) {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT));
                                }
                                GroupMembersWithVersion.Builder builder = GroupMembersWithVersion.newBuilder();
                                if (withStatus) {
                                    return fillMembersBuilderWithStatus(members, builder);
                                }
                                for (GroupMember member : members) {
                                    var groupMember = ProtoModelUtil.groupMember2proto(member).build();
                                    builder.addGroupMembers(groupMember);
                                }
                                return Mono.just(builder
                                        .setLastUpdatedDate(version.getTime())
                                        .build());
                            });
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<UpdateResult> authAndUpdateGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable Date muteEndDate) {
        try {
            AssertUtil.notNull(memberId, "memberId");
            DomainConstraintUtil.validGroupMemberRole(role);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<TurmsStatusCode> isAuthorizedMono;
        if (role != null) {
            isAuthorizedMono = isOwner(requesterId, groupId)
                    .map(isOwner -> isOwner ? TurmsStatusCode.OK : TurmsStatusCode.NOT_OWNER_TO_UPDATE_GROUP_MEMBER_INFO);
        } else if (muteEndDate != null || (name != null && !requesterId.equals(memberId))) {
            isAuthorizedMono = isOwnerOrManager(requesterId, groupId)
                    .map(isOwnerOrManager -> isOwnerOrManager ? TurmsStatusCode.OK :
                            TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO);
        } else {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        return isAuthorizedMono
                .flatMap(code -> code == TurmsStatusCode.OK
                        ? updateGroupMember(groupId, memberId, name, role, new Date(), muteEndDate, null, false)
                        : Mono.error(TurmsBusinessException.get(code)));
    }

    public Mono<DeleteResult> deleteAllGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable ClientSession session,
            boolean updateMembersVersion) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds);
        return mongoClient.deleteMany(session, GroupMember.class, filter)
                .flatMap(result -> updateMembersVersion && result.getDeletedCount() > 0
                        ? groupVersionService.updateMembersVersion(groupIds).thenReturn(result)
                        : Mono.just(OperationResultConstant.ACKNOWLEDGED_DELETE_RESULT));
    }

    public Flux<Long> queryGroupManagersAndOwnerId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId)
                .in(GroupMember.Fields.ROLE, GroupMemberRole.MANAGER, GroupMemberRole.OWNER);
        return mongoClient.findMany(GroupMember.class, filter)
                .map(member -> member.getKey().getUserId());
    }

    private Mono<GroupMembersWithVersion> fillMembersBuilderWithStatus(
            @NotNull List<GroupMember> members,
            @NotNull GroupMembersWithVersion.Builder builder) {
        try {
            AssertUtil.notNull(members, "members");
            AssertUtil.notNull(builder, "builder");
        } catch (TurmsBusinessException e) {
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
                        var groupMember = ProtoModelUtil
                                .userOnlineInfo2groupMember(
                                        member.getKey().getUserId(),
                                        info,
                                        node.getSharedProperties().getService().getUser().isRespondOfflineIfInvisible())
                                .build();
                        builder.addGroupMembers(groupMember);
                    }
                    return builder.build();
                });
    }

    private TurmsStatusCode isAllowedToInviteUserWithSpecifiedRole(@NotNull GroupMemberRole requesterRole,
                                                                   @Nullable GroupMemberRole newMemberRole,
                                                                   @NotNull GroupInvitationStrategy groupInvitationStrategy) {
        TurmsStatusCode isAllowToAddRole = switch (groupInvitationStrategy) {
            case OWNER, OWNER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    ? TurmsStatusCode.OK
                    : TurmsStatusCode.NOT_OWNER_TO_SEND_INVITATION;
            case OWNER_MANAGER, OWNER_MANAGER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    || requesterRole == GroupMemberRole.MANAGER
                    ? TurmsStatusCode.OK
                    : TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION;
            case OWNER_MANAGER_MEMBER, OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL -> requesterRole == GroupMemberRole.OWNER
                    || requesterRole == GroupMemberRole.MANAGER
                    || requesterRole == GroupMemberRole.MEMBER
                    ? TurmsStatusCode.OK
                    : TurmsStatusCode.NOT_MEMBER_TO_SEND_INVITATION;
            default -> TurmsStatusCode.OK;
        };
        if (isAllowToAddRole == TurmsStatusCode.OK) {
            boolean isRequesterRoleHigherThanNewMemberRole = newMemberRole == null
                    || requesterRole.getNumber() < newMemberRole.getNumber();
            if (isRequesterRoleHigherThanNewMemberRole) {
                return TurmsStatusCode.OK;
            }
            return TurmsStatusCode.ADD_NEW_MEMBER_WITH_ROLE_HIGHER_THAN_REQUESTER;
        }
        return isAllowToAddRole;
    }

}