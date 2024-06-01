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

package im.turms.server.common.infra.property;

import java.lang.reflect.Field;
import java.util.BitSet;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.Size;

import org.springframework.scheduling.support.CronExpression;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.lang.StringPattern;
import im.turms.server.common.infra.lang.StringUtil;
import im.turms.server.common.infra.validation.MatchesStringPattern;
import im.turms.server.common.infra.validation.ValidCron;
import im.turms.server.common.infra.validation.ValidRegex;

import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getFieldInfo;
import static im.turms.server.common.infra.property.TurmsPropertiesInspector.getFieldInfos;

/**
 * @author James Chen
 */
public class TurmsPropertiesValidator {

    private static final BitSet NUMERIC_CHAR_SET =
            CollectionUtil.newBitSet(StringUtil.NUMERIC_TABLE);
    private static final BitSet ALPHABETIC_CHAR_SET =
            CollectionUtil.newBitSet(StringUtil.ALPHABETIC_TABLE);
    private static final BitSet ALPHANUMERIC_CHAR_SET =
            CollectionUtil.newBitSet(StringUtil.ALPHANUMERIC_TABLE);

    private TurmsPropertiesValidator() {
    }

    /**
     * @implNote We don't use "hibernate-validator" because it has a terrible performance
     */
    @Nullable
    public static InvalidPropertyException validate(TurmsProperties properties) {
        List<String> errorMessages = validateProperties(properties);
        if (errorMessages.isEmpty()) {
            return null;
        }
        InvalidPropertyException root =
                new InvalidPropertyException("The properties contain invalid properties");
        for (String message : errorMessages) {
            root.addSuppressed(new InvalidPropertyException(message));
        }
        return root;
    }

    private static List<String> validateProperties(TurmsProperties properties) {
        List<String> errorMessages = new LinkedList<>();
        validateProperties(properties, errorMessages);
        return errorMessages;
    }

    private static void validateProperties(Object properties, List<String> errorMessages) {
        List<PropertyFieldInfo> propertyFieldInfos = getFieldInfos(properties.getClass());
        for (PropertyFieldInfo propertyFieldInfo : propertyFieldInfos) {
            if (propertyFieldInfo.isNestedProperty()) {
                Object nestedProperties = propertyFieldInfo.get(properties);
                validateProperties(nestedProperties, errorMessages);
            } else {
                validateProperty(properties, propertyFieldInfo, errorMessages);
            }
        }
    }

    private static void validateProperty(
            Object properties,
            PropertyFieldInfo fieldInfo,
            List<String> errorMessages) {
        PropertyConstraints constraints = fieldInfo.constraints();
        long min = constraints.min();
        long max = constraints.max();
        String lessThanOrEqualTo = constraints.lessThanOrEqualTo();
        Size size = constraints.size();
        ValidCron validCron = constraints.validCron();
        ValidRegex validRegex = constraints.validRegex();
        MatchesStringPattern matchesStringPattern = constraints.matchesStringPattern();
        if (min == Long.MIN_VALUE
                && max == Long.MAX_VALUE
                && lessThanOrEqualTo == null
                && size == null
                && validCron == null
                && validRegex == null
                && matchesStringPattern == null) {
            return;
        }
        Object value = fieldInfo.get(properties);
        Field field = fieldInfo.field();
        if (min != Long.MIN_VALUE || max != Long.MAX_VALUE) {
            validateMinMaxProperty(min, max, value, field, errorMessages);
        }
        if (lessThanOrEqualTo != null) {
            validateLessThanOrEqualTo(lessThanOrEqualTo, properties, value, field, errorMessages);
        }
        if (size != null) {
            validateSizeProperty(size, value, field, errorMessages);
        }
        if (validCron != null) {
            validateCronProperty(value, field, errorMessages);
        }
        if (validRegex != null) {
            validateRegexProperty(value, field, errorMessages);
        }
        if (matchesStringPattern != null) {
            validateStringPatternProperty(matchesStringPattern.value(),
                    value,
                    field,
                    errorMessages);
        }
    }

    private static void validateStringPatternProperty(
            StringPattern stringPattern,
            Object value,
            Field field,
            List<String> errorMessages) {
        if (!(value instanceof String str)) {
            throw new IllegalArgumentException(
                    "The value of the field ("
                            + ClassUtil.getReference(field)
                            + ") must be a string for string pattern validation");
        }
        switch (stringPattern) {
            case NUMERIC -> {
                if (!StringUtil.allCharsInSet(str, NUMERIC_CHAR_SET)) {
                    String message = "The property \""
                            + field.getName()
                            + "\" has an invalid string pattern \""
                            + str
                            + "\". Expected a numeric string";
                    errorMessages.add(message);
                }
            }
            case ALPHABETIC -> {
                if (!StringUtil.allCharsInSet(str, ALPHABETIC_CHAR_SET)) {
                    String message = "The property \""
                            + field.getName()
                            + "\" has an invalid string pattern \""
                            + str
                            + "\". Expected an alphabetic string";
                    errorMessages.add(message);
                }
            }
            case ALPHANUMERIC -> {
                if (!StringUtil.allCharsInSet(str, ALPHANUMERIC_CHAR_SET)) {
                    String message = "The property \""
                            + field.getName()
                            + "\" has an invalid string pattern \""
                            + str
                            + "\". Expected an alphanumeric string";
                    errorMessages.add(message);
                }
            }
        }
    }

