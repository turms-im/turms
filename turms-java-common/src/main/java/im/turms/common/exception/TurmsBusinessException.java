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

import im.turms.common.TurmsStatusCode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class TurmsBusinessException extends NoStackTraceException {
    private static final Map<TurmsStatusCode, TurmsBusinessException> EXCEPTION_POOL = new EnumMap<>(TurmsStatusCode.class);
    private final TurmsStatusCode code;
    private final String reason;

    public TurmsStatusCode getCode() {
        return code;
    }

    public String getReason() {
        return reason;
    }

    private TurmsBusinessException(@Nonnull TurmsStatusCode code, @Nullable String reason) {
        this.code = code;
        this.reason = reason;
    }

    @Override
    public String toString() {
        return String.format("code: %d, reason: %s", code.getBusinessCode(), reason);
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o instanceof TurmsBusinessException) {
            TurmsBusinessException other = (TurmsBusinessException) o;
            if (code == other.getCode()) {
                if (reason != null) {
                    return reason.equals(other.reason);
                } else {
                    return other.reason == null;
                }
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = (result * PRIME) + code.hashCode();
        result = (result * PRIME) + (reason == null ? 0 : reason.hashCode());
        return result;
    }

    public static TurmsBusinessException get(@Nonnull TurmsStatusCode code) {
        return EXCEPTION_POOL.computeIfAbsent(code, key -> new TurmsBusinessException(code, code.getReason()));
    }

    public static TurmsBusinessException get(int statusCode) {
        for (TurmsStatusCode value : TurmsStatusCode.values()) {
            if (value.getBusinessCode() == statusCode) {
                return get(value);
            }
        }
        return null;
    }

    public static TurmsBusinessException get(@Nonnull TurmsStatusCode code, @Nullable String reason) {
        if (reason != null && !reason.isBlank()) {
            return new TurmsBusinessException(code, reason);
        } else {
            return get(code);
        }
    }

    public static TurmsBusinessException get(int statusCode, @Nullable String reason) {
        for (TurmsStatusCode value : TurmsStatusCode.values()) {
            if (value.getBusinessCode() == statusCode) {
                return get(value, reason);
            }
        }
        return null;
    }

    public static CompletableFuture getFuture(TurmsStatusCode statusCode) {
        return getFuture(statusCode, null);
    }

    public static CompletableFuture getFuture(TurmsStatusCode statusCode, @Nullable String reason) {
        CompletableFuture<Object> future = new CompletableFuture<>();
        future.completeExceptionally(get(statusCode, reason));
        return future;
    }
}
