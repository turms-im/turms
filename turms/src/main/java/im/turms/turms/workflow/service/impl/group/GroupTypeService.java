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

import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.GroupInvitationStrategy;
import im.turms.common.constant.GroupJoinStrategy;
import im.turms.common.constant.GroupUpdateStrategy;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constraint.NoWhitespace;
import im.turms.server.common.util.AssertUtil;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.GroupType;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Log4j2
@Service
public class GroupTypeService {

    private final Node node;
    private final Map<Long, GroupType> groupTypeMap = new HashMap<>();
    private final ReactiveMongoTemplate mongoTemplate;

    public GroupTypeService(
            Node node,
            @Qualifier("groupMongoTemplate") ReactiveMongoTemplate mongoTemplate) {
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
                        GroupInvitationStrategy.OWNER_MANAGER_MEMBER_REQUIRING_ACCEPTANCE,
                        GroupJoinStrategy.DECLINE_ANY_REQUEST,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        GroupUpdateStrategy.OWNER_MANAGER,
                        false,
                        true,
                        true,
                        true));
        mongoTemplate.changeStream(GroupType.class)
                .withOptions(ChangeStreamOptions.ChangeStreamOptionsBuilder::returnFullDocumentOnUpdate)
                .watchCollection(GroupType.class)
                .listen()
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    GroupType groupType = event.getBody();
                    switch (operationType) {
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            groupTypeMap.put(groupType.getId(), groupType);
                            break;
                        case DELETE:
                            long groupTypeId = ChangeStreamUtil.getIdAsLong(event);
                            groupTypeMap.remove(groupTypeId);
                            break;
                        case INVALIDATE:
                            groupTypeMap.clear();
                            break;
                        default:
                            log.error("Detect an illegal operation on GroupType collection: " + event);
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of GroupType: {}", o, throwable))
                .subscribe();
        mongoTemplate.find(new Query(), GroupType.class, GroupType.COLLECTION_NAME)
                .doOnNext(groupType -> groupTypeMap.put(groupType.getId(), groupType))
                .subscribe();
    }

    public GroupType getDefaultGroupType() {
        return groupTypeMap.get(DaoConstant.DEFAULT_GROUP_TYPE_ID);
    }

    public Flux<GroupType> queryGroupTypes(
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, GroupType.class, GroupType.COLLECTION_NAME)
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
        Long id = node.nextId(ServiceType.GROUP_TYPE);
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
        return mongoTemplate.insert(groupType, GroupType.COLLECTION_NAME);
    }

    public Mono<Boolean> updateGroupTypes(
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
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(ids));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(GroupType.Fields.NAME, name)
                .setIfNotNull(GroupType.Fields.GROUP_SIZE_LIMIT, groupSizeLimit)
                .setIfNotNull(GroupType.Fields.INVITATION_STRATEGY, groupInvitationStrategy)
                .setIfNotNull(GroupType.Fields.JOIN_STRATEGY, groupJoinStrategy)
                .setIfNotNull(GroupType.Fields.GROUP_INFO_UPDATE_STRATEGY, groupInfoUpdateStrategy)
                .setIfNotNull(GroupType.Fields.MEMBER_INFO_UPDATE_STRATEGY, memberInfoUpdateStrategy)
                .setIfNotNull(GroupType.Fields.GUEST_SPEAKABLE, guestSpeakable)
                .setIfNotNull(GroupType.Fields.SELF_INFO_UPDATABLE, selfInfoUpdatable)
                .setIfNotNull(GroupType.Fields.ENABLE_READ_RECEIPT, enableReadReceipt)
                .setIfNotNull(GroupType.Fields.MESSAGE_EDITABLE, messageEditable)
                .build();
        return mongoTemplate.updateMulti(query, update, GroupType.class, GroupType.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<Boolean> deleteGroupTypes(@Nullable Set<Long> groupTypeIds) {
        if (groupTypeIds != null) {
            if (groupTypeIds.contains(DaoConstant.DEFAULT_GROUP_TYPE_ID)) {
                return Mono.error(TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The default group type cannot be deleted"));
            }
            for (Long id : groupTypeIds) {
                groupTypeMap.remove(id);
            }
        } else {
            for (Long key : groupTypeMap.keySet()) {
                if (!key.equals(DaoConstant.DEFAULT_GROUP_TYPE_ID)) {
                    groupTypeMap.remove(key);
                }
            }
        }
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, groupTypeIds)
                .buildQuery();
        return mongoTemplate.remove(query, GroupType.class, GroupType.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
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
            return mongoTemplate.findById(groupTypeId, GroupType.class, GroupType.COLLECTION_NAME)
                    .doOnNext(type -> groupTypeMap.put(groupTypeId, type));
        }
    }

    public Mono<Boolean> groupTypeExists(@NotNull Long groupTypeId) {
        try {
            AssertUtil.notNull(groupTypeId, "groupTypeId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        GroupType groupType = groupTypeMap.get(groupTypeId);
        if (groupType != null) {
            return Mono.just(true);
        } else {
            Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(groupTypeId));
            return mongoTemplate.findOne(query, GroupType.class, GroupType.COLLECTION_NAME)
                    .map(type -> {
                        groupTypeMap.put(groupTypeId, type);
                        return true;
                    })
                    .defaultIfEmpty(false);
        }
    }

    public Mono<Long> countGroupTypes() {
        return mongoTemplate.count(new Query(), GroupType.class, GroupType.COLLECTION_NAME)
                .map(number -> number + 1);
    }

}
