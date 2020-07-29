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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.constraint.NoWhitespaceConstraint;
import im.turms.server.common.manager.PasswordManager;
import im.turms.turms.bo.AdminInfo;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.Admin;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;
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

/**
 * @author James Chen
 */
@Log4j2
@Service
@Validated
public class AdminService {

    /**
     * Use the hard-coded account because it's forbidden to update the account of admins.
     */
    public static final String ROOT_ADMIN_ACCOUNT = "turms";
    public static final String ROOT_ADMIN_PASSWORD = "turms";
    //Account -> AdminInfo
    private static final Map<String, AdminInfo> ADMIN_MAP = new ConcurrentHashMap<>();
    private static final int MIN_ACCOUNT_LIMIT = 1;
    private static final int MIN_PASSWORD_LIMIT = 1;
    private static final int MIN_NAME_LIMIT = 1;
    public static final int MAX_ACCOUNT_LIMIT = 32;
    public static final int MAX_PASSWORD_LIMIT = 32;
    public static final int MAX_NAME_LIMIT = 32;
    private final PasswordManager passwordManager;
    private final ReactiveMongoTemplate mongoTemplate;
    private final AdminRoleService adminRoleService;

    public AdminService(
            PasswordManager passwordManager,
            @Qualifier("adminMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            AdminRoleService adminRoleService) {
        this.passwordManager = passwordManager;
        this.mongoTemplate = mongoTemplate;
        this.adminRoleService = adminRoleService;

        listenAndLoadAdmins();
    }

    public void listenAndLoadAdmins() {
        // Listen
        mongoTemplate.changeStream(Admin.class)
                .withOptions(ChangeStreamOptions.ChangeStreamOptionsBuilder::returnFullDocumentOnUpdate)
                .watchCollection(Admin.class)
                .listen()
                .doOnNext(event -> {
                    Admin admin = event.getBody();
                    switch (event.getOperationType()) {
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            ADMIN_MAP.put(admin.getAccount(), new AdminInfo(admin, null));
                            break;
                        case DELETE:
                            String account = ChangeStreamUtil.getIdAsString(event);
                            ADMIN_MAP.remove(account);
                            break;
                        case INVALIDATE:
                            ADMIN_MAP.clear();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + event.getOperationType());
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of Admin: {}", o, throwable))
                .subscribe();

        // Load
        mongoTemplate.find(new Query(), Admin.class, Admin.COLLECTION_NAME)
                .collectList()
                .doOnNext(admins -> {
                    boolean rootAdminExists = false;
                    for (Admin admin : admins) {
                        if (admin.getRoleId().equals(DaoConstant.ADMIN_ROLE_ROOT_ID)) {
                            rootAdminExists = true;
                        }
                        ADMIN_MAP.put(admin.getAccount(), new AdminInfo(admin, null));
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
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_ACCOUNT_LIMIT, max = MAX_ACCOUNT_LIMIT) String account,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Long roleId,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        if (roleId.equals(DaoConstant.ADMIN_ROLE_ROOT_ID)) {
            throw TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED);
        }
        return adminRoleService.isAdminHigherThanRole(requesterAccount, roleId)
                .flatMap(isHigher -> isHigher
                        ? addAdmin(account, rawPassword, roleId, name, registrationDate, upsert)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)))
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
    }

    public Mono<Admin> addAdmin(
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_ACCOUNT_LIMIT, max = MAX_ACCOUNT_LIMIT) String account,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Long roleId,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        account = account != null ? account : RandomStringUtils.randomAlphabetic(16);
        String password = StringUtils.hasText(rawPassword) ?
                passwordManager.encodeAdminPassword(rawPassword) :
                passwordManager.encodeAdminPassword(RandomStringUtils.randomAlphabetic(10));
        name = StringUtils.hasText(name) ? name : RandomStringUtils.randomAlphabetic(8);
        registrationDate = registrationDate != null ? registrationDate : new Date();
        Admin admin = new Admin(account, password, name, roleId, registrationDate);
        AdminInfo adminInfo = new AdminInfo(admin, rawPassword);
        String finalAccount = account;
        return upsert
                ? mongoTemplate.save(admin, Admin.COLLECTION_NAME).doOnNext(result -> ADMIN_MAP.put(finalAccount, adminInfo))
                : mongoTemplate.insert(admin, Admin.COLLECTION_NAME).doOnNext(result -> ADMIN_MAP.put(finalAccount, adminInfo));
    }

    public Mono<Long> queryRoleId(@NotNull String account) {
        return queryAdmin(account).map(Admin::getRoleId);
    }

    public Flux<Long> queryRolesIds(@NotEmpty Set<String> accounts) {
        Set<Long> rolesIds = Sets.newHashSetWithExpectedSize(accounts.size());
        for (String account : accounts) {
            AdminInfo adminInfo = ADMIN_MAP.get(account);
            if (adminInfo != null) {
                rolesIds.add(adminInfo.getAdmin().getRoleId());
            }
        }
        return rolesIds.size() == accounts.size()
                ? Flux.fromIterable(rolesIds)
                : queryAdmins(accounts, null, null, null)
                .map(Admin::getRoleId);
    }

    public Mono<Boolean> isAdminAuthorized(
            @NotNull String account,
            @NotNull AdminPermission permission) {
        return queryRoleId(account)
                .flatMap(roleId -> adminRoleService.hasPermission(roleId, permission))
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isAdminAuthorized(
            @NotNull ServerWebExchange exchange,
            @NotNull String account,
            @NotNull AdminPermission permission) {
        boolean isQueryingOneselfInfo = isQueryingOneselfInfo(exchange, account, permission);
        return isQueryingOneselfInfo
                ? Mono.just(true)
                : isAdminAuthorized(account, permission);
    }

    private boolean isQueryingOneselfInfo(
            @NotNull ServerWebExchange exchange,
            @NotNull String account,
            @NotNull AdminPermission permission) {
        if (permission == AdminPermission.ADMIN_QUERY) {
            String accounts = exchange.getRequest().getQueryParams().getFirst("accounts");
            return accounts != null && accounts.equals(account);
        } else {
            return false;
        }
    }

    public Mono<Boolean> authenticate(
            @NotNull @NoWhitespaceConstraint String account,
            @NotNull @NoWhitespaceConstraint String rawPassword) {
        AdminInfo adminInfo = ADMIN_MAP.get(account);
        if (adminInfo != null && adminInfo.getRawPassword() != null) {
            return Mono.just(adminInfo.getRawPassword().equals(rawPassword));
        } else {
            return queryAdmin(account)
                    .map(admin -> {
                        boolean valid = passwordManager.matchesAdminPassword(rawPassword, admin.getPassword());
                        if (valid) {
                            ADMIN_MAP.get(admin.getAccount()).setRawPassword(rawPassword);
                        }
                        return valid;
                    })
                    .defaultIfEmpty(false);
        }
    }

    public Mono<Admin> queryAdmin(@NotNull String account) {
        AdminInfo adminInfo = ADMIN_MAP.get(account);
        if (adminInfo != null) {
            return Mono.just(adminInfo.getAdmin());
        } else {
            return mongoTemplate.findById(account, Admin.class, Admin.COLLECTION_NAME)
                    .doOnNext(admin -> ADMIN_MAP.put(account, new AdminInfo(admin, null)));
        }
    }

    public Flux<Admin> queryAdmins(
            @Nullable Set<String> accounts,
            @Nullable Set<Long> roleIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder.newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, accounts)
                .addInIfNotNull(Admin.Fields.ROLE_ID, roleIds)
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, Admin.class, Admin.COLLECTION_NAME);
    }


