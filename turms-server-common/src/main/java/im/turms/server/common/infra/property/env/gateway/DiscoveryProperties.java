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

import im.turms.server.common.infra.property.constant.AdvertiseStrategy;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class DiscoveryProperties {

    @MutableProperty
    @Description("The identity of the local node will be sent to clients as a notification if identity is not blank"
            + " and \"turms.gateway.session.notifyClientsOfSessionInfoAfterConnected\" is true"
            + " (e.g. \"turms-east-0001\")")
    private String identity = "";

    @MutableProperty
    @Description("The advertise strategy is used to help clients or load balancing servers to access the local node. "
            + "Note: For security, do NOT use \"PUBLIC_ADDRESS\" in production "
            + "to prevent from exposing the origin IP address for DDoS attack.")
    private AdvertiseStrategy advertiseStrategy = AdvertiseStrategy.PRIVATE_ADDRESS;

    @MutableProperty
    @Description("The advertise address of the local node exposed to the public. "
            + "The property can be used to advertise the DDoS Protected IP address to hide the origin IP address "
            + "(e.g. 100.131.251.96)")
    private String advertiseHost = "";

    @MutableProperty
    @Description("Whether to attach the local port to the host. "
            + "For example, if the local host is 100.131.251.96, and the port is 10510, "
            + "so the service address will be 100.131.251.96:10510")
    private boolean attachPortToHost = true;

}