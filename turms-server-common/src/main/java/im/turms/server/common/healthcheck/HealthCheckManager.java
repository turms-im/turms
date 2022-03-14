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

import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.ThreadNameConstant;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.healthcheck.HealthCheckProperties;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 */
@Component
public class HealthCheckManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(HealthCheckManager.class);

    private final Node node;
    private final CpuHealthChecker cpuHealthChecker;
    private final MemoryHealthChecker memoryHealthChecker;
    private long lastUpdateTimestamp;

    public HealthCheckManager(@Lazy Node node, TurmsPropertiesManager propertiesManager) {
        this.node = node;
        HealthCheckProperties properties = propertiesManager.getLocalProperties().getHealthCheck();
        cpuHealthChecker = new CpuHealthChecker(properties.getCpu());
        memoryHealthChecker = new MemoryHealthChecker(properties.getMemory());

        startHealthCheck(properties.getCheckIntervalSeconds());
    }

    public boolean isHealthy() {
        return cpuHealthChecker.isHealthy() && memoryHealthChecker.isHealthy();
    }

    private void startHealthCheck(int intervalSeconds) {
        long intervalMillis = intervalSeconds * 1000L;
        DefaultThreadFactory threadFactory = new DefaultThreadFactory(ThreadNameConstant.HEALTH_CHECKER, true);
        new ScheduledThreadPoolExecutor(1, threadFactory)
                .scheduleWithFixedDelay(() -> {
                    cpuHealthChecker.updateHealthStatus();
                    memoryHealthChecker.updateHealthStatus();
                    node.getDiscoveryService().getLocalNodeStatusManager().updateHealthStatus(isHealthy());
                    long previousUpdateTimestamp = lastUpdateTimestamp + intervalMillis;
                    lastUpdateTimestamp = System.currentTimeMillis();
                    if (previousUpdateTimestamp > lastUpdateTimestamp) {
                        // There are a lof of modules heavily depending on the system time, e.g. logging, snowflake ID.
                        // So we log a warning message for troubleshooting if the time goes backwards.
                        LOGGER.warn("The system time goes backwards. The time drift is {} millis",
                                previousUpdateTimestamp - lastUpdateTimestamp);
                    }
                }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

}
