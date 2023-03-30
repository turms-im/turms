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
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
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
public class UserPermissionGroupService {

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

        idToPermissionGroup.putIfAbsent(DEFAULT_USER_PERMISSION_GROUP_ID,
                new UserPermissionGroup(
                        DEFAULT_USER_PERMISSION_GROUP_ID,
                        Set.of(DEFAULT_GROUP_TYPE_ID),
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE,
                        Collections.emptyMap()));
        userPermissionGroupRepository.findAll()
                .subscribe(userPermissionGroup -> idToPermissionGroup
                        .put(userPermissionGroup.getId(), userPermissionGroup));
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

    public UserPermissionGroup getDefaultUserPermissionGroup() {
        return idToPermissionGroup.get(DEFAULT_USER_PERMISSION_GROUP_ID);
    }

    public Flux<UserPermissionGroup> queryUserPermissionGroups(
            @Nullable Integer page,
            @Nullable Integer size) {
        return userPermissionGroupRepository.findAll(page, size)
                .concatWithValues(getDefaultUserPermissionGroup());
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
        return userPermissionGroupRepository.updateUserPermissionGroups(groupIds,
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeIdToLimit);
    }

    public Mono<DeleteResult> deleteUserPermissionGroups(@Nullable Set<Long> groupIds) {
        if (groupIds != null && groupIds.contains(DEFAULT_USER_PERMISSION_GROUP_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The default user permission group cannot be deleted"));
        }
        return userPermissionGroupRepository.deleteByIds(groupIds)
                .doOnNext(result -> {
                    if (groupIds != null) {
                        for (Long id : groupIds) {
                            idToPermissionGroup.remove(id);
                        }
                    } else {
                        idToPermissionGroup.keySet()
                                .removeIf(id -> !id.equals(DEFAULT_USER_PERMISSION_GROUP_ID));
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

    public Mono<UserPermissionGroup> queryUserPermissionGroupByUserId(@NotNull Long userId) {
        return userService.queryUserPermissionGroupId(userId)
                .flatMap(groupId -> queryUserPermissionGroup(groupId).switchIfEmpty(
                        Mono.error(ResponseException.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                                "The user ("
                                        + userId
                                        + ") is in the nonexistent permission group ("
                                        + groupId
                                        + ")"))))
                .switchIfEmpty(Mono.error(ResponseException
                        .get(ResponseStatusCode.QUERY_PERMISSION_OF_NON_EXISTING_USER)));
    }

    public Mono<Long> countUserPermissionGroups() {
        return userPermissionGroupRepository.countAll()
                .map(number -> number + 1);
    }

}