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
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupInvitationDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupInvitationDTO;
import im.turms.service.domain.group.access.admin.dto.response.GroupInvitationDTO;
import im.turms.service.domain.group.service.GroupInvitationService;

import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_INVITATION_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_INVITATION_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_INVITATION_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.GROUP_INVITATION_UPDATE;

/**
 * @author James Chen
 */
@RestController("groups/invitations")
public class GroupInvitationController extends BaseController {

    private final GroupInvitationService groupInvitationService;

    public GroupInvitationController(
            TurmsPropertiesManager propertiesManager,
            GroupInvitationService groupInvitationService) {
        super(propertiesManager);
        this.groupInvitationService = groupInvitationService;
    }

    @PostMapping
    @RequiredPermission(GROUP_INVITATION_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<GroupInvitationDTO>>> addGroupInvitation(
            @RequestBody AddGroupInvitationDTO addGroupInvitationDTO) {
        Mono<GroupInvitationDTO> createMono = groupInvitationService
                .createGroupInvitation(addGroupInvitationDTO.id(),
                        addGroupInvitationDTO.groupId(),
                        addGroupInvitationDTO.inviterId(),
                        addGroupInvitationDTO.inviteeId(),
                        addGroupInvitationDTO.content(),
                        addGroupInvitationDTO.status(),
                        addGroupInvitationDTO.creationDate(),
                        addGroupInvitationDTO.responseDate())
                .map(invitation -> new GroupInvitationDTO(
                        invitation,
                        groupInvitationService.getEntityExpirationDate()));
        return HttpHandlerResult.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(GROUP_INVITATION_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<GroupInvitationDTO>>>> queryGroupInvitations(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Set<Long> inviterIds,
            @QueryParam(required = false) Set<Long> inviteeIds,
            @QueryParam(required = false) Set<RequestStatus> statuses,
            @QueryParam(required = false) Date creationDateStart,
            @QueryParam(required = false) Date creationDateEnd,
            @QueryParam(required = false) Date responseDateStart,
            @QueryParam(required = false) Date responseDateEnd,
            @QueryParam(required = false) Date expirationDateStart,
            @QueryParam(required = false) Date expirationDateEnd,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupInvitationDTO> invitationFlux = groupInvitationService
                .queryInvitations(ids,
                        groupIds,
                        inviterIds,
                        inviteeIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        0,
                        size)
                .map(invitation -> new GroupInvitationDTO(
                        invitation,
                        groupInvitationService.getEntityExpirationDate()));
        return HttpHandlerResult.okIfTruthy(invitationFlux);
    }

    @GetMapping("page")
    @RequiredPermission(GROUP_INVITATION_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<GroupInvitationDTO>>>> queryGroupInvitations(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Set<Long> inviterIds,
            @QueryParam(required = false) Set<Long> inviteeIds,
            @QueryParam(required = false) Set<RequestStatus> statuses,
            @QueryParam(required = false) Date creationDateStart,
            @QueryParam(required = false) Date creationDateEnd,
            @QueryParam(required = false) Date responseDateStart,
            @QueryParam(required = false) Date responseDateEnd,
            @QueryParam(required = false) Date expirationDateStart,
            @QueryParam(required = false) Date expirationDateEnd,
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupInvitationService.countInvitations(ids,
                groupIds,
                inviterIds,
                inviteeIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd));
        Flux<GroupInvitationDTO> invitationFlux = groupInvitationService
                .queryInvitations(ids,
                        groupIds,
                        inviterIds,
                        inviteeIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        page,
                        size)
                .map(invitation -> new GroupInvitationDTO(
                        invitation,
                        groupInvitationService.getEntityExpirationDate()));
        return HttpHandlerResult.page(count, invitationFlux);
    }

    @PutMapping
    @RequiredPermission(GROUP_INVITATION_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateGroupInvitations(
            Set<Long> ids,
            @RequestBody UpdateGroupInvitationDTO updateGroupInvitationDTO) {
        Mono<UpdateResultDTO> updateMono = groupInvitationService
                .updateInvitations(ids,
                        updateGroupInvitationDTO.inviterId(),
                        updateGroupInvitationDTO.inviteeId(),
                        updateGroupInvitationDTO.content(),
                        updateGroupInvitationDTO.status(),
                        updateGroupInvitationDTO.creationDate(),
                        updateGroupInvitationDTO.responseDate())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(GROUP_INVITATION_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteGroupInvitations(
            @QueryParam(required = false) Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = groupInvitationService.deleteInvitations(ids)
                .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}