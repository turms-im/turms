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

package im.turms.service.plugin;

import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.plugin.AbstractTurmsPluginManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.service.plugin.extension.AdminActionHandler;
import im.turms.service.plugin.extension.ClientRequestHandler;
import im.turms.service.plugin.extension.ExpiredMessageAutoDeletionNotificationHandler;
import im.turms.service.plugin.extension.StorageServiceProvider;
import lombok.Getter;
import lombok.extern.log4j.Log4j2;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author James Chen
 */
@Component
@Getter
@Log4j2
public class TurmsPluginManager extends AbstractTurmsPluginManager {

    private List<AdminActionHandler> adminActionHandlerList;
    private List<ClientRequestHandler> clientRequestHandlerList;
    private List<ExpiredMessageAutoDeletionNotificationHandler> expiredMessageAutoDeletionNotificationHandlerList;
    private StorageServiceProvider storageServiceProvider;

    public TurmsPluginManager(ApplicationContext context,
                              TurmsApplicationContext applicationContext,
                              TurmsPropertiesManager propertiesManager) {
        super(context, applicationContext, propertiesManager);
    }

    @Override
    protected void afterPluginsInitialized() {
        adminActionHandlerList = getAndStartExtensionPoints(AdminActionHandler.class);
        clientRequestHandlerList = getAndStartExtensionPoints(ClientRequestHandler.class);
        expiredMessageAutoDeletionNotificationHandlerList = getAndStartExtensionPoints(ExpiredMessageAutoDeletionNotificationHandler.class);
        storageServiceProvider = getAndStartFirstExtensionPoint(StorageServiceProvider.class);
    }

}