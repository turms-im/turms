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

import im.turms.common.constant.DeviceType;
import im.turms.server.common.redis.RedisEntryId;
import org.springframework.data.redis.serializer.RedisElementReader;
import org.springframework.data.redis.serializer.RedisElementWriter;

import java.nio.ByteBuffer;

/**
 * @author James Chen
 */
public class SessionHashKeySerializer implements RedisElementWriter<Object>, RedisElementReader<Object> {

    @Override
    public ByteBuffer write(Object element) {
        byte data = element instanceof DeviceType
                ? (byte) ((DeviceType) element).getNumber()
                // im.turms.server.common.service.session.UserStatusService.STATUS_KEY_STATUS
                : (byte) element;
        return ByteBuffer.allocate(Byte.BYTES)
                .put(data)
                .flip();
    }

    @Override
    public Object read(ByteBuffer buffer) {
        byte data = buffer.get();
        return data == RedisEntryId.SESSIONS_STATUS
                ? RedisEntryId.SESSIONS_STATUS
                : DeviceType.forNumber(data);
    }

}