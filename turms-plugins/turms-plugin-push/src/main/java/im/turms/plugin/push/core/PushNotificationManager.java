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

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import io.micrometer.core.instrument.Metrics;
import io.micrometer.core.instrument.Tags;
import lombok.Getter;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;
import reactor.util.retry.RetryBackoffSpec;

import im.turms.plugin.push.core.sender.apns.ApnsSender;
import im.turms.plugin.push.core.sender.fcm.FcmSender;
import im.turms.plugin.push.property.ApnsProperties;
import im.turms.plugin.push.property.FcmProperties;
import im.turms.plugin.push.property.LocaleProperties;
import im.turms.plugin.push.property.PushNotificationProperties;
import im.turms.plugin.push.property.TemplateProperties;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.exception.FeatureDisabledException;
import im.turms.server.common.infra.lang.StringUtil;

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
    private final String localeFieldName;
    private final String fallbackLocale;
    private final boolean isApnsEnabled;
    private final boolean isFcmEnabled;

    private volatile boolean isClosed;

    // TODO: make configurable
    private final RetryBackoffSpec retrySpec = Retry.backoff(2, Duration.ofMillis(1))
            .filter(throwable -> throwable instanceof SendPushNotificationException exception
                    && exception.getErrorCode()
                            .isRetryable()
                    && !isClosed);

    public PushNotificationManager(PushNotificationProperties properties) {
        LocaleProperties localeProperties = properties.getLocale();
        Map<String, TemplateProperties> templates = properties.getTemplates();
        ApnsProperties apnsProperties = properties.getApns();
        FcmProperties fcmProperties = properties.getFcm();
        localeFieldName = localeProperties.getFieldName();
        fallbackLocale = localeProperties.getFallback();
        if (StringUtil.isBlank(localeFieldName)) {
            throw new IllegalArgumentException("The locale field name must not be blank");
        }
        if (StringUtil.isBlank(fallbackLocale)) {
            throw new IllegalArgumentException("The fallback locale must not be blank");
        }
        isApnsEnabled = apnsProperties.isEnabled();
        isFcmEnabled = fcmProperties.isEnabled();
        apnsSender = isApnsEnabled
                ? new ApnsSender(templates, apnsProperties)
                : null;
        fcmSender = isFcmEnabled
                ? new FcmSender(templates, fcmProperties)
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

    public Mono<Void> sendNotification(
            PushNotification pushNotification,
            TurmsRequest request,
            Long requesterId,
            DeviceType requesterDevice,
            String requesterName,
            Map<String, String> deviceDetails) {
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
                return Mono.error(new RuntimeException(
                        "Unknown push service provider: "
                                + pushNotification.serviceProvider()));
            }
        }
        String locale = deviceDetails.getOrDefault(localeFieldName, fallbackLocale);
        Supplier<Object> dataModelSupplier = new Supplier<>() {
            private Map<String, Object> map;

            @Override
            public Object get() {
                if (map != null) {
                    return map;
                }
                String title = pushNotification.title();
                String body = pushNotification.body();
                Integer badgeNumber = pushNotification.badgeNumber();
                map = new HashMap<>(16);
                map.put("serviceProvider", pushNotification.serviceProvider());
                map.put("locale", locale);
                map.put("request", request);
                map.put("requester",
                        Map.of("id",
                                requesterId,
                                "device",
                                requesterDevice,
                                "name",
                                requesterName));
                map.put("recipient", Map.of("deviceDetails", deviceDetails));
                if (badgeNumber != null) {
                    map.put("badgeNumber", badgeNumber);
                }
                if (title != null) {
                    map.put("title", title);
                }
                if (body != null) {
                    map.put("body", body);
                }
                return map;
            }
        };
        return sender.sendNotification(pushNotification, locale, dataModelSupplier)
                .doOnSuccess(unused -> {
                    Tags tags = Tags.of("provider",
                            pushNotification.serviceProvider()
                                    .name(),
                            // We only have this type currently
                            "type",
                            "SEND_MESSAGE",
                            "accepted",
                            "true");
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST, tags)
                            .increment();
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
                            cause = unwrappedCause.getClass()
                                    .getSimpleName();
                        }
                    } else {
                        cause = throwable.getClass()
                                .getSimpleName();
                    }
                    Tags tags = Tags.of("provider",
                            pushNotification.serviceProvider()
                                    .name(),
                            "type",
                            "SEND_MESSAGE",
                            "accepted",
                            "false");
                    if (errorCode != null) {
                        tags = tags.and("code", String.valueOf(errorCode.getCode()));
                    }
                    if (serviceErrorCode != null) {
                        tags = tags.and("service_code", serviceErrorCode);
                    }
                    if (cause != null) {
                        tags = tags.and("cause", cause);
                    }
                    Metrics.counter(PUSH_NOTIFICATION_REQUEST, tags)
                            .increment();
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