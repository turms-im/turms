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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.util.List;
import java.util.TimeZone;

import im.turms.plugin.antispam.TextPreprocessor;
import im.turms.server.common.infra.collection.ChunkedArrayList;
import im.turms.server.common.infra.exception.IncompatibleInternalChangeException;
import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.lang.CharArrayBuffer;
import im.turms.server.common.infra.lang.ClassUtil;

/**
 * @author James Chen
 */
public class DictionaryParser {

    private static final String VALID_CODE_POINT_RANGES_REF;

    static {
        try {
            VALID_CODE_POINT_RANGES_REF = ClassUtil.getReference(
                    TextPreprocessor.class.getDeclaredField("VALID_CODE_POINT_RANGES"));
        } catch (NoSuchFieldException e) {
            throw new IncompatibleInternalChangeException(e);
        }
    }

    private final TextPreprocessor textPreprocessor;

    public DictionaryParser(TextPreprocessor textPreprocessor) {
        this.textPreprocessor = textPreprocessor;
    }

    public List<Word> parse(
            Path dictPath,
            String charsetName,
            boolean skipInvalidCharacter,
            boolean enableExtendedWord) {
        try (InputStream stream = Files.newInputStream(dictPath);
                BufferedReader reader =
                        new BufferedReader(new InputStreamReader(stream, charsetName))) {
            return enableExtendedWord
                    ? parseExtendedWords(reader, skipInvalidCharacter)
                    : parseSimpleWords(reader, skipInvalidCharacter);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to read the dictionary: "
                            + dictPath,
                    e);
        }
    }

    private List<Word> parseSimpleWords(BufferedReader reader, boolean skipInvalidCharacter) {
        List<Word> words = new ChunkedArrayList<>();
        // use CharArrayBuffer to collect each line because
        // it is far faster and more efficient than "reader.readLine()"
        CharArrayBuffer buffer = new CharArrayBuffer(64);
        int result;
        char character;
        boolean ignoreFollowingChars = false;
        boolean isFileEnd;
        while (true) {
            try {
                result = reader.read();
            } catch (IOException e) {
                throw new InputOutputException("Failed to read", e);
            }
            isFileEnd = result == -1;
            character = (char) result;
            if (character == '\n' || character == '\r' || isFileEnd) {
                if (!buffer.isEmpty()) {
                    words.add(new Word(buffer.toCharArray()));
                }
                if (isFileEnd) {
                    break;
                } else {
                    buffer.clear();
                    ignoreFollowingChars = false;
                    continue;
                }
            } else if (character == '\t' || character == ',') {
                ignoreFollowingChars = true;
            }
            if (!ignoreFollowingChars) {
                normalizeChar(skipInvalidCharacter, buffer, character);
            }
        }
        return words;
    }

    private List<Word> parseExtendedWords(BufferedReader reader, boolean skipInvalidCharacter) {
        List<Word> words = new ChunkedArrayList<>();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        dateFormat.setTimeZone(TimeZone.getTimeZone(ZoneOffset.UTC));
        CharArrayBuffer buffer = new CharArrayBuffer(64);
        int result;
        char character;
        ExtendedWord.Builder builder = ExtendedWord.builder();
        int columnIndex = 0;
        boolean isWordParsed = false;
        char[] word;
        boolean isFileEnd;
        while (true) {
            try {
                result = reader.read();
            } catch (IOException e) {
                throw new InputOutputException("Failed to read", e);
            }
            isFileEnd = result == -1;
            character = (char) result;
            // TODO: support quotes
            boolean isRecordEnd = character == '\n' || character == '\r';
            boolean isSeparator = character == '\t' || character == ',';
            if (isRecordEnd || isSeparator || isFileEnd) {
                parseExtendedWord(builder, columnIndex, buffer, dateFormat);
                if (columnIndex == 0) {
                    isWordParsed = true;
                }
                buffer.clear();
                if (isRecordEnd || isFileEnd) {
                    word = builder.getWord();
                    if (word != null && word.length > 0) {
                        words.add(builder.build());
                    }
                    if (isFileEnd) {
                        break;
                    } else {
                        builder.reset();
                        columnIndex = 0;
                        isWordParsed = false;
                    }
                } else {
                    columnIndex++;
                }
            } else if (isWordParsed) {
                buffer.append(character);
            } else {
                normalizeChar(skipInvalidCharacter, buffer, character);
            }
        }
        return words;
    }

    private void parseExtendedWord(
            ExtendedWord.Builder builder,
            int columnIndex,
            CharArrayBuffer buffer,
            SimpleDateFormat dateFormat) {
        if (buffer.isEmpty()) {
            return;
        }
        if (columnIndex == 0) {
            builder.setWord(buffer.toCharArray());
            return;
        }
        String string = buffer.toString();
        try {
            switch (columnIndex) {
                case 1 -> builder.setId(string);
                case 2 -> builder.setLevel(Integer.parseInt(string));
                case 3 -> builder.setCategory(string);
                case 4 -> builder.setSource(string);
                case 5 -> builder.setCreateTime(dateFormat.parse(string));
                case 6 -> builder.setDisableTime(dateFormat.parse(string));
                case 7 -> builder.setEnableTime(dateFormat.parse(string));
                case 8 -> builder.setUpdateTime(dateFormat.parse(string));
                case 9 -> builder.setComment(string);
                default -> throw new IllegalArgumentException(
                        "Unexpected column index: "
                                + columnIndex);
            }
        } catch (ParseException e) {
            throw new IllegalArgumentException(
                    "The date string \""
                            + string
                            + "\" must follow the date format pattern: "
                            + dateFormat.toPattern());
        }
    }

    public char[] parseWord(String word, boolean skipInvalidCharacter) {
        return parseWord(word.toCharArray(), skipInvalidCharacter);
    }

    /**
     * "Hello,./" -> "hello" "⑩*&(元Ⅰ[]二角" -> ["10元12角", "10yuan12jiao"]
     */
    public char[] parseWord(char[] word, boolean skipInvalidCharacter) {
        CharArrayBuffer newWord = new CharArrayBuffer(word.length);
        for (char character : word) {
            normalizeChar(skipInvalidCharacter, newWord, character);
        }
        return newWord.toCharArray();
    }

    private void normalizeChar(
            boolean skipInvalidCharacter,
            CharArrayBuffer newWord,
            char character) {
        Object newChars = textPreprocessor.process(character);
        if (newChars == null) {
            if (!skipInvalidCharacter) {
                throw new IllegalArgumentException(
                        "The character \""
                                + character
                                + "\" of the unicode block \""
                                + Character.UnicodeBlock.of(character)
                                + "\" is invalid. "
                                + "Please delete the character in the dictionary, "
                                + "or update the field ("
                                + VALID_CODE_POINT_RANGES_REF
                                + ") to support the character");
            }
        } else if (newChars instanceof char[] chars) {
            newWord.append(chars);
        } else {
            newWord.append((char) newChars);
        }
    }

}