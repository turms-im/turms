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

package im.turms.turms.workflow.service.impl.user;

import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.constant.statuscode.TurmsStatusCode;
import im.turms.common.exception.TurmsBusinessException;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.turms.constant.DaoConstant;
import im.turms.turms.workflow.dao.builder.QueryBuilder;
import im.turms.turms.workflow.dao.builder.UpdateBuilder;
import im.turms.turms.workflow.dao.domain.UserPermissionGroup;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.ChangeStreamOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author James Chen
 */
@Log4j2
@Service
@Validated
public class UserPermissionGroupService {

    private final Map<Long, UserPermissionGroup> userPermissionGroupMap = new HashMap<>();
    private final ReactiveMongoTemplate mongoTemplate;
    private final Node node;
    private final UserService userService;

    public UserPermissionGroupService(
            @Qualifier("userMongoTemplate") ReactiveMongoTemplate mongoTemplate,
            Node node,
            UserService userService) {
        this.mongoTemplate = mongoTemplate;
        this.node = node;
        this.userService = userService;

        userPermissionGroupMap.putIfAbsent(
                DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID,
                new UserPermissionGroup(
                        DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID,
                        Set.of(DaoConstant.DEFAULT_GROUP_TYPE_ID),
                        Integer.MAX_VALUE,
                        Integer.MAX_VALUE,
                        Collections.emptyMap()));
        mongoTemplate.find(new Query(), UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                .subscribe(userPermissionGroup -> userPermissionGroupMap.put(userPermissionGroup.getId(), userPermissionGroup));
        mongoTemplate.changeStream(UserPermissionGroup.class)
                .withOptions(ChangeStreamOptions.ChangeStreamOptionsBuilder::returnFullDocumentOnUpdate)
                .watchCollection(UserPermissionGroup.class)
                .listen()
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    UserPermissionGroup userPermissionGroup = event.getBody();
                    switch (operationType) {
                        case INSERT:
                        case UPDATE:
                        case REPLACE:
                            userPermissionGroupMap.put(userPermissionGroup.getId(), userPermissionGroup);
                            break;
                        case DELETE:
                            long groupTypeId = ChangeStreamUtil.getIdAsLong(event);
                            userPermissionGroupMap.remove(groupTypeId);
                            break;
                        case INVALIDATE:
                            userPermissionGroupMap.clear();
                            break;
                        default:
                            throw new IllegalStateException("Unexpected value: " + operationType);
                    }
                })
                .onErrorContinue((throwable, o) -> log.error("Error while processing the change stream event of UserPermissionGroup: {}", o, throwable))
                .subscribe();
    }

    public UserPermissionGroup getDefaultUserPermissionGroup() {
        return userPermissionGroupMap.get(DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID);
    }

    public Flux<UserPermissionGroup> queryUserPermissionGroups(
            @Nullable Integer page,
            @Nullable Integer size) {
        Query query = QueryBuilder
                .newBuilder()
                .paginateIfNotNull(page, size);
        return mongoTemplate.find(query, UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                .concatWithValues(getDefaultUserPermissionGroup());
    }

    public Mono<UserPermissionGroup> addUserPermissionGroup(
            @Nullable Long id,
            @NotNull Set<Long> creatableGroupTypeIds,
            @NotNull Integer ownedGroupLimit,
            @NotNull Integer ownedGroupLimitForEachGroupType,
            @NotNull Map<Long, Integer> groupTypeLimitMap) {
        if (id == null) {
            id = node.nextId(ServiceType.USER_PERMISSION_GROUP);
        }
        UserPermissionGroup userPermissionGroup = new UserPermissionGroup(
                id,
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeLimitMap);
        userPermissionGroupMap.put(id, userPermissionGroup);
        return mongoTemplate.insert(userPermissionGroup, UserPermissionGroup.COLLECTION_NAME);
    }

    public Mono<Boolean> updateUserPermissionGroups(
            @NotEmpty Set<Long> ids,
            @Nullable Set<Long> creatableGroupTypeIds,
            @Nullable Integer ownedGroupLimit,
            @Nullable Integer ownedGroupLimitForEachGroupType,
            @Nullable Map<Long, Integer> groupTypeLimitMap) {
        if (Validator.areAllNull(
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeLimitMap)) {
            return Mono.just(true);
        }
        Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).in(ids));
        Update update = UpdateBuilder.newBuilder()
                .setIfNotNull(UserPermissionGroup.Fields.CREATABLE_GROUP_TYPE_IDS, creatableGroupTypeIds)
                .setIfNotNull(UserPermissionGroup.Fields.OWNED_GROUP_LIMIT, ownedGroupLimit)
                .setIfNotNull(UserPermissionGroup.Fields.OWNED_GROUP_LIMIT_FOR_EACH_GROUP_TYPE, ownedGroupLimitForEachGroupType)
                .setIfNotNull(UserPermissionGroup.Fields.GROUP_TYPE_LIMITS, groupTypeLimitMap)
                .build();
        return mongoTemplate.updateMulti(query, update, UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                .map(UpdateResult::wasAcknowledged);
    }

    public Mono<Boolean> deleteUserPermissionGroups(@Nullable Set<Long> ids) {
        if (ids != null) {
            if (ids.contains(DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID)) {
                throw TurmsBusinessException.get(TurmsStatusCode.ILLEGAL_ARGUMENTS, "The default user permission group cannot be deleted");
            }
            for (Long id : ids) {
                userPermissionGroupMap.remove(id);
            }
        } else {
            for (Long key : userPermissionGroupMap.keySet()) {
                if (!key.equals(DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID)) {
                    userPermissionGroupMap.remove(key);
                }
            }
        }
        Query query = QueryBuilder
                .newBuilder()
                .addInIfNotNull(DaoConstant.ID_FIELD_NAME, ids)
                .buildQuery();
        return mongoTemplate.remove(query, UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                .map(DeleteResult::wasAcknowledged);
    }

    public Mono<UserPermissionGroup> queryUserPermissionGroup(@NotNull Long id) {
        UserPermissionGroup userPermissionGroup = userPermissionGroupMap.get(id);
        if (userPermissionGroup != null) {
            return Mono.just(userPermissionGroup);
        } else {
            return mongoTemplate.findById(id, UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                    .doOnNext(type -> userPermissionGroupMap.put(id, type));
        }
    }

    public Mono<UserPermissionGroup> queryUserPermissionGroupByUserId(@NotNull Long userId) {
        return userService.queryUserPermissionGroupId(userId)
                .flatMap(this::queryUserPermissionGroup);
    }

    public Mono<Boolean> userPermissionGroupExists(@NotNull Long id) {
        UserPermissionGroup userPermissionGroup = userPermissionGroupMap.get(id);
        if (userPermissionGroup != null) {
            return Mono.just(true);
        } else {
            Query query = new Query().addCriteria(Criteria.where(DaoConstant.ID_FIELD_NAME).is(id));
            return mongoTemplate.findOne(query, UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                    .map(type -> {
                        userPermissionGroupMap.put(id, type);
                        return true;
                    })
                    .defaultIfEmpty(false);
        }
    }

    public Mono<Long> countUserPermissionGroups() {
        return mongoTemplate.count(new Query(), UserPermissionGroup.class, UserPermissionGroup.COLLECTION_NAME)
                .map(number -> number + 1);
    }

}
