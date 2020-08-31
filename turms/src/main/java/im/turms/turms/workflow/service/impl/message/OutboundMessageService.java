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

package im.turms.turms.workflow.service.impl.message;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.rpc.request.SendNotificationRequest;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.ProtoUtil;
import im.turms.server.common.util.ReactorUtil;
import io.netty.buffer.ByteBuf;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.*;

/**
 * @author James Chen
 */
@Service
@Log4j2
public class OutboundMessageService {

    private final Node node;
    private final UserStatusService userStatusService;

    public OutboundMessageService(Node node, UserStatusService userStatusService) {
        this.node = node;
        this.userStatusService = userStatusService;
    }

    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notification,
            @NotNull Set<Long> recipientIds) {
        ByteBuf notificationData = ProtoUtil.getByteBuffer(notification);
        return forwardNotification(notificationData, recipientIds);
    }

    public Mono<Boolean> forwardNotification(
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds) {
        if (recipientIds.isEmpty()) {
            return Mono.just(true);
        } else if (recipientIds.size() == 1) {
            return forwardClientMessageByRecipientId(notificationData, recipientIds.iterator().next());
        } else {
            return forwardClientMessageByRecipientIds(notificationData, recipientIds);
        }
    }

    private Mono<Boolean> forwardClientMessageByRecipientIds(
            @NotNull ByteBuf messageData,
            @NotNull Set<Long> recipientIds) {
        if (recipientIds.isEmpty()) {
            return Mono.just(true);
        }
        int recipientIdsSize = recipientIds.size();
        if (recipientIdsSize == 1) {
            return forwardClientMessageByRecipientId(messageData, recipientIds.iterator().next());
        } else {
            int expectedMembersCount = Math.min(node.getDiscoveryService().getAllKnownMembers().size(), recipientIdsSize);
            int expectedRecipientCountPerMember = Math.min(1, recipientIdsSize / expectedMembersCount);
            SetMultimap<String, Long> userIdsByNodeId = HashMultimap.create(expectedMembersCount, expectedRecipientCountPerMember);
            List<Mono<Pair<Long, Collection<String>>>> monos = new ArrayList<>(recipientIdsSize);
            for (Long recipientId : recipientIds) {
                monos.add(userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                        .map(map -> Pair.of(recipientId, map.values())));
            }
            return Flux.merge(monos)
                    .doOnNext(pair -> {
                        for (String nodeId : pair.getSecond()) {
                            userIdsByNodeId.put(nodeId, pair.getFirst());
                        }
                    })
                    .then(Mono.defer(() -> {
                        List<Mono<Boolean>> monoList = new LinkedList<>();
                        for (String nodeId : userIdsByNodeId.keys()) {
                            monoList.add(forwardClientMessageByRecipientIds(messageData, userIdsByNodeId.get(nodeId)));
                        }
                        return ReactorUtil.atLeastOneTrue(monoList);
                    }));
        }
    }

    private Mono<Boolean> forwardClientMessageByRecipientId(
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId) {
        return userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Collection<String> nodeIds = deviceTypeAndNodeIdMap.values();
                    if (nodeIds.isEmpty()) {
                        return Mono.just(false);
                    }
                    int size = nodeIds.size();
                    if (size == 1) {
                        return forwardClientMessageToNode(notificationData, nodeIds.iterator().next(), recipientId);
                    } else {
                        List<Mono<Boolean>> monos = new ArrayList<>(size);
                        for (String nodeId : nodeIds) {
                            monos.add(forwardClientMessageToNode(notificationData, nodeId, recipientId));
                        }
                        return ReactorUtil.atLeastOneTrue(monos);
                    }
                })
                .defaultIfEmpty(false);
    }

    private Mono<Boolean> forwardClientMessageToNode(
            @NotNull ByteBuf messageData,
            @NotNull String nodeId,
            @NotNull Long recipient) {
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                Set.of(recipient));
        return node.getRpcService()
                .requestResponse(nodeId, request);
    }

    public Mono<Boolean> forwardNotification(
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId,
            @NotNull DeviceType excludedDeviceType) {
        return userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Map<DeviceType, String> map = new EnumMap<>(deviceTypeAndNodeIdMap);
                    map.remove(excludedDeviceType);
                    Collection<String> nodeIds = map.values();
                    if (nodeIds.isEmpty()) {
                        return Mono.just(false);
                    }
                    int size = nodeIds.size();
                    if (size == 1) {
                        return forwardClientMessageToNode(notificationData, nodeIds.iterator().next(), recipientId);
                    } else {
                        List<Mono<Boolean>> monos = new ArrayList<>(size);
                        for (String nodeId : nodeIds) {
                            monos.add(forwardClientMessageToNode(notificationData, nodeId, recipientId));
                        }
                        return ReactorUtil.atLeastOneTrue(monos);
                    }
                })
                .defaultIfEmpty(false);
    }

}
