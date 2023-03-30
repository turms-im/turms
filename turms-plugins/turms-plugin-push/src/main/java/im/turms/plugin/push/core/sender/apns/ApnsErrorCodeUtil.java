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

import java.util.HashMap;
import java.util.Map;

import im.turms.plugin.push.core.PushNotificationErrorCode;

/**
 * @author James Chen
 */
public final class ApnsErrorCodeUtil {

    private static final Map<String, PushNotificationErrorCode> ERROR_TO_CODE;

    static {
        HashMap<String, PushNotificationErrorCode> map = new HashMap<>(64);
        map.put("BadCollapseId", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("BadDeviceToken", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("BadExpirationDate", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("BadMessageId", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("BadPriority", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("BadTopic", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("DeviceTokenNotForTopic", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("DuplicateHeaders", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("IdleTimeout", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("InvalidPushType", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("MissingDeviceToken", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("MissingTopic", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("PayloadEmpty", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("TopicDisallowed", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("BadCertificate", PushNotificationErrorCode.INVALID_AUTH);
        map.put("BadCertificateEnvironment", PushNotificationErrorCode.AUTH_MISMATCH);
        map.put("ExpiredProviderToken", PushNotificationErrorCode.INVALID_AUTH);
        map.put("Forbidden", PushNotificationErrorCode.INVALID_AUTH);
        map.put("InvalidProviderToken", PushNotificationErrorCode.INVALID_AUTH);
        map.put("MissingProviderToken", PushNotificationErrorCode.INVALID_AUTH);
        map.put("BadPath", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("MethodNotAllowed", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("ExpiredToken", PushNotificationErrorCode.INACTIVE_TOKEN);
        map.put("Unregistered", PushNotificationErrorCode.INACTIVE_TOKEN);
        map.put("PayloadTooLarge", PushNotificationErrorCode.INVALID_REQUEST);
        map.put("TooManyProviderTokenUpdates", PushNotificationErrorCode.TOO_MANY_REQUESTS);
        map.put("TooManyRequests", PushNotificationErrorCode.TOO_MANY_REQUESTS);
        map.put("InternalServerError", PushNotificationErrorCode.SERVICE_ERROR);
        map.put("ServiceUnavailable", PushNotificationErrorCode.SERVICE_UNAVAILABLE);
        map.put("Shutdown", PushNotificationErrorCode.SERVICE_UNAVAILABLE);
        ERROR_TO_CODE = Map.copyOf(map);
    }

    private ApnsErrorCodeUtil() {
    }

    public static PushNotificationErrorCode toCode(String errorString) {
        PushNotificationErrorCode code = ERROR_TO_CODE.get(errorString);
        return code == null
                ? PushNotificationErrorCode.UNKNOWN
                : code;
    }

}