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

import im.turms.server.common.domain.location.bo.Coordinates;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.storage.redis.codec.context.RedisCodecContext;
import im.turms.server.common.storage.redis.script.RedisScript;
import im.turms.server.common.storage.redis.sharding.ShardingAlgorithm;
import io.lettuce.core.GeoArgs;
import io.lettuce.core.GeoCoordinates;
import io.lettuce.core.GeoWithin;
import io.lettuce.core.protocol.CommandArgsUtil;
import io.lettuce.core.protocol.CustomKeyBuffer;
import io.lettuce.core.protocol.LongKeyGenerator;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.Collection;
import java.util.IdentityHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * @author James Chen
 */
public class TurmsRedisClientManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsRedisClientManager.class);

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
                LOGGER.error("Failed to shutdown a connection", e);
            }
        }
    }

    public <T> Mono<Void> execute(Set<Long> shardKeys, BiFunction<TurmsRedisClient, Collection<Long>, Mono<T>> execute) {
        int size = shardKeys.size();
        if (size == 0) {
            return Mono.empty();
        } else if (size == 1) {
            return execute.apply(getClient(shardKeys.iterator().next()), shardKeys)
                    .then();
        }
        Map<TurmsRedisClient, Collection<Long>> clients = new IdentityHashMap<>();
        for (Long shardKey : shardKeys) {
            TurmsRedisClient client = getClient(shardKey);
            Collection<Long> collection = clients.computeIfAbsent(client, k -> new LinkedList<>());
            collection.add(shardKey);
        }
        Set<Map.Entry<TurmsRedisClient, Collection<Long>>> entries = clients.entrySet();
        List<Mono<Void>> results = new ArrayList<>(entries.size());
        for (Map.Entry<TurmsRedisClient, Collection<Long>> entry : entries) {
            results.add(execute.apply(entry.getKey(), entry.getValue()).then());
        }
        return Mono.whenDelayError(results);
    }

    public Mono<Long> del(Long shardKey, Collection<ByteBuf> keys) {
        return getClient(shardKey).del(keys);
    }

    public Mono<Long> incr(Long shardKey, ByteBuf key) {
        return getClient(shardKey).incr(key);
    }

    // Hashes

    public Mono<Long> hdel(Long shardKey, Object key, Object[] fields) {
        return getClient(shardKey).hdel(key, fields);
    }

    public <K, V> Flux<Map.Entry<K, V>> hgetall(Long shardKey, Object key) {
        return (Flux) getClient(shardKey).hgetall(key);
    }

    // Geo

    public Mono<Long> geoadd(Long shardKey, Object key, Coordinates coordinates, Object member) {
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

    /**
     * In fact, the method is designed for {@link UserStatusService#updateOnlineUsersTtl} currently
     *
     * @param keyGenerator The size of keys should not be larger than 1,048,576(1024*1024), or Redis will throw
     */
    public Mono<Void> eval(RedisScript script, short firstKey, LongKeyGenerator keyGenerator) {
        int expectedKeySize = Math.max(keyGenerator.expectedSize(), 1);
        int clientSize = clients.size();
        long key;
        // fast path
        if (clientSize == 1) {
            key = keyGenerator.next();
            if (key == -1) {
                return Mono.empty();
            }
            ByteBuf keysBuffer = PooledByteBufAllocator.DEFAULT
                    .directBuffer(expectedKeySize * (Long.BYTES + 16));
            int keyCount = 1;
            try {
                CommandArgsUtil.writeRawShortArg(keysBuffer, firstKey);
                do {
                    keyCount++;
                    CommandArgsUtil.writeRawLongArg(keysBuffer, key);
                    key = keyGenerator.next();
                } while (key != -1);
            } catch (Exception e) {
                ByteBufUtil.safeEnsureReleased(keysBuffer);
                throw e;
            }
            TurmsRedisClient client = clients.get(0);
            return client.eval(script, keyCount, new CustomKeyBuffer(keysBuffer))
                    .then();
        }
        // slow path
        int keysPerClient = Math.max(expectedKeySize / clientSize, 1);
        Map<TurmsRedisClient, BufferEntry> keyForClients = new IdentityHashMap<>(clientSize);
        while ((key = keyGenerator.next()) != -1) {
            TurmsRedisClient client = getClient(key);
            BufferEntry entry = keyForClients.get(client);
            ByteBuf buffer;
            if (entry == null) {
                buffer = PooledByteBufAllocator.DEFAULT
                        .directBuffer(keysPerClient * (Long.BYTES + 16));
                CommandArgsUtil.writeRawShortArg(buffer, firstKey);
                entry = new BufferEntry(buffer, 1);
                keyForClients.put(client, entry);
            } else {
                buffer = entry.buffer;
                entry.incrementKeyCount();
            }
            CommandArgsUtil.writeRawLongArg(buffer, key);
        }
        int targetClientSize = keyForClients.size();
        if (targetClientSize == 0) {
            return Mono.empty();
        }
        List<Mono<?>> list = new ArrayList<>(targetClientSize);
        for (Map.Entry<TurmsRedisClient, BufferEntry> entry : keyForClients.entrySet()) {
            BufferEntry bufferEntry = entry.getValue();
            Mono<?> result = entry.getKey()
                    .eval(script, bufferEntry.keyCount, new CustomKeyBuffer(bufferEntry.buffer));
            list.add(result);
        }
        return Mono.whenDelayError(list);
    }

    // Internal

    private TurmsRedisClient getClient(long shardKey) {
        return clients.get(shardingAlgorithm.doSharding(shardKey, clients.size()));
    }

    @AllArgsConstructor
    private static class BufferEntry {
        final ByteBuf buffer;
        int keyCount;

        void incrementKeyCount() {
            keyCount++;
        }
    }

}
