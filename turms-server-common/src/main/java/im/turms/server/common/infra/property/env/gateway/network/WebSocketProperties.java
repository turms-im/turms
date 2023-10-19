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

package im.turms.server.common.infra.property.env.gateway.network;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.gateway.BaseServerProperties;
import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@SuperBuilder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WebSocketProperties extends BaseServerProperties {

    @Description("The maximum number of connection requests waiting in the backlog queue. "
            + "Large enough to handle bursts and GC pauses "
            + "but do not set too large to prevent SYN-Flood attacks")
    private int backlog = 4096;

    @Description("Used to mitigate the Slowloris DoS attack by lowering the timeout for the TCP connection handshake")
    private int connectTimeoutMillis = 30 * 1000;

    @NestedConfigurationProperty
    private WebSocketRemoteAddressSourceProperties remoteAddressSource =
            new WebSocketRemoteAddressSourceProperties();

    @NestedConfigurationProperty
    private WebSocketSessionProperties session = new WebSocketSessionProperties();
}