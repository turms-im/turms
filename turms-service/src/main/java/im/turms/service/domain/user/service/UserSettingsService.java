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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import jakarta.annotation.Nullable;

import com.mongodb.reactivestreams.client.ClientSession;
import org.eclipse.collections.impl.set.mutable.UnifiedSet;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.service.CustomSettingService;
import im.turms.service.domain.user.po.UserSettings;
import im.turms.service.domain.user.repository.UserSettingsRepository;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class UserSettingsService extends CustomSettingService {

    private final UserSettingsRepository userSettingsRepository;

    public UserSettingsService(
            TurmsPropertiesManager propertiesManager,
            UserSettingsRepository userSettingsRepository) {
        this.userSettingsRepository = userSettingsRepository;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        super.updateGlobalProperties(properties.getService()
                .getUser()
                .getSettings());
    }

    public Mono<Boolean> upsertSettings(Long userId, Map<String, Value> settings) {
        try {
            Validator.notNull(userId, "userId");
            Validator.notNull(settings, "settings");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (settings.isEmpty()) {
            return PublisherPool.FALSE;
        }
        Set<String> immutableSettingsForUpsert = null;
        if (!immutableSettings.isEmpty()) {
            for (String settingName : settings.keySet()) {
                if (immutableSettings.contains(settingName)) {
                    if (immutableSettingsForUpsert == null) {
                        immutableSettingsForUpsert = new UnifiedSet<>(4);
                    }
                    immutableSettingsForUpsert.add(settingName);
                }
            }
        }
        if (immutableSettingsForUpsert == null) {
            Map<String, Object> parsedSettings = parseSettings(settings);
            return userSettingsRepository.upsertSettings(userId, parsedSettings)
                    .map(updateResult -> updateResult.getModifiedCount() > 0
                            || updateResult.getUpsertedId() != null);
        }
        Set<String> finalImmutableSettingsForUpsert = immutableSettingsForUpsert;
        return userSettingsRepository
                .findObjectFieldsById(userId,
                        UserSettings.Fields.SETTINGS,
                        immutableSettingsForUpsert)
                .collect(CollectorUtil.toSet(immutableSettingsForUpsert.size()))
                .flatMap(existingSettings -> {
                    if (existingSettings.isEmpty()) {
                        Map<String, Object> parsedSettings = parseSettings(settings);
                        return userSettingsRepository.upsertSettings(userId, parsedSettings);
                    }
                    finalImmutableSettingsForUpsert
                            .removeIf(settingName -> !existingSettings.contains(settingName));
                    if (finalImmutableSettingsForUpsert.isEmpty()) {
                        Map<String, Object> parsedSettings = parseSettings(settings);
                        return userSettingsRepository.upsertSettings(userId, parsedSettings);
                    }
                    List<String> sortedConflictedSettings =
                            new ArrayList<>(finalImmutableSettingsForUpsert);
                    sortedConflictedSettings.sort(null);
                    return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "Cannot update existing immutable settings: "
                                    + sortedConflictedSettings));
                })
                .map(updateResult -> updateResult.getModifiedCount() > 0
                        || updateResult.getUpsertedId() != null);
    }

    public Mono<Boolean> deleteSettings(
            Collection<Long> userIds,
            @Nullable ClientSession clientSession) {
        try {
            Validator.notNull(userIds, "userIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userSettingsRepository.deleteByIds(userIds, clientSession)
                .map(deleteResult -> deleteResult.getDeletedCount() > 0);
    }

    public Mono<Boolean> unsetSettings(Long userId, @Nullable Set<String> settingNames) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        if (settingNames == null || settingNames.isEmpty()) {
            if (deletableSettings.isEmpty()) {
                return PublisherPool.FALSE;
            }
            return userSettingsRepository.unsetSettings(userId, deletableSettings)
                    .map(updateResult -> updateResult.getModifiedCount() > 0);
        }
        if (ignoreUnknownSettingsOnDelete) {
            List<String> nonDeletableSettings = null;
            for (String settingName : settingNames) {
                Boolean deletable = settingToDeletable.get(settingName);
                if (deletable != null && !deletable) {
                    if (nonDeletableSettings == null) {
                        nonDeletableSettings = new ArrayList<>(4);
                    }
                    nonDeletableSettings.add(settingName);
                }
            }
            if (nonDeletableSettings != null) {
                nonDeletableSettings.sort(null);
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Cannot delete the non-deletable settings: "
                                + nonDeletableSettings));
            }
            return userSettingsRepository.unsetSettings(userId, settingNames)
                    .map(updateResult -> updateResult.getModifiedCount() > 0);
        }
        List<String> unknownSettings = null;
        List<String> nonDeletableSettings = null;
        for (String settingName : settingNames) {
            Boolean deletable = settingToDeletable.get(settingName);
            if (deletable == null) {
                if (unknownSettings == null) {
                    unknownSettings = new ArrayList<>(4);
                }
                unknownSettings.add(settingName);
            } else if (!deletable) {
                if (nonDeletableSettings == null) {
                    nonDeletableSettings = new ArrayList<>(4);
                }
                nonDeletableSettings.add(settingName);
            }
        }
        if (unknownSettings != null) {
            unknownSettings.sort(null);
            if (nonDeletableSettings != null) {
                nonDeletableSettings.sort(null);
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Cannot delete unknown settings: "
                                + unknownSettings
                                + ", and the non-deletable settings: "
                                + nonDeletableSettings));
            } else {
                return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "Cannot delete unknown settings: "
                                + unknownSettings));
            }
        } else if (nonDeletableSettings != null) {
            nonDeletableSettings.sort(null);
            return Mono.error(ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Cannot delete the non-deletable settings: "
                            + nonDeletableSettings));
        }
        return userSettingsRepository.unsetSettings(userId, settingNames)
                .map(updateResult -> updateResult.getModifiedCount() > 0);
    }

    public Mono<UserSettings> querySettings(
            Long userId,
            @Nullable Set<String> settingNames,
            @Nullable Date lastUpdatedDateStart) {
        try {
            Validator.notNull(userId, "userId");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return userSettingsRepository
                .findByIdAndSettingNames(userId, settingNames, lastUpdatedDateStart);
    }

}