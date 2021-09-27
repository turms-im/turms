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

import org.springframework.util.StringUtils;

import javax.annotation.Nullable;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
public class PluginDescriptorLoader {

    private static final String PROPERTIES_FILE_NAME = "plugin.properties";

    private static final String PLUGIN_ID = "plugin.id";
    private static final String PLUGIN_CLASS = "plugin.class";
    private static final String PLUGIN_VERSION = "plugin.version";
    private static final String PLUGIN_PROVIDER = "plugin.provider";
    private static final String PLUGIN_LICENSE = "plugin.license";
    private static final String PLUGIN_DESCRIPTION = "plugin.description";

    public List<PluginDescriptor> load(List<ZipFile> files) {
        if (files.isEmpty()) {
            return Collections.emptyList();
        }
        List<PluginDescriptor> descriptors = new ArrayList<>(files.size());
        for (ZipFile file : files) {
            String name = file.getName();
            try (file) {
                PluginDescriptor pluginDescriptor;
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
    private PluginDescriptor tryCreatePluginDescriptor(ZipFile file, URL jarUrl) {
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
                return createPluginDescriptor(properties, jarUrl);
            } catch (Exception e) {
                throw new IllegalStateException("Cannot parse the jar " + jarUrl.toString(), e);
            }
        }
        return null;
    }

    private PluginDescriptor createPluginDescriptor(Properties properties, URL jarUrl) {
        String id = readPropertiesString(properties, PLUGIN_ID, true);
        String clazz = readPropertiesString(properties, PLUGIN_CLASS, true);
        String version = readPropertiesString(properties, PLUGIN_VERSION, false);
        String provider = readPropertiesString(properties, PLUGIN_PROVIDER, false);
        String license = readPropertiesString(properties, PLUGIN_LICENSE, false);
        String description = readPropertiesString(properties, PLUGIN_DESCRIPTION, false);
        return new PluginDescriptor(id, clazz, version, provider, license, description, jarUrl);
    }

    @Nullable
    private String readPropertiesString(Properties properties, String key, boolean required) {
        String value = properties.getProperty(key);
        if (StringUtils.hasText(value)) {
            return value;
        }
        if (required) {
            throw new IllegalArgumentException("Field \"%s\" cannot be blank".formatted(key));
        }
        return null;
    }

}