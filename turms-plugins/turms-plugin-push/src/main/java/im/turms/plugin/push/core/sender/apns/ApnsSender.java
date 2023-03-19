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
package im.turms.plugin.push.core.sender.apns;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.DeliveryPriority;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.PushType;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import im.turms.plugin.push.core.PushNotification;
import im.turms.plugin.push.core.PushNotificationSender;
import im.turms.plugin.push.core.SendPushNotificationResult;
import im.turms.plugin.push.property.ApnsProperties;
import im.turms.server.common.infra.exception.FeatureDisabledException;
import im.turms.server.common.infra.lang.StrJoiner;
import lombok.Getter;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.function.BiConsumer;

/**
 * @author James Chen
 */
public class ApnsSender implements PushNotificationSender {

    private static final Instant MAX_EXPIRATION = Instant.ofEpochMilli(Integer.MAX_VALUE * 1000L);
    private static final String COLLAPSE_ID = "0";

    private final boolean isEnabled;
    private final String bundleId;
    @Getter
    private final String deviceTokenFieldName;

    private final ApnsClient apnsClient;

    public ApnsSender(ApnsProperties apnsProperties) {
        isEnabled = apnsProperties.isEnabled();
        if (!isEnabled) {
            bundleId = null;
            deviceTokenFieldName = null;
            apnsClient = null;
            return;
        }
        bundleId = apnsProperties.getBundleId();
        deviceTokenFieldName = apnsProperties.getDeviceTokenFieldName();
        byte[] signingKeyBytes = apnsProperties.getSigningKey().getBytes();
        ApnsSigningKey signingKey;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(signingKeyBytes);
            signingKey = ApnsSigningKey.loadFromInputStream(inputStream,
                    apnsProperties.getTeamId(),
                    apnsProperties.getKeyId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to read the signing key", e);
        }
        try {
            String hostname = apnsProperties.isSandboxEnabled()
                    ? ApnsClientBuilder.DEVELOPMENT_APNS_HOST
                    : ApnsClientBuilder.PRODUCTION_APNS_HOST;
            apnsClient = new ApnsClientBuilder()
                    .setSigningKey(signingKey)
                    .setApnsServer(hostname)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize the APNs client", e);
        }
    }

    @Override
    public Mono<SendPushNotificationResult> sendNotification(PushNotification notification) {
        if (!isEnabled) {
            return Mono.error(new FeatureDisabledException("APNs is disabled"));
        }
        String title = notification.title();
        Integer badgeNumber = notification.badgeNumber();
        int count = 3;
        boolean hasTitle = title != null;
        boolean hasBadgeNumber = badgeNumber != null;
        if (hasTitle) {
            count++;
        }
        if (hasBadgeNumber) {
            count++;
        }
        StrJoiner joiner = new StrJoiner(count);
        joiner.add("""
                {"aps":{"mutable-content":1,"alert":{"loc-key":"APN_Message"}}""");
        if (hasTitle) {
            // TODO: sanitize
            joiner.add(",\"title\":\"" + title + "\"");
        }
        // TODO: sanitize
        joiner.add(",\"body\":\"" + notification.body() + "\"");
        if (hasBadgeNumber) {
            joiner.add(",\"badge\":" + badgeNumber);
        }
        joiner.add("}");
        SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(
                notification.deviceToken(),
                bundleId,
                joiner.toString(),
                MAX_EXPIRATION,
                DeliveryPriority.IMMEDIATE,
                PushType.ALERT,
                COLLAPSE_ID);
        return Mono.create(sink -> {
            BiConsumer<PushNotificationResponse<SimpleApnsPushNotification>, Throwable> consumer = (response, throwable) -> {
                if (throwable != null) {
                    sink.error(throwable);
                    return;
                }
                if (response == null) {
                    sink.error(new NullPointerException("The send notification response is null"));
                    return;
                }
                if (response.isAccepted()) {
                    sink.success(new SendPushNotificationResult(true, null, false));
                } else {
                    String rejectionReason = response.getRejectionReason().orElse(null);
                    boolean unregistered = "Unregistered".equals(rejectionReason) || "BadDeviceToken".equals(rejectionReason);
                    sink.success(new SendPushNotificationResult(false, rejectionReason, unregistered));
                }
            };
            apnsClient.sendNotification(pushNotification)
                    .whenComplete(consumer);
        });
    }

    @Override
    public Mono<Void> close() {
        if (!isEnabled) {
            return Mono.empty();
        }
        return Mono.fromFuture(apnsClient.close());
    }

}