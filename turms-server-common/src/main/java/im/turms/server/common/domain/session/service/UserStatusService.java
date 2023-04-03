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

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.protocol.LongKeyGenerator;
import io.netty.buffer.ByteBuf;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.HashedWheelScheduler;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.redis.RedisEntryId;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.script.RedisScript;

/**
 * @author James Chen
 */
@Service
public class UserStatusService {

    private final RedisScript addOnlineUserScript;
    private final RedisScript getUsersDeviceDetailsScript =
            RedisScript.get(new ClassPathResource("redis/session/get_users_device_details.lua"),
                    ScriptOutputType.MULTI);
    private final RedisScript updateUsersTtlScript =
            RedisScript.get(new ClassPathResource("redis/session/update_users_ttl.lua"),
                    ScriptOutputType.BOOLEAN);
    private final RedisScript updateOnlineUserStatusIfPresent = RedisScript.get(
            new ClassPathResource("redis/session/update_online_user_status_if_present.lua"),
            ScriptOutputType.BOOLEAN);

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
     * 
     * "s" is the fixed hash key of the user status value, and its value is the user status value
     * represented in number.
     * <p>
     * The number (e.g. 1,2,3) represents the online device type, and its value is the node ID that
     * the client connects to.
     */
    private final TurmsRedisClientManager sessionRedisClientManager;

    /**
     * Note that both online and offline information will be cached
     */
    private final Cache<Long, UserSessionsStatus> userIdToStatusCache;

    private final Duration operationTimeout;
    private final boolean cacheUserSessionsStatus;

    private final ByteBuf localNodeId;

