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

package unit.im.turms.server.common.infra.logging.core.compression;

import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.zip.GZIPOutputStream;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import im.turms.server.common.infra.logging.core.compression.FastGzipOutputStream;

import static org.assertj.core.api.Assertions.assertThat;

import static im.turms.server.common.infra.unit.ByteSizeUnit.KB;

/**
 * @author James Chen
 */
class FastGzipOutputStreamTests {

    @Test
    void test() {
        Path outputPath = Path.of("test.txt.gz");
        try {
            test0(outputPath);
        } finally {
            try {
                Files.deleteIfExists(outputPath);
            } catch (Exception ignored) {
            }
        }
    }

    @SneakyThrows
    void test0(Path outputPath) {
        int compressionLevel = 6;
        byte[] bytes = """
                abcdefg123456
                abcdefg123456
                abcdefg123456
                """.getBytes();

        FastGzipOutputStream stream = new FastGzipOutputStream(compressionLevel, KB);
        stream.init(FileChannel.open(outputPath,
                StandardOpenOption.CREATE,
                StandardOpenOption.WRITE,
                StandardOpenOption.TRUNCATE_EXISTING));
        try (stream) {
            stream.write(ByteBuffer.allocateDirect(bytes.length)
                    .put(bytes)
                    .flip());
        }
        byte[] actualBytes = Files.readAllBytes(outputPath);
        byte[] expectedBytes = getExpectedBytes(bytes, compressionLevel);
        assertThat(actualBytes).isEqualTo(expectedBytes);

        bytes = """
                123456789
                123456789
                """.getBytes();
        try {
            stream.init(FileChannel.open(outputPath,
                    StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING));
            stream.write(ByteBuffer.allocateDirect(bytes.length)
                    .put(bytes)
                    .flip());
        } finally {
            stream.close();
        }
        actualBytes = Files.readAllBytes(outputPath);
        expectedBytes = getExpectedBytes(bytes, compressionLevel);
        assertThat(actualBytes).isEqualTo(expectedBytes);
    }

    @SneakyThrows
    private byte[] getExpectedBytes(byte[] input, int compressionLevel) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream stream = new GZIPOutputStream(out) {
            {
                def.setLevel(compressionLevel);
            }
        }) {
            stream.write(input);
            stream.finish();
            return out.toByteArray();
        }
    }

}
