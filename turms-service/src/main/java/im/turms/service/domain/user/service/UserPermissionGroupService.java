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

import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.service.domain.user.po.UserPermissionGroup;
import im.turms.service.domain.user.repository.UserPermissionGroupRepository;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_ID;
import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_USER_PERMISSION_GROUP_ID;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserPermissionGroupService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserPermissionGroupService.class);

    private final Map<Long, UserPermissionGroup> idToPermissionGroup = new ConcurrentHashMap<>(16);

    private final Node node;
    private final UserPermissionGroupRepository userPermissionGroupRepository;
    private final UserService userService;

    public UserPermissionGroupService(
            Node node,
            UserPermissionGroupRepository userPermissionGroupRepository,
            UserService userService) {
        this.node = node;
        this.userPermissionGroupRepository = userPermissionGroupRepository;
        this.userService = userService;

        LOGGER.info(
                "Loading all user permission groups and adding the default user permission group");
        userPermissionGroupRepository.findAll()
                .doOnNext(userPermissionGroup -> idToPermissionGroup
                        .put(userPermissionGroup.getId(), userPermissionGroup))
                .onErrorMap(t -> new RuntimeException(
                        "Caught an error while loading all user permission groups",
                        t))
                .then(Mono.defer(() -> {
                    UserPermissionGroup userPermissionGroup =
                            idToPermissionGroup.get(DEFAULT_USER_PERMISSION_GROUP_ID);
                    if (userPermissionGroup != null) {
                        return Mono.empty();
                    }
                    return addDefaultUserPermissionGroup().onErrorMap(t -> new RuntimeException(
                            "Caught an error while adding the default user permission group",
                            t));
                }))
                .block(DurationConst.ONE_MINUTE);
        LOGGER.info(
                "Loaded all user permission groups and added the default user permission group");

        userPermissionGroupRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    UserPermissionGroup userPermissionGroup = event.getFullDocument();
                    switch (operationType) {
                        case INSERT, UPDATE, REPLACE -> idToPermissionGroup
                                .put(userPermissionGroup.getId(), userPermissionGroup);
                        case DELETE -> {
                            long groupTypeId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            idToPermissionGroup.remove(groupTypeId);
                            if (groupTypeId == DEFAULT_USER_PERMISSION_GROUP_ID) {
                                LOGGER.warn(
                                        "Adding the default user permission group because it is deleted unexpectedly");
                                addDefaultUserPermissionGroup().subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while adding the default user permission group",
                                                t),
                                        () -> LOGGER
                                                .warn("Added the default user permission group"));
                            }
                        }
                        case INVALIDATE -> idToPermissionGroup.clear();
                        default -> LOGGER.fatal("Detected an illegal operation on the collection \""
                                + UserPermissionGroup.COLLECTION_NAME
                                + "\" in the change stream event: {}", event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event ({}) of the collection: \""
                                + UserPermissionGroup.COLLECTION_NAME
                                + "\"",
                        o,
                        throwable))
                .subscribe();
    }

    private Mono<UserPermissionGroup> addDefaultUserPermissionGroup() {
        return addUserPermissionGroup(DEFAULT_USER_PERMISSION_GROUP_ID,
                Set.of(DEFAULT_GROUP_TYPE_ID),
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                Collections.emptyMap()).onErrorComplete(DuplicateKeyException.class);
    }

    public Flux<UserPermissionGroup> queryUserPermissionGroups(
            @Nullable Integer page,
            @Nullable Integer size) {
        return userPermissionGroupRepository.findAll(page, size);
    }

    public Mono<UserPermissionGroup> addUserPermissionGroup(
            @Nullable Long groupId,
            @NotNull Set<Long> creatableGroupTypeIds,
            @NotNull Integer ownedGroupLimit,
            @NotNull Integer ownedGroupLimitForEachGroupType,
            @NotNull Map<Long, Integer> groupTypeIdToLimit) {
        try {
            Validator.notNull(creatableGroupTypeIds, "creatableGroupTypeIds");
            Validator.notNull(ownedGroupLimit, "ownedGroupLimit");
            Validator.notNull(ownedGroupLimitForEachGroupType, "ownedGroupLimitForEachGroupType");
            Validator.notNull(groupTypeIdToLimit, "groupTypeIdToLimit");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (groupId == null) {
            groupId = node.nextLargeGapId(ServiceType.USER_PERMISSION_GROUP);
        }
        UserPermissionGroup userPermissionGroup = new UserPermissionGroup(
                groupId,
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeIdToLimit);
        idToPermissionGroup.put(groupId, userPermissionGroup);
        return userPermissionGroupRepository.insert(userPermissionGroup)
                .thenReturn(userPermissionGroup);
    }

    public Mono<UpdateResult> updateUserPermissionGroups(
            @NotEmpty Set<Long> groupIds,
            @Nullable Set<Long> creatableGroupTypeIds,
            @Nullable Integer ownedGroupLimit,
            @Nullable Integer ownedGroupLimitForEachGroupType,
            @Nullable Map<Long, Integer> groupTypeIdToLimit) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeIdToLimit)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return userPermissionGroupRepository
                .updateUserPermissionGroups(groupIds,
                        creatableGroupTypeIds,
                        ownedGroupLimit,
                        ownedGroupLimitForEachGroupType,
                        groupTypeIdToLimit)
                .doOnNext(updateResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long groupId : groupIds) {
                        idToPermissionGroup.remove(groupId);
                    }
                });
    }

    /**
     * @implNote Note that we don't need to remove corresponding groups in "idToPermissionGroup"
     *           because their data will be synced in the watch callback.
     */
    public Mono<DeleteResult> deleteUserPermissionGroups(@Nullable Set<Long> groupIds) {
        if (groupIds == null) {
            return userPermissionGroupRepository
                    .deleteByNotIds(Set.of(DEFAULT_USER_PERMISSION_GROUP_ID));
        }
        if (groupIds.contains(DEFAULT_USER_PERMISSION_GROUP_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The default user permission group cannot be deleted"));
        }
        return userPermissionGroupRepository.deleteByIds(groupIds)
                .doOnNext(deleteResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long groupId : groupIds) {
                        idToPermissionGroup.remove(groupId);
                    }
                });
    }

    public Mono<UserPermissionGroup> queryUserPermissionGroup(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        UserPermissionGroup userPermissionGroup = idToPermissionGroup.get(groupId);
        if (userPermissionGroup == null) {
            return userPermissionGroupRepository.findById(groupId)
                    .doOnNext(type -> idToPermissionGroup.put(groupId, type));
        }
        return Mono.just(userPermissionGroup);
    }

    public Mono<UserPermissionGroup> queryStoredOrDefaultUserPermissionGroupByUserId(
            @NotNull Long userId) {
        Mono<Long> queryUserPermissionGroupId = userService.queryUserPermissionGroupId(userId)
                .defaultIfEmpty(DEFAULT_USER_PERMISSION_GROUP_ID);
        return queryUserPermissionGroupId
                .flatMap(groupId -> queryUserPermissionGroup(groupId).switchIfEmpty(
                        Mono.error(ResponseException.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                                "The user ("
                                        + userId
                                        + ") is in the nonexistent permission group ("
                                        + groupId
                                        + ")"))));
    }

    public Mono<Long> countUserPermissionGroups() {
        return userPermissionGroupRepository.countAll();
    }

}