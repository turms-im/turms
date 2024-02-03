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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;

import io.netty.buffer.ByteBuf;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.constant.DeviceType;
import im.turms.server.common.access.client.dto.notification.TurmsNotification;
import im.turms.server.common.domain.notification.rpc.SendNotificationRequest;
import im.turms.server.common.domain.session.bo.UserDeviceSessionInfo;
import im.turms.server.common.domain.session.bo.UserSessionId;
import im.turms.server.common.domain.session.service.UserStatusService;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.proto.ProtoEncoder;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.tracing.TracingCloseableContext;
import im.turms.server.common.infra.tracing.TracingContext;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.infra.logging.ApiLoggingContext;
import im.turms.service.infra.logging.NotificationLogging;

/**
 * @author James Chen
 * @implNote 1. All operations that send the outbound message buffer to other servers need to ensure
 *           that the buffer will be released by 1.
 *           <p>
 *           2. To keep operations as simple as possible, all operations don't support the
 *           cancellation operation. In other words, it will leak memory if a cancellation operation
 *           occurs.
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class OutboundMessageService {

    private final Node node;
    private final ApiLoggingContext apiLoggingContext;
    private final UserStatusService userStatusService;

    public OutboundMessageService(
            Node node,
            ApiLoggingContext apiLoggingContext,
            UserStatusService userStatusService) {
        this.node = node;
        this.apiLoggingContext = apiLoggingContext;
        this.userStatusService = userStatusService;
    }

    /**
     * @return offline recipient IDs
     */
    public Mono<Set<Long>> forwardNotification(
            @NotNull TurmsNotification notification,
            @NotNull Set<Long> recipientIds) {
        return forwardNotification(notification,
                ProtoEncoder.getDirectByteBuffer(notification),
                recipientIds,
                Collections.emptySet());
    }

    /**
     * @return offline recipient IDs
     */
    public Mono<Set<Long>> forwardNotification(
            @NotNull TurmsNotification notification,
            @NotNull Set<Long> recipientIds,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        int recipientCount = recipientIds.size();
        if (0 == recipientCount) {
            return PublisherPool.emptySet();
        }
        ByteBuf notificationData = ProtoEncoder.getDirectByteBuffer(notification);
        Mono<Set<Long>> forwardClientMessage = 1 == recipientCount
                ? forwardClientMessageByRecipientId(notificationData,
                        recipientIds.iterator()
                                .next(),
                        excludedUserSessionIds)
                : forwardClientMessageByRecipientIds(notificationData,
                        recipientIds,
                        excludedUserSessionIds);
        return tryLogNotification(forwardClientMessage, notification, recipientCount);
    }

    /**
     * @return offline recipient IDs
     */
    public Mono<Set<Long>> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds) {
        return forwardNotification(notificationForLogging,
                notificationData,
                recipientIds,
                Collections.emptySet());
    }

    /**
     * @return offline recipient IDs
     */
    public Mono<Set<Long>> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        int recipientCount = recipientIds.size();
        if (0 == recipientCount) {
            notificationData.release();
            return PublisherPool.emptySet();
        }
        Mono<Set<Long>> forwardClientMessage = 1 == recipientCount
                ? forwardClientMessageByRecipientId(notificationData,
                        recipientIds.iterator()
                                .next(),
                        excludedUserSessionIds)
                : forwardClientMessageByRecipientIds(notificationData,
                        recipientIds,
                        excludedUserSessionIds);
        return tryLogNotification(forwardClientMessage, notificationForLogging, recipientCount);
    }

    /**
     * @return offline recipient IDs
     */
    public Mono<Set<Long>> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId,
            @Nullable DeviceType excludedDeviceType) {
        return userStatusService.getUserSessionsStatus(recipientId)
                .doOnError(t -> notificationData.release())
                .flatMap(sessionsStatus -> {
                    if (sessionsStatus.isOffline()) {
                        notificationData.release();
                        return Mono.just(Set.of(recipientId));
                    }
                    Map<DeviceType, UserDeviceSessionInfo> deviceTypeToSessionInfo =
                            sessionsStatus.getDeviceTypeToSessionInfo();
                    Set<String> nodeIds =
                            CollectionUtil.newSetWithExpectedSize(deviceTypeToSessionInfo.size());
                    for (Map.Entry<DeviceType, UserDeviceSessionInfo> entry : deviceTypeToSessionInfo
                            .entrySet()) {
                        DeviceType deviceType = entry.getKey();
                        if (deviceType != excludedDeviceType) {
                            UserDeviceSessionInfo sessionInfo = entry.getValue();
                            if (sessionInfo.isActive()) {
                                nodeIds.add(sessionInfo.getNodeId());
                            }
                        }
                    }
                    if (nodeIds.isEmpty()) {
                        notificationData.release();
                        return Mono.just(Set.of(recipientId));
                    }
                    Mono<Set<Long>> forwardClientMessage =
                            forwardClientMessageToNodes(notificationData,
                                    nodeIds,
                                    recipientId,
                                    Collections.emptySet(),
                                    excludedDeviceType);
                    return tryLogNotification(forwardClientMessage, notificationForLogging, 1);
                })
                .switchIfEmpty(Mono.fromCallable(() -> {
                    notificationData.release();
                    return Set.of(recipientId);
                }));
    }

    /**
     * @return offline recipient IDs
     */
    private Mono<Set<Long>> forwardClientMessageByRecipientIds(
            @NotNull ByteBuf messageData,
            @NotNull Set<Long> recipientIds,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        if (recipientIds.isEmpty()) {
            messageData.release();
            return PublisherPool.emptySet();
        }
        int recipientIdCount = recipientIds.size();
        if (recipientIdCount == 1) {
            return forwardClientMessageByRecipientId(messageData,
                    recipientIds.iterator()
                            .next(),
                    excludedUserSessionIds);
        }
        List<Mono<RecipientAndNodeIds>> getUserSessionsStatusMonos =
                new ArrayList<>(recipientIdCount);
        for (Long recipientId : recipientIds) {
            getUserSessionsStatusMonos.add(userStatusService.getUserSessionsStatus(recipientId)
                    .flatMap(sessionsStatus -> sessionsStatus.isOffline()
                            ? Mono.empty()
                            : Mono.just(new RecipientAndNodeIds(
                                    recipientId,
                                    sessionsStatus.getActiveNodeIds()))));
        }
        return Flux.merge(getUserSessionsStatusMonos)
                .doOnError(t -> messageData.release())
                .collect(CollectorUtil.toList(recipientIdCount))
                .flatMap(pairs -> {
                    if (pairs.isEmpty()) {
                        messageData.release();
                        return Mono.just(recipientIds);
                    }
                    int gatewayMemberCount = node.getDiscoveryService()
                            .getActiveSortedGatewayMembers()
                            .size();
                    if (gatewayMemberCount == 0) {
                        messageData.release();
                        return Mono.just(recipientIds);
                    }
                    int expectedMembersCount = Math.min(gatewayMemberCount, recipientIdCount);
                    int expectedRecipientCountPerMember =
                            Math.max(1, recipientIdCount / expectedMembersCount);
                    Map<String, Set<Long>> nodeIdToUserIds =
                            CollectionUtil.newMapWithExpectedSize(expectedMembersCount);
                    for (RecipientAndNodeIds pair : pairs) {
                        for (String nodeId : pair.nodeIds) {
                            nodeIdToUserIds
                                    .computeIfAbsent(nodeId,
                                            key -> CollectionUtil.newSetWithExpectedSize(
                                                    expectedRecipientCountPerMember))
                                    .add(pair.recipientId);
                        }
                    }
                    return forwardClientMessageToNodes(messageData,
                            nodeIdToUserIds,
                            excludedUserSessionIds);
                });
    }

    /**
     * @return offline user IDs
     * @implNote Return {@code Set<Long>} instead of {@code Long} for consistency.
     */
    private Mono<Set<Long>> forwardClientMessageByRecipientId(
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId,
            @NotNull Set<UserSessionId> excludedUserSessionIds) {
        return userStatusService.getUserSessionsStatus(recipientId)
                .doOnError(t -> notificationData.release())
                .flatMap(sessionsStatus -> {
                    if (sessionsStatus.isOffline()) {
                        notificationData.release();
                        return Mono.just(Set.of(recipientId));
                    }
                    Set<String> activeNodeIds = sessionsStatus.getActiveNodeIds();
                    if (activeNodeIds.isEmpty()) {
                        notificationData.release();
                        return Mono.just(Set.of(recipientId));
                    }
                    return forwardClientMessageToNodes(notificationData,
                            activeNodeIds,
                            recipientId,
                            excludedUserSessionIds,
                            null);
                })
                .switchIfEmpty(Mono.fromCallable(() -> {
                    notificationData.release();
                    return Set.of(recipientId);
                }));
    }

    // Network transmission methods

    private Mono<Set<Long>> forwardClientMessageToNodes(
            ByteBuf messageData,
            Map<String, Set<Long>> nodeIdToRecipientIds,
            Set<UserSessionId> excludedUserSessionIds) {
        Set<String> nodeIds = nodeIdToRecipientIds.keySet();
        int nodeIdCount = nodeIds.size();
        if (nodeIdCount == 0) {
            messageData.release();
            return PublisherPool.emptySet();
        }
        if (nodeIdCount == 1) {
            String nodeId = nodeIds.iterator()
                    .next();
            return forwardClientMessageToNode(messageData,
                    nodeId,
                    nodeIdToRecipientIds.get(nodeId),
                    excludedUserSessionIds,
                    null);
        }
        List<Mono<Set<Long>>> forwardClientMessageMonos = new ArrayList<>(nodeIdCount);
        messageData.retain(nodeIdCount);
        for (Map.Entry<String, Set<Long>> nodeIdAndRecipientIds : nodeIdToRecipientIds.entrySet()) {
            forwardClientMessageMonos.add(forwardClientMessageToNode(messageData,
                    nodeIdAndRecipientIds.getKey(),
                    nodeIdAndRecipientIds.getValue(),
                    excludedUserSessionIds,
                    null));
        }
        return collectOfflineRecipientIds(forwardClientMessageMonos)
                .doFinally(signal -> messageData.release());
    }

    private Mono<Set<Long>> forwardClientMessageToNodes(
            @NotNull ByteBuf messageData,
            @NotNull Set<String> nodeIds,
            @NotNull Long recipientId,
            @NotNull Set<UserSessionId> excludedUserSessionIds,
            @Nullable DeviceType excludedDeviceType) {
        int nodeCount = nodeIds.size();
        if (nodeCount == 0) {
            messageData.release();
            return Mono.just(Set.of(recipientId));
        }
        if (nodeCount == 1) {
            return forwardClientMessageToNode(messageData,
                    nodeIds.iterator()
                            .next(),
                    Set.of(recipientId),
                    excludedUserSessionIds,
                    excludedDeviceType);
        }
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                Set.of(recipientId),
                excludedUserSessionIds,
                excludedDeviceType);
        messageData.retain(nodeCount);
        List<Mono<Set<Long>>> sendNotificationRequests = new ArrayList<>(nodeCount);
        for (String nodeId : nodeIds) {
            sendNotificationRequests.add(node.getRpcService()
                    .requestResponse(nodeId, request));
        }
        return collectOfflineRecipientIds(sendNotificationRequests)
                .doFinally(signal -> messageData.release());
    }

    /**
     * @return offline user IDs
     */
    private Mono<Set<Long>> forwardClientMessageToNode(
            @NotNull ByteBuf messageData,
            @NotNull String nodeId,
            @NotNull Set<Long> recipients,
            @NotNull Set<UserSessionId> excludedUserSessionIds,
            @Nullable DeviceType excludedDeviceType) {
        if (recipients.isEmpty()) {
            messageData.release();
            return PublisherPool.emptySet();
        }
        SendNotificationRequest request = new SendNotificationRequest(
                messageData,
                recipients,
                excludedUserSessionIds,
                excludedDeviceType);
        return node.getRpcService()
                .requestResponse(nodeId, request);
    }

    private Mono<Set<Long>> collectOfflineRecipientIds(List<Mono<Set<Long>>> monos) {
        return Flux.merge(monos)
                .collect(CollectorUtil.toList(monos.size()))
                .map(CollectionUtil::union);
    }

    // Logging

    private Mono<Set<Long>> tryLogNotification(
            Mono<Set<Long>> mono,
            TurmsNotification notification,
            int recipientCount) {
        if (!apiLoggingContext.shouldLogNotification(notification.getRelayedRequest()
                .getKindCase())) {
            return mono;
        }
        return mono.doOnEach(signal -> {
            if (!signal.isOnNext()) {
                return;
            }
            int offlineRecipientCount = CollectionUtil.getSize(signal.get());
            try (TracingCloseableContext ignored =
                    TracingContext.getCloseableContext(signal.getContextView())) {
                NotificationLogging
                        .log(recipientCount, recipientCount - offlineRecipientCount, notification);
            }
        });
    }

    private record RecipientAndNodeIds(
            Long recipientId,
            Collection<String> nodeIds
    ) {
    }

}