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

package im.turms.gateway.access.common;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.gateway.access.common.controller.UserRequestDispatcher;
import im.turms.gateway.access.common.function.ConnectionHandler;
import im.turms.gateway.access.common.model.UserSessionWrapper;
import im.turms.gateway.logging.ApiLoggingContext;
import im.turms.gateway.logging.ClientApiLogging;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.bo.session.connection.NetConnection;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.logging.RequestLoggingContext;
import im.turms.server.common.tracing.TracingCloseableContext;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ExceptionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import lombok.extern.log4j.Log4j2;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;

import javax.annotation.Nullable;
import java.net.InetSocketAddress;

import static im.turms.common.model.dto.request.TurmsRequest.KindCase.DELETE_SESSION_REQUEST;

/**
 * @author James Chen
 */
@Log4j2
public abstract class UserSessionDispatcher {

    private final ApiLoggingContext apiLoggingContext;
    protected final ServiceMediator serviceMediator;
    protected final UserRequestDispatcher userRequestDispatcher;
    protected final int closeIdleConnectionAfterSeconds;

    protected UserSessionDispatcher(ApiLoggingContext apiLoggingContext,
                                    ServiceMediator serviceMediator,
                                    UserRequestDispatcher userRequestDispatcher,
                                    int closeIdleConnectionAfterSeconds) {
        this.apiLoggingContext = apiLoggingContext;
        this.serviceMediator = serviceMediator;
        this.userRequestDispatcher = userRequestDispatcher;
        this.closeIdleConnectionAfterSeconds = closeIdleConnectionAfterSeconds;
    }

    protected ConnectionHandler bindConnectionWithSessionWrapper() {
        return (connection, isWebSocketConnection, in, out, onClose) -> {
            InetSocketAddress ip = (InetSocketAddress) connection.address();
            NetConnection netConnection = NetConnection.create(connection);
            UserSessionWrapper sessionWrapper = new UserSessionWrapper(netConnection, ip, closeIdleConnectionAfterSeconds, userSession -> {
                userSession.setNotificationConsumer((turmsNotificationBuffer, tracingContext) -> {
                    turmsNotificationBuffer.touch(turmsNotificationBuffer);
                    // sendObject() will release the buffer no matter it succeeds or fails
                    NettyOutbound outbound = isWebSocketConnection
                            ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                            : out.sendObject(turmsNotificationBuffer);
                    Mono.from(outbound)
                            .onErrorResume(throwable -> handleConnectionError(throwable, netConnection, userSession, tracingContext))
                            .subscribe();
                });
            });
            respondWithRequests(connection, isWebSocketConnection, in, out, sessionWrapper)
                    .subscribe();
            return tryRemoveSessionInfoOnConnectionClosed(onClose, sessionWrapper);
        };
    }

    private Mono<Void> respondWithRequests(Connection connection,
                                           boolean isWebSocketConnection,
                                           Flux<ByteBuf> in,
                                           NettyOutbound out,
                                           UserSessionWrapper sessionWrapper) {
        return in
                .doOnNext(requestData -> {
                    if (connection.isDisposed()) {
                        return;
                    }
                    // Retain by 1 so that it won't be released by FluxReceive#drainReceiver
                    // before we finish handling the buffer.
                    // And it should be 2 after retained
                    requestData.retain();

                    // Note that handleRequest() should never return MonoError
                    RequestLoggingContext loggingContext = new RequestLoggingContext();
                    userRequestDispatcher.handleRequest(sessionWrapper, requestData)
                            .onErrorResume(throwable -> {
                                loggingContext.updateMdc();
                                handleNotificationError(throwable, sessionWrapper.getUserSession());
                                return Mono.empty();
                            })
                            .flatMap(turmsNotificationBuffer -> {
                                NettyOutbound outbound = isWebSocketConnection
                                        ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                                        : out.sendObject(turmsNotificationBuffer);
                                return Mono.from(outbound);
                            })
                            .onErrorResume(throwable -> handleConnectionError(throwable, sessionWrapper.getConnection(),
                                    sessionWrapper.getUserSession(), loggingContext.getTracingContext()))
                            .contextWrite(context -> context.put(RequestLoggingContext.CTX_KEY_NAME, loggingContext))
                            .doFinally(signal -> loggingContext.clearMdc())
                            .subscribe();
                })
                .then()
                .onErrorResume(
                        throwable -> handleConnectionError(throwable, sessionWrapper.getConnection(),
                                sessionWrapper.getUserSession(), TracingContext.NOOP));
    }

    private Mono<Void> tryRemoveSessionInfoOnConnectionClosed(Mono<Void> onClose, UserSessionWrapper sessionWrapper) {
        return onClose
                .onErrorResume(
                        throwable -> handleConnectionError(throwable, sessionWrapper.getConnection(),
                                sessionWrapper.getUserSession(), TracingContext.NOOP))
                .doFinally(signal -> {
                    UserSession userSession = sessionWrapper.getUserSession();
                    if (userSession == null) {
                        return;
                    }
                    Long userId = userSession.getUserId();
                    DeviceType deviceType = userSession.getDeviceType();
                    if (userSession.isOpen() && !userSession.getConnection().isSwitchingToUdp()) {
                        // The close status code is UNKNOWN_ERROR
                        // and should never be sent to the client because
                        // the connection has been closed
                        serviceMediator
                                .setLocalUserDeviceOffline(userId, deviceType, SessionCloseStatus.UNKNOWN_ERROR)
                                .subscribe();
                    }
                    if (userSession.acquireDeleteSessionRequestLoggingLock()
                            && apiLoggingContext.shouldLogRequest(DELETE_SESSION_REQUEST)) {
                        ClientApiLogging.log(
                                userSession.getId(),
                                userId,
                                deviceType,
                                userSession.getVersion(),
                                sessionWrapper.getIp(),
                                0,
                                DELETE_SESSION_REQUEST,
                                0,
                                System.currentTimeMillis(),
                                TurmsStatusCode.OK.getBusinessCode(),
                                0);
                    }
                });
    }

    // Error handling

    private Mono<Void> handleConnectionError(Throwable throwable,
                                             NetConnection connection,
                                             @Nullable UserSession userSession,
                                             TracingContext tracingContext) {
        if (!ExceptionUtil.isDisconnectedClientError(throwable)) {
            try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                log.error("Caught an exception from a connection bound with the session: {}",
                        userSession,
                        throwable);
            }
        }
        if (userSession == null) {
            connection.close();
            return Mono.empty();
        } else {
            Long userId = userSession.getUserId();
            DeviceType deviceType = userSession.getDeviceType();
            CloseReason closeReason = CloseReason.get(throwable);
            return serviceMediator.setLocalUserDeviceOffline(userId, deviceType, closeReason)
                    .onErrorResume(t -> {
                        // Log because this should be the last error handler here
                        try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                            log.error("Caught an exception when setting the local session [{}:{}] offline due to connection error",
                                    userId, deviceType, t);
                        }
                        return Mono.empty();
                    })
                    .then();
        }
    }

    private void handleNotificationError(Throwable throwable, @Nullable UserSession userSession) {
        if (userSession == null) {
            return;
        }
        CloseReason closeReason = CloseReason.get(throwable);
        if (closeReason.isServerError()) {
            log.error("Failed to send outbound notification to the session: " + userSession, throwable);
        }
        Long userId = userSession.getUserId();
        DeviceType deviceType = userSession.getDeviceType();
        serviceMediator.setLocalUserDeviceOffline(userId, deviceType, closeReason).subscribe();
    }

}