    public Mono<Boolean> authAndDeleteAdmins(
            @NotNull String requesterAccount,
            @NotEmpty Set<String> accounts) {
        if (accounts.contains(ROOT_ADMIN_ACCOUNT)) {
            throw TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED);
        }
        return adminRoleService.isAdminHigherThanAdmins(requesterAccount, accounts)
                .flatMap(triple -> triple.getLeft()
                        ? deleteAdmins(accounts)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)))
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
    }

    public Mono<Boolean> deleteAdmins(@NotEmpty Set<String> accounts) {
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(accounts));
        return mongoTemplate.remove(query, Admin.class, Admin.COLLECTION_NAME)
                .map(result -> {
                    if (result.wasAcknowledged()) {
                        for (String account : accounts) {
                            ADMIN_MAP.remove(account);
                        }
                    }
                    return result.wasAcknowledged();
                });
    }

    public Mono<Boolean> authAndUpdateAdmins(
            @NotNull String requesterAccount,
            @NotEmpty Set<String> targetAccounts,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable Long roleId) {
        if (Validator.areAllNull(rawPassword, name, roleId)) {
            return Mono.just(true);
        }
        boolean onlyUpdateOneself = targetAccounts.size() == 1 && targetAccounts.iterator().next().equals(requesterAccount);
        if (onlyUpdateOneself) {
            if (roleId == null) {
                return updateAdmins(targetAccounts, rawPassword, name, null);
            } else {
                throw TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED);
            }
        } else {
            return adminRoleService.isAdminHigherThanAdmins(requesterAccount, targetAccounts)
                    .flatMap(triple -> {
                        if (triple.getLeft()) {
                            if (roleId != null) {
                                return adminRoleService.queryRankByRole(roleId)
                                        .flatMap(targetRoleRank -> triple.getMiddle() > targetRoleRank
                                                ? updateAdmins(targetAccounts, rawPassword, name, roleId)
                                                : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
                            } else {
                                return updateAdmins(targetAccounts, rawPassword, name, null);
                            }
                        } else {
                            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                        }
                    })
                    .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
        }
    }

    public Mono<Boolean> updateAdmins(
            @NotEmpty Set<String> targetAccounts,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_PASSWORD_LIMIT, max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @NoWhitespaceConstraint @Length(min = MIN_NAME_LIMIT, max = MAX_NAME_LIMIT) String name,
            @Nullable Long roleId) {
        if (Validator.areAllNull(rawPassword, name, roleId)) {
            return Mono.just(true);
        }
        Query query = new Query();
        query.addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(targetAccounts));
        String password = rawPassword != null
                ? passwordManager.encodeAdminPassword(rawPassword)
                : null;
        Update update = UpdateBuilder
                .newBuilder()
                .setIfNotNull(Admin.Fields.PASSWORD, password)
                .setIfNotNull(Admin.Fields.NAME, name)
                .setIfNotNull(Admin.Fields.ROLE_ID, roleId)
                .build();
        return mongoTemplate.updateMulti(query, update, Admin.class)
                .map(result -> {
                    boolean wasAcknowledged = result.wasAcknowledged();
                    if (wasAcknowledged && rawPassword != null) {
                        for (String account : targetAccounts) {
                            AdminInfo adminInfo = ADMIN_MAP.get(account);
                            if (adminInfo != null) {
                                adminInfo.setRawPassword(rawPassword);
                            }
                        }
                    }
                    return wasAcknowledged;
                });
    }

    public Mono<Long> countAdmins(@Nullable Set<String> accounts, @Nullable Set<Long> roleIds) {
        Query query = QueryBuilder.newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, accounts)
                .addInIfNotNull(Admin.Fields.ROLE_ID, roleIds)
                .buildQuery();
        return mongoTemplate.count(query, Admin.class, Admin.COLLECTION_NAME);
    }

}