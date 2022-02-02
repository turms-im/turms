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

package im.turms.gateway.dao.config;

import im.turms.server.common.dao.BaseMongoConfig;
import im.turms.server.common.dao.domain.Admin;
import im.turms.server.common.dao.domain.AdminRole;
import im.turms.server.common.dao.domain.User;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Configuration
public class MongoConfig extends BaseMongoConfig {

    @Bean
    public TurmsMongoClient adminMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        TurmsMongoProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getMongo().getAdmin();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByClasses(Admin.class, AdminRole.class);
        return mongoClient;
    }

    @Bean
    @ConditionalOnProperty("turms.gateway.session.enable-authentication")
    public TurmsMongoClient userMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        TurmsMongoProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getMongo().getUser();
        TurmsMongoClient mongoClient = getMongoClient(properties);
        mongoClient.registerEntitiesByClasses(User.class);
        return mongoClient;
    }

    @Bean(IMongoCollectionInitializer.BEAN_NAME)
    public IMongoCollectionInitializer mongoDataGenerator() {
        return new IMongoCollectionInitializer() {
        };
    }

}