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

package im.turms.server.common.infra.archive;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
public final class ZipUtils {

    private static final byte[] BYTES_EMPTY = new byte[0];

    private ZipUtils() {
    }

    public static byte[] readEntry(ZipFile zipFile, ZipEntry entry) throws IOException {
        long size = entry.getSize();
        if (size == 0) {
            return BYTES_EMPTY;
        }
        if (size < 0) {
            try (InputStream inputStream = zipFile.getInputStream(entry)) {
                return inputStream.readAllBytes();
            }
        }
        if (size > Integer.MAX_VALUE) {
            throw new IOException(
                    "The size of the entry must be less than or equal to "
                            + Integer.MAX_VALUE
                            + ", but got:"
                            + size);
        }
        int sizeInt = (int) size;
        byte[] bytes = new byte[sizeInt];
        try (InputStream inputStream = zipFile.getInputStream(entry)) {
            int bytesRead = inputStream.readNBytes(bytes, 0, sizeInt);
            if (bytesRead != sizeInt) {
                throw new IOException(
                        "The size of the entry must be "
                                + sizeInt
                                + ", but got: "
                                + bytesRead);
            }
        }
        return bytes;
    }
}