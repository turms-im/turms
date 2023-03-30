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

import java.util.Collection;
import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;

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
import im.turms.service.domain.group.po.Group;

/**
 * @author James Chen
 */
@Repository
public class GroupRepository extends BaseRepository<Group, Long> {

    public GroupRepository(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, Group.class);
    }

    public Mono<UpdateResult> updateGroupsDeletionDate(
            @Nullable Collection<Long> groupIds,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DomainFieldName.ID, groupIds);
        Update update = Update.newBuilder(1)
                .set(Group.Fields.DELETION_DATE, new Date());
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<UpdateResult> updateGroups(
            Set<Long> groupIds,
            @Nullable Long typeId,
            @Nullable Long creatorId,
            @Nullable Long ownerId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String announcement,
            @Nullable Integer minimumScore,
            @Nullable Boolean isActive,
            @Nullable Date creationDate,
            @Nullable Date deletionDate,
            @Nullable Date muteEndDate,
            @Nullable Date lastUpdatedDate,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, groupIds);
        Update update = Update.newBuilder(12)
                .setIfNotNull(Group.Fields.TYPE_ID, typeId)
                .setIfNotNull(Group.Fields.CREATOR_ID, creatorId)
                .setIfNotNull(Group.Fields.OWNER_ID, ownerId)
                .setIfNotNull(Group.Fields.NAME, name)
                .setIfNotNull(Group.Fields.INTRO, intro)
                .setIfNotNull(Group.Fields.ANNOUNCEMENT, announcement)
                .setIfNotNull(Group.Fields.MINIMUM_SCORE, minimumScore)
                .setIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                .setIfNotNull(Group.Fields.CREATION_DATE, creationDate)
                .setIfNotNull(Group.Fields.DELETION_DATE, deletionDate)
                .setIfNotNull(Group.Fields.LAST_UPDATED_DATE, lastUpdatedDate)
                .setIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDate);
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<Long> countCreatedGroups(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder(3)
                .addBetweenIfNotNull(Group.Fields.CREATION_DATE, dateRange)
                .eq(Group.Fields.DELETION_DATE, null);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countDeletedGroups(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder(2)
                .addBetweenIfNotNull(Group.Fields.DELETION_DATE, dateRange);
        return mongoClient.count(entityClass, filter);
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
            @Nullable DateRange muteEndDateRange) {
        Filter filter = Filter.newBuilder(13)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(Group.Fields.TYPE_ID, typeIds)
                .inIfNotNull(Group.Fields.CREATOR_ID, creatorIds)
                .inIfNotNull(Group.Fields.OWNER_ID, ownerIds)
                .eqIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                .addBetweenIfNotNull(Group.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(Group.Fields.DELETION_DATE, deletionDateRange)
                .addBetweenIfNotNull(Group.Fields.LAST_UPDATED_DATE, lastUpdatedDateRange)
                .addBetweenIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDateRange);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countOwnedGroups(Long ownerId) {
        Filter filter = Filter.newBuilder(1)
                .eq(Group.Fields.OWNER_ID, ownerId);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countOwnedGroups(Long ownerId, Long groupTypeId) {
        Filter filter = Filter.newBuilder(2)
                .eq(Group.Fields.OWNER_ID, ownerId)
                .eq(Group.Fields.TYPE_ID, groupTypeId);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<Group> findGroups(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> typeIds,
            @Nullable Set<Long> creatorIds,
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isActive,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable DateRange lastUpdatedDateRange,
            @Nullable DateRange muteEndDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(13)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(Group.Fields.TYPE_ID, typeIds)
                .inIfNotNull(Group.Fields.CREATOR_ID, creatorIds)
                .inIfNotNull(Group.Fields.OWNER_ID, ownerIds)
                .eqIfNotNull(Group.Fields.IS_ACTIVE, isActive)
                .addBetweenIfNotNull(Group.Fields.CREATION_DATE, creationDateRange)
                .addBetweenIfNotNull(Group.Fields.DELETION_DATE, deletionDateRange)
                .addBetweenIfNotNull(Group.Fields.LAST_UPDATED_DATE, lastUpdatedDateRange)
                .addBetweenIfNotNull(Group.Fields.MUTE_END_DATE, muteEndDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<Group> findNotDeletedGroups(
            @Nullable Set<Long> ids,
            @Nullable Date lastUpdatedDate) {
        Filter filter = Filter.newBuilder(3)
                .in(DomainFieldName.ID, ids)
                .eq(Group.Fields.DELETION_DATE, null)
                .gtIfNotNull(Group.Fields.LAST_UPDATED_DATE, lastUpdatedDate);
        return mongoClient.findMany(entityClass, filter);
    }

    public Mono<Long> findTypeId(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(Group.Fields.TYPE_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(Group::getTypeId);
    }

    public Mono<Long> findTypeIdIfActiveAndNotDeleted(Long groupId) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, groupId)
                .eq(Group.Fields.IS_ACTIVE, true)
                .eq(Group.Fields.DELETION_DATE, null);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(Group.Fields.TYPE_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(Group::getTypeId);
    }

    public Mono<Integer> findMinimumScore(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(Group.Fields.MINIMUM_SCORE);
        return mongoClient.findOne(entityClass, filter, options)
                .map(Group::getMinimumScore);
    }

    public Mono<Long> findOwnerId(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(Group.Fields.OWNER_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(Group::getOwnerId);
    }

    public Mono<Boolean> isGroupMuted(Long groupId, Date muteEndDate) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, groupId)
                .gt(Group.Fields.MUTE_END_DATE, muteEndDate);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> isGroupActiveAndNotDeleted(Long groupId) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, groupId)
                .eq(Group.Fields.IS_ACTIVE, true)
                .eq(Group.Fields.DELETION_DATE, null);
        return mongoClient.exists(entityClass, filter);
    }

}
