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

package im.turms.service.domain.user.service;

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
import im.turms.service.domain.user.po.UserVersion;
import im.turms.service.domain.user.repository.UserVersionRepository;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserVersionService {

    private final UserVersionRepository userVersionRepository;

    public UserVersionService(UserVersionRepository userVersionRepository) {
        this.userVersionRepository = userVersionRepository;
    }

    // Query

    public Mono<Date> queryRelationshipsLastUpdatedDate(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findRelationships(userId);
    }

    public Mono<Date> querySentGroupInvitationsLastUpdatedDate(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findSentGroupInvitations(userId);
    }

    public Mono<Date> queryReceivedGroupInvitationsLastUpdatedDate(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findReceivedGroupInvitations(userId);
    }

    public Mono<Date> queryGroupJoinRequestsVersion(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findGroupJoinRequests(userId);
    }

    public Mono<Date> queryRelationshipGroupsLastUpdatedDate(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findRelationshipGroups(userId);
    }

    public Mono<Date> queryJoinedGroupVersion(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findJoinedGroup(userId);
    }

    public Mono<Date> querySentFriendRequestsVersion(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findSentFriendRequests(userId);
    }

    public Mono<Date> queryReceivedFriendRequestsVersion(@NotNull Long userId) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.findReceivedFriendRequests(userId);
    }

    // Upsert

    public Mono<UserVersion> upsertEmptyUserVersion(
            @NotNull Long userId,
            @NotNull Date timestamp,
            @Nullable ClientSession session) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(timestamp, "timestamp");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        UserVersion userVersion = new UserVersion(
                userId,
                timestamp,
                timestamp,
                timestamp,
                timestamp,
                timestamp,
                timestamp,
                timestamp,
                timestamp,
                timestamp);
        return userVersionRepository.upsert(userVersion, session)
                .thenReturn(userVersion);
    }

    // Update

    public Mono<UpdateResult> updateRelationshipsVersion(
            @NotNull Long userId,
            @Nullable ClientSession session) {
        return updateSpecificVersion(userId, session, UserVersion.Fields.RELATIONSHIPS);
    }

    public Mono<UpdateResult> updateRelationshipsVersion(
            @NotEmpty Set<Long> userIds,
            @Nullable ClientSession session) {
        return updateSpecificVersion(userIds, session, UserVersion.Fields.RELATIONSHIPS);
    }

    public Mono<UpdateResult> updateSentFriendRequestsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.SENT_FRIEND_REQUESTS);
    }

    public Mono<UpdateResult> updateReceivedFriendRequestsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RECEIVED_FRIEND_REQUESTS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RELATIONSHIP_GROUPS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsVersion(@NotEmpty Set<Long> userIds) {
        return updateSpecificVersion(userIds, null, UserVersion.Fields.RELATIONSHIP_GROUPS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsMembersVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RELATIONSHIP_GROUP_MEMBERS);
    }

    public Mono<UpdateResult> updateRelationshipGroupsMembersVersion(@NotEmpty Set<Long> userIds) {
        return updateSpecificVersion(userIds, null, UserVersion.Fields.RELATIONSHIP_GROUP_MEMBERS);
    }

    public Mono<UpdateResult> updateSentGroupInvitationsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.SENT_GROUP_INVITATIONS);
    }

    public Mono<UpdateResult> updateReceivedGroupInvitationsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.RECEIVED_GROUP_INVITATIONS);
    }

    public Mono<UpdateResult> updateSentGroupJoinRequestsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.GROUP_JOIN_REQUESTS);
    }

    public Mono<UpdateResult> updateJoinedGroupsVersion(@NotNull Long userId) {
        return updateSpecificVersion(userId, null, UserVersion.Fields.JOINED_GROUPS);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            @NotNull Long userId,
            @Nullable ClientSession session,
            @NotEmpty String... fields) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notEmpty(fields, "fields");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.updateSpecificVersion(userId, session, fields);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            @NotNull Long userId,
            @Nullable ClientSession session,
            @NotNull String field) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(field, "field");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.updateSpecificVersion(userId, session, field);
    }

    public Mono<UpdateResult> updateSpecificVersion(
            @NotEmpty Set<Long> userIds,
            @Nullable ClientSession session,
            @NotEmpty String... fields) {
        try {
            Validator.notEmpty(userIds, "userIds");
            Validator.notEmpty(fields, "fields");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.updateSpecificVersion(userIds, session, fields);
    }

    // Delete

    public Mono<DeleteResult> delete(@NotEmpty Set<Long> userIds, @Nullable ClientSession session) {
        try {
            Validator.notEmpty(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userVersionRepository.deleteByIds(userIds, session);
    }

}
