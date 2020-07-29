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
import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.common.constant.UserStatus;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.server.common.bo.session.UserSessionsStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import org.springframework.data.redis.core.ReactiveHashOperations;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.Duration;
import java.util.*;

/**
 * @author James Chen
 */
@Service
@Validated
public class UserStatusService {

    private static final Byte STATUS_KEY_STATUS = 's';

    private final Node node;
    private final ReactiveRedisTemplate<Long, String> redisTemplate;
    /**
     * fixed hash key for status field "s" + user ID -> fixed user status field "s" -> user status.
     * fixed hash key for status field "s" + user ID -> device type -> node ID.
     * e.g. "s1000" -> "s" -> "1"
     * e.g. "s1000" -> "1" -> "turms-east-0001"
     * The representation of the enum value is number instead of string,
     * and we save them as string instead of number so that the key and value can be saved in one byte.
     */
    private final ReactiveHashOperations<Long, Object, Object> sessionOperations;

    /**
     * Note that both online and offline information will be cached
     */
    private final Cache<Long, UserSessionsStatus> userSessionsStatusCache;

    private final Duration operationTimeout;
    private final boolean cacheUserSessionsStatus;

    public UserStatusService(
            Node node,
            TurmsPropertiesManager turmsPropertiesManager,
            ReactiveRedisTemplate<Long, String> sessionRedisTemplate) {
        this.node = node;
        TurmsProperties turmsProperties = turmsPropertiesManager.getLocalProperties();
        cacheUserSessionsStatus = turmsProperties.getUserStatus().isCacheUserSessionsStatus();
        operationTimeout = Duration.ofSeconds(10);
        redisTemplate = sessionRedisTemplate;
        sessionOperations = sessionRedisTemplate.opsForHash();
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

    public Mono<String> getNodeIdByUserIdAndDeviceType(@NotNull Long userId, @NotNull DeviceType deviceType) {
        return getUserSessionsStatus(userId)
                .flatMap(sessionsStatus -> {
                    String nodeId = sessionsStatus.getOnlineDeviceTypeAndNodeIdMap().get(deviceType);
                    return nodeId != null ? Mono.just(nodeId) : Mono.empty();
                });
    }

    public Mono<Map<DeviceType, String>> getDeviceAndNodeIdMapByUserId(@NotNull Long userId) {
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
        int size = userIds.size();
        switch (size) {
            case 0:
                return Mono.just(true);
            case 1:
                return updateOnlineUserStatus(userIds.iterator().next(), userStatus);
            default:
                List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    monos.add(updateOnlineUserStatus(userId, userStatus));
                }
                return Flux.merge(monos).all(value -> value);
        }
    }

    public Mono<Boolean> updateOnlineUserStatus(@NotNull Long userId, @NotNull UserStatus userStatus) {
        if (userStatus == UserStatus.UNRECOGNIZED || userStatus == UserStatus.OFFLINE) {
            String failedReason = userStatus == UserStatus.UNRECOGNIZED ?
                    "The user status must not be UNRECOGNIZED" :
                    "The online user status must not be OFFLINE";
            throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, failedReason);
        }
        return sessionOperations
                .put(userId, STATUS_KEY_STATUS, userStatus)
                .timeout(operationTimeout);
    }

    public Mono<Boolean> updateTtl(@NotNull Long userId, @NotNull Duration timeout) {
        return redisTemplate.expire(userId, timeout).timeout(operationTimeout);
    }

    public Mono<UserSessionsStatus> getUserSessionsStatus(@NotNull Long userId) {
        if (cacheUserSessionsStatus) {
            UserSessionsStatus sessionsStatus = userSessionsStatusCache.getIfPresent(userId);
            if (sessionsStatus != null) {
                return Mono.just(sessionsStatus);
            }
        }
        return fetchUserSessionsStatus(userId);
    }

    public Mono<UserSessionsStatus> fetchUserSessionsStatus(@NotNull Long userId) {
        return sessionOperations.entries(userId)
                .timeout(operationTimeout)
                .collectList()
                .map(entries -> {
                    UserStatus userStatus = UserStatus.OFFLINE;
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
                    }
                    if (userStatus == UserStatus.OFFLINE) {
                        onlineDeviceTypeAndNodeIdMap = Collections.emptyMap();
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
     * and don't remove the online status information to avoid querying and removing one more time.
     * The status will be removed when the TTL has been reached
     * and turms won't respond an incorrect user status to clients
     * because turms will check both the "status" and the online devices.
     *
     * @see UserStatusService#fetchUserSessionsStatus(java.lang.Long)
     */
    public Mono<Boolean> removeStatusByUserIdAndDeviceTypes(Long userId, @NotNull Set<DeviceType> deviceTypes) {
        DeviceType[] deviceTypesArray = deviceTypes.toArray(new DeviceType[0]);
        return sessionOperations.remove(userId, (Object[]) deviceTypesArray)
                .timeout(operationTimeout)
                .map(number -> number > 0);
    }

    public Mono<Boolean> addOnlineDeviceIfAbsent(Long userId, DeviceType deviceType, @Nullable UserStatus userStatus, Duration heartbeatTimeout, @NotNull UserSessionsStatus sessionsStatus) {
        String nodeId = node.getNodeId();
        Mono<Boolean> updateMono;
        if (userStatus != null) {
            updateMono = sessionOperations.putAll(userId, Map.of(STATUS_KEY_STATUS, userStatus, deviceType, nodeId));
        } else if (sessionsStatus.getUserStatus() == UserStatus.OFFLINE) {
            updateMono = sessionOperations.putAll(userId, Map.of(STATUS_KEY_STATUS, UserStatus.AVAILABLE, deviceType, nodeId));
        } else {
            updateMono = sessionOperations.put(userId, deviceType, nodeId);
        }
        return updateMono
                .timeout(operationTimeout)
                .flatMap(wasSuccessful -> wasSuccessful
                        ? redisTemplate.expire(userId, heartbeatTimeout)
                        : Mono.just(false));
    }

}