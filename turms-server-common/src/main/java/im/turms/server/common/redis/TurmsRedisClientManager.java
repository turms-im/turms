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

package im.turms.server.common.redis;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ListMultimap;
import im.turms.server.common.redis.codec.context.RedisCodecContext;
import im.turms.server.common.redis.script.RedisScript;
import im.turms.server.common.redis.sharding.ShardingAlgorithm;
import im.turms.server.common.util.ByteBufUtil;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoWithin;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.geo.Point;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
@Log4j2
public class TurmsRedisClientManager {

    private final List<TurmsRedisClient> clients;
    private final ShardingAlgorithm shardingAlgorithm;

    public TurmsRedisClientManager(RedisProperties properties,
                                   RedisCodecContext serializationContext) {
        shardingAlgorithm = properties.getShardingAlgorithm();
        List<String> uriList = properties.getUriList();
        clients = new ArrayList<>(uriList.size());
        for (String uri : uriList) {
            clients.add(new TurmsRedisClient(uri, serializationContext));
        }
    }

    public void destroy() {
        for (TurmsRedisClient client : clients) {
            try {
                client.destroy();
            } catch (Exception e) {
                log.error("Failed to shutdown a connection", e);
            }
        }
    }

    // Hashes

    public Mono<Long> hdel(Long shardKey, Object key, Object[] fields) {
        return getClient(shardKey).hdel(key, fields);
    }

    public <K, V> Flux<Map.Entry<K, V>> hgetall(Long shardKey, Object key) {
        Flux hgetall = getClient(shardKey).hgetall(key);
        return hgetall;
    }

    // Geo

    public Mono<Long> geoadd(Long shardKey, Object key, Point coordinates, Object member) {
        return getClient(shardKey).geoadd(key, coordinates, member);
    }

    public Flux<GeoCoordinates> geopos(Long shardKey, Object key, Object... members) {
        return getClient(shardKey).geopos(key, members);
    }

    public <T> Flux<GeoWithin<T>> georadiusbymember(Long shardKey,
                                                    Object key,
                                                    Object member,
                                                    double distanceMeters,
                                                    GeoArgs args) {
        return getClient(shardKey).georadiusbymember(key, member, distanceMeters, args);
    }

    public Mono<Long> georem(Long shardKey, Object key, Object... members) {
        return getClient(shardKey).georem(key, members);
    }

    // Scripting

    public <T> Mono<T> eval(Long shardKey, RedisScript script, Object... keys) {
        ByteBuf[] buffers = ByteBufUtil.objs2Buffers(keys);
        return getClient(shardKey).eval(script, buffers);
    }

    public Mono<Void> eval(Collection<Long> shardKeys, RedisScript script, ByteBuf[] keys) {
        int clientSize = clients.size();
        if (clientSize == 1) {
            TurmsRedisClient client = clients.get(0);
            return client.eval(script, keys)
                    .then();
        }
        ListMultimap<TurmsRedisClient, Long> map = ArrayListMultimap.create(clientSize, shardKeys.size() / clientSize);
        for (Long shardKey : shardKeys) {
            map.put(getClient(shardKey), shardKey);
        }
        Map<TurmsRedisClient, Collection<Long>> clientAndUserIds = map.asMap();
        int resultSize = clientAndUserIds.keySet().size();
        List<Mono<?>> list = new ArrayList<>(resultSize);
        for (ByteBuf key : keys) {
            key.retain(resultSize);
        }
        for (Map.Entry<TurmsRedisClient, Collection<Long>> entry : clientAndUserIds.entrySet()) {
            Mono<?> result = entry.getKey()
                    .eval(script, keys);
            list.add(result);
        }
        return Mono.when(list)
                .doFinally(type -> {
                    for (ByteBuf key : keys) {
                        key.release();
                    }
                });
    }

    // Internal

    private TurmsRedisClient getClient(Long shardKey) {
        return clients.get(shardingAlgorithm.doSharding(shardKey, clients.size()));
    }

}
