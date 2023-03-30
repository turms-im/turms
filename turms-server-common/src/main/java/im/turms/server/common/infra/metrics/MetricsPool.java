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

package im.turms.server.common.infra.metrics;

import java.lang.invoke.MethodHandle;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import jakarta.annotation.Nullable;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import org.eclipse.collections.impl.factory.Sets;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.AsciiCode;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.reflect.ReflectionUtil;

/**
 * @author James Chen
 * @implNote We don't cache because the metrics in the registry may change especially the metrics of
 *           turms service requests
 */
public class MetricsPool {

    private static final MethodHandle ID_TO_METER =
            ReflectionUtil.getGetter(MeterRegistry.class, "meterMap");
    private final MeterRegistry registry;

    public MetricsPool(MeterRegistry registry) {
        this.registry = registry;
    }

    public Set<String> collectNames() {
        return collectNames(registry);
    }

    public Collection<Meter> findAllMeters() {
        return getIdToMeterMap().values();
    }

    public List<Meter> findFirstMatchingMeters(String name, List<String> tags) {
        return findFirstMatchingMeters(registry, name, parseTags(tags));
    }

    public Map<String, Double> getMeasurements(Meter meter) {
        Iterable<Measurement> measures = meter.measure();
        Map<String, Double> measurements =
                measures instanceof Collection<Measurement> measurementCollection
                        ? CollectionUtil.newMapWithExpectedSize(measurementCollection.size())
                        : new HashMap<>(8);
        for (Measurement measurement : measures) {
            String tag = measurement.getStatistic()
                    .getTagValueRepresentation();
            measurements.put(tag, measurement.getValue());
        }
        return measurements;
    }

    public Map<String, Set<String>> getAvailableTags(Collection<Meter> meters) {
        Map<String, Set<String>> availableTags = new HashMap<>();
        for (Meter meter : meters) {
            for (Tag tag : meter.getId()
                    .getTagsAsIterable()) {
                Set<String> value = Collections.singleton(tag.getValue());
                // tag.getKey() e.g. "total", "max", "count", "value" etc.
                availableTags.merge(tag.getKey(), value, Sets::union);
            }
        }
        return availableTags;
    }

    /**
     * Internal Implementations
     */

    private Set<String> collectNames(MeterRegistry registry) {
        Set<String> names = new TreeSet<>();
        if (registry instanceof CompositeMeterRegistry compositeMeterRegistry) {
            for (MeterRegistry meterRegistry : compositeMeterRegistry.getRegistries()) {
                names.addAll(collectNames(meterRegistry));
            }
        } else {
            Map<Meter.Id, Meter> idToMeter = getIdToMeterMap();
            for (Meter meter : idToMeter.values()) {
                names.add(meter.getId()
                        .getName());
            }
        }
        return names;
    }

    private Map<Meter.Id, Meter> getIdToMeterMap() {
        try {
            return (Map<Meter.Id, Meter>) ID_TO_METER.invokeExact(registry);
        } catch (Throwable e) {
            throw new RuntimeException("Failed to get the meter map", e);
        }
    }

    private List<Tag> parseTags(List<String> tags) {
        if (tags == null) {
            return Collections.emptyList();
        }
        List<Tag> tagList = new ArrayList<>(tags.size());
        for (String tag : tags) {
            Pair<String, String> keyAndValue = StringUtil.splitLatin1(tag, AsciiCode.COLON);
            if (keyAndValue == null) {
                throw new IllegalArgumentException(
                        "The tag parameter must be in the form \"key:value\", but got: "
                                + tag);
            }
            tagList.add(Tag.of(keyAndValue.first(), keyAndValue.second()));
        }
        return tagList;
    }

    private List<Meter> findFirstMatchingMeters(
            MeterRegistry registry,
            String name,
            @Nullable List<Tag> tags) {
        if (registry instanceof CompositeMeterRegistry meterRegistry) {
            return findFirstMatchingMeters(meterRegistry, name, tags);
        }
        List<Meter> list = null;
        Map<Meter.Id, Meter> meterMap;
        try {
            meterMap = (Map<Meter.Id, Meter>) ID_TO_METER.invokeExact(registry);
        } catch (Throwable t) {
            throw new RuntimeException(t);
        }
        for (Meter meter : meterMap.values()) {
            Meter.Id id = meter.getId();
            if (!id.getName()
                    .equals(name)) {
                continue;
            }
            if (tags == null
                    || !id.getTags()
                            .containsAll(tags)) {
                continue;
            }
            if (list == null) {
                list = new LinkedList<>();
            }
            list.add(meter);
        }
        return list == null
                ? Collections.emptyList()
                : list;
    }

    private List<Meter> findFirstMatchingMeters(
            CompositeMeterRegistry composite,
            String name,
            List<Tag> tags) {
        for (MeterRegistry meterRegistry : composite.getRegistries()) {
            List<Meter> meters = findFirstMatchingMeters(meterRegistry, name, tags);
            if (!meters.isEmpty()) {
                return meters;
            }
        }
        return Collections.emptyList();
    }

}