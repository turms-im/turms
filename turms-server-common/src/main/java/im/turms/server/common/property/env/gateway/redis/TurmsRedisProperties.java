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

package im.turms.server.common.property.env.gateway.redis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import im.turms.server.common.property.env.gateway.redis.sharding.RedisShardingProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.data.annotation.Transient;

import java.util.List;

/**
 * @author James Chen
 */
@AllArgsConstructor
@Builder(toBuilder = true)
@Data
@NoArgsConstructor
public class TurmsRedisProperties {

    private static final List<RedisProperties> REDIS_PROPERTIES_LIST = List.of(new RedisProperties());

    @NestedConfigurationProperty
    @JsonIgnore
    @Transient
    private RedisShardingProperties shardingProperties = new RedisShardingProperties();

    @JsonIgnore
    @Transient
    private List<RedisProperties> session = REDIS_PROPERTIES_LIST;

    @JsonIgnore
    @Transient
    private List<RedisProperties> location = REDIS_PROPERTIES_LIST;

    @JsonIgnore
    @Transient
    private List<RedisProperties> loginFailureReason = REDIS_PROPERTIES_LIST;

    @JsonIgnore
    @Transient
    private List<RedisProperties> sessionDisconnectionReason = REDIS_PROPERTIES_LIST;

}