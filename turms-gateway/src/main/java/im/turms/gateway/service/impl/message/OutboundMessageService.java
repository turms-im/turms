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

package im.turms.gateway.service.impl.message;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.gateway.logging.ApiLoggingContext;
import im.turms.gateway.logging.NotificationLogging;
import im.turms.gateway.manager.UserSessionsManager;
import im.turms.gateway.plugin.extension.NotificationHandler;
import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.gateway.pojo.bo.session.UserSession;
import im.turms.gateway.pojo.dto.SimpleTurmsNotification;
import im.turms.gateway.pojo.parser.TurmsNotificationParser;
import im.turms.gateway.service.impl.session.SessionService;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.rpc.service.IOutboundMessageService;
import im.turms.server.common.tracing.TracingContext;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectionUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.RefCntAwareByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * @author James Chen
 */
@Component
@Log4j2
public class OutboundMessageService implements IOutboundMessageService {

    private final Node node;
    private final ApiLoggingContext apiLoggingContext;
    private final SessionService sessionService;
    private final TurmsPluginManager turmsPluginManager;

    private final boolean isNotificationLoggingEnabled;

    public OutboundMessageService(
            Node node,
            ApiLoggingContext apiLoggingContext,
            SessionService sessionService,
            TurmsPluginManager turmsPluginManager,
            TurmsPropertiesManager turmsPropertiesManager) {
        this.node = node;
        this.apiLoggingContext = apiLoggingContext;
        this.sessionService = sessionService;
        this.turmsPluginManager = turmsPluginManager;
        isNotificationLoggingEnabled = turmsPropertiesManager
                .getLocalProperties().getGateway().getNotificationLogging().isEnabled();
    }

    /**
     * @param notificationData should be a buffer of TurmsNotification, and it's
     *                         always a "notification" instead of "response" in fact
     * @return true if the notification is ready to forward or has forwarded
     * to one recipient at least
     * @implNote 1. The method ensures notificationData will be released by 1
     * 2. No need to updateMdc for trace ID here
     * because we know sendNotificationToLocalClients() is in the tracing scope
     */
    @Override
    public boolean sendNotificationToLocalClients(TracingContext tracingContext,
                                                  ByteBuf notificationData,
                                                  Set<Long> recipientIds) {
        AssertUtil.notNull(notificationData, "notificationData");
        AssertUtil.notEmpty(recipientIds, "recipientIds");
        // Prepare data
        boolean hasForwardedMessageToOneRecipient = false;
        boolean triggerHandlers = node.getSharedProperties().getPlugin().isEnabled()
                && !turmsPluginManager.getNotificationHandlerList().isEmpty();
        Set<Long> offlineRecipientIds = triggerHandlers
                ? CollectionUtil.newSetWithExpectedSize(Math.max(1, recipientIds.size() / 2))
                : Collections.emptySet();

        int notificationSize = notificationData.readableBytes();
        SimpleTurmsNotification notification = isNotificationLoggingEnabled
                ? TurmsNotificationParser.parseSimpleNotification(notificationData.nioBuffer())
                : null;

        // RefCntAwareByteBuf is used to release the buffer to 0
        // because when the method returns, Netty may not flush the buffer to recipients
        RefCntAwareByteBuf wrappedNotificationData = new RefCntAwareByteBuf(notificationData, notificationData::release);

        wrappedNotificationData.startRetainCounter();

        // Send notification
        for (Long recipientId : recipientIds) {
            UserSessionsManager userSessionsManager = sessionService.getUserSessionsManager(recipientId);
            if (userSessionsManager != null) {
                for (UserSession userSession : userSessionsManager.getSessionMap().values()) {
                    wrappedNotificationData.retain();
                    // It's the responsibility of the downstream to decrease the reference count of the notification by 1
                    // when the notification is queued successfully and released by Netty, or fails to be queued.
                    // Otherwise, there is a memory leak
                    try {
                        userSession.sendNotification(wrappedNotificationData, tracingContext);
                    } catch (Exception e) {
                        if (userSession.isSessionOpen()) {
                            log.warn("Failed to send a notification to the session: {}", userSession);
                        }
                    }
                    // Keep the logic easy, and we don't care about whether the notification is really flushed
                    hasForwardedMessageToOneRecipient = true;
                    userSession.getConnection().tryNotifyClientToRecover();
                }
            } else {
                if (triggerHandlers) {
                    offlineRecipientIds.add(recipientId);
                }
            }
        }

        wrappedNotificationData.stopRetainCounter();

        // Trigger plugins
        if (triggerHandlers) {
            triggerPlugins(wrappedNotificationData, recipientIds, offlineRecipientIds);
        }

        if (isNotificationLoggingEnabled
                && apiLoggingContext.shouldLogNotification(notification.relayedRequestType())) {
            NotificationLogging.log(hasForwardedMessageToOneRecipient,
                    notification,
                    notificationSize,
                    recipientIds.size());
        }

        return hasForwardedMessageToOneRecipient;
    }

    private void triggerPlugins(@NotNull ByteBuf notificationData,
                                @NotNull Set<Long> recipientIds,
                                @NotNull Set<Long> offlineRecipientIds) {
        TurmsNotification notification = null;
        try {
            // Note that "parseFrom" won't block because the buffer is fully read
            notification = TurmsNotification.parseFrom(notificationData.nioBuffer());
        } catch (Exception e) {
            log.error("Failed to parse TurmsNotification", e);
        }
        if (notification == null) {
            return;
        }
        List<NotificationHandler> handlerList = turmsPluginManager.getNotificationHandlerList();
        int size = handlerList.size();
        if (size <= 0) {
            return;
        }
        Mono<Void> resultMono;
        if (size == 1) {
            resultMono = handlerList.get(0)
                    .handle(notification, recipientIds, offlineRecipientIds);
        } else {
            List<Mono<Void>> monos = new ArrayList<>(size);
            for (NotificationHandler handler : handlerList) {
                monos.add(handler.handle(notification, recipientIds, offlineRecipientIds));
            }
            resultMono = Mono.when(monos);
        }
        resultMono
                .onErrorResume(t -> {
                    log.error("Plugins failed to handle", t);
                    return Mono.empty();
                })
                .subscribe();
    }

}
