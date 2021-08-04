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
import im.turms.server.common.property.env.common.AddressProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class DiscoveryProperties {

    @Min(0)
    @Max(SharedConfigService.EXPIRABLE_RECORD_TTL)
    private int heartbeatTimeoutSeconds = 30;

    @Min(0)
    private int heartbeatIntervalSeconds = 10;

    @Description("Delay to notify listeners on members change. Waits for seconds to avoid thundering herd")
    @Min(0)
    private int delayToNotifyMembersChangeSeconds = 3;

    @NestedConfigurationProperty
    private AddressProperties address = new AddressProperties();

}