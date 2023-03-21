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
import im.turms.plugin.push.property.ApnsProperties;
import im.turms.plugin.push.property.FcmProperties;
import im.turms.plugin.push.property.PushNotificationProperties;
import im.turms.server.common.infra.exception.FeatureDisabledException;
import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import java.time.Duration;
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
    private final boolean isApnsEnabled;
    private final boolean isFcmEnabled;

    private volatile boolean isClosed;

    // TODO: make configurable
    private final RetryBackoffSpec retrySpec = Retry.backoff(2, Duration.ofMillis(1))
            .filter(throwable -> throwable instanceof SendPushNotificationException exception
                    && exception.getErrorCode().isRetryable()
                    && !isClosed);

    public PushNotificationManager(PushNotificationProperties properties) {
        ApnsProperties apnsProperties = properties.getApns();
        FcmProperties fcmProperties = properties.getFcm();
        isApnsEnabled = apnsProperties.isEnabled();
        isFcmEnabled = fcmProperties.isEnabled();
        apnsSender = isApnsEnabled
                ? new ApnsSender(apnsProperties)
                : null;
        fcmSender = isFcmEnabled
                ? new FcmSender(fcmProperties)
                : null;

        List<String> names = new ArrayList<>(2);
        if (isApnsEnabled) {
            names.add(apnsSender.getDeviceTokenFieldName());
        }
        if (isFcmEnabled) {
            names.add(fcmSender.getDeviceTokenFieldName());
        }
        deviceTokenFieldNames = names;
    }

    public Mono<Void> sendNotification(PushNotification pushNotification) {
        PushNotificationSender sender;
        switch (pushNotification.serviceProvider()) {
            case FCM -> {
                if (isFcmEnabled) {
                    sender = fcmSender;
                } else {
                    return Mono.error(new FeatureDisabledException("FCM is disabled"));
                }
            }
            case APN -> {
                if (isApnsEnabled) {
                    sender = apnsSender;
                } else {
                    return Mono.error(new FeatureDisabledException("APNs is disabled"));
                }
            }
            default -> {
                return Mono.error(new RuntimeException("Unknown push service provider: " + pushNotification.serviceProvider()));
            }
        }
        return sender.sendNotification(pushNotification)
                .doOnSuccess(unused -> {
                    Tags tags = Tags.of("provider", pushNotification.serviceProvider().name(),
                            // We only have this type currently
                            "type", "SEND_MESSAGE",
                            "accepted", "true");
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST, tags).increment();
                })
                .doOnError(throwable -> {
                    PushNotificationErrorCode errorCode = null;
                    String serviceErrorCode = null;
                    String cause = null;
                    if (throwable instanceof SendPushNotificationException exception) {
                        errorCode = exception.getErrorCode();
                        serviceErrorCode = exception.getServiceErrorCode();
                        Throwable unwrappedCause = exception.getCause();
                        if (unwrappedCause != null) {
                            cause = unwrappedCause.getClass().getSimpleName();
                        }
                    } else {
                        cause = throwable.getClass().getSimpleName();
                    }
                    Tags tags = Tags.of("provider", pushNotification.serviceProvider().name(),
                            "type", "SEND_MESSAGE",
                            "accepted", "false");
                    if (errorCode != null) {
                        tags = tags.and("code", String.valueOf(errorCode.getCode()));
                    }
                    if (serviceErrorCode != null) {
                        tags = tags.and("service_code", serviceErrorCode);
                    }
                    if (cause != null) {
                        tags = tags.and("cause", cause);
                    }
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST, tags).increment();
                })
                .retryWhen(retrySpec);
    }

    /**
     * TODO: Shutdown gracefully
     */
    public Mono<Void> close() {
        isClosed = true;
        List<Mono<Void>> monos = new ArrayList<>(2);
        if (isApnsEnabled) {
            monos.add(apnsSender.close());
        }
        if (isFcmEnabled) {
            monos.add(fcmSender.close());
        }
        return Mono.whenDelayError(monos);
    }

}