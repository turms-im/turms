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
import java.util.Date;
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
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.RequestContext;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.HeadMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.admin.access.admin.dto.request.AddAdminDTO;
import im.turms.service.domain.admin.access.admin.dto.request.UpdateAdminDTO;
import im.turms.service.domain.admin.service.AdminService;
import im.turms.service.domain.common.access.admin.controller.BaseController;

import static im.turms.server.common.access.admin.permission.AdminPermission.ADMIN_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.ADMIN_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.ADMIN_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.ADMIN_UPDATE;

/**
 * @author James Chen
 */
@RestController("admins")
public class AdminController extends BaseController {

    private final AdminService adminService;

    public AdminController(TurmsPropertiesManager propertiesManager, AdminService adminService) {
        super(propertiesManager);
        this.adminService = adminService;
    }

    @HeadMapping
    public HttpHandlerResult<Void> checkAccountAndPassword() {
        return HttpHandlerResult.OK;
    }

    @PostMapping
    @RequiredPermission(ADMIN_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<Admin>>> addAdmin(
            RequestContext requestContext,
            @RequestBody AddAdminDTO addAdminDTO) {
        Mono<Admin> generatedAdmin = adminService.authAndAddAdmin(requestContext.getAccount(),
                addAdminDTO.account(),
                addAdminDTO.password(),
                addAdminDTO.roleId(),
                addAdminDTO.name(),
                new Date(),
                false);
        return HttpHandlerResult.okIfTruthy(generatedAdmin);
    }

    @GetMapping
    @RequiredPermission(ADMIN_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<Admin>>>> queryAdmins(
            @QueryParam(required = false) Set<String> accounts,
            @QueryParam(required = false) Set<Long> roleIds,
            boolean withPassword,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<Admin> admins = adminService.queryAdmins(accounts, roleIds, 0, size)
                .map(admin -> withPassword
                        ? admin
                        : admin.toBuilder()
                                .password(null)
                                .build());
        return HttpHandlerResult.okIfTruthy(admins);
    }

    @GetMapping("page")
    @RequiredPermission(ADMIN_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<Admin>>>> queryAdmins(
            @QueryParam(required = false) Set<String> accounts,
            @QueryParam(required = false) Set<Long> roleIds,
            boolean withPassword,
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = adminService.countAdmins(accounts, roleIds);
        Flux<Admin> admins = adminService.queryAdmins(accounts, roleIds, page, size)
                .map(admin -> withPassword
                        ? admin
                        : admin.toBuilder()
                                .password(null)
                                .build());
        return HttpHandlerResult.page(count, admins);
    }

    @PutMapping
    @RequiredPermission(ADMIN_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateAdmins(
            RequestContext requestContext,
            Set<String> accounts,
            @RequestBody UpdateAdminDTO updateAdminDTO) {
        Mono<UpdateResult> updateMono =
                adminService.authAndUpdateAdmins(requestContext.getAccount(),
                        accounts,
                        updateAdminDTO.password(),
                        updateAdminDTO.name(),
                        updateAdminDTO.roleId());
        return HttpHandlerResult.updateResult(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(ADMIN_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteAdmins(
            RequestContext requestContext,
            Set<String> accounts) {
        Mono<DeleteResult> deleteMono =
                adminService.authAndDeleteAdmins(requestContext.getAccount(), accounts);
        return HttpHandlerResult.deleteResult(deleteMono);
    }

}
