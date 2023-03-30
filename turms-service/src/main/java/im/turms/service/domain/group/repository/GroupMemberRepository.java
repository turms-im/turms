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

package im.turms.service.domain.group.repository;

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.infra.validation.ValidGroupMemberRole;

/**
 * @author James Chen
 */
@Repository
public class GroupMemberRepository extends BaseRepository<GroupMember, GroupMember.Key> {

    public GroupMemberRepository(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupMember.class);
    }

    public Mono<DeleteResult> deleteAllGroupMembers(
            @Nullable Set<Long> groupIds,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds);
        return mongoClient.deleteMany(session, entityClass, filter);
    }

    public Mono<UpdateResult> updateGroupMembers(
            Set<GroupMember.Key> keys,
            @Nullable String name,
            @Nullable GroupMemberRole role,
            @Nullable Date joinDate,
            @Nullable Date muteEndDate,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, keys);
        Update update = Update.newBuilder(muteEndDate == null
                ? 3
                : 4)
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
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<Long> countMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable Set<GroupMemberRole> roles,
            @Nullable DateRange joinDateRange,
            @Nullable DateRange muteEndDateRange) {
        Filter filter = Filter.newBuilder(7)
                .inIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupMember.Fields.ROLE, roles)
                .addBetweenIfNotNull(GroupMember.Fields.JOIN_DATE, joinDateRange)
                .addBetweenIfNotNull(GroupMember.Fields.MUTE_END_DATE, muteEndDateRange);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<GroupMember> findGroupManagersAndOwnerId(Long groupId) {
        Filter filter = Filter.newBuilder(2)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId)
                .in(GroupMember.Fields.ROLE, GroupMemberRole.MANAGER, GroupMemberRole.OWNER);
        return mongoClient.findMany(entityClass, filter);
    }

    public Flux<GroupMember> findGroupMembers(Long groupId, Set<Long> memberIds) {
        Filter filter = Filter.newBuilder(2)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId)
                .in(GroupMember.Fields.ID_USER_ID, memberIds);
        return mongoClient.findMany(entityClass, filter);
    }

    public Flux<GroupMember> findGroupsMembers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable Set<@ValidGroupMemberRole GroupMemberRole> roles,
            @Nullable DateRange joinDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(7)
                .inIfNotNull(GroupMember.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupMember.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupMember.Fields.ROLE, roles)
                .addBetweenIfNotNull(GroupMember.Fields.JOIN_DATE, joinDateRange)
                .addBetweenIfNotNull(GroupMember.Fields.MUTE_END_DATE, muteEndDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<Long> findGroupMemberIds(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(member -> member.getKey()
                        .getUserId());
    }

    public Flux<Long> findGroupMemberIds(Set<Long> groupIds) {
        Filter filter = Filter.newBuilder(1)
                .in(GroupMember.Fields.ID_GROUP_ID, groupIds);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(member -> member.getKey()
                        .getUserId());
    }

    public Flux<GroupMember> findGroupMemberKeyAndRoleParis(Set<Long> userIds, Long groupId) {
        Filter filter = Filter.newBuilder(2)
                .in(GroupMember.Fields.ID_USER_ID, userIds)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ROLE);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Mono<GroupMemberRole> findGroupMemberRole(Long userId, Long groupId) {
        Filter filter = Filter.newBuilder(2)
                .eq(GroupMember.Fields.ID_USER_ID, userId)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ROLE);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupMember::getRole);
    }

    public Flux<Long> findMemberIdsByGroupId(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(groupMember -> groupMember.getKey()
                        .getUserId());
    }

    public Flux<Long> findUserJoinedGroupIds(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(GroupMember.Fields.ID_USER_ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupMember.Fields.ID_GROUP_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(groupMember -> groupMember.getKey()
                        .getGroupId());
    }

    public Flux<Long> findUsersJoinedGroupIds(
            @NotEmpty Set<Long> userIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(GroupMember.Fields.ID_USER_ID, userIds);
        QueryOptions options = QueryOptions.newBuilder(3)
                .paginateIfNotNull(page, size)
                .include(GroupMember.Fields.ID_GROUP_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(groupMember -> groupMember.getKey()
                        .getGroupId());
    }

    public Mono<Boolean> isMemberMuted(Long groupId, Long userId) {
        Filter filter = Filter.newBuilder(3)
                .eq(GroupMember.Fields.ID_GROUP_ID, groupId)
                .eq(GroupMember.Fields.ID_USER_ID, userId)
                .gt(GroupMember.Fields.MUTE_END_DATE, new Date());
        return mongoClient.exists(entityClass, filter);
    }

}
