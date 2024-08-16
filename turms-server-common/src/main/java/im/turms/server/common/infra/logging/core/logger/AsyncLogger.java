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

package im.turms.server.common.infra.logging.core.logger;

import java.util.List;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import lombok.Data;
import org.jctools.queues.MpscUnboundedArrayQueue;

import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.layout.TurmsTemplateLayout;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;

/**
 * @author James Chen
 */
@Data
public final class AsyncLogger extends BaseLogger {

    private final String name;
    private final TurmsTemplateLayout layout;
    private final MpscUnboundedArrayQueue<LogRecord> queue;

    private final byte[] nameForLog;
    private final boolean shouldParse;

    public AsyncLogger(
            @Nullable String name,
            boolean shouldParse,
            List<Appender> appenders,
            TurmsTemplateLayout layout,
            MpscUnboundedArrayQueue<LogRecord> queue) {
        super(appenders);
        this.name = name;
        this.shouldParse = shouldParse;
        this.layout = layout;
        this.queue = queue;
        nameForLog = name == null
                ? null
                : TurmsTemplateLayout.formatClassName(name);
    }

    @Override
    protected void doLog(
            LogLevel level,
            @Nullable CharSequence message,
            @Nullable Object[] args,
            @Nullable Throwable throwable) {
        ByteBuf buffer = null;
        try {
            buffer = layout.format(shouldParse, nameForLog, level, message, args, throwable);
            boolean offer =
                    queue.offer(new LogRecord(this, level, System.currentTimeMillis(), buffer));
            // Should never happen because the queue is unlimited
            if (!offer) {
                buffer.release();
            }
        } catch (Exception e) {
            String log = null;
            if (buffer != null) {
                try {
                    log = ByteBufUtil.readString(buffer);
                } catch (Exception ex) {
                    e.addSuppressed(new RuntimeException("Failed to read the log message", ex));
                }
                ReferenceCountUtil.safeEnsureReleased(buffer);
            }
            InternalLogger.INSTANCE.error("Caught an error while logging: "
                    + log, e);
        }
    }

    @Override
    protected void doLog(LogLevel level, ByteBuf message) {
        ByteBuf buffer = null;
        try {
            buffer = layout.format(nameForLog, level, message);
            boolean offer =
                    queue.offer(new LogRecord(this, level, System.currentTimeMillis(), buffer));
            // Should never happen because the queue is unlimited
            if (!offer) {
                buffer.release();
            }
        } catch (Exception e) {
            String log = null;
            if (buffer != null) {
                try {
                    log = ByteBufUtil.readString(buffer);
                } catch (Exception ex) {
                    e.addSuppressed(new RuntimeException("Failed to read the log message", ex));
                }
                ReferenceCountUtil.safeEnsureReleased(buffer);
            }
            ReferenceCountUtil.safeEnsureReleased(message);
            InternalLogger.INSTANCE.error("Caught an error while logging: "
                    + log, e);
        }
    }

}