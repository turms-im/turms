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

package im.turms.service.domain.user.repository;

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.time.DateConst;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.common.repository.ExpirableEntityRepository;
import im.turms.service.domain.user.po.UserFriendRequest;

/**
 * @author James Chen
 */
@Repository
public class UserFriendRequestRepository
        extends ExpirableEntityRepository<UserFriendRequest, Long> {

    private int friendRequestExpireAfterSeconds;

    public UserFriendRequestRepository(
            TurmsPropertiesManager propertiesManager,
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserFriendRequest.class);
        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        friendRequestExpireAfterSeconds = properties.getService()
                .getUser()
                .getFriendRequest()
                .getFriendRequestExpireAfterSeconds();
    }

    @Override
    public int getEntityExpireAfterSeconds() {
        return friendRequestExpireAfterSeconds;
    }

    public Mono<UpdateResult> updateFriendRequests(
            Set<Long> requestIds,
            @Nullable Long requesterId,
            @Nullable Long recipientId,
            @Nullable String content,
            @Nullable RequestStatus status,
            @Nullable String reason,
            @Nullable Date creationDate) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, requestIds);
        Update update = Update.newBuilder(5)
                .setIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .setIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .setIfNotNull(UserFriendRequest.Fields.CONTENT, content)
                .setIfNotNull(UserFriendRequest.Fields.REASON, reason)
                .setIfNotNull(UserFriendRequest.Fields.CREATION_DATE, creationDate);
        updateResponseDateBasedOnStatus(update, status, new Date());
        return mongoClient.updateMany(entityClass, filter, update);
    }

    public Mono<UpdateResult> updatePendingFriendRequestStatus(
            Long requestId,
            RequestStatus requestStatus,
            @Nullable String reason,
            @Nullable ClientSession session) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, requestId)
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING)
                .isNotExpired(UserFriendRequest.Fields.CREATION_DATE, getEntityExpirationDate());
        Update update = Update.newBuilder(2)
                .set(UserFriendRequest.Fields.STATUS, requestStatus)
                .setIfNotNull(UserFriendRequest.Fields.REASON, reason);
        return mongoClient.updateOne(session, entityClass, filter, update);
    }

    public Mono<Long> countFriendRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> recipientIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange) {
        Filter filter = Filter.newBuilder(9)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .inIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE,
                        getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses,
                        UserFriendRequest.Fields.CREATION_DATE,
                        getEntityExpirationDate());
        return mongoClient.count(entityClass, filter);
    }

    public Flux<UserFriendRequest> findFriendRequests(
            @Nullable Set<Long> ids,
            @Nullable Set<Long> requesterIds,
            @Nullable Set<Long> recipientIds,
            @Nullable Set<RequestStatus> statuses,
            @Nullable DateRange creationDateRange,
            @Nullable DateRange responseDateRange,
            @Nullable DateRange expirationDateRange,
            @Nullable Integer page,
            @Nullable Integer size) {
        Date expirationDate = getEntityExpirationDate();
        Filter filter = Filter.newBuilder(9)
                .inIfNotNull(DomainFieldName.ID, ids)
                .inIfNotNull(UserFriendRequest.Fields.REQUESTER_ID, requesterIds)
                .inIfNotNull(UserFriendRequest.Fields.RECIPIENT_ID, recipientIds)
                .inIfNotNull(UserFriendRequest.Fields.STATUS, statuses)
                .addBetweenIfNotNull(UserFriendRequest.Fields.CREATION_DATE,
                        getCreationDateRange(creationDateRange, expirationDateRange))
                .addBetweenIfNotNull(UserFriendRequest.Fields.RESPONSE_DATE, responseDateRange)
                .isExpiredOrNot(statuses, UserFriendRequest.Fields.CREATION_DATE, expirationDate);
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return findExpirableDocs(filter, options);
    }

    public Flux<UserFriendRequest> findFriendRequestsByRecipientId(Long recipientId) {
        Filter filter = Filter.newBuilder(1)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId);
        return findExpirableDocs(filter);
    }

    public Flux<UserFriendRequest> findFriendRequestsByRequesterId(Long requesterId) {
        Filter filter = Filter.newBuilder(1)
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId);
        return findExpirableDocs(filter);
    }

    public Mono<Long> findRecipientId(Long requestId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, requestId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserFriendRequest.Fields.RECIPIENT_ID);
        return mongoClient.findOne(entityClass, filter, options)
                .map(UserFriendRequest::getRecipientId);
    }

    public Mono<UserFriendRequest> findRequesterAndRecipient(Long requestId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, requestId);
        QueryOptions options = QueryOptions.newBuilder(1)
                .include(UserFriendRequest.Fields.REQUESTER_ID,
                        UserFriendRequest.Fields.RECIPIENT_ID);
        return mongoClient.findOne(entityClass, filter, options);
    }

    public Mono<Boolean> hasPendingFriendRequest(Long requesterId, Long recipientId) {
        Date expirationDate = getEntityExpirationDate();
        Filter filter = Filter.newBuilder(4)
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .eq(UserFriendRequest.Fields.STATUS, RequestStatus.PENDING)
                // creationDate is used to:
                // 1. Make sure the pending requests are not expired actually
                // 2. Make full use of the compound index for better performance
                .isNotExpired(UserFriendRequest.Fields.CREATION_DATE,
                        expirationDate == null
                                ? DateConst.EPOCH
                                : expirationDate);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> hasPendingOrDeclinedOrIgnoredOrExpiredRequest(
            Long requesterId,
            Long recipientId) {
        // Do not need to check expirationDate because both PENDING status or EXPIRED status has
        // been used
        Filter filter = Filter.newBuilder(4)
                .gt(UserFriendRequest.Fields.CREATION_DATE, DateConst.EPOCH)
                .eq(UserFriendRequest.Fields.REQUESTER_ID, requesterId)
                .eq(UserFriendRequest.Fields.RECIPIENT_ID, recipientId)
                .in(UserFriendRequest.Fields.STATUS,
                        RequestStatus.PENDING,
                        RequestStatus.DECLINED,
                        RequestStatus.IGNORED,
                        RequestStatus.EXPIRED);
        return mongoClient.exists(entityClass, filter);
    }

}
