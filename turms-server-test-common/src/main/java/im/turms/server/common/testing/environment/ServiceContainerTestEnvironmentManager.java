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

package im.turms.server.common.testing.environment;

import java.util.Optional;

import lombok.Getter;
import org.testcontainers.containers.ContainerState;

/**
 * @author James Chen
 */
public abstract class ServiceContainerTestEnvironmentManager
        implements ServiceTestEnvironmentAware {

    protected final TestEnvironmentContainer container;
    @Getter
    protected final String serviceName;

    protected ServiceContainerTestEnvironmentManager(
            TestEnvironmentContainer container,
            String serviceName) {
        this.container = container;
        this.serviceName = serviceName;
    }

    @Override
    public boolean isRunning() {
        Optional<ContainerState> containerState = container.getContainerByServiceName(serviceName);
        if (containerState.isPresent()) {
            return containerState.get()
                    .isRunning();
        }
        throw new RuntimeException(
                "Could not find the container with the service name: \""
                        + serviceName
                        + "\"");
    }
}