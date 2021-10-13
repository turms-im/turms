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

import org.junit.jupiter.api.Test;

import static im.turms.plugin.antispam.parser.DictionaryParser.parseWord;
import static im.turms.plugin.antispam.property.TextParsingStrategy.NORMALIZATION;
import static im.turms.plugin.antispam.property.TextParsingStrategy.NORMALIZATION_TRANSLITERATION;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class DictionaryParserTests {

    @Test
    void test() {
        String word = "Hello,./";
        assertThat(parseWord(word, true, NORMALIZATION))
                .containsExactly("hello".toCharArray());
        assertThat(parseWord(word, true, NORMALIZATION_TRANSLITERATION))
                .containsExactly("hello".toCharArray());

        word = "⑩*&(元Ⅰ[]二角";
        assertThat(parseWord(word, true, NORMALIZATION))
                .containsExactly("10元12角".toCharArray());
        assertThat(parseWord(word, true, NORMALIZATION_TRANSLITERATION))
                .containsExactly("10yuan12jiao".toCharArray());

        word = "";
        assertThat(parseWord(word, true, NORMALIZATION))
                .containsExactly("".toCharArray());
        assertThat(parseWord(word, true, NORMALIZATION_TRANSLITERATION))
                .containsExactly("".toCharArray());

        word = "*";
        assertThat(parseWord(word, true, NORMALIZATION))
                .containsExactly("".toCharArray());
        assertThat(parseWord(word, true, NORMALIZATION_TRANSLITERATION))
                .containsExactly("".toCharArray());
    }

}
