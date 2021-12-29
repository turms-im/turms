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

package im.turms.server.common.access.http.controller;

import im.turms.server.common.access.http.dto.response.MetricDTO;
import im.turms.server.common.access.http.dto.response.ResponseDTO;
import im.turms.server.common.access.http.dto.response.ResponseFactory;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.metrics.CsvReporter;
import im.turms.server.common.metrics.MetricsPool;
import im.turms.server.common.util.CollectorUtil;
import io.micrometer.core.instrument.Meter;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import io.prometheus.client.exporter.common.TextFormat;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * We implement our own metrics endpoint for better performance and fine-grained control
 * (the MetricsEndpoint of Spring has a terrible performance)
 *
 * @author James Chen
 * @see org.springframework.boot.actuate.autoconfigure.metrics.MetricsEndpointAutoConfiguration
 * @see org.springframework.boot.actuate.endpoint.web.reactive.AbstractWebFluxEndpointHandlerMapping
 * @see org.springframework.boot.actuate.metrics.MetricsEndpoint
 */
@RestController
@RequestMapping("/metrics")
public class MetricsController {

    private static final MediaType MEDIA_TYPE_CSV = MediaType.parseMediaType("text/csv");

    private final MetricsPool pool;
    private final PrometheusMeterRegistry prometheusMeterRegistry;

    private int expectedPrometheusDataSize = 8 * 1024;

    public MetricsController(CompositeMeterRegistry registry) {
        pool = new MetricsPool(registry);
        prometheusMeterRegistry = (PrometheusMeterRegistry) registry.getRegistries()
                .stream()
                .filter(register -> register instanceof PrometheusMeterRegistry)
                .findFirst()
                .get();
    }

    @GetMapping
    public ResponseEntity<ResponseDTO<Collection<MetricDTO>>> getMetrics(@RequestParam(required = false) Set<String> names,
                                                                         @RequestParam(required = false) List<String> tags,
                                                                         @RequestParam(defaultValue = "false") Boolean returnDescription,
                                                                         @RequestParam(defaultValue = "false")
                                                                                 Boolean returnAvailableTags) {
        boolean isNamesEmpty = CollectionUtils.isEmpty(names);
        if (isNamesEmpty) {
            if (!CollectionUtils.isEmpty(tags)) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "Names must not be empty if tags are not empty");
            }
            Collection<List<Meter>> metersList;
            metersList = pool.findAllMeters()
                    .stream()
                    .collect(Collectors.groupingBy(meter -> meter.getId().getName()))
                    .values();
            List<MetricDTO> list = metersList
                    .stream()
                    .map(meters -> meters2Dto(meters.get(0).getId().getName(), meters, returnDescription, returnAvailableTags))
                    .collect(CollectorUtil.toList(metersList.size()));
            return ResponseFactory.okIfTruthy(list);
        }
        List<MetricDTO> list = new ArrayList<>(names.size());
        for (String name : names) {
            List<Meter> meters;
            try {
                meters = pool.findFirstMatchingMeters(name, tags);
            } catch (Throwable t) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, t.getMessage());
            }
            if (!meters.isEmpty()) {
                list.add(meters2Dto(name, meters, returnDescription, returnAvailableTags));
            }
        }
        return ResponseFactory.okIfTruthy(list);
    }

    @GetMapping("/names")
    public ResponseEntity<ResponseDTO<Collection<String>>> getNames() {
        Set<String> names = pool.collectNames();
        return ResponseFactory.okIfTruthy(names);
    }

    @GetMapping("/csv")
    @ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))
    public ResponseEntity<ByteBuf> getCsv(@RequestParam(required = false) Set<String> names) {
        ByteBuf csv = CsvReporter.scrape(pool, names);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"turms-metrics.csv\"")
                .contentType(MEDIA_TYPE_CSV)
                .body(csv);
    }

    @GetMapping("/prometheus")
    @ApiResponse(content = @Content(schema = @Schema(implementation = String.class)))
    public ByteBuf scrape(@RequestParam(required = false) Set<String> names) throws IOException {
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

    private MetricDTO meters2Dto(String name, Collection<Meter> meters, boolean returnDescription, boolean returnAvailableTags) {
        Meter.Id meterId = meters.iterator().next().getId();
        String description = returnDescription
                ? meterId.getDescription()
                : null;
        Map<String, Set<String>> availableTags = returnAvailableTags
                ? pool.getAvailableTags(meters)
                : null;
        List<MetricDTO.MeasurementDTO> measurements = new ArrayList<>(meters.size());
        for (Meter meter : meters) {
            List<Tag> tags = meter.getId().getTags();
            List<String> tagNames = new ArrayList<>(tags.size());
            for (Tag tag : tags) {
                tagNames.add(tag.getKey() + ":" + tag.getValue());
            }
            measurements.add(new MetricDTO.MeasurementDTO(tagNames, pool.getMeasurements(meter)));
        }
        return new MetricDTO(name, description, meterId.getBaseUnit(), measurements, availableTags);
    }

}
