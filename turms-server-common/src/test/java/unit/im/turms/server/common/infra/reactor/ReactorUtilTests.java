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

import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.infra.reactor.PublisherUtil;

/**
 * @author James Chen
 */
class ReactorUtilTests {

    @Test
    void areAllTrue_shouldPass() {
        List<Mono<Boolean>> allFalse =
                List.of(Mono.just(false), Mono.just(false), Mono.just(false));
        List<Mono<Boolean>> notAllTrue =
                List.of(Mono.just(false), Mono.just(false), Mono.just(true));
        List<Mono<Boolean>> allTrue = List.of(Mono.just(true), Mono.just(true), Mono.just(true));
        List<Mono<Boolean>> empty = Collections.emptyList();

        StepVerifier.create(PublisherUtil.areAllTrue(allFalse))
                .expectNext(false)
                .verifyComplete();
        StepVerifier.create(PublisherUtil.areAllTrue(notAllTrue))
                .expectNext(false)
                .verifyComplete();
        StepVerifier.create(PublisherUtil.areAllTrue(allTrue))
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(PublisherUtil.areAllTrue(empty))
                .expectNext(false)
                .verifyComplete();
    }

    @Test
    void atLeastOneTrue_shouldPass() {
        List<Mono<Boolean>> allFalse =
                List.of(Mono.just(false), Mono.just(false), Mono.just(false));
        List<Mono<Boolean>> notAllTrue =
                List.of(Mono.just(false), Mono.just(false), Mono.just(true));
        List<Mono<Boolean>> allTrue = List.of(Mono.just(true), Mono.just(true), Mono.just(true));
        List<Mono<Boolean>> empty = Collections.emptyList();

        StepVerifier.create(PublisherUtil.atLeastOneTrue(allFalse))
                .expectNext(false)
                .verifyComplete();
        StepVerifier.create(PublisherUtil.atLeastOneTrue(notAllTrue))
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(PublisherUtil.atLeastOneTrue(allTrue))
                .expectNext(true)
                .verifyComplete();
        StepVerifier.create(PublisherUtil.atLeastOneTrue(empty))
                .expectNext(false)
                .verifyComplete();
    }

}
