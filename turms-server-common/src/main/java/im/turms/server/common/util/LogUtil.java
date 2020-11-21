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

package im.turms.server.common.util;

import im.turms.server.common.tracing.TracingContext;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import reactor.util.context.ContextView;

import java.io.Closeable;

/**
 * @author James Chen
 */
public class LogUtil {

    private LogUtil() {
    }

    public static void error(Logger logger, ContextView contextView, String message) {
        try (TurmsContext ignored = new TurmsContext(contextView)) {
            logger.error(message);
        }
    }

    public static void error(Logger logger, ContextView contextView, String message, Throwable throwable) {
        try (TurmsContext ignored = new TurmsContext(contextView)) {
            logger.error(message, throwable);
        }
    }

    public static void error(Logger logger, ContextView contextView, String message, Object p0, Throwable throwable) {
        try (TurmsContext ignored = new TurmsContext(contextView)) {
            logger.error(message, p0, throwable);
        }
    }

    public static void error(Logger logger, ContextView contextView, String message, Object p0, Object p1, Throwable throwable) {
        try (TurmsContext ignored = new TurmsContext(contextView)) {
            logger.error(message, p0, p1, throwable);
        }
    }

    private static class TurmsContext implements Closeable {
        private final boolean isInitialized;

        public TurmsContext(ContextView contextView) {
            TracingContext tracingContext = contextView.hasKey(TracingContext.CTX_KEY_NAME)
                    ? contextView.get(TracingContext.CTX_KEY_NAME)
                    : null;
            if (tracingContext == null) {
                isInitialized = false;
            } else {
                ThreadContext.put(TracingContext.Fields.traceId, tracingContext.getTraceIdStr());
                isInitialized = true;
            }
        }

        @Override
        public void close() {
            if (isInitialized) {
                ThreadContext.remove(TracingContext.Fields.traceId);
            }
        }
    }
}