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

import com.google.protobuf.Int64Value;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.user.UserInfo;
import im.turms.common.model.bo.user.UsersInfosWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constraint.ValidGroupBlacklistedUserKey;
import im.turms.turms.util.MapUtil;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.GroupBlacklistedUser;
import im.turms.turms.workflow.service.impl.user.UserService;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import org.springframework.beans.factory.annotation.Qualifier;
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
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Service
public class GroupBlacklistService {

    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserService userService;

    public GroupBlacklistService(
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            UserService userService) {
        this.groupMemberService = groupMemberService;
        this.mongoTemplate = mongoTemplate;
        this.groupVersionService = groupVersionService;
        this.userService = userService;
    }

    public Mono<Boolean> blacklistUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long blacklistedUserId,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(blacklistedUserId, "blacklistedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> authenticated != null && authenticated
                        ? groupMemberService.isGroupMember(groupId, blacklistedUserId)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)))
                .flatMap(isGroupMember -> {
                    GroupBlacklistedUser blacklistedUser = new GroupBlacklistedUser(
                            groupId, blacklistedUserId, new Date(), requesterId);
                    if (isGroupMember != null && isGroupMember) {
                        Mono<Boolean> updateVersion = groupVersionService.updateVersion(
                                groupId,
                                false,
                                true,
                                true,
                                false,
                                false);
                        if (operations != null) {
                            return groupMemberService.deleteGroupMembers(groupId, Set.of(blacklistedUserId), operations, false)
                                    .then(operations.insert(blacklistedUser, GroupBlacklistedUser.COLLECTION_NAME))
                                    .then(updateVersion)
                                    .thenReturn(true);
                        } else {
                            return mongoTemplate
                                    .inTransaction()
                                    .execute(newOperations -> groupMemberService.deleteGroupMembers(groupId, Set.of(blacklistedUserId), newOperations, false)
                                            .then(newOperations.insert(blacklistedUser, GroupBlacklistedUser.COLLECTION_NAME))
                                            .then(updateVersion)
                                            .thenReturn(true))
                                    .retryWhen(DaoConstant.TRANSACTION_RETRY)
                                    .singleOrEmpty();
                        }
                    } else {
                        Mono<Boolean> updateVersion = groupVersionService.updateBlacklistVersion(groupId);
                        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                        return mongoOperations.insert(blacklistedUser, GroupBlacklistedUser.COLLECTION_NAME)
                                .then(updateVersion)
                                .thenReturn(true);
                    }
                });
    }

    public Mono<Boolean> unblacklistUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long unblacklistedUserId,
            @Nullable ReactiveMongoOperations operations,
            boolean updateBlacklistVersion) {
        try {
            AssertUtil.notNull(unblacklistedUserId, "unblacklistedUserId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService
                .isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> {
                    if (authenticated != null && authenticated) {
                        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                        Query query = new Query()
                                .addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_GROUP_ID).is(groupId))
                                .addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_USER_ID).is(unblacklistedUserId));
                        return mongoOperations.remove(query, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME)
                                .flatMap(result -> {
                                    if (result.wasAcknowledged()) {
                                        return updateBlacklistVersion
                                                ? groupVersionService.updateBlacklistVersion(groupId).thenReturn(true)
                                                : Mono.just(true);
                                    } else {
                                        return Mono.just(false);
                                    }
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Flux<Long> queryGroupBlacklistedUsersIds(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupBlacklistedUser.Fields.ID_USER_ID);
        return mongoTemplate
                .find(query, GroupBlacklistedUser.class)
                .map(groupBlacklistedUser -> groupBlacklistedUser.getKey().getUserId());
    }

    public Flux<GroupBlacklistedUser> queryBlacklistedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupBlacklistedUser.Fields.ID_USER_ID, userIds)
                .addInIfNotNull(GroupBlacklistedUser.Fields.ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupBlacklistedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlacklistedUser.Fields.BLOCK_DATE, blockDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME);
    }

    public Mono<Long> countBlacklistedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupBlacklistedUser.Fields.ID_USER_ID, userIds)
                .addInIfNotNull(GroupBlacklistedUser.Fields.ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupBlacklistedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlacklistedUser.Fields.BLOCK_DATE, blockDateRange)
                .buildQuery();
        return mongoTemplate.count(query, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME);
    }

    public Mono<Int64ValuesWithVersion> queryGroupBlacklistedUsersIdsWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupVersionService
                .queryBlacklistVersion(groupId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupBlacklistedUsersIds(groupId)
                                .collect(Collectors.toSet())
                                .map(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    return Int64ValuesWithVersion
                                            .newBuilder()
                                            .setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build())
                                            .addAllValues(ids)
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<UsersInfosWithVersion> queryGroupBlacklistedUsersInfosWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        return groupVersionService
                .queryBlacklistVersion(groupId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupBlacklistedUsersIds(groupId)
                                .collect(Collectors.toSet())
                                .flatMapMany(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    } else {
                                        return userService.queryUsersProfiles(ids, false);
                                    }
                                })
                                .collect(Collectors.toSet())
                                .map(users -> {
                                    if (users.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    UsersInfosWithVersion.Builder builder = UsersInfosWithVersion.newBuilder();
                                    builder.setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build());
                                    for (User user : users) {
                                        UserInfo userInfo = ProtoUtil.userProfile2proto(user).build();
                                        builder.addUserInfos(userInfo);
                                    }
                                    return builder.build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<GroupBlacklistedUser> addBlacklistedUser(
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull Long requesterId,
            @Nullable @PastOrPresent Date blockDate) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.pastOrPresent(blockDate, "blockDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (blockDate == null) {
            blockDate = new Date();
        }
        GroupBlacklistedUser user = new GroupBlacklistedUser(groupId, userId, blockDate, requesterId);
        return mongoTemplate.insert(user, GroupBlacklistedUser.COLLECTION_NAME);
    }

    public Mono<Boolean> updateBlacklistedUsers(
            @NotNull Long groupId,
            @NotEmpty Set<Long> userIds,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Long requesterId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notEmpty(userIds, "userIds");
            AssertUtil.pastOrPresent(blockDate, "blockDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(blockDate, requesterId)) {
            return Mono.just(true);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_USER_ID).in(userIds));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(GroupBlacklistedUser.Fields.BLOCK_DATE, blockDate)
                .setIfNotNull(GroupBlacklistedUser.Fields.REQUESTER_ID, requesterId)
                .build();
        return mongoTemplate.updateMulti(query, update, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<Boolean> updateBlacklistedUsers(
            @NotEmpty Set<GroupBlacklistedUser.@ValidGroupBlacklistedUserKey Key> keys,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Long requesterId) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (GroupBlacklistedUser.Key key : keys) {
                DomainConstraintUtil.validGroupBlacklistedUserKey(key);
            }
            AssertUtil.pastOrPresent(blockDate, "blockDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(blockDate, requesterId)) {
            return Mono.just(true);
        }
        return MapUtil.fluxMerge(multimap -> {
            for (GroupBlacklistedUser.Key key : keys) {
                multimap.put(key.getGroupId(), key.getUserId());
            }
        }, (monos, key, values) -> monos.add(updateBlacklistedUsers(
                key,
                values,
                blockDate,
                requesterId)));
    }

    public Mono<Boolean> deleteBlacklistedUsers(@NotEmpty Set<GroupBlacklistedUser.@ValidGroupBlacklistedUserKey Key> keys) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (GroupBlacklistedUser.Key key : keys) {
                DomainConstraintUtil.validGroupBlacklistedUserKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return MapUtil.fluxMerge(multimap -> {
            for (GroupBlacklistedUser.Key key : keys) {
                multimap.put(key.getGroupId(), key.getUserId());
            }
        }, (monos, key, values) -> monos.add(deleteBlacklistedUsers(key, values)));
    }

    public Mono<Boolean> deleteBlacklistedUsers(@NotNull Long groupId, @NotEmpty Set<Long> userIds) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notEmpty(userIds, "userIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_GROUP_ID).is(groupId))
                .addCriteria(Criteria.where(GroupBlacklistedUser.Fields.ID_USER_ID).in(userIds));
        return mongoTemplate.remove(query, GroupBlacklistedUser.class, GroupBlacklistedUser.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
    }

}
