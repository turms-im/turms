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

package im.turms.service.workflow.service.impl.user.onlineuser;

import im.turms.common.constant.DeviceType;
import im.turms.common.constant.statuscode.SessionCloseStatus;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.constraint.ValidDeviceType;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.rpc.request.SetUserOfflineRequest;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.AssertUtil;
import im.turms.server.common.util.CollectionUtil;
import im.turms.server.common.util.ReactorUtil;
import im.turms.service.workflow.service.util.DomainConstraintUtil;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
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

    public SessionService(
            Node node,
            UserStatusService userStatusService) {
        this.node = node;
        this.userStatusService = userStatusService;
    }

    /**
     * @return true if at least one device of the user was online
     */
    public Mono<Boolean> disconnect(@NotNull Long userId, @NotNull SessionCloseStatus closeStatus) {
        try {
            AssertUtil.notNull(closeStatus, "closeStatus");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        return userStatusService.getNodeIdAndDeviceMapByUserId(userId)
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
            AssertUtil.notNull(userId, "userId");
            AssertUtil.notNull(deviceTypes, "deviceTypes");
            AssertUtil.notNull(closeStatus, "closeStatus");
            for (DeviceType deviceType : deviceTypes) {
                DomainConstraintUtil.validDeviceType(deviceType);
            }
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (deviceTypes.isEmpty()) {
            return disconnect(userId, closeStatus);
        }
        int size = deviceTypes.size();
        if (size == 1) {
            return disconnect(userId, deviceTypes.iterator().next(), closeStatus);
        } else {
            return userStatusService.getNodeIdAndDeviceMapByUserId(userId)
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
    }

    /**
     * @return true if was online
     */
    public Mono<Boolean> disconnect(@NotNull Long userId,
                                    @NotNull @ValidDeviceType DeviceType deviceType,
                                    @NotNull SessionCloseStatus closeStatus) {
        try {
            AssertUtil.notNull(closeStatus, "closeStatus");
        } catch (TurmsBusinessException e) {
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
            AssertUtil.notNull(userIds, "userIds");
            AssertUtil.notNull(closeStatus, "closeStatus");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        switch (userIds.size()) {
            case 0:
                return Mono.just(true);
            case 1:
                return disconnect(userIds.iterator().next(), closeStatus);
            default:
                List<Mono<Boolean>> monos = new ArrayList<>(userIds.size());
                for (Long userId : userIds) {
                    monos.add(disconnect(userId, closeStatus));
                }
                return ReactorUtil.atLeastOneTrue(monos);
        }
    }

    public Mono<Boolean> disconnect(@NotNull Set<Long> userIds,
                                    @Nullable Set<@ValidDeviceType DeviceType> deviceTypes,
                                    @NotNull SessionCloseStatus closeStatus) {
        try {
            AssertUtil.notNull(userIds, "userIds");
            AssertUtil.notNull(closeStatus, "closeStatus");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (deviceTypes == null || deviceTypes.isEmpty()) {
            return disconnect(userIds, closeStatus);
        } else {
            int size = userIds.size();
            switch (size) {
                case 0:
                    return Mono.just(true);
                case 1:
                    return disconnect(userIds.iterator().next(), deviceTypes, closeStatus);
                default:
                    List<Mono<Boolean>> monos = new ArrayList<>(size);
                    for (Long userId : userIds) {
                        monos.add(disconnect(userId, deviceTypes, closeStatus));
                    }
                    return ReactorUtil.atLeastOneTrue(monos);
            }
        }
    }

}
