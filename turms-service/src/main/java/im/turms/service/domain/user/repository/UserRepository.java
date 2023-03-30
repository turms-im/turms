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
import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.ProfileAccessStrategy;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;

/**
 * @author James Chen
 */
@Repository
public class UserRepository extends BaseRepository<User, Long> {

    public UserRepository(@Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, User.class);
    }

    public Mono<UpdateResult> updateUsers(
            Set<Long> userIds,
            @Nullable byte[] password,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String profilePicture,
            @Nullable ProfileAccessStrategy profileAccessStrategy,
            @Nullable Long permissionGroupId,
            @Nullable Date registrationDate,
            @Nullable Boolean isActive) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, userIds);
        Update update = Update.newBuilder(9)
                .setIfNotNull(User.Fields.PASSWORD, password)
                .setIfNotNull(User.Fields.NAME, name)
                .setIfNotNull(User.Fields.INTRO, intro)
                .setIfNotNull(User.Fields.PROFILE_PICTURE, profilePicture)
                .setIfNotNull(User.Fields.PROFILE_ACCESS_STRATEGY, profileAccessStrategy)
                .setIfNotNull(User.Fields.PERMISSION_GROUP_ID, permissionGroupId)
                .setIfNotNull(User.Fields.REGISTRATION_DATE, registrationDate)
                .setIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .setIfNotNull(User.Fields.LAST_UPDATED_DATE, new Date());
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateUsersDeletionDate(Set<Long> userIds) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, userIds);
        Date now = new Date();
        Update update = Update.newBuilder(2)
                .set(User.Fields.DELETION_DATE, now)
                .set(User.Fields.LAST_UPDATED_DATE, now);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Boolean> checkIfUserExists(Long userId, boolean queryDeletedRecords) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, userId)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Long> countRegisteredUsers(
            @Nullable DateRange dateRange,
            boolean queryDeletedRecords) {
        Filter filter = Filter.newBuilder(3)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, dateRange)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countDeletedUsers(@Nullable DateRange dateRange) {
        Filter filter = Filter.newBuilder(2)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, dateRange);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countUsers(boolean queryDeletedRecords) {
        Filter filter = Filter.newBuilder(1)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> countUsers(
            @Nullable Set<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive) {
        Filter filter = Filter.newBuilder(6)
                .inIfNotNull(DomainFieldName.ID, userIds)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, registrationDateRange)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, deletionDateRange)
                .eqIfNotNull(User.Fields.IS_ACTIVE, isActive);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<String> findName(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(User.Fields.NAME);
        return mongoClient.findOne(entityClass, filter, options)
                .map(User::getName);
    }

    public Mono<ProfileAccessStrategy> findProfileAccessIfNotDeleted(Long userId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, userId)
                .eq(User.Fields.DELETION_DATE, null);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(User.Fields.PROFILE_ACCESS_STRATEGY);
        return mongoClient.findOne(entityClass, filter, options)
                .map(User::getProfileAccessStrategy);
    }

    public Flux<User> findUsers(
            @Nullable Collection<Long> userIds,
            @Nullable DateRange registrationDateRange,
            @Nullable DateRange deletionDateRange,
            @Nullable Boolean isActive,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean queryDeletedRecords) {
        Filter filter = Filter.newBuilder(7)
                .inIfNotNull(DomainFieldName.ID, userIds)
                .addBetweenIfNotNull(User.Fields.REGISTRATION_DATE, registrationDateRange)
                .addBetweenIfNotNull(User.Fields.DELETION_DATE, deletionDateRange)
                .eqIfNotNull(User.Fields.IS_ACTIVE, isActive)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<User> findNotDeletedUserProfiles(
            Collection<Long> userIds,
            @Nullable Date lastUpdatedDate) {
        Filter filter = Filter.newBuilder(3)
                .in(DomainFieldName.ID, userIds)
                .eq(User.Fields.DELETION_DATE, null)
                .gtIfNotNull(User.Fields.LAST_UPDATED_DATE, lastUpdatedDate);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(DomainFieldName.ID,
                        User.Fields.NAME,
                        User.Fields.INTRO,
                        User.Fields.PROFILE_PICTURE,
                        User.Fields.REGISTRATION_DATE,
                        User.Fields.PROFILE_ACCESS_STRATEGY,
                        User.Fields.PERMISSION_GROUP_ID,
                        User.Fields.IS_ACTIVE);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<User> findUsersProfile(Collection<Long> userIds, boolean queryDeletedRecords) {
        Filter filter = Filter.newBuilder(2)
                .in(DomainFieldName.ID, userIds)
                .eqIfFalse(User.Fields.DELETION_DATE, null, queryDeletedRecords);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(DomainFieldName.ID,
                        User.Fields.NAME,
                        User.Fields.INTRO,
                        User.Fields.PROFILE_PICTURE,
                        User.Fields.REGISTRATION_DATE,
                        User.Fields.PROFILE_ACCESS_STRATEGY,
                        User.Fields.PERMISSION_GROUP_ID,
                        User.Fields.IS_ACTIVE);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Mono<Long> findUserPermissionGroupId(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(User.Fields.PERMISSION_GROUP_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(User::getPermissionGroupId);
    }

    public Mono<Boolean> isActiveAndNotDeleted(Long userId) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, userId)
                .eq(User.Fields.IS_ACTIVE, true)
                .eq(User.Fields.DELETION_DATE, null);
        return mongoClient.exists(entityClass, filter);
    }

}