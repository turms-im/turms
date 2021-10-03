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

package im.turms.plugin.spam.ac;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author James Chen
 */
class AhoCorasickCodecTests {

    private static final String FILE = "./aho-corasick-codec-test-temp.bin";

    @SneakyThrows
    @Test
    void test() {
        try {
            test0();
        } finally {
            Files.delete(Path.of(FILE));
        }
    }

    @SneakyThrows
    void test0() {
        AhoCorasickDoubleArrayTrie trie = new AhoCorasickDoubleArrayTrie(Store.TERMS);
        AhoCorasickCodec.serialize(trie, FILE);
        AhoCorasickDoubleArrayTrie actualTrie = AhoCorasickCodec.deserialize(FILE);

        assertThat(actualTrie.fail).containsExactly(trie.fail);
        assertThat(actualTrie.output).isDeepEqualTo(trie.output);
        assertThat(actualTrie.termLengths).containsExactly(trie.termLengths);
        assertThat(actualTrie.dat.base).containsExactly(trie.dat.base);
        assertThat(actualTrie.dat.check).containsExactly(trie.dat.check);
        assertThat(actualTrie.dat.capacity).isEqualTo(trie.dat.capacity);
    }

}
