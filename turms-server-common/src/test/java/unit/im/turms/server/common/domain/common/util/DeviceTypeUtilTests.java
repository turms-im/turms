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

package unit.im.turms.server.common.domain.common.util;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.Test;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class DeviceTypeUtilTests {

    private static final Set<DeviceType> DEVICE_TYPES =
            Set.of(DeviceType.ANDROID, DeviceType.DESKTOP);
    private static final byte DEVICE_TYPES_BYTES = 9;

    @Test
    void byte2DeviceTypes_shouldReturnCorrespondingDeviceTypes_forNotZeroByte() {
        Set<DeviceType> deviceTypes = DeviceTypeUtil.byte2DeviceTypes(DEVICE_TYPES_BYTES);
        assertThat(deviceTypes).hasSameElementsAs(DEVICE_TYPES);
    }

    @Test
    void deviceTypesToByte_shouldReturnCorrespondingByte_forNotEmptyDeviceTypes() {
        byte deviceTypesByte = DeviceTypeUtil.deviceTypes2Byte(DEVICE_TYPES);
        assertThat(deviceTypesByte).isEqualTo(DEVICE_TYPES_BYTES);
    }

    @Test
    void byte2DeviceTypes_shouldReturnEmptyDeviceTypes_forZeroByte() {
        Set<DeviceType> deviceTypes = DeviceTypeUtil.byte2DeviceTypes((byte) 0);
        assertThat(deviceTypes).hasSameElementsAs(Collections.emptySet());
    }

    @Test
    void deviceTypesToByte_shouldReturn0_forEmptyDeviceTypes() {
        byte deviceTypesByte = DeviceTypeUtil.deviceTypes2Byte(Collections.emptySet());
        assertThat(deviceTypesByte).isZero();
    }

}
