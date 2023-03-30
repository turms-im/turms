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

package im.turms.service.domain.common.repository;

import java.util.Date;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.common.po.Expirable;
import im.turms.service.domain.common.util.ExpirableRequestInspector;
import im.turms.service.domain.group.po.GroupInvitation;
import im.turms.service.domain.group.po.GroupJoinRequest;

/**
 * @author James Chen
 */
public abstract class ExpirableEntityRepository<T extends Expirable, K>
        extends BaseRepository<T, K> {

    protected ExpirableEntityRepository(TurmsMongoClient mongoClient, Class<T> entityClass) {
        super(mongoClient, entityClass);
    }

    protected abstract int getEntityExpireAfterSeconds();

    @Nullable
    public Date getEntityExpirationDate() {
        int expireAfterSeconds = getEntityExpireAfterSeconds();
        if (expireAfterSeconds <= 0) {
            return null;
        }
        return new Date(System.currentTimeMillis() - expireAfterSeconds * 1000L);
    }

    public Mono<Void> deleteExpiredData(String creationDateFieldName, Date expirationDate) {
        Filter filter = Filter.newBuilder(1)
                .isExpired(creationDateFieldName, expirationDate);
        return mongoClient.deleteMany(GroupJoinRequest.class, filter)
                .then();
    }

    protected Flux<T> findExpirableDocs(Filter filter) {
        return mongoClient.findMany(entityClass, filter)
                .map(request -> transformExpiredRequest(request, getEntityExpireAfterSeconds()));
    }

    protected Flux<T> findExpirableDocs(Filter filter, QueryOptions options) {
        return mongoClient.findMany(entityClass, filter, options)
                .map(request -> transformExpiredRequest(request, getEntityExpireAfterSeconds()));
    }

    protected Mono<T> findExpirableDoc(Filter filter, QueryOptions options) {
        return mongoClient.findOne(entityClass, filter, options)
                .map(request -> transformExpiredRequest(request, getEntityExpireAfterSeconds()));
    }

    public Flux<T> findMany(Filter filter) {
        return mongoClient.findMany(entityClass, filter);
    }

    public Flux<T> findMany(Filter filter, QueryOptions options) {
        return mongoClient.findMany(entityClass, filter, options);
    }

    protected void updateResponseDateBasedOnStatus(
            @NotNull Update update,
            @Nullable RequestStatus status,
            @Nullable Date responseDate) {
        if (status == null) {
            return;
        }
        if (ExpirableRequestInspector.isProcessedByResponder(status)) {
            if (responseDate == null) {
                responseDate = new Date();
            }
            update.set(GroupInvitation.Fields.RESPONSE_DATE, responseDate);
        } else {
            update.unset(GroupInvitation.Fields.RESPONSE_DATE);
        }
    }

    @Nullable
    protected DateRange getCreationDateRange(
            @Nullable DateRange creationDateRange,
            @Nullable DateRange expirationDateRange) {
        if (expirationDateRange == null) {
            return creationDateRange;
        }
        int expireAfterSeconds = getEntityExpireAfterSeconds();
        DateRange creationDateRangeFromExpiration = expireAfterSeconds > 0
                ? expirationDateRange.move(-expireAfterSeconds * 1000L)
                : null;
        if (creationDateRange == null) {
            return creationDateRangeFromExpiration;
        }
        if (creationDateRangeFromExpiration == null) {
            return creationDateRange;
        }
        return creationDateRange.intersect(creationDateRangeFromExpiration);
    }

    private T transformExpiredRequest(T request, int expireAfterSeconds) {
        if (isPendingRequestExpired(request.getStatus(),
                request.getCreationDate(),
                expireAfterSeconds)) {
            request.setStatus(RequestStatus.EXPIRED);
        }
        return request;
    }

    private boolean isPendingRequestExpired(
            RequestStatus status,
            Date creationDate,
            int expireAfterSeconds) {
        if (status == RequestStatus.PENDING) {
            if (expireAfterSeconds <= 0) {
                return false;
            }
            return System.currentTimeMillis() - creationDate.getTime() >= expireAfterSeconds
                    * 1000L;
        }
        return false;
    }

}
