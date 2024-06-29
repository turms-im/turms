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

package integration.im.turms.service.domain.message.service;

import java.util.List;
import java.util.Set;

import io.micrometer.core.instrument.Metrics;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.message.OutboundMessageManager;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.storage.redis.RedisProperties;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContext;
import im.turms.server.common.testing.BaseIntegrationTest;
import im.turms.service.domain.conversation.service.ConversationService;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.group.service.GroupService;
import im.turms.service.domain.message.repository.MessageRepository;
import im.turms.service.domain.message.service.MessageService;
import im.turms.service.domain.observation.service.MetricsService;
import im.turms.service.domain.user.service.UserService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class MessageServiceIT extends BaseIntegrationTest {

    private static final long GROUP_ID_1 = 1L;
    private static final long GROUP_ID_2 = 2L;
    private static final long GROUP_ID_3 = 3L;

    private static final long USER_ID_1 = 10L;
    private static final long USER_ID_2 = 11L;
    private static final long USER_ID_3 = 12L;

    private static MessageService messageService;

    @BeforeAll
    static void setup() {
        setupTestEnvironment();
        TurmsRedisClientManager sequenceIdRedisClientManager = new TurmsRedisClientManager(
                new RedisProperties().toBuilder()
                        .uriList(List.of(testEnvironmentManager.getRedisUri()))
                        .build(),
                RedisCodecContext.DEFAULT);
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        TurmsProperties properties = new TurmsProperties();
        when(propertiesManager.getLocalProperties()).thenReturn(properties);
        when(propertiesManager.getGlobalProperties()).thenReturn(properties);
        messageService = new MessageService(
                mock(MessageRepository.class),
                mock(OutboundMessageManager.class),
                sequenceIdRedisClientManager,
                mock(Node.class),
                propertiesManager,
                mock(ConversationService.class),
                mock(GroupService.class),
                mock(GroupMemberService.class),
                mock(UserService.class),
                new MetricsService(Metrics.globalRegistry),
                mock(PluginManager.class),
                mock(TaskManager.class));
    }

    @Order(100)
    @Test
    void fetchGroupMessageSequenceId() {
        Mono<Long> sequenceId = messageService.fetchGroupMessageSequenceId(GROUP_ID_1);
        StepVerifier.create(sequenceId)
                .expectNext(1L)
                .as("The sequence ID should be 1 at the 1st time")
                .verifyComplete();
        sequenceId = messageService.fetchGroupMessageSequenceId(GROUP_ID_1);
        StepVerifier.create(sequenceId)
                .expectNext(2L)
                .as("The sequence ID should be 2 at the 2nd time")
                .verifyComplete();
        sequenceId = messageService.fetchGroupMessageSequenceId(GROUP_ID_2);
        StepVerifier.create(sequenceId)
                .expectNext(1L)
                .as("The sequence ID should be 1 at the 1st time")
                .verifyComplete();
    }

    @Order(101)
    @Test
    void fetchPrivateMessageSequenceId() {
        Mono<Long> sequenceId = messageService.fetchPrivateMessageSequenceId(USER_ID_1, USER_ID_2);
        StepVerifier.create(sequenceId)
                .expectNext(1L)
                .as("The sequence ID should be 1 at the 1st time")
                .verifyComplete();
        sequenceId = messageService.fetchPrivateMessageSequenceId(USER_ID_1, USER_ID_2);
        StepVerifier.create(sequenceId)
                .expectNext(2L)
                .as("The sequence ID should be 2 at the 2nd time")
                .verifyComplete();
        sequenceId = messageService.fetchPrivateMessageSequenceId(USER_ID_1, USER_ID_3);
        StepVerifier.create(sequenceId)
                .expectNext(1L)
                .as("The sequence ID should be 1 at the 1st time")
                .verifyComplete();
        sequenceId = messageService.fetchPrivateMessageSequenceId(USER_ID_2, USER_ID_3);
        StepVerifier.create(sequenceId)
                .expectNext(1L)
                .as("The sequence ID should be 1 at the 1st time")
                .verifyComplete();
    }

    @Order(200)
    @Test
    void deleteGroupMessageSequenceIds() {
        Mono<Long> delete =
                messageService.deleteGroupMessageSequenceIds(Set.of(GROUP_ID_1, GROUP_ID_2));
        StepVerifier.create(delete)
                .expectNext(2L)
                .as("Deleted count should be 2 since sequence IDs for both groups exist")
                .verifyComplete();
        delete = messageService.deleteGroupMessageSequenceIds(Set.of(GROUP_ID_1, GROUP_ID_2));
        StepVerifier.create(delete)
                .expectNext(0L)
                .as("Deleted count should be 0 since sequence IDs for both groups have been deleted")
                .verifyComplete();
        delete = messageService.deleteGroupMessageSequenceIds(Set.of(GROUP_ID_1, GROUP_ID_3));
        StepVerifier.create(delete)
                .expectNext(0L)
                .as("Deleted count should be 0 since sequence ID for group1 has been deleted and the group3 never use sequence ID")
                .verifyComplete();
    }

    @Order(201)
    @Test
    void deletePrivateMessageSequenceIds() {
        Mono<Long> delete =
                messageService.deletePrivateMessageSequenceIds(Set.of(USER_ID_1, USER_ID_2));
        StepVerifier.create(delete)
                .expectNext(2L)
                .as("Deleted count should be 2 since sequence IDs for both users exist")
                .verifyComplete();
        delete = messageService.deletePrivateMessageSequenceIds(Set.of(USER_ID_3));
        StepVerifier.create(delete)
                .expectNext(0L)
                .as("Deleted count should be 0 since user3-related users have been deleted")
                .verifyComplete();
        delete = messageService.deletePrivateMessageSequenceIds(Set.of(USER_ID_1, USER_ID_3));
        StepVerifier.create(delete)
                .expectNext(0L)
                .as("Deleted count should be 0 since user1 and user3 has been deleted")
                .verifyComplete();
        delete = messageService.deletePrivateMessageSequenceIds(Set.of(USER_ID_2, USER_ID_3));
        StepVerifier.create(delete)
                .expectNext(0L)
                .as("Deleted count should be 0 since user2 and user3 has been deleted")
                .verifyComplete();
    }

}