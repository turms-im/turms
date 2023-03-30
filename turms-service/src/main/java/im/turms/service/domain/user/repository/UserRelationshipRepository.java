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
import im.turms.service.domain.user.po.UserRelationship;

/**
 * @author James Chen
 */
@Repository
public class UserRelationshipRepository
        extends BaseRepository<UserRelationship, UserRelationship.Key> {

    public UserRelationshipRepository(@Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserRelationship.class);
    }

    public Mono<DeleteResult> deleteAllRelationships(
            Set<Long> userIds,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .or(Filter.newBuilder(1)
                        .in(UserRelationship.Fields.ID_OWNER_ID, userIds),
                        Filter.newBuilder(1)
                                .in(UserRelationship.Fields.ID_RELATED_USER_ID, userIds));
        return mongoClient.deleteMany(session, entityClass, filter);
    }

    public Mono<UpdateResult> updateUserOneSidedRelationships(
            Set<UserRelationship.Key> keys,
            @Nullable Date blockDate,
            @Nullable Date establishmentDate) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, keys);
        Update update = Update.newBuilder(2)
                .setIfNotNull(UserRelationship.Fields.ESTABLISHMENT_DATE, establishmentDate)
                .setOrUnsetDate(UserRelationship.Fields.BLOCK_DATE, blockDate);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Long> countRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked) {
        Filter filter = Filter.newBuilder(3)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                .neNullIfTrueOrEqNullIfFalse(UserRelationship.Fields.BLOCK_DATE, isBlocked);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<Long> findRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isBlocked) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .neNullIfTrueOrEqNullIfFalse(UserRelationship.Fields.BLOCK_DATE, isBlocked);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserRelationship.Fields.ID_RELATED_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(userRelationship -> userRelationship.getKey()
                        .getRelatedUserId());
    }

    public Flux<UserRelationship> findRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked,
            @Nullable DateRange establishmentDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                .addBetweenIfNotNull(UserRelationship.Fields.ESTABLISHMENT_DATE,
                        establishmentDateRange)
                .neNullIfTrueOrEqNullIfFalse(UserRelationship.Fields.BLOCK_DATE, isBlocked);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<UserRelationship> findRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Mono<Boolean> hasRelationshipAndNotBlocked(Long ownerId, Long relatedUserId) {
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, key)
                .eq(UserRelationship.Fields.BLOCK_DATE, null);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> isBlocked(Long ownerId, Long relatedUserId) {
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, key)
                .ne(UserRelationship.Fields.BLOCK_DATE, null);
        return mongoClient.exists(entityClass, filter);
    }

}
