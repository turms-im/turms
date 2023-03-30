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

package integration.im.turms.server.common.domain.blocklist.service;

import java.time.Duration;
import java.util.Set;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.security.BlocklistProperties;
import im.turms.server.common.infra.property.env.common.security.SecurityProperties;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.storage.redis.CommonRedisConfig;
import im.turms.server.common.testing.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlocklistServiceIT extends BaseIntegrationTest {

    private static final BlocklistService BLOCKLIST_SERVICE;
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    static {
        BLOCKLIST_SERVICE = newBlocklistService(2);
    }

    BlocklistServiceIT() {
        waitMillis = 500L;
    }

    @Order(0)
    @Test
    void test() {
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked at first")
                .isFalse();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked at first")
                .isFalse();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked at first")
                .isFalse();

        Set<Long> userIds = Set.of(1L, 2L, 3L);

        BLOCKLIST_SERVICE.blockUserIds(userIds, 1)
                .block(TIMEOUT);
        waitToSync();

        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(1L))
                .as("The user with ID 1 should be blocked after blockUserIds()")
                .isTrue();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(2L))
                .as("The user with ID 2 should be blocked after blockUserIds()")
                .isTrue();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(3L))
                .as("The user with ID 3 should be blocked after blockUserIds()")
                .isTrue();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked after blockUserIds()")
                .isFalse();

        BLOCKLIST_SERVICE.unblockUserIds(userIds)
                .block(TIMEOUT);
        waitToSync();

        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked after unblockUserIds()")
                .isFalse();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked after unblockUserIds()")
                .isFalse();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked after unblockUserIds()")
                .isFalse();
        assertThat(BLOCKLIST_SERVICE.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked after unblockUserIds()")
                .isFalse();
    }

    @Order(1)
    @Test
    void testLogSynchronization_simple() {
        BlocklistService myBlocklistService = newBlocklistService(2);

        assertThat(myBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked at first")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked at first")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked at first")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked at first")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(5L))
                .as("The user with ID 5 should not be blocked at first")
                .isFalse();

        Set<Long> userIds = Set.of(1L, 2L, 3L, 4L, 5L);

        BLOCKLIST_SERVICE.blockUserIds(userIds, 1)
                .block(TIMEOUT);
        waitToSync();

        assertThat(myBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should be blocked after blockUserIds() on remote")
                .isTrue();
        assertThat(myBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should be blocked after blockUserIds() on remote")
                .isTrue();
        assertThat(myBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should be blocked after blockUserIds() on remote")
                .isTrue();
        assertThat(myBlocklistService.isUserIdBlocked(4L))
                .as("The user with ID 4 should be blocked after blockUserIds() on remote")
                .isTrue();
        assertThat(myBlocklistService.isUserIdBlocked(5L))
                .as("The user with ID 5 should be blocked after blockUserIds() on remote")
                .isTrue();
        assertThat(myBlocklistService.isUserIdBlocked(6L))
                .as("The user with ID 6 should not be blocked after blockUserIds() on remote")
                .isFalse();

        BLOCKLIST_SERVICE.unblockUserIds(userIds)
                .block(TIMEOUT);
        waitToSync();

        assertThat(myBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked after unblockUserIds() on remote")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked after unblockUserIds() on remote")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked after unblockUserIds() on remote")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked after unblockUserIds() on remote")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(5L))
                .as("The user with ID 5 should not be blocked after unblockUserIds() on remote")
                .isFalse();
        assertThat(myBlocklistService.isUserIdBlocked(6L))
                .as("The user with ID 6 should not be blocked after unblockUserIds() on remote")
                .isFalse();
    }

    @Order(2)
    @Test
    void testLogSynchronization_idempotency() {
        BlocklistService localBlocklistService = newBlocklistService(2);
        long userId = 1L;

        // Local block - Remote block - Remote block

        blockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local block - Remote block - Remote block = Local block");
        blockUserIdsOnRemote(localBlocklistService,
                userId,
                "Local block - Remote block - Remote block = Remote block 1");
        blockUserIdsOnRemote(localBlocklistService,
                userId,
                "Local block - Remote block - Remote block = Remote block 2");

        // Remote unblock - Remote unblock - Local unblock.

        unblockUserIdsOnRemote(localBlocklistService,
                userId,
                "Remote unblock - Remote unblock - Local unblock = Remote unblock 1");
        unblockUserIdsOnRemote(localBlocklistService,
                userId,
                "Remote unblock - Remote unblock - Local unblock = Remote unblock 2");
        blockUserIdsOnLocal(localBlocklistService,
                userId,
                "Remote unblock - Remote unblock - Local unblock = Local unblock");
    }

    @Order(3)
    @Test
    void testLogSynchronization_interweave() {
        BlocklistService localBlocklistService = newBlocklistService(2);
        long userId = 1L;

        // Local block -> Remote unblock -> Local block -> Remote unblock

        blockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local block -> Remote unblock -> Local block -> Remote unblock = Local block 1");
        unblockUserIdsOnRemote(localBlocklistService,
                userId,
                "Local block -> Remote unblock -> Local block -> Remote unblock = Remote unblock 1");
        blockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local block -> Remote unblock -> Local block -> Remote unblock = Local block 2");
        unblockUserIdsOnRemote(localBlocklistService,
                userId,
                "Local block -> Remote unblock -> Local block -> Remote unblock = Remote unblock 2");

        // Remote block -> Local unblock -> Remote block -> Local unblock

        blockUserIdsOnRemote(localBlocklistService,
                userId,
                "Remote block -> Local unblock -> Remote block -> Local unblock = Remote block 1");
        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Remote block -> Local unblock -> Remote block -> Local unblock = Local unblock 1");
        blockUserIdsOnRemote(localBlocklistService,
                userId,
                "Remote block -> Local unblock -> Remote block -> Local unblock = Remote block 2");
        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Remote block -> Local unblock -> Remote block -> Local unblock = Local unblock 2");

        // Local unblock -> Remote block -> Local unblock -> Remote block

        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local unblock -> Remote block -> Local unblock -> Remote block = Local unblock 1");
        blockUserIdsOnRemote(localBlocklistService,
                userId,
                "Local unblock -> Remote block -> Local unblock -> Remote block = Remote block 1");
        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local unblock -> Remote block -> Local unblock -> Remote block = Local unblock 2");
        blockUserIdsOnRemote(localBlocklistService,
                userId,
                "Local unblock -> Remote block -> Local unblock -> Remote block = Remote block 2");

        // Remote unblock -> Local block -> Remote unblock -> Local block

        unblockUserIdsOnRemote(localBlocklistService,
                userId,
                "Remote unblock -> Local block -> Remote unblock -> Local block = Remote unblock 1");
        blockUserIdsOnLocal(localBlocklistService,
                userId,
                "Remote unblock -> Local block -> Remote unblock -> Local block = Local block 1");
        unblockUserIdsOnRemote(localBlocklistService,
                userId,
                "Remote unblock -> Local block -> Remote unblock -> Local block = Remote unblock 2");
        blockUserIdsOnLocal(localBlocklistService,
                userId,
                "Remote unblock -> Local block -> Remote unblock -> Local block = Local block 2");
    }

    private static BlocklistService newBlocklistService(int maxLogSize) {
        Node node = mock(Node.class);
        when(node.isLocalNodeLeader()).thenReturn(true);

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        BlocklistProperties.IpBlocklistTypeProperties ipBlocklistTypeProperties =
                new BlocklistProperties.IpBlocklistTypeProperties().toBuilder()
                        .syncBlocklistIntervalMillis(10)
                        .build();
        BlocklistProperties.UserIdBlocklistTypeProperties userIdBlocklistTypeProperties =
                new BlocklistProperties.UserIdBlocklistTypeProperties().toBuilder()
                        .syncBlocklistIntervalMillis(10)
                        .build();
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .security(SecurityProperties.builder()
                        .blocklist(new BlocklistProperties().toBuilder()
                                .ip(ipBlocklistTypeProperties)
                                .userId(userIdBlocklistTypeProperties)
                                .build())
                        .build())
                .build());

        String uri = "redis://%s:%d".formatted(ENV.getRedisHost(), ENV.getRedisPort());

        BlocklistService.maxLogQueueSize = maxLogSize;
        return new BlocklistService(
                node,
                new TaskManager(),
                mock(TurmsApplicationContext.class),
                CommonRedisConfig.newIpBlocklistRedisClient(uri),
                CommonRedisConfig.newUserIdBlocklistRedisClient(uri),
                propertiesManager,
                null);
    }

    private void blockUserIdsOnLocal(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        localBlocklistService.blockUserIds(Set.of(userId), 1)
                .block(TIMEOUT);
        waitToSync();
        assertThat(localBlocklistService.isUserIdBlocked(userId)).as(desc)
                .isTrue();
    }

    private void unblockUserIdsOnLocal(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        localBlocklistService.unblockUserIds(Set.of(userId))
                .block(TIMEOUT);
        waitToSync();
        assertThat(localBlocklistService.isUserIdBlocked(userId)).as(desc)
                .isFalse();
    }

    private void blockUserIdsOnRemote(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        BLOCKLIST_SERVICE.blockUserIds(Set.of(userId), 1)
                .block(TIMEOUT);
        waitToSync();
        assertThat(localBlocklistService.isUserIdBlocked(userId)).as(desc)
                .isTrue();
    }

    private void unblockUserIdsOnRemote(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        BLOCKLIST_SERVICE.unblockUserIds(Set.of(userId))
                .block(TIMEOUT);
        waitToSync();
        assertThat(localBlocklistService.isUserIdBlocked(userId)).as(desc)
                .isFalse();
    }

}
