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

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
public class JavaPluginDescriptorFactory extends PluginDescriptorFactory {

    static final String PLUGIN_CLASS = "class";

    private static final String PROPERTIES_FILE_NAME = "plugin.properties";

    private JavaPluginDescriptorFactory() {
    }

    public static List<JavaPluginDescriptor> load(List<ZipFile> files) {
        if (files.isEmpty()) {
            return Collections.emptyList();
        }
        List<JavaPluginDescriptor> descriptors = new ArrayList<>(files.size());
        for (ZipFile file : files) {
            String name = file.getName();
            try (file) {
                JavaPluginDescriptor pluginDescriptor;
                try {
                    pluginDescriptor = tryCreatePluginDescriptor(file, Path.of(name).toUri().toURL());
                    if (pluginDescriptor != null) {
                        descriptors.add(pluginDescriptor);
                    }
                } catch (Exception e) {
                    throw new IllegalStateException("Failed to create plugin descriptor for the jar file: " + name, e);
                }
            } catch (IOException e) {
                throw new IllegalStateException("Failed to load the jar file: " + name, e);
            }
        }
        return descriptors;
    }

    @Nullable
    private static JavaPluginDescriptor tryCreatePluginDescriptor(ZipFile file, URL jarUrl) {
        Enumeration<? extends ZipEntry> entries = file.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            String name = zipEntry.getName();
            if (!PROPERTIES_FILE_NAME.equals(name)) {
                continue;
            }
            Properties properties;
            try (InputStream stream = file.getInputStream(zipEntry)) {
                properties = new Properties();
                properties.load(stream);
            } catch (IOException e) {
                throw new IllegalStateException("Failed to read \"%s\" into properties for the jar %s"
                        .formatted(PROPERTIES_FILE_NAME, jarUrl.toString()), e);
            }
            try {
                Map map = properties;
                String clazz = readPropertiesString(map, PLUGIN_CLASS, true);
                PluginDescriptor pluginDescriptor = createPluginDescriptor(map);
                return new JavaPluginDescriptor(pluginDescriptor.getId(),
                        pluginDescriptor.getVersion(),
                        pluginDescriptor.getProvider(),
                        pluginDescriptor.getLicense(),
                        pluginDescriptor.getDescription(),
                        clazz,
                        jarUrl);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot parse the jar " + jarUrl.toString(), e);
            }
        }
        return null;
    }

}