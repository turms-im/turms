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

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import im.turms.server.common.domain.blocklist.service.BlocklistService;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.security.BlocklistProperties;
import im.turms.server.common.infra.property.env.common.security.SecurityProperties;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.storage.redis.TurmsRedisClient;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContext;
import im.turms.server.common.testing.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BlocklistServiceIT extends BaseIntegrationTest {

    private static final long BLOCK_DURATION_SECONDS = 60;
    private static final Duration TIMEOUT = Duration.ofSeconds(15);

    private static BlocklistService sharedBlocklistService;

    @BeforeAll
    static void setup() {
        setupTestEnvironment();
        sharedBlocklistService = newBlocklistService(2);
    }

    @Order(0)
    @Test
    void test() {
        assertThat(sharedBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked at first")
                .isFalse();
        assertThat(sharedBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked at first")
                .isFalse();
        assertThat(sharedBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked at first")
                .isFalse();

        Set<Long> userIds = Set.of(1L, 2L, 3L);

        sharedBlocklistService.blockUserIds(userIds, BLOCK_DURATION_SECONDS)
                .block(TIMEOUT);
        waitToSync();

        assertThat(sharedBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should be blocked after blockUserIds(...)")
                .isTrue();
        assertThat(sharedBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should be blocked after blockUserIds(...)")
                .isTrue();
        assertThat(sharedBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should be blocked after blockUserIds(...)")
                .isTrue();
        assertThat(sharedBlocklistService.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked after blockUserIds(...)")
                .isFalse();

        sharedBlocklistService.unblockUserIds(userIds)
                .block(TIMEOUT);
        waitToSync();

        assertThat(sharedBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked after unblockUserIds(...)")
                .isFalse();
        assertThat(sharedBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked after unblockUserIds(...)")
                .isFalse();
        assertThat(sharedBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked after unblockUserIds(...)")
                .isFalse();
        assertThat(sharedBlocklistService.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked after unblockUserIds(...)")
                .isFalse();
    }

    @Order(1)
    @Test
    void testLogSynchronization_simple() {
        BlocklistService localBlocklistService = newBlocklistService(2);

        assertThat(localBlocklistService.isUserIdBlocked(1L))
                .as("The user with ID 1 should not be blocked at first")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(2L))
                .as("The user with ID 2 should not be blocked at first")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(3L))
                .as("The user with ID 3 should not be blocked at first")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(4L))
                .as("The user with ID 4 should not be blocked at first")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(5L))
                .as("The user with ID 5 should not be blocked at first")
                .isFalse();

        Set<Long> userIds = Set.of(1L, 2L, 3L, 4L, 5L);

        sharedBlocklistService.blockUserIds(userIds, BLOCK_DURATION_SECONDS)
                .block(TIMEOUT);
        waitToSync();

        assertThat(localBlocklistService.isUserIdBlocked(1L)).as(
                "The user with ID 1 should be blocked after the shared blocklist service called blockUserIds(...)")
                .isTrue();
        assertThat(localBlocklistService.isUserIdBlocked(2L)).as(
                "The user with ID 2 should be blocked after the shared blocklist service called blockUserIds(...)")
                .isTrue();
        assertThat(localBlocklistService.isUserIdBlocked(3L)).as(
                "The user with ID 3 should be blocked after the shared blocklist service called blockUserIds(...)")
                .isTrue();
        assertThat(localBlocklistService.isUserIdBlocked(4L)).as(
                "The user with ID 4 should be blocked after the shared blocklist service called blockUserIds(...)")
                .isTrue();
        assertThat(localBlocklistService.isUserIdBlocked(5L)).as(
                "The user with ID 5 should be blocked after the shared blocklist service called blockUserIds(...)")
                .isTrue();
        assertThat(localBlocklistService.isUserIdBlocked(6L)).as(
                "The user with ID 6 should not be blocked after the shared blocklist service called blockUserIds(...)")
                .isFalse();

        sharedBlocklistService.unblockUserIds(userIds)
                .block(TIMEOUT);
        waitToSync();

        assertThat(localBlocklistService.isUserIdBlocked(1L)).as(
                "The user with ID 1 should not be blocked after the shared blocklist service called unblockUserIds(...)")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(2L)).as(
                "The user with ID 2 should not be blocked after the shared blocklist service called unblockUserIds(...)")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(3L)).as(
                "The user with ID 3 should not be blocked after the shared blocklist service called unblockUserIds(...)")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(4L)).as(
                "The user with ID 4 should not be blocked after the shared blocklist service called unblockUserIds(...)")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(5L)).as(
                "The user with ID 5 should not be blocked after the shared blocklist service called unblockUserIds(...)")
                .isFalse();
        assertThat(localBlocklistService.isUserIdBlocked(6L)).as(
                "The user with ID 6 should not be blocked after the shared blocklist service called unblockUserIds(...)")
                .isFalse();
    }

    @Order(2)
    @Test
    void testLogSynchronization_idempotency() {
        BlocklistService localBlocklistService = newBlocklistService(2);
        long userId = 1L;

        // Local block -> Shared block -> Shared block

        blockUserIdsOnLocalBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared block -> Shared block = Local block");
        blockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared block -> Shared block = Shared block 1");
        blockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared block -> Shared block = Shared block 2");

        // Shared unblock -> Shared unblock -> Local unblock

        unblockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Shared unblock -> Local unblock = Shared unblock 1");
        unblockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Shared unblock -> Local unblock = Shared unblock 2");
        blockUserIdsOnLocalBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Shared unblock -> Local unblock = Local unblock");
    }

    @Order(3)
    @Test
    void testLogSynchronization_interweave() {
        BlocklistService localBlocklistService = newBlocklistService(2);
        long userId = 1L;

        // Local block -> Shared unblock -> Local block -> Shared unblock

        blockUserIdsOnLocalBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared unblock -> Local block -> Shared unblock = Local block 1");
        unblockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared unblock -> Local block -> Shared unblock = Shared unblock 1");
        blockUserIdsOnLocalBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared unblock -> Local block -> Shared unblock = Local block 2");
        unblockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Local block -> Shared unblock -> Local block -> Shared unblock = Shared unblock 2");

        // Shared block -> Local unblock -> Shared block -> Local unblock

        blockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Shared block -> Local unblock -> Shared block -> Local unblock = Shared block 1");
        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Shared block -> Local unblock -> Shared block -> Local unblock = Local unblock 1");
        blockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Shared block -> Local unblock -> Shared block -> Local unblock = Shared block 2");
        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Shared block -> Local unblock -> Shared block -> Local unblock = Local unblock 2");

        // Local unblock -> Shared block -> Local unblock -> Shared block

        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local unblock -> Shared block -> Local unblock -> Shared block = Local unblock 1");
        blockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Local unblock -> Shared block -> Local unblock -> Shared block = Shared block 1");
        unblockUserIdsOnLocal(localBlocklistService,
                userId,
                "Local unblock -> Shared block -> Local unblock -> Shared block = Local unblock 2");
        blockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Local unblock -> Shared block -> Local unblock -> Shared block = Shared block 2");

        // Shared unblock -> Local block -> Shared unblock -> Local block

        unblockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Local block -> Shared unblock -> Local block = Shared unblock 1");
        blockUserIdsOnLocalBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Local block -> Shared unblock -> Local block = Local block 1");
        unblockUserIdsOnSharedBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Local block -> Shared unblock -> Local block = Shared unblock 2");
        blockUserIdsOnLocalBlocklistService(localBlocklistService,
                userId,
                "Shared unblock -> Local block -> Shared unblock -> Local block = Local block 2");
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

        String uri = testEnvironmentManager.getRedisUri();

        BlocklistService.maxLogQueueSize = maxLogSize;
        TurmsRedisClient redisClient = new TurmsRedisClient(uri, RedisCodecContext.DEFAULT);
        return new BlocklistService(
                node,
                new TaskManager(),
                mock(TurmsApplicationContext.class),
                redisClient,
                redisClient,
                propertiesManager,
                null);
    }

    private void blockUserIdsOnLocalBlocklistService(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        localBlocklistService.blockUserIds(Set.of(userId), BLOCK_DURATION_SECONDS)
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

    private void blockUserIdsOnSharedBlocklistService(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        sharedBlocklistService.blockUserIds(Set.of(userId), BLOCK_DURATION_SECONDS)
                .block(TIMEOUT);
        waitToSync();
        assertThat(localBlocklistService.isUserIdBlocked(userId)).as(desc)
                .isTrue();
    }

    private void unblockUserIdsOnSharedBlocklistService(
            BlocklistService localBlocklistService,
            long userId,
            String desc) {
        sharedBlocklistService.unblockUserIds(Set.of(userId))
                .block(TIMEOUT);
        waitToSync();
        assertThat(localBlocklistService.isUserIdBlocked(userId)).as(desc)
                .isFalse();
    }

}