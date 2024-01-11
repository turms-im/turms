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

package im.turms.gateway.infra.ldap.element.operation.search;

import jakarta.annotation.Nullable;

import im.turms.gateway.infra.ldap.LdapException;
import im.turms.gateway.infra.ldap.asn1.BerBuffer;
import im.turms.gateway.infra.ldap.element.common.ResultCode;
import im.turms.server.common.infra.lang.ByteUtil;

/**
 * @author James Chen
 * @implSpec <a href="https://tools.ietf.org/html/rfc4515"> RFC 4515: String Representation of
 *           Search Filters</a>
 */
public final class Filter {

    static final int TYPE_AND = 0xA0;
    static final int TYPE_OR = 0xA1;
    static final int TYPE_NOT = 0xA2;
    static final int TYPE_EQUALITY = 0xA3;
    static final int TYPE_SUBSTRING = 0xA4;
    static final int TYPE_GREATER = 0xA5;
    static final int TYPE_LESS = 0xA6;
    static final int TYPE_APPROXIMATE = 0xA8;
    static final int TYPE_EXTENSIBLE_MATCH = 0xA9;
    static final int TYPE_PRESENT = 0x87;

    static final int EXTENSIBLE_MATCHING_RULE = 0x81;
    static final int EXTENSIBLE_MATCHING_TYPE = 0x82;
    static final int EXTENSIBLE_MATCHING_VALUE = 0x83;
    static final int EXTENSIBLE_MATCHING_DN = 0x84;

    static final int SUBSTRING_INITIAL = 0x80;
    static final int SUBSTRING_ANY = 0x81;
    static final int SUBSTRING_FINAL = 0x82;

    private Filter() {
    }

    public static void write(BerBuffer buffer, String filter) {
        byte[] filterBytes = filter.getBytes();
        writeFilter(buffer, filterBytes, filterBytes.length);
    }

    private static void writeFilter(BerBuffer buffer, byte[] filter, int filterEndIndex) {
        int currentFilterEndIndex;
        int balance;
        boolean escape;
        int currentParensIndex = 0;
        Context context = new Context();

        for (context.readIndex = 0; context.readIndex < filterEndIndex;) {
            switch (filter[context.readIndex]) {
                case '(' -> {
                    context.readIndex++;
                    currentParensIndex++;
                    switch (filter[context.readIndex]) {
                        case '&':
                            writeFilterSet(buffer, filter, TYPE_AND, context, filterEndIndex);
                            currentParensIndex--;
                            break;
                        case '|':
                            writeFilterSet(buffer, filter, TYPE_OR, context, filterEndIndex);
                            currentParensIndex--;
                            break;
                        case '!':
                            writeFilterSet(buffer, filter, TYPE_NOT, context, filterEndIndex);
                            currentParensIndex--;
                            break;
                        default:
                            balance = 1;
                            escape = false;
                            currentFilterEndIndex = context.readIndex;
                            while (currentFilterEndIndex < filterEndIndex && balance > 0) {
                                if (!escape) {
                                    if (filter[currentFilterEndIndex] == '(') {
                                        balance++;
                                    } else if (filter[currentFilterEndIndex] == ')') {
                                        balance--;
                                    }
                                }
                                escape = filter[currentFilterEndIndex] == '\\' && !escape;
                                if (balance > 0) {
                                    currentFilterEndIndex++;
                                }
                            }
                            if (balance != 0) {
                                throw new LdapException(
                                        ResultCode.FILTER_ERROR,
                                        "Unbalanced parenthesis");
                            }
                            writeFilter(buffer, filter, context.readIndex, currentFilterEndIndex);
                            context.readIndex = currentFilterEndIndex + 1;
                            currentParensIndex--;
                            break;
                    }
                }
                case ')' -> {
                    buffer.endSequence();
                    context.readIndex++;
                    currentParensIndex--;
                }
                case ' ' -> context.readIndex++;
                default -> {
                    writeFilter(buffer, filter, context.readIndex, filterEndIndex);
                    context.readIndex = filterEndIndex;
                }
            }
            if (currentParensIndex < 0) {
                throw new LdapException(ResultCode.FILTER_ERROR, "Unbalanced parenthesis");
            }
        }
        if (currentParensIndex != 0) {
            throw new LdapException(ResultCode.FILTER_ERROR, "Unbalanced parenthesis");
        }
    }

