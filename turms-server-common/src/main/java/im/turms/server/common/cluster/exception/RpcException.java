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

package im.turms.server.common.cluster.exception;

import im.turms.common.exception.NoStackTraceException;
import im.turms.server.common.cluster.service.rpc.RpcErrorCode;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.util.IntegerUtil;
import io.rsocket.exceptions.ApplicationErrorException;
import io.rsocket.exceptions.Exceptions;
import io.rsocket.frame.ErrorFrameCodec;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.data.util.Pair;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static im.turms.server.common.cluster.service.rpc.RpcErrorCode.ERROR_CODE_LENGTH;
import static im.turms.server.common.constant.TurmsStatusCode.STATUS_CODE_LENGTH;

/**
 * @author James Chen
 * @see ErrorFrameCodec#encode(io.netty.buffer.ByteBufAllocator, int, java.lang.Throwable)
 * @see Exceptions#from(int, io.netty.buffer.ByteBuf)
 */
@Getter
@EqualsAndHashCode(callSuper = false)
public final class RpcException extends NoStackTraceException {

    private static final int CODE_STRING_LENGTH = ERROR_CODE_LENGTH + STATUS_CODE_LENGTH + 1;
    private static final Map<Pair<RpcErrorCode, TurmsStatusCode>, RpcException> EXCEPTION_POOL;

    static {
        int initialCapacity = (RpcErrorCode.values().length * TurmsStatusCode.values().length) / 2;
        EXCEPTION_POOL = new ConcurrentHashMap<>(initialCapacity);
    }

    @NotNull
    private final RpcErrorCode errorCode;

    @NotNull
    private final TurmsStatusCode statusCode;

    @Nullable
    private final String description;

    private RpcException(RpcErrorCode errorCode, TurmsStatusCode statusCode) {
        super((errorCode.getErrorCode() + ":" + statusCode.getBusinessCode()).intern());
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        description = null;
    }

    private RpcException(RpcErrorCode errorCode, TurmsStatusCode statusCode, @Nullable String description) {
        // FIXME: This is a terrible implementation to use getMessage() for both serialization and logging
        //  (RSocket uses getMessage() of Throwable to serialize the throwable instance,
        //  see io.rsocket.frame.ErrorFrameCodec.encode(io.netty.buffer.ByteBufAllocator, int, java.lang.Throwable))
        //  but getMessage() is also used to log throwable instances.
        //  So we should use a custom encoder once the issue https://github.com/rsocket/rsocket-java/issues/741 has been fixed.
        super(errorCode.getErrorCode() + ":" + statusCode.getBusinessCode() + description);
        this.errorCode = errorCode;
        this.statusCode = statusCode;
        this.description = description;
    }

    public static RpcException get(RpcErrorCode errorCode, TurmsStatusCode statusCode) {
        return EXCEPTION_POOL.computeIfAbsent(Pair.of(errorCode, statusCode), key -> new RpcException(errorCode, statusCode));
    }

    public static RpcException get(RpcErrorCode errorCode, TurmsStatusCode statusCode, String message) {
        return message != null
                ? new RpcException(errorCode, statusCode, message)
                : EXCEPTION_POOL.computeIfAbsent(Pair.of(errorCode, statusCode), key -> new RpcException(errorCode, statusCode));
    }

    public static RpcException parse(ApplicationErrorException exception) {
        String exceptionMessage = exception.getMessage();
        if (exceptionMessage == null || exceptionMessage.isBlank()) {
            return null;
        }
        RpcErrorCode errorCode = parseErrorCode(exceptionMessage);
        TurmsStatusCode statusCode = parseStatusCode(exceptionMessage);
        String message = exceptionMessage.length() > CODE_STRING_LENGTH
                ? exceptionMessage.substring(CODE_STRING_LENGTH)
                : null;
        return new RpcException(errorCode, statusCode, message);
    }

    private static RpcErrorCode parseErrorCode(String exceptionMessage) {
        RpcErrorCode rpcErrorCode;
        try {
            int errorCode = Integer.parseInt(exceptionMessage.substring(0, ERROR_CODE_LENGTH));
            rpcErrorCode = RpcErrorCode.from(errorCode);
        } catch (Exception e) {
            rpcErrorCode = null;
        }
        if (rpcErrorCode == null) {
            throw new IllegalArgumentException("Failed to parse error code for message: " + exceptionMessage);
        }
        return rpcErrorCode;
    }

    private static TurmsStatusCode parseStatusCode(String exceptionMessage) {
        TurmsStatusCode turmsStatusCode;
        try {
            int statusCode = IntegerUtil.parseInt(exceptionMessage, ERROR_CODE_LENGTH + 1, CODE_STRING_LENGTH);
            turmsStatusCode = TurmsStatusCode.from(statusCode);
        } catch (Exception e) {
            turmsStatusCode = null;
        }
        if (turmsStatusCode == null) {
            throw new IllegalArgumentException("Failed to parse status code for message: " + exceptionMessage);
        }
        return turmsStatusCode;
    }

    public boolean isServerError() {
        return errorCode != RpcErrorCode.FAILED_TO_RUN_RPC || statusCode.isServerError();
    }

    public static boolean isErrorCode(Throwable throwable, RpcErrorCode code) {
        if (throwable instanceof RpcException) {
            return ((RpcException) throwable).getErrorCode().equals(code);
        }
        return false;
    }

}