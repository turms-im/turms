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

package im.turms.turms.plugin.manager;

import im.turms.server.common.plugin.base.ITurmsPluginManager;
import im.turms.server.common.plugin.base.TurmsExtension;
import im.turms.server.common.plugin.extension.UserLocationLogHandler;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.turms.plugin.extension.handler.*;
import im.turms.turms.plugin.extension.service.StorageServiceProvider;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginManager;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.nio.file.Path;
import java.util.List;

/**
 * @author James Chen
 */
@Component
@Data
@Log4j2
public class TurmsPluginManager implements ITurmsPluginManager {

    private final ApplicationContext context;
    private final TurmsProperties turmsProperties;
    private PluginManager pluginManager;

    private List<AdminActionLogHandler> adminActionHandlerList;
    private List<UserActionLogHandler> userActionLogHandlerList;
    private List<UserLocationLogHandler> userLocationLogHandlerList;
    private List<ClientRequestHandler> clientRequestHandlerList;
    private List<ExpiredMessageAutoDeletionNotificationHandler> expiredMessageAutoDeletionNotificationHandlerList;
    private List<NotificationHandler> notificationHandlerList;
    private StorageServiceProvider storageServiceProvider;

    private final boolean enabled;

    public TurmsPluginManager(ApplicationContext context, TurmsPropertiesManager turmsPropertiesManager) throws Exception {
        this.context = context;
        turmsProperties = turmsPropertiesManager.getLocalProperties();
        enabled = turmsProperties.getPlugin().isEnabled();
        if (enabled) {
            initPlugins();
        }
    }

    public void initPlugins() throws Exception {
        Path dir = Path.of(turmsProperties.getPlugin().getDir());
        pluginManager = new DefaultPluginManager(dir);
        pluginManager.loadPlugins();
        pluginManager.startPlugins();

        // According to the method org.pf4j.AbstractPluginManager.getExtensions(java.util.List<org.pf4j.ExtensionWrapper<T>>)
        // getExtensions never return null
        adminActionHandlerList = pluginManager.getExtensions(AdminActionLogHandler.class);
        userActionLogHandlerList = pluginManager.getExtensions(UserActionLogHandler.class);
        userLocationLogHandlerList = pluginManager.getExtensions(UserLocationLogHandler.class);
        clientRequestHandlerList = pluginManager.getExtensions(ClientRequestHandler.class);
        expiredMessageAutoDeletionNotificationHandlerList = pluginManager.getExtensions(ExpiredMessageAutoDeletionNotificationHandler.class);
        notificationHandlerList = pluginManager.getExtensions(NotificationHandler.class);
        List<StorageServiceProvider> storageServiceProviders = pluginManager.getExtensions(StorageServiceProvider.class);
        if (!storageServiceProviders.isEmpty()) {
            this.storageServiceProvider = storageServiceProviders.get(0);
            initExtension(storageServiceProvider);
        }
        initExtensions(clientRequestHandlerList);
        initExtensions(expiredMessageAutoDeletionNotificationHandlerList);
        initExtensions(notificationHandlerList);
    }

    @PreDestroy
    public void destroy() {
        pluginManager.stopPlugins();
    }

    private void initExtension(TurmsExtension extension) throws Exception {
        try {
            extension.setContext(context);
        } catch (Exception e) {
            log.error("Extension {} failed to init", extension.getClass().getName(), e);
            if (turmsProperties.getPlugin().isExitIfExceptionOccursAtStartup()) {
                throw e;
            }
        }
    }

    private void initExtensions(List<? extends TurmsExtension> extensions) throws Exception {
        for (TurmsExtension extension : extensions) {
            initExtension(extension);
        }
    }

}