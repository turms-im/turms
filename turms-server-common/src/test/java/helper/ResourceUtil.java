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

package helper;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author James Chen
 */
public final class ResourceUtil {

    public static final Path TEST_RESOURCES_PATH;
    public static final Path TEST_CLASSES_PATH;

    static {
        Path basePath;
        try {
            basePath = Path.of(ResourceUtil.class.getProtectionDomain()
                    .getCodeSource()
                    .getLocation()
                    .toURI());
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to get the output path", e);
        }
        TEST_RESOURCES_PATH = basePath.resolve("../../src/test/resources");
        TEST_CLASSES_PATH = basePath.resolve("../../target/test-classes");
    }

    private ResourceUtil() {
    }

    public static InputStream findTestResource(String path) {
        Path resourcePath = TEST_RESOURCES_PATH.resolve(path);
        try {
            return Files.newInputStream(resourcePath);
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read from the path: "
                            + resourcePath.toAbsolutePath()
                                    .normalize(),
                    e);
        }
    }

    public static ClassResource findTestClass(String className) {
        className = className.replace('.', '/')
                .concat(".class");
        Path classPath = TEST_CLASSES_PATH.resolve(className);
        try {
            return new ClassResource(className, Files.newInputStream(classPath));
        } catch (IOException e) {
            throw new RuntimeException(
                    "Failed to read from the path: "
                            + classPath.toAbsolutePath()
                                    .normalize(),
                    e);
        }
    }

}