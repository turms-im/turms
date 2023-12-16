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

package integration.im.turms.server.common.domain.session.service;

import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import io.lettuce.core.protocol.LongKeyGenerator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.session.bo.UserDeviceSessionInfo;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.discovery.DiscoveryService;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.UserStatusProperties;
import im.turms.server.common.storage.redis.RedisProperties;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.testing.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static im.turms.server.common.storage.redis.codec.context.RedisCodecContextPool.USER_SESSIONS_STATUS_CODEC_CONTEXT;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStatusServiceIT extends BaseIntegrationTest {

    static final int ORDER_ADD_ONLINE_DEVICE_IF_ABSENT = 0;
    static final int ORDER_GET_NODE_ID_BY_USER_ID_AND_DEVICE_TYPE = 10;
    static final int ORDER_GET_DEVICE_TYPE_TO_NODE_ID_MAP_BY_USER_ID = 20;
    static final int ORDER_GET_USER_SESSIONS_STATUS_BY_USER_ID = 30;
    static final int ORDER_FETCH_DEVICE_DETAILS = 40;
    static final int ORDER_UPDATE_ONLINE_USER_STATUS = 50;
    static final int ORDER_UPDATE_ONLINE_USERS_TTL = 60;
    static final int ORDER_GET_USER_SESSIONS_STATUS = 70;
    static final int ORDER_REMOVE_STATUS_BY_USER_ID_AND_DEVICE_TYPES = 80;

    static final int HEARTBEAT_TIMEOUT_SECONDS = 10_00;

    static final String LOCAL_NODE_ID = "turms-east01";
    static final String NEW_LOCAL_NODE_ID = "turms-west01";
    static final UserStatus USER_INITIAL_STATUS = UserStatus.DO_NOT_DISTURB;

    static final long USER_1_ID = 1;
    static final DeviceType USER_1_DEVICE = DeviceType.ANDROID;
    static final Map<String, String> USER_1_DEVICE_DETAILS =
            Map.of("f", "fake-fcm-token", "a", "fake-apns-token");
    static final DeviceType USER_1_DIFF_DEVICE = DeviceType.IOS;
    static final UserStatus USER_1_STATUS_AFTER_UPDATED = UserStatus.BUSY;

    static final long USER_2_ID = 2;
    static final DeviceType USER_2_DEVICE = DeviceType.DESKTOP;
    static final Map<String, String> USER_2_DEVICE_DETAILS = null;

    static final long NON_EXISTING_USER_ID = 999;
    static final DeviceType NON_EXISTING_USER_DEVICE_TYPE = DeviceType.ANDROID;

    static UserStatusService userStatusService;

    @BeforeAll
    static void setup() {
        setupTestEnvironment();

        Node node = mock(Node.class);
        when(node.getLocalMemberId()).thenReturn(LOCAL_NODE_ID);
        DiscoveryService discoveryService = mock(DiscoveryService.class);
        when(discoveryService.checkIfMemberExists(anyString())).thenReturn(Mono.just(true));
        when(node.getDiscoveryService()).thenReturn(discoveryService);

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .userStatus(new UserStatusProperties().toBuilder()
                        .cacheUserSessionsStatus(false)
                        .build())
                .build());
        RedisProperties redisProperties = new RedisProperties().toBuilder()
                .uriList(List.of(testEnvironmentManager.getRedisUri()))
                .build();
        TurmsRedisClientManager manager =
                new TurmsRedisClientManager(redisProperties, USER_SESSIONS_STATUS_CODEC_CONTEXT);
        userStatusService = new UserStatusService(node, propertiesManager, manager);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForFirstUser() {
        Mono<Boolean> addOnlineDevice = userStatusService.addOnlineDeviceIfAbsent(USER_1_ID,
                USER_1_DEVICE,
                USER_1_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_SECONDS,
                null,
                null);
        StepVerifier.create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 1)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForFirstUserWithDifferentDevice() {
        Mono<Boolean> addOnlineDevice = userStatusService.addOnlineDeviceIfAbsent(USER_1_ID,
                USER_1_DIFF_DEVICE,
                USER_1_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_SECONDS,
                null,
                null);
        StepVerifier.create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 2)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForSecondUser() {
        Mono<Boolean> addOnlineDevice = userStatusService.addOnlineDeviceIfAbsent(USER_2_ID,
                USER_2_DEVICE,
                USER_2_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_SECONDS,
                null,
                null);
        StepVerifier.create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 3)
    @Test
    void addOnlineDeviceIfAbsent_shouldFail_ifPresent() {
        Mono<Boolean> addOnlineDevice = userStatusService.addOnlineDeviceIfAbsent(USER_1_ID,
                USER_1_DEVICE,
                USER_1_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_SECONDS,
                null,
                null);
        StepVerifier.create(addOnlineDevice)
                .expectNext(false)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 4)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifPresentButMatchedExpectedInfoProvided() {
        Mono<Boolean> addOnlineDeviceIfAbsent = userStatusService.fetchUserSessionsStatus(USER_1_ID)
                .flatMap(sessionsStatus -> {
                    UserDeviceSessionInfo sessionInfo = sessionsStatus.getDeviceTypeToSessionInfo()
                            .values()
                            .iterator()
                            .next();
                    String nodeId = sessionInfo.getNodeId();
                    Long heartbeatTimestampSeconds = sessionInfo.getHeartbeatTimestampSeconds();
                    byte[] newLocalNodeIdBytes =
                            NEW_LOCAL_NODE_ID.getBytes(StandardCharsets.US_ASCII);
                    ByteBuf newLocalNodeIdBuffer = Unpooled.directBuffer()
                            .writeBytes(newLocalNodeIdBytes);
                    return userStatusService.addOnlineDeviceIfAbsent(newLocalNodeIdBuffer,
                            USER_1_ID,
                            USER_1_DEVICE,
                            USER_1_DEVICE_DETAILS,
                            USER_INITIAL_STATUS,
                            HEARTBEAT_TIMEOUT_SECONDS,
                            nodeId,
                            heartbeatTimestampSeconds);
                });
        StepVerifier.create(addOnlineDeviceIfAbsent)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);

        Mono<UserSessionsStatus> userSessionsStatusMono =
                userStatusService.fetchUserSessionsStatus(USER_1_ID);
        StepVerifier.create(userSessionsStatusMono)
                .expectNextMatches(sessionsStatus -> {
                    Map<DeviceType, UserDeviceSessionInfo> deviceTypeToSessionInfo =
                            sessionsStatus.getDeviceTypeToSessionInfo();
                    Collection<UserDeviceSessionInfo> sessionInfos =
                            deviceTypeToSessionInfo.values();

                    assertThat(deviceTypeToSessionInfo.keySet())
                            .containsExactlyInAnyOrder(USER_1_DEVICE, USER_1_DIFF_DEVICE);
                    assertThat(sessionInfos).hasSize(2);
                    assertThat(sessionInfos.stream()
                            .filter(UserDeviceSessionInfo::isActive)
                            .count()).isEqualTo(2);
                    assertThat(sessionInfos.stream()
                            .map(UserDeviceSessionInfo::getNodeId)
                            .toList()).containsExactlyInAnyOrder(LOCAL_NODE_ID, NEW_LOCAL_NODE_ID);
                    return true;
                })
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 4)
    @Test
    void addOnlineDeviceIfAbsent_shouldFail_ifPresentAndMismatchedExpectedInfoProvided() {
        byte[] newLocalNodeIdBytes = NEW_LOCAL_NODE_ID.getBytes(StandardCharsets.US_ASCII);
        ByteBuf newLocalNodeIdBuffer = Unpooled.directBuffer()
                .writeBytes(newLocalNodeIdBytes);
        Mono<Boolean> addOnlineDeviceIfAbsent =
                userStatusService.addOnlineDeviceIfAbsent(newLocalNodeIdBuffer,
                        USER_1_ID,
                        USER_1_DEVICE,
                        USER_1_DEVICE_DETAILS,
                        USER_INITIAL_STATUS,
                        HEARTBEAT_TIMEOUT_SECONDS,
                        "a-mismatched-node-id",
                        123456789L);
        StepVerifier.create(addOnlineDeviceIfAbsent)
                .expectNext(false)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_GET_NODE_ID_BY_USER_ID_AND_DEVICE_TYPE)
    @Test
    void getNodeIdByUserIdAndDeviceType_shouldReturnNodeId_forExistingUser() {
        Mono<String> nodeIdMono =
                userStatusService.getNodeIdByUserIdAndDeviceType(USER_1_ID, USER_1_DEVICE);
        StepVerifier.create(nodeIdMono)
                .expectNext(NEW_LOCAL_NODE_ID)
                .expectComplete()
                .verify();

        nodeIdMono =
                userStatusService.getNodeIdByUserIdAndDeviceType(USER_1_ID, USER_1_DIFF_DEVICE);
        StepVerifier.create(nodeIdMono)
                .expectNext(LOCAL_NODE_ID)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_NODE_ID_BY_USER_ID_AND_DEVICE_TYPE + 1)
    @Test
    void getNodeIdByUserIdAndDeviceType_shouldReturnEmpty_forNonExistingUser() {
        Mono<String> nodeIdMono =
                userStatusService.getNodeIdByUserIdAndDeviceType(NON_EXISTING_USER_ID,
                        NON_EXISTING_USER_DEVICE_TYPE);
        StepVerifier.create(nodeIdMono)
                .expectComplete()
                .verify();
    }

    private void validate(
            UserSessionsStatus sessionsStatus,
            Map<DeviceType, String> deviceTypeToNodeId) {
        Map<DeviceType, UserDeviceSessionInfo> deviceTypeToSessionInfo =
                sessionsStatus.getDeviceTypeToSessionInfo();
        for (Map.Entry<DeviceType, String> entry : deviceTypeToNodeId.entrySet()) {
            DeviceType deviceType = entry.getKey();
            String expectedNodeId = entry.getValue();
            UserDeviceSessionInfo sessionInfo = deviceTypeToSessionInfo.get(deviceType);

            assertThat(sessionInfo.isActive()).isTrue();
            assertThat(sessionInfo.getNodeId()).isEqualTo(expectedNodeId);
        }
    }

    @Order(ORDER_GET_DEVICE_TYPE_TO_NODE_ID_MAP_BY_USER_ID)
    @Test
    void getUserSessionsStatus_shouldReturnDeviceAndNodeId_forExistingUser() {
        Mono<UserSessionsStatus> sessionsStatusMono =
                userStatusService.getUserSessionsStatus(USER_1_ID);
        StepVerifier.create(sessionsStatusMono)
                .assertNext(sessionsStatus -> validate(sessionsStatus,
                        Map.of(USER_1_DEVICE,
                                NEW_LOCAL_NODE_ID,
                                USER_1_DIFF_DEVICE,
                                LOCAL_NODE_ID)))
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_DEVICE_TYPE_TO_NODE_ID_MAP_BY_USER_ID + 1)
    @Test
    void getUserSessionsStatus_shouldReturnEmpty_forNonExistingUser() {
        Mono<UserSessionsStatus> sessionsStatusMono =
                userStatusService.getUserSessionsStatus(NON_EXISTING_USER_ID);
        StepVerifier.create(sessionsStatusMono)
                .expectNextMatches(
                        sessionsStatus -> sessionsStatus.getUserStatus() == UserStatus.OFFLINE)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_USER_SESSIONS_STATUS_BY_USER_ID)
    @Test
    void getUserSessionsStatus_shouldContainDevices_forExistingUser() {
        Mono<UserSessionsStatus> sessionsStatusMono =
                userStatusService.getUserSessionsStatus(USER_1_ID);
        StepVerifier.create(sessionsStatusMono)
                .assertNext(sessionsStatus -> {
                    Set<DeviceType> deviceTypes = sessionsStatus.getDeviceTypeToSessionInfo()
                            .entrySet()
                            .stream()
                            .filter(entry -> entry.getValue()
                                    .isActive())
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toSet());

                    assertThat(deviceTypes).containsExactlyInAnyOrder(USER_1_DEVICE,
                            USER_1_DIFF_DEVICE);
                })
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_USER_SESSIONS_STATUS_BY_USER_ID + 1)
    @Test
    void getUserSessionsStatus_shouldReturnOfflineStatus_forNonExistingUser() {
        Mono<UserSessionsStatus> sessionsStatusMono =
                userStatusService.getUserSessionsStatus(NON_EXISTING_USER_ID);
        StepVerifier.create(sessionsStatusMono)
                .expectNextMatches(sessionsStatus -> sessionsStatus.getActiveNodeIds()
                        .isEmpty()
                        && sessionsStatus.getUserStatus() == UserStatus.OFFLINE)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_FETCH_DEVICE_DETAILS)
    @Test
    void fetchDeviceDetails_shouldEqual() {
        Set<Long> userIds = Set.of(USER_1_ID, USER_2_ID);
        List<String> fields = USER_1_DEVICE_DETAILS.keySet()
                .stream()
                .toList();
        Mono<Map<Long, Map<String, String>>> mono =
                userStatusService.fetchDeviceDetails(userIds, fields);
        StepVerifier.create(mono)
                .expectNext(Map.of(USER_1_ID, USER_1_DEVICE_DETAILS))
                .expectComplete()
                .verify();
    }

    @Order(ORDER_UPDATE_ONLINE_USER_STATUS)
    @Test
    void updateOnlineUserStatus_shouldReturnTrue_forExistingUser() {
        Mono<Boolean> updateMono = userStatusService.updateOnlineUserStatusIfPresent(USER_1_ID,
                USER_1_STATUS_AFTER_UPDATED);
        StepVerifier.create(updateMono)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_UPDATE_ONLINE_USER_STATUS + 1)
    @Test
    void updateOnlineUserStatus_shouldReturnFalse_forNonExistingUser() {
        Mono<Boolean> updateMono = userStatusService
                .updateOnlineUserStatusIfPresent(NON_EXISTING_USER_ID, UserStatus.AVAILABLE);
        StepVerifier.create(updateMono)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_UPDATE_ONLINE_USERS_TTL)
    @Test
    void updateOnlineUsersTtl_shouldReturnNonExistingUserId() {
        List<Long> userIds = List.of(USER_1_ID, USER_2_ID, NON_EXISTING_USER_ID);
        Iterator<Long> iterator = userIds.iterator();
        LongKeyGenerator users = new LongKeyGenerator() {
            @Override
            public int estimatedSize() {
                return userIds.size();
            }

            @Override
            public long next() {
                if (iterator.hasNext()) {
                    return iterator.next();
                }
                return -1;
            }
        };
        Mono<Set<Long>> updateMono = userStatusService.updateOnlineUsersTtl(users, 30);
        StepVerifier.create(updateMono)
                .expectNext(Set.of(NON_EXISTING_USER_ID))
                .expectComplete()
                .verify();
    }

    @Order(ORDER_UPDATE_ONLINE_USERS_TTL)
    @Test
    void updateOnlineUsersTtl_shouldReturnEmpty_forExistentUser() {
        List<Long> userIds = List.of(USER_1_ID);
        Iterator<Long> iterator = userIds.iterator();
        LongKeyGenerator users = new LongKeyGenerator() {
            @Override
            public int estimatedSize() {
                return userIds.size();
            }

            @Override
            public long next() {
                if (iterator.hasNext()) {
                    return iterator.next();
                }
                return -1;
            }
        };
        Mono<Set<Long>> updateMono = userStatusService.updateOnlineUsersTtl(users, 30);
        StepVerifier.create(updateMono)
                .expectNext(Collections.emptySet())
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_USER_SESSIONS_STATUS)
    @Test
    void getUserSessionsStatus_shouldStatusAfterUpdated_forExistingUser() {
        Mono<UserSessionsStatus> statusMono = userStatusService.getUserSessionsStatus(USER_1_ID);
        StepVerifier.create(statusMono)
                .assertNext(status -> {
                    assertThat(status.getUserStatus()).isEqualTo(USER_1_STATUS_AFTER_UPDATED);
                    validate(status,
                            Map.of(USER_1_DEVICE,
                                    NEW_LOCAL_NODE_ID,
                                    USER_1_DIFF_DEVICE,
                                    LOCAL_NODE_ID));
                })
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_USER_SESSIONS_STATUS + 1)
    @Test
    void getUserSessionsStatus_shouldReturnOffline_forNonExistingUser() {
        Mono<UserSessionsStatus> statusMono =
                userStatusService.getUserSessionsStatus(NON_EXISTING_USER_ID);
        StepVerifier.create(statusMono)
                .assertNext(status -> {
                    assertThat(status.getUserStatus()).isEqualTo(UserStatus.OFFLINE);
                    assertThat(status.getDeviceTypeToSessionInfo()).isEmpty();
                })
                .expectComplete()
                .verify();
    }

    @Order(ORDER_REMOVE_STATUS_BY_USER_ID_AND_DEVICE_TYPES)
    @Test
    void removeStatusByUserIdAndDeviceTypes_shouldReturnTrue_forExistingUser() {
        Mono<Boolean> removeMono = userStatusService.removeStatusByUserIdAndDeviceTypes(USER_1_ID,
                Set.of(USER_1_DEVICE, USER_1_DIFF_DEVICE));
        StepVerifier.create(removeMono)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_REMOVE_STATUS_BY_USER_ID_AND_DEVICE_TYPES + 1)
    @Test
    void removeStatusByUserIdAndDeviceTypes_shouldReturnFalse_forExistingUser() {
        Mono<Boolean> removeMono =
                userStatusService.removeStatusByUserIdAndDeviceTypes(NON_EXISTING_USER_ID,
                        Set.of(NON_EXISTING_USER_DEVICE_TYPE));
        StepVerifier.create(removeMono)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

}