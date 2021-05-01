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

package im.turms.server.common.service.session;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.ListMultimap;
import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.redis.script.RedisScriptExecutor;
import im.turms.server.common.redis.sharding.ShardingAlgorithm;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.DeviceTypeUtil;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.ReactiveScriptingCommands;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.nio.ByteBuffer;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author James Chen
 */
@Service
public class UserStatusService {

    private static final Byte STATUS_KEY_STATUS = 's';
    private static final RedisScript<Boolean> ADD_ONLINE_USER_SCRIPT =
            RedisScript.of(new ClassPathResource("redis/try_add_online_user_with_ttl.lua"));
    private static final RedisScript<Boolean> UPDATE_USERS_TTL_SCRIPT =
            RedisScript.of(new ClassPathResource("redis/update_users_ttl.lua"));

    private final Node node;

    private final ShardingAlgorithm shardingAlgorithmForSession;
    private final List<ReactiveRedisTemplate<Long, String>> sessionRedisTemplates;
    private final boolean onlyOneSessionRedisTemplate;
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
     * and its value is the user status value.
     * <p>
     * The number (e.g. 1,2,3) represents the online device type,
     * and its value is the node ID that the client connects to.
     */
    private final List<ReactiveHashOperations<Long, Object, Object>> sessionOperationsList;

    /**
     * Note that both online and offline information will be cached
     */
    private final Cache<Long, UserSessionsStatus> userSessionsStatusCache;

    private final Duration operationTimeout;
    private final boolean cacheUserSessionsStatus;

