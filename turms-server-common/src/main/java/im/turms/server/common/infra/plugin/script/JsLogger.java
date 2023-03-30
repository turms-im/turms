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

package im.turms.server.common.infra.plugin.script;

import im.turms.server.common.infra.logging.core.logger.Logger;
import im.turms.server.common.infra.logging.core.logger.LoggerFactory;

/**
 * @author James Chen
 */
public class JsLogger {

    private final Logger logger;

    public JsLogger(String name) {
        String loggerName = JsLogger.class.getName()
                + "."
                + name;
        logger = LoggerFactory.getLogger(loggerName);
    }

    private String stringify(Object... msgs) {
        int length = msgs.length;
        if (length == 0) {
            return "";
        } else if (length == 1) {
            Object msg = msgs[0];
            if (msg == null) {
                return "";
            } else if (msg instanceof String s) {
                return s;
            } else {
                return String.valueOf(msg);
            }
        } else {
            StringBuilder builder = new StringBuilder(length * 8);
            for (Object msg : msgs) {
                builder.append(msg);
            }
            return builder.toString();
        }
    }

    public void error(Object... msgs) {
        if (logger.isErrorEnabled()) {
            logger.error(stringify(msgs));
        }
    }

    public void warn(Object... msgs) {
        if (logger.isWarnEnabled()) {
            logger.warn(stringify(msgs));
        }
    }

    public void info(Object... msgs) {
        if (logger.isInfoEnabled()) {
            logger.info(stringify(msgs));
        }
    }

    public void debug(Object... msgs) {
        if (logger.isDebugEnabled()) {
            logger.debug(stringify(msgs));
        }
    }

}