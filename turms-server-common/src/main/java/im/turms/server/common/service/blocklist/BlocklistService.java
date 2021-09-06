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

package im.turms.server.common.service.blocklist;

import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.security.BlocklistProperties;
import im.turms.server.common.redis.TurmsRedisClient;
import im.turms.server.common.redis.script.RedisScript;
import im.turms.server.common.rpc.service.ISessionService;
import im.turms.server.common.util.CollectionUtil;
import im.turms.server.common.util.InetAddressUtil;
import io.lettuce.core.ScriptOutputType;
import io.netty.util.concurrent.DefaultThreadFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author James Chen
 */
@Service
public class BlocklistService {

    public static int maxLogQueueSize = 100_000;

    private final boolean isIpBlocklistEnabled;
    private final boolean isUserIdBlocklistEnabled;

    private final BlocklistServiceManager<byte[]> ipBlocklistServiceManager;
    private final BlocklistServiceManager<Long> userIdBlocklistServiceManager;

    private final ScheduledThreadPoolExecutor threadPoolExecutor;

    public BlocklistService(Node node,
                            TurmsRedisClient ipBlocklistRedisClient,
                            TurmsRedisClient userIdBlocklistRedisClient,
                            TurmsPropertiesManager turmsPropertiesManager,
                            @Autowired(required = false) ISessionService sessionService) {
        BlocklistProperties blocklistProperties = turmsPropertiesManager.getLocalProperties().getSecurity().getBlocklist();
        BlocklistProperties.BlocklistTypeProperties ipBlocklistProperties = blocklistProperties.getIp();
        BlocklistProperties.BlocklistTypeProperties userIdBlocklistProperties = blocklistProperties.getUserId();

        threadPoolExecutor =
                new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("turms-client-blocklist-sync", true));

        isIpBlocklistEnabled = ipBlocklistProperties.isEnabled();
        isUserIdBlocklistEnabled = userIdBlocklistProperties.isEnabled();

        Map<String, Object> params = Map.of("MAX_LOG_QUEUE_SIZE", maxLogQueueSize);

        RedisScript blockClientsScript = RedisScript
                .get(new ClassPathResource("redis/blocklist/block_clients.lua"), ScriptOutputType.MULTI, params);
        RedisScript unblockClientsScript = RedisScript
                .get(new ClassPathResource("redis/blocklist/unblock_clients.lua"), ScriptOutputType.MULTI, params);
        RedisScript getBlockedClientsScript = RedisScript
                .get(new ClassPathResource("redis/blocklist/get_blocked_clients.lua"), ScriptOutputType.MULTI);
        RedisScript evictAllBlockedClients = RedisScript
                .get(new ClassPathResource("redis/blocklist/evict_all_blocked_clients.lua"), ScriptOutputType.BOOLEAN);
        RedisScript evictExpiredBlockedClients = RedisScript
                .get(new ClassPathResource("redis/blocklist/evict_expired_blocked_clients.lua"), ScriptOutputType.BOOLEAN);
        RedisScript getBlocklistLogsScript = RedisScript
                .get(new ClassPathResource("redis/blocklist/get_blocklist_logs.lua"), ScriptOutputType.MULTI, params);