    @Nullable
    static byte[] unescapeFilterValue(byte[] filter, int start, int end) {
        boolean escape = false;
        boolean escapeStart = false;
        boolean hasEscapeChar = false;
        int number;
        byte filterChar;

        int length = end - start;
        byte[] resultBytes = null;
        int resultWriterIndex = 0;
        for (int i = start; i < end; i++) {
            filterChar = filter[i];
            if (escape) {
                if ((number = Character.digit(filterChar, 16)) < 0) {
                    throw new LdapException(ResultCode.FILTER_ERROR, "Invalid escape filter");
                }
                if (escapeStart) {
                    resultBytes[resultWriterIndex] = (byte) (number << 4);
                    escapeStart = false;
                } else {
                    resultBytes[resultWriterIndex++] |= (byte) number;
                    escape = false;
                }
            } else if (filterChar == '\\') {
                escapeStart = escape = true;
                if (!hasEscapeChar) {
                    resultBytes = new byte[length];
                    int lengthToCopy = i - start;
                    resultWriterIndex = lengthToCopy;
                    System.arraycopy(filter, start, resultBytes, 0, lengthToCopy);
                    hasEscapeChar = true;
                }
            } else {
                if (hasEscapeChar) {
                    resultBytes[resultWriterIndex++] = filterChar;
                }
                escape = false;
            }
        }
        if (hasEscapeChar) {
            byte[] result = new byte[resultWriterIndex];
            System.arraycopy(resultBytes, 0, result, 0, resultWriterIndex);
            return result;
        }
        return null;
    }

    private static int findUnescaped(byte[] str, int start, int end) {
        while (start < end) {
            int charIndex = ByteUtil.indexOf(str, '*', start, end);
            if (charIndex == start || charIndex == -1) {
                return charIndex;
            }
            int backSlashCount = 0;
            int backSlashIndex = charIndex - 1;
            while (((backSlashIndex >= start) && (str[backSlashIndex] == '\\'))) {
                backSlashIndex--;
                backSlashCount++;
            }

            if (backSlashCount % 2 == 0) {
                return charIndex;
            }

            // Start the next search after the escaped star.
            start = charIndex + 1;
        }
        return -1;
    }

    private static int findClosingParenIndex(byte[] filter, int start, int end) {
        int depth = 1;
        boolean escape = false;
        int closingParenIndex = start;
        while (closingParenIndex < end && depth > 0) {
            char c = (char) filter[closingParenIndex];
            if (!escape) {
                if (c == '(') {
                    depth++;
                } else if (c == ')') {
                    depth--;
                }
            }
            escape = c == '\\' && !escape;
            if (depth > 0) {
                closingParenIndex++;
            }
        }
        if (depth != 0) {
            throw new LdapException(ResultCode.FILTER_ERROR, "Unbalanced parenthesis");
        }
        return closingParenIndex;
    }

