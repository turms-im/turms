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

package im.turms.service.infra.push.sender.fcm;

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
import im.turms.server.common.infra.exception.FeatureDisabledException;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.property.env.service.env.push.FcmProperties;
import im.turms.service.infra.push.PushNotification;
import im.turms.service.infra.push.PushNotificationSender;
import im.turms.service.infra.push.SendPushNotificationResult;
import reactor.core.publisher.Mono;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * @author James Chen
 */
public class FcmSender implements PushNotificationSender {

    private static final AndroidConfig ANDROID_CONFIG = AndroidConfig.builder()
            .setPriority(AndroidConfig.Priority.HIGH)
            .build();

    private final boolean isEnabled;
    private final FirebaseMessaging firebaseMessagingClient;

    public FcmSender(FcmProperties fcmProperties) {
        isEnabled = fcmProperties.isEnabled();
        if (!isEnabled) {
            firebaseMessagingClient = null;
            return;
        }
        byte[] credentialsBytes = fcmProperties.getCredentials().getBytes(StandardCharsets.UTF_8);
        ByteArrayInputStream credentialInputStream = new ByteArrayInputStream(credentialsBytes);
        GoogleCredentials credentials;
        try {
            credentials = GoogleCredentials.fromStream(credentialInputStream);
        } catch (IOException e) {
            InputOutputException exception = new InputOutputException("Failed to read credentials", e);
            try {
                credentialInputStream.close();
            } catch (IOException ex) {
                IOException suppressed = new IOException("Caught an error while closing the credentials input stream", ex);
                exception.addSuppressed(suppressed);
            }
            throw exception;
        }
        try {
            FirebaseApp.initializeApp(FirebaseOptions.builder()
                    .setCredentials(credentials)
                    .build());
        } catch (Exception e) {
            RuntimeException exception = new RuntimeException("Failed to initialize the Firebase application", e);
            try {
                credentialInputStream.close();
            } catch (IOException ex) {
                IOException suppressed = new IOException("Caught an error while closing the credentials input stream", ex);
                exception.addSuppressed(suppressed);
            }
            throw exception;
        }
        try {
            credentialInputStream.close();
        } catch (IOException e) {
            throw new InputOutputException("Caught an error while closing the credentials input stream", e);
        }
        firebaseMessagingClient = FirebaseMessaging.getInstance();
    }

    @Override
    public Mono<SendPushNotificationResult> sendNotification(PushNotification notification) {
        if (!isEnabled) {
            return Mono.error(new FeatureDisabledException("FCM is disabled"));
        }
        Message message = Message.builder()
                .setToken(notification.deviceToken())
                .setAndroidConfig(ANDROID_CONFIG)
                .putData(
                        notification.notificationType().getType(),
                        notification.data() == null ? "" : notification.data())
                .build();
        ApiFuture<String> sendFuture = firebaseMessagingClient.sendAsync(message);
        return Mono.create(sink -> {
            Runnable listener = () -> {
                try {
                    // `get()` should return immediately in the listener
                    sendFuture.get();
                    sink.success(new SendPushNotificationResult(true, null, false));
                } catch (ExecutionException e) {
                    if (e.getCause() instanceof FirebaseMessagingException messagingException) {
                        MessagingErrorCode messagingErrorCode = messagingException.getMessagingErrorCode();
                        String errorCode = messagingErrorCode == null ? null : messagingErrorCode.name();
                        sink.success(new SendPushNotificationResult(
                                false, errorCode, messagingErrorCode == MessagingErrorCode.UNREGISTERED));
                    } else {
                        sink.error(e.getCause());
                    }
                } catch (InterruptedException e) {
                    // This should never happen
                    sink.error(e);
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
