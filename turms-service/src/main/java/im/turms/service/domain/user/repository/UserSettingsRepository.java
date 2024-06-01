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

package im.turms.service.domain.user.repository;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import jakarta.annotation.Nullable;

import com.mongodb.client.result.UpdateResult;
import org.bson.BsonDocument;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import im.turms.server.common.domain.common.repository.BaseRepository;
import im.turms.server.common.storage.mongo.BsonPool;
import im.turms.server.common.storage.mongo.DomainFieldName;
import im.turms.server.common.storage.mongo.TurmsMongoClient;
import im.turms.server.common.storage.mongo.operation.option.Filter;
import im.turms.server.common.storage.mongo.operation.option.QueryOptions;
import im.turms.server.common.storage.mongo.operation.option.Update;
import im.turms.service.domain.user.po.UserSettings;

/**
 * @author James Chen
 */
@Repository
public class UserSettingsRepository extends BaseRepository<UserSettings, Long> {

    public UserSettingsRepository(@Qualifier("userMongoClient") TurmsMongoClient mongoClient) {
        super(mongoClient, UserSettings.class);
    }

    public Mono<UpdateResult> upsertSettings(Long userId, Map<String, Object> settings) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        Update update = Update.newBuilder(settings.size() + 1)
                .set(UserSettings.Fields.LAST_UPDATED_DATE, new Date());
        for (Map.Entry<String, Object> entry : settings.entrySet()) {
            update.set(UserSettings.Fields.SETTINGS
                    + "."
                    + entry.getKey(), entry.getValue());
        }
        return mongoClient.upsert(entityClass, filter, update);
    }

    public Mono<UpdateResult> unsetSettings(
            Long userId,
            @Nullable Collection<String> settingNames) {
        Filter filter = Filter.newBuilder(1)
                .eq(DomainFieldName.ID, userId);
        Update update = Update.newBuilder(1)
                .set(UserSettings.Fields.LAST_UPDATED_DATE, new Date());
        if (settingNames == null || settingNames.isEmpty()) {
            update = update.unset(UserSettings.Fields.SETTINGS);
        } else {
            for (String settingName : settingNames) {
                update = update.unset(UserSettings.Fields.SETTINGS
                        + "."
                        + settingName);
            }
        }
        return mongoClient.updateOne(entityClass, filter, update);
    }

    public Mono<UserSettings> findByIdAndSettingNames(
            Long userId,
            @Nullable Collection<String> settingNames,
            @Nullable Date lastUpdatedDateStart) {
        Filter filter = lastUpdatedDateStart == null
                ? Filter.newBuilder(1)
                        .eq(DomainFieldName.ID, userId)
                : Filter.newBuilder(2)
                        .eq(DomainFieldName.ID, userId)
                        .gte(UserSettings.Fields.LAST_UPDATED_DATE, lastUpdatedDateStart);
        QueryOptions queryOptions = null;
        if (settingNames != null && !settingNames.isEmpty()) {
            BsonDocument projection = new BsonDocument()
                    .append(UserSettings.Fields.LAST_UPDATED_DATE, BsonPool.BSON_INT32_1);
            for (String settingName : settingNames) {
                projection.append(settingName, BsonPool.BSON_INT32_1);
            }
            queryOptions = QueryOptions.newBuilder()
                    .projection(projection);
        }
        return mongoClient.findOne(entityClass, filter, queryOptions);
    }

}