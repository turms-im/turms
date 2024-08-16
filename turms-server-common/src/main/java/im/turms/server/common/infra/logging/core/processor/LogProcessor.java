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

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.LockSupport;

import org.jctools.queues.MpscUnboundedArrayQueue;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.logging.core.appender.Appender;
import im.turms.server.common.infra.logging.core.idle.BackoffIdleStrategy;
import im.turms.server.common.infra.logging.core.logger.InternalLogger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.thread.ThreadNameConst;
import im.turms.server.common.infra.thread.TurmsThread;

/**
 * Note that we only use one thread to process logs, which means that we don't need to consider the
 * scenarios of concurrent modifications
 *
 * @author James Chen
 */
public final class LogProcessor {

    private final TurmsThread thread;
    private volatile boolean active;

    public LogProcessor(MpscUnboundedArrayQueue<LogRecord> recordQueue) {
        thread = TurmsThread
                .create(ThreadNameConst.LOG_PROCESSOR, false, () -> drainLogsForever(recordQueue));
    }

    public synchronized void start() {
        if (thread.getState() == Thread.State.NEW) {
            active = true;
            thread.start();
        }
    }

    public Mono<Void> close(Duration timeout) {
        if (!thread.isAlive()) {
            return Mono.empty();
        }
        return Mono.defer(() -> {
            active = false;
            // If idle, wake it up to take the last chance to write logs.
            LockSupport.unpark(thread);
            return thread.onTerminated()
                    .timeout(timeout)
                    .onErrorResume(TimeoutException.class, e -> thread.terminate());
        })
                .onErrorMap(t -> new RuntimeException(
                        "Caught an error while closing the log processor",
                        t));
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
                        InternalLogger.INSTANCE
                                .error("Caught an error while appending the log record: "
                                        + logRecord, e);
                    }
                }
                ReferenceCountUtil.safeEnsureReleased(logRecord.data());
            }
            if (!active) {
                break;
            }
            idleStrategy.idle();
            // Don't check if it is active here so that we can have
            // the last chance to output remaining logs after waking up.
        }
        for (Appender appender : LoggerFactory.getAllAppenders()) {
            try {
                appender.close();
            } catch (Exception e) {
                InternalLogger.INSTANCE.error("Caught an error while closing the appender: "
                        + appender, e);
            }
        }
    }

}