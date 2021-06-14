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

package im.turms.server.common.cluster.service.config.domain.property;

import im.turms.server.common.property.env.common.IpProperties;
import im.turms.server.common.property.env.common.LoggingProperties;
import im.turms.server.common.property.env.common.MonitorProperties;
import im.turms.server.common.property.env.common.PluginProperties;
import im.turms.server.common.property.env.common.ServerAvailabilityProperties;
import im.turms.server.common.property.env.common.UserStatusProperties;
import im.turms.server.common.property.env.common.cluster.ClusterProperties;
import im.turms.server.common.property.env.common.location.LocationProperties;
import im.turms.server.common.property.env.common.security.SecurityProperties;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
@AllArgsConstructor
public class CommonProperties {

    private ClusterProperties cluster;

    private IpProperties ip;

    private LocationProperties location;

    private LoggingProperties logging;

    private MonitorProperties monitor;

    private PluginProperties plugin;

    private SecurityProperties security;

    private ServerAvailabilityProperties serverAvailability;

    private UserStatusProperties userStatus;

}