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
import im.turms.server.common.infra.netty.ReferenceCountUtil;

import static im.turms.server.common.infra.logging.core.model.LogLevel.DEBUG_VALUE;
import static im.turms.server.common.infra.logging.core.model.LogLevel.ERROR_VALUE;
import static im.turms.server.common.infra.logging.core.model.LogLevel.FATAL_VALUE;
import static im.turms.server.common.infra.logging.core.model.LogLevel.INFO_VALUE;
import static im.turms.server.common.infra.logging.core.model.LogLevel.TRACE_VALUE;
import static im.turms.server.common.infra.logging.core.model.LogLevel.WARN_VALUE;

/**
 * @author James Chen
 */
@Data
public final class AsyncLogger implements Logger {

    private final String name;
    private final int level;
    private final List<Appender> appenders;
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
        this.name = name;
        this.shouldParse = shouldParse;
        this.appenders = appenders;
        this.layout = layout;
        this.queue = queue;
        nameForLog = name == null
                ? null
                : TurmsTemplateLayout.formatClassName(name);

        int level;
        if (appenders.isEmpty()) {
            level = Integer.MAX_VALUE;
        } else {
            level = -1;
            for (Appender appender : appenders) {
                level = Math.max(level,
                        appender.getLevel()
                                .ordinal());
            }
        }
        this.level = level;
    }

    @Override
    public boolean isTraceEnabled() {
        return level <= TRACE_VALUE;
    }

    @Override
    public boolean isDebugEnabled() {
        return level <= DEBUG_VALUE;
    }

    @Override
    public boolean isInfoEnabled() {
        return level <= INFO_VALUE;
    }

    @Override
    public boolean isWarnEnabled() {
        return level <= WARN_VALUE;
    }

    @Override
    public boolean isErrorEnabled() {
        return level <= ERROR_VALUE;
    }

    @Override
    public boolean isFatalEnabled() {
        return level <= FATAL_VALUE;
    }

    @Override
    public boolean isEnabled(LogLevel logLevel) {
        return level <= logLevel.ordinal();
    }

    @Override
    public void log(LogLevel level, String message) {
        if (!isEnabled(level)) {
            return;
        }
        doLog(level, message, null, null);
    }

    @Override
    public void log(LogLevel level, String message, Object... objects) {
        if (!isEnabled(level)) {
            return;
        }
        Throwable throwable = null;
        if (objects != null
                && objects.length > 0
                && objects[objects.length - 1] instanceof Throwable t) {
            throwable = t;
        }
        doLog(level, message, objects, throwable);
    }

    @Override
    public void log(LogLevel level, String message, Throwable throwable) {
        if (!isEnabled(level)) {
            return;
        }
        doLog(level, message, null, throwable);
    }

    @Override
    public void debug(String message, Object... args) {
        log(LogLevel.DEBUG, message, args);
    }

    @Override
    public void info(String message, Object... args) {
        log(LogLevel.INFO, message, args);
    }

    @Override
    public void info(ByteBuf message) {
        doLog(LogLevel.INFO, message);
    }

    @Override
    public void warn(String message) {
        doLog(LogLevel.WARN, message, null, null);
    }

    @Override
    public void warn(String message, Object... args) {
        log(LogLevel.WARN, message, args);
    }

    @Override
    public void error(Throwable throwable) {
        doLog(LogLevel.ERROR, null, null, throwable);
    }

    @Override
    public void error(String message, @Nullable Throwable throwable) {
        doLog(LogLevel.ERROR, message, null, throwable);
    }

    @Override
    public void error(String message, Object... args) {
        log(LogLevel.ERROR, message, args);
    }

    @Override
    public void error(ByteBuf message) {
        doLog(LogLevel.ERROR, message);
    }

    @Override
    public void fatal(String message, Object... args) {
        log(LogLevel.FATAL, message, args);
    }

    @Override
    public void fatal(String message, Throwable throwable) {
        doLog(LogLevel.FATAL, message, null, throwable);
    }

    @Override
    public void fatal(String message) {
        doLog(LogLevel.FATAL, message, null, null);
    }

    private void doLog(
            LogLevel level,
            CharSequence message,
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
            if (buffer != null) {
                ReferenceCountUtil.safeEnsureReleased(buffer);
            }
            InternalLogger.printException(e);
        }
    }

    private void doLog(LogLevel level, ByteBuf message) {
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
            if (buffer != null) {
                ReferenceCountUtil.safeEnsureReleased(buffer);
            }
            ReferenceCountUtil.safeEnsureReleased(message);
            InternalLogger.printException(e);
        }
    }

}
