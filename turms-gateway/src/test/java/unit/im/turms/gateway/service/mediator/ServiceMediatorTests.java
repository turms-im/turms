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

package unit.im.turms.gateway.service.mediator;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.TurmsPluginManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.message.InboundRequestService;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.gateway.service.impl.session.UserService;
import im.turms.gateway.service.impl.session.UserSimultaneousLoginService;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.lang.ByteArrayWrapper;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.env.gateway.GatewayProperties;
import im.turms.server.common.property.env.gateway.SessionProperties;
import im.turms.server.common.util.ThrowableUtil;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class ServiceMediatorTests {

    private final int version = 1;
    private final ByteArrayWrapper ip = new ByteArrayWrapper(new byte[]{127, 0, 0, 1});
    private final Long userId = 1L;
    private final DeviceType deviceType = DeviceType.ANDROID;

    @Test
    void constructor_shouldSucceed() {
        ServiceMediator mediator = new ServiceMediator(null, null, null, null, null, null);

        assertThat(mediator).isNotNull();
    }

    // Login

    @Test
    void processLoginRequest_shouldReturnUnsupportedClientVersion_ifIsUnsupportedVersion() {
        ServiceMediator mediator = newServiceMediator(true, true, true, true);
        Mono<UserSession> result = mediator.processLoginRequest(Integer.MAX_VALUE, ip, userId, null, deviceType, null, null, null, null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable, TurmsStatusCode.UNSUPPORTED_CLIENT_VERSION))
                .verify();
    }

    @Test
    void processLoginRequest_shouldReturnForbiddenDeviceType_ifIsForbiddenDeviceType() {
        ServiceMediator mediator = newServiceMediator(true, true, true, true);
        Mono<UserSession> result = mediator.processLoginRequest(version, ip, userId, null, deviceType, null, null, null, null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable, TurmsStatusCode.LOGIN_FROM_FORBIDDEN_DEVICE_TYPE))
                .verify();
    }

    @Test
    void processLoginRequest_shouldReturnUnauthorized_ifIsUnauthorized() {
        ServiceMediator mediator = newServiceMediator(true, true, false, false);
        Mono<UserSession> result = mediator.processLoginRequest(version, ip, userId, null, deviceType, null, null, null, null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable, TurmsStatusCode.LOGIN_AUTHENTICATION_FAILED))
                .verify();
    }

    @Test
    void processLoginRequest_shouldReturnNotActive_ifUserIsNotActive() {
        ServiceMediator mediator = newServiceMediator(true, false, false, false);
        Mono<UserSession> result = mediator.processLoginRequest(version, ip, userId, null, deviceType, null, null, null, null);

        StepVerifier.create(result)
                .expectErrorMatches(throwable -> ThrowableUtil.isStatusCode(throwable, TurmsStatusCode.LOGGING_IN_USER_NOT_ACTIVE))
                .verify();
    }

    @Test
    void processLoginRequest_shouldReturnNotActive_ifUserIsActiveAndAuthenticated() {
        ServiceMediator mediator = newServiceMediator(true, true, true, false);
        Mono<UserSession> result = mediator.processLoginRequest(version, ip, userId, null, deviceType, null, null, null, null);

        StepVerifier.create(result)
                .expectNextCount(1)
                .verifyComplete();
    }

    @Test
    void setLocalUserDeviceOffline_shouldSucceed() {
        ServiceMediator mediator = newServiceMediator();
        Mono<Boolean> result = mediator.setLocalUserDeviceOffline(userId, deviceType, SessionCloseStatus.SERVER_ERROR);

        StepVerifier.create(result)
                .expectNext(true)
                .verifyComplete();
    }

    /**
     * FIXME: The test should belong to SessionService
     */
    @Disabled
    @Test
    void onSessionEstablished_shouldSendSessionNotification_ifIsNotifyClientsOfSessionInfoAfterConnected() {
        ServiceMediator mediator = newServiceMediator();
        UserSessionsManager manager = mock(UserSessionsManager.class);
        when(manager.pushSessionNotification(any(), any()))
                .thenReturn(true);
        mediator.onSessionEstablished(manager, deviceType);

        boolean sessionExists = verify(manager, times(1))
                .pushSessionNotification(eq(deviceType), any());
        assertThat(sessionExists).isTrue();
    }

    @Test
    void processServiceRequest_shouldSucceed() {
        ServiceMediator mediator = newServiceMediator();
        ServiceRequest request = mock(ServiceRequest.class);
        Mono<TurmsNotification> result = mediator.processServiceRequest(request);

        StepVerifier.create(result)
                .verifyComplete();
    }

    @Test
    void processHeartbeatRequest_shouldSucceed() {
        ServiceMediator mediator = newServiceMediator();
        assertThatNoException()
                .isThrownBy(() -> mediator.processHeartbeatRequest(new UserSession(version, userId, deviceType, null)));
    }

    @Test
    void triggerGoOnlinePlugins_shouldSucceed() {
        ServiceMediator mediator = newServiceMediator();
        UserSessionsManager manager = mock(UserSessionsManager.class);
        UserSession session = mock(UserSession.class);
        Mono<Void> result = mediator.triggerGoOnlinePlugins(manager, session);

        StepVerifier.create(result)
                .verifyComplete();
    }

    private ServiceMediator newServiceMediator() {
        return newServiceMediator(true, true, true, false);
    }

    private ServiceMediator newServiceMediator(
            boolean enableAuthentication,
            boolean isActiveAndNotDeleted,
            boolean isAuthenticated,
            boolean isForbiddenDeviceType) {
        Node node = mock(Node.class);
        TurmsProperties properties = new TurmsProperties().toBuilder()
                .gateway(new GatewayProperties().toBuilder()
                        .session(new SessionProperties().toBuilder()
                                .notifyClientsOfSessionInfoAfterConnected(true)
                                .enableAuthentication(enableAuthentication)
                                .build())
                        .build())
                .build();
        when(node.getSharedProperties())
                .thenReturn(properties);

        TurmsPluginManager pluginManager = mock(TurmsPluginManager.class);
        when(pluginManager.isEnabled())
                .thenReturn(true);
        when(pluginManager.getUserAuthenticatorList())
                .thenReturn(Collections.emptyList());
        when(pluginManager.getUserOnlineStatusChangeHandlerList())
                .thenReturn(Collections.emptyList());

        UserService userService = mock(UserService.class);
        when(userService.isActiveAndNotDeleted(any()))
                .thenReturn(Mono.just(isActiveAndNotDeleted));
        when(userService.authenticate(any(), any()))
                .thenReturn(Mono.just(isAuthenticated));

        SessionService sessionService = mock(SessionService.class);
        UserSession userSession = mock(UserSession.class);
        when(sessionService.tryRegisterOnlineUser(anyInt(), any(), any(), any(), any(), any()))
                .thenReturn(Mono.just(userSession));
        when(sessionService.setLocalSessionOfflineByUserIdAndDeviceType(any(), any(), any()))
                .thenReturn(Mono.just(true));

        UserSimultaneousLoginService userSimultaneousLoginService = mock(UserSimultaneousLoginService.class);
        when(userSimultaneousLoginService.isForbiddenDeviceType(any()))
                .thenReturn(isForbiddenDeviceType);

        InboundRequestService inboundRequestService = mock(InboundRequestService.class);
        when(inboundRequestService.processServiceRequest(any()))
                .thenReturn(Mono.empty());
        return new ServiceMediator(node, pluginManager, userService, sessionService, userSimultaneousLoginService, inboundRequestService);
    }

}