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

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

/**
 * @author James Chen
 */
public final class AhoCorasickCodec {

    private AhoCorasickCodec() {
    }

    public static void main(String[] args) throws IOException {
        String dictPath = parseArg(args, 0);
        String charsetName = parseArg(args, 1);
        if (dictPath == null) {
            throw new RuntimeException("The dictionary path must be specified");
        }
        if (charsetName == null) {
            System.out.println("The charset isn't specified, and falls back to UTF-8");
            charsetName = "UTF-8";
        }
        Path path = Path.of(dictPath);
        List<char[]> list = new LinkedList<>();
        try (InputStream stream = Files.newInputStream(path)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream, charsetName));
            String line;
            while ((line = reader.readLine()) != null) {
                list.add(line.toCharArray());
            }
        }
        AhoCorasickDoubleArrayTrie trie = new AhoCorasickDoubleArrayTrie(list);
        serialize(trie, path.getParent().resolve("words.bin").toString());
    }

    public static void serialize(AhoCorasickDoubleArrayTrie trie, String outputFile) throws IOException {
        try (FileOutputStream stream = new FileOutputStream(outputFile, false)) {
            try (ObjectOutputStream outputStream = new ObjectOutputStream(stream)) {
                // version
                outputStream.writeInt(1);

                outputStream.writeObject(trie.fail);
                outputStream.writeObject(trie.output);
                outputStream.writeObject(trie.termLengths);

                outputStream.writeObject(trie.dat.base);
                outputStream.writeObject(trie.dat.check);
                outputStream.writeInt(trie.dat.capacity);
            }
        }
    }

    public static AhoCorasickDoubleArrayTrie deserialize(String file) throws IOException, ClassNotFoundException {
        try (FileInputStream stream = new FileInputStream(file)) {
            try (ObjectInputStream inputStream = new ObjectInputStream(stream)) {
                // version
                int version = inputStream.readInt();
                if (version != 1) {
                    throw new RuntimeException("Unknown version: " + version);
                }
                int[] fail = (int[]) inputStream.readObject();
                int[][] output = (int[][]) inputStream.readObject();
                int[] termLengths = (int[]) inputStream.readObject();

                int[] base = (int[]) inputStream.readObject();
                int[] check = (int[]) inputStream.readObject();
                int capacity = inputStream.readInt();
                DoubleArrayTrie trie = new DoubleArrayTrie(base, check, capacity);
                return new AhoCorasickDoubleArrayTrie(fail, output, termLengths, trie);
            }
        }
    }

    private static String parseArg(String[] args, int index) {
        if (args.length <= index) {
            return null;
        }
        return args[index];
    }

}
