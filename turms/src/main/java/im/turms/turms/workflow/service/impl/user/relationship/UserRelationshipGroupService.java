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

import com.google.common.collect.HashMultimap;
import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.user.UserRelationshipGroupsWithVersion;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constraint.ValidUserRelationshipGroupKey;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.Group;
import im.turms.turms.workflow.dao.domain.GroupMember;
import im.turms.turms.workflow.dao.domain.UserRelationshipGroup;
import im.turms.turms.workflow.dao.domain.UserRelationshipGroupMember;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    private static final UserRelationshipGroup EMPTY_RELATIONSHIP_GROUP = new UserRelationshipGroup(null, null, null, null);

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
            groupIndex = (int) node.nextId(ServiceType.USER_RELATIONSHIP_GROUP);
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
        return mongoOperations.insert(group, Group.COLLECTION_NAME);
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
                        UserRelationshipGroupsWithVersion.Builder builder = UserRelationshipGroupsWithVersion.newBuilder();
                        builder.setLastUpdatedDate(Int64Value.newBuilder().setValue(date.getTime()).build());
                        return queryRelationshipGroupsInfos(ownerId)
                                .map(group -> builder.addUserRelationshipGroups(ProtoUtil.relationshipGroup2proto(group)))
                                .then(Mono.just(builder.build()));
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

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
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID).is(relatedUserId));
        query.fields().include(UserRelationshipGroup.Fields.ID_GROUP_INDEX);
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
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_GROUP_INDEX).is(groupIndex));
        query.fields().include(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID);
        return mongoTemplate.find(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getRelatedUserId());
    }

    public Mono<Boolean> updateRelationshipGroupName(
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
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_GROUP_INDEX).is(groupIndex));
        Update update = new Update().set(UserRelationshipGroup.Fields.NAME, newGroupName);
        return mongoTemplate.findAndModify(query, update, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                .defaultIfEmpty(EMPTY_RELATIONSHIP_GROUP)
                .flatMap(group -> {
                    if (EMPTY_RELATIONSHIP_GROUP == group) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT));
                    } else {
                        return userVersionService.updateRelationshipGroupsVersion(ownerId)
                                .thenReturn(true);
                    }
                });
    }

    public Mono<Boolean> updateRelationshipGroups(
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
        HashMultimap<Long, Integer> multimap = HashMultimap.create();
        for (UserRelationshipGroup.Key key : keys) {
            multimap.put(key.getOwnerId(), key.getGroupIndex());
        }
        ArrayList<Mono<Boolean>> monos = new ArrayList<>(multimap.keySet().size());
        for (Long ownerId : multimap.keySet()) {
            Set<Integer> indexes = multimap.get(ownerId);
            monos.add(updateRelationshipGroups(ownerId, indexes, name, creationDate));
        }
        return Flux.merge(monos).all(value -> value);
    }

    public Mono<Boolean> updateRelationshipGroups(
            @NotNull Long ownerId,
            @Nullable Set<Integer> indexes,
            @Nullable String name,
            @Nullable @PastOrPresent Date creationDate) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.pastOrPresent(creationDate, "creationDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = QueryBuilder
                .newBuilder()
                .addIsIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerId)
                .addInIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .buildQuery();
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(UserRelationshipGroup.Fields.NAME, name)
                .setIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDate)
                .build();
        return mongoTemplate.updateMulti(query, update, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<Boolean> addRelatedUserToRelationshipGroups(
            @NotNull Long ownerId,
            @NotEmpty Set<Integer> groupIndexes,
            @NotNull Long relatedUserId,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notEmpty(groupIndexes, "groupIndexes");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userRelationshipService.hasOneSidedRelationship(ownerId, relatedUserId)
                .flatMap(hasRelationship -> {
                    if (hasRelationship != null && hasRelationship) {
                        List<Mono<?>> monos = new ArrayList<>(groupIndexes.size());
                        for (Integer groupIndex : groupIndexes) {
                            UserRelationshipGroupMember member = new UserRelationshipGroupMember(
                                    ownerId, groupIndex, relatedUserId, new Date());
                            ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                            monos.add(mongoOperations.save(member, GroupMember.COLLECTION_NAME));
                        }
                        monos.add(userVersionService.updateRelationshipGroupsVersion(ownerId));
                        return Mono.when(monos).thenReturn(true);
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Mono<Boolean> deleteRelationshipGroupAndMoveMembers(
            @NotNull Long ownerId,
            @NotNull Integer deleteGroupIndex,
            @NotNull Integer existingUsersToTargetGroupIndex) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(deleteGroupIndex, "deleteGroupIndex");
            AssertUtil.notNull(existingUsersToTargetGroupIndex, "existingUsersToTargetGroupIndex");
            AssertUtil.state(!deleteGroupIndex.equals(DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX), "The default relationship group cannot be deleted");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (deleteGroupIndex.equals(existingUsersToTargetGroupIndex)) {
            return Mono.just(true);
        } else {
            return mongoTemplate.inTransaction()
                    .execute(operations -> {
                        Query query = new Query()
                                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).is(ownerId))
                                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_GROUP_INDEX).is(deleteGroupIndex));
                        Update update = new Update().set(UserRelationshipGroup.Fields.ID_GROUP_INDEX, existingUsersToTargetGroupIndex);
                        return operations.findAndModify(query, update, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                                .then(operations.remove(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME))
                                .then(userVersionService.updateRelationshipGroupsVersion(ownerId))
                                .thenReturn(true);
                    })
                    .retryWhen(DaoConstant.TRANSACTION_RETRY)
                    .singleOrEmpty();
        }
    }

    public Mono<Boolean> deleteAllRelationshipGroups(
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
                    .flatMap(result -> result.wasAcknowledged()
                            ? userVersionService.updateRelationshipGroupsVersion(ownerIds).thenReturn(true)
                            : Mono.just(false));
        } else {
            return mongoOperations.remove(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                    .map(DeleteResult::wasAcknowledged);
        }
    }

    public Mono<Boolean> deleteRelatedUserFromAllRelationshipGroups(
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
        return deleteRelatedUsersFromAllRelationshipGroups(Set.of(ownerId), Set.of(relatedUserId), operations, updateRelationshipGroupsMembersVersion);
    }

    public Mono<Boolean> deleteRelatedUsersFromAllRelationshipGroups(
            @NotEmpty Set<Long> ownerIds,
            @NotEmpty Set<Long> relatedUserIds,
            @Nullable ReactiveMongoOperations operations,
            boolean updateRelationshipGroupsMembersVersion) {
        try {
            AssertUtil.notEmpty(ownerIds, "ownerIds");
            AssertUtil.notEmpty(relatedUserIds, "relatedUserIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).in(ownerIds))
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID).in(relatedUserIds));
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        if (updateRelationshipGroupsMembersVersion) {
            return mongoOperations.remove(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                    .flatMap(result -> {
                        if (result.wasAcknowledged()) {
                            return userVersionService.updateRelationshipGroupsVersion(ownerIds)
                                    .thenReturn(true);
                        } else {
                            return Mono.just(false);
                        }
                    });
        } else {
            return mongoOperations.remove(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                    .map(DeleteResult::wasAcknowledged);
        }
    }

    public Mono<Boolean> removeRelatedUserFromRelationshipGroup(
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
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID).is(relatedUserId))
                .addCriteria(Criteria.where(UserRelationshipGroup.Fields.ID_GROUP_INDEX).is(currentGroupIndex));
        if (currentGroupIndex.equals(targetGroupIndex)) {
            return Mono.just(true);
        } else {
            Update update = new Update().set(UserRelationshipGroup.Fields.ID_GROUP_INDEX, targetGroupIndex);
            return mongoTemplate.findAndModify(query, update, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                    .thenReturn(true);
        }
    }

    public Mono<Boolean> deleteRelationshipGroups() {
        return mongoTemplate.remove(new Query(), UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
    }

    public Mono<Boolean> deleteRelationshipGroups(@NotEmpty Set<UserRelationshipGroup.@ValidUserRelationshipGroupKey Key> keys) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationshipGroup.Key key : keys) {
                DomainConstraintUtil.validRelationshipGroupKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        HashMultimap<Long, Integer> multimap = HashMultimap.create();
        for (UserRelationshipGroup.Key key : keys) {
            multimap.put(key.getOwnerId(), key.getGroupIndex());
        }
        Set<Long> longs = multimap.keySet();
        ArrayList<Mono<Boolean>> monos = new ArrayList<>(longs.size());
        for (Long ownerId : longs) {
            Set<Integer> indexes = multimap.get(ownerId);
            monos.add(deleteRelationshipGroups(ownerId, indexes));
        }
        return Flux.merge(monos).all(value -> value);
    }

    public Mono<Boolean> deleteRelationshipGroups(@Nullable Long ownerId, @Nullable Set<Integer> indexes) {
        Query query = QueryBuilder
                .newBuilder()
                .addIsIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerId)
                .addInIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .buildQuery();
        return mongoTemplate.remove(query, UserRelationshipGroup.class, UserRelationshipGroup.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
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
