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

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

import io.netty.buffer.ByteBuf;
import lombok.Data;

import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;

/**
 * @author James Chen
 */
@Data
public abstract class Appender implements AutoCloseable {

    private final LogLevel level;

    protected FileChannel channel;

    protected Appender(LogLevel level) {
        this.level = level;
    }

    @Override
    public void close() {
        try {
            channel.force(true);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Caught an error while forcing updates to the channel's file to be written",
                    e);
        }
        try {
            channel.close();
        } catch (IOException e) {
            throw new InputOutputException("Caught an error while closing the channel", e);
        }
    }

    public int append(LogRecord record) {
        if (!record.level()
                .isLoggable(level)) {
            return 0;
        }
        ByteBuf buffer = record.data();
        if (buffer.nioBufferCount() == 1) {
            try {
                return channel.write(buffer.nioBuffer());
            } catch (IOException e) {
                throw new InputOutputException(
                        "Failed to write the buffer: "
                                + buffer,
                        e);
            }
        }
        int written = 0;
        for (ByteBuffer buf : buffer.nioBuffers()) {
            try {
                written += channel.write(buf);
            } catch (IOException e) {
                throw new InputOutputException(
                        "Failed to write the buffer: "
                                + buffer,
                        e);
            }
        }
        return written;
    }

}
