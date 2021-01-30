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

package im.turms.gateway.pojo.bo.session.connection;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.factory.NotificationFactory;
import im.turms.server.common.util.ExceptionUtil;
import im.turms.server.common.util.ProtoUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.io.buffer.NettyDataBufferFactory;
import org.springframework.web.reactive.socket.CloseStatus;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;

/**
 * @author James Chen
 */
@Log4j2
public class WebSocketConnection extends NetConnection {

    private final WebSocketSession connection;

    public WebSocketConnection(WebSocketSession connection, boolean isConnected) {
        super(isConnected);
        this.connection = connection;
    }

    /**
     * It's acceptable that the method isn't thread-safe
     */
    @Override
    public void close(@NotNull CloseReason closeReason) {
        if (isConnected() && connection.isOpen()) {
            super.close(closeReason);
            TurmsNotification closeNotification = NotificationFactory.fromReason(closeReason);
            WebSocketMessage message = connection.binaryMessage(factory -> ((NettyDataBufferFactory) factory).wrap(ProtoUtil.getDirectByteBuffer(closeNotification)));
            connection.send(Mono.just(message))
                    .doOnError(throwable -> {
                        if (!ExceptionUtil.isDisconnectedClientError(throwable)) {
                            log.error("Failed to send the close notification", throwable);
                        }
                    })
                    .retryWhen(RETRY_SEND_CLOSE_NOTIFICATION)
                    .onErrorResume(throwable -> {
                        log.error("Failed to send the close notification with retries exhausted: " + RETRY_SEND_CLOSE_NOTIFICATION.maxAttempts, throwable);
                        return Mono.empty();
                    })
                    .flatMap(unused -> connection.close(CloseStatus.NORMAL)
                            .onErrorResume(throwable -> {
                                if (!ExceptionUtil.isDisconnectedClientError(throwable)) {
                                    log.error("Failed to close the connection", throwable);
                                }
                                return Mono.empty();
                            }))
                    .subscribe();
        }
    }

}