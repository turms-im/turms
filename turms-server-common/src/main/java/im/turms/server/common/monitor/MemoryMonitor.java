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

import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.internal.PlatformDependent;
import lombok.extern.log4j.Log4j2;

import java.lang.management.BufferPoolMXBean;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author James Chen
 * @see jdk.internal.misc.JavaNioAccess.BufferPool#getMemoryUsed
 * @see io.micrometer.core.instrument.binder.jvm.JvmMemoryMetrics
 */
@Log4j2
public final class MemoryMonitor {

    // TODO: make configurable
    private static final int MEMORY_WATER_MARK = 85;
    private static final BufferPoolMXBean DIRECT_BUFFER_POOL_BEAN;
    private static final MemoryMXBean MEMORY_BEAN;
    private static final long TOTAL_MEMORY;

    static {
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
        DIRECT_BUFFER_POOL_BEAN = pool.get();
        MEMORY_BEAN = ManagementFactory.getMemoryMXBean();
        TOTAL_MEMORY = getTotalMemory();
    }

    /**
     * Note: Make sure DEFAULT is under the static block, or the static block will run
     * after constructor which will call updateUsedMemory while DIRECT_BUFFER_POOL_BEAN is null
     */
    public static final MemoryMonitor DEFAULT = new MemoryMonitor();

    private long usedMemory;
    private int memoryPercentage;

    private MemoryMonitor() {
        DefaultThreadFactory threadFactory = new DefaultThreadFactory("memory-monitor", true);
        Executors.newScheduledThreadPool(1, threadFactory)
                .scheduleWithFixedDelay(() -> {
                    updateUsedMemory();
                    updateMemoryPercentage();
                    if (log.isDebugEnabled()) {
                        log.debug("Used memory: {}/{}", asMbString(usedMemory), asMbString(TOTAL_MEMORY));
                    }
                }, 0, 15, TimeUnit.SECONDS);
    }

    private static long getTotalMemory() {
        // Don't use MEMORY_BEAN.getNonHeapMemoryUsage().getMax()
        return PlatformDependent.maxDirectMemory()
                + MEMORY_BEAN.getHeapMemoryUsage().getMax();
    }

    /**
     * Note that the method doesn't count the following arenas:
     * 1.The memory usage of native code
     * 2.Mapped memory
     */
    private void updateUsedMemory() {
        usedMemory = DIRECT_BUFFER_POOL_BEAN.getMemoryUsed()
                + MEMORY_BEAN.getHeapMemoryUsage().getUsed()
                + MEMORY_BEAN.getNonHeapMemoryUsage().getUsed();
    }

    private String asMbString(long bytes) {
        return bytes / 1024 / 1024 + "MB";
    }

    private void updateMemoryPercentage() {
        memoryPercentage = (int) (100 * usedMemory / TOTAL_MEMORY);
    }

    public boolean isExceeded() {
        return memoryPercentage > MEMORY_WATER_MARK;
    }

}
