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

package im.turms.server.common.infra.client;

import jakarta.annotation.Nullable;

import reactor.core.publisher.Mono;
import reactor.netty.channel.ChannelOperations;
import reactor.netty.resources.LoopResources;
import reactor.netty.tcp.TcpClient;

import im.turms.server.common.access.client.codec.CodecFactory;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.user.CreateSessionRequest;
import im.turms.server.common.access.client.dto.request.user.DeleteSessionRequest;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.proto.ProtoFormatter;
import im.turms.server.common.infra.random.RandomUtil;

/**
 * @author James Chen
 */
public class TurmsTcpClient extends TurmsClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsTcpClient.class);

    private ChannelOperations<?, ?> connection;
    private long userId;
    private DeviceType deviceType;

    @Override
    public long getUserId() {
        return userId;
    }

    @Override
    public DeviceType getDeviceType() {
        return deviceType;
    }

    @Override
    public boolean isOpen() {
        return !connection.isDisposed();
    }

    @Override
    public Mono<Void> connect(String host, int port, LoopResources loopResources) {
        return TcpClient.newConnection()
                .host(host)
                .port(port)
                .runOn(loopResources)
                .connect()
                .doOnNext(conn -> {
                    connection = (ChannelOperations<?, ?>) conn;

                    connection
                            // Inbound
                            .addHandlerLast("varintLengthBasedFrameDecoder",
                                    CodecFactory.getVarintLengthBasedFrameDecoder())
                            .addHandlerLast("turmsNotificationDecoder",
                                    CodecFactory.getTurmsNotificationDecoder())
                            // Outbound
                            .addHandlerFirst("protobufFrameEncoder",
                                    CodecFactory.getProtobufFrameEncoder())
                            .addHandlerFirst("varintLengthFieldPrepender",
                                    CodecFactory.getVarintLengthFieldPrepender());

                    connection.receiveObject()
                            .cast(TurmsNotification.class)
                            .subscribe(this::handleResponse,
                                    t -> LOGGER.error("The turms client is closed unexpectedly",
                                            t));
                })
                .then();
    }

    @Override
    public Mono<TurmsNotification> login(
            long userId,
            DeviceType deviceType,
            @Nullable String password) {
        this.userId = userId;
        this.deviceType = deviceType;
        return sendRequest(TurmsRequest.newBuilder()
                .setCreateSessionRequest(CreateSessionRequest.newBuilder()
                        .setVersion(1)
                        .setUserId(userId)
                        .setDeviceType(deviceType)
                        .setPassword(password)
                        .build()))
                .flatMap(n -> {
                    if (n.getCode() != ResponseStatusCode.OK.getBusinessCode()) {
                        return Mono.error(new RuntimeException(
                                "Failed to log in: "
                                        + ProtoFormatter.toJSON5(n, 128)));
                    }
                    return Mono.just(n);
                });
    }

    @Override
    public Mono<Void> logout() {
        if (connection == null) {
            return Mono.empty();
        }
        if (connection.isDisposed()) {
            return Mono.empty();
        }
        return sendRequest(TurmsRequest.newBuilder()
                .setDeleteSessionRequest(DeleteSessionRequest.newBuilder()
                        .build()))
                .then();
    }

    @Override
    public Mono<TurmsNotification> sendRequest(TurmsRequest.Builder requestBuilder) {
        if (!requestBuilder.hasRequestId()) {
            requestBuilder.setRequestId(RandomUtil.nextPositiveLong());
        }
        TurmsRequest request = requestBuilder.build();
        return sendRequest(request);
    }

    @Override
    public Mono<TurmsNotification> sendRequest(TurmsRequest request) {
        if (connection == null) {
            return Mono.error(new IllegalStateException("The connection has not established"));
        }
        if (connection.isDisposed()) {
            return Mono.error(new IllegalStateException("The connection has been closed"));
        }
        return connection.sendObject(request)
                .then()
                .then(Mono.defer(() -> waitForResponse(request)));
    }

}
