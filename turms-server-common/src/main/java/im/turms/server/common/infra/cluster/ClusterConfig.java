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

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import im.turms.server.common.infra.address.BaseServiceAddressManager;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.healthcheck.HealthCheckManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;

/**
 * @author James Chen
 */
@Configuration
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class ClusterConfig {

    @Bean
    public Node node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsApplicationContext turmsContext,
            TurmsPropertiesManager propertiesManager,
            BaseServiceAddressManager serviceAddressManager,
            HealthCheckManager healthCheckManager) {
        Node node = new Node(
                context,
                nodeType,
                turmsContext,
                propertiesManager,
                serviceAddressManager,
                healthCheckManager);
        // Note that the shutdown hook should be registered before "node#start"
        // because the node may fail to start and throw while the shutdown job should also run
        // in this case. e.g. unregister current node.
        turmsContext.addShutdownHook(JobShutdownOrder.CLOSE_NODE, node::stop);
        node.start();
        return node;
    }

}