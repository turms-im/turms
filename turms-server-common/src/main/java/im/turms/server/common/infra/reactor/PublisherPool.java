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

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
public class PublisherPool {

    private PublisherPool() {
    }

    public static final Mono<Boolean> TRUE = Mono.just(true);
    public static final Mono<Boolean> FALSE = Mono.just(false);

    public static final Mono<Integer> INT_ZERO = Mono.just(0);

    public static final Mono EMPTY_LIST = Mono.just(Collections.emptyList());
    public static final Mono EMPTY_SET = Mono.just(Collections.emptySet());
    public static final Mono EMPTY_MAP = Mono.just(Collections.emptyMap());

    public static <T> Mono<Collection<T>> emptyCollection() {
        return EMPTY_LIST;
    }

    public static <T> Mono<List<T>> emptyList() {
        return EMPTY_LIST;
    }

    public static <T> Mono<Set<T>> emptySet() {
        return EMPTY_SET;
    }

    public static <K, V> Mono<Map<K, V>> emptyMap() {
        return EMPTY_MAP;
    }

}
