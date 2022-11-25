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

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.domain.notification.rpc.SendNotificationRequest;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.proto.ProtoEncoder;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.reactor.PublisherUtil;
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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

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
        return forwardNotification(notification, ProtoEncoder.getDirectByteBuffer(notification), recipientIds, Collections.emptySet());
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notification,
            @NotNull Set<Long> recipientIds,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        if (recipientIds.isEmpty()) {
            return PublisherPool.TRUE;
        }
        ByteBuf notificationData = ProtoEncoder.getDirectByteBuffer(notification);
        int recipientCount = recipientIds.size();
        Mono<Boolean> mono = recipientCount == 1
                ? forwardClientMessageByRecipientId(notificationData, recipientIds.iterator().next(), excludedUserSessionIds)
                : forwardClientMessageByRecipientIds(notificationData, recipientIds, excludedUserSessionIds);
        return tryLogNotification(mono, notification, recipientCount);
    }

    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds) {
        return forwardNotification(notificationForLogging, notificationData, recipientIds, Collections.emptySet());
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        if (recipientIds.isEmpty()) {
            notificationData.release();
            return PublisherPool.TRUE;
        }
        int recipientCount = recipientIds.size();
        Mono<Boolean> mono = recipientCount == 1
                ? forwardClientMessageByRecipientId(notificationData, recipientIds.iterator().next(), excludedUserSessionIds)
                : forwardClientMessageByRecipientIds(notificationData, recipientIds, excludedUserSessionIds);
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
        return userStatusService.getDeviceTypeToNodeIdMapByUserId(recipientId)
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
                        return PublisherPool.FALSE;
                    }
                    Mono<Boolean> mono = forwardClientMessageToNodes(notificationData,
                            nodeIds,
                            recipientId,
                            Collections.emptySet(),
                            excludedDeviceType);
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
            @NotNull Set<Long> recipientIds,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        if (recipientIds.isEmpty()) {
            messageData.release();
            return PublisherPool.TRUE;
        }
        int recipientIdCount = recipientIds.size();
        if (recipientIdCount == 1) {
            return forwardClientMessageByRecipientId(messageData, recipientIds.iterator().next(), excludedUserSessionIds);
        }
        List<Mono<RecipientAndNodeIds>> monos = new ArrayList<>(recipientIdCount);
        for (Long recipientId : recipientIds) {
            monos.add(userStatusService.getDeviceTypeToNodeIdMapByUserId(recipientId)
                    .map(map -> new RecipientAndNodeIds(recipientId, map.values())));
        }
        return Flux.merge(monos)
                .doOnError(t -> messageData.release())
                .collect(CollectorUtil.toList(recipientIdCount))
                .flatMap(pairs -> {
                    if (pairs.isEmpty()) {
                        messageData.release();
                        return PublisherPool.FALSE;
                    }
                    int gatewayMemberCount = node.getDiscoveryService().getActiveSortedGatewayMembers().size();
                    if (gatewayMemberCount == 0) {
                        messageData.release();
                        return PublisherPool.FALSE;
                    }
                    int expectedMembersCount = Math.min(gatewayMemberCount, recipientIdCount);
                    int expectedRecipientCountPerMember = Math.max(1, recipientIdCount / expectedMembersCount);
                    Map<String, Set<Long>> nodeIdToUserIds = CollectionUtil.newMapWithExpectedSize(expectedMembersCount);
                    for (RecipientAndNodeIds pair : pairs) {
                        for (String nodeId : pair.nodeIds) {
                            nodeIdToUserIds.computeIfAbsent(nodeId, key ->
                                            CollectionUtil.newSetWithExpectedSize(expectedRecipientCountPerMember))
                                    .add(pair.recipientId);
                        }
                    }
                    return forwardClientMessageToNodes(messageData, nodeIdToUserIds, excludedUserSessionIds);
                });
    }

    /**
     * @return true if at least one recipient has received the notification
     */
    private Mono<Boolean> forwardClientMessageByRecipientId(
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        return userStatusService.getDeviceTypeToNodeIdMapByUserId(recipientId)
                .doOnError(t -> notificationData.release())
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Set<String> nodeIds = CollectionUtil.newSet(deviceTypeAndNodeIdMap.values());
                    if (nodeIds.isEmpty()) {
                        notificationData.release();
                        return PublisherPool.FALSE;
                    }
                    return forwardClientMessageToNodes(notificationData, nodeIds, recipientId, excludedUserSessionIds, null);
                })
                .switchIfEmpty(Mono.fromCallable(() -> {
                    notificationData.release();
                    return false;
                }));
    }

    // Network transmission methods

    private Mono<Boolean> forwardClientMessageToNodes(ByteBuf messageData,
                                                      Map<String, Set<Long>> nodeIdToRecipientIds,
                                                      Set<UserSessionId> excludedUserSessionIds) {
        Set<String> nodeIds = nodeIdToRecipientIds.keySet();
        int size = nodeIds.size();
        if (size == 0) {
            messageData.release();
            return PublisherPool.FALSE;
        }
        if (size == 1) {
            String nodeId = nodeIds.iterator().next();
            return forwardClientMessageToNode(messageData,
                    nodeId,
                    nodeIdToRecipientIds.get(nodeId),
                    excludedUserSessionIds,
                    null);
        }
        List<Mono<Boolean>> monos = new ArrayList<>(size);
        messageData.retain(size);
        for (String nodeId : nodeIds) {
            Set<Long> recipientIds = nodeIdToRecipientIds.get(nodeId);
            monos.add(forwardClientMessageToNode(messageData,
                    nodeId,
                    recipientIds,
                    excludedUserSessionIds,
                    null));
        }
        return PublisherUtil.atLeastOneTrue(monos)
                .doFinally(signal -> messageData.release());
    }

    private Mono<Boolean> forwardClientMessageToNodes(
            @NotNull ByteBuf messageData,
            @NotNull Set<String> nodeIds,
            @NotNull Long recipientId,
            @NotNull Set<UserSessionId> excludedUserSessionIds,
            @Nullable DeviceType excludedDeviceType) {
        int size = nodeIds.size();
        if (size == 0) {
            messageData.release();
            return PublisherPool.FALSE;
        }
        if (size == 1) {
            return forwardClientMessageToNode(messageData,
                    nodeIds.iterator().next(),
                    Set.of(recipientId),
                    excludedUserSessionIds,
                    excludedDeviceType);
        }
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                Set.of(recipientId),
                excludedUserSessionIds,
                excludedDeviceType);
        messageData.retain(size);
        List<Mono<Boolean>> monos = new ArrayList<>(size);
        for (String nodeId : nodeIds) {
            monos.add(node.getRpcService().requestResponse(nodeId, request));
        }
        return PublisherUtil.atLeastOneTrue(monos)
                .doFinally(signal -> messageData.release());
    }

    private Mono<Boolean> forwardClientMessageToNode(
            @NotNull ByteBuf messageData,
            @NotNull String nodeId,
            @NotNull Set<Long> recipients,
            @NotNull Set<UserSessionId> excludedUserSessionIds,
            @Nullable DeviceType excludedDeviceType) {
        int size = recipients.size();
        if (size == 0) {
            messageData.release();
            return PublisherPool.FALSE;
        }
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                recipients,
                excludedUserSessionIds,
                excludedDeviceType);
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