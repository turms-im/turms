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

package im.turms.server.common.logging.core.processor;

import im.turms.server.common.logging.core.appender.Appender;
import im.turms.server.common.logging.core.idle.BackoffIdleStrategy;
import im.turms.server.common.logging.core.logger.InternalLogger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.logging.core.model.LogRecord;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.jctools.queues.MpscUnboundedArrayQueue;

import java.util.List;

/**
 * @author James Chen
 */
public final class LogProcessor {

    private final Thread thread;
    private volatile boolean active;

    public LogProcessor(MpscUnboundedArrayQueue<LogRecord> recordQueue) {
        Runnable processor = () -> {
            BackoffIdleStrategy idleStrategy = new BackoffIdleStrategy(128, 128, 1024000, 1024000);
            LogRecord record;
            Thread thread = Thread.currentThread();
            while (active && !thread.isInterrupted()) {
                while ((record = recordQueue.relaxedPoll()) != null) {
                    idleStrategy.reset();
                    List<Appender> appenders = record.logger().getAppenders();
                    for (Appender appender : appenders) {
                        try {
                            appender.append(record);
                        } catch (Exception e) {
                            InternalLogger.printException(e);
                        }
                    }
                    try {
                        record.data().release();
                    } catch (Exception e) {
                        InternalLogger.printException(e);
                    }
                }
                idleStrategy.idle();
            }
            List<Appender> appenders = LoggerFactory.getAllAppenders();
            for (Appender appender : appenders) {
                try {
                    appender.close();
                } catch (Throwable e) {
                    InternalLogger.printException(e);
                }
            }
        };
        thread = new DefaultThreadFactory("turms-log-processor")
                .newThread(processor);
        active = true;
        Runtime.getRuntime()
                .addShutdownHook(new DefaultThreadFactory("turms-log-shutdown")
                        .newThread(this::close));
    }

    public void start() {
        if (thread.getState() == Thread.State.NEW) {
            thread.start();
        }
    }

    public void close() {
        active = false;
    }

}
