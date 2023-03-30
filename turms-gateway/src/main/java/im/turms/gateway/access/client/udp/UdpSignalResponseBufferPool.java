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

package im.turms.gateway.access.client.udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import im.turms.gateway.access.client.udp.dto.UdpNotificationType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.lang.ClassUtil;

/**
 * @author James Chen
 */
public final class UdpSignalResponseBufferPool {

    private static final FastEnumMap<ResponseStatusCode, ByteBuf> CODE_POOL =
            new FastEnumMap<>(ResponseStatusCode.class);
    private static final FastEnumMap<UdpNotificationType, ByteBuf> NOTIFICATION_POOL =
            new FastEnumMap<>(UdpNotificationType.class);

    static {
        for (UdpNotificationType type : ClassUtil
                .getSharedEnumConstants(UdpNotificationType.class)) {
            NOTIFICATION_POOL.put(type,
                    Unpooled.unreleasableBuffer(Unpooled.directBuffer(Byte.BYTES)
                            .writeByte(type.ordinal() + 1)));
        }
    }

    private UdpSignalResponseBufferPool() {
    }

    public static ByteBuf get(ResponseStatusCode code) {
        ByteBuf buf = CODE_POOL.get(code);
        if (buf != null) {
            return buf;
        }
        synchronized (CODE_POOL) {
            buf = CODE_POOL.get(code);
            if (buf != null) {
                return buf;
            }
            if (code == ResponseStatusCode.OK) {
                buf = Unpooled.EMPTY_BUFFER;
            } else {
                buf = Unpooled.unreleasableBuffer(Unpooled.directBuffer(Short.BYTES)
                        .writeShort(code.getBusinessCode()));
            }
            CODE_POOL.put(code, buf);
            return buf;
        }
    }

    public static ByteBuf get(UdpNotificationType type) {
        return NOTIFICATION_POOL.get(type);
    }

}
