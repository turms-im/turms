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

import java.util.Map;

import com.google.firebase.messaging.MessagingErrorCode;

import im.turms.plugin.push.core.PushNotificationErrorCode;
import im.turms.server.common.infra.collection.FastEnumMap;

/**
 * @author James Chen
 */
public final class FcmErrorCodeUtil {

    private static final Map<MessagingErrorCode, PushNotificationErrorCode> ERROR_TO_CODE;

    static {
        FastEnumMap<MessagingErrorCode, PushNotificationErrorCode> map =
                new FastEnumMap<>(MessagingErrorCode.class);
        map.put(MessagingErrorCode.THIRD_PARTY_AUTH_ERROR, PushNotificationErrorCode.INVALID_AUTH);
        map.put(MessagingErrorCode.INTERNAL, PushNotificationErrorCode.SERVICE_ERROR);
        map.put(MessagingErrorCode.INVALID_ARGUMENT, PushNotificationErrorCode.INVALID_REQUEST);
        map.put(MessagingErrorCode.QUOTA_EXCEEDED, PushNotificationErrorCode.QUOTA_EXCEEDED);
        map.put(MessagingErrorCode.SENDER_ID_MISMATCH, PushNotificationErrorCode.AUTH_MISMATCH);
        map.put(MessagingErrorCode.UNAVAILABLE, PushNotificationErrorCode.SERVICE_UNAVAILABLE);
        map.put(MessagingErrorCode.UNREGISTERED, PushNotificationErrorCode.INACTIVE_TOKEN);
        ERROR_TO_CODE = Map.copyOf(map);
    }

    private FcmErrorCodeUtil() {
    }

    public static PushNotificationErrorCode toCode(MessagingErrorCode errorCode) {
        PushNotificationErrorCode code = ERROR_TO_CODE.get(errorCode);
        return code == null
                ? PushNotificationErrorCode.UNKNOWN
                : code;
    }

}