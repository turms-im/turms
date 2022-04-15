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

import im.turms.server.common.access.common.ResponseStatusCode;
import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.context.TurmsApplicationContext;
import im.turms.server.common.infra.exception.ResponseException;
import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.mergeProperties;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.toMutablePropertiesString;
import static im.turms.server.common.infra.property.TurmsPropertiesConvertor.validaPropertiesForUpdating;
import static im.turms.server.common.infra.property.TurmsPropertiesSerializer.persist;
import static im.turms.server.common.infra.property.TurmsPropertiesValidator.validate;

/**
 * To make the code of {@link TurmsProperties} clean, we separate the operation methods from it.
 *
 * @author James Chen
 */
@Component
public class TurmsPropertiesManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(TurmsPropertiesManager.class);

    public final List<Consumer<TurmsProperties>> propertiesChangeListeners = new LinkedList<>();

    private static final TurmsProperties DEFAULT_PROPERTIES = new TurmsProperties();
    private static final String DEFAULT_PROPERTIES_STR = toMutablePropertiesString(DEFAULT_PROPERTIES);

    private final Path latestConfigFilePath;
    private final Node node;
    private TurmsProperties localTurmsProperties;

    /**
     * @param node is lazy because: Node -> TurmsPropertiesManager -> Node
     */
    public TurmsPropertiesManager(@Lazy Node node,
                                  TurmsProperties localTurmsProperties,
                                  TurmsApplicationContext context) {
        this.node = node;
        this.localTurmsProperties = localTurmsProperties;
        // Get latestConfigFilePath according to the active profiles
        String activeProfile = context.getActiveEnvProfile();
        String latestConfigFileName = activeProfile == null
                ? "application-latest.yaml"
                : "application-%s-latest.yaml".formatted(activeProfile);
        latestConfigFilePath = Path.of("%s/%s".formatted(context.getConfigDir(), latestConfigFileName));
        InvalidPropertyException exception = validate(localTurmsProperties);
        if (exception != null) {
            throw exception;
        }
    }

    /**
     * Use the instance of TurmsPropertiesManager instead of TurmsProperties instance
     * so that we can update the global TurmsProperties instance easily by replacing its reference
     */
    public TurmsProperties getLocalProperties() {
        return localTurmsProperties;
    }

    // Update

    public void updateLocalProperties(
            boolean reset,
            Map<String, Object> propertiesForUpdating) {
        TurmsProperties newLocalProperties;
        // Convert new turms properties to String instead of byte[] because the properties will be saved
        // as a yaml file in the local file system later
        String newPropertiesStr;
        if (reset) {
            newLocalProperties = DEFAULT_PROPERTIES;
            newPropertiesStr = DEFAULT_PROPERTIES_STR;
        } else {
            if (propertiesForUpdating == null || propertiesForUpdating.isEmpty()) {
                return;
            }
            InvalidPropertyException exception = validaPropertiesForUpdating(DEFAULT_PROPERTIES, propertiesForUpdating);
            if (exception != null) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
            }
            newPropertiesStr = toMutablePropertiesString(propertiesForUpdating);
            newLocalProperties = mergeProperties(node.getSharedProperties(), newPropertiesStr);
            exception = validate(newLocalProperties);
            if (exception != null) {
                throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
            }
        }
        try {
            localTurmsProperties = newLocalProperties;
            persist(latestConfigFilePath, newPropertiesStr);
        } catch (IOException e) {
            LOGGER.error("Failed to persist new turms properties", e);
        }
        notifyListeners(newLocalProperties);
    }

    public Mono<Void> updateGlobalProperties(
            boolean reset,
            Map<String, Object> propertiesForUpdating) {
        if (reset) {
            return node.updateSharedProperties(DEFAULT_PROPERTIES);
        }
        if (propertiesForUpdating == null || propertiesForUpdating.isEmpty()) {
            return Mono.empty();
        }
        InvalidPropertyException exception = validaPropertiesForUpdating(DEFAULT_PROPERTIES, propertiesForUpdating);
        if (exception != null) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
        }
        TurmsProperties properties = mergeProperties(node.getSharedProperties(), propertiesForUpdating);
        exception = validate(properties);
        if (exception != null) {
            throw ResponseException.get(ResponseStatusCode.ILLEGAL_ARGUMENT, exception);
        }
        return node.updateSharedProperties(properties);
    }

    // Listener

    public void addListeners(Consumer<TurmsProperties> listener) {
        propertiesChangeListeners.add(listener);
    }

    public void notifyListeners(TurmsProperties properties) {
        for (Consumer<TurmsProperties> listener : propertiesChangeListeners) {
            try {
                listener.accept(properties);
            } catch (Exception e) {
                LOGGER.error("The properties listener {} failed to handle the new properties", listener.getClass().getName(), e);
            }
        }
    }

}
