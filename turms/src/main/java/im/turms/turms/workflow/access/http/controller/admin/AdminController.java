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

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.turms.workflow.access.http.dto.request.admin.AddAdminDTO;
import im.turms.turms.workflow.access.http.dto.request.admin.UpdateAdminDTO;
import im.turms.turms.workflow.access.http.dto.response.*;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.admin.Admin;
import im.turms.turms.workflow.service.impl.admin.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.*;

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
    @RequiredPermission(NONE)
    public Mono<Void> checkAccountAndPassword(
            @RequestHeader String account,
            @RequestHeader String password) {
        if (StringUtils.isBlank(account) || StringUtils.isBlank(password)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return adminService.authenticate(account, password)
                .map(authenticated -> {
                    HttpStatus httpStatus = authenticated != null && authenticated
                            ? HttpStatus.OK
                            : HttpStatus.UNAUTHORIZED;
                    throw new ResponseStatusException(httpStatus);
                });
    }

    @PostMapping
    @RequiredPermission(ADMIN_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Admin>>> addAdmin(
            @RequestHeader("account") String requesterAccount,
            @RequestBody AddAdminDTO addAdminDTO) {
        Mono<Admin> generatedAdmin = adminService.authAndAddAdmin(
                requesterAccount,
                addAdminDTO.getAccount(),
                addAdminDTO.getPassword(),
                addAdminDTO.getRoleId(),
                addAdminDTO.getName(),
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
            @RequestHeader("account") String requesterAccount,
            @RequestParam Set<String> accounts,
            @RequestBody UpdateAdminDTO updateAdminDTO) {
        Mono<UpdateResult> updateMono = adminService.authAndUpdateAdmins(
                requesterAccount,
                accounts,
                updateAdminDTO.getPassword(),
                updateAdminDTO.getName(),
                updateAdminDTO.getRoleId());
        return ResponseFactory.updateResult(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(ADMIN_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteAdmins(
            @RequestHeader("account") String requesterAccount,
            @RequestParam Set<String> accounts) {
        Mono<DeleteResult> deleteMono = adminService.authAndDeleteAdmins(requesterAccount, accounts);
        return ResponseFactory.deleteResult(deleteMono);
    }

}
