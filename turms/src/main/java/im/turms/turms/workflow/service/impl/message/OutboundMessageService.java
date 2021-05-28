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
import com.google.common.collect.Multiset;
import com.google.common.collect.SetMultimap;
import im.turms.common.constant.DeviceType;
import im.turms.common.model.dto.notification.TurmsNotification;
import im.turms.common.model.dto.request.TurmsRequest;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.logging.ClientApiLogging;
import im.turms.server.common.logging.LoggingRequestUtil;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.gateway.clientapi.ClientApiLoggingProperties;
import im.turms.server.common.property.env.service.env.clientapi.property.LoggingRequestProperties;
import im.turms.server.common.rpc.request.SendNotificationRequest;
import im.turms.server.common.service.session.UserStatusService;
import im.turms.server.common.util.CollectorUtil;
import im.turms.server.common.util.MapUtil;
import im.turms.server.common.util.ProtoUtil;
import im.turms.server.common.util.ReactorUtil;
import io.netty.buffer.ByteBuf;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Service
@Log4j2
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class OutboundMessageService {

    private final Node node;
    private final UserStatusService userStatusService;
    private final Map<TurmsRequest.KindCase, LoggingRequestProperties> supportedLoggingNotificationProperties;

    public OutboundMessageService(Node node, TurmsPropertiesManager propertiesManager, UserStatusService userStatusService) {
        this.node = node;
        this.userStatusService = userStatusService;
        ClientApiLoggingProperties loggingProperties = propertiesManager.getLocalProperties().getGateway().getClientApi().getLogging();
        supportedLoggingNotificationProperties = LoggingRequestUtil.getSupportedLoggingRequestProperties(
                loggingProperties.getIncludedNotificationCategories(),
                loggingProperties.getIncludedNotifications(),
                loggingProperties.getExcludedNotificationCategories(),
                loggingProperties.getExcludedNotificationTypes());
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notification,
            @NotNull Set<Long> recipientIds) {
        return forwardNotification(notification, ProtoUtil.getDirectByteBuffer(notification), recipientIds);
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
    public Mono<Boolean> forwardNotification(
            @NotNull TurmsNotification notificationForLogging,
            @NotNull ByteBuf notificationData,
            @NotNull Set<Long> recipientIds) {
        if (recipientIds.isEmpty()) {
            return Mono.just(true);
        } else {
            Mono<Boolean> mono = recipientIds.size() == 1
                    ? forwardClientMessageByRecipientId(notificationData, recipientIds.iterator().next())
                    : forwardClientMessageByRecipientIds(notificationData, recipientIds);
            return tryLogNotification(mono, notificationForLogging);
        }
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
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Set<String> nodeIds = new HashSet<>(MapUtil.getCapability(deviceTypeAndNodeIdMap.size()));
                    for (Map.Entry<DeviceType, String> entry : deviceTypeAndNodeIdMap.entrySet()) {
                        DeviceType deviceType = entry.getKey();
                        if (deviceType != excludedDeviceType) {
                            nodeIds.add(entry.getValue());
                        }
                    }
                    if (nodeIds.isEmpty()) {
                        return Mono.just(false);
                    }
                    Mono<Boolean> mono = forwardClientMessageToNodes(notificationData, nodeIds, recipientId);
                    return tryLogNotification(mono, notificationForLogging);
                })
                .defaultIfEmpty(false);
    }

    /**
     * @return true if recipientIds is empty, or at least one recipient has received the notification
     */
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
            List<Mono<RecipientAndNodeIds>> monos = new ArrayList<>(recipientIdsSize);
            for (Long recipientId : recipientIds) {
                monos.add(userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                        .map(map -> new RecipientAndNodeIds(recipientId, map.values())));
            }
            return Flux.merge(monos)
                    .collect(CollectorUtil.toList(recipientIdsSize))
                    .flatMap(pairs -> {
                        int expectedMembersCount =
                                Math.min(node.getDiscoveryService().getActiveSortedGatewayMemberList().size(), recipientIdsSize);
                        int expectedRecipientCountPerMember = Math.min(1, recipientIdsSize / expectedMembersCount);
                        SetMultimap<String, Long> userIdsByNodeId =
                                HashMultimap.create(expectedMembersCount, expectedRecipientCountPerMember);
                        for (RecipientAndNodeIds pair : pairs) {
                            for (String nodeId : pair.getNodeIds()) {
                                userIdsByNodeId.put(nodeId, pair.getRecipientId());
                            }
                        }
                        return forwardClientMessageToNodes(messageData, userIdsByNodeId);
                    });
        }
    }

    /**
     * @return true if at least one recipient has received the notification
     */
    private Mono<Boolean> forwardClientMessageByRecipientId(
            @NotNull ByteBuf notificationData,
            @NotNull Long recipientId) {
        return userStatusService.getDeviceAndNodeIdMapByUserId(recipientId)
                .flatMap(deviceTypeAndNodeIdMap -> {
                    Set<String> nodeIds = new HashSet<>(deviceTypeAndNodeIdMap.values());
                    if (nodeIds.isEmpty()) {
                        return Mono.just(false);
                    }
                    return forwardClientMessageToNodes(notificationData, nodeIds, recipientId);
                })
                .defaultIfEmpty(false);
    }

    // Network transmission methods

    private Mono<Boolean> forwardClientMessageToNodes(ByteBuf messageData, SetMultimap<String, Long> recipientIdsByNodeId) {
        Multiset<String> nodeIds = recipientIdsByNodeId.keys();
        int size = nodeIds.size();
        if (size == 0) {
            return Mono.just(false);
        } else if (size == 1) {
            String nodeId = nodeIds.iterator().next();
            return forwardClientMessageToNode(messageData, nodeId, recipientIdsByNodeId.get(nodeId));
        } else {
            List<Mono<Boolean>> monos = new ArrayList<>(size);
            messageData.retain(size);
            for (String nodeId : nodeIds) {
                Set<Long> recipientIds = recipientIdsByNodeId.get(nodeId);
                monos.add(forwardClientMessageToNode(messageData, nodeId, recipientIds));
            }
            return ReactorUtil.atLeastOneTrue(monos)
                    .doFinally(ignored -> messageData.release());
        }
    }

    private Mono<Boolean> forwardClientMessageToNodes(
            @NotNull ByteBuf messageData,
            @NotNull Set<String> nodeIds,
            @NotNull Long recipientId) {
        int size = nodeIds.size();
        if (size == 0) {
            return Mono.just(false);
        } else {
            if (size == 1) {
                return forwardClientMessageToNode(messageData, nodeIds.iterator().next(), Set.of(recipientId));
            } else {
                SendNotificationRequest request = new SendNotificationRequest(
                        messageData,
                        Set.of(recipientId));
                messageData.retain(size);
                List<Mono<Boolean>> monos = new ArrayList<>(size);
                for (String nodeId : nodeIds) {
                    monos.add(node.getRpcService().requestResponse(nodeId, request));
                }
                return ReactorUtil.atLeastOneTrue(monos)
                        .doFinally(ignored -> messageData.release());
            }
        }
    }

    private Mono<Boolean> forwardClientMessageToNode(
            @NotNull ByteBuf messageData,
            @NotNull String nodeId,
            @NotNull Set<Long> recipients) {
        int size = recipients.size();
        if (size == 0) {
            return Mono.just(false);
        } else {
            SendNotificationRequest request = new SendNotificationRequest(
                    messageData,
                    recipients);
            return node.getRpcService().requestResponse(nodeId, request);
        }
    }

    // Logging

    private Mono<Boolean> tryLogNotification(Mono<Boolean> mono, TurmsNotification notification) {
        if (LoggingRequestUtil.shouldLog(notification.getRelayedRequest().getKindCase(), supportedLoggingNotificationProperties)) {
            return mono
                    .doOnSuccess(sent -> {
                        String message = sent ? "Sent: " : "Unsent: ";
                        ClientApiLogging.log(message + notification);
                    });
        } else {
            return mono;
        }
    }

    @Data
    private static class RecipientAndNodeIds {
        private final Long recipientId;
        private final Collection<String> nodeIds;
    }

}