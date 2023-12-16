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

import im.turms.server.common.testing.environment.ServiceTestEnvironmentManager;
import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;
import im.turms.server.common.testing.environment.TestEnvironmentContainer;
import im.turms.server.common.testing.properties.RedisTestEnvironmentProperties;

/**
 * @author James Chen
 */
public class RedisTestEnvironmentManager extends ServiceTestEnvironmentManager
        implements RedisTestEnvironmentAware {

    private final RedisTestEnvironmentAware environment;

    public RedisTestEnvironmentManager(
            RedisTestEnvironmentProperties properties,
            TestEnvironmentContainer container) {
        super(properties.getType());
        environment = ServiceTestEnvironmentType.LOCAL == type
                ? new RedisLocalTestEnvironmentManager(properties.getLocal())
                : new RedisContainerTestEnvironmentManager(container);
    }

    @Override
    public ServiceTestEnvironmentType getRedisTestEnvironmentType() {
        return type;
    }

    @Override
    public boolean isRunning() {
        return environment.isRunning();
    }

    @Override
    public String getRedisHost() {
        return environment.getRedisHost();
    }

    @Override
    public int getRedisPort() {
        return environment.getRedisPort();
    }
}