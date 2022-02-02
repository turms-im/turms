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

package im.turms.server.common.service.admin;

import com.mongodb.client.model.changestream.FullDocument;
import im.turms.server.common.access.http.permission.AdminPermission;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.dao.domain.AdminRole;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.util.AssertUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.server.common.constant.BusinessConstant.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminRoleService.class);

    private static final AdminRole ROOT_ROLE =
            new AdminRole(ADMIN_ROLE_ROOT_ID, "ROOT", AdminPermission.ALL, Integer.MAX_VALUE);

    private final Map<Long, AdminRole> roles = new ConcurrentHashMap<>(16);
    private final TurmsMongoClient mongoClient;

    protected BaseAdminRoleService(@Qualifier("adminMongoClient") TurmsMongoClient mongoClient) {
        this.mongoClient = mongoClient;

        listenAndLoadRoles();
    }

    public void listenAndLoadRoles() {
        mongoClient.watch(AdminRole.class, FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    AdminRole adminRole = event.getFullDocument();
                    switch (event.getOperationType()) {
                        case INSERT, UPDATE, REPLACE -> roles.put(adminRole.getId(), adminRole);
                        case DELETE -> {
                            long roleId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            roles.remove(roleId);
                        }
                        case INVALIDATE -> resetRoles();
                        default -> LOGGER.fatal("Detected an illegal operation on AdminRole collection: " + event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER
                        .error("Caught an error while processing the change stream event of AdminRole: {}", o, throwable))
                .subscribe();

        // Load
        resetRoles();
        mongoClient.findAll(AdminRole.class)
                .doOnNext(role -> roles.put(role.getId(), role))
                .subscribe(null, t -> LOGGER.error("Caught an error while find all admin roles", t));
    }

    public AdminRole getRootRole() {
        return roles.get(ADMIN_ROLE_ROOT_ID);
    }

    public Mono<AdminRole> queryAndCacheRole(@NotNull Long roleId) {
        try {
            AssertUtil.notNull(roleId, "roleId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return roleId.equals(ADMIN_ROLE_ROOT_ID)
                ? Mono.just(getRootRole())
                : mongoClient.findById(AdminRole.class, roleId)
                .doOnNext(role -> roles.put(roleId, role));
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull Long roleId) {
        try {
            AssertUtil.notNull(roleId, "roleId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        AdminRole role = roles.get(roleId);
        return role == null
                ? queryAndCacheRole(roleId).map(AdminRole::getPermissions)
                : Mono.just(role.getPermissions());
    }

    public Mono<Boolean> hasPermission(@NotNull Long roleId, @NotNull AdminPermission permission) {
        try {
            AssertUtil.notNull(roleId, "roleId");
            AssertUtil.notNull(permission, "permission");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryPermissions(roleId)
                .map(permissions -> permissions.contains(permission))
                .defaultIfEmpty(false);
    }

    private void resetRoles() {
        roles.keySet().removeIf(id -> !id.equals(ADMIN_ROLE_ROOT_ID));
        roles.put(ADMIN_ROLE_ROOT_ID, ROOT_ROLE);
    }

}