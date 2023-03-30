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

import java.io.ByteArrayInputStream;
import java.time.Instant;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.DeliveryPriority;
import com.eatthepath.pushy.apns.PushNotificationResponse;
import com.eatthepath.pushy.apns.PushType;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.Getter;
import reactor.core.publisher.Mono;

import im.turms.plugin.push.core.Message;
import im.turms.plugin.push.core.PushNotification;
import im.turms.plugin.push.core.PushNotificationErrorCode;
import im.turms.plugin.push.core.PushNotificationSender;
import im.turms.plugin.push.core.SendPushNotificationException;
import im.turms.plugin.push.property.ApnsProperties;
import im.turms.plugin.push.property.TemplateProperties;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.json.JsonCodecPool;
import im.turms.server.common.infra.test.VisibleForTesting;

/**
 * @author James Chen
 */
public class ApnsSender extends PushNotificationSender {

    private static final Instant MAX_EXPIRATION = Instant.ofEpochMilli(Integer.MAX_VALUE * 1000L);
    private static final String COLLAPSE_ID = "0";
    private static final ObjectWriter APNS_PAYLOAD_WRITER =
            JsonCodecPool.MAPPER.writerFor(ApnsPayload.class);

    private final String bundleId;
    @Getter
    private final String deviceTokenFieldName;

    private final ApnsClient apnsClient;

    public ApnsSender(Map<String, TemplateProperties> templates, ApnsProperties apnsProperties) {
        super(templates, apnsProperties.getTemplate());
        bundleId = apnsProperties.getBundleId();
        deviceTokenFieldName = apnsProperties.getDeviceTokenFieldName();
        byte[] signingKeyBytes = apnsProperties.getSigningKey()
                .getBytes();
        ApnsSigningKey signingKey;
        try {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(signingKeyBytes);
            signingKey = ApnsSigningKey.loadFromInputStream(inputStream,
                    apnsProperties.getTeamId(),
                    apnsProperties.getKeyId());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to read the signing key", e);
        }
        try {
            String hostname = apnsProperties.isSandboxEnabled()
                    ? ApnsClientBuilder.DEVELOPMENT_APNS_HOST
                    : ApnsClientBuilder.PRODUCTION_APNS_HOST;
            apnsClient = new ApnsClientBuilder().setSigningKey(signingKey)
                    .setApnsServer(hostname)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize the APNs client", e);
        }
    }

    @VisibleForTesting
    public String buildPayload(
            PushNotification notification,
            String locale,
            Supplier<Object> dataModelSupplier) {
        Message message = buildMessage(locale,
                notification.type(),
                notification.title(),
                notification.body(),
                dataModelSupplier);
        ApnsPayload apnsPayload = new ApnsPayload(
                new ApnsPayload.Aps(
                        new ApnsPayload.Alert(message.title(), message.body()),
                        notification.badgeNumber(),
                        1));
        try {
            return APNS_PAYLOAD_WRITER.writeValueAsString(apnsPayload);
        } catch (JsonProcessingException e) {
            throw new InputOutputException("Failed to encode payload", e);
        }
    }

    @Override
    public Mono<Void> sendNotification(
            PushNotification notification,
            String locale,
            Supplier<Object> dataModelSupplier) {
        String payload;
        try {
            payload = buildPayload(notification, locale, dataModelSupplier);
        } catch (Exception e) {
            return Mono.error(e);
        }
        SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(
                notification.deviceToken(),
                bundleId,
                payload,
                MAX_EXPIRATION,
                DeliveryPriority.IMMEDIATE,
                PushType.ALERT,
                COLLAPSE_ID);
        return Mono.create(sink -> {
            BiConsumer<PushNotificationResponse<SimpleApnsPushNotification>, Throwable> consumer =
                    (response, throwable) -> {
                        if (throwable != null) {
                            sink.error(SendPushNotificationException.internalError(throwable));
                            return;
                        }
                        if (response == null) {
                            NullPointerException cause = new NullPointerException(
                                    "The send notification response is null");
                            sink.error(SendPushNotificationException.internalError(cause));
                            return;
                        }
                        if (response.isAccepted()) {
                            sink.success();
                        } else {
                            String rejectionReason = response.getRejectionReason()
                                    .orElse(null);
                            if (rejectionReason == null) {
                                sink.error(new SendPushNotificationException(
                                        PushNotificationErrorCode.UNKNOWN));
                            } else {
                                sink.error(new SendPushNotificationException(
                                        ApnsErrorCodeUtil.toCode(rejectionReason),
                                        rejectionReason));
                            }
                        }
                    };
            apnsClient.sendNotification(pushNotification)
                    .whenComplete(consumer);
        });
    }

    @Override
    public Mono<Void> close() {
        return Mono.fromFuture(apnsClient::close);
    }

}