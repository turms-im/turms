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

package im.turms.service.domain.group.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.access.client.dto.model.common.LongsWithVersion;
import im.turms.server.common.access.client.dto.model.user.UserInfosWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.po.GroupBlockedUser;
import im.turms.service.domain.group.repository.GroupBlocklistRepository;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidGroupBlockedUserKey;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.service.storage.mongo.MongoOperationConst.TRANSACTION_RETRY;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupBlocklistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupBlocklistService.class);

    private final GroupBlocklistRepository groupBlocklistRepository;
    private final GroupMemberService groupMemberService;
    private final GroupVersionService groupVersionService;
    private final UserService userService;

    public GroupBlocklistService(
            GroupBlocklistRepository groupBlocklistRepository,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            UserService userService) {
        this.groupBlocklistRepository = groupBlocklistRepository;
        this.groupMemberService = groupMemberService;
        this.groupVersionService = groupVersionService;
        this.userService = userService;
    }

    /**
     * @return an empty publish if the user was blocked successfully, or an error for other cases.
     *         Note that the method will throw if the user has been blocked
     */
    public Mono<Void> authAndBlockUser(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Long userIdToBlock,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userIdToBlock, "userIdToBlock");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (requesterId.equals(userIdToBlock)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Cannot block oneself"));
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                .flatMap(authenticated -> authenticated
                        ? groupMemberService.isGroupMember(groupId, userIdToBlock, false)
                        : Mono.error(ResponseException
                                .get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_ADD_BLOCKED_USER)))
                .flatMap(isGroupMember -> {
                    GroupBlockedUser blockedUser =
                            new GroupBlockedUser(groupId, userIdToBlock, new Date(), requesterId);
                    if (isGroupMember) {
                        Mono<Void> updateVersion =
                                groupVersionService.updateVersion(groupId, true, true, false, false)
                                        .onErrorResume(t -> {
                                            LOGGER.error(
                                                    "Caught an error while updating the members and blocklist version of the group ({}) after blocking a user",
                                                    groupId,
                                                    t);
                                            return Mono.empty();
                                        })
                                        .then();
                        if (session == null) {
                            return groupBlocklistRepository
                                    .inTransaction(newSession -> groupMemberService
                                            .deleteGroupMember(groupId,
                                                    userIdToBlock,
                                                    newSession,
                                                    false)
                                            .then(groupBlocklistRepository.insert(blockedUser,
                                                    newSession))
                                            .then(updateVersion)
                                            .retryWhen(TRANSACTION_RETRY));
                        }
                        return groupMemberService
                                .deleteGroupMember(groupId, userIdToBlock, session, false)
                                .then(groupBlocklistRepository.insert(blockedUser, session))
                                .then(updateVersion);
                    }
                    return groupBlocklistRepository.insert(blockedUser, session)
                            .then(groupVersionService.updateBlocklistVersion(groupId)
                                    .onErrorResume(t -> {
                                        LOGGER.error(
                                                "Caught an error while updating the blocklist version of the group ({}) after blocking a user",
                                                groupId,
                                                t);
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
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userIdToUnblock, "userIdToUnblock");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_REMOVE_BLOCKED_USER));
                    }
                    GroupBlockedUser.Key key = new GroupBlockedUser.Key(groupId, userIdToUnblock);
                    Mono<DeleteResult> removeMono =
                            groupBlocklistRepository.deleteById(key, session);
                    if (updateBlocklistVersion) {
                        return removeMono.flatMap(
                                result -> groupVersionService.updateBlocklistVersion(groupId)
                                        .onErrorResume(t -> {
                                            LOGGER.error(
                                                    "Caught an error while updating the blocklist version of the group ({}) after unblocking a user",
                                                    groupId,
                                                    t);
                                            return Mono.empty();
                                        })
                                        .then());
                    }
                    return removeMono.then();
                });
    }

    public Flux<Long> findBlockedUserIds(@NotNull Long groupId, @NotNull Set<Long> userIds) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userIds, "userIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        List<GroupBlockedUser.Key> keys = new ArrayList<>(userIds.size());
        for (Long userId : userIds) {
            keys.add(new GroupBlockedUser.Key(groupId, userId));
        }
        return groupBlocklistRepository.findIdsByIds(keys)
                .map(user -> user.getKey()
                        .getUserId());
    }

    public Mono<Boolean> isBlocked(@NotNull Long groupId, @NotNull Long userId) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        GroupBlockedUser.Key key = new GroupBlockedUser.Key(groupId, userId);
        return groupBlocklistRepository.existsById(key);
    }

    public Flux<Long> queryGroupBlockedUserIds(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        return groupBlocklistRepository.findBlockedUserIds(groupId);
    }

    public Flux<GroupBlockedUser> queryBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        return groupBlocklistRepository
                .findBlockedUsers(groupIds, userIds, blockDateRange, requesterIds, page, size);
    }

    public Mono<Long> countBlockedUsers(
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> userIds,
            @Nullable DateRange blockDateRange,
            @Nullable Set<Long> requesterIds) {
        return groupBlocklistRepository.count(groupIds, userIds, blockDateRange, requesterIds);
    }

    public Mono<LongsWithVersion> queryGroupBlockedUserIdsWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionService.queryBlocklistVersion(groupId)
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
                    return queryGroupBlockedUserIds(groupId)
                            .collect(Collectors.toCollection(recyclableList::getValue))
                            .map(ids -> {
                                if (ids.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                return ClientMessagePool.getLongsWithVersionBuilder()
                                        .setLastUpdatedDate(version.getTime())
                                        .addAllLongs(ids)
                                        .build();
                            })
                            .doFinally(signalType -> recyclableList.recycle());

                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<UserInfosWithVersion> queryGroupBlockedUserInfosWithVersion(
            @NotNull Long groupId,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionService.queryBlocklistVersion(groupId)
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    Recyclable<List<Long>> recyclableList = ListRecycler.obtain();
                    Recyclable<Set<User>> recyclableSet = SetRecycler.obtain();
                    return queryGroupBlockedUserIds(groupId)
                            .collect(Collectors.toCollection(recyclableList::getValue))
                            .flatMapMany(ids -> {
                                if (ids.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                return userService.queryUsersProfile(ids, false);
                            })
                            .collect(Collectors.toCollection(recyclableSet::getValue))
                            .map(users -> {
                                if (users.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                UserInfosWithVersion.Builder builder =
                                        ClientMessagePool.getUserInfosWithVersionBuilder()
                                                .setLastUpdatedDate(version.getTime());
                                for (User user : users) {
                                    builder.addUserInfos(
                                            ProtoModelConvertor.userProfile2proto(user));
                                }
                                return builder.build();
                            })
                            .doFinally(signalType -> {
                                recyclableList.recycle();
                                recyclableSet.recycle();
                            });

                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<GroupBlockedUser> addBlockedUser(
            @NotNull Long groupId,
            @NotNull Long userId,
            @NotNull Long requesterId,
            @Nullable @PastOrPresent Date blockDate) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(userId, "userId");
            Validator.notNull(requesterId, "requesterId");
            Validator.pastOrPresent(blockDate, "blockDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (blockDate == null) {
            blockDate = new Date();
        }
        GroupBlockedUser user = new GroupBlockedUser(groupId, userId, blockDate, requesterId);
        return groupBlocklistRepository.insert(user)
                .thenReturn(user);
    }

    public Mono<UpdateResult> updateBlockedUsers(
            @NotEmpty Set<GroupBlockedUser.@ValidGroupBlockedUserKey Key> keys,
            @Nullable @PastOrPresent Date blockDate,
            @Nullable Long requesterId) {
        try {
            Validator.notEmpty(keys, "keys");
            for (GroupBlockedUser.Key key : keys) {
                DataValidator.validGroupBlockedUserKey(key);
            }
            Validator.pastOrPresent(blockDate, "blockDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(blockDate, requesterId)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupBlocklistRepository.updateBlockedUsers(keys, blockDate, requesterId);
    }

    public Mono<DeleteResult> deleteBlockedUsers(
            @NotEmpty Set<GroupBlockedUser.@ValidGroupBlockedUserKey Key> keys) {
        try {
            Validator.notEmpty(keys, "keys");
            for (GroupBlockedUser.Key key : keys) {
                DataValidator.validGroupBlockedUserKey(key);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupBlocklistRepository.deleteByIds(keys);
    }

}