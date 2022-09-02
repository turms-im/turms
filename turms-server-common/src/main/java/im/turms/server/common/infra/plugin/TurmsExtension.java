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

package im.turms.server.common.infra.plugin;

import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;

/**
 * @author James Chen
 */
public abstract class TurmsExtension {

    private ApplicationContext context;
    private boolean initialized;
    private boolean started;
    private boolean running;
    private List<Class<? extends ExtensionPoint>> extensionPointClasses;

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

    List<Class<? extends ExtensionPoint>> getExtensionPointClasses() {
        if (extensionPointClasses == null) {
            Class<?>[] interfaces = getClass().getInterfaces();
            List<Class<? extends ExtensionPoint>> classes = new ArrayList<>(interfaces.length);
            for (Class<?> interfaceClass : interfaces) {
                if (ExtensionPoint.class.isAssignableFrom(interfaceClass)
                        && interfaceClass != ExtensionPoint.class) {
                    classes.add((Class<? extends ExtensionPoint>) interfaceClass);
                }
            }
            extensionPointClasses = classes;
        }
        return extensionPointClasses;
    }

    synchronized void start() {
        if (initialized || started) {
            return;
        }
        onStarted();
        initialized = true;
        started = true;
        running = true;
    }

    synchronized void stop() {
        if (!started) {
            return;
        }
        try {
            onStopped();
        } finally {
            running = false;
            started = false;
        }
    }

    synchronized void resume() {
        if (!started || running) {
            return;
        }
        try {
            onResumed();
        } finally {
            running = true;
        }
    }

    synchronized void pause() {
        if (!running) {
            return;
        }
        try {
            onPaused();
        } finally {
            running = false;
        }
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