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

package im.turms.server.common.property.env.gateway;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.env.gateway.redis.TurmsRedisProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author James Chen
 */
@Data
public class GatewayProperties {

    @Description("The url exposed to clients. See org.springframework.web.util.pattern.PathPattern")
    private String url = "/";

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private DatabaseProperties database = new DatabaseProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private DiscoveryProperties discovery = new DiscoveryProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private ClientApiProperties clientApi = new ClientApiProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private LogProperties log = new LogProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private PluginProperties plugin = new PluginProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private SimultaneousLoginProperties simultaneousLogin = new SimultaneousLoginProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private SessionProperties session = new SessionProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private TurmsRedisProperties redis = new TurmsRedisProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private UdpProperties udp = new UdpProperties();

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private TcpProperties tcp = new TcpProperties();

}
