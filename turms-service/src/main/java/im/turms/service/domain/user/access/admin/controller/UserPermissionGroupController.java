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
import im.turms.service.domain.user.access.admin.dto.request.AddUserPermissionGroupDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateUserPermissionGroupDTO;
import im.turms.service.domain.user.po.UserPermissionGroup;
import im.turms.service.domain.user.service.UserPermissionGroupService;

/**
 * @author James Chen
 */
@RestController("users/permission-groups")
public class UserPermissionGroupController extends BaseController {

    private final UserPermissionGroupService userPermissionGroupService;

    public UserPermissionGroupController(
            TurmsPropertiesManager propertiesManager,
            UserPermissionGroupService userPermissionGroupService) {
        super(propertiesManager);
        this.userPermissionGroupService = userPermissionGroupService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<UserPermissionGroup>>> addUserPermissionGroup(
            @RequestBody AddUserPermissionGroupDTO addUserPermissionGroupDTO) {
        Set<Long> creatableGroupTypesIds = addUserPermissionGroupDTO.creatableGroupTypeIds();
        creatableGroupTypesIds = creatableGroupTypesIds == null
                ? Collections.emptySet()
                : creatableGroupTypesIds;
        Map<Long, Integer> groupTypeIdToLimit = addUserPermissionGroupDTO.groupTypeIdToLimit();
        groupTypeIdToLimit = groupTypeIdToLimit == null
                ? Collections.emptyMap()
                : groupTypeIdToLimit;
        Mono<UserPermissionGroup> userPermissionGroupMono =
                userPermissionGroupService.addUserPermissionGroup(addUserPermissionGroupDTO.id(),
                        creatableGroupTypesIds,
                        addUserPermissionGroupDTO.ownedGroupLimit(),
                        addUserPermissionGroupDTO.ownedGroupLimitForEachGroupType(),
                        groupTypeIdToLimit);
        return HttpHandlerResult.okIfTruthy(userPermissionGroupMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<UserPermissionGroup>>>> queryUserPermissionGroups(
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<UserPermissionGroup> groupTypesFlux =
                userPermissionGroupService.queryUserPermissionGroups(0, size);
        return HttpHandlerResult.okIfTruthy(groupTypesFlux);
    }

    @GetMapping("page")
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<UserPermissionGroup>>>> queryUserPermissionGroups(
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = userPermissionGroupService.countUserPermissionGroups();
        Flux<UserPermissionGroup> groupTypesFlux =
                userPermissionGroupService.queryUserPermissionGroups(page, size);
        return HttpHandlerResult.page(count, groupTypesFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateUserPermissionGroup(
            Set<Long> ids,
            @RequestBody UpdateUserPermissionGroupDTO updateUserPermissionGroupDTO) {
        Mono<UpdateResultDTO> updateMono = userPermissionGroupService
                .updateUserPermissionGroups(ids,
                        updateUserPermissionGroupDTO.creatableGroupTypeIds(),
                        updateUserPermissionGroupDTO.ownedGroupLimit(),
                        updateUserPermissionGroupDTO.ownedGroupLimitForEachGroupType(),
                        updateUserPermissionGroupDTO.groupTypeIdToLimit())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.USER_PERMISSION_GROUP_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteUserPermissionGroup(
            Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono =
                userPermissionGroupService.deleteUserPermissionGroups(ids)
                        .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}