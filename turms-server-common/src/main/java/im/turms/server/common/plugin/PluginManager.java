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

import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContext;

import java.nio.file.Path;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipFile;

/**
 * @author James Chen
 */
@Log4j2
public class PluginManager {

    private final PluginRepository pluginRepository = new PluginRepository();
    private final ApplicationContext context;

    @SneakyThrows
    public PluginManager(Path pluginHome, ApplicationContext context) {
        this.context = context;
        JarFileFinder jarFileFinder = new JarFileFinder();
        List<ZipFile> zipFiles = jarFileFinder.find(pluginHome);
        PluginDescriptorLoader pluginDescriptorLoader = new PluginDescriptorLoader();
        List<PluginDescriptor> descriptors = pluginDescriptorLoader.load(zipFiles);
        for (PluginDescriptor descriptor : descriptors) {
            PluginFactory pluginFactory = new PluginFactory();
            PluginWrapper wrapper = pluginFactory.register(descriptor, context);
            pluginRepository.register(wrapper);
        }
    }

    public <T extends ExtensionPoint> List<T> getExtensionPoints(Class<T> clazz) {
        return pluginRepository.getExtensionPoints(clazz);
    }

    public Collection<PluginWrapper> getWrappers() {
        return pluginRepository.getWrappers();
    }

    public void stopExtension(TurmsExtension extension) {
        extension.stop();
    }

    @SneakyThrows
    public <T> T loadProperties(Class<T> propertiesClass) {
        T properties = propertiesClass.getDeclaredConstructor().newInstance();
        String s = propertiesClass.getName() + UUID.randomUUID();
        return (T) context.getBean(ConfigurationPropertiesBindingPostProcessor.class)
                .postProcessBeforeInitialization(properties, s);
    }

}