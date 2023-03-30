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

package im.turms.service.domain.group.access.admin.controller;

import java.util.Collection;
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
import im.turms.service.domain.group.access.admin.dto.request.AddGroupTypeDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupTypeDTO;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.service.GroupTypeService;

/**
 * @author James Chen
 */
@RestController("groups/types")
public class GroupTypeController extends BaseController {

    private final GroupTypeService groupTypeService;

    public GroupTypeController(
            TurmsPropertiesManager propertiesManager,
            GroupTypeService groupTypeService) {
        super(propertiesManager);
        this.groupTypeService = groupTypeService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<GroupType>>> addGroupType(
            @RequestBody AddGroupTypeDTO addGroupTypeDTO) {
        Mono<GroupType> addedGroupType = groupTypeService.addGroupType(addGroupTypeDTO.name(),
                addGroupTypeDTO.groupSizeLimit(),
                addGroupTypeDTO.invitationStrategy(),
                addGroupTypeDTO.joinStrategy(),
                addGroupTypeDTO.groupInfoUpdateStrategy(),
                addGroupTypeDTO.memberInfoUpdateStrategy(),
                addGroupTypeDTO.guestSpeakable(),
                addGroupTypeDTO.selfInfoUpdatable(),
                addGroupTypeDTO.enableReadReceipt(),
                addGroupTypeDTO.messageEditable());
        return HttpHandlerResult.okIfTruthy(addedGroupType);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<GroupType>>>> queryGroupTypes(
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(0, size);
        return HttpHandlerResult.okIfTruthy(groupTypesFlux);
    }

    @GetMapping("page")
    @RequiredPermission(AdminPermission.GROUP_TYPE_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<GroupType>>>> queryGroupTypes(
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupTypeService.countGroupTypes();
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(page, size);
        return HttpHandlerResult.page(count, groupTypesFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateGroupType(
            Set<Long> ids,
            @RequestBody UpdateGroupTypeDTO updateGroupTypeDTO) {
        Mono<UpdateResultDTO> updateMono = groupTypeService
                .updateGroupTypes(ids,
                        updateGroupTypeDTO.name(),
                        updateGroupTypeDTO.groupSizeLimit(),
                        updateGroupTypeDTO.invitationStrategy(),
                        updateGroupTypeDTO.joinStrategy(),
                        updateGroupTypeDTO.groupInfoUpdateStrategy(),
                        updateGroupTypeDTO.memberInfoUpdateStrategy(),
                        updateGroupTypeDTO.guestSpeakable(),
                        updateGroupTypeDTO.selfInfoUpdatable(),
                        updateGroupTypeDTO.enableReadReceipt(),
                        updateGroupTypeDTO.messageEditable())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteGroupType(Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = groupTypeService.deleteGroupTypes(ids)
                .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}