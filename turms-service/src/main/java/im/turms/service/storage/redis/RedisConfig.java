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

package im.turms.service.storage.redis;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.ServiceProperties;
import im.turms.server.common.infra.property.env.service.business.message.SequenceIdProperties;
import im.turms.server.common.storage.redis.CommonRedisConfig;
import im.turms.server.common.storage.redis.RedisProperties;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContext;

/**
 * @author James Chen
 */
@Configuration
public class RedisConfig extends CommonRedisConfig {

    private final TurmsRedisClientManager sequenceIdRedisClientManager;

    protected RedisConfig(
            TurmsApplicationContext context,
            TurmsPropertiesManager propertiesManager) {
        super(context,
                propertiesManager.getLocalProperties()
                        .getService()
                        .getRedis(),
                propertiesManager.getLocalProperties()
                        .getLocation()
                        .isTreatUserIdAndDeviceTypeAsUniqueUser());
        ServiceProperties serviceProperties = propertiesManager.getLocalProperties()
                .getService();
        SequenceIdProperties sequenceIdProperties = serviceProperties.getMessage()
                .getSequenceId();
        sequenceIdRedisClientManager = sequenceIdProperties.isUseSequenceIdForGroupConversation()
                || sequenceIdProperties.isUseSequenceIdForPrivateConversation()
                        ? newSequenceIdRedisClientManager(serviceProperties.getRedis()
                                .getSequenceId())
                        : null;
        if (sequenceIdRedisClientManager != null) {
            registerClientManagers(List.of(sequenceIdRedisClientManager));
        }
    }

    public static TurmsRedisClientManager newSequenceIdRedisClientManager(
            RedisProperties properties) {
        return new TurmsRedisClientManager(
                properties,
                RedisCodecContext.builder()
                        .build());
    }

    @Bean
    public TurmsRedisClientManager sequenceIdRedisClientManager() {
        return sequenceIdRedisClientManager;
    }

}