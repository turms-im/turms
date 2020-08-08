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


import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.property.metadata.annotation.Description;
import im.turms.server.common.property.metadata.view.MutablePropertiesView;
import lombok.Data;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@Data
public class LocationProperties {

    @Description("Whether to handle users' locations")
    private boolean enabled = true;

    @Description("Whether to treat the pair of user ID and device type as an unique user when querying users nearby. If false, only the user ID is used to identify an unique user")
    private boolean treatUserIdAndDeviceTypeAsUniqueUser = false;

    @JsonView(MutablePropertiesView.class)
    @Description("The default maximum available number of users nearby records per query request")
    @Min(0)
    private short defaultMaxAvailableUsersNearbyNumberPerQuery = 20;

    @JsonView(MutablePropertiesView.class)
    @Description("The default maximum distance per query request")
    @DecimalMin("0")
    private double defaultMaxDistancePerQuery = 0.01;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum allowed available number of users nearby records per query request")
    @Min(0)
    private short maxAvailableUsersNearbyNumberLimitPerQuery = 100;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum allowed distance per query request")
    @DecimalMin("0")
    private double maxDistanceLimitPerQuery = 0.1;

}