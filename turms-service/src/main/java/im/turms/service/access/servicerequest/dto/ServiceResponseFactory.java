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

package im.turms.service.access.servicerequest.dto;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.access.servicerequest.dto.ServiceResponse;
import im.turms.server.common.infra.collection.FastEnumMap;

/**
 * @author James Chen
 */
public final class ServiceResponseFactory {

    private static final FastEnumMap<ResponseStatusCode, ServiceResponse> POOL =
            new FastEnumMap<>(ResponseStatusCode.class);
    public static final ServiceResponse NO_CONTENT;

    static {
        for (ResponseStatusCode status : ResponseStatusCode.VALUES) {
            POOL.put(status, new ServiceResponse(null, status, null));
        }
        NO_CONTENT = POOL.get(ResponseStatusCode.NO_CONTENT);
    }

    private ServiceResponseFactory() {
    }

    public static ServiceResponse get(@NotNull ResponseStatusCode statusCode) {
        return POOL.get(statusCode);
    }

    public static ServiceResponse get(@NotNull ResponseStatusCode statusCode, String message) {
        return message == null
                ? POOL.get(statusCode)
                : new ServiceResponse(null, statusCode, message);
    }

    public static ServiceResponse get(
            @Nullable TurmsNotification.Data data,
            @NotNull ResponseStatusCode statusCode,
            @Nullable String message) {
        return data == null && message == null
                ? POOL.get(statusCode)
                : new ServiceResponse(data, statusCode, message);
    }

}
