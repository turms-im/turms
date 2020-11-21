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

    private static final MethodHandle GET_LOGGER;

    static {
        GET_LOGGER = InvokeUtil.getField(RingBufferLogEvent.class, "asyncLogger");
    }

    @Override
    public String lookup(String key) {
        return null;
    }

    /**
     * @implNote event should always be RingBufferLogEvent
     */
    @SneakyThrows
    @Override
    public String lookup(LogEvent event, String key) {
        if (LogContextConstant.LOG_TYPE.equals(key) && event instanceof RingBufferLogEvent) {
            RingBufferLogEvent logEvent = (RingBufferLogEvent) event;
            AsyncLogger logger = (AsyncLogger) GET_LOGGER.invokeExact(logEvent);
            if (logger == ClientApiLogging.logger) {
                return LogContextConstant.Type.CLIENT_API;
            } else if (logger == UserActivityLogging.logger) {
                return LogContextConstant.Type.USER_ACTIVITY;
            } else if (logger == AdminApiLogging.logger) {
                return LogContextConstant.Type.ADMIN_API;
            }
        }
        return null;
    }

}