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

import com.mongodb.client.result.DeleteResult;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.user.access.admin.dto.request.AddRelationshipGroupDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateRelationshipGroupDTO;
import im.turms.service.domain.user.po.UserRelationshipGroup;
import im.turms.service.domain.user.service.UserRelationshipGroupService;
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

import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_GROUP_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_GROUP_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_GROUP_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_GROUP_UPDATE;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/relationships/groups")
public class UserRelationshipGroupController extends BaseController {
    private final UserRelationshipGroupService userRelationshipGroupService;

    public UserRelationshipGroupController(Node node, UserRelationshipGroupService userRelationshipGroupService) {
        super(node);
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    @PostMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_CREATE)
    public Mono<ResponseEntity<ResponseDTO<UserRelationshipGroup>>> addRelationshipGroup(
            @RequestBody AddRelationshipGroupDTO addRelationshipGroupDTO) {
        Mono<UserRelationshipGroup> createMono = userRelationshipGroupService.createRelationshipGroup(
                addRelationshipGroupDTO.ownerId(),
                addRelationshipGroupDTO.index(),
                addRelationshipGroupDTO.name(),
                addRelationshipGroupDTO.creationDate(),
                null);
        return ResponseFactory.okIfTruthy(createMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteRelationshipGroups(
            UserRelationshipGroup.KeyList keys) {
        Mono<DeleteResult> deleteMono = keys != null && !keys.getKeys().isEmpty()
                ? userRelationshipGroupService.deleteRelationshipGroups(CollectionUtil.newSet(keys.getKeys()))
                : userRelationshipGroupService.deleteRelationshipGroups();
        return ResponseFactory.okIfTruthy(deleteMono.map(DeleteResultDTO::get));
    }

    @PutMapping
    @RequiredPermission(USER_RELATIONSHIP_GROUP_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateRelationshipGroups(
            UserRelationshipGroup.KeyList keys,
            @RequestBody UpdateRelationshipGroupDTO updateRelationshipGroupDTO) {
        Mono<UpdateResultDTO> updateMono = userRelationshipGroupService.updateRelationshipGroups(
                        CollectionUtil.newSet(keys.getKeys()),
                        updateRelationshipGroupDTO.name(),
                        updateRelationshipGroupDTO.creationDate())
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
        size = getPageSize(size);
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
        size = getPageSize(size);
        Mono<Long> count = userRelationshipGroupService.countRelationshipGroups(
                ownerIds, indexes, names, DateRange.of(creationDateStart, creationDateEnd));
        Flux<UserRelationshipGroup> queryFlux = userRelationshipGroupService.queryRelationshipGroups(
                ownerIds, indexes, names, DateRange.of(creationDateStart, creationDateEnd), page, size);
        return ResponseFactory.page(count, queryFlux);
    }

}
