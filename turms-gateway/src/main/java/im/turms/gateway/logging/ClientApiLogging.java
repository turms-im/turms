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
import im.turms.server.common.logging.CommonClientApiLogging;

import java.time.Instant;

import static im.turms.server.common.logging.CommonClientApiLogging.LOG_FIELD_DELIMITER;

/**
 * @author James Chen
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
    public static void log(Long userId,
                           Integer version,
                           String ip,
                           Integer sessionId,
                           DeviceType deviceType,
                           long requestId,
                           TurmsRequest.KindCase requestType,
                           int requestSize,
                           long requestTime,
                           TurmsNotification response,
                           long processingTime) {
        String message =
                // user information
                userId
                        + LOG_FIELD_DELIMITER
                        // session information
                        + version
                        + LOG_FIELD_DELIMITER
                        + ip
                        + LOG_FIELD_DELIMITER
                        + sessionId
                        + LOG_FIELD_DELIMITER
                        + deviceType
                        + LOG_FIELD_DELIMITER
                        // request information
                        + requestId
                        + LOG_FIELD_DELIMITER
                        + requestType
                        + LOG_FIELD_DELIMITER
                        + Instant.ofEpochMilli(requestTime)
                        + LOG_FIELD_DELIMITER
                        + requestSize
                        + LOG_FIELD_DELIMITER
                        // response information
                        + processingTime
                        + LOG_FIELD_DELIMITER
                        + response.getCode()
                        + LOG_FIELD_DELIMITER
                        + (response.hasData() ? response.getData().getKindCase() : null)
                        + LOG_FIELD_DELIMITER
                        + response.getSerializedSize();
        CommonClientApiLogging.logger.info(message);
    }

    public static void log(Long userId,
                           Integer version,
                           String ip,
                           Integer sessionId,
                           DeviceType deviceType,
                           long requestId,
                           TurmsRequest.KindCase requestType,
                           int requestSize,
                           long requestTime,
                           int responseCode,
                           long processingTime) {
        String message =
                // user information
                userId
                        + LOG_FIELD_DELIMITER
                        // session information
                        + version
                        + LOG_FIELD_DELIMITER
                        + ip
                        + LOG_FIELD_DELIMITER
                        + sessionId
                        + LOG_FIELD_DELIMITER
                        + deviceType
                        + LOG_FIELD_DELIMITER
                        // request information
                        + requestId
                        + LOG_FIELD_DELIMITER
                        + requestType
                        + LOG_FIELD_DELIMITER
                        + Instant.ofEpochMilli(requestTime)
                        + LOG_FIELD_DELIMITER
                        + requestSize
                        + LOG_FIELD_DELIMITER
                        // response information
                        + processingTime
                        + LOG_FIELD_DELIMITER
                        + responseCode
                        + LOG_FIELD_DELIMITER
                        + null
                        + LOG_FIELD_DELIMITER
                        + 0;
        CommonClientApiLogging.logger.info(message);
    }

}