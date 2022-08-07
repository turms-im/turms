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
package im.turms.service.infra.push.sender.apns;

import com.eatthepath.pushy.apns.ApnsClient;
import com.eatthepath.pushy.apns.ApnsClientBuilder;
import com.eatthepath.pushy.apns.DeliveryPriority;
import com.eatthepath.pushy.apns.PushType;
import com.eatthepath.pushy.apns.auth.ApnsSigningKey;
import com.eatthepath.pushy.apns.util.SimpleApnsPushNotification;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.service.env.push.ApnsProperties;
import im.turms.service.infra.push.DeviceTokenType;
import im.turms.service.infra.push.PushNotification;
import im.turms.service.infra.push.PushNotificationSender;
import im.turms.service.infra.push.PushNotificationType;
import im.turms.service.infra.push.SendPushNotificationResult;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;

/**
 * @author James Chen
 */
public class ApnsSender implements PushNotificationSender {

    private static final String APN_CHALLENGE_PAYLOAD = "{\"aps\":{\"sound\":\"default\",\"alert\":{\"loc-key\":\"APN_Message\"}},\"challenge\":\"{}\"}";
    private static final String APN_NSE_NOTIFICATION_PAYLOAD = "{\"aps\":{\"mutable-content\":1,\"alert\":{\"loc-key\":\"APN_Message\"}}}";
    private static final String APN_RATE_LIMIT_CHALLENGE_PAYLOAD = "{\"aps\":{\"sound\":\"default\",\"alert\":{\"loc-key\":\"APN_Message\"}},\"rateLimitChallenge\":\"{}\"}";
    private static final String APN_VOIP_NOTIFICATION_PAYLOAD = "{\"aps\":{\"sound\":\"default\",\"alert\":{\"loc-key\":\"APN_Message\"}}}";

    private static final String APNS_CA_FILENAME = "apns-certificates.pem";

    private static final Instant MAX_EXPIRATION = Instant.ofEpochMilli(Integer.MAX_VALUE * 1000L);

    private final boolean isEnabled;
    private final String bundleId;
    private final String bundleIdForVoip;

    private final ApnsClient apnsClient;

    public ApnsSender(ApnsProperties apnsProperties) {
        isEnabled = apnsProperties.isEnabled();
        if (!isEnabled) {
            bundleId = null;
            bundleIdForVoip = null;
            apnsClient = null;
            return;
        }
        bundleId = apnsProperties.getBundleId();
        bundleIdForVoip = bundleId + ".voip";
        ByteArrayInputStream inputStream = new ByteArrayInputStream(apnsProperties.getSigningKey().getBytes());
        try {
            apnsClient = new ApnsClientBuilder()
                    .setSigningKey(ApnsSigningKey.loadFromInputStream(inputStream, apnsProperties.getTeamId(), apnsProperties.getKeyId()))
                    .setTrustedServerCertificateChain(getClass().getResourceAsStream(APNS_CA_FILENAME))
                    .setApnsServer(apnsProperties.isSandboxEnabled()
                            ? ApnsClientBuilder.DEVELOPMENT_APNS_HOST
                            : ApnsClientBuilder.PRODUCTION_APNS_HOST)
                    .build();
        } catch (IOException | NoSuchAlgorithmException | InvalidKeyException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Mono<SendPushNotificationResult> sendNotification(PushNotification notification) {
        if (!isEnabled) {
            return Mono.error(new UnsupportedOperationException("APNs is disabled"));
        }
        DeviceTokenType deviceTokenType = notification.deviceTokenType();
        String topic = switch (deviceTokenType) {
            case APN -> bundleId;
            case APN_VOIP -> bundleIdForVoip;
            default -> throw new IllegalArgumentException("Unsupported token type: " + deviceTokenType);
        };
        boolean isVoip = deviceTokenType == DeviceTokenType.APN_VOIP;
        PushNotificationType notificationType = notification.notificationType();
        String payload = switch (notificationType) {
            case NOTIFICATION -> isVoip ? APN_VOIP_NOTIFICATION_PAYLOAD : APN_NSE_NOTIFICATION_PAYLOAD;
            case CHALLENGE -> StringUtil.substitute(APN_CHALLENGE_PAYLOAD, notification.data());
            case RATE_LIMIT_CHALLENGE -> StringUtil.substitute(APN_RATE_LIMIT_CHALLENGE_PAYLOAD, notification.data());
        };
        String collapseId = notificationType == PushNotificationType.NOTIFICATION && !isVoip
                ? "0"
                : null;
        SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(
                notification.deviceToken(),
                topic,
                payload,
                MAX_EXPIRATION,
                DeliveryPriority.IMMEDIATE,
                isVoip ? PushType.VOIP : PushType.ALERT,
                collapseId);
        return Mono.create(sink -> apnsClient.sendNotification(pushNotification)
                .whenComplete((response, throwable) -> {
                    if (throwable != null) {
                        sink.error(throwable);
                        return;
                    }
                    if (response == null) {
                        sink.error(new NullPointerException("response is null"));
                        return;
                    }
                    if (response.isAccepted()) {
                        sink.success(new SendPushNotificationResult(true, null, false));
                    } else {
                        String rejectionReason = response.getRejectionReason().orElse(null);
                        sink.success(new SendPushNotificationResult(false, rejectionReason,
                                "Unregistered".equals(rejectionReason) || "BadDeviceToken".equals(rejectionReason)));
                    }
                }));
    }

    @Override
    public Mono<Void> close() {
        if (!isEnabled) {
            return Mono.empty();
        }
        return Mono.fromFuture(apnsClient.close());
    }

}
