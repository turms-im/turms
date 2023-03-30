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

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.time.DateConst;
import im.turms.server.common.infra.validation.Validator;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminRoleService.class);

    private static final AdminRole ROOT_ROLE = new AdminRole(
            ADMIN_ROLE_ROOT_ID,
            "ROOT",
            AdminPermission.ALL,
            Integer.MAX_VALUE,
            DateConst.EPOCH);

    private final Map<Long, AdminRole> idToRole = new ConcurrentHashMap<>(16);
    private final BaseRepository<AdminRole, Long> adminRoleRepository;

    protected BaseAdminRoleService(BaseRepository<AdminRole, Long> adminRoleRepository) {
        this.adminRoleRepository = adminRoleRepository;

        listenAndLoadRoles();
    }

    public void listenAndLoadRoles() {
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

        // Load
        resetRoles();
        adminRoleRepository.findAll()
                .doOnNext(role -> idToRole.put(role.getId(), role))
                .subscribe(null,
                        t -> LOGGER.error("Caught an error while finding all admin roles", t));
    }

    public AdminRole getRootRole() {
        return idToRole.get(ADMIN_ROLE_ROOT_ID);
    }

    public Mono<AdminRole> queryAndCacheRole(@NotNull Long roleId) {
        try {
            Validator.notNull(roleId, "roleId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return roleId.equals(ADMIN_ROLE_ROOT_ID)
                ? Mono.just(getRootRole())
                : adminRoleRepository.findById(roleId)
                        .doOnNext(role -> idToRole.put(roleId, role));
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull Long roleId) {
        try {
            Validator.notNull(roleId, "roleId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminRole role = idToRole.get(roleId);
        return role == null
                ? queryAndCacheRole(roleId).map(AdminRole::getPermissions)
                : Mono.just(role.getPermissions());
    }

    public Mono<Boolean> hasPermission(@NotNull Long roleId, @NotNull AdminPermission permission) {
        try {
            Validator.notNull(roleId, "roleId");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryPermissions(roleId).map(permissions -> permissions.contains(permission))
                .defaultIfEmpty(false);
    }

    private void resetRoles() {
        idToRole.keySet()
                .removeIf(id -> !id.equals(ADMIN_ROLE_ROOT_ID));
        idToRole.put(ADMIN_ROLE_ROOT_ID, ROOT_ROLE);
    }

}