    private static void writeFilter(
            BerBuffer buffer,
            byte[] filter,
            int filterStartIndex,
            int filterEndIndex) {
        int equalIndex = ByteUtil.indexOf(filter, '=', filterStartIndex, filterEndIndex);
        if (equalIndex == -1) {
            throw new LdapException(ResultCode.FILTER_ERROR, "Missing \"=\"");
        }
        int filterTypeEndIndex;
        int valueStartIndex = equalIndex + 1;
        int filterType;
        switch (filter[equalIndex - 1]) {
            case '<' -> {
                filterType = TYPE_LESS;
                filterTypeEndIndex = equalIndex - 1;
            }
            case '>' -> {
                filterType = TYPE_GREATER;
                filterTypeEndIndex = equalIndex - 1;
            }
            case '~' -> {
                filterType = TYPE_APPROXIMATE;
                filterTypeEndIndex = equalIndex - 1;
            }
            case ':' -> {
                filterType = TYPE_EXTENSIBLE_MATCH;
                filterTypeEndIndex = equalIndex - 1;
            }
            default -> {
                filterType = -1;
                filterTypeEndIndex = equalIndex;
            }
        }
        byte lastChar = filter[filterTypeEndIndex - 1];
        if (lastChar == '.' || lastChar == ';' || lastChar == ':') {
            throw new LdapException(
                    ResultCode.FILTER_ERROR,
                    "Invalid attribute description: The last character must not be a \".\", \";\", or \":\"");
        }
        int attributeOptionsStartIndex = -1;
        // ":"
        int extensibleMatchStartIndex = -1;
        char firstFilterChar = (char) filter[filterStartIndex];
        if (isAlphabeticOrDigit(firstFilterChar)) {
            boolean isNumericOid = firstFilterChar <= '9';
            for (int i = filterStartIndex + 1; i < filterTypeEndIndex; i++) {
                char currentChar = (char) filter[i];
                if (currentChar == ';') {
                    if (isNumericOid && filter[i - 1] == '.') {
                        throw new LdapException(
                                ResultCode.FILTER_ERROR,
                                "Invalid attribute description");
                    }
                    attributeOptionsStartIndex = i;
                    break;
                }
                if (currentChar == ':' && filterType == TYPE_EXTENSIBLE_MATCH) {
                    if (isNumericOid && filter[i - 1] == '.') {
                        throw new LdapException(
                                ResultCode.FILTER_ERROR,
                                "Invalid attribute description");
                    }
                    extensibleMatchStartIndex = i;
                    break;
                }
                validate(filter, isNumericOid, i);
            }
        } else if (filterType == TYPE_EXTENSIBLE_MATCH && firstFilterChar == ':') {
            extensibleMatchStartIndex = filterStartIndex;
        } else {
            throw new LdapException(ResultCode.FILTER_ERROR, "Invalid attribute description");
        }
        if (attributeOptionsStartIndex > 0) {
            for (int i = attributeOptionsStartIndex + 1; i < filterTypeEndIndex; i++) {
                byte previousFilterChar = filter[i - 1];
                byte filterChar = filter[i];
                if (filterChar == ';') {
                    if (previousFilterChar == ';') {
                        throw new LdapException(
                                ResultCode.FILTER_ERROR,
                                "Invalid attribute description: Consecutive \";\" are not allowed");
                    }
                    continue;
                }
                if (filterChar == ':' && filterType == TYPE_EXTENSIBLE_MATCH) {
                    if (previousFilterChar == ';') {
                        throw new LdapException(
                                ResultCode.FILTER_ERROR,
                                "Invalid attribute description: \";\" should not be followed by \":\"");
                    }
                    extensibleMatchStartIndex = i;
                    break;
                }
                validateAttributeDescription((char) filter[i]);
            }
        }

        if (extensibleMatchStartIndex > 0) {
            boolean hasMatchingRule = false;
            for (int i = extensibleMatchStartIndex + 1; i < filterTypeEndIndex; i++) {
                char c = (char) filter[i];
                if (c == ':') {
                    throw new LdapException(
                            ResultCode.FILTER_ERROR,
                            "Invalid attribute description: multiple matching rules are not allowed");
                }
                if (!isAlphabeticOrDigit(c)) {
                    throw new LdapException(
                            ResultCode.FILTER_ERROR,
                            "Invalid attribute description");
                }
                boolean isNumericOid = c <= '9';
                i++;
                for (int j = i; j < filterTypeEndIndex; j++, i++) {
                    if (filter[j] == ':') {
                        if (hasMatchingRule) {
                            throw new LdapException(
                                    ResultCode.FILTER_ERROR,
                                    "Invalid attribute description: multiple matching rules are not allowed");
                        }
                        if (isNumericOid && filter[j - 1] == '.') {
                            throw new LdapException(
                                    ResultCode.FILTER_ERROR,
                                    "Invalid attribute description");
                        }
                        hasMatchingRule = true;
                        break;
                    }
                    validate(filter, isNumericOid, j);
                }
            }
        }
        if (filterTypeEndIndex == equalIndex) {
            if (findUnescaped(filter, valueStartIndex, filterEndIndex) == -1) {
                filterType = TYPE_EQUALITY;
            } else if (filter[valueStartIndex] == '*' && valueStartIndex == (filterEndIndex - 1)) {
                filterType = TYPE_PRESENT;
                buffer.writeOctetString(filterType,
                        filter,
                        filterStartIndex,
                        filterTypeEndIndex - filterStartIndex);
                return;
            } else {
                writeSubstringFilter(buffer,
                        filter,
                        filterStartIndex,
                        filterTypeEndIndex,
                        valueStartIndex,
                        filterEndIndex);
                return;
            }
        }

        if (filterType == TYPE_EXTENSIBLE_MATCH) {
            writeExtensibleMatchFilter(buffer,
                    filter,
                    filterStartIndex,
                    filterTypeEndIndex,
                    valueStartIndex,
                    filterEndIndex);
        } else {
            buffer.beginSequence(filterType)
                    .writeOctetString(filter,
                            filterStartIndex,
                            filterTypeEndIndex - filterStartIndex);
            byte[] filterValue = unescapeFilterValue(filter, valueStartIndex, filterEndIndex);
            if (filterValue == null) {
                buffer.writeOctetString(filter, valueStartIndex, filterEndIndex - valueStartIndex);
            } else {
                buffer.writeOctetString(filterValue, 0, filterValue.length);
            }
            buffer.endSequence();
        }
    }

