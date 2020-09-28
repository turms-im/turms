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

package im.turms.common.exception;

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.model.dto.notification.TurmsNotification;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author James Chen
 */
public class TurmsBusinessException extends NoStackTraceException {
    private static final Map<TurmsStatusCode, TurmsBusinessException> EXCEPTION_POOL = new EnumMap<>(TurmsStatusCode.class);
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

    public TurmsStatusCode getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TurmsBusinessException that = (TurmsBusinessException) o;
        return code == that.code
                && Objects.equals(reason, that.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, reason);
    }

    public static TurmsBusinessException get(@NotNull TurmsStatusCode code) {
        TurmsBusinessException exception = EXCEPTION_POOL.get(code);
        if (exception != null) {
            return exception;
        } else {
            TurmsBusinessException newException = new TurmsBusinessException(code, code.getReason());
            return EXCEPTION_POOL.put(code, newException);
        }
    }

    public static TurmsBusinessException get(int statusCode) {
        for (TurmsStatusCode value : TurmsStatusCode.values()) {
            if (value.getBusinessCode() == statusCode) {
                return get(value);
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

    public static TurmsBusinessException get(int statusCode, @Nullable String reason) {
        for (TurmsStatusCode value : TurmsStatusCode.values()) {
            if (value.getBusinessCode() == statusCode) {
                return get(value, reason);
            }
        }
        return null;
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
