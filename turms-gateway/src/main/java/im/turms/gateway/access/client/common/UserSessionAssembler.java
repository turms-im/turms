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

package im.turms.gateway.access.client.common;

import java.net.InetSocketAddress;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.netty.Connection;
import reactor.netty.NettyOutbound;

import im.turms.gateway.access.client.common.connection.ConnectionListener;
import im.turms.gateway.access.client.common.connection.NetConnection;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.logging.ApiLoggingContext;
import im.turms.gateway.infra.logging.ClientApiLogging;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;

import static im.turms.server.common.access.client.dto.request.TurmsRequest.KindCase.DELETE_SESSION_REQUEST;

/**
 * @author James Chen
 */
public abstract class UserSessionAssembler {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserSessionAssembler.class);

    private final ApiLoggingContext apiLoggingContext;
    protected final ClientRequestDispatcher clientRequestDispatcher;
    protected final SessionService sessionService;
    protected final int closeIdleConnectionAfterSeconds;

    protected UserSessionAssembler(
            ApiLoggingContext apiLoggingContext,
            ClientRequestDispatcher clientRequestDispatcher,
            SessionService sessionService,
            int closeIdleConnectionAfterSeconds) {
        this.apiLoggingContext = apiLoggingContext;
        this.clientRequestDispatcher = clientRequestDispatcher;
        this.sessionService = sessionService;
        this.closeIdleConnectionAfterSeconds = closeIdleConnectionAfterSeconds;
    }

    protected abstract NetConnection createConnection(Connection connection);

    protected ConnectionListener bindConnectionWithSessionWrapper() {
        return (connection, isWebSocketConnection, in, out, onClose) -> {
            InetSocketAddress address = (InetSocketAddress) connection.address();
            NetConnection netConnection = createConnection(connection);
            UserSessionWrapper sessionWrapper = new UserSessionWrapper(
                    netConnection,
                    address,
                    closeIdleConnectionAfterSeconds,
                    userSession -> userSession
                            .setNotificationConsumer((turmsNotificationBuffer, tracingContext) -> {
                                turmsNotificationBuffer.touch(turmsNotificationBuffer);
                                // Duplicate the buffer to use an independent reader index
                                // because we don't want to modify the reader index of the original
                                // buffer
                                // if it is an unreleasable buffer internally, or it may be sent to
                                // multiple endpoints.
                                // Note that the content of the buffer is not copied, so
                                // "duplicate()" is efficient.
                                turmsNotificationBuffer = turmsNotificationBuffer.duplicate();
                                // sendObject() will release the buffer no matter it succeeds or
                                // fails
                                NettyOutbound outbound = isWebSocketConnection
                                        ? out.sendObject(
                                                new BinaryWebSocketFrame(turmsNotificationBuffer))
                                        : out.sendObject(turmsNotificationBuffer);
                                return Mono.from(outbound)
                                        .doOnError(t -> handleConnectionError(t,
                                                netConnection,
                                                userSession,
                                                tracingContext));
                            }));
            respondToRequests(connection, isWebSocketConnection, in, out, sessionWrapper);
            return tryRemoveSessionInfoOnConnectionClosed(onClose, sessionWrapper);
        };
    }

    private void respondToRequests(
            Connection connection,
            boolean isWebSocketConnection,
            Flux<ByteBuf> in,
            NettyOutbound out,
            UserSessionWrapper sessionWrapper) {
        in.doOnNext(requestData -> {
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
                        turmsNotificationBuffer.touch(turmsNotificationBuffer);
                        // Duplicate the buffer to use an independent reader index
                        // because we don't want to modify the reader index of the original buffer
                        // if it is an unreleasable buffer internally, or it may be sent to multiple
                        // endpoints.
                        // Note that the content of the buffer is not copied, so "duplicate()" is
                        // efficient.
                        turmsNotificationBuffer = turmsNotificationBuffer.duplicate();
                        NettyOutbound outbound = isWebSocketConnection
                                ? out.sendObject(new BinaryWebSocketFrame(turmsNotificationBuffer))
                                : out.sendObject(turmsNotificationBuffer);
                        return Mono.from(outbound);
                    })
                    .contextWrite(context -> context.put(TracingContext.CTX_KEY_NAME, ctx))
                    .doFinally(signal -> ctx.clearThreadContext())
                    .subscribe(null,
                            t -> handleConnectionError(t,
                                    sessionWrapper.getConnection(),
                                    sessionWrapper.getUserSession(),
                                    ctx));
        })
                .then()
                .subscribe(null,
                        t -> handleConnectionError(t,
                                sessionWrapper.getConnection(),
                                sessionWrapper.getUserSession(),
                                TracingContext.NOOP));
    }

    private Mono<Void> tryRemoveSessionInfoOnConnectionClosed(
            Mono<Void> onClose,
            UserSessionWrapper sessionWrapper) {
        return onClose.onErrorResume(throwable -> {
            handleConnectionError(throwable,
                    sessionWrapper.getConnection(),
                    sessionWrapper.getUserSession(),
                    TracingContext.NOOP);
            return Mono.empty();
        })
                .doFinally(signal -> {
                    UserSession userSession = sessionWrapper.getUserSession();
                    if (userSession == null) {
                        return;
                    }
                    Long userId = userSession.getUserId();
                    DeviceType deviceType = userSession.getDeviceType();
                    if (userSession.isOpen()
                            && !userSession.getConnection()
                                    .isSwitchingToUdp()) {
                        // The close status code is UNKNOWN_ERROR
                        // and should never be sent to the client because
                        // the connection has been closed
                        sessionService
                                .closeLocalSession(userId,
                                        deviceType,
                                        SessionCloseStatus.UNKNOWN_ERROR)
                                .subscribe(null,
                                        t -> LOGGER.error(
                                                "Caught an error while closing the user session with the user ID: "
                                                        + userId,
                                                t));
                    }
                    if (userSession.acquireDeleteSessionRequestLoggingLock()
                            && apiLoggingContext.shouldLogRequest(DELETE_SESSION_REQUEST)) {
                        ClientApiLogging.log(userSession.getId(),
                                userId,
                                deviceType,
                                userSession.getVersion(),
                                sessionWrapper.getIpStr(),
                                0,
                                DELETE_SESSION_REQUEST,
                                0,
                                System.currentTimeMillis(),
                                ResponseStatusCode.OK.getBusinessCode(),
                                0);
                    }
                });
    }

    // Error handling

    private void handleConnectionError(
            Throwable throwable,
            NetConnection connection,
            @Nullable UserSession userSession,
            TracingContext tracingContext) {
        if (!ThrowableUtil.isDisconnectedClientError(throwable)) {
            try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                LOGGER.error("Caught an exception from a connection bound with the user session: "
                        + userSession, throwable);
            }
        }
        if (userSession == null) {
            connection.close();
            return;
        }
        Long userId = userSession.getUserId();
        DeviceType deviceType = userSession.getDeviceType();
        CloseReason closeReason = CloseReason.get(throwable);
        sessionService.closeLocalSession(userId, deviceType, closeReason)
                .subscribe(null, t -> {
                    try (TracingCloseableContext ignored = tracingContext.asCloseable()) {
                        LOGGER.error(
                                "Caught an error while closing the local user session {user={}, device={}} due to a connection error",
                                userId,
                                deviceType,
                                t);
                    }
                });
    }

    private void handleNotificationError(Throwable throwable, @Nullable UserSession userSession) {
        if (userSession == null) {
            return;
        }
        CloseReason closeReason = CloseReason.get(throwable);
        if (closeReason.isServerError()) {
            LOGGER.error("Failed to send outbound notification to the user session: "
                    + userSession, throwable);
        }
        Long userId = userSession.getUserId();
        DeviceType deviceType = userSession.getDeviceType();
        sessionService.closeLocalSession(userId, deviceType, closeReason)
                .subscribe(null,
                        t -> LOGGER.error(
                                "Caught an error while closing the user session of the user ID: {}",
                                userId,
                                t));
    }

}