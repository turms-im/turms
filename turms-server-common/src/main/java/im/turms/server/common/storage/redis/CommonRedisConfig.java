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

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.property.env.common.CommonRedisProperties;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContext;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContextPool;

/**
 * @author James Chen
 * @see org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisReactiveAutoConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.RedisConnectionConfiguration
 * @see org.springframework.boot.autoconfigure.data.redis.LettuceConnectionConfiguration
 */
public abstract class CommonRedisConfig {

    private final TurmsRedisClientManager sessionRedisClientManager;
    private final TurmsRedisClientManager locationRedisClientManager;

    private final TurmsRedisClient ipBlocklistRedisClient;
    private final TurmsRedisClient userIdBlocklistRedisClient;

    private final List<TurmsRedisClientManager> registeredClientManagers = new LinkedList<>();
    private final List<TurmsRedisClient> registeredClients = new LinkedList<>();

    protected CommonRedisConfig(
            TurmsApplicationContext context,
            CommonRedisProperties redisProperties,
            boolean treatUserIdAndDeviceTypeAsUniqueUser) {
        sessionRedisClientManager = newSessionRedisClientManager(redisProperties.getSession());
        locationRedisClientManager = newLocationRedisClientManager(redisProperties.getLocation(),
                treatUserIdAndDeviceTypeAsUniqueUser);
        ipBlocklistRedisClient = newIpBlocklistRedisClient(redisProperties.getIpBlocklist()
                .getUri());
        userIdBlocklistRedisClient =
                newUserIdBlocklistRedisClient(redisProperties.getUserIdBlocklist()
                        .getUri());

        context.addShutdownHook(JobShutdownOrder.CLOSE_REDIS_CONNECTIONS, this::destroy);
    }

    public static TurmsRedisClientManager newSessionRedisClientManager(RedisProperties properties) {
        return new TurmsRedisClientManager(
                properties,
                RedisCodecContextPool.USER_SESSIONS_STATUS_CODEC_CONTEXT);
    }

    public static TurmsRedisClientManager newLocationRedisClientManager(
            RedisProperties properties,
            boolean treatUserIdAndDeviceTypeAsUniqueUser) {
        RedisCodecContext codecContext = treatUserIdAndDeviceTypeAsUniqueUser
                ? RedisCodecContextPool.GEO_USER_SESSION_ID_CODEC_CONTEXT
                : RedisCodecContextPool.GEO_USER_ID_CODEC_CONTEXT;
        return new TurmsRedisClientManager(properties, codecContext);
    }

    public static TurmsRedisClient newIpBlocklistRedisClient(String uri) {
        return new TurmsRedisClient(
                uri,
                RedisCodecContext.builder()
                        .build());
    }

    public static TurmsRedisClient newUserIdBlocklistRedisClient(String uri) {
        return new TurmsRedisClient(
                uri,
                RedisCodecContext.builder()
                        .build());
    }

    public void registerClientManagers(List<TurmsRedisClientManager> clientManagers) {
        registeredClientManagers.addAll(clientManagers);
    }

    public void registerClients(List<TurmsRedisClient> clients) {
        registeredClients.addAll(clients);
    }

    public Mono<Void> destroy(long timeoutMillis) {
        List<Mono<Void>> monos = new LinkedList<>();
        for (TurmsRedisClientManager manager : CollectionUtil.union(registeredClientManagers,
                List.of(sessionRedisClientManager, locationRedisClientManager))) {
            monos.add(manager.destroy(timeoutMillis));
        }
        for (TurmsRedisClient client : CollectionUtil.union(registeredClients,
                List.of(ipBlocklistRedisClient, userIdBlocklistRedisClient))) {
            monos.add(client.destroy(timeoutMillis));
        }
        return Mono.whenDelayError(monos);
    }

    @Bean
    public TurmsRedisClientManager sessionRedisClientManager() {
        return sessionRedisClientManager;
    }

    @Bean
    public TurmsRedisClientManager locationRedisClientManager() {
        return locationRedisClientManager;
    }

    @Bean
    public TurmsRedisClient ipBlocklistRedisClient() {
        return ipBlocklistRedisClient;
    }

    @Bean
    public TurmsRedisClient userIdBlocklistRedisClient() {
        return userIdBlocklistRedisClient;
    }

}