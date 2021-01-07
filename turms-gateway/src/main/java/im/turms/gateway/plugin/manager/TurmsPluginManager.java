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

package im.turms.gateway.plugin.manager;

import im.turms.gateway.plugin.extension.NotificationHandler;
import im.turms.gateway.plugin.extension.UserAuthenticator;
import im.turms.gateway.plugin.extension.UserLoginActionLogHandler;
import im.turms.gateway.plugin.extension.UserOnlineStatusChangeHandler;
import im.turms.server.common.plugin.base.AbstractTurmsPluginManager;
import im.turms.server.common.plugin.extension.UserLocationLogHandler;
import im.turms.server.common.property.TurmsPropertiesManager;
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

    private List<UserLoginActionLogHandler> userLoginActionLogHandlerList;
    private List<UserLocationLogHandler> userLocationLogHandlerList;
    private List<NotificationHandler> notificationHandlerList;
    private List<UserAuthenticator> userAuthenticatorList;
    private List<UserOnlineStatusChangeHandler> userOnlineStatusChangeHandlerList;

    public TurmsPluginManager(
            ApplicationContext context,
            TurmsPropertiesManager turmsPropertiesManager) {
        super(context, turmsPropertiesManager.getLocalProperties());
    }

    @Override
    protected void initPlugins() {
        userLoginActionLogHandlerList = getAndInitExtensions(UserLoginActionLogHandler.class);
        userLocationLogHandlerList = getAndInitExtensions(UserLocationLogHandler.class);
        notificationHandlerList = getAndInitExtensions(NotificationHandler.class);
        userAuthenticatorList = getAndInitExtensions(UserAuthenticator.class);
        userOnlineStatusChangeHandlerList = getAndInitExtensions(UserOnlineStatusChangeHandler.class);
    }

}