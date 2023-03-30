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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.List;

import im.turms.plugin.antispam.TextPreprocessor;
import im.turms.plugin.antispam.dictionary.DictionaryParser;
import im.turms.plugin.antispam.dictionary.Word;
import im.turms.plugin.antispam.property.TextParsingStrategy;
import im.turms.server.common.infra.serialization.DeserializationException;
import im.turms.server.common.infra.serialization.SerializationException;

/**
 * @author James Chen
 */
public final class AhoCorasickCodec {

    private AhoCorasickCodec() {
    }

    public static void main(String[] args) {
        String dictPath = parseArg(args, 0);
        String charsetName = parseArg(args, 1);
        String skipInvalidCharacterStr = parseArg(args, 2);
        String wordParsingStrategyStr = parseArg(args, 3);
        String enableExtendedWordStr = parseArg(args, 4);

        boolean skipInvalidCharacter = false;
        boolean enableExtendedWord = true;
        TextParsingStrategy parsingStrategy = TextParsingStrategy.NORMALIZATION_TRANSLITERATION;
        if (dictPath == null) {
            throw new RuntimeException("The dictionary path must be specified");
        }
        if (charsetName == null) {
            System.out.println("The charset is not specified, and falls back to UTF-8");
            charsetName = "UTF-8";
        }
        if (skipInvalidCharacterStr != null) {
            skipInvalidCharacter = Boolean.parseBoolean(skipInvalidCharacterStr);
        }
        if (wordParsingStrategyStr != null) {
            parsingStrategy = TextParsingStrategy.valueOf(wordParsingStrategyStr);
        }
        if (enableExtendedWordStr != null) {
            enableExtendedWord = Boolean.parseBoolean(enableExtendedWordStr);
        }
        Path path = Path.of(dictPath);
        DictionaryParser parser = new DictionaryParser(new TextPreprocessor(parsingStrategy));
        List<Word> words =
                parser.parse(path, charsetName, skipInvalidCharacter, enableExtendedWord);
        AhoCorasickDoubleArrayTrie trie = new AhoCorasickDoubleArrayTrie(words);
        serialize(trie,
                path.getParent()
                        .resolve("words.bin")
                        .toString());
    }

    public static void serialize(AhoCorasickDoubleArrayTrie trie, String outputFile) {
        try (FileOutputStream stream = new FileOutputStream(outputFile, false)) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(stream)) {
                // version
                outputStream.writeInt(1);

                outputStream.writeObject(trie.fail);
                outputStream.writeObject(trie.output);
                outputStream.writeObject(trie.words);

                outputStream.writeObject(trie.dat.base);
                outputStream.writeObject(trie.dat.check);
                outputStream.writeInt(trie.dat.capacity);
            }
        } catch (Exception e) {
            throw new SerializationException("Failed to serialize the trie", e);
        }
    }

    public static AhoCorasickDoubleArrayTrie deserialize(String file) {
        try (FileInputStream stream = new FileInputStream(file);
                ObjectInputStream inputStream = new ObjectInputStream(stream)) {
            // version
            int version = inputStream.readInt();
            if (version != 1) {
                throw new DeserializationException(
                        "Unknown version: "
                                + version);
            }
            int[] fail = (int[]) inputStream.readObject();
            int[][] output = (int[][]) inputStream.readObject();
            Word[] words = (Word[]) inputStream.readObject();

            int[] base = (int[]) inputStream.readObject();
            int[] check = (int[]) inputStream.readObject();
            int capacity = inputStream.readInt();
            DoubleArrayTrie trie = new DoubleArrayTrie(base, check, capacity);
            return new AhoCorasickDoubleArrayTrie(fail, output, trie, words);
        } catch (Exception e) {
            throw new DeserializationException("Failed to deserialize the trie", e);
        }
    }

    private static String parseArg(String[] args, int index) {
        if (args.length <= index) {
            return null;
        }
        return args[index];
    }
}