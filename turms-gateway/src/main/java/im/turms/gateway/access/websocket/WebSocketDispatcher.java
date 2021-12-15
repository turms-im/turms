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

package im.turms.gateway.access.websocket;

import im.turms.gateway.access.common.UserSessionDispatcher;
import im.turms.gateway.access.common.ClientRequestDispatcher;
import im.turms.gateway.access.websocket.factory.WebSocketFactory;
import im.turms.gateway.logging.ApiLoggingContext;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.WebSocketProperties;
import im.turms.server.common.service.blocklist.BlocklistService;
import org.springframework.stereotype.Component;
import reactor.netty.DisposableServer;

import javax.annotation.PreDestroy;

/**
 * @author James Chen
 */
@Component
public class WebSocketDispatcher extends UserSessionDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(WebSocketDispatcher.class);

    private final DisposableServer server;

    public WebSocketDispatcher(
            Node node,
            ApiLoggingContext apiLoggingContext,
            BlocklistService blocklistService,
            TurmsPropertiesManager propertiesManager,
            ServerStatusManager serverStatusManager,
            ServiceMediator serviceMediator,
            ClientRequestDispatcher clientRequestDispatcher) {
        super(apiLoggingContext, serviceMediator, clientRequestDispatcher,
                node.getSharedProperties().getGateway().getWebsocket().getCloseIdleConnectionAfterSeconds());
        WebSocketProperties webSocketProperties = propertiesManager.getLocalProperties().getGateway().getWebsocket();
        if (webSocketProperties.isEnabled()) {
            server = WebSocketFactory.create(
                    webSocketProperties,
                    blocklistService,
                    serverStatusManager,
                    bindConnectionWithSessionWrapper());
            LOGGER.info("WebSocket server started on {}:{}", server.host(), server.port());
        } else {
            server = null;
        }
    }

    @PreDestroy
    public void preDestroy() {
        if (server != null) {
            LOGGER.info("Closing WebSocket server");
            server.dispose();
        }
    }

}