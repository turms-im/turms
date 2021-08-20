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

package im.turms.server.common.factory;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.exception.ThrowableInfo;

/**
 * @author James Chen
 */
public final class NotificationFactory {

    public static boolean returnReasonForServerError;

    private NotificationFactory() {
    }

    public static TurmsNotification create(TurmsStatusCode code, long requestId) {
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(code.getBusinessCode());
        String reason = code.getReason();
        trySetReason(builder, reason, code);
        return builder.build();
    }

    public static TurmsNotification create(TurmsStatusCode code, String reason, long requestId) {
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(code.getBusinessCode());
        reason = reason == null ? code.getReason() : reason;
        trySetReason(builder, reason, code);
        return builder.build();
    }

    public static TurmsNotification create(ThrowableInfo info, long requestId) {
        TurmsStatusCode code = info.code();
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(code.getBusinessCode());
        String reason = info.reason();
        trySetReason(builder, reason, code);
        return builder.build();
    }

    public static TurmsNotification create(CloseReason closeReason) {
        TurmsStatusCode statusCode = closeReason.businessStatusCode();
        SessionCloseStatus closeStatus = closeReason.closeStatus();
        String reason = closeReason.reason();
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setCloseStatus(closeStatus.getCode());
        if (statusCode != null) {
            builder.setCode(statusCode.getBusinessCode());
        }
        trySetReason(builder, reason, statusCode);
        return builder.build();
    }

    private static void trySetReason(TurmsNotification.Builder builder, String reason, TurmsStatusCode code) {
        if (reason != null) {
            if (TurmsStatusCode.isServerError(code.getBusinessCode())) {
                if (returnReasonForServerError) {
                    builder.setReason(reason);
                }
            } else {
                builder.setReason(reason);
            }
        }
    }

}