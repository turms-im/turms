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

package im.turms.service.infra.push;

import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.env.push.PushNotificationProperties;
import im.turms.service.infra.push.sender.apns.ApnsSender;
import im.turms.service.infra.push.sender.fcm.FcmSender;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import static im.turms.service.infra.metrics.MetricNameConst.PUSH_NOTIFICATION_REQUEST;

/**
 * @author James Chen
 */
@Component
public class PushNotificationManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(PushNotificationManager.class);

    private final ApnsSender apnSender;
    private final FcmSender fcmSender;

    public PushNotificationManager(TurmsApplicationContext applicationContext, TurmsPropertiesManager propertiesManager) {
        PushNotificationProperties pushNotificationProperties = propertiesManager.getLocalProperties()
                .getService()
                .getPushNotification();
        apnSender = new ApnsSender(pushNotificationProperties.getApns());
        fcmSender = new FcmSender(pushNotificationProperties.getFcm());
        applicationContext.addShutdownHook(JobShutdownOrder.CLOSE_PUSH_NOTIFICATION, timeoutMillis -> close());
    }

    public void sendNotification(PushNotification pushNotification) {
        PushNotificationSender sender = switch (pushNotification.deviceTokenType()) {
            case FCM -> fcmSender;
            case APN, APN_VOIP -> apnSender;
        };
        sender.sendNotification(pushNotification)
                .subscribe(result -> {
                    Tags tags = Tags.of("token.type", pushNotification.deviceTokenType().name(),
                            "notification.type", pushNotification.notificationType().name(),
                            "accepted", String.valueOf(result.accepted()),
                            "unregistered", String.valueOf(result.unregistered()));
                    if (StringUtils.isNotBlank(result.errorCode())) {
                        tags = tags.and("error", result.errorCode());
                    }
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST, tags).increment();
                }, throwable -> {
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST,
                                    "token.type", pushNotification.deviceTokenType().name(),
                                    "notification.type", pushNotification.notificationType().name(),
                                    "cause", throwable.getClass().getSimpleName())
                            .increment();
                    LOGGER.error("Failed to deliver the push notification: {}", pushNotification.toStringWithoutDate(), throwable);
                });
    }

    private Mono<Void> close() {
        return Mono.whenDelayError(apnSender.close(), fcmSender.close());
    }

}