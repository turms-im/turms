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

package unit.im.turms.gateway.plugin.manager;

import im.turms.gateway.plugin.manager.TurmsPluginManager;
import im.turms.server.common.property.TurmsProperties;
import im.turms.server.common.property.TurmsPropertiesManager;
import im.turms.server.common.property.env.common.PluginProperties;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author James Chen
 */
class TurmsPluginManagerTests {

    @Test
    void constructor_shouldInitPlugins_ifPluginEnabled() {
        ApplicationContext context = mock(ApplicationContext.class);
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        TurmsProperties properties = new TurmsProperties();
        PluginProperties plugin = new PluginProperties();
        plugin.setEnabled(true);
        plugin.setDir(".");
        properties.setPlugin(plugin);
        when(propertiesManager.getLocalProperties())
                .thenReturn(properties);
        TurmsPluginManager turmsPluginManager = new TurmsPluginManager(context, propertiesManager);

        assertThat(turmsPluginManager.getUserLoginActionLogHandlerList()).isNotNull();
        assertThat(turmsPluginManager.getUserLocationLogHandlerList()).isNotNull();
        assertThat(turmsPluginManager.getNotificationHandlerList()).isNotNull();
        assertThat(turmsPluginManager.getUserAuthenticatorList()).isNotNull();
        assertThat(turmsPluginManager.getUserOnlineStatusChangeHandlerList()).isNotNull();
    }

    @Test
    void destroy_shouldSucceed() {
        ApplicationContext context = mock(ApplicationContext.class);
        TurmsPropertiesManager propertiesManager = mock(TurmsPropertiesManager.class);
        TurmsProperties properties = new TurmsProperties();
        PluginProperties plugin = new PluginProperties();
        plugin.setEnabled(true);
        plugin.setDir(".");
        properties.setPlugin(plugin);
        when(propertiesManager.getLocalProperties())
                .thenReturn(properties);
        TurmsPluginManager turmsPluginManager = new TurmsPluginManager(context, propertiesManager);

        assertThatCode(turmsPluginManager::destroy)
                .doesNotThrowAnyException();
    }

}
