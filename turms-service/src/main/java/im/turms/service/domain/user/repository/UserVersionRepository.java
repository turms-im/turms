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

import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.user.po.UserVersion;

/**
 * @author James Chen
 */
@Repository
public class UserVersionRepository extends BaseRepository<UserVersion, Long> {

    public UserVersionRepository(@Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserVersion.class);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            Long userId,
            @Nullable ClientSession session,
            String... fields) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        Date now = new Date();
        Update update = Update.newBuilder(fields.length);
        for (String field : fields) {
            update.set(field, now);
        }
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            Long userId,
            @Nullable ClientSession session,
            String field) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        Date now = new Date();
        Update update = Update.newBuilder(1)
                .set(field, now);
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            Set<Long> userIds,
            @Nullable ClientSession session,
            String... fields) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, userIds);
        Date now = new Date();
        Update update = Update.newBuilder(fields.length);
        for (String field : fields) {
            update.set(field, now);
        }
        return mongoClient.updateMany(session, entityClass, filter, update);
    }

    public Mono<Date> findGroupJoinRequests(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.GROUP_JOIN_REQUESTS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getGroupJoinRequests);
    }

    public Mono<Date> findJoinedGroup(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.JOINED_GROUPS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getJoinedGroups);
    }

    public Mono<Date> findReceivedGroupInvitations(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.RECEIVED_GROUP_INVITATIONS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getReceivedGroupInvitations);
    }

    public Mono<Date> findRelationships(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.RELATIONSHIPS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getRelationships);
    }

    public Mono<Date> findRelationshipGroups(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.RELATIONSHIP_GROUPS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getRelationshipGroups);
    }

    public Mono<Date> findSentGroupInvitations(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.SENT_GROUP_INVITATIONS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getSentGroupInvitations);
    }

    public Mono<Date> findSentFriendRequests(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.SENT_FRIEND_REQUESTS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getSentFriendRequests);
    }

    public Mono<Date> findReceivedFriendRequests(Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserVersion.Fields.RECEIVED_FRIEND_REQUESTS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserVersion::getReceivedFriendRequests);
    }

}
