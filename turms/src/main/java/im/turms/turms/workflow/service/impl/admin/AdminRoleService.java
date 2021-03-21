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

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoDataGenerator;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.dao.domain.admin.AdminRole;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Triple;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Log4j2
@Service
@DependsOn(IMongoDataGenerator.BEAN_NAME)
public class AdminRoleService {

    private static final int MIN_ROLE_NAME_LIMIT = 1;
    private static final int MAX_ROLE_NAME_LIMIT = 32;
    private static final AdminRole ROOT_ROLE =
            new AdminRole(DaoConstant.ADMIN_ROLE_ROOT_ID, "ROOT", AdminPermission.ALL, Integer.MAX_VALUE);

    private static final String ERROR_UPDATE_ROLE_WITH_HIGHER_RANK =
            "Only a role with a lower rank compared to the one of the account can be created, updated, or deleted";
    private static final String ERROR_NO_PERMISSION = "The account doesn't have the permissions";

    private final Map<Long, AdminRole> roles = new ConcurrentHashMap<>(16);
    private final TurmsMongoClient mongoClient;
    private final AdminService adminService;

    /**
     * @param adminService is lazy because: AdminService -> AdminRoleService -> AdminService
     */
    public AdminRoleService(@Qualifier("adminMongoClient") TurmsMongoClient mongoClient,
                            @Lazy AdminService adminService) {
        this.mongoClient = mongoClient;
        this.adminService = adminService;

        listenAndLoadRoles();
    }

    public void listenAndLoadRoles() {
        mongoClient.watch(AdminRole.class, FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    AdminRole adminRole = event.getFullDocument();
                    switch (event.getOperationType()) {
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            roles.put(adminRole.getId(), adminRole);
                            break;
                        case DELETE:
                            long roleId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            roles.remove(roleId);
                            break;
                        case INVALIDATE:
                            resetRoles();
                            break;
                        default:
                            log.fatal("Detect an illegal operation on AdminRole collection: " + event);
                    }
                })
                .onErrorContinue(
                        (throwable, o) -> log.error("Error while processing the change stream event of AdminRole: {}", o, throwable))
                .subscribe();

