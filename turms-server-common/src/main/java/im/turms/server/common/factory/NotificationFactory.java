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

import com.google.protobuf.Int32Value;
import com.google.protobuf.Int64Value;
import com.google.protobuf.StringValue;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.pojo.ThrowableInfo;

/**
 * @author James Chen
 */
public class NotificationFactory {

    public static boolean returnReasonForServerError;

    private NotificationFactory() {
    }

    public static TurmsNotification fromThrowable(ThrowableInfo info, long requestId) {
        TurmsStatusCode code = info.getCode();
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setRequestId(Int64Value.newBuilder().setValue(requestId).build())
                .setCode(Int32Value.newBuilder().setValue(code.getBusinessCode()).build());
        String reason = info.getReason();
        trySetReason(builder, reason, code);
        return builder.build();
    }

    public static TurmsNotification fromReason(CloseReason closeReason) {
        TurmsStatusCode statusCode = closeReason.getBusinessStatusCode();
        SessionCloseStatus closeStatus = closeReason.getCloseStatus();
        String reason = closeReason.getReason();
        TurmsNotification.Builder builder = TurmsNotification
                .newBuilder()
                .setCloseStatus(Int32Value.newBuilder().setValue(closeStatus.getCode()).build());
        if (statusCode != null) {
            builder.setCode(Int32Value.newBuilder().setValue(statusCode.getBusinessCode()).build());
        }
        trySetReason(builder, reason, statusCode);
        return builder.build();
    }

    private static void trySetReason(TurmsNotification.Builder builder, String reason, TurmsStatusCode code) {
        if (reason != null) {
            if (TurmsStatusCode.isServerError(code.getBusinessCode())) {
                if (returnReasonForServerError) {
                    builder.setReason(StringValue.newBuilder().setValue(reason).build());
                }
            } else {
                builder.setReason(StringValue.newBuilder().setValue(reason).build());
            }
        }
    }

}