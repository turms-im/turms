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

package im.turms.plugin.antispam.parser;

import im.turms.plugin.antispam.character.CharNormalizer;
import im.turms.plugin.antispam.property.TextParsingStrategy;
import im.turms.server.common.lang.CharArrayBuffer;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * @author James Chen
 */
public abstract class DictionaryParser {

    private static final char[][] VALID_CODE_POINT_RANGES = {
            {'a', 'z'},
            {'0', '9'},
            {'\u4E00', '\u9FFF'} // CJK Unified Ideographs
    };

    private DictionaryParser() {
    }

    @SneakyThrows
    public static List<char[]> parse(Path dictPath,
                                     String charsetName,
                                     boolean skipInvalidCharacter,
                                     TextParsingStrategy parsingStrategy) {
        List<char[]> list = new LinkedList<>();
        try (InputStream stream = Files.newInputStream(dictPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charsetName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(parseWord(line.toCharArray(), skipInvalidCharacter, parsingStrategy));
            }
        }
        return list;
    }

    public static char[] parseWord(String word,
                                   boolean skipInvalidCharacter,
                                   TextParsingStrategy parsingStrategy) {
        return parseWord(word.toCharArray(), skipInvalidCharacter, parsingStrategy);
    }

    /**
     * "Hello,./" -> "hello"
     * "⑩*&(元Ⅰ[]二角" -> ["10元12角", "10yuan12jiao"]
     */
    public static char[] parseWord(char[] word,
                                   boolean skipInvalidCharacter,
                                   TextParsingStrategy parsingStrategy) {
        CharArrayBuffer newWord = new CharArrayBuffer(word.length);
        for (char character : word) {
            if (Character.isDigit(character)) {
                newWord.append(character);
                continue;
            }
            char[] normalizedChars = CharNormalizer.normalize(character);
            if (normalizedChars.length > 0 && Character.isDigit(normalizedChars[0])) {
                newWord.append(normalizedChars);
                continue;
            }
            if (parsingStrategy == TextParsingStrategy.NORMALIZATION && isValidateChar(character)) {
                newWord.append(character);
                continue;
            }
            if (isValidateChars(normalizedChars)) {
                newWord.append(normalizedChars);
            } else if (!skipInvalidCharacter) {
                throw new IllegalArgumentException("The character '%c' of the block %s is invalid. "
                        .formatted(character, Character.UnicodeBlock.of(character).toString()) +
                        "Please remove the character in the dictionary, " +
                        "or update \"im.turms.plugin.antispam.parser.DictionaryParser.VALID_CODE_POINT_RANGES\" to support the character.");
            }
        }
        return newWord.toCharArray();
    }

    private static boolean isValidateChars(char[] chars) {
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

    private static boolean isValidateChar(char c) {
        for (char[] validCharRange : VALID_CODE_POINT_RANGES) {
            if (validCharRange[0] <= c && c <= validCharRange[1]) {
                return true;
            }
        }
        return false;
    }

}