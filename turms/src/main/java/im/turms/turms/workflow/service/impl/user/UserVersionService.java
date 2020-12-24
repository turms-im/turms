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

package im.turms.turms.workflow.service.impl.user;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.workflow.dao.domain.UserVersion;
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
import java.util.Collections;
import java.util.Date;
import java.util.Set;

import static im.turms.turms.constant.DaoConstant.ID_FIELD_NAME;

/**
 * @author James Chen
 */
@Service
public class UserVersionService {

    private final ReactiveMongoTemplate mongoTemplate;

    public UserVersionService(@Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    // Query

    public Mono<Date> queryRelationshipsLastUpdatedDate(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.RELATIONSHIPS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getRelationships);
    }

    public Mono<Date> querySentGroupInvitationsLastUpdatedDate(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.SENT_GROUP_INVITATIONS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getSentGroupInvitations);
    }

    public Mono<Date> queryReceivedGroupInvitationsLastUpdatedDate(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.RECEIVED_GROUP_INVITATIONS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getReceivedGroupInvitations);
    }

    public Mono<Date> queryGroupJoinRequestsVersion(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.GROUP_JOIN_REQUESTS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getGroupJoinRequests);
    }

    public Mono<Date> queryRelationshipGroupsLastUpdatedDate(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.RELATIONSHIP_GROUPS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getRelationshipGroups);
    }

    public Mono<Date> queryJoinedGroupVersion(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.JOINED_GROUPS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getJoinedGroups);
    }

    public Mono<Date> querySentFriendRequestsVersion(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.SENT_FRIEND_REQUESTS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getSentFriendRequests);
    }

    public Mono<Date> queryReceivedFriendRequestsVersion(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).is(userId));
        query.fields().include(UserVersion.Fields.RECEIVED_FRIEND_REQUESTS);
        return mongoTemplate.findOne(query, UserVersion.class, UserVersion.COLLECTION_NAME)
                .map(UserVersion::getReceivedFriendRequests);
    }

    // Upsert

    public Mono<UserVersion> upsertEmptyUserVersion(
            @NotNull Long userId,
            @NotNull Date timestamp,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(timestamp, "timestamp");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserVersion userVersion = new UserVersion(userId, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp, timestamp);
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.save(userVersion, UserVersion.COLLECTION_NAME);
    }

    // Update

    public Mono<UpdateResult> updateRelationshipsVersion(@NotNull Long userId, @Nullable ReactiveMongoOperations operations) {
        return updateSpecificVersion(userId, operations, UserVersion.Fields.RELATIONSHIPS);
    }

    public Mono<UpdateResult> updateRelationshipsVersion(@NotEmpty Set<Long> userIds, @Nullable ReactiveMongoOperations operations) {
        return updateSpecificVersion(userIds, operations, UserVersion.Fields.RELATIONSHIPS);
    }

    public Mono<UpdateResult> updateSentFriendRequestsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.SENT_FRIEND_REQUESTS);
    }

    public Mono<UpdateResult> updateReceivedFriendRequestsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RECEIVED_FRIEND_REQUESTS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RELATIONSHIP_GROUPS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsVersion(@NotEmpty Set<Long> userIds) {
        return updateSpecificVersion(userIds, null, UserVersion.Fields.RELATIONSHIP_GROUPS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsMembersVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RELATIONSHIP_GROUPS_MEMBERS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsMembersVersion(@NotEmpty Set<Long> userIds) {
        return updateSpecificVersion(userIds, null, UserVersion.Fields.RELATIONSHIP_GROUPS_MEMBERS);
    }

    public Mono<UpdateResult> updateSentGroupInvitationsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.SENT_GROUP_INVITATIONS);
    }

    public Mono<UpdateResult> updateReceivedGroupInvitationsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RECEIVED_GROUP_INVITATIONS);
    }

    public Mono<UpdateResult> updateSentGroupJoinRequestsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.GROUP_JOIN_REQUESTS);
    }

    public Mono<UpdateResult> updateJoinedGroupsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.JOINED_GROUPS);
    }

    public Mono<UpdateResult> updateSpecificVersion(@NotNull Long userId, @Nullable ReactiveMongoOperations operations, @NotEmpty String... fields) {
        return updateSpecificVersion(Collections.singleton(userId), operations, fields);
    }

    public Mono<UpdateResult> updateSpecificVersion(@NotEmpty Set<Long> userIds, @Nullable ReactiveMongoOperations operations, @NotEmpty String... fields) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
            AssertUtil.notEmpty(fields, "fields");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).in(userIds));
        Update update = new Update();
        Date now = new Date();
        for (String field : fields) {
            update.set(field, now);
        }
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.updateMulti(query, update, UserVersion.class, UserVersion.COLLECTION_NAME);
    }

    // Delete

    public Mono<DeleteResult> delete(
            @NotEmpty Set<Long> userIds,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(ID_FIELD_NAME).in(userIds));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.remove(query, UserVersion.COLLECTION_NAME);
    }

}
