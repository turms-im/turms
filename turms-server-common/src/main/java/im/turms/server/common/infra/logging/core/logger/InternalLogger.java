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

import java.time.Instant;
import java.util.Arrays;
import java.util.List;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;

import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.layout.TurmsTemplateLayout;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;

/**
 * @author James Chen
 */
public class InternalLogger extends BaseLogger {

    public static InternalLogger INSTANCE;
    private static final byte[] NAME_FOR_LOG = InternalLogger.class.getName()
            .getBytes();

    private final TurmsTemplateLayout layout;

    private InternalLogger(List<Appender> appenders, TurmsTemplateLayout layout) {
        super(appenders);
        this.layout = layout;
    }

    public static synchronized void init(List<Appender> appenders, TurmsTemplateLayout layout) {
        if (INSTANCE != null) {
            return;
        }
        INSTANCE = new InternalLogger(appenders, layout);
    }

    @Override
    protected void doLog(
            LogLevel level,
            @Nullable CharSequence message,
            @Nullable Object[] args,
            @Nullable Throwable throwable) {
        try {
            doLog0(level, message, args, throwable);
        } catch (Exception ignored) {
            // Should not happen
        }
    }

    private void doLog0(
            LogLevel level,
            @Nullable CharSequence message,
            @Nullable Object[] args,
            @Nullable Throwable throwable) {
        ByteBuf buffer;
        try {
            buffer = layout.format(false, NAME_FOR_LOG, level, message, args, throwable);
        } catch (Exception e) {
            synchronized (System.err) {
                System.err.println(Instant.now()
                        + " Failed to format the log message: "
                        + message
                        + " with args: "
                        + Arrays.toString(args)
                        + " and the throwable: "
                        + throwable
                        + ". Cause: "
                        + e);
                e.printStackTrace();
            }
            return;
        }
        try {
            LogRecord record = new LogRecord(this, level, System.currentTimeMillis(), buffer);
            for (Appender appender : getAppenders()) {
                try {
                    appender.append(record);
                } catch (Exception ex) {
                    printInternalException(appender, record, ex);
                }
            }
        } finally {
            buffer.release();
        }
    }

    @Override
    protected void doLog(LogLevel level, ByteBuf message) {
        try {
            doLog0(level, message);
        } catch (Exception ignored) {
            // Should not happen
        }
    }

    private void doLog0(LogLevel level, ByteBuf message) {
        ByteBuf buffer;
        try {
            buffer = layout.format(NAME_FOR_LOG, level, message);
        } catch (Exception e) {
            synchronized (System.err) {
                System.err.println(Instant.now()
                        + " Failed to format the log message: "
                        + message
                        + ". Cause: "
                        + e);
                e.printStackTrace();
            }
            return;
        }
        try {
            LogRecord record = new LogRecord(this, level, System.currentTimeMillis(), buffer);
            for (Appender appender : getAppenders()) {
                try {
                    appender.append(record);
                } catch (Exception ex) {
                    printInternalException(appender, record, ex);
                }
            }
        } finally {
            buffer.release();
        }
    }

    private void printInternalException(Appender appender, LogRecord record, Exception e) {
        try {
            synchronized (System.err) {
                System.err.println(Instant.now()
                        + " The appender ("
                        + appender.getClass()
                                .getName()
                        + ") of the internal logger failed to append the log record: "
                        + record
                        + ". Cause: "
                        + e);
                e.printStackTrace();
            }
        } catch (Exception ex) {
            // nothing we can do, so just ignore.
        }
    }
}