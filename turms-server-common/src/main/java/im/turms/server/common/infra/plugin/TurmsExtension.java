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

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationContext;

import im.turms.server.common.access.admin.web.HttpRequestDispatcher;
import im.turms.server.common.infra.lang.ClassUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.property.TurmsPropertiesManager;

/**
 * @author James Chen
 */
public abstract class TurmsExtension {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsExtension.class);

    @Setter(AccessLevel.PACKAGE)
    @Getter
    private ApplicationContext context;
    @Setter(AccessLevel.PACKAGE)
    @Getter(AccessLevel.PACKAGE)
    private Plugin plugin;
    private boolean initialized;
    private boolean started;
    private boolean running;
    private List<Class<? extends ExtensionPoint>> extensionPointClasses;

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
        return context.getBean(TurmsPropertiesManager.class)
                .loadProperties(propertiesClass);
    }

    protected void registerController(Object controller) {
        registerControllers(List.of(controller));
    }

    protected void registerControllers(List<Object> controllers) {
        context.getBean(HttpRequestDispatcher.class)
                .registerControllers(controllers);
    }

    ExtensionPoint getExtensionPoint() {
        return (ExtensionPoint) this;
    }

    List<Class<? extends ExtensionPoint>> getExtensionPointClasses() {
        if (extensionPointClasses == null) {
            Set<Class<? extends ExtensionPoint>> classes = (Set) ClassUtil.getInterfaces(getClass(),
                    interfaceClass -> ClassUtil.isSuperClass(interfaceClass, ExtensionPoint.class));
            extensionPointClasses = new ArrayList<>(classes);
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
        LOGGER.info("The extension ({}) of the plugin ({}) has been started",
                getClass().getName(),
                plugin.descriptor()
                        .getId());
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
            LOGGER.info("The extension ({}) of the plugin ({}) has been stopped",
                    getClass().getName(),
                    plugin.descriptor()
                            .getId());
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
            LOGGER.info("The extension ({}) of the plugin ({}) has been resumed",
                    getClass().getName(),
                    plugin.descriptor()
                            .getId());
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
            LOGGER.info("The extension ({}) of the plugin ({}) has been paused",
                    getClass().getName(),
                    plugin.descriptor()
                            .getId());
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