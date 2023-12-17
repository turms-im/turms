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

package im.turms.server.common.testing.environment.minio;

import im.turms.server.common.testing.environment.ServiceTestEnvironmentManager;
import im.turms.server.common.testing.environment.ServiceTestEnvironmentType;
import im.turms.server.common.testing.environment.TestEnvironmentContainer;
import im.turms.server.common.testing.properties.MinioTestEnvironmentProperties;

/**
 * @author James Chen
 */
public class MinioTestEnvironmentManager extends ServiceTestEnvironmentManager
        implements MinioTestEnvironmentAware {

    private final MinioTestEnvironmentAware environment;

    public MinioTestEnvironmentManager(
            MinioTestEnvironmentProperties properties,
            TestEnvironmentContainer container) {
        super(properties.getType());
        environment = ServiceTestEnvironmentType.LOCAL == type
                ? new MinioLocalTestEnvironmentManager(properties.getLocal())
                : new MinioContainerTestEnvironmentManager(container);
    }

    @Override
    public ServiceTestEnvironmentType getMinioTestEnvironmentType() {
        return type;
    }

    @Override
    public boolean isRunning() {
        return environment.isRunning();
    }

    @Override
    public String getMinioHost() {
        return environment.getMinioHost();
    }

    @Override
    public int getMinioPort() {
        return environment.getMinioPort();
    }

    @Override
    public String getMinioUsername() {
        return environment.getMinioUsername();
    }

    @Override
    public String getMinioPassword() {
        return environment.getMinioPassword();
    }
}