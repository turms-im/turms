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
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

/**
 * @author James Chen
 */
@Data
public class DiscoveryProperties {

    @JsonView(MutablePropertiesView.class)
    @Description("The identity of the local node will be sent to clients as a notification if identity is not blank" +
            " and turms.gateway.session.notifyClientsOfSessionInfoAfterConnected is true" +
            " (e.g. \"turms-east-0001\")")
    private String identity = "";

    @JsonView(MutablePropertiesView.class)
    @Description("The advertise strategy is used to help clients or load balancing servers to access the local node. " +
            "Note: For security, do NOT use PUBLIC_ADDRESS in the production environment " +
            "to prevent from exposing the origin IP address for DDoS attack.")
    private AdvertiseStrategy advertiseStrategy = AdvertiseStrategy.LOCAL_ADDRESS;

    @JsonView(MutablePropertiesView.class)
    @Description("The advertise address of the local node exposed to the public. " +
            "The property can be used to advertise the DDoS Protected IP address to hide the origin IP address)\n" +
            "(e.g. 100.131.251.96)")
    private String advertiseHost = "";

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to attach the local port to the host.\n" +
            "e.g. The local host is 100.131.251.96, and the port is 10510" +
            "so the service address will be 100.131.251.96:10510")
    private boolean attachPortToHost = true;

}