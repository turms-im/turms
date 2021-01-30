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

package im.turms.server.common.plugin.base;

import im.turms.server.common.plugin.extension.UserLocationLogHandler;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.env.common.PluginProperties;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.springframework.context.ApplicationContext;

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
    private final ApplicationContext context;
    private final TurmsProperties turmsProperties;
    private final PluginManager pluginManager;

    protected AbstractTurmsPluginManager(ApplicationContext context, TurmsProperties localProperties) {
        this.context = context;
        turmsProperties = localProperties;
        PluginProperties pluginProperties = turmsProperties.getPlugin();
        enabled = pluginProperties.isEnabled();
        if (enabled) {
            Path dir = Path.of(pluginProperties.getDir());
            pluginManager = new DefaultPluginManager(dir);
            pluginManager.loadPlugins();
            pluginManager.startPlugins();
            initPlugins();
        } else {
            pluginManager = null;
        }
    }

    @PreDestroy
    public void destroy() {
        if (pluginManager != null) {
            pluginManager.stopPlugins();
        }
    }

    protected abstract void initPlugins();

    public abstract List<UserLocationLogHandler> getUserLocationLogHandlerList();

    protected <T extends TurmsExtension> T getAndInitExtension(Class<T> clazz) {
        List<T> extensions = pluginManager.getExtensions(clazz);
        if (extensions != null && !extensions.isEmpty()) {
            T extension = extensions.get(0);
            initExtension(extension);
            return extension;
        }
        return null;
    }

    protected <T extends TurmsExtension> List<T> getAndInitExtensions(Class<T> clazz) {
        List<T> extensions = pluginManager.getExtensions(clazz);
        initExtensions(extensions);
        return extensions;
    }

    private void initExtension(TurmsExtension extension) {
        try {
            extension.setContext(context);
        } catch (Exception e) {
            log.error("Extension {} failed to init", extension.getClass().getName(), e);
            if (turmsProperties.getPlugin().isExitIfExceptionOccursAtStartup()) {
                throw e;
            }
        }
    }

    private void initExtensions(List<? extends TurmsExtension> extensions) {
        for (TurmsExtension extension : extensions) {
            initExtension(extension);
        }
    }

}