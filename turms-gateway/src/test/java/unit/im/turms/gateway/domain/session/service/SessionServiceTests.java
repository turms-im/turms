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

package unit.im.turms.gateway.domain.session.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.domain.observation.service.MetricsService;
import im.turms.gateway.domain.session.manager.UserSessionsManager;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.domain.session.service.UserService;
import im.turms.gateway.domain.session.service.UserSimultaneousLoginService;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.request.TurmsRequestTypePool;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.bo.UserDeviceSessionInfo;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.service.SessionLocationService;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.application.TurmsApplicationContext;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.GatewayProperties;
import im.turms.server.common.infra.property.env.gateway.identityaccessmanagement.IdentityAccessManagementProperties;
import im.turms.server.common.infra.property.env.gateway.session.SessionProperties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class SessionServiceTests {

    private static final int VERSION = 1;
    private static final ByteArrayWrapper IP = new ByteArrayWrapper(new byte[]{127, 0, 0, 1});
    private static final Long USER_ID = 1L;
    private static final DeviceType DEVICE_TYPE_1 = DeviceType.ANDROID;
    private static final DeviceType DEVICE_TYPE_2 = DeviceType.DESKTOP;

    @Order(0)
    @Test
    void handleLoginRequest_shouldReturnUnsupportedClientVersion_ifIsUnsupportedVersion() {
        SessionService service = newSessionService(true, true, true, true, false);
        Mono<UserSession> result = service.handleLoginRequest(Integer.MAX_VALUE,
                IP,
                USER_ID,
                null,
                DEVICE_TYPE_1,
                null,
                null,
                null,
                null);
        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable,
                        ResponseStatusCode.UNSUPPORTED_CLIENT_VERSION))
                .verify();
    }

    @Order(1)
    @Test
    void handleLoginRequest_shouldReturnForbiddenDeviceType_ifIsForbiddenDeviceType() {
        SessionService service = newSessionService(true, true, true, true, false);
        Mono<UserSession> result = service.handleLoginRequest(VERSION,
                IP,
                USER_ID,
                null,
                DEVICE_TYPE_1,
                null,
                null,
                null,
                null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable,
                        ResponseStatusCode.LOGIN_FROM_FORBIDDEN_DEVICE_TYPE))
                .verify();
    }

    @Order(2)
    @Test
    void handleLoginRequest_shouldReturnUnauthorized_ifIsUnauthorized() {
        SessionService service = newSessionService(true, true, false, false, false);
        Mono<UserSession> result = service.handleLoginRequest(VERSION,
                IP,
                USER_ID,
                null,
                DEVICE_TYPE_1,
                null,
                null,
                null,
                null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable,
                        ResponseStatusCode.LOGIN_AUTHENTICATION_FAILED))
                .verify();
    }

    @Order(3)
    @Test
    void handleLoginRequest_shouldReturnNotActive_ifUserIsNotActive() {
        SessionService service = newSessionService(true, false, false, false, false);
        Mono<UserSession> result = service.handleLoginRequest(VERSION,
                IP,
                USER_ID,
                null,
                DEVICE_TYPE_1,
                null,
                null,
                null,
                null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable,
                        ResponseStatusCode.LOGGING_IN_USER_NOT_ACTIVE))
                .verify();
    }

    @Order(4)
    @Disabled
    @Test
    void handleLoginRequest_shouldCreateSession_ifUserIsActiveAndAuthenticated() {
        SessionService service = newSessionService(true, true, true, false, false);
        Mono<UserSession> result = service.handleLoginRequest(VERSION,
                IP,
                USER_ID,
                null,
                DEVICE_TYPE_1,
                null,
                null,
                null,
                null);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Order(5)
    @Test
    void handleLoginRequest_shouldCreateSession_ifUserLoggingInOnNewDevice() {
        SessionService service = newSessionService(true, true, true, false, true);
        Mono<UserSession> result = service.handleLoginRequest(VERSION,
                IP,
                USER_ID,
                null,
                DEVICE_TYPE_2,
                null,
                null,
                null,
                null);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Order(6)
    @Disabled
    @Test
    void closeLocalSession_shouldSucceed() {
        SessionService service = newSessionService();
        Mono<Integer> result =
                service.closeLocalSession(USER_ID, DEVICE_TYPE_1, SessionCloseStatus.SERVER_ERROR);

        StepVerifier.create(result)
                .expectNext(1)
                .verifyComplete();
    }

    @Order(7)
    @Disabled
    @Test
    void onSessionEstablished_shouldSendSessionNotification_ifIsNotifyClientsOfSessionInfoAfterConnected() {
        SessionService service = newSessionService();
        UserSessionsManager manager = mock(UserSessionsManager.class);
        when(manager.pushSessionNotification(any(), any())).thenReturn(true);
        service.onSessionEstablished(manager, DEVICE_TYPE_1);

        boolean hasBeenCalled =
                verify(manager, times(1)).pushSessionNotification(eq(DEVICE_TYPE_1), any());
        assertThat(hasBeenCalled).isTrue();
    }

    @Order(8)
    @Test
    void handleHeartbeatRequest_shouldSucceed() {
        SessionService service = newSessionService();
        assertThatNoException()
                .isThrownBy(() -> service.handleHeartbeatUpdateRequest(new UserSession(
                        VERSION,
                        TurmsRequestTypePool.ALL,
                        USER_ID,
                        DEVICE_TYPE_1,
                        null,
                        null)));
    }

    @Order(9)
    @Test
    void invokeGoOnlineHandlers_shouldSucceed() {
        SessionService service = newSessionService();
        UserSessionsManager manager = mock(UserSessionsManager.class);
        UserSession session = mock(UserSession.class);
        Mono<Void> result = service.invokeGoOnlineHandlers(manager, session);

        StepVerifier.create(result)
                .verifyComplete();
    }

    private SessionService newSessionService() {
        return newSessionService(true, true, true, false, false);
    }

    private SessionService newSessionService(
            boolean enableAuthentication,
            boolean isActiveAndNotDeleted,
            boolean isAuthenticated,
            boolean isForbiddenDeviceType,
            boolean hasOnlineDevices) {
        Node node = mock(Node.class);

        TurmsProperties properties = new TurmsProperties().toBuilder()
                .gateway(new GatewayProperties().toBuilder()
                        .session(new SessionProperties().toBuilder()
                                .notifyClientsOfSessionInfoAfterConnected(true)
                                .identityAccessManagement(
                                        new IdentityAccessManagementProperties().toBuilder()
                                                .enabled(enableAuthentication)
                                                .build())
                                .build())
                        .build())
                .build();
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        when(propertiesManager.getGlobalProperties()).thenReturn(properties);
        when(propertiesManager.getLocalProperties()).thenReturn(properties);
        doCallRealMethod().when(propertiesManager)
                .notifyAndAddGlobalPropertiesChangeListener(any());

        PluginManager pluginManager = mock(PluginManager.class);
        when(pluginManager.invokeExtensionPointsSimultaneously(any(), any(), any()))
                .thenReturn(Mono.empty());

        SessionLocationService locationService = mock(SessionLocationService.class);

        UserService userService = mock(UserService.class);
        when(userService.isEnabled()).thenReturn(true);
        when(userService.isActiveAndNotDeleted(any())).thenReturn(Mono.just(isActiveAndNotDeleted));
        when(userService.authenticate(any(), any())).thenReturn(Mono.just(isAuthenticated));

        UserStatusService userStatusService = mock(UserStatusService.class);
        when(userStatusService.updateOnlineUsersTtl(any(), anyInt())).thenReturn(Mono.empty());
        Mono<UserSessionsStatus> sessionsStatusMono = hasOnlineDevices
                ? Mono.just(new UserSessionsStatus(
                        USER_ID,
                        UserStatus.AVAILABLE,
                        Map.of(DEVICE_TYPE_1, new UserDeviceSessionInfo("123456789", 0L, true))))
                : Mono.just(new UserSessionsStatus(USER_ID, UserStatus.OFFLINE, new HashMap<>()));
        when(userStatusService
                .addOnlineDeviceIfAbsent(any(), any(), any(), any(), anyInt(), any(), any()))
                .thenReturn(Mono.just(true));
        when(userStatusService.fetchUserSessionsStatus(any())).thenReturn(sessionsStatusMono);
        when(userStatusService.removeStatusByUserIdAndDeviceTypes(any(), any()))
                .thenReturn(Mono.just(true));

        UserSimultaneousLoginService userSimultaneousLoginService =
                mock(UserSimultaneousLoginService.class);
        when(userSimultaneousLoginService.isForbiddenDeviceType(any()))
                .thenReturn(isForbiddenDeviceType);

        return new SessionService(
                node,
                mock(TurmsApplicationContext.class),
                propertiesManager,
                pluginManager,
                locationService,
                userService,
                userStatusService,
                userSimultaneousLoginService,
                new MetricsService());
    }

}