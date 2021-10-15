/*
 * Copyright (C) 2019 The Turms Project
 * https://github.com/turms-im/turms
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package im.turms.plugin.antispam;

import im.turms.plugin.antispam.ac.AhoCorasickDoubleArrayTrie;

/**
 * @author James Chen
 */
public class SpamDetector extends AhoCorasickDoubleArrayTrie {

    private final TextPreprocessor textPreprocessor;

    public SpamDetector(TextPreprocessor textPreprocessor, AhoCorasickDoubleArrayTrie trie) {
        super(trie.fail, trie.output, trie.dat);
        this.textPreprocessor = textPreprocessor;
    }

    public boolean mask(char[] text, char mask) {
        boolean found = false;
        char code;
        Object newChars;
        int firstCharIndex = 0;
        int currentState = 0;
        int nextState;
        for (int i = 0; i < text.length; i++) {
            code = text[i];
            newChars = textPreprocessor.process(code);
            if (newChars instanceof char[] chars) {
                for (char c : chars) {
                    nextState = transitionWithRoot(currentState, c);
                    while (nextState == -1) {
                        currentState = fail[currentState];
                        if (currentState == 0) {
                            firstCharIndex = i;
                        }
                        nextState = transitionWithRoot(currentState, c);
                    }
                    currentState = nextState;
                    if (output[currentState] != null) {
                        found = true;
                        for (int j = firstCharIndex; j <= i; j++) {
                            text[j] = mask;
                        }
                    }
                }
            } else if (newChars instanceof Character c) {
                nextState = transitionWithRoot(currentState, c);
                while (nextState == -1) {
                    currentState = fail[currentState];
                    if (currentState == 0) {
                        firstCharIndex = i;
                    }
                    nextState = transitionWithRoot(currentState, c);
                }
                currentState = nextState;
                if (output[currentState] != null) {
                    found = true;
                    for (int j = firstCharIndex; j <= i; j++) {
                        text[j] = mask;
                    }
                }
            }
        }
        return found;
    }

    public boolean containsUnwantedWords(char[] text) {
        int currentState = 0;
        for (char code : text) {
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

}
