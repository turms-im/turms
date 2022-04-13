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

package im.turms.service.domain.user.service.onlineuser;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.domain.session.bo.SessionCloseStatus;
import im.turms.server.common.domain.session.bo.UserSessionsInfo;
import im.turms.server.common.domain.session.bo.UserSessionsStatus;
import im.turms.server.common.domain.session.rpc.QueryUserSessionsRequest;
import im.turms.server.common.domain.session.rpc.SetUserOfflineRequest;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.rpc.RpcService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.collection.MapUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.reactor.ReactorUtil;
import im.turms.server.common.infra.validation.ValidDeviceType;
import im.turms.server.common.infra.validation.Validator;
import im.turms.service.domain.common.validation.DataValidator;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
public class SessionService {

    private final Node node;
    private final UserStatusService userStatusService;
    private final RpcService rpcService;

    public SessionService(
            Node node,
            UserStatusService userStatusService) {
        this.node = node;
        this.userStatusService = userStatusService;
        rpcService = node.getRpcService();
    }

    /**
     * @return true if at least one device of the user was online
     */
    public Mono<Boolean> disconnect(@NotNull Long userId, @NotNull SessionCloseStatus closeStatus) {
        try {
            Validator.notNull(closeStatus, "closeStatus");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userStatusService.getNodeIdToDeviceTypeMapByUserId(userId)
                .flatMap(nodeIdAndDeviceTypeMap -> {
                    Set<Map.Entry<String, Collection<DeviceType>>> entries = nodeIdAndDeviceTypeMap.asMap().entrySet();
                    List<Mono<Boolean>> monos = new ArrayList<>(entries.size());
                    for (Map.Entry<String, Collection<DeviceType>> entry : entries) {
                        SetUserOfflineRequest request = new SetUserOfflineRequest(userId, (Set<DeviceType>) entry.getValue(), closeStatus);
                        monos.add(node.getRpcService().requestResponse(entry.getKey(), request));
                    }
                    return ReactorUtil.atLeastOneTrue(monos);
                })
                .defaultIfEmpty(false);
    }

    /**
     * @return true if was online
     */
    public Mono<Boolean> disconnect(@NotNull Long userId,
                                    @NotNull Set<@ValidDeviceType DeviceType> deviceTypes,
                                    @NotNull SessionCloseStatus closeStatus) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(deviceTypes, "deviceTypes");
            Validator.notNull(closeStatus, "closeStatus");
            for (DeviceType deviceType : deviceTypes) {
                DataValidator.validDeviceType(deviceType);
            }
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (deviceTypes.isEmpty()) {
            return disconnect(userId, closeStatus);
        }
        int size = deviceTypes.size();
        if (size == 1) {
            return disconnect(userId, deviceTypes.iterator().next(), closeStatus);
        }
        return userStatusService.getNodeIdToDeviceTypeMapByUserId(userId)
                .flatMap(nodeIdAndDeviceTypeMap -> {
                    Set<Map.Entry<String, Collection<DeviceType>>> entries = nodeIdAndDeviceTypeMap.asMap().entrySet();
                    List<Mono<Boolean>> monos = new ArrayList<>(entries.size());
                    for (Map.Entry<String, Collection<DeviceType>> entry : entries) {
                        Set<DeviceType> types = CollectionUtil.intersection(deviceTypes, entry.getValue());
                        if (!types.isEmpty()) {
                            SetUserOfflineRequest request = new SetUserOfflineRequest(userId, types, closeStatus);
                            monos.add(node.getRpcService().requestResponse(entry.getKey(), request));
                        }
                    }
                    return ReactorUtil.atLeastOneTrue(monos);
                })
                .defaultIfEmpty(false);
    }

