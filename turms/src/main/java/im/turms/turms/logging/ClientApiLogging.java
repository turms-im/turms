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
import im.turms.server.common.logging.CustomLogger;
import im.turms.turms.workflow.access.servicerequest.dto.ClientRequest;

import java.time.Instant;

import static im.turms.server.common.logging.CustomLogger.LOG_FIELD_DELIMITER;

/**
 * @author James Chen
 * @implNote Don't use StringBuilder because String#join is more efficient
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
        TurmsNotification.Data dataForRequester = response.dataForRequester();
        String responseType = dataForRequester == null ? "" : dataForRequester.getKindCase().name();
        String message = String.join(LOG_FIELD_DELIMITER,
                // session information
                request.userId().toString(),
                request.deviceType().name(),
                serviceRequest.getIpStr(),
                // request information
                request.requestId().toString(),
                request.turmsRequest().getKindCase().name(),
                String.valueOf(requestSize),
                Instant.ofEpochMilli(requestTime).toString(),
                // response information
                String.valueOf(response.code().getBusinessCode()),
                responseType,
                String.valueOf(processingTime));
        if (response.code().isServerError()) {
            CustomLogger.CLIENT_API_LOGGER.error(message);
        } else {
            CustomLogger.CLIENT_API_LOGGER.info(message);
        }
    }

}