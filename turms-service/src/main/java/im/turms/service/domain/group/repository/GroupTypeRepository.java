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

package im.turms.service.domain.group.repository;

import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.group.bo.GroupInvitationStrategy;
import im.turms.service.domain.group.bo.GroupJoinStrategy;
import im.turms.service.domain.group.bo.GroupUpdateStrategy;
import im.turms.service.domain.group.po.GroupType;

/**
 * @author James Chen
 */
@Repository
public class GroupTypeRepository extends BaseRepository<GroupType, Long> {

    public GroupTypeRepository(@Qualifier("groupMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, GroupType.class);
    }

    public Mono<UpdateResult> updateTypes(
            Set<Long> ids,
            @Nullable String name,
            @Nullable Integer groupSizeLimit,
            @Nullable GroupInvitationStrategy groupInvitationStrategy,
            @Nullable GroupJoinStrategy groupJoinStrategy,
            @Nullable GroupUpdateStrategy groupInfoUpdateStrategy,
            @Nullable GroupUpdateStrategy memberInfoUpdateStrategy,
            @Nullable Boolean guestSpeakable,
            @Nullable Boolean selfInfoUpdatable,
            @Nullable Boolean enableReadReceipt,
            @Nullable Boolean messageEditable) {
        Filter filter = Filter.newBuilder(1)
                .in(DomainFieldName.ID, ids);
        Update update = Update.newBuilder(10)
                .setIfNotNull(GroupType.Fields.NAME, name)
                .setIfNotNull(GroupType.Fields.GROUP_SIZE_LIMIT, groupSizeLimit)
                .setIfNotNull(GroupType.Fields.INVITATION_STRATEGY, groupInvitationStrategy)
                .setIfNotNull(GroupType.Fields.JOIN_STRATEGY, groupJoinStrategy)
                .setIfNotNull(GroupType.Fields.GROUP_INFO_UPDATE_STRATEGY, groupInfoUpdateStrategy)
                .setIfNotNull(GroupType.Fields.MEMBER_INFO_UPDATE_STRATEGY,
                        memberInfoUpdateStrategy)
                .setIfNotNull(GroupType.Fields.GUEST_SPEAKABLE, guestSpeakable)
                .setIfNotNull(GroupType.Fields.SELF_INFO_UPDATABLE, selfInfoUpdatable)
                .setIfNotNull(GroupType.Fields.ENABLE_READ_RECEIPT, enableReadReceipt)
                .setIfNotNull(GroupType.Fields.MESSAGE_EDITABLE, messageEditable);
        return mongoClient.updateMany(entityClass, filter, update);
    }

}
