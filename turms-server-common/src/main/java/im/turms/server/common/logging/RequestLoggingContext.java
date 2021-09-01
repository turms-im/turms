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

package im.turms.server.common.logging;

import im.turms.server.common.tracing.TracingContext;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import reactor.util.context.ContextView;

/**
 * @author James Chen
 */
@AllArgsConstructor
@NoArgsConstructor
public class RequestLoggingContext {

    @Getter
    @Setter
    private TracingContext tracingContext = TracingContext.NOOP;

    public static final RequestLoggingContext DEFAULT = new RequestLoggingContext(TracingContext.NOOP);
    public static final String CTX_KEY_NAME = "REQ";

    public static Long readTraceIdFromContext(ContextView context) {
        RequestLoggingContext loggingContext = context.getOrDefault(CTX_KEY_NAME, null);
        if (loggingContext == null) {
            return null;
        }
        TracingContext tracingContext = loggingContext.getTracingContext();
        if (tracingContext == TracingContext.NOOP) {
            return null;
        }
        return tracingContext.getTraceId();
    }

    public void clearMdc() {
        tracingContext.clearMdc();
    }

    public void updateMdc() {
        tracingContext.updateMdc();
    }

}