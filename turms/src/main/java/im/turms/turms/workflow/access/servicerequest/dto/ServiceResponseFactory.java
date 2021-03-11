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

package im.turms.turms.workflow.access.servicerequest.dto;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceResponse;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author James Chen
 */
public final class ServiceResponseFactory {

    private static final Map<TurmsStatusCode, ServiceResponse> RESPONSE_POOL = new EnumMap<>(TurmsStatusCode.class);
    public static final ServiceResponse NO_CONTENT;

    static {
        for (TurmsStatusCode status : TurmsStatusCode.values()) {
            RESPONSE_POOL.put(status, new ServiceResponse(null, status, null));
        }
        NO_CONTENT = RESPONSE_POOL.get(TurmsStatusCode.NO_CONTENT);
    }

    private ServiceResponseFactory() {
    }

    public static ServiceResponse get(@NotNull TurmsStatusCode statusCode) {
        return RESPONSE_POOL.get(statusCode);
    }

    public static ServiceResponse get(@NotNull TurmsStatusCode statusCode, String message) {
        return message == null
                ? RESPONSE_POOL.get(statusCode)
                : new ServiceResponse(null, statusCode, message);
    }

    public static ServiceResponse get(
            @Nullable TurmsNotification.Data data,
            @NotNull TurmsStatusCode statusCode,
            @Nullable String message) {
        return data == null && message == null
                ? RESPONSE_POOL.get(statusCode)
                : new ServiceResponse(data, statusCode, message);
    }

}
