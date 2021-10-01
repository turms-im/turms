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

import im.turms.plugin.spam.ac.AhoCorasickDoubleArrayTrie;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class AhoCorasickDoubleArrayTrieTests {

    @Test
    void test() {
        List<char[]> terms = Stream.of(
                        "薬指",
                        "リング",
                        "人目忍ぶ恋",
                        "選んだ",
                        "強い",
                        "女",
                        "見えて",
                        "心",
                        "中",
                        "切なさ",
                        "揺れて",
                        "oh no",
                        "oh yes",
                        "but no",
                        "loving you is not right")
                .map(String::toCharArray)
                .toList();
        AhoCorasickDoubleArrayTrie trie = new AhoCorasickDoubleArrayTrie(terms);
        assertThat(trie.matches("人".toCharArray())).isFalse();
        assertThat(trie.matches("恋".toCharArray())).isFalse();
        assertThat(trie.matches("人目忍ぶ".toCharArray())).isFalse();
        assertThat(trie.matches("忍ぶ恋".toCharArray())).isFalse();
        assertThat(trie.matches("人目忍ぶ恋".toCharArray())).isTrue();

        assertThat(trie.matches("oo".toCharArray())).isFalse();
        assertThat(trie.matches("oh no".toCharArray())).isTrue();
    }

}
