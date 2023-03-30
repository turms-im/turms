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

import java.nio.file.Path;
import java.util.function.Consumer;
import jakarta.annotation.Nullable;

import lombok.Getter;

/**
 * @author James Chen
 */
public class FileResource extends BaseFileResource {

    @Getter
    private final Path file;
    @Nullable
    private final Consumer<Throwable> cleanup;

    public FileResource(String name, Path file) {
        super(name, FileUtil.size(file));
        this.file = file;
        this.cleanup = null;
    }

    public FileResource(String name, Path file, @Nullable Consumer<Throwable> cleanup) {
        super(name, FileUtil.size(file));
        this.file = file;
        this.cleanup = cleanup;
    }

    public void cleanup(@Nullable Throwable throwable) {
        if (cleanup != null) {
            cleanup.accept(throwable);
        }
    }

}