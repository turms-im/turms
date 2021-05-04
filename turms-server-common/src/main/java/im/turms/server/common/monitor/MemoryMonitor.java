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

package im.turms.server.common.monitor;

import com.sun.management.OperatingSystemMXBean;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author James Chen
 * @implNote JVM Total Memory:
 * 1. Off-Heap:
 * a. Mapped files, b. Direct buffers c. Java stacks d. Metaspace (Class meta data: Constant pool, Field & Method data),
 * e. Native code (JVM internal use only): PC registers, Native method stacks, Code cache,
 * Structures used & allocated by native libraries(e.g IO libraries), Shared libraries of the JVM, etc.
 * f. etc
 * 2. Heap (eden, survivor, old)
 * <a href="https://www.oracle.com/technetwork/tutorials/tutorials-1876574.html">
 * Getting Started with the G1 Garbage Collector</a>
 * @see jdk.internal.misc.JavaNioAccess.BufferPool#getMemoryUsed
 * @see io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
 */
@Log4j2
public final class MemoryMonitor {

    private final long maxManagedMemory;
    private final long maxDirectMemory;
    private final long maxHeapMemory;

    private final long maxAvailableManagedMemory;
    private final long maxAvailableDirectMemory;
    private final long maxAvailableHeapMemory;

    private long usedMemory;
    private long usedDirectMemory;
    private long usedHeapMemory;

    private final BufferPoolMXBean directBufferPoolBean;
    private final MemoryMXBean memoryMXBean;

    public MemoryMonitor(int intervalSeconds,
                         int maxAvailableMemoryPercentage,
                         int maxAvailableDirectPercentage,
                         int maxAvailableHeapPercentage) {
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
                    .collect(Collectors.toList());
            String s = "Cannot find the direct buffer pool management bean from the pool beans: "
                    + String.join(", ", names);
            throw new IllegalStateException(s);
        }
        directBufferPoolBean = pool.get();
        memoryMXBean = ManagementFactory.getMemoryMXBean();

        maxDirectMemory = PlatformDependent.maxDirectMemory();
        if (maxDirectMemory < 0) {
            throw new IllegalStateException("Cannot detect the max direct memory: " + maxDirectMemory);
        }
        maxHeapMemory = memoryMXBean.getHeapMemoryUsage().getMax();
        if (maxHeapMemory < 0) {
            throw new IllegalStateException("Cannot detect the max heap memory: " + maxHeapMemory);
        }
        long totalMemory = maxHeapMemory + maxDirectMemory;
        OperatingSystemMXBean operatingSystemBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
        long totalPhysicalMemorySize = operatingSystemBean.getTotalPhysicalMemorySize();
        maxManagedMemory = Math.min(totalMemory, totalPhysicalMemorySize);

        maxAvailableManagedMemory = maxManagedMemory * maxAvailableMemoryPercentage;
        maxAvailableDirectMemory = maxDirectMemory * maxAvailableDirectPercentage;
        maxAvailableHeapMemory = maxHeapMemory * maxAvailableHeapPercentage;

        startMonitor(intervalSeconds);
    }

    public boolean isExceeded() {
        return usedMemory > maxAvailableManagedMemory
                || usedDirectMemory > maxAvailableDirectMemory
                || usedHeapMemory > maxAvailableHeapMemory;
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
        usedDirectMemory = directBufferPoolBean.getMemoryUsed();
        usedHeapMemory = memoryMXBean.getHeapMemoryUsage().getUsed();
        usedMemory = usedDirectMemory + usedHeapMemory;
    }

    private void tryLog() {
        Level logLevel = isExceeded()
                ? Level.WARN
                : Level.DEBUG;
        if (log.isEnabled(logLevel)) {
            log.log(logLevel, "Used managed memory: {}/{}; Used direct memory: {}/{}; Used heap memory: {}/{}",
                    asMbString(usedMemory),
                    asMbString(maxManagedMemory),
                    asMbString(usedDirectMemory),
                    asMbString(maxDirectMemory),
                    asMbString(usedHeapMemory),
                    asMbString(maxHeapMemory));
        }
    }

    private String asMbString(long bytes) {
        return bytes / 1024 / 1024 + "MB";
    }

}
