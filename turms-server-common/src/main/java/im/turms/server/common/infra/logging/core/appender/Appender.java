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

package im.turms.server.common.infra.logging.core.appender;

import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import lombok.Data;
import lombok.SneakyThrows;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author James Chen
 */
@Data
public abstract class Appender implements AutoCloseable {

    private final LogLevel level;

    protected FileChannel channel;

    @SneakyThrows
    protected Appender(LogLevel level) {
        this.level = level;
    }

    @SneakyThrows
    @Override
    public void close() {
        channel.force(true);
        channel.close();
    }

    @SneakyThrows
    public int append(LogRecord record) {
        if (!record.level().isLoggable(level)) {
            return 0;
        }
        ByteBuf buffer = record.data();
        if (buffer instanceof CompositeByteBuf buf) {
            int written = 0;
            for (ByteBuffer nioBuffer : buf.nioBuffers()) {
                written += channel.write(nioBuffer);
            }
            return written;
        }
        return channel.write(buffer.nioBuffer());
    }

}
