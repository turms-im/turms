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

package im.turms.server.common.cluster.service.rpc;

import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.NoStackTraceException;
import io.rsocket.exceptions.ApplicationErrorException;
import io.rsocket.exceptions.Exceptions;
import io.rsocket.frame.ErrorFrameCodec;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.util.Pair;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;

/**
 * @author James Chen
 * @see ErrorFrameCodec#encode(io.netty.buffer.ByteBufAllocator, int, java.lang.Throwable)
 * @see Exceptions#from(int, io.netty.buffer.ByteBuf)
 */
@Getter
@EqualsAndHashCode
public class RpcException extends NoStackTraceException {

    private static final Map<Pair<RpcErrorCode, TurmsStatusCode>, RpcException> EXCEPTION_POOL;
    private static final int ERROR_CODE_LENGTH = 1;
    private static final int STATUS_CODE_LENGTH = 4;

    static {
        int initialCapacity = (RpcErrorCode.values().length * TurmsStatusCode.values().length) / 2;
        EXCEPTION_POOL = new HashMap<>(initialCapacity);
    }

    @NotNull
    private final RpcErrorCode errorCode;

    @NotNull
    private final TurmsStatusCode statusCode;

    @Nullable
    private final String message;

    private RpcException(RpcErrorCode errorCode, TurmsStatusCode statusCode) {
        super(errorCode.getErrorCode() + "" + statusCode.getBusinessCode());
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        message = null;
    }

    private RpcException(RpcErrorCode errorCode, TurmsStatusCode statusCode, String message) {
        super(errorCode.getErrorCode() + "" + statusCode.getBusinessCode() + message);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.message = message;
    }

    public boolean isServerError() {
        return errorCode != RpcErrorCode.FAILED_TO_RUN_RPC || statusCode.isServerError();
    }

    public static RpcException get(RpcErrorCode errorCode, TurmsStatusCode statusCode) {
        return EXCEPTION_POOL.computeIfAbsent(Pair.of(errorCode, statusCode), key -> new RpcException(errorCode, statusCode));
    }

    public static RpcException get(RpcErrorCode errorCode, TurmsStatusCode statusCode, String message) {
        return message != null
                ? new RpcException(errorCode, statusCode, message)
                : EXCEPTION_POOL.computeIfAbsent(Pair.of(errorCode, statusCode), key -> new RpcException(errorCode, statusCode));
    }

    // https://github.com/rsocket/rsocket-java/issues/741
    public static RpcException parse(ApplicationErrorException exception) {
        String exceptionMessage = exception.getMessage();
        if (exceptionMessage.isBlank()) {
            return null;
        }
        try {
            int errorCode = Integer.parseInt(exceptionMessage.substring(0, ERROR_CODE_LENGTH));
            int statusCode = Integer.parseInt(exceptionMessage.substring(ERROR_CODE_LENGTH, ERROR_CODE_LENGTH + STATUS_CODE_LENGTH));
            String message = null;
            if (exceptionMessage.length() > ERROR_CODE_LENGTH + STATUS_CODE_LENGTH) {
                message = exceptionMessage.substring(ERROR_CODE_LENGTH + STATUS_CODE_LENGTH);
            }
            RpcErrorCode rpcErrorCode = RpcErrorCode.from(errorCode);
            if (rpcErrorCode == null) {
                return null;
            }
            TurmsStatusCode turmsStatusCode = TurmsStatusCode.from(statusCode);
            if (turmsStatusCode == null) {
                return null;
            }
            return new RpcException(rpcErrorCode, turmsStatusCode, message);
        } catch (Exception e) {
            return null;
        }
    }

}
