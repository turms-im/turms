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

package im.turms.plugin.antispam.dictionary;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.plugin.antispam.TextPreprocessor;

import static org.assertj.core.api.Assertions.assertThat;

import static im.turms.plugin.antispam.property.TextParsingStrategy.NORMALIZATION;
import static im.turms.plugin.antispam.property.TextParsingStrategy.NORMALIZATION_TRANSLITERATION;

/**
 * @author James Chen
 */
class DictionaryParserTests {

    private static final char[] EMPTY = "".toCharArray();

    Path path = Path.of("./anti-spam-dictionary-parser-tests.tmp");
    String text =
            """
                    你好,123,1,打招呼的敬语,网络采集,1970-01-01T00:00:00.000Z,1970-01-01T00:00:00.000Z,1970-01-01T00:00:00.000Z,,汉语中打招呼的敬语常用词语
                    Hello,,2,,

                    안녕하세요,,,,,,,,,,,,,,,,,,,,,,,,,,,
                    こんにちは
                    """;

    @SneakyThrows
    @Test
    void parse_enableExtendedWord() {
        List<Word> words = getParsedWords(true);
        Date epoch = new Date(0);

        assertThat(words).hasSize(4);
        assertThat((ExtendedWord) words.get(0)).isEqualTo(ExtendedWord.builder()
                .setWord("你好".toCharArray())
                .setId("123")
                .setLevel(1)
                .setCategory("打招呼的敬语")
                .setSource("网络采集")
                .setCreateTime(epoch)
                .setDisableTime(epoch)
                .setEnableTime(epoch)
                .setComment("汉语中打招呼的敬语常用词语")
                .build());
        assertThat((ExtendedWord) words.get(1)).isEqualTo(ExtendedWord.builder()
                .setWord("Hello".toCharArray())
                .setLevel(2)
                .build());
        assertThat((ExtendedWord) words.get(2)).isEqualTo(ExtendedWord.builder()
                .setWord("안녕하세요".toCharArray())
                .build());
        assertThat((ExtendedWord) words.get(3)).isEqualTo(ExtendedWord.builder()
                .setWord("こんにちは".toCharArray())
                .build());
    }

    @SneakyThrows
    @Test
    void parse_disableExtendedWord() {
        List<Word> words = getParsedWords(false);

        assertThat(words).hasSize(4);
        assertThat(words.get(0)).isEqualTo(new Word("你好".toCharArray()));
        assertThat(words.get(1)).isEqualTo(new Word("hello".toCharArray()));
        assertThat(words.get(2)).isEqualTo(new Word("annyeonghaseyo".toCharArray()));
        assertThat(words.get(3)).isEqualTo(new Word("konnichiha".toCharArray()));
    }

    @Test
    void parseWord() {
        DictionaryParser nParser = new DictionaryParser(new TextPreprocessor(NORMALIZATION));
        DictionaryParser tParser =
                new DictionaryParser(new TextPreprocessor(NORMALIZATION_TRANSLITERATION));

        String word;

        word = "Hello,./";
        assertThat(nParser.parseWord(word, true)).containsExactly("hello".toCharArray());
        assertThat(tParser.parseWord(word, true)).containsExactly("hello".toCharArray());

        word = "⑩*&(元Ⅰ[]二角";
        assertThat(nParser.parseWord(word, true)).containsExactly("10元12角".toCharArray());
        assertThat(tParser.parseWord(word, true)).containsExactly("10yuan12jiao".toCharArray());

        word = "";
        assertThat(nParser.parseWord(word, true)).containsExactly(EMPTY);
        assertThat(tParser.parseWord(word, true)).containsExactly(EMPTY);

        word = "*";
        assertThat(nParser.parseWord(word, true)).containsExactly(EMPTY);
        assertThat(tParser.parseWord(word, true)).containsExactly(EMPTY);

        word = "𤳵";
        assertThat(nParser.parseWord(word, true)).containsExactly(EMPTY);
        assertThat(tParser.parseWord(word, true)).containsExactly(EMPTY);
    }

    @SneakyThrows
    List<Word> getParsedWords(boolean enableExtendedWord) {
        try {
            Files.writeString(path, text, StandardCharsets.UTF_8);
            DictionaryParser parser = new DictionaryParser(new TextPreprocessor(NORMALIZATION));
            return parser.parse(path, "UTF-8", true, enableExtendedWord);
        } finally {
            Files.delete(path);
        }
    }

}
