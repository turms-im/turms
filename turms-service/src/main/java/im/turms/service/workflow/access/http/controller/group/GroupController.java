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

package im.turms.service.workflow.access.http.controller.group;

import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.access.http.dto.response.DeleteResultDTO;
import im.turms.server.common.access.http.dto.response.PaginationDTO;
import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.access.http.dto.response.UpdateResultDTO;
import im.turms.server.common.access.http.permission.RequiredPermission;
import im.turms.server.common.bo.common.DateRange;
import im.turms.service.constant.DivideBy;
import im.turms.service.workflow.access.http.dto.request.group.AddGroupDTO;
import im.turms.service.workflow.access.http.dto.request.group.GroupStatisticsDTO;
import im.turms.service.workflow.access.http.dto.request.group.UpdateGroupDTO;
import im.turms.service.workflow.access.http.util.DateTimeUtil;
import im.turms.service.workflow.access.http.util.PageUtil;
import im.turms.service.workflow.dao.domain.group.Group;
import im.turms.service.workflow.service.impl.group.GroupService;
import im.turms.service.workflow.service.impl.message.MessageService;
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

import static im.turms.server.common.access.http.permission.AdminPermission.GROUP_CREATE;
import static im.turms.server.common.access.http.permission.AdminPermission.GROUP_DELETE;
import static im.turms.server.common.access.http.permission.AdminPermission.GROUP_QUERY;
import static im.turms.server.common.access.http.permission.AdminPermission.GROUP_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups")
public class GroupController {

    private final GroupService groupService;
    private final MessageService messageService;
    private final PageUtil pageUtil;
    private final DateTimeUtil dateTimeUtil;

    public GroupController(GroupService groupService, PageUtil pageUtil, MessageService messageService, DateTimeUtil dateTimeUtil) {
        this.groupService = groupService;
        this.pageUtil = pageUtil;
        this.messageService = messageService;
        this.dateTimeUtil = dateTimeUtil;
    }

