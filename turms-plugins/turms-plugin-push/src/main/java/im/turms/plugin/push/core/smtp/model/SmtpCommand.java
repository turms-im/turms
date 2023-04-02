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

import lombok.Getter;

import im.turms.server.common.infra.lang.StringUtil;

/**
 * @author James Chen
 */
public enum SmtpCommand {
    EHLO("EHLO"),
    AUTH("AUTH"),
    MAIL("MAIL"),
    RCPT("RCPT"),
    DATA("DATA"),
    NOOP("NOOP"),
    RSET("RSET"),
    STARTTLS("STARTTLS"),
    QUIT("QUIT");

    @Getter
    private final String name;

    @Getter
    private final byte[] nameBytes;

    SmtpCommand(String name) {
        this.name = name;
        nameBytes = StringUtil.getBytes(name);
    }

}