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

package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;
import java.util.zip.ZipEntry;

import lombok.SneakyThrows;

import im.turms.server.common.infra.io.ResourceNotFoundException;

/**
 * @author James Chen
 */
public final class JarUtil {

    private JarUtil() {
    }

    @SneakyThrows
    public static Path createJarFile(
            String outputFile,
            List<Class<?>> classEntries,
            List<String> resources) {
        ClassLoader loader = JarUtil.class.getClassLoader();
        URI jarFileUri = loader.getResource(".")
                .toURI()
                .resolve(outputFile);
        File jarFile = Paths.get(jarFileUri)
                .toFile();
        Manifest manifest = new Manifest();
        manifest.getMainAttributes()
                .put(Attributes.Name.MANIFEST_VERSION, "1.0");
        try (FileOutputStream fos = new FileOutputStream(jarFile, false);
                JarOutputStream output = new JarOutputStream(fos, manifest)) {
            for (Class<?> classEntry : classEntries) {
                String classFileName = classEntry.getName()
                        .replace('.', '/')
                        + ".class";
                addZipEntry(loader, classFileName, output);
            }
            for (String resource : resources) {
                addZipEntry(loader, resource, output);
            }
        }
        return Path.of(jarFile.toURI());
    }

    private static void addZipEntry(ClassLoader loader, String resource, JarOutputStream output)
            throws IOException {
        InputStream source = loader.getResourceAsStream(resource);
        if (source == null) {
            throw new ResourceNotFoundException(
                    "Could not find the resource: "
                            + resource);
        }
        ZipEntry entry = new ZipEntry(resource);
        output.putNextEntry(entry);
        source.transferTo(output);
        output.closeEntry();
        source.close();
    }

}
