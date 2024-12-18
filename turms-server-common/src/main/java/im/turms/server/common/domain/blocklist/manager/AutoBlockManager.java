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
import jakarta.annotation.Nullable;

import lombok.AllArgsConstructor;
import lombok.Data;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.MathUtil;
import im.turms.server.common.infra.property.env.common.security.AutoBlockItemProperties;
import im.turms.server.common.infra.test.VisibleForTesting;
import im.turms.server.common.infra.time.DateTimeUtil;

/**
 * @author James Chen
 */
public class AutoBlockManager<T> {

    @VisibleForTesting
    public static final int UNSET_BLOCK_LEVEL = -1;

    private final ObjLongConsumer<T> onClientBlocked;

    private final boolean isEnabled;
    private final List<ParsedBlockLevelProperties> blockLevelPropertiesList;
    private final int maxLevel;

    private final ConcurrentHashMap<T, BlockStatus> blockedClientIdToStatus;

    public AutoBlockManager(
            AutoBlockItemProperties autoBlockProperties,
            ObjLongConsumer<T> onClientBlocked) {
        this.onClientBlocked = onClientBlocked;
        blockLevelPropertiesList =
                CollectionUtil.transformAsList(autoBlockProperties.getBlockLevels(),
                        properties -> new ParsedBlockLevelProperties(
                                properties.getBlockDurationSeconds(),
                                DateTimeUtil.millisToNanos(
                                        properties.getReduceOneTriggerTimeIntervalMillis()),
                                properties.getTriggerTimesThreshold()));
        isEnabled = autoBlockProperties.isEnabled() && !blockLevelPropertiesList.isEmpty();
        if (!isEnabled) {
            blockedClientIdToStatus = null;
            maxLevel = UNSET_BLOCK_LEVEL;
            return;
        }
        blockedClientIdToStatus = new ConcurrentHashMap<>(1024);
        maxLevel = blockLevelPropertiesList.size() - 1;
    }

    @Nullable
    public BlockStatus tryBlockClient(T id) {
        if (!isEnabled) {
            return null;
        }
        return blockedClientIdToStatus.compute(id, (key, status) -> {
            long now = System.nanoTime();
            if (status == null) {
                status = new BlockStatus(
                        UNSET_BLOCK_LEVEL,
                        null,
                        blockLevelPropertiesList.getFirst(),
                        0,
                        now);
            }
            ParsedBlockLevelProperties nextLevelProperties = status.nextLevelProperties;
            // If already reaching the max level,
            // notify the callback to refresh the block end time.
            if (nextLevelProperties == null) {
                status.lastBlockTriggerTimeNanos = now;
                status.triggerTimes++;
                onClientBlocked.accept(id, status.currentLevelProperties.blockDurationSeconds);
                return status;
            }

            long previousBlockTriggerTimeNanos = status.lastBlockTriggerTimeNanos;
            status.lastBlockTriggerTimeNanos = now;
            // Update the trigger times
            long reduceOneTriggerTimeIntervalNanos =
                    nextLevelProperties.reduceOneTriggerTimeIntervalNanos;
            int triggerTimes = status.triggerTimes;
            if (reduceOneTriggerTimeIntervalNanos > 0) {
                triggerTimes -= MathUtil.toInt(((now - previousBlockTriggerTimeNanos)
                        / reduceOneTriggerTimeIntervalNanos));
                if (triggerTimes <= 0) {
                    status.triggerTimes = 1;
                } else {
                    status.triggerTimes = triggerTimes + 1;
                }
            } else {
                status.triggerTimes++;
            }
            ParsedBlockLevelProperties currentLevelProperties = status.currentLevelProperties;
            // Check if the status needs to advance to the next level
            if (status.triggerTimes >= nextLevelProperties.triggerTimesThreshold
                    && status.currentLevel < maxLevel) {
                status.currentLevel++;
                status.currentLevelProperties = blockLevelPropertiesList.get(status.currentLevel);
                if (status.currentLevel + 1 <= maxLevel) {
                    status.nextLevelProperties =
                            blockLevelPropertiesList.get(status.currentLevel + 1);
                } else {
                    status.nextLevelProperties = null;
                }
                status.triggerTimes = 0;
                onClientBlocked.accept(id, status.currentLevelProperties.blockDurationSeconds);
            } else if (currentLevelProperties != null) {
                // If already blocked,
                // notify the callback to refresh the block end time.
                onClientBlocked.accept(id, currentLevelProperties.blockDurationSeconds);
            }
            return status;
        });
    }

    public void unblockClient(T id) {
        if (!isEnabled) {
            return;
        }
        blockedClientIdToStatus.remove(id);
    }

    public void evictExpiredBlockedClients() {
        if (!isEnabled) {
            return;
        }
        long now = System.nanoTime();
        Iterator<BlockStatus> iterator = blockedClientIdToStatus.values()
                .iterator();
        while (iterator.hasNext()) {
            BlockStatus status = iterator.next();
            ParsedBlockLevelProperties currentLevelProperties = status.currentLevelProperties;
            // If the client has been blocked,
            // remove if the block duration has expired.
            if (currentLevelProperties != null) {
                if ((now - status.lastBlockTriggerTimeNanos)
                        / DateTimeUtil.NANOS_PER_SECOND > currentLevelProperties.blockDurationSeconds) {
                    iterator.remove();
                }
                continue;
            }
            // If the client is not blocked,
            // remove if the trigger times have expired.
            long reduceOneTriggerTimeIntervalNanos =
                    status.nextLevelProperties.reduceOneTriggerTimeIntervalNanos;
            if (reduceOneTriggerTimeIntervalNanos > 0) {
                int triggerTimes = status.triggerTimes
                        - MathUtil.toInt((now - status.lastBlockTriggerTimeNanos)
                                / reduceOneTriggerTimeIntervalNanos);
                if (triggerTimes <= 0) {
                    iterator.remove();
                }
            }
        }
    }

    @AllArgsConstructor
    @Data
    public static class BlockStatus {
        private int currentLevel;
        /**
         * Null if the client is not blocked.
         */
        @Nullable
        private ParsedBlockLevelProperties currentLevelProperties;
        /**
         * Null if the current level is the max level.
         */
        @Nullable
        private ParsedBlockLevelProperties nextLevelProperties;
        private int triggerTimes;
        private long lastBlockTriggerTimeNanos;
    }

    public record ParsedBlockLevelProperties(
            long blockDurationSeconds,
            long reduceOneTriggerTimeIntervalNanos,
            int triggerTimesThreshold
    ) {
    }

}