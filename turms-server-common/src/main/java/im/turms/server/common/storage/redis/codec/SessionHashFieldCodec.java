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

package im.turms.server.common.storage.redis.codec;

import java.nio.ByteBuffer;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.session.bo.UserStatusField;
import im.turms.server.common.domain.session.bo.UserStatusFieldType;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.storage.redis.RedisEntryIdConst;

/**
 * @author James Chen
 */
public class SessionHashFieldCodec implements TurmsRedisCodec<Object> {

    @Override
    public ByteBuf encode(Object value) {
        if (value instanceof DeviceType deviceType) {
            return ByteBufUtil.getPooledPreferredByteBuffer(deviceType.getNumber());
        } else {
            // This should never happen because we set session status in lua currently
            return ByteBufUtil.getPooledPreferredByteBuffer((byte) value);
        }
    }

    @Override
    public Object decode(ByteBuffer in) {
        int readableBytes = in.remaining();
        byte b;
        byte[] nodeIdBytes;
        if (readableBytes == 1) {
            b = in.get();
            if (b == RedisEntryIdConst.FIELD_SESSIONS_STATUS) {
                return UserStatusField.USER_STATUS;
            }
            DeviceType deviceType = DeviceType.forNumber(b);
            if (deviceType != null) {
                return UserStatusField.getDeviceTypeToNodeIdField(deviceType);
            }
            nodeIdBytes = new byte[]{b};
        } else {
            nodeIdBytes = new byte[readableBytes];
            in.get(nodeIdBytes);
        }
        String nodeId = StringUtil.newLatin1String(nodeIdBytes);
        return new UserStatusField(UserStatusFieldType.NODE_ID_TO_HEARTBEAT_TIMESTAMP, nodeId);
    }

}