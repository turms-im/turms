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

package im.turms.server.common.tracing;

import im.turms.common.util.RandomUtil;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.FieldNameConstants;
import org.apache.logging.log4j.ThreadContext;
import reactor.core.scheduler.Schedulers;

/**
 * @author James Chen
 */
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@FieldNameConstants
@ToString(onlyExplicitlyIncluded = true)
public class TracingContext {

    public static final String SCHEDULE_HOOK_NAME = "TRACING";

    public static final TracingContext NOOP = new TracingContext(0) {
        @Override
        public String getTraceIdStr() {
            return super.getTraceIdStr();
        }

        @Override
        public boolean hasTraceId() {
            return false;
        }

        @Override
        public void updateMdc() {
        }

        @Override
        public void clearMdc() {
        }
    };

    static {
        Schedulers.onScheduleHook(SCHEDULE_HOOK_NAME, task -> {
            String traceId = ThreadContext.get(Fields.traceId);
            // Make sure the traceId is removed on the current thread
            // because the traceId will be misused if the current thread is scheduled to handle other requests
            if (traceId != null) {
                ThreadContext.remove(Fields.traceId);
            }
            return () -> {
                if (traceId == null) {
                    task.run();
                } else {
                    ThreadContext.put(Fields.traceId, traceId);
                    try {
                        task.run();
                    } finally {
                        ThreadContext.remove(Fields.traceId);
                    }
                }
            };
        });
    }

    @EqualsAndHashCode.Include
    @ToString.Include
    private final long traceId;
    private String traceIdStr;

    public TracingContext(long traceId) {
        this.traceId = traceId;
    }

    public TracingContext() {
        // TODO: use a global unique trace ID
        this.traceId = RandomUtil.nextPositiveLong();
    }

    public String getTraceIdStr() {
        if (traceIdStr == null) {
            traceIdStr = String.valueOf(traceId);
        }
        return traceIdStr;
    }

    public boolean hasTraceId() {
        return true;
    }

    public void updateMdc() {
        ThreadContext.put(Fields.traceId, getTraceIdStr());
    }

    public void clearMdc() {
        String currentTraceId = ThreadContext.get(Fields.traceId);
        if (currentTraceId != null && currentTraceId.equals(getTraceIdStr())) {
            ThreadContext.remove(Fields.traceId);
        }
    }

    public TracingCloseableContext asCloseable() {
        return new TracingCloseableContext(this);
    }

}
