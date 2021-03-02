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

import im.turms.server.common.dao.domain.User;
import im.turms.server.common.mongo.IMongoDataGenerator;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.service.env.database.TurmsMongoProperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Log4j2
@Configuration
public class MongoConfig {

    @Bean
    @ConditionalOnProperty("turms.gateway.session.enable-authentication")
    public TurmsMongoClient userMongoClient(TurmsPropertiesManager turmsPropertiesManager) {
        TurmsMongoProperties properties = turmsPropertiesManager.getLocalProperties().getGateway().getMongo().getUser();
        TurmsMongoClient mongoClient = TurmsMongoClient.of(properties);
        mongoClient.registerEntitiesByClasses(User.class);
        return mongoClient;
    }

    @Bean(IMongoDataGenerator.BEAN_NAME)
    public IMongoDataGenerator mongoDataGenerator() {
        return new IMongoDataGenerator() {
        };
    }

}