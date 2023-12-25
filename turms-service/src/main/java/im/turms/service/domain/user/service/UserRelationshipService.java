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

package im.turms.service.domain.user.service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.model.common.LongsWithVersion;
import im.turms.server.common.access.client.dto.model.user.UserRelationshipsWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.user.po.UserRelationship;
import im.turms.service.domain.user.po.UserVersion;
import im.turms.service.domain.user.repository.UserRelationshipRepository;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidUserRelationshipKey;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_RELATIONSHIP_GROUP_INDEX;
import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserRelationshipService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRelationshipService.class);

    private final UserRelationshipRepository userRelationshipRepository;

    private final UserVersionService userVersionService;
    private final UserRelationshipGroupService userRelationshipGroupService;

    private final Cache<Pair<Long, Long>, Boolean> ownerIdAndRelatedUserIdToIsBlockedCache;
    private final Cache<Pair<Long, Long>, Boolean> ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache;

    public UserRelationshipService(
            UserRelationshipRepository userRelationshipRepository,
            UserVersionService userVersionService,
            UserRelationshipGroupService userRelationshipGroupService) {
        this.userRelationshipRepository = userRelationshipRepository;
        this.userVersionService = userVersionService;
        this.userRelationshipGroupService = userRelationshipGroupService;

        // TODO: make configurable
        ownerIdAndRelatedUserIdToIsBlockedCache = Caffeine.newBuilder()
                .maximumSize(8096)
                .expireAfterWrite(DurationConst.ONE_MINUTE)
                .build();
        ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache = Caffeine.newBuilder()
                .maximumSize(8096)
                .expireAfterWrite(DurationConst.ONE_MINUTE)
                .build();
    }

    public Mono<DeleteResult> deleteAllRelationships(
            @NotEmpty Set<Long> userIds,
            @Nullable ClientSession session,
            boolean updateRelationshipsVersion) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (updateRelationshipsVersion) {
            if (session == null) {
                return userRelationshipRepository
                        .inTransaction(newSession -> userRelationshipRepository
                                .deleteAllRelationships(userIds, newSession)
                                .flatMap(result -> userVersionService
                                        .updateRelationshipsVersion(userIds, newSession)
                                        .onErrorResume(t -> {
                                            LOGGER.error(
                                                    "Caught an error while updating the relationships version of the users {} after deleting all relationships",
                                                    userIds,
                                                    t);
                                            return Mono.empty();
                                        })
                                        .thenReturn(result)))
                        .retryWhen(TRANSACTION_RETRY);
            }
            return userRelationshipRepository.deleteAllRelationships(userIds, session)
                    .flatMap(result -> userVersionService
                            .updateRelationshipsVersion(userIds, session)
                            .onErrorResume(t -> {
                                LOGGER.error(
                                        "Caught an error while updating the relationships version of the users {} after deleting all relationships",
                                        userIds,
                                        t);
                                return Mono.empty();
                            })
                            .thenReturn(result));
        }
        return userRelationshipRepository.deleteAllRelationships(userIds, session);
    }

    public Mono<DeleteResult> deleteOneSidedRelationships(
            @NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys) {
        try {
            Validator.notEmpty(keys, "keys");
            for (UserRelationship.Key key : keys) {
                DataValidator.validRelationshipKey(key);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Set<Long> ownerIds = CollectionUtil.newSetWithExpectedSize(keys.size());
        for (UserRelationship.Key key : keys) {
            ownerIds.add(key.getOwnerId());
        }
        return userRelationshipRepository
                .inTransaction(session -> userRelationshipRepository.deleteByIds(keys, session)
                        .flatMap(result -> userRelationshipGroupService
                                .deleteRelatedUsersFromAllRelationshipGroups(keys, session, true)
                                .then(userVersionService.updateRelationshipsVersion(ownerIds, null)
                                        .onErrorResume(t -> {
                                            LOGGER.error(
                                                    "Caught an error while updating relationships version of the group owners {} after deleting their relationships",
                                                    ownerIds,
                                                    t);
                                            return Mono.empty();
                                        }))
                                .doOnSuccess(unused -> invalidateRelationshipCache(keys))
                                .thenReturn(result)))
                .retryWhen(TRANSACTION_RETRY);
    }

    public Mono<Void> deleteOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(relatedUserId, "relatedUserId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (session == null) {
            return userRelationshipRepository.inTransaction(
                    newSession -> deleteOneSidedRelationship(ownerId, relatedUserId, newSession))
                    .retryWhen(TRANSACTION_RETRY);
        }
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        return userRelationshipRepository.deleteById(key)
                .then(userRelationshipGroupService.deleteRelatedUserFromAllRelationshipGroups(
                        ownerId,
                        relatedUserId,
                        session,
                        false))
                .then(userVersionService
                        .updateSpecificVersion(ownerId,
                                session,
                                UserVersion.Fields.RELATIONSHIP_GROUP_MEMBERS,
                                UserVersion.Fields.RELATIONSHIPS)
                        .onErrorResume(t -> {
                            LOGGER.error(
                                    "Caught an error while updating the relationships version and relationships group members version of the owner ({}) after deleting a relationship",
                                    ownerId,
                                    t);
                            return Mono.empty();
                        }))
                .doOnSuccess(unused -> invalidateRelationshipCache(ownerId, relatedUserId))
                .then();
    }

    public Mono<Void> deleteTwoSidedRelationships(
            @NotNull Long userOneId,
            @NotNull Long userTwoId) {
        try {
            Validator.notNull(userOneId, "userOneId");
            Validator.notNull(userTwoId, "userTwoId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userRelationshipRepository
                .inTransaction(session -> deleteOneSidedRelationship(userOneId, userTwoId, session)
                        .then(deleteOneSidedRelationship(userTwoId, userOneId, session))
                        .then())
                .retryWhen(TRANSACTION_RETRY);
    }

    public Mono<LongsWithVersion> queryRelatedUserIdsWithVersion(
            @NotNull Long ownerId,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked,
            @Nullable Date lastUpdatedDate) {
        return userVersionService.queryRelationshipsLastUpdatedDate(ownerId)
                .flatMap(date -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, date)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
                    return queryRelatedUserIds(Set.of(ownerId), groupIndexes, isBlocked)
                            .collect(Collectors.toCollection(recyclableSet::getValue))
                            .map(relatedUserIds -> {
                                if (relatedUserIds.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                if (isBlocked != null) {
                                    Pair<Long, Long> cacheKey;
                                    for (Long relatedUserId : relatedUserIds) {
                                        cacheKey = Pair.of(ownerId, relatedUserId);
                                        ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache
                                                .put(cacheKey, !isBlocked);
                                        ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey,
                                                isBlocked);
                                    }
                                }
                                return ClientMessagePool.getLongsWithVersionBuilder()
                                        .setLastUpdatedDate(date.getTime())
                                        .addAllLongs(relatedUserIds)
                                        .build();
                            })
                            .doFinally(signalType -> recyclableSet.recycle());
                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<UserRelationshipsWithVersion> queryRelationshipsWithVersion(
            @NotNull Long ownerId,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked,
            @Nullable Date lastUpdatedDate) {
        return userVersionService.queryRelationshipsLastUpdatedDate(ownerId)
                .flatMap(date -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, date)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    Recyclable<Set<UserRelationship>> recyclableSet = SetRecycler.obtain();
                    return queryRelationships(Set
                            .of(ownerId), relatedUserIds, groupIndexes, isBlocked, null, null, null)
                            .collect(Collectors.toCollection(recyclableSet::getValue))
                            .map(relationships -> {
                                if (relationships.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                UserRelationshipsWithVersion.Builder builder =
                                        ClientMessagePool.getUserRelationshipsWithVersionBuilder()
                                                .setLastUpdatedDate(date.getTime());
                                UserRelationship.Key relationshipKey;
                                Pair<Long, Long> cacheKey;
                                boolean blocked;
                                for (UserRelationship relationship : relationships) {
                                    relationshipKey = relationship.getKey();
                                    cacheKey = Pair.of(ownerId, relationshipKey.getRelatedUserId());
                                    blocked = relationship.getBlockDate() != null;
                                    ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache
                                            .put(cacheKey, !blocked);
                                    ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey, blocked);
                                    builder.addUserRelationships(
                                            ProtoModelConvertor.relationship2proto(relationship));
                                }
                                return builder.build();
                            })
                            .doFinally(signalType -> recyclableSet.recycle());
                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Flux<Long> queryRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Boolean isBlocked) {
        return userRelationshipRepository.findRelatedUserIds(ownerIds, isBlocked);
    }

    public Flux<Long> queryRelatedUserIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Boolean isBlocked) {
        if (groupIndexes != null && isBlocked != null) {
            Recyclable<List<Long>> recyclableList1 = ListRecycler.obtain();
            Recyclable<List<Long>> recyclableList2 = ListRecycler.obtain();
            Mono<List<Long>> queryRelationshipGroupMemberIds = userRelationshipGroupService
                    .queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toCollection(recyclableList1::getValue));
            Mono<List<Long>> queryRelatedUserIds = queryRelatedUserIds(ownerIds, isBlocked)
                    .collect(Collectors.toCollection(recyclableList2::getValue));
            return Mono.zip(queryRelationshipGroupMemberIds, queryRelatedUserIds)
                    .flatMapMany(tuple -> Flux
                            .fromIterable(CollectionUtil.newSet(tuple.getT1(), tuple.getT2())))
                    .doFinally(signalType -> {
                        recyclableList1.recycle();
                        recyclableList2.recycle();
                    });
        }
        if (groupIndexes == null) {
            return queryRelatedUserIds(ownerIds, isBlocked);
        }
        return userRelationshipGroupService
                .queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null);
    }

    private Flux<UserRelationship> queryRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked,
            @Nullable DateRange establishmentDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        return userRelationshipRepository.findRelationships(ownerIds,
                relatedUserIds,
                isBlocked,
                establishmentDateRange,
                page,
                size);
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
        boolean queryByRelationshipInfo =
                relatedUserIds != null || isBlocked != null || establishmentDateRange != null;
        if (queryByGroupIndexes && queryByRelationshipInfo) {
            if (relatedUserIds != null && relatedUserIds.isEmpty()) {
                return Flux.empty();
            }
            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
            return userRelationshipGroupService
                    .queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toCollection(recyclableSet::getValue))
                    .flatMapMany(userIds -> {
                        if (relatedUserIds != null) {
                            userIds.retainAll(relatedUserIds);
                        }
                        return queryRelationships(ownerIds,
                                userIds,
                                isBlocked,
                                establishmentDateRange,
                                page,
                                size);
                    })
                    .doFinally(signalType -> recyclableSet.recycle());
        } else if (queryByGroupIndexes) {
            return queryMembersRelationships(ownerIds, groupIndexes, page, size);
        }
        return queryRelationships(ownerIds,
                relatedUserIds,
                isBlocked,
                establishmentDateRange,
                page,
                size);
    }

    public Flux<UserRelationship> queryMembersRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Integer page,
            @Nullable Integer size) {
        Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
        return queryRelatedUserIds(ownerIds, groupIndexes, null)
                .collect(Collectors.toCollection(recyclableSet::getValue))
                .flatMapMany(relatedUserIds -> {
                    if (relatedUserIds.isEmpty()) {
                        return Flux.empty();
                    }
                    return userRelationshipRepository
                            .findRelationships(ownerIds, relatedUserIds, page, size);
                })
                .doFinally(signalType -> recyclableSet.recycle());
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
            Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
            return userRelationshipGroupService
                    .queryRelationshipGroupMemberIds(ownerIds, groupIndexes, null, null)
                    .collect(Collectors.toCollection(recyclableSet::getValue))
                    .flatMap(userIds -> {
                        if (relatedUserIds != null) {
                            userIds.retainAll(relatedUserIds);
                        }
                        return countRelationships(ownerIds, userIds, isBlocked);
                    })
                    .doFinally(signalType -> recyclableSet.recycle());
        } else if (queryByGroupIndexes) {
            return userRelationshipGroupService.countRelationshipGroupMembers(ownerIds,
                    groupIndexes);
        }
        return countRelationships(ownerIds, relatedUserIds, isBlocked);
    }

    public Mono<Long> countRelationships(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Long> relatedUserIds,
            @Nullable Boolean isBlocked) {
        return userRelationshipRepository.countRelationships(ownerIds, relatedUserIds, isBlocked);
    }

    public Mono<Boolean> friendTwoUsers(
            @NotNull Long userOneId,
            @NotNull Long userTwoId,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(userOneId, "userOneId");
            Validator.notNull(userTwoId, "userTwoId");
            Validator.notEquals(userOneId,
                    userTwoId,
                    "The ID of user one must not be equal to the ID of user two");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (session == null) {
            return userRelationshipRepository
                    .inTransaction(newSession -> friendTwoUsers(userOneId, userTwoId, newSession))
                    .retryWhen(TRANSACTION_RETRY);
        }
        Date now = new Date();
        return upsertOneSidedRelationship(userOneId,
                userTwoId,
                null,
                DEFAULT_RELATIONSHIP_GROUP_INDEX,
                null,
                now,
                true,
                session)
                .then(upsertOneSidedRelationship(userTwoId,
                        userOneId,
                        null,
                        DEFAULT_RELATIONSHIP_GROUP_INDEX,
                        null,
                        now,
                        true,
                        session))
                .doOnSuccess(unused -> {
                    Pair<Long, Long> cacheKey = Pair.of(userOneId, userTwoId);
                    ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.put(cacheKey, true);
                    ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey, false);
                    cacheKey = Pair.of(userTwoId, userOneId);
                    ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.put(cacheKey, true);
                    ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey, false);
                })
                .then(PublisherPool.TRUE);
    }

    public Mono<Void> upsertOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Integer newGroupIndex,
            @Nullable Integer deleteGroupIndex,
            @Nullable @PastOrPresent Date establishmentDate,
            boolean upsert,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(relatedUserId, "relatedUserId");
            Validator.pastOrPresent(blockDate, "blockDate");
            Validator.pastOrPresent(establishmentDate, "establishmentDate");
            Validator.notNull(upsert, "upsert");
            Validator.notEquals(ownerId,
                    relatedUserId,
                    "The owner ID must not be equal to the related user ID");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        // Notes:
        // 1. It is unnecessary to check whether the requester is in the blocklist of the related
        // user
        // because only a one-sided relationship will be created here
        // 2. Upsert relationship first to ensure that the relationship exists before
        // "upsertRelationshipGroupMember"
        return upsertOneSidedRelationship(ownerId,
                relatedUserId,
                blockDate,
                establishmentDate,
                upsert,
                session)
                .then(userRelationshipGroupService.upsertRelationshipGroupMember(ownerId,
                        relatedUserId,
                        newGroupIndex,
                        deleteGroupIndex,
                        session));
    }

    private Mono<Void> upsertOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Date establishmentDate,
            boolean upsert,
            @Nullable ClientSession session) {
        if (establishmentDate == null) {
            establishmentDate = new Date();
        }
        UserRelationship userRelationship =
                new UserRelationship(ownerId, relatedUserId, blockDate, establishmentDate);
        return (upsert
                ? userRelationshipRepository.upsert(userRelationship, session)
                : userRelationshipRepository.insert(userRelationship, session)
                        .onErrorMap(DuplicateKeyException.class,
                                e -> ResponseException
                                        .get(ResponseStatusCode.CREATE_EXISTING_RELATIONSHIP)))
                .doOnSuccess(unused -> {
                    Pair<Long, Long> cacheKey = Pair.of(ownerId, relatedUserId);
                    boolean isBlocked = blockDate != null;
                    ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.put(cacheKey,
                            !isBlocked);
                    ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey, isBlocked);
                });
    }

    public Mono<Boolean> isBlocked(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            boolean preferCache) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(relatedUserId, "relatedUserId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Pair<Long, Long> cacheKey = Pair.of(ownerId, relatedUserId);
        if (preferCache) {
            Boolean isBlocked = ownerIdAndRelatedUserIdToIsBlockedCache.getIfPresent(cacheKey);
            if (isBlocked != null) {
                return Mono.just(isBlocked);
            }
        }
        return userRelationshipRepository.isBlocked(ownerId, relatedUserId)
                .doOnNext(isBlocked -> {
                    if (isBlocked) {
                        ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.put(cacheKey,
                                false);
                    }
                    ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey, isBlocked);
                });
    }

    public Mono<Boolean> hasNoRelationshipOrNotBlocked(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            boolean preferCache) {
        return isBlocked(ownerId, relatedUserId, preferCache).map(isBlocked -> !isBlocked);
    }

    /**
     * The method exists for backward compatibility
     */
    public Mono<Boolean> hasRelationshipAndNotBlocked(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId) {
        return hasRelationshipAndNotBlocked(ownerId, relatedUserId, false);
    }

    public Mono<Boolean> hasRelationshipAndNotBlocked(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            boolean preferCache) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(relatedUserId, "relatedUserId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Pair<Long, Long> cacheKey = Pair.of(ownerId, relatedUserId);
        if (preferCache) {
            Boolean hasRelationshipAndNotBlocked =
                    ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache
                            .getIfPresent(cacheKey);
            if (hasRelationshipAndNotBlocked != null) {
                return Mono.just(hasRelationshipAndNotBlocked);
            }
        }
        return userRelationshipRepository.hasRelationshipAndNotBlocked(ownerId, relatedUserId)
                .doOnNext(hasRelationshipAndNotBlocked -> {
                    ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.put(cacheKey,
                            hasRelationshipAndNotBlocked);
                    if (hasRelationshipAndNotBlocked) {
                        ownerIdAndRelatedUserIdToIsBlockedCache.put(cacheKey, false);
                    }
                });
    }

    public Mono<UpdateResult> updateUserOneSidedRelationships(
            @NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable @PastOrPresent Date establishmentDate) {
        Set<Long> ownerIds;
        try {
            Validator.notEmpty(keys, "keys");
            ownerIds = CollectionUtil.newSetWithExpectedSize(keys.size());
            for (UserRelationship.Key key : keys) {
                DataValidator.validRelationshipKey(key);
                ownerIds.add(key.getOwnerId());
            }
            Validator.pastOrPresent(blockDate, "blockDate");
            Validator.pastOrPresent(establishmentDate, "establishmentDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(blockDate, establishmentDate)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return userRelationshipRepository
                .updateUserOneSidedRelationships(keys, blockDate, establishmentDate)
                .flatMap(result -> {
                    if (result.getModifiedCount() > 0) {
                        return userVersionService.updateRelationshipsVersion(ownerIds, null)
                                .onErrorResume(t -> {
                                    LOGGER.error(
                                            "Caught an error while updating the relationships version of the owners {} after updating their relationships",
                                            ownerIds,
                                            t);
                                    return Mono.empty();
                                })
                                .thenReturn(result);
                    }
                    return Mono.just(result);
                });
    }

    public Mono<Boolean> hasOneSidedRelationship(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(relatedUserId, "relatedUserId");
            Validator.notEquals(ownerId,
                    relatedUserId,
                    "The owner ID must not equal to the related user ID");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        UserRelationship.Key key = new UserRelationship.Key(ownerId, relatedUserId);
        return userRelationshipRepository.existsById(key);
    }

    // Cache

    private void invalidateRelationshipCache(Collection<UserRelationship.Key> keys) {
        for (UserRelationship.Key key : keys) {
            Pair<Long, Long> pair = Pair.of(key.getOwnerId(), key.getRelatedUserId());
            ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.invalidate(pair);
            ownerIdAndRelatedUserIdToIsBlockedCache.invalidate(pair);
        }
    }

    private void invalidateRelationshipCache(Long ownerId, Long relatedUserId) {
        Pair<Long, Long> pair = Pair.of(ownerId, relatedUserId);
        ownerIdAndRelatedUserIdToHasRelationshipAndNotBlockedCache.invalidate(pair);
        ownerIdAndRelatedUserIdToIsBlockedCache.invalidate(pair);
    }

}