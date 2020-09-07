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

package im.turms.server.common.property.env.service.business.activity;

import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.bo.property.ActivityLoggingCategory;
import im.turms.server.common.bo.property.ActivityLoggingRequest;
import im.turms.server.common.property.constant.ActivityLoggingCategoryName;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import java.util.LinkedHashSet;

/**
 * @author James Chen
 */
@Data
public class ActivityLoggingProperties {

    private LinkedHashSet<ActivityLoggingCategory> includedCategories;
    private LinkedHashSet<ActivityLoggingCategoryName> excludedCategoryNames;

    private LinkedHashSet<ActivityLoggingRequest> includedRequests;
    private LinkedHashSet<TurmsRequest.KindCase> excludedRequestNames;

    @NestedConfigurationProperty
    private StatisticsProperties statistics = new StatisticsProperties();

}