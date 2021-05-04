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
public class ServerAvailabilityProperties {

    private static final String MAX_MEMORY_PERCENTAGE_DESC =
            "Do not use 100% memory to reserve some memory for JVM internal use. " +
                    "Note that the max available memory percentage doesn't conflict with " +
                    "the usage of limiting memory in docker because docker limits the " +
                    "memory of the JVM process, while the memory percentage only limits " +
                    "the available managed memory for our (server developers) use rather " +
                    "than JVM internal use.";

    @Description(MAX_MEMORY_PERCENTAGE_DESC)
    @Min(1)
    private int maxAvailableMemoryPercentage = 90;

    @Description(MAX_MEMORY_PERCENTAGE_DESC)
    @Min(1)
    private int maxAvailableDirectPercentage = 90;

    @Description(MAX_MEMORY_PERCENTAGE_DESC)
    @Min(1)
    private int maxAvailableHeapPercentage = 90;

}