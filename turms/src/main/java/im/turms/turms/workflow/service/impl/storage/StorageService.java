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

package im.turms.turms.workflow.service.impl.storage;

import im.turms.common.constant.ContentType;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.turms.plugin.extension.service.StorageServiceProvider;
import im.turms.turms.plugin.manager.TurmsPluginManager;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Service
@Validated
public class StorageService {

    private final StorageServiceProvider provider;

    public StorageService(TurmsPluginManager turmsPluginManager) {
        this.provider = turmsPluginManager.getStorageServiceProvider();
    }

    public Mono<String> queryPresignedGetUrl(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
        if (provider != null) {
            if (provider.isServing()) {
                return provider.queryPresignedGetUrl(requesterId, contentType, keyStr, keyNum);
            } else {
                throw TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE);
            }
        } else {
            throw TurmsBusinessException.get(TurmsStatusCode.NOT_IMPLEMENTED);
        }
    }

    public Mono<String> queryPresignedPutUrl(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum, long contentLength) {
        if (provider != null) {
            if (provider.isServing()) {
                return provider.queryPresignedPutUrl(requesterId, contentType, keyStr, keyNum, contentLength);
            } else {
                throw TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE);
            }
        } else {
            throw TurmsBusinessException.get(TurmsStatusCode.NOT_IMPLEMENTED);
        }
    }

    public Mono<Void> deleteResource(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
        if (provider != null) {
            if (provider.isServing()) {
                return provider.deleteResource(requesterId, contentType, keyStr, keyNum);
            } else {
                throw TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE);
            }
        } else {
            throw TurmsBusinessException.get(TurmsStatusCode.NOT_IMPLEMENTED);
        }
    }

}
