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

package im.turms.server.common.cluster;

import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.manager.address.BaseServiceAddressManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.annotation.PreDestroy;

/**
 * @author James Chen
 */
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class ClusterConfig {

    private Node node;

    @Bean
    public Node node(
            ApplicationContext context,
            NodeType nodeType,
            TurmsPropertiesManager turmsPropertiesManager,
            BaseServiceAddressManager serviceAddressManager) {
        this.node = new Node(context, nodeType, turmsPropertiesManager, serviceAddressManager);
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