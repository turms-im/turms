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

package im.turms.server.common.domain.blocklist.manager;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.ObjLongConsumer;

import lombok.AllArgsConstructor;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.property.env.common.security.AutoBlockItemProperties;
import im.turms.server.common.infra.property.env.common.security.AutoBlockItemProperties.BlockLevel;

/**
 * @author James Chen
 */
public class AutoBlockManager<T> {

    private static final int UNSET_BLOCK_LEVEL = -1;

    private final ObjLongConsumer<T> onClientBlocked;

    private final boolean isEnabled;
    private final List<BlockLevel> levels;
    private final int maxLevel;
    private final int blockTriggerTimes;

    private final ConcurrentHashMap<T, BlockStatus> blockedClientIdToStatus;

    public AutoBlockManager(
            AutoBlockItemProperties autoBlockProperties,
            ObjLongConsumer<T> onClientBlocked) {
        this.onClientBlocked = onClientBlocked;
        levels = CollectionUtil.toListSupportRandomAccess(autoBlockProperties.getBlockLevels());
        maxLevel = levels.size() - 1;
        isEnabled = autoBlockProperties.isEnabled() && !levels.isEmpty();
        blockTriggerTimes = autoBlockProperties.getBlockTriggerTimes();
        if (isEnabled) {
            blockedClientIdToStatus = null;
            return;
        }
        blockedClientIdToStatus = new ConcurrentHashMap<>(1024);
    }

    public void tryBlockClient(T id) {
        if (!isEnabled) {
            return;
        }
        blockedClientIdToStatus.compute(id, (key, status) -> {
            long now = System.currentTimeMillis();
            if (status == null) {
                status = new BlockStatus(UNSET_BLOCK_LEVEL, null, 0, now);
            }
            // Update status
            long previousBlockTriggerTime = status.lastBlockTriggerTime;
            status.lastBlockTriggerTime = now;
            int reduceOneTriggerTimeInterval =
                    status.currentLevelProperties.getReduceOneTriggerTimeIntervalMillis();
            int times = status.triggerTimes;
            if (reduceOneTriggerTimeInterval > 0) {
                times -= (int) (status.lastBlockTriggerTime - previousBlockTriggerTime)
                        / reduceOneTriggerTimeInterval;
                if (times < 0) {
                    times = 0;
                }
            }
            status.triggerTimes = times + 1;
            boolean isBlocked = status.currentLevel != UNSET_BLOCK_LEVEL;
            if (isBlocked) {
                if (status.triggerTimes >= status.currentLevelProperties
                        .getGoNextLevelTriggerTimes() && status.currentLevel < maxLevel) {
                    status.currentLevel++;
                    status.currentLevelProperties = levels.get(status.currentLevel);
                    status.triggerTimes = 0;
                }
                onClientBlocked.accept(id, status.currentLevelProperties.getBlockDurationSeconds());
            } else if (status.triggerTimes >= blockTriggerTimes) {
                status.currentLevel = 0;
                status.currentLevelProperties = levels.get(0);
                status.triggerTimes = 0;
                onClientBlocked.accept(id, status.currentLevelProperties.getBlockDurationSeconds());
            } else {
                status.triggerTimes++;
            }
            return status;
        });
    }

    public void unblockClient(T id) {
        blockedClientIdToStatus.remove(id);
    }

    public void evictExpiredBlockedClients() {
        long now = System.currentTimeMillis();
        Iterator<BlockStatus> iterator = blockedClientIdToStatus.values()
                .iterator();
        while (iterator.hasNext()) {
            BlockStatus status = iterator.next();
            int reduceOneTriggerTimeInterval =
                    status.currentLevelProperties.getReduceOneTriggerTimeIntervalMillis();
            if (reduceOneTriggerTimeInterval > 0) {
                int times = status.triggerTimes
                        - (int) (now - status.lastBlockTriggerTime) / reduceOneTriggerTimeInterval;
                if (times <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    @AllArgsConstructor
    private static class BlockStatus {
        private int currentLevel;
        private BlockLevel currentLevelProperties;
        private int triggerTimes;
        private long lastBlockTriggerTime;
    }

}
