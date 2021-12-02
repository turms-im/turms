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

import im.turms.gateway.access.common.UserSessionDispatcher;
import im.turms.gateway.access.common.controller.UserRequestDispatcher;
import im.turms.gateway.access.tcp.factory.TcpServerFactory;
import im.turms.gateway.logging.ApiLoggingContext;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.healthcheck.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.TcpProperties;
import im.turms.server.common.service.blocklist.BlocklistService;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.netty.DisposableServer;

import javax.annotation.PreDestroy;

/**
 * @author James Chen
 */
@Log4j2
@Component
public class TcpDispatcher extends UserSessionDispatcher {

    private final DisposableServer server;
    @Getter
    private final boolean enabled;
    private final String host;
    private final int port;

    public TcpDispatcher(ApiLoggingContext apiLoggingContext,
                         TurmsPropertiesManager propertiesManager,
                         BlocklistService blocklistService,
                         ServiceMediator serviceMediator,
                         ServerStatusManager serverStatusManager,
                         UserRequestDispatcher userRequestDispatcher) {
        super(apiLoggingContext, serviceMediator, userRequestDispatcher,
                propertiesManager.getLocalProperties().getGateway().getTcp().getCloseIdleConnectionAfterSeconds());
        TcpProperties tcpProperties = propertiesManager.getLocalProperties().getGateway().getTcp();
        enabled = tcpProperties.isEnabled();
        if (enabled) {
            server = TcpServerFactory.create(
                    tcpProperties,
                    blocklistService,
                    serverStatusManager,
                    bindConnectionWithSessionWrapper());
            host = server.host();
            port = server.port();
            log.info("TCP server started on {}:{}", host, port);
        } else {
            server = null;
            host = null;
            port = -1;
        }
    }

    @PreDestroy
    public void preDestroy() {
        if (server != null) {
            log.info("Closing TCP server");
            server.dispose();
        }
    }

    public String getHost() {
        if (server == null) {
            throw new IllegalStateException("TCP server is disabled");
        }
        return host;
    }

    public int getPort() {
        if (server == null) {
            throw new IllegalStateException("TCP server is disabled");
        }
        return port;
    }

}