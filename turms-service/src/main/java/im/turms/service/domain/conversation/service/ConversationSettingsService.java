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

package im.turms.service.domain.conversation.service;

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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.collection.CollectorUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.TurmsProperties;
import im.turms.server.common.infra.property.TurmsPropertiesManager;
import im.turms.server.common.infra.reactor.PublisherPool;
import im.turms.server.common.infra.validation.Validator;
import im.turms.server.common.storage.mongo.IMongoCollectionInitializer;
import im.turms.service.domain.common.service.CustomSettingService;
import im.turms.service.domain.conversation.po.ConversationSettings;
import im.turms.service.domain.conversation.repository.ConversationSettingsRepository;
import im.turms.service.domain.group.service.GroupMemberService;
import im.turms.service.domain.user.service.UserRelationshipService;

/**
 * @author James Chen
 */
@Service
@DependsOn(IMongoCollectionInitializer.BEAN_NAME)
public class ConversationSettingsService extends CustomSettingService {

    private final GroupMemberService groupMemberService;
    private final UserRelationshipService userRelationshipService;

    private final ConversationSettingsRepository conversationSettingsRepository;

    public ConversationSettingsService(
            TurmsPropertiesManager propertiesManager,
            GroupMemberService groupMemberService,
            UserRelationshipService userRelationshipService,
            ConversationSettingsRepository conversationSettingsRepository) {
        this.groupMemberService = groupMemberService;
        this.userRelationshipService = userRelationshipService;
        this.conversationSettingsRepository = conversationSettingsRepository;

        propertiesManager.notifyAndAddGlobalPropertiesChangeListener(this::updateGlobalProperties);
    }

    private void updateGlobalProperties(TurmsProperties properties) {
        super.updateGlobalProperties(properties.getService()
                .getConversation()
                .getSettings());
    }

