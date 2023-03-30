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
public class AddressProperties {

    @MutableProperty
    @Description("The advertise strategy is used to decide which type of address should be used"
            + " so that admins can access admin APIs and metrics APIs")
    private AdvertiseStrategy advertiseStrategy = AdvertiseStrategy.PRIVATE_ADDRESS;

    @MutableProperty
    @Description("The advertise address of the local node exposed to admins. "
            + "(e.g. 100.131.251.96)")
    private String advertiseHost = "";

    @MutableProperty
    @Description("Whether to attach the local port to the host.\n"
            + "e.g. The local host is 100.131.251.96, and the port is 9510"
            + " so the service address will be 100.131.251.96:9510")
    private boolean attachPortToHost = true;

}