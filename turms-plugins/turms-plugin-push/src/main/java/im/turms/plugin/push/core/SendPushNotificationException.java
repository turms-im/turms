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

import jakarta.annotation.Nullable;

import lombok.Data;

/**
 * @author James Chen
 */
@Data
public class SendPushNotificationException extends RuntimeException {

    private final PushNotificationErrorCode errorCode;
    @Nullable
    private final String serviceErrorCode;

    public SendPushNotificationException(
            Throwable cause,
            PushNotificationErrorCode errorCode,
            @Nullable String serviceErrorCode) {
        super(cause);
        this.errorCode = errorCode;
        this.serviceErrorCode = serviceErrorCode;
    }

    public SendPushNotificationException(Throwable cause, PushNotificationErrorCode errorCode) {
        super(cause);
        this.errorCode = errorCode;
        serviceErrorCode = null;
    }

    public SendPushNotificationException(PushNotificationErrorCode errorCode) {
        this.errorCode = errorCode;
        serviceErrorCode = null;
    }

    public SendPushNotificationException(
            PushNotificationErrorCode errorCode,
            @Nullable String serviceErrorCode) {
        this.errorCode = errorCode;
        this.serviceErrorCode = serviceErrorCode;
    }

    public static SendPushNotificationException internalError(Throwable cause) {
        return new SendPushNotificationException(
                cause,
                PushNotificationErrorCode.INTERNAL_ERROR,
                null);
    }

}