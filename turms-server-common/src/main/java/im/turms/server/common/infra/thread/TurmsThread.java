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

package im.turms.server.common.infra.thread;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

/**
 * @author James Chen
 */
public class TurmsThread extends Thread {

    private final Task task;

    private TurmsThread(String name, Task task) {
        super(task, name);
        this.task = task;
    }

    public static TurmsThread create(String name, boolean daemon, Runnable target) {
        TurmsThread thread = new TurmsThread(name, new Task(target));
        thread.setDaemon(daemon);
        return thread;
    }

    public Mono<Void> terminate() {
        return Mono.defer(() -> {
            this.interrupt();
            return task.onTerminated();
        });
    }

    public Mono<Void> onTerminated() {
        return task.onTerminated();
    }

    private static class Task implements Runnable {

        private final Runnable runnable;
        private final Sinks.One<Void> onTerminated;

        public Task(Runnable runnable) {
            this.runnable = runnable;
            this.onTerminated = Sinks.one();
        }

        @Override
        public void run() {
            try {
                runnable.run();
            } catch (Throwable t) {
                onTerminated.tryEmitError(t);
                return;
            }
            onTerminated.tryEmitEmpty();
        }

        Mono<Void> onTerminated() {
            return onTerminated.asMono();
        }
    }
}