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

import im.turms.server.common.property.metadata.annotation.Description;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Convention over configuration
 *
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class MonitorProperties {

    @Min(1)
    private int updateMemoryInfoIntervalSeconds = 3;

    // Warning

    @Description("Log warning messages if the used direct memory exceeds the max direct memory of the percentage")
    @Max(100)
    @Min(0)
    private int directMemoryWarningThresholdPercentage = 50;

    @Description("Log warning messages if the used heap memory exceeds the max heap memory of the percentage")
    @Max(100)
    @Min(0)
    private int heapMemoryWarningThresholdPercentage = 95;

    private int minMemoryWarningIntervalSeconds = 10;

}