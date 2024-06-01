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

package im.turms.service.domain.conversation.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.ClientSession;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.conversation.po.ConversationSettings;

/**
 * @author James Chen
 */
@Repository
public class ConversationSettingsRepository extends BaseRepository<ConversationSettings, Long> {

    public ConversationSettingsRepository(
            @Qualifier("conversationMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, ConversationSettings.class);
    }

    public Mono<UpdateResult> upsertSettings(
            Long ownerId,
            Long targetId,
            Map<String, Object> settings) {
        Filter filter = Filter.newBuilder(2)
                .eq(DomainFieldName.ID, new ConversationSettings.Key(ownerId, targetId));
        Update update = Update.newBuilder(settings.size() + 1)
                .set(ConversationSettings.Fields.LAST_UPDATED_DATE, new Date());
        for (Map.Entry<String, Object> entry : settings.entrySet()) {
            update.set(ConversationSettings.Fields.SETTINGS
                    + "."
                    + entry.getKey(), entry.getValue());
        }
        return mongoClient.upsert(entityClass, filter, update);
    }

    public Mono<UpdateResult> unsetSettings(
            Long ownerId,
            @Nullable Collection<Long> targetIds,
            @Nullable Collection<String> settingNames) {
        Filter filter;
        if (targetIds == null || targetIds.isEmpty()) {
            filter = Filter.newBuilder(1)
                    .eq(ConversationSettings.Fields.ID_OWNER_ID, ownerId);
        } else {
            filter = Filter.newBuilder(1)
                    .in(DomainFieldName.ID, targetIds);
        }
        Update update = Update.newBuilder(1)
                .set(ConversationSettings.Fields.LAST_UPDATED_DATE, new Date());
        if (settingNames == null || settingNames.isEmpty()) {
            update = update.unset(ConversationSettings.Fields.SETTINGS);
        } else {
            for (String settingName : settingNames) {
                update = update.unset(ConversationSettings.Fields.SETTINGS
                        + "."
                        + settingName);
            }
        }
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Flux<ConversationSettings> findByIdAndSettingNames(
            Long ownerId,
            @Nullable Collection<String> settingNames,
            @Nullable Date lastUpdatedDateStart) {
        Filter filter = Filter.newBuilder(2)
                .eq(ConversationSettings.Fields.ID_OWNER_ID, ownerId)
                .gteIfNotNull(ConversationSettings.Fields.LAST_UPDATED_DATE, lastUpdatedDateStart);
        QueryOptions queryOptions = null;
        if (settingNames != null && !settingNames.isEmpty()) {
            BsonDocument projection = new BsonDocument()
                    .append(ConversationSettings.Fields.LAST_UPDATED_DATE, BsonPool.BSON_INT32_1);
            for (String settingName : settingNames) {
                projection.append(settingName, BsonPool.BSON_INT32_1);
            }
            queryOptions = QueryOptions.newBuilder()
                    .projection(projection);
        }
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Flux<ConversationSettings> findByIdAndSettingNames(
            Collection<ConversationSettings.Key> keys,
            @Nullable Collection<String> settingNames,
            @Nullable Date lastUpdatedDateStart) {
        Filter filter = Filter.newBuilder(3)
                .eq(DomainFieldName.ID, keys)
                .gteIfNotNull(ConversationSettings.Fields.LAST_UPDATED_DATE, lastUpdatedDateStart);
        QueryOptions queryOptions = null;
        if (settingNames != null && !settingNames.isEmpty()) {
            BsonDocument projection = new BsonDocument()
                    .append(ConversationSettings.Fields.LAST_UPDATED_DATE, BsonPool.BSON_INT32_1);
            for (String settingName : settingNames) {
                projection.append(settingName, BsonPool.BSON_INT32_1);
            }
            queryOptions = QueryOptions.newBuilder()
                    .projection(projection);
        }
        return mongoClient.findMany(entityClass, filter, queryOptions);
    }

    public Flux<String> findSettingFields(
            Long ownerId,
            Long targetId,
            Collection<String> includedFields) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, new ConversationSettings.Key(ownerId, targetId));
        return mongoClient.findObjectFields(entityClass,
                filter,
                ConversationSettings.Fields.SETTINGS,
                includedFields);
    }

    public Mono<DeleteResult> deleteByOwnerIds(
            Collection<Long> ownerIds,
            @Nullable ClientSession clientSession) {
        Filter filter = Filter.newBuilder(1)
                .in(ConversationSettings.Fields.ID_OWNER_ID, ownerIds);
        return mongoClient.deleteMany(clientSession, entityClass, filter);
    }
}