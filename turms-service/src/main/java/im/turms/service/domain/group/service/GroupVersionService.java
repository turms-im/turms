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

import java.util.Date;
import java.util.Set;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.group.po.GroupVersion;
import im.turms.service.domain.group.repository.GroupVersionRepository;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class GroupVersionService {
    private final GroupVersionRepository groupVersionRepository;

    public GroupVersionService(GroupVersionRepository groupVersionRepository) {
        this.groupVersionRepository = groupVersionRepository;
    }

    public Mono<Date> queryMembersVersion(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.findMembers(groupId);
    }

    public Mono<Date> queryBlocklistVersion(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.findBlocklist(groupId);
    }

    public Mono<Date> queryGroupJoinRequestsVersion(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.findJoinRequests(groupId);
    }

    public Mono<Date> queryGroupJoinQuestionsVersion(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.findJoinQuestions(groupId);
    }

    public Mono<Date> queryGroupInvitationsVersion(@NotNull Long groupId) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.findInvitations(groupId);
    }

    public Mono<Boolean> updateVersion(
            @NotNull Long groupId,
            boolean updateMembers,
            boolean updateBlocklist,
            boolean joinRequests,
            boolean joinQuestions) {
        try {
            Validator.notNull(groupId, "groupId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository
                .updateVersion(groupId, updateMembers, updateBlocklist, joinRequests, joinQuestions)
                .map(result -> result.getModifiedCount() > 0);
    }

    public Mono<Boolean> updateMembersVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.MEMBERS);
    }

    public Mono<UpdateResult> updateMembersVersion(@Nullable Set<Long> groupIds) {
        return updateSpecificVersion(groupIds, GroupVersion.Fields.MEMBERS);
    }

    public Mono<UpdateResult> updateMembersVersion() {
        return updateSpecificVersion(GroupVersion.Fields.MEMBERS);
    }

    public Mono<Boolean> updateBlocklistVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.BLOCKLIST);
    }

    public Mono<Boolean> updateJoinRequestsVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.JOIN_REQUESTS);
    }

    public Mono<Boolean> updateJoinQuestionsVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.JOIN_QUESTIONS);
    }

    public Mono<Boolean> updateGroupInvitationsVersion(@NotNull Long groupId) {
        return updateSpecificVersion(groupId, GroupVersion.Fields.INVITATIONS);
    }

    public Mono<Boolean> updateSpecificVersion(@NotNull Long groupId, @NotNull String field) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(field, "field");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.updateVersion(groupId, field)
                .map(result -> result.getModifiedCount() > 0);
    }

    public Mono<UpdateResult> updateSpecificVersion(@NotNull String field) {
        try {
            Validator.notNull(field, "field");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.updateVersions(field);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            @Nullable Set<Long> groupIds,
            @NotNull String field) {
        try {
            Validator.notNull(field, "field");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.updateVersions(groupIds, field);
    }

    public Mono<GroupVersion> upsert(@NotNull Long groupId, @NotNull Date timestamp) {
        try {
            Validator.notNull(groupId, "groupId");
            Validator.notNull(timestamp, "timestamp");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        GroupVersion version =
                new GroupVersion(groupId, timestamp, timestamp, timestamp, timestamp, timestamp);
        return groupVersionRepository.insert(version)
                .thenReturn(version);
    }

    public Mono<DeleteResult> delete(
            @NotEmpty Set<Long> groupIds,
            @Nullable ClientSession session) {
        try {
            Validator.notEmpty(groupIds, "groupIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return groupVersionRepository.deleteByIds(groupIds, session);
    }

}