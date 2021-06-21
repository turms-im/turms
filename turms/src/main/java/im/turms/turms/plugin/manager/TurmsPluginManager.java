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

import im.turms.server.common.plugin.base.AbstractTurmsPluginManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.turms.plugin.extension.handler.AdminActionHandler;
import im.turms.turms.plugin.extension.handler.ClientRequestHandler;
import im.turms.turms.plugin.extension.handler.ExpiredMessageAutoDeletionNotificationHandler;
import im.turms.turms.plugin.extension.service.StorageServiceProvider;
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

    public TurmsPluginManager(ApplicationContext context, TurmsPropertiesManager turmsPropertiesManager) {
        super(context, turmsPropertiesManager.getLocalProperties());
    }

    @Override
    protected void initPlugins() {
        adminActionHandlerList = getAndInitExtensions(AdminActionHandler.class);
        clientRequestHandlerList = getAndInitExtensions(ClientRequestHandler.class);
        expiredMessageAutoDeletionNotificationHandlerList = getAndInitExtensions(ExpiredMessageAutoDeletionNotificationHandler.class);
        storageServiceProvider = getAndInitExtension(StorageServiceProvider.class);
    }

}