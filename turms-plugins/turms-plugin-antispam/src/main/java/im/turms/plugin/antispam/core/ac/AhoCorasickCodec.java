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

package im.turms.plugin.antispam.core.ac;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import jakarta.annotation.Nullable;

import io.netty.buffer.PooledByteBufAllocator;

import im.turms.plugin.antispam.core.TextPreprocessor;
import im.turms.plugin.antispam.core.dictionary.DictionaryParser;
import im.turms.plugin.antispam.core.dictionary.ExtendedWord;
import im.turms.plugin.antispam.core.dictionary.Word;
import im.turms.plugin.antispam.core.exception.CorruptedTrieDataException;
import im.turms.plugin.antispam.property.TextParsingStrategy;
import im.turms.server.common.infra.io.FileUtil;
import im.turms.server.common.infra.io.Stream;
import im.turms.server.common.infra.serialization.DeserializationException;
import im.turms.server.common.infra.serialization.SerializationException;
import im.turms.server.common.infra.unit.ByteSizeUnit;

/**
 * @author James Chen
 */
public final class AhoCorasickCodec {

    private static final int VERSION = 0;

    private static final int TAG_WORD_WORD = 0;
    private static final int TAG_WORD_ID = 1;
    private static final int TAG_WORD_LEVEL = 2;
    private static final int TAG_WORD_CATEGORY = 3;
    private static final int TAG_WORD_SOURCE = 4;
    private static final int TAG_WORD_CREATE_DATE = 5;
    private static final int TAG_WORD_DISABLE_DATE = 6;
    private static final int TAG_WORD_ENABLE_DATE = 7;
    private static final int TAG_WORD_UPDATE_DATE = 8;
    private static final int TAG_WORD_COMMENT = 9;

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
        Stream stream = new Stream(
                PooledByteBufAllocator.DEFAULT.directBuffer(
                        // TODO: estimate the size according to the trie.
                        ByteSizeUnit.MB));
        RuntimeException cause = null;
        try {
            stream.writeByte(VERSION);

            stream.writeSizeAndSparseInts(trie.fail);
            stream.writeSizeAndSparseInt2DArray(trie.output);
            stream.writeSizeAndSparseInts(trie.dat.base);
            stream.writeSizeAndSparseInts(trie.dat.check);
            stream.writeVarint32(trie.dat.capacity);

            Word[] words = trie.words;
            stream.writeVarint32(words.length);
            for (Word word : words) {
                writeWord(stream, word);
            }
            FileUtil.write(new File(outputFile), stream.getBuffer());
        } catch (Exception e) {
            cause = new SerializationException("Failed to serialize the trie", e);
            throw cause;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                if (cause == null) {
                    throw new SerializationException("Failed to close the stream", e);
                } else {
                    cause.addSuppressed(e);
                }
            }
        }
    }

    public static AhoCorasickDoubleArrayTrie deserialize(String file) {
        FileChannel fileChannel;
        try {
            fileChannel = FileChannel.open(Path.of(file), StandardOpenOption.READ);
        } catch (IOException e) {
            throw new DeserializationException(
                    "Failed to open the file: "
                            + file,
                    e);
        }
        RuntimeException cause = null;
        ByteBuffer buffer = FileUtil.read(fileChannel);
        Stream stream = new Stream(buffer);
        try {
            return deserialize(stream);
        } catch (Exception e) {
            cause = new DeserializationException("Failed to deserialize the trie", e);
            throw cause;
        } finally {
            try {
                stream.close();
            } catch (Exception e) {
                if (cause == null) {
                    cause = new DeserializationException("Failed to close the stream", e);
                } else {
                    cause.addSuppressed(e);
                }
            }
            try {
                fileChannel.close();
            } catch (Exception e) {
                if (cause == null) {
                    cause = new DeserializationException(
                            "Failed to close the file: "
                                    + file,
                            e);
                } else {
                    cause.addSuppressed(e);
                }
            }
            if (cause != null) {
                throw cause;
            }
        }
    }

    private static AhoCorasickDoubleArrayTrie deserialize(Stream stream) {
        // version
        byte version = stream.readByte();
        if (version != VERSION) {
            throw new CorruptedTrieDataException(
                    "Unknown version: "
                            + version);
        }
        int[] fail;
        int[][] output;
        int[] base;
        int[] check;
        int capacity;
        int wordCount;
        Word[] words;
        try {
            fail = stream.readSparseInts();
            output = stream.readSparseInt2DArray();
            base = stream.readSparseInts();
            check = stream.readSparseInts();
            capacity = stream.readVarint32();
            wordCount = stream.readVarint32();
            words = new Word[wordCount];
            for (int i = 0; i < wordCount; i++) {
                words[i] = readWord(stream);
            }
        } catch (Exception e) {
            throw new CorruptedTrieDataException("Failed to deserialize the trie", e);
        }
        DoubleArrayTrie trie = new DoubleArrayTrie(base, check, capacity);
        return new AhoCorasickDoubleArrayTrie(fail, output, trie, words);
    }

    private static void writeWord(Stream stream, Word word) {
        stream.writeByte(TAG_WORD_WORD)
                .writeSizeAndChars(word.getWord());
        if (!(word instanceof ExtendedWord extendedWord)) {
            return;
        }
        String id = extendedWord.getId();
        Integer level = extendedWord.getLevel();
        String category = extendedWord.getCategory();
        String source = extendedWord.getSource();
        Date createDate = extendedWord.getCreateDate();
        Date disableDate = extendedWord.getDisableDate();
        Date enableDate = extendedWord.getEnableDate();
        Date updateDate = extendedWord.getUpdateDate();
        String comment = extendedWord.getComment();
        if (id != null) {
            stream.writeByte(TAG_WORD_ID)
                    .writeString(id);
        }
        if (level != null) {
            stream.writeByte(TAG_WORD_LEVEL)
                    .writeVarint32(level);
        }
        if (category != null) {
            stream.writeByte(TAG_WORD_CATEGORY)
                    .writeString(category);
        }
        if (source != null) {
            stream.writeByte(TAG_WORD_SOURCE)
                    .writeString(source);
        }
        if (createDate != null) {
            stream.writeByte(TAG_WORD_CREATE_DATE)
                    .writeLong(createDate.getTime());
        }
        if (disableDate != null) {
            stream.writeByte(TAG_WORD_DISABLE_DATE)
                    .writeLong(disableDate.getTime());
        }
        if (enableDate != null) {
            stream.writeByte(TAG_WORD_ENABLE_DATE)
                    .writeLong(enableDate.getTime());
        }
        if (updateDate != null) {
            stream.writeByte(TAG_WORD_UPDATE_DATE)
                    .writeLong(updateDate.getTime());
        }
        if (comment != null) {
            stream.writeByte(TAG_WORD_COMMENT)
                    .writeString(comment);
        }
    }

    private static Word readWord(Stream stream) {
        char[] word = null;
        String id = null;
        Integer level = null;
        String category = null;
        String source = null;
        Date createDate = null;
        Date disableDate = null;
        Date enableDate = null;
        Date updateDate = null;
        String comment = null;
        byte type = stream.readByte();
        switch (type) {
            case TAG_WORD_WORD -> word = stream.readChars();
            case TAG_WORD_ID -> id = stream.readString();
            case TAG_WORD_LEVEL -> level = stream.readVarint32();
            case TAG_WORD_CATEGORY -> category = stream.readString();
            case TAG_WORD_SOURCE -> source = stream.readString();
            case TAG_WORD_CREATE_DATE -> createDate = new Date(stream.readLong());
            case TAG_WORD_DISABLE_DATE -> disableDate = new Date(stream.readLong());
            case TAG_WORD_ENABLE_DATE -> enableDate = new Date(stream.readLong());
            case TAG_WORD_UPDATE_DATE -> updateDate = new Date(stream.readLong());
            case TAG_WORD_COMMENT -> comment = stream.readString();
            default -> throw new DeserializationException(
                    "Unknown type: "
                            + type);
        }
        return new ExtendedWord(
                word,
                id,
                level,
                category,
                source,
                createDate,
                disableDate,
                enableDate,
                updateDate,
                comment);
    }

    @Nullable
    private static String parseArg(String[] args, int index) {
        if (args.length <= index) {
            return null;
        }
        return args[index];
    }
}