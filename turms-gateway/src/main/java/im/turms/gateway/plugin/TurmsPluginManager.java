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

package im.turms.gateway.plugin;

import im.turms.gateway.plugin.extension.NotificationHandler;
import im.turms.gateway.plugin.extension.UserAuthenticator;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.plugin.AbstractTurmsPluginManager;
import im.turms.server.common.property.TurmsPropertiesManager;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author James Chen
 */
@Component
@Getter
public class TurmsPluginManager extends AbstractTurmsPluginManager {

    private List<NotificationHandler> notificationHandlerList;
    private List<UserAuthenticator> userAuthenticatorList;
    private List<UserOnlineStatusChangeHandler> userOnlineStatusChangeHandlerList;

    public TurmsPluginManager(
            ApplicationContext context,
            TurmsApplicationContext applicationContext,
            TurmsPropertiesManager propertiesManager) {
        super(context, applicationContext, propertiesManager);
    }

    @Override
    protected void afterPluginsInitialized() {
        notificationHandlerList = getAndStartExtensionPoints(NotificationHandler.class);
        userAuthenticatorList = getAndStartExtensionPoints(UserAuthenticator.class);
        userOnlineStatusChangeHandlerList = getAndStartExtensionPoints(UserOnlineStatusChangeHandler.class);
    }

}