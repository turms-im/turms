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

package im.turms.service.workflow.service.impl.group;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.GroupMemberRole;
import im.turms.common.model.bo.group.GroupJoinQuestionsAnswerResult;
import im.turms.common.model.bo.group.GroupJoinQuestionsWithVersion;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.DateUtil;
import im.turms.service.bo.GroupQuestionIdAndAnswer;
import im.turms.service.constant.DaoConstant;
import im.turms.service.constant.OperationResultConstant;
import im.turms.service.constraint.ValidGroupQuestionIdAndAnswer;
import im.turms.service.util.ProtoModelUtil;
import im.turms.service.workflow.dao.domain.group.GroupJoinQuestion;
import im.turms.service.workflow.service.util.DomainConstraintUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
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

    private final Node node;
    private final TurmsMongoClient mongoClient;
    private final GroupMemberService groupMemberService;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;

    public GroupQuestionService(
            Node node,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            GroupService groupService) {
        this.mongoClient = mongoClient;
        this.node = node;
        this.groupMemberService = groupMemberService;
        this.groupVersionService = groupVersionService;
        this.groupService = groupService;
    }

    public Mono<Integer> checkGroupQuestionAnswerAndCountScore(
            @NotNull Long questionId,
            @NotNull String answer,
            @Nullable Long groupId) {
        try {
            AssertUtil.notNull(questionId, "questionId");
            AssertUtil.notNull(answer, "answer");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(3)
                .eq(DaoConstant.ID_FIELD_NAME, questionId)
                .in(GroupJoinQuestion.Fields.ANSWERS, answer)
                .eqIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupJoinQuestion.Fields.SCORE);
        return mongoClient.findOne(GroupJoinQuestion.class, filter, options)
                .map(GroupJoinQuestion::getScore);
    }

    /**
     * group join questions ids -> score
     */
    public Mono<Pair<List<Long>, Integer>> checkGroupQuestionAnswersAndCountScore(
            @NotEmpty Set<@ValidGroupQuestionIdAndAnswer GroupQuestionIdAndAnswer> questionIdAndAnswers,
            @Nullable Long groupId) {
        try {
            AssertUtil.notEmpty(questionIdAndAnswers, "questionIdAndAnswers");
            for (GroupQuestionIdAndAnswer idAndAnswer : questionIdAndAnswers) {
                DomainConstraintUtil.validGroupQuestionIdAndAnswer(idAndAnswer);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        List<Mono<Pair<Long, Integer>>> checks = new ArrayList<>(questionIdAndAnswers.size());
        for (GroupQuestionIdAndAnswer entry : questionIdAndAnswers) {
            checks.add(checkGroupQuestionAnswerAndCountScore(entry.id(), entry.answer(), groupId)
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
            @NotEmpty Set<@ValidGroupQuestionIdAndAnswer GroupQuestionIdAndAnswer> questionIdAndAnswers) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notEmpty(questionIdAndAnswers, "questionIdAndAnswers");
            for (GroupQuestionIdAndAnswer idAndAnswer : questionIdAndAnswers) {
                DomainConstraintUtil.validGroupQuestionIdAndAnswer(idAndAnswer);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Long firstQuestionId = questionIdAndAnswers.iterator().next().id();
        return queryGroupId(firstQuestionId)
                .flatMap(groupId -> groupMemberService.isBlocked(groupId, requesterId)
                        .flatMap(isBlocked -> isBlocked
                                ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.GROUP_QUESTION_ANSWERER_HAS_BEEN_BLOCKED))
                                : groupMemberService.isGroupMember(groupId, requesterId))
                        .flatMap(isGroupMember -> isGroupMember
                                ? Mono.error(TurmsBusinessException.get(TurmsStatusCode.MEMBER_CANNOT_ANSWER_GROUP_QUESTION))
                                : groupService.isGroupActiveAndNotDeleted(groupId))
                        .flatMap(isActive -> isActive
                                ? checkGroupQuestionAnswersAndCountScore(questionIdAndAnswers, groupId)
                                : Mono.error(TurmsBusinessException.get(TurmsStatusCode.ANSWER_QUESTION_OF_INACTIVE_GROUP)))
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
                                        .thenReturn(true)
                                        : Mono.just(false))
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
            AssertUtil.notNull(question, "question");
            AssertUtil.notEmpty(answers, "answers");
            AssertUtil.notNull(score, "score");
            AssertUtil.min(score, "score", 0);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return groupMemberService.isOwnerOrManager(requesterId, groupId)
                .flatMap(authenticated -> authenticated
                        ? createGroupJoinQuestion(groupId, question, answers, score)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_CREATE_GROUP_QUESTION)));
    }

    public Mono<GroupJoinQuestion> createGroupJoinQuestion(
            @NotNull Long groupId,
            @NotNull String question,
            @NotEmpty Set<String> answers,
            @NotNull @Min(0) Integer score) {
        try {
            AssertUtil.notNull(groupId, "groupId");
            AssertUtil.notNull(question, "question");
            AssertUtil.notEmpty(answers, "answers");
            AssertUtil.notNull(score, "score");
            AssertUtil.min(score, "score", 0);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupJoinQuestion groupJoinQuestion = new GroupJoinQuestion(
                node.nextLargeGapId(ServiceType.GROUP_JOIN_QUESTION),
                groupId,
                question,
                answers,
                score);
        return mongoClient.insert(groupJoinQuestion)
                .then(Mono.defer(() -> groupVersionService.updateJoinQuestionsVersion(groupId)
                        .onErrorResume(t -> Mono.empty())))
                .thenReturn(groupJoinQuestion);
    }

    public Mono<Long> queryGroupId(@NotNull Long questionId) {
        try {
            AssertUtil.notNull(questionId, "questionId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(DaoConstant.ID_FIELD_NAME, questionId);
        QueryOptions options = QueryOptions.newBuilder(2)
                .include(GroupJoinQuestion.Fields.GROUP_ID);
        return mongoClient.findOne(GroupJoinQuestion.class, filter, options)
                .map(GroupJoinQuestion::getGroupId);
    }

    public Mono<Void> authAndDeleteGroupJoinQuestion(
            @NotNull Long requesterId,
            @NotNull Long questionId) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return queryGroupId(questionId)
                .flatMap(groupId -> groupMemberService.isOwnerOrManager(requesterId, groupId)
                        .flatMap(authenticated -> {
                            if (!authenticated) {
                                return Mono
                                        .error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION));
                            }
                            Filter filter = Filter.newBuilder(1)
                                    .eq(DaoConstant.ID_FIELD_NAME, questionId);
                            return mongoClient.deleteOne(GroupJoinQuestion.class, filter)
                                    .flatMap(result -> groupVersionService.updateJoinQuestionsVersion(groupId)
                                            .onErrorResume(t -> Mono.empty())
                                            .then());
                        }));
    }

    public Flux<GroupJoinQuestion> queryGroupJoinQuestions(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean withAnswers) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupIds);
        QueryOptions options = QueryOptions.newBuilder(withAnswers ? 2 : 3)
                .paginateIfNotNull(page, size);
        if (!withAnswers) {
            options.exclude(GroupJoinQuestion.Fields.ANSWERS);
        }
        return mongoClient.findMany(GroupJoinQuestion.class, filter, options);
    }

    public Mono<Long> countGroupJoinQuestions(@Nullable Set<Long> ids, @Nullable Set<Long> groupIds) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .inIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupIds);
        return mongoClient.count(GroupJoinQuestion.class, filter);
    }

    public Mono<DeleteResult> deleteGroupJoinQuestions(@Nullable Set<Long> ids) {
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, ids);
        return mongoClient.deleteMany(GroupJoinQuestion.class, filter);
    }

    public Mono<GroupJoinQuestionsWithVersion> queryGroupJoinQuestionsWithVersion(
            @NotNull Long requesterId,
            @NotNull Long groupId,
            boolean withAnswers,
            @Nullable Date lastUpdatedDate) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Mono<Boolean> authenticated = withAnswers
                ? groupMemberService.isOwnerOrManager(requesterId, groupId)
                : Mono.just(true);
        return authenticated
                .flatMap(isAuthenticated -> isAuthenticated != null && isAuthenticated
                        ? groupVersionService.queryGroupJoinQuestionsVersion(groupId)
                        : Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_ACCESS_GROUP_QUESTION_ANSWER)))
                .flatMap(version -> {
                    if (DateUtil.isAfterOrSame(lastUpdatedDate, version)) {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
                    return queryGroupJoinQuestions(null, Set.of(groupId), null, null, false)
                            .collect(Collectors.toSet())
                            .map(groupJoinQuestions -> {
                                if (groupJoinQuestions.isEmpty()) {
                                    throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                }
                                GroupJoinQuestionsWithVersion.Builder builder = GroupJoinQuestionsWithVersion.newBuilder();
                                builder.setLastUpdatedDate(version.getTime());
                                for (GroupJoinQuestion question : groupJoinQuestions) {
                                    builder.addGroupJoinQuestions(ProtoModelUtil.groupJoinQuestion2proto(question));
                                }
                                return builder.build();
                            });
                })
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE)));
    }

    public Mono<UpdateResult> authAndUpdateGroupJoinQuestion(
            @NotNull Long requesterId,
            @NotNull Long questionId,
            @Nullable String question,
            @Nullable Set<String> answers,
            @Nullable @Min(0) Integer score) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(questionId, "questionId");
            AssertUtil.min(score, "score", 0);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(question, answers, score)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        return queryGroupId(questionId)
                .flatMap(groupId -> groupMemberService.isOwnerOrManager(requesterId, groupId)
                        .flatMap(authenticated -> {
                            if (authenticated == null || !authenticated) {
                                return Mono
                                        .error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION));
                            }
                            Filter filter = Filter.newBuilder(1)
                                    .eq(DaoConstant.ID_FIELD_NAME, questionId);
                            Update update = Update.newBuilder(3)
                                    .setIfNotNull(GroupJoinQuestion.Fields.QUESTION, question)
                                    .setIfNotNull(GroupJoinQuestion.Fields.ANSWERS, answers)
                                    .setIfNotNull(GroupJoinQuestion.Fields.SCORE, score);
                            return mongoClient.updateOne(GroupJoinQuestion.class, filter, update)
                                    .flatMap(result -> groupVersionService.updateJoinQuestionsVersion(groupId)
                                            .onErrorResume(t -> Mono.empty())
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
            AssertUtil.notNull(ids, "ids");
            AssertUtil.min(score, "score", 0);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllFalsy(groupId, question, answers, score)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, ids);
        Update update = Update.newBuilder(4)
                .setIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupId)
                .setIfNotNull(GroupJoinQuestion.Fields.QUESTION, question)
                .setIfNotNull(GroupJoinQuestion.Fields.ANSWERS, answers)
                .setIfNotNull(GroupJoinQuestion.Fields.SCORE, score);
        return mongoClient.updateMany(GroupJoinQuestion.class, filter, update);
    }

}
