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

package im.turms.service.domain.user.repository;

import java.util.Collection;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.service.domain.user.po.UserRelationshipGroupMember;

/**
 * @author James Chen
 */
@Repository
public class UserRelationshipGroupMemberRepository
        extends BaseRepository<UserRelationshipGroupMember, UserRelationshipGroupMember.Key> {

    public UserRelationshipGroupMemberRepository(
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserRelationshipGroupMember.class);
    }

    public Mono<DeleteResult> deleteRelatedUsersFromAllRelationshipGroups(
            Long ownerId,
            Collection<Long> relatedUserIds,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(2)
                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                .in(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID, relatedUserIds);
        return mongoClient.deleteMany(session, entityClass, filter);
    }

    public Mono<Long> countMembers(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndexes);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<Integer> findGroupIndexes(Long ownerId, Long relatedUserId) {
        Filter filter = Filter.newBuilder(2)
                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                .eq(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID, relatedUserId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX);
        return mongoClient.findMany(entityClass, filter, options)
                .map(member -> member.getKey()
                        .getGroupIndex());
    }

    public Flux<Long> findRelationshipGroupMemberIds(Long ownerId, Integer groupIndex) {
        Filter filter = Filter.newBuilder(2)
                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                .eq(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndex);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(member -> member.getKey()
                        .getRelatedUserId());
    }

    public Flux<Long> findRelationshipGroupMemberIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndexes);
        QueryOptions options = QueryOptions.newBuilder(3)
                .paginateIfNotNull(page, size)
                .include(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(member -> member.getKey()
                        .getRelatedUserId());
    }

    public Flux<UserRelationshipGroupMember> findRelationshipGroupMembers(
            Long ownerId,
            Integer groupIndex) {
        Filter filterMember = Filter.newBuilder(2)
                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                .eq(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndex);
        return mongoClient.findMany(entityClass, filterMember);
    }

}
