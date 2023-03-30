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

import java.util.List;

import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.env.database.MongoProperties;
import im.turms.server.common.infra.property.env.service.env.redis.TurmsRedisProperties;
import im.turms.server.common.testing.BaseIntegrationTest;

/**
 * @author James Chen
 */
public class ContainerConfig {

    ContainerConfig(TurmsPropertiesManager propertiesManager) {
        String mongoUri = BaseIntegrationTest.ENV.getMongoUri("turms-test");
        String redisUri = BaseIntegrationTest.ENV.getRedisUri();

        TurmsProperties localProperties = propertiesManager.getLocalProperties();
        ServiceProperties service = localProperties.getService();

        localProperties.getCluster()
                .getSharedConfig()
                .getMongo()
                .setUri(mongoUri);

        MongoProperties serviceMongo = service.getMongo();
        serviceMongo.getAdmin()
                .setUri(mongoUri);
        serviceMongo.getUser()
                .setUri(mongoUri);
        serviceMongo.getGroup()
                .setUri(mongoUri);
        serviceMongo.getConversation()
                .setUri(mongoUri);
        serviceMongo.getMessage()
                .setUri(mongoUri);

        TurmsRedisProperties redis = service.getRedis();
        redis.getSequenceId()
                .setUriList(List.of(redisUri));
        redis.getSession()
                .setUriList(List.of(redisUri));
        redis.getLocation()
                .setUriList(List.of(redisUri));
        redis.getIpBlocklist()
                .setUri(redisUri);
        redis.getUserIdBlocklist()
                .setUri(redisUri);
    }

}
