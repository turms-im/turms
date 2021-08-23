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

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.common.model.bo.user.UserRelationshipGroupsWithVersion;
import im.turms.common.util.RandomUtil;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.cluster.node.Node;
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
import im.turms.server.common.util.CollectorUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidUserRelationshipGroupKey;
import im.turms.turms.constraint.ValidUserRelationshipKey;
import im.turms.turms.util.ProtoModelUtil;
import im.turms.turms.workflow.dao.domain.user.UserRelationship;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroupMember;
import im.turms.turms.workflow.service.documentation.UsesNonIndexedData;
import im.turms.turms.workflow.service.impl.user.UserVersionService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserRelationshipGroupService {

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final UserVersionService userVersionService;
    private final UserRelationshipService userRelationshipService;

    /**
     * @param userRelationshipService is lazy because: UserRelationshipService -> UserRelationshipGroupService -> UserRelationshipService
     */
    public UserRelationshipGroupService(
            Node node,
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient,
            UserVersionService userVersionService,
            @Lazy UserRelationshipService userRelationshipService) {
        this.mongoClient = mongoClient;
        this.node = node;
        this.userVersionService = userVersionService;
        this.userRelationshipService = userRelationshipService;
    }

    public Mono<UserRelationshipGroup> createRelationshipGroup(
            @NotNull Long ownerId,
            @Nullable Integer groupIndex,
            @NotNull String groupName,
            @Nullable @PastOrPresent Date creationDate,
            @Nullable ClientSession session) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(groupName, "groupName");
            AssertUtil.pastOrPresent(creationDate, "creationDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Integer finalGroupIndex = groupIndex == null
                ? RandomUtil.nextPositiveInt()
                : groupIndex;
        if (creationDate == null) {
            creationDate = new Date();
        }
        UserRelationshipGroup group = new UserRelationshipGroup(
                ownerId,
                finalGroupIndex,
                groupName,
                creationDate);
        Mono<UserRelationshipGroup> result = mongoClient.insert(session, group)
                .thenReturn(group);
        // If groupIndex is null but session isn't null and DuplicateKeyException occurs,
        // it's a bug of server because we cannot "resume" the session.
        // Luckily, we don't have the case now.
        if (groupIndex == null && session == null) {
            Date finalCreationDate = creationDate;
            return result
                    .onErrorResume(DuplicateKeyException.class, t ->
                            createRelationshipGroup(ownerId, null, groupName, finalCreationDate, null));
        }
        return result;
    }

    public Flux<UserRelationshipGroup> queryRelationshipGroupsInfos(@NotNull Long ownerId) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerId);
        return mongoClient.findMany(UserRelationshipGroup.class, filter);
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
                                .setLastUpdatedDate(date.getTime());
                        return queryRelationshipGroupsInfos(ownerId)
                                .collect(CollectorUtil.toList())
                                .map(groups -> {
                                    for (UserRelationshipGroup group : groups) {
                                        builder.addUserRelationshipGroups(ProtoModelUtil.relationshipGroup2proto(group));
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
        Filter filter = Filter.newBuilder(2)
                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                .eq(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID, relatedUserId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX);
        return mongoClient.findMany(UserRelationshipGroupMember.class, filter, options)
                .map(member -> member.getKey().getGroupIndex());
    }

    public Flux<Long> queryRelationshipGroupMemberIds(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(groupIndex, "groupIndex");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                .eq(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndex);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID);
        return mongoClient.findMany(UserRelationshipGroupMember.class, filter, options)
                .map(member -> member.getKey().getRelatedUserId());
    }

    public Flux<Long> queryRelationshipGroupMemberIds(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> groupIndexes,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, groupIndexes);
        QueryOptions options = QueryOptions.newBuilder(3)
                .paginateIfNotNull(page, size)
                .include(UserRelationshipGroupMember.Fields.ID_RELATED_USER_ID);
        return mongoClient.findMany(UserRelationshipGroupMember.class, filter, options)
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
        UserRelationshipGroup.Key key = new UserRelationshipGroup.Key(ownerId, groupIndex);
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, key);
        Update update = Update.newBuilder(1)
                .set(UserRelationshipGroup.Fields.NAME, newGroupName);
        return mongoClient.updateOne(UserRelationshipGroup.class, filter, update)
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
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, keys);
        Update update = Update
                .newBuilder(2)
                .setIfNotNull(UserRelationshipGroup.Fields.NAME, name)
                .setIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDate);
        return mongoClient.updateMany(UserRelationshipGroup.class, filter, update);
    }

    public Mono<UserRelationshipGroupMember> addRelatedUserToRelationshipGroups(
            @NotNull Long ownerId,
            @NotNull Integer groupIndex,
            @NotNull Long relatedUserId,
            @Nullable ClientSession session) {
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
                    Date now = new Date();
                    UserRelationshipGroupMember member = new UserRelationshipGroupMember(
                            ownerId, groupIndex, relatedUserId, now);
                    return mongoClient.upsert(session, member)
                            .flatMap(groupMember -> userVersionService.updateRelationshipGroupsVersion(ownerId)
                                    .onErrorResume(t -> Mono.empty()))
                            .thenReturn(member);
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
            AssertUtil.state(!deleteGroupIndex.equals(DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX),
                    "The default relationship group cannot be deleted");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (deleteGroupIndex.equals(newGroupIndex)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        } else {
            return mongoClient
                    .inTransaction(session -> {
                        Filter filterMember = Filter.newBuilder(2)
                                .eq(UserRelationshipGroupMember.Fields.ID_OWNER_ID, ownerId)
                                .eq(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, deleteGroupIndex);
                        UserRelationshipGroup.Key key = new UserRelationshipGroup.Key(ownerId, deleteGroupIndex);
                        Filter filterGroup = Filter.newBuilder(1)
                                .eq(DaoConstant.ID_FIELD_NAME, key);
                        // FIXME: after https://github.com/turms-im/turms/issues/589 done
                        Update update = Update.newBuilder(1)
                                .set(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, newGroupIndex);
                        return mongoClient.updateMany(session, UserRelationshipGroupMember.class, filterMember, update)
                                .then(mongoClient.deleteMany(session, UserRelationshipGroup.class, filterGroup))
                                .then(userVersionService.updateRelationshipGroupsVersion(ownerId).onErrorResume(t -> Mono.empty()));
                    })
                    .retryWhen(DaoConstant.TRANSACTION_RETRY);
        }
    }

    public Mono<DeleteResult> deleteAllRelationshipGroups(
            @NotEmpty Set<Long> ownerIds,
            @Nullable ClientSession session,
            boolean updateRelationshipGroupsVersion) {
        try {
            AssertUtil.notEmpty(ownerIds, "ownerIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(2)
                .in(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .ne(UserRelationshipGroup.Fields.ID_GROUP_INDEX, 0);
        if (updateRelationshipGroupsVersion) {
            return mongoClient.deleteMany(session, UserRelationshipGroup.class, filter)
                    .flatMap(result -> userVersionService.updateRelationshipGroupsVersion(ownerIds)
                            .onErrorResume(t -> Mono.empty())
                            .thenReturn(result));
        } else {
            return mongoClient.deleteMany(session, UserRelationshipGroup.class, filter);
        }
    }

    public Mono<DeleteResult> deleteRelatedUserFromAllRelationshipGroups(
            @NotNull Long ownerId,
            @NotNull Long relatedUserId,
            @Nullable ClientSession session,
            boolean updateRelationshipGroupsMembersVersion) {
        try {
            AssertUtil.notNull(ownerId, "ownerId");
            AssertUtil.notNull(relatedUserId, "relatedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return deleteRelatedUsersFromAllRelationshipGroups(Set.of(new UserRelationship.Key(ownerId, relatedUserId)), session,
                updateRelationshipGroupsMembersVersion);
    }

    public Mono<DeleteResult> deleteRelatedUsersFromAllRelationshipGroups(
            @NotEmpty Set<UserRelationship.@ValidUserRelationshipKey Key> keys,
            @Nullable ClientSession session,
            boolean updateRelationshipGroupsMembersVersion) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (UserRelationship.Key key : keys) {
                DomainConstraintUtil.validRelationshipKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, keys);
        if (updateRelationshipGroupsMembersVersion) {
            return mongoClient.deleteMany(session, UserRelationshipGroupMember.class, filter)
                    .flatMap(result -> {
                        Set<Long> ownerIds = CollectionUtil.newSetWithExpectedSize(keys.size());
                        for (UserRelationship.Key key : keys) {
                            ownerIds.add(key.getOwnerId());
                        }
                        return userVersionService.updateRelationshipGroupsVersion(ownerIds)
                                .onErrorResume(t -> Mono.empty())
                                .thenReturn(result);
                    });
        } else {
            return mongoClient.deleteMany(session, UserRelationshipGroupMember.class, filter);
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
        UserRelationshipGroupMember.Key key = new UserRelationshipGroupMember.Key(ownerId, currentGroupIndex, relatedUserId);
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, key);
        if (currentGroupIndex.equals(targetGroupIndex)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        } else {
            // FIXME: after https://github.com/turms-im/turms/issues/589 done
            Update update = Update.newBuilder(1)
                    .set(UserRelationshipGroupMember.Fields.ID_GROUP_INDEX, targetGroupIndex);
            return mongoClient.updateOne(UserRelationshipGroupMember.class, filter, update);
        }
    }

    public Mono<DeleteResult> deleteRelationshipGroups() {
        return mongoClient.deleteAll(UserRelationshipGroup.class);
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
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, keys);
        return mongoClient.deleteMany(UserRelationshipGroup.class, filter);
    }

    public Flux<UserRelationshipGroup> queryRelationshipGroups(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> indexes,
            @Nullable Set<String> names,
            @Nullable DateRange creationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .inIfNotNull(UserRelationshipGroup.Fields.NAME, names)
                .addBetweenIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(UserRelationshipGroup.class, filter, options);
    }

    public Mono<Long> countRelationshipGroups(
            @Nullable Set<Long> ownerIds,
            @Nullable Set<Integer> indexes,
            @Nullable Set<String> names,
            @Nullable DateRange creationDateRange) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_OWNER_ID, ownerIds)
                .inIfNotNull(UserRelationshipGroup.Fields.ID_GROUP_INDEX, indexes)
                .inIfNotNull(UserRelationshipGroup.Fields.NAME, names)
                .addBetweenIfNotNull(UserRelationshipGroup.Fields.CREATION_DATE, creationDateRange);
        return mongoClient.count(UserRelationshipGroup.class, filter);
    }

}