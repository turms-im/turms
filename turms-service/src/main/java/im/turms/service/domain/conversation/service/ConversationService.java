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

package im.turms.service.domain.conversation.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.property.env.service.business.conversation.ReadReceiptProperties;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
import im.turms.service.domain.conversation.po.GroupConversation;
import im.turms.service.domain.conversation.po.PrivateConversation;
import im.turms.service.domain.conversation.repository.GroupConversationRepository;
import im.turms.service.domain.conversation.repository.PrivateConversationRepository;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class ConversationService {

    private final GroupMemberService groupMemberService;

    private final GroupConversationRepository groupConversationRepository;
    private final PrivateConversationRepository privateConversationRepository;

    private boolean allowMoveReadDateForward;
    private boolean isReadReceiptEnabled;
    private boolean useServerTime;

    public ConversationService(
            TurmsPropertiesManager propertiesManager,
            GroupMemberService groupMemberService,
            GroupConversationRepository groupConversationRepository,
            PrivateConversationRepository privateConversationRepository) {
        this.groupMemberService = groupMemberService;
        this.groupConversationRepository = groupConversationRepository;
        this.privateConversationRepository = privateConversationRepository;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateProperties);
    }

    private void updateProperties(TurmsProperties properties) {
        ReadReceiptProperties readReceiptProperties = properties.getService()
                .getConversation()
                .getReadReceipt();
        allowMoveReadDateForward = readReceiptProperties.isAllowMoveReadDateForward();
        isReadReceiptEnabled = readReceiptProperties.isEnabled();
        useServerTime = readReceiptProperties.isUseServerTime();
    }

    // TODO: authenticate
    public Mono<Void> authAndUpsertGroupConversationReadDate(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable @PastOrPresent Date readDate) {
        if (!isReadReceiptEnabled) {
            return Mono.error(
                    ResponseException.get(ResponseStatusCode.UPDATING_READ_DATE_IS_DISABLED));
        }
        if (useServerTime) {
            readDate = new Date();
        }
        return upsertGroupConversationReadDate(groupId, memberId, readDate);
    }

    // TODO: authenticate
    public Mono<Void> authAndUpsertPrivateConversationReadDate(
            @NotNull Long ownerId,
            @NotNull Long targetId,
            @Nullable @PastOrPresent Date readDate) {
        if (!isReadReceiptEnabled) {
            return Mono.error(
                    ResponseException.get(ResponseStatusCode.UPDATING_READ_DATE_IS_DISABLED));
        }
        if (useServerTime) {
            readDate = new Date();
        }
        return upsertPrivateConversationReadDate(ownerId, targetId, readDate);
    }

    public Mono<Void> upsertGroupConversationReadDate(
            @NotNull Long groupId,
            @NotNull Long memberId,
            @Nullable @PastOrPresent Date readDate) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(memberId, "memberId");
            Validator.pastOrPresent(readDate, "readDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Date finalReadDate = readDate == null
                ? new Date()
                : readDate;
        return groupConversationRepository
                .upsert(groupId, memberId, finalReadDate, allowMoveReadDateForward)
                .onErrorResume(DuplicateKeyException.class,
                        e -> readDate == null
                                ? Mono.empty()
                                : Mono.error(ResponseException.get(
                                        ResponseStatusCode.MOVING_READ_DATE_FORWARD_IS_DISABLED)));
    }

    public Mono<Void> upsertGroupConversationsReadDate(
            @NotNull Set<GroupConversation.GroupConversionMemberKey> keys,
            @Nullable @PastOrPresent Date readDate) {
        try {
            Validator.notNull(keys, "keys");
            Validator.pastOrPresent(readDate, "readDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (keys.isEmpty()) {
            return Mono.empty();
        }
        if (readDate == null) {
            readDate = new Date();
        }
        Map<Long, List<Long>> groupIdToMemberIds = CollectionUtil.newMapWithExpectedSize(1);
        int keyCount = keys.size();
        for (GroupConversation.GroupConversionMemberKey key : keys) {
            groupIdToMemberIds.computeIfAbsent(key.getGroupId(), k -> new ArrayList<>(keyCount))
                    .add(key.getMemberId());
        }
        Set<Map.Entry<Long, List<Long>>> entries = groupIdToMemberIds.entrySet();
        List<Mono<Void>> upsertMonos = new ArrayList<>(entries.size());
        for (Map.Entry<Long, List<Long>> entry : entries) {
            Long groupId = entry.getKey();
            List<Long> memberIds = entry.getValue();
            upsertMonos.add(groupConversationRepository.upsert(groupId, memberIds, readDate));
        }
        return Mono.whenDelayError(upsertMonos);
    }

    public Mono<Void> upsertPrivateConversationReadDate(
            @NotNull Long ownerId,
            @NotNull Long targetId,
            @Nullable @PastOrPresent Date readDate) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(targetId, "targetId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return upsertPrivateConversationsReadDate(
                Set.of(new PrivateConversation.Key(ownerId, targetId)),
                readDate);
    }

    /**
     * @throws com.mongodb.DuplicateKeyException if {@code readDate} isn't after the read date in DB
     *                                           when "isAllowMoveReadDateForward" is enabled
     */
    public Mono<Void> upsertPrivateConversationsReadDate(
            @NotNull Set<PrivateConversation.Key> keys,
            @Nullable @PastOrPresent Date readDate) {
        try {
            Validator.notNull(keys, "keys");
            Validator.pastOrPresent(readDate, "readDate");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (keys.isEmpty()) {
            return Mono.empty();
        }
        Date finalReadDate = readDate == null
                ? new Date()
                : readDate;
        return privateConversationRepository.upsert(keys, finalReadDate, allowMoveReadDateForward)
                .onErrorResume(DuplicateKeyException.class,
                        e -> readDate == null
                                ? Mono.empty()
                                : Mono.error(ResponseException.get(
                                        ResponseStatusCode.MOVING_READ_DATE_FORWARD_IS_DISABLED)));
    }

    public Flux<GroupConversation> queryGroupConversations(@NotNull Collection<Long> groupIds) {
        try {
            Validator.notNull(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (groupIds.isEmpty()) {
            return Flux.empty();
        }
        return groupConversationRepository.findByIds(groupIds);
    }

    public Flux<PrivateConversation> queryPrivateConversationsByOwnerIds(
            @NotNull Set<Long> ownerIds) {
        try {
            Validator.notNull(ownerIds, "ownerIds");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (ownerIds.isEmpty()) {
            return Flux.empty();
        }
        return privateConversationRepository.findConversations(ownerIds);
    }

    public Flux<PrivateConversation> queryPrivateConversations(
            @NotNull Collection<Long> ownerIds,
            @NotNull Long targetId) {
        try {
            Validator.notNull(ownerIds, "ownerIds");
            Validator.notNull(targetId, "targetId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        int size = ownerIds.size();
        if (size == 0) {
            return Flux.empty();
        }
        Set<PrivateConversation.Key> keys = CollectionUtil.newSetWithExpectedSize(size);
        for (Long ownerId : ownerIds) {
            keys.add(new PrivateConversation.Key(ownerId, targetId));
        }
        return queryPrivateConversations(keys);
    }

    public Flux<PrivateConversation> queryPrivateConversations(
            @NotNull Set<PrivateConversation.Key> keys) {
        try {
            Validator.notNull(keys, "keys");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        if (keys.isEmpty()) {
            return Flux.empty();
        }
        return privateConversationRepository.findByIds(keys);
    }

    public Mono<DeleteResult> deletePrivateConversations(
            @NotNull Set<PrivateConversation.Key> keys) {
        try {
            Validator.notNull(keys, "keys");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (keys.isEmpty()) {
            return OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT;
        }
        return privateConversationRepository.deleteByIds(keys);
    }

    public Mono<DeleteResult> deletePrivateConversations(
            @NotNull Set<Long> userIds,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (userIds.isEmpty()) {
            return OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT;
        }
        return privateConversationRepository.deleteConversationsByOwnerIds(userIds, session);
    }

    public Mono<DeleteResult> deleteGroupConversations(
            @NotNull Set<Long> groupIds,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (groupIds.isEmpty()) {
            return OperationResultPublisherPool.ACKNOWLEDGED_DELETE_RESULT;
        }
        return groupConversationRepository.deleteByIds(groupIds, session);
    }

    public Mono<Void> deleteGroupMemberConversations(
            @NotNull Collection<Long> userIds,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Mono<Void> delete = Mono.empty();
        for (Long userId : userIds) {
            delete = delete.then(groupMemberService.queryUserJoinedGroupIds(userId)
                    .collect(CollectorUtil.toChunkedList())
                    // it is expected there are some cases that the user just joined a group,
                    // and sent a message to the group after "queryUserJoinedGroupIds", meaning
                    // there may some deleted members' conversations that should be deleted
                    // but not deleted in the following "deleteGroupConversations".
                    // TODO: support detecting deleted members' conversations when querying
                    // conversations in a efficient way.
                    .flatMap(groupIds -> groupConversationRepository
                            .deleteMemberConversations(groupIds, userId, session)))
                    .then();
        }
        return delete;
    }

}