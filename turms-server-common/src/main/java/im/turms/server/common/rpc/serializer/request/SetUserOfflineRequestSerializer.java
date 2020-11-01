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

package im.turms.server.common.rpc.serializer.request;

import im.turms.common.constant.DeviceType;
import im.turms.server.common.cluster.service.serialization.serializer.Serializer;
import im.turms.server.common.cluster.service.serialization.serializer.SerializerId;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.rpc.request.SetUserOfflineRequest;
import im.turms.server.common.util.DeviceTypeUtil;
import io.netty.buffer.ByteBuf;

import java.util.Set;

/**
 * @author James Chen
 */
public class SetUserOfflineRequestSerializer implements Serializer<SetUserOfflineRequest> {

    @Override
    public void write(ByteBuf output, SetUserOfflineRequest data) {
        output.writeLong(data.getUserId());
        CloseReason closeReason = data.getCloseReason();
        int code = closeReason.getCode();
        output.writeShort(closeReason.isTurmsStatusCode() ? -code : code);
        Set<DeviceType> deviceTypes = data.getDeviceTypes();
        if (!deviceTypes.isEmpty()) {
            byte deviceTypesByte = DeviceTypeUtil.deviceTypesToByte(deviceTypes);
            output.writeByte(deviceTypesByte);
        }
    }

    @Override
    public SetUserOfflineRequest read(ByteBuf input) {
        long userId = input.readLong();
        int code = input.readShort();
        boolean isTurmsStatusCode = code < 0;
        if (isTurmsStatusCode) {
            code = -code;
        }
        CloseReason closeReason = CloseReason.get(code, null, isTurmsStatusCode);
        Set<DeviceType> deviceTypes = null;
        if (input.readableBytes() > 0) {
            byte deviceTypesMask = input.readByte();
            deviceTypes = DeviceTypeUtil.byte2DeviceTypes(deviceTypesMask);
        }
        return new SetUserOfflineRequest(userId, deviceTypes, closeReason);
    }

    @Override
    public int initialCapacity(SetUserOfflineRequest data) {
        int capacityForDeviceTypes = data.getDeviceTypes().isEmpty() ? 0 : 1;
        return Long.BYTES + Short.BYTES + capacityForDeviceTypes;
    }

    @Override
    public SerializerId getSerializerId() {
        return SerializerId.RPC_SET_USER_OFFLINE;
    }

}
