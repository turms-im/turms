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

import com.sun.management.HotSpotDiagnosticMXBean;
import com.sun.management.OperatingSystemMXBean;
import com.sun.management.VMOption;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.healthcheck.HealthCheckProperties;
import im.turms.server.common.property.env.common.healthcheck.MemoryHealthCheckProperties;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.springframework.stereotype.Component;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author James Chen
 * @implNote JVM Total Memory:
 * 1. Off-Heap:
 * a. Mapped files, b. Direct buffers c. Java stacks d. Metaspace (Class metadata: Constant pool, Field & Method data),
 * e. Native code (JVM internal use only): PC registers, Native method stacks, Code cache,
 * Structures used & allocated by native libraries(e.g IO libraries), Shared libraries of the JVM, etc.
 * f. etc
 * 2. Heap (eden, survivor, old)
 * <a href="https://www.oracle.com/technetwork/tutorials/tutorials-1876574.html">
 * Getting Started with the G1 Garbage Collector</a>
 * @see jdk.internal.access.JavaNioAccess#getDirectBufferPool
 * @see io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
 */
@Component
@Log4j2
public final class HealthChecker {

    private final long maxAvailableMemory;
    private final long maxAvailableDirectMemory;
    private final long maxDirectMemory;
    private final long maxHeapMemory;
    private final int minFreeSystemMemory;
    private final long totalPhysicalMemorySize;

    private long usedAvailableMemory;
    private long usedDirectMemory;
    private long usedHeapMemory;
    private long usedNonHeapMemory;
    private long usedSystemMemory;
    private long freeSystemMemory;

    private final BufferPoolMXBean directBufferPoolBean;
    private final MemoryMXBean memoryMXBean;
    private final OperatingSystemMXBean operatingSystemBean;

    private final int directMemoryWarningThresholdPercentage;
    private final int heapMemoryWarningThresholdPercentage;
    private final int minMemoryWarningIntervalMillis;
    private long lastDirectMemoryWarningTimestamp;
    private long lastHeapMemoryWarningTimestamp;

    private final int heapMemoryGcThresholdPercentage;
    private final int minHeapMemoryGcIntervalMillis;
    private long lastHeapMemoryGcTimestamp;

    public HealthChecker(TurmsPropertiesManager propertiesManager) {
        HotSpotDiagnosticMXBean diagnosticBean = ManagementFactory.getPlatformMXBean(HotSpotDiagnosticMXBean.class);
        VMOption disableExplicitGC = diagnosticBean.getVMOption("DisableExplicitGC");
        if (!"false".equals(disableExplicitGC.getValue())) {
            throw new IllegalStateException("\"DisableExplicitGC\" is enabled while it should be disabled");
        }
        // update memory properties
        List<BufferPoolMXBean> poolBeans = ManagementFactory.getPlatformMXBeans(BufferPoolMXBean.class);
        Optional<BufferPoolMXBean> pool = poolBeans
                .stream()
                .filter(bean -> "direct".equals(bean.getName()))
                .findFirst();
        if (pool.isEmpty()) {
            List<String> names = poolBeans
                    .stream()
                    .map(BufferPoolMXBean::getName)
                    .toList();
            String s = "Cannot find the direct buffer pool management bean from the pool beans: "
                    + String.join(", ", names);
            throw new IllegalStateException(s);
        }
        directBufferPoolBean = pool.get();
        memoryMXBean = ManagementFactory.getMemoryMXBean();

        HealthCheckProperties properties = propertiesManager.getLocalProperties().getHealthCheck();
        MemoryHealthCheckProperties memoryProperties = properties.getMemory();
        // "-XX:MaxDirectMemorySize" or "Runtime.getRuntime().maxMemory()"
        maxDirectMemory = PlatformDependent.maxDirectMemory();
        if (maxDirectMemory < 0) {
            throw new IllegalStateException("Cannot detect the max direct memory: " + maxDirectMemory);
        }
        maxAvailableDirectMemory = (long) (maxDirectMemory * (memoryProperties.getMaxAvailableDirectMemoryPercentage() / 100F));
        maxHeapMemory = memoryMXBean.getHeapMemoryUsage().getMax();
        if (maxHeapMemory < 0) {
            throw new IllegalStateException("Cannot detect the max heap memory: " + maxHeapMemory);
        }
        operatingSystemBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        totalPhysicalMemorySize = operatingSystemBean.getTotalMemorySize();
        maxAvailableMemory = (long) (totalPhysicalMemorySize * (memoryProperties.getMaxAvailableMemoryPercentage() / 100F));
        int minAvailableMemory = 1000 * 1024 * 1024;
        if (maxAvailableMemory < minAvailableMemory) {
            throw new IllegalStateException("The max available memory is too small to run. Actual: %s. Expected: >= %s"
                    .formatted(asMbString(maxAvailableMemory), asMbString(minAvailableMemory)));
        }
        if (maxAvailableMemory < maxHeapMemory) {
            throw new IllegalStateException("The max available memory %s should not be less than the max heap memory %s"
                    .formatted(asMbString(maxAvailableMemory), asMbString(maxHeapMemory)));
        }
        int estimatedMaxNonHeapMemory = 256 * 1024 * 1024;
        if (maxAvailableMemory > maxAvailableDirectMemory + maxHeapMemory + estimatedMaxNonHeapMemory) {
            log.warn("The max available memory %s is larger than the total of the available direct memory %s, the max heap memory %s, and the estimated max non-heap memory %s, "
                    .formatted(asMbString(maxAvailableMemory), asMbString(maxAvailableDirectMemory), asMbString(maxHeapMemory), asMbString(estimatedMaxNonHeapMemory))
                    + "which indicates that some memory will never be used by the server");
        }

        this.directMemoryWarningThresholdPercentage = memoryProperties.getDirectMemoryWarningThresholdPercentage();
        this.heapMemoryWarningThresholdPercentage = memoryProperties.getHeapMemoryWarningThresholdPercentage();
        this.minMemoryWarningIntervalMillis = memoryProperties.getMinMemoryWarningIntervalSeconds() * 1000;
        this.minFreeSystemMemory = memoryProperties.getMinFreeSystemMemoryBytes();
        this.heapMemoryGcThresholdPercentage = memoryProperties.getHeapMemoryGcThresholdPercentage();
        this.minHeapMemoryGcIntervalMillis = memoryProperties.getMinHeapMemoryGcIntervalSeconds() * 1000;

        startMonitor(properties.getCheckIntervalSeconds());
    }

