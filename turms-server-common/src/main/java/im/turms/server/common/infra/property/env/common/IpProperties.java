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

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.infra.property.metadata.annotation.Description;
import im.turms.server.common.infra.property.metadata.view.MutablePropertiesView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class IpProperties {

    @JsonView(MutablePropertiesView.class)
    @Description("The public IP detectors will only be used to query the public IP of the local node " +
            "if needed (e.g. If the discovery property \"advertiseStrategy\" is PUBLIC_ADDRESS")
    private List<String> publicIpDetectorAddresses =
            List.of("https://checkip.amazonaws.com", "https://bot.whatismyipaddress.com", "https://myip.dnsomatic.com");

}
