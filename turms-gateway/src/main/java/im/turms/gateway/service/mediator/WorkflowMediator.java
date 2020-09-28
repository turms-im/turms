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
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.UserAuthenticator;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.gateway.pojo.bo.login.UserLoginInfo;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.impl.*;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.dto.ServiceRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.CloseStatus;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A mediator between the underlying technical implementation (i.e. the UDP/WebSocket access layer)
 * and the implementation of business logic (i.e. the service layer)
 *
 * @author James Chen
 */
@Component
@Log4j2
public class WorkflowMediator {

    private final Node node;
    private final TurmsPluginManager turmsPluginManager;
    private final UserService userService;
    private final ReasonCacheService reasonCacheService;
    private final SessionService sessionService;
    private final UserSimultaneousLoginService userSimultaneousLoginService;
    private final InboundRequestService inboundRequestService;

    public WorkflowMediator(
            Node node,
            TurmsPluginManager turmsPluginManager,
            UserService userService,
            ReasonCacheService reasonCacheService,
            SessionService sessionService,
            UserSimultaneousLoginService userSimultaneousLoginService,
            InboundRequestService inboundRequestService) {
        this.node = node;
        this.turmsPluginManager = turmsPluginManager;
        this.userService = userService;
        this.reasonCacheService = reasonCacheService;
        this.sessionService = sessionService;
        this.userSimultaneousLoginService = userSimultaneousLoginService;
        this.inboundRequestService = inboundRequestService;
    }

    // Login

    public Mono<TurmsStatusCode> processLoginRequest(
            @NotNull Long userId,
            @Nullable String password,
            @NotNull DeviceType deviceType,
            @Nullable UserStatus userStatus,
            @Nullable Point userLocation,
            @Nullable String ip,
            @Nullable Map<String, String> deviceDetails) {
        if (userSimultaneousLoginService.isForbiddenDeviceType(deviceType)) {
            return Mono.just(TurmsStatusCode.FORBIDDEN_DEVICE_TYPE);
        }
        return authenticate(userId, password, deviceType, userStatus, userLocation, ip, deviceDetails)
                .flatMap(statusCode -> statusCode == TurmsStatusCode.OK
                        ? sessionService.tryRegisterOnlineUser(userId, deviceType, userStatus, userLocation, ip, deviceDetails)
                        : Mono.just(statusCode));
    }

    /**
     * @return true if the reason has been cached
     */
    public Mono<Boolean> rejectLoginRequest(
            @NotNull TurmsStatusCode statusCode,
            @Nullable Long userId,
            @Nullable DeviceType deviceType,
            @Nullable Long requestId) {
        return reasonCacheService.shouldCacheLoginFailureReason(userId, deviceType, requestId)
                ? reasonCacheService.cacheLoginFailureReason(userId, deviceType, requestId, statusCode)
                : Mono.just(false);
    }

    // Disconnect

    /**
     * @return true if the user was online
     */
    public Mono<Boolean> setLocalUserDeviceOffline(Long userId, DeviceType deviceType, CloseStatus closeStatus) {
        return sessionService.setLocalSessionOfflineByUserIdAndDeviceType(userId, deviceType, closeStatus);
    }

    public Mono<Boolean> authAndSetLocalUserDeviceOffline(Long userId, DeviceType deviceType, CloseStatus closeStatus, int sessionId) {
        return sessionService.authAndSetLocalSessionOfflineByUserIdAndDeviceType(userId, deviceType, closeStatus, sessionId);
    }

    // Session

    public void onSessionEstablished(UserSessionsManager userSessionsManager, DeviceType deviceType) {
        sessionService.onSessionEstablished(userSessionsManager, deviceType);
    }

    // Request

    public Mono<TurmsNotification> processServiceRequest(ServiceRequest serviceRequest) {
        return inboundRequestService.processServiceRequest(serviceRequest);
    }

    public Mono<Boolean> processHeartbeatRequest(long userId, DeviceType deviceType) {
        return sessionService.updateHeartbeatTimestamp(userId, deviceType);
    }

    public Mono<UserSession> authAndProcessHeartbeatRequest(long userId, DeviceType deviceType, int sessionId) {
        return sessionService.authAndUpdateHeartbeatTimestamp(userId, deviceType, sessionId);
    }

    // Plugin

    public Mono<Void> triggerGoOnlinePlugins(@NotNull UserSessionsManager userSessionsManager, @NotNull UserSession userSession) {
        boolean enabled = turmsPluginManager.isEnabled();
        List<UserOnlineStatusChangeHandler> handlers = turmsPluginManager.getUserOnlineStatusChangeHandlerList();
        if (enabled) {
            int size = handlers.size();
            switch (size) {
                case 0:
                    return Mono.empty();
                case 1:
                    return handlers.get(0).goOnline(userSessionsManager, userSession);
                default:
                    List<Mono<Void>> monos = new ArrayList<>(size);
                    for (UserOnlineStatusChangeHandler handler : handlers) {
                        monos.add(handler.goOnline(userSessionsManager, userSession));
                    }
                    return Mono.when(monos);
            }
        } else {
            return Mono.empty();
        }
    }

    // private

    /**
     * @return OK, UNAUTHORIZED, NOT_ACTIVE
     */
    private Mono<TurmsStatusCode> authenticate(
            @NotNull Long userId,
            @Nullable String password,
            @NotNull DeviceType deviceType,
            @Nullable UserStatus userStatus,
            @Nullable Point userLocation,
            @Nullable String ip,
            @Nullable Map<String, String> deviceDetails) {
        boolean enableAuthentication = node.getSharedProperties().getGateway().getSession().isEnableAuthentication();
        if (!enableAuthentication) {
            return Mono.just(TurmsStatusCode.OK);
        }
        if (turmsPluginManager.isEnabled()) {
            List<UserAuthenticator> authenticatorList = turmsPluginManager.getUserAuthenticatorList();
            if (!authenticatorList.isEmpty()) {
                Mono<Boolean> authenticate = Mono.empty();
                UserLoginInfo userLoginInfo = new UserLoginInfo(
                        userId,
                        password,
                        deviceType,
                        userStatus,
                        userLocation,
                        ip,
                        deviceDetails);
                for (UserAuthenticator authenticator : authenticatorList) {
                    Mono<Boolean> authenticateMono = authenticator.authenticate(userLoginInfo);
                    authenticate = authenticate.switchIfEmpty(authenticateMono);
                }
                return authenticate
                        .map(authenticated -> authenticated ? TurmsStatusCode.OK : TurmsStatusCode.UNAUTHORIZED)
                        .switchIfEmpty(authenticate0(userId, password));
            }
        }
        return authenticate0(userId, password);
    }

    /**
     * @return OK, UNAUTHORIZED, NOT_ACTIVE
     */
    private Mono<TurmsStatusCode> authenticate0(
            @NotNull Long userId,
            @Nullable String password) {
        return userService.isActiveAndNotDeleted(userId)
                .flatMap(isActiveAndNotDeleted -> isActiveAndNotDeleted != null && isActiveAndNotDeleted
                        ? userService.authenticate(userId, password)
                        .map(authenticated -> authenticated ? TurmsStatusCode.OK : TurmsStatusCode.UNAUTHORIZED)
                        : Mono.just(TurmsStatusCode.NOT_ACTIVE));
    }

}
