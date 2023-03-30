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

package im.turms.server.common.infra.property.env.gateway.session;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.IdentityAccessManagementProperties;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class SessionProperties {

    @Description("A session will be closed if turms server does not receive any request "
            + "(including heartbeat request) from the client during closeIdleSessionAfterSeconds. References: "
            + "https://mp.weixin.qq.com/s?__biz=MzAwNDY1ODY2OQ==&mid=207243549&idx=1&sn=4ebe4beb8123f1b5ab58810ac8bc5994&scene=0#rd")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int closeIdleSessionAfterSeconds = 180;

    @Description("If the turms server only receives heartbeat requests from the client during switchProtocolAfterSeconds, "
            + "the TCP/WebSocket connection will be closed with the close status \"SWITCH\" to indicate the client "
            + "should keep sending heartbeat requests over UDP if they want to keep online. "
            + "Note: 1. The property only works if UDP is enabled; 2. For browser clients, UDP is not supported")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int switchProtocolAfterSeconds = closeIdleSessionAfterSeconds * 3;

    @Description("The minimum interval to refresh the heartbeat status by client requests "
            + "to avoid refreshing the heartbeat status frequently")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int minHeartbeatIntervalSeconds = closeIdleSessionAfterSeconds / 10;

    @Description("The client heartbeat interval. "
            + "Note that the value will NOT change the actual heartbeat behavior of clients, "
            + "and the value is only used to facilitate related operations of turms-gateway")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int clientHeartbeatIntervalSeconds = closeIdleSessionAfterSeconds / 3;

    @Description("Whether to notify clients of the session information after connected with the server")
    @GlobalProperty
    @MutableProperty
    private boolean notifyClientsOfSessionInfoAfterConnected = true;

    @NestedConfigurationProperty
    private IdentityAccessManagementProperties identityAccessManagement =
            new IdentityAccessManagementProperties();

    @NestedConfigurationProperty
    private DeviceDetailsProperties deviceDetails = new DeviceDetailsProperties();

}