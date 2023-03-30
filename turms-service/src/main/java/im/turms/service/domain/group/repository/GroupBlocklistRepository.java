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

import com.mongodb.client.result.UpdateResult;
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
import im.turms.service.domain.group.po.GroupBlockedUser;

/**
 * @author James Chen
 */
@Repository
public class GroupBlocklistRepository
        extends BaseRepository<GroupBlockedUser, GroupBlockedUser.Key> {

    public GroupBlocklistRepository(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupBlockedUser.class);
    }

    public Mono<UpdateResult> updateBlockedUsers(
            Set<GroupBlockedUser.Key> keys,
            @Nullable Date blockDate,
            @Nullable Long requesterId) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, keys);
        Update update = Update.newBuilder(2)
                .setIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDate)
                .setIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterId);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Long> count(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(GroupBlockedUser.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupBlockedUser.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDateRange);
        return mongoClient.count(entityClass, filter);
    }

    public Flux<Long> findBlockedUserIds(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(GroupBlockedUser.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupBlockedUser.Fields.ID_USER_ID);
        return mongoClient.findMany(entityClass, filter, options)
                .map(groupBlockedUser -> groupBlockedUser.getKey()
                        .getUserId());
    }

    public Flux<GroupBlockedUser> findBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(GroupBlockedUser.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupBlockedUser.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(entityClass, filter, options);
    }

}
