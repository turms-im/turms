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

package im.turms.server.common.access.admin.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public final class FastUriParser {

    private FastUriParser() {
    }

    /**
     * @implNote Note that this method only handles the cases for turms servers, meaning it will
     *           throw and won't waste resources to parse if a URI is meaningless for turms servers
     *           even if it is a valid URI.
     */
    public static Pair<String, Map<String, List<Object>>> parsePathAndQueryParams(String uri) {
        if (!StringUtil.isLatin1(uri)) {
            throw new IllegalArgumentException("The URI must only contain ASCII characters");
        }
        byte[] srcBytes = StringUtil.getBytes(uri);
        String path = null;
        Map<String, List<Object>> queryParams = new HashMap<>(srcBytes.length >> 3);

        int currentItemCharBeginIndex = -1;
        int currentItemCharCount;

        int previousArrayIndex = -1;
        int currentArrayIndex = -1;
        int currentArrayIndexCharBeginIndex = -1;
        boolean hasArrayWithoutIndexes = false;
        boolean hasArrayWithIndexes = false;

        boolean isEncodedLeftBracket;
        boolean isEncodedRightBracket;

        String currentParamKey = null;
        String currentParamNestedKey = null;
        List<Object> currentParamValues = null;
        Map<String, String> currentNestedParamValues = null;
        byte b;
        ParseState currentState = ParseState.PARSING_PATH;
        for (int i = 0, srcBytesLength = srcBytes.length; i < srcBytesLength; i++) {
            b = srcBytes[i];
            switch (currentState) {
                case PARSING_PATH -> {
                    if (b == '?') {
                        path = StringUtil.newLatin1String(srcBytes, 0, i);
                        if (i == srcBytesLength - 1) {
                            return Pair.of(path, Collections.emptyMap());
                        }
                        currentItemCharBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_KEY;
                    }
                }
                case PARSING_QUERY_PARAM_KEY -> {
                    if (b == '%') {
                        if (i + 2 < srcBytesLength
                                && srcBytes[i + 1] == '5'
                                && srcBytes[i + 2] == 'B') {
                            isEncodedLeftBracket = true;
                        } else {
                            // %5B => [
                            // %5D => ]
                            throw new IllegalArgumentException(
                                    "The query parameter key must only contain escape codes of \"%5B\" and \"%5D\"");
                        }
                    } else {
                        isEncodedLeftBracket = false;
                    }
                    if (isEncodedLeftBracket || b == '[') {
                        currentItemCharCount = i - currentItemCharBeginIndex;
                        String newParamKey = StringUtil.newLatin1String(srcBytes,
                                currentItemCharBeginIndex,
                                currentItemCharCount);
                        if (!newParamKey.equals(currentParamKey)) {
                            hasArrayWithoutIndexes = false;
                            hasArrayWithIndexes = false;
                        }
                        currentParamKey = newParamKey;
                        currentParamValues = queryParams.computeIfAbsent(currentParamKey,
                                s -> new ChunkedArrayList<>(16, 4));
                        if (isEncodedLeftBracket) {
                            i += 2;
                        }
                        currentArrayIndexCharBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_KEY_ARRAY_INDEX;
                    } else if (b == '=') {
                        currentItemCharCount = i - currentItemCharBeginIndex;
                        String newParamKey = StringUtil.newLatin1String(srcBytes,
                                currentItemCharBeginIndex,
                                currentItemCharCount);
                        if (!newParamKey.equals(currentParamKey)) {
                            hasArrayWithoutIndexes = false;
                            hasArrayWithIndexes = false;
                        }
                        currentParamKey = newParamKey;
                        currentParamValues = queryParams.computeIfAbsent(currentParamKey,
                                s -> new ChunkedArrayList<>(16, 4));
                        currentItemCharBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                    } else if (b == '.') {
                        currentItemCharBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                    }
                }
                case PARSING_QUERY_PARAM_KEY_ARRAY_INDEX -> {
                    if (b == '%') {
                        if (i + 2 < srcBytesLength
                                && srcBytes[i + 1] == '5'
                                && srcBytes[i + 2] == 'D') {
                            isEncodedRightBracket = true;
                        } else {
                            throw new IllegalArgumentException(
                                    "The query parameter key must only contain escape codes of \"%5B\" and \"%5D\"");
                        }
                    } else {
                        isEncodedRightBracket = false;
                    }
                    if (isEncodedRightBracket || b == ']') {
                        currentItemCharCount = i - currentArrayIndexCharBeginIndex;
                        if (isEncodedRightBracket) {
                            i += 2;
                        }
                        if (currentItemCharCount == 0) {
                            hasArrayWithoutIndexes = true;
                            if (hasArrayWithIndexes) {
                                throw new IllegalArgumentException(
                                        "The array item must be all indexed or not indexed. "
                                                + "e.g. \"keys[]=1&keys[]=2&keys[]=3\" or "
                                                + "e.g. \"keys[0]=1&keys[1]=2&keys[2]=3\"");
                            }
                            if (i + 1 < srcBytesLength) {
                                byte tempByte = srcBytes[i + 1];
                                currentItemCharBeginIndex = i + 2;
                                if (tempByte == '=') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                                } else if (tempByte == '.') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                                } else {
                                    throw new IllegalArgumentException(
                                            "\"[]\" must come with a \"=\" or a \".\"");
                                }
                            } else {
                                throw new IllegalArgumentException(
                                        "\"[]\" must come with a \"=\" or a \".\"");
                            }
                        } else {
                            hasArrayWithIndexes = true;
                            if (hasArrayWithoutIndexes) {
                                throw new IllegalArgumentException(
                                        "The array item must be all indexed or not indexed. "
                                                + "e.g. \"keys[]=1&keys[]=2&keys[]=3\" or "
                                                + "e.g. \"keys[0]=1&keys[1]=2&keys[2]=3\"");
                            }
                            String s = StringUtil.newLatin1String(srcBytes,
                                    currentArrayIndexCharBeginIndex,
                                    currentItemCharCount);
                            try {
                                currentArrayIndex = Integer.parseInt(s);
                            } catch (NumberFormatException e) {
                                throw new IllegalArgumentException(
                                        "\"[indexed]\" should contain an integer greater than or equal to 0");
                            }
                            if (currentArrayIndex < previousArrayIndex) {
                                throw new IllegalArgumentException(
                                        "The array index should start from 0 incrementally");
                            }
                            previousArrayIndex = currentArrayIndex;
                            if (i + 1 < srcBytesLength) {
                                byte tempByte = srcBytes[i + 1];
                                currentItemCharBeginIndex = i + 2;
                                if (tempByte == '=') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                                } else if (tempByte == '.') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                                } else {
                                    throw new IllegalArgumentException(
                                            "\"[indexed]\" should comes with a \"=\" or a \".\"");
                                }
                            } else {
                                throw new IllegalArgumentException(
                                        "\"[indexed]\" should comes with a \"=\" or a \".\"");
                            }
                        }
                    }
                }
                case PARSING_QUERY_PARAM_KEY_NESTED_KEY -> {
                    if (b == '=') {
                        currentParamNestedKey = StringUtil.newLatin1String(srcBytes,
                                currentItemCharBeginIndex,
                                i - currentItemCharBeginIndex);
                        currentItemCharBeginIndex = i + 1;
                        List<Object> objects = queryParams.get(currentParamKey);
                        if (currentArrayIndex >= objects.size()) {
                            currentNestedParamValues = new HashMap<>(8);
                            objects.add(currentArrayIndex, currentNestedParamValues);
                        } else {
                            currentNestedParamValues =
                                    (Map<String, String>) objects.get(currentArrayIndex);
                        }
                        currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                    }
                }
                case PARSING_QUERY_PARAM_VALUE -> {
                    String value;
                    int length = -1;
                    boolean isValueDelimiter = false;
                    if (b == '&') {
                        length = i - currentItemCharBeginIndex;
                    } else if (i == srcBytesLength - 1) {
                        length = i - currentItemCharBeginIndex + 1;
                    } else if (b == '%') {
                        // %2C => &
                        if (i + 2 < srcBytesLength
                                && srcBytes[i + 1] == '2'
                                && srcBytes[i + 2] == 'C') {
                            isValueDelimiter = true;
                            length = i - currentItemCharBeginIndex;
                            i += 2;
                        } else {
                            throw new IllegalArgumentException(
                                    "The query parameter can only contain escape codes of \"%2C\"");
                        }
                    } else if (b == ',') {
                        if (currentParamNestedKey != null) {
                            throw new IllegalArgumentException(
                                    "The array query parameter cannot contain nested keys");
                        }
                        length = i - currentItemCharBeginIndex;
                        isValueDelimiter = true;
                    }
                    if (length != -1) {
                        value = StringUtil
                                .newLatin1String(srcBytes, currentItemCharBeginIndex, length);
                        if (currentParamNestedKey == null) {
                            currentParamValues.add(value);
                        } else {
                            currentNestedParamValues.put(currentParamNestedKey, value);
                            currentParamNestedKey = null;
                        }
                        previousArrayIndex = -1;
                        currentItemCharBeginIndex = i + 1;
                        if (!isValueDelimiter) {
                            currentState = ParseState.PARSING_QUERY_PARAM_KEY;
                        }
                    }
                }
            }
        }
        return Pair.of(path == null
                ? uri
                : path, queryParams);
    }

    private enum ParseState {
        PARSING_PATH,
        PARSING_QUERY_PARAM_KEY,
        PARSING_QUERY_PARAM_KEY_ARRAY_INDEX,
        PARSING_QUERY_PARAM_KEY_NESTED_KEY,
        PARSING_QUERY_PARAM_VALUE
    }
}
