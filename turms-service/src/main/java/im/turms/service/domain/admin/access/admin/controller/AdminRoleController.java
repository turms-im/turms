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

package im.turms.service.domain.admin.access.admin.controller;

import java.util.Collection;
import java.util.Set;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.PaginationDTO;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.RequestContext;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.admin.access.admin.dto.request.AddAdminRoleDTO;
import im.turms.service.domain.admin.access.admin.dto.request.UpdateAdminRoleDTO;
import im.turms.service.domain.admin.service.AdminRoleService;
import im.turms.service.domain.common.access.admin.controller.BaseController;

/**
 * @author James Chen
 */
@RestController("admins/roles")
public class AdminRoleController extends BaseController {

    private final AdminRoleService adminRoleService;

    public AdminRoleController(
            TurmsPropertiesManager propertiesManager,
            AdminRoleService adminRoleService) {
        super(propertiesManager);
        this.adminRoleService = adminRoleService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<AdminRole>>> addAdminRole(
            RequestContext requestContext,
            @RequestBody AddAdminRoleDTO addAdminRoleDTO) {
        Mono<AdminRole> adminRoleMono =
                adminRoleService.authAndAddAdminRole(requestContext.getAccount(),
                        addAdminRoleDTO.id(),
                        addAdminRoleDTO.name(),
                        addAdminRoleDTO.permissions() == null
                                ? null
                                : AdminPermission.matchPermissions(addAdminRoleDTO.permissions()),
                        addAdminRoleDTO.rank());
        return HttpHandlerResult.okIfTruthy(adminRoleMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<AdminRole>>>> queryAdminRoles(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<String> names,
            @QueryParam(required = false) Set<AdminPermission> includedPermissions,
            @QueryParam(required = false) Set<Integer> ranks,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<AdminRole> adminRolesFlux =
                adminRoleService.queryAdminRoles(ids, names, includedPermissions, ranks, 0, size);
        return HttpHandlerResult.okIfTruthy(adminRolesFlux);
    }

    @GetMapping("page")
    @RequiredPermission(AdminPermission.ADMIN_ROLE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<AdminRole>>>> queryAdminRoles(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<String> names,
            @QueryParam(required = false) Set<AdminPermission> includedPermissions,
            @QueryParam(required = false) Set<Integer> ranks,
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = adminRoleService.countAdminRoles(ids, names, includedPermissions, ranks);
        Flux<AdminRole> adminRolesFlux = adminRoleService
                .queryAdminRoles(ids, names, includedPermissions, ranks, page, size);
        return HttpHandlerResult.page(count, adminRolesFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateAdminRole(
            RequestContext requestContext,
            Set<Long> ids,
            @RequestBody UpdateAdminRoleDTO updateAdminRoleDTO) {
        Mono<UpdateResult> updateMono = adminRoleService.authAndUpdateAdminRole(
                requestContext.getAccount(),
                ids,
                updateAdminRoleDTO.name(),
                updateAdminRoleDTO.permissions() == null
                        ? null
                        : AdminPermission.matchPermissions(updateAdminRoleDTO.permissions()),
                updateAdminRoleDTO.rank());
        return HttpHandlerResult.updateResult(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteAdminRoles(
            RequestContext requestContext,
            Set<Long> ids) {
        Mono<DeleteResult> deleteMono =
                adminRoleService.authAndDeleteAdminRoles(requestContext.getAccount(), ids);
        return HttpHandlerResult.deleteResult(deleteMono);
    }

}