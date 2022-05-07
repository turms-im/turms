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

package im.turms.server.common.infra.io;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.SneakyThrows;
import org.springframework.util.AntPathMatcher;
import org.webjars.WebJarAssetLocator;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * @author James Chen
 */
public class FileUtil {

    private static final Set<String> WEB_JAR_ASSETS = new WebJarAssetLocator().listAssets();
    private static final AntPathMatcher ANT_PATH_MATCHER = new AntPathMatcher();

    private FileUtil() {
    }

    @SneakyThrows
    public static File createTempFile(String prefix, String suffix, File directory) {
        directory.mkdirs();
        File tempFile = File.createTempFile(prefix, suffix, directory);
        tempFile.deleteOnExit();
        return tempFile;
    }

    public static ByteBuf getWebJarAssetAsBuffer(String resourceNamePattern) {
        byte[] bytes = getWebJarAssetAsBytes(resourceNamePattern);
        return Unpooled.unreleasableBuffer(Unpooled
                .directBuffer(bytes.length).writeBytes(bytes));
    }

    @SneakyThrows
    public static byte[] getWebJarAssetAsBytes(String resourceNamePattern) {
        return getWebJarAsset(resourceNamePattern)
                .readAllBytes();
    }

    public static InputStream getWebJarAsset(String resourceNamePattern) {
        resourceNamePattern = WebJarAssetLocator.WEBJARS_PATH_PREFIX + "/" + resourceNamePattern;
        String resourcePath = null;
        for (String asset : WEB_JAR_ASSETS) {
            if (ANT_PATH_MATCHER.match(resourceNamePattern, asset)) {
                resourcePath = asset;
            }
        }
        if (resourcePath == null) {
            throw new IllegalArgumentException("Resource not found: " + resourceNamePattern);
        }
        InputStream inputStream = FileUtil.class.getClassLoader().getResourceAsStream(resourcePath);
        if (inputStream == null) {
            throw new IllegalStateException("Cannot find the resource from the path: " + resourcePath);
        }
        return inputStream;
    }

    public static long size(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new IllegalStateException("Failed to get the size of the file: " + path, e);
        }
    }

}
