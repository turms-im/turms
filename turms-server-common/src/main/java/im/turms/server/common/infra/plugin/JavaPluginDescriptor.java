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

package im.turms.server.common.infra.plugin;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Path;

import lombok.EqualsAndHashCode;
import lombok.Value;

/**
 * @author James Chen
 */
@EqualsAndHashCode(callSuper = true)
@Value
public class JavaPluginDescriptor extends PluginDescriptor {
    String entryClass;
    URL jarUrl;

    public JavaPluginDescriptor(
            String id,
            String version,
            String provider,
            String license,
            String description,
            String entryClass,
            String jarPath) {
        super(id, version, provider, license, description, Path.of(jarPath));
        this.entryClass = entryClass;
        URI uri = getPath().toUri();
        try {
            jarUrl = uri.toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(
                    "Malformed URL: "
                            + uri,
                    e);
        }
    }
}