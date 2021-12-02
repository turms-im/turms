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

import com.sun.management.OperatingSystemMXBean;
import im.turms.server.common.property.env.common.healthcheck.CpuHealthCheckProperties;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import java.lang.management.ManagementFactory;

/**
 * @author James Chen
 */
@Log4j2
public final class CpuHealthChecker extends HealthChecker {

    private final OperatingSystemMXBean operatingSystemBean;

    private final boolean isCpuHealthCheckAvailable;
    private final int cpuCheckRetries;
    private final float unhealthyLoadThreshold;

    private boolean isCpuHealthy;
    private int currentUnhealthyTimes;

    public CpuHealthChecker(CpuHealthCheckProperties properties) {
        operatingSystemBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        cpuCheckRetries = properties.getRetries();
        unhealthyLoadThreshold = properties.getUnhealthyLoadThresholdPercentage() / 100F;
        double cpuLoad = operatingSystemBean.getCpuLoad();

        boolean isCpuHealthCheckAvailable = true;
        if (cpuLoad < 0) {
            log.warn("CPU health checker cannot work because the \"recent cpu usage\" for the whole operating environment is unavailable");
            isCpuHealthCheckAvailable = false;
            isCpuHealthy = true;
        }
        this.isCpuHealthCheckAvailable = isCpuHealthCheckAvailable;
        updateHealthStatus();
    }

    @Override
    public boolean isHealthy() {
        return isCpuHealthy;
    }

    @Override
    public void updateHealthStatus() {
        if (!isCpuHealthCheckAvailable) {
            return;
        }
        boolean wasCpuHealthy = isCpuHealthy;
        double cpuLoad = operatingSystemBean.getCpuLoad();
        if (cpuLoad > unhealthyLoadThreshold) {
            currentUnhealthyTimes++;
            isCpuHealthy = currentUnhealthyTimes > cpuCheckRetries;
        } else {
            currentUnhealthyTimes = 0;
            isCpuHealthy = true;
        }

        // Log
        log.log(Level.DEBUG, "CPU load: {}", cpuLoad);
        if (wasCpuHealthy != isCpuHealthy) {
            if (isCpuHealthy) {
                log.info("The CPU has become healthy. The current CPU load: {}", cpuLoad);
            } else {
                log.info("The CPU has become unhealthy. The current CPU load: {}", cpuLoad);
            }
        }
    }

}
