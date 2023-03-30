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

package integration.im.turms.gateway.storage.mongo;

import org.junit.jupiter.api.Test;

import im.turms.gateway.storage.mongo.MongoConfig;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.IdentityAccessManagementType;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.gateway.MongoProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.IdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.session.SessionProperties;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.testing.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class MongoConfigIT extends BaseIntegrationTest {

    @Test
    void userMongoClient_shouldReturnNotNullInstance() {
        MongoConfig mongoConfig = new MongoConfig(mock(TurmsApplicationContext.class));
        TurmsProperties properties = new TurmsProperties().toBuilder()
                .gateway(new GatewayProperties().toBuilder()
                        .mongo(new MongoProperties().toBuilder()
                                .user(new TurmsMongoProperties(getMongoUri()))
                                .build())
                        .session(new SessionProperties().toBuilder()
                                .identityAccessManagement(
                                        new IdentityAccessManagementProperties().toBuilder()
                                                .enabled(true)
                                                .type(IdentityAccessManagementType.PASSWORD)
                                                .build())
                                .build())
                        .build())
                .build();
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(properties);
        TurmsMongoClient mongoClient = mongoConfig.userMongoClient(propertiesManager);

        assertThat(mongoClient).isNotNull();
    }

}
