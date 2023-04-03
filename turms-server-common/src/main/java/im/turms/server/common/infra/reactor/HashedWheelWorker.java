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

package im.turms.server.common.infra.reactor;

import java.util.concurrent.TimeUnit;

import io.netty.util.HashedWheelTimer;
import reactor.core.Disposable;
import reactor.core.Scannable;
import reactor.core.scheduler.Scheduler;

import im.turms.server.common.infra.thread.NamedThreadFactory;
import im.turms.server.common.infra.thread.ThreadNameConst;

/**
 * @author James Chen
 */
public class HashedWheelWorker implements Scheduler.Worker, Scannable {

    private static final HashedWheelTimer TIMER = new HashedWheelTimer(
            new NamedThreadFactory(ThreadNameConst.COMMON_TIMER, true),
            100,
            TimeUnit.MILLISECONDS,
            // 256 is enough, and we don't need to assign a larger number
            // because the push/poll time complexity has nothing to do with it.
            256,
            true,
            -1);

    static {
        TIMER.start();
    }

    HashedWheelWorker() {
    }

    @Override
    public Disposable schedule(Runnable task) {
        ExtendedTimerTask timerTask = new ExtendedTimerTask(task);
        TIMER.newTimeout(timerTask, 0, TimeUnit.MILLISECONDS);
        return timerTask::cancel;
    }

    @Override
    public Disposable schedule(Runnable task, long delay, TimeUnit unit) {
        ExtendedTimerTask timerTask = new ExtendedTimerTask(task);
        TIMER.newTimeout(timerTask, delay, unit);
        return timerTask::cancel;
    }

    @Override
    public Disposable schedulePeriodically(
            Runnable task,
            long initialDelay,
            long period,
            TimeUnit unit) {
        return Scheduler.Worker.super.schedulePeriodically(task, initialDelay, period, unit);
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public Object scanUnsafe(Attr key) {
        if (key == Attr.BUFFERED) {
            return TIMER.pendingTimeouts();
        }
        if (key == Attr.TERMINATED || key == Attr.CANCELLED) {
            return isDisposed();
        }
        if (key == Attr.NAME) {
            return "HashedWheelWorker";
        }
        return null;
    }

}