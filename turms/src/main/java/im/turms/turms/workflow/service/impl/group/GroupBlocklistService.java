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
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.user.UserInfo;
import im.turms.common.model.bo.user.UsersInfosWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidGroupBlockedUserKey;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.group.GroupBlockedUser;
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
public class GroupBlocklistService {

    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserService userService;

    public GroupBlocklistService(
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            UserService userService) {
        this.groupMemberService = groupMemberService;
        this.mongoTemplate = mongoTemplate;
        this.groupVersionService = groupVersionService;
        this.userService = userService;
    }

    /**
     * @return an empty publish if the user was blocked successfully, or an error for other cases.
     * Note that the method will throw if the user has been blocked
     */
    public Mono<Void> blockUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userIdToBlock,
            @Nullable ReactiveMongoOperations operations) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userIdToBlock, "userIdToBlock");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> authenticated
                        ? groupMemberService.isGroupMember(groupId, userIdToBlock)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER)))
                .flatMap(isGroupMember -> {
                    GroupBlockedUser blockedUser = new GroupBlockedUser(
                            groupId, userIdToBlock, new Date(), requesterId);
                    if (isGroupMember) {
                        Mono<Boolean> updateVersion = groupVersionService.updateVersion(
                                groupId,
                                false,
                                true,
                                true,
                                false,
                                false);
                        if (operations != null) {
                            return groupMemberService.deleteGroupMembers(groupId, userIdToBlock, operations, false)
                                    .then(operations.insert(blockedUser, GroupBlockedUser.COLLECTION_NAME))
                                    .then(updateVersion.then().onErrorResume(throwable -> Mono.empty()));
                        } else {
                            return mongoTemplate
                                    .inTransaction()
                                    .execute(newOperations -> groupMemberService.deleteGroupMembers(groupId, userIdToBlock, newOperations, false)
                                            .then(newOperations.insert(blockedUser, GroupBlockedUser.COLLECTION_NAME))
                                            .then(updateVersion.then().onErrorResume(throwable -> Mono.empty())))
                                    .retryWhen(DaoConstant.TRANSACTION_RETRY)
                                    .singleOrEmpty();
                        }
                    } else {
                        Mono<Boolean> updateVersion = groupVersionService.updateBlocklistVersion(groupId);
                        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                        return mongoOperations.insert(blockedUser, GroupBlockedUser.COLLECTION_NAME)
                                .then(updateVersion.then().onErrorResume(throwable -> Mono.empty()));
                    }
                });
    }

    public Mono<Void> unblockUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userIdToUnblock,
            @Nullable ReactiveMongoOperations operations,
            boolean updateBlocklistVersion) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(userIdToUnblock, "userIdToUnblock");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService
                .isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> {
                    if (authenticated) {
                        ReactiveMongoOperations mongoOperations = operations != null ? operations : mongoTemplate;
                        Query query = new Query()
                                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(new GroupBlockedUser.Key(groupId, userIdToUnblock)));
                        Mono<DeleteResult> removeMono = mongoOperations.remove(query, GroupBlockedUser.class, GroupBlockedUser.COLLECTION_NAME);
                        if (updateBlocklistVersion) {
                            return removeMono.flatMap(result -> groupVersionService.updateBlocklistVersion(groupId)
                                    .onErrorResume(throwable -> Mono.empty())
                                    .then());
                        }
                        return removeMono.then();
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER));
                    }
                });
    }

    public Flux<Long> queryGroupBlockedUserIds(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(GroupBlockedUser.Fields.ID_GROUP_ID).is(groupId));
        query.fields().include(GroupBlockedUser.Fields.ID_USER_ID);
        return mongoTemplate
                .find(query, GroupBlockedUser.class)
                .map(groupBlockedUser -> groupBlockedUser.getKey().getUserId());
    }

    public Flux<GroupBlockedUser> queryBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupBlockedUser.Fields.ID_USER_ID, userIds)
                .addInIfNotNull(GroupBlockedUser.Fields.ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDateRange)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupBlockedUser.class, GroupBlockedUser.COLLECTION_NAME);
    }

    public Mono<Long> countBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(GroupBlockedUser.Fields.ID_USER_ID, userIds)
                .addInIfNotNull(GroupBlockedUser.Fields.ID_GROUP_ID, groupIds)
                .addInIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDateRange)
                .buildQuery();
        return mongoTemplate.count(query, GroupBlockedUser.class, GroupBlockedUser.COLLECTION_NAME);
    }

    public Mono<Int64ValuesWithVersion> queryGroupBlockedUserIdsWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupVersionService
                .queryBlocklistVersion(groupId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupBlockedUserIds(groupId)
                                .collect(Collectors.toSet())
                                .map(ids -> {
                                    if (ids.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    return Int64ValuesWithVersion
                                            .newBuilder()
                                            .setLastUpdatedDate(Int64Value.of(version.getTime()))
                                            .addAllValues(ids)
                                            .build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<UsersInfosWithVersion> queryGroupBlockedUserInfosWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupVersionService
                .queryBlocklistVersion(groupId)
                .flatMap(version -> {
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupBlockedUserIds(groupId)
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
                                    builder.setLastUpdatedDate(Int64Value.of(version.getTime()));
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

    public Mono<GroupBlockedUser> addBlockedUser(
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
        GroupBlockedUser user = new GroupBlockedUser(groupId, userId, blockDate, requesterId);
        return mongoTemplate.insert(user, GroupBlockedUser.COLLECTION_NAME);
    }

    public Mono<UpdateResult> updateBlockedUsers(
            @NotEmpty Set<GroupBlockedUser.@ValidGroupBlockedUserKey Key> keys,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Long requesterId) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (GroupBlockedUser.Key key : keys) {
                DomainConstraintUtil.validGroupBlockedUserKey(key);
            }
            AssertUtil.pastOrPresent(blockDate, "blockDate");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(blockDate, requesterId)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDate)
                .setIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterId)
                .build();
        return mongoTemplate.updateMulti(query, update, GroupBlockedUser.class, GroupBlockedUser.COLLECTION_NAME);
    }

    public Mono<DeleteResult> deleteBlockedUsers(@NotEmpty Set<GroupBlockedUser.@ValidGroupBlockedUserKey Key> keys) {
        try {
            AssertUtil.notEmpty(keys, "keys");
            for (GroupBlockedUser.Key key : keys) {
                DomainConstraintUtil.validGroupBlockedUserKey(key);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(keys));
        return mongoTemplate.remove(query, GroupBlockedUser.class, GroupBlockedUser.COLLECTION_NAME);
    }

}