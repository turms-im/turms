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

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.binder.MeterBinder;

/**
 * @author James Chen
 */
public class ProcessorMetrics implements MeterBinder {

    @Override
    public void bindTo(MeterRegistry registry) {
        OperatingSystemMXBean operatingSystemBean =
                ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        Runtime runtime = Runtime.getRuntime();

        Gauge.builder("system.cpu.count", runtime, Runtime::availableProcessors)
                .description("The number of processors available to the Java virtual machine")
                .register(registry);

        if (operatingSystemBean.getSystemLoadAverage() >= 0) {
            Gauge.builder("system.load.average.1m",
                    operatingSystemBean,
                    OperatingSystemMXBean::getSystemLoadAverage)
                    .description(
                            "The sum of the number of runnable entities queued to available processors and the number "
                                    + "of runnable entities running on the available processors averaged over a period of time")
                    .baseUnit("‱")
                    .register(registry);
        }

        Gauge.builder("system.cpu.usage",
                operatingSystemBean,
                x -> operatingSystemBean.getCpuLoad())
                .description("The \"recent cpu usage\" for the whole system")
                .baseUnit("‱")
                .register(registry);

        Gauge.builder("process.cpu.usage",
                operatingSystemBean,
                x -> operatingSystemBean.getProcessCpuLoad())
                .description("The \"recent cpu usage\" for the Java Virtual Machine process")
                .baseUnit("‱")
                .register(registry);
    }

}
