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
import java.util.function.Consumer;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import lombok.experimental.Accessors;
import reactor.core.publisher.Mono;

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

    @Setter(AccessLevel.PACKAGE)
    private Consumer<TurmsExtension> onExtensionStarted;

    Mono<Void> start() {
        List<Mono<Void>> startMonos = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            startMonos.add(extension.startExtension()
                    .onErrorMap(t -> new RuntimeException(
                            "Caught an error while starting the extension: "
                                    + extension.getClass()
                                            .getName(),
                            t))
                    .doOnSuccess(unused -> {
                        try {
                            onExtensionStarted.accept(extension);
                        } catch (Exception e) {
                            LOGGER.error(
                                    "Caught an error while notifying the onExtensionStarted listener: "
                                            + onExtensionStarted,
                                    e);
                        }
                    }));
        }
        return Mono.whenDelayError(startMonos)
                .onErrorMap(t -> new RuntimeException(
                        "Caught errors while starting the extensions of the plugin: "
                                + descriptor.getId()))
                .doOnSuccess(
                        unused -> LOGGER.info("The extensions of the plugin ({}) have been started",
                                descriptor.getId()));
    }

    Mono<Void> stop() {
        List<Mono<Void>> stopMonos = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            stopMonos.add(extension.stopExtension()
                    .onErrorMap(t -> new RuntimeException(
                            "Caught an error while stopping the extension: "
                                    + extension.getClass()
                                            .getName(),
                            t)));
        }
        return Mono.whenDelayError(stopMonos)
                .materialize()
                .flatMap(signal -> {
                    Throwable stopExtensionsException = signal.getThrowable();
                    RuntimeException closeContextException = null;
                    try {
                        closeContext();
                    } catch (RuntimeException e) {
                        closeContextException = e;
                    }
                    if (stopExtensionsException != null || closeContextException != null) {
                        RuntimeException e = new RuntimeException(
                                "Caught errors while stopping the extensions of the plugin: "
                                        + descriptor.getId());
                        if (stopExtensionsException != null) {
                            e.addSuppressed(stopExtensionsException);
                        }
                        if (closeContextException != null) {
                            e.addSuppressed(closeContextException);
                        }
                        return Mono.error(e);
                    }
                    LOGGER.info("The extensions of the plugin ({}) have been stopped",
                            descriptor.getId());
                    return Mono.empty();
                });
    }

    Mono<Void> resume() {
        List<Mono<Void>> resumeMonos = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            resumeMonos.add(extension.resumeExtension()
                    .onErrorMap(t -> new RuntimeException(
                            "Caught an error while resuming the extension: "
                                    + extension.getClass()
                                            .getName(),
                            t)));
        }
        return Mono.whenDelayError(resumeMonos)
                .onErrorMap(t -> new RuntimeException(
                        "Caught errors while resuming the extensions of the plugin: "
                                + descriptor.getId()))
                .doOnSuccess(
                        unused -> LOGGER.info("The extensions of the plugin ({}) have been resumed",
                                descriptor.getId()));
    }

    Mono<Void> pause() {
        List<Mono<Void>> pauseMonos = new ArrayList<>(extensions.size());
        for (TurmsExtension extension : extensions) {
            pauseMonos.add(extension.pauseExtension()
                    .onErrorMap(t -> new RuntimeException(
                            "Caught an error while pausing the extension: "
                                    + extension.getClass()
                                            .getName(),
                            t)));
        }
        return Mono.whenDelayError(pauseMonos)
                .onErrorMap(t -> new RuntimeException(
                        "Caught errors while pausing the extensions of the plugin: "
                                + descriptor.getId()))
                .doOnSuccess(
                        unused -> LOGGER.info("The extensions of the plugin ({}) have been paused",
                                descriptor.getId()));
    }

    abstract void closeContext();

}