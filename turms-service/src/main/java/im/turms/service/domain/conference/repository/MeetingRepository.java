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

package im.turms.service.domain.conference.repository;

import java.util.Collection;
import java.util.Date;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

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
import im.turms.service.domain.conference.po.Meeting;

/**
 * @author James Chen
 */
@Repository
public class MeetingRepository extends BaseRepository<Meeting, Long> {

    public MeetingRepository(@Qualifier("conferenceMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, Meeting.class);
    }

    public Mono<UpdateResult> updateEndDate(Long meetingId, Date endDate) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, meetingId);
        Update update = Update.newBuilder(1)
                .set(Meeting.Fields.END_DATE, endDate);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateCancelDateIfNotCanceled(Long meetingId, Date cancelDate) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, meetingId)
                .eq(Meeting.Fields.CANCEL_DATE, null);
        Update update = Update.newBuilder(1)
                .set(Meeting.Fields.CANCEL_DATE, cancelDate);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateMeeting(
            Long meetingId,
            @Nullable String name,
            @Nullable String intro,
            @Nullable String password) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, meetingId);
        Update update = Update.newBuilder(3)
                .setIfNotNull(Meeting.Fields.NAME, name)
                .setIfNotNull(Meeting.Fields.INTRO, intro)
                .setIfNotNull(Meeting.Fields.PASSWORD, password);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Flux<Meeting> find(
            @Nullable Collection<Long> ids,
            @Nullable Collection<Long> creatorIds,
            @Nullable Collection<Long> userIds,
            @Nullable Collection<Long> groupIds,
            @Nullable Date creationDateStart,
            @Nullable Date creationDateEnd,
            @Nullable Integer skip,
            @Nullable Integer limit) {
        Filter filter = Filter.newBuilder(6)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(Meeting.Fields.CREATOR_ID, creatorIds)
                .inIfNotNull(Meeting.Fields.USER_ID, userIds)
                .inIfNotNull(Meeting.Fields.GROUP_ID, groupIds)
                .addBetweenIfNotNull(Meeting.Fields.CREATION_DATE,
                        creationDateStart,
                        creationDateEnd);
        QueryOptions options = QueryOptions.newBuilder(2)
                .skipIfNotNull(skip)
                .limitIfNotNull(limit);
        return mongoClient.findMany(entityClass, filter, options);
    }

    public Flux<Meeting> find(
            @Nullable Collection<Long> ids,
            @NotNull Long creatorId,
            @NotNull Long userId,
            @Nullable Date creationDateStart,
            @Nullable Date creationDateEnd,
            @Nullable Integer skip,
            @Nullable Integer limit) {
        Filter filter = Filter.newBuilder(3)
                .inIfNotNull(DomainFieldName.ID, ids)
                .or(Filter.newBuilder(2)
                        .eq(Meeting.Fields.CREATOR_ID, creatorId)
                        .eq(Meeting.Fields.USER_ID, userId))
                .addBetweenIfNotNull(Meeting.Fields.CREATION_DATE,
                        creationDateStart,
                        creationDateEnd);
        QueryOptions options = QueryOptions.newBuilder(2)
                .skipIfNotNull(skip)
                .limitIfNotNull(limit);
        return mongoClient.findMany(entityClass, filter, options);
    }
}