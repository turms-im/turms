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

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.logging.CommonApiLoggingContext;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.clientapi.ClientApiLoggingProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author James Chen
 */
@Configuration
public class ApiLoggingContext extends CommonApiLoggingContext {

    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingRequestProperties;

    public ApiLoggingContext(TurmsPropertiesManager propertiesManager) {
        ClientApiLoggingProperties loggingProperties = propertiesManager.getLocalProperties().getGateway().getClientApi().getLogging();
        supportedLoggingRequestProperties = getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedRequestCategories(),
                loggingProperties.getIncludedRequests(),
                loggingProperties.getExcludedRequestCategories(),
                loggingProperties.getExcludedRequestTypes());
    }

    public boolean shouldLog(TurmsRequest.KindCase requestType) {
        return shouldLog(requestType, supportedLoggingRequestProperties);
    }

}
