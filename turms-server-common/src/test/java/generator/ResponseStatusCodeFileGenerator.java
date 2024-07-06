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

package generator;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.List;

import lombok.SneakyThrows;

import im.turms.server.common.access.common.ResponseStatusCode;

/**
 * @author James Chen
 */
class ResponseStatusCodeFileGenerator {

    @SneakyThrows
    public static void main(String[] args) {

        Class<ResponseStatusCode> responseStatusCodeClass = ResponseStatusCode.class;
        URI dir = responseStatusCodeClass.getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI();
        String filename = responseStatusCodeClass.getCanonicalName()
                .replace('.', '/')
                + ".java";
        Path path = Path.of(dir)
                // \target\classes\
                .resolve("../../src/main/java")
                .resolve(filename);
        List<String> lines = Files.readAllLines(path);

        // cpp
        generateSourceCodeFile("h",
                lines,
                0,
                (statusCodeName, statusCode, groupName, isFirstGroupCode) -> {
                    String codeName = convertToCamelCase(statusCodeName);
                    return "const int k"
                            + codeName.substring(0, 1)
                                    .toUpperCase()
                            + codeName.substring(1)
                            + " = "
                            + statusCode
                            + ";";
                });

        // dart
        generateSourceCodeFile("dart",
                lines,
                2,
                (statusCodeName, statusCode, groupName, isFirstGroupCode) -> "static const "
                        + convertToCamelCase(statusCodeName)
                        + " = "
                        + statusCode
                        + ";");

        // kotlin
        generateSourceCodeFile("kt",
                lines,
                4,
                (statusCodeName, statusCode, groupName, isFirstGroupCode) -> "const val "
                        + statusCodeName
                        + " = "
                        + statusCode);

        // typescript
        generateSourceCodeFile("ts",
                lines,
                4,
                (statusCodeName, statusCode, groupName, isFirstGroupCode) -> isFirstGroupCode
                        ? statusCodeName
                                + " = "
                                + statusCode
                                + ","
                        : statusCodeName
                                + ",");

        // swift
        generateSourceCodeFile("swift",
                lines,
                4,
                (statusCodeName, statusCode, groupName, isFirstGroupCode) -> {
                    String codeName = convertToCamelCase(statusCodeName);
                    return isFirstGroupCode
                            ? "case "
                                    + codeName
                                    + " = "
                                    + statusCode
                            : "case "
                                    + codeName;
                });

        // markdown
        generateSourceCodeFile("md",
                lines,
                0,
                false,
                (statusCodeName, statusCode, groupName, isFirstGroupCode) -> isFirstGroupCode
                        ? "|"
                                + groupName
                                + "|"
                                + statusCodeName
                                + "|"
                                + statusCode
                                + "|"
                        : "||"
                                + statusCodeName
                                + "|"
                                + statusCode
                                + "|");
    }

    private static String convertToCamelCase(String input) {
        String[] words = input.toLowerCase()
                .split("_");
        StringBuilder builder = new StringBuilder(64);
        int length = words.length;
        for (int i = 0; i < length; i++) {
            if (i == 0) {
                builder.append(words[i]);
            } else {
                builder.append(Character.toUpperCase(words[i].charAt(0)))
                        .append(words[i].substring(1));
            }
        }
        return builder.toString();
    }

    private static void generateSourceCodeFile(
            String fileSuffix,
            List<String> lines,
            int indent,
            StatusCodeTransformer transformer) throws IOException {
        generateSourceCodeFile(fileSuffix, lines, indent, true, transformer);
    }

    private static void generateSourceCodeFile(
            String fileSuffix,
            List<String> lines,
            int indent,
            boolean outputComments,
            StatusCodeTransformer transformer) throws IOException {
        StringBuilder builder = new StringBuilder(1024 * 16);
        boolean start = false;
        int lastStatusCode = Integer.MIN_VALUE;
        String indentStr = " ".repeat(indent);
        String groupName = null;
        for (String line : lines) {
            line = line.trim();
            if (!start) {
                start = line.startsWith("public enum ResponseStatusCode {");
                continue;
            }
            if (line.isBlank()) {
                if (outputComments) {
                    builder.append('\n');
                }
                continue;
            }
            if (line.contains("TODO")) {
                continue;
            }
            if (line.startsWith("//")) {
                if (outputComments) {
                    builder.append(indentStr)
                            .append(line)
                            .append('\n');
                }
                groupName = line.substring(2)
                        .trim();
                continue;
            }
            if (line.endsWith(";")) {
                break;
            }
            char firstChar = line.charAt(0);
            if (firstChar < 'A' || firstChar > 'Z') {
                continue;
            }
            int constructorIndex = line.indexOf('(');
            String statusCodeName = line.substring(0, constructorIndex);
            String statusCodeStr =
                    line.substring(constructorIndex + 1, line.indexOf(',', constructorIndex));
            int statusCode;
            try {
                statusCode = Integer.parseInt(statusCodeStr);
            } catch (NumberFormatException e) {
                throw new RuntimeException(
                        "Failed to parse \""
                                + statusCodeStr
                                + "\" as number",
                        e);
            }
            String newLine = transformer.transform(statusCodeName,
                    statusCode,
                    groupName,
                    statusCode != lastStatusCode + 1);
            builder.append(indentStr)
                    .append(newLine)
                    .append('\n');
            lastStatusCode = statusCode;
        }
        String code = builder.toString();
        Path path = Path.of("./code."
                + fileSuffix);
        Files.writeString(path,
                code,
                StandardOpenOption.CREATE,
                StandardOpenOption.TRUNCATE_EXISTING);

        System.out.println("Generated a file: "
                + path.toAbsolutePath()
                        .normalize());
    }

    private interface StatusCodeTransformer {
        String transform(
                String statusCodeName,
                int statusCode,
                String groupName,
                boolean isFirstGroupCode);
    }

}