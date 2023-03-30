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

package im.turms.server.common.domain.observation.access.admin.controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import io.prometheus.client.exporter.common.TextFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.util.CollectionUtils;

import im.turms.server.common.access.admin.dto.response.HttpHandlerResult;
import im.turms.server.common.access.admin.dto.response.ResponseDTO;
import im.turms.server.common.access.admin.web.annotation.GetMapping;
import im.turms.server.common.access.admin.web.annotation.QueryParam;
import im.turms.server.common.access.admin.web.annotation.RestController;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.observation.access.admin.dto.response.MetricDTO;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.ByteBufFileResource;
import im.turms.server.common.infra.metrics.CsvReporter;
import im.turms.server.common.infra.metrics.MetricsPool;

import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_CSV_UTF_8;
import static im.turms.server.common.access.admin.web.MediaTypeConst.TEXT_PLAIN_UTF_8;
import static im.turms.server.common.infra.unit.ByteSizeUnit.KB;

/**
 * @author James Chen
 */
@RestController("metrics")
public class MetricsController {

    private final MetricsPool pool;
    private final PrometheusMeterRegistry prometheusMeterRegistry;

    private int expectedPrometheusDataSize = 8 * KB;

    public MetricsController(CompositeMeterRegistry registry) {
        pool = new MetricsPool(registry);
        prometheusMeterRegistry = (PrometheusMeterRegistry) registry.getRegistries()
                .stream()
                .filter(register -> register instanceof PrometheusMeterRegistry)
                .findFirst()
                .get();
    }

    @GetMapping
    public HttpHandlerResult<ResponseDTO<Collection<MetricDTO>>> getMetrics(
            @QueryParam(required = false) Set<String> names,
            @QueryParam(required = false) List<String> tags,
            boolean returnDescription,
            boolean returnAvailableTags) {
        boolean isNamesEmpty = CollectionUtils.isEmpty(names);
        if (isNamesEmpty) {
            if (!CollectionUtils.isEmpty(tags)) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Names must not be empty if tags are not empty");
            }
            Collection<List<Meter>> metersList;
            metersList = pool.findAllMeters()
                    .stream()
                    .collect(Collectors.groupingBy(meter -> meter.getId()
                            .getName()))
                    .values();
            List<MetricDTO> list = metersList.stream()
                    .map(meters -> meters2Dto(meters.get(0)
                            .getId()
                            .getName(), meters, returnDescription, returnAvailableTags))
                    .collect(CollectorUtil.toList(metersList.size()));
            return HttpHandlerResult.okIfTruthy(list);
        }
        List<MetricDTO> list = new ArrayList<>(names.size());
        for (String name : names) {
            List<Meter> meters;
            try {
                meters = pool.findFirstMatchingMeters(name, tags);
            } catch (Throwable t) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, t.getMessage());
            }
            if (!meters.isEmpty()) {
                list.add(meters2Dto(name, meters, returnDescription, returnAvailableTags));
            }
        }
        return HttpHandlerResult.okIfTruthy(list);
    }

    @GetMapping("names")
    public HttpHandlerResult<ResponseDTO<Collection<String>>> getNames() {
        Set<String> names = pool.collectNames();
        return HttpHandlerResult.okIfTruthy(names);
    }

    @GetMapping(value = "csv", produces = TEXT_CSV_UTF_8)
    public ByteBufFileResource getCsv(Set<String> names) {
        ByteBuf csv = CsvReporter.scrape(pool, names);
        return new ByteBufFileResource("turms-metrics.csv", csv);
    }

    @GetMapping(value = "prometheus", produces = TEXT_PLAIN_UTF_8)
    @Schema(implementation = String.class)
    public ByteBuf scrape(@QueryParam(required = false) Set<String> names) throws IOException {
        ByteBuf buffer = PooledByteBufAllocator.DEFAULT.directBuffer(expectedPrometheusDataSize);
        Writer writer = new OutputStreamWriter(new ByteBufOutputStream(buffer));
        prometheusMeterRegistry.scrape(writer, TextFormat.CONTENT_TYPE_OPENMETRICS_100, names);
        int actualLength = buffer.readableBytes();
        if (actualLength > expectedPrometheusDataSize) {
            expectedPrometheusDataSize = actualLength + 100;
        } else if (actualLength < (expectedPrometheusDataSize / 2)) {
            expectedPrometheusDataSize /= 2;
        }
        return buffer;
    }

    private MetricDTO meters2Dto(
            String name,
            Collection<Meter> meters,
            boolean returnDescription,
            boolean returnAvailableTags) {
        Meter.Id meterId = meters.iterator()
                .next()
                .getId();
        String description = returnDescription
                ? meterId.getDescription()
                : null;
        Map<String, Set<String>> availableTags = returnAvailableTags
                ? pool.getAvailableTags(meters)
                : null;
        List<MetricDTO.MeasurementDTO> measurements = new ArrayList<>(meters.size());
        for (Meter meter : meters) {
            List<Tag> tags = meter.getId()
                    .getTags();
            List<String> tagNames = new ArrayList<>(tags.size());
            for (Tag tag : tags) {
                tagNames.add(tag.getKey()
                        + ":"
                        + tag.getValue());
            }
            measurements.add(new MetricDTO.MeasurementDTO(tagNames, pool.getMeasurements(meter)));
        }
        return new MetricDTO(name, description, meterId.getBaseUnit(), measurements, availableTags);
    }

}
