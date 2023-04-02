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

import java.util.ArrayList;
import java.util.List;

import reactor.core.publisher.Sinks;

import im.turms.plugin.push.core.smtp.model.SmtpResponse;
import im.turms.plugin.push.core.smtp.model.SmtpResponseGroup;

/**
 * @author James Chen
 */
public class SmtpResponseGroupCollector extends ResponseCollector<SmtpResponseGroup> {

    private final List<SmtpResponse> responses;
    private int remainingResponses;

    SmtpResponseGroupCollector(int expectedResponses) {
        super(Sinks.one());
        remainingResponses = expectedResponses;
        responses = new ArrayList<>(expectedResponses);
    }

    @Override
    boolean addResponse(SmtpResponse response) {
        if (remainingResponses == 0) {
            throw new IllegalStateException("All responses have already been collected");
        }
        remainingResponses--;
        responses.add(response);
        boolean complete = remainingResponses == 0;
        if (complete) {
            sink.tryEmitValue(new SmtpResponseGroup(responses));
        }
        return complete;
    }

    @Override
    void emitError(Throwable cause) {
        sink.tryEmitError(cause);
    }

}