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

package im.turms.service.domain.group.access.admin.controller;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.admin.dto.response.DeleteResultDTO;
import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.PaginationDTO;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.dto.response.UpdateResultDTO;
import im.turms.server.common.access.admin.permission.AdminPermission;
import im.turms.server.common.access.admin.permission.RequiredPermission;
import im.turms.server.common.access.admin.web.annotation.DeleteMapping;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.PostMapping;
import im.turms.server.common.access.admin.web.annotation.PutMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RequestBody;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.service.domain.common.access.admin.controller.BaseController;
import im.turms.service.domain.group.access.admin.dto.request.AddGroupJoinQuestionDTO;
import im.turms.service.domain.group.access.admin.dto.request.UpdateGroupJoinQuestionDTO;
import im.turms.service.domain.group.bo.NewGroupQuestion;
import im.turms.service.domain.group.po.GroupJoinQuestion;
import im.turms.service.domain.group.service.GroupQuestionService;

/**
 * @author James Chen
 */
@RestController("groups/questions")
public class GroupQuestionController extends BaseController {

    private final GroupQuestionService groupQuestionService;

    public GroupQuestionController(
            TurmsPropertiesManager propertiesManager,
            GroupQuestionService groupQuestionService) {
        super(propertiesManager);
        this.groupQuestionService = groupQuestionService;
    }

    @GetMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<Collection<GroupJoinQuestion>>>> queryGroupJoinQuestions(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<Long> groupIds,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Flux<GroupJoinQuestion> groupJoinQuestionFlux =
                groupQuestionService.queryGroupJoinQuestions(ids, groupIds, 0, size, true);
        return HttpHandlerResult.okIfTruthy(groupJoinQuestionFlux);
    }

    @GetMapping("page")
    @RequiredPermission(AdminPermission.GROUP_QUESTION_QUERY)
    public Mono<HttpHandlerResult<ResponseDTO<PaginationDTO<GroupJoinQuestion>>>> queryGroupJoinQuestions(
            @QueryParam(required = false) Set<Long> ids,
            @QueryParam(required = false) Set<Long> groupIds,
            int page,
            @QueryParam(required = false) Integer size) {
        size = getPageSize(size);
        Mono<Long> count = groupQuestionService.countGroupJoinQuestions(ids, groupIds);
        Flux<GroupJoinQuestion> groupJoinQuestionFlux =
                groupQuestionService.queryGroupJoinQuestions(ids, groupIds, page, size, true);
        return HttpHandlerResult.page(count, groupJoinQuestionFlux);
    }

    @PostMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_CREATE)
    public Mono<HttpHandlerResult<ResponseDTO<GroupJoinQuestion>>> addGroupJoinQuestion(
            @RequestBody AddGroupJoinQuestionDTO addGroupJoinQuestionDTO) {
        Mono<GroupJoinQuestion> createMono = groupQuestionService
                .createGroupJoinQuestions(addGroupJoinQuestionDTO.groupId(),
                        List.of(new NewGroupQuestion(
                                addGroupJoinQuestionDTO.question(),
                                addGroupJoinQuestionDTO.answers(),
                                addGroupJoinQuestionDTO.score())))
                .map(questions -> questions.get(0));
        return HttpHandlerResult.okIfTruthy(createMono);
    }

    @PutMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_UPDATE)
    public Mono<HttpHandlerResult<ResponseDTO<UpdateResultDTO>>> updateGroupJoinQuestions(
            Set<Long> ids,
            @RequestBody UpdateGroupJoinQuestionDTO updateGroupJoinQuestionDTO) {
        Mono<UpdateResultDTO> updateMono = groupQuestionService
                .updateGroupJoinQuestions(ids,
                        updateGroupJoinQuestionDTO.groupId(),
                        updateGroupJoinQuestionDTO.question(),
                        updateGroupJoinQuestionDTO.answers(),
                        updateGroupJoinQuestionDTO.score())
                .map(UpdateResultDTO::get);
        return HttpHandlerResult.okIfTruthy(updateMono);
    }

    @DeleteMapping
    @RequiredPermission(AdminPermission.GROUP_QUESTION_DELETE)
    public Mono<HttpHandlerResult<ResponseDTO<DeleteResultDTO>>> deleteGroupJoinQuestions(
            @QueryParam(required = false) Set<Long> ids) {
        Mono<DeleteResultDTO> deleteMono = groupQuestionService.deleteGroupJoinQuestions(ids)
                .map(DeleteResultDTO::get);
        return HttpHandlerResult.okIfTruthy(deleteMono);
    }

}