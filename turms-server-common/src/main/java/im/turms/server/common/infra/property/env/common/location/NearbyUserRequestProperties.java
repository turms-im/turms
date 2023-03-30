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

import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.infra.property.metadata.GlobalProperty;
import im.turms.server.common.infra.property.metadata.MutableProperty;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class NearbyUserRequestProperties {

    @Description("The default maximum allowed number of nearby users")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private short defaultMaxNearbyUserCount = 20;

    @Description("The maximum allowed number of nearby users")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private short maxNearbyUserCount = 100;

    @Description("The default maximum allowed distance in meters")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int defaultMaxDistanceMeters = 10_000;

    @Description("The maximum allowed distance in meters")
    @GlobalProperty
    @MutableProperty
    @Min(0)
    private int maxDistanceMeters = 10_000;

}