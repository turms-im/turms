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

package im.turms.plugin.push.core;

import lombok.Getter;

/**
 * @author James Chen
 */
public enum PushNotificationErrorCode {
    // Server
    INVALID_REQUEST(100, false),

    INVALID_AUTH(110, false),
    AUTH_MISMATCH(111, false),

    INTERNAL_ERROR(120, true),

    // Client
    INACTIVE_TOKEN(200, false),

    // Service - Status
    SERVICE_ERROR(300, true),
    SERVICE_UNAVAILABLE(301, true),

    // Service - limit
    TOO_MANY_REQUESTS(400, true),
    QUOTA_EXCEEDED(401, false),

    // Unknown
    UNKNOWN(500, false);

    @Getter
    private final int code;

    @Getter
    private final boolean retryable;

    PushNotificationErrorCode(int code, boolean retryable) {
        this.code = code;
        this.retryable = retryable;
    }
}