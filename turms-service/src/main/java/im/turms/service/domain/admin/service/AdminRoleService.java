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

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Supplier;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.admin.service.BaseAdminRoleService;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
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

    private static final ResponseException EXCEPTION_UPDATE_ROLE_WITH_HIGHER_RANK =
            ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                    "Only lower-rank roles can be created, updated, or deleted");
    private static final Mono<Set<AdminPermission>> MONO_ERROR_UPDATE_ROLE_WITH_HIGHER_RANK =
            Mono.error(EXCEPTION_UPDATE_ROLE_WITH_HIGHER_RANK);

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
            @NotNull Long requesterId,
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
        if (roleId.equals(ADMIN_ROLE_ROOT_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                    "The new role ID cannot be the root role ID"));
        }
        return isAdminRankHigherThanRank(requesterId, rank)
                .switchIfEmpty(adminService.errorRequesterNotExist())
                .flatMap(isHigher -> isHigher
                        ? queryPermissions(requesterId)
                                .switchIfEmpty(adminService.errorRequesterNotExist())
                        : MONO_ERROR_UPDATE_ROLE_WITH_HIGHER_RANK)
                .flatMap(requesterPermissions -> {
                    if (requesterPermissions.containsAll(permissions)) {
                        return addAdminRole(roleId, name, permissions, rank);
                    }
                    TreeSet<AdminPermission> missingPermissions = new TreeSet<>(permissions);
                    missingPermissions.removeAll(requesterPermissions);
                    return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                            "The requester does not have the permissions: "
                                    + missingPermissions));
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
            @NotNull Long requesterId,
            @NotEmpty Set<Long> roleIds) {
        try {
            Validator.notEmpty(roleIds, "roleIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return checkIfAllowedToManageRoles(requesterId,
                roleIds,
                OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT,
                () -> deleteAdminRoles(roleIds));
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
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long id : roleIds) {
                        idToRole.remove(id);
                    }
                    return result;
                });
    }

    public Mono<UpdateResult> authAndUpdateAdminRoles(
            @NotNull Long requesterId,
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
        return checkIfAllowedToManageRoles(requesterId,
                roleIds,
                OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT,
                () -> {
                    if (permissions == null) {
                        return updateAdminRole(roleIds, newName, null, rank);
                    }
                    return queryPermissions(requesterId)
                            .switchIfEmpty(adminService.errorRequesterNotExist())
                            .flatMap(requesterPermissions -> {
                                if (requesterPermissions.containsAll(permissions)) {
                                    return updateAdminRole(roleIds, newName, permissions, rank);
                                }
                                TreeSet<AdminPermission> missingPermissions =
                                        new TreeSet<>(permissions);
                                missingPermissions.removeAll(requesterPermissions);
                                return Mono.error(
                                        ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                                                "The requester does not have the permissions: "
                                                        + missingPermissions));
                            });
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
        return adminRoleRepository.updateAdminRoles(roleIds, newName, permissions, rank)
                .doOnNext(updateResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long roleId : roleIds) {
                        idToRole.remove(roleId);
                    }
                });
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
            return roleFlux.startWith(getRootRole());
        }
        return roleFlux;
    }

    public Flux<AdminRole> queryAndCacheRolesByRoleIdsAndRankGreaterThan(
            @NotNull Collection<Long> roleIds,
            @NotNull Integer rankGreaterThan) {
        try {
            Validator.notNull(roleIds, "roleIds");
            Validator.notNull(rankGreaterThan, "rankGreaterThan");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (roleIds.isEmpty()) {
            return Flux.empty();
        }
        if (roleIds.contains(ADMIN_ROLE_ROOT_ID)) {
            AdminRole rootRole = getRootRole();
            if (roleIds.size() == 1) {
                return rootRole.getRank() > rankGreaterThan
                        ? Flux.just(rootRole)
                        : Flux.empty();
            }
            roleIds = new UnifiedSet<>(roleIds);
            roleIds.remove(ADMIN_ROLE_ROOT_ID);
            Flux<AdminRole> adminRoleFlux = adminRoleRepository
                    .findAdminRolesByIdsAndRankGreaterThan(roleIds, rankGreaterThan)
                    .doOnNext(role -> idToRole.put(role.getId(), role));
            return rootRole.getRank() > rankGreaterThan
                    ? adminRoleFlux.startWith(rootRole)
                    : adminRoleFlux;
        }
        return adminRoleRepository.findAdminRolesByIdsAndRankGreaterThan(roleIds, rankGreaterThan)
                .doOnNext(role -> idToRole.put(role.getId(), role));
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

    public Mono<Integer> queryHighestRankByAdminId(@NotNull Long adminId) {
        try {
            Validator.notNull(adminId, "adminId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminService.queryRoleIdsByAdminId(adminId)
                .flatMap(this::queryHighestRankByRoleIds);
    }

    public Mono<Integer> queryHighestRankByRoleIds(@NotNull Set<Long> roleIds) {
        try {
            Validator.notNull(roleIds, "roleIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (roleIds.isEmpty()) {
            return Mono.empty();
        }
        if (roleIds.contains(ADMIN_ROLE_ROOT_ID)) {
            return Mono.just(getRootRole().getRank());
        }
        return adminRoleRepository.findHighestRankByRoleIds(roleIds);
    }

    public Mono<Boolean> isAdminRankHigherThanRank(@NotNull Long adminId, @NotNull Integer rank) {
        try {
            Validator.notNull(rank, "rank");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryHighestRankByAdminId(adminId).map(adminRank -> adminRank > rank);
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull Long adminId) {
        try {
            Validator.notNull(adminId, "adminId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminService.queryRoleIdsByAdminId(adminId)
                .flatMap(this::queryPermissions);
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

    private <T> Mono<T> checkIfAllowedToManageRoles(
            @NotNull Long requesterId,
            @NotEmpty Set<Long> roleIds,
            @NotNull Mono<T> noOpResult,
            @NotNull Supplier<Mono<T>> task) {
        return Mono.zip(
                queryHighestRankByAdminId(requesterId)
                        .switchIfEmpty(adminService.errorRequesterNotExist()),
                queryAndCacheRolesByRoleIds(roleIds).collect(CollectorUtil.toList(roleIds.size())))
                .flatMap(tuple -> {
                    List<AdminRole> targetRoles = tuple.getT2();
                    if (targetRoles.isEmpty()) {
                        return noOpResult;
                    }
                    Integer requesterAdminRank = tuple.getT1();
                    TreeSet<Long> higherOrEqualRankTargetRoleIds = null;
                    for (AdminRole targetRole : targetRoles) {
                        if (targetRole.getRank() >= requesterAdminRank) {
                            if (higherOrEqualRankTargetRoleIds == null) {
                                higherOrEqualRankTargetRoleIds = new TreeSet<>();
                            }
                            higherOrEqualRankTargetRoleIds.add(targetRole.getId());
                        }
                    }
                    if (higherOrEqualRankTargetRoleIds == null) {
                        return task.get();
                    }
                    return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                            "Only lower-rank roles can be created, updated, or deleted. "
                                    + "The following roles have an equal or higher rank than the requester: "
                                    + higherOrEqualRankTargetRoleIds));
                });
    }
}