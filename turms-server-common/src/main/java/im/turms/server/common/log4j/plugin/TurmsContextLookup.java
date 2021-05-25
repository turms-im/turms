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

package im.turms.server.common.log4j.plugin;


import im.turms.server.common.cluster.node.Node;
import im.turms.server.common.cluster.node.NodeType;
import im.turms.server.common.log4j.AdminApiLogging;
import im.turms.server.common.log4j.ClientApiLogging;
import im.turms.server.common.log4j.LogContextConstant;
import im.turms.server.common.log4j.UserActivityLogging;
import im.turms.server.common.util.ReflectionUtil;
import lombok.SneakyThrows;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.async.AsyncLogger;
import org.apache.logging.log4j.core.async.RingBufferLogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

import java.lang.invoke.VarHandle;

/**
 * @author James Chen
 */
@Plugin(name = "myctx", category = StrLookup.CATEGORY)
public class TurmsContextLookup implements StrLookup {

    private static final VarHandle LOGGER = ReflectionUtil.getVarHandle(RingBufferLogEvent.class, "asyncLogger");

    @Override
    public String lookup(String key) {
        return null;
    }

    /**
     * @param event should always be RingBufferLogEvent
     */
    @SneakyThrows
    @Override
    public String lookup(LogEvent event, String key) {
        if (!(event instanceof RingBufferLogEvent logEvent)) {
            return null;
        }
        return switch (key) {
            case LogContextConstant.LOG_TYPE -> {
                AsyncLogger logger = (AsyncLogger) LOGGER.get(logEvent);
                if (logger == ClientApiLogging.logger) {
                    yield LogContextConstant.LogType.CLIENT_API;
                } else if (logger == UserActivityLogging.logger) {
                    yield LogContextConstant.LogType.USER_ACTIVITY;
                } else if (logger == AdminApiLogging.logger) {
                    yield LogContextConstant.LogType.ADMIN_API;
                } else {
                    yield null;
                }
            }
            case LogContextConstant.NODE_TYPE -> {
                NodeType nodeType = Node.getNodeType();
                if (nodeType == null) {
                    yield "";
                }
                yield switch (nodeType) {
                    case SERVICE -> LogContextConstant.NodeType.SERVICE;
                    case GATEWAY -> LogContextConstant.NodeType.GATEWAY;
                };
            }
            case LogContextConstant.NODE_ID -> {
                String nodeId = Node.getNodeId();
                yield nodeId == null ? "" : nodeId;
            }
            default -> throw new IllegalStateException("Unexpected value: " + key);
        };
    }

}