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

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import io.lettuce.core.ScriptOutputType;
import io.lettuce.core.protocol.LongKeyGenerator;
import io.netty.buffer.ByteBuf;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.constant.UserStatus;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.domain.common.util.DeviceTypeUtil;
import im.turms.server.common.domain.session.bo.UserDeviceSessionInfo;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.bo.UserStatusField;
import im.turms.server.common.domain.session.bo.UserStatusFieldType;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.collection.FastEnumMap;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.MathUtil;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.netty.ByteBufUtil;
import im.turms.server.common.infra.netty.ReferenceCountUtil;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.gateway.session.SessionProperties;
import im.turms.server.common.infra.reactor.HashedWheelScheduler;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.test.VisibleForTesting;
import im.turms.server.common.infra.time.DateTimeUtil;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.redis.TurmsRedisClientManager;
import im.turms.server.common.storage.redis.script.RedisScript;

/**
 * @author James Chen
 */
@ConditionalOnBean(name = "sessionRedisClientManager")
@Service
public class UserStatusService extends BaseService {

    private static final long NODE_STATUS_TTL_NANOS = 15 * DateTimeUtil.NANOS_PER_SECOND;

    private final RedisScript<ByteBuf> addOnlineUserScript;
    private final RedisScript<List<Object>> getUsersDeviceDetailsScript =
            RedisScript.get(new ClassPathResource("redis/session/get_users_device_details.lua"),
                    ScriptOutputType.MULTI);
    private final RedisScript<Boolean> removeUserStatusesScript =
            RedisScript.get(new ClassPathResource("redis/session/remove_user_statuses.lua"),
                    ScriptOutputType.BOOLEAN);
    private final RedisScript<List<ByteBuf>> updateUsersTtlScript =
            RedisScript.get(new ClassPathResource("redis/session/update_users_ttl.lua"),
                    ScriptOutputType.MULTI);
    private final RedisScript<Boolean> updateOnlineUserStatusIfPresent = RedisScript.get(
            new ClassPathResource("redis/session/update_online_user_status_if_present.lua"),
            ScriptOutputType.BOOLEAN);

