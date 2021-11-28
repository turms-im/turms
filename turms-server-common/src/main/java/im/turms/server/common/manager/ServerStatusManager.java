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

package im.turms.server.common.manager;

import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.monitor.MemoryMonitor;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.MonitorProperties;
import im.turms.server.common.property.env.common.ServerAvailabilityProperties;
import org.springframework.stereotype.Component;

/**
 * @author James Chen
 */
@Component
public class ServerStatusManager {

    private final Node node;
    private final MemoryMonitor memoryMonitor;

    public ServerStatusManager(Node node, TurmsPropertiesManager propertiesManager) {
        this.node = node;
        MonitorProperties monitorProperties = propertiesManager.getLocalProperties().getMonitor();
        ServerAvailabilityProperties availabilityProperties = propertiesManager.getLocalProperties().getServerAvailability();
        memoryMonitor = new MemoryMonitor(monitorProperties.getUpdateMemoryInfoIntervalSeconds(),
                monitorProperties.getDirectMemoryWarningThresholdPercentage(),
                monitorProperties.getHeapMemoryWarningThresholdPercentage(),
                monitorProperties.getMinMemoryWarningIntervalSeconds(),
                availabilityProperties.getMaxAvailableMemoryPercentage(),
                availabilityProperties.getMaxAvailableDirectMemoryPercentage(),
                availabilityProperties.getMinFreeSystemMemoryBytes(),
                availabilityProperties.getHeapMemoryGcThresholdPercentage(),
                availabilityProperties.getMinHeapMemoryGcIntervalSeconds());
    }

    public boolean isActive() {
        return node.isActive() && !memoryMonitor.isExceeded();
    }

}
