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

import java.io.IOException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import com.ibm.icu.lang.UCharacter;
import com.ibm.icu.text.Transliterator;
import lombok.SneakyThrows;

import im.turms.server.common.infra.io.InputOutputException;

/**
 * @author James Chen
 */
public class CharNormalizerGenerator {

    @SneakyThrows
    public static void main(String[] args) {
        URI outputPath = CharNormalizerGenerator.class.getClassLoader()
                .getResource(".")
                .toURI()
                .resolve("../../src/main/java/im/turms/plugin/antispam/character/data");
        generate(Path.of(outputPath));
    }

    public static void generate(Path outputDir) {
        DataSet dataSet = generateDataSet();
        CodePointEntry[] entries = dataSet.entries;
        Map<String, CodePointEntry> common = dataSet.common;
        generateCommonFile(common, outputDir);
        for (int high = 0; high < 256; high++) {
            generateDataFile(high, entries, common, outputDir);
        }
        System.out.println("The data files have been generated in "
                + outputDir.toString());
    }

    private static DataSet generateDataSet() {
        CodePointEntry[] entries = new CodePointEntry[65536];
        Map<String, CodePointEntry> cache = new HashMap<>(16384);
        Map<String, CodePointEntry> common = new TreeMap<>();
        Transliterator transliterator =
                Transliterator.getInstance("Any-Latin; NFD; [^a-zA-Z0-9-] Remove");
        for (int i = 0; i < 65536; i++) {
            char rawChar = (char) i;
            double num = UCharacter.getUnicodeNumericValue(i);
            char[] targetChars;
            if (num == UCharacter.NO_NUMERIC_VALUE) {
                // e.g. "à" -> "a"; "樂乐" -> "lele"; "あいうえお" -> "aiueo"
                targetChars = transliterator.transliterate(String.valueOf(rawChar))
                        .toLowerCase()
                        .toCharArray();
            } else {
                // TODO: support fractions
                if (num % 1 == 0) {
                    targetChars = String.valueOf((int) num)
                            .toCharArray();
                } else {
                    targetChars = new char[]{rawChar};
                }
            }
            String cacheKey = normalizeCacheKey(targetChars);
            CodePointEntry entry = cache.get(cacheKey);
            CodePointEntry newEntry = new CodePointEntry(rawChar, targetChars, cacheKey);
            if (entry == null) {
                cache.put(cacheKey, newEntry);
            } else {
                common.put(cacheKey, entry);
            }
            entries[i] = newEntry;
        }
        return new DataSet(entries, common);
    }

    private static String normalizeCacheKey(char[] chars) {
        StringBuilder builder = new StringBuilder(1 + chars.length * 4);
        builder.append('$');
        for (char c : chars) {
            builder.append(Integer.toHexString(c)
                    .toUpperCase());
        }
        return builder.toString();
    }

    @SneakyThrows
    private static void generateCommonFile(Map<String, CodePointEntry> common, Path outputDir) {
        StringBuilder builder = new StringBuilder(common.size() * 96);
        for (Map.Entry<String, CodePointEntry> entry : common.entrySet()) {
            builder.append("    public static final char[] ")
                    .append(entry.getKey())
                    .append(" = ")
                    .append(formatCodePoints(entry.getValue().targetChars))
                    .append(";\n");
        }
        // language=JAVA
        String template = """
                // Generated Code - Do NOT edit manually

                package im.turms.plugin.antispam.character.data;

                public final class Common {

                %s
                    private Common() {}

                }
                """.formatted(builder.toString());
        writeStr(outputDir.resolve("Common.java"), template);
    }

    @SneakyThrows
    private static void generateDataFile(
            int high,
            CodePointEntry[] entries,
            Map<String, CodePointEntry> common,
            Path outputDir) {
        StringBuilder builder = new StringBuilder(256 * 32);
        int startIndex = high << 8;
        for (int i = 0; i < 256; i++) {
            CodePointEntry entry = entries[startIndex + i];
            builder.append("            ");
            String cacheKey = entry.cacheKey;
            if (cacheKey != null && common.containsKey(cacheKey)) {
                builder.append("Common.%s, // '%c'(%04X) -> \"%s\"".formatted(cacheKey,
                        getSafeCharForDisplay(entry.rawChar),
                        (int) entry.rawChar,
                        new String(entry.targetChars)));
            } else {
                builder.append("%s, // '%c'(%04X)".formatted(formatCodePoints(entry.targetChars),
                        getSafeCharForDisplay(entry.rawChar),
                        (int) entry.rawChar));
            }
            if (i < 255) {
                builder.append('\n');
            }
        }
        String className = "U%02X".formatted(high);
        // language=JAVA
        String template = """
                // Generated Code - Do NOT edit manually

                package im.turms.plugin.antispam.character.data;

                public final class %s {

                    public static final char[][] DATA = {
                %s
                    };

                    private %s() {}

                }
                """.formatted(className, builder.toString(), className);
        writeStr(outputDir.resolve(className
                + ".java"), template);
    }

    private static char getSafeCharForDisplay(char c) {
        if (Character.isSurrogate(c) || c == '\n' || c == '\r') {
            return '?';
        }
        return c;
    }

    private static String formatCodePoints(char[] chars) {
        StringBuilder builder = new StringBuilder(chars.length * 16);
        builder.append("{");
        for (int i = 0; i < chars.length; i++) {
            builder.append("'")
                    .append(chars[i])
                    .append("'");
            if (i != chars.length - 1) {
                builder.append(", ");
            }
        }
        builder.append("}");
        return builder.toString();
    }

    private static void writeStr(Path path, String str) {
        try {
            Files.writeString(path, str, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to write to the file: "
                            + path.toAbsolutePath()
                            + ".\n\""
                            + str
                            + "\"",
                    e);
        }
    }

    private record DataSet(
            CodePointEntry[] entries,
            Map<String, CodePointEntry> common
    ) {
    }

    private record CodePointEntry(
            char rawChar,
            char[] targetChars,
            String cacheKey
    ) {
    }

}
