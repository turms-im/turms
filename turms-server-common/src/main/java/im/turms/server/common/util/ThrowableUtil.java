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
import reactor.netty.channel.AbortedException;

import javax.annotation.Nullable;
import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.util.Set;
import java.util.function.Supplier;

/**
 * @author James Chen
 */
public final class ThrowableUtil {

    private static final Set<Class<?>> DISCONNECTED_CLIENT_EXCEPTIONS = Set.of(
            AbortedException.class,
            ClosedChannelException.class,
            EOFException.class,
            TlsChannelImpl.EofException.class
    );

    private ThrowableUtil() {
    }

    public static int countCauses(Throwable t) {
        int i = 0;
        while ((t = t.getCause()) != null) {
            i++;
        }
        return i;
    }

    @Nullable
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

    public static boolean isStatusCode(Throwable throwable, TurmsStatusCode statusCode) {
        return throwable instanceof TurmsBusinessException e
                && e.getCode() == statusCode;
    }

}