    public UserStatusService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            ShardingAlgorithm shardingAlgorithmForSession,
            List<ReactiveRedisTemplate<Long, String>> sessionRedisTemplates) {
        this.node = node;
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        cacheUserSessionsStatus = turmsProperties.getUserStatus().isCacheUserSessionsStatus();
        operationTimeout = Duration.ofSeconds(10);
        this.shardingAlgorithmForSession = shardingAlgorithmForSession;
        this.sessionRedisTemplates = sessionRedisTemplates;
        onlyOneSessionRedisTemplate = sessionRedisTemplates.size() == 1;
        sessionOperationsList = sessionRedisTemplates
                .stream()
                .map(ReactiveRedisTemplate::opsForHash)
                .collect(Collectors.toList());
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
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return getUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    String nodeId = sessionsStatus.getOnlineDeviceTypeAndNodeIdMap().get(deviceType);
                    return nodeId != null ? Mono.just(nodeId) : Mono.empty();
                });
    }

    public Mono<Map<DeviceType, String>> getDeviceAndNodeIdMapByUserId(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (cacheUserSessionsStatus) {
            UserSessionsStatus sessionsStatus = userSessionsStatusCache.getIfPresent(userId);
            if (sessionsStatus != null) {
                Map<DeviceType, String> deviceTypeAndNodeIdMap = sessionsStatus.getOnlineDeviceTypeAndNodeIdMap();
                return deviceTypeAndNodeIdMap != null && !deviceTypeAndNodeIdMap.isEmpty()
                        ? Mono.just(deviceTypeAndNodeIdMap)
                        : Mono.empty();
            }
        }
        return fetchUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    Map<DeviceType, String> deviceTypeAndNodeIdMap = sessionsStatus.getOnlineDeviceTypeAndNodeIdMap();
                    return deviceTypeAndNodeIdMap != null && !deviceTypeAndNodeIdMap.isEmpty()
                            ? Mono.just(deviceTypeAndNodeIdMap)
                            : Mono.empty();
                });
    }

    public Mono<SetMultimap<String, DeviceType>> getNodeIdAndDeviceMapByUserId(@NotNull Long userId) {
        return getDeviceAndNodeIdMapByUserId(userId)
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
            AssertUtil.notEmpty(userIds, "userIds");
            AssertUtil.notNull(userStatus, "userStatus");
            AssertUtil.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            AssertUtil.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        int size = userIds.size();
        switch (size) {
            case 0:
                return Mono.just(true);
            case 1:
                return updateOnlineUserStatusIfPresent(userIds.iterator().next(), userStatus);
            default:
                List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    monos.add(updateOnlineUserStatusIfPresent(userId, userStatus));
                }
                return Flux.merge(monos).all(value -> value);
        }
    }

    /**
     * @return true if updated
     * @implNote Redis doesn't support a command like "HSETEX" currently
     * (https://github.com/redis/redis/issues/2905)
     */
    public Mono<Boolean> updateOnlineUserStatusIfPresent(@NotNull Long userId, @NotNull UserStatus userStatus) {
        try {
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(userStatus, "userStatus");
            AssertUtil.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            AssertUtil.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        ReactiveHashOperations<Long, Object, Object> sessionOperations = getSessionOperations(userId);
        return sessionOperations
                .get(userId, STATUS_KEY_STATUS)
                .flatMap(o -> sessionOperations.put(userId, STATUS_KEY_STATUS, userStatus).thenReturn(true))
                .defaultIfEmpty(false)
                .timeout(operationTimeout);
    }

    public Mono<Void> updateOnlineUsersTtl(@NotNull Collection<Long> userIds, @NotNull int timeoutSeconds) {
        try {
            AssertUtil.notNull(userIds, "userIds");
            AssertUtil.notNull(timeoutSeconds, "timeoutSeconds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (onlyOneSessionRedisTemplate) {
            ReactiveScriptingCommands commands =
                    sessionRedisTemplates.get(0).getConnectionFactory().getReactiveConnection().scriptingCommands();
            return RedisScriptExecutor.execute(commands,
                    UPDATE_USERS_TTL_SCRIPT,
                    ReturnType.BOOLEAN,
                    serializeUpdateUsersTtlScript(timeoutSeconds, userIds))
                    .then();
        }
        int templateSize = sessionRedisTemplates.size();
        ListMultimap<ReactiveRedisTemplate, Long> map = ArrayListMultimap.create(templateSize, userIds.size() / templateSize);
        for (Long userId : userIds) {
            map.put(getSessionRedisTemplate(userId), userId);
        }
        Map<ReactiveRedisTemplate, Collection<Long>> templateAndUserIds = map.asMap();
        int resultSize = templateAndUserIds.keySet().size();
        List<Mono<Boolean>> list = new ArrayList<>(resultSize);
        for (Map.Entry<ReactiveRedisTemplate, Collection<Long>> entry : templateAndUserIds.entrySet()) {
            ReactiveScriptingCommands commands =
                    entry.getKey().getConnectionFactory().getReactiveConnection().scriptingCommands();
            Mono<Boolean> result = RedisScriptExecutor.execute(commands,
                    UPDATE_USERS_TTL_SCRIPT,
                    ReturnType.BOOLEAN,
                    serializeUpdateUsersTtlScript(timeoutSeconds, userIds));
            list.add(result);
        }
        return Mono.when(list);
    }

    /**
     * @return OFFLINE instead of MonoEmpty for an offline user
     */
    public Mono<UserSessionsStatus> getUserSessionsStatus(@NotNull Long userId) {
        try {
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
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
            AssertUtil.notNull(userId, "userId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return getSessionOperations(userId).entries(userId)
                .timeout(operationTimeout)
                .collect(CollectorUtil.toList())
                .map(entries -> {
                    UserStatus userStatus = null;
                    Map<DeviceType, String> onlineDeviceTypeAndNodeIdMap = null;
                    for (Map.Entry<Object, Object> entry : entries) {
                        if (STATUS_KEY_STATUS.equals(entry.getKey())) {
                            userStatus = (UserStatus) entry.getValue();
                        } else {
                            if (onlineDeviceTypeAndNodeIdMap == null) {
                                onlineDeviceTypeAndNodeIdMap = new EnumMap<>(DeviceType.class);
                            }
                            onlineDeviceTypeAndNodeIdMap.put(
                                    (DeviceType) entry.getKey(),
                                    (String) entry.getValue());
                        }
                    }
                    if (onlineDeviceTypeAndNodeIdMap == null) {
                        userStatus = UserStatus.OFFLINE;
                        onlineDeviceTypeAndNodeIdMap = Collections.emptyMap();
                    } else if (userStatus == null || userStatus == UserStatus.OFFLINE) {
                        userStatus = UserStatus.AVAILABLE;
                    }
                    UserSessionsStatus userSessionsStatus = new UserSessionsStatus(userStatus, onlineDeviceTypeAndNodeIdMap);
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
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notEmpty(deviceTypes, "deviceTypes");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        DeviceType[] deviceTypesArray = deviceTypes.toArray(new DeviceType[0]);
        return getSessionOperations(userId).remove(userId, (Object[]) deviceTypesArray)
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
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            AssertUtil.state(userStatus != UserStatus.UNRECOGNIZED, "The user status must not be UNRECOGNIZED");
            AssertUtil.state(userStatus != UserStatus.OFFLINE, "The user status must not be OFFLINE");
            AssertUtil.notNull(heartbeatSeconds, "heartbeatSeconds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        ReactiveScriptingCommands commands =
                getSessionRedisTemplate(userId).getConnectionFactory().getReactiveConnection().scriptingCommands();
        Object[] args = new Object[userStatus == null ? 4 : 5];
        args[0] = userId;
        args[1] = (byte) deviceType.getNumber();
        args[2] = node.getLocalMemberId();
        args[3] = (short) heartbeatSeconds;
        if (userStatus != null) {
            args[4] = (byte) userStatus.getNumber();
        }
        return RedisScriptExecutor.execute(commands, ADD_ONLINE_USER_SCRIPT, ReturnType.BOOLEAN, args);
    }

    private ReactiveRedisTemplate<Long, String> getSessionRedisTemplate(long userId) {
        return sessionRedisTemplates.get(shardingAlgorithmForSession.doSharding(userId, sessionRedisTemplates.size()));
    }

    private ReactiveHashOperations<Long, Object, Object> getSessionOperations(long userId) {
        return sessionOperationsList.get(shardingAlgorithmForSession.doSharding(userId, sessionRedisTemplates.size()));
    }

    private ByteBuffer[] serializeUpdateUsersTtlScript(int ttl, Collection<Long> userIds) {
        int userCount = userIds.size();
        int size = 1 + userCount;
        ByteBuffer[] buffers = new ByteBuffer[size];
        buffers[0] = RedisScriptExecutor.obj2Buffer((short) ttl);
        int i = 1;
        for (Long userId : userIds) {
            buffers[i] = RedisScriptExecutor.obj2Buffer(userId);
            i++;
        }
        return buffers;
    }

}