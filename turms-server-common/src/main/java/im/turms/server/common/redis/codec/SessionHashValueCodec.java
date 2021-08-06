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

import im.turms.common.constant.UserStatus;
import im.turms.server.common.property.env.common.cluster.NodeProperties;
import im.turms.server.common.util.ByteBufUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public class SessionHashValueCodec implements TurmsRedisCodec<Object> {

    @Override
    public ByteBuf encode(Object element) {
        ByteBuf buffer;
        if (element instanceof UserStatus status) {
            // Note that we use the negative number for user status so that we can know
            // the type of the value from the first byte when deserializing
            int userStatus = status.getNumber();
            buffer = ByteBufUtil.getByteBuffer(userStatus);
        } else if (element instanceof String nodeId) {
            byte[] nodeIdBytes = nodeId.getBytes(StandardCharsets.UTF_8);
            if (nodeIdBytes.length == 0 || nodeIdBytes.length > NodeProperties.NODE_ID_MAX_LENGTH) {
                throw new IllegalArgumentException(
                        "The length of node ID must be greater than 0 and less than or equals to " + NodeProperties.NODE_ID_MAX_LENGTH);
            }
            buffer = UnpooledByteBufAllocator.DEFAULT.directBuffer(nodeIdBytes.length)
                    .writeBytes(nodeIdBytes);
        } else {
            throw new IllegalArgumentException("The data must be an instance of UserStatus or String");
        }
        return buffer;
    }

    @Override
    public Object decode(ByteBuffer in) {
        int remaining = in.remaining();
        if (remaining == 0) {
            throw new IllegalStateException("The buffer should not be empty");
        } else if (remaining == 1) {
            byte value = in.get();
            UserStatus userStatus = UserStatus.forNumber(value);
            if (userStatus == null) {
                throw new IllegalArgumentException("Cannot parse " + value + " to UserStatus");
            }
            return userStatus;
        } else {
            byte[] bytes = new byte[remaining];
            in.get(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }
}