    public boolean isHealthy() {
        return usedAvailableMemory < maxAvailableMemory
                || usedDirectMemory < maxAvailableDirectMemory
                || freeSystemMemory > minFreeSystemMemory;
    }

    private void startMonitor(int intervalSeconds) {
        DefaultThreadFactory threadFactory = new DefaultThreadFactory("memory-monitor", true);
        new ScheduledThreadPoolExecutor(1, threadFactory)
                .scheduleWithFixedDelay(() -> {
                    updateUsedMemory();
                    tryLog();
                }, 0, intervalSeconds, TimeUnit.SECONDS);
    }

    private void updateUsedMemory() {
        // No need to call "UnpooledByteBufAllocator.DEFAULT.metric().usedDirectMemory()"
        // and "PooledByteBufAllocator.DEFAULT.metric().usedDirectMemory()"
        // because we have requested Netty to create DirectBuffer instances via its constructor with the counter supported by JDK
        usedDirectMemory = directBufferPoolBean.getMemoryUsed();
        usedHeapMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        // Non-heap memory pools: [CodeHeap 'non-nmethods', CodeHeap 'non-profiled nmethods', CodeHeap 'profiled nmethods',
        // Compressed Class Space, Metaspace]
        // via ManagementFactory.getMemoryPoolMXBeans().stream().filter(bean -> bean.getType() == MemoryType.NON_HEAP)
        // .map(MemoryPoolMXBean::getName).sorted().toList().toString()
        usedNonHeapMemory = memoryMXBean.getNonHeapMemoryUsage().getUsed();
        usedAvailableMemory = usedDirectMemory + usedHeapMemory + usedNonHeapMemory;
        freeSystemMemory = operatingSystemBean.getFreeMemorySize();
        usedSystemMemory = totalPhysicalMemorySize - freeSystemMemory;
    }

    private void tryLog() {
        boolean isHealthy = isHealthy();
        Level logLevel = isHealthy ? Level.DEBUG : Level.WARN;
        if (log.isEnabled(logLevel)) {
            log.log(logLevel, "Used system memory: {}/{}; "
                            + "Used available memory: {}/{}; "
                            + "Used direct memory: {}/{}/{}; "
                            + "Used heap memory: {}/{}; "
                            + "Used non-heap memory: {}",
                    asMbString(usedSystemMemory),
                    asMbString(totalPhysicalMemorySize),

                    asMbString(usedAvailableMemory),
                    asMbString(maxAvailableMemory),

                    asMbString(usedDirectMemory),
                    asMbString(maxAvailableDirectMemory),
                    asMbString(maxDirectMemory),

                    asMbString(usedHeapMemory),
                    asMbString(maxHeapMemory),

                    asMbString(usedNonHeapMemory));
        }
        long now = System.currentTimeMillis();
        float usedMemoryPercentage = 100F * usedDirectMemory / maxDirectMemory;
        if (directMemoryWarningThresholdPercentage > 0 && directMemoryWarningThresholdPercentage < usedMemoryPercentage
                && minMemoryWarningIntervalMillis < (now - lastDirectMemoryWarningTimestamp)) {
            lastDirectMemoryWarningTimestamp = now;
            log.warn("The used direct memory has exceeded the warning threshold: {}/{}/{}/{}",
                    asMbString(usedDirectMemory), asMbString(maxDirectMemory), usedMemoryPercentage, directMemoryWarningThresholdPercentage);
        }
        usedMemoryPercentage = 100F * usedHeapMemory / maxHeapMemory;
        if (heapMemoryWarningThresholdPercentage > 0 && heapMemoryWarningThresholdPercentage < usedMemoryPercentage
                && minMemoryWarningIntervalMillis < (now - lastHeapMemoryWarningTimestamp)) {
            lastHeapMemoryWarningTimestamp = now;
            log.warn("The used heap memory has exceeded the warning threshold: {}/{}/{}/{}",
                    asMbString(usedHeapMemory), asMbString(maxHeapMemory), usedMemoryPercentage, heapMemoryWarningThresholdPercentage);
        }
        if (!isHealthy && heapMemoryGcThresholdPercentage > 0 && heapMemoryGcThresholdPercentage < usedMemoryPercentage
                && minHeapMemoryGcIntervalMillis < (now - lastHeapMemoryGcTimestamp)) {
            lastHeapMemoryGcTimestamp = now;
            log.info("Trying to start GC because the available memory has exceeded and the used heap memory has exceeded the GC threshold: {}/{}/{}/{}",
                    asMbString(usedHeapMemory), asMbString(maxHeapMemory), usedMemoryPercentage, heapMemoryGcThresholdPercentage);
            System.gc();
        }
    }

    private String asMbString(long bytes) {
        return bytes / 1024 / 1024 + "MB";
    }

}
