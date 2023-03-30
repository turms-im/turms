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

package im.turms.service.domain.admin.service;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.bo.AdminInfo;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.security.password.PasswordManager;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.service.domain.admin.repository.AdminRepository;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;
import static im.turms.server.common.domain.admin.constant.AdminConst.ROOT_ADMIN_ACCOUNT;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class AdminService extends BaseAdminService {

    private static final String ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK =
            "Only an admin with a lower rank compared to the account can be created, updated, or deleted";
    private static final int MIN_ACCOUNT_LIMIT = 1;
    private static final int MIN_PASSWORD_LIMIT = 1;
    private static final int MIN_NAME_LIMIT = 1;
    public static final int MAX_ACCOUNT_LIMIT = 32;
    public static final int MAX_PASSWORD_LIMIT = 32;
    public static final int MAX_NAME_LIMIT = 32;
    private final PasswordManager passwordManager;
    private final TurmsPropertiesManager propertiesManager;
    private final AdminRepository adminRepository;
    private final AdminRoleService adminRoleService;

    private final Map<String, AdminInfo> accountToAdmin = new ConcurrentHashMap<>();

    public AdminService(
            PasswordManager passwordManager,
            TurmsPropertiesManager propertiesManager,
            AdminRepository adminRepository,
            AdminRoleService adminRoleService) {
        super(passwordManager, adminRepository, adminRoleService);
        this.passwordManager = passwordManager;
        this.propertiesManager = propertiesManager;
        this.adminRepository = adminRepository;
        this.adminRoleService = adminRoleService;
        listenAndLoadAdmins();
    }

    public Flux<Long> queryRoleIds(@NotEmpty Set<String> accounts) {
        try {
            Validator.notEmpty(accounts, "accounts");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        Set<Long> roleIds = CollectionUtil.newSetWithExpectedSize(accounts.size());
        for (String account : accounts) {
            AdminInfo adminInfo = accountToAdmin.get(account);
            if (adminInfo != null) {
                roleIds.add(adminInfo.getAdmin()
                        .getRoleId());
            }
        }
        return roleIds.size() == accounts.size()
                ? Flux.fromIterable(roleIds)
                : queryAdmins(accounts, null, null, null).map(Admin::getRoleId);
    }

    public Mono<Admin> authAndAddAdmin(
            @NotNull String requesterAccount,
            @Nullable @NoWhitespace @Size(
                    min = MIN_ACCOUNT_LIMIT,
                    max = MAX_ACCOUNT_LIMIT) String account,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Long roleId,
            @Nullable @NoWhitespace @Size(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        try {
            Validator.notNull(requesterAccount, "requesterAccount");
            Validator.noWhitespace(account, "account");
            Validator.length(account, "account", MIN_ACCOUNT_LIMIT, MAX_ACCOUNT_LIMIT);
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.noWhitespace(name, "name");
            Validator.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
            Validator.pastOrPresent(registrationDate, "registrationDate");
            Validator.notNull(roleId, "roleId");
            Validator.notEquals(roleId,
                    ADMIN_ROLE_ROOT_ID,
                    "The role ID cannot be the root role ID: "
                            + ADMIN_ROLE_ROOT_ID);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminRoleService.isAdminHigherThanRole(requesterAccount, roleId)
                .flatMap(isHigher -> isHigher
                        ? addAdmin(account, rawPassword, roleId, name, registrationDate, upsert)
                        : Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
    }

    public Mono<Admin> addAdmin(
            @Nullable @NoWhitespace @Size(
                    min = MIN_ACCOUNT_LIMIT,
                    max = MAX_ACCOUNT_LIMIT) String account,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Long roleId,
            @Nullable @NoWhitespace @Size(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        try {
            Validator.noWhitespace(account, "account");
            Validator.length(account, "account", MIN_ACCOUNT_LIMIT, MAX_ACCOUNT_LIMIT);
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.notNull(roleId, "roleId");
            Validator.noWhitespace(name, "name");
            Validator.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
            Validator.pastOrPresent(registrationDate, "registrationDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        account = account == null
                ? RandomStringUtils.randomAlphabetic(16)
                : account;
        byte[] password = StringUtils.hasText(rawPassword)
                ? passwordManager.encodeAdminPassword(rawPassword)
                : passwordManager.encodeAdminPassword(RandomStringUtils.randomAlphabetic(10));
        name = StringUtils.hasText(name)
                ? name
                : RandomStringUtils.randomAlphabetic(8);
        registrationDate = registrationDate == null
                ? new Date()
                : registrationDate;
        Admin admin = new Admin(account, password, name, roleId, registrationDate);
        AdminInfo adminInfo = new AdminInfo(admin, rawPassword);
        String finalAccount = account;
        Mono<Void> result = upsert
                ? adminRepository.upsert(admin)
                : adminRepository.insert(admin);
        return result.then(Mono.fromCallable(() -> {
            accountToAdmin.put(finalAccount, adminInfo);
            return admin;
        }));
    }

    protected Mono<Admin> addRootAdmin() {
        return addAdmin(ROOT_ADMIN_ACCOUNT,
                propertiesManager.getLocalProperties()
                        .getSecurity()
                        .getPassword()
                        .getInitialRootPassword(),
                ADMIN_ROLE_ROOT_ID,
                ROOT_ADMIN_ACCOUNT,
                new Date(),
                false).onErrorComplete(DuplicateKeyException.class);
    }

    public Flux<Admin> queryAdmins(
            @Nullable Set<String> accounts,
            @Nullable Set<Long> roleIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        return adminRepository.findAdmins(accounts, roleIds, page, size);
    }

    public Mono<DeleteResult> authAndDeleteAdmins(
            @NotNull String requesterAccount,
            @NotEmpty Set<String> accounts) {
        try {
            Validator.notNull(requesterAccount, "requesterAccount");
            Validator.notEmpty(accounts, "accounts");
            Validator.notContains(accounts,
                    ROOT_ADMIN_ACCOUNT,
                    "The root admin is reserved and cannot be deleted");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminRoleService.isAdminHigherThanAdmins(requesterAccount, accounts)
                .flatMap(triple -> triple.getLeft()
                        ? deleteAdmins(accounts)
                        : Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
    }

    private Mono<DeleteResult> deleteAdmins(@NotEmpty Set<String> accounts) {
        try {
            Validator.notEmpty(accounts, "accounts");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminRepository.deleteByIds(accounts)
                .map(result -> {
                    for (String account : accounts) {
                        accountToAdmin.remove(account);
                    }
                    return result;
                });
    }

    public Mono<UpdateResult> authAndUpdateAdmins(
            @NotNull String requesterAccount,
            @NotEmpty Set<String> targetAccounts,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @NoWhitespace @Size(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable Long roleId) {
        try {
            Validator.notNull(requesterAccount, "requesterAccount");
            Validator.notEmpty(targetAccounts, "targetAccounts");
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.noWhitespace(name, "name");
            Validator.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(rawPassword, name, roleId)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        boolean onlyUpdateRequesterInfo = targetAccounts.size() == 1
                && targetAccounts.iterator()
                        .next()
                        .equals(requesterAccount);
        if (onlyUpdateRequesterInfo) {
            return roleId == null
                    ? updateAdmins(targetAccounts, rawPassword, name, null)
                    : Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                            "It is forbidden to update one's own role ID"));
        }
        return adminRoleService.isAdminHigherThanAdmins(requesterAccount, targetAccounts)
                .flatMap(triple -> {
                    if (!triple.getLeft()) {
                        return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK));
                    }
                    if (roleId == null) {
                        return updateAdmins(targetAccounts, rawPassword, name, null);
                    }
                    return adminRoleService.queryRankByRole(roleId)
                            .flatMap(targetRoleRank -> triple.getMiddle() > targetRoleRank
                                    ? updateAdmins(targetAccounts, rawPassword, name, roleId)
                                    : Mono.error(
                                            ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                                    ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
                });
    }

    public Mono<UpdateResult> updateAdmins(
            @NotEmpty Set<String> targetAccounts,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @NoWhitespace @Size(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable Long roleId) {
        try {
            Validator.notEmpty(targetAccounts, "targetAccounts");
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.noWhitespace(name, "name");
            Validator.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(rawPassword, name, roleId)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        byte[] password = rawPassword == null
                ? null
                : passwordManager.encodeAdminPassword(rawPassword);
        return adminRepository.updateAdmins(targetAccounts, password, name, roleId);
    }

    public Mono<Long> countAdmins(@Nullable Set<String> accounts, @Nullable Set<Long> roleIds) {
        return adminRepository.countAdmins(accounts, roleIds);
    }

}