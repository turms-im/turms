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

package im.turms.turms.workflow.access.http.controller.group;

import im.turms.turms.workflow.access.http.dto.request.group.AddGroupTypeDTO;
import im.turms.turms.workflow.access.http.dto.request.group.UpdateGroupTypeDTO;
import im.turms.turms.workflow.access.http.dto.response.DeleteResultDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.dto.response.UpdateResultDTO;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.group.GroupType;
import im.turms.turms.workflow.service.impl.group.GroupTypeService;
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
public class GroupTypeController {

    private final GroupTypeService groupTypeService;
    private final PageUtil pageUtil;

    public GroupTypeController(GroupTypeService groupTypeService, PageUtil pageUtil) {
        this.groupTypeService = groupTypeService;
        this.pageUtil = pageUtil;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupType>>> addGroupType(@RequestBody AddGroupTypeDTO addGroupTypeDTO) {
        Mono<GroupType> addedGroupType = groupTypeService.addGroupType(
                addGroupTypeDTO.getName(),
                addGroupTypeDTO.getGroupSizeLimit(),
                addGroupTypeDTO.getInvitationStrategy(),
                addGroupTypeDTO.getJoinStrategy(),
                addGroupTypeDTO.getGroupInfoUpdateStrategy(),
                addGroupTypeDTO.getMemberInfoUpdateStrategy(),
                addGroupTypeDTO.getGuestSpeakable(),
                addGroupTypeDTO.getSelfInfoUpdatable(),
                addGroupTypeDTO.getEnableReadReceipt(),
                addGroupTypeDTO.getMessageEditable());
        return ResponseFactory.okIfTruthy(addedGroupType);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_TYPE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupType>>>> queryGroupTypes(
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<GroupType> groupTypesFlux = groupTypeService.queryGroupTypes(0, size);
        return ResponseFactory.okIfTruthy(groupTypesFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(AdminPermission.GROUP_TYPE_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupType>>>> queryGroupTypes(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
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
                updateGroupTypeDTO.getName(),
                updateGroupTypeDTO.getGroupSizeLimit(),
                updateGroupTypeDTO.getInvitationStrategy(),
                updateGroupTypeDTO.getJoinStrategy(),
                updateGroupTypeDTO.getGroupInfoUpdateStrategy(),
                updateGroupTypeDTO.getMemberInfoUpdateStrategy(),
                updateGroupTypeDTO.getGuestSpeakable(),
                updateGroupTypeDTO.getSelfInfoUpdatable(),
                updateGroupTypeDTO.getEnableReadReceipt(),
                updateGroupTypeDTO.getMessageEditable())
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