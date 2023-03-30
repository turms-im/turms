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

package im.turms.server.common.infra.property.env.common.healthcheck;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;

import static im.turms.server.common.infra.unit.ByteSizeUnit.MB;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class MemoryHealthCheckProperties {

    // Memory Usage

    @Description("The server will refuse to serve when the used memory (heap memory + JVM internal non-heap memory + direct buffer pool) "
            + "exceeds the physical memory of the percentage. "
            + "The server will try to reserve max(maxAvailableMemoryPercentage of the physical memory, minFreeSystemMemoryBytes) "
            + "for kernel and other processes. "
            + "Note that the max available memory percentage does not conflict with "
            + "the usage of limiting memory in docker because docker limits the "
            + "memory of the container, while this memory percentage only limits "
            + "the available memory for JVM")
    @Max(100)
    @Min(1)
    private int maxAvailableMemoryPercentage = 95;

    @Description("The server will refuse to serve when the used direct memory exceeds the max direct memory of the percentage "
            + "to try to avoid OutOfMemoryError")
    @Max(100)
    @Min(1)
    private int maxAvailableDirectMemoryPercentage = 95;

    @Description("The server will refuse to serve when the free system memory is less than minFreeSystemMemoryBytes")
    private int minFreeSystemMemoryBytes = 128 * MB;

    // Memory Usage Warning

    @Description("Log warning messages if the used direct memory exceeds the max direct memory of the percentage")
    @Max(100)
    @Min(0)
    private int directMemoryWarningThresholdPercentage = 50;

    @Description("Log warning messages if the used heap memory exceeds the max heap memory of the percentage")
    @Max(100)
    @Min(0)
    private int heapMemoryWarningThresholdPercentage = 95;

    private int minMemoryWarningIntervalSeconds = 10;

    // GC

    @Description("If the used memory has used the reserved memory specified by maxAvailableMemoryPercentage and minFreeSystemMemoryBytes, "
            + "try to start GC when the used heap memory exceeds the max heap memory of the percentage")
    @Max(100)
    @Min(0)
    private int heapMemoryGcThresholdPercentage = 60;

    private int minHeapMemoryGcIntervalSeconds = 10;

}