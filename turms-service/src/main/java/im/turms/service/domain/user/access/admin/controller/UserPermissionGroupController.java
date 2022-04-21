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

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.user.access.admin.dto.request.AddUserPermissionGroupDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateUserPermissionGroupDTO;
import im.turms.service.domain.user.po.UserPermissionGroup;
import im.turms.service.domain.user.service.UserPermissionGroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/permission-groups")
public class UserPermissionGroupController extends BaseController {

    private final UserPermissionGroupService userPermissionGroupService;

    public UserPermissionGroupController(TurmsPropertiesManager propertiesManager, UserPermissionGroupService userPermissionGroupService) {
        super(propertiesManager);
        this.userPermissionGroupService = userPermissionGroupService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_CREATE)
    public Mono<ResponseEntity<ResponseDTO<UserPermissionGroup>>> addUserPermissionGroup(
            @RequestBody AddUserPermissionGroupDTO addUserPermissionGroupDTO) {
        Set<Long> creatableGroupTypesIds = addUserPermissionGroupDTO.creatableGroupTypeIds();
        creatableGroupTypesIds = creatableGroupTypesIds == null ? Collections.emptySet() : creatableGroupTypesIds;
        Map<Long, Integer> groupTypeIdToLimit = addUserPermissionGroupDTO.groupTypeIdToLimit();
        groupTypeIdToLimit = groupTypeIdToLimit == null ? Collections.emptyMap() : groupTypeIdToLimit;
        Mono<UserPermissionGroup> userPermissionGroupMono = userPermissionGroupService.addUserPermissionGroup(
                addUserPermissionGroupDTO.id(),
                creatableGroupTypesIds,
                addUserPermissionGroupDTO.ownedGroupLimit(),
                addUserPermissionGroupDTO.ownedGroupLimitForEachGroupType(),
                groupTypeIdToLimit);
        return ResponseFactory.okIfTruthy(userPermissionGroupMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<UserPermissionGroup>>>> queryUserPermissionGroups(
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<UserPermissionGroup> groupTypesFlux = userPermissionGroupService.queryUserPermissionGroups(0, size);
        return ResponseFactory.okIfTruthy(groupTypesFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<UserPermissionGroup>>>> queryUserPermissionGroups(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = userPermissionGroupService.countUserPermissionGroups();
        Flux<UserPermissionGroup> groupTypesFlux = userPermissionGroupService.queryUserPermissionGroups(page, size);
        return ResponseFactory.page(count, groupTypesFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateUserPermissionGroup(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateUserPermissionGroupDTO updateUserPermissionGroupDTO) {
        Mono<UpdateResultDTO> updateMono = userPermissionGroupService.updateUserPermissionGroups(
                        ids,
                        updateUserPermissionGroupDTO.creatableGroupTypeIds(),
                        updateUserPermissionGroupDTO.ownedGroupLimit(),
                        updateUserPermissionGroupDTO.ownedGroupLimitForEachGroupType(),
                        updateUserPermissionGroupDTO.groupTypeIdToLimit())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteUserPermissionGroup(@RequestParam Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = userPermissionGroupService
                .deleteUserPermissionGroups(ids)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}