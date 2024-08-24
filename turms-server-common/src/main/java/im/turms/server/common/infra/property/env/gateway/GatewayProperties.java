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
import im.turms.server.common.infra.property.env.gateway.network.TcpProperties;
import im.turms.server.common.infra.property.env.gateway.network.UdpProperties;
import im.turms.server.common.infra.property.env.gateway.network.WebSocketProperties;
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
    protected AdminApiProperties adminApi = new AdminApiProperties();

    @NestedConfigurationProperty
    protected ClientApiProperties clientApi = new ClientApiProperties();

    @NestedConfigurationProperty
    protected NotificationLoggingProperties notificationLogging =
            new NotificationLoggingProperties();

    // Business Behavior

    @NestedConfigurationProperty
    protected SessionProperties session = new SessionProperties();

    @NestedConfigurationProperty
    protected SimultaneousLoginProperties simultaneousLogin = new SimultaneousLoginProperties();

    // Cluster

    @NestedConfigurationProperty
    protected DiscoveryProperties serviceDiscovery = new DiscoveryProperties();

    // Data Store

    @NestedConfigurationProperty
    protected MongoGroupProperties mongo = new MongoGroupProperties();

    @NestedConfigurationProperty
    protected TurmsRedisProperties redis = new TurmsRedisProperties();

    // Faking

    @NestedConfigurationProperty
    protected FakeProperties fake = new FakeProperties();

    // Network Access Layer

    @NestedConfigurationProperty
    protected UdpProperties udp = new UdpProperties();

    @NestedConfigurationProperty
    protected TcpProperties tcp = new TcpProperties();

    @NestedConfigurationProperty
    protected WebSocketProperties websocket = new WebSocketProperties();

}