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
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.group.po.GroupVersion;

/**
 * @author James Chen
 */
@Repository
public class GroupVersionRepository extends BaseRepository<GroupVersion, Long> {

    public GroupVersionRepository(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupVersion.class);
    }

    public Mono<UpdateResult> updateVersions(String field) {
        Update update = Update.newBuilder(1)
                .set(field, new Date());
        return mongoClient.updateOne(entityClass, Filter.newBuilder(0), update);
    }

    public Mono<UpdateResult> updateVersions(@Nullable Set<Long> groupIds, String field) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DomainFieldName.ID, groupIds);
        Update update = Update.newBuilder(1)
                .set(field, new Date());
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateVersion(Long groupId, String field) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        Update update = Update.newBuilder(1)
                .set(field, new Date());
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateVersion(
            Long groupId,
            boolean updateMembers,
            boolean updateBlocklist,
            boolean joinRequests,
            boolean joinQuestions) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        Date now = new Date();
        Update update = Update.newBuilder(4)
                .setIfTrue(GroupVersion.Fields.MEMBERS, now, updateMembers)
                .setIfTrue(GroupVersion.Fields.BLOCKLIST, now, updateBlocklist)
                .setIfTrue(GroupVersion.Fields.JOIN_REQUESTS, now, joinRequests)
                .setIfTrue(GroupVersion.Fields.JOIN_QUESTIONS, now, joinQuestions);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<Date> findBlocklist(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupVersion.Fields.BLOCKLIST);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupVersion::getBlocklist);
    }

    public Mono<Date> findInvitations(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupVersion.Fields.INVITATIONS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupVersion::getInvitations);
    }

    public Mono<Date> findJoinRequests(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupVersion.Fields.JOIN_REQUESTS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupVersion::getJoinRequests);
    }

    public Mono<Date> findJoinQuestions(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupVersion.Fields.JOIN_QUESTIONS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupVersion::getJoinQuestions);
    }

    public Mono<Date> findMembers(Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupVersion.Fields.MEMBERS);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupVersion::getMembers);
    }

}
