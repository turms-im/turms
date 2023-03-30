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

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.mongodb.client.result.DeleteResult;
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
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupMemberDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupMemberDTO;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.group.service.GroupMemberService;

/**
 * @author James Chen
 */
@RestController("groups/members")
public class GroupMemberController extends BaseController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(
            TurmsPropertiesManager propertiesManager,
            GroupMemberService groupMemberService) {
        super(propertiesManager);
        this.groupMemberService = groupMemberService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<GroupMember>>> addGroupMember(
            @RequestBody AddGroupMemberDTO addGroupMemberDTO) {
        Mono<GroupMember> createMono =
                groupMemberService.addGroupMember(addGroupMemberDTO.groupId(),
                        addGroupMemberDTO.userId(),
                        addGroupMemberDTO.role(),
                        addGroupMemberDTO.name(),
                        addGroupMemberDTO.joinDate(),
                        addGroupMemberDTO.muteEndDate(),
                        null);
        return HttpHandlerResult.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<List<GroupMember>>>> queryGroupMembers(
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Set<Long> userIds,
            @QueryParam(required = false) Set<GroupMemberRole> roles,
            @QueryParam(required = false) Date joinDateStart,
            @QueryParam(required = false) Date joinDateEnd,
            @QueryParam(required = false) Date muteEndDateStart,
            @QueryParam(required = false) Date muteEndDateEnd,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<List<GroupMember>> groupMemberFlux = groupMemberService.queryGroupMembers(groupIds,
                userIds,
                roles,
                DateRange.of(joinDateStart, joinDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                0,
                size);
        return HttpHandlerResult.okIfTruthy(groupMemberFlux);
    }

    @GetMapping("page")
    @RequiredPermission(AdminPermission.GROUP_MEMBER_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<GroupMember>>>> queryGroupMembers(
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Set<Long> userIds,
            @QueryParam(required = false) Set<GroupMemberRole> roles,
            @QueryParam(required = false) Date joinDateStart,
            @QueryParam(required = false) Date joinDateEnd,
            @QueryParam(required = false) Date muteEndDateStart,
            @QueryParam(required = false) Date muteEndDateEnd,
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupMemberService.countMembers(groupIds,
                userIds,
                roles,
                DateRange.of(joinDateStart, joinDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd));
        Mono<List<GroupMember>> userFlux = groupMemberService.queryGroupMembers(groupIds,
                userIds,
                roles,
                DateRange.of(joinDateStart, joinDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                page,
                size);
        return HttpHandlerResult.page(count, userFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateGroupMembers(
            List<GroupMember.Key> keys,
            @RequestBody UpdateGroupMemberDTO updateGroupMemberDTO) {
        Mono<UpdateResultDTO> updateMono = groupMemberService
                .updateGroupMembers(CollectionUtil.newSet(keys),
                        updateGroupMemberDTO.name(),
                        updateGroupMemberDTO.role(),
                        updateGroupMemberDTO.joinDate(),
                        updateGroupMemberDTO.muteEndDate(),
                        null,
                        true)
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteGroupMembers(
            @QueryParam(required = false) List<GroupMember.Key> keys) {
        Mono<DeleteResult> deleteMono = CollectionUtil.isEmpty(keys)
                ? groupMemberService.deleteGroupMembers(true)
                : groupMemberService.deleteGroupMembers(CollectionUtil.newSet(keys), null, true);
        Mono<DeleteResultDTO> mono = deleteMono.map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(mono);
    }

}