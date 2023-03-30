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

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.collection.CollectionUtil;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class CollectionUtilTests {

    @Test
    void containsAllLooseComparison() {
        Map<String, Object> map1 = Map.of("bool1",
                true,
                "bool2",
                true,
                "int1",
                123,
                "int2",
                123,
                "double1",
                123321.123D,
                "double2",
                123321.123D,
                "map",
                Map.of("bool1",
                        true,
                        "bool2",
                        true,
                        "int1",
                        123,
                        "int2",
                        123,
                        "double1",
                        123321.123D,
                        "double2",
                        123321.123D),
                "list",
                List.of(true, 123, 123.123F),
                "array",
                new Object[]{true, 123, 123.123F});
        Map<String, Object> map2 = Map.of("bool1",
                true,
                "bool2",
                "true",
                "int1",
                123,
                "int2",
                "123",
                "double1",
                123321.123D,
                "double2",
                "123321.123",
                "map",
                Map.of("bool1",
                        true,
                        "bool2",
                        "true",
                        "int1",
                        123,
                        "int2",
                        "123",
                        "double1",
                        123321.123D,
                        "double2",
                        "123321.123"),
                "list",
                List.of("true", "123", "123.123"),
                "array",
                new Object[]{"true", "123", "123.123"});
        assertThat(CollectionUtil.containsAllLooseComparison(map1, map2)).isTrue();
        assertThat(CollectionUtil.containsAllLooseComparison(map2, map1)).isTrue();

        map1 = Map.of("map", Map.of("key1", "value1", "key2", "value2", "key3", "value3"));
        map2 = Map.of("map", Map.of("key1", "value1"));
        assertThat(CollectionUtil.containsAllLooseComparison(map1, map2)).isTrue();
        assertThat(CollectionUtil.containsAllLooseComparison(map2, map1)).isFalse();

        map2 = Map.of("map", Map.of("key1", "value2"));
        assertThat(CollectionUtil.containsAllLooseComparison(map1, map2)).isFalse();
        assertThat(CollectionUtil.containsAllLooseComparison(map2, map1)).isFalse();

        map1 = Map.of("list", List.of("value1", "value2", "value3"));
        map2 = Map.of("list", List.of("value1", "value2"));
        assertThat(CollectionUtil.containsAllLooseComparison(map1, map2)).isFalse();
        assertThat(CollectionUtil.containsAllLooseComparison(map2, map1)).isFalse();

        map1 = Map.of("key", Duration.ofSeconds(123));
        map2 = Map.of("key", Instant.ofEpochMilli(123));
        assertThat(CollectionUtil.containsAllLooseComparison(map1, map2)).isFalse();
        assertThat(CollectionUtil.containsAllLooseComparison(map2, map1)).isFalse();

        map2 = Map.of("key", Duration.ofSeconds(123));
        assertThat(CollectionUtil.containsAllLooseComparison(map1, map2)).isTrue();
        assertThat(CollectionUtil.containsAllLooseComparison(map2, map1)).isTrue();
    }
}
