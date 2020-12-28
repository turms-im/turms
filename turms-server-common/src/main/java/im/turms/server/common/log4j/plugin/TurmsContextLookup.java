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
import im.turms.server.common.util.InvokeUtil;
import lombok.SneakyThrows;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.async.AsyncLogger;
import org.apache.logging.log4j.core.async.RingBufferLogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

import java.lang.invoke.MethodHandle;

/**
 * @author James Chen
 */
@Plugin(name = "myctx", category = StrLookup.CATEGORY)
public class TurmsContextLookup implements StrLookup {

    private static final MethodHandle GET_LOGGER = InvokeUtil.getField(RingBufferLogEvent.class, "asyncLogger");

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
        if (!(event instanceof RingBufferLogEvent)) {
            return null;
        }
        RingBufferLogEvent logEvent = (RingBufferLogEvent) event;
        switch (key) {
            case LogContextConstant.LOG_TYPE:
                AsyncLogger logger = (AsyncLogger) GET_LOGGER.invokeExact(logEvent);
                if (logger == ClientApiLogging.logger) {
                    return LogContextConstant.LogType.CLIENT_API;
                } else if (logger == UserActivityLogging.logger) {
                    return LogContextConstant.LogType.USER_ACTIVITY;
                } else if (logger == AdminApiLogging.logger) {
                    return LogContextConstant.LogType.ADMIN_API;
                } else {
                    return null;
                }
            case LogContextConstant.NODE_TYPE:
                NodeType nodeType = Node.getNodeType();
                if (nodeType == null) {
                    return "";
                }
                switch (nodeType) {
                    case SERVICE:
                        return LogContextConstant.NodeType.SERVICE;
                    case GATEWAY:
                        return LogContextConstant.NodeType.GATEWAY;
                    default:
                        throw new IllegalStateException("Unexpected value: " + Node.getNodeType());
                }
            case LogContextConstant.NODE_ID:
                String nodeId = Node.getNodeId();
                return nodeId == null ? "" : nodeId;
            default:
                return null;
        }
    }

}