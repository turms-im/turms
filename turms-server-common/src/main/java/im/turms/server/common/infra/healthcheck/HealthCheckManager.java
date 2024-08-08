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

package im.turms.server.common.infra.healthcheck;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import jakarta.annotation.Nullable;

import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.healthcheck.HealthCheckProperties;
import im.turms.server.common.infra.thread.ThreadNameConst;

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
    private static final long MILLISECONDS_IN_SECOND = 1000L;

    public HealthCheckManager(@Lazy Node node, TurmsPropertiesManager propertiesManager) {
        this.node = node;
        HealthCheckProperties properties = propertiesManager.getLocalProperties()
                .getHealthCheck();
        cpuHealthChecker = new CpuHealthChecker(properties.getCpu());
        memoryHealthChecker = new MemoryHealthChecker(properties.getMemory());

        startHealthCheck(properties.getCheckIntervalSeconds());
    }

    @Nullable
    public String getCpuUnhealthyReason() {
        return cpuHealthChecker.getUnhealthyReason();
    }

    @Nullable
    public String getMemoryUnhealthyReason() {
        return memoryHealthChecker.getUnhealthyReason();
    }

    public boolean isHealthy() {
        return cpuHealthChecker.isHealthy() && memoryHealthChecker.isHealthy();
    }

    private void startHealthCheck(int intervalSeconds) {
        long intervalMillis = intervalSeconds * MILLISECONDS_IN_SECOND;
        DefaultThreadFactory threadFactory =
                new DefaultThreadFactory(ThreadNameConst.HEALTH_CHECKER, true);
        new ScheduledThreadPoolExecutor(1, threadFactory).scheduleWithFixedDelay(() -> {
           try {
               cpuHealthChecker.updateHealthStatus();
               memoryHealthChecker.updateHealthStatus();
               node.getDiscoveryService()
                       .getLocalNodeStatusManager()
                       .updateHealthStatus(isHealthy());


               this.checkAndUpdateTimestamp(System.currentTimeMillis(), intervalMillis);
           }catch (Exception e){
               LOGGER.error("Failed to update health status", e);
           }
        }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    private void checkAndUpdateTimestamp(long now, long intervalMillis) {
        long previousUpdateTimestamp = lastUpdateTimestamp + intervalMillis;
        lastUpdateTimestamp = now;
        if (previousUpdateTimestamp > now) {
            long timeDrift = previousUpdateTimestamp - now;
            if (timeDrift > MILLISECONDS_IN_SECOND) { // 增加条件以减少不必要的日志
                LOGGER.warn("The system time goes backwards. The time drift is ({}) millis", timeDrift);
            }
        }
    }

}