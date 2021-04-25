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

package im.turms.turms.workflow.service.impl;

import im.turms.server.common.bo.common.DateRange;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.turms.workflow.dao.domain.Expirable;
import im.turms.turms.workflow.service.util.RequestStatusUtil;
import reactor.core.publisher.Flux;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * @author James Chen
 */
public abstract class ExpirableModelService<T extends Expirable> {

    private final TurmsMongoClient mongoClient;
    private final Class<T> modelClass;

    protected ExpirableModelService(TurmsMongoClient mongoClient, Class<T> modelClass) {
        this.mongoClient = mongoClient;
        this.modelClass = modelClass;
    }

    protected abstract int getModelExpireAfterSeconds();

    @Nullable
    public Date getModelExpirationDate() {
        int expireAfterSeconds = getModelExpireAfterSeconds();
        if (expireAfterSeconds <= 0) {
            return null;
        }
        return new Date(System.currentTimeMillis() - expireAfterSeconds * 1000L);
    }

    @Nullable
    protected DateRange getCreationDateRange(@Nullable DateRange creationDateRange,
                                             @Nullable DateRange expirationDateRange) {
        if (expirationDateRange == null) {
            return creationDateRange;
        }
        int expireAfterSeconds = getModelExpireAfterSeconds();
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

    protected Flux<T> queryExpirableData(Filter filter) {
        return mongoClient.findMany(modelClass, filter)
                .map(request -> RequestStatusUtil.transformExpiredDoc(request, getModelExpireAfterSeconds()));
    }

    protected Flux<T> queryExpirableData(Filter filter, QueryOptions options) {
        return mongoClient.findMany(modelClass, filter, options)
                .map(request -> RequestStatusUtil.transformExpiredDoc(request, getModelExpireAfterSeconds()));
    }

}