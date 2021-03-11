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

import com.mongodb.client.result.DeleteResult;
import im.turms.server.common.bo.common.DateRange;
import im.turms.turms.workflow.access.http.dto.request.user.AddRelationshipGroupDTO;
import im.turms.turms.workflow.access.http.dto.request.user.UpdateRelationshipGroupDTO;
import im.turms.turms.workflow.access.http.dto.response.DeleteResultDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.dto.response.UpdateResultDTO;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.user.UserRelationshipGroup;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipGroupService;
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
import java.util.HashSet;
import java.util.Set;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_RELATIONSHIP_GROUP_CREATE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_RELATIONSHIP_GROUP_DELETE;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_RELATIONSHIP_GROUP_QUERY;
import static im.turms.turms.workflow.access.http.permission.AdminPermission.USER_RELATIONSHIP_GROUP_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/relationships/groups")
public class UserRelationshipGroupController {
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final PageUtil pageUtil;

    public UserRelationshipGroupController(PageUtil pageUtil, UserRelationshipGroupService userRelationshipGroupService) {
        this.pageUtil = pageUtil;
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    @PostMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_CREATE)
    public Mono<ResponseEntity<ResponseDTO<UserRelationshipGroup>>> addRelationshipGroup(
            @RequestBody AddRelationshipGroupDTO addRelationshipGroupDTO) {
        Mono<UserRelationshipGroup> createMono = userRelationshipGroupService.createRelationshipGroup(
                addRelationshipGroupDTO.getOwnerId(),
                addRelationshipGroupDTO.getIndex(),
                addRelationshipGroupDTO.getName(),
                addRelationshipGroupDTO.getCreationDate(),
                null);
        return ResponseFactory.okIfTruthy(createMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteRelationshipGroups(
            UserRelationshipGroup.KeyList keys) {
        Mono<DeleteResult> deleteMono = keys != null && !keys.getKeys().isEmpty()
                ? userRelationshipGroupService.deleteRelationshipGroups(new HashSet<>(keys.getKeys()))
                : userRelationshipGroupService.deleteRelationshipGroups();
        return ResponseFactory.okIfTruthy(deleteMono.map(DeleteResultDTO::get));
    }

    @PutMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateRelationshipGroups(
            UserRelationshipGroup.KeyList keys,
            @RequestBody UpdateRelationshipGroupDTO updateRelationshipGroupDTO) {
        Mono<UpdateResultDTO> updateMono = userRelationshipGroupService.updateRelationshipGroups(
                new HashSet<>(keys.getKeys()),
                updateRelationshipGroupDTO.getName(),
                updateRelationshipGroupDTO.getCreationDate())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @GetMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<UserRelationshipGroup>>>> queryRelationshipGroups(
            @RequestParam(required = false) Set<Long> ownerIds,
            @RequestParam(required = false) Set<Integer> indexes,
            @RequestParam(required = false) Set<String> names,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<UserRelationshipGroup> queryFlux = userRelationshipGroupService.queryRelationshipGroups(
                ownerIds, indexes, names, DateRange.of(creationDateStart, creationDateEnd), 0, size);
        return ResponseFactory.okIfTruthy(queryFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(USER_RELATIONSHIP_GROUP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<UserRelationshipGroup>>>> queryRelationshipGroups(
            @RequestParam(required = false) Set<Long> ownerIds,
            @RequestParam(required = false) Set<Integer> indexes,
            @RequestParam(required = false) Set<String> names,
            @RequestParam(required = false) Date creationDateStart,
            @RequestParam(required = false) Date creationDateEnd,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = userRelationshipGroupService.countRelationshipGroups(
                ownerIds, indexes, names, DateRange.of(creationDateStart, creationDateEnd));
        Flux<UserRelationshipGroup> queryFlux = userRelationshipGroupService.queryRelationshipGroups(
                ownerIds, indexes, names, DateRange.of(creationDateStart, creationDateEnd), page, size);
        return ResponseFactory.page(count, queryFlux);
    }

}
