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

package im.turms.server.common.healthcheck;

import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.healthcheck.HealthCheckProperties;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
@Component
public class HealthCheckManager {

    private final CpuHealthChecker cpuHealthChecker;
    private final MemoryHealthChecker memoryHealthChecker;

    public HealthCheckManager(TurmsPropertiesManager propertiesManager) {
        HealthCheckProperties properties = propertiesManager.getLocalProperties().getHealthCheck();
        cpuHealthChecker = new CpuHealthChecker(properties.getCpu());
        memoryHealthChecker = new MemoryHealthChecker(properties.getMemory());

        startHealthCheck(properties.getCheckIntervalSeconds());
    }

    public boolean isHealthy() {
        return cpuHealthChecker.isHealthy() && memoryHealthChecker.isHealthy();
    }

    private void startHealthCheck(int intervalSeconds) {
        DefaultThreadFactory threadFactory = new DefaultThreadFactory("health-checker", true);
        new ScheduledThreadPoolExecutor(1, threadFactory)
                .scheduleWithFixedDelay(() -> {
                    cpuHealthChecker.updateHealthStatus();
                    memoryHealthChecker.updateHealthStatus();
                }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

}
