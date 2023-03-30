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

package im.turms.service.domain.group.repository;

import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.group.po.GroupJoinQuestion;

/**
 * @author James Chen
 */
@Repository
public class GroupQuestionRepository extends BaseRepository<GroupJoinQuestion, Long> {

    public GroupQuestionRepository(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupJoinQuestion.class);
    }

    public Mono<UpdateResult> updateQuestion(
            Long questionId,
            @Nullable String question,
            @Nullable Set<String> answers,
            @Nullable Integer score) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, questionId);
        Update update = Update.newBuilder(3)
                .setIfNotNull(GroupJoinQuestion.Fields.QUESTION, question)
                .setIfNotNull(GroupJoinQuestion.Fields.ANSWERS, answers)
                .setIfNotNull(GroupJoinQuestion.Fields.SCORE, score);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateQuestions(
            Set<Long> ids,
            @Nullable Long groupId,
            @Nullable String question,
            @Nullable Set<String> answers,
            @Nullable Integer score) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, ids);
        Update update = Update.newBuilder(4)
                .setIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupId)
                .setIfNotNull(GroupJoinQuestion.Fields.QUESTION, question)
                .setIfNotNull(GroupJoinQuestion.Fields.ANSWERS, answers)
                .setIfNotNull(GroupJoinQuestion.Fields.SCORE, score);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Long> countQuestions(@Nullable Set<Long> ids, @Nullable Set<Long> groupIds) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupIds);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Integer> checkQuestionAnswerAndGetScore(
            Long questionId,
            String answer,
            @Nullable Long groupId) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, questionId)
                .in(GroupJoinQuestion.Fields.ANSWERS, answer)
                .eqIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupJoinQuestion.Fields.SCORE);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupJoinQuestion::getScore);
    }

    public Mono<Long> findGroupId(Long questionId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, questionId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupJoinQuestion.Fields.GROUP_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupJoinQuestion::getGroupId);
    }

    public Flux<GroupJoinQuestion> findQuestions(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Integer page,
            @Nullable Integer size,
            boolean withAnswers) {
        Filter filter = Filter.newBuilder(2)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(GroupJoinQuestion.Fields.GROUP_ID, groupIds);
        QueryOptions options = QueryOptions.newBuilder(withAnswers
                ? 2
                : 3)
                .paginateIfNotNull(page, size);
        if (!withAnswers) {
            options.exclude(GroupJoinQuestion.Fields.ANSWERS);
        }
        return mongoClient.findMany(entityClass, filter, options);
    }

}
