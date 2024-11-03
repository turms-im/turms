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

package im.turms.service.domain.user.access.admin.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.PaginationDTO;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.user.access.admin.dto.request.AddUserRoleDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateUserRoleDTO;
import im.turms.service.domain.user.po.UserRole;
import im.turms.service.domain.user.service.UserRoleService;

/**
 * @author James Chen
 */
@RestController("users/roles")
public class UserRoleController extends BaseController {

    private final UserRoleService userRoleService;

    public UserRoleController(
            TurmsPropertiesManager propertiesManager,
            UserRoleService userRoleService) {
        super(propertiesManager);
        this.userRoleService = userRoleService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.USER_ROLE_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<UserRole>>> addUserRole(
            @RequestBody AddUserRoleDTO addUserRoleDTO) {
        Set<Long> creatableGroupTypesIds = addUserRoleDTO.creatableGroupTypeIds();
        creatableGroupTypesIds = creatableGroupTypesIds == null
                ? Collections.emptySet()
                : creatableGroupTypesIds;
        Map<Long, Integer> groupTypeIdToLimit = addUserRoleDTO.groupTypeIdToLimit();
        groupTypeIdToLimit = groupTypeIdToLimit == null
                ? Collections.emptyMap()
                : groupTypeIdToLimit;
        Mono<UserRole> userRoleMono = userRoleService.addUserRole(addUserRoleDTO.id(),
                addUserRoleDTO.name(),
                creatableGroupTypesIds,
                addUserRoleDTO.ownedGroupLimit(),
                addUserRoleDTO.ownedGroupLimitForEachGroupType(),
                groupTypeIdToLimit);
        return HttpHandlerResult.okIfTruthy(userRoleMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.USER_ROLE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<UserRole>>>> queryUserRoles(
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<UserRole> userRoleFlux = userRoleService.queryUserRoles(0, size);
        return HttpHandlerResult.okIfTruthy(userRoleFlux);
    }

    @GetMapping("page")
    @RequiredPermission(AdminPermission.USER_ROLE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<UserRole>>>> queryUserRoleGroups(
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = userRoleService.countUserRoles();
        Flux<UserRole> userRoleFlux = userRoleService.queryUserRoles(page, size);
        return HttpHandlerResult.page(count, userRoleFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.USER_ROLE_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateUserRole(
            Set<Long> ids,
            @RequestBody UpdateUserRoleDTO updateUserRoleDTO) {
        Mono<UpdateResultDTO> updateMono = userRoleService
                .updateUserRoles(ids,
                        updateUserRoleDTO.name(),
                        updateUserRoleDTO.creatableGroupTypeIds(),
                        updateUserRoleDTO.ownedGroupLimit(),
                        updateUserRoleDTO.ownedGroupLimitForEachGroupType(),
                        updateUserRoleDTO.groupTypeIdToLimit())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.USER_ROLE_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteUserRole(Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = userRoleService.deleteUserRoles(ids)
                .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}