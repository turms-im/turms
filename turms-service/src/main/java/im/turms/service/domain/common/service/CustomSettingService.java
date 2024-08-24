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

package im.turms.service.domain.common.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingsProperties;

/**
 * @author James Chen
 */
public abstract class CustomSettingService extends CustomValueService {

    private static final String EXCEPTION_MESSAGE_PREFIX_FOUND_DUPLICATE_SETTING =
            "Found a duplicate setting: ";
    private static final String EXCEPTION_MESSAGE_PREFIX_UNKNOWN_SETTING = "Unknown setting: ";
    private static final String EXCEPTION_MESSAGE_PREFIX_UNKNOWN_SETTINGS = "Unknown settings: ";

    protected Map<String, CustomSettingProperties> sourceNameToSettingProperties =
            Collections.emptyMap();
    protected Set<String> immutableSettings = Collections.emptySet();
    protected Set<String> deletableSettings = Collections.emptySet();
    protected Map<String, Boolean> settingToDeletable = Collections.emptyMap();
    protected boolean ignoreUnknownSettingsOnUpsert;
    protected boolean ignoreUnknownSettingsOnDelete;

    protected CustomSettingService() {
        super("The value of the setting \"",
                "The string value of the setting \"",
                "The string value length of the setting \"",
                "The array value of the setting \"");
    }

    protected void updateGlobalProperties(CustomSettingsProperties properties) {
        List<CustomSettingProperties> settingPropertiesList = properties.getAllowedSettings();
        int size = settingPropertiesList.size();
        if (0 == size) {
            return;
        }
        Map<String, CustomSettingProperties> newSourceNameToSettingProperties =
                CollectionUtil.newMapWithExpectedSize(size);
        Map<String, Boolean> newSettingToDeletable = CollectionUtil.newMapWithExpectedSize(size);
        Set<String> newImmutableSettings = null;
        Set<String> newDeletableSettings = null;
        for (int i = 0; i < size; i++) {
            CustomSettingProperties settingProperties = settingPropertiesList.get(i);
            String sourceName = settingProperties.getSourceName();
            String storedName = settingProperties.getStoredName();
            if (StringUtil.isEmpty(storedName)) {
                if (newSourceNameToSettingProperties.put(sourceName,
                        settingProperties.toBuilder()
                                .storedName(sourceName)
                                .build()) != null) {
                    throw new IllegalArgumentException(
                            EXCEPTION_MESSAGE_PREFIX_FOUND_DUPLICATE_SETTING + sourceName);
                }
            } else if (newSourceNameToSettingProperties.put(storedName,
                    settingProperties) != null) {
                throw new IllegalArgumentException(
                        EXCEPTION_MESSAGE_PREFIX_FOUND_DUPLICATE_SETTING + storedName);
            }
            if (settingProperties.isImmutable()) {
                if (newImmutableSettings == null) {
                    newImmutableSettings = new UnifiedSet<>(4);
                }
                newImmutableSettings.add(sourceName);
            }
            boolean deletable = settingProperties.isDeletable();
            newSettingToDeletable.put(sourceName, deletable);
            if (deletable) {
                if (newDeletableSettings == null) {
                    newDeletableSettings = new UnifiedSet<>(4);
                }
                newDeletableSettings.add(sourceName);
            }
        }

        this.sourceNameToSettingProperties = newSourceNameToSettingProperties;
        this.immutableSettings = newImmutableSettings == null
                ? Collections.emptySet()
                : newImmutableSettings;
        this.deletableSettings = newDeletableSettings == null
                ? Collections.emptySet()
                : newDeletableSettings;
        this.settingToDeletable = newSettingToDeletable;
        this.ignoreUnknownSettingsOnUpsert = properties.isIgnoreUnknownSettingsOnUpsert();
        this.ignoreUnknownSettingsOnDelete = properties.isIgnoreUnknownSettingsOnDelete();
    }

    protected Map<String, Object> parseSettings(
            boolean ignoreUnknownSettings,
            Map<String, Value> inputSettings) {
        if (inputSettings.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, CustomSettingProperties> localSourceNameToSettingProperties =
                sourceNameToSettingProperties;
        if (localSourceNameToSettingProperties.isEmpty()) {
            if (ignoreUnknownSettings) {
                return Collections.emptyMap();
            }
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    EXCEPTION_MESSAGE_PREFIX_UNKNOWN_SETTINGS
                            + new TreeSet<>(inputSettings.keySet()));
        }
        int inputSettingCount = inputSettings.size();
        Map<String, Object> outputSettings =
                CollectionUtil.newMapWithExpectedSize(inputSettingCount);
        if (inputSettingCount <= localSourceNameToSettingProperties.size()) {
            if (ignoreUnknownSettings) {
                for (Map.Entry<String, Value> entry : inputSettings.entrySet()) {
                    String sourceName = entry.getKey();
                    CustomSettingProperties settingProperties =
                            localSourceNameToSettingProperties.get(sourceName);
                    if (settingProperties == null) {
                        continue;
                    }
                    outputSettings.put(settingProperties.getStoredName(),
                            parseValue(settingProperties.getValue(), sourceName, entry.getValue()));
                }
            } else {
                for (Map.Entry<String, Value> entry : inputSettings.entrySet()) {
                    String sourceName = entry.getKey();
                    CustomSettingProperties settingProperties =
                            localSourceNameToSettingProperties.get(sourceName);
                    if (settingProperties == null) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                EXCEPTION_MESSAGE_PREFIX_UNKNOWN_SETTING + sourceName);
                    }
                    outputSettings.put(settingProperties.getStoredName(),
                            parseValue(settingProperties.getValue(), sourceName, entry.getValue()));
                }
            }
        } else {
            if (!ignoreUnknownSettings) {
                Set<String> unknownSettingNames = new TreeSet<>();
                for (String sourceName : inputSettings.keySet()) {
                    if (!localSourceNameToSettingProperties.containsKey(sourceName)) {
                        unknownSettingNames.add(sourceName);
                    }
                }
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        EXCEPTION_MESSAGE_PREFIX_UNKNOWN_SETTINGS + unknownSettingNames);
            }
            for (Map.Entry<String, CustomSettingProperties> sourceNameAndSettingProperties : localSourceNameToSettingProperties
                    .entrySet()) {
                String sourceName = sourceNameAndSettingProperties.getKey();
                Value value = inputSettings.get(sourceName);
                if (value == null) {
                    continue;
                }
                CustomSettingProperties settingProperties =
                        sourceNameAndSettingProperties.getValue();
                outputSettings.put(settingProperties.getStoredName(),
                        parseValue(settingProperties.getValue(), sourceName, value));
            }
        }
        return outputSettings;
    }
}