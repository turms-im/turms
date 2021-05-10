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

package im.turms.turms.workflow.access.http.controller.user;

import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.dao.domain.User;
import im.turms.turms.constant.DivideBy;
import im.turms.turms.workflow.access.http.dto.request.user.AddUserDTO;
import im.turms.turms.workflow.access.http.dto.request.user.UpdateUserDTO;
import im.turms.turms.workflow.access.http.dto.request.user.UserStatisticsDTO;
import im.turms.server.common.access.http.dto.response.DeleteResultDTO;
import im.turms.server.common.access.http.dto.response.PaginationDTO;
import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.access.http.dto.response.UpdateResultDTO;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.DateTimeUtil;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.service.impl.message.MessageService;
import im.turms.turms.workflow.service.impl.user.UserService;
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
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_CREATE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_DELETE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_QUERY;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MessageService messageService;
    private final PageUtil pageUtil;
    private final DateTimeUtil dateTimeUtil;

    public UserController(UserService userService, PageUtil pageUtil, MessageService messageService, DateTimeUtil dateTimeUtil) {
        this.userService = userService;
        this.pageUtil = pageUtil;
        this.messageService = messageService;
        this.dateTimeUtil = dateTimeUtil;
    }

    @PostMapping
    @RequiredPermission(USER_CREATE)
    public Mono<ResponseEntity<ResponseDTO<User>>> addUser(@RequestBody AddUserDTO addUserDTO) {
        Mono<User> addUser = userService.addUser(
                addUserDTO.getId(),
                addUserDTO.getPassword(),
                addUserDTO.getName(),
                addUserDTO.getIntro(),
                addUserDTO.getProfileAccess(),
                addUserDTO.getPermissionGroupId(),
                addUserDTO.getRegistrationDate(),
                addUserDTO.getIsActive());
        return ResponseFactory.okIfTruthy(addUser);
    }

    @GetMapping
    @RequiredPermission(USER_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<User>>>> queryUsers(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Date registrationDateStart,
            @RequestParam(required = false) Date registrationDateEnd,
            @RequestParam(required = false) Date deletionDateStart,
            @RequestParam(required = false) Date deletionDateEnd,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<User> usersFlux = userService.queryUsers(
                ids,
                DateRange.of(registrationDateStart, registrationDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                isActive,
                0,
                size,
                true);
        return ResponseFactory.okIfTruthy(usersFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(USER_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<User>>>> queryUsers(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Date registrationDateStart,
            @RequestParam(required = false) Date registrationDateEnd,
            @RequestParam(required = false) Date deletionDateStart,
            @RequestParam(required = false) Date deletionDateEnd,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = userService.countUsers(
                ids,
                DateRange.of(registrationDateStart, registrationDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                isActive);
        Flux<User> usersFlux = userService.queryUsers(
                ids,
                DateRange.of(registrationDateStart, registrationDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                isActive,
                page,
                size,
                true);
        return ResponseFactory.page(count, usersFlux);
    }

    @GetMapping("/count")
    @RequiredPermission(USER_QUERY)
    public Mono<ResponseEntity<ResponseDTO<UserStatisticsDTO>>> countUsers(
            @RequestParam(required = false) Date registeredStartDate,
            @RequestParam(required = false) Date registeredEndDate,
            @RequestParam(required = false) Date deletedStartDate,
            @RequestParam(required = false) Date deletedEndDate,
            @RequestParam(required = false) Date sentMessageStartDate,
            @RequestParam(required = false) Date sentMessageEndDate,
            @RequestParam(defaultValue = "NOOP") DivideBy divideBy) {
        List<Mono<?>> counts = new LinkedList<>();
        UserStatisticsDTO statistics = new UserStatisticsDTO();
        if (divideBy == null || divideBy == DivideBy.NOOP) {
            if (deletedStartDate != null || deletedEndDate != null) {
                counts.add(userService.countDeletedUsers(
                        DateRange.of(deletedStartDate, deletedEndDate))
                        .doOnNext(statistics::setDeletedUsers));
            }
            if (sentMessageStartDate != null || sentMessageEndDate != null) {
                counts.add(messageService.countUsersWhoSentMessage(
                        DateRange.of(sentMessageStartDate, sentMessageEndDate),
                        null,
                        false)
                        .doOnNext(statistics::setUsersWhoSentMessages));
            }
            if (counts.isEmpty() || registeredStartDate != null || registeredEndDate != null) {
                counts.add(userService.countRegisteredUsers(
                        DateRange.of(registeredStartDate, registeredEndDate), true)
                        .doOnNext(statistics::setRegisteredUsers));
            }
        } else {
            if (deletedStartDate != null && deletedEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                        DateRange.of(deletedStartDate, deletedEndDate),
                        divideBy,
                        userService::countDeletedUsers)
                        .doOnNext(statistics::setDeletedUsersRecords));
            }
            if (sentMessageStartDate != null && sentMessageEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                        DateRange.of(sentMessageStartDate, sentMessageEndDate),
                        divideBy,
                        messageService::countUsersWhoSentMessage,
                        null,
                        false)
                        .doOnNext(statistics::setUsersWhoSentMessagesRecords));
            }
            if (registeredStartDate != null && registeredEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                        DateRange.of(registeredStartDate, registeredEndDate),
                        divideBy,
                        dateRange -> userService.countRegisteredUsers(dateRange, true))
                        .doOnNext(statistics::setRegisteredUsersRecords));
            }
            if (counts.isEmpty()) {
                return Mono.empty();
            }
        }
        return ResponseFactory.okIfTruthy(Flux.merge(counts).then(Mono.just(statistics)));
    }

    @PutMapping
    @RequiredPermission(USER_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateUser(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateUserDTO updateUserDTO) {
        Mono<UpdateResultDTO> updateMono = userService.updateUsers(
                ids,
                updateUserDTO.getPassword(),
                updateUserDTO.getName(),
                updateUserDTO.getIntro(),
                updateUserDTO.getProfileAccess(),
                updateUserDTO.getPermissionGroupId(),
                updateUserDTO.getRegistrationDate(),
                updateUserDTO.getIsActive())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteUsers(
            @RequestParam Set<Long> ids,
            @RequestParam(required = false) Boolean deleteLogically) {
        Mono<DeleteResultDTO> deleteMono = userService
                .deleteUsers(ids, deleteLogically)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

}
