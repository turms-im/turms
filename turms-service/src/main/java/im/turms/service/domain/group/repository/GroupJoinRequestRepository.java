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

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.common.repository.ExpirableEntityRepository;
import im.turms.service.domain.group.po.GroupJoinRequest;

/**
 * @author James Chen
 */
@Repository
public class GroupJoinRequestRepository extends ExpirableEntityRepository<GroupJoinRequest, Long> {

    private int expireAfterSeconds;

    public GroupJoinRequestRepository(
            TurmsPropertiesManager propertiesManager,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupJoinRequest.class);
        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        expireAfterSeconds = properties.getService()
                .getGroup()
                .getJoinRequest()
                .getExpireAfterSeconds();
    }

    @Override
    public int getEntityExpireAfterSeconds() {
        return expireAfterSeconds;
    }

    public Mono<UpdateResult> updateStatusIfPending(
            Long requestId,
            RequestStatus status,
            Long responderId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, requestId)
                .eq(GroupJoinRequest.Fields.STATUS, RequestStatus.PENDING);
        Update update = Update.newBuilder(2)
                .set(GroupJoinRequest.Fields.STATUS, status)
                .set(GroupJoinRequest.Fields.RESPONDER_ID, responderId);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> updateRequests(
            Set<Long> requestIds,
            @Nullable Long requesterId,
            @Nullable Long responderId,
            @Nullable String content,
            @Nullable RequestStatus status,
            @Nullable Date creationDate,
            @Nullable Date responseDate) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, requestIds);
        Update update = Update.newBuilder(5)
                .setIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterId)
                .setIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderId)
                .setIfNotNull(GroupJoinRequest.Fields.CONTENT, content)
                .setIfNotNull(GroupJoinRequest.Fields.STATUS, status)
                .setIfNotNull(GroupJoinRequest.Fields.CREATION_DATE, creationDate);
        updateResponseDateBasedOnStatus(update, status, responseDate);
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<Long> countRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> responderIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        Date expirationDate = getEntityExpirationDate();
        Filter filter = Filter.newBuilder(10)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(GroupJoinRequest.Fields.GROUP_ID, groupIds)
                .inIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderIds)
                .inIfNotNull(GroupJoinRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.CREATION_DATE,
                        getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(GroupJoinRequest.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, GroupJoinRequest.Fields.CREATION_DATE, expirationDate);
        return mongoClient.count(entityClass, filter);
    }

    public Mono<Long> findGroupId(Long requestId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, requestId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupJoinRequest.Fields.GROUP_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(GroupJoinRequest::getGroupId);
    }

    public Mono<GroupJoinRequest> findRequesterIdAndStatusAndGroupId(Long requestId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, requestId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(GroupJoinRequest.Fields.REQUESTER_ID,
                        GroupJoinRequest.Fields.STATUS,
                        GroupJoinRequest.Fields.GROUP_ID);
        return findExpirableDoc(filter, options);
    }

    public Flux<GroupJoinRequest> findRequestsByGroupId(Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        Filter filter = Filter.newBuilder(1)
                .eq(GroupJoinRequest.Fields.GROUP_ID, groupId);
        return findExpirableDocs(filter);
    }

    public Flux<GroupJoinRequest> findRequestsByRequesterId(Long requesterId) {
        Filter filter = Filter.newBuilder(1)
                .eq(GroupJoinRequest.Fields.REQUESTER_ID, requesterId);
        return findExpirableDocs(filter);
    }

    public Flux<GroupJoinRequest> findRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> groupIds,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> responderIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Date expirationDate = getEntityExpirationDate();
        Filter filter = Filter.newBuilder(10)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(GroupJoinRequest.Fields.GROUP_ID, groupIds)
                .inIfNotNull(GroupJoinRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(GroupJoinRequest.Fields.RESPONDER_ID, responderIds)
                .inIfNotNull(GroupJoinRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(GroupJoinRequest.Fields.CREATION_DATE,
                        getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(GroupJoinRequest.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, GroupJoinRequest.Fields.CREATION_DATE, expirationDate);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return findExpirableDocs(filter, options);
    }

}
