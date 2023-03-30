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

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import jakarta.annotation.Nullable;

import im.turms.server.common.infra.io.InputOutputException;
import im.turms.server.common.infra.yaml.YamlUtil;

/**
 * @author James Chen
 */
public class JavaPluginDescriptorFactory extends PluginDescriptorFactory {

    static final String PLUGIN_CLASS = "class";

    private static final String PROPERTIES_FILE_PREFIX = "plugin.";

    private JavaPluginDescriptorFactory() {
    }

    public static List<JavaPluginDescriptor> load(List<ZipFile> files) {
        if (files.isEmpty()) {
            return Collections.emptyList();
        }
        List<JavaPluginDescriptor> descriptors = new ArrayList<>(files.size());
        for (ZipFile file : files) {
            JavaPluginDescriptor pluginDescriptor = load(file);
            if (pluginDescriptor != null) {
                descriptors.add(pluginDescriptor);
            }
        }
        return descriptors;
    }

    @Nullable
    public static JavaPluginDescriptor load(ZipFile file) {
        String name = file.getName();
        try (file) {
            try {
                return tryCreatePluginDescriptor(file, name);
            } catch (Exception e) {
                throw new MalformedPluginArchiveException(
                        "Failed to create plugin descriptor for the JAR file: "
                                + name,
                        e);
            }
        } catch (IOException e) {
            throw new InputOutputException(
                    "Caught an error while closing the JAR file: "
                            + name,
                    e);
        }
    }

    @Nullable
    private static JavaPluginDescriptor tryCreatePluginDescriptor(ZipFile file, String filePath) {
        Enumeration<? extends ZipEntry> entries = file.entries();
        while (entries.hasMoreElements()) {
            ZipEntry zipEntry = entries.nextElement();
            String descriptorFileName = zipEntry.getName();
            if (!descriptorFileName.startsWith(PROPERTIES_FILE_PREFIX)
                    || (!descriptorFileName.endsWith(".yaml")
                            && !descriptorFileName.endsWith(".yml"))) {
                continue;
            }
            Map<String, String> properties;
            try (InputStream stream = file.getInputStream(zipEntry)) {
                properties = YamlUtil.readValue(stream, HashMap.class);
            } catch (IOException e) {
                String message = "Failed to read the plugin descriptor file ("
                        + descriptorFileName
                        + ") from the JAR file: "
                        + filePath;
                throw new MalformedPluginArchiveException(message, e);
            }
            try {
                String entryClass = readPropertiesString(properties, PLUGIN_CLASS, true);
                PluginDescriptor pluginDescriptor = createPluginDescriptor(properties);
                return new JavaPluginDescriptor(
                        pluginDescriptor.getId(),
                        pluginDescriptor.getVersion(),
                        pluginDescriptor.getProvider(),
                        pluginDescriptor.getLicense(),
                        pluginDescriptor.getDescription(),
                        entryClass,
                        filePath);
            } catch (Exception e) {
                throw new MalformedPluginArchiveException(
                        "Failed to parse the JAR file: "
                                + filePath,
                        e);
            }
        }
        return null;
    }

}