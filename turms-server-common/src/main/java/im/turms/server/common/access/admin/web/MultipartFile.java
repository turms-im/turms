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

package im.turms.server.common.access.admin.web;

import java.io.File;

import io.netty.handler.codec.http.multipart.HttpData;

/**
 * @param name     e.g. "file.123.jar"
 * @param basename e.g. "file.123"
 * @author James Chen
 */
public record MultipartFile(
        HttpData data,
        String name,
        String basename,
        File file
) {

    public void retain() {
        data.retain();
    }

    public void release() {
        data.release();
    }

    @Override
    public String toString() {
        return "MultipartFile{"
                + "name='"
                + name
                + '\''
                + "basename='"
                + basename
                + '\''
                + '}';
    }

}
