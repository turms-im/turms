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

package im.turms.server.common.infra.cluster;

import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.healthcheck.HealthCheckManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.annotation.PreDestroy;

/**
 * @author James Chen
 */
@Configuration
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class ClusterConfig {

    private Node node;

    @Bean
    public Node node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsApplicationContext turmsContext,
            TurmsPropertiesManager propertiesManager,
            BaseServiceAddressManager serviceAddressManager,
            HealthCheckManager healthCheckManager) {
        node = new Node(context, nodeType, turmsContext, propertiesManager, serviceAddressManager, healthCheckManager);
        node.start();
        return node;
    }

    @PreDestroy
    public void destroy() {
        if (node != null) {
            node.stop();
        }
    }

}