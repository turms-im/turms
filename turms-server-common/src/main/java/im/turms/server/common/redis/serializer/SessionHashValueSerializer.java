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

package im.turms.server.common.redis.serializer;

import im.turms.common.constant.UserStatus;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @author James Chen
 */
public class SessionHashValueSerializer implements RedisElementWriter<Object>, RedisElementReader<Object> {

    @Override
    public ByteBuffer write(Object element) {
        if (element instanceof UserStatus) {
            // Note that we use the negative number for user status so that we can know
            // the type of the value from the first byte when deserializing
            int userStatus = ((UserStatus) element).getNumber();
            return ByteBuffer.allocate(Byte.BYTES)
                    .put((byte) userStatus)
                    .flip();
        } else if (element instanceof String) {
            byte[] nodeIdBytes = ((String) element).getBytes(StandardCharsets.UTF_8);
            if (nodeIdBytes.length == 0 || nodeIdBytes.length > Byte.MAX_VALUE) {
                throw new IllegalArgumentException("The length of the nodeId must be greater than 0 and less than 128");
            }
            return ByteBuffer.allocate(nodeIdBytes.length)
                    .put(nodeIdBytes)
                    .flip();
        } else {
            throw new IllegalArgumentException("The data must be an instance of UserStatus or String");
        }
    }

    @Override
    public Object read(ByteBuffer buffer) {
        int remaining = buffer.remaining();
        if (remaining == 0) {
            throw new IllegalStateException("The buffer should not be empty");
        } else if (remaining == 1) {
            byte value = buffer.get();
            UserStatus userStatus = UserStatus.forNumber(value);
            if (userStatus == null) {
                throw new IllegalArgumentException("Cannot convert " + value + " to UserStatus");
            }
            return userStatus;
        } else {
            byte[] bytes = new byte[remaining];
            buffer.get(bytes);
            return new String(bytes, StandardCharsets.UTF_8);
        }
    }

}
