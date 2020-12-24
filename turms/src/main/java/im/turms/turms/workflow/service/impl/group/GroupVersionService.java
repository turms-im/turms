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

package im.turms.turms.workflow.service.impl.group;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.domain.GroupVersion;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
public class GroupVersionService {
    private final ReactiveMongoTemplate mongoTemplate;

    public GroupVersionService(@Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public Mono<Date> queryInfoVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        query.fields().include(GroupVersion.Fields.INFO);
        return mongoTemplate.findOne(query, GroupVersion.class, GroupVersion.COLLECTION_NAME).map(GroupVersion::getInfo);
    }

    public Mono<Date> queryMembersVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        query.fields().include(GroupVersion.Fields.MEMBERS);
        return mongoTemplate.findOne(query, GroupVersion.class, GroupVersion.COLLECTION_NAME).map(GroupVersion::getMembers);
    }

    public Mono<Date> queryBlacklistVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        query.fields().include(GroupVersion.Fields.BLACKLIST);
        return mongoTemplate.findOne(query, GroupVersion.class, GroupVersion.COLLECTION_NAME).map(GroupVersion::getBlacklist);
    }

    public Mono<Date> queryGroupJoinRequestsVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        query.fields().include(GroupVersion.Fields.JOIN_REQUESTS);
        return mongoTemplate.findOne(query, GroupVersion.class, GroupVersion.COLLECTION_NAME).map(GroupVersion::getJoinRequests);
    }

    public Mono<Date> queryGroupJoinQuestionsVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        query.fields().include(GroupVersion.Fields.JOIN_QUESTIONS);
        return mongoTemplate.findOne(query, GroupVersion.class, GroupVersion.COLLECTION_NAME).map(GroupVersion::getJoinQuestions);
    }

    public Mono<Date> queryGroupInvitationsVersion(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        query.fields().include(GroupVersion.Fields.INVITATIONS);
        return mongoTemplate.findOne(query, GroupVersion.class, GroupVersion.COLLECTION_NAME).map(GroupVersion::getInvitations);
    }

    public Mono<Boolean> updateVersion(
            @NotNull Long groupId,
            boolean updateInfo,
            boolean updateMembers,
            boolean updateBlacklist,
            boolean joinRequests,
            boolean joinQuestions) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        Update update = new Update();
        Date now = new Date();
        if (updateInfo) {
            update.set(GroupVersion.Fields.INFO, now);
        }
        if (updateMembers) {
            update.set(GroupVersion.Fields.MEMBERS, now);
        }
        if (updateBlacklist) {
            update.set(GroupVersion.Fields.BLACKLIST, now);
        }
        if (joinRequests) {
            update.set(GroupVersion.Fields.JOIN_REQUESTS, now);
        }
        if (joinQuestions) {
            update.set(GroupVersion.Fields.JOIN_QUESTIONS, now);
        }
        return mongoTemplate.updateFirst(query, update, GroupVersion.class, GroupVersion.COLLECTION_NAME)
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

    public Mono<Boolean> updateBlacklistVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.BLACKLIST);
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
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupId));
        Update update = new Update().set(field, new Date());
        return mongoTemplate.updateFirst(query, update, GroupVersion.class, GroupVersion.COLLECTION_NAME)
                .map(result -> result.getModifiedCount() > 0);
    }

    public Mono<UpdateResult> updateSpecificVersion(@NotNull String field) {
        try {
            AssertUtil.notNull(field, "field");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Update update = new Update().set(field, new Date());
        return mongoTemplate.updateMulti(new Query(), update, GroupVersion.class, GroupVersion.COLLECTION_NAME);
    }

    public Mono<UpdateResult> updateSpecificVersion(@Nullable Set<Long> groupIds, @NotNull String field) {
        try {
            AssertUtil.notNull(field, "field");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, groupIds)
                .buildQuery();
        Update update = new Update().set(field, new Date());
        return mongoTemplate.updateMulti(query, update, GroupVersion.class, GroupVersion.COLLECTION_NAME);
    }

    public Mono<GroupVersion> upsert(@NotNull Long groupId, @NotNull Date timestamp) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(timestamp, "timestamp");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupVersion version = new GroupVersion(groupId, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp);
        return mongoTemplate.insert(version, GroupVersion.COLLECTION_NAME);
    }

    public Mono<DeleteResult> delete(@NotEmpty Set<Long> groupIds, @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(groupIds));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.remove(query, GroupVersion.class, GroupVersion.COLLECTION_NAME);
    }

}