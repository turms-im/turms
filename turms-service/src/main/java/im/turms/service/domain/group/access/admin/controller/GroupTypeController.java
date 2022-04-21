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

import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupTypeDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupTypeDTO;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.service.GroupTypeService;
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
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups/types")
public class GroupTypeController extends BaseController {

    private final GroupTypeService groupTypeService;

    public GroupTypeController(TurmsPropertiesManager propertiesManager, GroupTypeService groupTypeService) {
        super(propertiesManager);
        this.groupTypeService = groupTypeService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupType>>> addGroupType(@RequestBody AddGroupTypeDTO addGroupTypeDTO) {
        Mono<GroupType> addedGroupType = groupTypeService.addGroupType(
                addGroupTypeDTO.name(),
                addGroupTypeDTO.groupSizeLimit(),
                addGroupTypeDTO.invitationStrategy(),
                addGroupTypeDTO.joinStrategy(),
                addGroupTypeDTO.groupInfoUpdateStrategy(),
                addGroupTypeDTO.memberInfoUpdateStrategy(),
                addGroupTypeDTO.guestSpeakable(),
                addGroupTypeDTO.selfInfoUpdatable(),
                addGroupTypeDTO.enableReadReceipt(),
                addGroupTypeDTO.messageEditable());
        return ResponseFactory.okIfTruthy(addedGroupType);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupType>>>> queryGroupTypes(
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(0, size);
        return ResponseFactory.okIfTruthy(groupTypesFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(AdminPermission.GROUP_TYPE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupType>>>> queryGroupTypes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupTypeService.countGroupTypes();
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(page, size);
        return ResponseFactory.page(count, groupTypesFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateGroupType(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateGroupTypeDTO updateGroupTypeDTO) {
        Mono<UpdateResultDTO> updateMono = groupTypeService.updateGroupTypes(
                        ids,
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
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteGroupType(@RequestParam Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = groupTypeService
                .deleteGroupTypes(ids)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}