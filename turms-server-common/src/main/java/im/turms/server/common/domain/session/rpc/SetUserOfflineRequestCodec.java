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

package im.turms.server.common.domain.session.rpc;

import java.util.Set;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.cluster.service.codec.codec.CodecId;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamInput;
import im.turms.server.common.infra.cluster.service.codec.io.CodecStreamOutput;
import im.turms.server.common.infra.cluster.service.rpc.codec.RpcRequestCodec;

/**
 * @author James Chen
 */
public class SetUserOfflineRequestCodec extends RpcRequestCodec<SetUserOfflineRequest> {

    @Override
    public CodecId getCodecId() {
        return CodecId.RPC_SET_USER_OFFLINE;
    }

    @Override
    public void writeRequestData(CodecStreamOutput output, SetUserOfflineRequest data) {
        output.writeLong(data.getUserId());
        SessionCloseStatus closeStatus = data.getCloseStatus();
        int code = closeStatus.getCode();
        output.writeShort(code);
        Set<DeviceType> deviceTypes = data.getDeviceTypes();
        if (!deviceTypes.isEmpty()) {
            byte deviceTypesByte = DeviceTypeUtil.deviceTypes2Byte(deviceTypes);
            output.writeByte(deviceTypesByte);
        }
    }

    @Override
    public SetUserOfflineRequest readRequestData(CodecStreamInput input) {
        long userId = input.readLong();
        int code = input.readShort();
        SessionCloseStatus statusCode = SessionCloseStatus.get(code);
        Set<DeviceType> deviceTypes = null;
        if (input.readableBytes() > 0) {
            byte deviceTypesMask = input.readByte();
            deviceTypes = DeviceTypeUtil.byte2DeviceTypes(deviceTypesMask);
        }
        return new SetUserOfflineRequest(userId, deviceTypes, statusCode);
    }

    @Override
    public int initialCapacityForRequest(SetUserOfflineRequest data) {
        int capacityForDeviceTypes = data.getDeviceTypes()
                .isEmpty()
                        ? 0
                        : 1;
        return Long.BYTES + Short.BYTES + capacityForDeviceTypes;
    }

}
