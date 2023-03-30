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

package im.turms.service.domain.common.service;

import java.util.Date;
import jakarta.annotation.Nullable;

import im.turms.server.common.access.client.dto.constant.RequestStatus;
import im.turms.service.domain.common.po.Expirable;
import im.turms.service.domain.common.repository.ExpirableEntityRepository;
import im.turms.service.domain.common.util.ExpirableRequestInspector;

/**
 * @author James Chen
 */
public abstract class ExpirableEntityService<T extends Expirable> {

    private final ExpirableEntityRepository<T, ?> expirableEntityRepository;

    protected ExpirableEntityService(ExpirableEntityRepository<T, ?> expirableEntityRepository) {
        this.expirableEntityRepository = expirableEntityRepository;
    }

    @Nullable
    public Date getEntityExpirationDate() {
        return expirableEntityRepository.getEntityExpirationDate();
    }

    protected Date getResponseDateBasedOnStatusForNewRecord(
            Date now,
            @Nullable RequestStatus status,
            @Nullable Date responseDate) {
        if (ExpirableRequestInspector.isProcessedByResponder(status)) {
            if (responseDate == null) {
                return now;
            }
            return responseDate;
        }
        return null;
    }

}