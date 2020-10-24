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

package im.turms.gateway.access.tcp.model;

import im.turms.gateway.pojo.bo.session.UserSession;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import org.springframework.web.reactive.socket.CloseStatus;
import reactor.core.publisher.MonoSink;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;

/**
 * @author James Chen
 */
@Data
public class UserSessionWrapper {

    private final Connection connection;
    private final Sinks.Many<ByteBuf> outputSink;
    private final MonoSink<Void> completeSink;
    private UserSession userSession;
    private volatile boolean isOpen;

    public UserSessionWrapper(Connection connection,
                              Sinks.Many<ByteBuf> outputSink,
                              MonoSink<Void> completeSink,
                              boolean isOpen) {
        this.connection = connection;
        this.outputSink = outputSink;
        this.completeSink = completeSink;
        this.isOpen = isOpen;
    }

    public boolean hasUserSession() {
        return userSession != null;
    }

    public void close() {
        // No need to synchronize
        if (isOpen) {
            isOpen = false;
            outputSink.tryEmitComplete();
            completeSink.success();
            UserSession session = userSession;
            if (session != null) {
                session.close(CloseStatus.NORMAL);
            }
        }
    }

    public boolean isAvailable() {
        return !connection.isDisposed() && isOpen;
    }

}