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
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.plugin.extension.service.StorageServiceProvider;
import im.turms.turms.plugin.manager.TurmsPluginManager;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Service
public class StorageService {

    private final StorageServiceProvider provider;

    public StorageService(TurmsPluginManager turmsPluginManager) {
        this.provider = turmsPluginManager.getStorageServiceProvider();
    }

    public Mono<String> queryPresignedGetUrl(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(contentType, "contentType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (provider != null) {
            if (provider.isServing()) {
                return provider.queryPresignedGetUrl(requesterId, contentType, keyStr, keyNum);
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE));
            }
        } else {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_IMPLEMENTED));
        }
    }

    public Mono<String> queryPresignedPutUrl(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum, long contentLength) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(contentType, "contentType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (provider != null) {
            if (provider.isServing()) {
                return provider.queryPresignedPutUrl(requesterId, contentType, keyStr, keyNum, contentLength);
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE));
            }
        } else {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_IMPLEMENTED));
        }
    }

    public Mono<Void> deleteResource(@NotNull Long requesterId, @NotNull ContentType contentType, @Nullable String keyStr, @Nullable Long keyNum) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(contentType, "contentType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (provider != null) {
            if (provider.isServing()) {
                return provider.deleteResource(requesterId, contentType, keyStr, keyNum);
            } else {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNAVAILABLE));
            }
        } else {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.NOT_IMPLEMENTED));
        }
    }

}
