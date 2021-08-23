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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author James Chen
 */
public final class CustomLogger {

    public static final String LOG_FIELD_DELIMITER = "|";
    public static final Logger ADMIN_API_LOGGER = LogManager.getLogger(CustomLogger.class);
    public static final Logger CLIENT_API_LOGGER = LogManager.getLogger(CustomLogger.class);
    public static final Logger NOTIFICATION_LOGGER = LogManager.getLogger(CustomLogger.class);

//    public static final org.apache.logging.log4j.Logger slowlogLogger = LogManager.getLogger(Logger.class);

    private CustomLogger() {
    }

}