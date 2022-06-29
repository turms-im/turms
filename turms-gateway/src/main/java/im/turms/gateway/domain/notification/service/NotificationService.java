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
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.domain.notification.service.INotificationService;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.proto.ProtoDecoder;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.infra.validation.Validator;
import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
            TurmsPropertiesManager propertiesManager) {
        this.apiLoggingContext = apiLoggingContext;
        this.sessionService = sessionService;
        this.pluginManager = pluginManager;
        isNotificationLoggingEnabled = propertiesManager
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
                                                  Set<Long> recipientIds,
                                                  Set<UserSessionId> excludedUserSessionIds,
                                                  @Nullable DeviceType excludedDeviceType) {
        Validator.notNull(notificationData, "notificationData");
        Validator.notEmpty(recipientIds, "recipientIds");
        Validator.notNull(excludedUserSessionIds, "excludedUserSessionIds");
        // Prepare data
        boolean hasForwardedMessageToOneRecipient = false;
        boolean triggerHandlers = pluginManager.hasRunningExtensions(NotificationHandler.class);
        Set<Long> offlineRecipientIds = triggerHandlers
                ? CollectionUtil.newSetWithExpectedSize(Math.max(1, recipientIds.size() / 2))
                : Collections.emptySet();

        int notificationSize = notificationData.readableBytes();
        SimpleTurmsNotification notification = isNotificationLoggingEnabled
                ? TurmsNotificationParser.parseSimpleNotification(ProtoDecoder.newInputStream(notificationData))
                : null;

        List<Mono<Void>> monos = new ArrayList<>(recipientIds.size() >> 2);

        // Send notification
        boolean hasExcludedUserSessionIds = !excludedUserSessionIds.isEmpty();
        for (Long recipientId : recipientIds) {
            UserSessionsManager userSessionsManager = sessionService.getUserSessionsManager(recipientId);
            if (userSessionsManager == null) {
                if (triggerHandlers) {
                    offlineRecipientIds.add(recipientId);
                }
            } else {
                for (UserSession userSession : userSessionsManager.getDeviceTypeToSession().values()) {
                    if (excludedDeviceType == userSession.getDeviceType()
                            || (hasExcludedUserSessionIds && excludedUserSessionIds.contains(new UserSessionId(
                            userSession.getUserId(), userSession.getDeviceType())))) {
                        continue;
                    }
                    notificationData.retain();
                    // It's the responsibility of the downstream to decrease the reference count of the notification by 1
                    // when the notification is queued successfully and released by Netty, or fails to be queued.
                    // Otherwise, there is a memory leak.
                    monos.add(userSession.sendNotification(notificationData, tracingContext)
                            .onErrorResume(t -> {
                                if (userSession.isSessionOpen()) {
                                    LOGGER.warn("Failed to send a notification to the session: {}", userSession);
                                }
                                return Mono.empty();
                            }));
                    // Keep the logic simple, and we don't care about whether the notification is really flushed
                    hasForwardedMessageToOneRecipient = true;
                    userSession.getConnection().tryNotifyClientToRecover();
                }
            }
        }

        // Trigger plugins
        if (triggerHandlers) {
            triggerPlugins(notificationData, recipientIds, offlineRecipientIds);
        }

        Mono.whenDelayError(monos)
                .doFinally(signalType -> notificationData.release())
                .subscribe(null, t -> LOGGER.error("Caugh an unexpected error while sending a notification to user sessions", t));

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
            notification = TurmsNotification.parseFrom(ProtoDecoder.newInputStream(notificationData));
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
