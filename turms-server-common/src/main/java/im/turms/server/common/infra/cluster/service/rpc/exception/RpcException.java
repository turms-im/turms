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

package im.turms.server.common.infra.cluster.service.rpc.exception;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.service.rpc.RpcErrorCode;
import im.turms.server.common.infra.exception.StacklessException;
import im.turms.server.common.infra.lang.Pair;

import static im.turms.server.common.access.common.ResponseStatusCode.STATUS_CODE_LENGTH;
import static im.turms.server.common.infra.cluster.service.rpc.RpcErrorCode.ERROR_CODE_LENGTH;

/**
 * @author James Chen
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public final class RpcException extends StacklessException {

    private static final int CODE_STRING_LENGTH = ERROR_CODE_LENGTH + STATUS_CODE_LENGTH + 1;
    private static final Map<Pair<RpcErrorCode, ResponseStatusCode>, RpcException> EXCEPTION_POOL;

    static {
        int initialCapacity = (RpcErrorCode.VALUES.length * ResponseStatusCode.VALUES.length) / 2;
        EXCEPTION_POOL = new ConcurrentHashMap<>(initialCapacity);
    }

    @NotNull
    private final RpcErrorCode errorCode;

    @NotNull
    private final ResponseStatusCode statusCode;

    @Nullable
    private final String description;

    private RpcException(
            RpcErrorCode errorCode,
            ResponseStatusCode statusCode,
            @Nullable String description,
            @Nullable Throwable cause) {
        super(getErrorMessage(errorCode, statusCode, description), cause);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.description = description;
    }

    public static RpcException get(RpcErrorCode errorCode, ResponseStatusCode statusCode) {
        return EXCEPTION_POOL.computeIfAbsent(Pair.of(errorCode, statusCode),
                key -> new RpcException(errorCode, statusCode, null, null));
    }

    public static RpcException get(
            RpcErrorCode errorCode,
            ResponseStatusCode statusCode,
            String description) {
        if (description != null) {
            return new RpcException(errorCode, statusCode, description, null);
        }
        return get(errorCode, statusCode);
    }

    public static RpcException get(
            RpcErrorCode errorCode,
            ResponseStatusCode statusCode,
            String description,
            Throwable cause) {
        if (cause != null) {
            return new RpcException(errorCode, statusCode, description, cause);
        }
        return get(errorCode, statusCode, description);
    }

    public static boolean isErrorCode(Throwable throwable, RpcErrorCode code) {
        if (throwable instanceof RpcException e) {
            return e.getErrorCode()
                    .equals(code);
        }
        return false;
    }

    private static String getErrorMessage(
            RpcErrorCode errorCode,
            ResponseStatusCode statusCode,
            @Nullable String description) {
        String base = (errorCode.getErrorCode()
                + ":"
                + statusCode.getBusinessCode()).intern();
        return description == null
                ? base
                : base
                        + ":"
                        + description;
    }

}