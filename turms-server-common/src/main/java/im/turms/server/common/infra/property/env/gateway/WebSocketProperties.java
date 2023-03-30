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
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class WebSocketProperties extends BaseServerProperties {

    @Description("The maximum number of connection requests waiting in the backlog queue. "
            + "Large enough to handle bursts and GC pauses "
            + "but do not set too large to prevent SYN-Flood attacks")
    private int backlog = 4096;

    @Description("Used to mitigate the Slowloris DoS attack by lowering the timeout for the TCP connection handshake")
    private int connectTimeout = 30;

    @Description("A WebSocket connection will be closed on the server side "
            + "if a client has not established a user session in a specified time. "
            + "Note that the developers on the client side should take the responsibility "
            + "to close the WebSocket connection according to their business requirements")
    private int closeIdleConnectionAfterSeconds = 60 * 5;

}