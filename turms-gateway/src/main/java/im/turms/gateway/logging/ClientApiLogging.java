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

package im.turms.gateway.logging;

import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.logging.CustomLogger;
import im.turms.server.common.util.DateUtil;
import im.turms.server.common.util.StringUtil;

import javax.annotation.Nullable;

import static im.turms.server.common.logging.CustomLogger.LOG_FIELD_DELIMITER;

/**
 * @author James Chen
 * @implNote Don't use StringBuilder because String#join is more efficient
 */
public final class ClientApiLogging {

    private ClientApiLogging() {
    }

    /**
     * @implNote 1. We don't accept an object like ClientApiLoggingMessage
     * to collect all data to avoid creating unnecessary intermediate objects.
     * 2. We use the common log pattern (including the trace ID) so that our
     * users don't need to write different parsers for them.
     */
    public static void log(@Nullable Integer sessionId,
                           @Nullable Long userId,
                           @Nullable DeviceType deviceType,
                           @Nullable Integer version,
                           String ip,
                           long requestId,
                           TurmsRequest.KindCase requestType,
                           int requestSize,
                           long requestTime,
                           TurmsNotification response,
                           long processingTime) {
        String message = String.join(LOG_FIELD_DELIMITER,
                // session information
                StringUtil.toString(sessionId),
                StringUtil.toString(userId),
                StringUtil.toString(deviceType),
                StringUtil.toString(version),
                ip,
                // request information
                String.valueOf(requestId),
                requestType.name(),
                String.valueOf(requestSize),
                DateUtil.toISO(requestTime),
                // response information
                String.valueOf(response.getCode()),
                response.hasData() ? response.getData().getKindCase().name() : "",
                String.valueOf(response.getSerializedSize()),
                String.valueOf(processingTime));
        CustomLogger.CLIENT_API_LOGGER.info(message);
    }

    public static void log(@Nullable Integer sessionId,
                           @Nullable Long userId,
                           @Nullable DeviceType deviceType,
                           @Nullable Integer version,
                           String ip,
                           long requestId,
                           TurmsRequest.KindCase requestType,
                           int requestSize,
                           long requestTime,
                           int responseCode,
                           long processingTime) {
        String message = String.join(LOG_FIELD_DELIMITER,
                // session information
                StringUtil.toString(sessionId),
                StringUtil.toString(userId),
                StringUtil.toString(deviceType),
                StringUtil.toString(version),
                ip,
                // request information
                String.valueOf(requestId),
                requestType.name(),
                String.valueOf(requestSize),
                DateUtil.toISO(requestTime),
                // response information
                String.valueOf(responseCode),
                "", // Response data type
                "0", // Response serialized size
                String.valueOf(processingTime));
        CustomLogger.CLIENT_API_LOGGER.info(message);
    }

    public static void log(@Nullable Integer sessionId,
                           @Nullable Long userId,
                           @Nullable DeviceType deviceType,
                           @Nullable Integer version,
                           String ip,
                           long requestId,
                           String requestType,
                           int requestSize,
                           long requestTime,
                           int responseCode,
                           @Nullable String responseDataType,
                           int responseSize,
                           long processingTime) {
        String message = String.join(LOG_FIELD_DELIMITER,
                // session information
                StringUtil.toString(sessionId),
                StringUtil.toString(userId),
                StringUtil.toString(deviceType),
                StringUtil.toString(version),
                ip,
                // request information
                String.valueOf(requestId),
                requestType,
                String.valueOf(requestSize),
                DateUtil.toISO(requestTime),
                // response information
                String.valueOf(responseCode),
                StringUtil.toString(responseDataType),
                String.valueOf(responseSize),
                String.valueOf(processingTime));
        CustomLogger.CLIENT_API_LOGGER.info(message);
    }

}