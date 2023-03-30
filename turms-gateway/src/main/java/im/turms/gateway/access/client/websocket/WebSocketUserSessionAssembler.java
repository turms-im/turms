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

package im.turms.gateway.access.client.websocket;

import org.springframework.stereotype.Component;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;

import im.turms.gateway.access.client.common.ClientRequestDispatcher;
import im.turms.gateway.access.client.common.UserSessionAssembler;
import im.turms.gateway.access.client.common.connection.NetConnection;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.logging.ApiLoggingContext;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.gateway.WebSocketProperties;

/**
 * @author James Chen
 */
@Component
public class WebSocketUserSessionAssembler extends UserSessionAssembler {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(WebSocketUserSessionAssembler.class);

    private final DisposableServer server;

    public WebSocketUserSessionAssembler(
            ApiLoggingContext apiLoggingContext,
            BlocklistService blocklistService,
            TurmsApplicationContext applicationContext,
            TurmsPropertiesManager propertiesManager,
            ServerStatusManager serverStatusManager,
            SessionService sessionService,
            ClientRequestDispatcher clientRequestDispatcher) {
        super(apiLoggingContext,
                clientRequestDispatcher,
                sessionService,
                propertiesManager.getGlobalProperties()
                        .getGateway()
                        .getWebsocket()
                        .getCloseIdleConnectionAfterSeconds());
        GatewayProperties gatewayProperties = propertiesManager.getLocalProperties()
                .getGateway();
        WebSocketProperties webSocketProperties = gatewayProperties.getWebsocket();
        if (webSocketProperties.isEnabled()) {
            server = WebSocketServerFactory.create(webSocketProperties,
                    blocklistService,
                    serverStatusManager,
                    sessionService,
                    bindConnectionWithSessionWrapper(),
                    gatewayProperties.getClientApi()
                            .getMaxRequestSizeBytes());
            LOGGER.info("WebSocket server started on: {}:{}", server.host(), server.port());
            applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_GATEWAY_WEBSOCKET_SERVER,
                    timeoutMillis -> {
                        server.dispose();
                        return server.onDispose();
                    });
        } else {
            server = null;
        }
    }

    @Override
    protected NetConnection createConnection(Connection connection) {
        return new WebSocketConnection(connection, true);
    }

}