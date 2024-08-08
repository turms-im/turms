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

import im.turms.server.common.infra.property.constant.LoginConflictStrategy;
import im.turms.server.common.infra.property.constant.SimultaneousLoginStrategy;
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
public class SimultaneousLoginProperties {

    @Description("The simultaneous login strategy is used to control which devices can be online at the same time")
    @GlobalProperty
    @MutableProperty
    private SimultaneousLoginStrategy strategy =
            SimultaneousLoginStrategy.ALLOW_ONE_DEVICE_OF_EACH_DEVICE_TYPE_ONLINE;

    @Description("The login conflict strategy is used for servers to know how to behave "
            + "if a device is logging in when there are conflicted and logged-in devices")
    @GlobalProperty
    @MutableProperty
    private LoginConflictStrategy loginConflictStrategy =
            LoginConflictStrategy.DISCONNECT_LOGGED_IN_DEVICES;

    @Description("Whether to allow the devices of DeviceType.UNKNOWN to login")
    @GlobalProperty
    @MutableProperty
    private boolean allowDeviceTypeUnknownLogin = true;

    @Description("Whether to allow the devices of DeviceType.OTHERS to login")
    @GlobalProperty
    @MutableProperty
    private boolean allowDeviceTypeOthersLogin = true;

}
