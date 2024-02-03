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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
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
import im.turms.server.common.infra.time.DurationConst;
import im.turms.server.common.infra.validation.NoWhitespace;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.server.common.storage.mongo.exception.DuplicateKeyException;
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
        LOGGER.info("Loading all group types");
        groupTypeRepository.findAll()
                .doOnNext(groupType -> idToGroupType.put(groupType.getId(), groupType))
                .onErrorMap(t -> new RuntimeException(
                        "Caught an error while loading all group types",
                        t))
                .then(Mono.defer(() -> idToGroupType.containsKey(DEFAULT_GROUP_TYPE_ID)
                        ? Mono.empty()
                        : addDefaultGroupType().onErrorMap(t -> new RuntimeException(
                                "Caught an error while adding the default group type",
                                t))))
                .block(DurationConst.ONE_MINUTE);
        LOGGER.info("Loaded all group types");

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
                            if (groupTypeId == DEFAULT_GROUP_TYPE_ID) {
                                LOGGER.warn(
                                        "Adding the default group type because it is deleted unexpectedly");
                                addDefaultGroupType().subscribe(
                                        unused -> LOGGER.warn("Added the default group type"),
                                        t -> LOGGER.error(
                                                "Caught an error while adding the default group type",
                                                t));
                            }
                        }
                        case INVALIDATE -> idToGroupType.clear();
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
    }

    private Mono<GroupType> addDefaultGroupType() {
        return addGroupType(DEFAULT_GROUP_TYPE_ID,
                DEFAULT_GROUP_TYPE_NAME,
                500,
                GroupInvitationStrategy.OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL,
                GroupJoinStrategy.INVITATION,
                GroupUpdateStrategy.OWNER_MANAGER,
                GroupUpdateStrategy.OWNER_MANAGER,
                false,
                true,
                true,
                true).onErrorComplete(DuplicateKeyException.class);
    }

    public Flux<GroupType> queryGroupTypes(@Nullable Integer page, @Nullable Integer size) {
        return groupTypeRepository.findAll(page, size);
    }

    public Mono<GroupType> addGroupType(
            @Nullable Long id,
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
        if (id == null) {
            id = node.nextLargeGapId(ServiceType.GROUP_TYPE);
        }
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
        return groupTypeRepository.insert(groupType)
                .doOnSuccess(unused -> idToGroupType.put(groupType.getId(), groupType))
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
        return groupTypeRepository
                .updateTypes(ids,
                        name,
                        groupSizeLimit,
                        groupInvitationStrategy,
                        groupJoinStrategy,
                        groupInfoUpdateStrategy,
                        memberInfoUpdateStrategy,
                        guestSpeakable,
                        selfInfoUpdatable,
                        enableReadReceipt,
                        messageEditable)
                .doOnNext(updateResult -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
                    for (Long id : ids) {
                        idToGroupType.remove(id);
                    }
                });
    }

    public Mono<DeleteResult> deleteGroupTypes(@Nullable Set<Long> groupTypeIds) {
        if (groupTypeIds != null && groupTypeIds.contains(DEFAULT_GROUP_TYPE_ID)) {
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The default group type cannot be deleted"));
        }
        return groupTypeRepository.deleteByIds(groupTypeIds)
                .doOnNext(result -> {
                    // Though the latest records will be synced in the watch callback,
                    // we still need to invalid dirty cache immediately, so the subsequent query
                    // won't get outdated records
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

    public Mono<List<GroupType>> queryGroupTypes(@NotNull Collection<Long> groupTypeIds) {
        try {
            Validator.notNull(groupTypeIds, "groupTypeIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        int count = groupTypeIds.size();
        List<GroupType> groupTypes = new ArrayList<>(count);
        for (Long groupTypeId : groupTypeIds) {
            GroupType groupType = idToGroupType.get(groupTypeId);
            if (groupType != null) {
                groupTypes.add(groupType);
            }
        }
        if (groupTypes.size() == count) {
            return Mono.just(groupTypes);
        }
        groupTypes.clear();
        return groupTypeRepository.findByIds(groupTypeIds)
                .doOnNext(type -> idToGroupType.put(type.getId(), type))
                .collect(Collectors.toCollection(() -> groupTypes));
    }

    public Mono<Boolean> groupTypeExists(@NotNull Long groupTypeId) {
        return queryGroupType(groupTypeId).hasElement();
    }

    public Mono<Long> countGroupTypes() {
        return groupTypeRepository.countAll();
    }

}