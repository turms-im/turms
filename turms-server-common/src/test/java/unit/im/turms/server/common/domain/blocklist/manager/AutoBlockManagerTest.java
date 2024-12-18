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

package unit.im.turms.server.common.domain.blocklist.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.ObjLongConsumer;

import org.junit.jupiter.api.Test;

import im.turms.server.common.domain.blocklist.manager.AutoBlockManager;
import im.turms.server.common.infra.property.env.common.security.AutoBlockItemProperties;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class AutoBlockManagerTest {

    @Test
    void tryBlockClient() {
        Map<Long, BlockStats> clientIdToBlockStats = new HashMap<>();
        ObjLongConsumer<Long> onClientBlocked =
                (clientId, blockDurationSeconds) -> clientIdToBlockStats.compute(clientId,
                        (id, stats) -> stats == null
                                ? new BlockStats(1, blockDurationSeconds)
                                : new BlockStats(stats.triggerTimes + 1, blockDurationSeconds));
        AutoBlockItemProperties properties = new AutoBlockItemProperties();
        List<AutoBlockItemProperties.BlockLevelProperties> blockLevelPropertiesList =
                properties.getBlockLevels();
        AutoBlockManager<Long> manager = new AutoBlockManager<>(properties, onClientBlocked);

        AutoBlockManager.BlockStatus blockStatus;
        Long clientIdToBlock = 1L;

        // The client should not be blocked before reaching the threshold
        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isEmpty();
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(AutoBlockManager.UNSET_BLOCK_LEVEL);
        assertThat(blockStatus.getCurrentLevelProperties()).isNull();
        assertThat(blockStatus.getNextLevelProperties()).isNotNull();

        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isEmpty();
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(AutoBlockManager.UNSET_BLOCK_LEVEL);
        assertThat(blockStatus.getCurrentLevelProperties()).isNull();
        assertThat(blockStatus.getNextLevelProperties()).isNotNull();

        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isEmpty();
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(AutoBlockManager.UNSET_BLOCK_LEVEL);
        assertThat(blockStatus.getCurrentLevelProperties()).isNull();
        assertThat(blockStatus.getNextLevelProperties()).isNotNull();

        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isEmpty();
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(AutoBlockManager.UNSET_BLOCK_LEVEL);
        assertThat(blockStatus.getCurrentLevelProperties()).isNull();
        assertThat(blockStatus.getNextLevelProperties()).isNotNull();

        // The client should be blocked when reaching the threshold
        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isNotEmpty();
        assertThat(clientIdToBlockStats.get(clientIdToBlock).triggerTimes).isOne();
        assertThat(clientIdToBlockStats.get(clientIdToBlock).lastBlockTriggerSeconds)
                .isEqualTo(blockLevelPropertiesList.getFirst()
                        .getBlockDurationSeconds());
        assertThat(blockStatus.getCurrentLevel()).isZero();
        assertThat(blockStatus.getCurrentLevelProperties()).isNotNull();
        assertThat(blockStatus.getNextLevelProperties()).isNotNull();
        assertThat(blockStatus.getTriggerTimes()).isZero();

        // The client should be blocked with the second level
        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isNotEmpty();
        assertThat(clientIdToBlockStats.get(clientIdToBlock).triggerTimes).isEqualTo(2);
        assertThat(clientIdToBlockStats.get(clientIdToBlock).lastBlockTriggerSeconds)
                .isEqualTo(blockLevelPropertiesList.get(1)
                        .getBlockDurationSeconds());
        assertThat(blockStatus.getCurrentLevel()).isOne();
        assertThat(blockStatus.getCurrentLevelProperties()).isNotNull();
        assertThat(blockStatus.getNextLevelProperties()).isNotNull();
        assertThat(blockStatus.getTriggerTimes()).isZero();

        // The client should be blocked with the third level
        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isNotEmpty();
        assertThat(clientIdToBlockStats.get(clientIdToBlock).triggerTimes).isEqualTo(3);
        assertThat(clientIdToBlockStats.get(clientIdToBlock).lastBlockTriggerSeconds)
                .isEqualTo(blockLevelPropertiesList.get(2)
                        .getBlockDurationSeconds());
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(2);
        assertThat(blockStatus.getCurrentLevelProperties()).isNotNull();
        assertThat(blockStatus.getNextLevelProperties()).isNull();
        assertThat(blockStatus.getTriggerTimes()).isZero();

        // The client should be blocked with the third level
        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isNotEmpty();
        assertThat(clientIdToBlockStats.get(clientIdToBlock).triggerTimes).isEqualTo(4);
        assertThat(clientIdToBlockStats.get(clientIdToBlock).lastBlockTriggerSeconds)
                .isEqualTo(blockLevelPropertiesList.get(2)
                        .getBlockDurationSeconds());
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(2);
        assertThat(blockStatus.getCurrentLevelProperties()).isNotNull();
        assertThat(blockStatus.getNextLevelProperties()).isNull();
        assertThat(blockStatus.getTriggerTimes()).isEqualTo(1);

        // The client should be blocked with the third level
        blockStatus = manager.tryBlockClient(clientIdToBlock);
        assertThat(clientIdToBlockStats).isNotEmpty();
        assertThat(clientIdToBlockStats.get(clientIdToBlock).triggerTimes).isEqualTo(5);
        assertThat(clientIdToBlockStats.get(clientIdToBlock).lastBlockTriggerSeconds)
                .isEqualTo(blockLevelPropertiesList.get(2)
                        .getBlockDurationSeconds());
        assertThat(blockStatus.getCurrentLevel()).isEqualTo(2);
        assertThat(blockStatus.getCurrentLevelProperties()).isNotNull();
        assertThat(blockStatus.getNextLevelProperties()).isNull();
        assertThat(blockStatus.getTriggerTimes()).isEqualTo(2);
    }

    record BlockStats(
            int triggerTimes,
            long lastBlockTriggerSeconds
    ) {
    }

}