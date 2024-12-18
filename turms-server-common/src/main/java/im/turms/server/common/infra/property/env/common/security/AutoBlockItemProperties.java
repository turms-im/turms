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

    public static final List<BlockLevelProperties> DEFAULT_BLOCK_LEVELS =
            List.of(new BlockLevelProperties(10 * 60, 60 * 1000, 5),
                    new BlockLevelProperties(30 * 60, 60 * 1000, 1),
                    new BlockLevelProperties(60 * 60, 60 * 1000, 1));

    protected boolean enabled = true;

    protected List<BlockLevelProperties> blockLevels = DEFAULT_BLOCK_LEVELS;

    @AllArgsConstructor
    @Builder(toBuilder = true)
    @Data
    @NoArgsConstructor
    public static class BlockLevelProperties {

        @Description("Block the client for the specified duration in seconds. "
                + "After the block duration, the block level will be reset, "
                + "and the client will be unblocked automatically")
        @Min(1)
        protected long blockDurationSeconds = 10L * 60;

        @Description("If a user's block level is the previous level of this level, "
                + "reduce the trigger time by 1 when the time passes. "
                + "If 0, never reduce the trigger times and "
                + "the block status will remain in the memory until the server is closed")
        @Min(0)
        protected int reduceOneTriggerTimeIntervalMillis = 60 * 1000;

        @Description("When the block condition is triggered the specified times, advance to this block level")
        @Min(1)
        protected int triggerTimesThreshold = 1;

    }

}