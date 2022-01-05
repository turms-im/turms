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
import im.turms.gateway.access.common.function.ConnectionHandler;
import im.turms.gateway.access.common.model.UserSessionWrapper;
import im.turms.gateway.logging.ApiLoggingContext;
import im.turms.gateway.logging.ClientApiLogging;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.bo.session.connection.NetConnection;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.logging.core.logger.Logger;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.tracing.TracingCloseableContext;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.ThrowableUtil;
import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
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
public abstract class UserSessionDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionDispatcher.class);

    private final ApiLoggingContext apiLoggingContext;
    protected final ServiceMediator serviceMediator;
    protected final ClientRequestDispatcher clientRequestDispatcher;
    protected final int closeIdleConnectionAfterSeconds;

    protected UserSessionDispatcher(ApiLoggingContext apiLoggingContext,
                                    ServiceMediator serviceMediator,
                                    ClientRequestDispatcher clientRequestDispatcher,
                                    int closeIdleConnectionAfterSeconds) {
        this.apiLoggingContext = apiLoggingContext;
        this.serviceMediator = serviceMediator;
        this.clientRequestDispatcher = clientRequestDispatcher;
        this.closeIdleConnectionAfterSeconds = closeIdleConnectionAfterSeconds;
    }

    protected ConnectionHandler bindConnectionWithSessionWrapper() {
        return (connection, isWebSocketConnection, in, out, onClose) -> {
            InetSocketAddress address = (InetSocketAddress) connection.address();
            NetConnection netConnection = NetConnection.create(connection);
            UserSessionWrapper sessionWrapper = new UserSessionWrapper(netConnection, address, closeIdleConnectionAfterSeconds,
                    userSession -> userSession.setNotificationConsumer((turmsNotificationBuffer, tracingContext) -> {
                        turmsNotificationBuffer.touch(turmsNotificationBuffer);
                        // sendObject() will release the buffer no matter it succeeds or fails
                        NettyOutbound outbound = isWebSocketConnection
                                ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                                : out.sendObject(turmsNotificationBuffer);
                        Mono.from(outbound)
                                .subscribe(null, t -> handleConnectionError(t, netConnection, userSession, tracingContext));
                    }));
            respondWithRequests(connection, isWebSocketConnection, in, out, sessionWrapper);
            return tryRemoveSessionInfoOnConnectionClosed(onClose, sessionWrapper);
        };
    }

    private void respondWithRequests(Connection connection,
                                     boolean isWebSocketConnection,
                                     Flux<ByteBuf> in,
                                     NettyOutbound out,
                                     UserSessionWrapper sessionWrapper) {
        in
                .doOnNext(requestData -> {
                    if (connection.isDisposed()) {
                        return;
                    }
                    // Retain by 1 so that it won't be released by FluxReceive#drainReceiver
                    // before we finish handling the buffer.
                    // And it should be 2 after retained
                    requestData.retain();

                    // Note that handleRequest() should never return MonoError
                    TracingContext ctx = new TracingContext();
                    clientRequestDispatcher.handleRequest(sessionWrapper, requestData)
                            .onErrorResume(throwable -> {
                                ctx.updateThreadContext();
                                handleNotificationError(throwable, sessionWrapper.getUserSession());
                                return Mono.empty();
                            })
                            .flatMap(turmsNotificationBuffer -> {
                                NettyOutbound outbound = isWebSocketConnection
                                        ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                                        : out.sendObject(turmsNotificationBuffer);
                                return Mono.from(outbound);
                            })
                            .contextWrite(context -> context.put(TracingContext.CTX_KEY_NAME, ctx))
                            .doFinally(signal -> ctx.clearThreadContext())
                            .subscribe(null, t -> handleConnectionError(t, sessionWrapper.getConnection(),
                                    sessionWrapper.getUserSession(), ctx));
                })
                .then()
                .subscribe(null, t -> handleConnectionError(t, sessionWrapper.getConnection(),
                        sessionWrapper.getUserSession(), TracingContext.NOOP));
    }

    private Mono<Void> tryRemoveSessionInfoOnConnectionClosed(Mono<Void> onClose, UserSessionWrapper sessionWrapper) {
        return onClose
                .onErrorResume(throwable -> {
                    handleConnectionError(throwable, sessionWrapper.getConnection(),
                            sessionWrapper.getUserSession(), TracingContext.NOOP);
                    return Mono.empty();
                })
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
                                .subscribe(null, t -> LOGGER.error("Caught an error while closing the session of the user ID: " + userId, t));
                    }
                    if (userSession.acquireDeleteSessionRequestLoggingLock()
                            && apiLoggingContext.shouldLogRequest(DELETE_SESSION_REQUEST)) {
                        ClientApiLogging.log(
                                userSession.getId(),
                                userId,
                                deviceType,
                                userSession.getVersion(),
                                sessionWrapper.getIpStr(),
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

    private void handleConnectionError(Throwable throwable,
                                       NetConnection connection,
                                       @Nullable UserSession userSession,
                                       TracingContext tracingContext) {
        if (!ThrowableUtil.isDisconnectedClientError(throwable)) {
            try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                LOGGER.error("Caught an exception from a connection bound with the session: {}",
                        userSession,
                        throwable);
            }
        }
        if (userSession == null) {
            connection.close();
            return;
        }
        Long userId = userSession.getUserId();
        DeviceType deviceType = userSession.getDeviceType();
        CloseReason closeReason = CloseReason.get(throwable);
        serviceMediator.setLocalUserDeviceOffline(userId, deviceType, closeReason)
                .subscribe(null, t -> {
                    try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                        LOGGER.error("Caught an exception when setting the local session [{}:{}] offline due to connection error",
                                userId, deviceType, t);
                    }
                });
    }

    private void handleNotificationError(Throwable throwable, @Nullable UserSession userSession) {
        if (userSession == null) {
            return;
        }
        CloseReason closeReason = CloseReason.get(throwable);
        if (closeReason.isServerError()) {
            LOGGER.error("Failed to send outbound notification to the session: " + userSession, throwable);
        }
        Long userId = userSession.getUserId();
        DeviceType deviceType = userSession.getDeviceType();
        serviceMediator.setLocalUserDeviceOffline(userId, deviceType, closeReason)
                .subscribe(null, t -> LOGGER.error("Caught an error while closing the session of the user ID: " + userId, t));
    }

}