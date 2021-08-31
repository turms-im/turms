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

package im.turms.service.workflow.service.impl.group;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.service.constant.DaoConstant;
import im.turms.service.workflow.dao.domain.group.GroupVersion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupVersionService {
    private final TurmsMongoClient mongoClient;

    public GroupVersionService(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        this.mongoClient = mongoClient;
    }

    public Mono<Date> queryInfoVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupVersion.Fields.INFO);
        return mongoClient.findOne(GroupVersion.class, filter, options)
                .map(GroupVersion::getInfo);
    }

    public Mono<Date> queryMembersVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupVersion.Fields.MEMBERS);
        return mongoClient.findOne(GroupVersion.class, filter, options)
                .map(GroupVersion::getMembers);
    }

    public Mono<Date> queryBlocklistVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupVersion.Fields.BLOCKLIST);
        return mongoClient.findOne(GroupVersion.class, filter, options)
                .map(GroupVersion::getBlocklist);
    }

    public Mono<Date> queryGroupJoinRequestsVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupVersion.Fields.JOIN_REQUESTS);
        return mongoClient.findOne(GroupVersion.class, filter, options)
                .map(GroupVersion::getJoinRequests);
    }

    public Mono<Date> queryGroupJoinQuestionsVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupVersion.Fields.JOIN_QUESTIONS);
        return mongoClient.findOne(GroupVersion.class, filter, options)
                .map(GroupVersion::getJoinQuestions);
    }

    public Mono<Date> queryGroupInvitationsVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupVersion.Fields.INVITATIONS);
        return mongoClient.findOne(GroupVersion.class, filter, options)
                .map(GroupVersion::getInvitations);
    }

    public Mono<Boolean> updateVersion(
            @NotNull Long groupId,
            boolean updateInfo,
            boolean updateMembers,
            boolean updateBlocklist,
            boolean joinRequests,
            boolean joinQuestions) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        Date now = new Date();
        Update update = Update.newBuilder(5)
                .setIfTrue(GroupVersion.Fields.INFO, now, updateInfo)
                .setIfTrue(GroupVersion.Fields.MEMBERS, now, updateMembers)
                .setIfTrue(GroupVersion.Fields.BLOCKLIST, now, updateBlocklist)
                .setIfTrue(GroupVersion.Fields.JOIN_REQUESTS, now, joinRequests)
                .setIfTrue(GroupVersion.Fields.JOIN_QUESTIONS, now, joinQuestions);
        return mongoClient.updateOne(GroupVersion.class, filter, update)
                .map(result -> result.getModifiedCount() > 0);
    }

    public Mono<Boolean> updateInformation(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.INFO);
    }

    public Mono<Boolean> updateMembersVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.MEMBERS);
    }

    public Mono<UpdateResult> updateMembersVersion(@Nullable Set<Long> groupIds) {
        return updateSpecificVersion(groupIds, GroupVersion.Fields.MEMBERS);
    }

    public Mono<UpdateResult> updateMembersVersion() {
        return updateSpecificVersion(GroupVersion.Fields.MEMBERS);
    }

    public Mono<Boolean> updateBlocklistVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.BLOCKLIST);
    }

    public Mono<Boolean> updateJoinRequestsVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.JOIN_REQUESTS);
    }

    public Mono<Boolean> updateJoinQuestionsVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.JOIN_QUESTIONS);
    }

    public Mono<Boolean> updateGroupInvitationsVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.INVITATIONS);
    }

    public Mono<Boolean> updateSpecificVersion(@NotNull Long groupId, @NotNull String field) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(field, "field");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, groupId);
        Update update = Update.newBuilder(1)
                .set(field, new Date());
        return mongoClient.updateOne(GroupVersion.class, filter, update)
                .map(result -> result.getModifiedCount() > 0);
    }

    public Mono<UpdateResult> updateSpecificVersion(@NotNull String field) {
        try {
            AssertUtil.notNull(field, "field");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Update update = Update.newBuilder(1)
                .set(field, new Date());
        return mongoClient.updateMany(GroupVersion.class, Filter.newBuilder(0), update);
    }

    public Mono<UpdateResult> updateSpecificVersion(@Nullable Set<Long> groupIds, @NotNull String field) {
        try {
            AssertUtil.notNull(field, "field");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, groupIds);
        Update update = Update.newBuilder(1)
                .set(field, new Date());
        return mongoClient.updateMany(GroupVersion.class, filter, update);
    }

    public Mono<GroupVersion> upsert(@NotNull Long groupId, @NotNull Date timestamp) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(timestamp, "timestamp");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupVersion version = new GroupVersion(groupId, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp);
        return mongoClient.insert(version)
                .thenReturn(version);
    }

    public Mono<DeleteResult> delete(@NotEmpty Set<Long> groupIds, @Nullable ClientSession session) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, groupIds);
        return mongoClient.deleteMany(session, GroupVersion.class, filter);
    }

}