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

package im.turms.server.common.plugin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
public class JarFileFinder {

    public List<ZipFile> find(Path dir) {
        List<Path> files = listFiles(dir);
        if (files.isEmpty()) {
            return Collections.emptyList();
        }
        List<ZipFile> zipFiles = new ArrayList<>(files.size());
        for (Path path : files) {
            if (!path.toString().endsWith(".jar")) {
                continue;
            }
            ZipFile file;
            try {
                file = new ZipFile(path.toFile());
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load the jar file: " + path.toAbsolutePath(), e);
            }
            zipFiles.add(file);
        }
        return zipFiles;
    }

    private List<Path> listFiles(Path dir) {
        try (Stream<Path> paths = Files.list(dir)) {
            return paths.toList();
        } catch (NoSuchFileException e) {
            return Collections.emptyList();
        } catch (IOException e) {
            throw new IllegalStateException("Failed to list files in the dir " + dir, e);
        }
    }

}