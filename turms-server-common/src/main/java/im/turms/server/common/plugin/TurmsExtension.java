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

import org.springframework.context.ApplicationContext;

/**
 * @author James Chen
 */
public abstract class TurmsExtension {

    private ApplicationContext context;
    private boolean started;

    protected ApplicationContext getContext() {
        return context;
    }

    void setContext(ApplicationContext context) {
        this.context = context;
    }

    protected <T> T loadProperties(Class<T> propertiesClass) {
        return context.getBean(AbstractTurmsPluginManager.class).loadProperties(propertiesClass);
    }

    void start() {
        if (!started) {
            onStarted();
        }
        started = true;
    }

    void stop() {
        if (started) {
            onStopped();
        }
        started = false;
    }

    protected void onStarted() {
    }

    protected void onStopped() {
    }

}