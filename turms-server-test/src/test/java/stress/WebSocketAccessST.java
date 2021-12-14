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

import com.google.protobuf.InvalidProtocolBufferException;
import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.model.dto.request.user.CreateSessionRequest;
import im.turms.server.common.testing.TestingEnvContainer;
import im.turms.server.common.testing.TestingEnvContainerOptions;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketClientHandshakeException;
import org.junit.jupiter.api.Test;
import perf.BasePerformanceTest;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.websocket.WebsocketInbound;
import reactor.netty.http.websocket.WebsocketOutbound;
import reactor.netty.resources.LoopResources;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

/**
 * @author James Chen
 */
class WebSocketAccessST extends BasePerformanceTest {

    private static final int CONCURRENT_CLIENT_COUNT = 100_000;

    @Test
    void shouldDenyServiceRatherThanCrash_whenManyUsersTryToLogin_on1gJvm() throws InterruptedException {
        // Setup env
        TestingEnvContainer container = new TestingEnvContainer(TestingEnvContainerOptions.builder()
                .setupTurmsGateway(true)
                .turmsGatewayJvmOptions(List.of(
                        "-Xms=1g",
                        "-Xmx=1g",
                        "turms.healthCheck.checkIntervalSeconds=1"
                ))
                .build());
        container.start();

        // Test
        CountDownLatch pendingClientLatch = new CountDownLatch(CONCURRENT_CLIENT_COUNT);
        AtomicInteger loggedInClient = new AtomicInteger(0);
        AtomicInteger connectedButNotLoggedInClient = new AtomicInteger(0);
        AtomicInteger notConnectedClient = new AtomicInteger(0);

        try (container) {
            String gatewayAdminHost = container.getTurmsGatewayAdminHost();
            int gatewayAdminPort = container.getTurmsGatewayAdminPort();

            assertTurmsGatewayAvailable(gatewayAdminHost, gatewayAdminPort);

            AtomicReference<Throwable> unexpectedError = new AtomicReference<>();

            System.out.println("starting logging in...");

            // Note that we do not close the clients on the client side
            // until the test succeeds or fails
            LoopResources loopResources = LoopResources.create("turms-ws-client");
            for (int i = 0; i < CONCURRENT_CLIENT_COUNT; i++) {
                long userId = i;
                AtomicBoolean isCurrentClientHandled = new AtomicBoolean();
                HttpClient
                        .newConnection()
                        .runOn(loopResources)
                        .websocket()
                        .uri(container.getTurmsGatewayWebSocketServerUri())
                        .connect()
                        .onErrorResume(t -> {
                            tryHandleCurrentClient(pendingClientLatch, isCurrentClientHandled, notConnectedClient);
                            // It's a WebSocketClientHandshakeException when turms-gateway is unavailable
                            if (t instanceof WebSocketClientHandshakeException) {
                                return Mono.empty();
                            }
                            unexpectedError.set(t);
                            // It's a TCP exception no matter turms-gateway is available or not
                            // when the client or server has reach the maximum TCP connections that the system supports
                            if (t instanceof IOException) {
                                // It's an unexpected error and the client or the server should
                                // increase the maximum allowed TCP connections
                                System.err.println("Failed to connect. This may be a TCP related error: " + t);
                            } else {
                                System.err.println("Failed to connect. Unknown error: " + t);
                            }
                            return Mono.empty();
                        })
                        .map(connection -> {
                            WebsocketInbound in = (WebsocketInbound) connection;
                            WebsocketOutbound out = (WebsocketOutbound) connection;
                            in.aggregateFrames()
                                    .receive()
                                    .doOnError(t -> {
                                        // turms-gateway should keep the websocket session even in unavailable status
                                        // the error is mainly caused due to the problem of client itself
                                        unexpectedError.set(t);
                                        tryHandleCurrentClient(pendingClientLatch, isCurrentClientHandled, connectedButNotLoggedInClient);
                                        System.err.println("Failed to receive messages. This may be a client error: " + t);
                                    })
                                    .doOnNext(byteBuf -> {
                                        try {
                                            TurmsNotification notification = TurmsNotification.parseFrom(byteBuf.nioBuffer());
                                            String sessionId = notification.getData().getUserSession().getSessionId();
                                            if (!sessionId.isBlank()) {
                                                tryHandleCurrentClient(pendingClientLatch, isCurrentClientHandled, loggedInClient);
                                                loggedInClient.incrementAndGet();
                                            }
                                        } catch (InvalidProtocolBufferException e) {
                                            unexpectedError.set(e);
                                            System.err.println("Failed to parse data to TurmsNotification. " +
                                                    "Make sure the client version is consistent with the server version: "
                                                    + e);
                                        }
                                    })
                                    .subscribe();
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
                            return in.receiveCloseStatus()
                                    .doOnError(t -> {
                                        tryHandleCurrentClient(pendingClientLatch, isCurrentClientHandled, connectedButNotLoggedInClient);
                                        System.err.println("An connection error occurs: " + t);
                                    })
                                    .doOnSuccess(status -> {
                                        tryHandleCurrentClient(pendingClientLatch, isCurrentClientHandled, connectedButNotLoggedInClient);
                                    })
                                    .onErrorResume(t -> Mono.empty());
                        })
                        .subscribe();
            }
            System.out.println("all clients has sent login requests");

            boolean handleAllRequests = pendingClientLatch.await(1, TimeUnit.MINUTES);
            assertThat(handleAllRequests)
                    .as("turms-gateway should handle all login requests")
                    .isTrue();

            assertThat(unexpectedError.get())
                    .as("No unexpected error occurs")
                    .isNull();

            assertTurmsGatewayAvailable(gatewayAdminHost, gatewayAdminPort);
        }
        System.out.printf("Logged in clients: %d; " +
                        "Connected but not logged in clients: %d; " +
                        "Not Connected clients: %d; " +
                        "Pending clients: %d",
                loggedInClient.get(),
                connectedButNotLoggedInClient.get(),
                notConnectedClient.get(),
                pendingClientLatch.getCount());
    }

    private void tryHandleCurrentClient(CountDownLatch pendingClientLatch,
                                        AtomicBoolean isCurrentClientHandled,
                                        AtomicInteger count) {
        if (isCurrentClientHandled.compareAndSet(false, true)) {
            pendingClientLatch.countDown();
            count.incrementAndGet();
        }
    }

}
