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

import im.turms.common.constant.GroupMemberRole;
import im.turms.turms.bo.DateRange;
import im.turms.turms.workflow.access.http.dto.request.group.AddGroupMemberDTO;
import im.turms.turms.workflow.access.http.dto.request.group.UpdateGroupMemberDTO;
import im.turms.turms.workflow.access.http.dto.response.AcknowledgedDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.GroupMember;
import im.turms.turms.workflow.service.impl.group.GroupMemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups/members")
public class GroupMemberController {

    private final GroupMemberService groupMemberService;
    private final PageUtil pageUtil;

    public GroupMemberController(PageUtil pageUtil, GroupMemberService groupMemberService) {
        this.pageUtil = pageUtil;
        this.groupMemberService = groupMemberService;
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupMember>>> addGroupMember(@RequestBody AddGroupMemberDTO addGroupMemberDTO) {
        Mono<GroupMember> createMono = groupMemberService.addGroupMember(
                addGroupMemberDTO.getGroupId(),
                addGroupMemberDTO.getUserId(),
                addGroupMemberDTO.getRole(),
                addGroupMemberDTO.getName(),
                addGroupMemberDTO.getJoinDate(),
                addGroupMemberDTO.getMuteEndDate(),
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
        size = pageUtil.getSize(size);
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
        size = pageUtil.getSize(size);
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
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> updateGroupMembers(
            GroupMember.KeyList keys,
            @RequestBody UpdateGroupMemberDTO updateGroupMemberDTO) {
        Mono<Boolean> updateMono = groupMemberService.updateGroupMembers(
                new HashSet<>(keys.getKeys()),
                updateGroupMemberDTO.getName(),
                updateGroupMemberDTO.getRole(),
                updateGroupMemberDTO.getJoinDate(),
                updateGroupMemberDTO.getMuteEndDate(),
                null,
                true);
        return ResponseFactory.acknowledged(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_MEMBER_DELETE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> deleteGroupMembers(
            GroupMember.KeyList keys) {
        Mono<Boolean> deleteMono = keys != null && !keys.getKeys().isEmpty()
                ? groupMemberService.deleteGroupsMembers(new HashSet<>(keys.getKeys()), true)
                : groupMemberService.deleteGroupMembers(true);
        return ResponseFactory.acknowledged(deleteMono);
    }

}
