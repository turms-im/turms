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
import im.turms.service.domain.user.po.UserRole;
import im.turms.service.domain.user.repository.UserRoleRepository;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_ID;
import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_USER_ROLE_ID;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserRoleService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRoleService.class);

    private final Map<Long, UserRole> idToRole = new ConcurrentHashMap<>(16);

    private final Node node;
    private final UserRoleRepository userRoleRepository;
    private final UserService userService;

    public UserRoleService(
            Node node,
            UserRoleRepository userRoleRepository,
            UserService userService) {
        this.node = node;
        this.userRoleRepository = userRoleRepository;
        this.userService = userService;

        LOGGER.info("Loading all user roles and adding the default user role");
        userRoleRepository.findAll()
                .doOnNext(userRole -> idToRole.put(userRole.getId(), userRole))
                .onErrorMap(t -> new RuntimeException(
                        "Caught an error while loading all user roles",
                        t))
                .then(Mono.defer(() -> {
                    UserRole userRole = idToRole.get(DEFAULT_USER_ROLE_ID);
                    if (userRole != null) {
                        return Mono.empty();
                    }
                    return addDefaultUserRole().onErrorMap(t -> new RuntimeException(
                            "Caught an error while adding the default user role",
                            t));
                }))
                .block(DurationConst.ONE_MINUTE);
        LOGGER.info("Loaded all user roles and added the default user role");

        userRoleRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    UserRole userRole = event.getFullDocument();
                    switch (operationType) {
                        case INSERT, UPDATE, REPLACE -> idToRole.put(userRole.getId(), userRole);
                        case DELETE -> {
                            long userRoleId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            idToRole.remove(userRoleId);
                            if (userRoleId == DEFAULT_USER_ROLE_ID) {
                                LOGGER.warn(
                                        "Adding the default user role because it is deleted unexpectedly");
                                addDefaultUserRole().subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while adding the default user role",
                                                t),
                                        () -> LOGGER.warn("Added the default user role"));
                            }
                        }
                        case INVALIDATE -> idToRole.clear();
                        default -> LOGGER.fatal("Detected an illegal operation on the collection \""
                                + UserRole.COLLECTION_NAME
                                + "\" in the change stream event: {}", event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event ({}) of the collection: \""
                                + UserRole.COLLECTION_NAME
                                + "\"",
                        o,
                        throwable))
                .subscribe();
    }

    private Mono<UserRole> addDefaultUserRole() {
        return addUserRole(DEFAULT_USER_ROLE_ID,
                "default",
                Set.of(DEFAULT_GROUP_TYPE_ID),
                Integer.MAX_VALUE,
                Integer.MAX_VALUE,
                Collections.emptyMap()).onErrorComplete(DuplicateKeyException.class);
    }

    public Flux<UserRole> queryUserRoles(@Nullable Integer page, @Nullable Integer size) {
        return userRoleRepository.findAll(page, size);
    }

    public Mono<UserRole> addUserRole(
            @Nullable Long groupId,
            @Nullable String name,
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
            groupId = node.nextLargeGapId(ServiceType.USER_ROLE);
        }
        UserRole userRole = new UserRole(
                groupId,
                name,
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeIdToLimit);
        idToRole.put(groupId, userRole);
        return userRoleRepository.insert(userRole)
                .thenReturn(userRole);
    }

    public Mono<UpdateResult> updateUserRoles(
            @NotEmpty Set<Long> groupIds,
            @Nullable String name,
            @Nullable Set<Long> creatableGroupTypeIds,
            @Nullable Integer ownedGroupLimit,
            @Nullable Integer ownedGroupLimitForEachGroupType,
            @Nullable Map<Long, Integer> groupTypeIdToLimit) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(name,
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeIdToLimit)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return userRoleRepository
                .updateUserRoles(groupIds,
                        name,
                        creatableGroupTypeIds,
                        ownedGroupLimit,
                        ownedGroupLimitForEachGroupType,
                        groupTypeIdToLimit)
                .doOnNext(updateResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long groupId : groupIds) {
                        idToRole.remove(groupId);
                    }
                });
    }

    /**
     * @implNote Note that we don't need to remove corresponding groups in {@link #idToRole} because
     *           their data will be synced in the watch callback.
     */
    public Mono<DeleteResult> deleteUserRoles(@Nullable Set<Long> groupIds) {
        if (groupIds == null) {
            return userRoleRepository.deleteByNotIds(Set.of(DEFAULT_USER_ROLE_ID));
        }
        if (groupIds.contains(DEFAULT_USER_ROLE_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The default user role cannot be deleted"));
        }
        return userRoleRepository.deleteByIds(groupIds)
                .doOnNext(deleteResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long groupId : groupIds) {
                        idToRole.remove(groupId);
                    }
                });
    }

    public Mono<UserRole> queryUserRoleById(@NotNull Long id) {
        try {
            Validator.notNull(id, "id");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        UserRole userRole = idToRole.get(id);
        if (userRole == null) {
            return userRoleRepository.findById(id)
                    .doOnNext(type -> idToRole.put(id, type));
        }
        return Mono.just(userRole);
    }

    public Mono<UserRole> queryStoredOrDefaultUserRoleByUserId(@NotNull Long userId) {
        Mono<Long> userRoleIdMono = userService.queryUserRoleIdByUserId(userId)
                .defaultIfEmpty(DEFAULT_USER_ROLE_ID);
        return userRoleIdMono.flatMap(userRoleId -> queryUserRoleById(userRoleId).switchIfEmpty(
                Mono.error(ResponseException.get(ResponseStatusCode.SERVER_INTERNAL_ERROR,
                        "The role ("
                                + userRoleId
                                + ") of the user ("
                                + userId
                                + ") was not found"))));
    }

    public Mono<Long> countUserRoles() {
        return userRoleRepository.countAll();
    }

}