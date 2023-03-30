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

package im.turms.plugin.antispam.ac;

import java.util.List;

import org.junit.jupiter.api.Test;

import im.turms.plugin.antispam.dictionary.Word;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class AhoCorasickDoubleArrayTrieTests {

    @Test
    void test() {
        List<Word> words = Store.UNWANTED_WORDS;
        AhoCorasickDoubleArrayTrie trie = new AhoCorasickDoubleArrayTrie(words);
        for (Word word : words) {
            assertThat(trie.matches(word.getWord())).isTrue();
        }
        assertThat(trie.matches("人".toCharArray())).isFalse();
        assertThat(trie.matches("恋".toCharArray())).isFalse();
        assertThat(trie.matches("人目忍ぶ".toCharArray())).isFalse();
        assertThat(trie.matches("忍ぶ恋".toCharArray())).isFalse();
        assertThat(trie.matches("人目忍ぶ恋".toCharArray())).isTrue();

        assertThat(trie.matches("oo".toCharArray())).isFalse();
        assertThat(trie.matches("oh no".toCharArray())).isTrue();

        assertThat(trie.matches("𤳵靐".toCharArray())).isTrue();
        assertThat(trie.matches("靐𤳵".toCharArray())).isTrue();
        assertThat(trie.matches("𤳵𤳵".toCharArray())).isTrue();
        assertThat(trie.matches("靐靐".toCharArray())).isFalse();
    }

}
