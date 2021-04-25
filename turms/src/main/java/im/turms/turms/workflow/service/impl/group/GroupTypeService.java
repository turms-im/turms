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

package im.turms.turms.workflow.service.impl.group;

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupJoinStrategy;
import im.turms.common.constant.GroupUpdateStrategy;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.constant.OperationResultConstant;
import im.turms.turms.workflow.dao.domain.group.GroupType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Log4j2
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupTypeService {

    private final Node node;
    private final Map<Long, GroupType> groupTypeMap = new ConcurrentHashMap<>(16);
    private final TurmsMongoClient mongoTemplate;

    public GroupTypeService(
            Node node,
            @Qualifier("groupMongoClient") TurmsMongoClient mongoTemplate) {
        this.node = node;
        this.mongoTemplate = mongoTemplate;
        initGroupTypes();
    }

    public void initGroupTypes() {
        groupTypeMap.putIfAbsent(
                DaoConstant.DEFAULT_GROUP_TYPE_ID,
                new GroupType(
                        DaoConstant.DEFAULT_GROUP_TYPE_ID,
                        DaoConstant.DEFAULT_GROUP_TYPE_NAME,
                        500,
                        GroupInvitationStrategy.OWNER_MANAGER_MEMBER_REQUIRING_APPROVAL,
                        GroupJoinStrategy.DECLINE_ANY_REQUEST,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        false,
                        true,
                        true,
                        true));
        mongoTemplate.watch(GroupType.class, FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    GroupType groupType = event.getFullDocument();
                    switch (operationType) {
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            groupTypeMap.put(groupType.getId(), groupType);
                            break;
                        case DELETE:
                            long groupTypeId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            groupTypeMap.remove(groupTypeId);
                            break;
                        case INVALIDATE:
                            groupTypeMap.keySet().removeIf(id -> !id.equals(DaoConstant.DEFAULT_GROUP_TYPE_ID));
                            break;
                        default:
                            log.fatal("Detect an illegal operation on GroupType collection: " + event);
                    }
                })
                .onErrorContinue(
                        (throwable, o) -> log.error("Error while processing the change stream event of GroupType: {}", o, throwable))
                .subscribe();
        mongoTemplate.findAll(GroupType.class)
                .doOnNext(groupType -> groupTypeMap.put(groupType.getId(), groupType))
                .subscribe();
    }

    public GroupType getDefaultGroupType() {
        return groupTypeMap.get(DaoConstant.DEFAULT_GROUP_TYPE_ID);
    }

    public Flux<GroupType> queryGroupTypes(
            @Nullable Integer page,
            @Nullable Integer size) {
        QueryOptions options = QueryOptions.newBuilder()
                .paginateIfNotNull(page, size);
        return mongoTemplate.findAll(GroupType.class, options)
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
            AssertUtil.notNull(name, "name");
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.notNull(groupSizeLimit, "groupSizeLimit");
            AssertUtil.min(groupSizeLimit, "groupSizeLimit", 1);
            AssertUtil.notNull(groupInvitationStrategy, "groupInvitationStrategy");
            AssertUtil.notNull(groupJoinStrategy, "groupJoinStrategy");
            AssertUtil.notNull(groupInfoUpdateStrategy, "groupInfoUpdateStrategy");
            AssertUtil.notNull(memberInfoUpdateStrategy, "memberInfoUpdateStrategy");
            AssertUtil.notNull(guestSpeakable, "guestSpeakable");
            AssertUtil.notNull(selfInfoUpdatable, "selfInfoUpdatable");
            AssertUtil.notNull(enableReadReceipt, "enableReadReceipt");
            AssertUtil.notNull(messageEditable, "messageEditable");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        Long id = node.nextRandomId(ServiceType.GROUP_TYPE);
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
        groupTypeMap.put(id, groupType);
        return mongoTemplate.insert(groupType).thenReturn(groupType);
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
            AssertUtil.notEmpty(ids, "ids");
            AssertUtil.noWhitespace(name, "name");
            AssertUtil.min(groupSizeLimit, "groupSizeLimit", 1);
        } catch (TurmsBusinessException e) {
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
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder()
                .in(DaoConstant.ID_FIELD_NAME, ids);
        Update update = Update.newBuilder()
                .setIfNotNull(GroupType.Fields.NAME, name)
                .setIfNotNull(GroupType.Fields.GROUP_SIZE_LIMIT, groupSizeLimit)
                .setIfNotNull(GroupType.Fields.INVITATION_STRATEGY, groupInvitationStrategy)
                .setIfNotNull(GroupType.Fields.JOIN_STRATEGY, groupJoinStrategy)
                .setIfNotNull(GroupType.Fields.GROUP_INFO_UPDATE_STRATEGY, groupInfoUpdateStrategy)
                .setIfNotNull(GroupType.Fields.MEMBER_INFO_UPDATE_STRATEGY, memberInfoUpdateStrategy)
                .setIfNotNull(GroupType.Fields.GUEST_SPEAKABLE, guestSpeakable)
                .setIfNotNull(GroupType.Fields.SELF_INFO_UPDATABLE, selfInfoUpdatable)
                .setIfNotNull(GroupType.Fields.ENABLE_READ_RECEIPT, enableReadReceipt)
                .setIfNotNull(GroupType.Fields.MESSAGE_EDITABLE, messageEditable);
        return mongoTemplate.updateMany(GroupType.class, filter, update);
    }

    public Mono<DeleteResult> deleteGroupTypes(@Nullable Set<Long> groupTypeIds) {
        if (groupTypeIds != null && groupTypeIds.contains(DaoConstant.DEFAULT_GROUP_TYPE_ID)) {
            return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The default group type cannot be deleted"));
        }
        Filter filter = Filter
                .newBuilder()
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, groupTypeIds);
        return mongoTemplate.deleteMany(GroupType.class, filter)
                .doOnNext(result -> {
                    if (groupTypeIds != null) {
                        for (Long id : groupTypeIds) {
                            groupTypeMap.remove(id);
                        }
                    } else {
                        groupTypeMap.keySet().removeIf(id -> !id.equals(DaoConstant.DEFAULT_GROUP_TYPE_ID));
                    }
                });
    }

    public Mono<GroupType> queryGroupType(@NotNull Long groupTypeId) {
        try {
            AssertUtil.notNull(groupTypeId, "groupTypeId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupType groupType = groupTypeMap.get(groupTypeId);
        if (groupType != null) {
            return Mono.just(groupType);
        } else {
            return mongoTemplate.findById(GroupType.class, groupTypeId)
                    .doOnNext(type -> groupTypeMap.put(groupTypeId, type));
        }
    }

    public Mono<Boolean> groupTypeExists(@NotNull Long groupTypeId) {
        return queryGroupType(groupTypeId)
                .map(type -> true)
                .defaultIfEmpty(false);
    }

    public Mono<Long> countGroupTypes() {
        return mongoTemplate.countAll(GroupType.class)
                .map(number -> number + 1);
    }

}