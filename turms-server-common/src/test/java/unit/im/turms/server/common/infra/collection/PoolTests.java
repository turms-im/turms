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

package unit.im.turms.server.common.infra.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.collection.Pool;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class PoolTests {

    @Test
    void test() {
        Pool<Collection<String>> pool = new Pool<>(Integer.MAX_VALUE, 64);
        Set<String> emptySet = Collections.emptySet();
        if (emptySet.hashCode() != Pool.RESERVED_KEY) {
            throw new IllegalArgumentException(
                    "The hashcode of an empty set must be "
                            + Pool.RESERVED_KEY);
        }
        pool.poolIfAbsent(emptySet);

        pool.poolIfAbsent(Set.of("123"));
        pool.poolIfAbsent(Set.of("123"));
        pool.poolIfAbsent(Set.of("123"));
        pool.poolIfAbsent(Set.of("abc"));
        pool.poolIfAbsent(Set.of("123", "abc"));

        pool.poolIfAbsent(List.of("123"));
        pool.poolIfAbsent(List.of("123"));
        pool.poolIfAbsent(List.of("123"));
        pool.poolIfAbsent(List.of("abc"));
        pool.poolIfAbsent(List.of("123", "abc"));

        assertThat(pool.size()).isEqualTo(7);
        assertThat(pool.values()).containsExactlyInAnyOrder(Collections.emptySet(),
                Set.of("123"),
                Set.of("abc"),
                Set.of("123", "abc"),
                List.of("123"),
                List.of("abc"),
                List.of("123", "abc"));
    }

}
