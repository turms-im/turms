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

package im.turms.plugin.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.validation.constraints.NotNull;

import reactor.core.publisher.Mono;
import reactor.util.context.ContextView;

import im.turms.plugin.push.core.PushNotification;
import im.turms.plugin.push.core.PushNotificationManager;
import im.turms.plugin.push.core.PushNotificationServiceProvider;
import im.turms.plugin.push.core.PushNotificationType;
import im.turms.plugin.push.property.PushNotificationProperties;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.access.client.dto.request.message.CreateMessageRequest;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.plugin.ExtensionPointMethod;
import im.turms.server.common.infra.plugin.TurmsExtension;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.service.access.servicerequest.dto.RequestHandlerResult;
import im.turms.service.domain.user.service.UserService;
import im.turms.service.infra.plugin.extension.RequestHandlerResultHandler;

/**
 * @author James Chen
 */
public class NotificationPusher extends TurmsExtension implements RequestHandlerResultHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(NotificationPusher.class);

    private PushNotificationManager manager;
    private UserService userService;
    private UserStatusService userStatusService;

    private Set<String> deviceTokenFieldNames;
    private Map<String, PushNotificationServiceProvider> deviceTokenFieldNameToServiceProvider;

    @Override
    protected Mono<Void> start() {
        PushNotificationProperties properties = loadProperties(PushNotificationProperties.class);
        manager = new PushNotificationManager(properties);
        userService = getContext().getBean(UserService.class);
        userStatusService = getContext().getBean(UserStatusService.class);

        deviceTokenFieldNameToServiceProvider = manager.getDeviceTokenFieldNameToServiceProvider();
        deviceTokenFieldNames = deviceTokenFieldNameToServiceProvider.keySet();
        return Mono.empty();
    }

    @Override
    protected Mono<Void> stop() {
        return manager.close();
    }

    @ExtensionPointMethod
    @Override
    public Mono<RequestHandlerResult> afterNotify(
            @NotNull RequestHandlerResult result,
            @NotNull Long requesterId,
            @NotNull DeviceType requesterDevice,
            @NotNull Set<Long> offlineRecipientIds) {
        Mono<RequestHandlerResult> handlerResultMono = Mono.just(result);
        TurmsRequest request = result.notifications()
                .getFirst()
                .notification();
        if (request == null
                || offlineRecipientIds.isEmpty()
                || deviceTokenFieldNameToServiceProvider.isEmpty()) {
            return handlerResultMono;
        }
        // TODO: support other types
        if (request.getKindCase() != TurmsRequest.KindCase.CREATE_MESSAGE_REQUEST) {
            return handlerResultMono;
        }
        CreateMessageRequest createMessageRequest = request.getCreateMessageRequest();
        if (!createMessageRequest.hasText()) {
            return handlerResultMono;
        }
        String text = createMessageRequest.getText();
        return Mono.deferContextual(context -> {
            userService.queryUserName(requesterId)
                    .onErrorComplete(t -> {
                        try (TracingCloseableContext ignored =
                                TracingContext.getCloseableContext(context)) {
                            LOGGER.error("Failed to get the requester ID", t);
                        }
                        return true;
                    })
                    .doOnNext(name -> userStatusService
                            .fetchDeviceDetails(offlineRecipientIds, deviceTokenFieldNames)
                            .onErrorComplete(t -> {
                                try (TracingCloseableContext ignored =
                                        TracingContext.getCloseableContext(context)) {
                                    LOGGER.error(
                                            "Failed to fetch offline recipients' device details",
                                            t);
                                }
                                return true;
                            })
                            .doOnNext(recipientIdToDetails -> sendNotification(context,
                                    recipientIdToDetails,
                                    text,
                                    request,
                                    requesterId,
                                    requesterDevice,
                                    name))
                            .subscribe())
                    .subscribe();
            return handlerResultMono;
        });
    }

    /**
     * TODO: Support request timeout
     */
    private void sendNotification(
            ContextView context,
            Map<Long, Map<String, String>> recipientIdToDetails,
            String text,
            TurmsRequest request,
            Long requesterId,
            DeviceType requesterDevice,
            String requesterName) {
        Set<Map.Entry<Long, Map<String, String>>> entries = recipientIdToDetails.entrySet();
        int count = entries.size() << 2;
        List<Mono<Void>> monos = new ArrayList<>(count);
        for (Map.Entry<Long, Map<String, String>> recipientAndDeviceDetail : entries) {
            Map<String, String> deviceDetails = recipientAndDeviceDetail.getValue();
            for (Map.Entry<String, String> detail : deviceDetails.entrySet()) {
                String providerStr = detail.getKey();
                PushNotificationServiceProvider provider =
                        deviceTokenFieldNameToServiceProvider.get(providerStr);
                if (provider == null) {
                    continue;
                }
                PushNotification notification = new PushNotification(
                        // TODO: We have only this type currently
                        PushNotificationType.SEND_MESSAGE,
                        provider,
                        detail.getValue(),
                        requesterName,
                        text,
                        null);
                Mono<Void> mono = manager
                        .sendNotification(notification,
                                request,
                                requesterId,
                                requesterDevice,
                                requesterName,
                                deviceDetails)
                        .onErrorComplete(t -> {
                            try (TracingCloseableContext ignored =
                                    TracingContext.getCloseableContext(context)) {
                                LOGGER.error(
                                        "Caught an error while delivering the push notification: {}",
                                        notification.toStringWithoutDate(),
                                        t);
                            }
                            return true;
                        });
                monos.add(mono);
            }
        }
        Mono.whenDelayError(monos)
                .subscribe();
    }

}