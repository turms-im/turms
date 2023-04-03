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

import reactor.core.Disposable;
import reactor.core.scheduler.Scheduler;

/**
 * @author James Chen
 */
public class HashedWheelScheduler implements Scheduler {

    private static final HashedWheelWorker WORKER = new HashedWheelWorker();
    private static final HashedWheelScheduler INSTANCE = new HashedWheelScheduler();

    public static HashedWheelScheduler getDaemon() {
        return INSTANCE;
    }

    @Override
    public void dispose() {
    }

    @Override
    public boolean isDisposed() {
        return false;
    }

    @Override
    public Disposable schedule(Runnable task) {
        return WORKER.schedule(task);
    }

    @Override
    public Disposable schedule(Runnable task, long delay, TimeUnit unit) {
        return WORKER.schedule(task, delay, unit);
    }

    @Override
    public Disposable schedulePeriodically(
            Runnable task,
            long initialDelay,
            long period,
            TimeUnit unit) {
        return WORKER.schedulePeriodically(task, initialDelay, period, unit);
    }

    @Override
    public Worker createWorker() {
        return WORKER;
    }

}