    public Mono<Boolean> upsertPrivateConversationSettings(
            Long ownerId,
            Long userId,
            Map<String, Value> settings) {
        try {
            Validator.notNull(ownerId, "ownerId");
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
            return userRelationshipService.hasOneSidedRelationship(ownerId, userId)
                    .flatMap(hasOneSidedRelationship -> {
                        if (!hasOneSidedRelationship) {
                            return Mono.error(ResponseException.get(
                                    ResponseStatusCode.NOT_RELATED_USER_TO_UPDATE_PRIVATE_CONVERSATION_SETTING));
                        }
                        Map<String, Object> parsedSettings = parseSettings(settings);
                        return conversationSettingsRepository
                                .upsertSettings(ownerId, userId, parsedSettings)
                                .map(updateResult -> updateResult.getModifiedCount() > 0
                                        || updateResult.getUpsertedId() != null);
                    });
        }
        Set<String> finalImmutableSettingsForUpsert = immutableSettingsForUpsert;
        return userRelationshipService.hasOneSidedRelationship(ownerId, userId)
                .flatMap(hasOneSidedRelationship -> {
                    if (!hasOneSidedRelationship) {
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.NOT_RELATED_USER_TO_UPDATE_PRIVATE_CONVERSATION_SETTING));
                    }
                    return conversationSettingsRepository
                            .findSettingFields(ownerId, userId, finalImmutableSettingsForUpsert)
                            .collect(CollectorUtil.toSet(finalImmutableSettingsForUpsert.size()))
                            .flatMap(existingSettings -> {
                                if (existingSettings.isEmpty()) {
                                    Map<String, Object> parsedSettings = parseSettings(settings);
                                    return conversationSettingsRepository
                                            .upsertSettings(ownerId, userId, parsedSettings);
                                }
                                finalImmutableSettingsForUpsert.removeIf(
                                        settingName -> !existingSettings.contains(settingName));
                                if (finalImmutableSettingsForUpsert.isEmpty()) {
                                    Map<String, Object> parsedSettings = parseSettings(settings);
                                    return conversationSettingsRepository
                                            .upsertSettings(ownerId, userId, parsedSettings);
                                }
                                List<String> sortedConflictedSettings =
                                        new ArrayList<>(finalImmutableSettingsForUpsert);
                                sortedConflictedSettings.sort(null);
                                return Mono.error(
                                        ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                                "Cannot update existing immutable settings: "
                                                        + sortedConflictedSettings));
                            })
                            .map(updateResult -> updateResult.getModifiedCount() > 0
                                    || updateResult.getUpsertedId() != null);
                });
    }

    public Mono<Boolean> upsertGroupConversationSettings(
            Long ownerId,
            Long groupId,
            Map<String, Value> settings) {
        try {
            Validator.notNull(ownerId, "ownerId");
            Validator.notNull(groupId, "groupId");
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
            return groupMemberService.isGroupMember(groupId, ownerId, false)
                    .flatMap(isGroupMember -> {
                        if (!isGroupMember) {
                            return Mono.error(ResponseException.get(
                                    ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_CONVERSATION_SETTING));
                        }
                        Map<String, Object> parsedSettings = parseSettings(settings);
                        return conversationSettingsRepository
                                .upsertSettings(ownerId,
                                        getTargetIdFromGroupId(groupId),
                                        parsedSettings)
                                .map(updateResult -> updateResult.getModifiedCount() > 0
                                        || updateResult.getUpsertedId() != null);
                    });
        }
        Set<String> finalImmutableSettingsForUpsert = immutableSettingsForUpsert;
        return groupMemberService.isGroupMember(groupId, ownerId, false)
                .flatMap(hasOneSidedRelationship -> {
                    if (!hasOneSidedRelationship) {
                        return Mono.error(ResponseException.get(
                                ResponseStatusCode.NOT_GROUP_MEMBER_TO_UPDATE_GROUP_CONVERSATION_SETTING));
                    }
                    return conversationSettingsRepository
                            .findSettingFields(ownerId,
                                    getTargetIdFromGroupId(groupId),
                                    finalImmutableSettingsForUpsert)
                            .collect(CollectorUtil.toSet(finalImmutableSettingsForUpsert.size()))
                            .flatMap(existingSettings -> {
                                if (existingSettings.isEmpty()) {
                                    Map<String, Object> parsedSettings = parseSettings(settings);
                                    return conversationSettingsRepository.upsertSettings(ownerId,
                                            getTargetIdFromGroupId(groupId),
                                            parsedSettings);
                                }
                                finalImmutableSettingsForUpsert.removeIf(
                                        settingName -> !existingSettings.contains(settingName));
                                if (finalImmutableSettingsForUpsert.isEmpty()) {
                                    Map<String, Object> parsedSettings = parseSettings(settings);
                                    return conversationSettingsRepository.upsertSettings(ownerId,
                                            getTargetIdFromGroupId(groupId),
                                            parsedSettings);
                                }
                                List<String> sortedConflictedSettings =
                                        new ArrayList<>(finalImmutableSettingsForUpsert);
                                sortedConflictedSettings.sort(null);
                                return Mono.error(
                                        ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                                "Cannot update existing immutable settings: "
                                                        + sortedConflictedSettings));
                            })
                            .map(updateResult -> updateResult.getModifiedCount() > 0
                                    || updateResult.getUpsertedId() != null);
                });
    }

    public Mono<Boolean> deleteSettings(
            Collection<Long> ownerIds,
            @Nullable ClientSession clientSession) {
        try {
            Validator.notNull(ownerIds, "ownerIds");
        } catch (ResponseException e) {
            return Mono.error(e);
        }
        return conversationSettingsRepository.deleteByOwnerIds(ownerIds, clientSession)
                .map(deleteResult -> deleteResult.getDeletedCount() > 0);
    }

    public Mono<Boolean> unsetSettings(
            Long ownerId,
            @Nullable Set<Long> userIds,
            @Nullable Set<Long> groupIds,
            @Nullable Set<String> settingNames) {
        if (settingNames == null || settingNames.isEmpty()) {
            if (deletableSettings.isEmpty()) {
                return PublisherPool.FALSE;
            }
            return conversationSettingsRepository
                    .unsetSettings(ownerId, getTargetIds(userIds, groupIds), deletableSettings)
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
            return conversationSettingsRepository
                    .unsetSettings(ownerId, getTargetIds(userIds, groupIds), settingNames)
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
        return conversationSettingsRepository
                .unsetSettings(ownerId, getTargetIds(userIds, groupIds), settingNames)
                .map(updateResult -> updateResult.getModifiedCount() > 0);
    }

    @Nullable
    private List<Long> getTargetIds(Set<Long> userIds, Set<Long> groupIds) {
        int userIdCount = CollectionUtil.getSize(userIds);
        int groupIdCount = CollectionUtil.getSize(groupIds);
        int count = userIdCount + groupIdCount;
        List<Long> targetIds;
        if (count == 0) {
            return null;
        }
        targetIds = new ArrayList<>(count);
        if (userIdCount > 0) {
            targetIds.addAll(userIds);
        }
        if (groupIdCount > 0) {
            for (Long groupId : groupIds) {
                targetIds.add(getTargetIdFromGroupId(groupId));
            }
        }
        return targetIds;
    }

    public Flux<ConversationSettings> querySettings(
            Long ownerId,
            @Nullable Collection<Long> userIds,
            @Nullable Collection<Long> groupIds,
            @Nullable Set<String> settingNames,
            @Nullable Date lastUpdatedDateStart) {
        try {
            Validator.notNull(ownerId, "ownerId");
        } catch (ResponseException e) {
            return Flux.error(e);
        }
        Collection<ConversationSettings.Key> keys;
        int groupIdCount = CollectionUtil.getSize(groupIds);
        int userIdCount = CollectionUtil.getSize(userIds);
        if (userIdCount > 0) {
            if (groupIdCount > 0) {
                keys = new ArrayList<>(groupIdCount + userIdCount);
                for (Long groupId : groupIds) {
                    keys.add(
                            new ConversationSettings.Key(ownerId, getTargetIdFromGroupId(groupId)));
                }
            } else {
                keys = new ArrayList<>(userIdCount);
            }
            for (Long userId : userIds) {
                keys.add(new ConversationSettings.Key(ownerId, userId));
            }
            return conversationSettingsRepository
                    .findByIdAndSettingNames(keys, settingNames, lastUpdatedDateStart);
        } else if (groupIdCount > 0) {
            keys = new ArrayList<>(groupIdCount);
            for (Long groupId : groupIds) {
                keys.add(new ConversationSettings.Key(ownerId, getTargetIdFromGroupId(groupId)));
            }
            return conversationSettingsRepository
                    .findByIdAndSettingNames(keys, settingNames, lastUpdatedDateStart);
        } else {
            return conversationSettingsRepository
                    .findByIdAndSettingNames(ownerId, settingNames, lastUpdatedDateStart);
        }
    }

    private Long getTargetIdFromGroupId(Long groupId) {
        return -groupId;
    }
}