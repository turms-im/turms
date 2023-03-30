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

import java.io.File;
import java.util.List;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.binder.MeterBinder;
import io.micrometer.core.instrument.binder.jvm.ClassLoaderMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmGcMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics;
import io.micrometer.core.instrument.binder.jvm.JvmThreadMetrics;
import io.micrometer.core.instrument.binder.system.DiskSpaceMetrics;
import io.micrometer.core.instrument.binder.system.FileDescriptorMetrics;
import io.micrometer.core.instrument.binder.system.UptimeMetrics;
import io.micrometer.core.instrument.composite.CompositeMeterRegistry;
import io.micrometer.prometheus.PrometheusConfig;
import io.micrometer.prometheus.PrometheusMeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author James Chen
 */
@Configuration
public class MetricsConfig {

    @Bean
    public CompositeMeterRegistry meterRegistry() {
        CompositeMeterRegistry registry = Metrics.globalRegistry;
        registry.add(new PrometheusMeterRegistry(new PrometheusConfig() {
            @Override
            public String get(String key) {
                return null;
            }

            @Override
            public boolean descriptions() {
                return false;
            }
        }));
        List<MeterBinder> binders = List.of(
                // System
                new UptimeMetrics(),
                new ProcessorMetrics(),
                new SystemMemoryMetrics(),
                new FileDescriptorMetrics(),
                new DiskSpaceMetrics(new File(".")),
                // JVM
                new JvmGcMetrics(),
                new JvmMemoryMetrics(),
                new JvmThreadMetrics(),
                new ClassLoaderMetrics()
        // org.springframework.boot.actuate.autoconfigure.metrics.web.reactive.WebFluxMetricsAutoConfiguration
        );
        for (MeterBinder binder : binders) {
            binder.bindTo(registry);
        }
        return registry;
    }

}
