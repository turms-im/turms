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

package im.turms.server.common.property.env.common.cluster;


import im.turms.server.common.cluster.service.config.SharedConfigService;
import im.turms.server.common.property.constant.AdvertiseStrategy;
import im.turms.server.common.property.metadata.annotation.Description;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.boot.web.server.Ssl;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class DiscoveryProperties {

    @Min(0)
    @Max(SharedConfigService.EXPIRABLE_RECORD_TTL)
    private int heartbeatTimeoutInSeconds = 10;

    @Min(0)
    @Max(SharedConfigService.EXPIRABLE_RECORD_TTL)
    private int heartbeatIntervalInSeconds = 3;

    @Min(0)
    @Max(SharedConfigService.EXPIRABLE_RECORD_TTL)
    private int reconnectIntervalInSeconds = 15;

    @Min(0)
    private int jitterInSeconds = 5;

    @NestedConfigurationProperty
    private AdvertiseProperties advertise = new AdvertiseProperties();

    @NestedConfigurationProperty
    private Ssl clientSsl = new Ssl();

    @NestedConfigurationProperty
    private Ssl serverSsl = new Ssl();

    @Data
    public static class AdvertiseProperties {

        @Description("The identity of this node exposes in the cluster if the identity is blank. The node name will be used as an identity " +
                "(e.g. \"turms-east-0001\")")
        private String identity = "";

        @Description("The advertise strategy is used to choose to expose which type of address so that other nodes can access the local node")
        private AdvertiseStrategy advertiseStrategy = AdvertiseStrategy.BIND_ADDRESS;

        @Description("The advertise host of the local node exposed to the other members. Better use a private host " +
                "(e.g. 100.131.251.96)")
        private String advertiseHost = "";

        @Description("Whether to attach the local port to the host.\n" +
                "e.g. The local host is 100.131.251.96, and the port is 9510" +
                "so that the service address exposed to the public will be 100.131.251.96:9510")
        private boolean attachPortToHost = true;

    }

}