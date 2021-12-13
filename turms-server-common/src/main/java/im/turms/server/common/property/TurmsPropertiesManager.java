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

package im.turms.server.common.property;

import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.context.TurmsApplicationContext;
import im.turms.server.common.logging.core.logger.LoggerFactory;
import im.turms.server.common.logging.core.logger.Logger;
import lombok.Setter;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

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
    private static final String DEFAULT_PROPERTIES_STR = PropertiesUtil.toMutablePropertiesString(DEFAULT_PROPERTIES);

    private final Path latestConfigFilePath;
    @Setter
    private Node node;
    private TurmsProperties localTurmsProperties;

    /**
     * @param node is lazy because: Node -> TurmsPropertiesManager -> Node
     */
    public TurmsPropertiesManager(@Lazy Node node, TurmsProperties localTurmsProperties, TurmsApplicationContext context) {
        this.node = node;
        this.localTurmsProperties = localTurmsProperties;
        // Get latestConfigFilePath according to the active profiles
        String activeProfile = context.getActiveEnvProfile();
        // The property should be passed from "bin/run.sh"
        String configDir = System.getProperty("spring.config.location");
        if (configDir == null || configDir.isBlank()) {
            LOGGER.warn("The property \"spring.config.location\" is empty");
            configDir = "./config";
        }
        String latestConfigFileName = activeProfile != null
                ? "application-%s-latest.yaml".formatted(activeProfile)
                : "application-latest.yaml";
        latestConfigFilePath = Path.of("%s/%s".formatted(configDir, latestConfigFileName));
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
            Map<String, Object> propertiesForUpdating) throws IOException {
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
            newPropertiesStr = PropertiesUtil.toMutablePropertiesString(propertiesForUpdating);
            if ("{}".equals(newPropertiesStr)) {
                return;
            }
            newLocalProperties = PropertiesUtil.mergeAsProperties(
                    node.getSharedProperties(),
                    newPropertiesStr);
        }
        try {
            localTurmsProperties = newLocalProperties;
            PropertiesUtil.persist(latestConfigFilePath, newPropertiesStr);
        } catch (IOException e) {
            LOGGER.error("Failed to persist new turms properties", e);
        }
        notifyListeners(newLocalProperties);
    }

    public Mono<Void> updateGlobalProperties(
            boolean reset,
            Map<String, Object> turmsPropertiesForUpdating) throws IOException {
        if (reset) {
            return node.getSharedPropertyService().updateSharedProperties(new TurmsProperties());
        }
        if (turmsPropertiesForUpdating == null || turmsPropertiesForUpdating.isEmpty()) {
            return Mono.empty();
        }
        TurmsProperties properties = PropertiesUtil.mergeAsProperties(node.getSharedProperties(), turmsPropertiesForUpdating);
        return node.getSharedPropertyService().updateSharedProperties(properties);
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
