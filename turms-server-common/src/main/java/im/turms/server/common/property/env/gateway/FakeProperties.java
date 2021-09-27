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

package im.turms.server.common.property.env.gateway;

import im.turms.server.common.property.metadata.annotation.Description;
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
public class FakeProperties {

    @Description("Whether to fake data. Note that faking only works in non-production environments")
    private boolean enabled;

    @Min(0)
    private long firstUserId = 100;

    @Description("Run the number of real clients as faked users with an ID from [firstUserId, firstUserId + userCount) " +
            "to connect to turms-gateway. " +
            "So please ensure you has set turms.service.fake.userCount to a number larger than or equals to (firstUserId + userCount)")
    @Min(0)
    private int userCount = 100;

    @Description("The number of requests to send per interval. " +
            "If requestIntervalMillis is 1000, requestCountPerInterval is TPS in fact")
    @Min(1)
    private int requestCountPerInterval = 10;

    @Description("The interval to send request")
    @Min(1)
    private int requestIntervalMillis = 1000;

}