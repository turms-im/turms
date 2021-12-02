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

package im.turms.server.common.service.blocklist;

import im.turms.server.common.property.env.common.security.AutoBlockItemProperties;
import im.turms.server.common.property.env.common.security.AutoBlockItemProperties.BlockLevel;
import im.turms.server.common.util.CollectionUtil;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.BiConsumer;

/**
 * @author James Chen
 */
public class AutoBlockManager<T> {

    private final BiConsumer<T, Integer> onClientBlocked;

    private final boolean isEnabled;
    private final List<BlockLevel> levels;
    private final int maxLevel;
    private final int blockTriggerTimes;

    private final Map<T, BlockStatus> blockStatusMap;

    public AutoBlockManager(AutoBlockItemProperties autoBlockProperties, BiConsumer<T, Integer> onClientBlocked) {
        this.onClientBlocked = onClientBlocked;
        levels = CollectionUtil.toListSupportRandomAccess(autoBlockProperties.getBlockLevels());
        maxLevel = levels.size() - 1;
        isEnabled = autoBlockProperties.isEnabled() && !levels.isEmpty();
        blockTriggerTimes = autoBlockProperties.getBlockTriggerTimes();
        if (isEnabled) {
            blockStatusMap = null;
            return;
        }
        blockStatusMap = new ConcurrentHashMap<>(1024);
    }

    public void tryBlockClient(T id) {
        if (!isEnabled) {
            return;
        }
        long now = System.currentTimeMillis();
        BlockStatus status = blockStatusMap.computeIfAbsent(id, key -> new BlockStatus(-1, null, 0, now));
        synchronized (status) {
            // Update status
            long previousBlockTriggerTime = status.lastBlockTriggerTime;
            status.lastBlockTriggerTime = now;
            int reduceOneTriggerTimeInterval = status.currentLevelProperties.getReduceOneTriggerTimeIntervalMillis();
            int times = status.triggerTimes;
            if (reduceOneTriggerTimeInterval > 0) {
                times -= (int) (status.lastBlockTriggerTime - previousBlockTriggerTime) / reduceOneTriggerTimeInterval;
                if (times < 0) {
                    times = 0;
                }
            }
            status.triggerTimes = times + 1;
            boolean isBlocked = status.currentLevel != -1;
            if (isBlocked) {
                if (status.triggerTimes >= status.currentLevelProperties.getGoNextLevelTriggerTimes() && status.currentLevel < maxLevel) {
                    status.currentLevel++;
                    status.currentLevelProperties = levels.get(status.currentLevel);
                    status.triggerTimes = 0;
                }
                onClientBlocked.accept(id, status.currentLevelProperties.getBlockMinutes());
            } else if (status.triggerTimes >= blockTriggerTimes) {
                status.currentLevel = 0;
                status.currentLevelProperties = levels.get(0);
                status.triggerTimes = 0;
                onClientBlocked.accept(id, status.currentLevelProperties.getBlockMinutes());
            } else {
                status.triggerTimes++;
            }
        }
    }

    public void unblockClient(T id) {
        blockStatusMap.remove(id);
    }

    public void evictExpiredBlockedClient() {
        long now = System.currentTimeMillis();
        Iterator<Map.Entry<T, BlockStatus>> iterator = blockStatusMap.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<T, BlockStatus> entry = iterator.next();
            BlockStatus status = entry.getValue();
            int reduceOneTriggerTimeInterval = status.currentLevelProperties.getReduceOneTriggerTimeIntervalMillis();
            if (reduceOneTriggerTimeInterval > 0) {
                int times = status.triggerTimes - (int) (now - status.lastBlockTriggerTime) / reduceOneTriggerTimeInterval;
                if (times <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    @AllArgsConstructor
    @Data
    private static class BlockStatus {
        private int currentLevel;
        private BlockLevel currentLevelProperties;
        private int triggerTimes;
        private long lastBlockTriggerTime;
    }

}
