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

package im.turms.server.common.redis.codec;

import im.turms.common.constant.DeviceType;
import im.turms.server.common.redis.RedisEntryId;
import im.turms.server.common.util.ByteBufUtil;
import io.netty.buffer.ByteBuf;

import java.nio.ByteBuffer;

/**
 * @author James Chen
 */
public class SessionHashFieldCodec implements TurmsRedisCodec<Object> {

    @Override
    public ByteBuf encode(Object value) {
        if (value instanceof DeviceType) {
            return ByteBufUtil.getByteBuffer(((DeviceType) value).getNumber());
        } else {
            // This should never happen because we set session status in lua currently
            return ByteBufUtil.getByteBuffer((byte) value);
        }
    }

    @Override
    public Object decode(ByteBuffer in) {
        byte data = in.get();
        if (data == RedisEntryId.SESSIONS_STATUS) {
            return RedisEntryId.SESSIONS_STATUS;
        }
        DeviceType deviceType = DeviceType.forNumber(data);
        if (deviceType == null) {
            throw new IllegalArgumentException("Cannot parse " + data + "to DeviceType");
        }
        return deviceType;
    }

}