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

package im.turms.server.common.property.env.service.env;

import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.env.common.AdminApiDiscoveryProperties;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class AdminApiProperties {

    @Description("Whether to enable the APIs for administrators")
    private boolean enabled = true;

    @JsonView(MutablePropertiesView.class)
    @Description("Whether to allow administrators to delete data without any filter")
    private boolean allowDeletingWithoutFilter = true;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum day difference per query request")
    @Min(0)
    private int maxDayDifferencePerRequest = 3 * 30;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum hour difference per count request")
    @Min(0)
    private int maxHourDifferencePerCountRequest = 24;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum day difference per count request")
    @Min(0)
    private int maxDayDifferencePerCountRequest = 31;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum month difference per count request")
    @Min(0)
    private int maxMonthDifferencePerCountRequest = 12;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum available records per query request")
    @Min(0)
    private int maxAvailableRecordsPerRequest = 1000;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum available online users' status per query request")
    @Min(0)
    private int maxAvailableOnlineUsersStatusPerRequest = 20;

    @JsonView(MutablePropertiesView.class)
    @Description("The default available records per query request")
    @Min(0)
    private int defaultAvailableRecordsPerRequest = 10;

    private boolean enableSwagger = false;

    @JsonView(MutablePropertiesView.class)
    @NestedConfigurationProperty
    private AdminApiDiscoveryProperties discovery = new AdminApiDiscoveryProperties();

}