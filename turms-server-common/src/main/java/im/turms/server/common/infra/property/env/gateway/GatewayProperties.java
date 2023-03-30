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

package im.turms.server.common.infra.property.env.gateway;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.gateway.clientapi.ClientApiProperties;
import im.turms.server.common.infra.property.env.gateway.redis.TurmsRedisProperties;
import im.turms.server.common.infra.property.env.gateway.session.SessionProperties;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class GatewayProperties {

    // API

    @NestedConfigurationProperty
    private AdminApiProperties adminApi = new AdminApiProperties();

    @NestedConfigurationProperty
    private ClientApiProperties clientApi = new ClientApiProperties();

    @NestedConfigurationProperty
    private NotificationLoggingProperties notificationLogging = new NotificationLoggingProperties();

    // Business Behavior

    @NestedConfigurationProperty
    private SessionProperties session = new SessionProperties();

    @NestedConfigurationProperty
    private SimultaneousLoginProperties simultaneousLogin = new SimultaneousLoginProperties();

    // Cluster

    @NestedConfigurationProperty
    private DiscoveryProperties serviceDiscovery = new DiscoveryProperties();

    // Data Store

    @NestedConfigurationProperty
    private MongoProperties mongo = new MongoProperties();

    @NestedConfigurationProperty
    private TurmsRedisProperties redis = new TurmsRedisProperties();

    // Faking

    @NestedConfigurationProperty
    private FakeProperties fake = new FakeProperties();

    // Network Access Layer

    @NestedConfigurationProperty
    private UdpProperties udp = new UdpProperties();

    @NestedConfigurationProperty
    private TcpProperties tcp = new TcpProperties();

    @NestedConfigurationProperty
    private WebSocketProperties websocket = new WebSocketProperties();

}
