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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;

import io.netty.buffer.ByteBuf;

/**
 * @author James Chen
 */
public class FileUtil {

    private static Path tempDir;

    private FileUtil() {
    }

    public static void setTempDir(Path tempDir) {
        FileUtil.tempDir = tempDir;
        try {
            Files.createDirectories(tempDir);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the temp directory: "
                            + tempDir,
                    e);
        }
    }

    public static Path createTempFile(Path dir, String fileName) {
        dir = tempDir.resolve(dir);
        try {
            // Ensure the directory exists because it may be deleted by users unexpectedly
            Files.createDirectories(dir);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the directory: "
                            + dir,
                    e);
        }
        Path filePath = dir.resolve(fileName);
        try {
            return Files.createFile(filePath);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to create the file: "
                            + filePath,
                    e);
        }
    }

    public static long size(Path path) {
        try {
            return Files.size(path);
        } catch (IOException e) {
            throw new InputOutputException(
                    "Failed to get the size of the file: "
                            + path,
                    e);
        }
    }

    public static void write(File file, ByteBuf buffer) {
        try (FileOutputStream outputStream = new FileOutputStream(file);
                FileChannel channel = outputStream.getChannel()) {
            if (buffer.nioBufferCount() == 1) {
                channel.write(buffer.nioBuffer());
            } else {
                for (ByteBuffer buf : buffer.nioBuffers()) {
                    channel.write(buf);
                }
            }
        } catch (IOException e) {
            throw new InputOutputException("Caught an error while closing the file channel", e);
        }
    }

    public static ByteBuffer read(FileChannel channel) {
        long size;
        try {
            size = channel.size();
        } catch (IOException e) {
            throw new InputOutputException("Failed to get the size of the file", e);
        }
        try {
            return channel.map(FileChannel.MapMode.READ_ONLY, 0, size);
        } catch (IOException e) {
            throw new InputOutputException("Failed to map the file", e);
        }
    }

}