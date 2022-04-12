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

package im.turms.server.common.domain.session.service;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.redis.RedisEntryId;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.script.RedisScript;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.protocol.LongKeyGenerator;
import io.netty.buffer.ByteBuf;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
public class UserStatusService {

    private final RedisScript addOnlineUserScript =
            RedisScript.get(new ClassPathResource("redis/session/try_add_online_user_with_ttl.lua"), ScriptOutputType.BOOLEAN);
    private final RedisScript updateUsersTtlScript =
            RedisScript.get(new ClassPathResource("redis/session/update_users_ttl.lua"), ScriptOutputType.BOOLEAN);
    private final RedisScript updateOnlineUserStatusIfPresent =
            RedisScript.get(new ClassPathResource("redis/session/update_online_user_status_if_present.lua"), ScriptOutputType.BOOLEAN);

    /**
     * <pre>
     * +-------------+-------------------------+-------------------------+
     * |   User ID   |          Field          |          Value          |
     * +-------------+-------------------------+-------------------------+
     * |             | s (Key for User Status) |     1 (User Status)     |
     * |             +-------------------------+-------------------------+
     * |             |     1 (Device Type)     |   turms0001 (Node ID)   |
     * |             +-------------------------+-------------------------+
     * |  123456789  |     2 (Device Type)     |   turms0001 (Node ID)   |
     * |             +-------------------------+-------------------------+
     * |             |     3 (Device Type)     |   turms0002 (Node ID)   |
     * |             +-------------------------+-------------------------+
     * |             |           ...           |           ...           |
     * +-------------+-------------------------+-------------------------+
     * </pre>
     * "s" is the fixed hash key of the user status value,
     * and its value is the user status value represented in number.
     * <p>
     * The number (e.g. 1,2,3) represents the online device type,
     * and its value is the node ID that the client connects to.
     */
    private final TurmsRedisClientManager sessionRedisClientManager;

    /**
     * Note that both online and offline information will be cached
     */
    private final Cache<Long, UserSessionsStatus> userSessionsStatusCache;

    private final Duration operationTimeout;
    private final boolean cacheUserSessionsStatus;

    private final ByteBuf localNodeId;

