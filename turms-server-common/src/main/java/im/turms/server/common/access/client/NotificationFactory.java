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

package im.turms.server.common.access.client;

import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.infra.exception.ThrowableInfo;

import javax.annotation.Nullable;

/**
 * @author James Chen
 */
public final class NotificationFactory {

    public static boolean returnReasonForServerError;

    private NotificationFactory() {
    }

    public static TurmsNotification create(ResponseStatusCode code, long requestId) {
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(code.getBusinessCode());
        String reason = code.getReason();
        trySetReason(builder, code, reason);
        return builder.build();
    }

    public static TurmsNotification create(ResponseStatusCode code, String reason, long requestId) {
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(code.getBusinessCode());
        reason = reason == null ? code.getReason() : reason;
        trySetReason(builder, code, reason);
        return builder.build();
    }

    public static TurmsNotification create(ThrowableInfo info, long requestId) {
        ResponseStatusCode code = info.code();
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(code.getBusinessCode());
        String reason = info.reason();
        trySetReason(builder, code, reason);
        return builder.build();
    }

    public static TurmsNotification create(CloseReason closeReason) {
        ResponseStatusCode statusCode = closeReason.businessStatusCode();
        SessionCloseStatus closeStatus = closeReason.closeStatus();
        String reason = closeReason.reason();
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setCloseStatus(closeStatus.getCode());
        if (statusCode != null) {
            builder.setCode(statusCode.getBusinessCode());
        }
        trySetReason(builder, statusCode, reason);
        return builder.build();
    }

    public static TurmsNotification sessionClosed(long requestId) {
        return TurmsNotification
                .newBuilder()
                .setRequestId(requestId)
                .setCode(ResponseStatusCode.SERVER_INTERNAL_ERROR.getBusinessCode())
                .build();
    }

    private static void trySetReason(TurmsNotification.Builder builder,
                                     ResponseStatusCode code,
                                     @Nullable String reason) {
        if (reason == null) {
            return;
        }
        if (code.isServerError()) {
            if (returnReasonForServerError) {
                builder.setReason(reason);
            }
        } else {
            builder.setReason(reason);
        }
    }

}