    private static void writeFilterSet(
            BerBuffer buffer,
            byte[] filter,
            int filterType,
            Context context,
            int filterEnd) {
        context.readIndex++;
        buffer.beginSequence(filterType);
        int closingParenIndex = findClosingParenIndex(filter, context.readIndex, filterEnd);
        writeFilterInSet(buffer, filter, filterType, context.readIndex, closingParenIndex);
        context.readIndex = closingParenIndex + 1;
        buffer.endSequence();
    }

    private static void writeFilterInSet(
            BerBuffer buffer,
            byte[] filter,
            int filterType,
            int start,
            int end) {
        Context context = new Context();
        int currentFilterIndex = 0;
        for (context.readIndex = start; context.readIndex < end; context.readIndex++) {
            char c = (char) filter[context.readIndex];
            if (Character.isSpaceChar(c) || c == '(') {
                continue;
            }
            if ((filterType == TYPE_NOT) && (currentFilterIndex > 0)) {
                throw new LdapException(
                        ResultCode.FILTER_ERROR,
                        "The filter \"!\" cannot be followed by more than one filter");
            }

            int closingParenIndex = findClosingParenIndex(filter, context.readIndex, end);

            int length = closingParenIndex - context.readIndex;
            byte[] newFilter = new byte[length + 2];
            System.arraycopy(filter, context.readIndex, newFilter, 1, length);
            newFilter[0] = (byte) '(';
            newFilter[length + 1] = (byte) ')';
            writeFilter(buffer, newFilter, newFilter.length);
            currentFilterIndex++;
            context.readIndex = closingParenIndex + 1;
        }
    }