        if (isIpBlocklistEnabled) {
            ipBlocklistServiceManager = new BlocklistServiceManager<>(true, maxLogQueueSize,
                    ipBlocklistProperties.getSyncBlocklistIntervalMillis(), node,
                    ipBlocklistRedisClient, threadPoolExecutor,
                    blockClientsScript, unblockClientsScript, getBlockedClientsScript,
                    evictAllBlockedClients, evictExpiredBlockedClients, getBlocklistLogsScript,
                    ip -> {
                        if (sessionService != null) {
                            sessionService.setLocalSessionsOfflineByIp(ip, CloseReason.get(SessionCloseStatus.USER_IS_BLOCKED));
                        }
                    });
        } else {
            ipBlocklistServiceManager = null;
        }
        if (isUserIdBlocklistEnabled) {
            userIdBlocklistServiceManager = new BlocklistServiceManager<>(false, maxLogQueueSize,
                    userIdBlocklistProperties.getSyncBlocklistIntervalMillis(), node,
                    userIdBlocklistRedisClient, threadPoolExecutor,
                    blockClientsScript, unblockClientsScript, getBlockedClientsScript,
                    evictAllBlockedClients, evictExpiredBlockedClients, getBlocklistLogsScript,
                    userId -> {
                        if (sessionService != null) {
                            sessionService.setLocalUserOffline(userId, CloseReason.get(SessionCloseStatus.USER_IS_BLOCKED));
                        }
                    });
        } else {
            userIdBlocklistServiceManager = null;
        }
    }

    @PreDestroy
    public void destroy() {
        threadPoolExecutor.shutdown();
    }

    // Block

    public Mono<Void> blockIpStrings(Set<String> ips, int blockMinutes) {
        if (!isIpBlocklistEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.IP_BLOCKLIST_IS_DISABLED));
        }
        return ipBlocklistServiceManager.blockClients(ipsToBytes(ips), blockMinutes);
    }

    public Mono<Void> blockIps(Set<byte[]> ips, int blockMinutes) {
        if (!isIpBlocklistEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.IP_BLOCKLIST_IS_DISABLED));
        }
        return ipBlocklistServiceManager.blockClients(ips, blockMinutes);
    }

    public Mono<Void> blockUserIds(Set<Long> userIds, int blockMinutes) {
        if (!isUserIdBlocklistEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_ID_BLOCKLIST_IS_DISABLED));
        }
        return userIdBlocklistServiceManager.blockClients(userIds, blockMinutes);
    }

    // Unblock

    public Mono<Void> unblockIpStrings(Set<String> ips) {
        if (!isIpBlocklistEnabled) {
            return Mono.empty();
        }
        return ipBlocklistServiceManager.unblockTargets(ipsToBytes(ips));
    }

    public Mono<Void> unblockIps(Set<byte[]> ips) {
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

    public List<String> getBlockedIpStrings(Set<String> ips) {
        List<String> ipList = new ArrayList<>(ips.size());
        for (String ip : ips) {
            byte[] address = InetAddressUtil.ipStringToBytes(ip);
            if (ipBlocklistServiceManager.isTargetBlocked(address)) {
                ipList.add(ip);
            }
        }
        return ipList;
    }

    public List<byte[]> getBlockedIps(Set<byte[]> ips) {
        if (!isIpBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<byte[]> result = new LinkedList<>();
        for (byte[] ip : ips) {
            if (ipBlocklistServiceManager.isTargetBlocked(ip)) {
                result.add(ip);
            }
        }
        return result;
    }

    public List<String> getBlockedIps(int page, int size) {
        if (!isIpBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<byte[]> blockedClients = ipBlocklistServiceManager.getBlockedClients(page, size);
        if (blockedClients.isEmpty()) {
            return Collections.emptyList();
        }
        List<String> ips = new ArrayList<>(blockedClients.size());
        for (byte[] ip : blockedClients) {
            ips.add(InetAddressUtil.ipBytesToString(ip));
        }
        return ips;
    }

    public List<Long> getBlockedUserIds(Set<Long> userIds) {
        if (!isUserIdBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<Long> result = new LinkedList<>();
        for (Long userId : userIds) {
            if (userIdBlocklistServiceManager.isTargetBlocked(userId)) {
                result.add(userId);
            }
        }
        return result;
    }

    public List<Long> getBlockedUserIds(int page, int size) {
        if (!isUserIdBlocklistEnabled) {
            return Collections.emptyList();
        }
        return userIdBlocklistServiceManager.getBlockedClients(page, size);
    }

    public boolean isIpBlocked(String ip) {
        if (!isIpBlocklistEnabled) {
            return false;
        }
        return ipBlocklistServiceManager.isTargetBlocked(InetAddressUtil.ipStringToBytes(ip));
    }

    public boolean isIpBlocked(byte[] ip) {
        if (!isIpBlocklistEnabled) {
            return false;
        }
        return ipBlocklistServiceManager.isTargetBlocked(ip);
    }

    public boolean isUserIdBlocked(Long userId) {
        if (!isUserIdBlocklistEnabled) {
            return false;
        }
        return userIdBlocklistServiceManager.isTargetBlocked(userId);
    }

    // Internals

    private Set<byte[]> ipsToBytes(Set<String> ips) {
        Set<byte[]> ipList = CollectionUtil.newSetWithExpectedSize(ips.size());
        for (String ip : ips) {
            byte[] address = InetAddressUtil.ipStringToBytes(ip);
            ipList.add(address);
        }
        return ipList;
    }

}