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

package im.turms.gateway.domain.session.access.client.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import io.netty.util.Timeout;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

import im.turms.gateway.access.client.common.RequestHandlerResult;
import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.access.client.common.UserSessionWrapper;
import im.turms.gateway.domain.session.manager.UserSessionsManager;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.access.client.dto.model.user.UserLocation;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.user.CreateSessionRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.location.bo.Location;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
@Controller
public class SessionClientController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionClientController.class);
    private static final String ERROR_INVOKE_GO_ONLINE;

    static {
        try {
            Method method = UserOnlineStatusChangeHandler.class
                    .getDeclaredMethod("goOnline", UserSessionsManager.class, UserSession.class);
            ERROR_INVOKE_GO_ONLINE =
                    "Caught an error while invoking the extension point handlers for: "
                            + ClassUtil.getReference(method);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    private final SessionService sessionService;

    public SessionClientController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    public Mono<TurmsNotification> handleDeleteSessionRequest(UserSessionWrapper sessionWrapper) {
        UserSession session = sessionWrapper.getUserSession();
        if (session == null) {
            return Mono.empty();
        }
        Long userId = session.getUserId();
        sessionService
                .closeLocalSession(userId,
                        session.getDeviceType(),
                        SessionCloseStatus.DISCONNECTED_BY_CLIENT)
                .subscribe(null,
                        t -> LOGGER.error(
                                "Caught an error while closing the session with the user ID: "
                                        + userId,
                                t));
        return Mono.empty();
    }

    public Mono<RequestHandlerResult> handleCreateSessionRequest(
            UserSessionWrapper sessionWrapper,
            CreateSessionRequest createSessionRequest) {
        if (sessionWrapper.hasUserSession()) {
            return Mono.just(new RequestHandlerResult(ResponseStatusCode.CREATE_EXISTING_SESSION));
        }
        long userId = createSessionRequest.getUserId();
        String password = createSessionRequest.hasPassword()
                ? createSessionRequest.getPassword()
                : null;
        UserStatus userStatus = createSessionRequest.getUserStatus();
        if (userStatus == UserStatus.UNRECOGNIZED) {
            userStatus = null;
        }
        DeviceType deviceType = createSessionRequest.getDeviceType();
        if (deviceType == DeviceType.UNRECOGNIZED) {
            deviceType = DeviceType.UNKNOWN;
        }
        // TODO: Log deviceDetails in API logs
        Map<String, String> deviceDetails = createSessionRequest.getDeviceDetailsMap();
        Location location = null;
        if (createSessionRequest.hasLocation()) {
            UserLocation userLocation = createSessionRequest.getLocation();
            location = new Location(
                    userLocation.getLongitude(),
                    userLocation.getLatitude(),
                    userLocation.hasTimestamp()
                            ? new Date(userLocation.getTimestamp())
                            : null,
                    userLocation.getDetailsMap());
        }
        Mono<UserSession> handleLoginRequestMono =
                sessionService.handleLoginRequest(createSessionRequest.getVersion(),
                        sessionWrapper.getIp(),
                        userId,
                        password,
                        deviceType,
                        deviceDetails,
                        userStatus,
                        location,
                        sessionWrapper.getIpStr());
        Timeout idleConnectionTimeout = sessionWrapper.getConnectionTimeoutTask();
        DeviceType finalDeviceType = deviceType;
        return handleLoginRequestMono.flatMap(session -> {
            if (idleConnectionTimeout == null || idleConnectionTimeout.cancel()) {
                if (sessionWrapper.getConnection()
                        .isConnected()) {
                    sessionWrapper.setUserSession(session);
                    UserSessionsManager userSessionsManager =
                            sessionService.getUserSessionsManager(userId);
                    sessionService.onSessionEstablished(userSessionsManager,
                            session.getDeviceType());
                    sessionService.invokeGoOnlineHandlers(userSessionsManager, session)
                            .subscribe(null, t -> LOGGER.error(ERROR_INVOKE_GO_ONLINE, t));
                    return Mono.just(new RequestHandlerResult(ResponseStatusCode.OK));
                } else {
                    return sessionService
                            .closeLocalSession(userId,
                                    finalDeviceType,
                                    SessionCloseStatus.LOGIN_TIMEOUT)
                            .then(Mono.empty());
                }
            } else {
                return sessionService
                        .closeLocalSession(userId,
                                finalDeviceType,
                                SessionCloseStatus.LOGIN_TIMEOUT)
                        .map(ignored -> new RequestHandlerResult(ResponseStatusCode.LOGIN_TIMEOUT));
            }
        });
    }

}