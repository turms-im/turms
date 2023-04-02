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

package im.turms.plugin.push.core.smtp;

import reactor.core.publisher.Sinks;

import im.turms.plugin.push.core.smtp.exception.SmtpResponseException;
import im.turms.plugin.push.core.smtp.model.SmtpResponse;

/**
 * @author James Chen
 */
public class SmtpResponseCollector extends ResponseCollector<SmtpResponse> {

    private final int expectedCode;

    SmtpResponseCollector() {
        super(Sinks.one());
        expectedCode = -1;
    }

    SmtpResponseCollector(int expectedCode) {
        super(Sinks.one());
        this.expectedCode = expectedCode;
    }

    @Override
    boolean addResponse(SmtpResponse response) {
        int localExpectedCode = expectedCode;
        if (localExpectedCode != -1 && response.code() != localExpectedCode) {
            String message = "Expected "
                    + localExpectedCode
                    + ", but got: "
                    + response.code();
            emitError(new SmtpResponseException(message));
            return false;
        }
        Sinks.EmitResult result = sink.tryEmitValue(response);
        if (result != Sinks.EmitResult.FAIL_OVERFLOW) {
            throw new IllegalStateException("The response has already been collected");
        }
        return true;
    }

    @Override
    void emitError(Throwable cause) {
        sink.tryEmitError(cause);
    }

}