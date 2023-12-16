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

package im.turms.server.common.testing.environment.turmsgateway;

import org.testcontainers.containers.wait.strategy.Wait;

import im.turms.server.common.testing.ServiceLogConsumer;
import im.turms.server.common.testing.environment.ServiceContainerTestEnvironmentManager;
import im.turms.server.common.testing.environment.TestEnvironmentContainer;

import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_GATEWAY_SERVICE_ADMIN_HTTP_PORT;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_GATEWAY_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_GATEWAY_SERVICE_WEBSOCKET_PORT;

/**
 * @author James Chen
 */
public class TurmsGatewayContainerTestEnvironmentManager
        extends ServiceContainerTestEnvironmentManager implements TurmsGatewayTestEnvironmentAware {

    public TurmsGatewayContainerTestEnvironmentManager(TestEnvironmentContainer container) {
        super(container, TURMS_GATEWAY_SERVICE_NAME);
        container.withExposedService(serviceName, TURMS_GATEWAY_SERVICE_ADMIN_HTTP_PORT);
        container.withExposedService(serviceName, TURMS_GATEWAY_SERVICE_WEBSOCKET_PORT);
        container.withLogConsumer(serviceName, new ServiceLogConsumer(serviceName));
        container.waitingFor(serviceName, Wait.forHealthcheck());
    }

    @Override
    public String getTurmsGatewayAdminHttpHost() {
        return container.getServiceHost(serviceName, TURMS_GATEWAY_SERVICE_ADMIN_HTTP_PORT);
    }

    @Override
    public int getTurmsGatewayAdminHttpPort() {
        return container.getServicePort(serviceName, TURMS_GATEWAY_SERVICE_ADMIN_HTTP_PORT);
    }

    @Override
    public String getTurmsGatewayWebSocketHost() {
        return container.getServiceHost(serviceName, TURMS_GATEWAY_SERVICE_WEBSOCKET_PORT);
    }

    @Override
    public int getTurmsGatewayWebSocketPort() {
        return container.getServicePort(serviceName, TURMS_GATEWAY_SERVICE_WEBSOCKET_PORT);
    }

}