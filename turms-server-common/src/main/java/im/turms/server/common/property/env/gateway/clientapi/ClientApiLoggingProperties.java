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

package im.turms.server.common.property.env.gateway.clientapi;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.property.constant.LoggingRequestCategory;
import im.turms.server.common.property.env.common.clientapi.CommonClientApiLoggingProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingCategoryProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class ClientApiLoggingProperties extends CommonClientApiLoggingProperties {

    private static final String DESC_STRATEGY_TO_GET_INCLUDED_NOTIFICATIONS = "Turms will get the notifications to log from the union of " +
            "\"includedNotificationCategories\" and \"includedNotifications\" " +
            "except the notifications included in \"excludedNotificationCategories\" and \"excludedNotificationTypes\". " +
            "Note that only turms supports to log notifications and turms-gateway doesn't because turms-gateway won't " +
            "parse the raw data of the notification passed from turms";

    // Notification

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_NOTIFICATIONS)
    private LinkedHashSet<LoggingCategoryProperties> includedNotificationCategories = new LinkedHashSet<>();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_NOTIFICATIONS)
    private LinkedHashSet<LoggingRequestProperties> includedNotifications = new LinkedHashSet<>();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_NOTIFICATIONS)
    private Set<LoggingRequestCategory> excludedNotificationCategories = Collections.emptySet();

    @Description(DESC_STRATEGY_TO_GET_INCLUDED_NOTIFICATIONS)
    private Set<TurmsRequest.KindCase> excludedNotificationTypes = Collections.emptySet();

}