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
import java.util.Date;
import java.util.List;
import java.util.Set;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.PaginationDTO;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupBlockedUserDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupBlockedUserDTO;
import im.turms.service.domain.group.po.GroupBlockedUser;
import im.turms.service.domain.group.service.GroupBlocklistService;

import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_BLOCKLIST_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_BLOCKLIST_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_BLOCKLIST_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_BLOCKLIST_UPDATE;

/**
 * @author James Chen
 */
@RestController("groups/blocked-users")
public class GroupBlocklistController extends BaseController {

    private final GroupBlocklistService groupBlocklistService;

    public GroupBlocklistController(
            TurmsPropertiesManager propertiesManager,
            GroupBlocklistService groupBlocklistService) {
        super(propertiesManager);
        this.groupBlocklistService = groupBlocklistService;
    }

    @PostMapping
    @RequiredPermission(GROUP_BLOCKLIST_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<GroupBlockedUser>>> addGroupBlockedUser(
            @RequestBody AddGroupBlockedUserDTO addGroupBlockedUserDTO) {
        Mono<GroupBlockedUser> createMono =
                groupBlocklistService.addBlockedUser(addGroupBlockedUserDTO.groupId(),
                        addGroupBlockedUserDTO.userId(),
                        addGroupBlockedUserDTO.requesterId(),
                        addGroupBlockedUserDTO.blockDate());
        return HttpHandlerResult.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(GROUP_BLOCKLIST_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<GroupBlockedUser>>>> queryGroupBlockedUsers(
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Set<Long> userIds,
            @QueryParam(required = false) Date blockDateStart,
            @QueryParam(required = false) Date blockDateEnd,
            @QueryParam(required = false) Set<Long> requesterIds,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupBlockedUser> userFlux = groupBlocklistService.queryBlockedUsers(groupIds,
                userIds,
                DateRange.of(blockDateStart, blockDateEnd),
                requesterIds,
                0,
                size);
        return HttpHandlerResult.okIfTruthy(userFlux);
    }

    @GetMapping("page")
    @RequiredPermission(GROUP_BLOCKLIST_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<GroupBlockedUser>>>> queryGroupBlockedUsers(
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Set<Long> userIds,
            @QueryParam(required = false) Date blockDateStart,
            @QueryParam(required = false) Date blockDateEnd,
            @QueryParam(required = false) Set<Long> requesterIds,
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupBlocklistService.countBlockedUsers(groupIds,
                userIds,
                DateRange.of(blockDateStart, blockDateEnd),
                requesterIds);
        Flux<GroupBlockedUser> userFlux = groupBlocklistService.queryBlockedUsers(groupIds,
                userIds,
                DateRange.of(blockDateStart, blockDateEnd),
                requesterIds,
                page,
                size);
        return HttpHandlerResult.page(count, userFlux);
    }

    @PutMapping
    @RequiredPermission(GROUP_BLOCKLIST_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateGroupBlockedUsers(
            List<GroupBlockedUser.Key> keys,
            @RequestBody UpdateGroupBlockedUserDTO updateGroupBlockedUserDTO) {
        Mono<UpdateResultDTO> updateMono = groupBlocklistService
                .updateBlockedUsers(CollectionUtil.newSet(keys),
                        updateGroupBlockedUserDTO.blockDate(),
                        updateGroupBlockedUserDTO.requesterId())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(GROUP_BLOCKLIST_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteGroupBlockedUsers(
            List<GroupBlockedUser.Key> keys) {
        Mono<DeleteResultDTO> deleteMono =
                groupBlocklistService.deleteBlockedUsers(CollectionUtil.newSet(keys))
                        .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}
