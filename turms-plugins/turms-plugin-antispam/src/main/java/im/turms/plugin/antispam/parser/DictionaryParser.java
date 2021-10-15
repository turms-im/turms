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

import im.turms.plugin.antispam.TextPreprocessor;
import im.turms.server.common.lang.CharArrayBuffer;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
            String line;
            while ((line = reader.readLine()) != null) {
                char[] chars = parseWord(line.toCharArray(), skipInvalidCharacter);
                if (chars.length != 0) {
                    terms.add(chars);
                }
            }
        }
        Set<CharArrayBuffer> set = new HashSet<>();
        for (char[] term : terms) {
            set.add(new CharArrayBuffer(term));
        }
        terms = set.stream().map(b -> b.toCharArray()).collect(Collectors.toList());
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
        return newWord.toCharArray();
    }

}