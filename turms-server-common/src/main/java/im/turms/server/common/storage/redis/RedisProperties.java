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

package im.turms.server.common.storage.redis;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import im.turms.server.common.infra.property.metadata.Description;
import im.turms.server.common.storage.redis.sharding.ConsistentHashingShardingAlgorithm;
import im.turms.server.common.storage.redis.sharding.ShardingAlgorithm;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class RedisProperties {

    @Description("Reference: https://lettuce.io/core/release/reference/index.html#redisuri.uri-syntax")
    private transient List<String> uriList = List.of("redis://localhost");

    private transient ShardingAlgorithm shardingAlgorithm =
            new ConsistentHashingShardingAlgorithm();

}
