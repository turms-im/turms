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

import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.property.TurmsPropertiesManager;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;

import javax.annotation.Nullable;
import javax.annotation.PreDestroy;
import java.nio.file.Path;
import java.util.List;

/**
 * @author James Chen
 */
@Log4j2
public abstract class AbstractTurmsPluginManager {

    @Getter
    private final boolean enabled;
    @Nullable
    private final PluginManager pluginManager;

    protected AbstractTurmsPluginManager(ApplicationContext context,
                                         TurmsApplicationContext applicationContext,
                                         TurmsPropertiesManager propertiesManager) {
        enabled = propertiesManager.getLocalProperties().getPlugin().isEnabled();
        if (!enabled) {
            pluginManager = null;
            return;
        }
        Path home = applicationContext.getHome();
        Path pluginDir = getPluginDir(home);
        pluginManager = new PluginManager(pluginDir, context);
        afterPluginsInitialized();
    }

    private Path getPluginDir(Path home) {
        return home.resolve("plugins").toAbsolutePath();
    }

    @PreDestroy
    public void destroy() {
        if (pluginManager == null) {
            return;
        }
        for (PluginWrapper wrapper : pluginManager.getWrappers()) {
            for (TurmsExtension extension : wrapper.extensions()) {
                try {
                    pluginManager.stopExtension(extension);
                } catch (Exception e) {
                    log.error("Caught an exception when stopping the extension " + extension.getClass().getName(), e);
                }
            }
        }
    }

    protected abstract void afterPluginsInitialized();

    protected <T extends ExtensionPoint> List<T> getAndStartExtensionPoints(Class<T> clazz) {
        List<T> extensionPoints = pluginManager.getExtensionPoints(clazz);
        for (T point : extensionPoints) {
            TurmsExtension extension = (TurmsExtension) point;
            extension.start();
        }
        return extensionPoints;
    }

    @Nullable
    protected <T extends ExtensionPoint> T getAndStartFirstExtensionPoint(Class<T> clazz) {
        List<T> extensions = pluginManager.getExtensionPoints(clazz);
        if (extensions.isEmpty()) {
            return null;
        }
        T extension = extensions.get(0);
        ((TurmsExtension) extension).start();
        return extension;
    }

    public <T> T loadProperties(Class<T> propertiesClass) {
        return pluginManager.loadProperties(propertiesClass);
    }

}