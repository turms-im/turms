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

package im.turms.server.common.infra.property;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.boot.context.properties.ConfigurationPropertiesBindingPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.mergeProperties;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.toJsonNode;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.toMutablePropertiesJsonNode;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.validatePropertiesForUpdating;
import static im.turms.server.common.infra.property.TurmsPropertiesSerializer.persist;
import static im.turms.server.common.infra.property.TurmsPropertiesValidator.validate;

/**
 * @author James Chen
 */
@Component
public class TurmsPropertiesManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsPropertiesManager.class);

    public final List<Consumer<TurmsProperties>> localPropertiesChangeListeners =
            new LinkedList<>();

    private static final TurmsProperties DEFAULT_PROPERTIES = new TurmsProperties();
    private static final JsonNode DEFAULT_PROPERTIES_JSON_NODE =
            toMutablePropertiesJsonNode(DEFAULT_PROPERTIES);

    private final Path latestConfigFilePath;
    private final Node node;
    private final ApplicationContext context;
    private TurmsProperties localTurmsProperties;

    /**
     * @param node is lazy because: Node -> TurmsPropertiesManager -> Node
     */
    public TurmsPropertiesManager(
            @Lazy Node node,
            ApplicationContext context,
            TurmsProperties localTurmsProperties,
            TurmsApplicationContext applicationContext) {
        this.node = node;
        this.context = context;
        this.localTurmsProperties = localTurmsProperties;
        // Get latestConfigFilePath according to the active profiles
        String activeProfile = applicationContext.getActiveEnvProfile();
        String latestConfigFileName = activeProfile == null
                ? "application-latest.yaml"
                : "application-"
                        + activeProfile
                        + "-latest.yaml";
        latestConfigFilePath = Path.of(applicationContext.getConfigDir()
                + "/"
                + latestConfigFileName);
        InvalidPropertyException exception = validate(localTurmsProperties);
        if (exception != null) {
            throw exception;
        }
    }

    public TurmsProperties getGlobalProperties() {
        return node.getSharedProperties();
    }

    /**
     * Use the instance of TurmsPropertiesManager instead of TurmsProperties instance so that we can
     * update the global TurmsProperties instance easily by replacing its reference
     */
    public TurmsProperties getLocalProperties() {
        return localTurmsProperties;
    }

    // Load

    public <T> T loadProperties(Class<T> propertiesClass) {
        Constructor<T> constructor;
        try {
            constructor = propertiesClass.getDeclaredConstructor();
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    "The properties class ("
                            + propertiesClass.getName()
                            + ") must have a public no-arg constructor",
                    e);
        }
        T properties;
        try {
            properties = constructor.newInstance();
        } catch (Exception e) {
            throw new RuntimeException(
                    "Failed to initialize the properties class: "
                            + propertiesClass.getName(),
                    e);
        }
        String s = propertiesClass.getName() + UUID.randomUUID();
        return (T) context.getBean(ConfigurationPropertiesBindingPostProcessor.class)
                .postProcessBeforeInitialization(properties, s);
    }

    // Update

    public Mono<Void> updateGlobalProperties(
            boolean reset,
            Map<String, Object> propertiesForUpdating) {
        if (reset) {
            return node.updateSharedProperties(DEFAULT_PROPERTIES);
        }
        if (propertiesForUpdating == null || propertiesForUpdating.isEmpty()) {
            return Mono.empty();
        }
        InvalidPropertyException exception =
                validatePropertiesForUpdating(DEFAULT_PROPERTIES, propertiesForUpdating);
        if (exception != null) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
        }
        TurmsProperties properties = mergeProperties(getGlobalProperties(), propertiesForUpdating);
        exception = validate(properties);
        if (exception != null) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
        }
        return node.updateSharedProperties(properties);
    }

    public void updateLocalProperties(boolean reset, Map<String, Object> propertiesForUpdating) {
        TurmsProperties newLocalProperties;
        JsonNode newPropertiesJsonNode;
        if (reset) {
            newLocalProperties = DEFAULT_PROPERTIES;
            newPropertiesJsonNode = DEFAULT_PROPERTIES_JSON_NODE;
        } else {
            if (propertiesForUpdating == null || propertiesForUpdating.isEmpty()) {
                return;
            }
            InvalidPropertyException exception =
                    validatePropertiesForUpdating(DEFAULT_PROPERTIES, propertiesForUpdating);
            if (exception != null) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
            }
            newPropertiesJsonNode = toJsonNode(propertiesForUpdating);
            newLocalProperties = mergeProperties(getGlobalProperties(), newPropertiesJsonNode);
            exception = validate(newLocalProperties);
            if (exception != null) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
            }
        }
        try {
            localTurmsProperties = newLocalProperties;
            persist(latestConfigFilePath, newPropertiesJsonNode);
        } catch (IOException e) {
            LOGGER.error("Failed to persist new turms properties", e);
        }
        notifyLocalPropertiesChangeListeners(newLocalProperties);
    }

    // Listener

    public void addGlobalPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        node.addPropertiesChangeListener(listener);
    }

    public void notifyAndAddGlobalPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        listener.accept(getGlobalProperties());
        addGlobalPropertiesChangeListener(listener);
    }

    public void addLocalPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        localPropertiesChangeListeners.add(listener);
    }

    public void notifyAndAddLocalPropertiesChangeListener(Consumer<TurmsProperties> listener) {
        listener.accept(getLocalProperties());
        localPropertiesChangeListeners.add(listener);
    }

    public void notifyLocalPropertiesChangeListeners(TurmsProperties properties) {
        for (Consumer<TurmsProperties> listener : localPropertiesChangeListeners) {
            try {
                listener.accept(properties);
            } catch (Exception e) {
                LOGGER.error(
                        "Caught an error while notifying the local properties listener ({}) to handle new properties",
                        listener.getClass()
                                .getName(),
                        e);
            }
        }
    }

}
