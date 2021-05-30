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

package im.turms.gateway.access.common.controller;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.common.util.RandomUtil;
import im.turms.gateway.access.common.model.UserSessionWrapper;
import im.turms.gateway.access.tcp.dto.RequestHandlerResult;
import im.turms.gateway.access.tcp.util.TurmsNotificationUtil;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.dto.SimpleTurmsRequest;
import im.turms.gateway.service.mediator.ServiceMediator;
import im.turms.gateway.util.TurmsRequestUtil;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.exception.ThrowableInfo;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.factory.NotificationFactory;
import im.turms.server.common.manager.ServerStatusManager;
import im.turms.server.common.util.ProtoUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.EmptyByteBuf;
import io.netty.buffer.UnpooledByteBufAllocator;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author James Chen
 */
@Component
@Log4j2
public class UserRequestDispatcher {

    private static final ByteBuf HEARTBEAT_RESPONSE = new EmptyByteBuf(UnpooledByteBufAllocator.DEFAULT);

    private final SessionController sessionController;
    private final ServiceMediator serviceMediator;
    private final ServerStatusManager serverStatusManager;

    public UserRequestDispatcher(SessionController sessionController,
                                 ServiceMediator serviceMediator,
                                 ServerStatusManager serverStatusManager) {
        this.sessionController = sessionController;
        this.serviceMediator = serviceMediator;
        this.serverStatusManager = serverStatusManager;
    }

    /**
     * @implNote If a throwable instance is thrown due to the failure of handling the client request,
     * the method should recover it to TurmsNotification.
     * In other words, the method should never return MonoError and it should be considered as a bug if it occurs.
     */
    public Mono<ByteBuf> handleRequest(UserSessionWrapper sessionWrapper, ByteBuf data) {
        if (!serverStatusManager.isActive()) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.SERVER_UNAVAILABLE));
        }
        if (!data.isReadable()) {
            return handleHeartbeatRequest(sessionWrapper);
        }
        SimpleTurmsRequest request = TurmsRequestUtil.parseSimpleRequest(data.nioBuffer());
        TurmsRequest.KindCase requestType = request.getType();
        Mono<TurmsNotification> notificationMono = switch (requestType) {
            case CREATE_SESSION_REQUEST -> sessionController
                    .handleCreateSessionRequest(sessionWrapper, request.getCreateSessionRequest())
                    .map(result -> getNotificationFromHandlerResult(result, request.getRequestId()));
            case DELETE_SESSION_REQUEST -> sessionController.handleDeleteSessionRequest(sessionWrapper);
            default -> handleServiceRequest(sessionWrapper, request, data);
        };
        return notificationMono
                .doOnNext(notification -> {
                    if (TurmsStatusCode.isServerError(notification.getCode())) {
                        log.error("Got a response with a server error code: {} to the request: {}", notification, request);
                    }
                })
                .onErrorResume(throwable -> {
                    ThrowableInfo info = ThrowableInfo.get(throwable);
                    if (info.getCode().isServerError()) {
                        log.error("Failed to handle the service request: {}", request, throwable);
                    }
                    return Mono.just(NotificationFactory.fromThrowable(info, request.getRequestId()));
                })
                .map(ProtoUtil::getDirectByteBuffer);
    }

    private Mono<ByteBuf> handleHeartbeatRequest(UserSessionWrapper sessionWrapper) {
        UserSession session = sessionWrapper.getUserSession();
        ByteBuf data;
        if (session != null) {
            serviceMediator.processHeartbeatRequest(session);
            data = HEARTBEAT_RESPONSE;
        } else {
            RequestHandlerResult result = new RequestHandlerResult(TurmsStatusCode.UPDATE_NON_EXISTING_SESSION_HEARTBEAT);
            // TODO: check -1
            TurmsNotification notification = getNotificationFromHandlerResult(result, -1);
            data = ProtoUtil.getDirectByteBuffer(notification);
        }
        return Mono.just(data);
    }

    private Mono<TurmsNotification> handleServiceRequest(UserSessionWrapper sessionWrapper, SimpleTurmsRequest request, ByteBuf data) {
        UserSession session = sessionWrapper.getUserSession();
        if (session == null || !session.isOpen()) {
            return Mono.just(TurmsNotificationUtil.sessionClosed(request.getRequestId()));
        }
        ServiceRequest serviceRequest = new ServiceRequest(
                sessionWrapper.getIp().getAddress().getAddress(),
                session.getUserId(),
                session.getDeviceType(),
                RandomUtil.nextPositiveLong(),
                request.getRequestId(),
                request.getType(),
                data);
        return serviceMediator.processServiceRequest(serviceRequest);
    }

    private TurmsNotification getNotificationFromHandlerResult(RequestHandlerResult result, long requestId) {
        TurmsNotification.Builder builder = TurmsNotification.newBuilder()
                .setRequestId(requestId)
                .setCode(result.getCode().getBusinessCode());
        String reason = result.getReason();
        if (reason != null) {
            builder.setReason(reason);
        }
        return builder.build();
    }

}