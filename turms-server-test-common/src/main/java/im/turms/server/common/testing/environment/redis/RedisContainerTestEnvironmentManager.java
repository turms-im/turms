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

package im.turms.server.common.testing.environment.redis;

import org.testcontainers.containers.wait.strategy.Wait;

import im.turms.server.common.testing.ServiceLogConsumer;
import im.turms.server.common.testing.environment.ServiceContainerTestEnvironmentManager;
import im.turms.server.common.testing.environment.TestEnvironmentContainer;

import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.REDIS_SERVICE_NAME;
import static im.turms.server.common.testing.environment.ContainerTestEnvironmentPropertyConst.REDIS_SERVICE_PORT;

/**
 * @author James Chen
 */
public class RedisContainerTestEnvironmentManager extends ServiceContainerTestEnvironmentManager
        implements RedisTestEnvironmentAware {

    public RedisContainerTestEnvironmentManager(TestEnvironmentContainer container) {
        super(container, REDIS_SERVICE_NAME);
        container.withExposedService(serviceName, REDIS_SERVICE_PORT);
        container.withLogConsumer(serviceName, new ServiceLogConsumer(serviceName));
        container.waitingFor(serviceName, Wait.forHealthcheck());
    }

    @Override
    public String getRedisHost() {
        return container.getServiceHost(serviceName, REDIS_SERVICE_PORT);
    }

    @Override
    public int getRedisPort() {
        return container.getServicePort(serviceName, REDIS_SERVICE_PORT);
    }

}