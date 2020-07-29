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

package im.turms.server.common.util;

import im.turms.common.constant.DeviceType;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
public class DeviceTypeUtil {

    public static final DeviceType[] ALL_DEVICE_TYPES = DeviceType.values();
    public static final DeviceType[] ALL_AVAILABLE_DEVICE_TYPES = Arrays.stream(ALL_DEVICE_TYPES)
            .filter(deviceType -> deviceType != DeviceType.UNRECOGNIZED)
            .toArray(DeviceType[]::new);
    public static final Set<DeviceType> ALL_AVAILABLE_DEVICE_TYPES_SET = Arrays.stream(ALL_AVAILABLE_DEVICE_TYPES)
            .collect(Collectors.toSet());
    public static final Set<DeviceType> BROWSER_SET = Set.of(DeviceType.BROWSER);

    private static final int DEVICE_TYPE_COUNT = ALL_DEVICE_TYPES.length;

    private DeviceTypeUtil() {
    }

    public static Set<DeviceType> byte2DeviceTypes(byte deviceTypesByte) {
        Set<DeviceType> deviceTypes = EnumSet.noneOf(DeviceType.class);
        for (int i = 0; i < DEVICE_TYPE_COUNT; i++) {
            if (BitUtil.isBitSet(deviceTypesByte, i)) {
                deviceTypes.add(ALL_DEVICE_TYPES[i]);
            }
        }
        return deviceTypes;
    }

    public static byte deviceTypesToByte(Set<DeviceType> deviceTypes) {
        if (deviceTypes != null && !deviceTypes.isEmpty()) {
            byte deviceTypesByte = 0;
            for (DeviceType deviceType : deviceTypes) {
                // e.g.
                // The first device type (0) -> 0000 0001
                // The last device type (5) -> 0001 0000
                deviceTypesByte |= 1 << deviceType.getNumber();
            }
            return deviceTypesByte;
        } else {
            return 0;
        }
    }

}
