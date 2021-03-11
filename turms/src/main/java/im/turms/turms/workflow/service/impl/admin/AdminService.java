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

package im.turms.turms.workflow.service.impl.admin;

import com.google.common.collect.Sets;
import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.manager.PasswordManager;
import im.turms.server.common.mongo.IMongoDataGenerator;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.bo.AdminInfo;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.dao.domain.admin.Admin;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Log4j2
@Service
@DependsOn(IMongoDataGenerator.BEAN_NAME)
public class AdminService {

    private static final String ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK =
            "Only a admin with a lower rank compared to the account can be created, updated, or deleted";
    /**
     * Use the hard-coded account because it's immutable.
     */
    public static final String ROOT_ADMIN_ACCOUNT = "turms";
    public static final String ROOT_ADMIN_PASSWORD = "turms";
    private static final int MIN_ACCOUNT_LIMIT = 1;
    private static final int MIN_PASSWORD_LIMIT = 1;
    private static final int MIN_NAME_LIMIT = 1;
    public static final int MAX_ACCOUNT_LIMIT = 32;
    public static final int MAX_PASSWORD_LIMIT = 32;
    public static final int MAX_NAME_LIMIT = 32;
    private final PasswordManager passwordManager;
    private final TurmsMongoClient mongoClient;
    private final AdminRoleService adminRoleService;

    /**
     * Account -> AdminInfo
     */
    private final Map<String, AdminInfo> adminMap = new ConcurrentHashMap<>();

    public AdminService(
            PasswordManager passwordManager,
            @Qualifier("adminMongoClient") TurmsMongoClient mongoClient,
            AdminRoleService adminRoleService) {
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
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            adminMap.put(admin.getAccount(), new AdminInfo(admin, null));
                            break;
                        case DELETE:
                            String account = ChangeStreamUtil.getIdAsString(event.getDocumentKey());
                            adminMap.remove(account);
                            break;
                        case INVALIDATE:
                            adminMap.clear();
                            break;
                        default:
                            log.fatal("Detect an illegal operation on Admin collection: " + event);
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of Admin: {}", o, throwable))
                .subscribe();

        // Load
        mongoClient.findAll(Admin.class)
                .collectList()
                .doOnNext(admins -> {
                    boolean rootAdminExists = false;
                    for (Admin admin : admins) {
                        if (admin.getRoleId().equals(DaoConstant.ADMIN_ROLE_ROOT_ID)) {
                            rootAdminExists = true;
                        }
                        adminMap.put(admin.getAccount(), new AdminInfo(admin, null));
                    }
                    if (!rootAdminExists) {
                        addAdmin(ROOT_ADMIN_ACCOUNT,
                                ROOT_ADMIN_PASSWORD,
                                DaoConstant.ADMIN_ROLE_ROOT_ID,
                                ROOT_ADMIN_ACCOUNT,
                                new Date(),
                                false)
                                .doOnNext(admin -> log.warn("A root admin has been generated: {}", Map.of(
                                        "Account", ROOT_ADMIN_ACCOUNT,
                                        "Raw Password", ROOT_ADMIN_PASSWORD)))
                                .subscribe();
                    }
                })
                .subscribe();
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
            AssertUtil.state(!roleId.equals(DaoConstant.ADMIN_ROLE_ROOT_ID),
                    "The role ID cannot be the root role ID: " + DaoConstant.ADMIN_ROLE_ROOT_ID);
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
        account = account != null
                ? account
                : RandomStringUtils.randomAlphabetic(16);
        String password = StringUtils.hasText(rawPassword)
                ? passwordManager.encodeAdminPassword(rawPassword)
                : passwordManager.encodeAdminPassword(RandomStringUtils.randomAlphabetic(10));
        name = StringUtils.hasText(name)
                ? name
                : RandomStringUtils.randomAlphabetic(8);
        registrationDate = registrationDate != null
                ? registrationDate
                : new Date();
        Admin admin = new Admin(account, password, name, roleId, registrationDate);
        AdminInfo adminInfo = new AdminInfo(admin, rawPassword);
        String finalAccount = account;
        Mono<?> result = upsert
                ? mongoClient.upsert(admin)
                : mongoClient.insert(admin);
        return result.then(Mono.fromCallable(() -> {
            adminMap.put(finalAccount, adminInfo);
            return admin;
        }));
    }

    public Mono<Long> queryRoleId(@NotNull String account) {
        return queryAdmin(account).map(Admin::getRoleId);
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
                : queryAdmins(accounts, null, null, null)
                .map(Admin::getRoleId);
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
        } else {
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
    }

    public Mono<Admin> queryAdmin(@NotNull String account) {
        try {
            AssertUtil.notNull(account, "account");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        AdminInfo adminInfo = adminMap.get(account);
        if (adminInfo != null) {
            return Mono.just(adminInfo.getAdmin());
        } else {
            return mongoClient.findById(Admin.class, account)
                    .doOnNext(admin -> adminMap.put(account, new AdminInfo(admin, null)));
        }
    }

    public Flux<Admin> queryAdmins(
            @Nullable Set<String> accounts,
            @Nullable Set<Long> roleIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter.newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, accounts)
                .inIfNotNull(Admin.Fields.ROLE_ID, roleIds);
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        return mongoClient.findMany(Admin.class, filter);
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
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, accounts);
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
            if (roleId == null) {
                return updateAdmins(targetAccounts, rawPassword, name, null);
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, "It's forbidden to update one's own role ID"));
            }
        } else {
            return adminRoleService.isAdminHigherThanAdmins(requesterAccount, targetAccounts)
                    .flatMap(triple -> {
                        if (triple.getLeft()) {
                            if (roleId != null) {
                                return adminRoleService.queryRankByRole(roleId)
                                        .flatMap(targetRoleRank -> triple.getMiddle() > targetRoleRank
                                                ? updateAdmins(targetAccounts, rawPassword, name, roleId)
                                                : Mono.error(TurmsBusinessException
                                                .get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK)));
                            } else {
                                return updateAdmins(targetAccounts, rawPassword, name, null);
                            }
                        } else {
                            return Mono
                                    .error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ADMIN_WITH_HIGHER_RANK));
                        }
                    });
        }
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
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, targetAccounts);
        String password = rawPassword != null
                ? passwordManager.encodeAdminPassword(rawPassword)
                : null;
        Update update = Update
                .newBuilder()
                .setIfNotNull(Admin.Fields.PASSWORD, password)
                .setIfNotNull(Admin.Fields.NAME, name)
                .setIfNotNull(Admin.Fields.ROLE_ID, roleId);
        return mongoClient.updateMany(Admin.class, filter, update)
                .map(result -> {
                    if (rawPassword != null) {
                        for (String account : targetAccounts) {
                            AdminInfo adminInfo = adminMap.get(account);
                            if (adminInfo != null) {
                                adminInfo.setRawPassword(rawPassword);
                            }
                        }
                    }
                    return result;
                });
    }

    public Mono<Long> countAdmins(@Nullable Set<String> accounts, @Nullable Set<Long> roleIds) {
        Filter filter = Filter.newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, accounts)
                .inIfNotNull(Admin.Fields.ROLE_ID, roleIds);
        return mongoClient.count(Admin.class, filter);
    }

}