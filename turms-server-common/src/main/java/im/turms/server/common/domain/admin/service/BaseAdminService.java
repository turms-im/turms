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
import java.util.concurrent.ConcurrentHashMap;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.web.MethodParameterInfo;
import im.turms.server.common.domain.admin.bo.AdminInfo;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminService.class);

    private final PasswordManager passwordManager;
    private final BaseRepository<Admin, String> adminRepository;
    private final BaseAdminRoleService adminRoleService;

    private final Map<String, AdminInfo> accountToAdmin = new ConcurrentHashMap<>();

    protected BaseAdminService(
            PasswordManager passwordManager,
            BaseRepository<Admin, String> adminRepository,
            BaseAdminRoleService adminRoleService) {
        this.passwordManager = passwordManager;
        this.adminRepository = adminRepository;
        this.adminRoleService = adminRoleService;
    }

    protected void listenAndLoadAdmins() {
        // Listen
        adminRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    Admin admin = event.getFullDocument();
                    switch (event.getOperationType()) {
                        case INSERT, UPDATE, REPLACE ->
                            accountToAdmin.put(admin.getAccount(), new AdminInfo(admin, null));
                        case DELETE -> {
                            String account = ChangeStreamUtil.getIdAsString(event.getDocumentKey());
                            accountToAdmin.remove(account);
                        }
                        case INVALIDATE -> accountToAdmin.clear();
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

        // Load
        adminRepository.findAll()
                .collect(CollectorUtil.toChunkedList())
                .doOnNext(admins -> {
                    for (Admin admin : admins) {
                        accountToAdmin.put(admin.getAccount(), new AdminInfo(admin, null));
                    }
                    for (Admin admin : admins) {
                        if (admin.getRoleId()
                                .equals(ADMIN_ROLE_ROOT_ID)) {
                            break;
                        }
                    }
                    addRootAdmin().subscribe(null,
                            t -> LOGGER.error("Caught an error while adding the root admin", t));
                })
                .subscribe(null, t -> LOGGER.error("Caught an error while finding all admins", t));
    }

    protected Mono<Admin> addRootAdmin() {
        return Mono.empty();
    }

    public Mono<Long> queryRoleId(@NotNull String account) {
        return queryAdmin(account).map(Admin::getRoleId);
    }

    public Mono<Boolean> isAdminAuthorized(
            @NotNull String account,
            @NotNull AdminPermission permission) {
        try {
            Validator.notNull(account, "account");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryRoleId(account)
                .flatMap(roleId -> adminRoleService.hasPermission(roleId, permission))
                .switchIfEmpty(PublisherPool.FALSE);
    }

    public Mono<Boolean> isAdminAuthorized(
            @NotNull MethodParameterInfo[] params,
            @NotNull Object[] paramValues,
            @NotNull String account,
            @NotNull AdminPermission permission) {
        try {
            Validator.notNull(params, "params");
            Validator.notNull(paramValues, "paramValues");
            Validator.notNull(account, "account");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        boolean isQueryingSelfInfo = false;
        // Even if the account doesn't have the permission ADMIN_QUERY,
        // it can still query its own information
        if (permission == AdminPermission.ADMIN_QUERY) {
            for (int i = 0, length = params.length; i < length; i++) {
                MethodParameterInfo param = params[i];
                if (param.name()
                        .equals("accounts")) {
                    Object value = paramValues[i];
                    if (value instanceof Collection<?> collection
                            && collection.size() == 1
                            && collection.iterator()
                                    .next()
                                    .equals(account)) {
                        isQueryingSelfInfo = true;
                    }
                    break;
                }
            }
        }
        return isQueryingSelfInfo
                ? PublisherPool.TRUE
                : isAdminAuthorized(account, permission);
    }

    public Mono<Boolean> authenticate(
            @NotNull @NoWhitespace String account,
            @NotNull @NoWhitespace String rawPassword) {
        try {
            Validator.notNull(account, "account");
            Validator.noWhitespace(account, "account");
            Validator.notNull(rawPassword, "rawPassword");
            Validator.noWhitespace(rawPassword, "rawPassword");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = accountToAdmin.get(account);
        if (adminInfo != null && adminInfo.getRawPassword() != null) {
            return Mono.just(adminInfo.getRawPassword()
                    .equals(rawPassword));
        }
        return queryAdmin(account).map(admin -> {
            boolean isValidPassword =
                    passwordManager.matchesAdminPassword(rawPassword, admin.getPassword());
            if (isValidPassword) {
                AdminInfo info = accountToAdmin.get(admin.getAccount());
                if (info != null) {
                    info.setRawPassword(rawPassword);
                }
            }
            return isValidPassword;
        })
                .switchIfEmpty(PublisherPool.FALSE);
    }

    public Mono<Admin> queryAdmin(@NotNull String account) {
        try {
            Validator.notNull(account, "account");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = accountToAdmin.get(account);
        if (adminInfo == null) {
            return adminRepository.findById(account)
                    .doOnNext(admin -> accountToAdmin.put(account, new AdminInfo(admin, null)));
        }
        return Mono.just(adminInfo.getAdmin());
    }

}