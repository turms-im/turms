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

package im.turms.server.common.domain.blocklist.manager;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.blocklist.bo.BlockedClient;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.lang.MathUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.storage.redis.TurmsRedisClient;
import im.turms.server.common.storage.redis.script.RedisScript;

/**
 * @author James Chen
 * @implNote For block/unblock actions, they take effects on the local node once the block/unblock
 *           method returns, and the actions will be pushed to Redis to share action logs for other
 *           nodes to sync.
 *           <p>
 *           If a push action fails, we will NOT retry to push it to keep logic simple currently, so
 *           it will only take effects on the local node. (To implement "retry", we need to
 *           implement "pendingActions" and add "log time" for each action to distinguish which
 *           actions come first, which makes thing complex, we will implement it in the future)
 *           <p>
 *           By comparing the latest log ID, fetching and applying the delta logs, other nodes can
 *           get a weak consistent map of blocklist.
 *           <p>
 *           If a node just starts, or lags behind and cannot catch up, it will perform a full sync
 *           (We do NOT support sync by cursor currently).
 *           <p>
 *           The implementation of blocklist is similar to the implementation of replicated map, and
 *           both of them use a full map and logs to perform full and delta sync.
 *           <p>
 *           Data in Redis, taking IP blocklist as an example: 1. "blocklist:ip:target": Sorted Set,
 *           sorted by the block end time. Used to perform full sync 2. "blocklist:ip:timestamp":
 *           Integer. Used to detect whether the blocklist is cleared to ensure the blocklist in
 *           nodes is consistent even if the Redis server crashes or the blocklist is cleared 3.
 *           "blocklist:ip:log": List. Used to perform delta sync 4. "blocklist:ip:log_id": Counter.
 *           Used to perform delta sync
 */
