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

package unit.im.turms.server.common.infra.reactor;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.infra.reactor.TaskScheduler;

/**
 * @author James Chen
 */
class TaskSchedulerTests {

    @Test
    void test() {
        TaskScheduler scheduler = new TaskScheduler();
        Mono<Integer> mono1 = scheduler.schedule(Mono.delay(Duration.ofMillis(100))
                .thenReturn(1));
        Mono<Integer> mono2 = scheduler.schedule(Mono.just(2));
        Mono<Integer> mono3 = scheduler.schedule(Mono.delay(Duration.ofMillis(200))
                .thenReturn(3));
        Mono<Integer> mono4 = scheduler.schedule(Mono.just(4));

        Flux<Integer> result = Flux.merge(mono1, mono2, mono3, mono4);
        StepVerifier.create(result)
                .expectNext(1, 2, 3, 4)
                .verifyComplete();
    }
}