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

package im.turms.server.common.infra.plugin;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

import im.turms.server.common.infra.io.InputOutputException;

/**
 * @author James Chen
 */
public class PluginFinder {

    private static final String JAR_EXTENSIONS = ".jar";
    private static final String JAVASCRIPT_EXTENSION = ".js";

    private PluginFinder() {
    }

    public static FindResult find(Path dir, boolean isJsEnabled) {
        List<Path> files = listFiles(dir);
        if (files.isEmpty()) {
            return new FindResult(Collections.emptyList(), Collections.emptyList());
        }
        int size = files.size();
        List<ZipFile> zipFiles = new ArrayList<>(size);
        List<JsFile> jsFiles = isJsEnabled
                ? new ArrayList<>(size)
                : Collections.emptyList();
        for (Path path : files) {
            try {
                Object file = find(isJsEnabled, path);
                if (file != null) {
                    if (file instanceof ZipFile zipFile) {
                        zipFiles.add(zipFile);
                    } else if (file instanceof JsFile jsFile) {
                        jsFiles.add(jsFile);
                    }
                }
            } catch (Exception e) {
                for (ZipFile zipFile : zipFiles) {
                    try {
                        zipFile.close();
                    } catch (IOException ex) {
                        e.addSuppressed(new InputOutputException(
                                "Caught an error while closing the zip file: "
                                        + zipFile.getName(),
                                ex));
                    }
                }
                throw new InputOutputException(
                        "Failed to load the JAR file: "
                                + path.toAbsolutePath()
                                        .normalize(),
                        e);
            }
        }
        return new FindResult(zipFiles, jsFiles);
    }

    public static Object find(boolean isJsEnabled, Path path) {
        String pathStr = path.toString();
        if (pathStr.endsWith(JAR_EXTENSIONS)) {
            try {
                return new ZipFile(path.toFile());
            } catch (Exception e) {
                throw new InputOutputException(
                        "Failed to load the JAR file: "
                                + path.toAbsolutePath()
                                        .normalize(),
                        e);
            }
        } else if (isJsEnabled && pathStr.endsWith(JAVASCRIPT_EXTENSION)) {
            try (FileInputStream stream = new FileInputStream(path.toFile())) {
                String script = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
                return new JsFile(script, path);
            } catch (Exception e) {
                throw new InputOutputException(
                        "Failed to load the JavaScript file: "
                                + path.toAbsolutePath()
                                        .normalize(),
                        e);
            }
        } else {
            return null;
        }
    }

    private static List<Path> listFiles(Path dir) {
        try (Stream<Path> paths = Files.list(dir)) {
            return paths.toList();
        } catch (NoSuchFileException e) {
            return Collections.emptyList();
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to list files in the dir: "
                            + dir,
                    e);
        }
    }

    public record FindResult(
            List<ZipFile> zipFiles,
            List<JsFile> jsFiles
    ) {
    }
}