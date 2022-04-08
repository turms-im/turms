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

import org.springframework.core.io.FileSystemResource;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.channels.ReadableByteChannel;

/**
 * @author James Chen
 */
public class FileResource extends FileSystemResource {

    @Nullable
    private final Runnable cleanup;

    public FileResource(File file, @Nullable Runnable cleanup) {
        super(file);
        this.cleanup = cleanup;
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        throw new UnsupportedEncodingException("TemporaryFileResource does not support ReadableByteChannel");
    }

    @Override
    public InputStream getInputStream() throws IOException {
        throw new UnsupportedEncodingException("TemporaryFileResource does not support InputStream");
    }

    public void cleanup() {
        if (cleanup != null) {
            cleanup.run();
        }
    }

    @Override
    public boolean isFile() {
        return true;
    }

}