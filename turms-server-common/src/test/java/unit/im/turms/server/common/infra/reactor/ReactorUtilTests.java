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

import im.turms.server.common.infra.reactor.ReactorUtil;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;
import java.util.List;

/**
 * @author James Chen
 */
class ReactorUtilTests {

    @Test
    void areAllTrue_shouldPass() {
        List<Mono<Boolean>> allFalse = List.of(Mono.just(false), Mono.just(false), Mono.just(false));
        List<Mono<Boolean>> notAllTrue = List.of(Mono.just(false), Mono.just(false), Mono.just(true));
        List<Mono<Boolean>> allTrue = List.of(Mono.just(true), Mono.just(true), Mono.just(true));
        List<Mono<Boolean>> empty = Collections.emptyList();

        StepVerifier.create(ReactorUtil.areAllTrue(allFalse))
                .expectNext(false)
                .verifyComplete();
        StepVerifier.create(ReactorUtil.areAllTrue(notAllTrue))
                .expectNext(false)
                .verifyComplete();
        StepVerifier.create(ReactorUtil.areAllTrue(allTrue))
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(ReactorUtil.areAllTrue(empty))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void atLeastOneTrue_shouldPass() {
        List<Mono<Boolean>> allFalse = List.of(Mono.just(false), Mono.just(false), Mono.just(false));
        List<Mono<Boolean>> notAllTrue = List.of(Mono.just(false), Mono.just(false), Mono.just(true));
        List<Mono<Boolean>> allTrue = List.of(Mono.just(true), Mono.just(true), Mono.just(true));
        List<Mono<Boolean>> empty = Collections.emptyList();

        StepVerifier.create(ReactorUtil.atLeastOneTrue(allFalse))
                .expectNext(false)
                .verifyComplete();
        StepVerifier.create(ReactorUtil.atLeastOneTrue(notAllTrue))
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(ReactorUtil.atLeastOneTrue(allTrue))
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(ReactorUtil.atLeastOneTrue(empty))
                .expectNext(false)
                .verifyComplete();
    }

}