    /**
     * @return true if was online
     */
    public Mono<Boolean> disconnect(@NotNull Long userId,
                                    @NotNull @ValidDeviceType DeviceType deviceType,
                                    @NotNull SessionCloseStatus closeStatus) {
        try {
            Validator.notNull(closeStatus, "closeStatus");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userStatusService.getNodeIdByUserIdAndDeviceType(userId, deviceType)
                .flatMap(nodeId -> {
                    SetUserOfflineRequest request = new SetUserOfflineRequest(userId, Set.of(deviceType), closeStatus);
                    return node.getRpcService().requestResponse(nodeId, request);
                })
                .defaultIfEmpty(false);
    }

    public Mono<Boolean> disconnect(@NotNull Set<Long> userIds, @NotNull SessionCloseStatus closeStatus) {
        try {
            Validator.notNull(userIds, "userIds");
            Validator.notNull(closeStatus, "closeStatus");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return switch (userIds.size()) {
            case 0 -> PublisherPool.TRUE;
            case 1 -> disconnect(userIds.iterator().next(), closeStatus);
            default -> {
                List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    monos.add(disconnect(userId, closeStatus));
                }
                yield ReactorUtil.atLeastOneTrue(monos);
            }
        };
    }

    public Mono<Boolean> disconnect(@NotNull Set<Long> userIds,
                                    @Nullable Set<@ValidDeviceType DeviceType> deviceTypes,
                                    @NotNull SessionCloseStatus closeStatus) {
        try {
            Validator.notNull(userIds, "userIds");
            Validator.notNull(closeStatus, "closeStatus");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (deviceTypes == null || deviceTypes.isEmpty()) {
            return disconnect(userIds, closeStatus);
        }
        int size = userIds.size();
        return switch (size) {
            case 0 -> PublisherPool.TRUE;
            case 1 -> disconnect(userIds.iterator().next(), deviceTypes, closeStatus);
            default -> {
                List<Mono<Boolean>> monos = new ArrayList<>(size);
                for (Long userId : userIds) {
                    monos.add(disconnect(userId, deviceTypes, closeStatus));
                }
                yield ReactorUtil.atLeastOneTrue(monos);
            }
        };
    }

    public Mono<Collection<UserSessionsInfo>> queryUserSessions(Set<Long> userIds) {
        int userCount = userIds.size();
        if (userCount == 0) {
            return PublisherPool.emptyCollection();
        }
        List<Mono<UserSessionsStatus>> sessionStatusMonos = new ArrayList<>(userCount);
        for (Long userId : userIds) {
            sessionStatusMonos.add(userStatusService.getUserSessionsStatus(userId));
        }
        return Flux.merge(sessionStatusMonos)
                .collect(CollectorUtil.toList(userCount))
                .flatMap(statuses -> {
                    // Find which nodes the users are in
                    Map<String, Set<Long>> nodeIdToUserIds = new HashMap<>(16);
                    for (UserSessionsStatus status : statuses) {
                        for (String nodeId : status.deviceTypeToNodeId().values()) {
                            nodeIdToUserIds.computeIfAbsent(nodeId, key -> CollectionUtil.newSetWithExpectedSize(userCount))
                                    .add(status.userId());
                        }
                    }
                    int nodeIdCount = nodeIdToUserIds.size();
                    if (nodeIdCount == 0) {
                        return PublisherPool.emptyCollection();
                    }
                    List<Mono<List<UserSessionsInfo>>> querySessionsRequests = new ArrayList<>(nodeIdCount);
                    // Send RPC to the nodes to query sessions
                    for (Map.Entry<String, Set<Long>> nodeIdAndUserIds : nodeIdToUserIds.entrySet()) {
                        querySessionsRequests.add(rpcService
                                .requestResponse(nodeIdAndUserIds.getKey(), new QueryUserSessionsRequest(nodeIdAndUserIds.getValue())));
                    }
                    return Flux.merge(querySessionsRequests)
                            .collect(CollectorUtil.toList(nodeIdCount))
                            .map(sessionsFromNodes -> {
                                int nodeCount = sessionsFromNodes.size();
                                // fast path
                                if (nodeCount == 1) {
                                    return sessionsFromNodes.get(0);
                                }
                                // slow path
                                // A user may have multiple sessions in different nodes,
                                // so we need to merge them together
                                Map<Long, UserSessionsInfo> userIdToSessions = new HashMap<>(MapUtil.getCapability(userCount));
                                for (List<UserSessionsInfo> sessionsFromNode : sessionsFromNodes) {
                                    for (UserSessionsInfo sessions : sessionsFromNode) {
                                        userIdToSessions.compute(sessions.userId(), (userId, existing) -> {
                                            if (existing == null) {
                                                return sessions;
                                            }
                                            existing.sessions().addAll(sessions.sessions());
                                            return existing;
                                        });
                                    }
                                }
                                return userIdToSessions.values();
                            });
                });
    }

}
