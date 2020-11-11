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

package im.turms.gateway.access.tcp.controller;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.model.bo.user.UserLocation;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.user.CreateSessionRequest;
import im.turms.gateway.access.tcp.dto.RequestHandlerResult;
import im.turms.gateway.access.tcp.model.UserSessionWrapper;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.bo.session.connection.TcpConnection;
import im.turms.gateway.service.mediator.WorkflowMediator;
import io.netty.util.Timeout;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;

/**
 * @author James Chen
 */
@Controller
public class SessionController {

    private final WorkflowMediator workflowMediator;

    public SessionController(WorkflowMediator workflowMediator) {
        this.workflowMediator = workflowMediator;
    }

    public Mono<TurmsNotification> handleDeleteSessionRequest(UserSessionWrapper sessionWrapper) {
        UserSession session = sessionWrapper.getUserSession();
        if (session != null) {
            workflowMediator.setLocalUserDeviceOffline(session.getUserId(), session.getDeviceType(), SessionCloseStatus.DISCONNECTED_BY_CLIENT)
                    .subscribe();
        }
        return Mono.empty();
    }

    public Mono<RequestHandlerResult> handleCreateSessionRequest(UserSessionWrapper sessionWrapper,
                                                                 CreateSessionRequest createSessionRequest,
                                                                 String ip,
                                                                 Timeout idleConnectionTimeout) {
        if (sessionWrapper.hasUserSession()) {
            return Mono.just(new RequestHandlerResult(TurmsStatusCode.CLIENT_SESSION_ALREADY_ESTABLISHED));
        }
        long userId = createSessionRequest.getUserId();
        String password = createSessionRequest.hasPassword()
                ? createSessionRequest.getPassword().getValue()
                : null;
        UserStatus userStatus = createSessionRequest.getUserStatus();
        if (userStatus == UserStatus.UNRECOGNIZED) {
            userStatus = null;
        }
        DeviceType deviceType = createSessionRequest.getDeviceType();
        if (deviceType == DeviceType.UNRECOGNIZED) {
            deviceType = DeviceType.UNKNOWN;
        }
        String deviceDetails = createSessionRequest.hasDeviceDetails()
                ? createSessionRequest.getDeviceDetails().getValue()
                : null;
        Point position = null;
        if (createSessionRequest.hasLocation()) {
            UserLocation location = createSessionRequest.getLocation();
            position = new Point(location.getLatitude(), location.getLongitude());
        }
        Mono<UserSession> processLoginRequestMono = workflowMediator.processLoginRequest(userId,
                password,
                deviceType,
                userStatus,
                position,
                ip,
                deviceDetails);
        if (idleConnectionTimeout == null) {
            return processLoginRequestMono
                    .map(session -> {
                        bindUserSession(sessionWrapper, session);
                        return new RequestHandlerResult(TurmsStatusCode.OK);
                    });
        } else {
            DeviceType finalDeviceType = deviceType;
            return processLoginRequestMono.flatMap(session -> {
                if (idleConnectionTimeout.cancel()) {
                    bindUserSession(sessionWrapper, session);
                    return Mono.just(new RequestHandlerResult(TurmsStatusCode.OK));
                } else {
                    return workflowMediator.setLocalUserDeviceOffline(userId, finalDeviceType, SessionCloseStatus.HEARTBEAT_TIMEOUT)
                            .then(Mono.empty());
                }
            });
        }
    }

    private void bindUserSession(UserSessionWrapper sessionWrapper, UserSession session) {
        Connection connection = sessionWrapper.getConnection();
        session.setConnection(new TcpConnection(connection, !connection.isDisposed()));
        sessionWrapper.setUserSession(session);
    }

}