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

package im.turms.server.common.domain.session.bo;

import java.util.Map;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.lang.ClassUtil;

/**
 * @author James Chen
 */
public record UserStatusField(
        UserStatusFieldType fieldType,
        Object value
) {

    public static final UserStatusField USER_STATUS =
            new UserStatusField(UserStatusFieldType.USER_STATUS, null);
    private static final Map<DeviceType, UserStatusField> DEVICE_TYPE_TO_NODE_ID_FIELD;

    static {
        Map<DeviceType, UserStatusField> deviceTypeToNodeIdField =
                new FastEnumMap<>(DeviceType.class);
        UserStatusField field;
        for (DeviceType type : ClassUtil.getSharedEnumConstants(DeviceType.class)) {
            field = new UserStatusField(UserStatusFieldType.DEVICE_TYPE_TO_NODE_ID, type);
            deviceTypeToNodeIdField.put(type, field);
        }
        DEVICE_TYPE_TO_NODE_ID_FIELD = deviceTypeToNodeIdField;
    }

    public static UserStatusField getDeviceTypeToNodeIdField(DeviceType deviceType) {
        return DEVICE_TYPE_TO_NODE_ID_FIELD.get(deviceType);
    }

}