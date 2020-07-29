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

import im.turms.turms.bo.DateRange;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.access.http.dto.request.user.AddRelationshipDTO;
import im.turms.turms.workflow.access.http.dto.request.user.UpdateRelationshipDTO;
import im.turms.turms.workflow.access.http.dto.request.user.UserRelationshipDTO;
import im.turms.turms.workflow.access.http.dto.response.AcknowledgedDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.UserRelationship;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipGroupService;
import im.turms.turms.workflow.service.impl.user.relationship.UserRelationshipService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static im.turms.turms.workflow.access.http.permission.AdminPermission.*;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/users/relationships")
public class UserRelationshipController {

    private final UserRelationshipService userRelationshipService;
    private final UserRelationshipGroupService userRelationshipGroupService;
    private final PageUtil pageUtil;

    public UserRelationshipController(UserRelationshipService userRelationshipService, PageUtil pageUtil, UserRelationshipGroupService userRelationshipGroupService) {
        this.userRelationshipService = userRelationshipService;
        this.pageUtil = pageUtil;
        this.userRelationshipGroupService = userRelationshipGroupService;
    }

    @PostMapping
    @RequiredPermission(USER_RELATIONSHIP_CREATE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> addRelationship(@RequestBody AddRelationshipDTO addRelationshipDTO) {
        Mono<Boolean> upsertMono = userRelationshipService.upsertOneSidedRelationship(
                addRelationshipDTO.getOwnerId(),
                addRelationshipDTO.getRelatedUserId(),
                addRelationshipDTO.getIsBlocked(),
                DaoConstant.DEFAULT_RELATIONSHIP_GROUP_INDEX,
                null,
                addRelationshipDTO.getEstablishmentDate(),
                false,
                null);
        return ResponseFactory.acknowledged(upsertMono);
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
            @RequestParam(defaultValue = "false") Boolean withGroupIndexes) {
        size = pageUtil.getSize(size);
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
            @RequestParam(defaultValue = "false") Boolean withGroupIndexes) {
        size = pageUtil.getSize(size);
        Mono<Long> count = userRelationshipService.countRelationships(
                ownerIds, relatedUserIds, groupIndexes, isBlocked);
        Flux<UserRelationship> relationshipsFlux = userRelationshipService.queryRelationships(
                ownerIds, relatedUserIds, groupIndexes, isBlocked, DateRange.of(establishmentDateStart, establishmentDateEnd), page, size);
        Flux<UserRelationshipDTO> dtoFlux = relationship2dto(withGroupIndexes, relationshipsFlux);
        return ResponseFactory.page(count, dtoFlux);
    }

    @PutMapping
    @RequiredPermission(USER_RELATIONSHIP_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> updateRelationships(
            UserRelationship.KeyList keys,
            @RequestBody UpdateRelationshipDTO updateRelationshipDTO) {
        Mono<Boolean> updated = userRelationshipService.updateUserOneSidedRelationships(
                new HashSet<>(keys.getKeys()),
                updateRelationshipDTO.getIsBlocked(),
                updateRelationshipDTO.getEstablishmentDate());
        return ResponseFactory.acknowledged(updated);
    }

    @DeleteMapping
    @RequiredPermission(USER_RELATIONSHIP_DELETE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> deleteRelationships(
            UserRelationship.KeyList keys) {
        Mono<Boolean> deleteMono = userRelationshipService.deleteOneSidedRelationships(new HashSet<>(keys.getKeys()));
        return ResponseFactory.acknowledged(deleteMono);
    }

    private Flux<UserRelationshipDTO> relationship2dto(Boolean withGroupIndexes, Flux<UserRelationship> relationshipsFlux) {
        return relationshipsFlux
                .flatMap(relationship -> withGroupIndexes
                        ? userRelationshipGroupService.queryGroupIndexes(relationship.getKey().getOwnerId(), relationship.getKey().getRelatedUserId())
                        .collect(Collectors.toSet())
                        .map(indexes -> UserRelationshipDTO.fromDomain(relationship, indexes))
                        : Mono.just(UserRelationshipDTO.fromDomain(relationship)));
    }

}
