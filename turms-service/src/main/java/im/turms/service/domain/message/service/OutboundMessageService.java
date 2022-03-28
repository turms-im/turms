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

package im.turms.service.domain.message.service;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.domain.notification.rpc.SendNotificationRequest;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.proto.ProtoEncoder;
import im.turms.server.common.infra.reactor.ReactorUtil;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.infra.logging.ApiLoggingContext;
import im.turms.service.infra.logging.NotificationLogging;
import io.netty.buffer.ByteBuf;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 * @implNote 1. All operations that send the outbound message buffer to other servers
 * need to ensure that the buffer will be released by 1.
 * 2. To keep operations as simple as possible,
 * all operations doesn't support the cancellation operation.
 * In other words, it will leak memory if cancellation occurs.
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class OutboundMessageService {

    private final Node node;
    private final ApiLoggingContext apiLoggingContext;
    private final UserStatusService userStatusService;

    public OutboundMessageService(Node node, ApiLoggingContext apiLoggingContext, UserStatusService userStatusService) {
        this.node = node;
        this.apiLoggingContext = apiLoggingContext;
        this.userStatusService = userStatusService;
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notification,
            @NotNull Set<Long> recipientIds) {
        return forwardNotification(notification, ProtoEncoder.getDirectByteBuffer(notification), recipientIds);
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds) {
        if (recipientIds.isEmpty()) {
            notificationData.release();
            return Mono.just(true);
        }
        int recipientCount = recipientIds.size();
        Mono<Boolean> mono = recipientCount == 1
                ? forwardClientMessageByRecipientId(notificationData, recipientIds.iterator().next())
                : forwardClientMessageByRecipientIds(notificationData, recipientIds);
        return tryLogNotification(mono, notificationForLogging, recipientCount);
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId,
            @NotNull DeviceType excludedDeviceType) {
        return userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                .doOnError(t -> notificationData.release())
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Set<String> nodeIds = CollectionUtil.newSetWithExpectedSize(deviceTypeAndNodeIdMap.size());
                    for (Map.Entry<DeviceType, String> entry : deviceTypeAndNodeIdMap.entrySet()) {
                        DeviceType deviceType = entry.getKey();
                        if (deviceType != excludedDeviceType) {
                            nodeIds.add(entry.getValue());
                        }
                    }
                    if (nodeIds.isEmpty()) {
                        notificationData.release();
                        return Mono.just(false);
                    }
                    Mono<Boolean> mono = forwardClientMessageToNodes(notificationData, nodeIds, recipientId);
                    return tryLogNotification(mono, notificationForLogging, 1);
                })
                .switchIfEmpty(Mono.fromCallable(() -> {
                    notificationData.release();
                    return false;
                }));
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    private Mono<Boolean> forwardClientMessageByRecipientIds(
            @NotNull ByteBuf messageData,
            @NotNull Set<Long> recipientIds) {
        if (recipientIds.isEmpty()) {
            messageData.release();
            return Mono.just(true);
        }
        int recipientIdCount = recipientIds.size();
        if (recipientIdCount == 1) {
            return forwardClientMessageByRecipientId(messageData, recipientIds.iterator().next());
        }
        List<Mono<RecipientAndNodeIds>> monos = new ArrayList<>(recipientIdCount);
        for (Long recipientId : recipientIds) {
            monos.add(userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                    .map(map -> new RecipientAndNodeIds(recipientId, map.values())));
        }
        return Flux.merge(monos)
                .doOnError(t -> messageData.release())
                .collect(CollectorUtil.toList(recipientIdCount))
                .flatMap(pairs -> {
                    if (pairs.isEmpty()) {
                        messageData.release();
                        return Mono.just(false);
                    }
                    int gatewayMemberCount = node.getDiscoveryService().getActiveSortedGatewayMembers().size();
                    if (gatewayMemberCount == 0) {
                        messageData.release();
                        return Mono.just(false);
                    }
                    int expectedMembersCount = Math.min(gatewayMemberCount, recipientIdCount);
                    int expectedRecipientCountPerMember = Math.max(1, recipientIdCount / expectedMembersCount);
                    SetMultimap<String, Long> userIdsByNodeId =
                            HashMultimap.create(expectedMembersCount, expectedRecipientCountPerMember);
                    for (RecipientAndNodeIds pair : pairs) {
                        for (String nodeId : pair.nodeIds) {
                            userIdsByNodeId.put(nodeId, pair.recipientId);
                        }
                    }
                    return forwardClientMessageToNodes(messageData, userIdsByNodeId);
                });
    }

    /**
     * @return true if at least one recipient has received the notification
     */
    private Mono<Boolean> forwardClientMessageByRecipientId(
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId) {
        return userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                .doOnError(t -> notificationData.release())
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Set<String> nodeIds = CollectionUtil.newSet(deviceTypeAndNodeIdMap.values());
                    if (nodeIds.isEmpty()) {
                        notificationData.release();
                        return Mono.just(false);
                    }
                    return forwardClientMessageToNodes(notificationData, nodeIds, recipientId);
                })
                .switchIfEmpty(Mono.fromCallable(() -> {
                    notificationData.release();
                    return false;
                }));
    }

    // Network transmission methods

    private Mono<Boolean> forwardClientMessageToNodes(ByteBuf messageData, SetMultimap<String, Long> recipientIdsByNodeId) {
        Multiset<String> nodeIds = recipientIdsByNodeId.keys();
        int size = nodeIds.size();
        if (size == 0) {
            messageData.release();
            return Mono.just(false);
        }
        if (size == 1) {
            String nodeId = nodeIds.iterator().next();
            return forwardClientMessageToNode(messageData, nodeId, recipientIdsByNodeId.get(nodeId));
        }
        List<Mono<Boolean>> monos = new ArrayList<>(size);
        messageData.retain(size);
        for (String nodeId : nodeIds) {
            Set<Long> recipientIds = recipientIdsByNodeId.get(nodeId);
            monos.add(forwardClientMessageToNode(messageData, nodeId, recipientIds));
        }
        return ReactorUtil.atLeastOneTrue(monos)
                .doFinally(signal -> messageData.release());
    }

    private Mono<Boolean> forwardClientMessageToNodes(
            @NotNull ByteBuf messageData,
            @NotNull Set<String> nodeIds,
            @NotNull Long recipientId) {
        int size = nodeIds.size();
        if (size == 0) {
            messageData.release();
            return Mono.just(false);
        }
        if (size == 1) {
            return forwardClientMessageToNode(messageData, nodeIds.iterator().next(), Set.of(recipientId));
        }
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                Set.of(recipientId));
        messageData.retain(size);
        List<Mono<Boolean>> monos = new ArrayList<>(size);
        for (String nodeId : nodeIds) {
            monos.add(node.getRpcService().requestResponse(nodeId, request));
        }
        return ReactorUtil.atLeastOneTrue(monos)
                .doFinally(signal -> messageData.release());
    }

    private Mono<Boolean> forwardClientMessageToNode(
            @NotNull ByteBuf messageData,
            @NotNull String nodeId,
            @NotNull Set<Long> recipients) {
        int size = recipients.size();
        if (size == 0) {
            messageData.release();
            return Mono.just(false);
        }
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                recipients);
        return node.getRpcService().requestResponse(nodeId, request);
    }

    // Logging

    private Mono<Boolean> tryLogNotification(Mono<Boolean> mono, TurmsNotification notification, int recipientCount) {
        if (!apiLoggingContext.shouldLogNotification(notification.getRelayedRequest().getKindCase())) {
            return mono;
        }
        return mono
                .doOnEach(signal -> {
                    if (!signal.isOnNext()) {
                        return;
                    }
                    boolean sent = Boolean.TRUE.equals(signal.get());
                    TracingContext context = signal.getContextView()
                            .getOrDefault(TracingContext.CTX_KEY_NAME, TracingContext.DEFAULT);
                    try (TracingCloseableContext ignored = context.asCloseable()) {
                        NotificationLogging.log(sent, notification, recipientCount);
                    }
                });
    }

    private record RecipientAndNodeIds(Long recipientId, Collection<String> nodeIds) {
    }

}