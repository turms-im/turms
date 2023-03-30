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

package im.turms.server.common.infra.property.env.service.env.adminapi;

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import im.turms.server.common.infra.property.env.common.adminapi.CommonAdminApiProperties;
import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
public class AdminApiProperties extends CommonAdminApiProperties {

    @Description("Whether to allow administrators to delete data without any filter. "
            + "Better false to prevent administrators from deleting all data by accident")
    @GlobalProperty
    @MutableProperty
    private boolean allowDeleteWithoutFilter;

    // FIXME: The property is unused
    @Description("The maximum day difference per query request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxDayDifferencePerRequest = 3 * 30;

    @Description("The maximum hour difference per count request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxHourDifferencePerCountRequest = 24;

    @Description("The maximum day difference per count request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxDayDifferencePerCountRequest = 31;

    @Description("The maximum month difference per count request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxMonthDifferencePerCountRequest = 12;

    @Description("The maximum available records per query request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxAvailableRecordsPerRequest = 1000;

    // FIXME: The property is unused
    @Description("The maximum available online users' status per query request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxAvailableOnlineUsersStatusPerRequest = 20;

    @Description("The default available records per query request")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int defaultAvailableRecordsPerRequest = 10;

    public AdminApiProperties() {
        http.setPort(8510);
    }

}