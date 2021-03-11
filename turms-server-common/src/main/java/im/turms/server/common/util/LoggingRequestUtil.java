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

package im.turms.server.common.util;

import com.google.common.collect.Sets;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.property.constant.LoggingRequestCategory;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingCategoryProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author James Chen
 */
public final class LoggingRequestUtil {

    private LoggingRequestUtil() {
    }

    public static Map<TurmsRequest.KindCase, LoggingRequestProperties> getSupportedLoggingRequestProperties(
            Set<LoggingCategoryProperties> includedCategories,
            Set<LoggingRequestProperties> loggingRequests,
            Set<LoggingRequestCategory> excludedCategories,
            Set<TurmsRequest.KindCase> excludedRequestTypes) {
        Map<TurmsRequest.KindCase, LoggingRequestProperties> result = new EnumMap<>(TurmsRequest.KindCase.class);

        // 1. handle included categories
        for (LoggingCategoryProperties includedCategory : includedCategories) {
            Set<LoggingRequestProperties> requestProperties = getRequestProperties(includedCategory);
            for (LoggingRequestProperties request : requestProperties) {
                result.put(request.getType(), request);
            }
        }

        // 2. handle included requests
        for (LoggingRequestProperties loggingRequest : loggingRequests) {
            result.put(loggingRequest.getType(), loggingRequest);
        }

        // 3. handle excluded category names
        for (LoggingRequestCategory excludedCategory : excludedCategories) {
            Set<TurmsRequest.KindCase> requestTypes = excludedCategory.getRequestTypes();
            for (TurmsRequest.KindCase requestType : requestTypes) {
                result.remove(requestType);
            }
        }

        // 4. handle excluded request names
        for (TurmsRequest.KindCase excludedRequestType : excludedRequestTypes) {
            result.remove(excludedRequestType);
        }

        return result;
    }

    public static boolean shouldLog(TurmsRequest.KindCase requestType,
                                    Map<TurmsRequest.KindCase, LoggingRequestProperties> requestPropertiesMap) {
        LoggingRequestProperties requestProperties = requestPropertiesMap.get(requestType);
        if (requestProperties == null) {
            return false;
        }
        float sampleRate = requestProperties.getSampleRate();
        if (sampleRate > 0) {
            if (sampleRate < 1.0f) {
                return ThreadLocalRandom.current().nextFloat() < sampleRate;
            } else {
                return true;
            }
        }
        return false;
    }

    private static Set<LoggingRequestProperties> getRequestProperties(LoggingCategoryProperties categoryProperties) {
        LoggingRequestCategory category = categoryProperties.getCategory();
        Set<TurmsRequest.KindCase> requestTypes = category.getRequestTypes();
        if (requestTypes.isEmpty()) {
            return Collections.emptySet();
        } else {
            Set<LoggingRequestProperties> loggingRequests = Sets.newHashSetWithExpectedSize(requestTypes.size());
            for (TurmsRequest.KindCase requestType : requestTypes) {
                loggingRequests.add(new LoggingRequestProperties(requestType, categoryProperties.getSampleRate()));
            }
            return loggingRequests;
        }
    }

}
