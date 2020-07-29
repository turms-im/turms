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

package im.turms.turms.workflow.access.http.controller.user.relationship;

import im.turms.common.constant.RequestStatus;
import im.turms.turms.bo.DateRange;
import im.turms.turms.workflow.access.http.dto.request.user.AddFriendRequestDTO;
import im.turms.turms.workflow.access.http.dto.request.user.UpdateFriendRequestDTO;
import im.turms.turms.workflow.access.http.dto.response.AcknowledgedDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.UserFriendRequest;
import im.turms.turms.workflow.service.impl.user.relationship.UserFriendRequestService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.*;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/relationships/friend-requests")
public class UserFriendRequestController {

    private final UserFriendRequestService userFriendRequestService;
    private final PageUtil pageUtil;

    public UserFriendRequestController(PageUtil pageUtil, UserFriendRequestService userFriendRequestService) {
        this.pageUtil = pageUtil;
        this.userFriendRequestService = userFriendRequestService;
    }

    @PostMapping
    @RequiredPermission(USER_FRIEND_REQUEST_CREATE)
    public Mono<ResponseEntity<ResponseDTO<UserFriendRequest>>> createFriendRequest(@RequestBody AddFriendRequestDTO addFriendRequestDTO) {
        Mono<UserFriendRequest> createMono = userFriendRequestService.createFriendRequest(
                addFriendRequestDTO.getId(),
                addFriendRequestDTO.getRequesterId(),
                addFriendRequestDTO.getRecipientId(),
                addFriendRequestDTO.getContent(),
                addFriendRequestDTO.getStatus(),
                addFriendRequestDTO.getCreationDate(),
                addFriendRequestDTO.getResponseDate(),
                addFriendRequestDTO.getExpirationDate(),
                addFriendRequestDTO.getReason());
        return ResponseFactory.okIfTruthy(createMono);
    }

    @GetMapping
    @RequiredPermission(USER_FRIEND_REQUEST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<UserFriendRequest>>>> queryFriendRequests(
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
        size = pageUtil.getSize(size);
        Flux<UserFriendRequest> userFriendRequestFlux = userFriendRequestService.queryFriendRequests(
                ids,
                requesterIds,
                recipientIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd),
                0,
                size);
        return ResponseFactory.okIfTruthy(userFriendRequestFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(USER_FRIEND_REQUEST_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<UserFriendRequest>>>> queryFriendRequests(
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
        size = pageUtil.getSize(size);
        Mono<Long> count = userFriendRequestService.countFriendRequests(
                ids,
                requesterIds,
                recipientIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd));
        Flux<UserFriendRequest> userFriendRequestFlux = userFriendRequestService.queryFriendRequests(
                ids,
                requesterIds,
                recipientIds,
                statuses,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(responseDateStart, responseDateEnd),
                DateRange.of(expirationDateStart, expirationDateEnd),
                page,
                size);
        return ResponseFactory.page(count, userFriendRequestFlux);
    }

    @PutMapping
    @RequiredPermission(USER_FRIEND_REQUEST_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> updateFriendRequests(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateFriendRequestDTO updateFriendRequestDTO) {
        Mono<Boolean> updateMono = userFriendRequestService.updateFriendRequests(
                ids,
                updateFriendRequestDTO.getRequesterId(),
                updateFriendRequestDTO.getRecipientId(),
                updateFriendRequestDTO.getContent(),
                updateFriendRequestDTO.getStatus(),
                updateFriendRequestDTO.getReason(),
                updateFriendRequestDTO.getCreationDate(),
                updateFriendRequestDTO.getResponseDate(),
                updateFriendRequestDTO.getExpirationDate());
        return ResponseFactory.acknowledged(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_FRIEND_REQUEST_DELETE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> deleteFriendRequests(@RequestParam(required = false) Set<Long> ids) {
        Mono<Boolean> deleteMono = userFriendRequestService.deleteFriendRequests(ids);
        return ResponseFactory.acknowledged(deleteMono);
    }

}
