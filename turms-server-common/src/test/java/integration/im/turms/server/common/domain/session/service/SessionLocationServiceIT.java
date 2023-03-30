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

import java.util.Date;
import java.util.List;
import java.util.function.Consumer;

import io.lettuce.core.GeoCoordinates;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.mockito.stubbing.Answer;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.location.LocationProperties;
import im.turms.server.common.infra.property.env.common.location.NearbyUserRequestProperties;
import im.turms.server.common.storage.redis.RedisProperties;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContextPool;
import im.turms.server.common.testing.BaseIntegrationTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionLocationServiceIT extends BaseIntegrationTest {

    static final SessionLocationService SESSION_LOCATION_SERVICE;

    static final int METERS_PER_LATITUDE_DEGREE = 111_320;

    static final long USER_1_ID = 1;
    static final DeviceType USER_1_DEVICE = DeviceType.ANDROID;
    static final Location USER_1_COORDINATES_1 = new Location(10, 10);
    static final Location USER_1_COORDINATES_2 = new Location(10, 20);

    static final long USER_2_ID = 2;
    static final DeviceType USER_2_DEVICE = DeviceType.ANDROID;
    static final Location USER_2_COORDINATES = new Location(10, 30);

    static final long USER_3_ID = 3;
    static final DeviceType USER_3_DEVICE = DeviceType.ANDROID;
    static final Location USER_3_COORDINATES = new Location(10, 40);

    static final long NONEXISTENT_USER_ID = 99999;
    static final DeviceType NONEXISTENT_USER_DEVICE = DeviceType.ANDROID;

    static {
        PluginManager pluginManager = mock(PluginManager.class);
        when(pluginManager.isEnabled()).thenReturn(false);

        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getLocalProperties()).thenReturn(new TurmsProperties().toBuilder()
                .location(new LocationProperties().toBuilder()
                        .enabled(true)
                        .treatUserIdAndDeviceTypeAsUniqueUser(true)
                        .build())
                .build());
        doAnswer((Answer<Void>) invocation -> {
            Consumer<TurmsProperties> listener = invocation.getArgument(0);
            listener.accept(new TurmsProperties().toBuilder()
                    .location(new LocationProperties().toBuilder()
                            .nearbyUserRequest(new NearbyUserRequestProperties().toBuilder()
                                    .maxDistanceMeters(Integer.MAX_VALUE)
                                    .build())
                            .build())
                    .build());
            return null;
        }).when(propertiesManager)
                .notifyAndAddGlobalPropertiesChangeListener(any());

        RedisProperties redisProperties = new RedisProperties().toBuilder()
                .uriList(List.of("redis://%s:%d".formatted(ENV.getRedisHost(), ENV.getRedisPort())))
                .build();
        TurmsRedisClientManager manager = new TurmsRedisClientManager(
                redisProperties,
                RedisCodecContextPool.GEO_USER_SESSION_ID_CODEC_CONTEXT);
        SESSION_LOCATION_SERVICE = new SessionLocationService(propertiesManager, manager);
    }

    @Order(0)
    @Test
    void upsertUserLocation_shouldInsert_ifNotExists() {
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.upsertUserLocation(USER_1_ID,
                        USER_1_DEVICE,
                        new Date(),
                        USER_1_COORDINATES_1.longitude(),
                        USER_1_COORDINATES_1.latitude()))
                .as("The location of user "
                        + USER_1_ID
                        + " should be inserted")
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.upsertUserLocation(USER_2_ID,
                        USER_2_DEVICE,
                        new Date(),
                        USER_2_COORDINATES.longitude(),
                        USER_2_COORDINATES.latitude()))
                .as("The location of user "
                        + USER_2_ID
                        + " should be inserted")
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.upsertUserLocation(USER_3_ID,
                        USER_3_DEVICE,
                        new Date(),
                        USER_3_COORDINATES.longitude(),
                        USER_3_COORDINATES.latitude()))
                .as("The location of user "
                        + USER_3_ID
                        + " should be inserted")
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(1)
    @Test
    void upsertUserLocation_shouldUpdate_ifExists() {
        Mono<Void> upsertUserLocation = SESSION_LOCATION_SERVICE.upsertUserLocation(USER_1_ID,
                USER_1_DEVICE,
                new Date(),
                USER_1_COORDINATES_2.longitude(),
                USER_1_COORDINATES_2.latitude());
        StepVerifier.create(upsertUserLocation)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(10)
    @Test
    void getUserLocation_shouldGet_ifExists() {
        Mono<GeoCoordinates> getUserLocation =
                SESSION_LOCATION_SERVICE.getUserLocation(USER_1_ID, USER_1_DEVICE);
        StepVerifier.create(getUserLocation)
                .expectNextMatches(coordinates -> {
                    assertThat(coordinates.getX()
                            .intValue()).isEqualTo((int) USER_1_COORDINATES_2.longitude());
                    assertThat(coordinates.getY()
                            .intValue()).isEqualTo((int) USER_1_COORDINATES_2.latitude());
                    return true;
                })
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(11)
    @Test
    void getUserLocation_shouldComplete_ifNotExists() {
        Mono<GeoCoordinates> getUserLocation = SESSION_LOCATION_SERVICE
                .getUserLocation(NONEXISTENT_USER_ID, NONEXISTENT_USER_DEVICE);
        StepVerifier.create(getUserLocation)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(20)
    @Test
    void queryNearbyUsers_shouldGetNearbyUsers() {
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.queryNearbyUsers(USER_1_ID,
                        USER_1_DEVICE,
                        (short) 100,
                        15 * METERS_PER_LATITUDE_DEGREE,
                        true,
                        true))
                .expectNextMatches(geo -> geo.getMember()
                        .equals(new UserSessionId(USER_2_ID, USER_2_DEVICE)))
                .as("Test queryNearestUserSessionIds for user "
                        + USER_1_ID)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.queryNearbyUsers(USER_2_ID,
                        USER_2_DEVICE,
                        (short) 100,
                        15 * METERS_PER_LATITUDE_DEGREE,
                        true,
                        true))
                .expectNextMatches(geo -> List
                        .of(new UserSessionId(USER_1_ID, USER_1_DEVICE),
                                new UserSessionId(USER_3_ID, USER_3_DEVICE))
                        .contains(geo.getMember()))
                .expectNextMatches(geo -> List
                        .of(new UserSessionId(USER_1_ID, USER_1_DEVICE),
                                new UserSessionId(USER_3_ID, USER_3_DEVICE))
                        .contains(geo.getMember()))
                .as("Test queryNearestUserSessionIds for user "
                        + USER_2_ID)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.queryNearbyUsers(NONEXISTENT_USER_ID,
                        NONEXISTENT_USER_DEVICE,
                        (short) 100,
                        15 * METERS_PER_LATITUDE_DEGREE,
                        true,
                        true))
                .as("Test queryNearestUserSessionIds for user "
                        + NONEXISTENT_USER_ID)
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(30)
    @Test
    void removeUserLocation_shouldSucceed_ifExists() {
        StepVerifier.create(SESSION_LOCATION_SERVICE.removeUserLocation(USER_1_ID, USER_1_DEVICE))
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

    @Order(31)
    @Test
    void removeUserLocation_shouldSucceed_ifNotExists() {
        StepVerifier
                .create(SESSION_LOCATION_SERVICE.removeUserLocation(NONEXISTENT_USER_ID,
                        NONEXISTENT_USER_DEVICE))
                .expectComplete()
                .verify(DEFAULT_IO_TIMEOUT);
    }

}