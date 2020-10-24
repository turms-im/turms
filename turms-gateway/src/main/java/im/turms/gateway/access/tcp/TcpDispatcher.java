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

package im.turms.gateway.access.tcp;

import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.gateway.access.tcp.codec.TurmsProtobufVarint32FrameDecoder;
import im.turms.gateway.access.tcp.controller.SessionController;
import im.turms.gateway.access.tcp.dto.RequestHandlerResult;
import im.turms.gateway.access.tcp.factory.TcpServerFactory;
import im.turms.gateway.access.tcp.model.UserSessionWrapper;
import im.turms.gateway.access.tcp.util.TurmsNotificationUtil;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.dto.SimpleTurmsRequest;
import im.turms.gateway.service.mediator.WorkflowMediator;
import im.turms.gateway.util.TurmsRequestUtil;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.pojo.CloseReason;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.util.CloseReasonUtil;
import im.turms.server.common.util.ProtoUtil;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.MonoSink;
import reactor.core.publisher.Sinks;
import reactor.netty.Connection;
import reactor.netty.DisposableServer;
import reactor.util.concurrent.Queues;

import javax.annotation.PreDestroy;
import java.net.InetSocketAddress;
import java.util.function.BiConsumer;

/**
 * @author James Chen
 */
@Log4j2
@Component
public class TcpDispatcher {

    private static final ByteBuf HEARTBEAT_RESPONSE = TurmsProtobufVarint32FrameDecoder.HEARTBEAT_DATA;

    private final DisposableServer server;
    private final WorkflowMediator workflowMediator;
    private final SessionController sessionController;

    private final BiConsumer<Connection, MonoSink<Void>> connectionHandler = (connection, completeSink) -> {
        InetSocketAddress address = (InetSocketAddress) connection.address();
        String ip = address.getHostString();
        Sinks.Many<ByteBuf> outputSink = Sinks.many()
                .unicast()
                .onBackpressureBuffer(Queues.<ByteBuf>unbounded(64).get());
        UserSessionWrapper sessionWrapper = new UserSessionWrapper(connection, outputSink, completeSink, true);

        connection.inbound()
                .receive()
                .doOnNext(data -> {
                    if (sessionWrapper.isAvailable()) {
                        handleRequestData(sessionWrapper, data, ip)
                                .doOnError(throwable -> handleExceptionForOutput(sessionWrapper, throwable))
                                .doOnNext(outputSink::tryEmitNext)
                                .subscribe();
                    }
                });

        Flux<ByteBuf> outputFlux = outputSink.asFlux()
                .doOnError(throwable -> handleExceptionForOutput(sessionWrapper, throwable))
                .doOnComplete(sessionWrapper::close);
        connection.outbound()
                .send(outputFlux, byteBuf -> true)
                .then()
                .subscribe();
    };

    public TcpDispatcher(WorkflowMediator workflowMediator,
                         TurmsPropertiesManager propertiesManager,
                         SessionController sessionController) {
        this.workflowMediator = workflowMediator;
        this.sessionController = sessionController;
        server = TcpServerFactory.create(propertiesManager, (inbound, outbound) ->
                Mono.create(completeSink -> inbound.withConnection(connection -> connectionHandler.accept(connection, completeSink))));
    }

    @PreDestroy
    public void preDestroy() {
        if (server != null) {
            server.disposeNow();
        }
    }

    private Mono<ByteBuf> handleRequestData(UserSessionWrapper sessionWrapper, ByteBuf data, String ip) {
        if (data.isReadable()) {
            SimpleTurmsRequest request = TurmsRequestUtil.parseSimpleRequest(data.nioBuffer());
            TurmsRequest.KindCase requestType = request.getType();
            Mono<TurmsNotification> notificationMono;
            switch (requestType) {
                case CREATE_SESSION_REQUEST:
                    notificationMono = sessionController.handleCreateSessionRequest(sessionWrapper, request.getCreateSessionRequest(), ip)
                            .map(result -> getNotificationFromHandlerResult(result, request.getRequestId()));
                    break;
                case DELETE_SESSION_REQUEST:
                    notificationMono = sessionController.handleDeleteSessionRequest(sessionWrapper, request.getRequestId());
                    break;
                default:
                    notificationMono = handleServiceRequest(sessionWrapper, request, data);
                    break;
            }
            return notificationMono.map(ProtoUtil::getDirectByteBuffer);
        } else {
            return handleHeartbeatRequest(sessionWrapper)
                    .flatMap(updated -> updated ? Mono.just(HEARTBEAT_RESPONSE) : Mono.empty());
        }
    }

    private Mono<Boolean> handleHeartbeatRequest(UserSessionWrapper sessionWrapper) {
        UserSession session = sessionWrapper.getUserSession();
        if (session != null) {
            return workflowMediator.processHeartbeatRequest(session.getUserId(), session.getDeviceType());
        } else {
            return Mono.just(false);
        }
    }

    private Mono<TurmsNotification> handleServiceRequest(UserSessionWrapper sessionWrapper, SimpleTurmsRequest request, ByteBuf data) {
        UserSession session = sessionWrapper.getUserSession();
        if (session == null) {
            return Mono.just(TurmsNotificationUtil.sessionClosed(request.getRequestId()));
        }
        ServiceRequest serviceRequest = new ServiceRequest(session.getUserId(),
                session.getDeviceType(),
                request.getRequestId(),
                request.getType(),
                data);
        return workflowMediator.processServiceRequest(serviceRequest);
    }

    private TurmsNotification getNotificationFromHandlerResult(RequestHandlerResult result, long requestId) {
        TurmsNotification.Builder builder = TurmsNotification.newBuilder()
                .setRequestId(Int64Value.newBuilder().setValue(requestId).build())
                .setCode(Int32Value.newBuilder().setValue(result.getCode().getBusinessCode()).build());
        String reason = result.getReason();
        if (reason != null) {
            builder.setReason(StringValue.newBuilder().setValue(result.getReason()).build());
        }
        return builder.build();
    }

    private void handleExceptionForOutput(UserSessionWrapper sessionWrapper, Throwable throwable) {
        CloseReason closeReason = CloseReasonUtil.parse(throwable);
        TurmsStatusCode code = closeReason.getCode();
        String reason = closeReason.getReason();
        if (code.isServerError()) {
            log.error("Failed to send outbound notification", throwable);
        }
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setCode(Int32Value.newBuilder().setValue(code.getBusinessCode()).build());
        if (reason != null) {
            builder.setReason(StringValue.newBuilder().setValue(reason).build());
        }
        // Note: don't recover even if it's just a client error
        TurmsNotification closeNotification = builder.build();
        sessionWrapper.setOpen(false);
        sessionWrapper.getConnection()
                .outbound()
                .sendObject(closeNotification)
                .then()
                .subscribe(unused -> sessionWrapper.close());
    }

}