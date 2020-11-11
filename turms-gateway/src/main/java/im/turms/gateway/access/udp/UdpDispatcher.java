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

package im.turms.gateway.access.udp;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.model.dto.udpsignal.UdpNotificationType;
import im.turms.common.model.dto.udpsignal.UdpRequestType;
import im.turms.common.model.dto.udpsignal.UdpSignalRequest;
import im.turms.gateway.access.udp.dto.UdpNotification;
import im.turms.gateway.access.udp.dto.UdpSignalResponseBufferPool;
import im.turms.gateway.access.websocket.dto.CloseStatusFactory;
import im.turms.gateway.service.mediator.WorkflowMediator;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.UdpProperties;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelOption;
import io.netty.channel.socket.DatagramPacket;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.socket.CloseStatus;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.udp.UdpServer;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;

/**
 * @author James Chen
 */
@Log4j2
@Component
public class UdpDispatcher {

    public static UdpDispatcher instance;
    private static final int REQUEST_LENGTH = Long.BYTES + Byte.BYTES * 2 + Integer.BYTES;
    @Getter
    private static boolean isEnabled;

    private final WorkflowMediator workflowMediator;
    private final Sinks.Many<UdpNotification> notificationSink;
    private final Connection connection;

    public UdpDispatcher(WorkflowMediator workflowMediator, TurmsPropertiesManager propertiesManager) {
        instance = this;
        UdpProperties udpProperties = propertiesManager.getLocalProperties().getGateway().getUdp();
        this.workflowMediator = workflowMediator;
        isEnabled = udpProperties.isEnabled();
        if (udpProperties.isEnabled()) {
            notificationSink = Sinks.many().unicast().onBackpressureBuffer();
            connection = UdpServer.create()
                    .option(ChannelOption.SO_REUSEADDR, true)
                    .host(udpProperties.getHost())
                    .port(udpProperties.getPort())
                    .handle((inbound, outbound) -> {
                        Flux<DatagramPacket> responseFlux = inbound.receiveObject()
                                .cast(DatagramPacket.class)
                                .flatMap(packet -> handleDatagramPackage(packet)
                                        .onErrorContinue((throwable, o) -> handleExceptionForIncomingPacket(throwable))
                                        .map(code -> new DatagramPacket(UdpSignalResponseBufferPool.get(code), packet.sender())));
                        Flux<DatagramPacket> notificationFlux = notificationSink.asFlux()
                                .map(notification -> new DatagramPacket(UdpSignalResponseBufferPool.get(notification.getType()), notification.getRecipientAddress()));
                        Flux<DatagramPacket> outputFlux = responseFlux.mergeWith(notificationFlux);
                        outbound.sendObject(outputFlux, o -> true)
                                .then()
                                .subscribe();
                        return Flux.never();
                    })
                    .bind()
                    .block();
        } else {
            notificationSink = null;
            connection = null;
        }
    }

    @PreDestroy
    public void preDestroy() {
        if (connection != null) {
            connection.disposeNow();
        }
    }

    public void sendSignal(InetSocketAddress address, UdpNotificationType signal) {
        if (notificationSink != null) {
            notificationSink.tryEmitNext(new UdpNotification(address, signal));
        }
    }

    private Mono<TurmsStatusCode> handleDatagramPackage(DatagramPacket packet) {
        ByteBuf content = packet.content();
        UdpSignalRequest signalRequest = parseRequest(content);
        content.release();
        InetSocketAddress senderAddress = packet.sender();
        if (signalRequest != null) {
            long userId = signalRequest.getUserId();
            DeviceType deviceType = signalRequest.getDeviceType();
            int sessionId = signalRequest.getSessionId();
            switch (signalRequest.getType()) {
                case HEARTBEAT:
                    return workflowMediator.authAndProcessHeartbeatRequest(userId, deviceType, sessionId)
                            .map(session -> {
                                // Update the address because it may has changed
                                session.getConnection().setAddress(senderAddress);
                                return TurmsStatusCode.OK;
                            })
                            .onErrorReturn(TurmsStatusCode.FAILED)
                            .defaultIfEmpty(TurmsStatusCode.FAILED);
                case GO_OFFLINE:
                    CloseStatus status = CloseStatusFactory.get(SessionCloseStatus.DISCONNECTED_BY_CLIENT);
                    return workflowMediator.authAndSetLocalUserDeviceOffline(userId, deviceType, status, sessionId)
                            .thenReturn(TurmsStatusCode.OK);
                default:
                    throw new IllegalStateException("Unexpected value: " + signalRequest.getType());
            }
        } else {
            return Mono.just(TurmsStatusCode.INVALID_DATA);
        }
    }

    private TurmsStatusCode handleExceptionForIncomingPacket(Throwable throwable) {
        if (throwable instanceof TurmsBusinessException) {
            TurmsBusinessException exception = (TurmsBusinessException) throwable;
            TurmsStatusCode code = exception.getCode();
            if (code.isServerError()) {
                log.error("Failed to handle incoming package", throwable);
            }
            return code;
        } else {
            log.error("Failed to handle incoming package", throwable);
            return TurmsStatusCode.SERVER_INTERNAL_ERROR;
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