public class BlocklistServiceManager<T extends Comparable<T>> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlocklistServiceManager.class);

    /**
     * Max block end date: 287396-10-12 in UTC.
     * <p>
     * From: 2^53
     */
    private static final long MAX_BLOCK_END_TIME_MILLIS = 9007199254740992L;
    private static final int LOG_ENTRY_ELEMENTS_COUNT = 3;
    private static final int BLOCK_ACTION_FLAG = 1;

    private static final ByteBufAllocator BUFFER_ALLOCATOR = PooledByteBufAllocator.DEFAULT;
    private static final ByteBuf BLOCKLIST_KEY_FOR_IP =
            ByteBufUtil.getUnreleasableDirectBuffer((byte) 'i');
    private static final ByteBuf BLOCKLIST_KEY_FOR_USER_ID =
            ByteBufUtil.getUnreleasableDirectBuffer((byte) 'u');
    private static final long UNINITIALIZED_TIMESTAMP = -1;
    private static final int UNINITIALIZED_LOG_ID = -1;

    private final RedisScript blockClientsScript;
    private final RedisScript unblockClientsScript;
    private final RedisScript getBlockedClientsScript;
    private final RedisScript evictAllBlockedClients;
    private final RedisScript evictExpiredBlockedClients;
    private final RedisScript getBlocklistLogsScript;

    private final Node node;
    private final TurmsRedisClient redisClient;

    private final boolean isIpBlocklist;
    private final int maxLogQueueSize;

    private final Consumer<T> onTargetBlocked;

    /**
     * Used to check if a user is blocked or not quickly.
     */
    private final ConcurrentHashMap<T, Long> blockedClientIdToBlockEndTimeMillis;
    /**
     * Used to remove the local expired blocked clients quickly, even if there are a lot of blocked
     * clients so that we can run eviction more frequently.
     *
     * @implNote It is acceptable to store the same IP or the user ID with different block times in
     *           the skip list because it is how the skip list works.
     */
    private final ConcurrentSkipListSet<BlockedClient<T>> blockedClientSkipList;

    private volatile long localTimestampSeconds = UNINITIALIZED_TIMESTAMP;
    private volatile int localLogId = UNINITIALIZED_LOG_ID;

    private final AtomicBoolean isSyncing = new AtomicBoolean();

    public BlocklistServiceManager(
            boolean isIpBlocklist,
            int maxLogQueueSize,
            int syncIntervalMillis,
            Node node,
            TurmsRedisClient redisClient,
            ScheduledThreadPoolExecutor threadPoolExecutor,
            RedisScript blockClientsScript,
            RedisScript unblockClientsScript,
            RedisScript getBlockedClientsScript,
            RedisScript evictAllBlockedClients,
            RedisScript evictExpiredBlockedClients,
            RedisScript getBlocklistLogsScript,
            Consumer<T> onTargetBlocked) {
        this.node = node;
        this.redisClient = redisClient;
        this.isIpBlocklist = isIpBlocklist;
        this.maxLogQueueSize = maxLogQueueSize;

        this.blockClientsScript = blockClientsScript;
        this.unblockClientsScript = unblockClientsScript;
        this.getBlockedClientsScript = getBlockedClientsScript;
        this.evictAllBlockedClients = evictAllBlockedClients;
        this.evictExpiredBlockedClients = evictExpiredBlockedClients;
        this.getBlocklistLogsScript = getBlocklistLogsScript;

        this.onTargetBlocked = onTargetBlocked;

        blockedClientSkipList = new ConcurrentSkipListSet<>((o1, o2) -> {
            int i = (int) (o1.blockEndTimeMillis() - o2.blockEndTimeMillis());
            if (i == 0) {
                return o1.id()
                        .compareTo(o2.id());
            }
            return i;
        });
        blockedClientIdToBlockEndTimeMillis = new ConcurrentHashMap<>(1024);

        try {
            resetAndSyncAllBlockedClients(false).block(Duration.ofSeconds(60));
        } catch (Exception e) {
            throw new RuntimeException("Failed to reset and sync blocked clients", e);
        }

        threadPoolExecutor.scheduleAtFixedRate(() -> {
            try {
                syncLocalBlocklist().subscribe(null,
                        t -> LOGGER
                                .error("Caught an error while synchronizing the client blocklist"));
            } catch (Exception e) {
                LOGGER.error("Caught an error while synchronizing the client blocklist");
            }
        }, syncIntervalMillis, syncIntervalMillis, TimeUnit.MILLISECONDS);
    }

    // Block and Unblock

    public Mono<Void> blockClients(Set<T> targetIds, long blockDurationSeconds) {
        if (CollectionUtils.isEmpty(targetIds) || blockDurationSeconds <= 0) {
            return Mono.empty();
        }
        long now = System.currentTimeMillis();
        long blockEndTimeMillis = MathUtil.multiply(blockDurationSeconds, 1000L, Long.MAX_VALUE);
        blockEndTimeMillis = MathUtil.add(now, blockEndTimeMillis, Long.MAX_VALUE);
        blockEndTimeMillis = Math.min(blockEndTimeMillis, MAX_BLOCK_END_TIME_MILLIS);
        int size = targetIds.size();
        ByteBuf[] args = new ByteBuf[size + 2];
        args[0] = getBlocklistKey();
        args[1] = encodeBlockEndTime(blockEndTimeMillis);
        int i = 2;
        for (T targetId : targetIds) {
            BlockedClient<T> blockedClient = new BlockedClient<>(targetId, blockEndTimeMillis);
            blockedClientIdToBlockEndTimeMillis.put(targetId, blockEndTimeMillis);
            blockedClientSkipList.add(blockedClient);
            notifyOnTargetBlockedListener(targetId);
            args[i++] = encodeId(targetId);
        }
        Mono<List<Object>> blockUsers = redisClient.eval(blockClientsScript, args);
        return blockUsers.flatMap(elements -> {
            LogVersion version =
                    new LogVersion((long) elements.get(0), (int) (long) elements.get(1));
            return checkVersionToSync(version, size);
        });
    }

    private void addBlockedClient(BlockedClient<T> blockedClient, long now) {
        long blockEndTimeInMillis = blockedClient.blockEndTimeMillis();
        if (blockEndTimeInMillis < now) {
            return;
        }
        T targetId = blockedClient.id();
        blockedClientIdToBlockEndTimeMillis.put(targetId, blockEndTimeInMillis);
        blockedClientSkipList.add(blockedClient);
        notifyOnTargetBlockedListener(targetId);
    }

    public Mono<Void> unblockTargets(Set<T> ids) {
        if (ids.isEmpty()) {
            return Mono.empty();
        }
        ByteBuf[] args = new ByteBuf[ids.size() + 1];
        args[0] = getBlocklistKey();
        int i = 1;
        for (T id : ids) {
            args[i] = encodeId(id);
            removeLocalBlockedClient(id);
            i++;
        }
        Mono<List<Object>> unblockClients = redisClient.eval(unblockClientsScript, args);
        return unblockClients.flatMap(elements -> {
            LogVersion version =
                    new LogVersion((long) elements.get(0), (int) (long) elements.get(1));
            return checkVersionToSync(version, (int) (long) elements.get(2));
        })
                .then();
    }

    private void removeLocalBlockedClient(T targetId) {
        Long blockEndTimeMillis = blockedClientIdToBlockEndTimeMillis.remove(targetId);
        if (blockEndTimeMillis == null) {
            return;
        }
        BlockedClient<T> key = new BlockedClient<>(targetId, blockEndTimeMillis);
        blockedClientSkipList.remove(key);
    }

    public Mono<Void> unblockAll() {
        blockedClientIdToBlockEndTimeMillis.clear();
        blockedClientSkipList.clear();
        return redisClient.eval(evictAllBlockedClients, getBlocklistKey());
    }

    private void evictLocalExpiredBlockedClients() {
        Iterator<BlockedClient<T>> iterator = blockedClientSkipList.iterator();
        long now = System.currentTimeMillis();
        while (iterator.hasNext()) {
            BlockedClient<T> blockedClient = iterator.next();
            if (blockedClient.blockEndTimeMillis() >= now) {
                break;
            }
            iterator.remove();
            T id = blockedClient.id();
            blockedClientIdToBlockEndTimeMillis.remove(id, blockedClient.blockEndTimeMillis());
        }
    }

    public int count() {
        return blockedClientIdToBlockEndTimeMillis.size();
    }

    public boolean isTargetBlocked(T target) {
        Long blockEndTimeMillis = blockedClientIdToBlockEndTimeMillis.get(target);
        if (blockEndTimeMillis == null) {
            return false;
        }
        if (blockEndTimeMillis < System.currentTimeMillis()) {
            blockedClientIdToBlockEndTimeMillis.remove(target, blockEndTimeMillis);
            blockedClientSkipList.remove(new BlockedClient<>(target, blockEndTimeMillis));
            return false;
        }
        return true;
    }

    @Nullable
    public BlockedClient<T> getBlockedClient(T target) {
        Long blockEndTimeMillis = blockedClientIdToBlockEndTimeMillis.get(target);
        if (blockEndTimeMillis == null) {
            return null;
        }
        if (blockEndTimeMillis < System.currentTimeMillis()) {
            blockedClientIdToBlockEndTimeMillis.remove(target, blockEndTimeMillis);
            blockedClientSkipList.remove(new BlockedClient<>(target, blockEndTimeMillis));
            return null;
        }
        return new BlockedClient<>(target, blockEndTimeMillis);
    }

    public List<BlockedClient<T>> getBlockedClients(int page, int size) {
        int offset = size * page;
        size = Math.min(size, blockedClientIdToBlockEndTimeMillis.size() - offset);
        if (size <= 0) {
            return Collections.emptyList();
        }
        List<BlockedClient<T>> blockedClients = new ChunkedArrayList<>();
        Iterator<Map.Entry<T, Long>> iterator = blockedClientIdToBlockEndTimeMillis.entrySet()
                .iterator();
        long now = System.currentTimeMillis();
        int i = 0;
        long blockEndTimeMillis;
        while (i < offset && iterator.hasNext()) {
            Map.Entry<T, Long> entry = iterator.next();
            blockEndTimeMillis = entry.getValue();
            if (blockEndTimeMillis < now) {
                iterator.remove();
                blockedClientSkipList
                        .remove(new BlockedClient<>(entry.getKey(), blockEndTimeMillis));
            } else {
                i++;
            }
        }
        i = 0;
        while (i < size && iterator.hasNext()) {
            Map.Entry<T, Long> entry = iterator.next();
            blockEndTimeMillis = entry.getValue();
            if (blockEndTimeMillis < now) {
                iterator.remove();
                blockedClientSkipList
                        .remove(new BlockedClient<>(entry.getKey(), blockEndTimeMillis));
            } else {
                blockedClients.add(new BlockedClient<>(entry.getKey(), blockEndTimeMillis));
                i++;
            }
        }
        return blockedClients;
    }

    // Logs and Synchronization

    private Mono<Void> syncLocalBlocklist() {
        if (!isSyncing.compareAndSet(false, true)) {
            return Mono.empty();
        }
        evictLocalExpiredBlockedClients();
        if (node.isLocalNodeLeader()) {
            redisClient.eval(evictExpiredBlockedClients)
                    .subscribe(null,
                            t -> LOGGER.error(
                                    "Caught an error while evicting expired blocked clients",
                                    t));
        }
        return fetchLogIdAndLogs(false).doFinally(signalType -> isSyncing.set(false));
    }

    private Mono<Void> checkVersionToSync(LogVersion version, int size) {
        if (version.timestampSeconds != localTimestampSeconds) {
            return resetAndSyncAllBlockedClients(true);
        }
        int expectedLogId = localLogId + size;
        int diff = version.logId - expectedLogId;
        if (diff == 0) {
            localLogId = expectedLogId;
            return Mono.empty();
        } else if (diff > maxLogQueueSize || diff < 0) {
            return resetAndSyncAllBlockedClients(true);
        } else {
            // Let the scheduler perform a delta sync
            return Mono.empty();
        }
    }

    private Mono<Void> resetAndSyncAllBlockedClients(boolean checkSyncStatus) {
        if (checkSyncStatus && isSyncing.compareAndSet(false, true)) {
            return Mono.empty();
        }
        LOGGER.info("Starting resetting and synchronizing blocked clients");
        blockedClientIdToBlockEndTimeMillis.clear();
        blockedClientSkipList.clear();
        localTimestampSeconds = UNINITIALIZED_TIMESTAMP;
        localLogId = UNINITIALIZED_LOG_ID;
        Mono<List<Object>> result = redisClient.eval(getBlockedClientsScript, getBlocklistKey());
        return result.doOnNext(elements -> {
            long timestampSeconds = (long) elements.get(0);
            if (timestampSeconds < 0) {
                return;
            }
            int logId = (int) (long) elements.get(1);
            localTimestampSeconds = timestampSeconds;
            localLogId = logId;
            List<BlockedClient<T>> blockedUsers = parseBlockedClients(elements);
            long now = System.currentTimeMillis();
            for (BlockedClient<T> blockedClient : blockedUsers) {
                addBlockedClient(blockedClient, now);
            }
        })
                .doOnSuccess(ignored -> LOGGER.info("Reset and synchronized blocked clients"))
                .doOnError(t -> LOGGER.error("Failed to reset and synchronize blocked clients", t))
                .doFinally(signalType -> {
                    if (checkSyncStatus) {
                        isSyncing.set(false);
                    }
                })
                .then();
    }

    private Mono<Void> fetchLogIdAndLogs(boolean checkSyncStatus) {
        Mono<List<Object>> getBlocklistLogs = redisClient.eval(getBlocklistLogsScript,
                getBlocklistKey(),
                encodeLocalTimestamp(),
                encodeLocalLogId());
        return getBlocklistLogs.flatMap(elements -> {
            long timestampSeconds = (long) elements.get(0);
            if (timestampSeconds < 0) {
                return Mono.empty();
            }
            if (timestampSeconds != localTimestampSeconds
                    && localTimestampSeconds != UNINITIALIZED_TIMESTAMP) {
                return resetAndSyncAllBlockedClients(checkSyncStatus);
            }
            int logId = (int) (long) elements.get(1);
            int diff = logId - localLogId;
            if (diff > maxLogQueueSize || diff < 0) {
                return resetAndSyncAllBlockedClients(checkSyncStatus);
            } else if (diff > 0) {
                List<BlockClientLog<T>> logs = parseClientLogs(elements);
                handleBlockClientLogs(logs, diff);
                localLogId = logId;
            }
            return Mono.empty();
        });
    }

    private List<BlockedClient<T>> parseBlockedClients(List<Object> elements) {
        if (elements.size() <= 2) {
            return Collections.emptyList();
        }
        elements = (List<Object>) elements.get(2);

        int elementCount = elements.size();
        List<BlockedClient<T>> clients = new ArrayList<>(elementCount / 2);
        for (int i = 0; i < elementCount; i += 2) {
            T targetId = decodeTargetId((ByteBuf) elements.get(i));
            long blockEndTimeMillis = decodeBlockEndTime((long) elements.get(i + 1));
            clients.add(new BlockedClient<>(targetId, blockEndTimeMillis));
        }
        return clients;
    }

    private List<BlockClientLog<T>> parseClientLogs(List<Object> elements) {
        if (elements.size() <= 2) {
            return Collections.emptyList();
        }
        elements = (List<Object>) elements.get(2);
        int elementCount = elements.size();
        List<BlockClientLog<T>> logs = new ArrayList<>(elementCount / LOG_ENTRY_ELEMENTS_COUNT);
        for (int i = 0; i < elementCount; i += LOG_ENTRY_ELEMENTS_COUNT) {
            byte[] bytes = ((ByteBuf) elements.get(i)).array();
            boolean isBlockAction = bytes[0] == BLOCK_ACTION_FLAG;
            T targetId = decodeTargetId((ByteBuf) elements.get(i + 1));
            if (isBlockAction) {
                long blockEndTimeMillis = decodeBlockEndTime((long) elements.get(i + 2));
                logs.add(new BlockClientLog<>(targetId, blockEndTimeMillis, true));
            } else {
                logs.add(new BlockClientLog<>(targetId, 0, false));
            }
        }
        return logs;
    }

    private void handleBlockClientLogs(List<BlockClientLog<T>> logs, int expectedSize) {
        int size = logs.size();
        if (expectedSize != size) {
            throw new IllegalArgumentException(
                    "Failed to handle block logs. "
                            + "The size of delta logs must be: "
                            + expectedSize
                            + ", but got: "
                            + size);
        }
        long now = System.currentTimeMillis();
        for (BlockClientLog<T> log : logs) {
            T targetId = log.targetId;
            if (log.isBlockAction) {
                addBlockedClient(new BlockedClient<>(log.targetId, log.blockEndTimeMillis), now);
            } else {
                removeLocalBlockedClient(targetId);
            }
        }
    }

    // Encode/Decode

    private ByteBuf getBlocklistKey() {
        return isIpBlocklist
                ? BLOCKLIST_KEY_FOR_IP
                : BLOCKLIST_KEY_FOR_USER_ID;
    }

    /**
     * TODO: cache
     */
    private ByteBuf encodeLocalTimestamp() {
        return BUFFER_ALLOCATOR.directBuffer(Long.BYTES)
                .writeLong(localTimestampSeconds);
    }

    /**
     * TODO: cache
     */
    private ByteBuf encodeLocalLogId() {
        return BUFFER_ALLOCATOR.directBuffer(Integer.BYTES)
                .writeInt(localLogId);
    }

    private ByteBuf encodeId(T id) {
        return isIpBlocklist
                ? Unpooled.wrappedBuffer(((ByteArrayWrapper) id).getBytes())
                : BUFFER_ALLOCATOR.directBuffer()
                        .writeLong((Long) id);
    }

    private T decodeTargetId(ByteBuf id) {
        T val = isIpBlocklist
                ? (T) new ByteArrayWrapper(id.array())
                : (T) (Long) id.readLong();
        id.release();
        return val;
    }

    private ByteBuf encodeBlockEndTime(long blockEndTimeMillis) {
        return BUFFER_ALLOCATOR.directBuffer(Long.BYTES)
                .writeLong(blockEndTimeMillis);
    }

    private long decodeBlockEndTime(long blockEndTimeMillis) {
        return blockEndTimeMillis;
    }

    // Listener

    private void notifyOnTargetBlockedListener(T id) {
        try {
            onTargetBlocked.accept(id);
        } catch (Exception e) {
            LOGGER.error("Caught an error while notifying the onTargetBlocked listener ("
                    + onTargetBlocked.getClass()
                            .getName()
                    + ") to handle the blocked target: "
                    + id);
        }
    }

    // Data structure

    /**
     * @param timestampSeconds uses long instead int because int cannot represent the dates after
     *                         the year 2038.
     */
    private record LogVersion(
            long timestampSeconds,
            int logId
    ) {
    }

    /**
     * The log doesn't have a field like "recordTime" because we use the item index in Redis to
     * distinguish which logs come first
     */
    private record BlockClientLog<T extends Comparable<T>>(
            T targetId,
            long blockEndTimeMillis,
            boolean isBlockAction
    ) {
    }

}