    private static void validateMinMaxProperty(
            long min,
            long max,
            Object value,
            Field field,
            List<String> errorMessages) {
        if (!(value instanceof Number number)) {
            throw new IllegalArgumentException(
                    "The value of the field ("
                            + ClassUtil.getReference(field)
                            + ") must be a number for min and max validation");
        }
        long val = number.longValue();
        if (min > val) {
            String message = "The property \""
                    + field.getName()
                    + "\" must be greater than or equal to "
                    + min;
            errorMessages.add(message);
        }
        if (max < val) {
            String message = "The property \""
                    + field.getName()
                    + "\" must be less than or equal to "
                    + max;
            errorMessages.add(message);
        }
    }

    private static void validateLessThanOrEqualTo(
            String lessThanOrEqualTo,
            Object properties,
            Object value,
            Field field,
            List<String> errorMessages) {
        PropertyFieldInfo info = getFieldInfo(field.getDeclaringClass(), lessThanOrEqualTo);
        if (info == null) {
            throw new IllegalArgumentException(
                    "The field ("
                            + field.getDeclaringClass()
                                    .getName()
                            + "#"
                            + lessThanOrEqualTo
                            + ") does not exist");
        }
        if (!(value instanceof Number number)) {
            throw new IllegalArgumentException(
                    "The value of the field ("
                            + ClassUtil.getReference(field)
                            + ") must be a number for min and max validation");
        }
        Object valueToCompare = info.get(properties);
        Field fieldToCompare = info.field();
        if (!(valueToCompare instanceof Number numberToCompare)) {
            throw new IllegalArgumentException(
                    "The value of the field ("
                            + ClassUtil.getReference(fieldToCompare)
                            + ") must be a number for min and max validation");
        }
        if (number.longValue() > numberToCompare.longValue()) {
            String message = "The property \""
                    + field.getName()
                    + "\" must be less than or equal to the value of the property \""
                    + fieldToCompare.getName()
                    + "\"";
            errorMessages.add(message);
        }
    }

    private static void validateSizeProperty(
            Size size,
            Object value,
            Field field,
            List<String> errorMessages) {
        int min = size.min();
        int max = size.max();
        int count = -1;
        String propertyLengthName = null;
        if (value instanceof String str) {
            count = str.length();
            propertyLengthName = "length";
        } else if (value instanceof Collection<?> collection) {
            count = collection.size();
            propertyLengthName = "size";
        } else if (value instanceof Map<?, ?> map) {
            count = map.size();
            propertyLengthName = "size";
        }
        if (count == -1) {
            throw new IllegalArgumentException(
                    "The field \""
                            + field
                            + "\" must be a string, collection, or map for size validation");
        }
        if (min > count) {
            String message = "The "
                    + propertyLengthName
                    + " of property \""
                    + field.getName()
                    + "\" must be greater than or equal to "
                    + min;
            errorMessages.add(message);
        }
        if (max < count) {
            String message = "The "
                    + propertyLengthName
                    + " of property \""
                    + field.getName()
                    + "\" must be less than or equal to "
                    + max;
            errorMessages.add(message);
        }
    }

    private static void validateCronProperty(
            Object value,
            Field field,
            List<String> errorMessages) {
        if (!(value instanceof String str)) {
            throw new IllegalArgumentException(
                    "The value of the field ("
                            + ClassUtil.getReference(field)
                            + ") must be a string for cron validation");
        }
        if (!CronExpression.isValidExpression(str)) {
            String message = "The property \""
                    + field.getName()
                    + "\" has an invalid cron \""
                    + str
                    + "\"";
            errorMessages.add(message);
        }
    }

    private static void validateRegexProperty(
            Object value,
            Field field,
            List<String> errorMessages) {
        if (!(value instanceof String str)) {
            if (!(value instanceof Collection<?> collection)) {
                throw new IllegalArgumentException(
                        "The value of the field ("
                                + ClassUtil.getReference(field)
                                + ") must be a string or string list for regex validation");
            }
            for (Object val : collection) {
                validateRegexProperty(val, field, errorMessages);
            }
            return;
        }
        try {
            Pattern.compile(str);
        } catch (PatternSyntaxException exception) {
            String message = "The property \""
                    + field.getName()
                    + "\" has an invalid regex \""
                    + str
                    + "\"";
            errorMessages.add(message);
        }
    }

}