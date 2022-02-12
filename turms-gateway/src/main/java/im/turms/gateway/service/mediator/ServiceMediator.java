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

package im.turms.gateway.service.mediator;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.UserAuthenticator;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.gateway.pojo.bo.login.UserLoginInfo;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.message.InboundRequestService;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.gateway.service.impl.session.UserService;
import im.turms.gateway.service.impl.session.UserSimultaneousLoginService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.lang.ByteArrayWrapper;
import im.turms.server.common.plugin.PluginManager;
import im.turms.server.common.plugin.SequentialExtensionPointInvoker;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * A mediator between the underlying technical implementation (i.e. the TCP/UDP/WebSocket access layer)
 * and the implementation of business logic (i.e. the service layer)
 *
 * @author James Chen
 */
@Component
public class ServiceMediator {

    private static final long ADMIN_ID = 0;

    private final Node node;
    private final PluginManager pluginManager;
    private final UserService userService;
    private final SessionService sessionService;
    private final UserSimultaneousLoginService userSimultaneousLoginService;
    private final InboundRequestService inboundRequestService;

    public ServiceMediator(
            Node node,
            PluginManager pluginManager,
            UserService userService,
            SessionService sessionService,
            UserSimultaneousLoginService userSimultaneousLoginService,
            InboundRequestService inboundRequestService) {
        this.node = node;
        this.pluginManager = pluginManager;
        this.userService = userService;
        this.sessionService = sessionService;
        this.userSimultaneousLoginService = userSimultaneousLoginService;
        this.inboundRequestService = inboundRequestService;
    }

    // Login

    public Mono<UserSession> processLoginRequest(
            int version,
            @NotNull ByteArrayWrapper ip,
            @NotNull Long userId,
            @Nullable String password,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Point position,
            @Nullable String ipStr) {
        if (version != 1) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.UNSUPPORTED_CLIENT_VERSION, "The supported versions are: 1"));
        }
        if (userSimultaneousLoginService.isForbiddenDeviceType(deviceType)) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.LOGIN_FROM_FORBIDDEN_DEVICE_TYPE));
        }
        return authenticate(version, userId, password, deviceType, deviceDetails, userStatus, position, ipStr)
                .flatMap(statusCode -> statusCode == TurmsStatusCode.OK
                        ? sessionService.tryRegisterOnlineUser(version, ip, userId, deviceType, deviceDetails, userStatus, position)
                        : Mono.error(TurmsBusinessException.get(statusCode)));
    }

    // Disconnect

    /**
     * @return true if the user was online
     */
    public Mono<Boolean> setLocalUserDeviceOffline(Long userId, DeviceType deviceType, SessionCloseStatus closeStatus) {
        CloseReason closeReason = CloseReason.get(closeStatus);
        return sessionService.setLocalSessionOfflineByUserIdAndDeviceType(userId, deviceType, closeReason);
    }

    public Mono<Boolean> setLocalUserDeviceOffline(Long userId, DeviceType deviceType, CloseReason closeReason) {
        return sessionService.setLocalSessionOfflineByUserIdAndDeviceType(userId, deviceType, closeReason);
    }

    public Mono<Boolean> authAndSetLocalUserDeviceOffline(Long userId, DeviceType deviceType, CloseReason closeReason, int sessionId) {
        return sessionService.authAndSetLocalSessionOfflineByUserIdAndDeviceType(userId, deviceType, closeReason, sessionId);
    }

    // Session

    public UserSessionsManager getUserSessionsManager(Long userId) {
        return sessionService.getUserSessionsManager(userId);
    }

    public void onSessionEstablished(UserSessionsManager userSessionsManager, DeviceType deviceType) {
        sessionService.onSessionEstablished(userSessionsManager, deviceType);
    }

    // Request

    public Mono<TurmsNotification> processServiceRequest(ServiceRequest serviceRequest) {
        return inboundRequestService.processServiceRequest(serviceRequest);
    }

    public void processHeartbeatRequest(UserSession session) {
        inboundRequestService.processHeartbeatRequest(session);
    }

    public UserSession authAndProcessHeartbeatRequest(long userId, DeviceType deviceType, int sessionId) {
        return sessionService.authAndUpdateHeartbeatTimestamp(userId, deviceType, sessionId);
    }

    // Plugin

    public Mono<Void> triggerGoOnlinePlugins(@NotNull UserSessionsManager userSessionsManager, @NotNull UserSession userSession) {
        return pluginManager.invokeExtensionPoints(UserOnlineStatusChangeHandler.class, "goOnline",
                handler -> handler.goOnline(userSessionsManager, userSession));
    }

    // Internal implementation

    /**
     * @return OK, LOGIN_AUTHENTICATION_FAILED, LOGGING_IN_USER_NOT_ACTIVE
     */
    private Mono<TurmsStatusCode> authenticate(
            int version,
            @NotNull Long userId,
            @Nullable String password,
            @NotNull DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            @Nullable Point position,
            @Nullable String ip) {
        if (userId == ADMIN_ID) {
            return Mono.just(TurmsStatusCode.LOGIN_AUTHENTICATION_FAILED);
        }
        boolean enableAuthentication = node.getSharedProperties().getGateway().getSession().isEnableAuthentication();
        if (!enableAuthentication) {
            return Mono.just(TurmsStatusCode.OK);
        }
        if (pluginManager.hasRunningExtensions(UserAuthenticator.class)) {
            UserLoginInfo userLoginInfo = new UserLoginInfo(
                    version,
                    userId,
                    password,
                    deviceType,
                    deviceDetails,
                    userStatus,
                    position,
                    ip);
            Mono<TurmsStatusCode> authenticate = pluginManager.invokeExtensionPointsSequentially(
                            UserAuthenticator.class,
                            "authenticate",
                            (SequentialExtensionPointInvoker<UserAuthenticator, Boolean>)
                                    (authenticator, pre) -> pre.switchIfEmpty(Mono.defer(() -> authenticator.authenticate(userLoginInfo))))
                    .map(authenticated -> authenticated ? TurmsStatusCode.OK : TurmsStatusCode.LOGIN_AUTHENTICATION_FAILED);
            return authenticate
                    .switchIfEmpty(authenticate0(userId, password));
        }
        return authenticate0(userId, password);
    }

    /**
     * @return OK, AUTHENTICATION_FAILED, LOGGING_IN_USER_NOT_ACTIVE
     */
    private Mono<TurmsStatusCode> authenticate0(
            @NotNull Long userId,
            @Nullable String password) {
        return userService.isActiveAndNotDeleted(userId)
                .flatMap(isActiveAndNotDeleted -> isActiveAndNotDeleted
                        ? userService.authenticate(userId, password)
                        .map(authenticated -> authenticated ? TurmsStatusCode.OK : TurmsStatusCode.LOGIN_AUTHENTICATION_FAILED)
                        : Mono.just(TurmsStatusCode.LOGGING_IN_USER_NOT_ACTIVE));
    }

}