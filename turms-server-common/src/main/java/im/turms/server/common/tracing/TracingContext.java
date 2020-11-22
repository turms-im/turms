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
@FieldNameConstants
public class TracingContext {

    public static final String SCHEDULE_HOOK_NAME = "TRACING";
    public static final String CTX_KEY_NAME = "TRACING";

    static {
        Schedulers.onScheduleHook(SCHEDULE_HOOK_NAME, task -> {
            String traceId = ThreadContext.get(Fields.traceId);
            // Make sure the traceId is remove on the current thread
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

    private final long traceId;

    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private final String traceIdStr;

    public TracingContext(Long traceId) {
        this.traceId = traceId;
        traceIdStr = Long.toString(traceId);
    }

    public void updateMdc() {
        ThreadContext.put(Fields.traceId, traceIdStr);
    }

    public void clearMdc() {
        String currentTraceId = ThreadContext.get(Fields.traceId);
        if (currentTraceId != null && currentTraceId.equals(traceIdStr)) {
            ThreadContext.remove(Fields.traceId);
        }
    }

}
