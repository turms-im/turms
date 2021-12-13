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

package im.turms.server.common.logging.core.appender;

import im.turms.server.common.logging.core.model.LogLevel;
import im.turms.server.common.logging.core.model.LogRecord;
import lombok.Data;
import lombok.SneakyThrows;

import java.nio.channels.FileChannel;

/**
 * @author James Chen
 */
@Data
public abstract class Appender implements AutoCloseable {

    private final String name;
    private final LogLevel level;

    protected int offset;
    protected FileChannel channel;

    @SneakyThrows
    protected Appender(String name, LogLevel level) {
        this.name = name;
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
        if (record.level().isLoggable(level)) {
            return channel.write(record.data().nioBuffer());
        }
        return 0;
    }

}
