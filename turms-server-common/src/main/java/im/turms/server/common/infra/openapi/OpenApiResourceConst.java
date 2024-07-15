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

package im.turms.server.common.infra.openapi;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.util.Enumeration;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.GZIPOutputStream;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufOutputStream;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.Unpooled;

import im.turms.server.common.infra.collection.CollectionUtil;
import im.turms.server.common.infra.exception.DuplicateResourceException;
import im.turms.server.common.infra.io.ResourceNotFoundException;

/**
 * @author James Chen
 */
public final class OpenApiResourceConst {

    public static final ByteBuf FAVICON_32x32;
    public static final ByteBuf INDEX_CSS;
    public static final ByteBuf SWAGGER_UI_CSS;
    public static final ByteBuf SWAGGER_UI_BUNDLE;
    public static final ByteBuf SWAGGER_UI_STANDALONE_PRESET;

    private static final String RESOURCE_PATH = "META-INF/resources/webjars/swagger-ui/";

    private static final String RESOURCE_FAVICON_32x32 = "favicon-32x32.png";
    private static final String RESOURCE_INDEX_CSS = "index.css";
    private static final String RESOURCE_SWAGGER_UI_CSS = "swagger-ui.css";
    private static final String RESOURCE_SWAGGER_UI_BUNDLE = "swagger-ui-bundle.js";
    private static final String RESOURCE_SWAGGER_UI_STANDALONE_PRESET =
            "swagger-ui-standalone-preset.js";

    private static final int RESOURCE_COUNT_TO_FIND = 5;

    private static final int GZIP_COMPRESSION_LEVEL = 6;

    static {
        Map<String, ByteBuf> nameToBuffer =
                CollectionUtil.newMapWithExpectedSize(RESOURCE_COUNT_TO_FIND);
        nameToBuffer.put(RESOURCE_FAVICON_32x32, null);
        nameToBuffer.put(RESOURCE_INDEX_CSS, null);
        nameToBuffer.put(RESOURCE_SWAGGER_UI_CSS, null);
        nameToBuffer.put(RESOURCE_SWAGGER_UI_BUNDLE, null);
        nameToBuffer.put(RESOURCE_SWAGGER_UI_STANDALONE_PRESET, null);

        URL folderUrl = OpenApiResourceConst.class.getClassLoader()
                .getResource(RESOURCE_PATH);
        if (folderUrl == null) {
            throw new ResourceNotFoundException(
                    "Missing OpenAPI resources: "
                            + RESOURCE_PATH);
        }

        int foundResourceCount = 0;
        try {
            JarURLConnection urlConnection = (JarURLConnection) folderUrl.openConnection();
            JarFile jarFile = urlConnection.getJarFile();
            Enumeration<JarEntry> entries = jarFile.entries();
            while (entries.hasMoreElements()) {
                JarEntry entry = entries.nextElement();
                foundResourceCount += findResources(entry, nameToBuffer, jarFile);
            }
        } catch (Exception e) {
            for (ByteBuf buffer : nameToBuffer.values()) {
                if (buffer != null) {
                    buffer.release();
                }
            }
            throw new RuntimeException("Failed to load OpenAPI resources", e);
        }
        if (foundResourceCount < RESOURCE_COUNT_TO_FIND) {
            Set<String> missingResourceNames = new TreeSet<>();
            for (Map.Entry<String, ByteBuf> entry : nameToBuffer.entrySet()) {
                if (entry.getValue() == null) {
                    missingResourceNames.add(entry.getKey());
                }
            }
            throw new ResourceNotFoundException(
                    "Missing OpenAPI resources: "
                            + missingResourceNames);
        }
        FAVICON_32x32 = nameToBuffer.get(RESOURCE_FAVICON_32x32);
        INDEX_CSS = nameToBuffer.get(RESOURCE_INDEX_CSS);
        SWAGGER_UI_CSS = nameToBuffer.get(RESOURCE_SWAGGER_UI_CSS);
        SWAGGER_UI_BUNDLE = nameToBuffer.get(RESOURCE_SWAGGER_UI_BUNDLE);
        SWAGGER_UI_STANDALONE_PRESET = nameToBuffer.get(RESOURCE_SWAGGER_UI_STANDALONE_PRESET);
    }

    private static int findResources(
            JarEntry entry,
            Map<String, ByteBuf> nameToBuffer,
            JarFile jarFile) throws IOException {
        int foundResourceCount = 0;
        String name = entry.getName();
        for (Map.Entry<String, ByteBuf> nameAndBuffer : nameToBuffer.entrySet()) {
            String resourceNameToFound = nameAndBuffer.getKey();
            if (!name.endsWith("/"
                    + resourceNameToFound)) {
                continue;
            }
            if (nameAndBuffer.getValue() != null) {
                throw new DuplicateResourceException(
                        "Found a duplicate resource: "
                                + resourceNameToFound);
            }
            ByteBuf buffer = null;
            try {
                if (name.endsWith(".js")) {
                    byte[] entryBytes = jarFile.getInputStream(entry)
                            .readAllBytes();
                    ByteBuf tempBuffer =
                            PooledByteBufAllocator.DEFAULT.heapBuffer(entryBytes.length << 1);
                    try {
                        try (ByteBufOutputStream byteBufOutputStream =
                                new ByteBufOutputStream(tempBuffer, false);
                                GZIPOutputStream gzipOutputStream = new GZIPOutputStream(
                                        byteBufOutputStream,
                                        GZIP_COMPRESSION_LEVEL) {
                                    {
                                        def.setLevel(GZIP_COMPRESSION_LEVEL);
                                    }
                                }) {
                            gzipOutputStream.write(entryBytes);
                        }
                        buffer = Unpooled.directBuffer(tempBuffer.readableBytes())
                                .writeBytes(tempBuffer);
                    } finally {
                        tempBuffer.release();
                    }
                } else {
                    buffer = Unpooled.directBuffer((int) entry.getSize());
                    jarFile.getInputStream(entry)
                            .transferTo(new ByteBufOutputStream(buffer));
                }
                nameToBuffer.put(resourceNameToFound, Unpooled.unreleasableBuffer(buffer));
            } catch (Exception e) {
                if (buffer != null) {
                    buffer.release();
                }
                throw e;
            }
            foundResourceCount++;
        }
        return foundResourceCount;
    }

    private OpenApiResourceConst() {
    }

}