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

package im.turms.gateway.access.client.tcp;

import lombok.Getter;
import org.springframework.stereotype.Component;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.netty.channel.ChannelOperations;

import im.turms.gateway.access.client.common.ClientRequestDispatcher;
import im.turms.gateway.access.client.common.UserSessionAssembler;
import im.turms.gateway.access.client.common.connection.NetConnection;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.logging.ApiLoggingContext;
import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.FeatureDisabledException;
import im.turms.server.common.infra.healthcheck.ServerStatusManager;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.gateway.TcpProperties;

/**
 * @author James Chen
 */
@Component
public class TcpUserSessionAssembler extends UserSessionAssembler {

    private static final Logger LOGGER = LoggerFactory.getLogger(TcpUserSessionAssembler.class);

    private final DisposableServer server;
    @Getter
    private final boolean enabled;
    private final String host;
    private final int port;

    public TcpUserSessionAssembler(
            ApiLoggingContext apiLoggingContext,
            TurmsApplicationContext applicationContext,
            TurmsPropertiesManager propertiesManager,
            BlocklistService blocklistService,
            ServerStatusManager serverStatusManager,
            SessionService sessionService,
            ClientRequestDispatcher clientRequestDispatcher) {
        super(apiLoggingContext,
                clientRequestDispatcher,
                sessionService,
                propertiesManager.getLocalProperties()
                        .getGateway()
                        .getTcp()
                        .getCloseIdleConnectionAfterSeconds());
        GatewayProperties gatewayProperties = propertiesManager.getLocalProperties()
                .getGateway();
        TcpProperties tcpProperties = gatewayProperties.getTcp();
        int maxRequestSizeBytes = gatewayProperties.getClientApi()
                .getMaxRequestSizeBytes();
        enabled = tcpProperties.isEnabled();
        if (enabled) {
            server = TcpServerFactory.create(tcpProperties,
                    blocklistService,
                    serverStatusManager,
                    sessionService,
                    bindConnectionWithSessionWrapper(),
                    maxRequestSizeBytes);
            host = server.host();
            port = server.port();
            LOGGER.info("TCP server started on: {}:{}", host, port);
            applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_GATEWAY_TCP_SERVER,
                    timeoutMillis -> {
                        server.dispose();
                        return server.onDispose();
                    });
        } else {
            server = null;
            host = null;
            port = -1;
        }
    }

    @Override
    protected NetConnection createConnection(Connection connection) {
        return new TcpConnection((ChannelOperations<?, ?>) connection, true);
    }

    public String getHost() {
        if (server == null) {
            throw new FeatureDisabledException("TCP server is disabled");
        }
        return host;
    }

    public int getPort() {
        if (server == null) {
            throw new FeatureDisabledException("TCP server is disabled");
        }
        return port;
    }

}