        // Load
        resetRoles();
        mongoClient.findAll(AdminRole.class)
                .doOnNext(role -> roles.put(role.getId(), role))
                .subscribe();
    }

    public Mono<AdminRole> authAndAddAdminRole(
            @NotNull String requesterAccount,
            @NotNull Long roleId,
            @NotNull @NoWhitespace @Length(min = MIN_ROLE_NAME_LIMIT, max = MAX_ROLE_NAME_LIMIT) String name,
            @NotEmpty Set<AdminPermission> permissions,
            @NotNull Integer rank) {
        try {
            AssertUtil.notNull(roleId, "roleId");
            AssertUtil.notNull(name, "name");
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.length(name, "name", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
            AssertUtil.notEmpty(permissions, "permissions");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return isAdminHigherThanRank(requesterAccount, rank)
                .flatMap(isHigher -> {
                    if (isHigher) {
                        return adminHasPermissions(requesterAccount, permissions)
                                .flatMap(hasPermissions -> hasPermissions
                                        ? addAdminRole(roleId, name, permissions, rank)
                                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_NO_PERMISSION)));
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ROLE_WITH_HIGHER_RANK));
                    }
                });
    }

    public Mono<AdminRole> addAdminRole(
            @NotNull Long roleId,
            @NotNull @NoWhitespace @Length(min = MIN_ROLE_NAME_LIMIT, max = MAX_ROLE_NAME_LIMIT) String name,
            @NotEmpty Set<AdminPermission> permissions,
            @NotNull Integer rank) {
        try {
            AssertUtil.notNull(roleId, "roleId");
            AssertUtil.notNull(name, "name");
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.length(name, "name", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
            AssertUtil.notEmpty(permissions, "permissions");
            AssertUtil.notNull(rank, "rank");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        AdminRole adminRole = new AdminRole(roleId, name, permissions, rank);
        if (adminRole.getId().equals(DaoConstant.ADMIN_ROLE_ROOT_ID)) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, "The new role ID cannot be the root role ID"));
        }
        return mongoClient.insert(adminRole)
                .map(id -> {
                    roles.put(adminRole.getId(), adminRole);
                    return adminRole;
                });
    }

    public Mono<DeleteResult> authAndDeleteAdminRoles(
            @NotNull String requesterAccount,
            @NotEmpty Set<Long> roleIds) {
        try {
            AssertUtil.notEmpty(roleIds, "roleIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Long highestRoleId = null;
        for (Long roleId : roleIds) {
            if (highestRoleId == null || highestRoleId < roleId) {
                highestRoleId = roleId;
            }
        }
        return isAdminHigherThanRole(requesterAccount, highestRoleId)
                .flatMap(isHigher -> isHigher
                        ? deleteAdminRoles(roleIds)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ROLE_WITH_HIGHER_RANK)));
    }

    public Mono<DeleteResult> deleteAdminRoles(@NotEmpty Set<Long> roleIds) {
        try {
            AssertUtil.notEmpty(roleIds, "roleIds");
            AssertUtil.state(!roleIds.contains(DaoConstant.ADMIN_ROLE_ROOT_ID), "The root admin is reserved and cannot be deleted");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, roleIds);
        return mongoClient.deleteMany(AdminRole.class, filter)
                .map(result -> {
                    for (Long id : roleIds) {
                        roles.remove(id);
                    }
                    return result;
                });
    }

    public Mono<UpdateResult> authAndUpdateAdminRole(
            @NotNull String requesterAccount,
            @NotEmpty Set<Long> roleIds,
            @Nullable @NoWhitespace @Length(min = MIN_ROLE_NAME_LIMIT, max = MAX_ROLE_NAME_LIMIT) String newName,
            @Nullable Set<AdminPermission> permissions,
            @Nullable Integer rank) {
        try {
            AssertUtil.notEmpty(roleIds, "roleIds");
            AssertUtil.noWhitespace(newName, "newName");
            AssertUtil.length(newName, "newName", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Long highestRoleId = null;
        for (Long roleId : roleIds) {
            if (highestRoleId == null || highestRoleId < roleId) {
                highestRoleId = roleId;
            }
        }
        return isAdminHigherThanRole(requesterAccount, highestRoleId)
                .flatMap(isHigher -> {
                    if (isHigher) {
                        if (permissions != null) {
                            return adminHasPermissions(requesterAccount, permissions)
                                    .flatMap(hasPermissions -> hasPermissions
                                            ? updateAdminRole(roleIds, newName, permissions, rank)
                                            : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_NO_PERMISSION)));
                        } else {
                            return updateAdminRole(roleIds, newName, null, rank);
                        }
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED, ERROR_UPDATE_ROLE_WITH_HIGHER_RANK));
                    }
                });
    }

    public Mono<UpdateResult> updateAdminRole(
            @NotEmpty Set<Long> roleIds,
            @Nullable @NoWhitespace @Length(min = MIN_ROLE_NAME_LIMIT, max = MAX_ROLE_NAME_LIMIT) String newName,
            @Nullable Set<AdminPermission> permissions,
            @Nullable Integer rank) {
        try {
            AssertUtil.notEmpty(roleIds, "roleIds");
            AssertUtil.state(!roleIds.contains(DaoConstant.ADMIN_ROLE_ROOT_ID), "The root admin is reserved and cannot be updated");
            AssertUtil.noWhitespace(newName, "newName");
            AssertUtil.length(newName, "newName", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(newName, permissions, rank)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, roleIds);
        Update update = Update.newBuilder()
                .setIfNotNull(AdminRole.Fields.NAME, newName)
                .setIfNotNull(AdminRole.Fields.PERMISSIONS, permissions)
                .setIfNotNull(AdminRole.Fields.RANK, rank);
        return mongoClient.updateMany(AdminRole.class, filter, update);
    }

    public AdminRole getRootRole() {
        return roles.get(DaoConstant.ADMIN_ROLE_ROOT_ID);
    }

    public Flux<AdminRole> queryAdminRoles(
            @Nullable Set<Long> ids,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks,
            @Nullable Integer page,
            @Nullable Integer size) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(AdminRole.Fields.NAME, names)
                .inIfNotNull(AdminRole.Fields.PERMISSIONS, includedPermissions)
                .inIfNotNull(AdminRole.Fields.RANK, ranks);
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        Flux<AdminRole> roleFlux = mongoClient.findMany(AdminRole.class, filter, options);
        if (isRootRoleQualified(ids, names, includedPermissions, ranks)) {
            // TODO: respect the page and the size
            return roleFlux.concatWithValues(getRootRole());
        }
        return roleFlux;
    }

    public Mono<Long> countAdminRoles(
            @Nullable Set<Long> ids,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks) {
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(AdminRole.Fields.NAME, names)
                .inIfNotNull(AdminRole.Fields.PERMISSIONS, includedPermissions)
                .inIfNotNull(AdminRole.Fields.RANK, ranks);
        return mongoClient.count(AdminRole.class, filter)
                // Add 1 because of the nested root role
                .map(number -> number + 1);
    }

    public Flux<Integer> queryRanksByAccounts(@NotEmpty Set<String> accounts) {
        try {
            AssertUtil.notEmpty(accounts, "accounts");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        return adminService.queryRoleIds(accounts)
                .collect(Collectors.toSet())
                .flatMapMany(this::queryRanksByRoles);
    }

    public Mono<Integer> queryRankByAccount(@NotNull String account) {
        try {
            AssertUtil.notNull(account, "account");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return adminService.queryRoleId(account)
                .flatMap(this::queryRankByRole);
    }

    public Mono<Integer> queryRankByRole(@NotNull Long roleId) {
        try {
            AssertUtil.notNull(roleId, "roleId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (roleId.equals(DaoConstant.ADMIN_ROLE_ROOT_ID)) {
            return Mono.just(getRootRole().getRank());
        } else {
            Filter filter = Filter.newBuilder()
                    .eq(DaoConstant.ID_FIELD_NAME, roleId);
            QueryOptions options = QueryOptions.newBuilder()
                    .include(AdminRole.Fields.RANK);
            return mongoClient.findOne(AdminRole.class, filter, options)
                    .map(AdminRole::getRank);
        }
    }

    public Flux<Integer> queryRanksByRoles(@NotEmpty Set<Long> roleIds) {
        try {
            AssertUtil.notEmpty(roleIds, "roleIds");
        } catch (TurmsBusinessException e) {
            return Flux.error(e);
        }
        boolean containsRoot = roleIds.contains(DaoConstant.ADMIN_ROLE_ROOT_ID);
        if (containsRoot && roleIds.size() == 1) {
            return Flux.just(getRootRole().getRank());
        } else {
            Filter query = Filter.newBuilder()
                    .in(DaoConstant.ID_FIELD_NAME, roleIds);
            QueryOptions options = QueryOptions.newBuilder()
                    .include(AdminRole.Fields.RANK);
            Flux<AdminRole> roleFlux = mongoClient.findMany(AdminRole.class, query, options);
            if (containsRoot) {
                roleFlux = roleFlux.concatWithValues(getRootRole());
            }
            return roleFlux.map(AdminRole::getRank);
        }
    }

    public Mono<Boolean> isAdminHigherThanRole(
            @NotNull String account,
            @NotNull Long roleId) {
        return Mono.zip(queryRankByAccount(account), queryRankByRole(roleId))
                .map(tuple -> tuple.getT1() > tuple.getT2())
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isAdminHigherThanRank(
            @NotNull String account,
            @NotNull Integer rank) {
        try {
            AssertUtil.notNull(rank, "rank");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryRankByAccount(account)
                .map(adminRank -> adminRank > rank)
                .defaultIfEmpty(false);
    }

    private Mono<Boolean> adminHasPermissions(@NotNull String account, @NotNull Set<AdminPermission> permissions) {
        try {
            AssertUtil.notNull(account, "account");
            AssertUtil.notNull(permissions, "permissions");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return permissions.isEmpty()
                ? Mono.just(true)
                : queryPermissions(account)
                .map(adminPermissions -> adminPermissions.containsAll(permissions))
                .defaultIfEmpty(false);
    }

    /**
     * @return isAdminHigherThanAdmins, admin rank, admins ranks
     */
    public Mono<Triple<Boolean, Integer, Set<Integer>>> isAdminHigherThanAdmins(
            @NotNull String account,
            @NotEmpty Set<String> accounts) {
        try {
            AssertUtil.notNull(account, "account");
            AssertUtil.notEmpty(accounts, "accounts");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryRankByAccount(account)
                .flatMap(rank -> queryRanksByAccounts(accounts)
                        .collect(Collectors.toSet())
                        .map(ranks -> {
                            for (Integer targetRank : ranks) {
                                if (targetRank >= rank) {
                                    return Triple.of(false, rank, ranks);
                                }
                            }
                            return Triple.of(true, rank, ranks);
                        }));
    }

    public Mono<AdminRole> queryAndCacheRole(@NotNull Long roleId) {
        try {
            AssertUtil.notNull(roleId, "roleId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return roleId.equals(DaoConstant.ADMIN_ROLE_ROOT_ID)
                ? Mono.just(getRootRole())
                : mongoClient.findById(AdminRole.class, roleId)
                .doOnNext(role -> roles.put(roleId, role));
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull String account) {
        try {
            AssertUtil.notNull(account, "account");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return adminService.queryRoleId(account)
                .flatMap(this::queryPermissions);
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull Long roleId) {
        try {
            AssertUtil.notNull(roleId, "roleId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        AdminRole role = roles.get(roleId);
        return role != null
                ? Mono.just(role.getPermissions())
                : queryAndCacheRole(roleId)
                .map(AdminRole::getPermissions);
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
        Long rootId = DaoConstant.ADMIN_ROLE_ROOT_ID;
        roles.keySet().removeIf(id -> !id.equals(rootId));
        roles.put(rootId, ROOT_ROLE);
    }

    private boolean isRootRoleQualified(
            @Nullable Set<Long> ids,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks) {
        AdminRole rootRole = getRootRole();
        if (ids != null && !ids.contains(rootRole.getId())) {
            return false;
        }
        if (names != null && !names.contains(rootRole.getName())) {
            return false;
        }
        if (includedPermissions != null && !includedPermissions.containsAll(rootRole.getPermissions())) {
            return false;
        }
        return ranks == null || ranks.contains(rootRole.getRank());
    }

}