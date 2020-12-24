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

package im.turms.server.common.exception;

import im.turms.common.exception.NoStackTraceException;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.constant.TurmsStatusCode;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.EnumMap;
import java.util.Map;

/**
 * @author James Chen
 */
@EqualsAndHashCode(callSuper = false)
@Data
public class TurmsBusinessException extends NoStackTraceException {

    private static final Map<TurmsStatusCode, TurmsBusinessException> EXCEPTION_POOL = new EnumMap<>(TurmsStatusCode.class);

    static {
        for (TurmsStatusCode code : TurmsStatusCode.values()) {
            TurmsBusinessException exception = new TurmsBusinessException(code, code.getReason());
            EXCEPTION_POOL.put(code, exception);
        }
    }

    private final TurmsStatusCode code;
    private final String reason;

    private TurmsBusinessException(@NotNull TurmsStatusCode code, @Nullable String reason) {
        super(formatMessage(code, reason));
        this.code = code;
        this.reason = reason;
    }

    private TurmsBusinessException(@NotNull TurmsStatusCode code, @Nullable Throwable cause) {
        super(formatMessage(code, null), cause);
        this.code = code;
        this.reason = null;
    }

    public static TurmsBusinessException get(@NotNull TurmsStatusCode code) {
        return EXCEPTION_POOL.get(code);
    }

    public static TurmsBusinessException get(int statusCode) {
        for (TurmsStatusCode value : TurmsStatusCode.values()) {
            if (value.getBusinessCode() == statusCode) {
                return get(value);
            }
        }
        return null;
    }

    public static TurmsBusinessException get(int statusCode, @Nullable String reason) {
        for (TurmsStatusCode value : TurmsStatusCode.values()) {
            if (value.getBusinessCode() == statusCode) {
                return get(value, reason);
            }
        }
        return null;
    }

    public static TurmsBusinessException get(@NotNull TurmsStatusCode code, @Nullable String reason) {
        if (reason != null && !reason.isEmpty()) {
            return new TurmsBusinessException(code, reason);
        } else {
            return get(code);
        }
    }

    public static TurmsBusinessException get(@NotNull TurmsStatusCode code, @Nullable Throwable cause) {
        return new TurmsBusinessException(code, cause);
    }

    public static TurmsBusinessException get(TurmsNotification notification) {
        int code = notification.getCode().getValue();
        return notification.hasReason()
                ? TurmsBusinessException.get(code, notification.getReason().getValue())
                : TurmsBusinessException.get(code);
    }

    private static String formatMessage(@NotNull TurmsStatusCode code, @Nullable String reason) {
        if (reason != null) {
            return "code: " + code.getBusinessCode() + ", reason: " + reason;
        } else {
            return "code: " + code.getBusinessCode();
        }
    }

}