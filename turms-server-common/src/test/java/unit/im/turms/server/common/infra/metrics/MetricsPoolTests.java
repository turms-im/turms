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

package unit.im.turms.server.common.infra.metrics;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Statistic;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.simple.SimpleMeterRegistry;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.metrics.MetricsPool;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class MetricsPoolTests {

    private static final String COUNTER_NAME = "my.counter";
    private static final double COUNTER1_VALUE = 123;
    private static final double COUNTER2_VALUE = 456;
    private static final Tag COUNTER1_TAG = Tag.of("my.counter.tag.1", "my.counter.value");
    private static final Tag COUNTER2_TAG = Tag.of("my.counter.tag.2", "my.counter.value");
    private static final List<Tag> COUNTER1_TAGS = List.of(COUNTER1_TAG);
    private static final List<Tag> COUNTER2_TAGS = List.of(COUNTER2_TAG);

    private final MeterRegistry registry;

    MetricsPoolTests() {
        registry = new SimpleMeterRegistry();
        registry.gauge("my.gauge.1", 0);
        registry.gauge("my.gauge.2", 0);
        registry.summary("my.summary", List.of(Tag.of("my.summary.tag", "my.summary.value")));
        Counter counter1 = registry.counter(COUNTER_NAME, COUNTER1_TAGS);
        Counter counter2 = registry.counter(COUNTER_NAME, COUNTER2_TAGS);
        counter1.increment(COUNTER1_VALUE);
        counter2.increment(COUNTER2_VALUE);
    }

    @Test
    void collectNames() {
        MetricsPool pool = new MetricsPool(registry);
        Set<String> names = pool.collectNames();
        Set<String> expected = registry.getMeters()
                .stream()
                .map(meter -> meter.getId()
                        .getName())
                .collect(Collectors.toSet());

        assertThat(names).hasSameElementsAs(expected);
    }

    @Test
    void findFirstMatchingMeters() {
        MetricsPool pool = new MetricsPool(registry);
        List<Meter> meters1 = pool.findFirstMatchingMeters(COUNTER_NAME, null);
        List<Meter> meters2 = pool.findFirstMatchingMeters(COUNTER_NAME,
                List.of(COUNTER1_TAG.getKey()
                        + ":"
                        + COUNTER1_TAG.getValue()));

        assertThat(meters1).hasSameElementsAs(registry.get(COUNTER_NAME)
                .counters());
        assertThat(meters2).hasSameElementsAs(registry.get(COUNTER_NAME)
                .tags(COUNTER1_TAGS)
                .counters());
    }

    @Test
    void getAvailableTags() {
        MetricsPool pool = new MetricsPool(registry);
        Collection<Meter> meters = registry.get(COUNTER_NAME)
                .meters();
        Map<String, Set<String>> tags = pool.getAvailableTags(meters);

        assertThat(tags.get(COUNTER1_TAG.getKey()))
                .hasSameElementsAs(List.of(COUNTER1_TAG.getValue()));
        assertThat(tags.get(COUNTER2_TAG.getKey()))
                .hasSameElementsAs(List.of(COUNTER2_TAG.getValue()));
    }

    @Test
    void getMeasurements() {
        MetricsPool pool = new MetricsPool(registry);
        Meter meter = registry.get(COUNTER_NAME)
                .meters()
                .stream()
                .findFirst()
                .get();
        Map<String, Double> measurements = pool.getMeasurements(meter);
        assertThat(measurements).containsExactlyEntriesOf(
                Map.of(Statistic.COUNT.getTagValueRepresentation(), COUNTER1_VALUE));
    }

}
