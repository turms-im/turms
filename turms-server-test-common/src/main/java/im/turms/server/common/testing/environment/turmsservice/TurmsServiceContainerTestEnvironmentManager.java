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

package im.turms.server.common.testing.environment.turmsservice;

import org.testcontainers.containers.wait.strategy.Wait;

import im.turms.server.common.testing.ServiceLogConsumer;
import im.turms.server.common.testing.environment.ServiceContainerTestEnvironmentManager;
import im.turms.server.common.testing.environment.TestEnvironmentContainer;

import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_SERVICE_ADMIN_HTTP_PORT;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.TURMS_SERVICE_SERVICE_NAME;

/**
 * @author James Chen
 */
public class TurmsServiceContainerTestEnvironmentManager
        extends ServiceContainerTestEnvironmentManager implements TurmsServiceTestEnvironmentAware {

    public TurmsServiceContainerTestEnvironmentManager(TestEnvironmentContainer container) {
        super(container, TURMS_SERVICE_SERVICE_NAME);
        container.withExposedService(serviceName, TURMS_SERVICE_ADMIN_HTTP_PORT);
        container.withLogConsumer(serviceName, new ServiceLogConsumer(serviceName));
        container.waitingFor(serviceName, Wait.forHealthcheck());
    }

    @Override
    public String getTurmsServiceAdminHttpHost() {
        return container.getServiceHost(serviceName, TURMS_SERVICE_ADMIN_HTTP_PORT);
    }

    @Override
    public int getTurmsServiceAdminHttpPort() {
        return container.getServicePort(serviceName, TURMS_SERVICE_ADMIN_HTTP_PORT);
    }

}