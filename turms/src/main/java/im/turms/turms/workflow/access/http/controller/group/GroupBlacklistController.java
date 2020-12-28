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

import im.turms.turms.bo.DateRange;
import im.turms.turms.workflow.access.http.dto.request.group.AddGroupBlacklistedUserDTO;
import im.turms.turms.workflow.access.http.dto.request.group.UpdateGroupBlacklistedUserDTO;
import im.turms.turms.workflow.access.http.dto.response.*;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.group.GroupBlacklistedUser;
import im.turms.turms.workflow.service.impl.group.GroupBlacklistService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.*;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups/blacklisted-users")
public class GroupBlacklistController {

    private final GroupBlacklistService groupBlacklistService;
    private final PageUtil pageUtil;

    public GroupBlacklistController(PageUtil pageUtil, GroupBlacklistService groupBlacklistService) {
        this.pageUtil = pageUtil;
        this.groupBlacklistService = groupBlacklistService;
    }

    @PostMapping
    @RequiredPermission(GROUP_BLACKLIST_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupBlacklistedUser>>> addGroupBlacklistedUser(@RequestBody AddGroupBlacklistedUserDTO addGroupBlacklistedUserDTO) {
        Mono<GroupBlacklistedUser> createMono = groupBlacklistService.addBlacklistedUser(
                addGroupBlacklistedUserDTO.getGroupId(),
                addGroupBlacklistedUserDTO.getUserId(),
                addGroupBlacklistedUserDTO.getRequesterId(),
                addGroupBlacklistedUserDTO.getBlockDate());
        return ResponseFactory.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(GROUP_BLACKLIST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupBlacklistedUser>>>> queryGroupBlacklistedUsers(
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Set<Long> userIds,
            @RequestParam(required = false) Date blockDateStart,
            @RequestParam(required = false) Date blockDateEnd,
            @RequestParam(required = false) Set<Long> requesterIds,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<GroupBlacklistedUser> userFlux = groupBlacklistService.queryBlacklistedUsers(
                groupIds,
                userIds,
                DateRange.of(blockDateStart, blockDateEnd),
                requesterIds,
                0,
                size);
        return ResponseFactory.okIfTruthy(userFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(GROUP_BLACKLIST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupBlacklistedUser>>>> queryGroupBlacklistedUsers(
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Set<Long> userIds,
            @RequestParam(required = false) Date blockDateStart,
            @RequestParam(required = false) Date blockDateEnd,
            @RequestParam(required = false) Set<Long> requesterIds,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = groupBlacklistService.countBlacklistedUsers(groupIds,
                userIds,
                DateRange.of(blockDateStart, blockDateEnd),
                requesterIds);
        Flux<GroupBlacklistedUser> userFlux = groupBlacklistService.queryBlacklistedUsers(
                groupIds,
                userIds,
                DateRange.of(blockDateStart, blockDateEnd),
                requesterIds,
                page,
                size);
        return ResponseFactory.page(count, userFlux);
    }

    @PutMapping
    @RequiredPermission(GROUP_BLACKLIST_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateGroupBlacklistedUsers(
            GroupBlacklistedUser.KeyList keys,
            @RequestBody UpdateGroupBlacklistedUserDTO updateGroupBlacklistedUserDTO) {
        Mono<UpdateResultDTO> updateMono = groupBlacklistService.updateBlacklistedUsers(
                new HashSet<>(keys.getKeys()),
                updateGroupBlacklistedUserDTO.getBlockDate(),
                updateGroupBlacklistedUserDTO.getRequesterId())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(GROUP_BLACKLIST_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteGroupBlacklistedUsers(
            GroupBlacklistedUser.KeyList keys) {
        Mono<DeleteResultDTO> deleteMono = groupBlacklistService
                .deleteBlacklistedUsers(new HashSet<>(keys.getKeys()))
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}
