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

import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.user.access.admin.dto.request.AddFriendRequestDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateFriendRequestDTO;
import im.turms.service.domain.user.access.admin.dto.response.UserFriendRequestDTO;
import im.turms.service.domain.user.service.UserFriendRequestService;
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

import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_FRIEND_REQUEST_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/relationships/friend-requests")
public class UserFriendRequestController extends BaseController {

    private final UserFriendRequestService userFriendRequestService;

    public UserFriendRequestController(Node node, UserFriendRequestService userFriendRequestService) {
        super(node);
        this.userFriendRequestService = userFriendRequestService;
    }

    @PostMapping
    @RequiredPermission(USER_FRIEND_REQUEST_CREATE)
    public Mono<ResponseEntity<ResponseDTO<UserFriendRequestDTO>>> createFriendRequest(
            @RequestBody AddFriendRequestDTO addFriendRequestDTO) {
        Mono<UserFriendRequestDTO> createMono = userFriendRequestService.createFriendRequest(
                        addFriendRequestDTO.id(),
                        addFriendRequestDTO.requesterId(),
                        addFriendRequestDTO.recipientId(),
                        addFriendRequestDTO.content(),
                        addFriendRequestDTO.status(),
                        addFriendRequestDTO.creationDate(),
                        addFriendRequestDTO.responseDate(),
                        addFriendRequestDTO.reason())
                .map(request -> new UserFriendRequestDTO(request, userFriendRequestService.getEntityExpirationDate()));
        return ResponseFactory.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(USER_FRIEND_REQUEST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<UserFriendRequestDTO>>>> queryFriendRequests(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> requesterIds,
            @RequestParam(required = false) Set<Long> recipientIds,
            @RequestParam(required = false) Set<RequestStatus> statuses,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(required = false) Date responseDateStart,
            @RequestParam(required = false) Date responseDateEnd,
            @RequestParam(required = false) Date expirationDateStart,
            @RequestParam(required = false) Date expirationDateEnd,
            @RequestParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<UserFriendRequestDTO> userFriendRequestFlux = userFriendRequestService.queryFriendRequests(
                        ids,
                        requesterIds,
                        recipientIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        0,
                        size)
                .map(request -> new UserFriendRequestDTO(request, userFriendRequestService.getEntityExpirationDate()));
        return ResponseFactory.okIfTruthy(userFriendRequestFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(USER_FRIEND_REQUEST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<UserFriendRequestDTO>>>> queryFriendRequests(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> requesterIds,
            @RequestParam(required = false) Set<Long> recipientIds,
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
        Mono<Long> count = userFriendRequestService.countFriendRequests(
                ids,
                requesterIds,
                recipientIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd));
        Flux<UserFriendRequestDTO> userFriendRequestFlux = userFriendRequestService.queryFriendRequests(
                        ids,
                        requesterIds,
                        recipientIds,
                        statuses,
                        DateRange.of(creationDateStart, creationDateEnd),
                        DateRange.of(responseDateStart, responseDateEnd),
                        DateRange.of(expirationDateStart, expirationDateEnd),
                        page,
                        size)
                .map(request -> new UserFriendRequestDTO(request, userFriendRequestService.getEntityExpirationDate()));
        return ResponseFactory.page(count, userFriendRequestFlux);
    }

    @PutMapping
    @RequiredPermission(USER_FRIEND_REQUEST_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateFriendRequests(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateFriendRequestDTO updateFriendRequestDTO) {
        Mono<UpdateResultDTO> updateMono = userFriendRequestService.updateFriendRequests(
                        ids,
                        updateFriendRequestDTO.requesterId(),
                        updateFriendRequestDTO.recipientId(),
                        updateFriendRequestDTO.content(),
                        updateFriendRequestDTO.status(),
                        updateFriendRequestDTO.reason(),
                        updateFriendRequestDTO.creationDate(),
                        updateFriendRequestDTO.responseDate())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_FRIEND_REQUEST_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteFriendRequests(@RequestParam(required = false) Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = userFriendRequestService
                .deleteFriendRequests(ids)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}
