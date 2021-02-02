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

package im.turms.gateway.access.tcp;

import im.turms.gateway.access.common.controller.UserRequestDispatcher;
import im.turms.gateway.access.tcp.factory.TcpServerFactory;
import im.turms.gateway.access.tcp.model.UserSessionWrapper;
import im.turms.gateway.pojo.bo.session.connection.TcpConnection;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.reactivestreams.Publisher;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.NettyInbound;
import reactor.netty.NettyOutbound;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.function.BiFunction;

/**
 * @author James Chen
 */
@Log4j2
@Component
public class TcpDispatcher {

    private final DisposableServer server;
    private final int closeIdleConnectionAfter;

    public TcpDispatcher(TurmsPropertiesManager propertiesManager,
                         ServerStatusManager serverStatusManager,
                         UserRequestDispatcher userRequestDispatcher) {
        closeIdleConnectionAfter = propertiesManager.getLocalProperties().getGateway().getTcp().getCloseIdleConnectionAfterSeconds();
        server = TcpServerFactory.create(
                propertiesManager,
                serverStatusManager,
                getConnectionHandler(userRequestDispatcher));
    }

    private BiFunction<NettyInbound, NettyOutbound, Publisher<Void>> getConnectionHandler(UserRequestDispatcher userRequestDispatcher) {
        return (inbound, outbound) -> {
            Connection connection = (Connection) inbound;
            InetSocketAddress address = (InetSocketAddress) connection.address();
            String ip = address.getHostString();
            TcpConnection tcpConnection = new TcpConnection(connection, true);
            UserSessionWrapper sessionWrapper = new UserSessionWrapper(tcpConnection, ip, closeIdleConnectionAfter, userSession -> {
                connection.outbound()
                        .send(userSession.getNotificationFlux(), byteBuf -> true)
                        .then()
                        .subscribe();
            });
            connection.inbound()
                    .receive()
                    .doOnNext(requestData -> {
                        if (!connection.isDisposed()) {
                            // Note that handleRequestData should never return MonoError
                            Mono<ByteBuf> response = userRequestDispatcher.handleRequest(sessionWrapper, requestData);
                            connection.outbound()
                                    .send(response, byteBuf -> true)
                                    .then()
                                    .subscribe();
                        }
                    })
                    .subscribe();
            return connection.onDispose();
        };
    }

    @PreDestroy
    public void preDestroy() {
        if (server != null) {
            server.dispose();
        }
    }

}