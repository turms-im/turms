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

import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.AdminRole;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.tuple.Triple;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Log4j2
@Service
public class AdminRoleService {

    private static final int MIN_ROLE_NAME_LIMIT = 1;
    private static final int MAX_ROLE_NAME_LIMIT = 32;

    private final Map<Long, AdminRole> roles = new HashMap<>();
    private final ReactiveMongoTemplate mongoTemplate;
    private final AdminService adminService;

    public AdminRoleService(@Qualifier("adminMongoTemplate") ReactiveMongoTemplate mongoTemplate, @Lazy AdminService adminService) {
        this.mongoTemplate = mongoTemplate;
        this.adminService = adminService;

        listenAndLoadRoles();
    }

    public void listenAndLoadRoles() {
        mongoTemplate.changeStream(AdminRole.class)
                .withOptions(ChangeStreamOptions.ChangeStreamOptionsBuilder::returnFullDocumentOnUpdate)
                .watchCollection(AdminRole.class)
                .listen()
                .doOnNext(event -> {
                    AdminRole adminRole = event.getBody();
                    switch (event.getOperationType()) {
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            roles.put(adminRole.getId(), adminRole);
                            break;
                        case DELETE:
                            long roleId = ChangeStreamUtil.getIdAsLong(event);
                            roles.remove(roleId);
                            break;
                        case INVALIDATE:
                            resetRoles();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + event.getOperationType());
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of AdminRole: {}", o, throwable))
                .subscribe();

        // Load
        resetRoles();
        mongoTemplate.find(new Query(), AdminRole.class, AdminRole.COLLECTION_NAME)
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
                                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
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
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
        }
        return mongoTemplate.insert(adminRole, AdminRole.COLLECTION_NAME)
                .doOnNext(role -> roles.put(adminRole.getId(), role));
    }

    public Mono<Boolean> authAndDeleteAdminRoles(
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
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
    }

    public Mono<Boolean> deleteAdminRoles(@NotEmpty Set<Long> roleIds) {
        try {
            AssertUtil.notEmpty(roleIds, "roleIds");
            AssertUtil.state(!roleIds.contains(DaoConstant.ADMIN_ROLE_ROOT_ID), "The root admin is reserved and cannot be deleted");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(roleIds));
        return mongoTemplate.remove(query, AdminRole.class, AdminRole.COLLECTION_NAME)
                .map(result -> {
                    if (result.wasAcknowledged()) {
                        for (Long id : roleIds) {
                            roles.remove(id);
                        }
                        return true;
                    } else {
                        return false;
                    }
                });
    }

    public Mono<Boolean> authAndUpdateAdminRole(
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
                                            : Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED)));
                        } else {
                            return updateAdminRole(roleIds, newName, null, rank);
                        }
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAUTHORIZED));
                    }
                });
    }

    public Mono<Boolean> updateAdminRole(
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
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(roleIds));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(AdminRole.Fields.NAME, newName)
                .setIfNotNull(AdminRole.Fields.PERMISSIONS, permissions)
                .setIfNotNull(AdminRole.Fields.RANK, rank)
                .build();
        return mongoTemplate.updateMulti(query, update, AdminRole.class, AdminRole.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
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
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(AdminRole.Fields.NAME, names)
                .addInIfNotNull(AdminRole.Fields.PERMISSIONS, includedPermissions)
                .addInIfNotNull(AdminRole.Fields.RANK, ranks)
                .paginateIfNotNull(page, size);
        return Flux.from(mongoTemplate.find(query, AdminRole.class, AdminRole.COLLECTION_NAME)
                .concatWithValues(getRootRole()));
    }

    public Mono<Long> countAdminRoles(
            @Nullable Set<Long> ids,
            @Nullable Set<String> names,
            @Nullable Set<AdminPermission> includedPermissions,
            @Nullable Set<Integer> ranks) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(AdminRole.Fields.NAME, names)
                .addInIfNotNull(AdminRole.Fields.PERMISSIONS, includedPermissions)
                .addInIfNotNull(AdminRole.Fields.RANK, ranks)
                .buildQuery();
        return mongoTemplate.count(query, AdminRole.class, AdminRole.COLLECTION_NAME)
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
            Query query = new Query();
            query.addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(roleId));
            query.fields().include(AdminRole.Fields.RANK);
            return mongoTemplate.findOne(query, AdminRole.class, AdminRole.COLLECTION_NAME)
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
            Query query = new Query();
            query.addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(roleIds));
            query.fields().include(AdminRole.Fields.RANK);
            Flux<AdminRole> roleFlux = mongoTemplate.find(query, AdminRole.class, AdminRole.COLLECTION_NAME);
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
                : mongoTemplate.findById(roleId, AdminRole.class, AdminRole.COLLECTION_NAME)
                .doOnNext(role -> roles.put(roleId, role));
    }

    public Mono<Set<AdminPermission>> queryPermissions(@NotNull String account) {
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
            AssertUtil.notNull(permission, "permission");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryPermissions(roleId)
                .map(permissions -> permissions.contains(permission))
                .defaultIfEmpty(false);
    }

    private void resetRoles() {
        roles.clear();
        roles.putIfAbsent(
                DaoConstant.ADMIN_ROLE_ROOT_ID,
                new AdminRole(
                        DaoConstant.ADMIN_ROLE_ROOT_ID,
                        DaoConstant.ADMIN_ROLE_ROOT_NAME,
                        AdminPermission.ALL,
                        Integer.MAX_VALUE));
    }

}
