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

import lombok.Data;
import lombok.experimental.Accessors;

import im.turms.server.common.infra.exception.ThrowableUtil;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * {@link TurmsPlugin} is the plugin class for plugin developers, while {@link Plugin} is the plugin
 * class for us and internal development.
 * <p>
 * From the perspective of Turms developers, it is more accurate to call {@link TurmsPlugin}
 * "CustomPlugin" or "JavaPlugin", but from the perspective of Java plugin developers, both
 * "JavaPlugin" and "CustomPlugin" are fluffy because it is obvious that they are developing a java
 * custom plugin while "TurmsPlugin" can ensure developers know that they are developing a plugin
 * for Turms to distinguish with other plugin classes in their classpath.
 *
 * @author James Chen
 */
@Accessors(fluent = true)
@Data
public abstract sealed class Plugin permits JavaPlugin, JsPlugin {

    private static final Logger LOGGER = LoggerFactory.getLogger(Plugin.class);

    private final PluginDescriptor descriptor;
    private final List<TurmsExtension> extensions;

    void start() {
        List<Runnable> runnables = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            runnables.add(() -> {
                try {
                    extension.start();
                } catch (Exception | LinkageError e) {
                    throw new RuntimeException(
                            "Caught an error while starting the extension: "
                                    + extension.getClass()
                                            .getName(),
                            e);
                }
            });
        }
        ThrowableUtil.delayError(runnables,
                "Caught errors while starting extensions of the plugin: "
                        + descriptor.getId());
        LOGGER.info("The plugin ({}) has been started", descriptor.getId());
    }

    void stop() {
        List<Runnable> runnables = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            runnables.add(() -> {
                try {
                    extension.stop();
                } catch (Exception | LinkageError e) {
                    throw new RuntimeException(
                            "Caught an error while stopping the extension: "
                                    + extension.getClass()
                                            .getName(),
                            e);
                }
            });
        }
        RuntimeException stopExtensionsException = null;
        RuntimeException closeContextException = null;
        try {
            ThrowableUtil.delayError(runnables, "Caught errors while stopping extensions");
        } catch (RuntimeException e) {
            stopExtensionsException = e;
        }
        try {
            closeContext();
        } catch (RuntimeException e) {
            closeContextException = e;
        }
        if (stopExtensionsException != null || closeContextException != null) {
            RuntimeException e = new RuntimeException(
                    "Caught errors while stopping the plugin: "
                            + descriptor.getId());
            if (stopExtensionsException != null) {
                e.addSuppressed(stopExtensionsException);
            }
            if (closeContextException != null) {
                e.addSuppressed(closeContextException);
            }
            throw e;
        }
        LOGGER.info("The plugin ({}) has been stopped", descriptor.getId());
    }

    void resume() {
        List<Runnable> runnables = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            runnables.add(() -> {
                try {
                    extension.resume();
                } catch (Exception | LinkageError e) {
                    throw new RuntimeException(
                            "Caught an error while resuming the extension: "
                                    + extension.getClass()
                                            .getName(),
                            e);
                }
            });
        }
        ThrowableUtil.delayError(runnables,
                "Caught errors while resuming extensions of the plugin: "
                        + descriptor.getId());
        LOGGER.info("The plugin ({}) has been resumed", descriptor.getId());
    }

    void pause() {
        List<Runnable> runnables = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            runnables.add(() -> {
                try {
                    extension.pause();
                } catch (Exception | LinkageError e) {
                    throw new RuntimeException(
                            "Caught an error while pausing the extension: "
                                    + extension.getClass()
                                            .getName(),
                            e);
                }
            });
        }
        ThrowableUtil.delayError(runnables,
                "Caught errors while pausing extensions of the plugin: "
                        + descriptor.getId());
        LOGGER.info("The plugin ({}) has been paused", descriptor.getId());
    }

    abstract void closeContext();

}