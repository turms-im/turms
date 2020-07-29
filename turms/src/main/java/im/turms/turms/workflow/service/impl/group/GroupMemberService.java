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
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.group.GroupMembersWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.turms.bo.DateRange;
import im.turms.turms.bo.InvitableAndInvitationStrategy;
import im.turms.turms.constraint.GroupMemberKeyConstraint;
import im.turms.turms.constraint.GroupMemberRoleConstraint;
import im.turms.turms.util.MapUtil;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.GroupBlacklistedUser;
import im.turms.turms.workflow.dao.domain.GroupMember;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Service
@Validated
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
            @NotNull @GroupMemberRoleConstraint GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations) {
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
                .zipWith(groupVersionService.updateMembersVersion(groupId))
                .map(Tuple2::getT1);
    }

    public Mono<GroupMember> authAndAddGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull @GroupMemberRoleConstraint GroupMemberRole groupMemberRole,
            @Nullable String name,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations) {
        return isAllowedToInviteOrAdd(groupId, requesterId, groupMemberRole)
                .flatMap(allowed -> {
                    if (allowed.isInvitable()) {
                        return Mono.zip(isBlacklisted(groupId, userId),
                                groupService.isGroupActiveAndNotDeleted(groupId))
                                .flatMap(results -> !results.getT1() && results.getT2()
                                        ? addGroupMember(groupId, userId, groupMemberRole, name, new Date(), muteEndDate, operations)
                                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.TARGET_USERS_UNAUTHORIZED)));
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Mono<Boolean> authAndDeleteGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long deleteMemberId,
            @Nullable Long successorId,
            @Nullable Boolean quitAfterTransfer) {
        if (successorId != null) {
            quitAfterTransfer = quitAfterTransfer != null ? quitAfterTransfer : false;
            return groupService.authAndTransferGroupOwnership(
                    requesterId, groupId, successorId, quitAfterTransfer, null);
        }
        if (requesterId.equals(deleteMemberId)) {
            return isOwner(deleteMemberId, groupId)
                    .flatMap(isOwner -> isOwner != null && isOwner
                            ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The successor ID must not be null if you are quiting the group"))
                            : deleteGroupMembers(groupId, Set.of(deleteMemberId), null, true));
        } else {
            return isOwnerOrManager(requesterId, groupId)
                    .flatMap(isOwnerOrManager -> isOwnerOrManager != null && isOwnerOrManager
                            ? deleteGroupMembers(groupId, Set.of(deleteMemberId), null, true)
                            : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
        }
    }

    public Mono<Boolean> deleteGroupMembers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> deleteMemberIds,
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).in(deleteMemberIds));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.remove(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .flatMap(result -> {
                    if (result.wasAcknowledged()) {
                        return updateGroupMembersVersion
                                ? groupVersionService.updateMembersVersion(groupId).thenReturn(true)
                                : Mono.just(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    public Mono<Boolean> updateGroupMember(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable String name,
            @Nullable @GroupMemberRoleConstraint GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        return updateGroupMembers(groupId, Set.of(memberId), name, role, joinDate, muteEndDate, operations, updateGroupMembersVersion);
    }

    public Mono<Boolean> updateGroupMembers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> memberIds,
            @Nullable String name,
            @Nullable @GroupMemberRoleConstraint GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations,
            boolean updateGroupMembersVersion) {
        if (Validator.areAllNull(name, role, joinDate, muteEndDate)) {
            return Mono.just(true);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).in(memberIds));
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
                    if (result.wasAcknowledged()) {
                        return updateGroupMembersVersion
                                ? groupVersionService.updateMembersVersion(groupId).thenReturn(true)
                                : Mono.just(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    public Mono<Boolean> updateGroupMembers(
            @NotEmpty Set<GroupMember.@GroupMemberKeyConstraint Key> keys,
            @Nullable String name,
            @Nullable @GroupMemberRoleConstraint GroupMemberRole role,
            @Nullable @PastOrPresent Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ReactiveMongoOperations operations,
            @NotNull Boolean updateGroupMembersVersion) {
        if (Validator.areAllNull(name, role, joinDate, muteEndDate)) {
            return Mono.just(true);
        }
        return MapUtil.fluxMerge(multimap -> {
            for (GroupMember.Key key : keys) {
                multimap.put(key.getGroupId(), key.getUserId());
            }
        }, (monos, key, values) -> monos.add(updateGroupMembers(
                key,
                values,
                name,
                role,
                joinDate,
                muteEndDate,
                operations,
                updateGroupMembersVersion)));
    }

    public Flux<Long> getMembersIdsByGroupId(@NotNull Long groupId) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupMember.Fields.ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(groupMember -> groupMember.getKey().getUserId());
    }

    public Mono<Boolean> isGroupMember(@NotNull Long groupId, @NotNull Long userId) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId));
        return mongoTemplate.exists(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

    public Mono<Boolean> isBlacklisted(@NotNull Long groupId, @NotNull Long userId) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId));
        return mongoTemplate.exists(query, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME);
    }

    public Mono<InvitableAndInvitationStrategy> isAllowedToInviteOrAdd(
            @NotNull Long groupId,
            @NotNull Long inviterId,
            @Nullable @GroupMemberRoleConstraint GroupMemberRole targetRole) {
        return groupService.queryGroupType(groupId)
                .flatMap(groupType -> {
                    GroupInvitationStrategy groupInvitationStrategy = groupType.getInvitationStrategy();
                    if (groupInvitationStrategy == GroupInvitationStrategy.ALL
                            || groupInvitationStrategy == GroupInvitationStrategy.ALL_REQUIRING_ACCEPTANCE) {
                        return Mono.just(new InvitableAndInvitationStrategy(true, groupInvitationStrategy));
                    } else {
                        return queryGroupMemberRole(inviterId, groupId)
                                .map(groupMemberRole -> {
                                    switch (groupInvitationStrategy) {
                                        case OWNER:
                                        case OWNER_REQUIRING_ACCEPTANCE:
                                            return groupMemberRole == GroupMemberRole.OWNER && targetRole != GroupMemberRole.OWNER;
                                        case OWNER_MANAGER:
                                        case OWNER_MANAGER_REQUIRING_ACCEPTANCE:
                                            if (groupMemberRole == GroupMemberRole.OWNER
                                                    || groupMemberRole == GroupMemberRole.MANAGER) {
                                                return targetRole == null || groupMemberRole.getNumber() < targetRole.getNumber();
                                            } else {
                                                return false;
                                            }
                                        case OWNER_MANAGER_MEMBER:
                                        case OWNER_MANAGER_MEMBER_REQUIRING_ACCEPTANCE:
                                            if (groupMemberRole == GroupMemberRole.OWNER
                                                    || groupMemberRole == GroupMemberRole.MANAGER
                                                    || groupMemberRole == GroupMemberRole.MEMBER) {
                                                return targetRole == null || groupMemberRole.getNumber() < targetRole.getNumber();
                                            } else {
                                                return false;
                                            }
                                        default:
                                            return false;
                                    }
                                })
                                .map(allowed -> new InvitableAndInvitationStrategy(allowed, groupInvitationStrategy))
                                .defaultIfEmpty(new InvitableAndInvitationStrategy(false, groupInvitationStrategy));
                    }
                });
    }

    public Mono<Boolean> isAllowedToBeInvited(@NotNull Long groupId, @NotNull Long inviteeId) {
        return isGroupMember(groupId, inviteeId)
                .flatMap(isGroupMember -> {
                    if (isGroupMember == null || !isGroupMember) {
                        return isBlacklisted(groupId, inviteeId)
                                .map(isBlacklisted -> {
                                    if (isBlacklisted) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.USER_HAS_BEEN_BLACKLISTED);
                                    } else {
                                        return true;
                                    }
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_NOT_GROUP_MEMBER));
                    }
                });
    }

    // Note that a blacklisted user is never a group member
    public Mono<Boolean> isAllowedToSendMessage(@NotNull Long groupId, @NotNull Long senderId) {
        return isGroupMember(groupId, senderId)
                .flatMap(isGroupMember -> {
                    if (isGroupMember != null && isGroupMember) {
                        return groupService.isGroupMuted(groupId)
                                .flatMap(isGroupMuted -> {
                                    if (isGroupMuted) {
                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.GROUP_HAS_BEEN_MUTED));
                                    } else {
                                        if (node.getSharedProperties().getService().getMessage()
                                                .isCheckIfTargetActiveAndNotDeleted()) {
                                            return groupService.isGroupActiveAndNotDeleted(groupId)
                                                    .flatMap(isGroupActiveAndNotDeleted -> {
                                                        if (isGroupActiveAndNotDeleted) {
                                                            return isMemberMuted(groupId, senderId)
                                                                    .map(muted -> {
                                                                        if (muted) {
                                                                            throw TurmsBusinessException.get(TurmsStatusCode.MEMBER_HAS_BEEN_MUTED);
                                                                        } else {
                                                                            return true;
                                                                        }
                                                                    });
                                                        } else {
                                                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_ACTIVE));
                                                        }
                                                    });
                                        } else {
                                            return isMemberMuted(groupId, senderId)
                                                    .map(muted -> {
                                                        if (muted) {
                                                            throw TurmsBusinessException.get(TurmsStatusCode.GROUP_HAS_BEEN_MUTED);
                                                        } else {
                                                            return true;
                                                        }
                                                    });
                                        }
                                    }
                                });
                    } else {
                        return groupService.queryGroupType(groupId)
                                .flatMap(type -> {
                                    Boolean speakable = type.getGuestSpeakable();
                                    if (speakable != null && speakable) {
                                        return groupService.isGroupMuted(groupId)
                                                .flatMap(isGroupMuted -> {
                                                    if (isGroupMuted) {
                                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.GROUP_HAS_BEEN_MUTED));
                                                    } else {
                                                        return groupService.isGroupActiveAndNotDeleted(groupId)
                                                                .flatMap(isGroupActiveAndNotDeleted -> {
                                                                    if (isGroupActiveAndNotDeleted) {
                                                                        return isBlacklisted(groupId, senderId)
                                                                                .map(isBlacklisted -> {
                                                                                    if (isBlacklisted) {
                                                                                        throw TurmsBusinessException.get(TurmsStatusCode.USER_HAS_BEEN_BLACKLISTED);
                                                                                    } else {
                                                                                        return true;
                                                                                    }
                                                                                });
                                                                    } else {
                                                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_ACTIVE));
                                                                    }
                                                                });
                                                    }
                                                });
                                    } else {
                                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.GUESTS_HAVE_BEEN_MUTED));
                                    }
                                })
                                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.TYPE_NOT_EXISTS)));
                    }
                });
    }

    public Mono<Boolean> isMemberMuted(@NotNull Long groupId, @NotNull Long userId) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId))
                .addCriteria(Criteria.where(GroupMember.Fields.MUTE_END_DATE).gt(new Date()));
        return mongoTemplate.exists(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

    public Mono<GroupMemberRole> queryGroupMemberRole(@NotNull Long userId, @NotNull Long groupId) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupMember.Fields.ROLE);
        return mongoTemplate.findOne(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(GroupMember::getRole);
    }

    public Mono<Boolean> isOwner(@NotNull Long userId, @NotNull Long groupId) {
        Mono<GroupMemberRole> role = queryGroupMemberRole(userId, groupId);
        return role.map(memberRole -> memberRole == GroupMemberRole.OWNER)
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isOwnerOrManager(@NotNull Long userId, @NotNull Long groupId) {
        Mono<GroupMemberRole> role = queryGroupMemberRole(userId, groupId);
        return role.map(memberRole -> memberRole == GroupMemberRole.OWNER
                || memberRole == GroupMemberRole.MANAGER)
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isOwnerOrManagerOrMember(@NotNull Long userId, @NotNull Long groupId) {
        Mono<GroupMemberRole> role = queryGroupMemberRole(userId, groupId);
        return role.map(memberRole -> memberRole == GroupMemberRole.OWNER
                || memberRole == GroupMemberRole.MANAGER
                || memberRole == GroupMemberRole.MEMBER)
                .defaultIfEmpty(false);
    }

    public Flux<Long> queryUsersJoinedGroupsIds(
            @NotEmpty Set<Long> userIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .paginateIfNotNull(page, size);
        query.fields().include(GroupMember.Fields.ID_GROUP_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(groupMember -> groupMember.getKey().getGroupId());
    }

    public Mono<Set<Long>> queryUsersJoinedGroupsMembersIds(@NotEmpty Set<Long> userIds) {
        return queryUsersJoinedGroupsIds(userIds, null, null)
                .collect(Collectors.toSet())
                .flatMap(groupsIds -> groupsIds.isEmpty()
                        ? Mono.empty()
                        : queryGroupMembersIds(groupsIds).collect(Collectors.toSet()));
    }

    public Mono<Boolean> isAllowedToCreateJoinQuestion(
            @NotNull Long userId,
            @NotNull Long groupId) {
        return isOwnerOrManager(userId, groupId);
    }

    public Flux<Long> queryGroupMembersIds(@NotNull Long groupId) {
        Query query = new Query().addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupMember.Fields.ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getUserId());
    }

    public Flux<Long> queryGroupMembersIds(@NotEmpty Set<Long> groupIds) {
        Query query = new Query().addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).in(groupIds));
        query.fields().include(GroupMember.Fields.ID_USER_ID);
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getUserId());
    }

    public Flux<GroupMember> queryGroupsMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable Set<@GroupMemberRoleConstraint GroupMemberRole> roles,
            @Nullable DateRange joinDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
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
            @Nullable Set<@GroupMemberRoleConstraint GroupMemberRole> roles,
            @Nullable DateRange joinDateRange,
            @Nullable DateRange muteEndDateRange) {
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

    public Mono<Boolean> deleteGroupMembers(boolean updateGroupMembersVersion) {
        Query query = new Query();
        return mongoTemplate.remove(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .flatMap(result -> {
                    if (result.wasAcknowledged()) {
                        return updateGroupMembersVersion
                                ? groupVersionService.updateMembersVersion().thenReturn(true)
                                : Mono.just(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    public Mono<Boolean> deleteGroupsMembers(@NotEmpty Set<GroupMember.Key> keys, boolean updateGroupsMembersVersion) {
        return MapUtil.fluxMerge(map -> {
            for (GroupMember.Key key : keys) {
                map.put(key.getGroupId(), key.getUserId());
            }
        }, (monos, key, values) -> monos.add(deleteGroupMembers(key, values, null, updateGroupsMembersVersion)));
    }

    public Flux<GroupMember> queryGroupMembers(@NotNull Long groupId, @NotEmpty Set<Long> membersIds) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).in(membersIds));
        return mongoTemplate.find(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

    public Mono<GroupMembersWithVersion> authAndQueryGroupMembers(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotEmpty Set<Long> membersIds,
            boolean withStatus) {
        return isGroupMember(groupId, requesterId)
                .flatMap(isGroupMember -> isGroupMember == null || !isGroupMember
                        ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED))
                        : queryGroupMembers(groupId, membersIds).collectList())
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
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)))
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

    public Mono<Boolean> authAndUpdateGroupMember(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable String name,
            @Nullable @GroupMemberRoleConstraint GroupMemberRole role,
            @Nullable Date muteEndDate) {
        Mono<Boolean> authorized;
        if (role != null) {
            authorized = isOwner(requesterId, groupId);
        } else if (muteEndDate != null || (name != null && !requesterId.equals(memberId))) {
            authorized = isOwnerOrManager(requesterId, groupId);
        } else {
            return Mono.just(true);
        }
        return authorized
                .flatMap(isAuthorized -> {
                    if (isAuthorized != null && isAuthorized) {
                        return updateGroupMember(groupId, memberId, name, role, new Date(), muteEndDate, null, false);
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Mono<Boolean> deleteAllGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable ReactiveMongoOperations operations,
            boolean updateMembersVersion) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .buildQuery();
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.remove(query, GroupMember.class, GroupMember.COLLECTION_NAME)
                .flatMap(result -> {
                    if (result.wasAcknowledged()) {
                        return updateMembersVersion
                                ? groupVersionService.updateMembersVersion(groupIds).thenReturn(true)
                                : Mono.just(true);
                    } else {
                        return Mono.just(false);
                    }
                });
    }

    public Flux<Long> queryGroupManagersAndOwnerId(@NotNull Long groupId) {
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

    public Mono<Boolean> exists(@NotNull Long groupId, @NotNull Long userId) {
        Query query = new Query()
                .addCriteria(Criteria.where(GroupMember.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupMember.Fields.ID_USER_ID).is(userId));
        return mongoTemplate.exists(query, GroupMember.class, GroupMember.COLLECTION_NAME);
    }

}
