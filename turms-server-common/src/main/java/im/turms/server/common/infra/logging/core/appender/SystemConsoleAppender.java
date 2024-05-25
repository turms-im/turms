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

package im.turms.server.common.infra.logging.core.appender;

import im.turms.server.common.infra.logging.core.model.LogLevel;
import im.turms.server.common.infra.logging.core.model.LogRecord;
import im.turms.server.common.infra.netty.ByteBufUtil;

public class SystemConsoleAppender extends Appender {

    public SystemConsoleAppender(LogLevel level) {
        super(level);
    }

    @Override
    public int append(LogRecord record) {
        if (!record.level()
                .isLoggable(level)) {
            return 0;
        }
        String s = ByteBufUtil.getString(record.data());
        if (record.level()
                .isErrorOrFatal()) {
            System.err.println(s);
        } else {
            System.out.println(s);
        }
        return record.data()
                .readableBytes();
    }

}