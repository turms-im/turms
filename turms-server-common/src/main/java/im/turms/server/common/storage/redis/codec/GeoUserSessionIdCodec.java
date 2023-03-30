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
import im.turms.server.common.domain.session.bo.UserSessionId;

/**
 * @author James Chen
 */
public class GeoUserSessionIdCodec implements TurmsRedisCodec<UserSessionId> {

    @Override
    public ByteBuf encode(UserSessionId sessionId) {
        return BUFFER_ALLOCATOR.directBuffer(Long.BYTES + Byte.BYTES)
                .writeLong(sessionId.userId())
                .writeByte(sessionId.deviceType()
                        .getNumber());
    }

    @Override
    public UserSessionId decode(ByteBuffer in) {
        long userId = in.getLong();
        byte value = in.get();
        DeviceType deviceType = DeviceType.forNumber(value);
        if (deviceType == null) {
            throw new IllegalArgumentException(
                    "Could not find the device type for the number: "
                            + value);
        }
        return new UserSessionId(userId, deviceType);
    }

}
