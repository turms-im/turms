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
import im.turms.server.common.bo.blocklist.BlockedClient;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.constant.CronConstant;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.dto.CloseReason;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.lang.ByteWrapper;
import im.turms.server.common.manager.TrivialTaskManager;
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

    private final BlocklistServiceManager<ByteWrapper> ipBlocklistServiceManager;
    private final BlocklistServiceManager<Long> userIdBlocklistServiceManager;

    private final AutoBlockManager<ByteWrapper> ipAutoBlockManagerForCorruptedFrame;
    private final AutoBlockManager<ByteWrapper> ipAutoBlockManagerForCorruptedRequest;
    private final AutoBlockManager<ByteWrapper> ipAutoBlockManagerForFrequentRequest;

    private final AutoBlockManager<Long> userIdAutoBlockManagerForCorruptedRequest;
    private final AutoBlockManager<Long> userIdAutoBlockManagerForFrequentRequest;

    private final ScheduledThreadPoolExecutor threadPoolExecutor;

    public BlocklistService(Node node,
                            TrivialTaskManager trivialTaskManager,
                            TurmsRedisClient ipBlocklistRedisClient,
                            TurmsRedisClient userIdBlocklistRedisClient,
                            TurmsPropertiesManager turmsPropertiesManager,
                            @Autowired(required = false) ISessionService sessionService) {
        BlocklistProperties blocklistProperties = turmsPropertiesManager.getLocalProperties().getSecurity().getBlocklist();
        BlocklistProperties.IpBlocklistTypeProperties ipBlocklistProperties = blocklistProperties.getIp();
        BlocklistProperties.UserIdBlocklistTypeProperties userIdBlocklistProperties = blocklistProperties.getUserId();

        threadPoolExecutor =
                new ScheduledThreadPoolExecutor(1, new DefaultThreadFactory("turms-client-blocklist-sync", true));

        isIpBlocklistEnabled = ipBlocklistProperties.isEnabled();
        isUserIdBlocklistEnabled = userIdBlocklistProperties.isEnabled();
        boolean isGateway = node.getNodeType() == NodeType.GATEWAY;

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
            BlocklistProperties.IpAutoBlockProperties autoBlock = ipBlocklistProperties.getAutoBlock();
            ipAutoBlockManagerForCorruptedRequest = new AutoBlockManager<>(autoBlock.getCorruptedRequest(), this::blockIp);
            if (isGateway) {
                ipAutoBlockManagerForCorruptedFrame = new AutoBlockManager<>(autoBlock.getCorruptedFrame(), this::blockIp);
                ipAutoBlockManagerForFrequentRequest = new AutoBlockManager<>(autoBlock.getFrequentRequest(), this::blockIp);
            } else {
                ipAutoBlockManagerForCorruptedFrame = null;
                ipAutoBlockManagerForFrequentRequest = null;
            }
            ipBlocklistServiceManager = new BlocklistServiceManager<>(true, maxLogQueueSize,
                    ipBlocklistProperties.getSyncBlocklistIntervalMillis(), node,
                    ipBlocklistRedisClient, threadPoolExecutor,
                    blockClientsScript, unblockClientsScript, getBlockedClientsScript,
                    evictAllBlockedClients, evictExpiredBlockedClients, getBlocklistLogsScript,
                    ip -> {
                        if (sessionService != null) {
                            sessionService.setLocalSessionsOfflineByIp(ip.bytes(), CloseReason.get(SessionCloseStatus.USER_IS_BLOCKED));
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
            BlocklistProperties.UserIdAutoBlockProperties autoBlock = userIdBlocklistProperties.getAutoBlock();
            userIdAutoBlockManagerForCorruptedRequest = new AutoBlockManager<>(autoBlock.getCorruptedRequest(), this::blockUserId);
            if (isGateway) {
                userIdAutoBlockManagerForFrequentRequest = new AutoBlockManager<>(autoBlock.getFrequentRequest(), this::blockUserId);
            } else {
                userIdAutoBlockManagerForFrequentRequest = null;
            }
            userIdBlocklistServiceManager = new BlocklistServiceManager<>(false, maxLogQueueSize,
                    userIdBlocklistProperties.getSyncBlocklistIntervalMillis(), node,
                    userIdBlocklistRedisClient, threadPoolExecutor,
                    blockClientsScript, unblockClientsScript, getBlockedClientsScript,
                    evictAllBlockedClients, evictExpiredBlockedClients, getBlocklistLogsScript,
                    userId -> {
                        if (sessionService != null) {
                            sessionService.setLocalUserOffline(userId, CloseReason.get(SessionCloseStatus.USER_IS_BLOCKED));
                        }
                        userIdAutoBlockManagerForCorruptedRequest.unblockClient(userId);
                        if (isGateway) {
                            userIdAutoBlockManagerForFrequentRequest.unblockClient(userId);
                        }
                    });
        } else {
            userIdBlocklistServiceManager = null;
            userIdAutoBlockManagerForCorruptedRequest = null;
            userIdAutoBlockManagerForFrequentRequest = null;
        }
        trivialTaskManager.reschedule("expiredBlockedClientCleanup", CronConstant.EXPIRED_BLOCKED_CLIENT_CLEANUP_CRON, () -> {
            if (isIpBlocklistEnabled) {
                ipAutoBlockManagerForCorruptedRequest.evictExpiredBlockedClient();
                if (isGateway) {
                    ipAutoBlockManagerForCorruptedFrame.evictExpiredBlockedClient();
                    ipAutoBlockManagerForFrequentRequest.evictExpiredBlockedClient();
                }
            }
            if (isUserIdBlocklistEnabled) {
                userIdAutoBlockManagerForCorruptedRequest.evictExpiredBlockedClient();
                if (isGateway) {
                    userIdAutoBlockManagerForFrequentRequest.evictExpiredBlockedClient();
                }
            }
        });
    }

    @PreDestroy
    public void destroy() {
        threadPoolExecutor.shutdown();
    }

    // Block

    public void blockIp(ByteWrapper address, int blockMinutes) {
        if (!isIpBlocklistEnabled) {
            throw TurmsBusinessException.get(TurmsStatusCode.IP_BLOCKLIST_IS_DISABLED);
        }
        ipBlocklistServiceManager.blockClients(Set.of(address), blockMinutes)
                .subscribe();
    }

    public Mono<Void> blockIpStrings(Set<String> ips, int blockMinutes) {
        if (!isIpBlocklistEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.IP_BLOCKLIST_IS_DISABLED));
        }
        return ipBlocklistServiceManager.blockClients(ipsToBytes(ips), blockMinutes);
    }

    public Mono<Void> blockIps(Set<ByteWrapper> ips, int blockMinutes) {
        if (!isIpBlocklistEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.IP_BLOCKLIST_IS_DISABLED));
        }
        return ipBlocklistServiceManager.blockClients(ips, blockMinutes);
    }

    public void blockUserId(Long userId, int blockMinutes) {
        if (!isUserIdBlocklistEnabled) {
            throw TurmsBusinessException.get(TurmsStatusCode.USER_ID_BLOCKLIST_IS_DISABLED);
        }
        userIdBlocklistServiceManager.blockClients(Set.of(userId), blockMinutes)
                .subscribe();
    }

    public Mono<Void> blockUserIds(Set<Long> userIds, int blockMinutes) {
        if (!isUserIdBlocklistEnabled) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.USER_ID_BLOCKLIST_IS_DISABLED));
        }
        return userIdBlocklistServiceManager.blockClients(userIds, blockMinutes);
    }

    public void tryBlockIpForCorruptedFrame(byte[] ip) {
        if (isIpBlocklistEnabled) {
            ipAutoBlockManagerForCorruptedFrame.tryBlockClient(new ByteWrapper(ip));
        }
    }

    public void tryBlockIpForCorruptedRequest(byte[] ip) {
        if (isIpBlocklistEnabled) {
            ipAutoBlockManagerForCorruptedRequest.tryBlockClient(new ByteWrapper(ip));
        }
    }

    public void tryBlockIpForFrequentRequest(byte[] ip) {
        if (isIpBlocklistEnabled) {
            ipAutoBlockManagerForFrequentRequest.tryBlockClient(new ByteWrapper(ip));
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

    public Mono<Void> unblockIps(Set<ByteWrapper> ips) {
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

    public List<BlockedClient> getBlockedIpStrings(Set<String> ips) {
        List<BlockedClient> ipList = new LinkedList<>();
        for (String ip : ips) {
            ByteWrapper address = new ByteWrapper(InetAddressUtil.ipStringToBytes(ip));
            BlockedClient blockedClient = ipBlocklistServiceManager.getBlockedClient(address);
            if (blockedClient != null) {
                ipList.add(blockedClient);
            }
        }
        return ipList;
    }

    public List<BlockedClient> getBlockedIps(Set<ByteWrapper> ips) {
        if (!isIpBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<BlockedClient> result = new LinkedList<>();
        for (ByteWrapper ip : ips) {
            BlockedClient blockedClient = ipBlocklistServiceManager.getBlockedClient(ip);
            if (blockedClient != null) {
                result.add(blockedClient);
            }
        }
        return result;
    }

    public List<BlockedClient> getBlockedIps(int page, int size) {
        if (!isIpBlocklistEnabled) {
            return Collections.emptyList();
        }
        return ipBlocklistServiceManager.getBlockedClients(page, size);
    }

    public List<BlockedClient> getBlockedUsers(Set<Long> userIds) {
        if (!isUserIdBlocklistEnabled) {
            return Collections.emptyList();
        }
        List<BlockedClient> result = new LinkedList<>();
        for (Long userId : userIds) {
            BlockedClient blockedClient = userIdBlocklistServiceManager.getBlockedClient(userId);
            if (blockedClient != null) {
                result.add(blockedClient);
            }
        }
        return result;
    }

    public List<BlockedClient> getBlockedUsers(int page, int size) {
        if (!isUserIdBlocklistEnabled) {
            return Collections.emptyList();
        }
        return userIdBlocklistServiceManager.getBlockedClients(page, size);
    }

    public boolean isIpBlocked(String ip) {
        if (!isIpBlocklistEnabled) {
            return false;
        }
        return ipBlocklistServiceManager.isTargetBlocked(new ByteWrapper(InetAddressUtil.ipStringToBytes(ip)));
    }

    public boolean isIpBlocked(byte[] ip) {
        if (!isIpBlocklistEnabled) {
            return false;
        }
        return ipBlocklistServiceManager.isTargetBlocked(new ByteWrapper(ip));
    }

    public boolean isUserIdBlocked(Long userId) {
        if (!isUserIdBlocklistEnabled) {
            return false;
        }
        return userIdBlocklistServiceManager.isTargetBlocked(userId);
    }

    // Internals

    private Set<ByteWrapper> ipsToBytes(Set<String> ips) {
        Set<ByteWrapper> ipList = CollectionUtil.newSetWithExpectedSize(ips.size());
        for (String ip : ips) {
            ByteWrapper address = new ByteWrapper(InetAddressUtil.ipStringToBytes(ip));
            ipList.add(address);
        }
        return ipList;
    }

}