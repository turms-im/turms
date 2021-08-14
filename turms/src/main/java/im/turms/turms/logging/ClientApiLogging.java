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

package im.turms.turms.logging;

import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.dto.ServiceRequest;
import im.turms.server.common.dto.ServiceResponse;
import im.turms.server.common.logging.CommonClientApiLogging;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;

import java.time.Instant;

import static im.turms.server.common.logging.CommonClientApiLogging.LOG_FIELD_DELIMITER;

/**
 * @author James Chen
 */
public final class ClientApiLogging {

    private ClientApiLogging() {
    }

    /**
     * @implNote 1. We don't accept an object like ServiceRequestMessage
     * to collect all data to avoid creating unnecessary intermediate objects.
     * 2. We use the common log pattern (including the trace ID) so that our
     * users don't need to write different parsers for them.
     */
    public static void log(ClientRequest request,
                           ServiceRequest serviceRequest,
                           long requestSize,
                           long requestTime,
                           ServiceResponse response,
                           long processingTime) {
        TurmsNotification.Data dataForRequester = response.getDataForRequester();
        TurmsNotification.Data.KindCase responseType = dataForRequester == null ? null : dataForRequester.getKindCase();
        String message =
                // client information
                serviceRequest.getIpStr()
                        + LOG_FIELD_DELIMITER
                        // user information
                        + request.getUserId()
                        + LOG_FIELD_DELIMITER
                        + request.getDeviceType()
                        + LOG_FIELD_DELIMITER
                        // request information
                        + request.getRequestId()
                        + LOG_FIELD_DELIMITER
                        + request.getTurmsRequest().getKindCase()
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
                        + responseType
                        + LOG_FIELD_DELIMITER
                        + response.getReason();
        if (response.getCode().isServerError()) {
            CommonClientApiLogging.logger.error(message);
        } else {
            CommonClientApiLogging.logger.info(message);
        }
    }

    public static void log(boolean sent, TurmsNotification notification) {
        String message =
                (sent ? "Sent" : "Unsent")
                        + LOG_FIELD_DELIMITER
                        + (notification.hasCode() ? notification.getCode() : null)
                        + LOG_FIELD_DELIMITER
                        + (notification.hasCloseStatus() ? notification.getCloseStatus() : null)
                        + LOG_FIELD_DELIMITER
                        + (notification.hasData() ? notification.getData().getKindCase() : null);
        CommonClientApiLogging.logger.info(message);
    }

}