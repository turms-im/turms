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

package stress;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import com.google.protobuf.InvalidProtocolBufferException;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakeException;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import perf.BasePerformanceTest;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;
import reactor.netty.resources.LoopResources;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.CreateSessionRequest;
import im.turms.server.common.testing.TestingEnvContainer;
import im.turms.server.common.testing.TestingEnvContainerOptions;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author James Chen
 */
@Log4j2
class WebSocketAccessST extends BasePerformanceTest {

    private static final int CONCURRENT_CLIENT_COUNT = 100_000;

    @Test
    void shouldDenyServiceRatherThanCrash_whenManyUsersTryToLogin_on1gJvm()
            throws InterruptedException {
        boolean useLocalServer = Boolean.parseBoolean(System.getProperty("USE_LOCAL_SERVER"));

        CountDownLatch pendingClientLatch = new CountDownLatch(CONCURRENT_CLIENT_COUNT);
        AtomicInteger loggedInClient = new AtomicInteger(0);
        AtomicInteger connectedButNotLoggedInClient = new AtomicInteger(0);
        AtomicInteger notConnectedClient = new AtomicInteger(0);

        // Setup env
        if (useLocalServer) {
            test("127.0.0.1",
                    9510,
                    "ws://127.0.0.1:10510",
                    pendingClientLatch,
                    loggedInClient,
                    connectedButNotLoggedInClient,
                    notConnectedClient);
        } else {
            TestingEnvContainer container = new TestingEnvContainer(
                    TestingEnvContainerOptions.builder()
                            .setupTurmsGateway(true)
                            .turmsGatewayJvmOptions(List.of("-Xms1g",
                                    "-Xmx1g",
                                    "turms.health-check.check-interval-seconds=1"))
                            .build());
            container.start();
            try (container) {
                test(container.getTurmsGatewayAdminHost(),
                        container.getTurmsGatewayAdminPort(),
                        container.getTurmsGatewayWebSocketServerUri(),
                        pendingClientLatch,
                        loggedInClient,
                        connectedButNotLoggedInClient,
                        notConnectedClient);
            }
        }

        log.info("""
                Logged in clients: {}
                Connected but not logged in clients: {}
                Not Connected clients: {}
                Pending clients: {}
                """,
                loggedInClient.get(),
                connectedButNotLoggedInClient.get(),
                notConnectedClient.get(),
                pendingClientLatch.getCount());
    }

    private void test(
            String gatewayAdminHost,
            int gatewayAdminPort,
            String websocketServerUri,
            CountDownLatch pendingClientLatch,
            AtomicInteger loggedInClient,
            AtomicInteger connectedButNotLoggedInClient,
            AtomicInteger notConnectedClient) throws InterruptedException {
        assertTurmsGatewayAvailable(gatewayAdminHost, gatewayAdminPort);

        AtomicReference<Throwable> unexpectedError = new AtomicReference<>();

        log.info("starting logging in");

        // Note that we do not close the clients on the client side
        // until the test succeeds or fails
        LoopResources loopResources = LoopResources.create("turms-ws-client");
        for (int i = 0; i < CONCURRENT_CLIENT_COUNT; i++) {
            long userId = i;
            AtomicBoolean isCurrentClientHandled = new AtomicBoolean();
            HttpClient.newConnection()
                    .runOn(loopResources)
                    .websocket()
                    .uri(websocketServerUri)
                    .connect()
                    .subscribe(connection -> {
                        WebsocketInbound in = (WebsocketInbound) connection;
                        WebsocketOutbound out = (WebsocketOutbound) connection;
                        in.aggregateFrames()
                                .receive()
                                .subscribe(byteBuf -> {
                                    try {
                                        TurmsNotification notification =
                                                TurmsNotification.parseFrom(byteBuf.nioBuffer());
                                        String sessionId = notification.getData()
                                                .getUserSession()
                                                .getSessionId();
                                        if (!sessionId.isBlank()) {
                                            tryHandleCurrentClient(pendingClientLatch,
                                                    isCurrentClientHandled,
                                                    loggedInClient);
                                            loggedInClient.incrementAndGet();
                                        }
                                    } catch (InvalidProtocolBufferException e) {
                                        unexpectedError.set(e);
                                        log.error("Failed to parse data to TurmsNotification. "
                                                + "Make sure the client version is consistent with the server version",
                                                e);
                                    }
                                }, t -> {
                                    // turms-gateway should keep the websocket session even in
                                    // unavailable
                                    // status
                                    // the error is mainly caused due to the problem of client
                                    // itself
                                    unexpectedError.set(t);
                                    tryHandleCurrentClient(pendingClientLatch,
                                            isCurrentClientHandled,
                                            connectedButNotLoggedInClient);
                                    log.error(
                                            "Failed to receive messages. This may be a client error",
                                            t);
                                });
                        byte[] request = TurmsRequest.newBuilder()
                                .setRequestId(1)
                                .setCreateSessionRequest(CreateSessionRequest.newBuilder()
                                        .setUserId(userId)
                                        .setDeviceType(DeviceType.ANDROID)
                                        .build())
                                .build()
                                .toByteArray();
                        ByteBuf requestBuffer = Unpooled.wrappedBuffer(request);
                        BinaryWebSocketFrame frame = new BinaryWebSocketFrame(requestBuffer);
                        out.sendObject(frame)
                                .then()
                                .subscribe();
                        in.receiveCloseStatus()
                                .doFinally(signalType -> tryHandleCurrentClient(pendingClientLatch,
                                        isCurrentClientHandled,
                                        connectedButNotLoggedInClient))
                                .subscribe(null, t -> log.error("An connection error occurs", t));
                    }, t -> {
                        tryHandleCurrentClient(pendingClientLatch,
                                isCurrentClientHandled,
                                notConnectedClient);
                        // It is a WebSocketClientHandshakeException when turms-gateway is
                        // unavailable
                        if (t instanceof WebSocketClientHandshakeException) {
                            return;
                        }
                        unexpectedError.set(t);
                        // It is a TCP exception no matter turms-gateway is available or not
                        // when the client or server has reached the maximum TCP connections that
                        // the system supports
                        if (t instanceof IOException) {
                            // It is an unexpected error and the client or the server should
                            // increase the maximum allowed TCP connections
                            log.error("Failed to connect. This may be a TCP-related error", t);
                        } else {
                            log.error("Failed to connect. Unknown error", t);
                        }
                    });
        }
        log.info("all clients has sent login requests");

        pendingClientLatch.await(3, TimeUnit.MINUTES);
        assertThat(pendingClientLatch.getCount())
                .as("turms-gateway should handle all login requests")
                .isZero();

        assertThat(unexpectedError.get()).as("No unexpected error occurs")
                .isNull();

        assertTurmsGatewayAvailable(gatewayAdminHost, gatewayAdminPort);
    }

    private void tryHandleCurrentClient(
            CountDownLatch pendingClientLatch,
            AtomicBoolean isCurrentClientHandled,
            AtomicInteger count) {
        if (isCurrentClientHandled.compareAndSet(false, true)) {
            pendingClientLatch.countDown();
            count.incrementAndGet();
        }
    }

}
