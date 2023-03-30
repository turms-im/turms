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
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.admin.service.BaseAdminRoleService;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.recycler.SetRecycler;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.admin.repository.AdminRoleRepository;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.admin.constant.AdminConst.ADMIN_ROLE_ROOT_ID;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class AdminRoleService extends BaseAdminRoleService {

    private static final int MIN_ROLE_NAME_LIMIT = 1;
    private static final int MAX_ROLE_NAME_LIMIT = 32;

    private static final String ERROR_UPDATE_ROLE_WITH_HIGHER_RANK =
            "Only a role with a lower rank compared to the one of the account can be created, updated, or deleted";
    private static final String ERROR_NO_PERMISSION = "The account does not have the permissions";

    private final Map<Long, AdminRole> idToRole = new ConcurrentHashMap<>(16);
    private final AdminRoleRepository adminRoleRepository;
    private final AdminService adminService;

    /**
     * @param adminService is lazy because: AdminService -> AdminRoleService -> AdminService
     */
    public AdminRoleService(
            AdminRoleRepository adminRoleRepository,
            @Lazy AdminService adminService) {
        super(adminRoleRepository);
        this.adminRoleRepository = adminRoleRepository;
        this.adminService = adminService;
    }

    public Mono<AdminRole> authAndAddAdminRole(
            @NotNull String requesterAccount,
            @NotNull Long roleId,
            @NotNull @NoWhitespace @Size(
                    min = MIN_ROLE_NAME_LIMIT,
                    max = MAX_ROLE_NAME_LIMIT) String name,
            @NotEmpty Set<AdminPermission> permissions,
            @NotNull Integer rank) {
        try {
            Validator.notNull(roleId, "roleId");
            Validator.notNull(name, "name");
            Validator.noWhitespace(name, "name");
            Validator.length(name, "name", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
            Validator.notEmpty(permissions, "permissions");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return isAdminHigherThanRank(requesterAccount, rank).flatMap(isHigher -> {
            if (isHigher) {
                return adminHasPermissions(requesterAccount, permissions)
                        .flatMap(hasPermissions -> hasPermissions
                                ? addAdminRole(roleId, name, permissions, rank)
                                : Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                        ERROR_NO_PERMISSION)));
            }
            return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                    ERROR_UPDATE_ROLE_WITH_HIGHER_RANK));
        });
    }

    public Mono<AdminRole> addAdminRole(
            @NotNull Long roleId,
            @NotNull @NoWhitespace @Size(
                    min = MIN_ROLE_NAME_LIMIT,
                    max = MAX_ROLE_NAME_LIMIT) String name,
            @NotEmpty Set<AdminPermission> permissions,
            @NotNull Integer rank) {
        try {
            Validator.notNull(roleId, "roleId");
            Validator.notNull(name, "name");
            Validator.noWhitespace(name, "name");
            Validator.length(name, "name", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
            Validator.notEmpty(permissions, "permissions");
            Validator.notNull(rank, "rank");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (roleId.equals(ADMIN_ROLE_ROOT_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                    "The new role ID cannot be the root role ID"));
        }
        AdminRole adminRole = new AdminRole(roleId, name, permissions, rank, new Date());
        return adminRoleRepository.insert(adminRole)
                .then(Mono.fromCallable(() -> {
                    idToRole.put(adminRole.getId(), adminRole);
                    return adminRole;
                }));
    }

    public Mono<DeleteResult> authAndDeleteAdminRoles(
            @NotNull String requesterAccount,
            @NotEmpty Set<Long> roleIds) {
        try {
            Validator.notEmpty(roleIds, "roleIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Long highestRoleId = null;
        for (Long roleId : roleIds) {
            if (highestRoleId == null || highestRoleId < roleId) {
                highestRoleId = roleId;
            }
        }
        return isAdminHigherThanRole(requesterAccount, highestRoleId).flatMap(isHigher -> isHigher
                ? deleteAdminRoles(roleIds)
                : Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                        ERROR_UPDATE_ROLE_WITH_HIGHER_RANK)));
    }

    public Mono<DeleteResult> deleteAdminRoles(@NotEmpty Set<Long> roleIds) {
        try {
            Validator.notEmpty(roleIds, "roleIds");
            Validator.notContains(roleIds,
                    ADMIN_ROLE_ROOT_ID,
                    "The root admin is reserved and cannot be deleted");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminRoleRepository.deleteByIds(roleIds)
                .map(result -> {
                    for (Long id : roleIds) {
                        idToRole.remove(id);
                    }
                    return result;
                });
    }

    public Mono<UpdateResult> authAndUpdateAdminRole(
            @NotNull String requesterAccount,
            @NotEmpty Set<Long> roleIds,
            @Nullable @NoWhitespace @Size(
                    min = MIN_ROLE_NAME_LIMIT,
                    max = MAX_ROLE_NAME_LIMIT) String newName,
            @Nullable Set<AdminPermission> permissions,
            @Nullable Integer rank) {
        try {
            Validator.notEmpty(roleIds, "roleIds");
            Validator.noWhitespace(newName, "newName");
            Validator.length(newName, "newName", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Long highestRoleId = null;
        for (Long roleId : roleIds) {
            if (highestRoleId == null || highestRoleId < roleId) {
                highestRoleId = roleId;
            }
        }
        return isAdminHigherThanRole(requesterAccount, highestRoleId).flatMap(isHigher -> {
            if (isHigher) {
                if (permissions == null) {
                    return updateAdminRole(roleIds, newName, null, rank);
                }
                return adminHasPermissions(requesterAccount, permissions)
                        .flatMap(hasPermissions -> hasPermissions
                                ? updateAdminRole(roleIds, newName, permissions, rank)
                                : Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                        ERROR_NO_PERMISSION)));
            }
            return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                    ERROR_UPDATE_ROLE_WITH_HIGHER_RANK));
        });
    }

    public Mono<UpdateResult> updateAdminRole(
            @NotEmpty Set<Long> roleIds,
            @Nullable @NoWhitespace @Size(
                    min = MIN_ROLE_NAME_LIMIT,
                    max = MAX_ROLE_NAME_LIMIT) String newName,
            @Nullable Set<AdminPermission> permissions,
            @Nullable Integer rank) {
        try {
            Validator.notEmpty(roleIds, "roleIds");
            Validator.notContains(roleIds,
                    ADMIN_ROLE_ROOT_ID,
                    "The root admin is reserved and cannot be updated");
            Validator.noWhitespace(newName, "newName");
            Validator.length(newName, "newName", MIN_ROLE_NAME_LIMIT, MAX_ROLE_NAME_LIMIT);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(newName, permissions, rank)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return adminRoleRepository.updateAdminRoles(roleIds, newName, permissions, rank);
    }

    public Flux<AdminRole> queryAdminRoles(
            @Nullable Set<Long> ids,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks,
            @Nullable Integer page,
            @Nullable Integer size) {
        Flux<AdminRole> roleFlux = adminRoleRepository
                .findAdminRoles(ids, names, includedPermissions, ranks, page, size);
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
        return adminRoleRepository.countAdminRoles(ids, names, includedPermissions, ranks)
                // Add 1 because of the builtin root role
                .map(number -> number + 1);
    }

    public Flux<Integer> queryRanksByAccounts(@NotEmpty Set<String> accounts) {
        try {
            Validator.notEmpty(accounts, "accounts");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        Recyclable<Set<Long>> recyclableSet = SetRecycler.obtain();
        return adminService.queryRoleIds(accounts)
                .collect(Collectors.toCollection(recyclableSet::getValue))
                .flatMapMany(this::queryRanksByRoles)
                .doFinally(signalType -> recyclableSet.recycle());
    }

    public Mono<Integer> queryRankByAccount(@NotNull String account) {
        try {
            Validator.notNull(account, "account");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminService.queryRoleId(account)
                .flatMap(this::queryRankByRole);
    }

    public Mono<Integer> queryRankByRole(@NotNull Long roleId) {
        try {
            Validator.notNull(roleId, "roleId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (roleId.equals(ADMIN_ROLE_ROOT_ID)) {
            return Mono.just(getRootRole().getRank());
        }
        return adminRoleRepository.findRank(roleId);
    }

    public Flux<Integer> queryRanksByRoles(@NotEmpty Set<Long> roleIds) {
        try {
            Validator.notEmpty(roleIds, "roleIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        boolean containsRoot = roleIds.contains(ADMIN_ROLE_ROOT_ID);
        if (containsRoot && roleIds.size() == 1) {
            return Flux.just(getRootRole().getRank());
        }
        Flux<AdminRole> roleFlux = adminRoleRepository.findAdminRoles(roleIds);
        if (containsRoot) {
            roleFlux = roleFlux.concatWithValues(getRootRole());
        }
        return roleFlux.map(AdminRole::getRank);
    }

    public Mono<Boolean> isAdminHigherThanRole(@NotNull String account, @NotNull Long roleId) {
        return Mono.zip(queryRankByAccount(account), queryRankByRole(roleId))
                .map(tuple -> tuple.getT1() > tuple.getT2())
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> isAdminHigherThanRank(@NotNull String account, @NotNull Integer rank) {
        try {
            Validator.notNull(rank, "rank");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryRankByAccount(account).map(adminRank -> adminRank > rank)
                .defaultIfEmpty(false);
    }

    private Mono<Boolean> adminHasPermissions(
            @NotNull String account,
            @NotNull Set<AdminPermission> permissions) {
        try {
            Validator.notNull(account, "account");
            Validator.notNull(permissions, "permissions");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return permissions.isEmpty()
                ? PublisherPool.TRUE
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
            Validator.notNull(account, "account");
            Validator.notEmpty(accounts, "accounts");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryRankByAccount(account).flatMap(rank -> {
            Recyclable<Set<Integer>> recyclableSet = SetRecycler.obtain();
            return queryRanksByAccounts(accounts)
                    .collect(Collectors.toCollection(recyclableSet::getValue))
                    .map(ranks -> {
                        for (int targetRank : ranks) {
                            if (targetRank >= rank) {
                                return Triple.of(false, rank, ranks);
                            }
                        }
                        return Triple.of(true, rank, ranks);
                    })
                    .doFinally(signalType -> recyclableSet.recycle());
        });
    }

    public Mono<AdminRole> queryAndCacheRole(@NotNull Long roleId) {
        try {
            Validator.notNull(roleId, "roleId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return roleId.equals(ADMIN_ROLE_ROOT_ID)
                ? Mono.just(getRootRole())
                : adminRoleRepository.findById(roleId)
                        .doOnNext(role -> idToRole.put(roleId, role));
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull String account) {
        try {
            Validator.notNull(account, "account");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminService.queryRoleId(account)
                .flatMap(this::queryPermissions);
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull Long roleId) {
        try {
            Validator.notNull(roleId, "roleId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        AdminRole role = idToRole.get(roleId);
        return role == null
                ? queryAndCacheRole(roleId).map(AdminRole::getPermissions)
                : Mono.just(role.getPermissions());
    }

    public Mono<Boolean> hasPermission(@NotNull Long roleId, @NotNull AdminPermission permission) {
        try {
            Validator.notNull(roleId, "roleId");
            Validator.notNull(permission, "permission");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryPermissions(roleId).map(permissions -> permissions.contains(permission))
                .defaultIfEmpty(false);
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
        if (includedPermissions != null
                && !includedPermissions.containsAll(rootRole.getPermissions())) {
            return false;
        }
        return ranks == null || ranks.contains(rootRole.getRank());
    }

}