    /**
     * <pre>
     * +-------------+-------------------------+-----------------------------------+
     * |   User ID   |          Field          |               Value               |
     * +-------------+-------------------------+-----------------------------------+
     * |             |   $ (User Status Key)   |          1 (User Status)          |
     * |             +-------------------------+-----------------------------------+
     * |             |     0 (Device Type)     |        turms0001 (Node ID)        |
     * |             +-------------------------+-----------------------------------+
     * |  123456789  |     1 (Device Type)     |        turms0001 (Node ID)        |
     * |             +-------------------------+-----------------------------------+
     * |             |     2 (Device Type)     |        turms0002 (Node ID)        |
     * |             +-------------------------+-----------------------------------+
     * |             |   turms0001 (Node ID)   |  123456789 (Heartbeat Timestamp)  |
     * |             +-------------------------+-----------------------------------+
     * |             |   turms0002 (Node ID)   |  123456789 (Heartbeat Timestamp)  |
     * |             +-------------------------+-----------------------------------+
     * |             |           ...           |                ...                |
     * +-------------+-------------------------+-----------------------------------+
     * </pre>
     * <p>
     * "$" is the fixed hash key of the user status value, and its value is the user status value
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

    private final Node node;
    private final String localNodeId;
    private final byte[] localNodeIdBytes;
    private final ByteBuf localNodeIdBuffer;

    private final Map<String, Mono<NodeStatus>> nodeIdToStatusCache;

    private final long deviceStatusTtlMillis;

    public UserStatusService(
            Node node,
            TurmsPropertiesManager propertiesManager,
            TurmsRedisClientManager sessionRedisClientManager) {
        this.node = node;
        localNodeId = node.getLocalMemberId();
        localNodeIdBytes = StringUtil.getBytes(localNodeId);
        localNodeIdBuffer = ByteBufUtil.getUnreleasableDirectBuffer(localNodeIdBytes);
        TurmsProperties turmsProperties = propertiesManager.getLocalProperties();
        SessionProperties sessionProperties = turmsProperties.getGateway()
                .getSession();
        int deviceDetailsExpireAfterSeconds = sessionProperties.getDeviceDetails()
                .getExpireAfterSeconds();
        int deviceStatusTtlSeconds = MathUtil
                .add(sessionProperties.getCloseIdleSessionAfterSeconds(), 1, Integer.MAX_VALUE);
        deviceStatusTtlMillis = deviceStatusTtlSeconds * 1000L;
        addOnlineUserScript = RedisScript.get(
                new ClassPathResource("redis/session/try_add_online_user_with_ttl.lua"),
                ScriptOutputType.VALUE,
                Map.of("DEVICE_DETAILS_TTL",
                        deviceDetailsExpireAfterSeconds,
                        "DEVICE_STATUS_TTL",
                        deviceStatusTtlSeconds));
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
        nodeIdToStatusCache = new ConcurrentHashMap<>(32);
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
            String nodeId = sessionsStatus.getNodeIdIfActive(deviceType);
            return nodeId == null
                    ? Mono.empty()
                    : Mono.just(nodeId);
        });
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
        Mono<Boolean> mono = sessionRedisClientManager
                .eval(userId,
                        updateOnlineUserStatusIfPresent,
                        userId,
                        (byte) userStatus.getNumber())
                .timeout(operationTimeout, HashedWheelScheduler.getDaemon());
        if (cacheUserSessionsStatus) {
            return mono.doOnNext(exists -> {
                if (!exists) {
                    userIdToStatusCache.invalidate(userId);
                }
            });
        }
        return mono;
    }

    public Mono<Set<Long>> updateOnlineUsersTtl(
            @NotNull LongKeyGenerator userIdGenerator,
            int timeoutSeconds) {
        try {
            Validator.notNull(userIdGenerator, "userIdGenerator");
            Validator.max(timeoutSeconds, "timeoutSeconds", Short.MAX_VALUE);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return sessionRedisClientManager
                .eval(updateUsersTtlScript,
                        (short) timeoutSeconds,
                        localNodeIdBytes,
                        userIdGenerator)
                .collect(CollectorUtil.toList(16))
                .map(objects -> {
                    if (objects.isEmpty()) {
                        return Collections.emptySet();
                    }
                    int size = userIdGenerator.estimatedSize();
                    Set<Long> nonexistentUserIds = CollectionUtil.newSetWithExpectedSize(size);
                    for (List<ByteBuf> buffers : objects) {
                        if (buffers.size() == 1 && buffers.getFirst() == null) {
                            return Collections.emptySet();
                        }
                        for (ByteBuf buffer : buffers) {
                            Long userId = buffer.readLong();
                            nonexistentUserIds.add(userId);
                            if (cacheUserSessionsStatus) {
                                userIdToStatusCache.invalidate(userId);
                            }
                        }
                    }
                    return nonexistentUserIds;
                });
    }

    /**
     * @return {@link UserStatus#OFFLINE} instead of MonoEmpty for an offline user
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
     * @return {@link UserStatus#OFFLINE} instead of MonoEmpty for the offline user
     */
    public Mono<UserSessionsStatus> fetchUserSessionsStatus(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<UserSessionsStatus> userSessionsStatusMono =
                sessionRedisClientManager.hgetall(userId, userId)
                        .timeout(operationTimeout, HashedWheelScheduler.getDaemon())
                        .collect(CollectorUtil.toList(8))
                        .flatMap(entries -> handleUserSessionsStatusEntries(userId, entries));
        if (cacheUserSessionsStatus) {
            return userSessionsStatusMono.doOnNext(
                    userSessionsStatus -> userIdToStatusCache.put(userId, userSessionsStatus));
        }
        return userSessionsStatusMono;
    }

