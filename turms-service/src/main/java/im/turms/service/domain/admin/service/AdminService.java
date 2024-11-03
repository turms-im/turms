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
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Supplier;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.RandomStringUtils;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.admin.bo.AdminInfo;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.admin.service.BaseAdminService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
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
import static im.turms.server.common.domain.admin.constant.AdminConst.ROOT_ADMIN_LOGIN_NAME;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class AdminService extends BaseAdminService {

    private static final Mono MONO_ERROR_REQUESTER_NOT_EXIST = Mono.error(
            ResponseException.get(ResponseStatusCode.UNAUTHORIZED, "The requester does not exist"));
    private static final Mono<UpdateResult> MONO_ERROR_UPDATE_ONE_OWN_ROLE_ID =
            Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                    "It is forbidden to update one's own role ID"));

    private static final int MIN_LOGIN_NAME_LIMIT = 1;
    private static final int MIN_PASSWORD_LIMIT = 1;
    private static final int MIN_DISPLAY_NAME_LIMIT = 1;
    public static final int MAX_LOGIN_NAME_LIMIT = 32;
    public static final int MAX_PASSWORD_LIMIT = 32;
    public static final int MAX_DISPLAY_NAME_LIMIT = 32;

    private final Node node;
    private final PasswordManager passwordManager;
    private final TurmsPropertiesManager propertiesManager;
    private final AdminRepository adminRepository;
    private final AdminRoleService adminRoleService;

    private final Map<Long, AdminInfo> idToAdmin = new ConcurrentHashMap<>(16);

    public AdminService(
            Node node,
            PasswordManager passwordManager,
            TurmsPropertiesManager propertiesManager,
            AdminRepository adminRepository,
            AdminRoleService adminRoleService) {
        super(passwordManager, adminRepository, adminRoleService);
        this.node = node;
        this.passwordManager = passwordManager;
        this.propertiesManager = propertiesManager;
        this.adminRepository = adminRepository;
        this.adminRoleService = adminRoleService;
        loadAndListenAdmins();
    }

    public Mono<Set<Long>> queryRoleIdsByAdminIds(@NotEmpty Set<Long> adminIds) {
        try {
            Validator.notEmpty(adminIds, "adminIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        boolean areAllAdminsFound = true;
        int adminCount = adminIds.size();
        Set<Long> roleIds = CollectionUtil.newSetWithExpectedSize(adminCount);
        for (Long adminId : adminIds) {
            AdminInfo adminInfo = idToAdmin.get(adminId);
            if (adminInfo != null) {
                roleIds.addAll(adminInfo.getAdmin()
                        .getRoleIds());
            } else {
                areAllAdminsFound = false;
                break;
            }
        }
        return areAllAdminsFound
                ? Mono.just(roleIds)
                : queryAdmins(adminIds, null, null, null, null).collect(CollectorUtil.toList())
                        .map(admins -> {
                            for (Admin admin : admins) {
                                roleIds.addAll(admin.getRoleIds());
                            }
                            return roleIds;
                        });
    }

    public Mono<Admin> authAndAddAdmin(
            @NotNull Long requesterId,
            @Nullable @NoWhitespace @Size(
                    min = MIN_LOGIN_NAME_LIMIT,
                    max = MAX_LOGIN_NAME_LIMIT) String loginName,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Set<Long> roleIds,
            @Nullable @Size(
                    min = MIN_DISPLAY_NAME_LIMIT,
                    max = MAX_DISPLAY_NAME_LIMIT) String displayName,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.noWhitespace(loginName, "loginName");
            Validator.length(loginName, "loginName", MIN_LOGIN_NAME_LIMIT, MAX_LOGIN_NAME_LIMIT);
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.length(displayName,
                    "displayName",
                    MIN_DISPLAY_NAME_LIMIT,
                    MAX_DISPLAY_NAME_LIMIT);
            Validator.pastOrPresent(registrationDate, "registrationDate");
            Validator.notNull(roleIds, "roleIds");
            Validator.notContains(roleIds,
                    ADMIN_ROLE_ROOT_ID,
                    "The role IDs must not contain the root role ID: "
                            + ADMIN_ROLE_ROOT_ID);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return checkIfAllowedToAddRolesToAdmins(requesterId,
                roleIds,
                requesterRank -> addAdmin(loginName,
                        rawPassword,
                        roleIds,
                        displayName,
                        registrationDate,
                        upsert));
    }

    private <T> Mono<T> checkIfAllowedToAddRolesToAdmins(
            @NotNull Long requesterId,
            @NotNull Set<Long> roleIds,
            @NotNull Function<Integer, Mono<T>> task) {
        return Mono.zip(adminRoleService.queryHighestRankByAdminId(requesterId)
                .switchIfEmpty(errorRequesterNotExist()),
                adminRoleService.queryAndCacheRolesByRoleIds(roleIds)
                        .collect(CollectorUtil.toList(roleIds.size())))
                .flatMap(tuple -> {
                    List<AdminRole> targetRoles = tuple.getT2();
                    if (targetRoles.isEmpty()) {
                        return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The role IDs do not exist: "
                                        + new TreeSet<>(roleIds)));
                    }
                    if (targetRoles.size() != roleIds.size()) {
                        TreeSet<Long> missingRoleIds = new TreeSet<>(roleIds);
                        for (AdminRole targetRole : targetRoles) {
                            missingRoleIds.remove(targetRole.getId());
                        }
                        return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The role IDs do not exist: "
                                        + missingRoleIds));
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
                        return task.apply(requesterAdminRank);
                    }
                    return Mono.error(ResponseException.get(ResponseStatusCode.UNAUTHORIZED,
                            "Only lower-rank admins can be created, updated, or deleted. "
                                    + "The following roles have an equal or higher rank than the requester: "
                                    + higherOrEqualRankTargetRoleIds));
                });
    }

    public Mono<Admin> addAdmin(
            @Nullable @NoWhitespace @Size(
                    min = MIN_LOGIN_NAME_LIMIT,
                    max = MAX_LOGIN_NAME_LIMIT) String loginName,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @NotNull Set<Long> roleIds,
            @Nullable @Size(
                    min = MIN_DISPLAY_NAME_LIMIT,
                    max = MAX_DISPLAY_NAME_LIMIT) String displayName,
            @Nullable @PastOrPresent Date registrationDate,
            boolean upsert) {
        try {
            Validator.noWhitespace(loginName, "loginName");
            Validator.length(loginName, "loginName", MIN_LOGIN_NAME_LIMIT, MAX_LOGIN_NAME_LIMIT);
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.notNull(roleIds, "roleIds");
            Validator.length(displayName,
                    "displayName",
                    MIN_DISPLAY_NAME_LIMIT,
                    MAX_DISPLAY_NAME_LIMIT);
            Validator.pastOrPresent(registrationDate, "registrationDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        loginName = loginName == null
                ? RandomStringUtils.randomAlphabetic(16)
                : loginName;
        byte[] password = StringUtils.hasText(rawPassword)
                ? passwordManager.encodeAdminPassword(rawPassword)
                : passwordManager.encodeAdminPassword(RandomStringUtils.randomAlphabetic(10));
        displayName = StringUtils.hasText(displayName)
                ? displayName
                : loginName;
        registrationDate = registrationDate == null
                ? new Date()
                : registrationDate;
        Long id = node.nextLargeGapId(ServiceType.ADMIN);
        Admin admin = new Admin(id, loginName, password, displayName, roleIds, registrationDate);
        AdminInfo adminInfo = new AdminInfo(admin, rawPassword);
        Mono<Void> result = upsert
                ? adminRepository.upsert(admin)
                        .then()
                : adminRepository.insert(admin);
        return result.then(Mono.fromCallable(() -> {
            idToAdmin.put(id, adminInfo);
            return admin;
        }));
    }

    protected Mono<Admin> addRootAdmin() {
        return addAdmin(ROOT_ADMIN_LOGIN_NAME,
                propertiesManager.getLocalProperties()
                        .getSecurity()
                        .getPassword()
                        .getInitialRootPassword(),
                Set.of(ADMIN_ROLE_ROOT_ID),
                ROOT_ADMIN_LOGIN_NAME,
                new Date(),
                false).onErrorComplete(DuplicateKeyException.class);
    }

    public Flux<Admin> queryAdmins(
            @Nullable Collection<Long> ids,
            @Nullable Collection<String> loginNames,
            @Nullable Collection<Long> roleIds,
            @Nullable Integer page,
            @Nullable Integer size) {
        return adminRepository.findAdmins(ids, loginNames, roleIds, page, size);
    }

    public Mono<DeleteResult> authAndDeleteAdmins(
            @NotNull Long requesterId,
            @NotEmpty Set<Long> adminIds) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notEmpty(adminIds, "adminIds");
            Validator.notContains(adminIds,
                    ROOT_ADMIN_LOGIN_NAME,
                    "The root admin is reserved and cannot be deleted");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return checkIfAllowedToManageAdmins(adminRoleService.queryHighestRankByAdminId(requesterId)
                .switchIfEmpty(errorRequesterNotExist()), adminIds, () -> deleteAdmins(adminIds));
    }

    private Mono<DeleteResult> deleteAdmins(@NotEmpty Set<Long> adminIds) {
        try {
            Validator.notEmpty(adminIds, "adminIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return adminRepository.deleteByIds(adminIds)
                .map(result -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long adminId : adminIds) {
                        idToAdmin.remove(adminId);
                    }
                    return result;
                });
    }

    public Mono<UpdateResult> authAndUpdateAdmins(
            @NotNull Long requesterId,
            @NotEmpty Set<Long> targetAdminIds,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @Size(
                    min = MIN_DISPLAY_NAME_LIMIT,
                    max = MAX_DISPLAY_NAME_LIMIT) String displayName,
            @Nullable Set<Long> roleIds) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notEmpty(targetAdminIds, "targetAdminIds");
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.length(displayName,
                    "displayName",
                    MIN_DISPLAY_NAME_LIMIT,
                    MAX_DISPLAY_NAME_LIMIT);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        boolean noRoleIds = CollectionUtil.isEmpty(roleIds);
        if (Validator.areAllNull(rawPassword, displayName) && noRoleIds) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        if (noRoleIds) {
            return checkIfAllowedToManageAdmins(
                    adminRoleService.queryHighestRankByAdminId(requesterId)
                            .switchIfEmpty(errorRequesterNotExist()),
                    targetAdminIds,
                    () -> updateAdmins(targetAdminIds, rawPassword, displayName, null));
        }
        if (targetAdminIds.contains(requesterId)) {
            return MONO_ERROR_UPDATE_ONE_OWN_ROLE_ID;
        }
        return checkIfAllowedToAddRolesToAdmins(requesterId,
                roleIds,
                requesterRank -> checkIfAllowedToManageAdmins(Mono.just(requesterRank),
                        targetAdminIds,
                        () -> updateAdmins(targetAdminIds, rawPassword, displayName, null)));
    }

    public Mono<UpdateResult> updateAdmins(
            @NotEmpty Set<Long> targetAdminIds,
            @Nullable @NoWhitespace @Size(
                    min = MIN_PASSWORD_LIMIT,
                    max = MAX_PASSWORD_LIMIT) String rawPassword,
            @Nullable @Size(
                    min = MIN_DISPLAY_NAME_LIMIT,
                    max = MAX_DISPLAY_NAME_LIMIT) String displayName,
            @Nullable Set<Long> roleIds) {
        try {
            Validator.notEmpty(targetAdminIds, "targetAdminIds");
            Validator.noWhitespace(rawPassword, "rawPassword");
            Validator.length(rawPassword, "rawPassword", MIN_PASSWORD_LIMIT, MAX_PASSWORD_LIMIT);
            Validator.length(displayName,
                    "displayName",
                    MIN_DISPLAY_NAME_LIMIT,
                    MAX_DISPLAY_NAME_LIMIT);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(rawPassword, displayName) && CollectionUtil.isEmpty(roleIds)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        byte[] password = rawPassword == null
                ? null
                : passwordManager.encodeAdminPassword(rawPassword);
        return adminRepository.updateAdmins(targetAdminIds, password, displayName, roleIds)
                .doOnNext(updateResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long adminId : targetAdminIds) {
                        idToAdmin.remove(adminId);
                    }
                });
    }

    public Mono<Long> countAdmins(@Nullable Set<Long> ids, @Nullable Set<Long> roleIds) {
        return adminRepository.countAdmins(ids, roleIds);
    }

    private <T> Mono<T> checkIfAllowedToManageAdmins(
            @NotNull Mono<Integer> requesterRank,
            @NotNull Collection<Long> targetAdminIds,
            @NotNull Supplier<Mono<T>> task) {
        int targetAdminCount = targetAdminIds.size();
        Mono<Map<Long, Set<Long>>> queryAdminRoles =
                queryAdmins(targetAdminIds, null, null, null, null)
                        .collect(CollectorUtil.toList(targetAdminCount))
                        .map(targetAdmins -> {
                            Map<Long, Set<Long>> targetAdminRoleIdToAdminIds =
                                    CollectionUtil.newMapWithExpectedSize(targetAdmins.size());
                            for (Admin admin : targetAdmins) {
                                Set<Long> targetAdminRoleIds = admin.getRoleIds();
                                for (Long targetAdminRoleId : targetAdminRoleIds) {
                                    targetAdminRoleIdToAdminIds
                                            .computeIfAbsent(targetAdminRoleId,
                                                    key -> new UnifiedSet<>(8))
                                            .add(admin.getId());
                                }
                            }
                            return targetAdminRoleIdToAdminIds;
                        });
        return Mono.zip(requesterRank, queryAdminRoles)
                .flatMap(tuple -> {
                    Map<Long, Set<Long>> targetAdminRoleIdToAdminIds = tuple.getT2();
                    return adminRoleService
                            .queryAndCacheRolesByRoleIdsAndRankGreaterThan(
                                    targetAdminRoleIdToAdminIds.keySet(),
                                    tuple.getT1())
                            .collect(CollectorUtil.toList(targetAdminRoleIdToAdminIds.size()))
                            .flatMap(roles -> {
                                if (roles.isEmpty()) {
                                    return task.get();
                                }
                                TreeSet<Long> higherOrEqualRankTargetAdminIds = new TreeSet<>();
                                for (AdminRole role : roles) {
                                    if (role.getRank() >= tuple.getT1()) {
                                        higherOrEqualRankTargetAdminIds.addAll(
                                                targetAdminRoleIdToAdminIds.get(role.getId()));
                                    }
                                }
                                return Mono.error(ResponseException.get(
                                        ResponseStatusCode.UNAUTHORIZED,
                                        "Only lower-rank admins can be created, updated, or deleted. "
                                                + "The following admins have an equal or higher rank than the requester: "
                                                + higherOrEqualRankTargetAdminIds));
                            });
                });
    }

    public <T> Mono<T> errorRequesterNotExist() {
        return MONO_ERROR_REQUESTER_NOT_EXIST;
    }

}