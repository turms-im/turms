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

package im.turms.server.common.infra.application;

import java.lang.annotation.Annotation;

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

import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_CLUSTER_NODE_ID;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_CONSOLE_ENABLED;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_CONSOLE_LEVEL;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_FILE_COMPRESSION_ENABLED;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_FILE_ENABLED;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_FILE_FILE_PATH;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_FILE_LEVEL;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_FILE_MAX_FILES;
import static im.turms.server.common.infra.property.HardcodedPropertyNameConst.TURMS_LOGGING_FILE_MAX_FILE_SIZE_MB;

/**
 * @author James Chen
 */
@Order(LoggingApplicationListener.DEFAULT_ORDER + 1)
public class ApplicationEnvironmentEventListener
        implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    /**
     * @implNote We don't use {@link ApplicationContextInitializedEvent} because it's still too late
     *           for logging.
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
        Class<?> mainApplicationClass = event.getSpringApplication()
                .getMainApplicationClass();
        String applicationClassName = mainApplicationClass.getSimpleName();
        NodeType nodeType = switch (applicationClassName) {
            case "TurmsAiServingApplication" -> NodeType.AI_SERVING;
            case "TurmsGatewayApplication" -> NodeType.GATEWAY;
            case "TurmsServiceApplication" -> NodeType.SERVICE;
            default -> {
                Class<?> currentClass = mainApplicationClass;
                do {
                    for (Annotation annotation : currentClass.getDeclaredAnnotations()) {
                        String annotationName = annotation.annotationType()
                                .getName();
                        if ("org.springframework.boot.test.context.SpringBootTest"
                                .equals(annotationName)) {
                            for (Object source : event.getSpringApplication()
                                    .getAllSources()) {
                                if (source instanceof Class<?> clazz) {
                                    switch (clazz.getSimpleName()) {
                                        case "TurmsAiServingApplication" -> {
                                            yield NodeType.AI_SERVING;
                                        }
                                        case "TurmsGatewayApplication" -> {
                                            yield NodeType.GATEWAY;
                                        }
                                        case "TurmsServiceApplication" -> {
                                            yield NodeType.SERVICE;
                                        }
                                    }
                                }
                            }
                            break;
                        }
                    }
                    currentClass = currentClass.getSuperclass();
                } while (currentClass != null);
                throw new RuntimeException(
                        "Unknown application class name: "
                                + applicationClassName);
            }
        };
        Node.initNodeId(env.getProperty(TURMS_CLUSTER_NODE_ID, String.class));

        ConsoleLoggingProperties consoleLoggingProperties = ConsoleLoggingProperties.builder()
                .enabled(env.getProperty(TURMS_LOGGING_CONSOLE_ENABLED,
                        Boolean.class,
                        ConsoleLoggingProperties.DEFAULT_VALUE_ENABLED))
                .level(env.getProperty(TURMS_LOGGING_CONSOLE_LEVEL,
                        LogLevel.class,
                        ConsoleLoggingProperties.DEFAULT_VALUE_LEVEL))
                .build();
        FileLoggingProperties fileLoggingProperties = FileLoggingProperties.builder()
                .enabled(env.getProperty(TURMS_LOGGING_FILE_ENABLED,
                        Boolean.class,
                        FileLoggingProperties.DEFAULT_VALUE_ENABLED))
                .level(env.getProperty(TURMS_LOGGING_FILE_LEVEL,
                        LogLevel.class,
                        FileLoggingProperties.DEFAULT_VALUE_LEVEL))
                .filePath(env.getProperty(TURMS_LOGGING_FILE_FILE_PATH,
                        String.class,
                        FileLoggingProperties.DEFAULT_VALUE_FILE_PATH))
                .maxFiles(env.getProperty(TURMS_LOGGING_FILE_MAX_FILES,
                        Integer.class,
                        FileLoggingProperties.DEFAULT_VALUE_MAX_FILES))
                .maxFileSizeMb(env.getProperty(TURMS_LOGGING_FILE_MAX_FILE_SIZE_MB,
                        Integer.class,
                        FileLoggingProperties.DEFAULT_VALUE_FILE_SIZE_MB))
                .compression(new FileLoggingCompressionProperties(
                        env.getProperty(TURMS_LOGGING_FILE_COMPRESSION_ENABLED,
                                Boolean.class,
                                FileLoggingCompressionProperties.DEFAULT_VALUE_ENABLED)))
                .build();
        LoggingProperties loggingProperties = new LoggingProperties().toBuilder()
                .console(consoleLoggingProperties)
                .file(fileLoggingProperties)
                .build();

        LoggerFactory.init(false, nodeType, Node.getNodeId(), loggingProperties);
    }

}