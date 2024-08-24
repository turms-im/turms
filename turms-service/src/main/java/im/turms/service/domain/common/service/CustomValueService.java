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
import java.util.Set;
import java.util.regex.Pattern;

import im.turms.server.common.access.client.dto.model.common.Value;
import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.domain.common.service.BaseService;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.property.env.service.business.common.customvalue.CustomArrayValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.customvalue.CustomDoubleValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.customvalue.CustomIntValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.customvalue.CustomLongValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.customvalue.CustomStringValueProperties;
import im.turms.server.common.infra.property.env.service.business.common.customvalue.CustomValueOneOfProperties;
import im.turms.service.infra.locale.LocaleUtil;

/**
 * @author James Chen
 */
public abstract class CustomValueService extends BaseService {

    private final String exceptionMessagePrefixValueOf;
    private final String exceptionMessagePrefixString;
    private final String exceptionMessagePrefixStringLength;
    private final String exceptionMessagePrefixArray;

    public CustomValueService(
            String exceptionMessagePrefixValueOf,
            String exceptionMessagePrefixString,
            String exceptionMessagePrefixStringLength,
            String exceptionMessagePrefixArray) {
        this.exceptionMessagePrefixValueOf = exceptionMessagePrefixValueOf;
        this.exceptionMessagePrefixString = exceptionMessagePrefixString;
        this.exceptionMessagePrefixStringLength = exceptionMessagePrefixStringLength;
        this.exceptionMessagePrefixArray = exceptionMessagePrefixArray;
    }

    protected Object parseValue(
            CustomValueOneOfProperties oneOfProperties,
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
            CustomValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomIntValueProperties properties = oneOfProperties.getIntValue();
        Set<Integer> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case INT32_VALUE -> {
                    int val = value.getInt32Value();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
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
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
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
                    } catch (NumberFormatException e) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        exceptionMessagePrefixValueOf
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
                            exceptionMessagePrefixValueOf
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
                } catch (NumberFormatException e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            exceptionMessagePrefixValueOf
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
                            + sourceName
                            + "\" must be in ["
                            + min
                            + ", "
                            + max
                            + "]");
        };
        if (val > max || val < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
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
            CustomValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomLongValueProperties properties = oneOfProperties.getLongValue();
        Set<Long> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case INT32_VALUE -> {
                    long val = value.getInt32Value();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
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
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case STRING_VALUE -> {
                    long val;
                    try {
                        val = Long.parseLong(value.getStringValue());
                    } catch (NumberFormatException e) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        exceptionMessagePrefixValueOf
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
                } catch (NumberFormatException e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            exceptionMessagePrefixValueOf
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
                            + sourceName
                            + "\" must be in ["
                            + min
                            + ", "
                            + max
                            + "]");
        };
        if (val > max || val < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
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
            CustomValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomDoubleValueProperties properties = oneOfProperties.getDoubleValue();
        Set<Double> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case FLOAT_VALUE -> {
                    double val = value.getFloatValue();
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
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
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case STRING_VALUE -> {
                    double val;
                    try {
                        val = Double.parseDouble(value.getStringValue());
                    } catch (NumberFormatException e) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        exceptionMessagePrefixValueOf
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
                } catch (NumberFormatException e) {
                    throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                            exceptionMessagePrefixValueOf
                                    + sourceName
                                    + "\" must be in ["
                                    + min
                                    + ", "
                                    + max
                                    + "]");
                }
            }
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
                            + sourceName
                            + "\" must be in ["
                            + min
                            + ", "
                            + max
                            + "]");
        };
        if (val > max || val < min) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
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
                        exceptionMessagePrefixValueOf
                                + sourceName
                                + "\" must be a bool, or a string of \"true\" or \"false\"");
            }
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
                            + sourceName
                            + "\" must be a bool, or a string of \"true\" or \"false\"");
        };
    }

    private String parseStringValue(
            CustomValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomStringValueProperties properties = oneOfProperties.getStringValue();
        Set<String> allowedValues = properties.getAllowedValues();
        if (!allowedValues.isEmpty()) {
            return switch (value.getKindCase()) {
                case INT32_VALUE -> {
                    String val = Integer.toString(value.getInt32Value());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case INT64_VALUE -> {
                    String val = Long.toString(value.getInt64Value());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case FLOAT_VALUE -> {
                    String val = Float.toString((value.getFloatValue()));
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                case DOUBLE_VALUE -> {
                    String val = Double.toString(value.getDoubleValue());
                    if (!allowedValues.contains(val)) {
                        throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                                exceptionMessagePrefixValueOf
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
                                exceptionMessagePrefixValueOf
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
                                exceptionMessagePrefixValueOf
                                        + sourceName
                                        + "\" must be one of "
                                        + allowedValues);
                    }
                    yield val;
                }
                default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                        exceptionMessagePrefixValueOf
                                + sourceName
                                + "\" must be one of "
                                + allowedValues);
            };
        }
        int minLength = properties.getMinLength();
        int maxLength = properties.getMaxLength();
        List<Pattern> parsedRegexes = properties.getParsedRegexes();
        String val = switch (value.getKindCase()) {
            case INT32_VALUE -> Integer.toString(value.getInt32Value());
            case INT64_VALUE -> Long.toString(value.getInt64Value());
            case FLOAT_VALUE -> Float.toString(value.getFloatValue());
            case DOUBLE_VALUE -> Double.toString(value.getDoubleValue());
            case BOOL_VALUE -> String.valueOf(value.getBoolValue());
            case STRING_VALUE -> value.getStringValue();
            default -> throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixValueOf
                            + sourceName
                            + "\" must be an int32, int64, float, double, bool or string");
        };
        int length = val.length();
        if (length < minLength || length > maxLength) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixStringLength
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
                            exceptionMessagePrefixString
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
                    exceptionMessagePrefixValueOf
                            + sourceName
                            + "\" must be a valid language ID");
        }
        return languageId;
    }

    private Collection<Object> parseArrayValue(
            CustomValueOneOfProperties oneOfProperties,
            String sourceName,
            Value value) {
        CustomArrayValueProperties arrayValueProperties = oneOfProperties.getArrayValue();
        List<Value> values = value.getListValueList();
        if (values.isEmpty()) {
            return Collections.emptyList();
        }
        Collection<Object> result = arrayValueProperties.isUnique()
                ? LinkedHashSet.newLinkedHashSet(values.size())
                : new ArrayList<>(values.size());
        CustomValueOneOfProperties elementProperties = arrayValueProperties.getElement();
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
                            exceptionMessagePrefixArray
                                    + sourceName
                                    + "\" must not contain null element");
                }
                result.add(parseValue(elementProperties, sourceName, val));
            }
        }
        int minElementCount = arrayValueProperties.getMinElementCount();
        if (minElementCount > result.size()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixArray
                            + sourceName
                            + "\" must have at least "
                            + minElementCount
                            + " elements");
        }
        int maxElementCount = arrayValueProperties.getMaxElementCount();
        if (maxElementCount < result.size()) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT,
                    exceptionMessagePrefixArray
                            + sourceName
                            + "\" must have at most "
                            + maxElementCount
                            + " elements");
        }
        return result;
    }
}