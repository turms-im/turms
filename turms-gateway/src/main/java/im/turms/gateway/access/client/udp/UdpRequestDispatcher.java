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

package im.turms.gateway.access.client.udp;

import java.net.InetSocketAddress;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.access.client.udp.dto.UdpNotification;
import im.turms.gateway.access.client.udp.dto.UdpNotificationType;
import im.turms.gateway.access.client.udp.dto.UdpRequestType;
import im.turms.gateway.access.client.udp.dto.UdpSignalRequest;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.metrics.MetricNameConst;
import im.turms.gateway.infra.thread.ThreadNameConst;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.common.LoopResourcesFactory;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.metrics.TurmsMicrometerChannelMetricsRecorder;
import im.turms.server.common.infra.net.BindException;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.UdpProperties;

/**
 * @author James Chen
 */
@Component
public class UdpRequestDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(UdpRequestDispatcher.class);

    public static UdpRequestDispatcher instance;
    private static final int REQUEST_LENGTH = Long.BYTES + Byte.BYTES * 2 + Integer.BYTES;
    @Getter
    private static boolean isEnabled;

    private final SessionService sessionService;
    private final Sinks.Many<UdpNotification> notificationSink;
    private final Connection connection;

    public UdpRequestDispatcher(
            SessionService sessionService,
            TurmsApplicationContext applicationContext,
            TurmsPropertiesManager propertiesManager) {
        instance = this;
        UdpProperties udpProperties = propertiesManager.getLocalProperties()
                .getGateway()
                .getUdp();
        this.sessionService = sessionService;
        isEnabled = udpProperties.isEnabled();
        String host = udpProperties.getHost();
        int port = udpProperties.getPort();
        if (udpProperties.isEnabled()) {
            notificationSink = Sinks.many()
                    .unicast()
                    .onBackpressureBuffer();
            UdpServer udpServer = UdpServer.create()
                    .host(host)
                    .port(port)
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .runOn(LoopResourcesFactory.createForServer(ThreadNameConst.GATEWAY_UDP_PREFIX))
                    .metrics(true,
                            () -> new TurmsMicrometerChannelMetricsRecorder(
                                    MetricNameConst.CLIENT_NETWORK,
                                    "udp"))
                    .handle((inbound, outbound) -> {
                        Flux<DatagramPacket> responseFlux = inbound.receiveObject()
                                .cast(DatagramPacket.class)
                                .flatMap(packet -> handleDatagramPackage(packet)
                                        .onErrorContinue(
                                                (throwable, o) -> handleExceptionForIncomingPacket(
                                                        throwable))
                                        .map(code -> new DatagramPacket(
                                                UdpSignalResponseBufferPool.get(code),
                                                packet.sender())));
                        Flux<DatagramPacket> notificationFlux = notificationSink.asFlux()
                                .map(notification -> new DatagramPacket(
                                        UdpSignalResponseBufferPool.get(notification.type()),
                                        notification.recipientAddress()));
                        Flux<DatagramPacket> outputFlux = responseFlux.mergeWith(notificationFlux);
                        outbound.sendObject(outputFlux, o -> true)
                                .then()
                                .subscribe(null,
                                        t -> LOGGER.error("Caught an error while sending a packet",
                                                t));
                        return Flux.never();
                    });
            try {
                connection = udpServer.bind()
                        .block();
            } catch (Exception e) {
                String message = "Failed to bind the UDP server on: "
                        + host
                        + ":"
                        + port;
                throw new BindException(message, e);
            }
            LOGGER.info("UDP server started on: {}:{}", host, port);
            applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_GATEWAY_UDP_SERVER,
                    timeoutMillis -> {
                        connection.dispose();
                        return connection.onDispose();
                    });
        } else {
            notificationSink = null;
            connection = null;
        }
    }

    public void sendSignal(InetSocketAddress address, UdpNotificationType signal) {
        if (notificationSink != null) {
            notificationSink.tryEmitNext(new UdpNotification(address, signal));
        }
    }

    private Mono<ResponseStatusCode> handleDatagramPackage(DatagramPacket packet) {
        ByteBuf content = packet.content();
        UdpSignalRequest signalRequest = parseRequest(content);
        content.release();
        InetSocketAddress senderAddress = packet.sender();
        if (signalRequest == null) {
            return Mono.just(ResponseStatusCode.INVALID_REQUEST);
        }
        long userId = signalRequest.userId();
        DeviceType deviceType = signalRequest.deviceType();
        int sessionId = signalRequest.sessionId();
        return switch (signalRequest.type()) {
            case HEARTBEAT -> {
                UserSession session = sessionService
                        .authAndUpdateHeartbeatTimestamp(userId, deviceType, sessionId);
                if (session == null) {
                    yield Mono.just(ResponseStatusCode.SEND_REQUEST_FROM_NON_EXISTING_SESSION);
                }
                // Update the address because it may have changed
                session.getConnection()
                        .setUdpAddress(senderAddress);
                yield Mono.just(ResponseStatusCode.OK);
            }
            case GO_OFFLINE -> {
                CloseReason reason = CloseReason.get(SessionCloseStatus.DISCONNECTED_BY_CLIENT);
                yield sessionService.authAndCloseLocalSession(userId, deviceType, reason, sessionId)
                        .thenReturn(ResponseStatusCode.OK);
            }
        };
    }

    private ResponseStatusCode handleExceptionForIncomingPacket(Throwable throwable) {
        if (throwable instanceof ResponseException exception) {
            ResponseStatusCode code = exception.getCode();
            if (code.isServerError()) {
                LOGGER.error("Failed to handle an incoming package", throwable);
            }
            return code;
        } else {
            LOGGER.error("Failed to handle an incoming package", throwable);
            return ResponseStatusCode.SERVER_INTERNAL_ERROR;
        }
    }

    private UdpSignalRequest parseRequest(ByteBuf byteBuf) {
        boolean isHeartbeatRequest = byteBuf.readableBytes() == REQUEST_LENGTH;
        if (isHeartbeatRequest) {
            UdpRequestType requestType = UdpRequestType.parse(byteBuf.readByte());
            if (requestType == null) {
                return null;
            }
            long userId = byteBuf.readLong();
            int deviceTypeNumber = byteBuf.readByte();
            DeviceType deviceType = DeviceType.forNumber(deviceTypeNumber);
            if (deviceType == null || deviceType == DeviceType.UNRECOGNIZED) {
                return null;
            }
            int sessionId = byteBuf.readInt();
            return new UdpSignalRequest(requestType, userId, deviceType, sessionId);
        }
        return null;
    }

}
