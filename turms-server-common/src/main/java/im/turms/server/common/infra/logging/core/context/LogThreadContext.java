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

package im.turms.server.common.infra.logging.core.context;

import jakarta.annotation.Nullable;

import io.netty.util.concurrent.FastThreadLocal;

import im.turms.server.common.infra.tracing.TracingContext;

/**
 * @author James Chen
 */
public class LogThreadContext {

    // We only need to use "TracingContext" currently,
    // so just keep it simple, don't wrap it
    private static final FastThreadLocal<TracingContext> CONTEXT = new FastThreadLocal<>();

    private LogThreadContext() {
    }

    public static void put(TracingContext context) {
        CONTEXT.set(context);
    }

    @Nullable
    public static TracingContext get() {
        return CONTEXT.getIfExists();
    }

    public static void remove() {
        CONTEXT.remove();
    }

    @Nullable
    public static TracingContext removeAndGet() {
        TracingContext context = CONTEXT.getIfExists();
        CONTEXT.remove();
        return context;
    }

    public static void removeIfEquals(TracingContext context) {
        TracingContext currentContext = CONTEXT.getIfExists();
        if (currentContext == context) {
            CONTEXT.remove();
        }
    }
}