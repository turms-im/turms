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

package im.turms.server.common.domain.admin.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.time.DateConst;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.infra.validation.Validator;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminRoleService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminRoleService.class);

    private static final AdminRole ROOT_ROLE = new AdminRole(
            ADMIN_ROLE_ROOT_ID,
            "ROOT",
            AdminPermission.ALL,
            Integer.MAX_VALUE,
            DateConst.EPOCH);

    protected final Map<Long, AdminRole> idToRole = new ConcurrentHashMap<>(16);
    private final BaseRepository<AdminRole, Long> adminRoleRepository;

    protected BaseAdminRoleService(BaseRepository<AdminRole, Long> adminRoleRepository) {
        this.adminRoleRepository = adminRoleRepository;

        loadAndListenRoles();
    }

    public void loadAndListenRoles() {
        // Load
        resetRoles();
        LOGGER.info("Loading all admin roles");
        adminRoleRepository.findAll()
                .doOnNext(role -> idToRole.put(role.getId(), role))
                .onErrorMap(t -> new RuntimeException(
                        "Caught an error while loading all admin roles",
                        t))
                .blockLast(DurationConst.ONE_MINUTE);
        LOGGER.info("Loaded all admin roles");

        // Listen
        adminRoleRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    AdminRole adminRole = event.getFullDocument();
                    switch (event.getOperationType()) {
                        case INSERT, UPDATE, REPLACE -> idToRole.put(adminRole.getId(), adminRole);
                        case DELETE -> {
                            long roleId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            idToRole.remove(roleId);
                        }
                        case INVALIDATE -> resetRoles();
                        default -> LOGGER.fatal("Detected an illegal operation on the collection \""
                                + AdminRole.COLLECTION_NAME
                                + "\" in the event: {}", event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event ({}) of the collection: \""
                                + AdminRole.COLLECTION_NAME
                                + "\"",
                        o,
                        throwable))
                .subscribe();
    }

    public AdminRole getRootRole() {
        return ROOT_ROLE;
    }

    public Flux<AdminRole> queryAndCacheRolesByRoleIds(@NotNull Collection<Long> roleIds) {
        try {
            Validator.notNull(roleIds, "roleIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (roleIds.isEmpty()) {
            return Flux.empty();
        }
        if (roleIds.contains(ADMIN_ROLE_ROOT_ID)) {
            if (roleIds.size() == 1) {
                return Flux.just(getRootRole());
            }
            roleIds = new UnifiedSet<>(roleIds);
            roleIds.remove(ADMIN_ROLE_ROOT_ID);
            return adminRoleRepository.findByIds(roleIds)
                    .doOnNext(role -> idToRole.put(role.getId(), role))
                    .startWith(getRootRole());
        }
        return adminRoleRepository.findByIds(roleIds)
                .doOnNext(role -> idToRole.put(role.getId(), role));
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull Set<Long> roleIds) {
        try {
            Validator.notNull(roleIds, "roleIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        List<Long> cachedRoleIds = null;
        Set<AdminPermission> cachedPermissions = null;
        for (Long roleId : roleIds) {
            AdminRole adminRole = idToRole.get(roleId);
            if (adminRole != null) {
                if (cachedRoleIds == null) {
                    cachedRoleIds = new ArrayList<>(roleIds.size());
                    cachedPermissions = new UnifiedSet<>(50);
                }
                cachedRoleIds.add(roleId);
                cachedPermissions.addAll(adminRole.getPermissions());
            }
        }
        int cachedRoleIdCount = CollectionUtil.getSize(cachedRoleIds);
        if (cachedRoleIdCount == 0) {
            return queryAndCacheRolesByRoleIds(roleIds).collectList()
                    .map(adminRoles -> {
                        if (adminRoles.isEmpty()) {
                            return Collections.emptySet();
                        }
                        Set<AdminPermission> permissions = new UnifiedSet<>(50);
                        for (AdminRole adminRole : adminRoles) {
                            permissions.addAll(adminRole.getPermissions());
                        }
                        return permissions;
                    });
        } else if (cachedRoleIdCount == roleIds.size()) {
            return Mono.just(cachedPermissions);
        } else {
            roleIds = new UnifiedSet<>(roleIds);
            roleIds.removeAll(cachedRoleIds);
            Set<AdminPermission> finalCachedPermissions = cachedPermissions;
            return queryAndCacheRolesByRoleIds(roleIds).collectList()
                    .map(adminRoles -> {
                        if (adminRoles.isEmpty()) {
                            return Collections.emptySet();
                        }
                        Set<AdminPermission> permissions = new UnifiedSet<>(50);
                        for (AdminRole adminRole : adminRoles) {
                            permissions.addAll(adminRole.getPermissions());
                        }
                        permissions.addAll(finalCachedPermissions);
                        return permissions;
                    });
        }
    }

    public Mono<Boolean> hasPermission(
            @NotNull Set<Long> roleIds,
            @NotNull AdminPermission permission) {
        try {
            Validator.notNull(roleIds, "roleIds");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (roleIds.isEmpty()) {
            return PublisherPool.FALSE;
        }
        return queryPermissions(roleIds).map(permissions -> permissions.contains(permission))
                .defaultIfEmpty(false);
    }

    private void resetRoles() {
        idToRole.keySet()
                .removeIf(id -> !id.equals(ADMIN_ROLE_ROOT_ID));
        idToRole.put(ADMIN_ROLE_ROOT_ID, ROOT_ROLE);
    }

}