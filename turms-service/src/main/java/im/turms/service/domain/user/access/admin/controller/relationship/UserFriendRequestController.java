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

package im.turms.service.domain.user.access.admin.controller.relationship;

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
import im.turms.service.domain.user.access.admin.dto.request.AddFriendRequestDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateFriendRequestDTO;
import im.turms.service.domain.user.access.admin.dto.response.UserFriendRequestDTO;
import im.turms.service.domain.user.service.UserFriendRequestService;

import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_UPDATE;

/**
 * @author James Chen
 */
@RestController("users/relationships/friend-requests")
public class UserFriendRequestController extends BaseController {

    private final UserFriendRequestService userFriendRequestService;

    public UserFriendRequestController(
            TurmsPropertiesManager propertiesManager,
            UserFriendRequestService userFriendRequestService) {
        super(propertiesManager);
        this.userFriendRequestService = userFriendRequestService;
    }

    @PostMapping
    @RequiredPermission(USER_FRIEND_REQUEST_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<UserFriendRequestDTO>>> createFriendRequest(
            @RequestBody AddFriendRequestDTO addFriendRequestDTO) {
        Mono<UserFriendRequestDTO> createMono = userFriendRequestService
                .createFriendRequest(addFriendRequestDTO.id(),
                        addFriendRequestDTO.requesterId(),
                        addFriendRequestDTO.recipientId(),
                        addFriendRequestDTO.content(),
                        addFriendRequestDTO.status(),
                        addFriendRequestDTO.creationDate(),
                        addFriendRequestDTO.responseDate(),
                        addFriendRequestDTO.reason())
                .map(request -> new UserFriendRequestDTO(
                        request,
                        userFriendRequestService.getEntityExpirationDate()));
        return HttpHandlerResult.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(USER_FRIEND_REQUEST_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<UserFriendRequestDTO>>>> queryFriendRequests(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<Long> requesterIds,
            @QueryParam(required = false) Set<Long> recipientIds,
            @QueryParam(required = false) Set<RequestStatus> statuses,
            @QueryParam(required = false) Date creationDateStart,
            @QueryParam(required = false) Date creationDateEnd,
            @QueryParam(required = false) Date responseDateStart,
            @QueryParam(required = false) Date responseDateEnd,
            @QueryParam(required = false) Date expirationDateStart,
            @QueryParam(required = false) Date expirationDateEnd,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<UserFriendRequestDTO> userFriendRequestFlux = userFriendRequestService
                .queryFriendRequests(ids,
                        requesterIds,
                        recipientIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        0,
                        size)
                .map(request -> new UserFriendRequestDTO(
                        request,
                        userFriendRequestService.getEntityExpirationDate()));
        return HttpHandlerResult.okIfTruthy(userFriendRequestFlux);
    }

    @GetMapping("page")
    @RequiredPermission(USER_FRIEND_REQUEST_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<UserFriendRequestDTO>>>> queryFriendRequests(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<Long> requesterIds,
            @QueryParam(required = false) Set<Long> recipientIds,
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
        Mono<Long> count = userFriendRequestService.countFriendRequests(ids,
                requesterIds,
                recipientIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd));
        Flux<UserFriendRequestDTO> userFriendRequestFlux = userFriendRequestService
                .queryFriendRequests(ids,
                        requesterIds,
                        recipientIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        page,
                        size)
                .map(request -> new UserFriendRequestDTO(
                        request,
                        userFriendRequestService.getEntityExpirationDate()));
        return HttpHandlerResult.page(count, userFriendRequestFlux);
    }

    @PutMapping
    @RequiredPermission(USER_FRIEND_REQUEST_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateFriendRequests(
            Set<Long> ids,
            @RequestBody UpdateFriendRequestDTO updateFriendRequestDTO) {
        Mono<UpdateResultDTO> updateMono = userFriendRequestService
                .updateFriendRequests(ids,
                        updateFriendRequestDTO.requesterId(),
                        updateFriendRequestDTO.recipientId(),
                        updateFriendRequestDTO.content(),
                        updateFriendRequestDTO.status(),
                        updateFriendRequestDTO.reason(),
                        updateFriendRequestDTO.creationDate(),
                        updateFriendRequestDTO.responseDate())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_FRIEND_REQUEST_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteFriendRequests(
            @QueryParam(required = false) Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = userFriendRequestService.deleteFriendRequests(ids)
                .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}
