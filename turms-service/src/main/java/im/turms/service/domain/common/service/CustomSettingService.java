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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingDoubleValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingIntValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingLongValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingStringValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingValueProperties;
import im.turms.service.infra.locale.LocaleUtil;

/**
 * @author James Chen
 */
public abstract class CustomSettingService {

    protected List<CustomSettingProperties> settingPropertiesList;
    protected Set<String> immutableSettings;
    protected Set<String> deletableSettings;

    protected void updateGlobalProperties(List<CustomSettingProperties> settingPropertiesList) {
        int size = settingPropertiesList.size();
        if (0 == size) {
            this.settingPropertiesList = Collections.emptyList();
            immutableSettings = Collections.emptySet();
            deletableSettings = Collections.emptySet();
            return;
        }
        List<CustomSettingProperties> newSettingsPropertiesList = null;
        Set<String> newImmutableSettings = null;
        Set<String> newDeletableSettings = null;
        for (int i = 0; i < size; i++) {
            CustomSettingProperties settingProperties = settingPropertiesList.get(i);
            String sourceName = settingProperties.getSourceName();
            String storedName = settingProperties.getStoredName();
            if (StringUtil.isEmpty(storedName)) {
                if (newSettingsPropertiesList == null) {
                    newSettingsPropertiesList = new ArrayList<>(settingPropertiesList);
                }
                newSettingsPropertiesList.set(i,
                        settingProperties.toBuilder()
                                .storedName(sourceName)
                                .build());
            }
            if (settingProperties.isImmutable()) {
                if (newImmutableSettings == null) {
                    newImmutableSettings = new UnifiedSet<>(4);
                }
                newImmutableSettings.add(sourceName);
            }
            if (settingProperties.isDeletable()) {
                if (newDeletableSettings == null) {
                    newDeletableSettings = new UnifiedSet<>(4);
                }
                newDeletableSettings.add(sourceName);
            }
        }

        this.settingPropertiesList = newSettingsPropertiesList == null
                ? settingPropertiesList
                : newSettingsPropertiesList;
        this.immutableSettings = newImmutableSettings == null
                ? Collections.emptySet()
                : newImmutableSettings;
        this.deletableSettings = newDeletableSettings == null
                ? Collections.emptySet()
                : newDeletableSettings;
    }

    /**
     * @param propertiesList we assume the properties are valid (i.e. both source name and stored
     *                       name are not blank).
     */
    protected Map<String, Object> parseSettings(
            List<CustomSettingProperties> propertiesList,
            Map<String, Value> inputSettings) {
        if (propertiesList.isEmpty() || inputSettings.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<String, Object> outputSettings =
                CollectionUtil.newMapWithExpectedSize(propertiesList.size());
        for (CustomSettingProperties properties : propertiesList) {
            String sourceName = properties.getSourceName();
            Value value = inputSettings.get(sourceName);
            if (value == null) {
                continue;
            }
            Object parsedValue = parseValue(properties.getValue(), sourceName, value);
            outputSettings.put(properties.getStoredName(), parsedValue);
        }
        return outputSettings;
    }

    private Object parseValue(
            CustomSettingValueProperties valueProperties,
            String sourceName,
            Value value) {
        return switch (valueProperties.getType()) {
            case INT -> {
                CustomSettingIntValueProperties properties = valueProperties.getIntValue();
                int min = properties.getMin();
                int max = properties.getMax();
                int val = switch (value.getKindCase()) {
                    case INT32_VALUE -> value.getInt32Value();
                    case INT64_VALUE -> (int) value.getInt64Value();
                    default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                };
                if (val > max || val < min) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
                yield val;
            }
            case LONG -> {
                CustomSettingLongValueProperties properties = valueProperties.getLongValue();
                long min = properties.getMin();
                long max = properties.getMax();
                long val = switch (value.getKindCase()) {
                    case INT32_VALUE -> value.getInt32Value();
                    case INT64_VALUE -> value.getInt64Value();
                    default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                };
                if (val > max || val < min) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
                yield val;
            }
            case DOUBLE -> {
                CustomSettingDoubleValueProperties properties = valueProperties.getDoubleValue();
                double min = properties.getMin();
                double max = properties.getMax();
                double val = switch (value.getKindCase()) {
                    case FLOAT_VALUE -> value.getFloatValue();
                    case DOUBLE_VALUE -> value.getDoubleValue();
                    default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                };
                if (val > max || val < min) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
                yield val;
            }
            case STRING -> {
                CustomSettingStringValueProperties properties = valueProperties.getStringValue();
                int minLength = properties.getMinLength();
                int maxLength = properties.getMaxLength();
                List<Pattern> parsedRegexes = properties.getParsedRegexes();
                String val = switch (value.getKindCase()) {
                    case INT32_VALUE -> String.valueOf(value.getInt32Value());
                    case INT64_VALUE -> String.valueOf(value.getInt64Value());
                    case FLOAT_VALUE -> String.valueOf(value.getFloatValue());
                    case DOUBLE_VALUE -> String.valueOf(value.getDoubleValue());
                    case BOOL_VALUE -> String.valueOf(value.getBoolValue());
                    case STRING_VALUE -> value.getStringValue();
                    default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be an int32, int64, float, double, bool or string");
                };
                int length = val.length();
                if (length < minLength || length > maxLength) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The string value length of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + minLength
                                    + ", "
                                    + maxLength
                                    + "]");
                }
                if (!parsedRegexes.isEmpty()) {
                    for (Pattern regex : parsedRegexes) {
                        if (!regex.matcher(val)
                                .matches()) {
                            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                    "The string value of the setting \""
                                            + sourceName
                                            + "\" must match: "
                                            + regex.pattern());
                        }
                    }
                }
                yield val;
            }
            case BOOL -> switch (value.getKindCase()) {
                case BOOL_VALUE -> value.getBoolValue();
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The value of the setting \""
                                + sourceName
                                + "\" must be a bool");
            };
            case LANGUAGE -> {
                String languageId = value.getStringValue();
                if (!LocaleUtil.isAvailableLanguage(languageId)) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be a valid language ID");
                }
                yield languageId;
            }
        };
    }
}