    public UserStatusService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            TurmsRedisClientManager sessionRedisClientManager) {
        localNodeId = ByteBufUtil.getUnreleasableDirectBuffer(node.getLocalMemberId()
                .getBytes(StandardCharsets.US_ASCII));
        TurmsProperties turmsProperties = propertiesManager.getLocalProperties();
        addOnlineUserScript = RedisScript.get(
                new ClassPathResource("redis/session/try_add_online_user_with_ttl.lua"),
                ScriptOutputType.BOOLEAN,
                Map.of("DEVICE_DETAILS_TTL",
                        turmsProperties.getGateway()
                                .getSession()
                                .getDeviceDetails()
                                .getExpireAfterSeconds()));
        cacheUserSessionsStatus = turmsProperties.getUserStatus()
                .isCacheUserSessionsStatus();
        operationTimeout = Duration.ofSeconds(10);
        this.sessionRedisClientManager = sessionRedisClientManager;
        if (cacheUserSessionsStatus) {
            Caffeine<Object, Object> builder = Caffeine.newBuilder();
            int maxSize = turmsProperties.getUserStatus()
                    .getUserSessionsStatusCacheMaxSize();
            int expireAfter = turmsProperties.getUserStatus()
                    .getUserSessionsStatusExpireAfter();
            if (maxSize > -1) {
                builder.maximumSize(maxSize);
            }
            if (expireAfter > -1) {
                builder.expireAfterWrite(Duration.ofSeconds(expireAfter));
            }
            userIdToStatusCache = builder.build();
        } else {
            userIdToStatusCache = null;
        }
    }

    public Mono<String> getNodeIdByUserIdAndDeviceType(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return getUserSessionsStatus(userId).flatMap(sessionsStatus -> {
            String nodeId = sessionsStatus.deviceTypeToNodeId()
                    .get(deviceType);
            return nodeId == null
                    ? Mono.empty()
                    : Mono.just(nodeId);
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
            UserSessionsStatus sessionsStatus = userIdToStatusCache.getIfPresent(userId);
            if (sessionsStatus != null) {
                Map<DeviceType, String> deviceTypeToNodeId = sessionsStatus.deviceTypeToNodeId();
                return deviceTypeToNodeId == null || deviceTypeToNodeId.isEmpty()
                        ? Mono.empty()
                        : Mono.just(deviceTypeToNodeId);
            }
        }
        return fetchUserSessionsStatus(userId).flatMap(sessionsStatus -> {
            Map<DeviceType, String> deviceTypeToNodeId = sessionsStatus.deviceTypeToNodeId();
            return deviceTypeToNodeId == null || deviceTypeToNodeId.isEmpty()
                    ? Mono.empty()
                    : Mono.just(deviceTypeToNodeId);
        });
    }

    public Mono<Map<String, Set<DeviceType>>> getNodeIdToDeviceTypeMapByUserId(
            @NotNull Long userId) {
        return getDeviceTypeToNodeIdMapByUserId(userId).map(
                deviceTypeToNodeId -> CollectionUtil.reverseAsSetValues(deviceTypeToNodeId, 2));
    }

    public Mono<Boolean> updateOnlineUsersStatus(
            @NotEmpty Set<Long> userIds,
            @NotNull UserStatus userStatus) {
        try {
            Validator.notEmpty(userIds, "userIds");
            Validator.notNull(userStatus, "userStatus");
            Validator.notEquals(userStatus,
                    UserStatus.UNRECOGNIZED,
                    "The user status must not be UNRECOGNIZED");
            Validator.notEquals(userStatus,
                    UserStatus.OFFLINE,
                    "The user status must not be OFFLINE");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        int size = userIds.size();
        return switch (size) {
            case 0 -> PublisherPool.TRUE;
            case 1 -> updateOnlineUserStatusIfPresent(userIds.iterator()
                    .next(), userStatus);
            default -> {
                List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    monos.add(updateOnlineUserStatusIfPresent(userId, userStatus));
                }
                yield Flux.merge(monos)
                        .all(value -> value);
            }
        };
    }

    /**
     * @return true if updated
     */
    public Mono<Boolean> updateOnlineUserStatusIfPresent(
            @NotNull Long userId,
            @NotNull UserStatus userStatus) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(userStatus, "userStatus");
            Validator.notEquals(userStatus,
                    UserStatus.UNRECOGNIZED,
                    "The user status must not be UNRECOGNIZED");
            Validator.notEquals(userStatus,
                    UserStatus.OFFLINE,
                    "The user status must not be OFFLINE");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Boolean> result = sessionRedisClientManager.eval(userId,
                updateOnlineUserStatusIfPresent,
                userId,
                (byte) userStatus.getNumber());
        return result.timeout(operationTimeout, HashedWheelScheduler.getDaemon());
    }

    public Mono<Void> updateOnlineUsersTtl(
            @NotNull LongKeyGenerator userIdGenerator,
            int timeoutSeconds) {
        try {
            Validator.notNull(userIdGenerator, "userIdGenerator");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return sessionRedisClientManager
                .eval(updateUsersTtlScript, (short) timeoutSeconds, userIdGenerator);
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
            UserSessionsStatus sessionsStatus = userIdToStatusCache.getIfPresent(userId);
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
                .timeout(operationTimeout, HashedWheelScheduler.getDaemon())
                .collect(CollectorUtil.toList(8))
                .map(entries -> {
                    UserStatus userStatus = null;
                    Map<DeviceType, String> onlineDeviceTypeToNodeId = null;
                    for (Map.Entry<Object, Object> entry : entries) {
                        if (entry.getKey()
                                .equals(RedisEntryId.SESSIONS_STATUS)) {
                            userStatus = (UserStatus) entry.getValue();
                        } else {
                            if (onlineDeviceTypeToNodeId == null) {
                                onlineDeviceTypeToNodeId = new FastEnumMap<>(DeviceType.class);
                            }
                            onlineDeviceTypeToNodeId.put((DeviceType) entry.getKey(),
                                    (String) entry.getValue());
                        }
                    }
                    if (onlineDeviceTypeToNodeId == null) {
                        userStatus = UserStatus.OFFLINE;
                        onlineDeviceTypeToNodeId = Collections.emptyMap();
                    } else if (userStatus == null || userStatus == UserStatus.OFFLINE) {
                        userStatus = UserStatus.AVAILABLE;
                    }
                    UserSessionsStatus userSessionsStatus =
                            new UserSessionsStatus(userId, userStatus, onlineDeviceTypeToNodeId);
                    if (cacheUserSessionsStatus) {
                        userIdToStatusCache.put(userId, userSessionsStatus);
                    }
                    return userSessionsStatus;
                });
    }

    /**
     * TODO: cache
     */
    public Mono<Map<Long, Map<String, String>>> fetchDeviceDetails(
            @NotEmpty Set<Long> userIds,
            @NotEmpty List<String> fields) {
        try {
            Validator.notEmpty(userIds, "userIds");
            Validator.notEmpty(fields, "fields");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return sessionRedisClientManager.execute(userIds, (client, ids) -> {
            int fieldCount = fields.size();
            int count = 1 + fieldCount + ids.size();
            ByteBuf[] args = new ByteBuf[count];
            int index = 0;
            try {
                args[index++] = ByteBufUtil.writeByte((byte) fieldCount);
                for (String field : fields) {
                    args[index++] = ByteBufUtil.writeString(field);
                }
                for (Long userId : ids) {
                    args[index++] = ByteBufUtil.writeLong(userId);
                }
            } catch (Exception e) {
                ReferenceCountUtil.ensureReleased(args, 0, index);
                return Mono.error(new InputOutputException("Failed to encode arguments", e));
            }
            return client.eval(getUsersDeviceDetailsScript, args);
        })
                .collect(CollectorUtil.toList(4))
                .map(results -> {
                    Map<Long, Map<String, String>> userIdToDetails =
                            CollectionUtil.newMapWithExpectedSize(userIds.size());
                    for (Object rawElements : results) {
                        List<Object> elements = (List<Object>) rawElements;
                        int elementCount = elements.size();
                        if (elementCount == 0) {
                            continue;
                        }
                        int index = 0;
                        do {
                            Long userId = ((ByteBuf) elements.get(index++)).readLong();
                            int fieldCount = (int) (long) elements.get(index++);
                            Map<String, String> details =
                                    CollectionUtil.newMapWithExpectedSize(fieldCount);
                            for (int i = 0; i < fieldCount; i++) {
                                String key =
                                        ByteBufUtil.readString((ByteBuf) elements.get(index++));
                                String value =
                                        ByteBufUtil.readString((ByteBuf) elements.get(index++));
                                details.put(key, value);
                            }
                            userIdToDetails.put(userId, details);
                        } while (index < elementCount);
                    }
                    return userIdToDetails;
                });
    }

    /**
     * Note that the method only removes the device information of a user and doesn't remove the
     * online status information to avoid querying and removing one more time. The status will be
     * removed when the TTL has been reached and turms won't respond an incorrect user status to
     * clients because turms will check both the "status" and the online devices.
     *
     * @return ture if at least one device type was present (online)
     * @see UserStatusService#fetchUserSessionsStatus(java.lang.Long)
     */
    public Mono<Boolean> removeStatusByUserIdAndDeviceTypes(
            @NotNull Long userId,
            @NotEmpty Set<DeviceType> deviceTypes) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notEmpty(deviceTypes, "deviceTypes");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        DeviceType[] deviceTypesArray = deviceTypes.toArray(new DeviceType[0]);
        return sessionRedisClientManager.hdel(userId, userId, deviceTypesArray)
                .timeout(operationTimeout, HashedWheelScheduler.getDaemon())
                .map(number -> number > 0);
    }

    /**
     * @return true if the userId:deviceType was absent (offline)
     */
    public Mono<Boolean> addOnlineDeviceIfAbsent(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            int heartbeatSeconds) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceType, "deviceType");
            DeviceTypeUtil.validDeviceType(deviceType);
            Validator.notEquals(userStatus,
                    UserStatus.UNRECOGNIZED,
                    "The user status must not be UNRECOGNIZED");
            Validator.notEquals(userStatus,
                    UserStatus.OFFLINE,
                    "The user status must not be OFFLINE");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        int deviceDetailCount = CollectionUtil.getSize(deviceDetails);
        boolean hasDeviceDetails = deviceDetailCount > 0;
        boolean hasUserStatus = userStatus != null;
        int count = hasUserStatus
                ? 5
                : 4;
        if (hasDeviceDetails) {
            count += 2 * deviceDetailCount;
        }
        int index = 0;
        ByteBuf[] args = new ByteBuf[count];
        try {
            args[index++] = ByteBufUtil.writeLong(userId);
            args[index++] = ByteBufUtil.writeByte((byte) deviceType.getNumber());
            args[index++] = localNodeId;
            args[index++] = ByteBufUtil.writeShort((short) heartbeatSeconds);
            if (hasUserStatus) {
                args[index++] = ByteBufUtil.writeByte((byte) userStatus.getNumber());
            }
            if (hasDeviceDetails) {
                for (Map.Entry<String, String> entry : deviceDetails.entrySet()) {
                    args[index++] = ByteBufUtil.writeString(entry.getKey());
                    args[index++] = ByteBufUtil.writeString(entry.getValue());
                }
            }
        } catch (Exception e) {
            ReferenceCountUtil.ensureReleased(args, 0, index);
            return Mono.error(new InputOutputException("Failed to encode arguments", e));
        }
        return sessionRedisClientManager.eval(userId, addOnlineUserScript, args);
    }

}