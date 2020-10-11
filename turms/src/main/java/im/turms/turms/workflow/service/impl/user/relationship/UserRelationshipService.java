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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.user.UserRelationshipsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.ReactorUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constraint.ValidUserRelationshipKey;
import im.turms.turms.util.MapUtil;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.UserRelationship;
import im.turms.turms.workflow.dao.domain.UserRelationshipGroupMember;
import im.turms.turms.workflow.dao.domain.UserVersion;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DuplicateKeyException;
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
import java.util.*;
import java.util.stream.Collectors;

import static im.turms.turms.constant.DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX;
import static im.turms.turms.constant.DaoConstant.TRANSACTION_RETRY;
import static im.turms.turms.workflow.dao.domain.UserRelationshipGroupMember.Fields.ID_GROUP_INDEX;

/**
 * @author James Chen
 */
@Service
public class UserRelationshipService {

    private final UserVersionService userVersionService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final ReactiveMongoTemplate mongoTemplate;

    public UserRelationshipService(
            UserVersionService userVersionService,
            @Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            UserRelationshipGroupService userRelationshipGroupService) {
        this.userVersionService = userVersionService;
        this.mongoTemplate = mongoTemplate;
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    public Mono<Boolean> deleteOneSidedRelationships(
            @NotEmpty Set<Long> ownerIds,
            @NotEmpty Set<Long> relatedUserIds) {
        try {
            AssertUtil.notEmpty(ownerIds, "ownerIds");
            AssertUtil.notEmpty(relatedUserIds, "relatedUserIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return mongoTemplate.inTransaction()
                .execute(operations -> {
                    Query query = new Query()
                            .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).in(ownerIds))
                            .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).in(relatedUserIds));
                    return operations.remove(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                            .flatMap(result -> result.wasAcknowledged()
                                    ? userRelationshipGroupService.deleteRelatedUsersFromAllRelationshipGroups(ownerIds, relatedUserIds, operations, true)
                                    .then(userVersionService.updateRelationshipsVersion(ownerIds, null))
                                    .thenReturn(true)
                                    : Mono.just(false));
                })
                .retryWhen(TRANSACTION_RETRY)
                .singleOrEmpty();
    }

