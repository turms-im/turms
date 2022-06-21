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

import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.lang.Pair;
import im.turms.server.common.infra.lang.StringUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author James Chen
 */
public class FastUriParser {

    private FastUriParser() {
    }

    /**
     * @implNote Note that this method only handles the cases for turms servers,
     * meaning it will throw and won't waste resources to parse if a URI is meaningless
     * for turms servers even if it is a valid URI.
     */
    public static Pair<String, Map<String, List<Object>>> parsePathAndQueryParams(String uri) {
        if (!StringUtil.isLatin1(uri)) {
            throw new IllegalArgumentException("The URI must only contain ASCII characters");
        }
        byte[] srcBytes = StringUtil.getBytes(uri);
        String path = null;
        Map<String, List<Object>> queryParams = new HashMap<>(srcBytes.length >> 3);
        int itemBeginIndex = -1;
        int arrayIndexBeginIndex = -1;
        int previousArrayIndex = -1;
        int arrayIndex = -1;
        boolean hasArrayWithoutIndexes = false;
        boolean hasArrayWithIndexes = false;
        String currentParamKey = null;
        String currentParamNestedKey = null;
        int currentItemLength;
        List<Object> currentParamValues = null;
        Map<String, String> currentNestedParamValues = null;
        byte b;
        ParseState currentState = ParseState.PARSING_PATH;
        for (int i = 0, srcBytesLength = srcBytes.length; i < srcBytesLength; i++) {
            b = srcBytes[i];
            switch (currentState) {
                case PARSING_PATH -> {
                    if (b == '?') {
                        if (i == srcBytesLength - 1) {
                            return Pair.of(StringUtil.newLatin1String(srcBytes, 0, srcBytesLength - 1),
                                    Collections.emptyMap());
                        }
                        path = StringUtil.newLatin1String(srcBytes, 0, i);
                        itemBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_KEY;
                    }
                }
                case PARSING_QUERY_PARAM_KEY -> {
                    if (b == '%') {
                        if (i + 2 < srcBytesLength && srcBytes[i + 1] == '5' && srcBytes[i + 2] == 'B') {
                            currentItemLength = i - itemBeginIndex;
                            String newParamKey = StringUtil.newLatin1String(srcBytes, itemBeginIndex, currentItemLength);
                            if (!newParamKey.equals(currentParamKey)) {
                                hasArrayWithoutIndexes = false;
                                hasArrayWithIndexes = false;
                            }
                            currentParamKey = newParamKey;
                            i += 2;
                            arrayIndexBeginIndex = i + 1;
                            currentParamValues = queryParams.computeIfAbsent(currentParamKey, s -> new ChunkedArrayList<>(16, 4));
                            currentState = ParseState.PARSING_QUERY_PARAM_KEY_ARRAY_INDEX;
                        } else {
                            // %5B => [
                            // %5D => ]
                            throw new IllegalArgumentException("The query parameter keys can only contain escape codes of %5B and %5D");
                        }
                    } else if (b == '[') {
                        currentItemLength = i - itemBeginIndex;
                        String newParamKey = StringUtil.newLatin1String(srcBytes, itemBeginIndex, currentItemLength);
                        if (!newParamKey.equals(currentParamKey)) {
                            hasArrayWithoutIndexes = false;
                            hasArrayWithIndexes = false;
                        }
                        currentParamKey = newParamKey;
                        currentParamValues = queryParams.computeIfAbsent(currentParamKey, s -> new ChunkedArrayList<>(16, 4));
                        arrayIndexBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_KEY_ARRAY_INDEX;
                    } else if (b == '=') {
                        currentItemLength = i - itemBeginIndex;
                        String newParamKey = StringUtil.newLatin1String(srcBytes, itemBeginIndex, currentItemLength);
                        if (!newParamKey.equals(currentParamKey)) {
                            hasArrayWithoutIndexes = false;
                            hasArrayWithIndexes = false;
                        }
                        currentParamKey = newParamKey;
                        currentParamValues = queryParams.computeIfAbsent(currentParamKey, s -> new ChunkedArrayList<>(16, 4));
                        itemBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                    } else if (b == '.') {
                        itemBeginIndex = i + 1;
                        currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                    }
                }
                case PARSING_QUERY_PARAM_KEY_ARRAY_INDEX -> {
                    if (b == '%') {
                        if (i + 2 < srcBytesLength && srcBytes[i + 1] == '5' && srcBytes[i + 2] == 'D') {
                            currentItemLength = i - arrayIndexBeginIndex;
                            if (currentItemLength == 1) {
                                if (i + 3 < srcBytesLength) {
                                    byte nextByte = srcBytes[i + 3];
                                    itemBeginIndex = i + 4;
                                    if (nextByte == '=') {
                                        currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                                    } else if (nextByte == '.') {
                                        currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                                    } else {
                                        throw new IllegalArgumentException("The \"[]\" should comes with a \"=\" or a \".\"");
                                    }
                                } else {
                                    throw new IllegalArgumentException("The \"[]\" should comes with a \"=\" or a \".\"");
                                }
                            } else {
                                String s = StringUtil.newLatin1String(srcBytes, arrayIndexBeginIndex, currentItemLength);
                                arrayIndex = Integer.parseInt(s);
                                if (arrayIndex < previousArrayIndex) {
                                    throw new IllegalArgumentException("The array index should come from 0 in ascending order");
                                }
                                previousArrayIndex = arrayIndex;
                            }
                            i += 2;
                        } else {
                            throw new IllegalArgumentException("The query parameter keys can only contain escape codes of %5B and %5D");
                        }
                    } else if (b == ']') {
                        currentItemLength = i - arrayIndexBeginIndex;
                        if (currentItemLength == 0) {
                            hasArrayWithoutIndexes = true;
                            if (hasArrayWithIndexes) {
                                throw new IllegalArgumentException("The array items should be all indexed or not indexed. " +
                                        "e.g. \"keys[]=1&keys[]=2&keys[]=3\" or " +
                                        "e.g. \"keys[0]=1&keys[1]=2&keys[2]=3\"");
                            }
                            if (i + 1 < srcBytesLength) {
                                byte tempByte = srcBytes[i + 1];
                                itemBeginIndex = i + 2;
                                if (tempByte == '=') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                                } else if (tempByte == '.') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                                } else {
                                    throw new IllegalArgumentException("\"[]\" should comes with a \"=\" or a \".\"");
                                }
                            } else {
                                throw new IllegalArgumentException("\"[]\" should comes with a \"=\" or a \".\"");
                            }
                        } else {
                            hasArrayWithIndexes = true;
                            if (hasArrayWithoutIndexes) {
                                throw new IllegalArgumentException("The array items should be all indexed or not indexed. " +
                                        "e.g. \"keys[]=1&keys[]=2&keys[]=3\" or " +
                                        "e.g. \"keys[0]=1&keys[1]=2&keys[2]=3\"");
                            }
                            String s = StringUtil.newLatin1String(srcBytes, arrayIndexBeginIndex, currentItemLength);
                            arrayIndex = Integer.parseInt(s);
                            if (arrayIndex < previousArrayIndex) {
                                throw new IllegalArgumentException("The array index should come from 0 increasingly");
                            }
                            previousArrayIndex = arrayIndex;
                            if (i + 1 < srcBytesLength) {
                                byte tempByte = srcBytes[i + 1];
                                itemBeginIndex = i + 2;
                                if (tempByte == '=') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                                } else if (tempByte == '.') {
                                    currentState = ParseState.PARSING_QUERY_PARAM_KEY_NESTED_KEY;
                                } else {
                                    throw new IllegalArgumentException("\"[indexed]\" should comes with a \"=\" or a \".\"");
                                }
                            } else {
                                throw new IllegalArgumentException("\"[indexed]\" should comes with a \"=\" or a \".\"");
                            }
                        }
                    }
                }
                case PARSING_QUERY_PARAM_KEY_NESTED_KEY -> {
                    if (b == '=') {
                        currentParamNestedKey = StringUtil.newLatin1String(srcBytes, itemBeginIndex, i - itemBeginIndex);
                        itemBeginIndex = i + 1;
                        List<Object> objects = queryParams.get(currentParamKey);
                        if (arrayIndex >= objects.size()) {
                            currentNestedParamValues = new HashMap<>(8);
                            objects.add(arrayIndex, currentNestedParamValues);
                        } else {
                            currentNestedParamValues = (Map<String, String>) objects.get(arrayIndex);
                        }
                        currentState = ParseState.PARSING_QUERY_PARAM_VALUE;
                    }
                }
                case PARSING_QUERY_PARAM_VALUE -> {
                    String value;
                    int length = -1;
                    boolean isValueDelimiter = false;
                    if (b == '&') {
                        length = i - itemBeginIndex;
                    } else if (i == srcBytesLength - 1) {
                        length = i - itemBeginIndex + 1;
                    } else if (b == '%') {
                        // %2C => &
                        if (i + 2 < srcBytesLength && srcBytes[i + 1] == '2' && srcBytes[i + 2] == 'C') {
                            isValueDelimiter = true;
                            length = i - itemBeginIndex;
                            i += 2;
                        } else {
                            throw new IllegalArgumentException("The query parameters can only contain escape codes of %2C");
                        }
                    } else if (b == ',') {
                        if (currentParamNestedKey != null) {
                            throw new IllegalArgumentException("The array query parameter cannot contain nested keys");
                        }
                        length = i - itemBeginIndex;
                        isValueDelimiter = true;
                    }
                    if (length != -1) {
                        value = StringUtil.newLatin1String(srcBytes, itemBeginIndex, length);
                        if (currentParamNestedKey == null) {
                            currentParamValues.add(value);
                        } else {
                            currentNestedParamValues.put(currentParamNestedKey, value);
                            currentParamNestedKey = null;
                        }
                        previousArrayIndex = -1;
                        itemBeginIndex = i + 1;
                        if (!isValueDelimiter) {
                            currentState = ParseState.PARSING_QUERY_PARAM_KEY;
                        }
                    }
                }
            }
        }
        return Pair.of(path == null ? uri : path, queryParams);
    }

    private enum ParseState {
        PARSING_PATH,
        PARSING_QUERY_PARAM_KEY,
        PARSING_QUERY_PARAM_KEY_ARRAY_INDEX,
        PARSING_QUERY_PARAM_KEY_NESTED_KEY,
        PARSING_QUERY_PARAM_VALUE
    }
}
