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

package im.turms.service.workflow.service.impl.user.relationship;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.user.UserRelationshipsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.exception.DuplicateKeyException;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectionUtil;
import im.turms.server.common.util.DateUtil;
import im.turms.service.constant.DaoConstant;
import im.turms.service.constant.OperationResultConstant;
import im.turms.service.constraint.ValidUserRelationshipKey;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.dao.domain.user.UserRelationship;
import im.turms.service.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.service.workflow.dao.domain.user.UserVersion;
import im.turms.service.workflow.service.impl.user.UserVersionService;
import im.turms.service.workflow.service.util.DomainConstraintUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
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
import java.util.stream.Collectors;

import static im.turms.service.constant.DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX;
import static im.turms.service.constant.DaoConstant.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserRelationshipService {

    private final UserVersionService userVersionService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final TurmsMongoClient mongoClient;

    public UserRelationshipService(
            UserVersionService userVersionService,
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient,
            UserRelationshipGroupService userRelationshipGroupService) {
        this.userVersionService = userVersionService;
        this.mongoClient = mongoClient;
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    public Mono<DeleteResult> deleteAllRelationships(
            @NotEmpty Set<Long> userIds,
            @Nullable ClientSession session,
            boolean updateRelationshipsVersion) {
        try {
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .or(Filter.newBuilder(1).in(UserRelationship.Fields.ID_OWNER_ID, userIds),
                        Filter.newBuilder(1).in(UserRelationship.Fields.ID_RELATED_USER_ID, userIds));
        if (updateRelationshipsVersion) {
            if (session == null) {
                return mongoClient
                        .inTransaction(newSession -> mongoClient.deleteMany(newSession, UserRelationship.class, filter)
                                .flatMap(result -> userVersionService.updateRelationshipsVersion(userIds, newSession)
                                        .onErrorResume(t -> Mono.empty())
                                        .thenReturn(result)))
                        .retryWhen(TRANSACTION_RETRY);
            }
            return mongoClient.deleteMany(session, UserRelationship.class, filter)
                    .flatMap(result -> userVersionService.updateRelationshipsVersion(userIds, session)
                            .onErrorResume(t -> Mono.empty())
                            .thenReturn(result));
        }
        return mongoClient.deleteMany(session, UserRelationship.class, filter);
    }

    public Mono<DeleteResult> deleteOneSidedRelationships(@NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationship.Key key : keys) {
                DomainConstraintUtil.validRelationshipKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Set<Long> ownerIds = CollectionUtil.newSetWithExpectedSize(keys.size());
        for (UserRelationship.Key key : keys) {
            ownerIds.add(key.getOwnerId());
        }
        return mongoClient
                .inTransaction(session -> {
                    Filter filter = Filter.newBuilder(1)
                            .in(DaoConstant.ID_FIELD_NAME, keys);
                    return mongoClient.deleteMany(session, UserRelationship.class, filter)
                            .flatMap(result -> userRelationshipGroupService.deleteRelatedUsersFromAllRelationshipGroups(keys, session, true)
                                    .then(userVersionService.updateRelationshipsVersion(ownerIds, null).onErrorResume(t -> Mono.empty()))
                                    .thenReturn(result));
                })
                .retryWhen(TRANSACTION_RETRY);
    }

    public Mono<Void> deleteOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (session == null) {
            return mongoClient
                    .inTransaction(newSession -> deleteOneSidedRelationship(ownerId, relatedUserId, newSession))
                    .retryWhen(TRANSACTION_RETRY);
        }
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, key);
        return mongoClient.deleteMany(UserRelationship.class, filter)
                .then(userRelationshipGroupService.deleteRelatedUserFromAllRelationshipGroups(
                        ownerId, relatedUserId, session, false))
                .then(userVersionService.updateSpecificVersion(
                                ownerId,
                                session,
                                UserVersion.Fields.RELATIONSHIP_GROUPS_MEMBERS,
                                UserVersion.Fields.RELATIONSHIPS)
                        .onErrorResume(t -> Mono.empty()))
                .then();
    }

    public Mono<Void> deleteTwoSidedRelationships(
            @NotNull Long userOneId,
            @NotNull Long userTwoId) {
        try {
            AssertUtil.notNull(userOneId, "userOneId");
            AssertUtil.notNull(userTwoId, "userTwoId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return mongoClient.inTransaction(session -> deleteOneSidedRelationship(userOneId, userTwoId, session)
                        .then(deleteOneSidedRelationship(userTwoId, userOneId, session))
                        .then())
                .retryWhen(TRANSACTION_RETRY);
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
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, date)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryRelatedUserIds(Set.of(ownerId), Set.of(groupIndex), isBlocked)
                            .collect(Collectors.toSet())
                            .map(ids -> {
                                if (ids.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                return Int64ValuesWithVersion.newBuilder()
                                        .setLastUpdatedDate(date.getTime())
                                        .addAllValues(ids)
                                        .build();
                            });
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
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, date)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryRelationships(
                            Set.of(ownerId),
                            relatedUserIds,
                            groupIndex == null ? null : Set.of(groupIndex),
                            isBlocked,
                            null,
                            null,
                            null)
                            .collect(Collectors.toSet())
                            .map(relationships -> {
                                if (relationships.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                UserRelationshipsWithVersion.Builder builder = UserRelationshipsWithVersion.newBuilder()
                                        .setLastUpdatedDate(date.getTime());
                                for (UserRelationship relationship : relationships) {
                                    builder.addUserRelationships(ProtoModelUtil.relationship2proto(relationship));
                                }
                                return builder.build();
                            });
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Flux<Long> queryRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isBlocked) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .neNullIfNotNull(UserRelationship.Fields.BLOCK_DATE, isBlocked);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserRelationship.Fields.ID_RELATED_USER_ID);
        return mongoClient.findMany(UserRelationship.class, filter, options)
                .map(userRelationship -> userRelationship.getKey().getRelatedUserId());
    }

    public Flux<Long> queryRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked) {
        if (groupIndexes != null && isBlocked != null) {
            Mono<Set<Long>> queryRelationshipGroupMemberIds = userRelationshipGroupService
                    .queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toSet());
            Mono<Set<Long>> queryRelatedUserIds = queryRelatedUserIds(ownerIds, isBlocked)
                    .collect(Collectors.toSet());
            return Mono
                    .zip(queryRelationshipGroupMemberIds, queryRelatedUserIds)
                    .flatMapIterable(tuple -> {
                        tuple.getT1().retainAll(tuple.getT2());
                        return tuple.getT1();
                    });
        }
        if (groupIndexes == null) {
            return queryRelatedUserIds(ownerIds, isBlocked);
        }
        return userRelationshipGroupService.queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null);
    }

    private Flux<UserRelationship> queryRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked,
            @Nullable DateRange establishmentDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                .addBetweenIfNotNull(UserRelationship.Fields.ESTABLISHMENT_DATE, establishmentDateRange)
                .neNullIfNotNull(UserRelationship.Fields.BLOCK_DATE, isBlocked);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(UserRelationship.class, filter, options);
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
            return userRelationshipGroupService.queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toSet())
                    .flatMapMany(userIds -> {
                        if (relatedUserIds != null) {
                            userIds.retainAll(relatedUserIds);
                        }
                        return queryRelationships(ownerIds, userIds, isBlocked, establishmentDateRange, page, size);
                    });
        } else if (queryByGroupIndexes) {
            return queryMembersRelationships(ownerIds, groupIndexes, page, size);
        }
        return queryRelationships(ownerIds, relatedUserIds, isBlocked, establishmentDateRange, page, size);
    }

    public Flux<UserRelationship> queryMembersRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Integer page,
            @Nullable Integer size) {
        return queryRelatedUserIds(ownerIds, groupIndexes, null)
                .collect(Collectors.toSet())
                .flatMapMany(relatedUserIds -> {
                    if (relatedUserIds.isEmpty()) {
                        return Flux.empty();
                    }
                    Filter filter = Filter.newBuilder(2)
                            .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                            .inIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds);
                    QueryOptions options = QueryOptions.newBuilder(2)
                            .paginateIfNotNull(page, size);
                    return mongoClient.findMany(UserRelationship.class, filter, options);
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
            return userRelationshipGroupService.queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toSet())
                    .flatMap(userIds -> {
                        if (relatedUserIds != null) {
                            userIds.retainAll(relatedUserIds);
                        }
                        return countRelationships(ownerIds, userIds, isBlocked);
                    });
        } else if (queryByGroupIndexes) {
            return countMembersRelationships(ownerIds, groupIndexes);
        }
        return countRelationships(ownerIds, relatedUserIds, isBlocked);
    }

    private Mono<Long> countMembersRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes) {
        Filter filter = Filter
                .newBuilder(2)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndexes);
        return mongoClient.count(UserRelationshipGroupMember.class, filter);
    }

    public Mono<Long> countRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked) {
        Filter filter = Filter
                .newBuilder(3)
                .inIfNotNull(UserRelationship.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationship.Fields.ID_RELATED_USER_ID, relatedUserIds)
                .neNullIfNotNull(UserRelationship.Fields.BLOCK_DATE, isBlocked);
        return mongoClient.count(UserRelationship.class, filter);
    }

    public Mono<Boolean> friendTwoUsers(
            @NotNull Long userOneId,
            @NotNull Long userTwoId,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(userOneId, "userOneId");
            AssertUtil.notNull(userTwoId, "userTwoId");
            AssertUtil.state(!userOneId.equals(userTwoId), "The ID of user one must not equal to the ID of user two");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Date now = new Date();
        if (session == null) {
            return mongoClient.inTransaction(newSession -> friendTwoUsers(userOneId, userTwoId, newSession))
                    .retryWhen(TRANSACTION_RETRY);
        }
        return upsertOneSidedRelationship(
                userOneId, userTwoId, null,
                DEFAULT_RELATIONSHIP_GROUP_INDEX, null, now, true, session)
                .then(upsertOneSidedRelationship(userTwoId, userOneId, null,
                        DEFAULT_RELATIONSHIP_GROUP_INDEX, null, now, true, session))
                .thenReturn(true);
    }

    // TODO: break down
    public Mono<Void> upsertOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Integer newGroupIndex,
            @Nullable Integer deleteGroupIndex,
            @Nullable @PastOrPresent Date establishmentDate,
            @NotNull Boolean upsert,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
            AssertUtil.pastOrPresent(blockDate, "blockDate");
            AssertUtil.pastOrPresent(establishmentDate, "establishmentDate");
            AssertUtil.notNull(upsert, "upsert");
            AssertUtil.state(!ownerId.equals(relatedUserId), "The owner ID must not equal to the related user ID");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        establishmentDate = establishmentDate == null ? new Date() : establishmentDate;
        UserRelationship userRelationship = new UserRelationship(ownerId, relatedUserId, blockDate, establishmentDate);
        List<Mono<?>> monos = new ArrayList<>(3);
        if (upsert) {
            monos.add(mongoClient.upsert(session, userRelationship));
        } else {
            monos.add(mongoClient.insert(session, userRelationship)
                    .onErrorMap(DuplicateKeyException.class,
                            e -> TurmsBusinessException.get(TurmsStatusCode.CREATE_EXISTING_RELATIONSHIP)));
        }
        if (newGroupIndex != null && deleteGroupIndex != null && !newGroupIndex.equals(deleteGroupIndex)) {
            monos.add(userRelationshipGroupService.moveRelatedUserToNewGroup(ownerId, relatedUserId, deleteGroupIndex, newGroupIndex));
        } else {
            if (newGroupIndex != null) {
                Mono<UserRelationshipGroupMember> add = userRelationshipGroupService.addRelatedUserToRelationshipGroups(
                        ownerId, newGroupIndex, relatedUserId, session);
                monos.add(add);
            }
            if (deleteGroupIndex != null) {
                Integer targetGroupIndex = newGroupIndex == null ? DEFAULT_RELATIONSHIP_GROUP_INDEX : newGroupIndex;
                Mono<Void> delete = userRelationshipGroupService
                        .moveRelatedUserToNewGroup(ownerId, relatedUserId, deleteGroupIndex, targetGroupIndex);
                monos.add(delete);
            }
        }
        return Flux.concat(monos).then();
    }

    public Mono<Boolean> isBlocked(@NotNull Long ownerId, @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        Filter filter = Filter.newBuilder(2)
                .eq(DaoConstant.ID_FIELD_NAME, key)
                .ne(UserRelationship.Fields.BLOCK_DATE, null);
        return mongoClient.exists(UserRelationship.class, filter);
    }

    public Mono<Boolean> hasNoRelationshipOrNotBlocked(@NotNull Long ownerId, @NotNull Long relatedUserId) {
        return isBlocked(ownerId, relatedUserId)
                .map(isBlocked -> !isBlocked);
    }

    public Mono<Boolean> hasRelationshipAndNotBlocked(@NotNull Long ownerId, @NotNull Long relatedUserId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        Filter filter = Filter.newBuilder(2)
                .eq(DaoConstant.ID_FIELD_NAME, key)
                .eq(UserRelationship.Fields.BLOCK_DATE, null);
        return mongoClient.exists(UserRelationship.class, filter);
    }

    public Mono<UpdateResult> updateUserOneSidedRelationships(
            @NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable @PastOrPresent Date establishmentDate) {
        Set<Long> ownerIds;
        try {
            AssertUtil.notEmpty(keys, "keys");
            ownerIds = CollectionUtil.newSetWithExpectedSize(keys.size());
            for (UserRelationship.Key key : keys) {
                DomainConstraintUtil.validRelationshipKey(key);
                ownerIds.add(key.getOwnerId());
            }
            AssertUtil.pastOrPresent(blockDate, "blockDate");
            AssertUtil.pastOrPresent(establishmentDate, "establishmentDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(blockDate, establishmentDate)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, keys);
        Update update = Update.newBuilder(2)
                .setIfNotNull(UserRelationship.Fields.ESTABLISHMENT_DATE, establishmentDate)
                .setOrUnsetDate(UserRelationship.Fields.BLOCK_DATE, blockDate);
        Set<Long> finalOwnerIds = ownerIds;
        return mongoClient.updateMany(UserRelationship.class, filter, update)
                .flatMap(result -> {
                    if (result.getModifiedCount() > 0) {
                        return userVersionService.updateRelationshipsVersion(finalOwnerIds, null).onErrorResume(t -> Mono.empty())
                                .thenReturn(result);
                    }
                    return Mono.just(result);
                });
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
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, key);
        return mongoClient.exists(UserRelationship.class, filter);
    }

}