    private Mono<UserSessionsStatus> handleUserSessionsStatusEntries(
            Long userId,
            List<Map.Entry<Object, Object>> entries) {
        UserStatus userStatus = null;
        Map<DeviceType, UserDeviceSessionInfo> onlineDeviceTypeToSessionInfo = null;
        List<Pair<String, Long>> nodeIdToHeartbeatTimestampPairs = new ArrayList<>(entries.size());
        UserStatusField field;
        Object value;
        long now = System.currentTimeMillis();

        for (Map.Entry<Object, Object> entry : entries) {
            field = (UserStatusField) entry.getKey();
            value = entry.getValue();
            UserStatusFieldType fieldedType = field.fieldType();
            if (fieldedType == UserStatusFieldType.USER_STATUS) {
                userStatus = (UserStatus) value;
                continue;
            }
            switch (fieldedType) {
                case DEVICE_TYPE_TO_NODE_ID -> {
                    if (onlineDeviceTypeToSessionInfo == null) {
                        onlineDeviceTypeToSessionInfo = new FastEnumMap<>(DeviceType.class);
                    }
                    UserDeviceSessionInfo sessionInfo = onlineDeviceTypeToSessionInfo
                            .computeIfAbsent((DeviceType) field.value(),
                                    // Set "isActive" to true for backward
                                    // compatibility
                                    type -> new UserDeviceSessionInfo(true));
                    sessionInfo.setNodeId((String) value);
                }
                case NODE_ID_TO_HEARTBEAT_TIMESTAMP -> nodeIdToHeartbeatTimestampPairs
                        .add(Pair.of((String) field.value(), (Long) value));
            }
        }
        if (onlineDeviceTypeToSessionInfo == null) {
            userStatus = UserStatus.OFFLINE;
            onlineDeviceTypeToSessionInfo = Collections.emptyMap();
        } else {
            if (onlineDeviceTypeToSessionInfo.isEmpty()) {
                userStatus = UserStatus.OFFLINE;
            } else {
                Collection<UserDeviceSessionInfo> sessionInfos =
                        onlineDeviceTypeToSessionInfo.values();
                int infoCount = sessionInfos.size();
                if (infoCount == 1
                        && localNodeId.equals(sessionInfos.iterator()
                                .next()
                                .getNodeId())) {
                    if (userStatus == null || userStatus == UserStatus.OFFLINE) {
                        userStatus = UserStatus.AVAILABLE;
                    }
                } else {
                    int activeNodeCount = nodeIdToHeartbeatTimestampPairs.size();
                    for (Pair<String, Long> pair : nodeIdToHeartbeatTimestampPairs) {
                        String nodeId = pair.first();
                        Long timestampSeconds = pair.second();
                        boolean isActive = now - timestampSeconds * 1000L <= deviceStatusTtlMillis;
                        if (!isActive) {
                            activeNodeCount--;
                        }
                        for (UserDeviceSessionInfo sessionInfo : sessionInfos) {
                            if (nodeId.equals(sessionInfo.getNodeId())) {
                                sessionInfo.setHeartbeatTimestampSeconds(timestampSeconds);
                                sessionInfo.setActive(isActive);
                            }
                        }
                    }
                    if (activeNodeCount == 0) {
                        userStatus = UserStatus.OFFLINE;
                        UserSessionsStatus userSessionsStatus = new UserSessionsStatus(
                                userId,
                                userStatus,
                                onlineDeviceTypeToSessionInfo);
                        return Mono.just(userSessionsStatus);
                    }
                    return detectInactiveNodesAndUpdateActiveInfos(userId,
                            sessionInfos,
                            infoCount,
                            onlineDeviceTypeToSessionInfo,
                            userStatus);
                }
            }
        }
        UserSessionsStatus userSessionsStatus =
                new UserSessionsStatus(userId, userStatus, onlineDeviceTypeToSessionInfo);
        return Mono.just(userSessionsStatus);
    }

    private Mono<UserSessionsStatus> detectInactiveNodesAndUpdateActiveInfos(
            Long userId,
            Collection<UserDeviceSessionInfo> sessionInfos,
            int nodeCount,
            Map<DeviceType, UserDeviceSessionInfo> onlineDeviceTypeToSessionInfo,
            @Nullable UserStatus userStatus) {
        List<Mono<Pair<String, NodeStatus>>> fetchNodeStatuses = new ArrayList<>(nodeCount);
        for (UserDeviceSessionInfo sessionInfo : sessionInfos) {
            if (sessionInfo.isActive()) {
                String nodeId = sessionInfo.getNodeId();
                if (!localNodeId.equals(nodeId)) {
                    fetchNodeStatuses
                            .add(fetchNodeStatus(nodeId).map(status -> Pair.of(nodeId, status)));
                }
            }
        }
        return Flux.merge(fetchNodeStatuses)
                .collect(CollectorUtil.toList(fetchNodeStatuses.size()))
                .map(pairs -> {
                    int activeNodeCount = pairs.size();
                    for (Pair<String, NodeStatus> pair : pairs) {
                        NodeStatus status = pair.second();
                        if (!status.isActive) {
                            activeNodeCount--;
                            String nodeId = pair.first();
                            for (Map.Entry<DeviceType, UserDeviceSessionInfo> entry : onlineDeviceTypeToSessionInfo
                                    .entrySet()) {
                                UserDeviceSessionInfo sessionInfo = entry.getValue();
                                if (sessionInfo.getNodeId()
                                        .equals(nodeId)) {
                                    sessionInfo.setActive(false);
                                }
                            }
                        }
                    }
                    UserStatus status;
                    if (activeNodeCount == 0) {
                        status = UserStatus.OFFLINE;
                    } else if (userStatus == null || userStatus == UserStatus.OFFLINE) {
                        status = UserStatus.AVAILABLE;
                    } else {
                        status = userStatus;
                    }
                    return new UserSessionsStatus(userId, status, onlineDeviceTypeToSessionInfo);
                });
    }

