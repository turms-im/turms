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

import im.turms.plugin.antispam.character.CharNormalizer;
import im.turms.plugin.antispam.property.TextParsingStrategy;

/**
 * @author James Chen
 */
public class TextPreprocessor {

    private static final char[][] VALID_CODE_POINT_RANGES =
            {{'a', 'z'}, {'0', '9'}, {'\u4E00', '\u9FFF'} // CJK Unified Ideographs
            };

    private final TextParsingStrategy parsingStrategy;

    public TextPreprocessor(TextParsingStrategy parsingStrategy) {
        this.parsingStrategy = parsingStrategy;
    }

    public Object process(char character) {
        if (isDigit(character)) {
            return character;
        }
        char[] normalizedChars = CharNormalizer.normalize(character);
        if (normalizedChars.length > 0 && isDigit(normalizedChars[0])) {
            return normalizedChars;
        }
        if (parsingStrategy == TextParsingStrategy.NORMALIZATION && isValidateChar(character)) {
            return character;
        }
        if (isValidateChars(normalizedChars)) {
            return normalizedChars;
        }
        return null;
    }

    /**
     * @implNote Don't use {@link Character#isDigit(char)}, which is slow
     */
    private boolean isDigit(char character) {
        return '0' <= character && character <= '9';
    }

    private boolean isValidateChars(char[] chars) {
        if (chars.length == 0) {
            return false;
        }
        for (char c : chars) {
            if (!isValidateChar(c)) {
                return false;
            }
        }
        return true;
    }

    private boolean isValidateChar(char c) {
        for (char[] validCharRange : VALID_CODE_POINT_RANGES) {
            if (validCharRange[0] <= c && c <= validCharRange[1]) {
                return true;
            }
        }
        return false;
    }

}