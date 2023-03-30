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

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import io.lettuce.core.protocol.LongKeyGenerator;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.UserStatusProperties;
import im.turms.server.common.storage.redis.RedisProperties;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.testing.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
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
    static final int ORDER_GET_NODE_ID_TO_DEVICE_TYPE_MAP_BY_USER_ID = 30;
    static final int ORDER_FETCH_DEVICE_DETAILS = 40;
    static final int ORDER_UPDATE_ONLINE_USER_STATUS = 50;
    static final int ORDER_UPDATE_ONLINE_USERS_TTL = 60;
    static final int ORDER_GET_USER_SESSIONS_STATUS = 70;
    static final int ORDER_REMOVE_STATUS_BY_USER_ID_AND_DEVICE_TYPES = 80;

    static final int HEARTBEAT_TIMEOUT_MILLIS = 10_000;

    static final UserStatusService USER_STATUS_SERVICE;

    static final String LOCAL_NODE_ID = "turms-east01";
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

    static {
        Node node = mock(Node.class);
        when(node.getLocalMemberId()).thenReturn(LOCAL_NODE_ID);

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .userStatus(new UserStatusProperties().toBuilder()
                        .cacheUserSessionsStatus(false)
                        .build())
                .build());
        RedisProperties redisProperties = new RedisProperties().toBuilder()
                .uriList(List.of("redis://%s:%d".formatted(ENV.getRedisHost(), ENV.getRedisPort())))
                .build();
        TurmsRedisClientManager manager =
                new TurmsRedisClientManager(redisProperties, USER_SESSIONS_STATUS_CODEC_CONTEXT);
        USER_STATUS_SERVICE = new UserStatusService(node, propertiesManager, manager);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForFirstUser() {
        Mono<Boolean> addOnlineDevice = USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_1_ID,
                USER_1_DEVICE,
                USER_1_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_MILLIS);
        StepVerifier.create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 1)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForFirstUserWithDifferentDevice() {
        Mono<Boolean> addOnlineDevice = USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_1_ID,
                USER_1_DIFF_DEVICE,
                USER_1_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_MILLIS);
        StepVerifier.create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 2)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForSecondUser() {
        Mono<Boolean> addOnlineDevice = USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_2_ID,
                USER_2_DEVICE,
                USER_2_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_MILLIS);
        StepVerifier.create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_ADD_ONLINE_DEVICE_IF_ABSENT + 3)
    @Test
    void addOnlineDeviceIfAbsent_shouldFail_ifPresent() {
        Mono<Boolean> addOnlineDevice = USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_1_ID,
                USER_1_DEVICE,
                USER_1_DEVICE_DETAILS,
                USER_INITIAL_STATUS,
                HEARTBEAT_TIMEOUT_MILLIS);
        StepVerifier.create(addOnlineDevice)
                .expectNext(false)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(ORDER_GET_NODE_ID_BY_USER_ID_AND_DEVICE_TYPE)
    @Test
    void getNodeIdByUserIdAndDeviceType_shouldReturnNodeId_forExistingUser() {
        Mono<String> nodeIdMono =
                USER_STATUS_SERVICE.getNodeIdByUserIdAndDeviceType(USER_1_ID, USER_1_DEVICE);
        StepVerifier.create(nodeIdMono)
                .expectNext(LOCAL_NODE_ID)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_NODE_ID_BY_USER_ID_AND_DEVICE_TYPE + 1)
    @Test
    void getNodeIdByUserIdAndDeviceType_shouldReturnEmpty_forNonExistingUser() {
        Mono<String> nodeIdMono =
                USER_STATUS_SERVICE.getNodeIdByUserIdAndDeviceType(NON_EXISTING_USER_ID,
                        NON_EXISTING_USER_DEVICE_TYPE);
        StepVerifier.create(nodeIdMono)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_DEVICE_TYPE_TO_NODE_ID_MAP_BY_USER_ID)
    @Test
    void getDeviceTypeToNodeIdMapByUserId_shouldReturnDeviceAndNodeId_forExistingUser() {
        Mono<Map<DeviceType, String>> deviceAndNodeId =
                USER_STATUS_SERVICE.getDeviceTypeToNodeIdMapByUserId(USER_1_ID);
        StepVerifier.create(deviceAndNodeId)
                .assertNext(map -> assertThat(map).containsOnly(entry(USER_1_DEVICE, LOCAL_NODE_ID),
                        entry(USER_1_DIFF_DEVICE, LOCAL_NODE_ID)))
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_DEVICE_TYPE_TO_NODE_ID_MAP_BY_USER_ID + 1)
    @Test
    void getDeviceTypeToNodeIdMapByUserId_shouldReturnEmpty_forNonExistingUser() {
        Mono<Map<DeviceType, String>> deviceAndNodeId =
                USER_STATUS_SERVICE.getDeviceTypeToNodeIdMapByUserId(NON_EXISTING_USER_ID);
        StepVerifier.create(deviceAndNodeId)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_NODE_ID_TO_DEVICE_TYPE_MAP_BY_USER_ID)
    @Test
    void getNodeIdToDeviceTypeMapByUserId_shouldReturnNodeIdToDeviceTypeMap_forExistingUser() {
        Mono<Map<String, Set<DeviceType>>> nodeIdAndDevices =
                USER_STATUS_SERVICE.getNodeIdToDeviceTypeMapByUserId(USER_1_ID);
        StepVerifier.create(nodeIdAndDevices)
                .assertNext(map -> assertThat(map.values()
                        .stream()
                        .findFirst()
                        .get()).containsExactlyInAnyOrder(USER_1_DEVICE, USER_1_DIFF_DEVICE))
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_NODE_ID_TO_DEVICE_TYPE_MAP_BY_USER_ID + 1)
    @Test
    void getNodeIdToDeviceTypeMapByUserId_shouldReturnEmpty_forNonExistingUser() {
        Mono<Map<DeviceType, String>> deviceAndNodeId =
                USER_STATUS_SERVICE.getDeviceTypeToNodeIdMapByUserId(NON_EXISTING_USER_ID);
        StepVerifier.create(deviceAndNodeId)
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
                USER_STATUS_SERVICE.fetchDeviceDetails(userIds, fields);
        StepVerifier.create(mono)
                .expectNext(Map.of(USER_1_ID, USER_1_DEVICE_DETAILS))
                .expectComplete()
                .verify();
    }

    @Order(ORDER_UPDATE_ONLINE_USER_STATUS)
    @Test
    void updateOnlineUserStatus_shouldReturnTrue_forExistingUser() {
        Mono<Boolean> updateMono = USER_STATUS_SERVICE.updateOnlineUserStatusIfPresent(USER_1_ID,
                USER_1_STATUS_AFTER_UPDATED);
        StepVerifier.create(updateMono)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_UPDATE_ONLINE_USER_STATUS + 1)
    @Test
    void updateOnlineUserStatus_shouldReturnFalse_forNonExistingUser() {
        Mono<Boolean> updateMono = USER_STATUS_SERVICE
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
        Mono<Void> updateMono = USER_STATUS_SERVICE.updateOnlineUsersTtl(users, 30);
        StepVerifier.create(updateMono)
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_USER_SESSIONS_STATUS)
    @Test
    void getUserSessionsStatus_shouldStatusAfterUpdated_forExistingUser() {
        Mono<UserSessionsStatus> statusMono = USER_STATUS_SERVICE.getUserSessionsStatus(USER_1_ID);
        StepVerifier.create(statusMono)
                .assertNext(status -> {
                    assertThat(status.userStatus()).isEqualTo(USER_1_STATUS_AFTER_UPDATED);
                    assertThat(status.deviceTypeToNodeId()).containsOnly(
                            entry(USER_1_DEVICE, LOCAL_NODE_ID),
                            entry(USER_1_DIFF_DEVICE, LOCAL_NODE_ID));
                })
                .expectComplete()
                .verify();
    }

    @Order(ORDER_GET_USER_SESSIONS_STATUS + 1)
    @Test
    void getUserSessionsStatus_shouldReturnOffline_forNonExistingUser() {
        Mono<UserSessionsStatus> statusMono =
                USER_STATUS_SERVICE.getUserSessionsStatus(NON_EXISTING_USER_ID);
        StepVerifier.create(statusMono)
                .assertNext(status -> {
                    assertThat(status.userStatus()).isEqualTo(UserStatus.OFFLINE);
                    assertThat(status.deviceTypeToNodeId()).isEmpty();
                })
                .expectComplete()
                .verify();
    }

    @Order(ORDER_REMOVE_STATUS_BY_USER_ID_AND_DEVICE_TYPES)
    @Test
    void removeStatusByUserIdAndDeviceTypes_shouldReturnTrue_forExistingUser() {
        Mono<Boolean> removeMono = USER_STATUS_SERVICE.removeStatusByUserIdAndDeviceTypes(USER_1_ID,
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
                USER_STATUS_SERVICE.removeStatusByUserIdAndDeviceTypes(NON_EXISTING_USER_ID,
                        Set.of(NON_EXISTING_USER_DEVICE_TYPE));
        StepVerifier.create(removeMono)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

}