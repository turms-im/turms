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

package im.turms.server.common.infra.logging.core.processor;

import java.util.List;

import org.jctools.queues.MpscUnboundedArrayQueue;

import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.idle.BackoffIdleStrategy;
import im.turms.server.common.infra.logging.core.logger.InternalLogger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.thread.ThreadNameConst;

/**
 * Note that we only use one thread to process logs, which means that we don't need to consider the
 * scenarios of concurrent modifications
 *
 * @author James Chen
 */
public final class LogProcessor {

    private final Thread thread;
    private volatile boolean active;

    public LogProcessor(MpscUnboundedArrayQueue<LogRecord> recordQueue) {
        thread = new Thread(() -> drainLogsForever(recordQueue), ThreadNameConst.LOG_PROCESSOR);
        active = true;
    }

    public void start() {
        if (thread.getState() == Thread.State.NEW) {
            thread.start();
        }
    }

    public void waitClose(long timeoutMillis) {
        active = false;
        try {
            thread.join(timeoutMillis);
        } catch (InterruptedException e) {
            throw new RuntimeException(
                    "Caught an error while waiting the log processor to close",
                    e);
        }
    }

    private void drainLogsForever(MpscUnboundedArrayQueue<LogRecord> recordQueue) {
        BackoffIdleStrategy idleStrategy = new BackoffIdleStrategy(128, 128, 1024000, 1024000);
        LogRecord logRecord;
        while (true) {
            while ((logRecord = recordQueue.relaxedPoll()) != null) {
                idleStrategy.reset();
                List<Appender> appenders = logRecord.logger()
                        .getAppenders();
                for (Appender appender : appenders) {
                    try {
                        appender.append(logRecord);
                    } catch (Exception e) {
                        InternalLogger.printException(e);
                    }
                }
                ReferenceCountUtil.safeEnsureReleased(logRecord.data());
            }
            if (!active) {
                break;
            }
            idleStrategy.idle();
        }
        for (Appender appender : LoggerFactory.getAllAppenders()) {
            try {
                appender.close();
            } catch (Exception e) {
                InternalLogger.printException(e);
            }
        }
    }

}
