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
import im.turms.server.common.bo.admin.AdminInfo;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.dao.domain.Admin;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.security.PasswordManager;
import im.turms.server.common.util.AssertUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.server.common.constant.BusinessConstant.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
public abstract class BaseAdminService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseAdminService.class);

    private final PasswordManager passwordManager;
    private final TurmsMongoClient mongoClient;
    private final BaseAdminRoleService adminRoleService;

    /**
     * Account -> AdminInfo
     */
    private final Map<String, AdminInfo> adminMap = new ConcurrentHashMap<>();

    protected BaseAdminService(
            PasswordManager passwordManager,
            @Qualifier("adminMongoClient") TurmsMongoClient mongoClient,
            BaseAdminRoleService adminRoleService) {
        this.passwordManager = passwordManager;
        this.mongoClient = mongoClient;
        this.adminRoleService = adminRoleService;

        listenAndLoadAdmins();
    }

    public void listenAndLoadAdmins() {
        // Listen
        mongoClient.watch(Admin.class, FullDocument.UPDATE_LOOKUP)
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
        mongoClient.findAll(Admin.class)
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
            AssertUtil.notNull(account, "account");
            AssertUtil.notNull(permission, "permission");
        } catch (TurmsBusinessException e) {
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
            AssertUtil.notNull(exchange, "exchange");
            AssertUtil.notNull(account, "account");
            AssertUtil.notNull(permission, "permission");
        } catch (TurmsBusinessException e) {
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
                ? Mono.just(true)
                : isAdminAuthorized(account, permission);
    }

    public Mono<Boolean> authenticate(
            @NotNull @NoWhitespace String account,
            @NotNull @NoWhitespace String rawPassword) {
        try {
            AssertUtil.notNull(account, "account");
            AssertUtil.noWhitespace(account, "account");
            AssertUtil.notNull(rawPassword, "rawPassword");
            AssertUtil.noWhitespace(rawPassword, "rawPassword");
        } catch (TurmsBusinessException e) {
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
            AssertUtil.notNull(account, "account");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = adminMap.get(account);
        if (adminInfo == null) {
            return mongoClient.findById(Admin.class, account)
                    .doOnNext(admin -> adminMap.put(account, new AdminInfo(admin, null)));
        }
        return Mono.just(adminInfo.getAdmin());
    }

}