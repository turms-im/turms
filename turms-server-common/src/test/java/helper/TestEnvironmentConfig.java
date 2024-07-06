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

package helper;

import lombok.extern.slf4j.Slf4j;

import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.mocknode.MockNodeProperties;
import im.turms.server.common.infra.property.env.mocknode.MongoGroupProperties;
import im.turms.server.common.testing.BaseIntegrationTest;
import im.turms.server.common.testing.environment.TestEnvironmentManager;
import im.turms.server.common.testing.properties.TestProperties;

/**
 * @author James Chen
 */
@Slf4j
public class TestEnvironmentConfig {

    public TestEnvironmentConfig(
            TurmsPropertiesManager propertiesManager,
            TestProperties testProperties) {
        TestEnvironmentManager testEnvironmentManager =
                BaseIntegrationTest.setupTestEnvironment(testProperties);
        configureTestEnvironmentProperties(propertiesManager, testEnvironmentManager);
    }

    private void configureTestEnvironmentProperties(
            TurmsPropertiesManager propertiesManager,
            TestEnvironmentManager testEnvironmentManager) {
        TurmsProperties localProperties = propertiesManager.getLocalProperties();
        MockNodeProperties mockNodeProperties = localProperties.getMockNode();

        String mongoUri = testEnvironmentManager.getMongoUri();

        // Cluster
        localProperties.getCluster()
                .getSharedConfig()
                .getMongo()
                .setUri(mongoUri);

        // Mongo
        MongoGroupProperties mongoGroupProperties = mockNodeProperties.getMongo();
        mongoGroupProperties.getAdmin()
                .setUri(mongoUri);
    }

}