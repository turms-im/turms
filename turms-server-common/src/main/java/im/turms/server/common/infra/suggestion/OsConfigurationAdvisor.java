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

package im.turms.server.common.infra.suggestion;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import jakarta.annotation.Nullable;

import com.sun.management.UnixOperatingSystemMXBean;
import org.apache.commons.lang3.SystemUtils;

public class OsConfigurationAdvisor {

    private static final String SYS = "/proc/sys/";

    private OsConfigurationAdvisor() {
    }

    public static List<String> getSuggestions() {
        if (!SystemUtils.IS_OS_LINUX) {
            return Collections.emptyList();
        }
        Suggestion[] suggestionList =
                new Suggestion[]{Suggestion.newRange("fs.file-max", 10_000, 1_629_424, 1_629_424),

                        Suggestion.newRange("vm.swappiness", 1, 10, 10),

                        Suggestion.newRange("net.core.somaxconn", 4096, 65536, 65536),

                        Suggestion.newRange("net.ipv4.tcp_max_syn_backlog", 4096, 65536, 65536),
                        Suggestion.newOption("net.ipv4.tcp_syncookies", 1),
                        Suggestion.newOption("net.ipv4.tcp_no_metrics_save", 1),
                        Suggestion.newRange("net.ipv4.tcp_retries2", 8, 15, 10),
                        Suggestion.newOption("net.ipv4.tcp_sack", 1),
                        Suggestion.newOption("net.ipv4.tcp_abort_on_overflow", 0),
                        Suggestion.newOption("net.ipv4.tcp_fastopen", 3),
                        Suggestion.newOption("net.ipv4.tcp_moderate_rcvbuf", 1),
                        Suggestion.newOption("net.ipv4.tcp_window_scaling", 1)};
        List<String> suggestions = new ArrayList<>(suggestionList.length + 1);
        Integer value;
        for (Suggestion suggestion : suggestionList) {
            value = readParam(suggestion.name.replace('.', '/'));
            if (value == null) {
                continue;
            }
            if (suggestion.isOption()) {
                Integer option = suggestion.option;
                if (!option.equals(value)) {
                    suggestions.add(
                            "The kernel parameter \"%s\" is suggested to be \"%d\" but it is \"%d\" actually. You can update it via \"sudo sysctl -w %s=%d\""
                                    .formatted(suggestion.name,
                                            option,
                                            value,
                                            suggestion.name,
                                            suggestion.defaultValue));
                }
            } else if (suggestion.min > value && suggestion.max < value) {
                suggestions.add(
                        "The kernel parameter \"%s\" is suggested to be a value in the range of [%d, %d] but it is \"%d\" actually. You can update it via \"sudo sysctl -w %s=%d\""
                                .formatted(suggestion.name,
                                        suggestion.min,
                                        suggestion.max,
                                        value,
                                        suggestion.name,
                                        suggestion.defaultValue));
            }
        }
        UnixOperatingSystemMXBean bean =
                ManagementFactory.getPlatformMXBean(UnixOperatingSystemMXBean.class);
        long maxFileDescriptorCount = bean.getMaxFileDescriptorCount();
        if (maxFileDescriptorCount < 10_000) {
            suggestions.add(
                    "The max file descriptor count is suggested to be a value in the range of [10000, 1048576] but it is \"%d\" actually"
                            .formatted(maxFileDescriptorCount));
        }
        return suggestions;
    }

    @Nullable
    private static Integer readParam(String name) {
        try {
            Path path = Paths.get(SYS + name);
            if (!Files.exists(path)) {
                return null;
            }
            String line = readLine(path);
            if (line == null) {
                return null;
            }
            return Integer.parseInt(line);
        } catch (Exception ignored) {
            return null;
        }
    }

    @Nullable
    private static String readLine(Path path) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            return reader.readLine();
        }
    }

    private record Suggestion(
            String name,

            Integer min,
            Integer max,
            Integer defaultValue,

            Integer option
    ) {
        static Suggestion newRange(String name, int min, int max, int defaultValue) {
            return new Suggestion(name, min, max, defaultValue, null);
        }

        static Suggestion newOption(String name, int option) {
            return new Suggestion(name, null, null, null, option);
        }

        boolean isOption() {
            return option != null;
        }
    }

}
