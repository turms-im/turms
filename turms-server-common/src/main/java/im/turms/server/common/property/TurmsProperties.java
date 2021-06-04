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

package im.turms.server.common.property;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.env.common.IpProperties;
import im.turms.server.common.property.env.common.LoggingProperties;
import im.turms.server.common.property.env.common.MonitorProperties;
import im.turms.server.common.property.env.common.PluginProperties;
import im.turms.server.common.property.env.common.SecurityProperties;
import im.turms.server.common.property.env.common.ServerAvailabilityProperties;
import im.turms.server.common.property.env.common.UserStatusProperties;
import im.turms.server.common.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.property.env.common.location.LocationProperties;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.property.env.service.ServiceProperties;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

/**
 * Don't use @Validated and hibernate-validator because it has a
 * noticeable impact on performance (CglibAopProxy + Reflection)
 * so we validate properties manually.
 *
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Component
@ConfigurationProperties(prefix = "turms", ignoreUnknownFields = false)
@Data
@NoArgsConstructor
public class TurmsProperties {

    // Common

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ClusterProperties cluster = new ClusterProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private IpProperties ip = new IpProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private LocationProperties location = new LocationProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private LoggingProperties logging = new LoggingProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private MonitorProperties monitor = new MonitorProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private PluginProperties plugin = new PluginProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private SecurityProperties security = new SecurityProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ServerAvailabilityProperties serverAvailability = new ServerAvailabilityProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private UserStatusProperties userStatus = new UserStatusProperties();

    // Gateway and Service

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private GatewayProperties gateway = new GatewayProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ServiceProperties service = new ServiceProperties();

}