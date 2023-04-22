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

package im.turms.server.common.domain.blocklist.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import io.lettuce.core.ScriptOutputType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.blocklist.bo.BlockedClient;
import im.turms.server.common.domain.blocklist.manager.AutoBlockManager;
import im.turms.server.common.domain.blocklist.manager.BlocklistServiceManager;
import im.turms.server.common.domain.session.bo.CloseReason;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.service.ISessionService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.context.JobShutdownOrder;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.exception.ResponseExceptionPublisherPool;
import im.turms.server.common.infra.lang.ByteArrayWrapper;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.net.InetAddressUtil;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.common.security.BlocklistProperties;
import im.turms.server.common.infra.task.CronConst;
import im.turms.server.common.infra.task.TaskManager;
import im.turms.server.common.infra.thread.NamedThreadFactory;
import im.turms.server.common.infra.thread.ThreadNameConst;
import im.turms.server.common.storage.redis.TurmsRedisClient;
import im.turms.server.common.storage.redis.script.RedisScript;

/**
 * @author James Chen
 */
@Service
public class BlocklistService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BlocklistService.class);

    public static int maxLogQueueSize = 100_000;

    private final boolean isIpBlocklistEnabled;
    private final boolean isUserIdBlocklistEnabled;

    private final BlocklistServiceManager<ByteArrayWrapper> ipBlocklistServiceManager;
    private final BlocklistServiceManager<Long> userIdBlocklistServiceManager;

    private final AutoBlockManager<ByteArrayWrapper> ipAutoBlockManagerForCorruptedFrame;
    private final AutoBlockManager<ByteArrayWrapper> ipAutoBlockManagerForCorruptedRequest;
    private final AutoBlockManager<ByteArrayWrapper> ipAutoBlockManagerForFrequentRequest;

    private final AutoBlockManager<Long> userIdAutoBlockManagerForCorruptedFrame;
    private final AutoBlockManager<Long> userIdAutoBlockManagerForCorruptedRequest;
    private final AutoBlockManager<Long> userIdAutoBlockManagerForFrequentRequest;

    private final ScheduledThreadPoolExecutor threadPoolExecutor;

    public BlocklistService(
            Node node,
            TaskManager taskManager,
            TurmsApplicationContext context,
            TurmsRedisClient ipBlocklistRedisClient,
            TurmsRedisClient userIdBlocklistRedisClient,
            TurmsPropertiesManager propertiesManager,
            @Autowired(required = false) ISessionService sessionService) {
        BlocklistProperties blocklistProperties = propertiesManager.getLocalProperties()
                .getSecurity()
                .getBlocklist();
        BlocklistProperties.IpBlocklistTypeProperties ipBlocklistProperties =
                blocklistProperties.getIp();
        BlocklistProperties.UserIdBlocklistTypeProperties userIdBlocklistProperties =
                blocklistProperties.getUserId();

        threadPoolExecutor = new ScheduledThreadPoolExecutor(
                1,
                new NamedThreadFactory(ThreadNameConst.CLIENT_BLOCKLIST_SYNC, true));

        isIpBlocklistEnabled = ipBlocklistProperties.isEnabled();
        isUserIdBlocklistEnabled = userIdBlocklistProperties.isEnabled();
        boolean isGateway = node.getNodeType() == NodeType.GATEWAY;

        Map<String, Object> params = Map.of("MAX_LOG_QUEUE_SIZE", maxLogQueueSize);

        RedisScript blockClientsScript =
                RedisScript.get(new ClassPathResource("redis/blocklist/block_clients.lua"),
                        ScriptOutputType.MULTI,
                        params);
        RedisScript unblockClientsScript =
                RedisScript.get(new ClassPathResource("redis/blocklist/unblock_clients.lua"),
                        ScriptOutputType.MULTI,
                        params);
        RedisScript getBlockedClientsScript =
                RedisScript.get(new ClassPathResource("redis/blocklist/get_blocked_clients.lua"),
                        ScriptOutputType.MULTI);
        RedisScript evictAllBlockedClients = RedisScript.get(
                new ClassPathResource("redis/blocklist/evict_all_blocked_clients.lua"),
                ScriptOutputType.BOOLEAN);
        RedisScript evictExpiredBlockedClients = RedisScript.get(
                new ClassPathResource("redis/blocklist/evict_expired_blocked_clients.lua"),
                ScriptOutputType.BOOLEAN);
        RedisScript getBlocklistLogsScript =
                RedisScript.get(new ClassPathResource("redis/blocklist/get_blocklist_logs.lua"),
                        ScriptOutputType.MULTI,
                        params);

        if (isIpBlocklistEnabled) {
            BlocklistProperties.IpAutoBlockProperties autoBlock =
                    ipBlocklistProperties.getAutoBlock();
            ipAutoBlockManagerForCorruptedRequest =
                    new AutoBlockManager<>(autoBlock.getCorruptedRequest(), this::blockIp);
            if (isGateway) {
                ipAutoBlockManagerForCorruptedFrame =
                        new AutoBlockManager<>(autoBlock.getCorruptedFrame(), this::blockIp);
                ipAutoBlockManagerForFrequentRequest =
                        new AutoBlockManager<>(autoBlock.getFrequentRequest(), this::blockIp);
            } else {
                ipAutoBlockManagerForCorruptedFrame = null;
                ipAutoBlockManagerForFrequentRequest = null;
            }
            ipBlocklistServiceManager = new BlocklistServiceManager<>(
                    true,
                    maxLogQueueSize,
                    ipBlocklistProperties.getSyncBlocklistIntervalMillis(),
                    node,
                    ipBlocklistRedisClient,
                    threadPoolExecutor,
                    blockClientsScript,
                    unblockClientsScript,
                    getBlockedClientsScript,
                    evictAllBlockedClients,
                    evictExpiredBlockedClients,
                    getBlocklistLogsScript,
                    ip -> {
                        if (sessionService != null) {
                            sessionService
                                    .closeLocalSessions(ip.getBytes(),
                                            CloseReason.get(SessionCloseStatus.USER_IS_BLOCKED))
                                    .subscribe(null,
                                            throwable -> LOGGER.error(
                                                    "Caught an error while closing local user sessions with the IP: {}",
                                                    InetAddressUtil.ipBytesToString(ip.getBytes()),
                                                    throwable));
                        }
                        ipAutoBlockManagerForCorruptedRequest.unblockClient(ip);
                        if (isGateway) {
                            ipAutoBlockManagerForCorruptedFrame.unblockClient(ip);
                            ipAutoBlockManagerForFrequentRequest.unblockClient(ip);
                        }
                    });
        } else {
            ipBlocklistServiceManager = null;
            ipAutoBlockManagerForCorruptedRequest = null;
            ipAutoBlockManagerForCorruptedFrame = null;
            ipAutoBlockManagerForFrequentRequest = null;
        }
        if (isUserIdBlocklistEnabled) {
            BlocklistProperties.UserIdAutoBlockProperties autoBlock =
                    userIdBlocklistProperties.getAutoBlock();
            userIdAutoBlockManagerForCorruptedRequest =
                    new AutoBlockManager<>(autoBlock.getCorruptedRequest(), this::blockUserId);
            if (isGateway) {
                userIdAutoBlockManagerForCorruptedFrame =
                        new AutoBlockManager<>(autoBlock.getCorruptedFrame(), this::blockUserId);
                userIdAutoBlockManagerForFrequentRequest =
                        new AutoBlockManager<>(autoBlock.getFrequentRequest(), this::blockUserId);
            } else {
                userIdAutoBlockManagerForCorruptedFrame = null;
                userIdAutoBlockManagerForFrequentRequest = null;
            }
            userIdBlocklistServiceManager = new BlocklistServiceManager<>(
                    false,
                    maxLogQueueSize,
                    userIdBlocklistProperties.getSyncBlocklistIntervalMillis(),
                    node,
                    userIdBlocklistRedisClient,
                    threadPoolExecutor,
                    blockClientsScript,
                    unblockClientsScript,
                    getBlockedClientsScript,
                    evictAllBlockedClients,
                    evictExpiredBlockedClients,
                    getBlocklistLogsScript,
                    userId -> {
                        if (sessionService != null) {
                            sessionService
                                    .closeLocalSession(userId,
                                            CloseReason.get(SessionCloseStatus.USER_IS_BLOCKED))
                                    .subscribe(null,
                                            throwable -> LOGGER.error(
                                                    "Caught an error while closing the user sessions with the user ID: {}",
                                                    userId,
                                                    throwable));
                        }
                        userIdAutoBlockManagerForCorruptedRequest.unblockClient(userId);
                        if (isGateway) {
                            userIdAutoBlockManagerForCorruptedFrame.unblockClient(userId);
                            userIdAutoBlockManagerForFrequentRequest.unblockClient(userId);
                        }
                    });
        } else {
            userIdBlocklistServiceManager = null;
            userIdAutoBlockManagerForCorruptedFrame = null;
            userIdAutoBlockManagerForCorruptedRequest = null;
            userIdAutoBlockManagerForFrequentRequest = null;
        }
        taskManager.reschedule("expiredBlockedClientCleanup",
                CronConst.EXPIRED_BLOCKED_CLIENT_CLEANUP_CRON,
                () -> {
                    if (isIpBlocklistEnabled) {
                        ipAutoBlockManagerForCorruptedRequest.evictExpiredBlockedClients();
                        if (isGateway) {
                            ipAutoBlockManagerForCorruptedFrame.evictExpiredBlockedClients();
                            ipAutoBlockManagerForFrequentRequest.evictExpiredBlockedClients();
                        }
                    }
                    if (isUserIdBlocklistEnabled) {
                        userIdAutoBlockManagerForCorruptedRequest.evictExpiredBlockedClients();
                        if (isGateway) {
                            userIdAutoBlockManagerForFrequentRequest.evictExpiredBlockedClients();
                        }
                    }
                });
        context.addShutdownHook(JobShutdownOrder.CLOSE_BLOCKLIST, this::destroy);
    }

    private Mono<Void> destroy(long timeoutMillis) {
        threadPoolExecutor.shutdown();
        try {
            threadPoolExecutor.awaitTermination(timeoutMillis, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            // ignore
        }
        return Mono.empty();
    }

    // Block

    public void blockIp(ByteArrayWrapper address, long blockDurationSeconds) {
        if (!isIpBlocklistEnabled) {
            throw ResponseException.get(ResponseStatusCode.IP_BLOCKLIST_IS_DISABLED);
        }
        ipBlocklistServiceManager.blockClients(Set.of(address), blockDurationSeconds)
                .subscribe(null, t -> LOGGER.error("Caught an error while blocking clients", t));
    }

    public Mono<Void> blockIpStrings(Set<String> ips, long blockDurationSeconds) {
        if (!isIpBlocklistEnabled) {
            return ResponseExceptionPublisherPool.ipBlocklistIsDisabled();
        }
        return ipBlocklistServiceManager.blockClients(ipsToBytes(ips), blockDurationSeconds);
    }

    public Mono<Void> blockIps(Set<ByteArrayWrapper> ips, long blockDurationSeconds) {
        if (!isIpBlocklistEnabled) {
            return ResponseExceptionPublisherPool.ipBlocklistIsDisabled();
        }
        return ipBlocklistServiceManager.blockClients(ips, blockDurationSeconds);
    }

    public void blockUserId(Long userId, long blockDurationSeconds) {
        if (!isUserIdBlocklistEnabled) {
            throw ResponseException.get(ResponseStatusCode.USER_ID_BLOCKLIST_IS_DISABLED);
        }
        userIdBlocklistServiceManager.blockClients(Set.of(userId), blockDurationSeconds)
                .subscribe(null,
                        t -> LOGGER.error(
                                "Caught an error while deleting expired group invitations",
                                t));
    }

    public Mono<Void> blockUserIds(Set<Long> userIds, long blockDurationSeconds) {
        if (!isUserIdBlocklistEnabled) {
            return ResponseExceptionPublisherPool.userIdBlocklistIsDisabled();
        }
        return userIdBlocklistServiceManager.blockClients(userIds, blockDurationSeconds);
    }

    public void tryBlockIpForCorruptedFrame(ByteArrayWrapper ip) {
        if (isIpBlocklistEnabled) {
            ipAutoBlockManagerForCorruptedFrame.tryBlockClient(ip);
        }
    }

    public void tryBlockIpForCorruptedRequest(ByteArrayWrapper ip) {
        if (isIpBlocklistEnabled) {
            ipAutoBlockManagerForCorruptedRequest.tryBlockClient(ip);
        }
    }

    public void tryBlockIpForFrequentRequest(ByteArrayWrapper ip) {
        if (isIpBlocklistEnabled) {
            ipAutoBlockManagerForFrequentRequest.tryBlockClient(ip);
        }
    }

    public void tryBlockUserIdForCorruptedFrame(Long userId) {
        if (isUserIdBlocklistEnabled) {
            userIdAutoBlockManagerForCorruptedFrame.tryBlockClient(userId);
        }
    }

    public void tryBlockUserIdForCorruptedRequest(Long userId) {
        if (isUserIdBlocklistEnabled) {
            userIdAutoBlockManagerForCorruptedRequest.tryBlockClient(userId);
        }
    }

    public void tryBlockUserIdForFrequentRequest(Long userId) {
        if (isUserIdBlocklistEnabled) {
            userIdAutoBlockManagerForFrequentRequest.tryBlockClient(userId);
        }
    }

    // Unblock

    public Mono<Void> unblockIpStrings(Set<String> ips) {
        if (!isIpBlocklistEnabled) {
            return Mono.empty();
        }
        return ipBlocklistServiceManager.unblockTargets(ipsToBytes(ips));
    }

    public Mono<Void> unblockIps(Set<ByteArrayWrapper> ips) {
        if (!isIpBlocklistEnabled) {
            return Mono.empty();
        }
        return ipBlocklistServiceManager.unblockTargets(ips);
    }

    public Mono<Void> unblockUserIds(Set<Long> userIds) {
        if (!isUserIdBlocklistEnabled) {
            return Mono.empty();
        }
        return userIdBlocklistServiceManager.unblockTargets(userIds);
    }

    public Mono<Void> unblockAllIps() {
        if (!isIpBlocklistEnabled) {
            return Mono.empty();
        }
        return ipBlocklistServiceManager.unblockAll();
    }

    public Mono<Void> unblockAllUserIds() {
        if (!isUserIdBlocklistEnabled) {
            return Mono.empty();
        }
        return userIdBlocklistServiceManager.unblockAll();
    }

    // Query

    public int countBlockIps() {
        return ipBlocklistServiceManager.count();
    }

    public int countBlockUsers() {
        return userIdBlocklistServiceManager.count();
    }

    public List<BlockedClient<ByteArrayWrapper>> getBlockedIpStrings(Set<String> ips) {
        List<BlockedClient<ByteArrayWrapper>> ipList = new ChunkedArrayList<>();
        for (String ip : ips) {
            ByteArrayWrapper address = new ByteArrayWrapper(InetAddressUtil.ipStringToBytes(ip));
            BlockedClient<ByteArrayWrapper> blockedClient =
                    ipBlocklistServiceManager.getBlockedClient(address);
            if (blockedClient != null) {
                ipList.add(blockedClient);
            }
        }
        return ipList;
    }

    public List<BlockedClient<ByteArrayWrapper>> getBlockedIps(Set<ByteArrayWrapper> ips) {
        if (!isIpBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<BlockedClient<ByteArrayWrapper>> result = new ChunkedArrayList<>();
        for (ByteArrayWrapper ip : ips) {
            BlockedClient<ByteArrayWrapper> blockedClient =
                    ipBlocklistServiceManager.getBlockedClient(ip);
            if (blockedClient != null) {
                result.add(blockedClient);
            }
        }
        return result;
    }

    public List<BlockedClient<ByteArrayWrapper>> getBlockedIps(int page, int size) {
        if (!isIpBlocklistEnabled) {
            return Collections.emptyList();
        }
        return ipBlocklistServiceManager.getBlockedClients(page, size);
    }

    public List<BlockedClient<Long>> getBlockedUsers(Set<Long> userIds) {
        if (!isUserIdBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<BlockedClient<Long>> result = new ChunkedArrayList<>();
        for (Long userId : userIds) {
            BlockedClient<Long> blockedClient =
                    userIdBlocklistServiceManager.getBlockedClient(userId);
            if (blockedClient != null) {
                result.add(blockedClient);
            }
        }
        return result;
    }

    public List<BlockedClient<Long>> getBlockedUsers(int page, int size) {
        if (!isUserIdBlocklistEnabled) {
            return Collections.emptyList();
        }
        return userIdBlocklistServiceManager.getBlockedClients(page, size);
    }

    public boolean isIpBlocked(String ip) {
        if (!isIpBlocklistEnabled) {
            return false;
        }
        return ipBlocklistServiceManager
                .isTargetBlocked(new ByteArrayWrapper(InetAddressUtil.ipStringToBytes(ip)));
    }

    public boolean isIpBlocked(byte[] ip) {
        if (!isIpBlocklistEnabled) {
            return false;
        }
        return ipBlocklistServiceManager.isTargetBlocked(new ByteArrayWrapper(ip));
    }

    public boolean isUserIdBlocked(Long userId) {
        if (!isUserIdBlocklistEnabled) {
            return false;
        }
        return userIdBlocklistServiceManager.isTargetBlocked(userId);
    }

    // Internals

    private Set<ByteArrayWrapper> ipsToBytes(Set<String> ips) {
        Set<ByteArrayWrapper> ipList = CollectionUtil.newSetWithExpectedSize(ips.size());
        for (String ip : ips) {
            ByteArrayWrapper address = new ByteArrayWrapper(InetAddressUtil.ipStringToBytes(ip));
            ipList.add(address);
        }
        return ipList;
    }

}