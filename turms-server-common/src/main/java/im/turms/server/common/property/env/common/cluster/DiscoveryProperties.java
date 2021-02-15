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
    private int heartbeatTimeoutInSeconds = 30;

    @Min(0)
    @Max(SharedConfigService.EXPIRABLE_RECORD_TTL)
    private int heartbeatIntervalInSeconds = 10;

    @Min(0)
    @Max(SharedConfigService.EXPIRABLE_RECORD_TTL)
    private int reconnectIntervalInSeconds = 15;

    @Min(0)
    private int jitterInSeconds = 5;

    @NestedConfigurationProperty
    private Ssl clientSsl = new Ssl();

    @NestedConfigurationProperty
    private Ssl serverSsl = new Ssl();

}