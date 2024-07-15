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
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;

import org.eclipse.collections.impl.set.mutable.UnifiedSet;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingArrayValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingDoubleValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingIntValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingLongValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingStringValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingValueOneOfProperties;
import im.turms.server.common.infra.property.env.service.business.common.setting.CustomSettingsProperties;
import im.turms.service.infra.locale.LocaleUtil;

/**
 * @author James Chen
 */
public abstract class CustomSettingService extends BaseService {

    protected Map<String, CustomSettingProperties> sourceNameToSettingProperties =
            Collections.emptyMap();
    protected Set<String> immutableSettings = Collections.emptySet();
    protected Set<String> deletableSettings = Collections.emptySet();
    protected Map<String, Boolean> settingToDeletable = Collections.emptyMap();
    protected boolean ignoreUnknownSettingsOnUpsert;
    protected boolean ignoreUnknownSettingsOnDelete;

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
                            "Found a duplicate setting: "
                                    + sourceName);
                }
            } else if (newSourceNameToSettingProperties.put(storedName,
                    settingProperties) != null) {
                throw new IllegalArgumentException(
                        "Found a duplicate setting: "
                                + storedName);
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

    protected Map<String, Object> parseSettings(Map<String, Value> inputSettings) {
        if (inputSettings.isEmpty()) {
            return Collections.emptyMap();
        }
        if (sourceNameToSettingProperties.isEmpty()) {
            if (ignoreUnknownSettingsOnUpsert) {
                return Collections.emptyMap();
            }
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "Unknown settings: "
                            + inputSettings.keySet());
        }
        Map<String, Object> outputSettings =
                CollectionUtil.newMapWithExpectedSize(inputSettings.size());
        int inputSettingCount = inputSettings.size();
        if (inputSettingCount <= sourceNameToSettingProperties.size()) {
            if (ignoreUnknownSettingsOnUpsert) {
                for (Map.Entry<String, Value> entry : inputSettings.entrySet()) {
                    String sourceName = entry.getKey();
                    CustomSettingProperties settingProperties =
                            sourceNameToSettingProperties.get(sourceName);
                    if (settingProperties == null) {
                        continue;
                    }
                    outputSettings.put(sourceName,
                            parseValue(settingProperties.getValue(), sourceName, entry.getValue()));
                }
            } else {
                for (Map.Entry<String, Value> entry : inputSettings.entrySet()) {
                    String sourceName = entry.getKey();
                    CustomSettingProperties settingProperties =
                            sourceNameToSettingProperties.get(sourceName);
                    if (settingProperties == null) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "Unknown setting: "
                                        + sourceName);
                    }
                    outputSettings.put(sourceName,
                            parseValue(settingProperties.getValue(), sourceName, entry.getValue()));
                }
            }
        } else {
            if (ignoreUnknownSettingsOnUpsert) {
                for (Map.Entry<String, CustomSettingProperties> sourceNameAndSettingProperties : sourceNameToSettingProperties
                        .entrySet()) {
                    String sourceName = sourceNameAndSettingProperties.getKey();
                    Value value = inputSettings.get(sourceName);
                    if (value == null) {
                        continue;
                    }
                    outputSettings.put(sourceName,
                            parseValue(sourceNameAndSettingProperties.getValue()
                                    .getValue(), sourceName, value));
                }
            } else {
                for (Map.Entry<String, CustomSettingProperties> sourceNameAndSettingProperties : sourceNameToSettingProperties
                        .entrySet()) {
                    String sourceName = sourceNameAndSettingProperties.getKey();
                    Value value = inputSettings.get(sourceName);
                    if (value == null) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "Missing setting: "
                                        + sourceName);
                    }
                }
                throw new IllegalStateException("Should not reach here");
            }
        }
        return outputSettings;
    }

    private Object parseValue(
            CustomSettingValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        return switch (oneOfProperties.getType()) {
            case INT -> parseIntValue(oneOfProperties, sourceName, value);
            case LONG -> parseLongValue(oneOfProperties, sourceName, value);
            case DOUBLE -> parseDoubleValue(oneOfProperties, sourceName, value);
            case BOOL -> parseBoolValue(sourceName, value);
            case STRING -> parseStringValue(oneOfProperties, sourceName, value);
            case LANGUAGE -> parseLanguageValue(sourceName, value);
            case ARRAY -> parseArrayValue(oneOfProperties, sourceName, value);
        };
    }

    private int parseIntValue(
            CustomSettingValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomSettingIntValueProperties properties = oneOfProperties.getIntValue();
        Set<Integer> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case INT32_VALUE -> {
                    int val = value.getInt32Value();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case INT64_VALUE -> {
                    int val;
                    try {
                        val = Math.toIntExact(value.getInt64Value());
                    } catch (Exception e) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case STRING_VALUE -> {
                    int val;
                    try {
                        val = Integer.parseInt(value.getStringValue());
                    } catch (Exception e) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The value of the setting \""
                                + sourceName
                                + "\" must be one of "
                                + allowedValues);
            };
        }
        int min = properties.getMin();
        int max = properties.getMax();
        int val = switch (value.getKindCase()) {
            case INT32_VALUE -> value.getInt32Value();
            case INT64_VALUE -> {
                try {
                    yield Math.toIntExact(value.getInt64Value());
                } catch (Exception e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
            case STRING_VALUE -> {
                try {
                    yield Integer.parseInt(value.getStringValue());
                } catch (Exception e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
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
        return val;
    }

    private long parseLongValue(
            CustomSettingValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomSettingLongValueProperties properties = oneOfProperties.getLongValue();
        Set<Long> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case INT32_VALUE -> {
                    long val = value.getInt32Value();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case INT64_VALUE -> {
                    long val = value.getInt64Value();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case STRING_VALUE -> {
                    long val = Long.parseLong(value.getStringValue());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The value of the setting \""
                                + sourceName
                                + "\" must be one of "
                                + allowedValues);
            };
        }
        long min = properties.getMin();
        long max = properties.getMax();
        long val = switch (value.getKindCase()) {
            case INT32_VALUE -> value.getInt32Value();
            case INT64_VALUE -> value.getInt64Value();
            case STRING_VALUE -> {
                try {
                    yield Long.parseLong(value.getStringValue());
                } catch (Exception e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
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
        return val;
    }

    private double parseDoubleValue(
            CustomSettingValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomSettingDoubleValueProperties properties = oneOfProperties.getDoubleValue();
        Set<Double> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case FLOAT_VALUE -> {
                    double val = value.getFloatValue();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case DOUBLE_VALUE -> {
                    double val = value.getDoubleValue();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case STRING_VALUE -> {
                    double val = Double.parseDouble(value.getStringValue());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The value of the setting \""
                                + sourceName
                                + "\" must be one of "
                                + allowedValues);
            };
        }
        double min = properties.getMin();
        double max = properties.getMax();
        double val = switch (value.getKindCase()) {
            case FLOAT_VALUE -> value.getFloatValue();
            case DOUBLE_VALUE -> value.getDoubleValue();
            case STRING_VALUE -> {
                try {
                    yield Double.parseDouble(value.getStringValue());
                } catch (Exception e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The value of the setting \""
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
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
        return val;
    }

    private boolean parseBoolValue(String sourceName, Value value) {
        return switch (value.getKindCase()) {
            case BOOL_VALUE -> value.getBoolValue();
            case STRING_VALUE -> {
                String val = value.getStringValue();
                if (val.equalsIgnoreCase("true")) {
                    yield true;
                } else if (val.equalsIgnoreCase("false")) {
                    yield false;
                }
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The value of the setting \""
                                + sourceName
                                + "\" must be a bool, or a string of \"true\" or \"false\"");
            }
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The value of the setting \""
                            + sourceName
                            + "\" must be a bool, or a string of \"true\" or \"false\"");
        };
    }

    private String parseStringValue(
            CustomSettingValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomSettingStringValueProperties properties = oneOfProperties.getStringValue();
        Set<String> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case INT32_VALUE -> {
                    String val = String.valueOf(value.getInt32Value());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case INT64_VALUE -> {
                    String val = String.valueOf(value.getInt64Value());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case FLOAT_VALUE -> {
                    String val = String.valueOf(value.getFloatValue());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case DOUBLE_VALUE -> {
                    String val = String.valueOf(value.getDoubleValue());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case BOOL_VALUE -> {
                    String val = String.valueOf(value.getBoolValue());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case STRING_VALUE -> {
                    String val = value.getStringValue();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                "The value of the setting \""
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        "The value of the setting \""
                                + sourceName
                                + "\" must be one of "
                                + allowedValues);
            };
        }
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
        return val;
    }

    private String parseLanguageValue(String sourceName, Value value) {
        String languageId = value.getStringValue();
        if (!LocaleUtil.isAvailableLanguage(languageId)) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The value of the setting \""
                            + sourceName
                            + "\" must be a valid language ID");
        }
        return languageId;
    }

    private Collection<Object> parseArrayValue(
            CustomSettingValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomSettingArrayValueProperties arrayValueProperties = oneOfProperties.getArrayValue();
        List<Value> values = value.getListValueList();
        if (values.isEmpty()) {
            return Collections.emptyList();
        }
        Collection<Object> result = arrayValueProperties.isUnique()
                ? LinkedHashSet.newLinkedHashSet(values.size())
                : new ArrayList<>(values.size());
        CustomSettingValueOneOfProperties elementProperties = arrayValueProperties.getElement();
        if (arrayValueProperties.isAllowNullElement()) {
            for (Value val : values) {
                if (val.getKindCase() == Value.KindCase.KIND_NOT_SET) {
                    result.add(null);
                } else {
                    result.add(parseValue(elementProperties, sourceName, val));
                }
            }
        } else {
            for (Value val : values) {
                if (val.getKindCase() == Value.KindCase.KIND_NOT_SET) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            "The array value of the setting \""
                                    + sourceName
                                    + "\" must not contain null element");
                }
                result.add(parseValue(elementProperties, sourceName, val));
            }
        }
        int minElementCount = arrayValueProperties.getMinElementCount();
        if (minElementCount > result.size()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The array value of the setting \""
                            + sourceName
                            + "\" must have at least "
                            + minElementCount
                            + " elements");
        }
        int maxElementCount = arrayValueProperties.getMaxElementCount();
        if (maxElementCount < result.size()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    "The array value of the setting \""
                            + sourceName
                            + "\" must have at most "
                            + maxElementCount
                            + " elements");
        }
        return result;
    }
}