    public UserStatusService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            TurmsRedisClientManager sessionRedisClientManager) {
        localNodeId = ByteBufUtil.getUnreleasableDirectBuffer(node.getLocalMemberId().getBytes(StandardCharsets.US_ASCII));
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        cacheUserSessionsStatus = turmsProperties.getUserStatus().isCacheUserSessionsStatus();
        operationTimeout = Duration.ofSeconds(10);
        this.sessionRedisClientManager = sessionRedisClientManager;
        if (cacheUserSessionsStatus) {
            Caffeine<Object, Object> builder = Caffeine.newBuilder();
            int maxSize = turmsProperties.getUserStatus().getUserSessionsStatusCacheMaxSize();
            int expireAfter = turmsProperties.getUserStatus().getUserSessionsStatusExpireAfter();
            if (maxSize > -1) {
                builder.maximumSize(maxSize);
            }
            if (expireAfter > -1) {
                builder.expireAfterWrite(Duration.ofSeconds(expireAfter));
            }
            userSessionsStatusCache = builder.build();
        } else {
            userSessionsStatusCache = null;
        }
    }

    public Mono<String> getNodeIdByUserIdAndDeviceType(@NotNull Long userId, @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return getUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    String nodeId = sessionsStatus.deviceTypeToNodeId().get(deviceType);
                    return nodeId == null ? Mono.empty() : Mono.just(nodeId);
                });
    }

    /**
     * @return MonoEmpty if the user is offline
     */
    public Mono<Map<DeviceType, String>> getDeviceTypeToNodeIdMapByUserId(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (cacheUserSessionsStatus) {
            UserSessionsStatus sessionsStatus = userSessionsStatusCache.getIfPresent(userId);
            if (sessionsStatus != null) {
                Map<DeviceType, String> deviceTypeToNodeId = sessionsStatus.deviceTypeToNodeId();
                return deviceTypeToNodeId == null || deviceTypeToNodeId.isEmpty()
                        ? Mono.empty()
                        : Mono.just(deviceTypeToNodeId);
            }
        }
        return fetchUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    Map<DeviceType, String> deviceTypeToNodeId = sessionsStatus.deviceTypeToNodeId();
                    return deviceTypeToNodeId == null || deviceTypeToNodeId.isEmpty()
                            ? Mono.empty()
                            : Mono.just(deviceTypeToNodeId);
                });
    }

    public Mono<SetMultimap<String, DeviceType>> getNodeIdToDeviceTypeMapByUserId(@NotNull Long userId) {
        return getDeviceTypeToNodeIdMapByUserId(userId)
                .map(deviceTypeAndNodeIdMap -> {
                    SetMultimap<String, DeviceType> multimap = HashMultimap.create();
                    for (Map.Entry<DeviceType, String> entry : deviceTypeAndNodeIdMap.entrySet()) {
                        multimap.put(entry.getValue(), entry.getKey());
                    }
                    return multimap;
                });
    }

    public Mono<Boolean> updateOnlineUsersStatus(@NotEmpty Set<Long> userIds, @NotNull UserStatus userStatus) {
        try {
            Validator.notEmpty(userIds, "userIds");
            Validator.notNull(userStatus, "userStatus");
            Validator.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            Validator.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        int size = userIds.size();
        return switch (size) {
            case 0 -> PublisherPool.TRUE;
            case 1 -> updateOnlineUserStatusIfPresent(userIds.iterator().next(), userStatus);
            default -> {
                List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    monos.add(updateOnlineUserStatusIfPresent(userId, userStatus));
                }
                yield Flux.merge(monos).all(value -> value);
            }
        };
    }

    /**
     * @return true if updated
     */
    public Mono<Boolean> updateOnlineUserStatusIfPresent(@NotNull Long userId, @NotNull UserStatus userStatus) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(userStatus, "userStatus");
            Validator.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            Validator.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Boolean> result = sessionRedisClientManager
                .eval(userId, updateOnlineUserStatusIfPresent, userId, (byte) userStatus.getNumber());
        return result
                .timeout(operationTimeout);
    }

    public Mono<Void> updateOnlineUsersTtl(@NotNull LongKeyGenerator userIdGenerator, @NotNull int timeoutSeconds) {
        try {
            Validator.notNull(userIdGenerator, "userIdGenerator");
            Validator.notNull(timeoutSeconds, "timeoutSeconds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return sessionRedisClientManager.eval(updateUsersTtlScript, (short) timeoutSeconds, userIdGenerator);
    }

    /**
     * @return OFFLINE instead of MonoEmpty for an offline user
     */
    public Mono<UserSessionsStatus> getUserSessionsStatus(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (cacheUserSessionsStatus) {
            UserSessionsStatus sessionsStatus = userSessionsStatusCache.getIfPresent(userId);
            if (sessionsStatus != null) {
                return Mono.just(sessionsStatus);
            }
        }
        return fetchUserSessionsStatus(userId);
    }

    /**
     * @return OFFLINE instead of MonoEmpty for an offline user
     */
    public Mono<UserSessionsStatus> fetchUserSessionsStatus(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return sessionRedisClientManager.hgetall(userId, userId)
                .timeout(operationTimeout)
                .collect(CollectorUtil.toList())
                .map(entries -> {
                    UserStatus userStatus = null;
                    Map<DeviceType, String> onlineDeviceTypeToNodeId = null;
                    for (Map.Entry<Object, Object> entry : entries) {
                        if (entry.getKey().equals(RedisEntryId.SESSIONS_STATUS)) {
                            userStatus = (UserStatus) entry.getValue();
                        } else {
                            if (onlineDeviceTypeToNodeId == null) {
                                onlineDeviceTypeToNodeId = new FastEnumMap<>(DeviceType.class);
                            }
                            onlineDeviceTypeToNodeId.put(
                                    (DeviceType) entry.getKey(),
                                    (String) entry.getValue());
                        }
                    }
                    if (onlineDeviceTypeToNodeId == null) {
                        userStatus = UserStatus.OFFLINE;
                        onlineDeviceTypeToNodeId = Collections.emptyMap();
                    } else if (userStatus == null || userStatus == UserStatus.OFFLINE) {
                        userStatus = UserStatus.AVAILABLE;
                    }
                    UserSessionsStatus userSessionsStatus = new UserSessionsStatus(userId, userStatus, onlineDeviceTypeToNodeId);
                    if (cacheUserSessionsStatus) {
                        userSessionsStatusCache.put(userId, userSessionsStatus);
                    }
                    return userSessionsStatus;
                });
    }

    /**
     * Note that the method only removes the device information of a user
     * and doesn't remove the online status information to avoid querying and removing one more time.
     * The status will be removed when the TTL has been reached
     * and turms won't respond an incorrect user status to clients
     * because turms will check both the "status" and the online devices.
     *
     * @return ture if at least one device type was present (online)
     * @see UserStatusService#fetchUserSessionsStatus(java.lang.Long)
     */
    public Mono<Boolean> removeStatusByUserIdAndDeviceTypes(@NotNull Long userId, @NotEmpty Set<DeviceType> deviceTypes) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notEmpty(deviceTypes, "deviceTypes");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        DeviceType[] deviceTypesArray = deviceTypes.toArray(new DeviceType[0]);
        return sessionRedisClientManager.hdel(userId, userId, deviceTypesArray)
                .timeout(operationTimeout)
                .map(number -> number > 0);
    }

    /**
     * @return true if the userId:deviceType was absent (offline)
     */
    public Mono<Boolean> addOnlineDeviceIfAbsent(@NotNull Long userId,
                                                 @NotNull @ValidDeviceType DeviceType deviceType,
                                                 @Nullable UserStatus userStatus,
                                                 @NotNull int heartbeatSeconds) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            Validator.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            Validator.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
            Validator.notNull(heartbeatSeconds, "heartbeatSeconds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Object[] args = new Object[userStatus == null ? 4 : 5];
        args[0] = userId;
        args[1] = (byte) deviceType.getNumber();
        args[2] = localNodeId;
        args[3] = (short) heartbeatSeconds;
        if (userStatus != null) {
            args[4] = (byte) userStatus.getNumber();
        }
        return sessionRedisClientManager.eval(userId, addOnlineUserScript, args);
    }

}