    private static void writeSubstringFilter(
            BerBuffer buffer,
            byte[] filter,
            int typeStart,
            int typeEnd,
            int valueStart,
            int valueEnd) {
        buffer.beginSequence(TYPE_SUBSTRING);

        buffer.writeOctetString(filter, typeStart, typeEnd - typeStart);
        buffer.beginSequence();

        int index;
        int previousIndex = valueStart;
        while ((index = findUnescaped(filter, previousIndex, valueEnd)) != -1) {
            if (previousIndex == valueStart) {
                if (previousIndex < index) {
                    byte[] filterValue = unescapeFilterValue(filter, previousIndex, index);
                    if (filterValue == null) {
                        buffer.writeOctetString(SUBSTRING_INITIAL,
                                filter,
                                previousIndex,
                                index - previousIndex);
                    } else {
                        buffer.writeOctetString(SUBSTRING_INITIAL, filterValue);
                    }
                }
            } else {
                if (previousIndex < index) {
                    byte[] filterValue = unescapeFilterValue(filter, previousIndex, index);
                    if (filterValue == null) {
                        buffer.writeOctetString(SUBSTRING_ANY,
                                filter,
                                previousIndex,
                                index - previousIndex);
                    } else {
                        buffer.writeOctetString(SUBSTRING_ANY, filterValue);
                    }
                }
            }
            previousIndex = index + 1;
        }
        if (previousIndex < valueEnd) {
            byte[] filterValue = unescapeFilterValue(filter, previousIndex, valueEnd);
            if (filterValue == null) {
                buffer.writeOctetString(SUBSTRING_FINAL,
                        filter,
                        previousIndex,
                        valueEnd - previousIndex);
            } else {
                buffer.writeOctetString(SUBSTRING_FINAL, filterValue);
            }
        }
        buffer.endSequence()
                .endSequence();
    }

    private static void writeExtensibleMatchFilter(
            BerBuffer buffer,
            byte[] filter,
            int matchStart,
            int matchEnd,
            int valueStart,
            int valueEnd) {
        boolean matchDN = false;
        int firstColon = ByteUtil.indexOf(filter, ':', matchStart, matchEnd);
        int secondColon;
        int i;

        buffer.beginSequence(TYPE_EXTENSIBLE_MATCH);

        if (firstColon >= 0) {
            if ((i = ByteUtil.indexOf(filter, ":dn", firstColon, matchEnd)) >= 0) {
                matchDN = true;
            }
            // test for matching rule
            if (((secondColon = ByteUtil.indexOf(filter, ':', firstColon + 1, matchEnd)) >= 0)
                    || (i == -1)) {
                if (i == firstColon) {
                    buffer.writeOctetString(EXTENSIBLE_MATCHING_RULE,
                            filter,
                            secondColon + 1,
                            matchEnd - (secondColon + 1));
                } else if ((i == secondColon) && (i >= 0)) {
                    buffer.writeOctetString(EXTENSIBLE_MATCHING_RULE,
                            filter,
                            firstColon + 1,
                            secondColon - (firstColon + 1));
                } else {
                    buffer.writeOctetString(EXTENSIBLE_MATCHING_RULE,
                            filter,
                            firstColon + 1,
                            matchEnd - (firstColon + 1));
                }
            }
            // test for the attribute type.
            if (firstColon > matchStart) {
                buffer.writeOctetString(EXTENSIBLE_MATCHING_TYPE,
                        filter,
                        matchStart,
                        firstColon - matchStart);
            }
        } else {
            buffer.writeOctetString(EXTENSIBLE_MATCHING_TYPE,
                    filter,
                    matchStart,
                    matchEnd - matchStart);
        }
        byte[] filterValue = unescapeFilterValue(filter, valueStart, valueEnd);
        if (filterValue == null) {
            buffer.writeOctetString(EXTENSIBLE_MATCHING_VALUE,
                    filter,
                    valueStart,
                    valueEnd - valueStart);
        } else {
            buffer.writeOctetString(EXTENSIBLE_MATCHING_VALUE, filterValue);
        }
        buffer.writeBoolean(EXTENSIBLE_MATCHING_DN, matchDN)
                .endSequence();
    }

    private static boolean isAlphabeticOrDigit(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
    }

    private static void validate(byte[] filter, boolean isNumericOid, int index) {
        char c = (char) filter[index];
        if (isNumericOid) {
            if ((c == '.' && filter[index - 1] == '.') || (c != '.' && !(c >= '0' && c <= '9'))) {
                throw new LdapException(ResultCode.FILTER_ERROR, "Invalid attribute description");
            }
        } else {
            validateAttributeDescription(c);
        }
    }

    private static void validateAttributeDescription(char c) {
        if (c != '-' && !(isAlphabeticOrDigit(c))) {
            throw new LdapException(ResultCode.FILTER_ERROR, "Invalid attribute description");
        }
    }

    private static class Context {
        int readIndex;
    }
}