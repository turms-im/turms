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

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.user.po.UserRelationshipGroup;

/**
 * @author James Chen
 */
@Repository
public class UserRelationshipGroupRepository
        extends BaseRepository<UserRelationshipGroup, UserRelationshipGroup.Key> {

    public UserRelationshipGroupRepository(
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserRelationshipGroup.class);
    }

    public Mono<DeleteResult> deleteAllRelationshipGroups(
            Set<Long> ownerIds,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .in(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds);
        return mongoClient.deleteMany(session, entityClass, filter);
    }

    public Mono<UpdateResult> updateRelationshipGroupName(
            Long ownerId,
            Integer groupIndex,
            String newGroupName) {
        UserRelationshipGroup.Key key = new UserRelationshipGroup.Key(ownerId, groupIndex);
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, key);
        Update update = Update.newBuilder(1)
                .set(UserRelationshipGroup.Fields.NAME, newGroupName);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateRelationshipGroups(
            Set<UserRelationshipGroup.Key> keys,
            @Nullable String name,
            @Nullable Date creationDate) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, keys);
        Update update = Update.newBuilder(2)
                .setIfNotNull(UserRelationshipGroup.Fields.NAME, name)
                .setIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDate);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Long> countRelationshipGroups(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> indexes,
            @Nullable Set<String> names,
            @Nullable DateRange creationDateRange) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .inIfNotNull(UserRelationshipGroup.Fields.NAME, names)
                .addBetweenIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDateRange);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<UserRelationshipGroup> findRelationshipGroups(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> indexes,
            @Nullable Set<String> names,
            @Nullable DateRange creationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .inIfNotNull(UserRelationshipGroup.Fields.NAME, names)
                .addBetweenIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<UserRelationshipGroup> findRelationshipGroupsInfos(Long ownerId) {
        Filter filter = Filter.newBuilder(1)
                .eq(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerId);
        return mongoClient.findMany(entityClass, filter);
    }

}
