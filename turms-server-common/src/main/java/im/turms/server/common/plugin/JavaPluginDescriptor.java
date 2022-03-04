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

package im.turms.server.common.plugin;

import lombok.Data;
import lombok.SneakyThrows;

import java.net.URL;
import java.nio.file.Path;

/**
 * @author James Chen
 */
@Data
public class JavaPluginDescriptor extends PluginDescriptor {
    private final String entryClass;
    private final URL jarUrl;

    @SneakyThrows
    public JavaPluginDescriptor(String id,
                                String version,
                                String provider,
                                String license,
                                String description,
                                String entryClass,
                                String jarPath) {
        super(id, version, provider, license, description, Path.of(jarPath));
        this.entryClass = entryClass;
        this.jarUrl = getPath().toUri().toURL();
    }
}
