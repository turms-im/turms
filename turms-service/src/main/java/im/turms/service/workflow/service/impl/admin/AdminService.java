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

package im.turms.service.workflow.service.impl.admin;

import com.google.common.collect.Sets;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.util.Validator;
import im.turms.server.common.bo.admin.AdminInfo;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.dao.domain.Admin;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.DomainFieldName;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.security.PasswordManager;
import im.turms.server.common.service.admin.BaseAdminService;
import im.turms.server.common.util.AssertUtil;
import im.turms.service.constant.OperationResultConstant;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.server.common.constant.BusinessConstant.ADMIN_ROLE_ROOT_ID;
import static im.turms.server.common.constant.BusinessConstant.ROOT_ADMIN_ACCOUNT;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class AdminService extends BaseAdminService {

    private static final String ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK =
            "Only a admin with a lower rank compared to the account can be created, updated, or deleted";
    private static final int MIN_ACCOUNT_LIMIT = 1;
    private static final int MIN_PASSWORD_LIMIT = 1;
    private static final int MIN_NAME_LIMIT = 1;
    public static final int MAX_ACCOUNT_LIMIT = 32;
    public static final int MAX_PASSWORD_LIMIT = 32;
    public static final int MAX_NAME_LIMIT = 32;
    private final PasswordManager passwordManager;
    private final TurmsMongoClient mongoClient;
    private final TurmsPropertiesManager turmsPropertiesManager;
    private final AdminRoleService adminRoleService;

    /**
     * Account -> AdminInfo
     */
    private final Map<String, AdminInfo> adminMap = new ConcurrentHashMap<>();

    public AdminService(
            PasswordManager passwordManager,
            @Qualifier("adminMongoClient") TurmsMongoClient mongoClient,
            TurmsPropertiesManager turmsPropertiesManager,
            AdminRoleService adminRoleService) {
        super(passwordManager, mongoClient, adminRoleService);
        this.passwordManager = passwordManager;
        this.mongoClient = mongoClient;
        this.turmsPropertiesManager = turmsPropertiesManager;
        this.adminRoleService = adminRoleService;
    }

    public Flux<Long> queryRoleIds(@NotEmpty Set<String> accounts) {
        try {
            AssertUtil.notEmpty(accounts, "accounts");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        Set<Long> roleIds = Sets.newHashSetWithExpectedSize(accounts.size());
        for (String account : accounts) {
            AdminInfo adminInfo = adminMap.get(account);
            if (adminInfo != null) {
                roleIds.add(adminInfo.getAdmin().getRoleId());
            }
        }
        return roleIds.size() == accounts.size()
                ? Flux.fromIterable(roleIds)
                : queryAdmins(accounts, null, null, null).map(Admin::getRoleId);
    }

    public Mono<Admin> authAndAddAdmin(
            @NotNull String requesterAccount,
            @Nullable @NoWhitespace @Length(min = MIN_ACCOUNT_LIMIT, max = MAX_ACCOUNT_LIMIT) String account,
            @Nullable @NoWhitespace @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Long roleId,
            @Nullable @NoWhitespace @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        try {
            AssertUtil.notNull(requesterAccount, "requesterAccount");
            AssertUtil.noWhitespace(account, "account");
            AssertUtil.length(account, "account", MIN_ACCOUNT_LIMIT, MAX_ACCOUNT_LIMIT);
            AssertUtil.noWhitespace(rawPassword, "rawPassword");
            AssertUtil.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
            AssertUtil.pastOrPresent(registrationDate, "registrationDate");
            AssertUtil.notNull(roleId, "roleId");
            AssertUtil.state(!roleId.equals(ADMIN_ROLE_ROOT_ID),
                    "The role ID cannot be the root role ID: " + ADMIN_ROLE_ROOT_ID);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return adminRoleService.isAdminHigherThanRole(requesterAccount, roleId)
                .flatMap(isHigher -> isHigher
                        ? addAdmin(account, rawPassword, roleId, name, registrationDate, upsert)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
    }

    public Mono<Admin> addAdmin(
            @Nullable @NoWhitespace @Length(min = MIN_ACCOUNT_LIMIT, max = MAX_ACCOUNT_LIMIT) String account,
            @Nullable @NoWhitespace @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Long roleId,
            @Nullable @NoWhitespace @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        try {
            AssertUtil.noWhitespace(account, "account");
            AssertUtil.length(account, "account", MIN_ACCOUNT_LIMIT, MAX_ACCOUNT_LIMIT);
            AssertUtil.noWhitespace(rawPassword, "rawPassword");
            AssertUtil.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            AssertUtil.notNull(roleId, "roleId");
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
            AssertUtil.pastOrPresent(registrationDate, "registrationDate");
        } catch (TurmsBusinessException e) {
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
                ? mongoClient.upsert(admin)
                : mongoClient.insert(admin);
        return result.then(Mono.fromCallable(() -> {
            adminMap.put(finalAccount, adminInfo);
            return admin;
        }));
    }

    public Mono<Admin> addRootAdmin() {
        return addAdmin(ROOT_ADMIN_ACCOUNT,
                turmsPropertiesManager.getLocalProperties().getSecurity().getPassword().getInitialRootPassword(),
                ADMIN_ROLE_ROOT_ID,
                ROOT_ADMIN_ACCOUNT,
                new Date(),
                false);
    }

    public Flux<Admin> queryAdmins(
            @Nullable Set<String> accounts,
            @Nullable Set<Long> roleIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(DomainFieldName.ID, accounts)
                .inIfNotNull(Admin.Fields.ROLE_ID, roleIds);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(Admin.class, filter, options);
    }

    public Mono<DeleteResult> authAndDeleteAdmins(
            @NotNull String requesterAccount,
            @NotEmpty Set<String> accounts) {
        try {
            AssertUtil.notNull(requesterAccount, "requesterAccount");
            AssertUtil.notEmpty(accounts, "accounts");
            AssertUtil.state(!accounts.contains(ROOT_ADMIN_ACCOUNT), "The root admin is reserved and cannot be deleted");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return adminRoleService.isAdminHigherThanAdmins(requesterAccount, accounts)
                .flatMap(triple -> triple.getLeft()
                        ? deleteAdmins(accounts)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
    }

    private Mono<DeleteResult> deleteAdmins(@NotEmpty Set<String> accounts) {
        try {
            AssertUtil.notEmpty(accounts, "accounts");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, accounts);
        return mongoClient.deleteMany(Admin.class, filter)
                .map(result -> {
                    for (String account : accounts) {
                        adminMap.remove(account);
                    }
                    return result;
                });
    }

    public Mono<UpdateResult> authAndUpdateAdmins(
            @NotNull String requesterAccount,
            @NotEmpty Set<String> targetAccounts,
            @Nullable @NoWhitespace @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @NoWhitespace @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable Long roleId) {
        try {
            AssertUtil.notNull(requesterAccount, "requesterAccount");
            AssertUtil.notEmpty(targetAccounts, "targetAccounts");
            AssertUtil.noWhitespace(rawPassword, "rawPassword");
            AssertUtil.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(rawPassword, name, roleId)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        boolean onlyUpdateRequesterInfo = targetAccounts.size() == 1 && targetAccounts.iterator().next().equals(requesterAccount);
        if (onlyUpdateRequesterInfo) {
            return roleId == null
                    ? updateAdmins(targetAccounts, rawPassword, name, null)
                    : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, "It's forbidden to update one's own role ID"));
        }
        return adminRoleService.isAdminHigherThanAdmins(requesterAccount, targetAccounts)
                .flatMap(triple -> {
                    if (!triple.getLeft()) {
                        return Mono
                                .error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK));
                    }
                    if (roleId == null) {
                        return updateAdmins(targetAccounts, rawPassword, name, null);
                    }
                    return adminRoleService.queryRankByRole(roleId)
                            .flatMap(targetRoleRank -> triple.getMiddle() > targetRoleRank
                                    ? updateAdmins(targetAccounts, rawPassword, name, roleId)
                                    : Mono.error(TurmsBusinessException
                                    .get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
                });
    }

    public Mono<UpdateResult> updateAdmins(
            @NotEmpty Set<String> targetAccounts,
            @Nullable @NoWhitespace @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @NoWhitespace @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable Long roleId) {
        try {
            AssertUtil.notEmpty(targetAccounts, "targetAccounts");
            AssertUtil.noWhitespace(rawPassword, "rawPassword");
            AssertUtil.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.length(name, "name", MIN_NAME_LIMIT, MAX_NAME_LIMIT);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(rawPassword, name, roleId)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, targetAccounts);
        byte[] password = rawPassword == null
                ? null
                : passwordManager.encodeAdminPassword(rawPassword);
        Update update = Update.newBuilder(3)
                .setIfNotNull(Admin.Fields.PASSWORD, password)
                .setIfNotNull(Admin.Fields.NAME, name)
                .setIfNotNull(Admin.Fields.ROLE_ID, roleId);
        return mongoClient.updateMany(Admin.class, filter, update)
                .map(result -> {
                    if (rawPassword == null) {
                        return result;
                    }
                    for (String account : targetAccounts) {
                        AdminInfo adminInfo = adminMap.get(account);
                        if (adminInfo != null) {
                            adminInfo.setRawPassword(rawPassword);
                        }
                    }
                    return result;
                });
    }

    public Mono<Long> countAdmins(@Nullable Set<String> accounts, @Nullable Set<Long> roleIds) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(DomainFieldName.ID, accounts)
                .inIfNotNull(Admin.Fields.ROLE_ID, roleIds);
        return mongoClient.count(Admin.class, filter);
    }

}