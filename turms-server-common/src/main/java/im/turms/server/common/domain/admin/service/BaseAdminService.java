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

import com.mongodb.client.model.changestream.FullDocument;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.domain.admin.bo.AdminInfo;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.security.PasswordManager;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminService.class);

    private final PasswordManager passwordManager;
    private final BaseRepository<Admin> adminRepository;
    private final BaseAdminRoleService adminRoleService;

    /**
     * Account -> AdminInfo
     */
    private final Map<String, AdminInfo> adminMap = new ConcurrentHashMap<>();

    protected BaseAdminService(
            PasswordManager passwordManager,
            BaseRepository<Admin> adminRepository,
            BaseAdminRoleService adminRoleService) {
        this.passwordManager = passwordManager;
        this.adminRepository = adminRepository;
        this.adminRoleService = adminRoleService;

        listenAndLoadAdmins();
    }

    public void listenAndLoadAdmins() {
        // Listen
        adminRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    Admin admin = event.getFullDocument();
                    switch (event.getOperationType()) {
                        case INSERT, UPDATE, REPLACE -> adminMap.put(admin.getAccount(), new AdminInfo(admin, null));
                        case DELETE -> {
                            String account = ChangeStreamUtil.getIdAsString(event.getDocumentKey());
                            adminMap.remove(account);
                        }
                        case INVALIDATE -> adminMap.clear();
                        default -> LOGGER.fatal("Detected an illegal operation on Admin collection: " + event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER
                        .error("Caught an error while processing the change stream event of Admin: {}", o, throwable))
                .subscribe();

        // Load
        adminRepository.findAll()
                .collectList()
                .doOnNext(admins -> {
                    for (Admin admin : admins) {
                        adminMap.put(admin.getAccount(), new AdminInfo(admin, null));
                    }
                    boolean rootAdminExists = admins.stream()
                            .anyMatch(admin -> admin.getRoleId().equals(ADMIN_ROLE_ROOT_ID));
                    if (!rootAdminExists) {
                        addRootAdmin()
                                .subscribe(null, t -> LOGGER.error("Caught an error while adding the root admin", t));
                    }
                })
                .subscribe(null, t -> LOGGER.error("Caught an error while finding all admins", t));
    }

    public Mono<Admin> addRootAdmin() {
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
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isAdminAuthorized(
            @NotNull ServerWebExchange exchange,
            @NotNull String account,
            @NotNull AdminPermission permission) {
        try {
            Validator.notNull(exchange, "exchange");
            Validator.notNull(account, "account");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        boolean isQueryingSelfInfo;
        // Even if the account doesn't have the permission ADMIN_QUERY, it can still query its own information
        if (permission == AdminPermission.ADMIN_QUERY) {
            List<String> accounts = exchange.getRequest().getQueryParams().get("accounts");
            isQueryingSelfInfo = accounts != null && accounts.size() == 1 && accounts.get(0).equals(account);
        } else {
            isQueryingSelfInfo = false;
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
        AdminInfo adminInfo = adminMap.get(account);
        if (adminInfo != null && adminInfo.getRawPassword() != null) {
            return Mono.just(adminInfo.getRawPassword().equals(rawPassword));
        }
        return queryAdmin(account)
                .map(admin -> {
                    boolean isValidPassword = passwordManager.matchesAdminPassword(rawPassword, admin.getPassword());
                    if (isValidPassword) {
                        AdminInfo info = adminMap.get(admin.getAccount());
                        if (info != null) {
                            info.setRawPassword(rawPassword);
                        }
                    }
                    return isValidPassword;
                })
                .defaultIfEmpty(false);
    }

    public Mono<Admin> queryAdmin(@NotNull String account) {
        try {
            Validator.notNull(account, "account");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = adminMap.get(account);
        if (adminInfo == null) {
            return adminRepository.findById(account)
                    .doOnNext(admin -> adminMap.put(account, new AdminInfo(admin, null)));
        }
        return Mono.just(adminInfo.getAdmin());
    }

}