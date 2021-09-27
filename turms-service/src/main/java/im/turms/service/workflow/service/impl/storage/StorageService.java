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

package im.turms.service.workflow.service.impl.storage;

import im.turms.common.constant.ContentType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.util.AssertUtil;
import im.turms.service.plugin.TurmsPluginManager;
import im.turms.service.plugin.extension.StorageServiceProvider;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
@Service
public class StorageService {

    private final StorageServiceProvider provider;

    public StorageService(TurmsPluginManager turmsPluginManager) {
        provider = turmsPluginManager.getStorageServiceProvider();
    }

    public Mono<String> queryPresignedGetUrl(Long requesterId,
                                             ContentType contentType,
                                             @Nullable String keyStr,
                                             @Nullable Long keyNum) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(contentType, "contentType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (provider == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.STORAGE_NOT_IMPLEMENTED));
        }
        try {
            return provider.queryPresignedGetUrl(requesterId, contentType, keyStr, keyNum);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    public Mono<String> queryPresignedPutUrl(Long requesterId,
                                             ContentType contentType,
                                             @Nullable String keyStr,
                                             @Nullable Long keyNum,
                                             long contentLength) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(contentType, "contentType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (provider == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.STORAGE_NOT_IMPLEMENTED));
        }
        try {
            return provider.queryPresignedPutUrl(requesterId, contentType, keyStr, keyNum, contentLength);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

    public Mono<Void> deleteResource(Long requesterId,
                                     ContentType contentType,
                                     @Nullable String keyStr,
                                     @Nullable Long keyNum) {
        try {
            AssertUtil.notNull(requesterId, "requesterId");
            AssertUtil.notNull(contentType, "contentType");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (provider == null) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.STORAGE_NOT_IMPLEMENTED));
        }
        try {
            return provider.deleteResource(requesterId, contentType, keyStr, keyNum);
        } catch (Exception e) {
            return Mono.error(e);
        }
    }

}
