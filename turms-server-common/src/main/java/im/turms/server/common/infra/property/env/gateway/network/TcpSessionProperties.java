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

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class TcpSessionProperties {

    @Description("turms-gateway will close the TCP connection if the client has not established a user session within the specified time. "
            + "0 means no timeout")
    @Min(0)
    private int establishTimeoutMillis = 60 * 5 * 1000;

    @Description("turms-gateway will send a TCP RST packet to the connection if the client has not closed "
            + "the TCP connection within the specified time after turms-gateway has sent and flushed the session close notification. "
            + "0 means sending a TCP RST packet immediately after flushing the session close notification, "
            + "and you should use 0 if you prefer fast connection close, but the client may never receive the last data sent by turms-gateway. "
            + "-1 means no timeout and waiting for the client to close the connection forever. "
            + "Positive value should be used when you prefer that turms-gateway waits for the client to receive within the specified time data "
            + "and only close the connection when it exceeds the timeout")
    @Min(-1)
    private int closeTimeoutMillis = 120 * 1000;

}