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

package im.turms.server.common.util;

import com.google.protobuf.GeneratedMessageV3;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.extern.log4j.Log4j2;
import reactor.core.Exceptions;

/**
 * @author James Chen
 */
@Log4j2
public class ProtoUtil {

    private ProtoUtil() {
    }

    /**
     * There haven't a good way to allocate pooled direct byte buffer
     * so we use the heap buffer for now
     */
    public static ByteBuf getByteBuffer(GeneratedMessageV3 message) {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer();
        ByteBufOutputStream bos = new ByteBufOutputStream(buffer);
        try {
            message.writeTo(bos);
        } catch (Exception e) {
            throw Exceptions.propagate(e);
        }
        return buffer;
    }

}
