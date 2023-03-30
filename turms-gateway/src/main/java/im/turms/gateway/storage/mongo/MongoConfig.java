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

package im.turms.gateway.storage.mongo;

import java.util.Collections;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.connection.ClusterType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import im.turms.server.common.domain.admin.po.Admin;
import im.turms.server.common.domain.admin.po.AdminRole;
import im.turms.server.common.domain.user.po.User;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.constant.IdentityAccessManagementType;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.IdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.service.env.database.TurmsMongoProperties;
import im.turms.server.common.storage.mongo.BaseMongoConfig;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.TurmsMongoClient;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.mongo.MongoReactiveDataAutoConfiguration
 */
@Configuration
public class MongoConfig extends BaseMongoConfig {

    public MongoConfig(TurmsApplicationContext applicationContext) {
        super(applicationContext);
    }

    @Bean
    public TurmsMongoClient adminMongoClient(TurmsPropertiesManager propertiesManager) {
        TurmsMongoProperties properties = propertiesManager.getLocalProperties()
                .getGateway()
                .getMongo()
                .getAdmin();
        TurmsMongoClient mongoClient = getMongoClient(properties, "admin", Collections.emptySet());
        mongoClient.registerEntitiesByClasses(Admin.class, AdminRole.class);
        return mongoClient;
    }

    /**
     * @return null if {@link IdentityAccessManagementProperties#enabled} is false or
     *         {@link IdentityAccessManagementProperties#type} is not
     *         {@link IdentityAccessManagementType#PASSWORD}
     */
    @Nullable
    @Bean
    public TurmsMongoClient userMongoClient(TurmsPropertiesManager propertiesManager) {
        TurmsProperties localProperties = propertiesManager.getLocalProperties();
        IdentityAccessManagementProperties authenticationProperties = localProperties.getGateway()
                .getSession()
                .getIdentityAccessManagement();
        // TODO: use global "enabled"
        if (!authenticationProperties.isEnabled()
                || authenticationProperties.getType() != IdentityAccessManagementType.PASSWORD) {
            return null;
        }
        TurmsMongoProperties properties = localProperties.getGateway()
                .getMongo()
                .getUser();
        TurmsMongoClient mongoClient = getMongoClient(properties,
                "user",
                Set.of(ClusterType.SHARDED, ClusterType.LOAD_BALANCED));
        mongoClient.registerEntitiesByClasses(User.class);
        return mongoClient;
    }

    @Bean(IMongoCollectionInitializer.BEAN_NAME)
    public IMongoCollectionInitializer mongoDataGenerator() {
        return new IMongoCollectionInitializer() {
        };
    }

}