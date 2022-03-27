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
import im.turms.server.common.domain.common.dto.response.DeleteResultDTO;
import im.turms.server.common.domain.common.dto.response.PaginationDTO;
import im.turms.server.common.domain.common.dto.response.ResponseDTO;
import im.turms.server.common.domain.common.dto.response.ResponseFactory;
import im.turms.server.common.domain.common.dto.response.UpdateResultDTO;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.time.DateRange;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.user.access.admin.dto.request.AddRelationshipDTO;
import im.turms.service.domain.user.access.admin.dto.request.UpdateRelationshipDTO;
import im.turms.service.domain.user.access.admin.dto.response.UserRelationshipDTO;
import im.turms.service.domain.user.po.UserRelationship;
import im.turms.service.domain.user.service.UserRelationshipGroupService;
import im.turms.service.domain.user.service.UserRelationshipService;
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
import java.util.stream.Collectors;

import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_CREATE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_DELETE;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_QUERY;
import static im.turms.server.common.access.admin.permission.AdminPermission.USER_RELATIONSHIP_UPDATE;
import static im.turms.server.common.domain.user.constant.UserConst.DEFAULT_RELATIONSHIP_GROUP_INDEX;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/relationships")
public class UserRelationshipController extends BaseController {

    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;

    public UserRelationshipController(Node node,
                                      UserRelationshipService userRelationshipService,
                                      UserRelationshipGroupService userRelationshipGroupService) {
        super(node);
        this.userRelationshipService = userRelationshipService;
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    @PostMapping
    @RequiredPermission(USER_RELATIONSHIP_CREATE)
    public Mono<ResponseEntity<ResponseDTO<Void>>> addRelationship(@RequestBody AddRelationshipDTO addRelationshipDTO) {
        Mono<Void> upsertMono = userRelationshipService.upsertOneSidedRelationship(
                addRelationshipDTO.ownerId(),
                addRelationshipDTO.relatedUserId(),
                addRelationshipDTO.blockDate(),
                DEFAULT_RELATIONSHIP_GROUP_INDEX,
                null,
                addRelationshipDTO.establishmentDate(),
                false,
                null);
        return upsertMono.thenReturn(ResponseFactory.OK);
    }

    @GetMapping
    @RequiredPermission(USER_RELATIONSHIP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<UserRelationshipDTO>>>> queryRelationships(
            @RequestParam(required = false) Set<Long> ownerIds,
            @RequestParam(required = false) Set<Long> relatedUserIds,
            @RequestParam(required = false) Set<Integer> groupIndexes,
            @RequestParam(required = false) Boolean isBlocked,
            @RequestParam(required = false) Date establishmentDateStart,
            @RequestParam(required = false) Date establishmentDateEnd,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "false") boolean withGroupIndexes) {
        size = getPageSize(size);
        Flux<UserRelationship> relationshipsFlux = userRelationshipService.queryRelationships(
                ownerIds, relatedUserIds, groupIndexes, isBlocked, DateRange.of(establishmentDateStart, establishmentDateEnd), 0, size);
        Flux<UserRelationshipDTO> dtoFlux = relationship2dto(withGroupIndexes, relationshipsFlux);
        return ResponseFactory.okIfTruthy(dtoFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(USER_RELATIONSHIP_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<UserRelationshipDTO>>>> queryRelationships(
            @RequestParam(required = false) Set<Long> ownerIds,
            @RequestParam(required = false) Set<Long> relatedUserIds,
            @RequestParam(required = false) Set<Integer> groupIndexes,
            @RequestParam(required = false) Boolean isBlocked,
            @RequestParam(required = false) Date establishmentDateStart,
            @RequestParam(required = false) Date establishmentDateEnd,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(defaultValue = "false") boolean withGroupIndexes) {
        size = getPageSize(size);
        Mono<Long> count = userRelationshipService.countRelationships(
                ownerIds, relatedUserIds, groupIndexes, isBlocked);
        Flux<UserRelationship> relationshipsFlux = userRelationshipService.queryRelationships(
                ownerIds, relatedUserIds, groupIndexes, isBlocked, DateRange.of(establishmentDateStart, establishmentDateEnd), page, size);
        Flux<UserRelationshipDTO> dtoFlux = relationship2dto(withGroupIndexes, relationshipsFlux);
        return ResponseFactory.page(count, dtoFlux);
    }

    @PutMapping
    @RequiredPermission(USER_RELATIONSHIP_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<UpdateResultDTO>>> updateRelationships(
            UserRelationship.KeyList keys,
            @RequestBody UpdateRelationshipDTO updateRelationshipDTO) {
        Mono<UpdateResultDTO> updateMono = userRelationshipService.updateUserOneSidedRelationships(
                        CollectionUtil.newSet(keys.getKeys()),
                        updateRelationshipDTO.blockDate(),
                        updateRelationshipDTO.establishmentDate())
                .map(UpdateResultDTO::get);
        return ResponseFactory.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(USER_RELATIONSHIP_DELETE)
    public Mono<ResponseEntity<ResponseDTO<DeleteResultDTO>>> deleteRelationships(
            UserRelationship.KeyList keys) {
        Mono<DeleteResultDTO> deleteMono = userRelationshipService
                .deleteOneSidedRelationships(CollectionUtil.newSet(keys.getKeys()))
                .map(DeleteResultDTO::get);
        return ResponseFactory.okIfTruthy(deleteMono);
    }

    private Flux<UserRelationshipDTO> relationship2dto(Boolean withGroupIndexes, Flux<UserRelationship> relationshipsFlux) {
        return relationshipsFlux
                .flatMap(relationship -> withGroupIndexes
                        ? userRelationshipGroupService
                        .queryGroupIndexes(relationship.getKey().getOwnerId(), relationship.getKey().getRelatedUserId())
                        .collect(Collectors.toSet())
                        .map(indexes -> UserRelationshipDTO.fromDomain(relationship, indexes))
                        : Mono.just(UserRelationshipDTO.fromDomain(relationship)));
    }

}
