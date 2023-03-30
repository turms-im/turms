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

package im.turms.service.domain.group.service;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.infra.cluster.service.idgen.ServiceType;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.bo.GroupUpdateStrategy;
import im.turms.service.domain.group.po.GroupType;
import im.turms.service.domain.group.repository.GroupTypeRepository;
import im.turms.service.storage.mongo.OperationResultPublisherPool;

import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_ID;
import static im.turms.server.common.domain.group.constant.GroupConst.DEFAULT_GROUP_TYPE_NAME;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupTypeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GroupTypeService.class);

    private final Node node;
    private final Map<Long, GroupType> idToGroupType = new ConcurrentHashMap<>(16);
    private final GroupTypeRepository groupTypeRepository;

    public GroupTypeService(Node node, GroupTypeRepository groupTypeRepository) {
        this.node = node;
        this.groupTypeRepository = groupTypeRepository;
        initGroupTypes();
    }

    public void initGroupTypes() {
        idToGroupType.putIfAbsent(DEFAULT_GROUP_TYPE_ID,
                new GroupType(
                        DEFAULT_GROUP_TYPE_ID,
                        DEFAULT_GROUP_TYPE_NAME,
                        500,
                        GroupInvitationStrategy.OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL,
                        GroupJoinStrategy.INVITATION,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        false,
                        true,
                        true,
                        true));
        groupTypeRepository.watch(FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    GroupType groupType = event.getFullDocument();
                    switch (operationType) {
                        case INSERT, UPDATE, REPLACE ->
                            idToGroupType.put(groupType.getId(), groupType);
                        case DELETE -> {
                            long groupTypeId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            idToGroupType.remove(groupTypeId);
                        }
                        case INVALIDATE -> idToGroupType.keySet()
                                .removeIf(id -> !id.equals(DEFAULT_GROUP_TYPE_ID));
                        default -> LOGGER.fatal("Detected an illegal operation on the collection \""
                                + GroupType.COLLECTION_NAME
                                + "\" in the change stream event: {}", event);
                    }
                })
                .onErrorContinue((throwable, o) -> LOGGER.error(
                        "Caught an error while processing the change stream event ({}) of the collection: \""
                                + GroupType.COLLECTION_NAME
                                + "\"",
                        o,
                        throwable))
                .subscribe();
        groupTypeRepository.findAll()
                .doOnNext(groupType -> idToGroupType.put(groupType.getId(), groupType))
                .subscribe(null,
                        t -> LOGGER.error("Caught an error while finding all group types", t));
    }

    public GroupType getDefaultGroupType() {
        return idToGroupType.get(DEFAULT_GROUP_TYPE_ID);
    }

    public Flux<GroupType> queryGroupTypes(@Nullable Integer page, @Nullable Integer size) {
        return groupTypeRepository.findAll(page, size)
                // TODO: respect page and size
                .concatWithValues(getDefaultGroupType());
    }

    public Mono<GroupType> addGroupType(
            @NotNull @NoWhitespace String name,
            @NotNull @Min(1) Integer groupSizeLimit,
            @NotNull GroupInvitationStrategy groupInvitationStrategy,
            @NotNull GroupJoinStrategy groupJoinStrategy,
            @NotNull GroupUpdateStrategy groupInfoUpdateStrategy,
            @NotNull GroupUpdateStrategy memberInfoUpdateStrategy,
            @NotNull Boolean guestSpeakable,
            @NotNull Boolean selfInfoUpdatable,
            @NotNull Boolean enableReadReceipt,
            @NotNull Boolean messageEditable) {
        try {
            Validator.notNull(name, "name");
            Validator.noWhitespace(name, "name");
            Validator.notNull(groupSizeLimit, "groupSizeLimit");
            Validator.min(groupSizeLimit, "groupSizeLimit", 1);
            Validator.notNull(groupInvitationStrategy, "groupInvitationStrategy");
            Validator.notNull(groupJoinStrategy, "groupJoinStrategy");
            Validator.notNull(groupInfoUpdateStrategy, "groupInfoUpdateStrategy");
            Validator.notNull(memberInfoUpdateStrategy, "memberInfoUpdateStrategy");
            Validator.notNull(guestSpeakable, "guestSpeakable");
            Validator.notNull(selfInfoUpdatable, "selfInfoUpdatable");
            Validator.notNull(enableReadReceipt, "enableReadReceipt");
            Validator.notNull(messageEditable, "messageEditable");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        Long id = node.nextLargeGapId(ServiceType.GROUP_TYPE);
        GroupType groupType = new GroupType(
                id,
                name,
                groupSizeLimit,
                groupInvitationStrategy,
                groupJoinStrategy,
                groupInfoUpdateStrategy,
                memberInfoUpdateStrategy,
                guestSpeakable,
                selfInfoUpdatable,
                enableReadReceipt,
                messageEditable);
        idToGroupType.put(id, groupType);
        return groupTypeRepository.insert(groupType)
                .thenReturn(groupType);
    }

    public Mono<UpdateResult> updateGroupTypes(
            @NotEmpty Set<Long> ids,
            @Nullable @NoWhitespace String name,
            @Nullable @Min(1) Integer groupSizeLimit,
            @Nullable GroupInvitationStrategy groupInvitationStrategy,
            @Nullable GroupJoinStrategy groupJoinStrategy,
            @Nullable GroupUpdateStrategy groupInfoUpdateStrategy,
            @Nullable GroupUpdateStrategy memberInfoUpdateStrategy,
            @Nullable Boolean guestSpeakable,
            @Nullable Boolean selfInfoUpdatable,
            @Nullable Boolean enableReadReceipt,
            @Nullable Boolean messageEditable) {
        try {
            Validator.notEmpty(ids, "ids");
            Validator.noWhitespace(name, "name");
            Validator.min(groupSizeLimit, "groupSizeLimit", 1);
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(name,
                groupSizeLimit,
                groupInvitationStrategy,
                groupJoinStrategy,
                groupInfoUpdateStrategy,
                memberInfoUpdateStrategy,
                guestSpeakable,
                selfInfoUpdatable,
                enableReadReceipt,
                messageEditable)) {
            return OperationResultPublisherPool.ACKNOWLEDGED_UPDATE_RESULT;
        }
        return groupTypeRepository.updateTypes(ids,
                name,
                groupSizeLimit,
                groupInvitationStrategy,
                groupJoinStrategy,
                groupInfoUpdateStrategy,
                memberInfoUpdateStrategy,
                guestSpeakable,
                selfInfoUpdatable,
                enableReadReceipt,
                messageEditable);
    }

    public Mono<DeleteResult> deleteGroupTypes(@Nullable Set<Long> groupTypeIds) {
        if (groupTypeIds != null && groupTypeIds.contains(DEFAULT_GROUP_TYPE_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The default group type cannot be deleted"));
        }
        return groupTypeRepository.deleteByIds(groupTypeIds)
                .doOnNext(result -> {
                    if (groupTypeIds != null) {
                        for (Long id : groupTypeIds) {
                            idToGroupType.remove(id);
                        }
                    } else {
                        idToGroupType.keySet()
                                .removeIf(id -> !id.equals(DEFAULT_GROUP_TYPE_ID));
                    }
                });
    }

    public Mono<GroupType> queryGroupType(@NotNull Long groupTypeId) {
        try {
            Validator.notNull(groupTypeId, "groupTypeId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        GroupType groupType = idToGroupType.get(groupTypeId);
        return groupType == null
                ? groupTypeRepository.findById(groupTypeId)
                        .doOnNext(type -> idToGroupType.put(groupTypeId, type))
                : Mono.just(groupType);
    }

    public Mono<Boolean> groupTypeExists(@NotNull Long groupTypeId) {
        return queryGroupType(groupTypeId).map(type -> true)
                .defaultIfEmpty(false);
    }

    public Mono<Long> countGroupTypes() {
        return groupTypeRepository.countAll()
                .map(number -> number + 1);
    }

}