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

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.netty.buffer.ByteBuf;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

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
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.PluginManager;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.proto.ProtoDecoder;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.infra.validation.Validator;

/**
 * @author James Chen
 */
@Component
public class NotificationService implements INotificationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationService.class);

    private static final Method HANDLE_METHOD;

    private final ApiLoggingContext apiLoggingContext;
    private final SessionService sessionService;
    private final PluginManager pluginManager;

    private final boolean isNotificationLoggingEnabled;

    static {
        try {
            HANDLE_METHOD = NotificationHandler.class
                    .getDeclaredMethod("handle", TurmsNotification.class, Set.class, Set.class);
        } catch (NoSuchMethodException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    public NotificationService(
            ApiLoggingContext apiLoggingContext,
            SessionService sessionService,
            PluginManager pluginManager,
            TurmsPropertiesManager propertiesManager) {
        this.apiLoggingContext = apiLoggingContext;
        this.sessionService = sessionService;
        this.pluginManager = pluginManager;
        isNotificationLoggingEnabled = propertiesManager.getLocalProperties()
                .getGateway()
                .getNotificationLogging()
                .isEnabled();
    }

    /**
     * @param notificationData should be a buffer of TurmsNotification, and it is always a
     *                         "notification" instead of "response" in fact
     * @return offline recipient IDs
     * @implNote 1. The method ensures notificationData will be released by 1 2. No need to
     *           updateMdc for trace ID here because we know sendNotificationToLocalClients() is in
     *           the tracing scope
     */
    @Override
    public Mono<Set<Long>> sendNotificationToLocalClients(
            TracingContext tracingContext,
            ByteBuf notificationData,
            Set<Long> recipientIds,
            Set<UserSessionId> excludedUserSessionIds,
            @Nullable DeviceType excludedDeviceType) {
        try {
            Validator.notNull(notificationData, "notificationData");
            Validator.notEmpty(recipientIds, "recipientIds");
            Validator.notNull(excludedUserSessionIds, "excludedUserSessionIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        // Prepare data
        int recipientCount = recipientIds.size();
        int halfRecipientCount = recipientCount >> 1;
        Set<Long> offlineRecipientIds =
                CollectionUtil.newConcurrentSetWithExpectedSize(halfRecipientCount);

        List<Mono<Void>> monos = new ArrayList<>(halfRecipientCount);

        // Send notification
        boolean hasExcludedUserSessionIds = !excludedUserSessionIds.isEmpty();
        for (Long recipientId : recipientIds) {
            UserSessionsManager userSessionsManager =
                    sessionService.getUserSessionsManager(recipientId);
            if (userSessionsManager == null) {
                offlineRecipientIds.add(recipientId);
            } else {
                for (UserSession userSession : userSessionsManager.getDeviceTypeToSession()
                        .values()) {
                    if (excludedDeviceType == userSession.getDeviceType()
                            || (hasExcludedUserSessionIds
                                    && excludedUserSessionIds.contains(new UserSessionId(
                                            userSession.getUserId(),
                                            userSession.getDeviceType())))) {
                        continue;
                    }
                    notificationData.retain();
                    // It is the responsibility of the downstream to decrease the reference count of
                    // the notification by 1
                    // when the notification is queued successfully and released by Netty, or fails
                    // to be queued.
                    // Otherwise, a memory leak will occur.
                    monos.add(userSession.sendNotification(notificationData, tracingContext)
                            .onErrorResume(t -> {
                                offlineRecipientIds.add(recipientId);
                                if (userSession.isSessionOpen()) {
                                    return Mono.error(new RuntimeException(
                                            "Failed to send a notification to the user session: "
                                                    + userSession));
                                }
                                return Mono.empty();
                            }));
                    userSession.getConnection()
                            .tryNotifyClientToRecover();
                }
            }
        }
        return Mono.deferContextual(context -> Mono.whenDelayError(monos)
                .doOnEach(signal -> {
                    boolean isOnError = signal.isOnError();
                    if (!signal.isOnComplete() && !isOnError) {
                        return;
                    }
                    if (pluginManager.hasRunningExtensions(NotificationHandler.class)) {
                        invokeExtensionPointHandlers(notificationData,
                                recipientIds,
                                offlineRecipientIds);
                    }
                    SimpleTurmsNotification notification = isNotificationLoggingEnabled
                            ? TurmsNotificationParser.parseSimpleNotification(
                                    ProtoDecoder.newInputStream(notificationData))
                            : null;
                    Throwable t = signal.getThrowable();
                    if (isNotificationLoggingEnabled
                            && apiLoggingContext
                                    .shouldLogNotification(notification.relayedRequestType())) {
                        int offlineRecipientCount = offlineRecipientIds.size();
                        int notificationBytes = notificationData.readableBytes();
                        try (TracingCloseableContext ignored =
                                TracingContext.getCloseableContext(context)) {
                            NotificationLogging.log(notification,
                                    notificationBytes,
                                    recipientCount,
                                    recipientCount - offlineRecipientCount);
                            if (t != null) {
                                LOGGER.error(
                                        "Caught an error while sending a notification to user sessions",
                                        t);
                            }
                        }
                    } else if (t != null) {
                        try (TracingCloseableContext ignored =
                                TracingContext.getCloseableContext(context)) {
                            LOGGER.error(
                                    "Caught an error while sending a notification to user sessions",
                                    t);
                        }
                    }
                })
                .onErrorComplete(t -> true)
                .thenReturn(offlineRecipientIds)
                .doFinally(signalType -> notificationData.release()));
    }

    private void invokeExtensionPointHandlers(
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds,
            @NotNull Set<Long> offlineRecipientIds) {
        TurmsNotification notification;
        try {
            // Note that "parseFrom" won't block because the buffer is fully read
            notification =
                    TurmsNotification.parseFrom(ProtoDecoder.newInputStream(notificationData));
        } catch (Exception e) {
            LOGGER.error(
                    "Failed to parse TurmsNotification. All recipients' ID: {}. Offline recipients' ID: {}",
                    recipientIds,
                    offlineRecipientIds,
                    e);
            return;
        }
        pluginManager
                .invokeExtensionPointsSimultaneously(NotificationHandler.class,
                        HANDLE_METHOD,
                        handler -> handler.handle(notification, recipientIds, offlineRecipientIds))
                .subscribe(null, LOGGER::error);
    }

}
