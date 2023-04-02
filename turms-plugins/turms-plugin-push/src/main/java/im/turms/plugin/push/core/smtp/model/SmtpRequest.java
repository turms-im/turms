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

package im.turms.plugin.push.core.smtp.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import jakarta.annotation.Nullable;

import io.netty.util.internal.ObjectUtil;

import im.turms.server.common.infra.collection.CollectionUtil;

/**
 * @author James Chen
 */
public record SmtpRequest(
        SmtpCommand command,
        List<String> parameters
) {

    public static final SmtpRequest DATA = SmtpRequest.create(SmtpCommand.DATA);
    public static final SmtpRequest NOOP = SmtpRequest.create(SmtpCommand.NOOP);
    public static final SmtpRequest RSET = SmtpRequest.create(SmtpCommand.RSET);
    public static final SmtpRequest STARTTLS = SmtpRequest.create(SmtpCommand.STARTTLS);
    public static final SmtpRequest QUIT = SmtpRequest.create(SmtpCommand.QUIT);

    public static SmtpRequest create(SmtpCommand command, List<String> parameters) {
        return new SmtpRequest(command, parameters);
    }

    public static SmtpRequest create(SmtpCommand command) {
        return new SmtpRequest(command, Collections.emptyList());
    }

    public static SmtpRequest create(SmtpCommand command, String parameter) {
        return new SmtpRequest(command, Collections.singletonList(parameter));
    }

    public static SmtpRequest ehlo(String hostname) {
        return SmtpRequest.create(SmtpCommand.EHLO, hostname);
    }

    public static SmtpRequest auth(List<String> parameter) {
        return SmtpRequest.create(SmtpCommand.AUTH, parameter);
    }

    public static SmtpRequest rcpt(String recipient) {
        return SmtpRequest.create(SmtpCommand.RCPT,
                "TO:<"
                        + recipient
                        + '>');
    }

    public static SmtpRequest rcpt(String recipient, @Nullable List<String> rcptParameters) {
        ObjectUtil.checkNotNull(recipient, "recipient");
        String parameter = "TO:<"
                + recipient
                + '>';
        if (rcptParameters == null || rcptParameters.isEmpty()) {
            return SmtpRequest.create(SmtpCommand.RCPT, parameter);
        }
        List<String> params = new ArrayList<>(rcptParameters.size() + 1);
        params.add(parameter);
        params.addAll(rcptParameters);
        return new SmtpRequest(SmtpCommand.RCPT, params);
    }

    public static List<SmtpRequest> rcptRequests(Collection<String> recipients) {
        return CollectionUtil.transformAsList(recipients, SmtpRequest::rcpt);
    }

    public static SmtpRequest mailWith8BitMime(String sender) {
        List<String> params = new ArrayList<>(2);
        params.add("FROM:<"
                + sender
                + '>');
        params.add("BODY=8BITMIME");
        return new SmtpRequest(SmtpCommand.MAIL, params);
    }

}