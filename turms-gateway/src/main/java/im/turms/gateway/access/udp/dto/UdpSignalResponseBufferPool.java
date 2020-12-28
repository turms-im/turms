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

package im.turms.gateway.access.udp.dto;

import im.turms.common.model.dto.udpsignal.UdpNotificationType;
import im.turms.server.common.constant.TurmsStatusCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.Unpooled;

import java.util.EnumMap;
import java.util.Map;

/**
 * @author James Chen
 */
public class UdpSignalResponseBufferPool {

    private static final Map<TurmsStatusCode, ByteBuf> CODE_POOL = new EnumMap<>(TurmsStatusCode.class);
    private static final Map<UdpNotificationType, ByteBuf> NOTIFICATION_POOL = new EnumMap<>(UdpNotificationType.class);

    private UdpSignalResponseBufferPool() {
    }

    public static ByteBuf get(TurmsStatusCode code) {
        return CODE_POOL.computeIfAbsent(code, key -> {
            if (key == TurmsStatusCode.OK) {
                return new EmptyByteBuf(ByteBufAllocator.DEFAULT);
            } else {
                ByteBuf buffer = Unpooled.directBuffer(Short.BYTES);
                buffer.writeShort(key.getBusinessCode());
                return Unpooled.unreleasableBuffer(buffer);
            }
        });
    }

    public static ByteBuf get(UdpNotificationType type) {
        return NOTIFICATION_POOL.computeIfAbsent(type, key -> {
            ByteBuf buffer = Unpooled.directBuffer(Byte.BYTES);
            buffer.writeByte(key.ordinal() + 1);
            return Unpooled.unreleasableBuffer(buffer);
        });
    }

}
