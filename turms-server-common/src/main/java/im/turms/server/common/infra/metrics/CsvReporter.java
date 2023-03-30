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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import jakarta.annotation.Nullable;

import io.micrometer.core.instrument.Measurement;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.StrJoiner;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;

/**
 * @author James Chen
 */
public final class CsvReporter {

    private static final byte[] TITLE = StringUtil.getBytes("Name,Tags,Type,Value,Unit\n");

    private static final Comparator<IdAndMeasure> MEASUREMENT_COMPARATOR = (o1, o2) -> {
        Meter.Id id1 = o1.id;
        Meter.Id id2 = o2.id;
        int result = id1.getName()
                .compareTo(id2.getName());
        if (result != 0) {
            return result;
        }
        Iterator<Tag> tagIterator1 = id1.getTagsAsIterable()
                .iterator();
        Iterator<Tag> tagIterator2 = id2.getTagsAsIterable()
                .iterator();
        while (tagIterator1.hasNext()) {
            Tag tag1 = tagIterator1.next();
            if (tagIterator2.hasNext()) {
                result = tag1.compareTo(tagIterator2.next());
                if (result != 0) {
                    return result;
                }
            } else {
                return 1;
            }
        }
        return result;
    };

    private CsvReporter() {
    }

    public static ByteBuf scrape(MetricsPool pool, Set<String> names) {
        List<IdAndMeasure> measures = getSortedMeasurements(pool.findAllMeters(), names);
        ByteBuf buffer = null;
        try {
            buffer = PooledByteBufAllocator.DEFAULT.directBuffer(measures.size() * 64);
            buffer.writeBytes(TITLE);
            for (Iterator<IdAndMeasure> iterator = measures.iterator(); iterator.hasNext();) {
                IdAndMeasure entry = iterator.next();
                Meter.Id meterId = entry.id;
                String tags = getTagsAsString(meterId);
                Measurement measurement = entry.measurement;
                buffer.writeBytes(StringUtil.getBytes(meterId.getName()))
                        .writeByte(',')
                        .writeBytes(StringUtil.getBytes(tags))
                        .writeByte(',')
                        .writeBytes(StringUtil.getBytes(meterId.getType()
                                .name()))
                        .writeByte(',')
                        .writeBytes(StringUtil.getBytes(String.valueOf(measurement.getValue())))
                        .writeByte(',')
                        .writeBytes(
                                StringUtil.getBytes(StringUtil.toString(meterId.getBaseUnit())));
                if (iterator.hasNext()) {
                    buffer.writeByte('\n');
                }
            }
        } catch (Exception e) {
            if (buffer != null) {
                ReferenceCountUtil.safeEnsureReleased(buffer);
            }
            throw new RuntimeException("Failed to scrape", e);
        }
        return buffer;
    }

    private static List<IdAndMeasure> getSortedMeasurements(
            Collection<Meter> meters,
            @Nullable Set<String> names) {
        int size = 0;
        for (Meter meter : meters) {
            if (names != null
                    && !names.contains(meter.getId()
                            .getName())) {
                continue;
            }
            size = CollectionUtil.getSize(meter.measure());
        }
        if (size == 0) {
            return Collections.emptyList();
        }
        List<IdAndMeasure> measures = new ArrayList<>(size);
        for (Meter meter : meters) {
            if (names != null
                    && !names.contains(meter.getId()
                            .getName())) {
                continue;
            }
            for (Measurement measurement : meter.measure()) {
                measures.add(new IdAndMeasure(meter.getId(), measurement));
            }
        }
        measures.sort(MEASUREMENT_COMPARATOR);
        return measures;
    }

    private static String getTagsAsString(Meter.Id id) {
        Tag[] tags = MetricsUtil.getTags(id);
        int size = tags.length;
        if (size == 0) {
            return "";
        }
        StrJoiner tagJoiner = new StrJoiner(size * 4 - 1);
        for (int i = 0; i < size; i++) {
            Tag tag = tags[i];
            tagJoiner.add(tag.getKey())
                    .add(":")
                    .add(tag.getValue());
            if (i != size - 1) {
                tagJoiner.add(";");
            }
        }
        return tagJoiner.toString();
    }

    private static record IdAndMeasure(
            Meter.Id id,
            Measurement measurement
    ) {
    }

}