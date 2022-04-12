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

package im.turms.service.domain.group.service;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.server.common.access.client.dto.constant.GroupMemberRole;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsAnswerResult;
import im.turms.server.common.access.client.dto.model.group.GroupJoinQuestionsWithVersion;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.bo.GroupQuestionIdAndAnswer;
import im.turms.service.domain.group.po.GroupJoinQuestion;
import im.turms.service.domain.group.repository.GroupQuestionRepository;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidGroupQuestionIdAndAnswer;
import im.turms.service.storage.mongo.OperationResultPublisherPool;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupQuestionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupQuestionService.class);

    private final Node node;
    private final GroupBlocklistService groupBlocklistService;
    private final GroupQuestionRepository groupQuestionRepository;
    private final GroupMemberService groupMemberService;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;

    public GroupQuestionService(
            Node node,
            GroupBlocklistService groupBlocklistService,
            GroupQuestionRepository groupQuestionRepository,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            GroupService groupService) {
        this.node = node;
        this.groupBlocklistService = groupBlocklistService;
        this.groupQuestionRepository = groupQuestionRepository;
        this.groupMemberService = groupMemberService;
        this.groupVersionService = groupVersionService;
        this.groupService = groupService;
    }

    public Mono<Integer> checkGroupQuestionAnswerAndGetScore(
            @NotNull Long questionId,
            @NotNull String answer,
            @Nullable Long groupId) {
        try {
            Validator.notNull(questionId, "questionId");
            Validator.notNull(answer, "answer");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupQuestionRepository
                .checkQuestionAnswerAndGetScore(questionId, answer, groupId);
    }

    /**
     * group join questions ids -> score
     */
    public Mono<Pair<List<Long>, Integer>> checkGroupQuestionAnswersAndCountScore(
            @NotEmpty Set<@ValidGroupQuestionIdAndAnswer GroupQuestionIdAndAnswer> questionIdAndAnswerPairs,
            @Nullable Long groupId) {
        try {
            Validator.notEmpty(questionIdAndAnswerPairs, "questionIdAndAnswerPairs");
            for (GroupQuestionIdAndAnswer idAndAnswer : questionIdAndAnswerPairs) {
                DataValidator.validGroupQuestionIdAndAnswer(idAndAnswer);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        List<Mono<Pair<Long, Integer>>> checks = new ArrayList<>(questionIdAndAnswerPairs.size());
        for (GroupQuestionIdAndAnswer entry : questionIdAndAnswerPairs) {
            checks.add(checkGroupQuestionAnswerAndGetScore(entry.id(), entry.answer(), groupId)
                    .map(score -> Pair.of(entry.id(), score)));
        }
        return Flux.merge(checks)
                .collect(CollectorUtil.toList(checks.size()))
                .map(pairs -> {
                    List<Long> questionIds = new ArrayList<>(pairs.size());
                    int score = 0;
                    for (Pair<Long, Integer> pair : pairs) {
                        questionIds.add(pair.getLeft());
                        score += pair.getRight();
                    }
                    return Pair.of(questionIds, score);
                });
    }

    public Mono<GroupJoinQuestionsAnswerResult> checkGroupQuestionAnswerAndJoin(
            @NotNull Long requesterId,
            @NotEmpty Set<@ValidGroupQuestionIdAndAnswer GroupQuestionIdAndAnswer> questionIdAndAnswerPairs) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notEmpty(questionIdAndAnswerPairs, "questionIdAndAnswerPairs");
            for (GroupQuestionIdAndAnswer idAndAnswer : questionIdAndAnswerPairs) {
                DataValidator.validGroupQuestionIdAndAnswer(idAndAnswer);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Long firstQuestionId = questionIdAndAnswerPairs.iterator().next().id();
        return queryGroupId(firstQuestionId)
                .flatMap(groupId -> groupBlocklistService.isBlocked(groupId, requesterId)
                        .flatMap(isBlocked -> isBlocked
                                ? Mono.error(ResponseException.get(ResponseStatusCode.GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED))
                                : groupMemberService.isGroupMember(groupId, requesterId))
                        .flatMap(isGroupMember -> isGroupMember
                                ? Mono.error(ResponseException.get(ResponseStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION))
                                : groupService.isGroupActiveAndNotDeleted(groupId))
                        .flatMap(isActive -> isActive
                                ? checkGroupQuestionAnswersAndCountScore(questionIdAndAnswerPairs, groupId)
                                : Mono.error(ResponseException.get(ResponseStatusCode.ANSWER_QUESTION_OF_INACTIVE_GROUP)))
                        .flatMap(idsAndScore -> groupService.queryGroupMinimumScore(groupId)
                                .flatMap(minimumScore -> idsAndScore.getRight() >= minimumScore
                                        ? groupMemberService.addGroupMember(
                                                groupId,
                                                requesterId,
                                                GroupMemberRole.MEMBER,
                                                null,
                                                null,
                                                null,
                                                null)
                                        .then(PublisherPool.TRUE)
                                        : PublisherPool.FALSE)
                                .map(joined -> GroupJoinQuestionsAnswerResult
                                        .newBuilder()
                                        .setJoined(joined)
                                        .addAllQuestionIds(idsAndScore.getKey())
                                        .setScore(idsAndScore.getRight())
                                        .build())));
    }

    public Mono<GroupJoinQuestion> authAndCreateGroupJoinQuestion(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull String question,
            @NotEmpty Set<String> answers,
            @NotNull @Min(0) Integer score) {
        try {
            Validator.notNull(question, "question");
            Validator.notEmpty(answers, "answers");
            Validator.notNull(score, "score");
            Validator.min(score, "score", 0);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> authenticated
                        ? createGroupJoinQuestion(groupId, question, answers, score)
                        : Mono.error(ResponseException.get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION)));
    }

    public Mono<GroupJoinQuestion> createGroupJoinQuestion(
            @NotNull Long groupId,
            @NotNull String question,
            @NotEmpty Set<String> answers,
            @NotNull @Min(0) Integer score) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(question, "question");
            Validator.notEmpty(answers, "answers");
            Validator.notNull(score, "score");
            Validator.min(score, "score", 0);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        GroupJoinQuestion groupJoinQuestion = new GroupJoinQuestion(
                node.nextLargeGapId(ServiceType.GROUP_JOIN_QUESTION),
                groupId,
                question,
                answers,
                score);
        return groupQuestionRepository.insert(groupJoinQuestion)
                .then(groupVersionService.updateJoinQuestionsVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error("Caught an error while updating the join questions version of the group {} after creating a join question",
                                    groupId, t);
                            return Mono.empty();
                        }))
                .thenReturn(groupJoinQuestion);
    }

    public Mono<Long> queryGroupId(@NotNull Long questionId) {
        try {
            Validator.notNull(questionId, "questionId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupQuestionRepository.findGroupId(questionId);
    }

    public Mono<Void> authAndDeleteGroupJoinQuestion(
            @NotNull Long requesterId,
            @NotNull Long questionId) {
        try {
            Validator.notNull(requesterId, "requesterId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return queryGroupId(questionId)
                .flatMap(groupId -> groupMemberService.isOwnerOrManager(requesterId, groupId)
                        .flatMap(authenticated -> {
                            if (!authenticated) {
                                return Mono
                                        .error(ResponseException.get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION));
                            }
                            return groupQuestionRepository.deleteById(questionId)
                                    .flatMap(result -> groupVersionService.updateJoinQuestionsVersion(groupId)
                                            .onErrorResume(t -> {
                                                LOGGER.error("Caught an error while updating the join questions version of the group {} after deleting a join question",
                                                        groupId, t);
                                                return Mono.empty();
                                            })
                                            .then());
                        }));
    }

    public Flux<GroupJoinQuestion> queryGroupJoinQuestions(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean withAnswers) {
        return groupQuestionRepository.findQuestions(ids, groupIds, page, size, withAnswers);
    }

    public Mono<Long> countGroupJoinQuestions(@Nullable Set<Long> ids, @Nullable Set<Long> groupIds) {
        return groupQuestionRepository.countQuestions(ids, groupIds);
    }

    public Mono<DeleteResult> deleteGroupJoinQuestions(@Nullable Set<Long> ids) {
        return groupQuestionRepository.deleteByIds(ids);
    }

    public Mono<GroupJoinQuestionsWithVersion> queryGroupJoinQuestionsWithVersion(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            boolean withAnswers,
            @Nullable Date lastUpdatedDate) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Boolean> authenticated = withAnswers
                ? groupMemberService.isOwnerOrManager(requesterId, groupId)
                : PublisherPool.TRUE;
        return authenticated
                .flatMap(isAuthenticated -> isAuthenticated != null && isAuthenticated
                        ? groupVersionService.queryGroupJoinQuestionsVersion(groupId)
                        : Mono.error(ResponseException.get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER)))
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    return queryGroupJoinQuestions(null, Set.of(groupId), null, null, false)
                            .collect(Collectors.toSet())
                            .map(groupJoinQuestions -> {
                                if (groupJoinQuestions.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                GroupJoinQuestionsWithVersion.Builder builder = GroupJoinQuestionsWithVersion.newBuilder();
                                builder.setLastUpdatedDate(version.getTime());
                                for (GroupJoinQuestion question : groupJoinQuestions) {
                                    builder.addGroupJoinQuestions(ProtoModelConvertor.groupJoinQuestion2proto(question));
                                }
                                return builder.build();
                            });
                })
                .switchIfEmpty(ResponseExceptionPublisherPool.alreadyUpToUpdate());
    }

    public Mono<UpdateResult> authAndUpdateGroupJoinQuestion(
            @NotNull Long requesterId,
            @NotNull Long questionId,
            @Nullable String question,
            @Nullable Set<String> answers,
            @Nullable @Min(0) Integer score) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(questionId, "questionId");
            Validator.min(score, "score", 0);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(question, answers, score)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return queryGroupId(questionId)
                .flatMap(groupId -> groupMemberService.isOwnerOrManager(requesterId, groupId)
                        .flatMap(authenticated -> {
                            if (authenticated == null || !authenticated) {
                                return Mono
                                        .error(ResponseException.get(ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION));
                            }
                            return groupQuestionRepository.updateQuestion(questionId, question, answers, score)
                                    .flatMap(result -> groupVersionService.updateJoinQuestionsVersion(groupId)
                                            .onErrorResume(t -> {
                                                LOGGER.error("Caught an error while updating the join questions version of the group {} after updating a join question",
                                                        groupId, t);
                                                return Mono.empty();
                                            })
                                            .thenReturn(result));
                        }));
    }

    public Mono<UpdateResult> updateGroupJoinQuestions(
            @NotEmpty Set<Long> ids,
            @Nullable Long groupId,
            @Nullable String question,
            @Nullable Set<String> answers,
            @Nullable @Min(0) Integer score) {
        try {
            Validator.notNull(ids, "ids");
            Validator.min(score, "score", 0);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(groupId, question, answers, score)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupQuestionRepository.updateQuestions(ids, groupId, question, answers, score);
    }

}
