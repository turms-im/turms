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

package im.turms.server.common.infra.property.env.common.location;


import com.fasterxml.jackson.annotation.JsonView;
import im.turms.server.common.infra.property.metadata.annotation.Description;
import im.turms.server.common.infra.property.metadata.view.MutablePropertiesView;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class UsersNearbyRequestProperties {

    @JsonView(MutablePropertiesView.class)
    @Description("The default maximum available number of nearby users")
    @Min(0)
    private short defaultMaxAvailableNearbyUsersNumber = 20;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum allowed available number of users nearby records")
    @Min(0)
    private short maxAvailableUsersNearbyNumberLimit = 100;

    @JsonView(MutablePropertiesView.class)
    @Description("The default maximum distance limit in meters")
    @Min(0)
    private int defaultMaxDistanceMeters = 10_000;

    @JsonView(MutablePropertiesView.class)
    @Description("The maximum distance limit in meters")
    @Min(0)
    private int maxDistanceMeters = 10_000;

}