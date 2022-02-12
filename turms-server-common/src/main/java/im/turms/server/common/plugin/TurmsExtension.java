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
    private boolean running;

    protected ApplicationContext getContext() {
        return context;
    }

    void setContext(ApplicationContext context) {
        this.context = context;
    }

    public boolean isStarted() {
        return started;
    }

    void setStarted(boolean started) {
        this.started = started;
    }

    public boolean isRunning() {
        return running;
    }

    void setRunning(boolean running) {
        this.running = running;
    }

    protected <T> T loadProperties(Class<T> propertiesClass) {
        return context.getBean(PluginManager.class).loadProperties(propertiesClass);
    }

    void start() {
        if (!started) {
            onStarted();
        }
        started = true;
        running = true;
    }

    void stop() {
        if (started) {
            onStopped();
        }
        running = false;
        started = false;
    }

    void resume() {
        if (!started) {
            return;
        }
        if (!running) {
            onResumed();
        }
        running = true;
    }

    void pause() {
        if (running) {
            onPaused();
        }
        running = false;
    }

    protected void onStarted() {
    }

    protected void onStopped() {
    }

    protected void onResumed() {
    }

    protected void onPaused() {
    }

}