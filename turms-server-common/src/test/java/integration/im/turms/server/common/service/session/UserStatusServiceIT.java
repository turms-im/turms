package integration.im.turms.server.common.service.session;

import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.UserStatusProperties;
import im.turms.server.common.redis.RedisTemplateFactory;
import im.turms.server.common.redis.sharding.ConsistentHashingShardingAlgorithm;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.testing.BaseIntegrationTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static im.turms.server.common.redis.RedisSerializationContextPool.USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;
import static org.assertj.guava.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserStatusServiceIT extends BaseIntegrationTest {

    static final UserStatusService USER_STATUS_SERVICE;

    static final String LOCAL_NODE_ID = "turms-east01";
    static final UserStatus USER_INITIAL_STATUS = UserStatus.DO_NOT_DISTURB;

    static final long USER_1_ID = 1;
    static final DeviceType USER_1_DEVICE = DeviceType.ANDROID;
    static final DeviceType USER_1_DIFF_DEVICE = DeviceType.IOS;
    static final UserStatus USER_1_STATUS_AFTER_UPDATED = UserStatus.BUSY;

    static final long USER_2_ID = 2;
    static final DeviceType USER_2_DEVICE = DeviceType.DESKTOP;

    static final long NON_EXISTING_USER_ID = 999;
    static final DeviceType NON_EXISTING_USER_DEVICE_TYPE = DeviceType.ANDROID;

    static {
        Node node = mock(Node.class);
        when(node.getLocalMemberId()).thenReturn(LOCAL_NODE_ID);

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties())
                .thenReturn(new TurmsProperties().toBuilder()
                        .userStatus(new UserStatusProperties().toBuilder()
                                .cacheUserSessionsStatus(false)
                                .build())
                        .build());
        RedisProperties redisProperties = new RedisProperties();
        redisProperties.setHost(ENV.getRedisHost());
        redisProperties.setPort(ENV.getRedisPort());
        List<ReactiveRedisTemplate<Long, String>> templates =
                RedisTemplateFactory.getTemplates(List.of(redisProperties), USER_SESSIONS_STATUS_SERIALIZATION_CONTEXT);
        USER_STATUS_SERVICE = new UserStatusService(node,
                propertiesManager,
                new ConsistentHashingShardingAlgorithm(),
                templates);
    }

    @Order(0)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForFirstUser() {
        Mono<Boolean> addOnlineDevice =
                USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_1_ID, USER_1_DEVICE, USER_INITIAL_STATUS, Duration.ofSeconds(60));
        StepVerifier
                .create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(1)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForFirstUserWithDifferent() {
        Mono<Boolean> addOnlineDevice =
                USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_1_ID, USER_1_DIFF_DEVICE, USER_INITIAL_STATUS, Duration.ofSeconds(60));
        StepVerifier
                .create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(2)
    @Test
    void addOnlineDeviceIfAbsent_shouldSucceed_ifAbsentForSecondUser() {
        Mono<Boolean> addOnlineDevice =
                USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_2_ID, USER_2_DEVICE, USER_INITIAL_STATUS, Duration.ofSeconds(60));
        StepVerifier
                .create(addOnlineDevice)
                .expectNext(true)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(3)
    @Test
    void addOnlineDeviceIfAbsent_shouldFail_ifPresent() {
        Mono<Boolean> addOnlineDevice =
                USER_STATUS_SERVICE.addOnlineDeviceIfAbsent(USER_1_ID, USER_1_DEVICE, USER_INITIAL_STATUS, Duration.ofSeconds(60));
        StepVerifier
                .create(addOnlineDevice)
                .expectNext(false)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(10)
    @Test
    void getNodeIdByUserIdAndDeviceType_shouldReturnNodeId_forExistingUser() {
        Mono<String> nodeIdMono = USER_STATUS_SERVICE.getNodeIdByUserIdAndDeviceType(USER_1_ID, USER_1_DEVICE);
        StepVerifier
                .create(nodeIdMono)
                .expectNext(LOCAL_NODE_ID)
                .expectComplete()
                .verify();
    }

    @Order(11)
    @Test
    void getNodeIdByUserIdAndDeviceType_shouldReturnEmpty_forNonExistingUser() {
        Mono<String> nodeIdMono = USER_STATUS_SERVICE.getNodeIdByUserIdAndDeviceType(NON_EXISTING_USER_ID, NON_EXISTING_USER_DEVICE_TYPE);
        StepVerifier
                .create(nodeIdMono)
                .expectComplete()
                .verify();
    }

    @Order(20)
    @Test
    void getDeviceAndNodeIdMapByUserId_shouldReturnDeviceAndNodeId_forExistingUser() {
        Mono<Map<DeviceType, String>> deviceAndNodeId = USER_STATUS_SERVICE.getDeviceAndNodeIdMapByUserId(USER_1_ID);
        StepVerifier
                .create(deviceAndNodeId)
                .assertNext(map -> assertThat(map)
                        .containsOnly(entry(USER_1_DEVICE, LOCAL_NODE_ID), entry(USER_1_DIFF_DEVICE, LOCAL_NODE_ID)))
                .expectComplete()
                .verify();
    }

    @Order(21)
    @Test
    void getDeviceAndNodeIdMapByUserId_shouldReturnEmpty_forNonExistingUser() {
        Mono<Map<DeviceType, String>> deviceAndNodeId = USER_STATUS_SERVICE.getDeviceAndNodeIdMapByUserId(NON_EXISTING_USER_ID);
        StepVerifier
                .create(deviceAndNodeId)
                .expectComplete()
                .verify();
    }

    @Order(30)
    @Test
    void getNodeIdAndDeviceMapByUserId_shouldReturnNodeIdAndDeviceMap_forExistingUser() {
        Mono<SetMultimap<String, DeviceType>> nodeIdAndDevices = USER_STATUS_SERVICE.getNodeIdAndDeviceMapByUserId(USER_1_ID);
        StepVerifier
                .create(nodeIdAndDevices)
                .assertNext(map -> assertThat(map)
                        .hasSize(2)
                        .containsValues(USER_1_DEVICE, USER_1_DIFF_DEVICE))
                .expectComplete()
                .verify();
    }

    @Order(31)
    @Test
    void getNodeIdAndDeviceMapByUserId_shouldReturnEmpty_forNonExistingUser() {
        Mono<Map<DeviceType, String>> deviceAndNodeId = USER_STATUS_SERVICE.getDeviceAndNodeIdMapByUserId(NON_EXISTING_USER_ID);
        StepVerifier
                .create(deviceAndNodeId)
                .expectComplete()
                .verify();
    }

    @Order(40)
    @Test
    void updateOnlineUserStatus_shouldReturnTrue_forExistingUser() {
        Mono<Boolean> updateMono = USER_STATUS_SERVICE.updateOnlineUserStatusIfPresent(USER_1_ID, USER_1_STATUS_AFTER_UPDATED);
        StepVerifier
                .create(updateMono)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Order(41)
    @Test
    void updateOnlineUserStatus_shouldReturnFalse_forNonExistingUser() {
        Mono<Boolean> updateMono = USER_STATUS_SERVICE.updateOnlineUserStatusIfPresent(NON_EXISTING_USER_ID, UserStatus.AVAILABLE);
        StepVerifier
                .create(updateMono)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

    @Order(50)
    @Test
    void getUserSessionsStatus_shouldStatusAfterUpdated_forExistingUser() {
        Mono<UserSessionsStatus> statusMono = USER_STATUS_SERVICE.getUserSessionsStatus(USER_1_ID);
        StepVerifier
                .create(statusMono)
                .assertNext(status -> {
                    assertThat(status.getUserStatus())
                            .isEqualTo(USER_1_STATUS_AFTER_UPDATED);
                    assertThat(status.getOnlineDeviceTypeAndNodeIdMap())
                            .containsOnly(entry(USER_1_DEVICE, LOCAL_NODE_ID), entry(USER_1_DIFF_DEVICE, LOCAL_NODE_ID));
                })
                .expectComplete()
                .verify();
    }

    @Order(51)
    @Test
    void getUserSessionsStatus_shouldReturnOffline_forNonExistingUser() {
        Mono<UserSessionsStatus> statusMono = USER_STATUS_SERVICE.getUserSessionsStatus(NON_EXISTING_USER_ID);
        StepVerifier
                .create(statusMono)
                .assertNext(status -> {
                    assertThat(status.getUserStatus()).isEqualTo(UserStatus.OFFLINE);
                    assertThat(status.getOnlineDeviceTypeAndNodeIdMap()).isEmpty();
                })
                .expectComplete()
                .verify();
    }

    @Order(60)
    @Test
    void removeStatusByUserIdAndDeviceTypes_shouldReturnTrue_forExistingUser() {
        Mono<Boolean> removeMono =
                USER_STATUS_SERVICE.removeStatusByUserIdAndDeviceTypes(USER_1_ID, Set.of(USER_1_DEVICE, USER_1_DIFF_DEVICE));
        StepVerifier
                .create(removeMono)
                .expectNext(true)
                .expectComplete()
                .verify();
    }

    @Order(61)
    @Test
    void removeStatusByUserIdAndDeviceTypes_shouldReturnFalse_forExistingUser() {
        Mono<Boolean> removeMono =
                USER_STATUS_SERVICE.removeStatusByUserIdAndDeviceTypes(NON_EXISTING_USER_ID, Set.of(NON_EXISTING_USER_DEVICE_TYPE));
        StepVerifier
                .create(removeMono)
                .expectNext(false)
                .expectComplete()
                .verify();
    }

}