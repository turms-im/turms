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

package im.turms.service.workflow.service.impl.user;

import com.mongodb.client.model.changestream.FullDocument;
import com.mongodb.client.model.changestream.OperationType;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import im.turms.common.util.Validator;
import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.service.config.ChangeStreamUtil;
import im.turms.server.common.cluster.service.idgen.ServiceType;
import im.turms.server.common.constant.TurmsStatusCode;
import im.turms.server.common.exception.TurmsBusinessException;
import im.turms.server.common.mongo.IMongoCollectionInitializer;
import im.turms.server.common.mongo.TurmsMongoClient;
import im.turms.server.common.mongo.operation.option.Filter;
import im.turms.server.common.mongo.operation.option.QueryOptions;
import im.turms.server.common.mongo.operation.option.Update;
import im.turms.server.common.util.AssertUtil;
import im.turms.service.constant.DaoConstant;
import im.turms.service.constant.OperationResultConstant;
import im.turms.service.workflow.dao.domain.user.UserPermissionGroup;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Nullable;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author James Chen
 */
@Log4j2
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserPermissionGroupService {

    private final Map<Long, UserPermissionGroup> userPermissionGroupMap = new ConcurrentHashMap<>(16);
    private final TurmsMongoClient mongoTemplate;
    private final Node node;
    private final UserService userService;

    public UserPermissionGroupService(
            @Qualifier("userMongoClient") TurmsMongoClient mongoClient,
            Node node,
            UserService userService) {
        this.mongoTemplate = mongoClient;
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
        mongoClient.findAll(UserPermissionGroup.class)
                .subscribe(userPermissionGroup -> userPermissionGroupMap.put(userPermissionGroup.getId(), userPermissionGroup));
        mongoClient.watch(UserPermissionGroup.class, FullDocument.UPDATE_LOOKUP)
                .doOnNext(event -> {
                    OperationType operationType = event.getOperationType();
                    UserPermissionGroup userPermissionGroup = event.getFullDocument();
                    switch (operationType) {
                        case INSERT, UPDATE, REPLACE -> userPermissionGroupMap.put(userPermissionGroup.getId(), userPermissionGroup);
                        case DELETE -> {
                            long groupTypeId = ChangeStreamUtil.getIdAsLong(event.getDocumentKey());
                            userPermissionGroupMap.remove(groupTypeId);
                        }
                        case INVALIDATE -> userPermissionGroupMap.clear();
                        default -> log.fatal("Detect an illegal operation on UserPermissionGroup collection: " + event);
                    }
                })
                .onErrorContinue((throwable, o) -> log
                        .error("Error while processing the change stream event of UserPermissionGroup: {}", o, throwable))
                .subscribe();
    }

    public UserPermissionGroup getDefaultUserPermissionGroup() {
        return userPermissionGroupMap.get(DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID);
    }

    public Flux<UserPermissionGroup> queryUserPermissionGroups(
            @Nullable Integer page,
            @Nullable Integer size) {
        QueryOptions options = QueryOptions.newBuilder(2)
                .paginateIfNotNull(page, size);
        return mongoTemplate.findAll(UserPermissionGroup.class, options)
                .concatWithValues(getDefaultUserPermissionGroup());
    }

    public Mono<UserPermissionGroup> addUserPermissionGroup(
            @Nullable Long groupId,
            @NotNull Set<Long> creatableGroupTypeIds,
            @NotNull Integer ownedGroupLimit,
            @NotNull Integer ownedGroupLimitForEachGroupType,
            @NotNull Map<Long, Integer> groupTypeLimitMap) {
        try {
            AssertUtil.notNull(creatableGroupTypeIds, "creatableGroupTypeIds");
            AssertUtil.notNull(ownedGroupLimit, "ownedGroupLimit");
            AssertUtil.notNull(ownedGroupLimitForEachGroupType, "ownedGroupLimitForEachGroupType");
            AssertUtil.notNull(groupTypeLimitMap, "groupTypeLimitMap");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (groupId == null) {
            groupId = node.nextRandomId(ServiceType.USER_PERMISSION_GROUP);
        }
        UserPermissionGroup userPermissionGroup = new UserPermissionGroup(
                groupId,
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeLimitMap);
        userPermissionGroupMap.put(groupId, userPermissionGroup);
        return mongoTemplate.insert(userPermissionGroup)
                .thenReturn(userPermissionGroup);
    }

    public Mono<UpdateResult> updateUserPermissionGroups(
            @NotEmpty Set<Long> groupIds,
            @Nullable Set<Long> creatableGroupTypeIds,
            @Nullable Integer ownedGroupLimit,
            @Nullable Integer ownedGroupLimitForEachGroupType,
            @Nullable Map<Long, Integer> groupTypeLimitMap) {
        try {
            AssertUtil.notEmpty(groupIds, "groupIds");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        if (Validator.areAllNull(
                creatableGroupTypeIds,
                ownedGroupLimit,
                ownedGroupLimitForEachGroupType,
                groupTypeLimitMap)) {
            return Mono.just(OperationResultConstant.ACKNOWLEDGED_UPDATE_RESULT);
        }
        Filter filter = Filter.newBuilder(1)
                .in(DaoConstant.ID_FIELD_NAME, groupIds);
        Update update = Update.newBuilder(4)
                .setIfNotNull(UserPermissionGroup.Fields.CREATABLE_GROUP_TYPE_IDS, creatableGroupTypeIds)
                .setIfNotNull(UserPermissionGroup.Fields.OWNED_GROUP_LIMIT, ownedGroupLimit)
                .setIfNotNull(UserPermissionGroup.Fields.OWNED_GROUP_LIMIT_FOR_EACH_GROUP_TYPE, ownedGroupLimitForEachGroupType)
                .setIfNotNull(UserPermissionGroup.Fields.GROUP_TYPE_LIMITS, groupTypeLimitMap);
        return mongoTemplate.updateMany(UserPermissionGroup.class, filter, update);
    }

    public Mono<DeleteResult> deleteUserPermissionGroups(@Nullable Set<Long> groupIds) {
        if (groupIds != null && groupIds.contains(DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID)) {
            return Mono.error(TurmsBusinessException
                    .get(TurmsStatusCode.ILLEGAL_ARGUMENT, "The default user permission group cannot be deleted"));
        }
        Filter filter = Filter.newBuilder(1)
                .inIfNotNull(DaoConstant.ID_FIELD_NAME, groupIds);
        return mongoTemplate.deleteMany(UserPermissionGroup.class, filter)
                .doOnNext(result -> {
                    if (groupIds != null) {
                        for (Long id : groupIds) {
                            userPermissionGroupMap.remove(id);
                        }
                    } else {
                        userPermissionGroupMap.keySet()
                                .removeIf(id -> !id.equals(DaoConstant.DEFAULT_USER_PERMISSION_GROUP_ID));
                    }
                });
    }

    public Mono<UserPermissionGroup> queryUserPermissionGroup(@NotNull Long groupId) {
        try {
            AssertUtil.notNull(groupId, "groupId");
        } catch (TurmsBusinessException e) {
            return Mono.error(e);
        }
        UserPermissionGroup userPermissionGroup = userPermissionGroupMap.get(groupId);
        if (userPermissionGroup != null) {
            return Mono.just(userPermissionGroup);
        } else {
            return mongoTemplate.findById(UserPermissionGroup.class, groupId)
                    .doOnNext(type -> userPermissionGroupMap.put(groupId, type));
        }
    }

    public Mono<UserPermissionGroup> queryUserPermissionGroupByUserId(@NotNull Long userId) {
        return userService.queryUserPermissionGroupId(userId)
                .flatMap(groupId -> queryUserPermissionGroup(groupId)
                        .switchIfEmpty(Mono.error(TurmsBusinessException
                                .get(TurmsStatusCode.SERVER_INTERNAL_ERROR, "The user is in a nonexistent permission group " + groupId))))
                .switchIfEmpty(Mono.error(TurmsBusinessException.get(TurmsStatusCode.QUERY_PERMISSION_OF_NON_EXISTING_USER)));
    }

    public Mono<Long> countUserPermissionGroups() {
        return mongoTemplate.countAll(UserPermissionGroup.class)
                .map(number -> number + 1);
    }

}