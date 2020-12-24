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
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupMemberRole;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.group.GroupMembersWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.MapUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.bo.ServicePermission;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidGroupMemberRole;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.GroupBlacklistedUser;
import im.turms.turms.workflow.dao.domain.GroupMember;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
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
import java.util.*;
import java.util.stream.Collectors;

import static im.turms.server.common.util.MapUtil.getCapability;

/**
 * @author James Chen
 */
@Service
public class GroupMemberService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;
    private final UserStatusService userStatusService;

    public GroupMemberService(
            Node node,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupService groupService,
            GroupVersionService groupVersionService,
            UserStatusService userStatusService) {
        this.mongoTemplate = mongoTemplate;
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
            @Nullable ReactiveMongoOperations operations) {
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
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.insert(groupMember, GroupMember.COLLECTION_NAME)
                .flatMap(member -> groupVersionService.updateMembersVersion(groupId)
                        .onErrorResume(t -> Mono.empty())
                        .thenReturn(member));
    }

    public Mono<GroupMember> authAndAddGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull @ValidGroupMemberRole GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return isAllowedToInviteOrAdd(groupId, requesterId, groupMemberRole)
                .flatMap(pair -> {
                    ServicePermission permission = pair.getLeft();
                    TurmsStatusCode code = permission.getCode();
                    if (code != TurmsStatusCode.OK) {
                        return Mono.error(TurmsBusinessException.get(code, permission.getReason()));
                    }
                    Mono<Boolean> isBlacklistedMono = isBlacklisted(groupId, userId);
                    Mono<Boolean> isGroupActiveMono = groupService.isGroupActiveAndNotDeleted(groupId);
                    return Mono.zip(isBlacklistedMono, isGroupActiveMono)
                            .flatMap(results -> {
                                boolean isUserBlocked = results.getT1();
                                boolean isGroupActive = results.getT2();
                                if (isUserBlocked) {
                                    return isGroupActive
                                            ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_BLOCKED_USER_TO_GROUP))
                                            : Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_BLOCKED_USER_TO_INACTIVE_GROUP));
                                } else if (isGroupActive) {
                                    return addGroupMember(groupId, userId, groupMemberRole, name, new Date(), muteEndDate, operations);
                                } else {
                                    return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_USER_TO_INACTIVE_GROUP));
                                }
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
        } else {
            return isOwnerOrManager(requesterId, groupId)
                    .flatMap(isOwnerOrManager -> isOwnerOrManager
                            ? deleteGroupMembers(Set.of(new GroupMember.Key(groupId, deleteMemberId)), null, true).then()
                            : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_GROUP_MEMBER)));
        }
    }

    public Mono<DeleteResult> deleteGroupMembers(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        return deleteGroupMembers(Set.of(new GroupMember.Key(groupId, memberId)), operations, updateGroupMembersVersion);
    }

    public Mono<DeleteResult> deleteGroupMembers(
            @NotEmpty Set<GroupMember.Key> keys,
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        Set<Long> groupIds;
        try {
            AssertUtil.notEmpty(keys, "keys");
            groupIds = new HashSet<>(getCapability(keys.size()));
            for (GroupMember.Key key : keys) {
                DomainConstraintUtil.validGroupMemberKey(key);
                groupIds.add(key.getGroupId());
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.remove(query, GroupMember.class, GroupMember.COLLECTION_NAME)
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
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        try {
            AssertUtil.notNull(memberId, "memberId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return updateGroupMembers(groupId, Set.of(memberId), name, role, joinDate, muteEndDate, operations, updateGroupMembersVersion);
    }

    public Mono<UpdateResult> updateGroupMembers(
            @NotEmpty Set<GroupMember.Key> keys,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations,
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
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(GroupMember.Fields.NAME, name)
                .setIfNotNull(GroupMember.Fields.ROLE, role)
                .setIfNotNull(GroupMember.Fields.JOIN_DATE, joinDate)
                .build();
        if (muteEndDate != null) {
            if (muteEndDate.getTime() < System.currentTimeMillis()) {
                update.unset(GroupMember.Fields.MUTE_END_DATE);
            } else {
                update.set(GroupMember.Fields.MUTE_END_DATE, muteEndDate);
            }
        }
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.updateMulti(query, update, GroupMember.class, GroupMember.COLLECTION_NAME)
                .flatMap(result -> {
                    if (updateGroupMembersVersion && result.getModifiedCount() > 0) {
                        int size = keys.size();
                        Mono<?> updateMono;
                        if (size == 1) {
                            Long groupId = keys.iterator().next().getGroupId();
                            updateMono = groupVersionService.updateMembersVersion(groupId);
                        } else {
                            Set<Long> groupIds = new HashSet<>(MapUtil.getCapability(size));
                            updateMono = groupVersionService.updateMembersVersion(groupIds);
                        }
                        return updateMono.onErrorResume(t -> Mono.empty()).thenReturn(result);
                    } else {
                        return Mono.just(result);
                    }
                });
    }

    public Mono<UpdateResult> updateGroupMembers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> memberIds,
            @Nullable String name,
            @Nullable @ValidGroupMemberRole GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notEmpty(memberIds, "memberIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Set<GroupMember.Key> keys = new HashSet<>(MapUtil.getCapability(memberIds.size()));
        for (Long memberId : memberIds) {
            keys.add(new GroupMember.Key(groupId, memberId));
        }
        return updateGroupMembers(keys, name, role, joinDate, muteEndDate, operations, updateGroupMembersVersion);
    }

    public Flux<Long> getMemberIdsByGroupId(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupMember.Fields.ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(groupMember -> groupMember.getKey().getUserId());
    }

    public Mono<Boolean> isGroupMember(@NotNull Long groupId, @NotNull Long userId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(new GroupMember.Key(groupId, userId)));
        return mongoTemplate.exists(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

    public Mono<Boolean> isBlacklisted(@NotNull Long groupId, @NotNull Long userId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(new GroupBlacklistedUser.Key(groupId, userId)));
        return mongoTemplate.exists(query, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME);
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
                            } else {
                                String reason = String.format("The inviter with the role %s isn't allowed to send an invitation under the strategy %s", inviterRole, strategy);
                                return Mono.just(Pair.of(ServicePermission.get(code, reason), strategy));
                            }
                        })
                        .defaultIfEmpty(Pair.of(ServicePermission.get(TurmsStatusCode.ADD_USER_TO_INACTIVE_GROUP), null)))
                .defaultIfEmpty(Pair.of(ServicePermission.get(TurmsStatusCode.GROUP_INVITER_NOT_MEMBER), null));
    }

    /**
     * @return Possible codes: OK, INVITEE_ALREADY_GROUP_MEMBER, INVITEE_HAS_BEEN_BLACKLISTED
     */
    public Mono<TurmsStatusCode> isAllowedToBeInvited(@NotNull Long groupId, @NotNull Long inviteeId) {
        return isGroupMember(groupId, inviteeId)
                .flatMap(isGroupMember -> {
                    if (isGroupMember) {
                        return Mono.just(TurmsStatusCode.GROUP_INVITEE_ALREADY_GROUP_MEMBER);
                    } else {
                        return isBlacklisted(groupId, inviteeId)
                                .map(isBlacklisted -> isBlacklisted
                                        ? TurmsStatusCode.INVITEE_HAS_BEEN_BLOCKED
                                        : TurmsStatusCode.OK);
                    }
                });
    }

    /**
     * Note that a blacklisted user is never a group member
     *
     * @return
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
                    } else {
                        if (!node.getSharedProperties().getService().getMessage().isCheckIfTargetActiveAndNotDeleted()) {
                            return Mono.just(TurmsStatusCode.OK);
                        }
                        return groupService.isGroupActiveAndNotDeleted(groupId)
                                .flatMap(isActive -> isActive
                                        ? isMemberMuted(groupId, senderId).map(muted -> muted ? TurmsStatusCode.MUTED_MEMBER_SEND_MESSAGE : TurmsStatusCode.OK)
                                        : Mono.just(TurmsStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP));
                    }
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
                                                return isBlacklisted(groupId, senderId)
                                                        .map(isBlacklisted -> isBlacklisted
                                                                ? TurmsStatusCode.GROUP_MESSAGE_SENDER_HAS_BEEN_BLOCKED
                                                                : TurmsStatusCode.OK);
                                            } else {
                                                return Mono.just(TurmsStatusCode.SEND_MESSAGE_TO_INACTIVE_GROUP);
                                            }
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
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId))
                .addCriteria(Criteria.where(GroupMember.Fields.MUTE_END_DATE).gt(new Date()));
        return mongoTemplate.exists(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

    public Mono<GroupMemberRole> queryGroupMemberRole(@NotNull Long userId, @NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupMember.Fields.ROLE);
        return mongoTemplate.findOne(query, GroupMember.class, GroupMember.COLLECTION_NAME)
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

    public Flux<Long> queryUsersJoinedGroupsIds(
            @NotEmpty Set<Long> userIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .paginateIfNotNull(page, size);
        query.fields().include(GroupMember.Fields.ID_GROUP_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(groupMember -> groupMember.getKey().getGroupId());
    }

    public Mono<Set<Long>> queryMemberIdsInUsersJoinedGroups(@NotEmpty Set<Long> userIds) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryUsersJoinedGroupsIds(userIds, null, null)
                .collect(Collectors.toSet())
                .flatMap(groupsIds -> groupsIds.isEmpty()
                        ? Mono.just(Collections.emptySet())
                        : queryGroupMemberIds(groupsIds).collect(Collectors.toSet()));
    }

    public Flux<Long> queryGroupMemberIds(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupMember.Fields.ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getUserId());
    }

    public Flux<Long> queryGroupMemberIds(@NotEmpty Set<Long> groupIds) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).in(groupIds));
        query.fields().include(GroupMember.Fields.ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .addInIfNotNull(GroupMember.Fields.ROLE, roles)
                .addBetweenIfNotNull(GroupMember.Fields.JOIN_DATE, joinDateRange)
                .addBetweenIfNotNull(GroupMember.Fields.MUTE_END_DATE, muteEndDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME);
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .addInIfNotNull(GroupMember.Fields.ROLE, roles)
                .addBetweenIfNotNull(GroupMember.Fields.JOIN_DATE, joinDateRange)
                .addBetweenIfNotNull(GroupMember.Fields.MUTE_END_DATE, muteEndDateRange)
                .buildQuery();
        return mongoTemplate.count(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

    public Mono<DeleteResult> deleteGroupMembers(boolean updateGroupMembersVersion) {
        Query query = new Query();
        return mongoTemplate.remove(query, GroupMember.class, GroupMember.COLLECTION_NAME)
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
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).in(memberIds));
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME);
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
                    } else {
                        for (GroupMember member : members) {
                            im.turms.common.model.bo.group.GroupMember groupMember = ProtoUtil
                                    .groupMember2proto(member).build();
                            builder.addGroupMembers(groupMember);
                        }
                        return Mono.just(builder.build());
                    }
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
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupsMembers(Set.of(groupId), null, null, null, null, null, null)
                                .collectList()
                                .flatMap(members -> {
                                    if (members.isEmpty()) {
                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT));
                                    }
                                    GroupMembersWithVersion.Builder builder = GroupMembersWithVersion.newBuilder();
                                    if (withStatus) {
                                        return fillMembersBuilderWithStatus(members, builder);
                                    } else {
                                        for (GroupMember member : members) {
                                            im.turms.common.model.bo.group.GroupMember groupMember = ProtoUtil
                                                    .groupMember2proto(member).build();
                                            builder.addGroupMembers(groupMember);
                                        }
                                        return Mono.just(builder
                                                .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                                .build());
                                    }
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
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
                    .map(isOwnerOrManager -> isOwnerOrManager ? TurmsStatusCode.OK : TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_MEMBER_INFO);
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
            @Nullable ReactiveMongoOperations operations,
            boolean updateMembersVersion) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .buildQuery();
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.remove(query, GroupMember.class, GroupMember.COLLECTION_NAME)
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
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ROLE)
                        .in(GroupMemberRole.MANAGER, GroupMemberRole.OWNER));
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
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
                    int memberNumber = members.size();
                    for (int i = 0; i < memberNumber; i++) {
                        GroupMember member = members.get(i);
                        UserSessionsStatus info = (UserSessionsStatus) results[i];
                        im.turms.common.model.bo.group.GroupMember groupMember = ProtoUtil
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
        TurmsStatusCode isAllowToAddRole;
        switch (groupInvitationStrategy) {
            case OWNER:
            case OWNER_REQUIRING_ACCEPTANCE:
                isAllowToAddRole = requesterRole == GroupMemberRole.OWNER
                        ? TurmsStatusCode.OK
                        : TurmsStatusCode.NOT_OWNER_TO_SEND_INVITATION;
                break;
            case OWNER_MANAGER:
            case OWNER_MANAGER_REQUIRING_ACCEPTANCE:
                isAllowToAddRole = requesterRole == GroupMemberRole.OWNER
                        || requesterRole == GroupMemberRole.MANAGER
                        ? TurmsStatusCode.OK
                        : TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_SEND_INVITATION;
                break;
            case OWNER_MANAGER_MEMBER:
            case OWNER_MANAGER_MEMBER_REQUIRING_ACCEPTANCE:
                isAllowToAddRole = requesterRole == GroupMemberRole.OWNER
                        || requesterRole == GroupMemberRole.MANAGER
                        || requesterRole == GroupMemberRole.MEMBER
                        ? TurmsStatusCode.OK
                        : TurmsStatusCode.NOT_MEMBER_TO_SEND_INVITATION;
                break;
            default:
                isAllowToAddRole = TurmsStatusCode.OK;
        }
        if (isAllowToAddRole == TurmsStatusCode.OK) {
            boolean isRequesterRoleHigherThanNewMemberRole = newMemberRole == null
                    || requesterRole.getNumber() < newMemberRole.getNumber();
            if (isRequesterRoleHigherThanNewMemberRole) {
                return TurmsStatusCode.OK;
            } else {
                return TurmsStatusCode.ADD_NEW_MEMBER_WITH_ROLE_HIGHER_THAN_REQUESTER;
            }
        } else {
            return isAllowToAddRole;
        }
    }

}