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

import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.property.env.common.cluster.NodeProperties;

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
            buffer = ByteBufUtil.getPooledPreferredByteBuffer(userStatus);
        } else if (element instanceof String nodeId) {
            // The node ID can only contain ASCII characters,
            // so we don't need to encode its coder
            byte[] nodeIdBytes = StringUtil.getBytes(nodeId);
            int length = nodeIdBytes.length;
            if (length == 0 || length > NodeProperties.NODE_ID_MAX_LENGTH) {
                throw new IllegalArgumentException(
                        "The length of the node ID must be greater than 0 "
                                + "and less than or equal to "
                                + NodeProperties.NODE_ID_MAX_LENGTH);
            }
            buffer = BUFFER_ALLOCATOR.directBuffer(length)
                    .writeBytes(nodeIdBytes);
        } else {
            throw new IllegalArgumentException(
                    "The data must be an instance of UserStatus or String");
        }
        return buffer;
    }

    @Override
    public Object decode(ByteBuffer in) {
        int remaining = in.remaining();
        if (remaining == 0) {
            throw new IllegalArgumentException("The buffer must not be empty");
        } else if (remaining == 1) {
            byte value = in.get();
            UserStatus userStatus = UserStatus.forNumber(value);
            if (userStatus != null) {
                return userStatus;
            }
            return StringUtil.newLatin1String(new byte[]{value});
        } else {
            byte[] bytes = new byte[remaining];
            in.get(bytes);
            return StringUtil.newLatin1String(bytes);
        }
    }
}
