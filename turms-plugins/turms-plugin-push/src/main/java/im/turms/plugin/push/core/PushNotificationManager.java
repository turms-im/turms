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

package im.turms.plugin.push.core;

import im.turms.plugin.push.core.sender.apns.ApnsSender;
import im.turms.plugin.push.core.sender.fcm.FcmSender;
import im.turms.plugin.push.property.PushNotificationProperties;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Chen
 */
public class PushNotificationManager {

    // Push notification
    public static final String PUSH_NOTIFICATION_REQUEST = "turms.push.request";

    private final ApnsSender apnsSender;
    private final FcmSender fcmSender;
    @Getter
    private final List<String> deviceTokenFieldNames;

    public PushNotificationManager(PushNotificationProperties properties) {
        apnsSender = new ApnsSender(properties.getApns());
        fcmSender = new FcmSender(properties.getFcm());

        String apnsDeviceTokenFieldName = apnsSender.getDeviceTokenFieldName();
        String fcmDeviceTokenFieldName = fcmSender.getDeviceTokenFieldName();
        List<String> names = new ArrayList<>(2);
        if (apnsDeviceTokenFieldName != null) {
            names.add(apnsDeviceTokenFieldName);
        }
        if (fcmDeviceTokenFieldName != null) {
            names.add(fcmDeviceTokenFieldName);
        }
        deviceTokenFieldNames = names;
    }

    // TODO: retry
    public Mono<SendPushNotificationResult> sendNotification(PushNotification pushNotification) {
        PushNotificationSender sender = switch (pushNotification.serviceProvider()) {
            case FCM -> fcmSender;
            case APN -> apnsSender;
        };
        return sender.sendNotification(pushNotification)
                .doOnNext(result -> {
                    Tags tags = Tags.of("token.type", pushNotification.serviceProvider().name(),
                            // We only have this type currently
                            "notification.type", "message",
                            "accepted", String.valueOf(result.accepted()),
                            "unregistered", String.valueOf(result.unregistered()));
                    if (StringUtils.isNotBlank(result.errorCode())) {
                        tags = tags.and("error", result.errorCode());
                    }
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST, tags).increment();
                })
                .doOnError(throwable -> {
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST,
                                    "token.type", pushNotification.serviceProvider().name(),
                                    "notification.type", "message",
                                    "cause", throwable.getClass().getSimpleName())
                            .increment();
                });
    }

    public Mono<Void> close() {
        return Mono.whenDelayError(apnsSender.close(), fcmSender.close());
    }

}