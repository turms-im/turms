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

package im.turms.gateway.domain.notification.service;

import im.turms.gateway.access.client.common.UserSession;
import im.turms.gateway.domain.session.manager.UserSessionsManager;
import im.turms.gateway.domain.session.service.SessionService;
import im.turms.gateway.infra.logging.ApiLoggingContext;
import im.turms.gateway.infra.logging.NotificationLogging;
import im.turms.gateway.infra.plugin.extension.NotificationHandler;
import im.turms.gateway.infra.proto.SimpleTurmsNotification;
import im.turms.gateway.infra.proto.TurmsNotificationParser;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.domain.notification.service.INotificationService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.infra.validation.Validator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.RefCntAwareByteBuf;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Set;

/**
 * @author James Chen
 */
@Component
public class NotificationService implements INotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private final ApiLoggingContext apiLoggingContext;
    private final SessionService sessionService;
    private final PluginManager pluginManager;

    private final boolean isNotificationLoggingEnabled;

    public NotificationService(
            ApiLoggingContext apiLoggingContext,
            SessionService sessionService,
            PluginManager pluginManager,
            TurmsPropertiesManager turmsPropertiesManager) {
        this.apiLoggingContext = apiLoggingContext;
        this.sessionService = sessionService;
        this.pluginManager = pluginManager;
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
        Validator.notNull(notificationData, "notificationData");
        Validator.notEmpty(recipientIds, "recipientIds");
        // Prepare data
        boolean hasForwardedMessageToOneRecipient = false;
        boolean triggerHandlers = pluginManager.hasRunningExtensions(NotificationHandler.class);
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
            if (userSessionsManager == null) {
                if (triggerHandlers) {
                    offlineRecipientIds.add(recipientId);
                }
            } else {
                for (UserSession userSession : userSessionsManager.getDeviceTypeToSession().values()) {
                    wrappedNotificationData.retain();
                    // It's the responsibility of the downstream to decrease the reference count of the notification by 1
                    // when the notification is queued successfully and released by Netty, or fails to be queued.
                    // Otherwise, there is a memory leak
                    try {
                        userSession.sendNotification(wrappedNotificationData, tracingContext);
                    } catch (Exception e) {
                        if (userSession.isSessionOpen()) {
                            LOGGER.warn("Failed to send a notification to the session: {}", userSession);
                        }
                    }
                    // Keep the logic easy, and we don't care about whether the notification is really flushed
                    hasForwardedMessageToOneRecipient = true;
                    userSession.getConnection().tryNotifyClientToRecover();
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
        TurmsNotification notification;
        try {
            // Note that "parseFrom" won't block because the buffer is fully read
            notification = TurmsNotification.parseFrom(notificationData.nioBuffer());
        } catch (Exception e) {
            LOGGER.error("Failed to parse TurmsNotification", e);
            return;
        }
        pluginManager.invokeExtensionPoints(NotificationHandler.class,
                        "handle",
                        handler -> handler.handle(notification, recipientIds, offlineRecipientIds))
                .subscribe(null, LOGGER::error);
    }

}
