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

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.web.MethodParameterInfo;
import im.turms.server.common.domain.admin.bo.AdminInfo;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.repository.BaseAdminRepository;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminService extends BaseService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminService.class);

    private final PasswordManager passwordManager;
    private final BaseAdminRepository adminRepository;
    private final BaseAdminRoleService adminRoleService;

    private final Map<Long, AdminInfo> idToAdminInfo = new ConcurrentHashMap<>(16);
    private final Map<String, AdminInfo> loginNameToAdminInfo = new ConcurrentHashMap<>(16);

    protected BaseAdminService(
            PasswordManager passwordManager,
            BaseAdminRepository adminRepository,
            BaseAdminRoleService adminRoleService) {
        this.passwordManager = passwordManager;
        this.adminRepository = adminRepository;
        this.adminRoleService = adminRoleService;
    }

    protected void loadAndListenAdmins() {
        // Load
        LOGGER.info("Loading all admins");
        adminRepository.findAll()
                .collect(CollectorUtil.toChunkedList())
                .onErrorMap(
                        t -> new RuntimeException("Caught an error while loading all admins", t))
                .flatMap(admins -> {
                    for (Admin admin : admins) {
                        upsertAdminIntoCache(admin);
                    }
                    for (Admin admin : admins) {
                        if (admin.getRoleIds()
                                .contains(ADMIN_ROLE_ROOT_ID)) {
                            break;
                        }
                    }
                    return addRootAdmin().onErrorMap(t -> new RuntimeException(
                            "Caught an error while adding the root admin",
                            t));
                })
                .block(DurationConst.ONE_MINUTE);
        LOGGER.info("Loaded all admins");

        // Listen
        adminRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    Admin admin = event.getFullDocument();
                    switch (event.getOperationType()) {
                        case INSERT, UPDATE, REPLACE -> upsertAdminIntoCache(admin);
                        case DELETE -> {
                            Long id = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            removeAdminFromCache(id);
                        }
                        case INVALIDATE -> {
                            synchronized (this) {
                                idToAdminInfo.clear();
                                loginNameToAdminInfo.clear();
                            }
                        }
                        default -> LOGGER.fatal("Detected an illegal operation on the collection \""
                                + Admin.COLLECTION_NAME
                                + "\" in the change stream event: {}", event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event ({}) of the collection: \""
                                + Admin.COLLECTION_NAME
                                + "\"",
                        o,
                        throwable))
                .subscribe();
    }

    private void upsertAdminIntoCache(Admin admin) {
        Long id = admin.getId();
        String loginName = admin.getLoginName();
        if (id == null || loginName == null) {
            LOGGER.error("Found an invalid admin record missing the ID or login name: {}", admin);
        } else {
            AdminInfo adminInfo = new AdminInfo(admin, null);
            synchronized (this) {
                idToAdminInfo.put(id, adminInfo);
                loginNameToAdminInfo.put(loginName, adminInfo);
            }
        }
    }

    private synchronized void removeAdminFromCache(Long id) {
        AdminInfo adminInfo = idToAdminInfo.remove(id);
        if (adminInfo != null) {
            loginNameToAdminInfo.remove(adminInfo.getAdmin()
                    .getLoginName());
        }
    }

    protected Mono<Admin> addRootAdmin() {
        return Mono.empty();
    }

    public Mono<Set<Long>> queryRoleIdsByAdminId(@NotNull Long id) {
        return queryAdminById(id).map(Admin::getRoleIds);
    }

    public Mono<Boolean> isAdminAuthorized(@NotNull Long id, @NotNull AdminPermission permission) {
        try {
            Validator.notNull(id, "id");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryRoleIdsByAdminId(id)
                .flatMap(roleIds -> adminRoleService.hasPermission(roleIds, permission))
                .switchIfEmpty(PublisherPool.FALSE);
    }

    public Mono<Boolean> isAdminAuthorized(
            @NotNull MethodParameterInfo[] params,
            @NotNull Object[] paramValues,
            @NotNull Long id,
            @NotNull AdminPermission permission) {
        try {
            Validator.notNull(params, "params");
            Validator.notNull(paramValues, "paramValues");
            Validator.notNull(id, "id");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        boolean isQueryingSelfInfo = false;
        // Even if the admin doesn't have the permission ADMIN_QUERY,
        // it can still query their own information.
        if (permission == AdminPermission.ADMIN_QUERY) {
            for (int i = 0, length = params.length; i < length; i++) {
                MethodParameterInfo param = params[i];
                if (param.name()
                        .equals("ids")) {
                    Object value = paramValues[i];
                    if (value instanceof Collection<?> collection
                            && collection.size() == 1
                            && collection.iterator()
                                    .next()
                                    .equals(id)) {
                        isQueryingSelfInfo = true;
                    }
                    break;
                }
            }
        }
        return isQueryingSelfInfo
                ? PublisherPool.TRUE
                : isAdminAuthorized(id, permission);
    }

    /**
     * @return admin ID if authenticated.
     */
    public Mono<Long> authenticate(
            @NotNull @NoWhitespace String loginName,
            @NotNull @NoWhitespace String rawPassword) {
        try {
            Validator.notNull(loginName, "loginName");
            Validator.noWhitespace(loginName, "loginName");
            Validator.notNull(rawPassword, "rawPassword");
            Validator.noWhitespace(rawPassword, "rawPassword");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = loginNameToAdminInfo.get(loginName);
        if (adminInfo != null) {
            String correctRawPassword = adminInfo.getRawPassword();
            // If the password doesn't match, it may happen due to the outdated cache,
            // so compare the input password with the one stored in MongoDB.
            if (correctRawPassword != null && correctRawPassword.equals(rawPassword)) {
                return Mono.just(adminInfo.getAdmin()
                        .getId());
            }
        }
        return queryAdminByLoginName(loginName).flatMap(admin -> {
            boolean isValidPassword =
                    passwordManager.matchesAdminPassword(rawPassword, admin.getPassword());
            if (!isValidPassword) {
                return Mono.empty();
            }
            AdminInfo info = idToAdminInfo.get(admin.getId());
            if (info != null) {
                info.setRawPassword(rawPassword);
            }
            return Mono.just(admin.getId());
        });
    }

    public Mono<Admin> queryAdminById(@NotNull Long id) {
        try {
            Validator.notNull(id, "id");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = idToAdminInfo.get(id);
        if (adminInfo == null) {
            return adminRepository.findById(id)
                    .doOnNext(this::upsertAdminIntoCache);
        }
        return Mono.just(adminInfo.getAdmin());
    }

    public Mono<Admin> queryAdminByLoginName(@NotNull String loginName) {
        try {
            Validator.notNull(loginName, "loginName");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = loginNameToAdminInfo.get(loginName);
        if (adminInfo == null) {
            return adminRepository.findByLoginName(loginName)
                    .doOnNext(this::upsertAdminIntoCache);
        }
        return Mono.just(adminInfo.getAdmin());
    }

}