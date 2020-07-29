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

package im.turms.turms.workflow.access.http.controller.admin;

import im.turms.turms.workflow.access.http.dto.request.admin.AddAdminRoleDTO;
import im.turms.turms.workflow.access.http.dto.request.admin.UpdateAdminRoleDTO;
import im.turms.turms.workflow.access.http.dto.response.AcknowledgedDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.AdminRole;
import im.turms.turms.workflow.service.impl.admin.AdminRoleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/admins/roles")
public class AdminRoleController {

    private final AdminRoleService adminRoleService;
    private final PageUtil pageUtil;

    public AdminRoleController(AdminRoleService adminRoleService, PageUtil pageUtil) {
        this.adminRoleService = adminRoleService;
        this.pageUtil = pageUtil;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_CREATE)
    public Mono<ResponseEntity<ResponseDTO<AdminRole>>> addAdminRole(
            @RequestHeader("account") String requesterAccount,
            @RequestBody AddAdminRoleDTO addAdminRoleDTO) {
        Mono<AdminRole> adminRoleMono = adminRoleService.authAndAddAdminRole(
                requesterAccount,
                addAdminRoleDTO.getId(),
                addAdminRoleDTO.getName(),
                addAdminRoleDTO.getPermissions(),
                addAdminRoleDTO.getRank());
        return ResponseFactory.okIfTruthy(adminRoleMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<AdminRole>>>> queryAdminRoles(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<String> names,
            @RequestParam(required = false) Set<AdminPermission> includedPermissions,
            @RequestParam(required = false) Set<Integer> ranks,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<AdminRole> adminRolesFlux = adminRoleService.queryAdminRoles(
                ids,
                names,
                includedPermissions,
                ranks,
                0,
                size);
        return ResponseFactory.okIfTruthy(adminRolesFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(AdminPermission.ADMIN_ROLE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<AdminRole>>>> queryAdminRoles(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<String> names,
            @RequestParam(required = false) Set<AdminPermission> includedPermissions,
            @RequestParam(required = false) Set<Integer> ranks,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = adminRoleService.countAdminRoles(
                ids,
                names,
                includedPermissions,
                ranks);
        Flux<AdminRole> adminRolesFlux = adminRoleService.queryAdminRoles(
                ids,
                names,
                includedPermissions,
                ranks,
                page,
                size);
        return ResponseFactory.page(count, adminRolesFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> updateAdminRole(
            @RequestHeader("account") String requesterAccount,
            @RequestParam Set<Long> ids,
            @RequestBody UpdateAdminRoleDTO updateAdminRoleDTO) {
        Mono<Boolean> updateMono = adminRoleService.authAndUpdateAdminRole(
                requesterAccount,
                ids,
                updateAdminRoleDTO.getName(),
                updateAdminRoleDTO.getPermissions(),
                updateAdminRoleDTO.getRank());
        return ResponseFactory.acknowledged(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.ADMIN_ROLE_DELETE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> deleteAdminRoles(
            @RequestHeader("account") String requesterAccount,
            @RequestParam Set<Long> ids) {
        Mono<Boolean> deleteMono = adminRoleService.authAndDeleteAdminRoles(requesterAccount, ids);
        return ResponseFactory.acknowledged(deleteMono);
    }

}