    private Mono<NodeStatus> fetchNodeStatus(String nodeId) {
        Mono<NodeStatus> nodeStatusMono = nodeIdToStatusCache.computeIfAbsent(nodeId,
                id -> node.getDiscoveryService()
                        .checkIfMemberExists(nodeId)
                        .map(isActive -> new NodeStatus(System.nanoTime(), isActive)));
        return nodeStatusMono
                // To not cache error
                .doOnError(t -> nodeIdToStatusCache.remove(nodeId, nodeStatusMono))
                .flatMap(status -> {
                    if ((System.nanoTime() - status.recordTimestampNanos) < NODE_STATUS_TTL_NANOS) {
                        return Mono.just(status);
                    }
                    Mono<NodeStatus> newStatus = Mono.defer(() -> node.getDiscoveryService()
                            .checkIfMemberExists(nodeId)
                            .map(isActive -> new NodeStatus(System.nanoTime(), isActive)));
                    boolean replaced =
                            nodeIdToStatusCache.replace(nodeId, nodeStatusMono, newStatus);
                    return replaced
                            ? newStatus
                                    // To not cache error
                                    .doOnError(t -> nodeIdToStatusCache.remove(nodeId, newStatus))
                            : nodeIdToStatusCache.get(nodeId);
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
                    for (List<Object> elements : results) {
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
     * @return true if at least one device type was present (online) and removed.
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
        int deviceTypeCount = deviceTypes.size();
        boolean removeAllDeviceStatuses = deviceTypeCount == DeviceTypeUtil.DEVICE_TYPE_COUNT;
        ByteBuf[] args = removeAllDeviceStatuses
                ? new ByteBuf[2]
                : new ByteBuf[2 + deviceTypeCount];
        int index = 0;
        try {
            args[index++] = ByteBufUtil.writeLong(userId);
            args[index++] = localNodeIdBuffer.duplicate();
            if (!removeAllDeviceStatuses) {
                for (DeviceType deviceType : deviceTypes) {
                    args[index++] = ByteBufUtil.writeByte((byte) deviceType.getNumber());
                }
            }
        } catch (Exception e) {
            ReferenceCountUtil.ensureReleased(args, 0, index);
            return Mono.error(new InputOutputException("Failed to encode arguments", e));
        }
        Mono<Boolean> mono = sessionRedisClientManager.eval(userId, removeUserStatusesScript, args);
        if (cacheUserSessionsStatus) {
            mono = mono.doOnSuccess(ignored -> userIdToStatusCache.invalidate(userId));
        }
        return mono.timeout(operationTimeout, HashedWheelScheduler.getDaemon());
    }

    public Mono<Boolean> addOnlineDeviceIfAbsent(
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            int heartbeatSeconds,
            @Nullable String expectedNodeId,
            @Nullable Long expectedDeviceTimestampSeconds) {
        return addOnlineDeviceIfAbsent(localNodeIdBuffer.duplicate(),
                userId,
                deviceType,
                deviceDetails,
                userStatus,
                heartbeatSeconds,
                expectedNodeId,
                expectedDeviceTimestampSeconds);
    }

    /**
     * @return true if the userId:deviceType was absent (offline)
     */
    @VisibleForTesting
    public Mono<Boolean> addOnlineDeviceIfAbsent(
            @NotNull ByteBuf localNodeId,
            @NotNull Long userId,
            @NotNull @ValidDeviceType DeviceType deviceType,
            @Nullable Map<String, String> deviceDetails,
            @Nullable UserStatus userStatus,
            int heartbeatSeconds,
            @Nullable String expectedNodeId,
            @Nullable Long expectedDeviceTimestampSeconds) {
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
        boolean hasExpectedValues =
                expectedNodeId != null && expectedDeviceTimestampSeconds != null;
        int count = 4;
        if (hasDeviceDetails) {
            count += 3 + 2 * deviceDetailCount;
        } else if (hasExpectedValues) {
            count += 3;
        } else if (hasUserStatus) {
            count++;
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
            } else {
                index++;
            }
            if (hasExpectedValues) {
                args[index++] = ByteBufUtil.writeString(expectedNodeId);
                args[index++] = ByteBufUtil.writeLong(expectedDeviceTimestampSeconds);
            } else {
                index += 2;
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
        return sessionRedisClientManager.eval(userId, addOnlineUserScript, args)
                .map(buffer -> {
                    byte returnCode = buffer.readByte();
                    return switch (returnCode) {
                        case '0' -> false;
                        case '1' -> {
                            if (cacheUserSessionsStatus) {
                                Map<DeviceType, UserDeviceSessionInfo> deviceTypeToSessions =
                                        new FastEnumMap<>(DeviceType.class);
                                deviceTypeToSessions.put(deviceType,
                                        new UserDeviceSessionInfo(
                                                this.localNodeId,
                                                System.currentTimeMillis(),
                                                true));
                                userIdToStatusCache.put(userId,
                                        new UserSessionsStatus(
                                                userId,
                                                userStatus,
                                                deviceTypeToSessions));
                            }
                            yield true;
                        }
                        case '2' -> true;
                        default -> throw new RuntimeException(
                                "Unexpected return code: "
                                        + returnCode);
                    };
                });
    }

    private record NodeStatus(
            long recordTimestampNanos,
            boolean isActive
    ) {
    }

}