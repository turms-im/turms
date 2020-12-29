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

package im.turms.turms.workflow.service.impl.group;

import com.google.protobuf.Int64Value;
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
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectorUtil;
import im.turms.turms.bo.GroupQuestionIdAndAnswer;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.constraint.ValidGroupQuestionIdAndAnswer;
import im.turms.turms.util.ProtoUtil;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.group.GroupJoinQuestion;
import im.turms.turms.workflow.service.util.DomainConstraintUtil;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
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
public class GroupQuestionService {

    private final Node node;
    private final ReactiveMongoTemplate mongoTemplate;
    private final GroupMemberService groupMemberService;
    private final GroupService groupService;
    private final GroupVersionService groupVersionService;

    public GroupQuestionService(
            Node node,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            GroupMemberService groupMemberService,
            GroupVersionService groupVersionService,
            GroupService groupService) {
        this.mongoTemplate = mongoTemplate;
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
        Query query = new Query()
                .addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(questionId))
                .addCriteria(Criteria.where(GroupJoinQuestion.Fields.ANSWERS).in(answer));
        if (groupId != null) {
            query.addCriteria(Criteria.where(GroupJoinQuestion.Fields.GROUP_ID).is(groupId));
        }
        query.fields().include(GroupJoinQuestion.Fields.SCORE);
        return mongoTemplate.findOne(query, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME)
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
            checks.add(checkGroupQuestionAnswerAndCountScore(entry.getId(), entry.getAnswer(), groupId)
                    .map(score -> Pair.of(entry.getId(), score)));
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
        Long firstQuestionId = questionIdAndAnswers.iterator().next().getId();
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
                node.nextId(ServiceType.GROUP_JOIN_QUESTION),
                groupId,
                question,
                answers,
                score);
        return mongoTemplate.insert(groupJoinQuestion, GroupJoinQuestion.COLLECTION_NAME)
                .flatMap(joinQuestion -> groupVersionService.updateJoinQuestionsVersion(groupId)
                        .onErrorResume(t -> Mono.empty())
                        .thenReturn(joinQuestion));
    }

    public Mono<Long> queryGroupId(@NotNull Long questionId) {
        try {
            AssertUtil.notNull(questionId, "questionId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(questionId));
        query.fields().include(GroupJoinQuestion.Fields.GROUP_ID);
        return mongoTemplate.findOne(query, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME)
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
                            if (authenticated != null && authenticated) {
                                Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(questionId));
                                return mongoTemplate.remove(query, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME)
                                        .flatMap(result -> groupVersionService.updateJoinQuestionsVersion(groupId)
                                                .onErrorResume(t -> Mono.empty())
                                                .then());
                            } else {
                                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_DELETE_GROUP_QUESTION));
                            }
                        }));
    }

    public Flux<GroupJoinQuestion> queryGroupJoinQuestions(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean withAnswers) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupIds)
                .paginateIfNotNull(page, size);
        if (!withAnswers) {
            query.fields().exclude(GroupJoinQuestion.Fields.ANSWERS);
        }
        return mongoTemplate.find(query, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME);
    }

    public Mono<Long> countGroupJoinQuestions(@Nullable Set<Long> ids, @Nullable Set<Long> groupIds) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .addInIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupIds)
                .buildQuery();
        return mongoTemplate.count(query, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME);
    }

    public Mono<DeleteResult> deleteGroupJoinQuestions(@Nullable Set<Long> ids) {
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .buildQuery();
        return mongoTemplate.remove(query, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME);
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
                    if (lastUpdatedDate == null || lastUpdatedDate.before(version)) {
                        return queryGroupJoinQuestions(null, Set.of(groupId), null, null, false)
                                .collect(Collectors.toSet())
                                .map(groupJoinQuestions -> {
                                    if (groupJoinQuestions.isEmpty()) {
                                        throw TurmsBusinessException.get(TurmsStatusCode.NO_CONTENT);
                                    }
                                    GroupJoinQuestionsWithVersion.Builder builder = GroupJoinQuestionsWithVersion.newBuilder();
                                    builder.setLastUpdatedDate(Int64Value.newBuilder().setValue(version.getTime()).build());
                                    for (GroupJoinQuestion question : groupJoinQuestions) {
                                        im.turms.common.model.bo.group.GroupJoinQuestion.Builder questionBuilder = ProtoUtil.groupJoinQuestion2proto(question);
                                        builder.addGroupJoinQuestions(questionBuilder.build());
                                    }
                                    return builder.build();
                                });
                    } else {
                        return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ALREADY_UP_TO_DATE));
                    }
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
                            if (authenticated != null && authenticated) {
                                Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(questionId));
                                Update update = UpdateBuilder.newBuilder()
                                        .setIfNotNull(GroupJoinQuestion.Fields.QUESTION, question)
                                        .setIfNotNull(GroupJoinQuestion.Fields.ANSWERS, answers)
                                        .setIfNotNull(GroupJoinQuestion.Fields.SCORE, score)
                                        .build();
                                return mongoTemplate.updateFirst(query, update, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME)
                                        .flatMap(result -> groupVersionService.updateJoinQuestionsVersion(groupId)
                                                .onErrorResume(t -> Mono.empty())
                                                .thenReturn(result));
                            } else {
                                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_OWNER_OR_MANAGER_TO_UPDATE_GROUP_QUESTION));
                            }
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
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(ids));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupId)
                .setIfNotNull(GroupJoinQuestion.Fields.QUESTION, question)
                .setIfNotNull(GroupJoinQuestion.Fields.ANSWERS, answers)
                .setIfNotNull(GroupJoinQuestion.Fields.SCORE, score)
                .build();
        return mongoTemplate.updateMulti(query, update, GroupJoinQuestion.class, GroupJoinQuestion.COLLECTION_NAME);
    }

}
