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

package im.turms.server.common.infra.property.env.common;

import java.util.List;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class IpProperties {

    /**
     * @implNote Removed addresses because they aren't available currently:
     *           bot.whatismyipaddress.com
     */
    @Description("The public IP detectors will only be used to query the public IP of the local node "
            + "if needed (e.g. If the node discovery property \"advertiseStrategy\" is \"PUBLIC_ADDRESS\". "
            + "Note that the HTTP response body must be a string of IP instead of a JSON")
    @MutableProperty
    private List<String> publicIpDetectorAddresses = List.of("https://checkip.amazonaws.com",
            "https://whatismyip.akamai.com",
            "https://ifconfig.me/ip",
            "https://myip.dnsomatic.com");

    @Description("The cached private IP will expire after the specified time has elapsed. 0 means no cache")
    @MutableProperty
    @Min(0)
    private int cachedPrivateIpExpireAfterMillis = 60 * 1000;

    @Description("The cached public IP will expire after the specified time has elapsed. 0 means no cache")
    @MutableProperty
    @Min(0)
    private int cachedPublicIpExpireAfterMillis = 60 * 1000;

}
