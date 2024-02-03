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

import java.util.ArrayDeque;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;

import im.turms.server.common.infra.thread.ThreadSafe;

/**
 * The class is used to run tasks one by one.
 *
 * @author James Chen
 */
@ThreadSafe
public class TaskScheduler {

    private volatile boolean hasRunningTask;
    private final ArrayDeque<Sinks.One<?>> tasks = new ArrayDeque<>(16);

    public <T> Mono<T> schedule(Mono<T> mono) {
        mono = mono.doFinally(signalType -> {
            synchronized (this) {
                Sinks.One<?> sink = tasks.poll();
                if (sink == null) {
                    hasRunningTask = false;
                } else {
                    sink.tryEmitEmpty();
                }
            }
        });
        synchronized (this) {
            if (!hasRunningTask) {
                hasRunningTask = true;
                return mono;
            }
            Sinks.One<T> sink = Sinks.one();
            tasks.add(sink);
            return sink.asMono()
                    .then(mono);
        }
    }

}