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

package im.turms.server.common.actuator.metrics;

import com.google.common.collect.Sets;
import im.turms.server.common.util.InvokeUtil;
import io.micrometer.core.instrument.*;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import lombok.SneakyThrows;
import org.springframework.boot.actuate.endpoint.InvalidEndpointRequestException;
import org.springframework.util.StringUtils;

import java.lang.invoke.MethodHandle;
import java.util.*;
import java.util.function.BiFunction;

/**
 * @author James Chen
 * @implNote We don't cache because the metrics in the registry may change
 * especially the metrics of turms service requests
 */
public class MetricsPool {

    private static final MethodHandle GET_METER_MAP = InvokeUtil.getGetter(MeterRegistry.class, "meterMap");
    private final MeterRegistry registry;

    public MetricsPool(MeterRegistry registry) {
        this.registry = registry;
    }

    Set<String> collectNames() {
        return collectNames(registry);
    }

    List<Meter> findFirstMatchingMeters(String name, List<String> tags) {
        return findFirstMatchingMeters(registry, name, parseTags(tags));
    }

    Map<String, Double> getSamples(Collection<Meter> meters) {
        Map<String, Double> samples = new LinkedHashMap<>();
        for (Meter meter : meters) {
            for (Measurement measurement : meter.measure()) {
                String tag = measurement.getStatistic().getTagValueRepresentation();
                samples.merge(tag, measurement.getValue(), mergeFunction(measurement.getStatistic()));
            }
        }
        return samples;
    }

    Map<String, Set<String>> getAvailableTags(Collection<Meter> meters) {
        Map<String, Set<String>> availableTags = new HashMap<>();
        for (Meter meter : meters) {
            for (Tag tag : meter.getId().getTags()) {
                Set<String> value = Collections.singleton(tag.getValue());
                availableTags.merge(tag.getKey(), value, Sets::union);
            }
        }
        return availableTags;
    }

    @SneakyThrows
    private Set<String> collectNames(MeterRegistry registry) {
        Set<String> names = new TreeSet<>();
        if (registry instanceof CompositeMeterRegistry) {
            CompositeMeterRegistry compositeMeterRegistry = (CompositeMeterRegistry) registry;
            for (MeterRegistry meterRegistry : compositeMeterRegistry.getRegistries()) {
                names.addAll(collectNames(meterRegistry));
            }
        } else {
            Map<Meter.Id, Meter> meterMap = (Map<Meter.Id, Meter>) GET_METER_MAP.invokeExact(registry);
            for (Meter meter : meterMap.values()) {
                names.add(meter.getId().getName());
            }
        }
        return names;
    }

    private List<Tag> parseTags(List<String> tags) {
        if (tags == null) {
            return Collections.emptyList();
        }
        List<Tag> tagList = new ArrayList<>(tags.size());
        for (String tag : tags) {
            String[] parts = StringUtils.split(tag, ":");
            if (parts == null) {
                throw new InvalidEndpointRequestException(
                        "Each tag parameter must be in the form 'key:value' but was: " + tag,
                        "Each tag parameter must be in the form 'key:value'");
            }
            tagList.add(Tag.of(parts[0], parts[1]));
        }
        return tagList;
    }

    private List<Meter> findFirstMatchingMeters(MeterRegistry registry, String name, List<Tag> tags) {
        if (registry instanceof CompositeMeterRegistry) {
            return findFirstMatchingMeters((CompositeMeterRegistry) registry, name, tags);
        }
        List<Meter> list = null;
        for (Meter meter : registry.getMeters()) {
            Meter.Id id = meter.getId();
            if (id.getName().equals(name) && (tags == null || id.getTags().containsAll(tags))) {
                if (list == null) {
                    list = new LinkedList<>();
                }
                list.add(meter);
            }
        }
        return list == null ? Collections.emptyList() : list;
    }

    private List<Meter> findFirstMatchingMeters(CompositeMeterRegistry composite, String name, List<Tag> tags) {
        for (MeterRegistry meterRegistry : composite.getRegistries()) {
            List<Meter> meters = findFirstMatchingMeters(meterRegistry, name, tags);
            if (!meters.isEmpty()) {
                return meters;
            }
        }
        return Collections.emptyList();
    }

    private BiFunction<Double, Double, Double> mergeFunction(Statistic statistic) {
        return Statistic.MAX.equals(statistic) ? Double::max : Double::sum;
    }

}