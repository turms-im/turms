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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import im.turms.server.common.access.client.dto.ClientMessagePool;
import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 * @see ClientMessagePool
 */
class ClientMessagePoolGenerator {

    public static void main(String[] args) throws IOException {
        String packageName = ClientMessagePool.class.getPackage()
                .getName()
                .replace('.', '/');
        Path path = Paths.get("src/main/java/"
                + packageName);
        Set<String> names = new HashSet<>(256);
        Pattern pattern = Pattern.compile("\\{@code im\\.turms\\.proto\\.(.*)\\}");
        Files.find(path, Integer.MAX_VALUE, (p, basicFileAttributes) -> {
            String file = p.getFileName()
                    .toString();
            boolean isValid = file.endsWith(".java")
                    && !file.endsWith("OrBuilder.java")
                    && !file.endsWith("OuterClass.java");
            if (!isValid) {
                return false;
            }
            try {
                for (String line : Files.readAllLines(p, StandardCharsets.UTF_8)) {
                    // " * Protobuf type {@code im.turms.proto.TurmsNotification.Data}"
                    if (line.contains("* Protobuf type {@code")) {
                        Matcher matcher = pattern.matcher(line);
                        if (matcher.find()) {
                            String group = matcher.group(1);
                            names.add(group);
                        }
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return false;
        })
                .count();
        List<String> modelNames = new ArrayList<>(names);
        modelNames.sort(String::compareTo);
        StringBuilder builder = new StringBuilder(65536);
        for (String name : modelNames) {
            String nameWithoutDot = name.replace(".", "");
            builder.append("""

                    private static final FastThreadLocal<%s.Builder> %s = new FastThreadLocal<>() {
                        @Override
                        protected %s.Builder initialValue() {
                            return %s.newBuilder();
                        }
                    };
                    """.formatted(name,
                    StringUtil.upperCamelToUpperUnderscoreLatin1(nameWithoutDot),
                    name,
                    name));
        }
        for (String name : modelNames) {
            String nameWithoutDot = name.replace(".", "");
            builder.append("""

                    public static %s.Builder get%sBuilder() {
                        return %s.get().clear();
                    }
                    """.formatted(name,
                    nameWithoutDot,
                    StringUtil.upperCamelToUpperUnderscoreLatin1(nameWithoutDot)));
        }
        String output = builder.toString();

        System.out.println(output);
    }

}
