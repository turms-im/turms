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

package unit.im.turms.gateway.dao.config;

import im.turms.gateway.dao.config.MongoConfig;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.property.env.gateway.MongoProperties;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class MongoConfigTests {

    @Test
    void userMongoClient_shouldReturnNotNullInstance() {
        MongoConfig mongoConfig = new MongoConfig();
        TurmsProperties turmsProperties = new TurmsProperties();
        GatewayProperties gateway = new GatewayProperties();
        MongoProperties mongo = new MongoProperties();
        mongo.setUser(new TurmsMongoProperties());
        gateway.setMongo(mongo);
        turmsProperties.setGateway(gateway);
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties())
                .thenReturn(turmsProperties);
        TurmsMongoClient mongoClient = mongoConfig.userMongoClient(propertiesManager);

        assertThat(mongoClient).isNotNull();
    }

}
