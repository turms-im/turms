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

package im.turms.gateway.access.common.controller;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.bo.user.UserLocation;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.user.CreateSessionRequest;
import im.turms.gateway.access.common.model.UserSessionWrapper;
import im.turms.gateway.access.tcp.dto.RequestHandlerResult;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import io.netty.util.Timeout;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
@Controller
public class SessionController {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);

    private final ServiceMediator serviceMediator;

    public SessionController(ServiceMediator serviceMediator) {
        this.serviceMediator = serviceMediator;
    }

    public Mono<TurmsNotification> handleDeleteSessionRequest(UserSessionWrapper sessionWrapper) {
        UserSession session = sessionWrapper.getUserSession();
        if (session != null) {
            Long userId = session.getUserId();
            serviceMediator
                    .setLocalUserDeviceOffline(userId, session.getDeviceType(), SessionCloseStatus.DISCONNECTED_BY_CLIENT)
                    .subscribe(null, t -> LOGGER.error("Caught an error while closing the session of the user ID: " + userId, t));
        }
        return Mono.empty();
    }

    public Mono<RequestHandlerResult> handleCreateSessionRequest(UserSessionWrapper sessionWrapper,
                                                                 CreateSessionRequest createSessionRequest) {
        if (sessionWrapper.hasUserSession()) {
            return Mono.just(new RequestHandlerResult(TurmsStatusCode.CREATE_EXISTING_SESSION));
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
        String deviceDetails = createSessionRequest.hasDeviceDetails()
                ? createSessionRequest.getDeviceDetails()
                : null;
        Point position = null;
        if (createSessionRequest.hasLocation()) {
            UserLocation location = createSessionRequest.getLocation();
            position = new Point(location.getLatitude(), location.getLongitude());
        }
        Mono<UserSession> processLoginRequestMono = serviceMediator.processLoginRequest(createSessionRequest.getVersion(),
                sessionWrapper.getIp(),
                userId,
                password,
                deviceType,
                userStatus,
                position,
                sessionWrapper.getIpStr(),
                deviceDetails);
        Timeout idleConnectionTimeout = sessionWrapper.getConnectionTimeoutTask();
        DeviceType finalDeviceType = deviceType;
        return processLoginRequestMono.flatMap(session -> {
            if (idleConnectionTimeout == null || idleConnectionTimeout.cancel()) {
                if (sessionWrapper.getConnection().isConnected()) {
                    sessionWrapper.setUserSession(session);
                    UserSessionsManager userSessionsManager = serviceMediator.getUserSessionsManager(userId);
                    serviceMediator.onSessionEstablished(userSessionsManager, session.getDeviceType());
                    serviceMediator.triggerGoOnlinePlugins(userSessionsManager, session)
                            .subscribe(null, t -> LOGGER.error("Caught an error while triggering the plugins of goOnline()", t));
                    return Mono.just(new RequestHandlerResult(TurmsStatusCode.OK));
                } else {
                    return serviceMediator.setLocalUserDeviceOffline(userId, finalDeviceType, SessionCloseStatus.LOGIN_TIMEOUT)
                            .then(Mono.empty());
                }
            } else {
                return serviceMediator.setLocalUserDeviceOffline(userId, finalDeviceType, SessionCloseStatus.LOGIN_TIMEOUT)
                        .map(ignored -> new RequestHandlerResult(TurmsStatusCode.LOGIN_TIMEOUT));
            }
        });
    }

}