    @PostMapping
    @RequiredPermission(GROUP_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Group>>> addGroup(@RequestBody AddGroupDTO addGroupDTO) {
        Long ownerId = addGroupDTO.ownerId();
        Mono<Group> createdGroup = groupService.authAndCreateGroup(
                addGroupDTO.creatorId(),
                ownerId == null ? addGroupDTO.creatorId() : ownerId,
                addGroupDTO.name(),
                addGroupDTO.intro(),
                addGroupDTO.announcement(),
                addGroupDTO.minimumScore(),
                addGroupDTO.typeId(),
                addGroupDTO.creationDate(),
                addGroupDTO.deletionDate(),
                addGroupDTO.muteEndDate(),
                addGroupDTO.isActive());
        return ResponseFactory.okIfTruthy(createdGroup);
    }

    @GetMapping
    @RequiredPermission(GROUP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<Group>>>> queryGroups(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> typeIds,
            @RequestParam(required = false) Set<Long> creatorIds,
            @RequestParam(required = false) Set<Long> ownerIds,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(required = false) Date deletionDateStart,
            @RequestParam(required = false) Date deletionDateEnd,
            @RequestParam(required = false) Date muteEndDateStart,
            @RequestParam(required = false) Date muteEndDateEnd,
            @RequestParam(required = false) Set<Long> memberIds,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<Group> groupsFlux = groupService.queryGroups(
                ids,
                typeIds,
                creatorIds,
                ownerIds,
                isActive,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                memberIds,
                0,
                size);
        return ResponseFactory.okIfTruthy(groupsFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(GROUP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<Group>>>> queryGroups(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> typeIds,
            @RequestParam(required = false) Set<Long> creatorIds,
            @RequestParam(required = false) Set<Long> ownerIds,
            @RequestParam(required = false) Boolean isActive,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(required = false) Date deletionDateStart,
            @RequestParam(required = false) Date deletionDateEnd,
            @RequestParam(required = false) Date muteEndDateStart,
            @RequestParam(required = false) Date muteEndDateEnd,
            @RequestParam(required = false) Set<Long> memberIds,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = groupService.countGroups(
                ids,
                typeIds,
                creatorIds,
                ownerIds,
                isActive,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                memberIds);
        Flux<Group> groupsFlux = groupService.queryGroups(
                ids,
                typeIds,
                creatorIds,
                ownerIds,
                isActive,
                DateRange.of(creationDateStart, creationDateEnd),
                DateRange.of(deletionDateStart, deletionDateEnd),
                DateRange.of(muteEndDateStart, muteEndDateEnd),
                memberIds,
                page,
                size);
        return ResponseFactory.page(count, groupsFlux);
    }

    @GetMapping("/count")
    public Mono<ResponseEntity<ResponseDTO<GroupStatisticsDTO>>> countGroups(
            @RequestParam(required = false) Date createdStartDate,
            @RequestParam(required = false) Date createdEndDate,
            @RequestParam(required = false) Date deletedStartDate,
            @RequestParam(required = false) Date deletedEndDate,
            @RequestParam(required = false) Date sentMessageStartDate,
            @RequestParam(required = false) Date sentMessageEndDate,
            @RequestParam(defaultValue = "NOOP") DivideBy divideBy) {
        List<Mono<?>> counts = new LinkedList<>();
        GroupStatisticsDTO statistics = new GroupStatisticsDTO();
        if (divideBy == null || divideBy == DivideBy.NOOP) {
            if (deletedStartDate != null || deletedEndDate != null) {
                counts.add(groupService.countDeletedGroups(
                                DateRange.of(deletedStartDate, deletedEndDate))
                        .doOnNext(statistics::setDeletedGroups));
            }
            if (sentMessageStartDate != null || sentMessageEndDate != null) {
                counts.add(messageService.countGroupsThatSentMessages(
                                DateRange.of(sentMessageStartDate, sentMessageEndDate))
                        .doOnNext(statistics::setGroupsThatSentMessages));
            }
            if (counts.isEmpty() || createdStartDate != null || createdEndDate != null) {
                counts.add(groupService.countCreatedGroups(
                                DateRange.of(createdStartDate, createdEndDate))
                        .doOnNext(statistics::setCreatedGroups));
            }
        } else {
            if (deletedStartDate != null && deletedEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                                DateRange.of(deletedStartDate, deletedEndDate),
                                divideBy,
                                groupService::countDeletedGroups)
                        .doOnNext(statistics::setDeletedGroupsRecords));
            }
            if (sentMessageStartDate != null && sentMessageEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                                DateRange.of(sentMessageStartDate, sentMessageEndDate),
                                divideBy,
                                messageService::countGroupsThatSentMessages)
                        .doOnNext(statistics::setGroupsThatSentMessagesRecords));
            }
            if (createdStartDate != null && createdEndDate != null) {
                counts.add(dateTimeUtil.checkAndQueryBetweenDate(
                                DateRange.of(createdStartDate, createdEndDate),
                                divideBy,
                                groupService::countCreatedGroups)
                        .doOnNext(statistics::setCreatedGroupsRecords));
            }
            if (counts.isEmpty()) {
                return Mono.empty();
            }
        }
        return ResponseFactory.okIfTruthy(Flux.merge(counts).then(Mono.just(statistics)));
    }

    @PutMapping
    @RequiredPermission(GROUP_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateGroups(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateGroupDTO updateGroupDTO) {
        Long successorId = updateGroupDTO.successorId();
        Mono<UpdateResult> updateMono = successorId == null
                ? groupService.updateGroupsInformation(ids,
                updateGroupDTO.typeId(),
                updateGroupDTO.creatorId(),
                updateGroupDTO.ownerId(),
                updateGroupDTO.name(),
                updateGroupDTO.intro(),
                updateGroupDTO.announcement(),
                updateGroupDTO.minimumScore(),
                updateGroupDTO.isActive(),
                updateGroupDTO.creationDate(),
                updateGroupDTO.deletionDate(),
                updateGroupDTO.muteEndDate(),
                null)
                : groupService.checkAndTransferGroupOwnership(ids, successorId, updateGroupDTO.quitAfterTransfer(), null);
        return ResponseFactory.updateResult(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(GROUP_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteGroups(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Boolean deleteLogically) {
        Mono<DeleteResultDTO> deleted = groupService.deleteGroupsAndGroupMembers(
                        ids,
                        deleteLogically)
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleted);
    }

}