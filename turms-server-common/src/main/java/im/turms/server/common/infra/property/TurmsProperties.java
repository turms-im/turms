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

package im.turms.server.common.infra.property;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import im.turms.server.common.infra.property.env.common.FlightRecorderProperties;
import im.turms.server.common.infra.property.env.common.IpProperties;
import im.turms.server.common.infra.property.env.common.ShutdownProperties;
import im.turms.server.common.infra.property.env.common.UserStatusProperties;
import im.turms.server.common.infra.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.infra.property.env.common.healthcheck.HealthCheckProperties;
import im.turms.server.common.infra.property.env.common.location.LocationProperties;
import im.turms.server.common.infra.property.env.common.logging.LoggingProperties;
import im.turms.server.common.infra.property.env.common.plugin.PluginProperties;
import im.turms.server.common.infra.property.env.common.security.SecurityProperties;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.service.ServiceProperties;

/**
 * Don't use @Validated and hibernate-validator because it has a noticeable impact on performance
 * (CglibAopProxy + Reflection) so we validate properties manually.
 *
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Component
@ConfigurationProperties(prefix = TurmsProperties.PROPERTIES_PREFIX, ignoreUnknownFields = false)
@Data
@NoArgsConstructor
public class TurmsProperties {

    public static final String PROPERTIES_PREFIX = "turms";

    public static final int SCHEMA_VERSION = 1;

    // Common

    @NestedConfigurationProperty
    private ClusterProperties cluster = new ClusterProperties();

    @NestedConfigurationProperty
    private FlightRecorderProperties flightRecorder = new FlightRecorderProperties();

    @NestedConfigurationProperty
    private HealthCheckProperties healthCheck = new HealthCheckProperties();

    @NestedConfigurationProperty
    private IpProperties ip = new IpProperties();

    @NestedConfigurationProperty
    private LocationProperties location = new LocationProperties();

    @NestedConfigurationProperty
    private LoggingProperties logging = new LoggingProperties();

    @NestedConfigurationProperty
    private PluginProperties plugin = new PluginProperties();

    @NestedConfigurationProperty
    private SecurityProperties security = new SecurityProperties();

    @NestedConfigurationProperty
    private ShutdownProperties shutdown = new ShutdownProperties();

    @NestedConfigurationProperty
    private UserStatusProperties userStatus = new UserStatusProperties();

    // Gateway and Service

    @NestedConfigurationProperty
    private GatewayProperties gateway = new GatewayProperties();

    @NestedConfigurationProperty
    private ServiceProperties service = new ServiceProperties();

}