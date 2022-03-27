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

import com.mongodb.client.result.DeleteResult;
import im.turms.common.constant.GroupMemberRole;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupMemberDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupMemberDTO;
import im.turms.service.domain.group.po.GroupMember;
import im.turms.service.domain.group.service.GroupMemberService;
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
import java.util.Date;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups/members")
public class GroupMemberController extends BaseController {

    private final GroupMemberService groupMemberService;

    public GroupMemberController(Node node, GroupMemberService groupMemberService) {
        super(node);
        this.groupMemberService = groupMemberService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupMember>>> addGroupMember(@RequestBody AddGroupMemberDTO addGroupMemberDTO) {
        Mono<GroupMember> createMono = groupMemberService.addGroupMember(
                addGroupMemberDTO.groupId(),
                addGroupMemberDTO.userId(),
                addGroupMemberDTO.role(),
                addGroupMemberDTO.name(),
                addGroupMemberDTO.joinDate(),
                addGroupMemberDTO.muteEndDate(),
                null);
        return ResponseFactory.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupMember>>>> queryGroupMembers(
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Set<Long> userIds,
            @RequestParam(required = false) Set<GroupMemberRole> roles,
            @RequestParam(required = false) Date joinDateStart,
            @RequestParam(required = false) Date joinDateEnd,
            @RequestParam(required = false) Date muteEndDateStart,
            @RequestParam(required = false) Date muteEndDateEnd,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupMember> groupMemberFlux = groupMemberService.queryGroupsMembers(
                groupIds,
                userIds,
                roles,
                DateRange.of(joinDateStart, joinDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                0,
                size);
        return ResponseFactory.okIfTruthy(groupMemberFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(AdminPermission.GROUP_MEMBER_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupMember>>>> queryGroupMembers(
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Set<Long> userIds,
            @RequestParam(required = false) Set<GroupMemberRole> roles,
            @RequestParam(required = false) Date joinDateStart,
            @RequestParam(required = false) Date joinDateEnd,
            @RequestParam(required = false) Date muteEndDateStart,
            @RequestParam(required = false) Date muteEndDateEnd,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupMemberService.countMembers(
                groupIds,
                userIds,
                roles,
                DateRange.of(joinDateStart, joinDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd));
        Flux<GroupMember> userFlux = groupMemberService.queryGroupsMembers(
                groupIds,
                userIds,
                roles,
                DateRange.of(joinDateStart, joinDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                page,
                size);
        return ResponseFactory.page(count, userFlux);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateGroupMembers(
            GroupMember.KeyList keys,
            @RequestBody UpdateGroupMemberDTO updateGroupMemberDTO) {
        Mono<UpdateResultDTO> updateMono = groupMemberService.updateGroupMembers(
                        CollectionUtil.newSet(keys.getKeys()),
                        updateGroupMemberDTO.name(),
                        updateGroupMemberDTO.role(),
                        updateGroupMemberDTO.joinDate(),
                        updateGroupMemberDTO.muteEndDate(),
                        null,
                        true)
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteGroupMembers(
            GroupMember.KeyList keys) {
        Mono<DeleteResult> deleteMono = keys != null && !keys.getKeys().isEmpty()
                ? groupMemberService.deleteGroupMembers(CollectionUtil.newSet(keys.getKeys()), null, true)
                : groupMemberService.deleteGroupMembers(true);
        Mono<DeleteResultDTO> mono = deleteMono.map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(mono);
    }

}