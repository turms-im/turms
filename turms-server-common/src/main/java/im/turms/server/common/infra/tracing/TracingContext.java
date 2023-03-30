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

package im.turms.server.common.infra.tracing;

import lombok.Getter;
import lombok.Setter;
import reactor.core.scheduler.Schedulers;
import reactor.util.context.ContextView;

import im.turms.server.common.infra.logging.core.context.LogThreadContext;
import im.turms.server.common.infra.random.RandomUtil;

/**
 * @author James Chen
 */
public class TracingContext {

    public static final long UNSET_TRACE_ID = -1;

    public static final String CTX_KEY_NAME = "REQ";
    public static final String SCHEDULE_HOOK_NAME = "TRACING";
    public static final TracingContext NOOP = new TracingContext(UNSET_TRACE_ID) {
        @Override
        public void updateThreadContext() {
        }

        @Override
        public void clearThreadContext() {
        }

        @Override
        public long getTraceId() {
            return UNSET_TRACE_ID;
        }

        @Override
        public void setTraceId(long traceId) {
        }
    };
    private static final TracingCloseableContext CLOSEABLE_CONTEXT_NOOP = NOOP.asCloseable();

    static {
        Schedulers.onScheduleHook(SCHEDULE_HOOK_NAME, task -> {
            TracingContext context = LogThreadContext.removeAndGet();
            // Make sure the request context is removed on the current thread
            // because the request context will be misused if the current thread is scheduled to
            // handle other requests
            return () -> {
                if (context == null) {
                    task.run();
                } else {
                    LogThreadContext.put(context);
                    try {
                        task.run();
                    } finally {
                        LogThreadContext.remove();
                    }
                }
            };
        });
    }

    @Getter
    @Setter
    private long traceId;

    public TracingContext(TracingContext tracingContext) {
        this.traceId = tracingContext.traceId;
    }

    public TracingContext(long traceId) {
        this.traceId = traceId;
    }

    public TracingContext() {
        // TODO: use a global unique trace ID
        this.traceId = RandomUtil.nextPositiveLong();
    }

    public static TracingContext getContext(ContextView context) {
        TracingContext ctx = context.getOrDefault(CTX_KEY_NAME, null);
        return ctx == null
                ? NOOP
                : ctx;
    }

    public static TracingCloseableContext getCloseableContext(ContextView context) {
        TracingContext ctx = context.getOrDefault(CTX_KEY_NAME, null);
        return ctx == null
                ? CLOSEABLE_CONTEXT_NOOP
                : ctx.asCloseable();
    }

    public static long getTraceId(ContextView context) {
        TracingContext ctx = context.getOrDefault(CTX_KEY_NAME, null);
        if (ctx == null) {
            return UNSET_TRACE_ID;
        }
        return ctx.traceId;
    }

    public void updateThreadContext() {
        LogThreadContext.put(this);
    }

    public void clearThreadContext() {
        LogThreadContext.removeIfEquals(this);
    }

    public TracingCloseableContext asCloseable() {
        return new TracingCloseableContext(this);
    }

}