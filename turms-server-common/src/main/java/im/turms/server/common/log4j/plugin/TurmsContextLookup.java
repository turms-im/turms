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

package im.turms.server.common.log4j.plugin;


import im.turms.server.common.log4j.LogContextConstant;
import im.turms.server.common.log4j.UserActivityLogging;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.lookup.StrLookup;

/**
 * @author James Chen
 */
@Plugin(name = "myctx", category = StrLookup.CATEGORY)
public class TurmsContextLookup implements StrLookup {

    private static final String ADMIN_ACTIVITY_LOGGING_CLASS_NAME = "im.turms.turms.workflow.service.impl.log.AdminActionLogService";
    private static final String USER_ACTIVITY_LOGGING_CLASS_NAME = UserActivityLogging.class.getName();

    @Override
    public String lookup(String key) {
        return null;
    }

    @Override
    public String lookup(LogEvent event, String key) {
        if (LogContextConstant.LOG_TYPE.equals(key)) {
            if (USER_ACTIVITY_LOGGING_CLASS_NAME.equals(event.getLoggerName())) {
                return LogContextConstant.Type.USER_ACTIVITY;
            } else if (ADMIN_ACTIVITY_LOGGING_CLASS_NAME.equals(event.getLoggerName())) {
                return LogContextConstant.Type.ADMIN_ACTIVITY;
            }
        }
        return null;
    }

}