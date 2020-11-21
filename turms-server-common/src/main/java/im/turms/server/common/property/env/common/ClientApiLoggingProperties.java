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

package im.turms.server.common.property.env.common;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.property.constant.LoggingRequestCategory;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingCategoryProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import lombok.Data;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author James Chen
 */
@Data
public class ClientApiLoggingProperties {

    private static final String DESC_STRATEGY_TO_GET_INCLUDED_REQUESTS = "Turms will get the requests to log from the union of " +
            "\"includedRequestCategories\" and \"includedRequests\" " +
            "except the requests included in \"excludedRequestCategories\" and \"excludedRequestTypes\"";

    private static final String DESC_STRATEGY_TO_GET_INCLUDED_RESPONSES = "Turms will get the responses to log from the union of " +
            "\"includedResponseCategories\" and \"includedResponses\" " +
            "except the responses included in \"excludedResponseCategories\" and \"excludedResponseTypes\"";

    /**
     * @implNote Use LinkedHashSet so that the properties (e.g. sample rate) of the previous categories
     * can be replaced by the ones of the following categories for common requests.
     */
    @Description(DESC_STRATEGY_TO_GET_INCLUDED_REQUESTS)
    private LinkedHashSet<LoggingCategoryProperties> includedRequestCategories = new LinkedHashSet<>();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_REQUESTS)
    private LinkedHashSet<LoggingRequestProperties> includedRequests = new LinkedHashSet<>();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_REQUESTS)
    private Set<LoggingRequestCategory> excludedRequestCategories = Collections.emptySet();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_REQUESTS)
    private Set<TurmsRequest.KindCase> excludedRequestTypes = Collections.emptySet();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_RESPONSES)
    private LinkedHashSet<LoggingCategoryProperties> includedResponseCategories = new LinkedHashSet<>();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_RESPONSES)
    private LinkedHashSet<LoggingRequestProperties> includedResponses = new LinkedHashSet<>();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_RESPONSES)
    private Set<LoggingRequestCategory> excludedResponseCategories = Collections.emptySet();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_RESPONSES)
    private Set<TurmsRequest.KindCase> excludedResponseTypes = Collections.emptySet();

}