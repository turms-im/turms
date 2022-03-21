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

package im.turms.service.workflow.service.impl.group;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import im.turms.common.model.bo.common.Int64ValuesWithVersion;
import im.turms.common.model.bo.user.UsersInfosWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.mongo.DomainFieldName;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.DateUtil;
import im.turms.service.constant.OperationResultConstant;
import im.turms.service.constraint.ValidGroupBlockedUserKey;
import im.turms.service.proto.ProtoModelConvertor;
import im.turms.service.workflow.dao.domain.group.GroupBlockedUser;
import im.turms.service.workflow.service.impl.user.UserService;
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
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.service.constant.DaoConstant.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupBlocklistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBlocklistService.class);

    private final TurmsMongoClient mongoClient;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserService userService;

    public GroupBlocklistService(
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            UserService userService) {
        this.groupMemberService = groupMemberService;
        this.mongoClient = mongoClient;
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
            @Nullable ClientSession session) {
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
                        Mono<Void> updateVersion = groupVersionService.updateVersion(
                                        groupId,
                                        false,
                                        true,
                                        true,
                                        false,
                                        false)
                                .onErrorResume(t -> {
                                    LOGGER.error("Caught an error while updating the members and blocklist version of the group {} after blocking a user",
                                            groupId, t);
                                    return Mono.empty();
                                })
                                .then();
                        if (session == null) {
                            return mongoClient
                                    .inTransaction(newSession ->
                                            groupMemberService.deleteGroupMembers(groupId, userIdToBlock, newSession, false)
                                                    .then(mongoClient.insert(newSession, blockedUser))
                                                    .then(updateVersion)
                                                    .retryWhen(TRANSACTION_RETRY));
                        }
                        return groupMemberService.deleteGroupMembers(groupId, userIdToBlock, session, false)
                                .then(mongoClient.insert(session, blockedUser))
                                .then(updateVersion);
                    }
                    return mongoClient.insert(session, blockedUser)
                            .then(groupVersionService.updateBlocklistVersion(groupId)
                                    .onErrorResume(t -> {
                                        LOGGER.error("Caught an error while updating the blocklist version of the group {} after blocking a user",
                                                groupId, t);
                                        return Mono.empty();
                                    })
                                    .then());
                });
    }

    public Mono<Void> unblockUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userIdToUnblock,
            @Nullable ClientSession session,
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
                    if (!authenticated) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER));
                    }
                    GroupBlockedUser.Key key = new GroupBlockedUser.Key(groupId, userIdToUnblock);
                    Filter filter = Filter.newBuilder(1)
                            .eq(DomainFieldName.ID, key);
                    Mono<DeleteResult> removeMono = mongoClient.deleteOne(session, GroupBlockedUser.class, filter);
                    if (updateBlocklistVersion) {
                        return removeMono.flatMap(result -> groupVersionService.updateBlocklistVersion(groupId)
                                .onErrorResume(t -> {
                                    LOGGER.error("Caught an error while updating the blocklist version of the group {} after unblocking a user",
                                            groupId, t);
                                    return Mono.empty();
                                })
                                .then());
                    }
                    return removeMono.then();
                });
    }

    public Flux<Long> queryGroupBlockedUserIds(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupBlockedUser.Fields.ID_GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupBlockedUser.Fields.ID_USER_ID);
        return mongoClient
                .findMany(GroupBlockedUser.class, filter, options)
                .map(groupBlockedUser -> groupBlockedUser.getKey().getUserId());
    }

    public Flux<GroupBlockedUser> queryBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(GroupBlockedUser.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupBlockedUser.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDateRange);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(GroupBlockedUser.class, filter, options);
    }

    public Mono<Long> countBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds) {
        Filter filter = Filter.newBuilder(5)
                .inIfNotNull(GroupBlockedUser.Fields.ID_USER_ID, userIds)
                .inIfNotNull(GroupBlockedUser.Fields.ID_GROUP_ID, groupIds)
                .inIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterIds)
                .addBetweenIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDateRange);
        return mongoClient.count(GroupBlockedUser.class, filter);
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
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryGroupBlockedUserIds(groupId)
                            .collect(Collectors.toSet())
                            .map(ids -> {
                                if (ids.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                return Int64ValuesWithVersion
                                        .newBuilder()
                                        .setLastUpdatedDate(version.getTime())
                                        .addAllValues(ids)
                                        .build();
                            });

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
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryGroupBlockedUserIds(groupId)
                            .collect(Collectors.toSet())
                            .flatMapMany(ids -> {
                                if (ids.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                return userService.queryUsersProfiles(ids, false);
                            })
                            .collect(Collectors.toSet())
                            .map(users -> {
                                if (users.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                UsersInfosWithVersion.Builder builder = UsersInfosWithVersion.newBuilder();
                                builder.setLastUpdatedDate(version.getTime());
                                for (User user : users) {
                                    builder.addUserInfos(ProtoModelConvertor.userProfile2proto(user));
                                }
                                return builder.build();
                            });

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
        return mongoClient.insert(user).thenReturn(user);
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
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, keys);
        Update update = Update
                .newBuilder(2)
                .setIfNotNull(GroupBlockedUser.Fields.BLOCK_DATE, blockDate)
                .setIfNotNull(GroupBlockedUser.Fields.REQUESTER_ID, requesterId);
        return mongoClient.updateMany(GroupBlockedUser.class, filter, update);
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
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, keys);
        return mongoClient.deleteMany(GroupBlockedUser.class, filter);
    }

}