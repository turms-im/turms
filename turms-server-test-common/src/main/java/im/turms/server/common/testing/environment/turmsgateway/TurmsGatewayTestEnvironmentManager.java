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

import im.turms.server.common.testing.environment.ServiceTestEnvironmentManager;
import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;
import im.turms.server.common.testing.environment.TestEnvironmentContainer;
import im.turms.server.common.testing.properties.TurmsGatewayTestEnvironmentProperties;

/**
 * @author James Chen
 */
public class TurmsGatewayTestEnvironmentManager extends ServiceTestEnvironmentManager
        implements TurmsGatewayTestEnvironmentAware {

    private final TurmsGatewayTestEnvironmentAware environment;

    public TurmsGatewayTestEnvironmentManager(
            TurmsGatewayTestEnvironmentProperties properties,
            TestEnvironmentContainer container) {
        super(properties.getType());
        environment = ServiceTestEnvironmentType.LOCAL == type
                ? new TurmsGatewayLocalTestEnvironmentManager(properties.getLocal())
                : new TurmsGatewayContainerTestEnvironmentManager(container);
    }

    @Override
    public String getTurmsGatewayAdminHttpHost() {
        return environment.getTurmsGatewayAdminHttpHost();
    }

    @Override
    public int getTurmsGatewayAdminHttpPort() {
        return environment.getTurmsGatewayAdminHttpPort();
    }

    @Override
    public String getTurmsGatewayWebSocketHost() {
        return environment.getTurmsGatewayWebSocketHost();
    }

    @Override
    public int getTurmsGatewayWebSocketPort() {
        return environment.getTurmsGatewayWebSocketPort();
    }
}