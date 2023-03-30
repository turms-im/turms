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

package im.turms.server.common.infra.exception;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import lombok.Data;
import lombok.EqualsAndHashCode;

import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.FastEnumMap;

/**
 * @author James Chen
 */
@EqualsAndHashCode(callSuper = false)
@Data
public final class ResponseException extends StacklessException {

    private static final FastEnumMap<ResponseStatusCode, ResponseException> POOL =
            new FastEnumMap<>(ResponseStatusCode.class);

    static {
        for (ResponseStatusCode code : ResponseStatusCode.VALUES) {
            ResponseException exception = new ResponseException(code, code.getReason());
            POOL.put(code, exception);
        }
    }

    private final ResponseStatusCode code;
    @Nullable
    private final String reason;

    private ResponseException(@NotNull ResponseStatusCode code, @Nullable String reason) {
        super(formatMessage(code, reason));
        this.code = code;
        this.reason = reason;
    }

    private ResponseException(@NotNull ResponseStatusCode code, @Nullable Throwable cause) {
        super(formatMessage(code, null), cause);
        this.code = code;
        reason = null;
    }

    public static ResponseException get(@NotNull ResponseStatusCode code) {
        return POOL.get(code);
    }

    @Nullable
    public static ResponseException get(int statusCode) {
        for (ResponseStatusCode value : ResponseStatusCode.VALUES) {
            if (value.getBusinessCode() == statusCode) {
                return get(value);
            }
        }
        return null;
    }

    @Nullable
    public static ResponseException get(int statusCode, @Nullable String reason) {
        for (ResponseStatusCode value : ResponseStatusCode.VALUES) {
            if (value.getBusinessCode() == statusCode) {
                return get(value, reason);
            }
        }
        return null;
    }

    public static ResponseException get(@NotNull ResponseStatusCode code, @Nullable String reason) {
        return reason == null || reason.isEmpty()
                ? get(code)
                : new ResponseException(code, reason);
    }

    public static ResponseException get(
            @NotNull ResponseStatusCode code,
            @Nullable Throwable cause) {
        return new ResponseException(code, cause);
    }

    @Nullable
    public static ResponseException get(TurmsNotification notification) {
        int code = notification.getCode();
        return notification.hasReason()
                ? get(code, notification.getReason())
                : get(code);
    }

    private static String formatMessage(@NotNull ResponseStatusCode code, @Nullable String reason) {
        return reason == null
                ? "code: "
                        + code.getBusinessCode()
                : "code: "
                        + code.getBusinessCode()
                        + ", reason: "
                        + reason;
    }

}