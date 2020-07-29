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

package im.turms.turms.workflow.access.http.controller.group;

import im.turms.turms.workflow.access.http.dto.request.group.AddGroupJoinQuestionDTO;
import im.turms.turms.workflow.access.http.dto.request.group.UpdateGroupJoinQuestionDTO;
import im.turms.turms.workflow.access.http.dto.response.AcknowledgedDTO;
import im.turms.turms.workflow.access.http.dto.response.PaginationDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseDTO;
import im.turms.turms.workflow.access.http.dto.response.ResponseFactory;
import im.turms.turms.workflow.access.http.permission.AdminPermission;
import im.turms.turms.workflow.access.http.permission.RequiredPermission;
import im.turms.turms.workflow.access.http.util.PageUtil;
import im.turms.turms.workflow.dao.domain.GroupJoinQuestion;
import im.turms.turms.workflow.service.impl.group.GroupQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Set;

/**
 * @author James Chen
 */
@RestController
@RequestMapping("/groups/questions")
public class GroupQuestionController {

    private final GroupQuestionService groupQuestionService;
    private final PageUtil pageUtil;

    public GroupQuestionController(PageUtil pageUtil, GroupQuestionService groupQuestionService) {
        this.pageUtil = pageUtil;
        this.groupQuestionService = groupQuestionService;
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_QUERY)
    public Mono<ResponseEntity<ResponseDTO<Collection<GroupJoinQuestion>>>> queryGroupJoinQuestions(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Flux<GroupJoinQuestion> groupJoinQuestionFlux = groupQuestionService
                .queryGroupJoinQuestions(ids, groupIds, 0, size, true);
        return ResponseFactory.okIfTruthy(groupJoinQuestionFlux);
    }

    @GetMapping("/page")
    @RequiredPermission(AdminPermission.GROUP_QUESTION_QUERY)
    public Mono<ResponseEntity<ResponseDTO<PaginationDTO<GroupJoinQuestion>>>> queryGroupJoinQuestions(
            @RequestParam(required = false) Set<Long> ids,
            @RequestParam(required = false) Set<Long> groupIds,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(required = false) Integer size) {
        size = pageUtil.getSize(size);
        Mono<Long> count = groupQuestionService.countGroupJoinQuestions(ids, groupIds);
        Flux<GroupJoinQuestion> groupJoinQuestionFlux = groupQuestionService
                .queryGroupJoinQuestions(ids, groupIds, page, size, true);
        return ResponseFactory.page(count, groupJoinQuestionFlux);
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_CREATE)
    public Mono<ResponseEntity<ResponseDTO<GroupJoinQuestion>>> addGroupJoinQuestion(@RequestBody AddGroupJoinQuestionDTO addGroupJoinQuestionDTO) {
        Mono<GroupJoinQuestion> createMono = groupQuestionService.createGroupJoinQuestion(
                addGroupJoinQuestionDTO.getGroupId(),
                addGroupJoinQuestionDTO.getQuestion(),
                addGroupJoinQuestionDTO.getAnswers(),
                addGroupJoinQuestionDTO.getScore());
        return ResponseFactory.okIfTruthy(createMono);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_UPDATE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> updateGroupJoinQuestions(
            @RequestParam Set<Long> ids,
            @RequestBody UpdateGroupJoinQuestionDTO updateGroupJoinQuestionDTO) {
        Mono<Boolean> updateMono = groupQuestionService.updateGroupJoinQuestions(
                ids,
                updateGroupJoinQuestionDTO.getGroupId(),
                updateGroupJoinQuestionDTO.getQuestion(),
                updateGroupJoinQuestionDTO.getAnswers(),
                updateGroupJoinQuestionDTO.getScore());
        return ResponseFactory.acknowledged(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_DELETE)
    public Mono<ResponseEntity<ResponseDTO<AcknowledgedDTO>>> deleteGroupJoinQuestions(
            @RequestParam(required = false) Set<Long> ids) {
        Mono<Boolean> deleteMono = groupQuestionService.deleteGroupJoinQuestions(ids);
        return ResponseFactory.acknowledged(deleteMono);
    }

}
