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

package im.turms.server.common.context;

import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.log4j.plugin.TurmsContextLookup;
import im.turms.server.common.property.env.common.LoggingProperties;
import org.apache.logging.log4j.core.async.AsyncLoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Order;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.boot.context.logging.LoggingApplicationListener;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;

import static org.apache.logging.log4j.LogManager.getContext;

/**
 * @author James Chen
 */
@Order(LoggingApplicationListener.DEFAULT_ORDER + 1)
public class ApplicationEnvironmentEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    /**
     * @implNote We don't use ApplicationContextInitializedEvent because it's still too late for logging
     */
    @Override
    public void onApplicationEvent(ApplicationEnvironmentPreparedEvent event) {
        configContextForLogging(event);
    }

    private void configContextForLogging(ApplicationEnvironmentPreparedEvent event) {
        ConfigurableEnvironment env = event.getEnvironment();

        // Though it's more reasonable to init the node type/ID in im.turms.server.common.cluster.node.Node,
        // we need to ensure the local node info is logged even if the local node hasn't been inited.
        // So we init the node info here
        String applicationClassName = event.getSpringApplication().getMainApplicationClass().getSimpleName();
        TurmsContextLookup.setNodeType(applicationClassName.equals("TurmsGatewayApplication")
                ? NodeType.GATEWAY
                : NodeType.SERVICE);
        Node.initNodeId(env.getProperty("turms.cluster.node.id", String.class));

        boolean enableConsoleAppender = env.getProperty("turms.logging.enable-console-appender",
                Boolean.class,
                LoggingProperties.ENABLE_CONSOLE_APPENDER_DEFAULT_VALUE);
        boolean enableFileAppender = env.getProperty("turms.logging.enable-file-appender",
                Boolean.class,
                LoggingProperties.ENABLE_FILE_APPENDER_DEFAULT_VALUE);

        AsyncLoggerContext context = (AsyncLoggerContext) getContext(false);
        Configuration config = context.getConfiguration();
        LoggerConfig loggerConfig = config.getRootLogger();

        if (!enableConsoleAppender || !enableFileAppender) {
            if (!enableConsoleAppender) {
                removeAppender(loggerConfig, "Console");
            }
            if (!enableFileAppender) {
                removeAppender(loggerConfig, "Routing");
            }
            context.updateLoggers();
        }
    }

    private void removeAppender(LoggerConfig loggerConfig, String appenderName) {
        if (loggerConfig.getAppenders().containsKey(appenderName)) {
            loggerConfig.removeAppender(appenderName);
        } else {
            throw new IllegalStateException("Cannot find appender %s".formatted(appenderName));
        }
    }

}