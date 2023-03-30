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

package im.turms.server.common.infra.property.env.common.cluster;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.common.cluster.connection.ConnectionProperties;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class ClusterProperties {

    private String id = "turms";

    @NestedConfigurationProperty
    private NodeProperties node = new NodeProperties();

    @NestedConfigurationProperty
    private ConnectionProperties connection = new ConnectionProperties();

    @NestedConfigurationProperty
    private DiscoveryProperties discovery = new DiscoveryProperties();

    @NestedConfigurationProperty
    private SharedConfigProperties sharedConfig = new SharedConfigProperties();

    @NestedConfigurationProperty
    private RpcProperties rpc = new RpcProperties();

}