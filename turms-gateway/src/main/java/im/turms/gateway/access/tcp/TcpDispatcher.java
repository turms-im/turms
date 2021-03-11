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
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.TcpProperties;
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

    public TcpDispatcher(TurmsPropertiesManager propertiesManager,
                         ServiceMediator serviceMediator,
                         ServerStatusManager serverStatusManager,
                         UserRequestDispatcher userRequestDispatcher) {
        super(serviceMediator, userRequestDispatcher,
                propertiesManager.getLocalProperties().getGateway().getTcp().getCloseIdleConnectionAfterSeconds());
        TcpProperties tcpProperties = propertiesManager.getLocalProperties().getGateway().getTcp();
        if (tcpProperties.isEnabled()) {
            server = TcpServerFactory.create(
                    tcpProperties,
                    serverStatusManager,
                    bindConnectionWithSessionWrapper());
            log.info(String.format("TCP server started on %s:%d", server.host(), server.port()));
        } else {
            server = null;
        }
    }

    @PreDestroy
    public void preDestroy() {
        if (server != null) {
            log.info("Closing TCP server");
            server.dispose();
        }
    }

}