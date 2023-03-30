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

package im.turms.service.infra.logging;

import java.util.Map;

import org.springframework.context.annotation.Configuration;

import im.turms.server.common.access.client.dto.request.TurmsRequest;
import im.turms.server.common.infra.logging.CommonApiLoggingContext;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.env.clientapi.ClientApiLoggingProperties;
import im.turms.server.common.infra.property.env.service.env.clientapi.property.LoggingRequestProperties;

/**
 * @author James Chen
 */
@Configuration
public class ApiLoggingContext extends CommonApiLoggingContext {

    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> typeToSupportedLoggingRequestProperties;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> typeToSupportedLoggingNotificationProperties;

    public ApiLoggingContext(TurmsPropertiesManager propertiesManager) {
        ClientApiLoggingProperties loggingProperties = propertiesManager.getLocalProperties()
                .getService()
                .getClientApi()
                .getLogging();
        typeToSupportedLoggingRequestProperties = getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedRequestCategories(),
                loggingProperties.getIncludedRequests(),
                loggingProperties.getExcludedRequestCategories(),
                loggingProperties.getExcludedRequestTypes());
        typeToSupportedLoggingNotificationProperties = getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedNotificationCategories(),
                loggingProperties.getIncludedNotifications(),
                loggingProperties.getExcludedNotificationCategories(),
                loggingProperties.getExcludedNotificationTypes());
    }

    public boolean shouldLogRequest(TurmsRequest.KindCase requestType) {
        return shouldLog(requestType, typeToSupportedLoggingRequestProperties);
    }

    public boolean shouldLogNotification(TurmsRequest.KindCase requestType) {
        return shouldLog(requestType, typeToSupportedLoggingNotificationProperties);
    }

}
