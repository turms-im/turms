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

import im.turms.plugin.push.core.smtp.model.SmtpResponse;

/**
 * @author James Chen
 */
public abstract class ResponseCollector<T> {

    final Sinks.One<T> sink;

    protected ResponseCollector(Sinks.One<T> sink) {
        this.sink = sink;
    }

    Sinks.One<T> getSink() {
        return sink;
    }

    abstract boolean addResponse(SmtpResponse response);

    abstract void emitError(Throwable cause);

    public static SmtpResponseCollector one(int expectedCode) {
        return new SmtpResponseCollector(expectedCode);
    }

    public static SmtpResponseCollector one() {
        return new SmtpResponseCollector();
    }

    public static SmtpResponseGroupCollector many(int count) {
        return new SmtpResponseGroupCollector(count);
    }

}