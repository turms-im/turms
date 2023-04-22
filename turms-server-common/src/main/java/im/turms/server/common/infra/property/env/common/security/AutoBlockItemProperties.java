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

package im.turms.server.common.infra.property.env.common.security;

import java.util.List;
import jakarta.validation.constraints.Min;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class AutoBlockItemProperties {

    public static final List<BlockLevel> DEFAULT_BLOCK_LEVELS =
            List.of(new BlockLevel(10 * 60, 60 * 1000, 1),
                    new BlockLevel(30 * 60, 60 * 1000, 1),
                    new BlockLevel(60 * 60, 60 * 1000, 0));

    private boolean enabled;

    @Description("Block the client when the block condition is triggered the times")
    @Min(0)
    private int blockTriggerTimes = 5;

    private List<BlockLevel> blockLevels = DEFAULT_BLOCK_LEVELS;

    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    public static class BlockLevel {

        @Description("Block the client for the specified duration in seconds")
        @Min(1)
        private long blockDurationSeconds = 10L * 60;

        @Description("Reduce the trigger time by 1 when the time passes. "
                + "If 0, never reduce the trigger times and "
                + "the block status will remain in the memory until the server is closed")
        @Min(0)
        private int reduceOneTriggerTimeIntervalMillis = 60 * 1000;

        @Description("Go to the next block level when the block condition is triggered the times")
        @Min(0)
        private int goNextLevelTriggerTimes = 1;

    }

}