    public Mono<Boolean> deleteAllRelationships(
            @NotEmpty Set<Long> userIds,
            @Nullable ReactiveMongoOperations operations,
            boolean updateRelationshipsVersion) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(new Criteria().orOperator(
                        Criteria.where(UserRelationship.Fields.ID_OWNER_ID).in(userIds),
                        Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).in(userIds)));
        if (updateRelationshipsVersion) {
            if (operations != null) {
                return operations.remove(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                        .flatMap(result -> result.wasAcknowledged()
                                ? userVersionService.updateRelationshipsVersion(userIds, operations)
                                : Mono.just(false));
            } else {
                return mongoTemplate.inTransaction()
                        .execute(newOperations -> newOperations.remove(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                                .flatMap(result -> result.wasAcknowledged()
                                        ? userVersionService.updateRelationshipsVersion(userIds, newOperations)
                                        : Mono.just(false)))
                        .retryWhen(TRANSACTION_RETRY)
                        .singleOrEmpty();
            }
        } else {
            ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
            return mongoOperations.remove(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                    .map(DeleteResult::wasAcknowledged);
        }
    }

    public Mono<Boolean> deleteOneSidedRelationships(@NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationship.Key key : keys) {
                DomainConstraintUtil.validRelationshipKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return MapUtil.fluxMerge(multimap -> {
            for (UserRelationship.Key key : keys) {
                multimap.put(key.getOwnerId(), key.getRelatedUserId());
            }
        }, (monos, key, values) -> monos.add(deleteOneSidedRelationships(Set.of(key), values)));
    }

    public Mono<Boolean> deleteOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (operations != null) {
            Query query = new Query()
                    .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                    .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(relatedUserId));
            return operations.remove(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                    .then(userRelationshipGroupService.deleteRelatedUserFromAllRelationshipGroups(
                            ownerId, relatedUserId, operations, false))
                    .then(userVersionService.updateSpecificVersion(
                            ownerId,
                            operations,
                            UserVersion.Fields.RELATIONSHIP_GROUPS_MEMBERS,
                            UserVersion.Fields.RELATIONSHIPS))
                    .thenReturn(true);
        } else {
            return mongoTemplate.inTransaction()
                    .execute(newOperations -> deleteOneSidedRelationship(ownerId, relatedUserId, newOperations))
                    .retryWhen(TRANSACTION_RETRY)
                    .singleOrEmpty();
        }
    }

    public Mono<Boolean> deleteTwoSidedRelationships(
            @NotNull Long userOneId,
            @NotNull Long userTwoId) {
        try {
            AssertUtil.notNull(userOneId, "userOneId");
            AssertUtil.notNull(userTwoId, "userTwoId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return mongoTemplate.inTransaction()
                .execute(operations -> deleteOneSidedRelationship(userOneId, userTwoId, operations)
                        .zipWith(deleteOneSidedRelationship(userTwoId, userOneId, operations))
                        .map(results -> results.getT1() && results.getT2()))
                .retryWhen(TRANSACTION_RETRY)
                .singleOrEmpty();
    }

    private Flux<Long> queryMembersRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .addInIfNotNull(ID_GROUP_INDEX, groupIndexes)
                .paginateIfNotNull(page, size);
        query.fields().include(UserRelationship.Fields.ID_RELATED_USER_ID);
        return mongoTemplate.find(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME)
                .map(member -> member.getKey().getRelatedUserId());
    }

    public Mono<Int64ValuesWithVersion> queryRelatedUserIdsWithVersion(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex,
            @Nullable Boolean isBlocked,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(groupIndex, "groupIndex");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userVersionService.queryRelationshipsLastUpdatedDate(ownerId)
                .flatMap(date -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(date)) {
                        return queryMembersRelatedUserIds(Set.of(ownerId), Set.of(groupIndex), isBlocked)
                                .collect(Collectors.toSet())
                                .map(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    return Int64ValuesWithVersion.newBuilder()
                                            .setLastUpdatedDate(Int64Value.newBuilder().setValue(date.getTime()).build())
                                            .addAllValues(ids)
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<UserRelationshipsWithVersion> queryRelationshipsWithVersion(
            @NotNull Long ownerId,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Integer groupIndex,
            @Nullable Boolean isBlocked,
            @Nullable Date lastUpdatedDate) {
        return userVersionService.queryRelationshipsLastUpdatedDate(ownerId)
                .flatMap(date -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(date)) {
                        return queryRelationships(
                                Set.of(ownerId),
                                relatedUserIds,
                                groupIndex != null ? Set.of(groupIndex) : null,
                                isBlocked,
                                null,
                                null,
                                null)
                                .collect(Collectors.toSet())
                                .map(relationships -> {
                                    if (relationships.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    UserRelationshipsWithVersion.Builder builder = UserRelationshipsWithVersion.newBuilder();
                                    builder.setLastUpdatedDate(Int64Value.newBuilder().setValue(date.getTime()).build());
                                    for (UserRelationship relationship : relationships) {
                                        im.turms.common.model.bo.user.UserRelationship userRelationship = ProtoUtil.relationship2proto(relationship).build();
                                        builder.addUserRelationships(userRelationship);
                                    }
                                    return builder.build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Flux<Long> queryRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isBlocked) {
        Query query = QueryBuilder.newBuilder()
                .addInIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .addIsIfNotNull(UserRelationship.Fields.IS_BLOCKED, isBlocked)
                .buildQuery();
        query.fields().include(UserRelationship.Fields.ID_RELATED_USER_ID);
        return mongoTemplate.find(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                .map(userRelationship -> userRelationship.getKey().getRelatedUserId());
    }

    public Flux<Long> queryMembersRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked) {
        if (groupIndexes != null && isBlocked != null) {
            return Mono.zip(
                    queryMembersRelatedUserIds(ownerIds, groupIndexes, null, null)
                            .collect(Collectors.toSet()),
                    queryRelatedUserIds(ownerIds, isBlocked)
                            .collect(Collectors.toSet()))
                    .flatMapIterable(tuple -> {
                        tuple.getT1().retainAll(tuple.getT2());
                        return tuple.getT1();
                    });
        } else if (groupIndexes != null) {
            return queryMembersRelatedUserIds(ownerIds, groupIndexes, null, null);
        } else {
            return queryRelatedUserIds(ownerIds, isBlocked);
        }
    }

    private Flux<UserRelationship> queryRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked,
            @Nullable DateRange establishmentDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder.newBuilder()
                .addInIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .addInIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                .addBetweenIfNotNull(UserRelationship.Fields.ESTABLISHMENT_DATE, establishmentDateRange)
                .addIsIfNotNull(UserRelationship.Fields.IS_BLOCKED, isBlocked)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
    }

    public Flux<UserRelationship> queryRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked,
            @Nullable DateRange establishmentDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        boolean queryByGroupIndexes = groupIndexes != null;
        boolean queryByRelationshipInfo = relatedUserIds != null || isBlocked != null || establishmentDateRange != null;
        if (queryByGroupIndexes && queryByRelationshipInfo) {
            if (relatedUserIds != null && relatedUserIds.isEmpty()) {
                return Flux.empty();
            }
            return queryMembersRelatedUserIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toSet())
                    .flatMapMany(userIds -> {
                        if (relatedUserIds != null) {
                            userIds.retainAll(relatedUserIds);
                        }
                        return queryRelationships(ownerIds, userIds, isBlocked, establishmentDateRange, page, size);
                    });
        } else if (queryByGroupIndexes) {
            return queryMembersRelationships(ownerIds, groupIndexes, page, size);
        } else {
            return queryRelationships(ownerIds, relatedUserIds, isBlocked, establishmentDateRange, page, size);
        }
    }

    public Flux<UserRelationship> queryMembersRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Integer page,
            @Nullable Integer size) {
        return queryMembersRelatedUserIds(ownerIds, groupIndexes, null)
                .collect(Collectors.toSet())
                .flatMapMany(relatedUserIds -> {
                    if (relatedUserIds.isEmpty()) {
                        return Flux.empty();
                    }
                    Query query = QueryBuilder
                            .newBuilder()
                            .addInIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                            .addInIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                            .paginateIfNotNull(page, size);
                    return mongoTemplate.find(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
                });
    }

    public Mono<Long> countRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked) {
        boolean queryByGroupIndexes = groupIndexes != null;
        boolean queryByRelationshipInfo = relatedUserIds != null || isBlocked != null;
        if (queryByGroupIndexes && queryByRelationshipInfo) {
            if (relatedUserIds != null && relatedUserIds.isEmpty()) {
                return Mono.just(0L);
            }
            return queryMembersRelatedUserIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toSet())
                    .flatMap(userIds -> {
                        if (relatedUserIds != null) {
                            userIds.retainAll(relatedUserIds);
                        }
                        return countRelationships(ownerIds, userIds, isBlocked);
                    });
        } else if (queryByGroupIndexes) {
            return countMembersRelationships(ownerIds, groupIndexes);
        } else {
            return countRelationships(ownerIds, relatedUserIds, isBlocked);
        }
    }

    private Mono<Long> countMembersRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .addInIfNotNull(ID_GROUP_INDEX, groupIndexes)
                .buildQuery();
        return mongoTemplate.count(query, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME);
    }

    public Mono<Long> countRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .addInIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                .addIsIfNotNull(UserRelationship.Fields.IS_BLOCKED, isBlocked)
                .buildQuery();
        return mongoTemplate.count(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
    }

    public Mono<Boolean> friendTwoUsers(
            @NotNull Long userOneId,
            @NotNull Long userTwoId,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(userOneId, "userOneId");
            AssertUtil.notNull(userTwoId, "userTwoId");
            AssertUtil.state(!userOneId.equals(userTwoId), "The ID of user one must not equal to the ID of user two");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        if (operations != null) {
            return upsertOneSidedRelationship(
                    userOneId, userTwoId, false,
                    DEFAULT_RELATIONSHIP_GROUP_INDEX, null, now, true, operations)
                    .then(upsertOneSidedRelationship(userTwoId, userOneId, false,
                            DEFAULT_RELATIONSHIP_GROUP_INDEX, null, now, true, operations))
                    .thenReturn(true);
        } else {
            return mongoTemplate.inTransaction()
                    .execute(newOperations -> friendTwoUsers(userOneId, userTwoId, newOperations))
                    .retryWhen(TRANSACTION_RETRY)
                    .singleOrEmpty();
        }
    }

    public Mono<Boolean> upsertOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable Boolean isBlocked,
            @Nullable Integer newGroupIndex,
            @Nullable Integer deleteGroupIndex,
            @Nullable @PastOrPresent Date establishmentDate,
            @NotNull Boolean upsert,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
            AssertUtil.pastOrPresent(establishmentDate, "establishmentDate");
            AssertUtil.notNull(upsert, "upsert");
            AssertUtil.state(!ownerId.equals(relatedUserId), "The owner ID must not equal to the related user ID");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        isBlocked = isBlocked != null && isBlocked;
        establishmentDate = establishmentDate != null ? establishmentDate : new Date();
        UserRelationship userRelationship = new UserRelationship(ownerId, relatedUserId, isBlocked, establishmentDate);
        List<Mono<Boolean>> monos = new LinkedList<>();
        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
        if (upsert) {
            monos.add(mongoOperations.save(userRelationship, UserRelationship.COLLECTION_NAME).thenReturn(true));
        } else {
            monos.add(mongoOperations.insert(userRelationship, UserRelationship.COLLECTION_NAME).thenReturn(true));
        }
        if (newGroupIndex != null && deleteGroupIndex != null && !newGroupIndex.equals(deleteGroupIndex)) {
            monos.add(moveToNewGroup(ownerId, relatedUserId, deleteGroupIndex, newGroupIndex).thenReturn(true));
        } else {
            if (newGroupIndex != null) {
                Mono<Boolean> add = userRelationshipGroupService.addRelatedUserToRelationshipGroups(
                        ownerId, Collections.singleton(newGroupIndex), relatedUserId, operations);
                monos.add(add);
            }
            if (deleteGroupIndex != null) {
                Mono<Boolean> delete = userRelationshipGroupService.removeRelatedUserFromRelationshipGroup
                        (ownerId, relatedUserId, deleteGroupIndex,
                                newGroupIndex != null ? newGroupIndex : DEFAULT_RELATIONSHIP_GROUP_INDEX);
                monos.add(delete);
            }
        }
        return ReactorUtil.areAllTrue(monos)
                .onErrorMap(DuplicateKeyException.class, e -> TurmsBusinessException.get(TurmsStatusCode.RELATIONSHIP_HAS_ESTABLISHED));
    }

    public Mono<UserRelationshipGroupMember> moveToNewGroup(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @NotNull Integer deleteGroupIndex,
            @NotNull Integer newGroupIndex) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
            AssertUtil.notNull(deleteGroupIndex, "deleteGroupIndex");
            AssertUtil.notNull(newGroupIndex, "newGroupIndex");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(relatedUserId))
                .addCriteria(Criteria.where(ID_GROUP_INDEX).is(deleteGroupIndex));
        Update update = new Update().set(ID_GROUP_INDEX, newGroupIndex);
        return mongoTemplate.findAndModify(query, update, UserRelationshipGroupMember.class, UserRelationshipGroupMember.COLLECTION_NAME);
    }

    public Mono<Boolean> isBlocked(@NotNull Long ownerId, @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(relatedUserId))
                .addCriteria(Criteria.where(UserRelationship.Fields.IS_BLOCKED).is(true));
        return mongoTemplate.exists(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
    }

    public Mono<Boolean> isNotBlocked(@NotNull Long ownerId, @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(relatedUserId))
                .addCriteria(Criteria.where(UserRelationship.Fields.IS_BLOCKED).is(true));
        return mongoTemplate.exists(query, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                .map(isBlocked -> !isBlocked);
    }

    public Mono<Boolean> isRelatedAndAllowed(@NotNull Long ownerId, @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(relatedUserId))
                .addCriteria(Criteria.where(UserRelationship.Fields.IS_BLOCKED).is(false));
        return mongoTemplate.exists(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
    }

    public Mono<Boolean> updateUserOneSidedRelationships(
            @NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys,
            @Nullable Boolean isBlocked,
            @Nullable @PastOrPresent Date establishmentDate) {
        try {
            AssertUtil.notEmpty(keys, "ownerId");
            for (UserRelationship.Key key : keys) {
                DomainConstraintUtil.validRelationshipKey(key);
            }
            AssertUtil.pastOrPresent(establishmentDate, "establishmentDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return MapUtil.fluxMerge(map -> {
            for (UserRelationship.Key key : keys) {
                map.put(key.getOwnerId(), key.getRelatedUserId());
            }
        }, (monos, key, values) -> monos.add(updateUserOneSidedRelationships(key, values, isBlocked, establishmentDate)));
    }

    public Mono<Boolean> updateUserOneSidedRelationships(
            @NotNull Long ownerId,
            @NotEmpty Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked,
            @Nullable @PastOrPresent Date establishmentDate) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notEmpty(relatedUserIds, "relatedUserIds");
            AssertUtil.pastOrPresent(establishmentDate, "establishmentDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(isBlocked, establishmentDate)) {
            return Mono.just(true);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).in(relatedUserIds));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(UserRelationship.Fields.IS_BLOCKED, isBlocked)
                .setIfNotNull(UserRelationship.Fields.ESTABLISHMENT_DATE, establishmentDate)
                .build();
        return mongoTemplate.updateMulti(query, update, UserRelationship.class, UserRelationship.COLLECTION_NAME)
                .zipWith(userVersionService.updateRelationshipsVersion(ownerId, null))
                .map(result -> result.getT1().wasAcknowledged());
    }

    /**
     * For user one, check if user two is a stranger
     */
    public Mono<Boolean> isStranger(
            @NotNull Long userOneId,
            @NotNull Long userTwoId) {
        try {
            AssertUtil.notNull(userOneId, "userOneId");
            AssertUtil.notNull(userTwoId, "userTwoId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(userTwoId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(userOneId))
                .addCriteria(Criteria.where(UserRelationship.Fields.IS_BLOCKED).is(null));
        return mongoTemplate.exists(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
    }

    public Mono<Boolean> hasOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
            AssertUtil.state(!ownerId.equals(relatedUserId), "The owner ID must not equal to the related user ID");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_OWNER_ID).is(ownerId))
                .addCriteria(Criteria.where(UserRelationship.Fields.ID_RELATED_USER_ID).is(relatedUserId));
        return mongoTemplate.exists(query, UserRelationship.class, UserRelationship.COLLECTION_NAME);
    }

}