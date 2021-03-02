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

package im.turms.server.common.util;

import com.mongodb.internal.connection.tlschannel.impl.TlsChannelImpl;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import io.rsocket.exceptions.ConnectionCloseException;
import io.rsocket.exceptions.ConnectionErrorException;
import reactor.netty.channel.AbortedException;

import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author James Chen
 */
public class ExceptionUtil {

    private static final Set<Class<?>> DISCONNECTED_CLIENT_EXCEPTIONS = Set.of(
            AbortedException.class,
            ClosedChannelException.class,
            EOFException.class,
            TlsChannelImpl.EofException.class,
            ConnectionErrorException.class,
            ConnectionCloseException.class
    );

    private ExceptionUtil() {
    }

    public static <T> T suppress(Supplier<T> supplier) {
        try {
            return supplier.get();
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean isDisconnectedClientError(Throwable throwable) {
        Class<?> clazz = throwable.getClass();
        if (DISCONNECTED_CLIENT_EXCEPTIONS.contains(clazz)) {
            return true;
        }
        if (throwable instanceof IOException) {
            String message = throwable.getMessage();
            if (message == null) {
                return false;
            }
            return message.contains("An existing connection was forcibly closed")
                    || message.contains("Connection reset")
                    || message.contains("Broken pipe");
        }
        return false;
    }

    public static boolean isClientError(Throwable throwable) {
        if (throwable instanceof TurmsBusinessException) {
            return ((TurmsBusinessException) throwable).getCode().isServerError();
        } else {
            return false;
        }
    }

    public static boolean isStatusCode(Throwable throwable, TurmsStatusCode statusCode) {
        if (throwable instanceof TurmsBusinessException) {
            return ((TurmsBusinessException) throwable).getCode() == statusCode;
        } else {
            return false;
        }
    }

}