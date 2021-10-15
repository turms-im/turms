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

package im.turms.plugin.antispam.dictionary;

import im.turms.plugin.antispam.TextPreprocessor;
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
public class DictionaryParser {

    private final TextPreprocessor textPreprocessor;

    public DictionaryParser(TextPreprocessor textPreprocessor) {
        this.textPreprocessor = textPreprocessor;
    }

    @SneakyThrows
    public List<char[]> parse(Path dictPath, String charsetName, boolean skipInvalidCharacter) {
        List<char[]> terms = new LinkedList<>();
        try (InputStream stream = Files.newInputStream(dictPath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charsetName))) {
            CharArrayBuffer newWord = new CharArrayBuffer(32);
            int result;
            char character;
            while ((result = reader.read()) != -1) {
                character = (char) result;
                if (character == '\n' || character == '\r') {
                    if (!newWord.isEmpty()) {
                        terms.add(newWord.toCharArray());
                    }
                    newWord.clear();
                }
                parseChar(skipInvalidCharacter, newWord, character);
            }
            if (!newWord.isEmpty()) {
                terms.add(newWord.toCharArray());
            }
        }
        return terms;
    }

    public char[] parseWord(String word, boolean skipInvalidCharacter) {
        return parseWord(word.toCharArray(), skipInvalidCharacter);
    }

    /**
     * "Hello,./" -> "hello"
     * "⑩*&(元Ⅰ[]二角" -> ["10元12角", "10yuan12jiao"]
     */
    public char[] parseWord(char[] word, boolean skipInvalidCharacter) {
        CharArrayBuffer newWord = new CharArrayBuffer(word.length);
        for (char character : word) {
            parseChar(skipInvalidCharacter, newWord, character);
        }
        return newWord.toCharArray();
    }

    private void parseChar(boolean skipInvalidCharacter, CharArrayBuffer newWord, char character) {
        Object newChars = textPreprocessor.process(character);
        if (newChars == null) {
            if (!skipInvalidCharacter) {
                throw new IllegalArgumentException("The character '%c' of the block %s is invalid. "
                        .formatted(character, Character.UnicodeBlock.of(character).toString()) +
                        "Please remove the character in the dictionary, " +
                        "or update \"im.turms.plugin.antispam.TextPreprocessor.VALID_CODE_POINT_RANGES\" to support the character.");
            }
        } else if (newChars instanceof char[] chars) {
            newWord.append(chars);
        } else {
            newWord.append((char) newChars);
        }
    }

}