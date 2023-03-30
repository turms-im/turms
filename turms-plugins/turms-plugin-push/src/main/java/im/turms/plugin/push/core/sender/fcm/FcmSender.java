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

package im.turms.plugin.push.core.sender.fcm;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.AndroidConfig;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.MessagingErrorCode;
import com.google.firebase.messaging.Notification;
import lombok.Getter;
import reactor.core.publisher.Mono;

import im.turms.plugin.push.core.PushNotification;
import im.turms.plugin.push.core.PushNotificationErrorCode;
import im.turms.plugin.push.core.PushNotificationSender;
import im.turms.plugin.push.core.SendPushNotificationException;
import im.turms.plugin.push.property.FcmProperties;
import im.turms.plugin.push.property.TemplateProperties;
import im.turms.server.common.infra.io.InputOutputException;

/**
 * @author James Chen
 */
public class FcmSender extends PushNotificationSender {

    private static final AndroidConfig ANDROID_CONFIG = AndroidConfig.builder()
            .setPriority(AndroidConfig.Priority.HIGH)
            .build();

    @Getter
    private final String deviceTokenFieldName;
    private final FirebaseMessaging firebaseMessagingClient;

    public FcmSender(Map<String, TemplateProperties> templates, FcmProperties fcmProperties) {
        super(templates, fcmProperties.getTemplate());
        deviceTokenFieldName = fcmProperties.getDeviceTokenFieldName();
        byte[] credentialsBytes = fcmProperties.getCredentials()
                .getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream credentialInputStream = new ByteArrayInputStream(credentialsBytes);
        GoogleCredentials credentials;
        try {
            credentials = GoogleCredentials.fromStream(credentialInputStream);
        } catch (IOException e) {
            IllegalArgumentException exception =
                    new IllegalArgumentException("Failed to read credentials", e);
            try {
                credentialInputStream.close();
            } catch (IOException ex) {
                IOException suppressed = new IOException(
                        "Caught an error while closing the credentials input stream",
                        ex);
                exception.addSuppressed(suppressed);
            }
            throw exception;
        }
        try {
            FirebaseApp.initializeApp(FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build());
        } catch (Exception e) {
            RuntimeException exception =
                    new RuntimeException("Failed to initialize the Firebase application", e);
            try {
                credentialInputStream.close();
            } catch (IOException ex) {
                IOException suppressed = new IOException(
                        "Caught an error while closing the credentials input stream",
                        ex);
                exception.addSuppressed(suppressed);
            }
            throw exception;
        }
        try {
            credentialInputStream.close();
        } catch (IOException e) {
            throw new InputOutputException(
                    "Caught an error while closing the credentials input stream",
                    e);
        }
        firebaseMessagingClient = FirebaseMessaging.getInstance();
    }

    @Override
    public Mono<Void> sendNotification(
            PushNotification notification,
            String locale,
            Supplier<Object> dataModelSupplier) {
        var message = buildMessage(locale,
                notification.type(),
                notification.title(),
                notification.body(),
                dataModelSupplier);
        String body = notification.body();
        Notification.Builder notificationBuilder = Notification.builder()
                .setBody(message.body());
        String title = message.title();
        boolean hasTitle = title != null;
        if (hasTitle) {
            notificationBuilder.setTitle(title);
        }
        Message.Builder messageBuilder = Message.builder()
                .setToken(notification.deviceToken())
                .setAndroidConfig(ANDROID_CONFIG)
                .setNotification(notificationBuilder.build())
                .putData("body", body);
        if (hasTitle) {
            messageBuilder.putData("title", title);
        }
        Integer badgeNumber = notification.badgeNumber();
        if (badgeNumber != null) {
            messageBuilder.putData("badge", String.valueOf(badgeNumber));
        }
        ApiFuture<String> sendFuture = firebaseMessagingClient.sendAsync(messageBuilder.build());
        return Mono.create(sink -> {
            Runnable listener = () -> {
                try {
                    // `get()` should return immediately in the listener
                    sendFuture.get();
                    sink.success();
                } catch (ExecutionException e) {
                    Throwable cause = e.getCause();
                    if (cause instanceof FirebaseMessagingException messagingException) {
                        PushNotificationErrorCode errorCode;
                        String serviceErrorCode;
                        MessagingErrorCode code = messagingException.getMessagingErrorCode();
                        if (code == null) {
                            errorCode = PushNotificationErrorCode.UNKNOWN;
                            serviceErrorCode = null;
                        } else {
                            errorCode = FcmErrorCodeUtil.toCode(code);
                            serviceErrorCode = code.name();
                        }
                        sink.error(new SendPushNotificationException(errorCode, serviceErrorCode));
                    } else {
                        sink.error(new SendPushNotificationException(
                                cause,
                                PushNotificationErrorCode.UNKNOWN));
                    }
                } catch (InterruptedException e) {
                    // This should never happen
                    sink.error(SendPushNotificationException.internalError(e));
                }
            };
            sendFuture.addListener(listener, MoreExecutors.directExecutor());
        });
    }

    @Override
    public Mono<Void> close() {
        return Mono.empty();
    }

}