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

import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupJoinRequestDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupJoinRequestDTO;
import im.turms.service.domain.group.access.admin.dto.response.GroupJoinRequestDTO;
import im.turms.service.domain.group.service.GroupJoinRequestService;
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

import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_JOIN_REQUEST_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_JOIN_REQUEST_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_JOIN_REQUEST_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_JOIN_REQUEST_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups/join-requests")
public class GroupJoinRequestController extends BaseController {

    private final GroupJoinRequestService groupJoinRequestService;

    public GroupJoinRequestController(TurmsPropertiesManager propertiesManager, GroupJoinRequestService groupJoinRequestService) {
        super(propertiesManager);
        this.groupJoinRequestService = groupJoinRequestService;
    }

    @PostMapping
    @RequiredPermission(GROUP_JOIN_REQUEST_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupJoinRequestDTO>>> addGroupJoinRequest(
            @RequestBody AddGroupJoinRequestDTO addGroupJoinRequestDTO) {
        Mono<GroupJoinRequestDTO> createMono = groupJoinRequestService.createGroupJoinRequest(
                        addGroupJoinRequestDTO.id(),
                        addGroupJoinRequestDTO.groupId(),
                        addGroupJoinRequestDTO.requesterId(),
                        addGroupJoinRequestDTO.responderId(),
                        addGroupJoinRequestDTO.content(),
                        addGroupJoinRequestDTO.status(),
                        addGroupJoinRequestDTO.creationDate(),
                        addGroupJoinRequestDTO.responseDate())
                .map(request -> new GroupJoinRequestDTO(request, groupJoinRequestService.getEntityExpirationDate()));
        return ResponseFactory.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(GROUP_JOIN_REQUEST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupJoinRequestDTO>>>> queryGroupJoinRequests(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Set<Long> requesterIds,
            @RequestParam(required = false) Set<Long> responderIds,
            @RequestParam(required = false) Set<RequestStatus> statuses,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(required = false) Date responseDateStart,
            @RequestParam(required = false) Date responseDateEnd,
            @RequestParam(required = false) Date expirationDateStart,
            @RequestParam(required = false) Date expirationDateEnd,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupJoinRequestDTO> joinRequestFlux = groupJoinRequestService.queryJoinRequests(
                        ids,
                        groupIds,
                        requesterIds,
                        responderIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        0,
                        size)
                .map(request -> new GroupJoinRequestDTO(request, groupJoinRequestService.getEntityExpirationDate()));
        return ResponseFactory.okIfTruthy(joinRequestFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(GROUP_JOIN_REQUEST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupJoinRequestDTO>>>> queryGroupJoinRequests(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Set<Long> requesterIds,
            @RequestParam(required = false) Set<Long> responderIds,
            @RequestParam(required = false) Set<RequestStatus> statuses,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(required = false) Date responseDateStart,
            @RequestParam(required = false) Date responseDateEnd,
            @RequestParam(required = false) Date expirationDateStart,
            @RequestParam(required = false) Date expirationDateEnd,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupJoinRequestService.countJoinRequests(
                ids,
                groupIds,
                requesterIds,
                responderIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd));
        Flux<GroupJoinRequestDTO> joinRequestFlux = groupJoinRequestService.queryJoinRequests(
                        ids,
                        groupIds,
                        requesterIds,
                        responderIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        page,
                        size)
                .map(request -> new GroupJoinRequestDTO(request, groupJoinRequestService.getEntityExpirationDate()));
        return ResponseFactory.page(count, joinRequestFlux);
    }

    @PutMapping
    @RequiredPermission(GROUP_JOIN_REQUEST_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateGroupJoinRequests(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateGroupJoinRequestDTO updateGroupJoinRequestDTO) {
        Mono<UpdateResultDTO> updateMono = groupJoinRequestService.updateJoinRequests(
                        ids,
                        updateGroupJoinRequestDTO.requesterId(),
                        updateGroupJoinRequestDTO.responderId(),
                        updateGroupJoinRequestDTO.content(),
                        updateGroupJoinRequestDTO.status(),
                        updateGroupJoinRequestDTO.creationDate(),
                        updateGroupJoinRequestDTO.responseDate())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(GROUP_JOIN_REQUEST_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteGroupJoinRequests(
            @RequestParam(required = false) Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = groupJoinRequestService
                .deleteJoinRequests(ids)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}