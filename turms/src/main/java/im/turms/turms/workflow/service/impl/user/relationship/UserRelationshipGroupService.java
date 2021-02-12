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

package im.turms.turms.workflow.service.impl.user.relationship;

import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.model.bo.user.UserRelationshipGroupsWithVersion;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.MapUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidUserRelationshipGroupKey;
import im.turms.turms.constraint.ValidUserRelationshipKey;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.user.UserRelationship;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.turms.workflow.service.documentation.UsesNonIndexedData;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
public class UserRelationshipGroupService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final UserVersionService userVersionService;
    private final UserRelationshipService userRelationshipService;

    public UserRelationshipGroupService(
            Node node,
            @Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            UserVersionService userVersionService,
            @Lazy UserRelationshipService userRelationshipService) {
        this.mongoTemplate = mongoTemplate;
        this.node = node;
        this.userVersionService = userVersionService;
        this.userRelationshipService = userRelationshipService;
    }

    public Mono<UserRelationshipGroup> createRelationshipGroup(
            @NotNull Long ownerId,
            @Nullable Integer groupIndex,
            @NotNull String groupName,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(groupName, "groupName");
            AssertUtil.pastOrPresent(creationDate, "creationDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (groupIndex == null) {
            groupIndex = (int) node.nextRandomId(ServiceType.USER_RELATIONSHIP_GROUP);
        }
        if (creationDate == null) {
            creationDate = new Date();
        }
        UserRelationshipGroup group = new UserRelationshipGroup(
                ownerId,
                groupIndex,
                groupName,
                creationDate);
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        return mongoOperations.insert(group, UserRelationshipGroup.COLLECTION_NAME);
    }

    public Flux<UserRelationshipGroup> queryRelationshipGroupsInfos(@NotNull Long ownerId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).is(ownerId));
        return mongoTemplate.find(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
    }

    public Mono<UserRelationshipGroupsWithVersion> queryRelationshipGroupsInfosWithVersion(
            @NotNull Long ownerId,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userVersionService.queryRelationshipGroupsLastUpdatedDate(ownerId)
                .flatMap(date -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(date)) {
                        UserRelationshipGroupsWithVersion.Builder builder = UserRelationshipGroupsWithVersion.newBuilder()
                                .setLastUpdatedDate(Int64Value.of(date.getTime()));
                        return queryRelationshipGroupsInfos(ownerId)
                                .collect(CollectorUtil.toList())
                                .map(groups -> {
                                    for (UserRelationshipGroup group : groups) {
                                        builder.addUserRelationshipGroups(ProtoUtil.relationshipGroup2proto(group));
                                    }
                                    return builder.build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    @UsesNonIndexedData
    public Flux<Integer> queryGroupIndexes(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID).is(relatedUserId));
        query.fields().include(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX);
        return mongoTemplate.find(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getGroupIndex());
    }

    public Flux<Long> queryRelatedUserIdsInRelationshipGroup(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(groupIndex, "groupIndex");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX).is(groupIndex));
        query.fields().include(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID);
        return mongoTemplate.find(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getRelatedUserId());
    }

    public Mono<UpdateResult> updateRelationshipGroupName(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex,
            @NotNull String newGroupName) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(groupIndex, "groupIndex");
            AssertUtil.notNull(newGroupName, "newGroupName");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(new UserRelationshipGroup.Key(ownerId, groupIndex)));
        Update update = new Update().set(UserRelationshipGroup.Fields.NAME, newGroupName);
        return mongoTemplate.updateFirst(query, update, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                .flatMap(result -> userVersionService.updateRelationshipGroupsVersion(ownerId)
                        .onErrorResume(t -> Mono.empty())
                        .thenReturn(result));
    }

    public Mono<UpdateResult> updateRelationshipGroups(
            @NotEmpty Set<UserRelationshipGroup.@ValidUserRelationshipGroupKey Key> keys,
            @Nullable String name,
            @Nullable @PastOrPresent Date creationDate) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationshipGroup.Key key : keys) {
                DomainConstraintUtil.validRelationshipGroupKey(key);
            }
            AssertUtil.pastOrPresent(creationDate, "creationDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (name == null && creationDate == null) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(UserRelationshipGroup.Fields.NAME, name)
                .setIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDate)
                .build();
        return mongoTemplate.updateMulti(query, update, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
    }

    public Mono<UserRelationshipGroupMember> addRelatedUserToRelationshipGroups(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex,
            @NotNull Long relatedUserId,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(groupIndex, "groupIndex");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userRelationshipService.hasOneSidedRelationship(ownerId, relatedUserId)
                .flatMap(hasRelationship -> {
                    if (!hasRelationship) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ADD_NOT_RELATED_USER_TO_GROUP));
                    }
                    ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                    Date now = new Date();
                    UserRelationshipGroupMember member = new UserRelationshipGroupMember(
                            ownerId, groupIndex, relatedUserId, now);
                    return mongoOperations.save(member, UserRelationshipGroupMember.COLLECTION_NAME)
                            .flatMap(groupMember -> userVersionService.updateRelationshipGroupsVersion(ownerId)
                                    .onErrorResume(t -> Mono.empty())
                                    .thenReturn(groupMember));
                });
    }

    public Mono<UpdateResult> deleteRelationshipGroupAndMoveMembers(
            @NotNull Long ownerId,
            @NotNull Integer deleteGroupIndex,
            @NotNull Integer newGroupIndex) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(deleteGroupIndex, "deleteGroupIndex");
            AssertUtil.notNull(newGroupIndex, "newGroupIndex");
            AssertUtil.state(!deleteGroupIndex.equals(DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX), "The default relationship group cannot be deleted");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (deleteGroupIndex.equals(newGroupIndex)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        } else {
            return mongoTemplate.inTransaction()
                    .execute(operations -> {
                        Query queryMember = new Query()
                                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_OWNER_ID).is(ownerId))
                                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX).is(deleteGroupIndex));
                        Query queryGroup = new Query()
                                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(new UserRelationshipGroup.Key(ownerId, deleteGroupIndex)));
                        // FIXME: after https://github.com/turms-im/turms/issues/589 done
                        Update update = new Update().set(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, newGroupIndex);
                        return operations.updateFirst(queryMember, update, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                                .then(operations.remove(queryGroup, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME))
                                .then(userVersionService.updateRelationshipGroupsVersion(ownerId).onErrorResume(t -> Mono.empty()));
                    })
                    .retryWhen(DaoConstant.TRANSACTION_RETRY)
                    .singleOrEmpty();
        }
    }

    public Mono<DeleteResult> deleteAllRelationshipGroups(
            @NotEmpty Set<Long> ownerIds,
            @Nullable ReactiveMongoOperations operations,
            boolean updateRelationshipGroupsVersion) {
        try {
            AssertUtil.notEmpty(ownerIds, "ownerIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).in(ownerIds))
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_GROUP_INDEX).ne(0));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        if (updateRelationshipGroupsVersion) {
            return mongoOperations.remove(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                    .flatMap(result -> userVersionService.updateRelationshipGroupsVersion(ownerIds)
                            .onErrorResume(t -> Mono.empty())
                            .thenReturn(result));
        } else {
            return mongoOperations.remove(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
        }
    }

    public Mono<DeleteResult> deleteRelatedUserFromAllRelationshipGroups(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable ReactiveMongoOperations operations,
            boolean updateRelationshipGroupsMembersVersion) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return deleteRelatedUsersFromAllRelationshipGroups(Set.of(new UserRelationship.Key(ownerId, relatedUserId)), operations, updateRelationshipGroupsMembersVersion);
    }

    public Mono<DeleteResult> deleteRelatedUsersFromAllRelationshipGroups(
            @NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys,
            @Nullable ReactiveMongoOperations operations,
            boolean updateRelationshipGroupsMembersVersion) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationship.Key key : keys) {
                DomainConstraintUtil.validRelationshipKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        if (updateRelationshipGroupsMembersVersion) {
            return mongoOperations.remove(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                    .flatMap(result -> {
                        Set<Long> ownerIds = new HashSet<>(MapUtil.getCapability(keys.size()));
                        for (UserRelationship.Key key : keys) {
                            ownerIds.add(key.getOwnerId());
                        }
                        return userVersionService.updateRelationshipGroupsVersion(ownerIds)
                                .onErrorResume(t -> Mono.empty())
                                .thenReturn(result);
                    });
        } else {
            return mongoOperations.remove(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME);
        }
    }

    public Mono<UpdateResult> moveRelatedUserToNewGroup(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @NotNull Integer currentGroupIndex,
            @NotNull Integer targetGroupIndex) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
            AssertUtil.notNull(currentGroupIndex, "currentGroupIndex");
            AssertUtil.notNull(targetGroupIndex, "targetGroupIndex");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(new UserRelationshipGroupMember.Key(ownerId, currentGroupIndex, relatedUserId)));
        if (currentGroupIndex.equals(targetGroupIndex)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        } else {
            // FIXME: after https://github.com/turms-im/turms/issues/589 done
            Update update = new Update().set(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, targetGroupIndex);
            return mongoTemplate.updateFirst(query, update, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME);
        }
    }

    public Mono<DeleteResult> deleteRelationshipGroups() {
        return mongoTemplate.remove(new Query(), UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
    }

    public Mono<DeleteResult> deleteRelationshipGroups(@NotEmpty Set<UserRelationshipGroup.@ValidUserRelationshipGroupKey Key> keys) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationshipGroup.Key key : keys) {
                DomainConstraintUtil.validRelationshipGroupKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        return mongoTemplate.remove(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
    }

    public Flux<UserRelationshipGroup> queryRelationshipGroups(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> indexes,
            @Nullable Set<String> names,
            @Nullable DateRange creationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .addInIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .addInIfNotNull(UserRelationshipGroup.Fields.NAME, names)
                .addBetweenIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
    }

    public Mono<Long> countRelationshipGroups(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> indexes,
            @Nullable Set<String> names,
            @Nullable DateRange creationDateRange) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .addInIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .addInIfNotNull(UserRelationshipGroup.Fields.NAME, names)
                .addBetweenIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDateRange)
                .buildQuery();
        return mongoTemplate.count(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME);
    }

}