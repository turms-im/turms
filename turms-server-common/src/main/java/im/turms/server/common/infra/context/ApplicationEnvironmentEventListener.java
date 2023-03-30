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

package im.turms.server.common.infra.context;

import org.springframework.boot.context.event.ApplicationContextInitializedEvent;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;

import im.turms.server.common.infra.cluster.node.Node;
import im.turms.server.common.infra.cluster.node.NodeType;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;
import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.property.env.common.logging.ConsoleLoggingProperties;
import im.turms.server.common.infra.property.env.common.logging.FileLoggingCompressionProperties;
import im.turms.server.common.infra.property.env.common.logging.FileLoggingProperties;
import im.turms.server.common.infra.property.env.common.logging.LoggingProperties;

/**
 * @author James Chen
 */
@Order(LoggingApplicationListener.DEFAULT_ORDER + 1)
public class ApplicationEnvironmentEventListener
        implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    /**
     * @implNote We don't use {@link ApplicationContextInitializedEvent} because it's still too late
     *           for logging
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        configureContextForLogging(event);
    }

    private void configureContextForLogging(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();
        // Though it is more reasonable to init the node type/ID in
        // "im.turms.server.common.infra.cluster.node.Node",
        // we need to ensure the local node info is logged even if the local node hasn't been
        // inited.
        // So we initialize the node info here.
        String applicationClassName = event.getSpringApplication()
                .getMainApplicationClass()
                .getSimpleName();
        NodeType nodeType = applicationClassName.equals("TurmsGatewayApplication")
                ? NodeType.GATEWAY
                : NodeType.SERVICE;
        Node.initNodeId(env.getProperty("turms.cluster.node.id", String.class));

        ConsoleLoggingProperties consoleLoggingProperties = ConsoleLoggingProperties.builder()
                .enabled(env.getProperty("turms.logging.console.enabled",
                        Boolean.class,
                        ConsoleLoggingProperties.DEFAULT_VALUE_ENABLED))
                .level(env.getProperty("turms.logging.console.level",
                        LogLevel.class,
                        ConsoleLoggingProperties.DEFAULT_VALUE_LEVEL))
                .build();
        FileLoggingProperties fileLoggingProperties = FileLoggingProperties.builder()
                .enabled(env.getProperty("turms.logging.file.enabled",
                        Boolean.class,
                        FileLoggingProperties.DEFAULT_VALUE_ENABLED))
                .level(env.getProperty("turms.logging.file.level",
                        LogLevel.class,
                        FileLoggingProperties.DEFAULT_VALUE_LEVEL))
                .filePath(env.getProperty("turms.logging.file.filePath",
                        String.class,
                        FileLoggingProperties.DEFAULT_VALUE_FILE_PATH))
                .maxFiles(env.getProperty("turms.logging.file.maxFiles",
                        Integer.class,
                        FileLoggingProperties.DEFAULT_VALUE_MAX_FILES))
                .maxFileSizeMb(env.getProperty("turms.logging.file.maxFileSizeMb",
                        Integer.class,
                        FileLoggingProperties.DEFAULT_VALUE_FILE_SIZE_MB))
                .compression(new FileLoggingCompressionProperties(
                        env.getProperty("turms.logging.file.compression.enabled",
                                Boolean.class,
                                FileLoggingCompressionProperties.DEFAULT_VALUE_ENABLED)))
                .build();
        LoggingProperties loggingProperties = new LoggingProperties().toBuilder()
                .console(consoleLoggingProperties)
                .file(fileLoggingProperties)
                .build();

        LoggerFactory.init(nodeType, Node.getNodeId(), loggingProperties);
    }

}