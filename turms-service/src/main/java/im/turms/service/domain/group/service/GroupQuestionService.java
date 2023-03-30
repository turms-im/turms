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

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.ClientMessagePool;
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
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.group.GroupQuestionProperties;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.recycler.ListRecycler;
import im.turms.server.common.infra.recycler.Recyclable;
import im.turms.server.common.infra.time.DateUtil;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.validation.DataValidator;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.bo.GroupQuestionIdAndAnswer;
import im.turms.service.domain.group.bo.NewGroupQuestion;
import im.turms.service.domain.group.po.GroupJoinQuestion;
import im.turms.service.domain.group.repository.GroupQuestionRepository;
import im.turms.service.infra.proto.ProtoModelConvertor;
import im.turms.service.infra.validation.ValidGroupQuestionIdAndAnswer;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

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

    private int questionContentLimit;
    private int answerContentLimit;
    private int maxAnswerCount;

    public GroupQuestionService(
            Node node,
            TurmsPropertiesManager propertiesManager,
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

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        GroupQuestionProperties questionProperties = properties.getService()
                .getGroup()
                .getQuestion();
        int questionContentLimit = questionProperties.getQuestionContentLimit();
        this.questionContentLimit = questionContentLimit > 0
                ? questionContentLimit
                : Integer.MAX_VALUE;
        int answerContentLimit = questionProperties.getAnswerContentLimit();
        this.answerContentLimit = answerContentLimit > 0
                ? answerContentLimit
                : Integer.MAX_VALUE;
        int maxAnswerCount = questionProperties.getMaxAnswerCount();
        this.maxAnswerCount = maxAnswerCount > 0
                ? maxAnswerCount
                : Integer.MAX_VALUE;
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
        return groupQuestionRepository.checkQuestionAnswerAndGetScore(questionId, answer, groupId);
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
        Long firstQuestionId = questionIdAndAnswerPairs.iterator()
                .next()
                .id();
        return queryGroupId(firstQuestionId).flatMap(groupId -> groupBlocklistService
                .isBlocked(groupId, requesterId)
                .flatMap(isBlocked -> isBlocked
                        ? Mono.error(ResponseException
                                .get(ResponseStatusCode.GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED))
                        : groupMemberService.isGroupMember(groupId, requesterId, false))
                .flatMap(isGroupMember -> isGroupMember
                        ? Mono.error(ResponseException
                                .get(ResponseStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION))
                        : groupService.queryGroupTypeIfActiveAndNotDeleted(groupId)
                                .switchIfEmpty(Mono.error(ResponseException.get(
                                        ResponseStatusCode.ANSWER_QUESTION_OF_INACTIVE_GROUP))))
                .flatMap(type -> type.getJoinStrategy() == GroupJoinStrategy.QUESTION
                        ? checkGroupQuestionAnswersAndCountScore(questionIdAndAnswerPairs, groupId)
                        : Mono.error(
                                ResponseException.get(ResponseStatusCode.ANSWER_INACTIVE_QUESTION)))
                .flatMap(idsAndScore -> groupService.queryGroupMinimumScore(groupId)
                        .flatMap(minimumScore -> idsAndScore.getRight() >= minimumScore
                                ? groupMemberService
                                        .addGroupMember(groupId,
                                                requesterId,
                                                GroupMemberRole.MEMBER,
                                                null,
                                                null,
                                                null,
                                                null)
                                        .then(PublisherPool.TRUE)
                                : PublisherPool.FALSE)
                        .map(joined -> ClientMessagePool.getGroupJoinQuestionsAnswerResultBuilder()
                                .setJoined(joined)
                                .addAllQuestionIds(idsAndScore.getKey())
                                .setScore(idsAndScore.getRight())
                                .build())));
    }

    public Mono<List<GroupJoinQuestion>> authAndCreateGroupJoinQuestions(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull List<NewGroupQuestion> questions) {
        try {
            Validator.notNull(questions, "questions");
            for (NewGroupQuestion question : questions) {
                DataValidator.validNewGroupQuestion(question);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (questions.isEmpty()) {
            return PublisherPool.emptyList();
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                .flatMap(authenticated -> authenticated
                        ? groupService.queryGroupTypeIfActiveAndNotDeleted(groupId)
                                .switchIfEmpty(Mono.error(ResponseException.get(
                                        ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_INACTIVE_GROUP)))
                        : Mono.error(ResponseException.get(
                                ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION)))
                .flatMap(type -> switch (type.getJoinStrategy()) {
                    case JOIN_REQUEST -> Mono.error(ResponseException.get(
                            ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_JOIN_REQUEST));
                    case INVITATION -> Mono.error(ResponseException.get(
                            ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_INVITATION));
                    case MEMBERSHIP_REQUEST -> Mono.error(ResponseException.get(
                            ResponseStatusCode.CREATE_GROUP_QUESTION_FOR_GROUP_USING_MEMBERSHIP_REQUEST));
                    case QUESTION -> createGroupJoinQuestions(groupId, questions);
                });
    }

    public Mono<List<GroupJoinQuestion>> createGroupJoinQuestions(
            @NotNull Long groupId,
            @NotNull List<NewGroupQuestion> questions) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        List<GroupJoinQuestion> newQuestions = new ArrayList<>(questions.size());
        for (NewGroupQuestion q : questions) {
            String question = q.question();
            LinkedHashSet<String> answers = q.answers();
            Integer score = q.score();
            try {
                Validator.notNull(question, "question");
                Validator.maxLength(question, "question", questionContentLimit);
                Validator.notNull(answers, "answers");
                Validator.inSizeRange(answers, "answers", 1, maxAnswerCount);
                Validator.maxLength(answers, "answers", answerContentLimit);
                Validator.notNull(score, "score");
                Validator.min(score, "score", 0);
            } catch (ResponseException e) {
                return Mono.error(e);
            }
            newQuestions.add(new GroupJoinQuestion(
                    node.nextLargeGapId(ServiceType.GROUP_JOIN_QUESTION),
                    groupId,
                    question,
                    answers,
                    score));
        }
        return groupQuestionRepository.insertAllOfSameType(newQuestions)
                .then(groupVersionService.updateJoinQuestionsVersion(groupId)
                        .onErrorResume(t -> {
                            LOGGER.error(
                                    "Caught an error while updating the join questions version of the group ({}) after creating a join question",
                                    groupId,
                                    t);
                            return Mono.empty();
                        }))
                .thenReturn(newQuestions);
    }

    public Mono<Long> queryGroupId(@NotNull Long questionId) {
        try {
            Validator.notNull(questionId, "questionId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupQuestionRepository.findGroupId(questionId);
    }

    public Mono<Void> authAndDeleteGroupJoinQuestions(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            @NotNull Set<Long> questionIds) {
        try {
            Validator.notNull(requesterId, "requesterId");
            Validator.notNull(groupId, "groupId");
            Validator.notNull(questionIds, "questionIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (questionIds.isEmpty()) {
            return Mono.empty();
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                .flatMap(authenticated -> {
                    if (!authenticated) {
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION));
                    }
                    return groupQuestionRepository.deleteByIds(questionIds);
                })
                .flatMap(result -> {
                    if (result.getDeletedCount() == 0) {
                        return Mono.empty();
                    }
                    return groupVersionService.updateJoinQuestionsVersion(groupId)
                            .onErrorResume(t -> {
                                LOGGER.error(
                                        "Caught an error while updating the join questions version of the group ({}) after deleting a join question",
                                        groupId,
                                        t);
                                return Mono.empty();
                            })
                            .then();
                });
    }

    public Flux<GroupJoinQuestion> queryGroupJoinQuestions(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean withAnswers) {
        return groupQuestionRepository.findQuestions(ids, groupIds, page, size, withAnswers);
    }

    public Mono<Long> countGroupJoinQuestions(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds) {
        return groupQuestionRepository.countQuestions(ids, groupIds);
    }

    public Mono<DeleteResult> deleteGroupJoinQuestions(@Nullable Set<Long> ids) {
        return groupQuestionRepository.deleteByIds(ids);
    }

    public Mono<GroupJoinQuestionsWithVersion> authAndQueryGroupJoinQuestionsWithVersion(
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
                ? groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                : PublisherPool.TRUE;
        return authenticated.flatMap(isAuthenticated -> isAuthenticated != null && isAuthenticated
                ? groupVersionService.queryGroupJoinQuestionsVersion(groupId)
                : Mono.error(ResponseException.get(
                        ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER)))
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return ResponseExceptionPublisherPool.alreadyUpToUpdate();
                    }
                    Recyclable<List<GroupJoinQuestion>> recyclableList = ListRecycler.obtain();
                    return queryGroupJoinQuestions(null, Set.of(groupId), null, null, false)
                            .collect(Collectors.toCollection(recyclableList::getValue))
                            .map(groupJoinQuestions -> {
                                if (groupJoinQuestions.isEmpty()) {
                                    throw ResponseException.get(ResponseStatusCode.NO_CONTENT);
                                }
                                GroupJoinQuestionsWithVersion.Builder builder =
                                        ClientMessagePool.getGroupJoinQuestionsWithVersionBuilder()
                                                .setLastUpdatedDate(version.getTime());
                                for (GroupJoinQuestion question : groupJoinQuestions) {
                                    builder.addGroupJoinQuestions(
                                            ProtoModelConvertor.groupJoinQuestion2proto(question));
                                }
                                return builder.build();
                            })
                            .doFinally(signalType -> recyclableList.recycle());
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
                .flatMap(groupId -> groupMemberService.isOwnerOrManager(requesterId, groupId, false)
                        .flatMap(authenticated -> {
                            if (authenticated == null || !authenticated) {
                                return Mono.error(ResponseException.get(
                                        ResponseStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION));
                            }
                            return groupQuestionRepository
                                    .updateQuestion(questionId, question, answers, score)
                                    .flatMap(result -> groupVersionService
                                            .updateJoinQuestionsVersion(groupId)
                                            .onErrorResume(t -> {
                                                LOGGER.error(
                                                        "Caught an error while updating the join questions version of the group ({}) after updating a join question",
                                                        groupId,
                                                        t);
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
            Validator.maxLength(question, "question", questionContentLimit);
            Validator.inSizeRange(answers, "answers", 1, maxAnswerCount);
            Validator.maxLength(answers, "answers", answerContentLimit);
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
