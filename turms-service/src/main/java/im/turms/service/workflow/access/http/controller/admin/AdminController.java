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

package im.turms.service.workflow.access.http.controller.admin;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.access.http.dto.response.DeleteResultDTO;
import im.turms.server.common.access.http.dto.response.PaginationDTO;
import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.access.http.dto.response.UpdateResultDTO;
import im.turms.service.workflow.access.http.dto.request.admin.AddAdminDTO;
import im.turms.service.workflow.access.http.dto.request.admin.UpdateAdminDTO;
import im.turms.service.workflow.access.http.permission.RequiredPermission;
import im.turms.service.workflow.access.http.util.PageUtil;
import im.turms.service.workflow.dao.domain.admin.Admin;
import im.turms.service.workflow.service.impl.admin.AdminService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static im.turms.service.workflow.access.http.permission.AdminPermission.ADMIN_CREATE;
import static im.turms.service.workflow.access.http.permission.AdminPermission.ADMIN_DELETE;
import static im.turms.service.workflow.access.http.permission.AdminPermission.ADMIN_QUERY;
import static im.turms.service.workflow.access.http.permission.AdminPermission.ADMIN_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/admins")
public class AdminController {

    private final AdminService adminService;
    private final PageUtil pageUtil;

    public AdminController(AdminService adminService, PageUtil pageUtil) {
        this.adminService = adminService;
        this.pageUtil = pageUtil;
    }

    @RequestMapping(method = RequestMethod.HEAD)
    public ResponseEntity<Void> checkAccountAndPassword() {
        return ResponseEntity.ok().build();
    }

    @PostMapping
    @RequiredPermission(ADMIN_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Admin>>> addAdmin(
            @RequestAttribute("account") String requesterAccount,
            @RequestBody AddAdminDTO addAdminDTO) {
        Mono<Admin> generatedAdmin = adminService.authAndAddAdmin(
                requesterAccount,
                addAdminDTO.account(),
                addAdminDTO.password(),
                addAdminDTO.roleId(),
                addAdminDTO.name(),
                new Date(),
                false);
        return ResponseFactory.okIfTruthy(generatedAdmin);
    }

    @GetMapping
    @RequiredPermission(ADMIN_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<Admin>>>> queryAdmins(
            @RequestParam(required = false) Set<String> accounts,
            @RequestParam(required = false) Set<Long> roleIds,
            @RequestParam(defaultValue = "false") boolean withPassword,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<Admin> admins = adminService.queryAdmins(accounts, roleIds, 0, size)
                .map(admin -> withPassword
                        ? admin
                        : admin.toBuilder()
                        .password(null)
                        .build());
        return ResponseFactory.okIfTruthy(admins);
    }

    @GetMapping("/page")
    @RequiredPermission(ADMIN_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<Admin>>>> queryAdmins(
            @RequestParam(required = false) Set<String> accounts,
            @RequestParam(required = false) Set<Long> roleIds,
            @RequestParam(defaultValue = "false") boolean withPassword,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = adminService.countAdmins(accounts, roleIds);
        Flux<Admin> admins = adminService.queryAdmins(accounts, roleIds, page, size)
                .map(admin -> withPassword
                        ? admin
                        : admin.toBuilder()
                        .password(null)
                        .build());
        return ResponseFactory.page(count, admins);
    }

    @PutMapping
    @RequiredPermission(ADMIN_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateAdmins(
            @RequestAttribute("account") String requesterAccount,
            @RequestParam Set<String> accounts,
            @RequestBody UpdateAdminDTO updateAdminDTO) {
        Mono<UpdateResult> updateMono = adminService.authAndUpdateAdmins(
                requesterAccount,
                accounts,
                updateAdminDTO.password(),
                updateAdminDTO.name(),
                updateAdminDTO.roleId());
        return ResponseFactory.updateResult(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(ADMIN_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteAdmins(
            @RequestAttribute("account") String requesterAccount,
            @RequestParam Set<String> accounts) {
        Mono<DeleteResult> deleteMono = adminService.authAndDeleteAdmins(requesterAccount, accounts);
        return ResponseFactory.deleteResult(deleteMono);
    }

}
