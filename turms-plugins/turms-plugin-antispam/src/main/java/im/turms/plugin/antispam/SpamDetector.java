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

package im.turms.plugin.antispam;

import java.util.Arrays;
import jakarta.annotation.Nullable;

import im.turms.plugin.antispam.ac.AhoCorasickDoubleArrayTrie;
import im.turms.server.common.infra.lang.FastStringBuilder;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public class SpamDetector extends AhoCorasickDoubleArrayTrie {

    /**
     * "Record Separator"
     */
    public static final byte UNWANTED_WORD_DELIMITER = 0x1E;

    private final TextPreprocessor textPreprocessor;

    public SpamDetector(TextPreprocessor textPreprocessor, AhoCorasickDoubleArrayTrie trie) {
        super(trie.fail, trie.output, trie.dat, trie.words);
        this.textPreprocessor = textPreprocessor;
    }

    @Nullable
    public String mask(String str, byte mask) {
        char code;
        Object newChars;
        int firstCharIndex = 0;
        int currentState = 0;
        int nextState;
        byte[] maskedBytes = null;
        int length = str.length();
        byte coder = StringUtil.getCoder(str);
        boolean isLatin1 = StringUtil.isLatin1(coder);
        for (int i = 0; i < length; i++) {
            code = str.charAt(i);
            newChars = textPreprocessor.process(code);
            if (currentState == ROOT_STATUS) {
                firstCharIndex = i;
            }
            if (newChars instanceof char[] chars) {
                for (char c : chars) {
                    nextState = transition(currentState, c);
                    while (nextState == STATUS_NOT_FOUND) {
                        currentState = fail[currentState];
                        if (currentState == ROOT_STATUS) {
                            firstCharIndex = i;
                        }
                        nextState = transition(currentState, c);
                    }
                    currentState = nextState;
                    if (output[currentState] != null) {
                        if (maskedBytes == null) {
                            byte[] bytes = StringUtil.getBytes(str);
                            maskedBytes = Arrays.copyOf(bytes, bytes.length);
                        }
                        if (isLatin1) {
                            for (int j = firstCharIndex; j <= i; j++) {
                                maskedBytes[j] = mask;
                            }
                        } else {
                            int k;
                            for (int j = firstCharIndex; j <= i; j++) {
                                k = j << 1;
                                maskedBytes[k] = mask;
                                maskedBytes[k + 1] = 0;
                            }
                        }
                    }
                }
            } else if (newChars instanceof Character c) {
                nextState = transition(currentState, c);
                while (nextState == STATUS_NOT_FOUND) {
                    currentState = fail[currentState];
                    if (currentState == ROOT_STATUS) {
                        firstCharIndex = i;
                    }
                    nextState = transition(currentState, c);
                }
                currentState = nextState;
                if (output[currentState] != null) {
                    if (maskedBytes == null) {
                        byte[] bytes = StringUtil.getBytes(str);
                        maskedBytes = Arrays.copyOf(bytes, bytes.length);
                    }
                    if (isLatin1) {
                        for (int j = firstCharIndex; j <= i; j++) {
                            maskedBytes[j] = mask;
                        }
                    } else {
                        int k;
                        for (int j = firstCharIndex; j <= i; j++) {
                            k = j << 1;
                            maskedBytes[k] = mask;
                            maskedBytes[k + 1] = 0;
                        }
                    }
                }
            }
        }
        if (maskedBytes == null) {
            return null;
        }
        return StringUtil.newString(maskedBytes, coder);
    }

    public boolean containsUnwantedWords(String text) {
        int currentState = 0;
        int length = text.length();
        for (int i = 0; i < length; i++) {
            char code = text.charAt(i);
            Object newChars = textPreprocessor.process(code);
            if (newChars instanceof char[] chars) {
                for (char c : chars) {
                    currentState = findNextState(currentState, c);
                    if (output[currentState] != null) {
                        return true;
                    }
                }
            } else if (newChars instanceof Character c) {
                currentState = findNextState(currentState, c);
                if (output[currentState] != null) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param maxNumberOfUnwantedWordsToReturn should be greater than 0
     */
    @Nullable
    public String findUnwantedWords(String text, int maxNumberOfUnwantedWordsToReturn) {
        char code;
        Object newChars;
        int firstByteIndex = 0;
        int currentState = 0;
        int nextState;
        FastStringBuilder builder = null;
        int length = text.length();
        byte[] textInternalBytes = null;
        byte coder = StringUtil.getCoder(text);
        boolean isLatin1 = StringUtil.isLatin1(coder);
        for (int i = 0; i < length; i++) {
            code = text.charAt(i);
            newChars = textPreprocessor.process(code);
            if (currentState == ROOT_STATUS) {
                firstByteIndex = isLatin1
                        ? i
                        : i * 2;
            }
            if (newChars instanceof char[] chars) {
                for (char c : chars) {
                    nextState = transition(currentState, c);
                    while (nextState == STATUS_NOT_FOUND) {
                        currentState = fail[currentState];
                        if (currentState == ROOT_STATUS) {
                            firstByteIndex = isLatin1
                                    ? i
                                    : i * 2;
                        }
                        nextState = transition(currentState, c);
                    }
                    currentState = nextState;
                    if (output[currentState] != null) {
                        if (builder == null) {
                            builder = new FastStringBuilder();
                            textInternalBytes = StringUtil.getBytes(text);
                        }
                        if (isLatin1) {
                            builder.append(textInternalBytes,
                                    firstByteIndex,
                                    i + 1 - firstByteIndex);
                        } else {
                            builder.append(textInternalBytes,
                                    firstByteIndex,
                                    (i + 1) * 2 - firstByteIndex);
                        }
                        if (builder.entryCount() >= maxNumberOfUnwantedWordsToReturn) {
                            return builder.build(coder, UNWANTED_WORD_DELIMITER);
                        }
                    }
                }
            } else if (newChars instanceof Character c) {
                nextState = transition(currentState, c);
                while (nextState == STATUS_NOT_FOUND) {
                    currentState = fail[currentState];
                    if (currentState == ROOT_STATUS) {
                        firstByteIndex = isLatin1
                                ? i
                                : i * 2;
                    }
                    nextState = transition(currentState, c);
                }
                currentState = nextState;
                if (output[currentState] != null) {
                    if (builder == null) {
                        builder = new FastStringBuilder();
                        textInternalBytes = StringUtil.getBytes(text);
                    }
                    if (isLatin1) {
                        builder.append(textInternalBytes, firstByteIndex, i + 1 - firstByteIndex);
                    } else {
                        builder.append(textInternalBytes,
                                firstByteIndex,
                                (i + 1) * 2 - firstByteIndex);
                    }
                    if (builder.entryCount() >= maxNumberOfUnwantedWordsToReturn) {
                        return builder.build(coder, UNWANTED_WORD_DELIMITER);
                    }
                }
            }
        }
        if (builder == null) {
            return null;
        }
        return builder.build(coder, UNWANTED_WORD_DELIMITER);
    }

}
