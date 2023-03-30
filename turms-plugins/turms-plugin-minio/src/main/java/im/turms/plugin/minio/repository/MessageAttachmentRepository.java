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

package im.turms.plugin.minio.repository;

import java.util.List;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.plugin.minio.po.MessageAttachment;
import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.infra.time.DateRange;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;

/**
 * @author James Chen
 */
public class MessageAttachmentRepository extends BaseRepository<MessageAttachment, Long> {

    private static final String[] INCLUDE_SIMPLE_ATTACHMENT = {MessageAttachment.Fields.NAME,
            MessageAttachment.Fields.MEDIA_TYPE,
            MessageAttachment.Fields.UPLOADER_ID,
            MessageAttachment.Fields.CREATION_DATE};

    public MessageAttachmentRepository(TurmsMongoClient mongoClient) {
        super(mongoClient, MessageAttachment.class);
    }

    public Mono<Boolean> isAssociatedWithUserId(Long messageAttachmentId, Long userId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, messageAttachmentId)
                .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, userId);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> isAssociatedWithGroupId(Long messageAttachmentId, Long groupId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, messageAttachmentId)
                .eq(MessageAttachment.Fields.SHARED_WITH_GROUP_IDS, groupId);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<Boolean> isUploader(Long messageAttachmentId, Long userId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, messageAttachmentId)
                .eq(MessageAttachment.Fields.UPLOADER_ID, userId);
        return mongoClient.exists(entityClass, filter);
    }

    public Mono<UpdateResult> addSharedWithUser(Long messageAttachmentId, Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageAttachmentId);
        Update update = Update.newBuilder(1)
                .addToSet(MessageAttachment.Fields.SHARED_WITH_USER_IDS, userId);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> addSharedWithGroup(Long messageAttachmentId, Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageAttachmentId);
        Update update = Update.newBuilder(1)
                .addToSet(MessageAttachment.Fields.SHARED_WITH_GROUP_IDS, groupId);
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> removeSharedWithUser(Long messageAttachmentId, Long userId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageAttachmentId);
        Update update = Update.newBuilder(1)
                .pullAll(MessageAttachment.Fields.SHARED_WITH_USER_IDS, List.of(userId));
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UpdateResult> removeSharedWithGroup(Long messageAttachmentId, Long groupId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageAttachmentId);
        Update update = Update.newBuilder(1)
                .pullAll(MessageAttachment.Fields.SHARED_WITH_GROUP_IDS, List.of(groupId));
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Flux<MessageAttachment> findSimpleAttachmentsBySharedWithGroupId(
            Long groupId,
            @Nullable Set<Long> userIds,
            @Nullable DateRange creationDateRange) {
        Filter filter = Filter.newBuilder(1)
                .eq(MessageAttachment.Fields.SHARED_WITH_GROUP_IDS, groupId)
                .inIfNotNull(MessageAttachment.Fields.UPLOADER_ID, userIds)
                .addBetweenIfNotNull(MessageAttachment.Fields.CREATION_DATE, creationDateRange);
        QueryOptions queryOptions = QueryOptions.newBuilder(1)
                .include(INCLUDE_SIMPLE_ATTACHMENT);
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Flux<MessageAttachment> findSimpleAttachmentsBySharedWithUserIdAndUploaderId(
            Long sharedWithUserId,
            Long uploaderId) {
        Filter filter = Filter.newBuilder(2)
                .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, sharedWithUserId)
                .eq(MessageAttachment.Fields.UPLOADER_ID, uploaderId);
        QueryOptions queryOptions = QueryOptions.newBuilder(1)
                .include(INCLUDE_SIMPLE_ATTACHMENT);
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Flux<MessageAttachment> findSimpleAttachmentsByUploaderId(
            Long userId,
            @Nullable DateRange creationDateRange) {
        Filter filter = Filter.newBuilder(2)
                .eq(MessageAttachment.Fields.UPLOADER_ID, userId)
                .addBetweenIfNotNull(MessageAttachment.Fields.CREATION_DATE, creationDateRange);
        QueryOptions queryOptions = QueryOptions.newBuilder(1)
                .include(INCLUDE_SIMPLE_ATTACHMENT);
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Flux<MessageAttachment> findSimpleAttachmentsInPrivateConversation(
            Long userId,
            @Nullable DateRange creationDateRange) {
        Filter filter = Filter.newBuilder(3)
                .eq(MessageAttachment.Fields.UPLOADER_ID, userId)
                .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, userId)
                .addBetweenIfNotNull(MessageAttachment.Fields.CREATION_DATE, creationDateRange);
        QueryOptions queryOptions = QueryOptions.newBuilder(1)
                .include(INCLUDE_SIMPLE_ATTACHMENT);
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Flux<MessageAttachment> findSimpleAttachmentsInPrivateConversation(
            Long requesterId,
            Long userId,
            @Nullable DateRange creationDateRange,
            @Nullable Boolean areSharedByRequester) {
        Filter filter;
        if (areSharedByRequester == null) {
            filter = Filter.newBuilder(2)
                    .or(Filter.newBuilder(2)
                            .eq(MessageAttachment.Fields.UPLOADER_ID, requesterId)
                            .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, userId),
                            Filter.newBuilder(2)
                                    .eq(MessageAttachment.Fields.UPLOADER_ID, userId)
                                    .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, requesterId))
                    .addBetweenIfNotNull(MessageAttachment.Fields.CREATION_DATE, creationDateRange);
        } else if (areSharedByRequester) {
            filter = Filter.newBuilder(3)
                    .eq(MessageAttachment.Fields.UPLOADER_ID, requesterId)
                    .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, userId)
                    .addBetweenIfNotNull(MessageAttachment.Fields.CREATION_DATE, creationDateRange);
        } else {
            filter = Filter.newBuilder(3)
                    .eq(MessageAttachment.Fields.UPLOADER_ID, userId)
                    .eq(MessageAttachment.Fields.SHARED_WITH_USER_IDS, requesterId)
                    .addBetweenIfNotNull(MessageAttachment.Fields.CREATION_DATE, creationDateRange);
        }
        QueryOptions queryOptions = QueryOptions.newBuilder(1)
                .include(INCLUDE_SIMPLE_ATTACHMENT);
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Mono<MessageAttachment> findUploaderIdAndSharedWithUserIdsAndGroupIds(
            Long messageAttachmentId) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, messageAttachmentId);
        QueryOptions queryOptions = QueryOptions.newBuilder(1)
                .include(MessageAttachment.Fields.UPLOADER_ID,
                        MessageAttachment.Fields.SHARED_WITH_USER_IDS,
                        MessageAttachment.Fields.SHARED_WITH_GROUP_IDS);
        return mongoClient.findOne(entityClass, filter, queryOptions);
    }

    public Mono<DeleteResult> deleteByIdAndUploaderId(String messageAttachmentId, Long uploaderId) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, messageAttachmentId)
                .eq(MessageAttachment.Fields.UPLOADER_ID, uploaderId);
        return mongoClient.deleteOne(entityClass, filter);
    }

}